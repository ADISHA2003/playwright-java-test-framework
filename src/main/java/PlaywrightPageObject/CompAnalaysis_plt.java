package PlaywrightPageObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;


public class CompAnalaysis_plt {
//	extends BasePageActions{
//}
//
//    Page page;
//    public CompAnalaysis_plt(Page page) {
//        super(page);
//        this.page = page;
//    }
@Test
    public void companalysis()
    {
            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));
                BrowserContext context = browser.newContext();
                Page page = context.newPage();

                page.navigate("https://devapp-v2.vccedge.com/market-intelligence/competitive-analysis");
                assertThat(page.getByPlaceholder("Try Searching for Swiggy")).isVisible();
                assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Start Search"))).isVisible();
                assertThat(page.locator(".border-box-header").first()).isVisible();
                page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Previous Competitive Analyses")).click();
                assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Previous Competitive Analyses"))).isVisible();
                assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Bookmarked Companies"))).isVisible();
                assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Frequently Asked Questions"))).isVisible();
                page.getByPlaceholder("Try Searching for Swiggy").click();
                page.getByPlaceholder("Try Searching for Swiggy").fill("swiggy");
                page.locator("li").filter(new Locator.FilterOptions().setHasText("Swiggy Pvt. Ltd.Internet")).click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Start Search")).click();
                assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Competitor Analysis - Swiggy"))).isVisible();
                assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("In Draft"))).isVisible();
                assertThat(page.locator(".ca-business-model")).isVisible();
                assertThat(page.getByText("Progression").nth(1)).isVisible();
                assertThat(page.getByText("Scale").nth(1)).isVisible();
                assertThat(page.getByText("Proficiency").nth(1)).isVisible();
                assertThat(page.getByText("Governance").nth(1)).isVisible();
                assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Result Preview (0)"))).isVisible();
                assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save & View all"))).isVisible();
            }
        }
    }
