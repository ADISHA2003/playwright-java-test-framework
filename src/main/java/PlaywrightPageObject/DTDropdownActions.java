package PlaywrightPageObject;

import Constants.FilePath;
import PlaywrightPageObject.DataTable.DataTableService;
import PlaywrightPageObject.DataTable.HandlingDropdDown;
import PlaywrightPageObject.PojoClasses.ScreenerTab;
import Utilities.JsonReaderService;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

import static Utilities.CurrencyUtils.resolveCurrencySymbol;

public class DTDropdownActions extends BasePageActions{
    HandlingDropdDown handlingDropdDown;
    DataTableService dataTableService;
    public DTDropdownActions(Page page) {
        super(page);
        handlingDropdDown = new HandlingDropdDown(page);
        dataTableService = new DataTableService(page);
    }

    public void changeCurrency(String currecy, int currencyDropdownIndex) {
        Locator currencyDropdown = page.locator(".flex-align-right.flex div.flex button ").nth(currencyDropdownIndex);
        Locator currencyOptions = page.locator(".border-box .button.transparent.sm");

        handlingDropdDown.selectFromDropdown(currencyDropdown, currencyOptions, currecy);
        page.waitForTimeout(4000);

    }

    public void verifyCurrencyInTableHeader(String currency) {

        String currencySymbol = resolveCurrencySymbol(currency);
        System.out.println(currencySymbol);

        String jsonFilePath =
                FilePath.ScreenerDTColumns_TestFolder + "CompanyScreenerDTColumns_TestData.json";

        // Read JSON
        List<ScreenerTab> tabs = JsonReaderService.readScreenerTabs(jsonFilePath);
        ScreenerTab tab = JsonReaderService.getTabByName(tabs, "Companies");

        List<String> currencyColumns =
                JsonReaderService.getCurrencyConversionColumns(tab);
        System.out.println(currencyColumns);

        if (currencyColumns.isEmpty()) {
            throw new RuntimeException("No columns found with currency conversion");
        }

        // RAW headers (actual UI text)
        List<String> visibleHeadersRaw =
                dataTableService.getVisibleColumnsName(false, false, false);

        // NORMALIZED headers (for matching)
        List<String> visibleHeadersNormalized =
                dataTableService.getVisibleColumnsName(true, true, true);
        System.out.println(visibleHeadersNormalized);

        // 🔴 Collect columns missing currency symbol
        Set<String> columnsMissingCurrency = new LinkedHashSet<>();

        for (String jsonColumn : currencyColumns) {

            String normalizedJsonColumn = jsonColumn.toLowerCase();

            for (int i = 0; i < visibleHeadersNormalized.size(); i++) {

                if (visibleHeadersNormalized.get(i).equalsIgnoreCase(normalizedJsonColumn)) {

                    String actualHeader = visibleHeadersRaw.get(i);
                    System.out.println("inside" + actualHeader);

                    if (!actualHeader.contains(currencySymbol)) {
                        columnsMissingCurrency.add(actualHeader);
                    }
                }
            }
        }

        // ❌ Fail once with all problematic columns
        if (!columnsMissingCurrency.isEmpty()) {
            throw new AssertionError(
                    "Currency symbol '" + currencySymbol + "' is missing in following column headers: "
                            + columnsMissingCurrency
            );
        }
    }

}
