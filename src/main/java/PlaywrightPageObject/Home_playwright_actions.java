package PlaywrightPageObject;

import com.microsoft.playwright.Page;

public class Home_playwright_actions extends BasePageActions {

    public Home_playwright_actions(Page page) {
        super(page);
    }

    public void navigateToHomePage(String url) {
        page.navigate(url);
    }

    public void whenIClickOnLoginButton(String buttonName) {
        clickButtonByExactText(buttonName);
    }

    public void thenIShouldBeOnLoginPage(String URL){
        verifyCurrentUrl(URL, 3);
    }
}
