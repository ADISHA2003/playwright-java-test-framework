package PlaywrightPageObject;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CompareCompany_existingVCCEdge_plt_actions extends BasePageActions {

    Page page;

    public CompareCompany_existingVCCEdge_plt_actions(Page page) {
        super(page);
        this.page = page;

    }

    public boolean compareCompanySanity() {
        page.navigate("https://vccedge.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
        page.getByPlaceholder("Login ID").click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.locator("#sidebar").getByText("Compare Companies").click();
       assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByRole(AriaRole.TAB, new FrameLocator.GetByRoleOptions().setName("COMPARE COMPANIES"))).isVisible();

        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name1*")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name2*")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name3")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name4")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name5")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name1*")).isVisible();
        assertThat(page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name1*")).isVisible();
        page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name1*").click();
        page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name1*").fill("zomato");
        page.frameLocator("iframe[class^='fancybox-iframe']").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Zomato Ltd.")).click();
        page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name2*").click();
        page.frameLocator("iframe[class^='fancybox-iframe']").getByPlaceholder("Company Name2*").fill("swiggy");
        page.frameLocator("iframe[class^='fancybox-iframe']").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Swiggy Ltd.")).click();
        page.frameLocator("iframe[class^='fancybox-iframe']").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Compare").setExact(true)).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Factsheet"))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Financials"))).isVisible();
        assertThat(page.getByPlaceholder("Search more companies (upto")).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Zomato Ltd."))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Swiggy Ltd."))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("DBA Name"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Company Type"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Company Status"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Year Founded"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("# of Employees"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Business Description"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Current Funding Status"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Sub-Industry"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("City"))).isVisible();
        assertThat(page.getByText("Funding Details")).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("# of Rounds Raised"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Capital Raised ($ mn)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Time Since Last Funding"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Last Funding Round"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Amount Raised ($ mn)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Lastest PE Valuation ($ mn)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Revenue Multiple (EV/Total"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Market Cap"))).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Financials")).click();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Zomato Ltd."))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Swiggy Ltd."))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Yearend"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Net Sales"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Income"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Expenditure"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("EBITDA").setExact(true))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("EBIT").setExact(true))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("PBT"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("PAT").setExact(true))).isVisible();
        assertThat(page.getByText("Sources Of Funds")).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Equity Paid up"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Reserves and Surplus"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Net Worth"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Debt / Total Borrowing"))).isVisible();
        assertThat(page.getByText("Application Of Funds")).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Net Block"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Investments"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Cash and Bank Balance"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Net Current Assets"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Current Liabilities"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Total Assets"))).isVisible();
        assertThat(page.getByText("Cash Flow", new Page.GetByTextOptions().setExact(true))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Cash Flow from Operations"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Cash Flow from Investing"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Cash Flow from Financing"))).isVisible();
        assertThat(page.getByText("Key Ratios")).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Debt To Equity"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Current Ratio"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("ROCE (%)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("RONW (%)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("EBITDA Margin (%)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("PAT Margin (%)"))).isVisible();
        assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("CPM (%)"))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Download data in Excel"))).isVisible();


        return true;
    }
}
