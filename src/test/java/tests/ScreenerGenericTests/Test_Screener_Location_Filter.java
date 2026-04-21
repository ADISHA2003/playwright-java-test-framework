package tests.ScreenerGenericTests;

import PlaywrightPageObject.ScreenerFilterPage.FilterFormat;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.util.LinkedList;

public class Test_Screener_Location_Filter extends BaseUI_Test {

    @Test(description = "As a user i view the results as non zero of every graph filter on filter and data table page along with values in linked tabs",dataProviderClass = utils.ScreenerJsonDataProvider.class,dataProvider = "getLocationFilters",groups = {"smoke","regression"},testName = "company")
    public void verifyUserApplyLocationTreeFilterAndNavigatesToDataTable(String screenerName,String groupName,String subgroupName,String category,String subCategory,String filterName,String currentFilterType,Boolean isDefault,String api,String filterAPIReference) {
        int position=0;
        String filterPreCondition="Where";
        String screenerTile = "All";

        FilterFormat ff = new FilterFormat(screenerName,screenerTile,filterPreCondition,position,groupName,filterName,currentFilterType,"In",new LinkedList<String>(){{add("India -> Delhi");}});

        //get range of input values
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+ff.screenerName+" screener page");
        slp.navigateToScreenerLandingPage(Domain, ff.screenerName);

        Allure.step("And I select tile " + screenerTile + " on landing page");
        slp.selectTileOnLandingPageOf(ff.screenerName,ff.tile);

        //apply filter on filter page with random value from input range values
        Allure.step("And I apply filter on filter section");
        fs.thenIShouldBeAbleToOpenFilterUnderGroup(ff);
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
        sdtp.thenIShouldSeeAppliedFilterOnDataTableScreenOnPopup(ff);

        Allure.step("And I should be able to click view filter criteria button");
        sdtp.AndIClickOnViewCriteriaButton();

        Allure.step("And I should be able to view the same applied criteria on view criteria section");
        sdtp.AndICanViewCriteriaCondition(ff);

    }

}
