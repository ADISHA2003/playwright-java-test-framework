package PlaywrightPageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.testng.Assert;
import com.microsoft.playwright.PlaywrightException;
import PlaywrightPageObject.BasePageActions;

public class KeyDevActions extends BasePageActions {

  public KeyDevActions(Page page) {
    super(page); // Set default timeout from BasePageActions.
  }

  public void navigateToKeyDevelopments() {
    // Navigate to the Key Developments page.
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Key Developments")).click();
    // Wait for the page to be fully loaded.
    page.waitForLoadState(LoadState.NETWORKIDLE);
    page.waitForSelector("button:has-text('Filters')");
    waitForKeyDevelopementTypeOnSideBarToBeVisible();
    page.waitForTimeout(20000);
  }

  public void applySourceFilter(String sourceName) {
    // Ensure the page is idle before interacting.
    page.waitForLoadState(LoadState.NETWORKIDLE);
    Locator filtersBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filters"));
    safeClick(filtersBtn);

    page.waitForTimeout(20000);
    // Open the "Sources" filter options.
    Locator sourcesButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sources"));
    safeClick(sourcesButton);

    // Select the desired source from the filter list.
    Locator sourceOption = page.locator("div.ff-item label").filter(new Locator.FilterOptions().setHasText(sourceName));
    safeClick(sourceOption);

    Locator applyBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"));
    safeClick(applyBtn);

    page.waitForTimeout(2000);
    // Wait for the source filter tag to appear on the page.
    page.waitForSelector("span[aria-label^='Source:']");
  }

  public void verifyVccEdgeSourceVisible() {
    // Primary locator: Check for a span with an aria-label containing 'VCCEdge'.
    Locator sourceTag = page.locator("span[aria-label*='VCCEdge']").first();
    try {
      sourceTag.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
      Assert.assertTrue(sourceTag.isVisible(), "Source 'VCCEdge' not visible!");
      return;
    } catch (PlaywrightException ignored) {
      // If primary fails, try other locators.
    }
    // Fallback 1: Check for any span element containing the text 'VCCEdge'.
    Locator spanText = page.locator("span:has-text('VCCEdge')").first();
    try {
      // Explicitly set a longer timeout for this fallback
      spanText.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
      Assert.assertTrue(spanText.isVisible(), "Source 'VCCEdge' not visible via span text!");
      return;
    } catch (PlaywrightException ignored) {
      // Log failure for debugging purposes.
      System.out.println("Fallback 1 (span:has-text('VCCEdge')) failed for VCCEdge visibility.");
      // Continue to the next fallback.
    }

    // Fallback 2: Check for any element on the page containing the text 'VCCEdge'.
    Locator anyText = page.locator(":text('VCCEdge')").first();
    try {
      // Explicitly set a longer timeout for this fallback
      anyText.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
      Assert.assertTrue(anyText.isVisible(), "Source 'VCCEdge' not visible via generic text search!");
      return;
    } catch (PlaywrightException e) {
      // If all locators fail, throw an assertion error.
      throw new AssertionError("Source 'VCCEdge' not visible (all locators exhausted).", e);
    }
  }

  public void verifyNonVccEdgeSourceVisible(String expectedSource) {
    Locator sourceTag = page.locator("span[aria-label^='Source:']").first();
    // Explicitly set a longer timeout for this verification
    sourceTag.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
    String sourceText = sourceTag.textContent().trim();
    Assert.assertTrue(sourceText.contains(expectedSource), "Source mismatch! Expected to contain: " + expectedSource + ", but found: " + sourceText);
  }

    public void addToReadingListAndVerify() {

        page.waitForLoadState(LoadState.NETWORKIDLE,
                new Page.WaitForLoadStateOptions().setTimeout(15000));

        // Wait for first item
        Locator firstItem = page.locator("div.tags").first();
        firstItem.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(15000));

        String keyDevText = firstItem.textContent().trim();

        // Locate bookmark icon
        Locator firstBookmark = page.locator("span[id^='bookmarkIconUniversal']").first();

        // ---------- CHECK IF ALREADY BOOKMARKED ----------
        String classAttr = firstBookmark.getAttribute("class");
        boolean alreadyBookmarked = classAttr != null && classAttr.contains("active");

        if (!alreadyBookmarked) {
            System.out.println("Item is not bookmarked. Bookmarking now...");
            safeClick(firstBookmark);

            // Wait until bookmark becomes active instead of hard wait
            page.waitForCondition(() -> {
                String updatedClass = firstBookmark.getAttribute("class");
                return updatedClass != null && updatedClass.contains("active");
            });

        } else {
            System.out.println("Item is already bookmarked. Skipping bookmark click.");
        }

        // ---------- NAVIGATE TO READING LIST ----------
        Locator readingListBtn = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Reading List")
        );
        safeClick(readingListBtn);

        page.waitForSelector("div.tags");

        String readingListText = page.locator("div.tags").first().textContent().trim();

        // ---------- VERIFY ----------
        Assert.assertTrue(
                readingListText.contains(keyDevText.split(" ")[0]),
                "Not found in Reading List!"
        );
    }

    public void applyDateFilter() {
    page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(15000));

    Locator filtersButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Filters"));
    safeClick(filtersButton);

    Locator dialogClose = page.locator("span.svg-icon.stroke.hover.dialog-close");
    if (dialogClose.count() > 0 && dialogClose.isVisible()) {
      safeClick(dialogClose);
      // Wait for the dialog to close before proceeding.
      page.waitForSelector("span.svg-icon.stroke.hover.dialog-close",
          new Page.WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED).setTimeout(8000));
    }

    Locator dateFilterButton = page.locator(
      "div.ml-8 div.button.secondary.blue-text.inline-flex.fs-12:has(span:has-text('Filter By Date'))"
    );
    dateFilterButton.scrollIntoViewIfNeeded();
    safeClick(dateFilterButton);

    Locator lastWeek = page.getByText("Last Week", new Page.GetByTextOptions().setExact(true));
    safeClick(lastWeek);

    Locator saveBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));
    safeClick(saveBtn);

    Locator dateRange = page.locator("div.snf-meta strong:has-text('Range:')").first();
    Assert.assertTrue(dateRange.isVisible(), "Date range not shown!");
  }

  // Helper method for a robust click action.
  private void safeClick(Locator locator) {
    try {
      // Wait for the element to be visible with a generous timeout.
      locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
      locator.scrollIntoViewIfNeeded();
      locator.click(new Locator.ClickOptions().setTimeout(20000L));
    } catch (PlaywrightException e) {
      // If the standard click fails, attempt a force click as a fallback.
      try {
        locator.scrollIntoViewIfNeeded();
        locator.click(new Locator.ClickOptions().setForce(true).setTimeout(20000L));
      } catch (PlaywrightException ex) {
        // rethrow original to preserve context
        throw e;
      }
    }
  }

  public void waitForKeyDevelopementTypeOnSideBarToBeVisible(){
      Locator sideKeyDevTypes = page.locator("css=.sidebar-content div").nth(0);
      waitForElementToBeVisible(sideKeyDevTypes,30);
  }
}
