package tests.ScreenerGenericTests;

import PlaywrightPageObject.ScreenerFilterPage.FilterFormat;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import tests.BaseUI_Test;
import utils.ScreenerJsonDataProvider;

public class Test_Screener_Top_Filters extends BaseUI_Test {

    @Test(description = "As a user i view the results as non zero of every multi_select_values filter on filter and data table page along with values in linked tabs",dataProviderClass = utils.ScreenerJsonDataProvider.class,dataProvider = "topFilters",groups = {"smoke","regression"})
    public void verifyUserApplyTopFilterAndNavigatesToDataTableForCompanyScreener(String screenerName,String groupName,String subgroupName,String category,String subCategory,String filterName,String currentFilterType,Boolean isDefault,String api,String filterAPIReference) {
        int position=0;
        String filterPreCondition="Where";
        String screenerTile = "All";
        FilterFormat ff = new FilterFormat(screenerName,screenerTile,filterPreCondition,position,groupName,filterName,currentFilterType,"In");

        //get range of input values
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+ff.screenerName+" screener page");
        slp.navigateToScreenerLandingPage(Domain, ff.screenerName);

        Allure.step("And I select tile " + screenerTile + " on landing page");
        slp.selectTileOnLandingPageOf(ff.screenerName,ff.tile);


        //apply filter on filter page with random value from input range values
        Allure.step("And I open filter group "+groupName+" and apply filter "+filterName+"on filter section");
        fs.thenIShouldBeAbleToOpenFilterUnderGroup(ff);

        // Retry logic to handle slow network capture on Jenkins
        String response = "";
        for (int i = 0; i < 10; i++) {
            response = mockingUtility.getResponseBodyFromScreenerAPICall(history, api, filterAPIReference);
            if (response != null && !response.isEmpty()) {
                break;
            }
            page.waitForTimeout(1000); // Wait 1s before retrying
        }
        if (response == null || response.isEmpty()) {
            throw new RuntimeException("Failed to capture API response for: " + filterAPIReference + " after 10 seconds.");
        }

        ff.addFilterInputValue(fms.getTopSelectInputOption(ff,1,response));
        fs.thenIShouldBeAbleToApplyFilterBasedOnType(ff);
        fs.clickApplyUpdateButton();

        Allure.step("Then I should see the applied filter in criteria section");
        vcs.AndICanViewButtonsOnCriteriaSection();
        vcs.AndICanViewCriteriaCondition(ff);

        Allure.step("Then I should see results section and card after applying any filter");
        vrs.thenIShouldSeeResultsSectionAndCard();

        Allure.step("And I should be able to click on view results button");
        vcs.AndIClickOnViewResultsButton();

        Allure.step("Then I should be able to view data table screen layout");
        sdt.verifyTableLayout(ff.screenerName);

        Allure.step("Then I should be able to view data table dropdown filters");
        sdtp.thenIShouldSeeDropDownFiltersOnDataTableScreen(ff);

        Allure.step("And I Should see applied filter on data table screen");
        sdtp.thenIShouldSeeAppliedFilterOnDataTableScreen(ff);

        Allure.step("And I should be able to click view filter criteria button");
        sdtp.AndIClickOnViewCriteriaButton();

        Allure.step("And I should be able to view the same applied criteria on view criteria section");
        sdtp.AndICanViewCriteriaCondition(ff);

    }

    @Test(description = "As a user i view the results as non zero of every multi_select_values filter on filter and data table page along with values in linked tabs",dataProviderClass = utils.ScreenerJsonDataProvider.class,dataProvider = "topFilters",groups = {"regression"},testName = "company")
    public void verifyUserApplyMultiSelectFilterAndNavigatesToDataTableForAllScreener(String groupName,
                                                                                          String subGroupName,
                                                                                          String category,
                                                                                          String subCategory,
                                                                                          String filterName,
                                                                                          String currentFilterType,
                                                                                          boolean isDefault,
                                                                                          String api,
                                                                                          String filterAPIReference) {
        int position=0;
        String filterPreCondition="Where";
        String screenerName = "Companies";
        String screenerTile = "All";

        FilterFormat ff = new FilterFormat(screenerName,screenerTile,filterPreCondition,position,groupName,filterName,currentFilterType,"In");

        //get range of input values
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+ff.screenerName+" screener page");
        slp.navigateToScreenerLandingPage(Domain, ff.screenerName);

        Allure.step("And I select tile " + screenerTile + " on landing page");
        slp.selectTileOnLandingPageOf(ff.screenerName,ff.tile);


        //apply filter on filter page with random value from input range values
        Allure.step("And I open filter group "+groupName+" and apply filter "+filterName+"on filter section");
        fs.thenIShouldBeAbleToOpenFilterUnderGroup(ff);

        // Retry logic to handle slow network capture on Jenkins
        String response = "";
        for (int i = 0; i < 10; i++) {
            response = mockingUtility.getResponseBodyFromScreenerAPICall(history, api, filterAPIReference);
            if (response != null && !response.isEmpty()) {
                break;
            }
            page.waitForTimeout(1000); // Wait 1s before retrying
        }
        if (response == null || response.isEmpty()) {
            throw new RuntimeException("Failed to capture API response for: " + filterAPIReference + " after 10 seconds.");
        }

        ff.addFilterInputValue(fms.getTopSelectInputOption(ff,1,response));
        fs.thenIShouldBeAbleToApplyFilterBasedOnType(ff);
        fs.clickApplyUpdateButton();

        Allure.step("Then I should see the applied filter in criteria section");
        vcs.AndICanViewButtonsOnCriteriaSection();
        vcs.AndICanViewCriteriaCondition(ff);

        Allure.step("Then I should see results section and card after applying any filter");
        vrs.thenIShouldSeeResultsSectionAndCard();

        Allure.step("And I should be able to click on view results button");
        vcs.AndIClickOnViewResultsButton();

        Allure.step("Then I should be able to view data table screen layout");
        sdt.verifyTableLayout(ff.screenerName);

        Allure.step("Then I should be able to view data table dropdown filters");
        sdtp.thenIShouldSeeDropDownFiltersOnDataTableScreen(ff);

        Allure.step("And I Should see applied filter on data table screen");
        sdtp.thenIShouldSeeAppliedFilterOnDataTableScreen(ff);

        Allure.step("And I should be able to click view filter criteria button");
        sdtp.AndIClickOnViewCriteriaButton();

        Allure.step("And I should be able to view the same applied criteria on view criteria section");
        sdtp.AndICanViewCriteriaCondition(ff);

    }

}
