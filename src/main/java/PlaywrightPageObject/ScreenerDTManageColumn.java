package PlaywrightPageObject;

import Constants.FilePath;
import PlaywrightPageObject.DataTable.DataTableService;
import PlaywrightPageObject.DataTable.HandlingRowOperations;
import PlaywrightPageObject.PojoClasses.ScreenerTab;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScreenerDTManageColumn extends BasePageActions{

    private final HandlingRowOperations handlingRowOperations;
    private  final DataTableService dataTableService;
    public ScreenerDTManageColumn(Page page) {
        super(page);
        handlingRowOperations = new HandlingRowOperations(page);
        dataTableService = new DataTableService(page);
    }

    public void resetToDefaultColumns(){

        // Skip for Linked Charges as it doesn't support column management
        if (page.url().contains("/charges")) {
            return;
        }

        Locator tableHeader = page.locator("table thead tr th .svg-icon.fill.col-nav").first();
        waitForElementToBeVisible(tableHeader,30);

        page.waitForTimeout(500);
        Locator columnsMenu = page.locator("div.flex-align-right.flex .flex button",
                new Page.LocatorOptions().setHasText(Pattern.compile("^\\s*Column\\b")));

        waitForElementToBeVisible(columnsMenu, 30);
        click(columnsMenu);

        // wait for side drawer to be visible.
        Locator sideDrawer = page.locator(".MuiPaper-root.MuiPaper-elevation");
        waitForElementToBeVisible(sideDrawer, 30);

        // click reset to default columns
        Locator resetBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset"));
        waitForElementToBeVisible(resetBtn, 30);
        click(resetBtn);
        System.out.println("click");

    }

    public void selectAllColumns(){

        // Skip for Linked Charges as it doesn't support column management
        if (page.url().contains("/charges")) {
            return;
        }

        Locator tableHeader = page.locator("table thead tr th .svg-icon.fill.col-nav").first();
        waitForElementToBeVisible(tableHeader,30);

        page.waitForTimeout(500);
        Locator columnsMenu = page.locator("div.flex-align-right.flex .flex button",
                new Page.LocatorOptions().setHasText(Pattern.compile("^\\s*Column\\b")));

        waitForElementToBeVisible(columnsMenu, 30);
        click(columnsMenu);

        // wait for side drawer to be visible.
        Locator sideDrawer = page.locator(".MuiPaper-root.MuiPaper-elevation");
        waitForElementToBeVisible(sideDrawer, 30);

        // click reset to default columns
        String selectAllCheckbox = "label.chip";
        waitForElementToBeVisible(selectAllCheckbox, 30);
        handlingRowOperations.toggleCheckbox(selectAllCheckbox, true);
        System.out.println("Select All");

        // click apply button
        Locator apply = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"));
        waitForElementToBeVisible(apply, 30);
        click(apply);


    }

    public static class Tab {
        public String tabName;
        public List<FilterDef> filters;
    }

    public static class FilterDef {
        public String filterName;
        public boolean isDefault;
    }

    // put this enum in the same class (or in its own file)
    public enum ColumnMode {
        DEFAULT,     // only isDefault == true
        NON_DEFAULT, // only isDefault == false
        ALL          // return all filters regardless of isDefault
    }


    /**
     * Generic reader to return column names from JSON for a given tab.
     *
     * @param jsonFilePath path to JSON file
     * @param tabName      tab name to match (case-insensitive)
     * @param mode         which columns to return: DEFAULT / NON_DEFAULT / ALL
     * @param normalize    whether to call normalizeText(...) on each returned name
     * @return list of column names (order preserved from JSON)
     */
    public List<String> readColumnsFromJson(String jsonFilePath, String tabName, ColumnMode mode, boolean normalize) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<ScreenerTab> tabs = mapper.readValue(
                    Paths.get(jsonFilePath).toFile(),
                    new TypeReference<List<ScreenerTab>>() {}
            );

            if (tabs == null || tabs.isEmpty()) {
                return Collections.emptyList();
            }

            Optional<ScreenerTab> matched = tabs.stream()
                    .filter(t -> t.getTabName() != null && t.getTabName().equalsIgnoreCase(tabName))
                    .findFirst();

            if (!matched.isPresent() || matched.get().getFilters() == null) {
                return Collections.emptyList();
            }

            return matched.get().getFilters().stream()
                    .filter(Objects::nonNull)
                    .filter(f -> {
                        if (mode == ColumnMode.DEFAULT) return f.isDefaultFlag();
                        if (mode == ColumnMode.NON_DEFAULT) return !f.isDefaultFlag();
                        return true; // ALL
                    })
                    .map(f -> {
                        String name = f.getFilterName();
                        return normalize ? normalizeText(name) : name;
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Failed to read/parse JSON file: " + jsonFilePath, e);
        }
    }


    /**
     * Get visible headers from UI and return normalized text list
     */
    public List<String> getVisibleColumnsName(boolean normalize) {

        Locator headerCells = page.locator("table thead tr th");

        // fallback if table headers are not present
        if (headerCells.count() == 0) {
            headerCells = page.locator("div.flex-align-right.flex .flex button.button");
        }

        List<String> headers = headerCells.allTextContents().stream()
                .filter(name -> !name.equalsIgnoreCase("Deal Details"))
                .collect(Collectors.toList());

        if (normalize) {
            return headers.stream()
                    .map(this::normalizeText)
                    .collect(Collectors.toList());
        }

        return headers;   // return raw text
    }


    /**
     * Normalize header/filter text for reliable comparison
     * - strips invisible characters
     * - removes currency symbols
     * - removes sort icons/arrows
     * - normalizes parentheses spacing
     * - collapses whitespace
     * - lowercases for case-insensitive match
     */
    private String normalizeText(String raw) {
        if (raw == null) return "";

        String cleaned = raw;

        // remove common invisible characters
        cleaned = cleaned.replace("\uFEFF", "")   // BOM
                .replace("\u200B", "")   // zero-width space
                .replace("\u00A0", " "); // non-breaking space -> normal space

        // remove currency symbols (add more symbols if needed)
        cleaned = cleaned.replaceAll("[₹$€£¥₩₽₺₴*]", "");

        // remove arrow / caret / sort icons
        cleaned = cleaned.replaceAll("[\\u25B2\\u25BC\\u25B3\\u25BD▴▾▲▼△▽]", "");

        // 🚀 remove any text inside parentheses including the parentheses
        // e.g., "(2021 to 2024)" → ""
        cleaned = cleaned.replaceAll("\\(.*?\\)", "");

        // collapse multiple spaces to single space and trim
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // lowercase for case-insensitive comparison
        cleaned = cleaned.toLowerCase(Locale.ROOT);

        return cleaned;
    }

    String getJsonFilePath(String screener) {

        switch (screener.toLowerCase()) {
            case "all":
                return FilePath.ScreenerDTColumns_TestFolder + "CompanyScreenerDTColumns_TestData.json";
            case "asset manager":
                return FilePath.ScreenerDTColumns_TestFolder + "AMScreenerDTColumns_TestData.json";
            case "fund":
                return FilePath.ScreenerDTColumns_TestFolder + "FundScreenerDTColumns_TestData.json";
            case "limited partner":
                return FilePath.ScreenerDTColumns_TestFolder + "LPScreenerDTColumns_TestData.json";
            case "family office":
                return FilePath.ScreenerDTColumns_TestFolder + "FOScreenerDTColumns_TestData.json";
            case "all deals":
                return FilePath.ScreenerDTColumns_TestFolder + "AllDealsScreenerDTColumns_TestData.json";
            case "private equity investment":
                return FilePath.ScreenerDTColumns_TestFolder + "PrivateEquityInvestmentScreenerDTColumns_TestData.json";
            case "merger and acquisition":
                return FilePath.ScreenerDTColumns_TestFolder + "MergerAndAcquisitionScreenerDTColumns_TestData.json";
            case "private equity exits":
                return FilePath.ScreenerDTColumns_TestFolder + "PrivateEquityExitsScreenerDTColumns_TestData.json";
            case "equity capital market":
                return FilePath.ScreenerDTColumns_TestFolder + "EquityCapitalMarketScreenerDTColumns_TestData.json";
            case "debt transaction":
                return FilePath.ScreenerDTColumns_TestFolder + "DebtTransactionScreenerDTColumns_TestData.json";
            default:
                throw new IllegalArgumentException("Unknown screener type: " + screener);
        }
    }

    public List<Locator> getSubTabLocator() {
        Locator tabs = page.locator("div.data-table-tabs a");
        waitForElementToBeVisible(tabs.nth(1), 30);
        int count = tabs.count();

        List<Locator> tabLocators = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tabLocators.add(tabs.nth(i));
        }
        return tabLocators;
    }

    public void navigateToSubTabs(Locator subtab){
        click(subtab);
        page.waitForTimeout(1000);
        page.reload();
    }


    public void verifyColumnsForAllSubTabs(Locator tabElement, String screener, String action) throws IOException {

        String jsonFilePath = getJsonFilePath(screener);


        String raw = tabElement.textContent().trim();
        String subTabName = raw.split("\\(")[0].trim();

        if (subTabName.equalsIgnoreCase("Linked Charges")) {
            return;
        }

        // VERIFY default columns for this tab
        if(action.equalsIgnoreCase("reset"))
            verifyColumns(jsonFilePath, subTabName, true, ColumnMode.DEFAULT);
        else
        //  Verify all columns for this tab
            verifyColumns(jsonFilePath, subTabName, true, ColumnMode.ALL);
    }


    public void verifyColumns(String jsonFilePath, String subTabName, boolean exactOrderMustMatch, ColumnMode columnMode) throws IOException {

        // 1) read expected defaults (normalized)
        List<String> expected = readColumnsFromJson(jsonFilePath, subTabName, columnMode, true);

        // 2) get actual headers visible on page (normalized)
        List<String> actual = getVisibleColumnsName(true);

        // debug logs
        System.out.println("Expected (normalized): " + expected);
        System.out.println("Actual   (normalized): " + actual);

        if (exactOrderMustMatch) {
            Assert.assertEquals(actual, expected,
                    "Visible table headers do not match expected default columns (order-sensitive).");
        } else {
            // compare as sets (ignoring order)
            Set<String> expectedSet = new LinkedHashSet<>(expected); // preserve unique order but compare as set
            Set<String> actualSet = new LinkedHashSet<>(actual);
            Assert.assertEquals(actualSet, expectedSet,
                    "Visible table headers do not match expected default columns (order-insensitive).");
        }
    }

    public void selectAllColumnsAndCancel() {

        // Skip for Linked Charges as it doesn't support column management
        if (page.url().contains("/charges")) {
            return;
        }

        // wait for table header (same as other methods)
        Locator tableHeader = page.locator("table thead tr th .svg-icon.fill.col-nav").first();
        waitForElementToBeVisible(tableHeader,30);

        page.waitForTimeout(500);

        // open Columns menu
        Locator columnsMenu = page.locator("div.flex-align-right.flex .flex button",
                new Page.LocatorOptions().setHasText(Pattern.compile("^\\s*Column\\b")));

        waitForElementToBeVisible(columnsMenu, 30);
        click(columnsMenu);

        // wait for side drawer
        Locator sideDrawer = page.locator(".MuiPaper-root.MuiPaper-elevation");
        waitForElementToBeVisible(sideDrawer, 30);

        // toggle "Select All" checkbox = true
        String selectAllCheckbox = "label.chip";
        waitForElementToBeVisible(selectAllCheckbox, 30);
        handlingRowOperations.toggleCheckbox(selectAllCheckbox, true);
        System.out.println("Select All (for cancel scenario)");

        // 🔴 click Cancel instead of Apply
        Locator cancel = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"));
        waitForElementToBeVisible(cancel, 30);
        click(cancel);

        System.out.println("Clicked Cancel – changes should NOT be applied");
    }

    public List<String> dragColumnInSideDrawer(int fromInd, int toInd) {

        // Skip for Linked Charges as it doesn't support column management
        if (page.url().contains("/charges")) {
            return Collections.emptyList();
        }

        // Ensure we start from default state
        resetToDefaultColumns();

        // Capture initial visible columns (normalized)
        List<String> before = getVisibleColumnsName(false);
//        System.out.println("Before drag: " + before);

        // Choose any two columns; you can replace with specific names if required
        String fromColumn = before.get(fromInd);  // e.g. "company name"
        String toColumn   = before.get(toInd);  // e.g. "trade name"

        // 1) Open side drawer
        Locator columnsMenu = page.locator("div.flex-align-right.flex .flex button",
                new Page.LocatorOptions().setHasText(Pattern.compile("^\\s*Column\\b")));
        waitForElementToBeVisible(columnsMenu, 30);
        click(columnsMenu);

        // side drawer container
        Locator sideDrawer = page.locator(".MuiPaper-root.MuiPaper-elevation");
        waitForElementToBeVisible(sideDrawer, 30);

        // 🔧 1) Locate the row elements for from & to columns inside drawer
        Locator fromRow = sideDrawer.locator("#manage-column-dragble-" + fromInd,
                new Locator.LocatorOptions().setHasText(fromColumn)).first();


        Locator toRow = sideDrawer.locator("#manage-column-dragble-" + toInd,
                new Locator.LocatorOptions().setHasText(toColumn)).first();


        // 🔧 2) Within each row, locate the DRAG HANDLE icon
        Locator fromHandle = fromRow.locator(".svg-icon.darkv1.fill.mln-4");
        Locator toHandle   = toRow.locator(".svg-icon.darkv1.fill.mln-4");

        waitForElementToBeVisible(fromHandle, 10);
        waitForElementToBeVisible(toHandle, 10);

        // 3) Perform drag & drop
        dragWithMouse(fromHandle, toHandle);
        page.waitForTimeout(1000); // let UI update

        // 3) Click Apply
        Locator apply = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"));
        waitForElementToBeVisible(apply, 30);
        click(apply);

        return before;
    }

    private void dragWithMouse(Locator from, Locator to) {

        from.scrollIntoViewIfNeeded();
        to.scrollIntoViewIfNeeded();


        var fromBox = from.boundingBox();
        var toBox = to.boundingBox();


        for (int i = 0; i < 5; i++) { // try up to 5 times
            fromBox = from.boundingBox();
            toBox = to.boundingBox();

            if (fromBox != null && toBox != null) {
                break;
            }

            page.waitForTimeout(200); // small wait and retry
        }

        if (fromBox == null || toBox == null) {
            throw new RuntimeException("Could not get bounding box for drag elements in headless mode. " +
                    "fromBox=" + fromBox + ", toBox=" + toBox);
        }

        double fromX = fromBox.x + fromBox.width / 2;
        double fromY = fromBox.y + fromBox.height / 2;
        double toX   = toBox.x + toBox.width / 2;
        double toY   = toBox.y + toBox.height / 2;

        // 3) Actually drag with multiple steps (more reliable in headless)
        page.mouse().move(fromX, fromY);
        page.mouse().down();
        page.mouse().move(toX, toY, new com.microsoft.playwright.Mouse.MoveOptions().setSteps(15));
        page.mouse().up();
    }

    public void verifyDragAndDrop(List<String> before, int fromInd, int toInd){
        List<String> after = getVisibleColumnsName(false);
//        System.out.println("After drag: " + after);

        List<String> expected = new ArrayList<>(before);
        Collections.swap(expected, fromInd, toInd);
//        System.out.println("expected drag: " + expected);
        Assert.assertEquals(after, expected);
    }

}
