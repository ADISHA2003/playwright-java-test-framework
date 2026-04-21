package PlaywrightPageObject.Pojo.CompanyScreener;

import lombok.Data;

import java.util.List;
import java.math.BigDecimal;

@Data
public class Companies {
    String companyName;
    // Identifiers and Basic Info
    private String tradeName;
    private String description;
    private String website;
    private String previousNames;
    private String parentCompany;
    private List<String> subsidiaries;

    // Classification and Status
    private String companyType; // e.g., Private, Public
    private String companyStage; // e.g., Seed, Series A, IPO
    private String businessModel; // e.g., B2B, SaaS, E-commerce
    private String companyStatus; // e.g., Active, Acquired, Closed
    private String sector;
    private String industry;
    private String subIndustry;

    // Geographic and Founding Info
    private Integer foundedYear;
    private String city;
    private String country;

    // Employee and Financial Metrics
    private Integer employeeCount;
    private Double edgeScore; // Assuming a score/rating
    private String valuationClass; // e.g., Unicorn, Decacorn

    // Funding and Valuation
    private BigDecimal totalEquityFundingRaised; // Use BigDecimal for currency
    private BigDecimal latestPEValuation;
    private BigDecimal latestMarketCap;
    private BigDecimal netWorth;

    // Revenue and Profit
    private BigDecimal annualRevenue;
    private BigDecimal netSales;
    private BigDecimal netProfit;
    private BigDecimal ebitda; // Earnings Before Interest, Taxes, Depreciation, and Amortization

    // Financial Ratios and Growth
    private Double patMargin; // Percentage
    private Double grossProfitMargin; // Percentage
    private Double ebitdaMargin; // Percentage
    private Double revenueCAGR; // Compound Annual Growth Rate (Percentage)
    private Double ebitdaCAGR; // Percentage
    private Double evEbitda; // Enterprise Value / EBITDA
    private Double evRevenue; // Enterprise Value / Revenue

    // Investors Information
    private Double noOfInvestors;
    private String investorsName;

}
