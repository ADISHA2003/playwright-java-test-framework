package tests;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;

public class Test_Dashboard extends BaseUI_Test {
	@Test(description = "Verify Xartup and VCCircle navigation from Dashboard", groups = "smoke")
	public void verify_Dashboard_xartupAndvcCircleRedirection() {

	    Allure.step("Given user is logged in and on Dashboard");
	    lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");
	    Allure.step("When user clicks on VCCIRCLE and new tab opens");
	    dp.navigateAndVerifyNewTabWithTitle(
	        "VCCIRCLE",
	        "vccircle",
	        null
	    );

	    Allure.step("When user clicks on XARTUP and new tab opens");
	    dp.navigateAndVerifyNewTabWithTitle(
	        "XARTUP",
	        "xartup",
	        "xartup"
	    );

	}	   
	// @Test(description = "Verify Smart Grid access and basic grid creation from Dashboard", groups = "smoke")
	// public void verify_Dashboard_SmartGridFunctionality() {

	//     Allure.step("Given user is logged in and on Dashboard");
	//     lp.givenIamAlreadyOnLoginPageAndOnDashboard(
	//         Domain,
	//         "testUserName",
	//         "testUserPassword"
	//     );

	//     Allure.step("When user clicks on Smart Grid from Dashboard");
	//     dp.clickOnSmarGrid();

	//     Allure.step("Then Smart Grid modal should be opened successfully");
	//     dp.verifySmartGridIsOpened();

	//     Allure.step("And Smart Grid dashboard should load completely");
	//     dp.waitForSmartGridToLoad();

	//     Allure.step("And Smart Grid default view should be Companies → Grid");
	//     dp.verifySmartGridDefaultView("Companies", "Grid");
	//     Allure.step("Then Company Name column should be visible");
	//     dp.verifyCompanyNameColumnPresent();

	//     Allure.step("And Add Column option should be visible");
	//     dp.verifyAddColumnButtonPresent();
	// }
}
	
	
	
	
	
	
	
	
	
	

