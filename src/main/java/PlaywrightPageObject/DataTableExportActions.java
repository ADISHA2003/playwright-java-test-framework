package PlaywrightPageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DataTableExportActions extends BasePageActions {

    // Locators
    private final Locator checkbox;
    private final Locator exportBtnMain;
    private final Locator exportBtnConfirm;
    private final Locator viewAllResultsBtn;

    public void navigateToScreenerPageWithSavedCriteria(String domain, String screenerPath) {
        page.navigate(domain + screenerPath);
        safeClick(viewAllResultsBtn);
    }

    public void navigateToCompanyTab(String domain) {
        page.navigate(domain + "screener/company/view-results");
    }

    public DataTableExportActions(Page page) {
        super(page); // Set default timeout from BasePageActions.
        this.checkbox = page.locator("span.checkmark.m-0").first();
        this.viewAllResultsBtn = page.locator("button:has-text('View All Result')");
        this.exportBtnMain = page.locator("button#export-button-id-for-screener-page");
        this.exportBtnConfirm = page.locator("button#export-button");
    }

    public void navigateToDataTableExport(String domain, String screenerPath) {
        page.navigate(domain + screenerPath);
        // Wait for the main export button to be visible to ensure the page is loaded.
        exportBtnMain.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void exportData() {
        safeClick(checkbox);
        safeClick(exportBtnMain);

        // Wait for the confirmation export button to be enabled before clicking.
        assertThat(exportBtnConfirm).isVisible();
        assertThat(exportBtnConfirm).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(30000));
        safeClick(exportBtnConfirm);
    }

    // New helper to navigate to linked screener pages (investors / deals / professionals)
    public void navigateToLinkedTab(String domain, String relativePath) {
        String url = domain + relativePath;
        page.navigate(url);
        // Wait for a table to appear on the linked pages (adjust selector if needed)
        Locator table = page.locator("table").first();
        table.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
    }

    public void selectAllLinkedSectionsForExport() {
        
            safeClick(exportBtnMain);

            Locator investorsCheckmark = page.locator("div:nth-child(6) > .flex.space-between > div > .custom-checkbox > .checkmark");
            safeClick(investorsCheckmark);

            Locator dealsCheckmark = page.locator("div:nth-child(7) > .flex.space-between > div > .custom-checkbox > .checkmark");
            safeClick(dealsCheckmark);

            Locator professionalsCheckmark = page.locator("div:nth-child(8) > .flex.space-between > div > .custom-checkbox > .checkmark");
            safeClick(professionalsCheckmark);

            page.waitForTimeout(1000);
            safeClick(exportBtnConfirm);
            page.waitForTimeout(1000);
    }

    // Helper method for a robust click action.
    private void safeClick(Locator locator) {
        try {
            // Wait for the element to be visible with a generous timeout.
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
            locator.scrollIntoViewIfNeeded();
            locator.click(new Locator.ClickOptions().setTimeout(20000));
        } catch (PlaywrightException e) {
            // If the standard click fails, attempt a force click as a fallback.
            try {
                locator.scrollIntoViewIfNeeded();
                locator.click(new Locator.ClickOptions().setForce(true).setTimeout(20000));
            } catch (PlaywrightException ex) {
                // rethrow original to preserve context
                throw e;
            }
        }
    }
}
