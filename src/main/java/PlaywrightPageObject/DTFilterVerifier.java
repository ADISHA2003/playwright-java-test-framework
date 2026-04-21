package PlaywrightPageObject;

import Constants.ColumnType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTFilterVerifier {
    private final BasePageActions basePageActions;
    private final Page page;

    public DTFilterVerifier(Page page) {
        this.page = page;
        basePageActions = new BasePageActions(page);
    }

    public void verifyFilterResults(
            String tableSelector,
            int columnIndex,
            ColumnType type,            // STRING, NUMBER, DATE
            String operator,            // IN, NOT_IN, GTE, BETWEEN, ON_OR_AFTER...
            List<String> filterValues,  // ["India"], ["5","10"], ["01/01/2020"]
            String tooltipSelector,
            String[][] popupSteps
    ) {

        System.out.println("Column Index : " + columnIndex);

        Locator filterAppliedIcon = page.locator("table thead tr th").nth(columnIndex).locator(".svg-icon.fill.darkv1.sm");
        basePageActions.waitForElementToBeVisible(filterAppliedIcon, 10);

        Locator rows = page.locator(tableSelector + " tr");
        int rowCount = rows.count();

        if (rowCount == 0)
            throw new AssertionError("No rows found for filter verification.");

        // Normalize operator
        String op = operator.trim().toUpperCase().replace(" ", "_");

        List<String> failures = new ArrayList<>();

        // Pre-process numbers/dates
        Double num1 = null, num2 = null;
        LocalDate d1 = null, d2 = null;

        if (type == ColumnType.NUMBER) {
            if (!filterValues.isEmpty()) num1 = Double.parseDouble(filterValues.get(0));
            if (filterValues.size() == 2) num2 = Double.parseDouble(filterValues.get(1));
        }

        if (type == ColumnType.DATE) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if (!filterValues.isEmpty()) d1 = LocalDate.parse(filterValues.get(0), fmt);
            if (filterValues.size() == 2) d2 = LocalDate.parse(filterValues.get(1), fmt);
        }

        // Prepare lowercase filters for easy matching across table
        List<String> filterLower = filterValues.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        // Keep a set of filters found somewhere in the table (for STRING + IN requirement)
        Set<String> foundFilterLower = new HashSet<>();

        System.out.println("Number of Rows : " + rowCount);
        // For each row → verify according to column type
        for (int i = 0; i < rowCount; i++) {

            Locator cell = rows.nth(i).locator("td").nth(columnIndex);

            // extract all texts
            Set<String> rawTexts = collectTextsForCell(
                    cell,
                    null,
                    tooltipSelector,
                    popupSteps
            );

            System.out.println(rawTexts);
            boolean pass = false;

            switch (type) {
                case STRING:
                    pass = verifyString(rawTexts, op, filterValues);

                    // --- NEW: mark which filter values are found in this row (for IN check across table) ---
                    if (op.equals("IN") && !rawTexts.isEmpty()) {
                        Set<String> lowerTexts = rawTexts.stream()
                                .map(String::toLowerCase)
                                .collect(Collectors.toSet());

                        for (String f : filterLower) {
                            for (String t : lowerTexts) {
                                if (t.contains(f)) {
                                    foundFilterLower.add(f);
                                    break; // go to next filter
                                }
                            }
                        }
                    }
                    break;

                case NUMBER:
                    pass = verifyNumber(rawTexts, op, num1, num2);
                    break;

                case DATE:
                    pass = verifyDate(rawTexts, op, d1, d2);
                    break;
            }

            if (!pass) {
                failures.add("Row " + i + " failed. Extracted: " + rawTexts +
                        " | Operator: " + op +
                        " | FilterValues: " + filterValues);
            }

            System.out.println("\n");
        }

        // --- NEW: after scanning rows, for STRING + IN ensure all requested values were found somewhere ---
        if (type == ColumnType.STRING && op.equals("IN")) {
            List<String> missing = filterLower.stream()
                    .filter(f -> !foundFilterLower.contains(f))
                    .collect(Collectors.toList());

            if (!missing.isEmpty()) {
                // Map back to original-casing filterValues for clearer message
                List<String> missingOriginalCase = filterValues.stream()
                        .filter(f -> missing.contains(f.toLowerCase()))
                        .collect(Collectors.toList());

                throw new AssertionError("❌ Some filter values were not present in table for columnIndex " + columnIndex +
                        ". Missing values: " + missingOriginalCase + ". Found values: " + foundFilterLower);
            }
        }

        if (!failures.isEmpty()) {
            throw new AssertionError("❌ Filter verification failed:\n" + String.join("\n", failures));
        }

        System.out.println("✅ Filter verification passed for column " + columnIndex +
                " with operator " + operator + " and values " + filterValues);
    }


    private boolean verifyString(Set<String> texts, String op, List<String> values) {

        Set<String> lower = texts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<String> filterLower = values.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        switch (op) {

            case "IN":
                return filterLower.stream().anyMatch(f ->
                        lower.stream().anyMatch(t -> t.contains(f)));

            case "NOT_IN":
                return filterLower.stream().noneMatch(f ->
                        lower.stream().anyMatch(t -> t.contains(f)));

            default:
                throw new IllegalArgumentException("Unsupported STRING operator: " + op);
        }
    }

    private boolean verifyNumber(Set<String> texts, String op, Double v1, Double v2) {

        List<Double> numbers = texts.stream()
                .map(t -> t.replaceAll("[^0-9.\\-]", ""))
                .filter(t -> !t.isEmpty())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        if (numbers.isEmpty()) return false;

        for (double n : numbers) {
            switch (op) {
                case "BETWEEN":
                    if (n >= v1 && n <= v2) return true;
                    break;
                case "NOT_BETWEEN":
                    if (!(n >= v1 && n <= v2)) return true;
                    break;

                case "GT":
                    if (n > v1) return true;
                    break;
                case "GTE":
                    if (n >= v1) return true;
                    break;
                case "LT":
                    if (n < v1) return true;
                    break;
                case "LTE":
                    if (n <= v1) return true;
                    break;

                case "EQUALS":
                    if (n == v1) return true;
                    break;
                case "NOT_EQUALS":
                    if (n != v1) return true;
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported NUMBER operator: " + op);
            }
        }
        return false;
    }

    private boolean verifyDate(Set<String> texts, String op, LocalDate d1, LocalDate d2) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<LocalDate> dates = texts.stream()
                .map(t -> t.replaceAll("[^0-9/]", ""))
                .filter(t -> t.matches("\\d{2}/\\d{2}/\\d{4}"))
                .map(t -> LocalDate.parse(t, fmt))
                .collect(Collectors.toList());

        if (dates.isEmpty()) return false;

        for (LocalDate dt : dates) {

            switch (op) {

                case "BETWEEN":
                    if (!dt.isBefore(d1) && !dt.isAfter(d2)) return true;
                    break;

                case "NOT_BETWEEN":
                    if (dt.isBefore(d1) || dt.isAfter(d2)) return true;
                    break;

                case "ON_OR_AFTER":
                    if (!dt.isBefore(d1)) return true;
                    break;

                case "AFTER":
                    if (dt.isAfter(d1)) return true;
                    break;

                case "ON_OR_BEFORE":
                    if (!dt.isAfter(d1)) return true;
                    break;

                case "BEFORE":
                    if (dt.isBefore(d1)) return true;
                    break;

                case "EQUALS":
                    if (dt.equals(d1)) return true;
                    break;

                case "NOT_EQUALS":
                    if (!dt.equals(d1)) return true;
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported DATE operator: " + op);
            }
        }
        return false;
    }

    /**
     * Collects all candidate texts for a given cell:
     * - cell innerText
     * - aria-label
     * - name attribute
     * - tooltip (hover)
     * - any text extracted via valueSelectors (popup/modal/side drawer etc.)
     * <p>
     * Returns ALL found texts (case preserved).
     */
    public Set<String> collectTextsForCell(
            Locator cell,
            String searchTextForDebug,              // only used for logs; can be null
            String cellInnerSelectorForTooltip,
            String[][] valueSelectors
    ) {
        Set<String> collected = new HashSet<>();

        // 1. cell innerText
        String cellText = cell.innerText().trim();
        if (!cellText.isEmpty()) {
            collected.add(cellText);
            if (searchTextForDebug != null &&
                    cellText.toLowerCase().contains(searchTextForDebug.toLowerCase())) {
                System.out.println("Search text found in cellText : " + cellText);
            }
        }

        // 2. aria-label
        String ariaLabel = cell.getAttribute("aria-label");
        if (ariaLabel != null && !ariaLabel.isEmpty()) {
            collected.add(ariaLabel.trim());
            if (searchTextForDebug != null &&
                    ariaLabel.toLowerCase().contains(searchTextForDebug.toLowerCase())) {
                System.out.println("Search text found in Aria Label : " + ariaLabel);
            }
        }

        // 3. name attribute (you asked to cover "name" also)
        String nameAttr = cell.getAttribute("name");
        if (nameAttr != null && !nameAttr.isEmpty()) {
            collected.add(nameAttr.trim());
            if (searchTextForDebug != null &&
                    nameAttr.toLowerCase().contains(searchTextForDebug.toLowerCase())) {
                System.out.println("Search text found in name attribute : " + nameAttr);
            }
        }

        // 4. Tooltip via hover on inner element (if any)
        if (cellInnerSelectorForTooltip != null) {
            Locator innerForTooltip = cell;
            if (!cellInnerSelectorForTooltip.isEmpty()) {
                innerForTooltip = cell.locator(cellInnerSelectorForTooltip);
            }

            if (innerForTooltip.count() > 0) {
                innerForTooltip.hover();
                Locator tooltip = page.locator(".react-tooltip:visible");

                try {
                    tooltip.first().waitFor(new Locator.WaitForOptions()
                            .setTimeout(500)
                            .setState(WaitForSelectorState.VISIBLE));

                    String tooltipText = tooltip.first().innerText().trim();
                    if (!tooltipText.isEmpty()) {
                        collected.add(tooltipText);
                        if (searchTextForDebug != null &&
                                tooltipText.toLowerCase().contains(searchTextForDebug.toLowerCase())) {
                            System.out.println("Search text found in Tooltip : " + tooltipText);
                        }
                    }
                } catch (Exception e) {
                    // tooltip not shown, ignore
                } finally {
                    // move mouse away to close tooltip
                    page.mouse().move(0, 0);
                    page.waitForTimeout(150);
                }
            }
        }

        // 5. Popup / modal / side drawer via valueSelectors (reusing your logic)
        if (valueSelectors != null) {
            try {
                for (String[] step : valueSelectors) {
                    String locatorStr = step[0];
                    String action = step[1].toLowerCase();

                    try {
                        switch (action) {
                            case "cellclick": {
                                Locator target = cell.locator(locatorStr);
                                if (target.count() == 0) {
                                    System.out.println("Skipping cellclick — element not present: " + locatorStr);
                                    continue;
                                }
                                // operate on the first matching element
                                Locator first = target.first();
                                if (!first.isVisible()) {
                                    System.out.println("Skipping cellclick — element not visible: " + locatorStr);
                                    continue;
                                }
                                basePageActions.waitForElementToBeVisible(locatorStr, 5);
                                first.click();
                                break;
                            }

                            case "click": { // page-level click (e.g. modal close button or global control)
                                Locator target = page.locator(locatorStr);
                                if (target.count() == 0) {
                                    System.out.println("Skipping click — page element not present: " + locatorStr);
                                    continue;
                                }
                                Locator first = target.first();
                                if (!first.isVisible()) {
                                    System.out.println("Skipping click — page element not visible: " + locatorStr);
                                    continue;
                                }
                                basePageActions.waitForElementToBeVisible(locatorStr, 5);
                                first.click();
                                break;
                            }

                            case "hover": {
                                Locator target = cell.locator(locatorStr);
                                if (target.count() == 0) {
                                    System.out.println("Skipping hover — element not present: " + locatorStr);
                                    continue;
                                }
                                Locator first = target.first();
                                if (!first.isVisible()) {
                                    System.out.println("Skipping hover — element not visible: " + locatorStr);
                                    continue;
                                }
                                basePageActions.waitForElementToBeVisible(locatorStr, 5);
                                first.hover();
                                break;
                            }

                            case "wait": {
                                // page-level wait: wait for element if it exists, otherwise skip quickly
                                Locator target = page.locator(locatorStr);
                                if (target.count() == 0) {
                                    System.out.println("Skipping wait — element not present: " + locatorStr);
                                    continue;
                                }
                                basePageActions.waitForElementToBeVisible(locatorStr, 10);
                                break;
                            }

                            case "extract": {
                                Locator extractLocator = page.locator(locatorStr);
                                int count = extractLocator.count();

                                if (count > 0) {
                                    basePageActions.waitForElementToBeVisible(locatorStr, 5);

                                    for (int idx = 0; idx < count; idx++) {
                                        String text = extractLocator.nth(idx).innerText().trim();
                                        if (!text.isEmpty()) {
                                            collected.add(text);
                                        }
                                        System.out.println("Extracted text from element " + (idx + 1) + ": " + text);
                                    }
                                } else {
                                    System.out.println("⚠️ No elements found for selector (extract): " + locatorStr);
                                }
                                break;
                            }

                            default:
                                // unknown action — don't fail entire flow, but inform
                                System.out.println("Unknown action in valueSelectors: " + action + " for locator: " + locatorStr);
                                break;
                        }
                    } catch (Exception stepEx) {
                        // If a single step fails for this row, log it and continue with the next step.
                        // We don't want a missing modal on this row to crash all verifications.
                        System.out.println("Warning: step '" + action + "' for '" + locatorStr + "' failed: " + stepEx.getMessage());
                    }
                }
            } catch (Exception e) {
                throw new AssertionError("⚠️ Error while collecting values from cell: " + e.getMessage());
            }
        }


        return collected;
    }

    // Helper: find 0-based column index by header name (throws if not found)
    public int getColumnIndexByName(String tableSelector, String columnName) {

        Locator headers = page.locator(tableSelector + " thead tr th");
        basePageActions.waitForElementToBeVisible(page.locator("thead tr th .svg-icon.fill.col-nav").first(), 30);
        int count = headers.count();

        if (count == 0) {
            throw new IllegalStateException("No table headers found for selector: " + tableSelector);
        }

        // 1) Try exact match (case-insensitive)
        for (int i = 0; i < count; i++) {
            String txt = headers.nth(i).innerText().trim();
            if (txt.equalsIgnoreCase(columnName.trim())) {
                return i;
            }
        }

        // 2) Try contains match (case-insensitive)
        for (int i = 0; i < count; i++) {
            String txt = headers.nth(i).innerText().trim();
            if (txt.toLowerCase().contains(columnName.trim().toLowerCase())) {
                return i;
            }
        }

        // 3) Not found
        throw new IllegalArgumentException("Column header not found: '" + columnName + "' among headers: " +
                headers.allInnerTexts());
    }

}
