package PlaywrightPageObject.DataTable;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashSet;
import java.util.Set;

public class HandlingRowData {
    private final Page page;
    private final BasePageActions basePageActions;

    public HandlingRowData(Page page){
        this.page = page;
        basePageActions = new BasePageActions(page);
    }


    public Set<String> getCellTextsFromAllRowsOfColumn(String table, String columnSelector, int columnIndex) {
        /**
         * Always make sure
         * 1st row 1st column exits
         * */


        basePageActions.waitForElementToBeVisible(page.locator(table).locator("tbody tr").first().locator("td").nth(columnIndex).locator(columnSelector), 30);

        // Get all rows
        Locator rows = page.locator(table).locator("tbody tr");
        int rowCount = rows.count();

        Set<String> entityNames = new HashSet<>();

        for (int i = 0; i < rowCount; i++) {
            Locator cell = rows.nth(i).locator("td").nth(columnIndex).locator(columnSelector);
            if (cell.isVisible()) {
                String text = cell.innerText().trim();
                if (!text.isEmpty()) {
                    entityNames.add(text);
                }
            }
        }

        return entityNames;
    }
}
