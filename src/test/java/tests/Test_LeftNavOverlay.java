

package tests;

import Constants.PageUrl;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class Test_LeftNavOverlay extends BaseUI_Test{

	
	
	@Test(description = "Left Nav | As a user I can navigate through all left navigation tabs and sub-tabs", groups = {"smoke", "regression"})
    public void verify_left_navigation_tabs() {
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I click on the Left nav icon to open left navigation");

        // ---------- TAB 1 ----------
		
		
        
        Allure.step("Then I click on Home (no sub-tabs) and verify it redirects to Dashboard page");
        lna.openLeftNavigation();  // custom method to click 9-dot icon 
        lna.navigateTo("Home");
        lna.verifyNavigationToPage("Home", "Home");
        
        
        
        Allure.step("Then I hover over Company Tab and verify Company sub-tabs navigation");
		  String[] CompanySubTabs = {"Company Screener", "Investor Screener","Deals Screener"}; 
		  for (String CompanyTab : CompanySubTabs) {
		  lna.openLeftNavigation(); // custom method to click 9-dot icon
		  lna.navigateTo("Screener", CompanyTab);
		  lna.verifyNavigationToPage("Screener", CompanyTab); }
		  
		  
		  // ---------- TAB 2 ---------- 
		  Allure.step("Then I click on Key Developement  (no sub-tabs) and verify navigation"); 
		  lna.openLeftNavigation(); // custom method to click 9-dot icon
		  lna.navigateTo("Key Development");
		  lna.verifyNavigationToPage("Key Development", "Key Development");
		  
		  
		  // ---------- TAB 3 ---------- 
		  Allure.step("Then I hover over Directories  and verify Directories sub-tabs navigation"); 
		  String[] DirectoriesSubTabs = { "All Companies","Private Companies","Public Companies",
		  "Private Equity Investment","Private Equity Exits","Debt Transaction",
		  "Merger Acquisition","Equity Capital Market","Asset Manager","Fund","Limited Partner","Family Office" }; 
		  for (String DirectoriesTab :
		  DirectoriesSubTabs) { 
		  lna.openLeftNavigation(); // custom method to click9-dot icon 
		  lna.navigateTo("Directories", DirectoriesTab);
		  lna.verifyNavigationToPage("Directories",DirectoriesTab); }
		 
      //   ---------- TAB 4 ----------
       Allure.step("Then I hover over XARTUP  and verify  navigation");
       lna.openLeftNavigation(); // custom method to click 9-dot icon
       lna.navigateAndVerifyNewTab("xartp", "xartup"); 


        
        
        // ---------- TAB 5 ----------
    
       Allure.step("Then I hover over VCCIRCLE  and verify New VCCIRCLE Tab navigation");
        lna.openLeftNavigation(); // custom method to click 9-dot icon
        lna.navigateAndVerifyNewTab("vccle", "vccircle");
        
        // ---------- TAB 6 ----------
		
		
        Allure.step("Then I hover over Analytical Tools and verify My Lists sub-tab navigation"); 
        lna.openLeftNavigation(); // custom method to click 9-dot icon
		lna.navigateTo("Analytical Tools", "My Lists");
		lna.verifyNavigationToPage("Analytical Tools","My Lists");
		  
		  // ---------- TAB 7 ----------
		
		  Allure.step("Then I click on VCCEdge Intelligence (no sub-tabs) and verify navigation"); 
		  lna.openLeftNavigation(); // custom method to click 9-dot icon
		  lna.navigateTo("VCCEdge Intelligence");
		  lna.verifyNavigationToPage("VCCEdge Intelligence","VCCEdge Intelligence");
		  
		  // ---------- TAB 8 ---------- Allure.
        Allure.step("Then I hover over myVccEdge Tab and verify myVccEdge sub-tabs navigation" ); 
        String[] myVccEdgeSubTabs = {"My Profile","Bookmarks","Saved Filters","XLS Downloads"}; 
        for (String
		  MyVccEdgeTab : myVccEdgeSubTabs) {
          lna.openLeftNavigation(); // custom method
		  lna.navigateTo("My VCC Edge", MyVccEdgeTab);
		  lna.verifyNavigationToPage("My VCC Edge",MyVccEdgeTab); }
		 
    }
}
