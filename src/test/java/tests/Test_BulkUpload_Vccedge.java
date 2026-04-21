package tests;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.nio.file.Paths;

public class Test_BulkUpload_Vccedge extends BaseUI_Test {

    @DataProvider(name = "fileUploadProvider")
    public Object[][] fileUploadProvider() {
        return new Object[][]{
            {"FinComps - FinComps 5000.csv", "sample_CSV_file", "Company Name"},
            {"Sample.xlsx", "sample_excel_file", "Company Name"}
        };
    }

    @Test( dataProvider = "fileUploadProvider", description = "Bulk Export | As a user I can upload companies in bulk export", groups = {"smoke", "regression"} )
    public void Test_BulkUpload_Vccedge(String fileName, String folderName, String columnName) {

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

        Allure.step("And I dismiss the blocking popup");
        bua.dismissBlockingPopup();

        Allure.step("Then I get the count of ready companies");
        int actualCount = bua.getCompaniesReadyCount();
        Allure.step("Companies ready for export count is: " + actualCount);

        Allure.step("And I clear the list");
        bea.clickClearList();

        Allure.step("And I confirm clearing the list");
        bea.clickConfirmClearList();
    }

}
