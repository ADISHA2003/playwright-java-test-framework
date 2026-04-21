package PlaywrightPageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EntityExportActions extends BasePageActions {

    public EntityExportActions(Page page) {
        super(page); // Set default timeout from BasePageActions.
    }

    // Navigate to entity details page with dynamic path
    public void navigateToEntityPage(String domain, String entityPath) {
        String entityUrl = domain + entityPath;
        page.navigate(entityUrl, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    // Trigger export
    public void exportEntity() {
        Locator exportDetailsButton = page.locator("#export-button-id-for-details-page");
        safeClick(exportDetailsButton);

        Locator switchInput = page.locator("input.MuiSwitch-input");
        safeClick(switchInput);

        // Corrected locator to be more specific and stable.
        Locator finalExportButton = page.locator("button.button.primary.cta.md.plr-12:has-text('Export')");
        assertThat(finalExportButton).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(30000));
        safeClick(finalExportButton);
    }

    public void waitForTimeout(int milliseconds) {
        page.waitForTimeout(milliseconds);
    }

    // Get latest page name
    public String getLatestPageName() {
        return page.locator("tbody tr:first-child td:nth-child(4) div.nowrap")
                .innerText().trim();
    }

    // Get current status text
    public String getLatestEntityStatus() {
        return page.locator("tbody tr:first-child td:nth-child(5) span")
                .innerText().trim();
    }

    // Refresh page
    public void reloadPage() {
        page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    // Poll until export is processed
    public boolean waitForExportProcessed() {
        boolean success = false;

        for (int i = 0; i < 4; i++) {  // 2 minutes max
            reloadPage();
            page.waitForTimeout(2000);

            String status = getLatestEntityStatus();

            if (status.equalsIgnoreCase("Processed")) {
                success = true;
                break;
            } else if (status.equalsIgnoreCase("Failed")) {
                break;
            }

            page.waitForTimeout(15000);
        }
        return success;
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
                throw e; // rethrow original to preserve context
            }
        }
    }
}
