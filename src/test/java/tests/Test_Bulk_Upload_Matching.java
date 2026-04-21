package tests;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Test_Bulk_Upload_Matching extends BaseUI_Test {

    @DataProvider(name = "fileUploadProvider")
    public Object[][] fileUploadProvider() {
        return new Object[][]{
            {"Test_Matching.csv", "Matching_logic_File", "Company Name"}
        };
    }

    @Test( dataProvider = "fileUploadProvider", description = "Bulk Export | As a user I can confirm companies matching logic in bulk export", groups = {"smoke", "regression"} )
    public void TestBulkUploadMatching(String fileName, String folderName, String columnName) {

        Allure.step("Given I am already logged in and on the dashboard");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");

        Allure.step("Then I navigate to Bulk Export page");
        bea.navigateToBulkExport(Domain);

        Allure.step("And I click 'Add Companies in Bulk'");
        bea.clickAddCompaniesInBulk();

        Allure.step("And I click 'Upload Excel/CSV'");
        buma.clickUploadExcelCsvTab();

        Allure.step("When I upload the file");
        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "src", "test", "java", folderName, fileName
        ).toString();
        buma.uploadFile(filePath);

        Allure.step("And I click 'Next'");
        buma.clickNext();

        Allure.step("And I choose column 'Company Name'");
        buma.clickChooseColumn();
        buma.selectColumnOption("company_name");

        Allure.step("And I click 'Fetch Companies'");
        buma.clickFetchCompanies();

        Allure.step("And I dismiss the blocking popup");
        buma.dismissBlockingPopup();

        Allure.step("Then I get the count of ready companies");
        int actualCount = buma.getCompaniesReadyCount();
        Allure.step("Companies ready for export count is: " + actualCount);

        Allure.step("And I verify the matched companies count is 10");
        Assert.assertEquals(buma.getMatchedCompaniesCount(), 10, "Matched companies count is not correct.");
        
        Allure.step("And I verify the partially matched companies count is 11");
        Assert.assertEquals(buma.getPartiallyMatchedCompaniesCount(), 11, "Partially matched companies count is not correct.");
        
        Allure.step("And I verify the not found companies count is 9");
        Assert.assertEquals(buma.getNotFoundCompaniesCount(), 9, "Not found companies count is not correct.");

        Allure.step("And I verify the Completely Matched company names");
        List<String> expectedMatched = Arrays.asList(
            "Kanam Latex Industries Pvt Ltd", "Trivitron Healthcare Private Limited", "Harsoria Healthcare Private Limited", "Brawn Laboratories Limited",
            "Advanced Microdevices Private Limited", "B L Lifesciences Private Limited", "Medico Electrodes International Limited",
            "Accumax Lab Devices Private Limited", "Gigindia", "Sensa Core Medical Instrumentation Private Limited"
        );
        buma.clickMatchedCompaniesFilter();
        List<String> actualMatched = buma.getVisibleCompanyNames();
        Assert.assertEquals(actualMatched.size(), expectedMatched.size(), "Count of visible matched companies mismatch");
        Assert.assertTrue(actualMatched.containsAll(expectedMatched), 
            "Mismatch in Completely Matched companies. \nExpected: " + expectedMatched + "\nActual: " + actualMatched);

            
        Allure.step("And i click on Review Unmatched Companies Button");
        buma.clickReviewUnmatchedCompaniesButton();

        
        Allure.step("And I verify the Partially Matched company names from the modal"); 
        List<String> expectedPartial = Arrays.asList(
            "Markasia Beverages", "The Wellness Company", "Angulair", "Digital Darwin", "Vaf Def-Aero Systems",
            "Universal Sompo", "Faering Capital", "Ascend Telecom", "Summit Digitel", "Bridge Health", "J Mitra and Co. Pvt. Ltd."
        );
        List<String> actualPartial = buma.getUnmatchedCompanyNamesFromModal();
        Assert.assertEquals(actualPartial.size(), expectedPartial.size(), "Count of visible partially matched companies in modal mismatch");
        Assert.assertTrue(actualPartial.containsAll(expectedPartial),
            "Mismatch in Partially Matched companies in modal. \nExpected: " + expectedPartial + "\nActual: " + actualPartial);

        
        Allure.step("And i click on Companies Not Found Button");
        buma.clickCompaniesNotFoundButton();
        

        Allure.step("And I verify the Not Found company names");
        List<String> expectedNotFound = Arrays.asList(
            "CJDarcl", "24SEVEN", "Lingopanda", "HIWiPay", "Elearnmarkets", "Leobrix", "StudynLearn", "Arkaholdings", "FirstBridgeFund"
        );
        List<String> actualNotFound = buma.getNotFoundCompanyNamesFromModal();
        Assert.assertEquals(actualNotFound.size(), expectedNotFound.size(), "Count of visible not found companies in modal mismatch");
        Assert.assertTrue(actualNotFound.containsAll(expectedNotFound),
            "Mismatch in Not Found companies in modal. \nExpected: " + expectedNotFound + "\nActual: " + actualNotFound);

        Allure.step("And i close the modal");
        buma.clickDialogCloseIcon();

        Allure.step("And I clear the list");
        bea.clickClearList();

        Allure.step("And I confirm clearing the list");
        bea.clickConfirmClearList();
    }

}
