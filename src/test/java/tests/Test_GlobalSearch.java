package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Constants.FilePath;
import Utilities.JsonReaderService;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class})
public class Test_GlobalSearch extends BaseUI_Test{

	
	

	@Test(enabled=true, description = "Global Search | Verify Global Search default display on click", groups = {"smoke","sanity","regression","GlobalSearch"})



    public void VCI_T1460__verify_Global_Search_default_display_on_click(){
		Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" open global search and verify default display");
        gs.verifyGlobalSearchDisplay();
    }


    @Test(enabled=true, description = "Global Search | Verify Global Search Placeholder", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void VCI_T1459__Verify_Global_Search_Placeholder(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify the Global Search placeholder text");
        gs.verifyGlobalSearchPlaceholder("Search with name, website, CIN, DIN or SC code");
    }


    @Test(enabled=true, description = "Global Search | Verify Smart Suggestions availability", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_smart_suggestions_availability(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" check Smart Suggestions availability");
        gs.verifySmartSuggestionAvailability("Smart Suggestions");
    }


    @Test(enabled=true, description = "Global Search | Verify NLP Banner", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_NLP_banner(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify the NLP banner is displayed");
        gs.verifyNlpBanner();
    }


    @Test(enabled=true, description = "Global Search | Verify Recently Searched Text View", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void VCI_T1463__Verify_recently_searched_textview(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify Recently Searched heading text");
        gs.verifyRecentlySearchedTitle("Recent Searches");
    }


    @Test(enabled=true, description = "Global Search | Verify Clear All button for Recent Searches", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_clearAll_button_recentSearch(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify Clear all button for recent searches");
        gs.verifyClearAllButtonRecentSearch("Clear all");
    }


    @Test(enabled=true, description = "Global Search | Verify Recently Visited Text View", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void VCI_T1463__Verify_recently_visited_textview(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify Recently Visited heading text");
        gs.verifyRecentlyVisitedTitle("Recently Visited");
    }


    @Test(enabled=true, description = "Global Search | Verify Clear All button for Recent Visited", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_clearAll_button_recentVisited(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify Clear all button for recent visited");
        gs.verifyRecentlyVisitedTitle("Clear all");
    }


    @Test(enabled=true, description = "Global Search | Verify Keyword Saved in Recent Search and Recent Visited", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_keyword_saved_in_recentSearch_and_recentVisited(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" search and verify keyword saved in recent search and visited");
        gs.verifyKeywordSavedToRecent("Swiggy Limited");
    }


    @Test(enabled=true, description = "Global Search | Verify Landing Page Navigation and Heading", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_landingPage_navigation_and_Heading(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" navigate to landing page and verify heading");
        gs.verifyLandingPageHeading("Swiggy Limited");
    }


    @Test(enabled=true, description = "Global Search | Verify View All button in dropdown", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_viewAll_button_in_dropdown(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify View All button in dropdown");
        gs.verifyViewAllButtonDropdown("Swiggy Limited");
    }


    @Test(enabled=true, description = "Global Search | Verify Minimum keyword for searching is more than 3 characters", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void VCI_T1465__Verify_Minimum_keyword_for_searching_is_more_than_3_characters_(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify minimum keyword length > 3 characters behavior");
        gs.verifyMinimumKeywordMoreThan3();
    }


    @Test(enabled=true, description = "Global Search | Verify NLP Search Placeholder", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void verify_NLP_Search_Placeholder(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        String placeholder = "Type your query like " + '"' + "Edtech Startup with Series A Evaluation" + '"' + "...";
        Allure.step(" verify NLP Search placeholder text: " + placeholder);
        gs.verifyNlpSearchPlaceholder(placeholder);
    }


    @Test(enabled=true, description = "Global Search | Verify See All button visibility when results count is more than 4", groups = {"smoke","GlobalSearch","regression"})



    public void VCI_T1969__GlobalSearch__To_Test_see_all_is_visible_when_result_count_is_more_than_4(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify See all button is visible when results > 4");
        gs.verifySeeAllButton();
    }
    

//    @Test(enabled=false , groups = {"GlobalSearch"})
    public void VCI_T2014__GlobalSearch___Verify_count_of__ALL__result_category_(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify count of All tab");
        gs.verifyCountOfAllTab("Swiggy",41);
    }


    @Test(enabled=true, description = "Global Search | Verify Result Category State", groups = {"smoke","GlobalSearch","regression"} , dataProvider = "getCategoryState")

 

    public void VCI_T2015__GlobalSearch___Verify_Reult_category_state_is_as_per_requirement_(String[] data){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify result category state as per requirement");
        gs.verifySearchCategoryState("tata",data[0],data[1]);
    }


//    @Test(enabled=false, groups = {"GlobalSearch"},dataProvider = "getCategoryCountVise")

    

    public void VCI_T2016__Global_Search___Verify_count_of_each_category_is_correct_(String[] data){
        RESOURCE_FOLDER = FilePath.WEB_RESOURCES;
        TEST_FOLDER = "GlobalSearch";
        TEST_FILE = "GlobalSearchTestData.json";
        TEST_NAME = "test_count";

        String testData = JsonReaderService.readTest_JsonFile(RESOURCE_FOLDER,TEST_FOLDER,TEST_FILE,TEST_NAME);
        String companyName = JsonReaderService.getValueFromJson(testData,"name");
        String count = data[1];
        String category = data[0];

       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify count of result categories for company: " + companyName);
        gs.verifyCountOfResultCategories(companyName,count,category);
    }


    @Test(enabled=true, description = "Global Search | Verify No Result Display", groups = {"smoke","GlobalSearch","regression"})

  

    public void VCI_T2017__GlobalSearch___Verify_no_result_display(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify No Result display for gibberish search");
        gs.verifyNoResultDisplay("Sfvnfdnvjfdn");
    }


    @Test(enabled=true, description = "Global Search | Verify NLP Icon Visibility", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_NLP_icon(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify NLP icon visibility");
        gs.verifyNlpIcon();
    }


    @Test(enabled=true, description = "Global Search | Verify NLP Recent Search Heading", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_NLP_recentSearch_heading(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify NLP recent search heading");
        gs.verifyNlpRecentSearches("Recent Searched Queries");
    }


    @Test(enabled=true, description = "Global Search | Verify Breadcrumb Visibility", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_breadcrumb(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify breadcrumb on results page");
        gs.verifyBreadcrumb("Swiggy" ,"Home\n" +
                " / \n" +
                "Search Results");
    }


    @Test(enabled=true, description = "Global Search | Verify NLP Clear All Button", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_NLP_clearAll_button(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify NLP Clear all button");
        gs.verifyNlpClearAllButton("Clear all");
    }


//    @Test(enabled=false , groups = {"GlobalSearch"} , dataProvider = "getCategorySectionCount")
    public void VCI_T2018__GlobalSearch___Verify_datacard_and_category_section_heading_count(String[] data){
        RESOURCE_FOLDER = FilePath.WEB_RESOURCES;
        TEST_FOLDER = "GlobalSearch";
        TEST_FILE = "GlobalSearchTestData.json";
        TEST_NAME = "test_count";

        String testData = JsonReaderService.readTest_JsonFile(RESOURCE_FOLDER,TEST_FOLDER,TEST_FILE,TEST_NAME);
        String companyName = JsonReaderService.getValueFromJson(testData,"name");
        String count = data[1];
        String category = data[0];

        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify category section count and datacard count for: " + companyName);
        gs.verifyCategorySectionCount(companyName,category,count);
    }


    @Test(enabled=true, description = "Global Search | Verify Dropdown DataCard", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_dropdown_dataCard(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify dropdown datacard for Upgrad");
        gs.verifyDropdownDataCard("Upgrad");
    }


    @Test(enabled=true, description = "Global Search | Verify Landing Page DataCard", groups = {"smoke","GlobalSearch","regression"})

  

    public void verify_landingPage_dataCard(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step(" verify landing page datacard for Upgrad");
        gs.verifyLandingPageDataCard("Upgrad");
    }



    @Test(enabled=true, description = "Global Search | Verify Result Categories Section Appears After Writing More Than 3 Letters", groups = {"smoke","sanity","regression","GlobalSearch"})

  

    public void VCI_T1467__Verify_result_categories_section_shoul_appear_after_writing_more_than_3_letters_(){
       Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");
        Allure.step("When verifying categories appear after writing more than 3 letters");
        gs.verifyMinimumKeywordMoreThan3();
    }


   /* ---------------------
     Data Providers (kept same logic but returns Object[][] as before)
       --------------------- */

    @DataProvider(name="getCategoryCountVise")
    public Object[][] getCategoryCountVise(){
        RESOURCE_FOLDER = FilePath.WEB_RESOURCES;
        TEST_FOLDER = "GlobalSearch";
        TEST_FILE = "GlobalSearchTestData.json";
        TEST_NAME = "test_count";
        Object[][] obj = new Object[8][8];
        String testData = JsonReaderService.readTest_JsonFile(RESOURCE_FOLDER,TEST_FOLDER,TEST_FILE,TEST_NAME);
        String count = JsonReaderService.getValueFromJson(testData,"count");
        Map<String, Object> countMap = null;
        try {
            countMap = new ObjectMapper().readValue(count, HashMap.class);
        } catch (JsonProcessingException e) {
            System.out.println("exception");
            throw new RuntimeException(e);
        }
        int i=0;
        for(String key:countMap.keySet()){
            obj[i][0]=key;
            obj[i][1]=countMap.get(key);
            i++;
        }
        return obj;
    }



    @DataProvider(name="getCategoryState")
    public Object[][] getCategoryState(){
        RESOURCE_FOLDER = FilePath.WEB_RESOURCES;
        TEST_FOLDER = "GlobalSearch";
        TEST_FILE = "GlobalSearchTestData.json";
        TEST_NAME = "test_count2";
        Object[][] obj = new Object[8][8];
        String testData = JsonReaderService.readTest_JsonFile(RESOURCE_FOLDER,TEST_FOLDER,TEST_FILE,TEST_NAME);
        String categoriesState = JsonReaderService.getValueFromJson(testData,"categoriesState");
        Map<String, Object> stateMap = null;
        try {
            stateMap = new ObjectMapper().readValue(categoriesState, HashMap.class);
        } catch (JsonProcessingException e) {
            System.out.println("exception");
            throw new RuntimeException(e);
        }
        int i=0;
        for(String key:stateMap.keySet()){
            obj[i][0]=key;
            obj[i][1]=stateMap.get(key);
            i++;
        }
        return obj;
    }


    @DataProvider(name="getCategorySectionCount")
    public Object[][] getCategorySectionCount(){
        RESOURCE_FOLDER = FilePath.WEB_RESOURCES;
        TEST_FOLDER = "GlobalSearch";
        TEST_FILE = "GlobalSearchTestData.json";
        TEST_NAME = "test_count";
        Object[][] obj = new Object[2][2];
        String testData = JsonReaderService.readTest_JsonFile(RESOURCE_FOLDER,TEST_FOLDER,TEST_FILE,TEST_NAME);
        String categoriesState = JsonReaderService.getValueFromJson(testData,"categorySectionCount");
        Map<String, Object> stateMap = null;
        try {
            stateMap = new ObjectMapper().readValue(categoriesState, HashMap.class);
        } catch (JsonProcessingException e) {
            System.out.println("exception");
            throw new RuntimeException(e);
        }
        int i=0;
        for(String key:stateMap.keySet()){
            obj[i][0]=key;
            obj[i][1]=stateMap.get(key);
            i++;
        }
        return obj;
    }

}

