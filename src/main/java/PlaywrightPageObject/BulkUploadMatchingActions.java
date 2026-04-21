package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BulkUploadMatchingActions extends BasePageActions {

    private final Locator fileInput;
    private final Locator nextBtn;
    private final Locator chooseColumnBtn;
    private final Locator fetchCompaniesBtn;
    private final Locator companiesReadyHeader;
    private final Locator backdrop;
    private final Locator popover;
    private final Locator uploadExcelCsvTab;
    private final Locator matchedCompaniesCount;
    private final Locator partiallyMatchedCompaniesCount;
    private final Locator notFoundCompaniesCount;
    private final Locator reviewUnmatchedCompaniesButton;
    private final Locator companiesNotFoundButton;
    private final Locator closeDialogIcon;


    public BulkUploadMatchingActions(Page page) {
        super(page);
        this.fileInput = page.locator("#file");
        this.nextBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
        this.chooseColumnBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose Column"));
        this.fetchCompaniesBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Fetch Companies"));
        this.companiesReadyHeader = page.locator("div.fs-18.fw-600.ml-12.mr-12.mt-8:has-text('Companies Ready For Export')");
        this.backdrop = page.locator(".MuiBackdrop-root");
        this.popover = page.locator("#simple-popover");
        this.uploadExcelCsvTab = page.getByText("Upload Excel/CSV");
        this.matchedCompaniesCount = page.getByText(Pattern.compile("\\d+\\s+Companies Matched"));
        this.partiallyMatchedCompaniesCount = page.getByText(Pattern.compile("\\d+\\s+Companies Partially Matched"));
        this.notFoundCompaniesCount = page.getByText(Pattern.compile("\\d+\\s+Companies Not Found"));
        this.reviewUnmatchedCompaniesButton = page.locator("//button[@id='reviewButton' and contains(text(),'Review Unmatched Companies')]");
        this.companiesNotFoundButton = page.locator("a.button.flex:has-text('Companies Not Found')");
        this.closeDialogIcon = page.locator("span.svg-icon.dialog-close");
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
        page.waitForTimeout(10000);
    }

    public int getCompaniesReadyCount() {
        companiesReadyHeader.waitFor();
        String text = companiesReadyHeader.innerText();
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Could not extract count from text: " + text);
    }

    private int getCountFromLocator(Locator locator, String description) {
        locator.waitFor();
        String text = locator.innerText();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Could not extract count for '" + description + "' from text: " + text);
    }

    public int getMatchedCompaniesCount() {
        return getCountFromLocator(matchedCompaniesCount, "Companies Matched");
    }

    public int getPartiallyMatchedCompaniesCount() {
        return getCountFromLocator(partiallyMatchedCompaniesCount, "Companies Partially Matched");
    }

    public int getNotFoundCompaniesCount() {
        return getCountFromLocator(notFoundCompaniesCount, "Companies Not Found");
    }

    public void clickMatchedCompaniesFilter() {
        matchedCompaniesCount.click();
        page.waitForTimeout(1000); // Allow grid to refresh
    }

    public void clickPartiallyMatchedCompaniesFilter() {
        partiallyMatchedCompaniesCount.click();
        page.waitForTimeout(1000); // Allow grid to refresh
    }

    public void clickNotFoundCompaniesFilter() {
        notFoundCompaniesCount.click();
        page.waitForTimeout(1000); // Allow grid to refresh
    }

    public List<String> getVisibleCompanyNames() {
        Locator rows = page.locator("//tbody//span[contains(@class,'text-eclipse')]");

        return rows.all().stream()
                .map(locator -> {
                    String aria = locator.getAttribute("aria-label");
                    if (aria != null && !aria.trim().isEmpty()) {
                        return aria.trim();
                    }
                    // fallback to visible text inside <a>
                    return locator.innerText().trim();
                })
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public void clickReviewUnmatchedCompaniesButton() {
        reviewUnmatchedCompaniesButton.click();
    }

    public void clickCompaniesNotFoundButton() {
        companiesNotFoundButton.click();
    }

    public List<String> getUnmatchedCompanyNamesFromModal() {
        page.waitForSelector("div.MuiModal-root");
        Locator companyNameLocators = page.locator("div.MuiModal-root td span[id^='tooltip']");

        return companyNameLocators.all().stream()
                .map(Locator::innerText)
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getNotFoundCompanyNamesFromModal() {
        page.waitForSelector("div.MuiModal-root");
        Locator companyNameLocators = page.locator("div.MuiModal-root td span[id^='tooltip']");

        return companyNameLocators.all().stream()
                .map(Locator::innerText)
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public void clickBackdrop() {
        backdrop.click();
    }

    public void dismissBlockingPopup() {
        page.waitForSelector("div.MuiPopover-root, div.MuiModal-root");
        page.mouse().click(10, 10);
    }

    public void clickDialogCloseIcon() {

        closeDialogIcon.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        closeDialogIcon.click();
    }
}