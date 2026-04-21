package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class XLSDownloadsPage {
    private Page page;

    private String entityNameCell = "tbody tr:first-child td:nth-child(3)";
    private String statusCell = "tbody tr:first-child td:nth-child(5) span";

    public XLSDownloadsPage(Page page) {
        this.page = page;
    }

    public void navigateToDownloads(String domain) {
        page.navigate(domain + "my-downloads");
        page.waitForTimeout(2000);
    }

    public String getLatestEntityName() {
        Locator cell = page.locator(entityNameCell);
        // Check if there is a div.nowrap inside
        if (cell.locator("div.nowrap").count() > 0) {
            return cell.locator("div.nowrap").innerText().trim();
        } else {
            String text = cell.innerText().trim();
            // Return empty string if it's just "-"
            return text.equals("-") ? "" : text;
        }
    }

    public boolean waitForExportProcessed() {
        // Robust polling: try up to ~5 minutes (20 * 15s)
        int attempts = 20;
        int pollIntervalMs = 15000;
        boolean success = false;

        for (int i = 0; i < attempts; i++) {
            // Reload to get latest table data
            page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
            page.waitForTimeout(2000); // give DOM a bit to render

            Locator statusCellLocator = page.locator(statusCell);

            // If status cell is not present yet, wait a short while and retry
            if (statusCellLocator.count() == 0) {
                page.waitForTimeout(1000);
                // continue to next iteration after pollInterval
                page.waitForTimeout(pollIntervalMs - 1000);
                continue;
            }

            // Try to read the text; guard against null/empty
            String finalStatus = "";
            try {
                String txt = statusCellLocator.textContent();
                if (txt != null) finalStatus = txt.trim();
            } catch (Exception e) {
                finalStatus = "";
            }

            if (!finalStatus.isEmpty()) {
                if (finalStatus.equalsIgnoreCase("Processed")) {
                    success = true;
                    break;
                } else if (finalStatus.equalsIgnoreCase("Failed")) {
                    // stop early on explicit failure
                    break;
                }
            }

            // wait before next reload
            page.waitForTimeout(pollIntervalMs);
        }

        return success;
    }
}
