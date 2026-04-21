package utils;

import PlaywrightPageObject.Pojo.CompanyScreener.Companies;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.TimeoutError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.*;

/**
 * Utility class to extract table data from a Playwright-rendered page (live DOM)
 * and map it into Companies POJOs instead of using static HTML parsing.
 */
public class DataTableExtractor {

    // ObjectMapper configured to exclude null values
    private static final ObjectMapper MAPPER = createObjectMapper();
    // Use default mapper for printing to include null values for debugging
    private static final ObjectMapper PRINT_MAPPER = new ObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    // New method: extract a generic table (header -> cell text) as List of maps.
    // This resolves the compile error when test calls DataTableExtractor.extractTableRows(page, "table")
    public static List<Map<String, String>> extractTableRows(Page page, String tableSelector, int limit) {
        List<Map<String, String>> rowsResult = new ArrayList<>();
        try {
            // Wait for at least one cell to be present
            page.waitForSelector(tableSelector + " tbody tr td", new Page.WaitForSelectorOptions().setTimeout(15000));
            Locator table = page.locator(tableSelector).first();

            // Robust header extraction: try thead first, then fall back to the first visible row.
            List<String> headers = new ArrayList<>();
            Locator headerCells = table.locator("thead th");
            if (headerCells.count() == 0) {
                // Fallback if no <thead> or <th> found in thead, use the first row's cells
                headerCells = table.locator("tr").first().locator("th, td");
            }

            for (int i = 0; i < headerCells.count(); i++) {
                String h = headerCells.nth(i).innerText().trim();
                headers.add(h.isEmpty() ? "col" + i : h);
            }

            // Collect data rows: prefer tbody tr else all tr skipping header row
            Locator bodyRows = table.locator("tbody tr");
            int rowCount = (int) bodyRows.count();
            List<Locator> rowsList = new ArrayList<>();
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) rowsList.add(bodyRows.nth(i));
            } else {
                Locator allRows = table.locator("tr");
                int allCount = (int) allRows.count();
                for (int i = 1; i < allCount; i++) rowsList.add(allRows.nth(i));
            }

            for (Locator rowLoc : rowsList) {
                if (limit > 0 && rowsResult.size() >= limit) {
                    break; // Stop if we've reached the desired number of rows
                }
                Locator cells = rowLoc.locator("td, th");
                int cellCount = (int) cells.count();
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int c = 0; c < headers.size(); c++) {
                    String header = headers.get(c);
                    String cellText = "";
                    if (c < cellCount) {
                        Locator cell = cells.nth(c);
                        cellText = cell.innerText().trim();

                        // Generic handling for cells with <a>text</a><span>+N</span>
                        Locator linkInCell = cell.locator("a").first();
                        Locator spanInCell = cell.locator("span.read-more").first();
                        if (linkInCell.count() > 0 && spanInCell.count() > 0) {
                            String linkText = linkInCell.innerText().trim();
                            String spanText = spanInCell.innerText().trim();
                            cellText = linkText + spanText;
                        }
                        // Fallback: If cell text is empty, try specific selectors or textContent
                        else if (cellText.isEmpty()) {
                            Locator textEclipse = cell.locator(".text-eclipse").first();
                            if (textEclipse.count() > 0) {
                                cellText = textEclipse.innerText().trim();
                                if (cellText.isEmpty()) {
                                    cellText = textEclipse.textContent().trim();
                                }
                            }
                            if (cellText.isEmpty()) {
                                cellText = cell.textContent().trim();
                            }
                        }

                        if (cellText.isEmpty() && "contact".equalsIgnoreCase(normalizeHeader(header))) {
                            Locator link = cell.locator("a").first();
                            // Ensure link exists before getting attribute to prevent timeout
                            if (link.count() > 0) {
                                cellText = link.getAttribute("href");
                            }
                        }
                    }
                    if (cellText.equals("-")) cellText = "";
                    rowMap.put(header, cellText);
                }
                rowsResult.add(rowMap);
            }
        } catch (TimeoutError te) {
            System.err.println("Timeout waiting for table: " + te.getMessage());
        } catch (Exception e) {
            System.err.println("Error extracting table rows: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsResult;
    }

    // Compact mapping of normalized header -> setter for Companies
    private static final Map<String, java.util.function.BiConsumer<Companies, String>> COMPANY_SETTERS;
    static {
        COMPANY_SETTERS = new LinkedHashMap<>();
        // use normalizeHeader(...) keys so matching is consistent
        COMPANY_SETTERS.put(normalizeHeader("Subsidiaries"), (c, v) -> {
            if (v != null && !v.isEmpty()) c.setSubsidiaries(Arrays.asList(v.split(",")));
        });
        COMPANY_SETTERS.put(normalizeHeader("Company Name"), (c, v) -> c.setCompanyName(v));
        COMPANY_SETTERS.put(normalizeHeader("Trade Name"), (c, v) -> c.setTradeName(v));
        COMPANY_SETTERS.put(normalizeHeader("Description"), (c, v) -> c.setDescription(v));
        COMPANY_SETTERS.put(normalizeHeader("Website"), (c, v) -> c.setWebsite(v));
        COMPANY_SETTERS.put(normalizeHeader("Previous Names"), (c, v) -> c.setPreviousNames(v));
        COMPANY_SETTERS.put(normalizeHeader("Parent Company"), (c, v) -> c.setParentCompany(v));
        COMPANY_SETTERS.put(normalizeHeader("Company Type"), (c, v) -> c.setCompanyType(v));
        COMPANY_SETTERS.put(normalizeHeader("Company Stage"), (c, v) -> c.setCompanyStage(v));
        COMPANY_SETTERS.put(normalizeHeader("Business Model"), (c, v) -> c.setBusinessModel(v));
        COMPANY_SETTERS.put(normalizeHeader("Company Status"), (c, v) -> c.setCompanyStatus(v));
        COMPANY_SETTERS.put(normalizeHeader("Sector"), (c, v) -> c.setSector(v));
        COMPANY_SETTERS.put(normalizeHeader("Industry"), (c, v) -> c.setIndustry(v));
        COMPANY_SETTERS.put(normalizeHeader("Sub Industry"), (c, v) -> c.setSubIndustry(v));
        COMPANY_SETTERS.put(normalizeHeader("Founded Year"), (c, v) -> c.setFoundedYear(parseInt(v)));
        COMPANY_SETTERS.put(normalizeHeader("City"), (c, v) -> c.setCity(v));
        COMPANY_SETTERS.put(normalizeHeader("Country"), (c, v) -> c.setCountry(v));
        COMPANY_SETTERS.put(normalizeHeader("Employee Count"), (c, v) -> c.setEmployeeCount(parseInt(v)));
        COMPANY_SETTERS.put(normalizeHeader("Edge Score"), (c, v) -> c.setEdgeScore(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("Valuation Class"), (c, v) -> c.setValuationClass(v));
        COMPANY_SETTERS.put(normalizeHeader("Latest PE Valuation"), (c, v) -> c.setLatestPEValuation(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("Latest Market Cap"), (c, v) -> c.setLatestMarketCap(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("Net Worth"), (c, v) -> c.setNetWorth(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("Annual Revenue"), (c, v) -> c.setAnnualRevenue(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("Net Sales"), (c, v) -> c.setNetSales(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("Net Profit"), (c, v) -> c.setNetProfit(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("EBITDA"), (c, v) -> c.setEbitda(parseBigDecimal(v)));
        COMPANY_SETTERS.put(normalizeHeader("PAT Margin"), (c, v) -> c.setPatMargin(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("Gross Profit Margin"), (c, v) -> c.setGrossProfitMargin(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("EBITDA Margin"), (c, v) -> c.setEbitdaMargin(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("Revenue CAGR"), (c, v) -> c.setRevenueCAGR(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("EBITDA CAGR"), (c, v) -> c.setEbitdaCAGR(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("EV/EBITDA"), (c, v) -> c.setEvEbitda(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("EV/Revenue"), (c, v) -> c.setEvRevenue(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("No of Investors"), (c, v) -> c.setNoOfInvestors(parseDouble(v)));
        COMPANY_SETTERS.put(normalizeHeader("Investors Name"), (c, v) -> c.setInvestorsName(v));
    }

    // apply setter if mapping exists
    private static void applyCompanySetter(String normalizedKey, Companies company, String value) {
        java.util.function.BiConsumer<Companies, String> setter = COMPANY_SETTERS.get(normalizedKey);
        if (setter != null) {
            setter.accept(company, value);
        }
    }

    public static List<Companies> extractCompanies(Page page, String tableSelector, int limit) {
        return extractCompanies(page, tableSelector, limit, false);
    }

    public static List<Companies> extractCompanies(Page page, String tableSelector, int limit, boolean isLimited) {
        List<Companies> companiesList = new ArrayList<>();

        try {
            // Step 1: Wait until table data (spans/divs) is fully visible
            page.waitForSelector(tableSelector + " tbody tr td", new Page.WaitForSelectorOptions().setTimeout(20000));
            page.waitForTimeout(1500); // allow dynamic content to settle

            Locator rows = page.locator(tableSelector + " tbody tr");
            int rowCount = rows.count();
            if (rowCount == 0) {
                System.err.println("No rows found in table.");
                return companiesList;
            }

            // Extract headers dynamically
            List<String> headers = page.locator(tableSelector + " thead th").allTextContents();
            System.out.println("Found " + rowCount + " rows and " + headers.size());

            for (int i = 0; i < rowCount; i++) {
                if (limit > 0 && companiesList.size() >= limit) {
                    break; // Stop if we've reached the desired number of rows
                }
                Locator cells = rows.nth(i).locator("td");
                int cellCount = cells.count();
                Companies company = new Companies();

                for (int j = 0; j < cellCount && j < headers.size(); j++) {
                    // use normalized header helper for consistency
                    String normalizedHeader = normalizeHeader(headers.get(j));
                    // Robust extraction: get all visible text, fallback to nested span/div if empty
                    String value = (String) cells.nth(j).evaluate("cell => cell.innerText || cell.textContent || ''");
                    if (value == null || value.trim().isEmpty()) {
                        value = String.join(" ", cells.nth(j).locator("span, div").allInnerTexts()).trim();
                    }
                    value = value.replace("\u00A0", "").trim();
                    if (value.equals("-")) value = null;

                    // First handle special phrase match for "total equity funding raised"
                    if (normalizedHeader.contains("total equity funding raised")) {
                        company.setTotalEquityFundingRaised(parseBigDecimal(value));
                        continue;
                    }

                    // Compact dispatch instead of long switch
                    applyCompanySetter(normalizedHeader, company, value);
                }

                companiesList.add(company);
            }

            System.out.println("Extracted and mapped " + companiesList.size() + " companies successfully!");

            int sampleCount = (limit > 0) ? limit : 2;
            // Print 2 sample rows from UI extraction (only non-null fields)
            System.out.println("\n[UI EXTRACTION] Companies - Sample " + sampleCount + " rows:");
            try {
                for (int i = 0; i < Math.min(sampleCount, companiesList.size()); i++) {
                    System.out.println(PRINT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(companiesList.get(i)));
                }
            } catch (Exception e) {
                System.err.println("Error printing Companies samples: " + e.getMessage());
            }

        } catch (TimeoutError e) {
            System.err.println("Timeout waiting for table to load: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error extracting table data: " + e.getMessage());
            e.printStackTrace();
        }

        return companiesList;
    }

    // New: specialized mapper for LinkedInvestors (handles col0, col1, ... column names)
    public static List<LinkedInvestors> extractLinkedInvestors(Page page, String tableSelector, int limit) {
        List<Map<String, String>> rows = extractTableRows(page, tableSelector, limit);
        List<LinkedInvestors> result = new ArrayList<>();
        List<Map<String, Object>> cleanedMapsForPrint = new ArrayList<>();
        if (rows == null || rows.isEmpty()) return result;

        // Canonical keys by column position (fallback when headers are generic)
        Map<Integer, String> canonicalByPos = new LinkedHashMap<>();
        canonicalByPos.put(0, "Investor Name");
        canonicalByPos.put(1, "Investments In");
        canonicalByPos.put(2, "Fund Type");
        canonicalByPos.put(3, "Location");
        canonicalByPos.put(4, "Asset Under Management (AUM) $mn");
        canonicalByPos.put(5, "Area of Interest");
        canonicalByPos.put(6, "Current Investments");
        canonicalByPos.put(7, "Deals in last 12 months");

        // Headers that must remain strings (do not coerce)
        Set<String> stringOnlyNormalized = new HashSet<>(Arrays.asList(
            "investments in", "area of interest", "investors name"
        ));

        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        boolean genericHeaders = headers.stream().allMatch(h -> h != null && h.trim().toLowerCase().matches("^col\\d+$"));

        for (Map<String, String> row : rows) {
            Map<String, Object> cleaned = new LinkedHashMap<>();

            for (int pos = 0; pos < headers.size(); pos++) {
                String headerKey = headers.get(pos);
                String rawVal = row.get(headerKey);
                String val = sanitizeCell(rawVal);

                String targetKey;
                if (genericHeaders || headerKey.matches("^col\\d+$")) {
                    // If all headers are generic OR this specific header is generic, use canonical mapping
                    targetKey = canonicalByPos.getOrDefault(pos, headerKey);
                } else {
                    targetKey = headerKey.trim();
                }

                String norm = normalizeHeader(targetKey);
                if (val != null && stringOnlyNormalized.contains(norm)) {
                    // preserve the visible text (including "+N") as string
                    cleaned.put(targetKey, val);
                } else if (val != null && isNumericHeader(norm)) {
                    cleaned.put(targetKey, parseNumericValue(val));
                } else {
                    cleaned.put(targetKey, val);
                }
            }

            // Filter out rows where Investor Name is missing (e.g. empty rows)
            if (cleaned.get("Investor Name") == null) {
                continue;
            }

            cleanedMapsForPrint.add(cleaned); // Add the map before conversion
            LinkedInvestors li = MAPPER.convertValue(cleaned, LinkedInvestors.class);
            result.add(li);
        }

        int sampleCount = (limit > 0) ? limit : 2;
        System.out.println("\n[UI EXTRACTION] LinkedInvestors - Sample " + sampleCount + " rows:");
        try {
            for (int i = 0; i < Math.min(sampleCount, cleanedMapsForPrint.size()); i++) {
                System.out.println(PRINT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(cleanedMapsForPrint.get(i)));
            }
        } catch (Exception e) {
            System.err.println("Error printing LinkedInvestors samples: " + e.getMessage());
        }
        return result;
    }

    // New: map generic rows into LinkedDeals POJOs
    public static List<LinkedDeals> extractLinkedDeals(Page page, String tableSelector, int limit) {
        List<Map<String, String>> rows = extractTableRows(page, tableSelector, limit);
        List<LinkedDeals> result = new ArrayList<>();
        List<Map<String, Object>> cleanedMapsForPrint = new ArrayList<>();
        if (rows == null || rows.isEmpty()) return result;

        Map<Integer, String> canonicalByPos = new LinkedHashMap<>();
        canonicalByPos.put(0, "Target Company");
        canonicalByPos.put(1, "Deal Date");
        canonicalByPos.put(2, "Deal Description");
        canonicalByPos.put(3, "Deal Type");
        canonicalByPos.put(4, "Buyer/Lender");
        canonicalByPos.put(5, "Seller/Borrower");
        canonicalByPos.put(6, "Deal Value ?cr");
        canonicalByPos.put(7, "Deal Subtype");

        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        boolean genericHeaders = headers.stream().allMatch(h -> h != null && h.trim().toLowerCase().matches("^col\\d+$"));

        for (Map<String, String> row : rows) {
            Map<String, Object> cleaned = new LinkedHashMap<>();

            for (int pos = 0; pos < headers.size(); pos++) {
                String headerKey = headers.get(pos);
                String rawVal = row.get(headerKey);
                String val = sanitizeCell(rawVal);

                String targetKey;
                if (genericHeaders || headerKey.matches("^col\\d+$")) {
                    // If all headers are generic OR this specific header is generic, use canonical mapping
                    targetKey = canonicalByPos.getOrDefault(pos, headerKey);
                } else {
                    targetKey = headerKey.trim();
                }

                String norm = normalizeHeader(targetKey);
                // Keep date and descriptive fields as strings
                if ("deal date".equals(norm) || "deal description".equals(norm) || "deal subtype".equals(norm)
                        || "buyer/lender".equals(norm) || "seller/borrower".equals(norm)) {
                    cleaned.put(targetKey, val);
                } else if (val != null && isNumericHeader(norm)) {
                    cleaned.put(targetKey, parseNumericValue(val));
                } else {
                    cleaned.put(targetKey, val);
                }
            }

            // Filter out rows where primary identifiers are missing
            if (cleaned.get("Target Company") == null && cleaned.get("Deal Date") == null) {
                continue;
            }

            cleanedMapsForPrint.add(cleaned);
            LinkedDeals ld = MAPPER.convertValue(cleaned, LinkedDeals.class);
            result.add(ld);
        }

        int sampleCount = (limit > 0) ? limit : 2;
        System.out.println("\n[UI EXTRACTION] LinkedDeals - Sample " + sampleCount + " rows:");
        try {
            for (int i = 0; i < Math.min(sampleCount, cleanedMapsForPrint.size()); i++) {
                System.out.println(PRINT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(cleanedMapsForPrint.get(i)));
            }
        } catch (Exception e) {
            System.err.println("Error printing LinkedDeals samples: " + e.getMessage());
        }
        return result;
    }

    // New: map generic rows into LinkedProfessionals POJOs
    public static List<LinkedProfessionals> extractLinkedProfessionals(Page page, String tableSelector, int limit) {
        List<Map<String, String>> rows = extractTableRows(page, tableSelector, limit);
        List<LinkedProfessionals> result = new ArrayList<>();
        List<Map<String, Object>> cleanedMapsForPrint = new ArrayList<>();
        if (rows == null || rows.isEmpty()) return result;

        Map<Integer, String> canonicalByPos = new LinkedHashMap<>();
        canonicalByPos.put(0, "Professionals Name");
        canonicalByPos.put(1, "Phone");
        canonicalByPos.put(2, "Email");
        canonicalByPos.put(3, "Company Name");
        canonicalByPos.put(4, "Designation");
        canonicalByPos.put(5, "Contact");

        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        boolean genericHeaders = headers.stream().allMatch(h -> h != null && h.trim().toLowerCase().matches("^col\\d+$"));

        for (Map<String, String> row : rows) {
            Map<String, Object> cleaned = new LinkedHashMap<>();

            for (int pos = 0; pos < headers.size(); pos++) {
                String headerKey = headers.get(pos);
                String rawVal = row.get(headerKey);
                String val = sanitizeCell(rawVal);

                String targetKey;
                if (genericHeaders || headerKey.matches("^col\\d+$")) {
                    // If all headers are generic OR this specific header is generic, use canonical mapping
                    targetKey = canonicalByPos.getOrDefault(pos, headerKey);
                } else {
                    targetKey = headerKey.trim();
                }

                String norm = normalizeHeader(targetKey);
                if (val != null && isNumericHeader(norm)) {
                    cleaned.put(targetKey, parseNumericValue(val));
                } else {
                    cleaned.put(targetKey, val);
                }
            }

            // Filter out rows where Professionals Name is missing
            if (cleaned.get("Professionals Name") == null) {
                continue;
            }

            cleanedMapsForPrint.add(cleaned);
            LinkedProfessionals lp = MAPPER.convertValue(cleaned, LinkedProfessionals.class);
            result.add(lp);
        }

        int sampleCount = (limit > 0) ? limit : 2;
        System.out.println("\n[UI EXTRACTION] LinkedProfessionals - Sample " + sampleCount + " rows:");
        try {
            for (int i = 0; i < Math.min(sampleCount, cleanedMapsForPrint.size()); i++) {
                System.out.println(PRINT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(cleanedMapsForPrint.get(i)));
            }
        } catch (Exception e) {
            System.err.println("Error printing LinkedProfessionals samples: " + e.getMessage());
        }
        return result;
    }

    // Helper: normalize header text to a compact lower-case token (used for heuristics)
    private static String normalizeHeader(String header) {
        if (header == null) return "";
        String s = header.replaceAll("[^A-Za-z0-9 ]", " ").toLowerCase().trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }

    // Heuristic: decide whether a header likely contains numeric values
    private static boolean isNumericHeader(String normalizedHeader) {
        if (normalizedHeader.isEmpty()) return false;
        String h = normalizedHeader;
        return h.contains("aum") ||
               h.contains("asset under management") ||
               h.contains("fund size") ||
               h.contains("amount raised") ||
               h.contains("deal value") ||
               h.contains("% sought") ||
               h.contains("investment size") ||
               h.contains("current investments") ||
               h.contains("deals in last") ||
               h.contains("number of deals") ||
               h.contains("number of exits") ||
               h.contains("total investment") ||
               h.contains("ev/") ||
               h.contains("ev ") ||
               h.matches(".*\\b\\d{1,3}(,\\d{2,3})+.*"); // also catch comma formatted numbers
    }

    // Parse numeric-like string into Double (remove commas/currency symbols)
    private static Double parseNumericValue(String raw) {
        if (raw == null) return null;
        String s = raw.replaceAll("[^0-9.\\-]", ""); // remove commas, currency symbols, spaces
        if (s.isEmpty() || s.equals(".") || s.equals("-")) return null;
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            // fallback: try replacing multiple dots/commas
            s = s.replaceAll(",", "");
            try {
                return Double.parseDouble(s);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    // Small helper used by the three methods above
    private static String sanitizeCell(String raw) {
        if (raw == null) return null;
        String v = raw.trim();
        if (v.isEmpty() || v.equals("-") || v.equalsIgnoreCase("nan")) {
            return null;
        }
        // remove non-breaking spaces and return
        return v.replace("\u00A0", " ").trim();
    }

    // ---------- Helper Parsing Methods ----------

    private static BigDecimal parseBigDecimal(String value) {
        try {
            if (value == null) return null;
            value = value.replaceAll("[^0-9.\\-]", "");
            if (value.isEmpty() || value.equals(".")) return null;
            return new BigDecimal(value);
        } catch (Exception e) {
            return null;
        }
    }

    private static Double parseDouble(String value) {
        try {
            value = value.replaceAll("[^\\d.\\-]", "");
            if (value.isEmpty()) return null;
            return Double.parseDouble(value);
        } catch (Exception e) {
            return null;
        }
    }

    private static Integer parseInt(String value) {
        try {
            value = value.replaceAll("[^\\d\\-]", "");
            if (value.isEmpty()) return null;
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }
}
