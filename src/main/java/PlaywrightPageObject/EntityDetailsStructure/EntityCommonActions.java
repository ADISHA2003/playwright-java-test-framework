package PlaywrightPageObject.EntityDetailsStructure;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EntityCommonActions extends BasePageActions {

    public EntityCommonActions(com.microsoft.playwright.Page page) {
        super(page);
    }
    String headingDescription = "//h3[text()='Business Description']";


    public void whenINavigateToEntityPageAndBookmark(String Domain, String entityEndPoint, String entityId) {
        String entityUrl = Domain + entityEndPoint + "/" + entityId;

        page.navigate(entityUrl);
        waitForElementToBeVisible(headingDescription, 30);


        String Bookmark = "#bookmarkIconUniversal" + entityId;
        waitForElementToBeVisible(Bookmark, 30);
        ensureEntityIsNotBookmarked(Bookmark);

        clickElementWhenVisible(Bookmark);
        thenIVerifyElementHasClass(Bookmark, "fill");

    }

    public void thenIVerifyElementHasClass(String selector, String expectedClass) {

        page.waitForSelector(
                selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(5000) // 5 seconds
        );

        if(hasClassValue(selector, expectedClass)) return ;

        throw new AssertionError("Expected element " + selector + " to have class '"
                + expectedClass);
    }

    public void ensureEntityIsNotBookmarked(String bookmarkSelector) {

        if (hasClassValue(bookmarkSelector, "fill")) {
            // Already bookmarked, so click again to unbookmark
            System.out.println("⚠️ Entity is already bookmarked, unbookmarking first.");
            page.click(bookmarkSelector);
            page.waitForSelector(bookmarkSelector + ":not(.fill)");
        }
    }
}
