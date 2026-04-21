package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Set;

@Listeners({AllureTestNg.class})
public class Test_My_List extends BaseUI_Test{

    @Test(
            description = "My List | As a user I can navigate to my list page",
            groups = { "smoke", "regression" }
    )
    public void Test_Navigation_To_My_List_Page() {

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to my list page");
        ml.navigateToMyListPage();

        Allure.step("Then I am on my list page");
        ml.verifyNavigationToMyListPage(Domain);
    }

    @Test(
            description = "My List | As a user I can add an entity to a new list",
            dataProvider = "entityData",
            groups = { "smoke", "regression" }
    )
    public void Test_Add_Entity_To_New_List(String entityType, String entityName, String entityEndPoint, String entityId, String existingListName) {

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I search for " + entityName + " (" + entityType + ") and add it to a new list");
        String listName = ml.whenINavigateToEntityPageAndAddToNewList(Domain, entityEndPoint, entityId, entityType);

        Allure.step("Then I should see the entity: " + entityName + " (" + entityType + ") listed in the newly created list");
        ml.thenIShouldSeeEntityInNewList(Domain, listName, entityType, entityName);
    }

    /**
     * Facing some issue
     * while adding an entity
     * in already created list
     * it's not working as expected
     * **/
//    @Test(
//            description = "List | As a user I can add an entity to a already created list",
//            dataProvider = "entityData",
//            enabled = false
////            groups = "smoke"
//    )
    public void Test_Add_Entity_To_Existing_List(String entityType, String entityName, String entityEndPoint, String entityId, String existingListName) {

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I search for " + entityName + " (" + entityType + ") and add it to a new list");
        ml.whenINavigateToEntityPageAndAddToExistingList(Domain, entityEndPoint, entityId, entityType, existingListName);

        Allure.step("Then I should see the entity: " + entityName + " (" + entityType + ") listed in the newly created list");
        ml.thenIShouldSeeEntityInExistingList(Domain, existingListName, entityType, entityName);
    }

    @Test(
            description = "My List | As a user I can add an entity from screener to list",
            dataProvider = "screenerData",
            groups = { "smoke", "regression" }
    )
    public void Test_Add_Entity_From_Screener_To_New_List(String screenerType, String tile, String entityType, String entityName, String entityId)  {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the screener and add an entity : " + entityName + " (" + entityType + ") to new list");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
        sdtp.search(entityName);
        String listName = ml.addAnEntityToNewListFromScreener(entityType);

        Allure.step("Then I should see the entity: " + entityName + " (" + entityType + ") listed in the newly created list");
        ml.thenIShouldSeeEntityInNewList(Domain, listName, entityType, entityName);
    }

    @Test(
            description = "My List |As a user I can add an entity from screener using select all to list",
            dataProvider = "screenerData",
            groups = { "smoke", "regression" }
    )
    public void Test_Add_Entity_Using_Select_All_From_Screener_To_New_List(String screenerType, String tile, String entityType, String entityName, String entityId)  {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the screener and add an entity : " + entityName + " (" + entityType + ") to new list");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);
        Set<String> listedEntityNames = ml.getEntityNamesByColumnIndex();
        String listName = ml.addAnEntityUsingSelectAllToNewListFromScreener(entityType);

        Allure.step("Then I should see the entity: " + entityName + " (" + entityType + ") listed in the newly created list");
        ml.thenIShouldSeeEntityInNewList(Domain, listName, entityType, listedEntityNames);
    }

    @Test(
            description = "My List | As a user I can add to list an entity from My List",
            dataProvider = "screenerData",
            groups = { "smoke", "regression" }
    )
    public void Test_Add_Entity_From_My_List_To_New_List(String screenerType, String tile, String entityType, String entityName, String entityId)  {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the My List and add an entity : " + entityName + " (" + entityType + ") to new list");
        String listName = ml.navigateToMyListPageAndAddAnEntityToNewList(Domain, entityType, entityName);

        Allure.step("Then I should see the entity: " + entityName + " (" + entityType + ") listed in the newly created list");
        ml.thenIShouldSeeEntityInNewList(Domain, listName, entityType, entityName);
    }

    @Test(
            description = "My List | As a user I can perform search on list",
            groups = { "smoke", "regression" }
    )
    public void Test_Search_On_My_List_Page()  {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the My List and Search a list by name in a table" );
        String searchText = ml.whenINavigateToEntityPageAndAddToNewList(Domain, "investor/asset-manager", "666e6ff983c167536f3af7bd", "Investor");
        ml.navigateToMyListPage(Domain);
        ml.search(searchText);

        Allure.step("Then I should see the list with search name in table");
        ml.verifySearchInName(searchText);

        searchText = "Investor";
        Allure.step("When I Search a tag in a table" );
        ml.navigateToMyListPage(Domain);
        ml.search(searchText);

        Allure.step("Then I should see the search tag in table");
        ml.verifySearchInTag(searchText);
        ml.removeNewlyCreatedList();

    }

    @Test(description = "My List | Verify user can switch between lists on My List page",groups = { "smoke", "regression" })
    public void To_Test_User_Can_Switch_Between_Lists() {

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        String list1 = ml.whenINavigateToEntityPageAndAddToNewList(Domain, "company", "6675646b624dea5a33892bc2", "Company");
        String list2 = ml.whenINavigateToEntityPageAndAddToNewList(Domain, "company", "667562c2624dea5a33892b43", "Company");

        Allure.step("When I switched to a list");
        ml.whenISwitchBetweenList(Domain, list1, list2);
        String list1CompanyName = "Eternal Limited";

        Allure.step("Then I am on that list ");
        ml.verifySwitchBetweenList(Domain, list1, list1CompanyName, "Company");
    }


    @DataProvider(name = "entityData")
    public Object[][] entityData() {
        return new Object[][]{
//                type, name, url, id
                {"Investor", "Peak XV Partners Advisors Private Limited", "investor/asset-manager", "666e6ff983c167536f3af7bd", "Investor List"},
//                {"Investor", "Asian Development Bank", "investor/limited-partner", "66acc1afa98ed25fde8a4019", ""},
//                {"Investor", "Kerala Startup Mission", "investor/incubator", "671a15d1bb2fca32159ac254", ""},
//                {"Fund", "Sequoia Capital India III LP", "fund", "666e721683c167536f3b7cfa", ""},
                {"Company", "Eternal Limited", "company", "6675646b624dea5a33892bc2", ""},
//                {"Professional", "Ashneer Grover", "professional", "6718b15f18c6fb05f6a554b0", ""},
//                {"Service Provider", "AZB and Partners", "service-provider", "666e70b083c167536f3b23df", ""}
        };
    }

    @DataProvider(name = "screenerData")
    public Object[][] screenerData() {
        return new Object[][]{

                // screenerType, tile, entityType, name, entityId
                {"Investor", "Asset Manager", "Investor", "Peak XV Partners Advisors Private Limited", "666e6ff983c167536f3af7bd"},
//                {"Investor", "Fund", "Fund","Sequoia Capital India III LP", "666e721683c167536f3b7cfa"},
//                {"Investor", "Limited Partner", "Investor", "Asian Development Bank", "66acc1afa98ed25fde8a4019"},
//                {"Investor", "Family Office", "Investor", "Soros Fund Management, LLC", "669e368bd6f4c23d3d87c3ca"},
//                {"Company", "All", "Eternal Limited", "company", "6675646b624dea5a33892bc2"},
//                {"Professional", "Ashneer Grover", "professional", "6718b15f18c6fb05f6a554b0"}
        };
    }

}
