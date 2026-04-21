import requests
import pandas as pd
from io import BytesIO
import json
import sys
import io
import warnings
import re
import traceback
import argparse

warnings.filterwarnings("ignore")
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding="utf-8")


def clean_number(x):
    """Convert numeric-like strings to float, keep None for missing values."""
    if isinstance(x, (int, float)):
        return x
    if isinstance(x, str):
        cleaned = re.sub(r"[^\d.\-]", "", x)
        if cleaned.strip() == "" or cleaned == "-":
            return None
        try:
            return float(cleaned)
        except ValueError:
            return None
    return None


def _detect_header_and_clean(df: pd.DataFrame) -> pd.DataFrame:
    """Detect header row, set it, strip text, remove top/bottom metadata rows."""
    # Detect header row
    header_row = None
    for i, row in df.iterrows():
        if row.notna().sum() >= len(row) / 2:
            header_row = i
            break
    if header_row is None:
        header_row = 0

    df.columns = df.iloc[header_row]
    df = df.iloc[header_row + 1:].dropna(how="all").reset_index(drop=True)

    # Strip whitespace from string fields
    df = df.applymap(lambda x: str(x).strip() if isinstance(x, str) else x)

    # Remove trailing metadata rows safely
    first_col = df.columns[0]
    df = df[~df[first_col].astype(str).str.contains("Copyright|Table of Contents", case=False, na=False)]

    df = df.reset_index(drop=True)
    return df


def extract_companies_from_s3(url: str, limit: int = 0):
    """Extracts and cleans data from all sheets of an Excel file hosted on S3."""
    response = requests.get(url, verify=False)
    response.raise_for_status()
    excel_file = BytesIO(response.content)

    # Read all sheets without headers
    sheets_raw = pd.read_excel(excel_file, sheet_name=None, header=None)

    # Column mapping for Companies sheet
    companies_mapping = {
        "Company Name": "companyName",
        "Trade Name": "tradeName",
        "Edge Score": "edgeScore",
        "Company Type": "companyType",
        "Founded Year": "foundedYear",
        "City": "city",
        "Company Stage": "companyStage",
        "Sub-Industry": "subIndustry",
        "Business Model": "businessModel",
        "Total Equity Funding Raised ₹cr": "totalEquityFundingRaised",
        "Latest PE Valuation ₹cr": "latestPEValuation",
        "Annual Revenue (2024) ₹cr": "annualRevenue",
        "Country": "country",
        "Sector": "sector",
        "Industry": "industry",
        "Company Status": "companyStatus",
        "Latest Market Cap ₹cr": "latestMarketCap",
        "Employee Count": "employeeCount",
        "PAT Margin (2024)": "patMargin",
        "EBITDA (2024) ₹cr": "ebitda",
        "Description": "description",
        "Valuation Class": "valuationClass",
        "Revenue CAGR (2021 - 2024)": "revenueCAGR",
        "EBITDA CAGR (2021 - 2024)": "ebitdaCAGR",
        "EBITDA Margin (2024)": "ebitdaMargin",
        "Gross Profit Margin (2024)": "grossProfitMargin",
        "Net Profit (2024) ₹cr": "netProfit",
        "No of Investors": "noOfInvestors",
        "Investors Name": "investorsName",
        "Parent Company": "parentCompany",
        "Previous Names": "previousNames",
        "Subsidiaries": "subsidiaries",
        "Net Sales ₹cr": "netSales",
        "Net Worth ₹cr": "netWorth",
        "EV/EBITDA": "evEbitda",
        "EV/Revenue": "evRevenue",
        "Website": "website"
    }

    # Column mapping for Linked Investors sheet
    linked_investors_mapping = {
        "Investor Name": "Investor Name",
        "Investments In": "Investments In",
        "Fund Type": "Fund Type",
        "Location": "Location",
        "Asset Under Management (AUM) $mn": "Asset Under Management (AUM) $mn",
        "Area of Interest": "Area of Interest",
        "Current Investments": "Current Investments",
        "Deals in last 12 months": "Deals in last 12 months",
        "Asset Manager": "Asset Manager",
        "Fund Size ₹cr": "Fund Size ₹cr",
        "Geographical Preference": "Geographical Preference",
        "Number of Deals": "Number of Deals",
        "Fund Status": "Fund Status",
        "Launched Date": "Launched Date",
        "Amount Raised ₹cr": "Amount Raised ₹cr",
        "Investment Type": "Investment Type",
        "Investment Size (Min) $mn": "Investment Size (Min) $mn",
        "Investment Size (Max) $mn": "Investment Size (Max) $mn",
        "Number of Exits": "Number of Exits",
        "Deal Type": "Deal Type",
        "Total Investment (All Deals Amount) ₹cr": "Total Investment (All Deals Amount) ₹cr"
    }

    # Column mapping for Linked Deals sheet
    linked_deals_mapping = {
        "Target Company": "Target Company",
        "Deal Date": "Deal Date",
        "Deal Description": "Deal Description",
        "Deal Type": "Deal Type",
        "Buyer/Lender": "Buyer/Lender",
        "Seller/Borrower": "Seller/Borrower",
        "Deal Value ₹cr": "Deal Value ₹cr",
        "Deal Subtype": "Deal Subtype",
        "Deal Feature": "Deal Feature",
        "Deal Status": "Deal Status",
        "Transaction Announced Date": "Transaction Announced Date",
        "Transaction Closing Date": "Transaction Closing Date",
        "Cancelled Date": "Cancelled Date",
        "Deal Stage": "Deal Stage",
        "Change in Control": "Change in Control",
        "% Sought": "% Sought",
        "EV/Revenue": "EV/Revenue",
        "EV/EBITDA": "EV/EBITDA",
        "EV/PAT": "EV/PAT",
        "Deal Details": "Deal Details"
    }

    # Column mapping for Linked Professionals sheet
    linked_professionals_mapping = {
        "Professionals Name": "Professionals Name",
        "Phone": "Phone",
        "Email": "Email",
        "Company Name": "Company Name",
        "Designation": "Designation",
        "Contact": "Contact"
    }

    # Numeric columns for Linked Investors
    linked_investors_numeric = [
        "Asset Under Management (AUM) $mn",
        "Current Investments",
        "Deals in last 12 months",
        "Fund Size ₹cr",
        "Number of Deals",
        "Amount Raised ₹cr",
        "Investment Size (Min) $mn",
        "Investment Size (Max) $mn",
        "Number of Exits",
        "Total Investment (All Deals Amount) ₹cr"
    ]

    # Numeric columns for Linked Deals
    linked_deals_numeric = [
        "Deal Value ₹cr",
        "% Sought",
        "EV/Revenue",
        "EV/EBITDA",
        "EV/PAT"
    ]

    output = {}

    for sheet_name, raw_df in sheets_raw.items():
        try:
            df = _detect_header_and_clean(raw_df)

            sheet_lower = str(sheet_name).strip().lower()

            if sheet_lower == "companies":
                df = df.rename(columns=companies_mapping)
                numeric_normalized = [
                    "edgeScore", "annualRevenue", "employeeCount", "patMargin",
                    "ebitda", "revenueCAGR", "ebitdaCAGR", "ebitdaMargin",
                    "grossProfitMargin", "netProfit", "noOfInvestors",
                    "netSales", "netWorth", "evEbitda", "evRevenue",
                    "totalEquityFundingRaised", "latestPEValuation", "latestMarketCap"
                ]
                for col in numeric_normalized:
                    if col in df.columns:
                        df[col] = df[col].apply(clean_number)

                if "subsidiaries" in df.columns:
                    df["subsidiaries"] = df["subsidiaries"].apply(
                        lambda x: [s.strip() for s in str(x).split(",")] if pd.notna(x) and x != "-" else []
                    )

            elif sheet_lower == "linked investors":
                df = df.rename(columns=linked_investors_mapping)
                for col in linked_investors_numeric:
                    if col in df.columns:
                        df[col] = df[col].apply(clean_number)

            elif sheet_lower == "linked deals":
                df = df.rename(columns=linked_deals_mapping)
                for col in linked_deals_numeric:
                    if col in df.columns:
                        df[col] = df[col].apply(clean_number)

            elif sheet_lower == "linked professionals":
                df = df.rename(columns=linked_professionals_mapping)
                # No numeric conversion needed for professionals sheet

            # Replace infinities and ensure JSON-friendly None for NaN
            df = df.replace([float("inf"), float("-inf")], None)
            df = df.where(pd.notnull(df), None)

            # Apply row limit if specified
            if limit > 0:
                df = df.head(limit)

            output[sheet_name] = df.to_dict(orient="records")
        except Exception:
            output[sheet_name] = {"error": "failed_to_parse_sheet", "trace": traceback.format_exc().splitlines()}

    return output


if __name__ == "__main__":
    try:
        parser = argparse.ArgumentParser(description="Extract data from VCCEdge S3 Excel export.")
        parser.add_argument("s3_url", help="The S3 URL of the Excel file.")
        parser.add_argument("--limit", type=int, default=0, help="Limit the number of rows to extract from each sheet. Default is 0 (all rows).")
        
        args = parser.parse_args()

        s3_url = args.s3_url
        all_sheets = extract_companies_from_s3(s3_url, args.limit)

        # Always print valid JSON
        print(json.dumps(all_sheets, ensure_ascii=False, allow_nan=True))
        sys.exit(0)

    except Exception as e:
        error_message = {
            "error": str(e),
            "trace": traceback.format_exc().splitlines()
        }
        # Always JSON output even for errors
        print(json.dumps(error_message, ensure_ascii=False))
        sys.exit(1)
