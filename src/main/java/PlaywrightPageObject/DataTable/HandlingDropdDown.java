package PlaywrightPageObject.DataTable;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.regex.Pattern;

public class HandlingDropdDown {
    private final BasePageActions basePageActions;
    private final Page page;

    public HandlingDropdDown(Page page){
        basePageActions = new BasePageActions(page);
        this.page = page;
    }

    public void selectFromDropdown(
                                                Locator dropdownClickSelector,
                                                Locator optionsSelector,
                                                String optionToSelect) {

        // Open dropdown
        basePageActions.click(dropdownClickSelector);

        System.out.println("Options : " + optionToSelect);
        page.waitForTimeout(1500);

        // Wait until options are visible
        optionsSelector.first().waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        // Debug (use once)
        System.out.println("All options: " + optionsSelector.allTextContents());

        optionsSelector
                .filter(new Locator.FilterOptions().setHasText(optionToSelect))
                .first()
                .click();
    }
}
