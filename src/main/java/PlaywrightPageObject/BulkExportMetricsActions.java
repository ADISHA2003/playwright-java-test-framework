package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class BulkExportMetricsActions extends BasePageActions {

    private final Locator selectExportFieldsBtn;
    private final Locator templateDropdownBtn;
    private final Locator defaultTemplateOption;
    private final Locator exportFieldItems;
    private final Locator addToTableBtn;
    private final Locator dataTableHeaders;
    private final Locator resetToDefaultBtn;
    private final Locator closeMetricsModalBtn;

    public BulkExportMetricsActions(Page page) {
        super(page);
        this.selectExportFieldsBtn = page.locator("button:has-text('Select Export Fields')");
        this.templateDropdownBtn = page.locator("button:has-text('Default Template')");
        this.defaultTemplateOption = page.locator("h4:has-text('Test Automation Template')");
        this.exportFieldItems = page.locator("span.export-field-item__text");
        this.addToTableBtn = page.locator("button:has-text('Add to Table')");
        this.dataTableHeaders = page.locator("div.fw-500.fs-14");
        this.resetToDefaultBtn = page.locator("button:has-text('Reset to Default')");
        this.closeMetricsModalBtn = page.locator("button.export-metric-popover__header-close");
    }

    public void clickSelectExportFields() {
        selectExportFieldsBtn.click();
    }

    public void clickTemplateDropdown() {
        templateDropdownBtn.click();
    }

    public void selectDefaultTemplate() {
        defaultTemplateOption.click();
        page.waitForTimeout(3000);
    }

    public List<String> getExportFieldNames() {
        exportFieldItems.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return exportFieldItems.allInnerTexts().stream().map(String::trim).collect(Collectors.toList());
    }

    public void clickAddToTable() {
        addToTableBtn.click();
        page.waitForTimeout(3000);
    }
    
    public List<String> getDataTableHeaders() {
        dataTableHeaders.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        List<String> tableHeaders = dataTableHeaders.allInnerTexts().stream().map(String::trim).collect(Collectors.toList());
        System.out.println("Data Table Headers: " + tableHeaders);
        return tableHeaders;
    }

    public void clickResetToDefault() {
        resetToDefaultBtn.click();
    }

    public void clickCloseMetricsModal() {
        closeMetricsModalBtn.click();
    }

    public List<String> getMissingMetrics(List<String> selectedMetrics, List<String> tableHeaders) {
        List<String> missingMetrics = new ArrayList<>();
        for (String metric : selectedMetrics) {
            boolean matchFound = tableHeaders.stream().anyMatch(header ->
                    header.equalsIgnoreCase(metric) ||
                            header.toLowerCase().startsWith(metric.toLowerCase() + "(") ||
                            header.toLowerCase().startsWith(metric.toLowerCase() + " (")
            );
            if (!matchFound) {
                missingMetrics.add(metric);
            }
        }
        if (!missingMetrics.isEmpty()) {
            System.out.println("Metrics missing in Data Table: " + missingMetrics);
        }
        return missingMetrics;
    }
}
