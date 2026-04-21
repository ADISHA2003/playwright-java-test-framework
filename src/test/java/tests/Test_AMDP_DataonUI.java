package tests;

import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;

public class Test_AMDP_DataonUI extends BaseUI_Test {
    
    @DataProvider(name = "AMDataProvider")
    public Object[][] AMDataProvider() {
        return new Object[][]{
            {"666e6ff983c167536f3af7bd"},
            {"66acc1d0a98ed25fde8a4179"}
        };
    }

    @Test(dataProvider = "AMDataProvider", description = "Verify Asset Manager Detail Page has data on UI", groups = "smoke")
	public void verify_AMDP_DataonUI(String assetId) {

	    Allure.step("Given user is logged in and on Dashboard");
	    lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When user navigates to Asset Manager DP page");
        amdpa.navigateToAssetManagerDP(Domain, assetId);

        Allure.step("Then user verifies Funds Under Management table is not empty");
        amdpa.Check_FUM_tableisNotEmpty();

        Allure.step("Then user navigate to Portfolio tab");
        amdpa.navigate_To_AMDP_Portfolio_tab(Domain, assetId);

        Allure.step("And user verifies Portfolio table is not empty");
        amdpa.Check_Portfolio_tableisNotEmpty();
    }
}