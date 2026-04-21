package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PlaywrightPageObject.Login_playwright_actions;
import PlaywrightPageObject.EntityExportActions;
import PlaywrightPageObject.XLSDownloadsPage;
import utils.Gmail_Inbox_Reader;
import tests.BaseUI_Test;

@Listeners({AllureTestNg.class})
public class Test_Entity_Export extends BaseUI_Test {

    @DataProvider(name = "entityExportData")
    public Object[][] entityExportData() {
        return new Object[][]{
            {"company/6675646b624dea5a33892bc2", "VCCEdge Export Ready: Company Details"},
            {"deal/68d28f22023b8252f3962520", "VCCEdge Export Ready: Deal Details"},
            {"investor/asset-manager/66acc61da98ed25fde8aefff", "VCCEdge Export Ready: Asset-manager Details"},
            {"fund/666ede8f13d72d46feab41b3", "VCCEdge Export Ready: Fund Details"},
            {"investor/limited-partner/66acc1b1a98ed25fde8a4040", "VCCEdge Export Ready: Limited-partner Details"},
            {"investor/asset-manager/669e368bd6f4c23d3d87c3ca", "VCCEdge Export Ready: Asset-manager Details"}
        };
    }

    @Test(dataProvider = "entityExportData", description = "Entity Export | As a user I can export an entity from entity detail page", groups = {"smoke", "regression"})
    public void Test_Export_Entity(String entityPath, String expectedSubject) throws Exception {

        Allure.step("Given I am already logged in and on the dashboard");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");

        Allure.step("Then I navigate to an entity details page: " + entityPath);
        eet.navigateToEntityPage(Domain, entityPath);

        Allure.step("And I trigger an entity export");
        long exportTriggerTime = System.currentTimeMillis();
        eet.exportEntity();

        Allure.step("Then I navigate to the My Downloads page");
        xls.navigateToDownloads(Domain);

        Allure.step("And I verify the latest exported page name");
        String latestPage = eet.getLatestPageName();
        Assert.assertNotNull(latestPage, "Latest exported page name should not be null.");

        Allure.step("And I verify the export is processed successfully");
        boolean isProcessed = eet.waitForExportProcessed();
        Assert.assertTrue(isProcessed, "Export was not processed successfully.");

        // Get S3 URL and Subject from email
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
