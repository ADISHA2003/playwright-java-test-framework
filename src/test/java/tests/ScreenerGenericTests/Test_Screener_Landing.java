package tests.ScreenerGenericTests;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

public class Test_Screener_Landing extends BaseUI_Test {

    @Test(description="As a user i can navigate to landing page of company screener", groups = "smoke")
    public void verify_company_screener_landing_page(){
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain ,"testUserName", "testUserPassword");

        Allure.step("When I navigate to company screener page");
        slp.navigateToScreenerLandingPage(Domain,"company");

        Allure.step("Then I should be on company screener landing page");
        slp.thenIShouldBeOnScreenerLandingPage();

        Allure.step("And I verify all the components on the page");
        slp.verifyAllTheComponentsOnThePage();
    }
}
