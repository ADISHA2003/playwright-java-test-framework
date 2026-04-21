package PlaywrightPageObject.DataTable;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HandlingRowOperations {

    private final Page page;
    private final BasePageActions basePageActions;
    public HandlingRowOperations(Page page){
        this.page = page;
        basePageActions = new BasePageActions(page);
    }

    /**
     * Clicks a checkbox element identified by the given selector.
     * Optionally ensures it is checked before returning.
     *
     * @param checkboxSelector CSS or XPath locator for the checkbox
     * @param shouldBeChecked  true if checkbox must be checked after click, false if unchecked
     */
    public void toggleCheckbox(String checkboxSelector, boolean shouldBeChecked) {
        // Wait for the checkbox to be visible
        basePageActions.waitForElementToBeVisible(checkboxSelector, 10);

        Locator checkbox = page.locator(checkboxSelector);
        Locator inputElement = page.locator(checkboxSelector + " input[type='checkbox']");

        // Check the current state (if checkbox input exists)
        boolean isChecked = false;
        if (inputElement.count() > 0) {
            isChecked = inputElement.first().isChecked();
        }

        // Click only if state differs
        if (shouldBeChecked && !isChecked) {
            basePageActions.click(checkbox);
        } else if (!shouldBeChecked && isChecked) {
            basePageActions.click(checkbox);
        }
    }

    /**
     * Clicks on an element identified by a given selector.
     *
     * @param elementSelector The CSS or XPath selector of the element to click
     */
    public void clickElementWhenVisible(String elementSelector) {
        Locator element = page.locator(elementSelector);
        basePageActions.waitForElementToBeVisible(elementSelector, 10); // optional wait for stability
        basePageActions.click(element);
    }

    public void hoverOnTableCell(String tableSelector, int tableIndex, int rowIndex, int columnIndex) {
        // Locate the specific table
        Locator table = page.locator(tableSelector).nth(tableIndex);

        // Build locator for the specific cell
        Locator cell = table.locator("tbody tr").nth(rowIndex).locator("td").nth(columnIndex);

        // Wait for the cell to be visible before hovering
        basePageActions.waitForElementToBeVisible(tableSelector + " tbody tr", 10);

        // Perform hover action
        cell.hover();
    }



}
