package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AMdpUIactions extends BasePageActions {

    public AMdpUIactions(Page page) {
        super(page);
    }

    public void navigateToAssetManagerDP(String domain, String assetId) {
        page.navigate(domain + "investor/asset-manager/" + assetId + "/overview");
        page.waitForTimeout(3000);
    }

    public void Check_FUM_tableisNotEmpty() {
        Locator rows = page.locator("[data-testid=\"company-details-data-table\"] tbody tr");
        // Wait till at least one row appears and is visible
        rows.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        
        int count = rows.count();
        if (count <= 1) {
            throw new AssertionError("Funds Under Management table should have more than 1 row, but found " + count + " row(s).");
        }
    }

    public void Check_Portfolio_tableisNotEmpty() {
        Locator rows = page.locator(".data-table table tbody tr");
        // Wait till at least one row appears and is visible
        rows.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        
        int count = rows.count();
        if (count <= 1) {
            throw new AssertionError("Portfolio table should have more than 1 row, but found " + count + " row(s).");
        }
    }

    public void navigate_To_AMDP_Portfolio_tab(String domain, String assetId) {
        page.navigate(domain + "investor/asset-manager/" + assetId + "/portfolio");
        page.waitForTimeout(3000);
    }
}