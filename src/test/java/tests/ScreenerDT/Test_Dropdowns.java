package tests.ScreenerDT;

import Constants.ColumnType;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.util.List;

public class Test_Dropdowns extends BaseUI_Test {

    @Test(
            description = "Change currency and verify currency is reflected in table header",
            groups = {"smoke", "regression"}
    )
    public void Test_Change_Currency_And_Verify_Table_Header() {

        String screenerType = "Companies";
        String tile = "All";
        String currencyToSelect = "$ USD Million";
        int currencyDropdownIndex = 3;

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(
                Domain,
                "testUserName",
                "testUserPassword"
        );

        Allure.step("And I navigate to Screener results page");
        slp.navigateToScreenerAndOpenResults(
                Domain,
                screenerType,
                tile
        );

        Allure.step("When I change the currency from dropdown");
        dtDropdownActions.changeCurrency(currencyToSelect, currencyDropdownIndex);

        Allure.step("Then I verify selected currency is displayed in table header");
        screenerDTManageColumn.resetToDefaultColumns();
        screenerDTManageColumn.selectAllColumns();
        dtDropdownActions.verifyCurrencyInTableHeader(currencyToSelect);
    }

}
