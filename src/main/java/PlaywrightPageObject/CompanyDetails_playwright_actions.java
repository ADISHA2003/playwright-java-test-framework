package PlaywrightPageObject;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CompanyDetails_playwright_actions extends BasePageActions{
    public CompanyDetails_playwright_actions(Page page) {
        super(page);
    }

    // naviate to subtab
    public void navigateToSubTab(String subTabName) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(subTabName)).click();
    }

    public void selectGrowthCagr(String years){

    }

    public void selectCurrency(String currency){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(currency)).click();
    }

    public void selectReportType(String format){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(format)).click();
    }

    public void selectReportFormat(String format){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(format)).click();
    }

    public void selectReportView(String view){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(view)).click();
    }



}
