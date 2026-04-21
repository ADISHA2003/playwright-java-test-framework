package PlaywrightPageObject;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BulkExportActions extends BasePageActions {

    // Locators
    private final Locator addCompaniesBulkBtn;
    private final Locator savedListOption;
    private final Locator AddToExportListBtn;
    private final Locator exportBtn;
    private final Locator acknowledgeCheckbox;
    private final Locator finalExportBtn;
    private final Locator exportSuccessText;
    private final Locator searchCompanyInput;
    private final Locator firstSearchResult;
    private final Locator companiesReadyForExportHeader;
    private final Locator closeDialogIcon;
    private final Locator clearListBtn;
    private final Locator confirmClearListBtn;

    public BulkExportActions(Page page) {
        super(page);
        this.addCompaniesBulkBtn = page.locator("button:has-text('Add Companies in Bulk')");
        this.savedListOption = page.locator("text=Saved List");
        this.AddToExportListBtn = page.locator("button:has-text('Add to Export List')");
        this.exportBtn = page.locator("button:text-is('Export')");
        this.acknowledgeCheckbox = page.locator("label:has(input[type='checkbox']) input[type='checkbox']");
        this.finalExportBtn = page.locator("div.MuiDialog-container button.button.primary.cta:has-text('Export')");
        this.exportSuccessText = page.locator("div.fw-600:has-text('Export Request Successfully Raised')");
        this.searchCompanyInput = page.locator("input[placeholder='Search & Add Companies']");
        this.firstSearchResult = page.locator("div.dropdown-card:visible div.list-hover");
        this.companiesReadyForExportHeader = page.locator("div.fs-18.fw-600.ml-12.mr-12.mt-8:has-text('Companies Ready For Export')");
        this.closeDialogIcon = page.locator("span.svg-icon.stroke.hover.dialog-close");
        this.clearListBtn = page.locator("button.button-type-text.link:has-text('Clear List')");
        this.confirmClearListBtn = page.locator("button.button.primary.cta.md:has-text('Confirm')");
    }

    public void navigateToBulkExport(String domain) {
        page.navigate(domain + "bulk-export");
    }

    public void clickAddCompaniesInBulk() {
        safeClick(addCompaniesBulkBtn);
    }

    public void clickSavedList() {
        safeClick(savedListOption);
    }

    public void clickCheckboxForList(String listText) {
        Locator checkbox = page.locator(String.format("tr:has-text('%s') label.custom-checkbox span.checkmark", listText));
        safeClick(checkbox);
    }

    public void clickAddToExportListButton() {
        assertThat(AddToExportListBtn).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(30000));
        safeClick(AddToExportListBtn);
    }

    public void searchForCompany(String companyName) {
        searchCompanyInput.click();
        searchCompanyInput.fill(companyName);
        page.waitForTimeout(2000); // Wait for search results to appear
    }

    public void clickFirstSearchResult() {
        firstSearchResult.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        firstSearchResult.first().click();
    }

    public int getCompaniesForExportCount() {
        page.waitForTimeout(10000);
        companiesReadyForExportHeader.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));

        // Explicitly wait for the count to appear in the text, e.g., "(10)".
        companiesReadyForExportHeader.first().page().waitForFunction("el => el.textContent.includes('(')", companiesReadyForExportHeader.first().elementHandle());

        String headerText = companiesReadyForExportHeader.first().innerText();

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\((\\d+)\\)");
        java.util.regex.Matcher matcher = pattern.matcher(headerText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new PlaywrightException("Could not find company count in header text: " + headerText);
    }
    
    public void waitForCompanyCountToUpdate(int initialCount) {
        // Wait for the text in the header to reflect a number greater than the initial count.
        companiesReadyForExportHeader.first().page().waitForFunction(
            "el => {" +
            "  const match = el.textContent.match(/\\((\\d+)\\)/);" +
            "  return match && parseInt(match[1]) > " + initialCount + ";" +
            "}",
            companiesReadyForExportHeader.first().elementHandle()
        );
    }
    public void clickExportButton() {
        safeClick(exportBtn);
    }

    public void clickAcknowledgeCheckbox() {
        safeClick(acknowledgeCheckbox);
    }

    public void clickFinalExportButton() {
        // Wait for the button to be enabled before clicking.
        assertThat(finalExportBtn).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(30000));
        safeClick(finalExportBtn);
    }

    public void clickCloseDialog() {
        safeClick(closeDialogIcon);
    }

    public void clickClearList() {
        safeClick(clearListBtn);
    }

    public void clickConfirmClearList() {
        safeClick(confirmClearListBtn);
    }

    public boolean isExportSuccessVisible() {
        try {
            exportSuccessText.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    // Helper method for a robust click action.
    private void safeClick(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
            locator.scrollIntoViewIfNeeded();
            locator.click(new Locator.ClickOptions().setTimeout(20000L));
        } catch (PlaywrightException e) {
            // Fallback to force click if standard click fails.
            locator.click(new Locator.ClickOptions().setForce(true).setTimeout(20000L));
        }
    }
}
