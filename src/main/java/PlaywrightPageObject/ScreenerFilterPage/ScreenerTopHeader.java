package PlaywrightPageObject.ScreenerFilterPage;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenerTopHeader extends BasePageActions{
    public ScreenerTopHeader(Page page) {
        super(page);
    }   

    // Page Actions
    private Locator screenerHeaderTitle = page.locator(".page-heading");
    
    public void thenIShouldBeOnScreenerFilterPage(String filterType,String tile) {
        String header = new String();
        switch (filterType.toLowerCase()) {
            case "companies":
            case "company":
                header = "Company Screener";
                System.out.println("User is on Company Screener Filter Page");
                break;
            case "deals":
            case "deal":if(tile.equalsIgnoreCase("Merger and Acquisition")) header = "Merger & Acquisition Screener";
                        else header = tile+" Screener"; break;
            default:
                header = tile+" Screener";
                break;
        }
        page.waitForLoadState();
        assertThat(screenerHeaderTitle).hasText(header);
    }
}
