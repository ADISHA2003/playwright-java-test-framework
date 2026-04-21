package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Gmail_Inbox_Reader;
import utils.S3DataExtractor;
import java.util.Map;
import java.nio.file.Paths;

@Listeners({AllureTestNg.class})
public class Test_BulkExport_Count extends BaseUI_Test {

    @DataProvider(name = "fileUploadProvider")
    public Object[][] fileUploadProvider() {
        return new Object[][]{
            {"Sample.xlsx", "sample_excel_file", "Company Name"}
        };
    }

    @Test(dataProvider = "fileUploadProvider", description = "Bulk Export | As a user I can verify that the company count in UI matches the exported Excel file", groups = {"smoke", "regression"})
    public void testBulkExport(String fileName, String folderName, String columnName) {

        Allure.step("Given I am already logged in and on the dashboard");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");

        Allure.step("Then I navigate to Bulk Export page");
        bea.navigateToBulkExport(Domain);
        
        Allure.step("And I click 'Add Companies in Bulk'");
        bea.clickAddCompaniesInBulk();

        Allure.step("And I click 'Upload Excel/CSV'");
        bua.clickUploadExcelCsvTab();

        Allure.step("When I upload the file");
        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "src", "test", "java", folderName, fileName
        ).toString();
        bua.uploadFile(filePath);

        Allure.step("And I click 'Next'");
        bua.clickNext();

        Allure.step("And I choose column 'Company Name'");
        bua.clickChooseColumn();
        bua.selectColumnOption("Company Name");

        Allure.step("And I click 'Fetch Companies'");
        bua.clickFetchCompanies();
        page.waitForTimeout(1000);

        Allure.step("And I dismiss the blocking popup");
        bua.dismissBlockingPopup();

        Allure.step("Then I extract Company count for export");
        int initialCompanyCount = bea.getCompaniesForExportCount();
        System.out.println("UI company count: " + initialCompanyCount);

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
            expectedSubject, 1000, 100, exportTriggerTime
        );

        Allure.step("And I verify an export email was received");
        Assert.assertNotNull(emailData, "Export email not found.");

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

        int s3CompanyCount = Allure.step(
            "Then I extract company count from S3 export file",
            () -> s3Extractor.getBulkExportCount(emailS3Url)
        );

        Allure.step("Extracted count from S3 export: " + s3CompanyCount);
        System.out.println("Total companies extracted from Excel export: " + s3CompanyCount);

        Allure.step("And I verify the count of companies in the export file matches the UI count");
        Assert.assertEquals(s3CompanyCount, initialCompanyCount, "The number of companies in the exported file does not match the count from the UI.");
    }
}