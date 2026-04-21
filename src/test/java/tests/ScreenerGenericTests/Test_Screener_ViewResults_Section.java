package tests.ScreenerGenericTests;

import PlaywrightPageObject.ScreenerFilterPage.FilterFormat;
import PlaywrightPageObject.ScreenerFilterPage.FilterManagementService;
import Utilities.JsonReaderService;
import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.util.*;
import java.util.logging.Filter;

public class Test_Screener_ViewResults_Section extends BaseUI_Test {


    @Test(description = "As a user i view results section and card before applying filter",dataProvider = "filterDataProvider", groups = "smoke")
    public void As_a_user_i_view_results_section_and_card_before_applying_filter(String filterType,String tile) {
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+filterType+" screener page");
        slp.navigateToScreenerLandingPage(Domain, filterType);

        Allure.step("And I select tile " + tile + " on landing page");
        slp.selectTileOnLandingPageOf(filterType,tile);

        Allure.step("Then I should see results section and card before applying any filter");
        vrs.thenIShouldSeeResultsSectionAndCard();
    }

    @DataProvider(name = "filterDataProvider")
    public Object[][] filterDataProvider() {
        return new Object[][] {
                {"Companies", "All"},

                {"Investor", "Asset Manager"},
                {"Investor", "Fund"},
                {"Investor", "Limited Partner"},
                {"Investor", "Family Office"},
        
                {"Deal", "All Deals"},
                {"Deal", "Private Equity Investment"},              
                {"Deal", "Merger and Acquisition"},
                {"Deal", "Private Equity Exits"},
                {"Deal", "Equity Capital Market"},
                {"Deal", "Debt Transaction"}
                
        };
    }

    @Test(description = "As a user i view results section and card after applying filter",dataProvider = "filterDataProviderWithValues",groups = "smoke")
    public void As_a_user_i_view_results_section_and_card_after_applying_filter(String screener,String tile,String filterGroup,String filterTypeInGroup,LinkedList<String> optionToSelect) {
        String filterType = sfe.getCompanyFilerType().get(filterTypeInGroup);
        FilterFormat ff = new FilterFormat(screener,tile,"Where",0,filterGroup,filterTypeInGroup,filterTypeInGroup,"In",optionToSelect);

        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+ff.screenerName+" screener page");
        slp.navigateToScreenerLandingPage(Domain, ff.screenerName);

        Allure.step("And I select tile " + tile + " on landing page");
        slp.selectTileOnLandingPageOf(ff.screenerName,ff.tile);

        Allure.step("And I apply filter on filter section");
        fs.openFilterUnderGroup(ff);
        fs.handleMultiSelectFilter(ff.filterInputValues.get(0));
        fs.clickApplyUpdateButton();

        Allure.step("Then I should see the applied filter in criteria section");
        vcs.AndICanViewButtonsOnCriteriaSection();
        vcs.AndICanViewCriteriaCondition(ff);


        Allure.step("Then I should see results section and card after applying any filter");
        vrs.thenIShouldSeeResultsSectionAndCard();

        Allure.step("And I should be able to click on view results button");
        vcs.AndIClickOnViewResultsButton();

        Allure.step("Then I should be able to view data table screen layout");
        sdt.verifyTableLayout(ff.tile);

        Allure.step("Then I should be able to view data table dropdown filters");
        sdtp.thenIShouldSeeDropDownFiltersOnDataTableScreen(ff);

        Allure.step("And I Should see applied filter on data table screen");
        sdtp.thenIShouldSeeAppliedFilterOnDataTableScreen(ff);

        Allure.step("And I should be able to click view filter criteria button");
        sdtp.AndIClickOnViewCriteriaButton();

        Allure.step("And I should be able to view the same applied criteria on view criteria section");
        sdtp.AndICanViewCriteriaCondition(ff);

    }




    @DataProvider(name = "filterDataProviderWithValues")
    public Object[][] filterDataProviderWithValues() {
        return new Object[][] {
            {"Companies", "All","Company Profile","Company Type",new LinkedList<>(Arrays.asList("Public Company"))},
            
            {"Investors", "Asset Manager","Profile","Investor Type",
                new LinkedList<>(Arrays.asList("Venture Capital"))},

            {"Investors", "Fund","Profile","Fund Type",
                    new LinkedList<>(Arrays.asList("Angel"))},
            
            {"Investors", "Limited Partner", "Profile", "Limited Partner Type",
                    	 new LinkedList<>(Arrays.asList("Asset Manager"))},

            {"Investors", "Family Office","Profile","Family Office Status",
                        new LinkedList<>(Arrays.asList("Subsidiary"))},

            {"Deals", "All Deals","Deal Information","Deal Type",
                            new LinkedList<>(Arrays.asList("Private Equity Investment"))},

            {"Deals", "Private Equity Investment","Deal Information","Deal Subtype",
                                new LinkedList<>(Arrays.asList("Venture Capital"))},

            {"Deals", "Merger and Acquisition","Deal Information","Deal Subtype",
                                    new LinkedList<>(Arrays.asList("Outbound"))},

            {"Deals", "Private Equity Exits","Deal Information","Deal Subtype",
                                new LinkedList<>(Arrays.asList("Open Market"))},
            
            {"Deals", "Equity Capital Market","Deal Information","Deal Subtype",
                                    new LinkedList<>(Arrays.asList("Initial Public Offering"))},

            {"Deals", "Debt Transaction","Deal Information","Deal Subtype",
                                    new LinkedList<>(Arrays.asList("Venture Debt"))}
        };
    }


}
