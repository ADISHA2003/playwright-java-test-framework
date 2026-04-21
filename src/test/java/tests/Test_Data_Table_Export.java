package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PlaywrightPageObject.Login_playwright_actions;
import PlaywrightPageObject.DataTableExportActions;
import PlaywrightPageObject.XLSDownloadsPage;
import utils.Gmail_Inbox_Reader;
import java.util.Map;
import tests.BaseUI_Test;

@Listeners({AllureTestNg.class})
public class Test_Data_Table_Export extends BaseUI_Test {


    @DataProvider(name = "screenerUrls")
    public Object[][] screenerUrls() {
        return new Object[][]{
            {"COMPANY_SCREENER", "screener/company/view-results"},
            {"ALL_DEALS", "screener/all-deals/view-results"},
            {"PE_INVESTMENT", "screener/pe-investment/view-results"},
            {"MERGER_AND_ACQUISITION", "screener/merger-and-acquisition/view-results"},
            {"PE_EXITS", "screener/pe-exits/view-results"},
            {"EQUITY_CAPITAL_MARKET", "screener/equity-capital-market/view-results"},
            {"DEBT_TRANSACTION", "screener/debt-transaction/view-results"},
            {"ASSET_MANAGER_SCREENER", "screener/asset-manager/view-results"},
            {"FUND_SCREENER", "screener/funds/view-results"},
            {"LIMITED_PARTNER", "screener/limited-partner/view-results"},
            {"FAMILY_OFFICE", "screener/family-office/view-results"}
        };
    }


    @Test(dataProvider = "screenerUrls", description = "Data Table Export | As a user I can export data from data table and verify S3 URL", groups = {"smoke", "regression"})
    public void Test_Export_Data_Table(String screenerType, String screenerPath) throws Exception {

        Allure.step("Given I am already logged in and on the dashboard");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");

        Allure.step("Then I navigate to the Data Table Export page");
        dte.navigateToDataTableExport(Domain, screenerPath);

        Allure.step("And I select first tab as default and export the data table");
        // Capture timestamp just before triggering the export to find the correct email.
        long exportTriggerTime = System.currentTimeMillis();
        dte.exportData();

        Allure.step("Then I navigate to the My Downloads page");
        xls.navigateToDownloads(Domain);

        String latestEntity = xls.getLatestEntityName();
        Allure.step("And I verify the latest exported entity: " + latestEntity);

        boolean isProcessed = xls.waitForExportProcessed();
        Allure.step("And I verify the export status is processed");
        Assert.assertTrue(isProcessed, "Export was not processed successfully.");

        // Get S3 URL and Subject from email with extended wait
        String expectedSubject = "VCCEdge Export Ready: Screener Details | " + screenerType;
        Map<String, String> emailData = Gmail_Inbox_Reader.waitForExportEmailAndExtractS3Url(
            expectedSubject, 1000, 100, exportTriggerTime
        );

        Allure.step("And I verify an export email was received");
        Assert.assertNotNull(emailData, "Export email not found within 1000 seconds.");

        String emailSubject = emailData.get("subject");
        String emailS3Url = emailData.get("s3Url");

        Allure.step("And I verify the email subject is correct: " + emailSubject);
        Assert.assertTrue(emailSubject.startsWith(expectedSubject),
            "Export email subject mismatch. Found: " + emailSubject);

        Allure.step("And I verify the S3 URL extracted from email: " + emailS3Url);
        Assert.assertNotNull(emailS3Url, "S3 URL was not found in the email body.");
        Assert.assertTrue(emailS3Url.startsWith("https://"),
            "Invalid S3 URL format in email. Found: " + emailS3Url);
    }
}
