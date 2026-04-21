package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PlaywrightPageObject.Login_playwright_actions;
import PlaywrightPageObject.BulkExportActions;
import PlaywrightPageObject.XLSDownloadsPage;
import utils.Gmail_Inbox_Reader;
import tests.BaseUI_Test;
import java.util.Map;

@Listeners({AllureTestNg.class})
public class Test_Bulk_Export extends BaseUI_Test {

    @Test(description = "Bulk Export | As a user I can export data from bulk export data table and verify S3 URL", groups = {"smoke", "regression"})
    public void testBulkExport() throws Exception {

        Allure.step("Given I am already logged in and on the dashboard");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");

        Allure.step("Then I navigate to Bulk Export page");
        bea.navigateToBulkExport(Domain);
        
        Allure.step("And I click 'Add Companies in Bulk'");
        bea.clickAddCompaniesInBulk();

        Allure.step("And I click 'Saved List'");
        bea.clickSavedList();

        Allure.step("And I select the checkbox for '10 list'");
        bea.clickCheckboxForList("10 list");

        Allure.step("And I click 'Add to Export List'");
        bea.clickAddToExportListButton();

        Allure.step("Given the initial company count is 10");
        int initialCompanyCount = bea.getCompaniesForExportCount();
        Assert.assertEquals(initialCompanyCount, 10, "Initial company count from saved list is not correct.");

        Allure.step("And I search for company 'Eternal Ltd.'");
        bea.searchForCompany("Eternal Ltd.");

        Allure.step("And I select the first search result");
        bea.clickFirstSearchResult();

        Allure.step("And I wait for the company count to update");
        bea.waitForCompanyCountToUpdate(initialCompanyCount);

        Allure.step("Then I verify the company count is 11");
        Assert.assertEquals(bea.getCompaniesForExportCount(), 11, "Company count after adding from search is not correct.");
        
        Allure.step("And I click 'Export' button");
        bea.clickExportButton();

        Allure.step("And I acknowledge the export terms");
        bea.clickAcknowledgeCheckbox();

        Allure.step("And I click the final 'Export' button");
        bea.clickFinalExportButton();

        Allure.step("Then I verify the export success message");
        Assert.assertTrue(bea.isExportSuccessVisible(), "Export success message not visible!");

        Allure.step("And I close the export dialog");
        bea.clickCloseDialog();

        Allure.step("And I click 'Clear List'");
        bea.clickClearList();

        Allure.step("And I confirm clearing the list");
        bea.clickConfirmClearList();

        // Additional steps for XLS Downloads and email verification
        Allure.step("Then I navigate to the My Downloads page");
        xls.navigateToDownloads(Domain);

        String latestEntity = xls.getLatestEntityName();
        Allure.step("And I verify the latest exported entity: " + latestEntity);

        boolean isProcessed = xls.waitForExportProcessed();
        Allure.step("And I verify the export status is processed");
        Assert.assertTrue(isProcessed, "Export was not processed successfully.");

        // Get S3 URL and Subject from email
        String expectedSubject = "VCCEdge Export Ready: Companybulkexports Details";
        long exportTriggerTime = System.currentTimeMillis();
        Map<String, String> emailData = Gmail_Inbox_Reader.waitForExportEmailAndExtractS3Url(
            expectedSubject, 4000, 100, exportTriggerTime
        );

        Allure.step("And I verify an export email was received");
        Assert.assertNotNull(emailData, "Export email not found within 2000 seconds.");

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