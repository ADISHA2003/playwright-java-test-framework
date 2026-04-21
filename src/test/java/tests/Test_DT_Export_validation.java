package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PlaywrightPageObject.Pojo.CompanyScreener.Companies;
import PlaywrightPageObject.Login_playwright_actions;
import PlaywrightPageObject.DataTableExportActions;
import PlaywrightPageObject.XLSDownloadsPage;
import utils.DataTableExtractor;
import utils.Gmail_Inbox_Reader;
import utils.S3DataExtractor;
import utils.DataComparator;
import utils.DataExport;
import tests.BaseUI_Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Listeners({AllureTestNg.class})
public class Test_DT_Export_validation extends BaseUI_Test {

    // Centralized control for how many rows to compare. Set to 0 or less for all rows.
    private static final int COMPARISON_ROW_LIMIT = 3;

    @DataProvider(name = "screenerUrls")
    public Object[][] screenerUrls() {
        return new Object[][]{
            {"screener/company/filters/6937bc082f52b8564466496e", "COMPANY_SCREENER"}
        };
    }

    @Test(dataProvider = "screenerUrls",
          description = "Validates that the Screener Data Table matches the data in the exported file.", groups = "smoke")
    public void Test_Data_Table_Validation(String screenerPath, String screenerType) throws Exception {
        
        Allure.step("Given I am logged in and on the dashboard", () -> {
            lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");
        });

        // Step 1: Navigate to Screener with Saved Criteria
        Allure.step("Then i navigate to screener page where i applied the saved criteria and click on View All Results", () -> {
            dte.navigateToScreenerPageWithSavedCriteria(Domain, screenerPath);
        });

        // Step 3: Extract Data Table into POJOs (Companies)
        List<Companies> companiesList = Allure.step(
            "Then I extract table data into POJOs (Companies) from the default tab and columns",
            () -> DataTableExtractor.extractCompanies(page, "table", COMPARISON_ROW_LIMIT)
        );

        if (companiesList == null || companiesList.isEmpty()) {
            throw new AssertionError("Table data extraction failed — no companies found!");
        }

        // NEW: Visit linked tabs and extract their tables into POJOs
        List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors> linkedInvestorsList = Allure.step(
            "Then I navigate to Linked Investors tab and extract table rows into POJOs", () -> {
                dte.navigateToLinkedTab(Domain, "screener/company/view-results/investors");
                List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors> investors = DataTableExtractor.extractLinkedInvestors(page, "table", COMPARISON_ROW_LIMIT);
                System.out.println("Linked Investors POJOs: " + investors.size());
                if (!investors.isEmpty()) System.out.println("First investor: " + investors.get(0).getInvestorName());
                return investors;
            });

        List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals> linkedDealsList = Allure.step(
            "Then I navigate to Linked Deals tab and extract table rows into POJOs", () -> {
                dte.navigateToLinkedTab(Domain, "screener/company/view-results/deals");
                List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals> deals = DataTableExtractor.extractLinkedDeals(page, "table", COMPARISON_ROW_LIMIT);
                System.out.println("Linked Deals POJOs: " + deals.size());
                if (!deals.isEmpty()) System.out.println("First deal target: " + deals.get(0).getTargetCompany());
                return deals;
            });

        List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals> linkedProfessionalsList = Allure.step(
            "Then I navigate to Linked Professionals tab and extract table rows into POJOs", () -> {
                dte.navigateToLinkedTab(Domain, "screener/company/view-results/professionals");
                List<PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals> professionals = DataTableExtractor.extractLinkedProfessionals(page, "table", COMPARISON_ROW_LIMIT);
                System.out.println("Linked Professionals POJOs: " + professionals.size());
                if (!professionals.isEmpty()) System.out.println("First professional: " + professionals.get(0).getProfessionalsName());
                return professionals;
            });

        // Navigate back to company screener tab (to perform export)
        Allure.step("Then I navigate back to the Company screener tab", () -> {
            dte.navigateToCompanyTab(Domain);
        });

        // NEW: Select all linked sections (investors, deals, professionals) and trigger export
        Allure.step("Then I select all linked sections and trigger the export of the data table", () -> {
            dte.selectAllLinkedSectionsForExport();
        });

        // Step 5: Wait for export to be processed
        Allure.step("And I wait until the export is processed successfully", () -> {
            xls.navigateToDownloads(Domain);
        });

        String latestEntity = xls.getLatestEntityName();
        Allure.step("And I verify the latest exported entity: " + latestEntity);

        boolean isProcessed = xls.waitForExportProcessed();
        Allure.step("And I verify the export status is processed");
        Assert.assertTrue(isProcessed, "Export was not processed successfully.");
        
        // Get S3 URL and Subject from email
        String expectedSubject = "VCCEdge Export Ready: Screener Details | " + screenerType;
        long exportTriggerTime = System.currentTimeMillis();
        Map<String, String> emailData = Gmail_Inbox_Reader.waitForExportEmailAndExtractS3Url(
            expectedSubject, 1000, 100, exportTriggerTime
        );

        Allure.step("And I verify an export email was received");
        Assert.assertNotNull(emailData, "Export email not found within 300 seconds.");

        String emailSubject = emailData.get("subject");
        String emailS3Url = emailData.get("s3Url");

        Allure.step("And I verify the email subject is correct: " + emailSubject);
        Assert.assertTrue(emailSubject.startsWith(expectedSubject),
            "Export email subject mismatch. Found: " + emailSubject);

        Allure.step("And I verify the S3 URL extracted from email: " + emailS3Url);
        Assert.assertNotNull(emailS3Url, "S3 URL was not found in the email body.");
        Assert.assertTrue(emailS3Url.startsWith("https://"),
            "Invalid S3 URL format in email. Found: " + emailS3Url);

        // Step 7: Download & Extract Exported Data into POJOs
        S3DataExtractor s3Extractor = new S3DataExtractor();

        List<Companies> s3Companies = Allure.step(
            "Then I extract company data from S3 export file into POJOs",
            () -> { return s3Extractor.getCompaniesFromS3Url(emailS3Url, COMPARISON_ROW_LIMIT); }
        );

        if (s3Companies == null || s3Companies.isEmpty()) {
            throw new AssertionError("Failed to extract company data from S3 export file!");
        }

        Allure.step("Extracted and mapped " + s3Companies.size() + " companies from S3 export");


        // Request full DataExport from S3 (companies + linked sheets)
        DataExport fullExport = s3Extractor.getDataExportFromS3Url(emailS3Url, COMPARISON_ROW_LIMIT);
        Assert.assertNotNull(fullExport, "Failed to parse S3 export into DataExport wrapper.");

        // Step 8: Compare Data Between UI and Export
        Allure.step("Then I compare UI table data with S3 export data", () -> {
            List<String> allMismatches = new ArrayList<>();

            allMismatches.addAll(DataComparator.compareCompanies(companiesList, s3Companies));
            allMismatches.addAll(DataComparator.compareLinkedInvestors(linkedInvestorsList, fullExport.getLinkedInvestors()));
            allMismatches.addAll(DataComparator.compareLinkedDeals(linkedDealsList, fullExport.getLinkedDeals()));
            allMismatches.addAll(DataComparator.compareLinkedProfessionals(linkedProfessionalsList, fullExport.getLinkedProfessionals()));

            if (!allMismatches.isEmpty()) {
                String failureMessage = "Data validation failed with " + allMismatches.size() + " mismatches:\n"
                        + String.join("\n", allMismatches);
                Assert.fail(failureMessage);
            }
        });

        Allure.step("Data validation completed successfully — all values match.");
    }
}
