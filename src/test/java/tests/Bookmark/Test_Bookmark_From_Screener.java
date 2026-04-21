package tests.Bookmark;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.util.Set;

@Listeners({AllureTestNg.class})
public class Test_Bookmark_From_Screener extends BaseUI_Test {

    @Test(
            description = "Bookmark | As a user I can bookmark an entity from screener",
            groups = {"smoke", "regression"}
    )
    public void Test_Bookmark_Entity_From_Screener()  {
        String screenerType = "Companies";
        String tile = "All";
        String entityType = "Company";
        String entityName = "HDFC Bank Limited";
        String entityId = "666e712e83c167536f3b49db";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the screener and bookmark an entity");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
        sdtp.search(entityName);
        bp.BookmarkFirstEntityFromTable(entityId);

        Allure.step("Then I should see the entity listed in bookmarks");
        bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName);
    }

    @Test(
            description = "Bookmark | As a user I can bookmark using select all from screener",
            groups = {"smoke", "regression"}
    )
    public void Test_SelectAll_Bookmark_Entity_From_Screener()  {
        String screenerType = "Companies";
        String tile = "All";
        String entityType = "Company";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the screener and bookmark using Select All");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
        Set<String> bookmarkedEntityNames = bp.getEntityNamesByColumnIndex();
        bp.BookmarkAllEntityFromScreener();

        Allure.step("Then I should see the entity listed in bookmarks");
        bp.thenIVerifyBookmarkedEntities(Domain, entityType, bookmarkedEntityNames);

        Allure.step("Clean up - Unbookmark all entities");
        bp.deleteBookmarkedEntitiesUsingSelectAll();
    }

//    @Test(
//            description = "Bookmark Regression| As a user I can bookmark an entity from screener",
//            dataProvider = "screenerData",
//            groups = "regression"
//    )
    // public void Test_Bookmark_Entity_From_Screener_Regression(String screenerType, String tile, String entityType, String entityName, String entityId)  {
    //     Allure.step("Given I am logged into the application");
    //     lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

    //     Allure.step("When I navigate to the screener and bookmark an entity");
    //     slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
    //     sdtp.search(entityName);
    //     bp.BookmarkFirstEntityFromTable(entityId);

    //     Allure.step("Then I should see the entity listed in bookmarks");
    //     bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName);
    // }

//    @Test(
//            description = "Bookmark Regression| As a user I can bookmark using select all from screener",
//            dataProvider = "screenerData",
//            groups = "regression"
//    )
    // public void Test_SelectAll_Bookmark_Entity_From_Screener_Regression(String screenerType, String tile, String entityType, String entityName, String entityId)  {
    //     Allure.step("Given I am logged into the application");
    //     lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

    //     Allure.step("When I navigate to the screener and bookmark using Select All");
    //     slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
    //     Set<String> bookmarkedEntityNames = bp.getEntityNamesByColumnIndex();
    //     bp.BookmarkAllEntityFromScreener();

    //     Allure.step("Then I should see the entity listed in bookmarks");
    //     bp.thenIVerifyBookmarkedEntities(Domain, entityType, bookmarkedEntityNames);
    // }

    // @DataProvider(name = "screenerData")
    // public Object[][] screenerData() {
    //     return new Object[][]{

    //             // screenerType, tile, entityType, name, entityId
    //             {"Investor", "Asset Manager", "Investor", "Peak XV Partners Advisors Pvt. Ltd.", "666e6ff983c167536f3af7bd"},
    //             {"Investor", "Fund", "Fund","Sequoia Capital India III LP", "666e721683c167536f3b7cfa"},
    //             {"Investor", "Limited Partner", "Investor", "Asian Development Bank", "66acc1afa98ed25fde8a4019"},
    //             {"Investor", "Family Office", "Investor", "Soros Fund Management, LLC", "669e368bd6f4c23d3d87c3ca"},
    //             {"Companies", "All", "Company","Eternal Ltd.", "6675646b624dea5a33892bc2"},
    //     };
    // }
}
