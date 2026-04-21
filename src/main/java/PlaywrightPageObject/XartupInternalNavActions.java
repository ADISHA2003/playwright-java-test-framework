package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.Assert;

public class XartupInternalNavActions extends BasePageActions {

    public XartupInternalNavActions(Page page) {
        super(page);
    }

    public void clickFirstCompanyAndVerify() {
        Locator firstCompanyLink = page.locator("table tbody tr:first-child a").first();
        
        firstCompanyLink.waitFor();

        String companyName = firstCompanyLink.innerText();
        Allure.step("Found first company in list: " + companyName);

        Allure.step("Clicking on company: " + companyName);
        
        // The click on the company link opens a new tab. We capture this new page.
        Page companyProfilePage = page.waitForPopup(firstCompanyLink::click);
        companyProfilePage.waitForLoadState();
        Allure.step("Navigated to company profile page in a new tab.");

        // On the new company profile page, we verify the company name.
        Locator companyNameHeader = companyProfilePage.locator("h2.company-name");
        String profileCompanyName = companyNameHeader.innerText();
        Allure.step("Found company name on profile page: " + profileCompanyName);

        Assert.assertEquals(profileCompanyName.trim(), companyName.trim(), "Company name on profile page does not match the name from the dashboard list.");
        Allure.step("Successfully verified company name on profile page.");

        companyProfilePage.close();
    }
}