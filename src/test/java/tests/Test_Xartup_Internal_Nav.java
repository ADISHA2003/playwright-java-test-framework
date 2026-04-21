package tests;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import com.microsoft.playwright.Page;
import PlaywrightPageObject.XartupInternalNavActions;

public class Test_Xartup_Internal_Nav extends BaseUI_Test {
    
    @Test(description = "Verify Xartup Internal Navigation", groups = "smoke")
	public void verify_Xartup_Internal_Navigation() {

	    Allure.step("Given user is logged in and on Dashboard");
	    lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

	    Allure.step("When user clicks on XARTUP and new tab opens");
        
        // Capture the new tab instead of using the DP helper which closes it
        Page xartupPage = page.waitForPopup(() -> {
            page.locator("div.lcg-blue:has(img[src*='logo_xartup'])").click();
        });
        xartupPage.waitForLoadState();

        Allure.step("And i click on company name and verify that company detail page opens in a new tab");
        
        // Initialize actions with the NEW page context (Investor Dashboard)
        XartupInternalNavActions xartupActions = new XartupInternalNavActions(xartupPage);
        xartupActions.clickFirstCompanyAndVerify();

        xartupPage.close();
	}	   
}
