package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.PlaywrightException;

import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BulkUploadActions extends BasePageActions {

    private final Locator fileInput;
    private final Locator nextBtn;
    private final Locator chooseColumnBtn;
    private final Locator fetchCompaniesBtn;
    private final Locator companiesReadyHeader;
    private final Locator backdrop;
    private final Locator popover;
    private final Locator uploadExcelCsvTab;

    public BulkUploadActions(Page page) {
        super(page);
        this.fileInput = page.locator("#file");
        this.nextBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
        this.chooseColumnBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose Column"));
        this.fetchCompaniesBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Fetch Companies"));
        this.companiesReadyHeader = page.locator("div.fs-18.fw-600.ml-12.mr-12.mt-8").filter(new Locator.FilterOptions().setHasText("Companies Ready For Export"));
        this.backdrop = page.locator(".MuiBackdrop-root");
        this.popover = page.locator("#simple-popover");
        this.uploadExcelCsvTab = page.getByText("Upload Excel/CSV");
    }

    public void uploadFile(String filePath) {
        fileInput.waitFor();
        fileInput.setInputFiles(Paths.get(filePath));
    }

    public void clickUploadExcelCsvTab() {
        uploadExcelCsvTab.click();
    }

    public void clickNext() {
        nextBtn.click();
    }

    public void clickChooseColumn() {
        chooseColumnBtn.click();
    }

    public void selectColumnOption(String columnName) {
        popover.getByText(columnName).click();
    }

    public void clickFetchCompanies() {
        fetchCompaniesBtn.click();
        try {
            // Robust locator for the progress bar
            String progressBarSelector = "span[role='progressbar'].MuiLinearProgress-root";
            Locator progressBar = page.locator(progressBarSelector).first();

            // Wait for progress bar to appear (short timeout)
            progressBar.waitFor(
                    new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
            
            // Wait for progress bar to reach 100% OR disappear (completed)
            page.waitForFunction(
                    "selector => {" +
                            "  const progressBar = document.querySelector(selector);" +
                            "  if (!progressBar) return true;" + // Progress bar gone implies process finished
                            "  return progressBar.getAttribute('aria-valuenow') === '100';" + // Reached 100%
                            "}",
                    progressBarSelector,
                    new Page.WaitForFunctionOptions().setTimeout(300000)
            );
        } catch (PlaywrightException e) {
            System.out.println("Progress bar did not appear or completed too quickly: " + e.getMessage());
        }
    }

    public int getCompaniesReadyCount() {
        companiesReadyHeader.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String text = companiesReadyHeader.innerText();
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Could not extract count from text: " + text);
    }

    public void clickBackdrop() {
        backdrop.click();
    }

    public void dismissBlockingPopup() {
        try {
            page.waitForSelector("div.MuiPopover-root, div.MuiModal-root", 
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
            page.mouse().click(10, 10);
        } catch (PlaywrightException e) {
            System.out.println("Popup not found or already closed: " + e.getMessage());
        }
    }
}
