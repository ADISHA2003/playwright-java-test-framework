package PlaywrightPageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyDevelopment_existingVCCEdge_plt_actions extends BasePageActions {

    Page page;

    public KeyDevelopment_existingVCCEdge_plt_actions(Page page) {
        super(page);
        this.page = page;

    }

    public boolean keyDevsanity()
    {
        page.navigate("https://vccedge.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
        page.getByPlaceholder("Login ID").click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.locator("#sidebar").getByText("Key Developments").click();
        assertThat(page.locator("#main_content_div").getByText("Key Developments")).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+ Customize")).click();
        assertThat(page.getByPlaceholder("Keyword Search")).isVisible();
        assertThat(page.getByPlaceholder("Search Company")).isVisible();
        assertThat(page.locator("#key_development_group")).isVisible();
        assertThat(page.locator("#key_development_type")).isVisible();
        assertThat(page.locator("#key_development_date")).isVisible();
        assertThat(page.locator("#source")).isVisible();
        assertThat(page.locator("#news_type")).isVisible();
        assertThat(page.locator("#submit_form div").filter(new Locator.FilterOptions().setHasText("Sector Details")).nth(3)).isVisible();
        assertThat(page.locator("#sector")).isVisible();
        assertThat(page.locator("#industry_group")).isVisible();
        assertThat(page.locator("#industry")).isVisible();
        assertThat(page.locator("#sub_industry")).isVisible();
        assertThat(page.getByPlaceholder("Search For Sub Industry")).isVisible();
        assertThat(page.getByText("Sector Theme")).isVisible();
        assertThat(page.getByText("Fin TechFood TechEdu")).isVisible();
        page.locator("#sector").selectOption("1");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("")).click();
        assertThat(page.getByText("Download data in Excel")).isVisible();
        assertThat(page.getByText("Show Records:")).isVisible();
        assertThat(page.locator("#per_page_records")).isVisible();
        assertThat(page.getByLabel("Next »")).isVisible();
                return true;
            }
                    }



