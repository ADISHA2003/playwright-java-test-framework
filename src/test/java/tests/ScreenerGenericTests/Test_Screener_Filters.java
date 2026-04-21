package tests.ScreenerGenericTests;
import io.qameta.allure.Allure;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import tests.BaseUI_Test;

import org.testng.annotations.Test;


public class Test_Screener_Filters extends BaseUI_Test {


    @Test( description ="As a user i can navigate to screener filter page", dataProvider = "filterDataProvider", groups = "smoke")
    public void test_screener_filter_navigation(String filterType,String tile){

        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to " + filterType + " screener page");
        slp.navigateToScreenerLandingPage(Domain, filterType);

        Allure.step("And I select tile " + tile + " on landing page");
        slp.selectTileOnLandingPageOf(filterType,tile);

        Allure.step("Then I should be on " + filterType + " filter page");
        sth.thenIShouldBeOnScreenerFilterPage(filterType,tile);
        
    }

    @Test( description ="As a user i can view the order of default as well as all filter on screener filter landing page", dataProvider = "filterDataProvider", groups = "smoke")
    public void test_manage_modal_filter_order(String filterType,String tile){
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to "+filterType+" screener page");
        slp.navigateToScreenerLandingPage(Domain, filterType);

        Allure.step("And I select tile " + tile + " on landing page");
        slp.selectTileOnLandingPageOf(filterType,tile);

        Allure.step("And I click on manage modal button");
        vrs.thenIShouldSeeResultsSectionAndCard();
        fs.whenIClickOnManageModalButton();

        Allure.step("Then I should see the manage modal popup");
        Assert.assertTrue(mma.thenIVerifyManageModalPopupVisible());
        mma.andIClickOnResetButton();
        fs.whenIClickOnManageModalButton();
        Assert.assertTrue(mma.thenIVerifyManageModalPopupVisible());

        Allure.step("And I should see all filters in correct order", step ->{
            mma.thenIVerifyDefaultFiltersInCorrectOrderOf(filterType,tile);
        });
       // mma.thenIVerifyDefaultFiltersInCorrectOrderOf(filterType,tile);
    }

    @DataProvider(name = "filterDataProvider")
    public Object[][] filterDataProvider() {
        return new Object[][] {
                {"Company", "All"},

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
    
}
