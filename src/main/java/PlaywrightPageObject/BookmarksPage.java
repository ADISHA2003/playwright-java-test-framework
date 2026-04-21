package PlaywrightPageObject;

import PlaywrightPageObject.DataTable.HandlingRowData;
import PlaywrightPageObject.DataTable.HandlingRowOperations;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Set;
import java.util.List;
import java.util.HashSet;

public class BookmarksPage extends BasePageActions{
    private final Page page;
    private final Locator bookmarksHeading;
    private final Locator viewAllBookmarksButton;
    private final Locator companyTabButton;
    private final Locator investorsTabButton;
    private final Locator fundsTabButton;
    private final Locator dealsTabButton;
    private final Locator professionalsTabButton;
    private final Locator bookmarkItems;
    private final Locator bookmarkCompanyName;
    private final Locator bookmarkYear;
    private final Locator bookmarkType;
    private final Locator bookmarkStatus;
    private final Locator bookmarkLocation;
    private final HandlingRowData handlingRowData;
    private final HandlingRowOperations handlingRowOperations;

    public BookmarksPage(Page page) {
        super(page);
        this.page = page;
        handlingRowData = new HandlingRowData(page);
        handlingRowOperations = new HandlingRowOperations(page);
        this.bookmarksHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Latest Bookmarks"));
        this.viewAllBookmarksButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View All Bookmarks"));
        this.companyTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Company"));
        this.investorsTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Investors"));
        this.fundsTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Funds"));
        this.dealsTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Deals"));
        this.professionalsTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Professionals"));
        this.bookmarkItems = page.locator("table tbody tr"); // Adjust selector as needed
        this.bookmarkCompanyName = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Gatil Properties Pvt Ltd."));
        this.bookmarkYear = page.getByText("2002"); // Example year
        this.bookmarkType = page.getByText("Real Estate and Construction Developers"); // Example type
        this.bookmarkStatus = page.getByText("Funded (Others)"); // Example status
        this.bookmarkLocation = page.getByText("Ahmedabad, IN"); // Example location
    }

    public void gotoBookmarks(String url) {
        page.navigate(url); // Or the direct bookmarks URL if available
    }

    public void clickViewAllBookmarks() {
        viewAllBookmarksButton.click();
    }

    public void selectCompanyTab() {
        companyTabButton.click();
    }

    public void selectInvestorsTab() {
        investorsTabButton.click();
    }

    public void selectFundsTab() {
        fundsTabButton.click();
    }

    public void selectDealsTab() {
        dealsTabButton.click();
    }

    public void selectProfessionalsTab() {
        professionalsTabButton.click();
    }

    public int getBookmarkCount() {
        return bookmarkItems.count();
    }

    public String getFirstBookmarkCompanyName() {
        return bookmarkCompanyName.innerText();
    }

    public void whenIclickOnBookmark(String entityId){
        String bookmarkSelector = "#bookmarkIconUniversal" + entityId;
        Locator bookmark = page.locator(bookmarkSelector);

        bookmark.waitFor();

        click(bookmark);
    }

    public void whenIclickOnBookmarkInNewPage(Page newPage){

        Locator bookmarkIcon = newPage.locator("span[id^='bookmarkIconUniversal']");


        bookmarkIcon.waitFor();

        click(bookmarkIcon);
    }

    public void thenIVerifyElementHasClass(String selector, String expectedClass) {

        page.waitForSelector(
                selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(5000) // 5 seconds
        );

        if(hasClassValue(selector, expectedClass)) return ;

        throw new AssertionError("Expected element " + selector + " to have class '"
                + expectedClass);
    }

    public void thenIVerifyElementDoesNotHaveClass(String selector, String unexpectedClass) {
        if(doesntHaveClassValue(selector, unexpectedClass) )
            return ;

        throw new AssertionError("Expected element " + selector + " not to have class '"
                + unexpectedClass );
    }

    public void thenIVerifyBookmarkItemVisible(String entityName, String entityType, int tab){

        page.waitForTimeout(2000);

        Locator rows = page.locator("tbody tr");
        int rowCount = getBookmarkCount();
        int columnCount = rows.locator("td").count();

        if(rowCount == 1 && columnCount == 1){
            throw new AssertionError("Bookmarked " + entityName + " was not found in " + tab + "tab");
        }


        String name = getCellText(0, 0); // First column = entity name
        String type = getCellText(0, 1); // Second column = entity type

        if (!name.equalsIgnoreCase(entityName) || !type.equalsIgnoreCase(entityType)) {
            throw new AssertionError("❌ Bookmarked entity not found → " + entityName + " (" + entityType + ")");
        }

    }

    public void thenIShouldNotSeeEntityBookmarked(String entityName, String entityType) {

        page.waitForTimeout(2000);
        int maxRetries = 3;          // number of retry attempts
        int waitBetweenRetries = 1000; // 1 second

        for (int i = 0; i < maxRetries; i++) {

            Locator firstRow = page.locator("tbody tr").first();
            int columnCount = firstRow.locator("td").count();

            if (columnCount == 1) {
                return; // nothing bookmarked
            }

            String name = getCellText(0, 0);
            String type = getCellText(0, 1);

            if (!(name.equalsIgnoreCase(entityName) && type.equalsIgnoreCase(entityType))) {
                return; // success - entity not found
            }

            // If still found, wait and retry
            page.reload();
            page.waitForTimeout(waitBetweenRetries);
        }

        // If it still exists after retries, fail the test
        throw new AssertionError("Bookmark " + entityName + " was not removed.");
    }


    public void ensureEntityIsNotBookmarked(String bookmarkSelector) {

        if (hasClassValue(bookmarkSelector, "fill")) {
            // Already bookmarked, so click again to unbookmark
            System.out.println("⚠️ Entity is already bookmarked, unbookmarking first.");
            page.click(bookmarkSelector);
            page.waitForSelector(bookmarkSelector + ":not(.fill)");
        }
    }

    public void BookmarkEntityFromDropdown(String entityId) {
        // Locate all entity type buttons inside the main div

        page.waitForTimeout(1000);

        // Now try to bookmark the entity
        Locator firstRow = page.locator("div[data-testid='dropdown-search-card-1']");
        firstRow.hover();

        page.waitForTimeout(500);
        String bookmarkSelector = "span[id='bookmarkIconUniversal" + entityId + "']";
        Locator bookmarkIcon = firstRow.locator(bookmarkSelector);

        if(!bookmarkIcon.isVisible()) return;
        if (hasClassValue(bookmarkSelector, "fill")) {
            System.out.println("Entity is already bookmarked. Unbookmarking first...");

            // Unbookmark
            bookmarkIcon.click();

            // Wait until the "fill" class is removed (unbookmarked state)
            page.waitForCondition(() -> {
                String updatedClass = bookmarkIcon.getAttribute("class");
                return updatedClass == null || !updatedClass.contains("fill");
            });

            // Bookmark again
            bookmarkIcon.click();

            // Optional: wait until it's filled again
            page.waitForCondition(() -> {
                String updatedClass = bookmarkIcon.getAttribute("class");
                return updatedClass != null && updatedClass.contains("fill");
            });
            System.out.println("Entity re-bookmarked successfully.");
        }
        else {
            bookmarkIcon.click();
            page.waitForCondition(() -> {
                String updatedClass = bookmarkIcon.getAttribute("class");
                return updatedClass != null && updatedClass.contains("fill");
            });
            System.out.println("Entity bookmarked successfully.");
        }


        System.out.println("Entity bookmarked successfully.");
    }

    public void thenIVerifyBookmarkedEntities(String Domain,String entityType, Set<String> bookmarkEntityNames) {
        if (bookmarkEntityNames.isEmpty()) {
            throw new AssertionError("No entities were bookmarked, nothing to verify!");
        }

        // Navigate to the bookmarks page
        page.navigate(Domain + "bookmarks");

        verifyBookmarkVisibleOnlyForEntityType(entityType, bookmarkEntityNames);

        removeBookmarksUsingSelectAllAndVerifyRemoval(entityType, bookmarkEntityNames);
    }

    public void deleteBookmarkedEntitiesUsingSelectAll() {
        // Step 1: Click on the select-all checkbox using the specific class path provided
        page.locator("span.checkbox-col label.custom-checkbox span.checkmark.m-0").click();

        // Step 2: Click on the Remove button/div containing the 'Remove' text
        page.locator("div.flex.cursor-hand:has-text('Remove')").click();

        // Step 3: Click on the 'Yes' confirmation button in the resulting dialog
        page.locator("button.button.primary.sm.full.cta:has-text('Yes')").click();
    }

    public void whenINavigateToEntityPageAndBookmark(String Domain, String entityEndPoint, String entityId) {
        String entityUrl = Domain + entityEndPoint + "/" + entityId;

        page.navigate(entityUrl);



        String Bookmark = "#bookmarkIconUniversal" + entityId;
        waitForElementToBeVisible(Bookmark, 30);
        ensureEntityIsNotBookmarked(Bookmark);

        handlingRowOperations.clickElementWhenVisible(Bookmark);
        thenIVerifyElementHasClass(Bookmark, "fill");

    }

    public void thenIShouldSeeEntityInBookmarks(String Domain, String entityType, String entityName, String entityEndPoint, String entityId) {
        String bookMarkUrl = Domain + "bookmarks";
        String entityUrl = Domain + entityEndPoint + "/" + entityId;

        page.navigate(bookMarkUrl);

        thenIVerifyBookmarkItemVisible(entityName, entityType, 0);

        verifyBookmarkVisibleOnlyForEntityType(entityType, entityName);

        page.navigate(entityUrl);

        handlingRowOperations.clickElementWhenVisible("#bookmarkIconUniversal" + entityId);

        page.navigate(bookMarkUrl);
        thenIShouldNotSeeEntityBookmarked(entityName, entityType);
    }

    public void thenIShouldSeeEntityInBookmarks(String Domain, String entityType, String entityName) {
        String bookMarkUrl = Domain + "bookmarks";

        page.navigate(bookMarkUrl);

        thenIVerifyBookmarkItemVisible(entityName, entityType, 0);

        verifyBookmarkVisibleOnlyForEntityType(entityType, entityName);

        Locator buttonAll = page.locator("div.flex.mt-20.ml-20 button").first();
        click(buttonAll);

        Locator bookmarkEntity = page.locator("table tbody tr").first().locator("td").first().locator(".flex span a");

        Page newPage = page.waitForPopup(() -> {
            click(bookmarkEntity);
        });

        waitForPageLoad();

        whenIclickOnBookmarkInNewPage(newPage);

        page.navigate(bookMarkUrl);
        thenIShouldNotSeeEntityBookmarked(entityName, entityType);
    }

    public void verifyBookmarkVisibleOnlyForEntityType(String entityType, String entityName) {
        // Locate all filter buttons
        Locator buttons = page.locator("div.flex.mt-20.ml-20 button");

        int count = buttons.count();
        System.out.println("Found " + count + " filter buttons");

        // Excluding All
        for (int i = 1; i < count; i++) {
            Locator button = buttons.nth(i);
            String buttonText = button.innerText().trim();

            System.out.println("Checking filter: " + buttonText);

            // Click on the button to apply filter
            button.click();
            waitForElementToBeVisible(page.locator("table tbody tr").first(),30);


            if (buttonText.startsWith(updateEntityType(entityType))) {
                // Expect the entity to be visible in this tab
                thenIVerifyBookmarkItemVisible(entityName, entityType, i);
                System.out.println("✅ Entity " + entityName + " is correctly visible under " + buttonText);
            } else {
                // Expect the entity not to be visible
                thenIShouldNotSeeEntityBookmarked(entityName, entityType);
                System.out.println("✅ Entity " + entityName + " is correctly NOT visible under " + buttonText);
            }
        }
    }


    public void BookmarkEntityFromLandingPage(String entityId){

        page.waitForTimeout(5000);

        // Locate the bookmark icon inside this row and click it
        String bookmark = ".pt-8.pl-12.pr-12.pb-12 .company-list span[id^='bookmarkIconUniversal" + entityId + "']";
        ensureEntityIsNotBookmarked(bookmark);
        handlingRowOperations.clickElementWhenVisible(bookmark);

    }

    public void BookmarkFirstEntityFromTable(String entityId){
        page.locator("table tbody tr td").first().hover();
        String bookmark = "span[id^='bookmarkIconUniversal" + entityId + "']";
        ensureEntityIsNotBookmarked(bookmark);

        click(page.locator(bookmark));

        page.waitForTimeout(1000);

    }

    public void BookmarkAllEntityFromScreener(){
        handlingRowOperations.toggleCheckbox(".custom-checkbox .checkmark.m-0", true);;
        handlingRowOperations.clickElementWhenVisible("#bookmark-bulk-items-button .svg-icon.stroke.dark.hover.md.mr-12");
    }

    public void verifyBookmarkVisibleOnlyForEntityType(String entityType, Set<String> bookmarkEntityNames) {
        String buttonsSelector = "div.flex.mt-20.ml-20 button";
        Locator buttonsLocator = page.locator(buttonsSelector);

        waitForElementToBeVisible(buttonsSelector, 5);

        int buttonCount = buttonsLocator.count();
        System.out.println("Found " + buttonCount + " filter buttons");

        for (int i = 0; i < buttonCount; i++) {
            Locator button = buttonsLocator.nth(i);
            String buttonText = button.innerText().trim();

            System.out.println("Checking filter: " + buttonText);
            button.click();

            // Wait for table rows to be visible
            waitForElementToBeVisible("table tbody tr td", 30);
            page.waitForTimeout(1000);
            List<Locator> rows = page.locator("tbody tr").all();

            boolean expectVisible = buttonText.startsWith("All") || buttonText.startsWith(updateEntityType(entityType));

            for (Locator row : rows) {
                if (expectVisible) {
                    String name = getEntityNameFromRow(row);
                    String type = row.locator("td").nth(1).innerText().trim();
                    if (!entityType.equalsIgnoreCase(type) || !bookmarkEntityNames.contains(name)) {
                        throw new AssertionError("❌ Bookmarked entity not found → " + name + " (" + type + ")");
                    }

                    System.out.println(name + " correctly found in " + buttonText);
                } else {
                    if(buttonText.contains("0"))    continue;

                    String name = getEntityNameFromRow(row);
                    String type = row.locator("td").nth(1).innerText().trim();
                    if (entityType.equalsIgnoreCase(type) && bookmarkEntityNames.contains(name)) {
                        throw new AssertionError("❌ Bookmarked entity " + name + " found in another entityType → (" + buttonText + ")");
                    }

                    System.out.println(name + " correctly not found in " + buttonText);
                }
            }
        }
    }

    /**
     * Helper method to get the entity name from a table row
     */
    private String getEntityNameFromRow(Locator row) {
        Locator nameCell = row.locator("td").first();
        Locator spanWithAria = nameCell.locator("span[aria-label]");

        if (spanWithAria.count() > 0) {
            return spanWithAria.first().getAttribute("aria-label").trim();
        } else {
            return nameCell.innerText().trim();
        }
    }

    public void removeBookmarksUsingSelectAllAndVerifyRemoval(String entityType, Set<String>bookmarkEntityNames) {
        // Step 1: Click on "All Bookmarks"
        Locator allBookmarksButton = page.locator("div.flex.mt-20.ml-20 button").first();
        click(allBookmarksButton);

        // Step 2: Select all checkboxes
        waitForElementToBeVisible("table tbody tr td", 30);
        page.waitForTimeout(500);
        handlingRowOperations.toggleCheckbox(".custom-checkbox .checkmark.m-0", true);

        // Step 3: Click remove icon
        handlingRowOperations.clickElementWhenVisible(".flex.cursor-hand");

        // Step 4: Confirm removal in popup
        handlingRowOperations.clickElementWhenVisible(".MuiPaper-root.MuiPaper-elevation .button.primary.sm.full.cta");

        page.waitForTimeout(500);
        page.reload();

        if(allBookmarksButton.innerText().contains("(0)")) return;
        // Step 5: Verify that bookmarks are gone
        verifyEntitiesNotInBookmark(entityType, bookmarkEntityNames);
    }

    private void verifyEntitiesNotInBookmark(String entityType, Set<String> removedCompanies) {

        // Re-fetch rows after removal
        waitForElementToBeVisible("table tbody tr td", 30);
        List<Locator> rows = page.locator("tbody tr").all();

        Set<String> stillVisible = new HashSet<>();

        for (Locator row : rows) {
            String name = getEntityNameFromRow(row);
            String type = row.locator("td").nth(1).innerText().trim();

            if (removedCompanies.contains(name) && type.equalsIgnoreCase(entityType)) {
                stillVisible.add(name);
            }

        }

        if (!stillVisible.isEmpty()) {
            throw new AssertionError("❌ Some removed companies are still visible in bookmarks: " + stillVisible);
        }

        System.out.println("✅ Verified: All removed companies are no longer visible in bookmarks.");
    }

    public Set<String> getEntityNamesByColumnIndex() {
        return handlingRowData.getCellTextsFromAllRowsOfColumn("table", ".text-eclipse a.link-hov", 0);
    }
    // Add more actions as needed for your test cases
}
