package PlaywrightPageObject.DataTable;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandlingSearch {
    private final BasePageActions basePageActions;
    private final Page page;

    public HandlingSearch(Page page){
        basePageActions = new BasePageActions(page);
        this.page = page;
    }

    /**
     * Performs a search by clicking a button, filling the input, and pressing Enter.
     *
     * @param searchInputSelector  Locator for the search input field
     * @param searchButtonSelector Locator for the search button (optional, can be null)
     * @param query                The text to search
     */
    public void performSearch(String searchInputSelector, String searchButtonSelector, String query) {
        // Click the search button if provided
        if (searchButtonSelector != null && !searchButtonSelector.isEmpty()) {
            basePageActions.click(page.locator(searchButtonSelector));
        }

        // Fill the search input
        Locator inputField = page.locator(searchInputSelector);
        inputField.fill(query);

        // Press Enter to submit
        inputField.press("Enter");
    }


    /**
     * Verify that a search text appears in at least one of the specified columns in a table.
     *
     * @param tableSelector   Locator for the table (e.g., "table tbody")
     * @param searchableCols  List of column indexes where the search should be checked
     * @param searchText      Text to verify in the table
     */

    public void verifySearchResultsInTable(
            String tableSelector,
            List<Integer> searchableCols,
            String searchText,
            String cellInnerSelectorForTooltip // e.g., ".text-eclipse.mw-200" or "a.link-hov"
    ) {
        Locator tableRows = page.locator(tableSelector + " tr");
        basePageActions.waitForElementToBeVisible(tableSelector, 10);
        Set<String> matchedValues = new HashSet<>();

        int rowCount = tableRows.count();

        if (rowCount == 0) {
            throw new AssertionError("No rows found in the table for search: " + searchText);
        }

        for (int i = 0; i < rowCount; i++) {
            Locator row = tableRows.nth(i);
            boolean foundInRow = false;

            for (int colIndex : searchableCols) {
                Locator cell = row.locator("td").nth(colIndex);

                // 1. Check innerText
                String cellText = cell.innerText().trim();
                if (cellText.toLowerCase().contains(searchText.toLowerCase())) {
                    foundInRow = true;
                    matchedValues.add(cellText);
                    break;
                }

                // 2. Check aria-label (tooltip alternative)
                String ariaLabel = cell.getAttribute("aria-label");
                if (ariaLabel != null && ariaLabel.toLowerCase().contains(searchText.toLowerCase())) {
                    foundInRow = true;
                    matchedValues.add(ariaLabel);
                    break;
                }

                // 3. Hover on inner element to trigger tooltip
                Locator innerForTooltip = cell.locator(cellInnerSelectorForTooltip);
                if (innerForTooltip.count() > 0) {
                    innerForTooltip.hover();
                    Locator tooltip = page.locator(".react-tooltip"); // adjust your tooltip selector
                    String tooltipText = tooltip.innerText();
                    if (tooltip.isVisible() && tooltipText.toLowerCase().contains(searchText.toLowerCase())) {
                        foundInRow = true;
                        matchedValues.add(tooltipText);
                        break;
                    }
                }

                // 4. Optional: handle side drawer / popup if needed
            }

            if (!foundInRow) {
                throw new AssertionError(
                        "Row " + (i + 1) + " does not contain '" + searchText + "' in searchable columns or tooltip."
                );
            }
        }

        System.out.println("✅ Matching values for '" + searchText + "': " + matchedValues);
    }



    /**
     * Generic function to verify search results in a specific column of a table.
     * Supports "normal", "popup", "modal", and "sideDrawer" column types.
     *
     * @param tableSelector   CSS selector for the table (e.g., "table tbody")
     * @param columnIndex     Index of the column to verify
     * @param searchText      Text to verify
     * @param valueSelectors  2D array defining locator + action sequence
     *                        e.g. { {"locator", ".open-modal", "click"}, {"locator", ".modal-content", "extract"}, {"locator", ".modal-close", "click"} }
     */
    public void verifySearchResultInColumnWithActions(
            String tableSelector,
            int columnIndex,
            String searchText,
            String cellInnerSelectorForTooltip,
            String[][] valueSelectors
    ) {
        Locator tableRows = page.locator(tableSelector + " tr");
        basePageActions.waitForElementToBeVisible(tableSelector, 10);

        int rowCount = tableRows.count();
        if (rowCount == 0) {
            throw new AssertionError("No rows found in table for search: " + searchText);
        }

        Set<String> matchedValues = new HashSet<>();

        for (int i = 0; i < rowCount; i++) {
            boolean matchFound = false;
            Locator row = tableRows.nth(i);
            Locator cell = row.locator("td").nth(columnIndex);

            // 1. Check innerText
            String cellText = cell.innerText().trim();
            if (cellText.toLowerCase().contains(searchText.toLowerCase())) {
                matchFound = true;
                matchedValues.add(cellText);
                System.out.println("Search text found in cellText : " + cellText);
                continue;
            }

            // 2. Check aria-label (tooltip alternative)
            String ariaLabel = cell.getAttribute("aria-label");
            if (ariaLabel != null && ariaLabel.toLowerCase().contains(searchText.toLowerCase())) {
                matchFound = true;
                System.out.println("Search text found in Aria Label : " + ariaLabel);
                matchedValues.add(ariaLabel);
                continue;
            }

            // 3. Hover on inner element to trigger tooltip
            Locator innerForTooltip = cell.locator(cellInnerSelectorForTooltip);
            if (innerForTooltip.count() > 0) {
                innerForTooltip.hover();
                Locator tooltip = page.locator(".react-tooltip:visible");
                boolean tooltipAppeared = false;

                try {
                    // Try waiting briefly for tooltip to appear
                    tooltip.first().waitFor(new Locator.WaitForOptions()
                            .setTimeout(500)
                            .setState(WaitForSelectorState.VISIBLE));
                    tooltipAppeared = true;
                } catch (Exception e) {
                    // Tooltip did not appear — continue silently
                    tooltipAppeared = false;
                }

                if (tooltipAppeared) {
                    String tooltipText = tooltip.first().innerText().trim();
                    if (tooltipText.toLowerCase().contains(searchText.toLowerCase())) {
                        matchFound = true;
                        matchedValues.add(tooltipText);
                        System.out.println("Search text found in Tooltip : " + tooltipText);
                    }

                    // ✅ Move mouse away to close tooltip
                    page.mouse().move(0, 0);
                    page.waitForTimeout(150);
                } else {
                    // Tooltip didn’t appear — just move mouse away
                    page.mouse().move(0, 0);
                }

            }

            if(matchFound) continue;

            // Now check for tooltip, modal, pop up box, side drawer
            String extractedValue = "";

            try {
                // Iterate through each step in valueSelectors sequence
                for (String[] step : valueSelectors) {
                    String locatorStr = step[0];
                    String action = step[1].toLowerCase();

                    Locator target = action.equalsIgnoreCase("extract") || action.equalsIgnoreCase("wait") ? page.locator(locatorStr) : cell.locator(locatorStr);


                    switch (action) {
                        case "cellclick":
                            basePageActions.waitForElementToBeVisible(locatorStr, 5);
                            target.click();
                            break;

                        case "click":
                            basePageActions.waitForElementToBeVisible(locatorStr, 5);
                            page.locator(locatorStr).click();
                            break;

                        case "hover":
                            basePageActions.waitForElementToBeVisible(locatorStr, 5);
                            target.hover();
                            break;

                        case "wait":
                            basePageActions.waitForElementToBeVisible(locatorStr, 10);
                            break;

                        case "extract":
                            Locator extractLocator = page.locator(locatorStr);
                            int count = extractLocator.count();
                            boolean localMatchFound = false;

                            if (count > 0) {
                                basePageActions.waitForElementToBeVisible(locatorStr, 5);

                                for (int idx = 0; idx < count; idx++) {
                                    String text = extractLocator.nth(idx).innerText().trim();

                                    // ✅ Print or store extracted text for debugging
                                    System.out.println("Extracted text from element " + (idx + 1) + ": " + text);

                                    // ✅ Check if any of the extracted texts contains the search text
                                    if (text.toLowerCase().contains(searchText.toLowerCase())) {
                                        matchedValues.add(text);
                                        localMatchFound = true;
                                    }
                                }
                            } else {
                                System.out.println("⚠️ No elements found for selector: " + locatorStr);
                            }

                            // Mark match found if at least one element contained the search text
                            if (localMatchFound) {
                                matchFound = true;
                            }

                            break;


                        default:
                            throw new IllegalArgumentException("Unknown action type: " + action);
                    }
                }

                if (!extractedValue.isEmpty() &&
                        extractedValue.toLowerCase().contains(searchText.toLowerCase())) {
                    matchedValues.add(extractedValue);
                    matchFound = true;
                }

            } catch (Exception e) {
                throw new AssertionError("⚠️ Skipping row " + (i + 1) + " due to error: " + e.getMessage());
            }
            if (!matchFound) {
                throw new AssertionError("❌ No matching values found for '" + searchText +
                        "' in column (" + columnIndex + ") and row (" + i + ").");
            }
        }


        System.out.println("✅ Verified search text '" + searchText +
                "' found in " + columnIndex + " column. Matches: " + matchedValues);
    }



//    public void verifySearch(String searchText) {
//        String tableSelector = "table tbody";
//        page.waitForTimeout(2000);
//        String [][] modal = {
//                {".truncated-count.fw-500.text-color-link.cursor-hand","cellClick"},
//                {".MuiPaper-root.MuiPaper-elevation", "wait"},
//                {".text-color-label.button.secondary.xsm.fw-400.br-pill", "extract"},
//                {".svg-icon.stroke.darkv1.hover.dialog-close", "click"}
//        };
//
//        String [][] popUpBox = {
//                {".fw-500.text-color-link.cursor-hand","cellClick"},
//                {".border-box.p-8.br-8", "wait"},
//                {".border-box.p-8.br-8 ul li", "extract"},
//                {".border-box.p-8.br-8 .flex.space-between span", "click"}
//
//        };
//
//        String [][] sideDrawer = {
//                {".read-more","cellClick"},
//                {".MuiPaper-root", "wait"},
//                {".flex.bdr-b.bdr-light.mb-12.pb-12 .flex-grow .flex strong", "extract"},
//                {".svg-icon.stroke.dark.dialog-close-outside", "click"}
//
//        };
//
//
//        handlingSearch.verifySearchResultInColumnWithActions(tableSelector, 1, searchText, ".nowrap ", sideDrawer);
//    }

}
