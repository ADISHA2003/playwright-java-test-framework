package tests;

import PlaywrightPageObject.ScreenerFilterPage.FilterFormat;
import io.qameta.allure.Allure;


import org.testng.annotations.Test;


public class Test_SavedFilters extends BaseUI_Test {

    @Test(description = "Saved Filter | As a user i can click on saved criteria from saved filters page and navigate to the screener filter page with applied criteria on results",groups = {"smoke","regression"})
    public void verifyUserCanSaveAndReloadFilterCriteria() {
        int position = 0;
        String filterPreCondition = "Where";
        String screenerName = "Companies";
        String screenerTile = "All";
        String filterGroup = "Company Profile";
        String filterName = "Company Type";
        String filterType = "multi_select_values";
        String filterOperator = "In";
        String api = "multi_select_values";
        String apiReference = "companyType";

        // Create FilterFormat instance
        FilterFormat ff = new FilterFormat(screenerName, screenerTile, filterPreCondition, position, filterGroup, filterName, filterType, filterOperator);
        ff.addFilterInputValue("Public Company");  // static value or fetched from method

        String criteriaName = "public_company_saved";

        // ========== TEST FLOW ==========

        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to " + screenerName + " screener page");
        slp.navigateToScreenerLandingPage(Domain, screenerName);

        Allure.step("And I select tile " + screenerTile + " on landing page");
        slp.selectTileOnLandingPageOf(screenerName, screenerTile);

        Allure.step("And I apply filter on filter section");
        fs.thenIShouldBeAbleToOpenFilterUnderGroup(ff);
//        String response = mockingUtility.getResponseBodyFromScreenerAPICall(history,api, apiReference);
//        ff.addFilterInputValue(fms.getMultiSelectInputOption(ff,0,response));
        fs.thenIShouldBeAbleToApplyFilterBasedOnType(ff);
        fs.clickApplyUpdateButton();

        Allure.step("Then I should see applied filter in criteria section");
        vcs.AndICanViewCriteriaCondition(ff);

        Allure.step("When I click on 'Save Criteria' button");
        sf.clickSaveCriteriaButton();

        Allure.step("And I enter criteria name and description in Save Criteria popup");
        sf.enterCriteriaDetails(criteriaName, "Automation test criteria saving functionality");
        sf.confirmSaveCriteria();

        Allure.step("Then I verify that 'Criteria saved successfully' toast message is visible");
        sf.verifyCriteriaSavedToast("Your criteria has been saved successfully!");
        
        fs.clickClearAppliedFiltersButton();
    
        Allure.step("When I click on Saved Criteria dropdown and select the same saved criteria");       
        sf.selectSavedCriteria(criteriaName);

        Allure.step("Then I verify that applied filters match the saved criteria");
        vcs.AndICanViewCriteriaCondition(ff);

        Allure.step("Then I verify results section and data table again");
        vrs.thenIShouldSeeResultsSectionAndCard();
        vcs.AndIClickOnViewResultsButton();
        sdt.verifyTableLayout(ff.screenerName);
    }
    
    @Test(description = "Saved Filter | As a user i can click on saved criteria from dashboard page and navigate to the screener filter page with applied criteria on results",groups = {"smoke", "regression"})
    public void verifySavedFilterNavigationOnDashboard() {
       
    	int position = 0;
        String filterPreCondition = "Where";
        String screenerName = "Companies";
        String screenerTile = "All";
        String filterGroup = "Company Profile";
        String filterName = "Company Type";
        String filterType = "Multi Select";
        String filterOperator = "In";
        String api = "multi_select_values";
        String apiReference = "companyType";
    	
    	FilterFormat ff = new FilterFormat(screenerName, screenerTile, filterPreCondition, position, filterGroup, filterName, filterType, filterOperator);
    	
    	
    	Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("And I should see the Saved Filters section");
        sf.verifySavedFiltersSectionVisible();

        Allure.step("When I click on the first saved filter");
        sf.clickFirstSavedFilter();

        Allure.step("Then I should be navigated to the results page with the applied filter");
        sf.verifyResultsPageVisible();
        
        Allure.step("Then I verify results section and data table again");
        vrs.thenIShouldSeeResultsSectionAndCard();
        vcs.AndIClickOnViewResultsButton();
        sdt.verifyTableLayout(ff.screenerName);
    }
    }

