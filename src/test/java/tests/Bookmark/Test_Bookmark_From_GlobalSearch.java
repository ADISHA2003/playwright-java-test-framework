package tests.Bookmark;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

@Listeners({AllureTestNg.class})
public class Test_Bookmark_From_GlobalSearch extends BaseUI_Test {

    @Test(
            description = "Bookmark | As a user I can bookmark an entity from search dropdown"
            // groups = {"smoke", "regression"}
    )
    public void Test_Bookmark_Entity_From_Search_Dropdown_Smoke()  {
        String entityType = "Company";
        String entityName = "Upgrad Education Private Limited";
        String entityEndPoint = "company";
        String entityId = "666e987e499bf94d6f71833f";

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I search for " + entityName + "(" + entityType + ") in the dropdown and bookmark it");
        gs.whenISearchForEntityWithGivenType(entityName, entityType );
        bp.BookmarkEntityFromDropdown(entityId);

        Allure.step("Then I should see the entity: " + entityName + "(" + entityType + ") listed in bookmarks");
        bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName, entityEndPoint, entityId);
    }

//    @Test(
//            description = "Bookmark Regression| As a user I can bookmark an entity from search dropdown",
//            dataProvider = "entityData",
//            groups = "regression"
//    )
    // public void Test_Bookmark_Entity_From_Search_Dropdown_Regression(String entityType, String entityName, String entityEndPoint, String entityId)  {

    //     Allure.step("Given I am logged into the application");
    //     lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

    //     Allure.step("When I search for " + entityName + "(" + entityType + ") in the dropdown and bookmark it");
    //     gs.whenISearchForEntityWithGivenType(entityName, entityType );
    //     bp.BookmarkEntityFromDropdown(entityId);

    //     Allure.step("Then I should see the entity: " + entityName + "(" + entityType + ") listed in bookmarks");
    //     bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName, entityEndPoint, entityId);
    // }

    @Test(
            description = "Bookmark | As a user I can bookmark an entity from search landing page",
            groups = {"smoke", "regression"}
    )
    public void Test_Bookmark_Entity_From_Search_Landing_Page_Smoke(){
        String entityType = "Company";
        String entityName = "Swiggy Limited";
        String entityEndPoint = "company";
        String entityId = "667562c2624dea5a33892b43";

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I search and bookmark an entity from the search landing page");
        gs.whenISearchForEntityAndGoToLandingPageWithGivenType(entityName, entityType);
        bp.BookmarkEntityFromLandingPage(entityId);

        Allure.step("Then I should see the entity: " + entityType + " listed in bookmarks");
        bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName, entityEndPoint, entityId);
    }

//    @Test(
//            description = "Bookmark Regression| As a user I can bookmark an entity from search landing page",
//            dataProvider = "entityData",
//            groups = "regression"
//    )
//     public void Test_Bookmark_Entity_From_Search_Landing_Page_Regression(String entityType, String entityName, String entityEndPoint, String entityId){

//         Allure.step("Given I am logged into the application");
//         lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

//         Allure.step("When I search and bookmark an entity from the search landing page");
//         gs.whenISearchForEntityAndGoToLandingPageWithGivenType(entityName, entityType);
//         bp.BookmarkEntityFromLandingPage(entityId);

//         Allure.step("Then I should see the entity: " + entityType + " listed in bookmarks");
//         bp.thenIShouldSeeEntityInBookmarks(Domain, entityType, entityName, entityEndPoint, entityId);
//     }

//     @DataProvider(name = "entityData")
//     public Object[][] entityData() {
//         return new Object[][]{
// //                type, name, url, id
//                 {"Investor", "Peak XV Partners Advisors Pvt. Ltd.", "investor/asset-manager", "666e6ff983c167536f3af7bd"},
//                 {"Investor", "Asian Development Bank", "investor/limited-partner", "66acc1afa98ed25fde8a4019"},
//                 {"Investor", "Kerala Startup Mission", "investor/incubator", "671a15d1bb2fca32159ac254"},
//                 {"Fund", "Sequoia Capital India III LP", "fund", "666e721683c167536f3b7cfa"},
//                 {"Company", "Eternal Ltd.", "company", "6675646b624dea5a33892bc2"},
//                 {"Professional", "Ashneer Grover", "professional", "6718b15f18c6fb05f6a554b0"}
//         };
//     }
}
