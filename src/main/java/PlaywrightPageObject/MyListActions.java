package PlaywrightPageObject;

import PlaywrightPageObject.DataTable.HandlingRowData;
import PlaywrightPageObject.DataTable.HandlingRowOperations;
import PlaywrightPageObject.DataTable.HandlingSearch;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.devtools.v123.schema.model.Domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class MyListActions extends BasePageActions{
    private final Locator AddToListIcon;
    private final Locator filterButtons;
    private final Locator listItems;
    private final Locator deleteButtonOnListPage;
    private final HandlingRowData handlingRowData;
    private final HandlingRowOperations handlingRowOperations;
    private final HandlingSearch handlingSearch;

    public MyListActions(Page page) {
        super(page);

        handlingSearch = new HandlingSearch(page);
        handlingRowData = new HandlingRowData(page);
        handlingRowOperations = new HandlingRowOperations(page);
        AddToListIcon = page.locator("#saveListTooltip");
        filterButtons = page.locator(".p-12.bdr-b.bdr-light.flex.space-between .flex .mr-8.flex");
        listItems = page.locator("table tbody tr");
        deleteButtonOnListPage = page.locator("div.flex.cursor-hand.link-hov >> text=Delete");

    }

    public  int getListCount(){
        return listItems.count();
    }
    public void navigateToMyListPage() {
        // Wait for the "My List" navigation button or link to be visible
        String grid = ".header-logo.flex .svg-icon.fill.invert.lg.mr-16.cursor-hand.z-index-6";
        Locator gridIcon = page.locator(grid); // adjust locator if it’s an icon or menu item

        waitForElementToBeVisible(grid, 10);

        // Click on grid icon
        click(gridIcon);

        // Wait for main menu
        String mainMenu = ".nav-wrapper.p-8";
        waitForElementToBeVisible(mainMenu, 2);

        // click analytical tool
        Locator analyticalTools = page.locator("a div span.fs-16:text-is('Analytical Tools')");
        waitForElementToBeVisible("a:has-text('Analytical Tools')", 2);
        click(analyticalTools);

        Locator myList = page.locator("a:has-text('My Lists')");
        waitForElementToBeVisible("a:has-text('My Lists')", 2);
        click(myList);

        System.out.println("✅ Successfully navigated to My List page.");
    }

    public void verifyNavigationToMyListPage(String Domain) {
        String expectedPartialUrl = "my-list";

        waitForURL(Domain + expectedPartialUrl, 5);
        String actualUrl = page.url();

        if (!actualUrl.contains(expectedPartialUrl)) {
            throw new AssertionError("❌ Navigation to My List page failed. Current URL: " + actualUrl);
        }

        System.out.println("✅ Successfully navigated to My List page: " + actualUrl);
    }

    public void navigateToMyListPage(String Domain){
        page.navigate(Domain + "my-list");
    }

    public String whenINavigateToEntityPageAndAddToNewList(String Domain, String entityEndPoint, String entityId, String entityType){
        // Navigate to entity detail's page
        String entityUrl = Domain + entityEndPoint + "/" + entityId;
        page.navigate(entityUrl);

        //click add to list icon
        click(AddToListIcon);

        // wait for add to list pop up box to be visible
        waitForElementToBeVisible(".border-box.bdr-light.p-8.br-6", 5);

        // click create a new list button
        Locator createNewListButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create A New List"));
        click(createNewListButton);

        // wait for create a new list pop up box to be visible
        Locator newListDialog = page.locator("div.MuiDialog-paper.MuiDialog-paperScrollPaper.MuiDialog-paperWidthSm");
        waitForElementToBeVisible(newListDialog, 5);

       String listName = enterListNameTagEntityName(entityType, null);

       Locator createButton = page.locator("xpath=//button[text()='Create']");

       click(createButton);

       return listName;

    }

    public void whenINavigateToEntityPageAndAddToExistingList(String Domain, String entityEndPoint, String entityId, String entityType, String listName){
        // Navigate to entity detail's page
        String entityUrl = Domain + entityEndPoint + "/" + entityId;
        page.navigate(entityUrl);

        //click add to list icon
        click(AddToListIcon);

        // wait for add to list pop up box to be visible
        waitForElementToBeVisible(".border-box.bdr-light.p-8.br-6", 5);

        // Add entity to an existingList
        addAnEntityToExistingListFromEntityDetailPage(listName);

    }

    public void addAnEntityToExistingListFromEntityDetailPage(String listName){
        // search for existing list
        Locator searchBoxForExistingList = page.locator(".search-input-wrapper.xmd.full.mb-12 input");
        searchBoxForExistingList.fill(listName);

        // small wait to get the existing list result
        page.waitForTimeout(1000);

        //if element already added then remove it and again add it.
        Locator searchListLocatorAddRemove = page.locator(".button.transparent.blue-text.xsm.fs-10.hover-bdr-cta");

        // check if list already exist
        // if yes then remove it first and add it again

        if(searchListLocatorAddRemove.innerText().equalsIgnoreCase("Remove")) {
            click(searchListLocatorAddRemove);
        }

        click(searchListLocatorAddRemove);

    }

    public String enterListNameTagEntityName(String entityType, String entityName){
        // Generate dynamic list name with entityType + current date + time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(dtf);
        String listName = entityType + " " + currentDateTime;

        System.out.println("Generated List Name: " + listName);

        // Enter List Name
        fillInputByPlaceholder(listName, "Enter List Name");

        // Enter tag
        fillInputByPlaceholder(entityType, "Add multiple tags using(,)");

        page.locator("input[placeholder='Add multiple tags using(,)']").press("Enter");

        if(entityName!=null){
            Locator entityNameInput = page.locator(".search-input-wrapper.md.mb-2").nth(1).locator("input");
            entityNameInput.fill(entityName);
            // wait for search result to be visible
            waitForElementToBeVisible(".dropdown-card.dropdown-type-list ul.list-reset", 30);
            entityNameInput.press("Enter");
        }

        return listName;
    }

    public void thenIShouldSeeEntityInNewList(String Domain, String listName, String entityType, Object entityNames){
        navigateToMyListPage(Domain);
        verifyListVisibleOnlyForEntityType(entityType, listName);

        // Go to All
        click(filterButtons.first());

        // click on first list
        page.waitForTimeout(1000);
        waitForElementToBeVisible(listItems.first().locator("td").first(), 30);
        click(listItems.first().locator("td").first());

        // verify entity added in the list is visible
        waitForPageLoad();
        if(entityNames instanceof String) {
            String name = (String) entityNames;
            verifyEntityVisibleInList(name);
        } else if(entityNames instanceof Set<?>){
            Set<String> names = (Set<String>) entityNames;
            verifyEntityVisibleInList(names);
        }

        // Navigate to my list page
        navigateToMyListPage(Domain);
        waitForElementToBeVisible(listItems.first().locator("td").first(), 30);
        removeNewlyCreatedList();

        // verify removal
        page.reload();
        waitForElementToBeVisible(listItems.first(), 30);
        verifyListNotVisible(listName,entityType);
    }

    public void thenIShouldSeeEntityInExistingList(String Domain, String listName, String entityType, String entityName){
        navigateToMyListPage(Domain);

        // click on first list
        page.waitForTimeout(1000);
        waitForElementToBeVisible(listItems.first().locator("td").first(), 30);
        click(listItems.first().locator("td").first());

        // verify entity added in the list is visible
        waitForPageLoad();
        verifyEntityVisibleInList(entityName);

    }

    public void verifyListVisibleOnlyForEntityType(String entityType, String listName) {
        // Locate all filter buttons
        waitForElementToBeVisible(listItems.first(), 30);

        int count = filterButtons.count();
        System.out.println("Found " + count + " filter buttons");

        // Excluding All
        for (int i = 0; i < count; i++) {
            Locator button = filterButtons.nth(i);
            String buttonText = button.innerText().trim();

            System.out.println("Checking filter: " + buttonText);

            if(button.locator("button").isDisabled()) continue;

            // Click on the button to apply filter
            click(button);
            page.waitForTimeout(500); // small wait for results to load

            if (buttonText.contains("All") || buttonText.startsWith(updateEntityType(entityType))) {
                // Expect the entity to be visible in this tab
                verifyCreatedListVisible(listName, entityType);
                System.out.println("✅ Entity " + listName + " is correctly visible under " + buttonText);
            } else {
                // Expect the entity not to be visible
                verifyListNotVisible(listName, entityType);
                System.out.println("✅ Entity " + listName + " is correctly NOT visible under " + buttonText);
            }
        }
    }

    public void verifyCreatedListVisible(String listName, String entityType){

        page.waitForTimeout(2000);

        int rowCount = getListCount();
        int columnCount = listItems.locator("td").count();

        if(rowCount == 1 && columnCount == 1){
            throw new AssertionError("created " + listName + " was not found.");
        }

        String name = getListNameFromTooltip(0);
        String type = getCellText(0, 1);// Second column = entity type
        String tag = getCellText(0, 3);

        if (!name.equalsIgnoreCase(listName) || !type.equalsIgnoreCase(entityType) || !tag.equalsIgnoreCase(entityType) ) {
            throw new AssertionError("❌ created list not found → " + listName + " (" + entityType + ")");
        }
    }

    public String getListNameFromTooltip(int rowIndex) {
        // Locate the target cell
        Locator name = listItems.nth(rowIndex).locator("td").first().locator(".text-eclipse.mw-200");

        // Hover on the cell to trigger tooltip
        name.hover();

        // Define the tooltip locator
        Locator tooltip = name.locator(".react-tooltip");

        // wait for tooltip to be visible
        waitForElementToBeVisible(tooltip, 5);

        return tooltip.innerText();

    }


    public void verifyEntityVisibleInList(String entityName){
        waitForElementToBeVisible("table tbody tr td .text-eclipse a.link-hov", 30);
        String name = listItems.first().locator("td .text-eclipse a.link-hov").innerText().trim();

        // Handle variations like "Pvt. Ltd." vs "Private Limited" by checking a significant partial match
        String expectedBasis = entityName.length() > 20 ? entityName.substring(0, 20).toLowerCase() : entityName.toLowerCase();

        if(!name.toLowerCase().contains(expectedBasis)) {
            throw new AssertionError("Expected entity resembling '" + entityName + "' not found in the list. Found: '" + name + "'");
        }
        System.out.println("Verified presence of entity: " + name);
    }

    public void removeNewlyCreatedList(){
        waitForElementToBeVisible(listItems.first(), 30);
        Locator checkbox = listItems.first().locator(".custom-checkbox");
        click(checkbox);

        waitForElementToBeVisible(deleteButtonOnListPage, 5);
        click(deleteButtonOnListPage);

        // Confirm removal in popup
        handlingRowOperations.clickElementWhenVisible(".MuiPaper-root.MuiPaper-elevation .button.primary.sm.full.cta");

    }

    public void verifyListNotVisible(String listName, String entityType) {

        page.waitForTimeout(2000);

        int columnCount = listItems.locator("td").count();

        if(columnCount == 1) return;

        String name = getListNameFromTooltip(0);
        String type = getCellText(0, 1);

        if( name.equalsIgnoreCase(listName) && type.equalsIgnoreCase(entityType)){
            throw new AssertionError("created " + listName + " is visible.");
        }
    }

    public String  addAnEntityToNewListFromScreener(String entityType){
        // hover on first of Data table
        page.locator("table tbody tr td").first().hover();

        // click on add to list icon
        handlingRowOperations.clickElementWhenVisible(".svg-icon.md.stroke.hover.dtr-list");

        // click create a new list button
        Locator createNewListButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create A New List"));
        waitForElementToBeVisible(createNewListButton, 30);
        click(createNewListButton);

        // wait for create a new list pop up box to be visible
        Locator newListDialog = page.locator("div.MuiDialog-paper.MuiDialog-paperScrollPaper.MuiDialog-paperWidthSm.css-uhb5lp");
        waitForElementToBeVisible(newListDialog, 5);

        String listName = enterListNameTagEntityName(entityType, null);

        handlingRowOperations.clickElementWhenVisible("button:has-text('Create')");

        return listName;
    }

    public String  addAnEntityUsingSelectAllToNewListFromScreener(String entityType){

        // select all entities
        handlingRowOperations.toggleCheckbox(".custom-checkbox .checkmark.m-0", true);

        Locator bulkAddToList = page.locator(".svg-icon.stroke.dark.hover.md.mr-12").first();
        click(bulkAddToList);

        // wait for add to list pop up box to be visible
        waitForElementToBeVisible(".border-box.bdr-light.p-8.br-6", 5);

        // click create a new list button
        Locator createNewListButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create A New List"));
        click(createNewListButton);

        // wait for create a new list pop up box to be visible
        Locator newListDialog = page.locator("div.MuiDialog-paper.MuiDialog-paperScrollPaper.MuiDialog-paperWidthSm");
        waitForElementToBeVisible(newListDialog, 5);

        String listName = enterListNameTagEntityName(entityType, null);

        handlingRowOperations.clickElementWhenVisible("button:has-text('Create')");

        return listName;
    }

    public void verifyEntityVisibleInList(Set<String> entityName){
        waitForElementToBeVisible("table tbody tr td .text-eclipse a.link-hov", 30);
        List<Locator> rows = listItems.all();

        int rowsCount = rows.size();
        if(rowsCount!=entityName.size()) {
            throw new AssertionError("rows count (" + rowsCount + ") is not equal added entities(" + entityName.size() + ")");
        }

        for(int i = 0; i < rows.size(); i++){
            String name = listItems.nth(i).locator("td .text-eclipse a.link-hov").innerText().trim().toLowerCase();

            // Use partial matching for each entity in the set to account for naming variations
            boolean found = entityName.stream().anyMatch(expected -> {
                String expectedBasis = expected.length() > 20 ? expected.substring(0, 20).toLowerCase() : expected.toLowerCase();
                return name.contains(expectedBasis);
            });

            if(!found) {
                throw new AssertionError("Entity '" + name + "' not found in the expected set of added entities: " + entityName);
            }
            System.out.println("found name in list " + name);
        }
    }

    public String navigateToMyListPageAndAddAnEntityToNewList(String Domain, String entityType, String entityName){
        navigateToMyListPage(Domain);

        // wait for button to be visible and click on it.
        handlingRowOperations.clickElementWhenVisible("button.button.primary.cta.md");

        // wait for pop up box to select an entity
        Locator popUpBoxToSelectEntity = page.locator("div.border-box.p-0.br-6");
        waitForElementToBeVisible(popUpBoxToSelectEntity, 30);

        Locator selectEntity = popUpBoxToSelectEntity.locator("div",
                new Locator.LocatorOptions().setHasText(entityType)
        );

        click(selectEntity);

        Locator newListDialog = page.locator("div.MuiDialog-paper.MuiDialog-paperScrollPaper.MuiDialog-paperWidthSm.css-uhb5lp");
        waitForElementToBeVisible(newListDialog, 5);

        String listName = enterListNameTagEntityName(entityType, entityName);

        // click on create button
        handlingRowOperations.clickElementWhenVisible("button:has-text('Create')");
        return listName;
    }

    public Set<String> getEntityNamesByColumnIndex() {
        return handlingRowData.getCellTextsFromAllRowsOfColumn("table", ".text-eclipse a.link-hov", 0);
    }

    public void verifySearchInName(String searchText) {
        String tableSelector = "table tbody";
        page.waitForTimeout(2000);
        handlingSearch.verifySearchResultInColumnWithActions(tableSelector, 0, searchText, ".nowrap ", new String [][]{});
    }

    public void verifySearchInTag(String searchText) {
        String tableSelector = "table tbody";
        page.waitForTimeout(2000);
        handlingSearch.verifySearchResultInColumnWithActions(tableSelector, 3, searchText, "pill bg-light text-color-secondary br-4 sm mr-4", new String [][]{});
    }

    public void search(String query) {
        handlingSearch.performSearch("input[placeholder='Search']", "button:has(span.svg-icon) >> text=Search", query);
    }


    public void whenISwitchBetweenList(String Domain, String list1, String list2) {
        // Wait for list switcher to appear

        navigateToMyListPage(Domain);

        // click on first list
        page.waitForTimeout(1000);
        waitForElementToBeVisible(listItems.first().locator("td").first(), 30);
        click(listItems.first().locator("td").first());

        waitForElementToBeVisible(listItems.first().locator("td").first(), 30);

        // click on switch
        click(page.locator("span  span.svg-icon-fixed.ml-8"));

        // wait for popup box to be visible
        waitForElementToBeVisible(".MuiPaper-root", 30);
        fillInputByPlaceholder(list1,"Create a list to enable search");

        // wait for result to be load
        Locator switchingList = page.locator(".max-h-220.scroll.pr.buttons-nav-vert.mb-12 .button .mr-8 strong");
        waitForElementToBeVisible(switchingList.first(), 30);

        page.waitForTimeout(1000);
        click(switchingList);

        page.waitForTimeout(10000);

    }

    public void verifySwitchBetweenList(String Domain, String listName, String companyName, String entityType){
        Locator listNameLocator = page.locator(".mr-12 .button.secondary .flex strong");
        waitForElementToBeVisible(listNameLocator, 30);

        String fetchedName = listNameLocator.innerText();
        String fetchedEntityType = page.locator(".pill.sm.fw-500").innerText();
        if(
                !listName.equalsIgnoreCase(fetchedName)
                || !entityType.equalsIgnoreCase(fetchedEntityType)
        )
            throw new AssertionError("switcher not working properly");
        else
            System.out.println("Switcher is working properly " + fetchedName);

        verifyEntityVisibleInList(companyName);
        navigateToMyListPage(Domain);

        removeNewlyCreatedList();
        page.reload();
        removeNewlyCreatedList();


    }


}
