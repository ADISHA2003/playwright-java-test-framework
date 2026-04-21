package PlaywrightPageObject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyDevelopment_Plt_Actions extends BasePageActions{

    Page page;
    public KeyDevelopment_Plt_Actions(Page page) {
        super(page);
        this.page = page;
    }

    public void verifyKeyDevBASICfLOW(){
        page.locator(".ml-8 > .button").click();
        page.locator(".MuiBackdrop-root").click();
        assertThat(page.locator(".ml-8 > .button")).isVisible();
        assertThat(page.getByRole(AriaRole.LIST)).containsText("All Key Developments (169601)");
        assertThat(page.getByPlaceholder("Search for a Keyword")).isEmpty();
        page.locator(".ml-8 > .button").click();
        page.getByText("Last 30 Days").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Filter"))).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Filter")).click();
        page.getByLabel("Market Listing (937)").check();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset")).click();
        page.locator(".MuiPaper-root > span > svg > path").click();

    }

    public void keydevRecord(){
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("VCCEdge"))).isVisible();
        assertThat(page.getByTestId("search-input")).isVisible();
        assertThat(page.getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG).nth(2)).isVisible();
        assertThat(page.getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG).nth(3)).isVisible();
        assertThat(page.getByRole(AriaRole.BANNER).locator("path").nth(3)).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("NewsLetter"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filter"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("All Key Developments"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My Feed"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reading List"))).isVisible();
        assertThat(page.locator(".button").first()).isVisible();
        assertThat(page.getByPlaceholder("Search for a Keyword")).isVisible();
        assertThat(page.getByTestId("clear-button")).isVisible();
        assertThat(page.locator(".content-card > .svg-icon > svg > path").first()).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filter")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Filter Key Developments"))).isVisible();
        assertThat(page.getByText("Filters Applied")).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset"))).isVisible();
        assertThat(page.locator(".close-btn")).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"))).isVisible();
        assertThat(page.locator(".mr-8").first()).isVisible();
        assertThat(page.locator(".close-btn")).isVisible();
        page.locator(".close-btn").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export")).click();
        assertThat(page.getByText("Data to Export")).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Export"))).isVisible();
        assertThat(page.getByRole(AriaRole.COMBOBOX)).isVisible();
        page.locator(".MuiBackdrop-root").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("NewsLetter")).click();
        assertThat(page.getByText("Receive personalised Key")).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))).isVisible();
        assertThat(page.getByRole(AriaRole.CHECKBOX)).isVisible();
        page.locator(".MuiBackdrop-root").click();
    }

}
