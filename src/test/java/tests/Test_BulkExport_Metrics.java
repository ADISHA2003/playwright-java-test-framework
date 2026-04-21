package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;
import java.nio.file.Paths;

@Listeners({AllureTestNg.class})
public class Test_BulkExport_Metrics extends BaseUI_Test {

    @DataProvider(name = "fileUploadProvider")
    public Object[][] fileUploadProvider() {
        return new Object[][]{
            {"Sample.xlsx", "sample_excel_file", "Company Name"}
        };
    }

    @Test(dataProvider = "fileUploadProvider", description = "Bulk Export | As a user i can see metrics selected on metrics modal are on datatable", groups = {"smoke", "regression"})
    public void testBulkExportMetrics(String fileName, String folderName, String columnName) {

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

        Allure.step("And I click 'Select Export Fields'");
        bema.clickSelectExportFields();

        Allure.step("And I click 'Default Template' dropdown");
        bema.clickTemplateDropdown();

        Allure.step("And I select 'Default Template' option");
        bema.selectDefaultTemplate();

        Allure.step("And I extract all metric names from the selection");
        List<String> selectedMetrics = bema.getExportFieldNames();
        System.out.println("Selected Metrics from Modal: " + selectedMetrics);

        Allure.step("And I click 'Add to Table'");
        bema.clickAddToTable();

        Allure.step("Then I verify metrics on data table match selected metrics");
        List<String> tableHeaders = bema.getDataTableHeaders();

        List<String> missingMetrics = bema.getMissingMetrics(selectedMetrics, tableHeaders);

        Assert.assertTrue(missingMetrics.isEmpty(), "Table headers do not contain all selected metrics. Missing: " + missingMetrics);
        
        Allure.step("And I click 'Select Export Fields'");
        bema.clickSelectExportFields();

        Allure.step("And I click 'Reset to Default'");
        bema.clickResetToDefault();

        Allure.step("And I close the metrics modal");
        bema.clickCloseMetricsModal();
        
        Allure.step("And I click 'Clear List'");
        bea.clickClearList();

        Allure.step("And I confirm clearing the list");
        bea.clickConfirmClearList();
    }
}
