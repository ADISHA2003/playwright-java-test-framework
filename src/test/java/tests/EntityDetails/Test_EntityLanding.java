package tests.EntityDetails;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.BaseUI_Test;

import java.util.Map;

public class Test_EntityLanding extends BaseUI_Test {

    @Test(description = "Navigate to company details page and validate all tabs and subtabs with API monitoring",
          groups = {"smoke","regression"})
    public void test_companyDetailsNavigationAndAPICheck(String entityType, String entityId) {

        SoftAssert softAssert = new SoftAssert();

        Allure.step("Given user is logged in to VCCEdge application");
            lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");
       

        Allure.step("When user navigates to company details page with ID: " + entityId);
            edna.navigateToEntityDetailsPage(entityType, entityId);
        
        try{Allure.step("Then company details page should load without API failures");
            edna.verifyEntityDetailsPageLoaded(entityType, entityId);
            } catch(AssertionError e){
                Allure.step("Then company details page should load without API failures",()->{softAssert.fail("Entity Details page did not load correctly: " + e.getMessage());});
            }
        
        Allure.step("When user navigates through all tabs and sub-tabs");
        Map<String, Integer> tabApiCounts = edna.navigateAndValidateAllTabs(false);
       
        Allure.step("Then all tabs should load successfully with API monitoring", () -> {
            if (tabApiCounts != null) {
                Allure.step("Total tabs/sub-tabs navigated: " + tabApiCounts.size());
                tabApiCounts.forEach((tabName, apiCount) ->
                    Allure.step(String.format("Tab '%s': %d API calls", tabName, apiCount))
                );
            }
        });

        Allure.step("And no API failures should be detected");
        if (edna.getAPIMonitor().hasAPIFailures()) {
            String failureReport = edna.getAPIMonitor().generateFailureReport();
            Allure.addAttachment("API Failures Detected", "text/plain", failureReport);
            softAssert.fail("API failures detected: " + failureReport);
        }

        edna.getAPIMonitor().logAPISummaryToAllure();

        // Assert all soft assertions
        softAssert.assertAll();
    }


    //data provider for other entity types can be added here
    @org.testng.annotations.DataProvider(name = "entityTypes")
    public Object[][] entityTypesProvider() {
        return new Object[][] {
            {"company","666e987e499bf94d6f71833f"}//public bank
            //public general
            //private bank
            //private insurance
            //private general
            //asset manager
            //fund
            //incubator
            //Fo
            //LP
            //incubator
            //mna
            //pe
            //pee
            //ecm
            //dd
            // Additional entity types can be added here
        };
    }

}
