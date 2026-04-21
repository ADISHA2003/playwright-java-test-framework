package tests.ScreenerDT;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.io.IOException;
import java.util.List;


public class Test_Manage_Columns extends BaseUI_Test {
    @Test(
            description = "Verify Reset button select default columns",
            groups = "smoke"
    )
    public void Test_Reset_Button_Select_Default_Columns() throws IOException {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();
        System.out.println(subTab.size());
        for(int i = 1; i < Math.min(subTab.size(), 5); i++){

            Locator currentTab = subTab.get(i);

            Allure.step("When I navigate to the " + screenerType + "(" + tile +") subtab(" + currentTab.textContent() +") and reset columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.resetToDefaultColumns();

            Allure.step("Then Default columns are visible in the table");
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab,tile, "Reset");

        }

    }

    @Test(
            description = "Verify Select all button selects all columns",
            groups = "smoke"
    )
    public void Test_Select_All_Button_Selects_All_Columns() throws IOException {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

        for(int i = 1; i < Math.min(subTab.size(), 5); i++){
            Locator currentTab = subTab.get(i);
            Allure.step("When I navigate to the " + screenerType + "(" + tile +") subtab(" + currentTab.textContent() +") and select all columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.selectAllColumns();

            Allure.step("Then all columns are visible in the table");
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab,tile, "Select All");

        }

    }

    @Test(
            description = "Verify Cancel button does not apply column changes",
            groups = "smoke"
    )
    public void Test_Cancel_Button_Does_Not_Apply_Changes() throws IOException {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the " + screenerType + "(" + tile +")");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

        for (int i = 1; i < Math.min(subTab.size(), 5); i++) {   // start from 1 to skip Overview
            Locator currentTab = subTab.get(i);

            Allure.step("When I navigate to the subtab (" + currentTab.textContent() + ") and reset columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.resetToDefaultColumns();

            // At this point: table should show DEFAULT columns

            Allure.step("And I open manage columns, select all, but click Cancel");
            screenerDTManageColumn.selectAllColumnsAndCancel();

            Allure.step("Then columns should remain default after Cancel");
            // Expectation is same as Reset scenario → default columns
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab, tile, "Reset");
        }
    }

    // @Test(
    //         description = "Verify drag and drop in Manage Columns side drawer reorders table columns",
    //         groups = "smoke",
    //         enabled = false
    // )
    // public void Test_Drag_And_Drop_Columns_Using_Drawer()  {
    //     String screenerType = "Companies";
    //     String tile = "All";
    //     Allure.step("Given I am logged into the application");
    //     lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

    //     Allure.step("When I navigate to the " + screenerType + "(" + tile +")");
    //     slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

    //     List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

    //     // start from 1 to skip 'Overview'
    //     for (int i = 1; i < Math.min(subTab.size(), 5); i++) {
    //         Locator currentTab = subTab.get(i);

    //         Allure.step("Navigate to subtab: " + currentTab.textContent());
    //         screenerDTManageColumn.navigateToSubTabs(currentTab);

    //         // 2) Drag in drawer using icon
    //         Allure.step("Open Manage Columns and drag " + 1 + "st column onto " + 2 + "nd column");
    //         List<String> before;
    //         before = screenerDTManageColumn.dragColumnInSideDrawer(1, 2);

    //         Allure.step("Then I verify the drag and drop");
    //         screenerDTManageColumn.verifyDragAndDrop(before, 1, 2);
    //     }
    // }



    @Test(
            description = "Verify Reset button select default columns",
            dataProvider = "screenerData",
            groups = "regression"
    )
    public void Test_Reset_Button_Select_Default_Columns_Regression(String screenerType, String tile) throws IOException {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

        for(int i = 1; i < Math.min(subTab.size(), 5); i++){

            Locator currentTab = subTab.get(i);

            Allure.step("When I navigate to the " + screenerType + "(" + tile +") subtab(" + currentTab.textContent() +") and reset columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.resetToDefaultColumns();

            Allure.step("Then Default columns are visible in the table");
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab,tile, "Reset");

        }

    }

    @Test(
            description = "Verify Select all button selects all columns",
            dataProvider = "screenerData",
            groups = "regression"
    )
    public void Test_Select_All_Button_Selects_All_Columns_Regression(String screenerType, String tile) throws IOException {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

        for(int i = 1; i < Math.min(subTab.size(), 5); i++){
            Locator currentTab = subTab.get(i);
            Allure.step("When I navigate to the " + screenerType + "(" + tile +") subtab(" + currentTab.textContent() +") and select all columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.selectAllColumns();

            Allure.step("Then all columns are visible in the table");
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab,tile, "Select All");

        }

    }

    @Test(
            description = "Verify Cancel button does not apply column changes",
            dataProvider = "screenerData",
            groups = "regression"
    )
    public void Test_Cancel_Button_Does_Not_Apply_Changes_Regression(String screenerType, String tile) throws IOException {
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to the " + screenerType + "(" + tile +")");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

        for (int i = 1; i < Math.min(subTab.size(), 5); i++) {   // start from 1 to skip Overview
            Locator currentTab = subTab.get(i);

            Allure.step("When I navigate to the subtab (" + currentTab.textContent() + ") and reset columns");
            screenerDTManageColumn.navigateToSubTabs(currentTab);
            screenerDTManageColumn.resetToDefaultColumns();

            // At this point: table should show DEFAULT columns

            Allure.step("And I open manage columns, select all, but click Cancel");
            screenerDTManageColumn.selectAllColumnsAndCancel();

            Allure.step("Then columns should remain default after Cancel");
            // Expectation is same as Reset scenario → default columns
            screenerDTManageColumn.verifyColumnsForAllSubTabs(currentTab, tile, "Reset");
        }
    }

    // @Test(
    //         description = "Verify drag and drop in Manage Columns side drawer reorders table columns",
    //         dataProvider = "screenerData", // Keep dataProvider for potential re-enabling
    //         enabled = false, // Disable this test
    //         groups = "regression"
    // )
    // public void Test_Drag_And_Drop_Columns_Using_Drawer_Regression(String screenerType, String tile)  {
    //     Allure.step("Given I am logged into the application");
    //     lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

    //     Allure.step("When I navigate to the " + screenerType + "(" + tile +")");
    //     slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

    //     List<Locator> subTab = screenerDTManageColumn.getSubTabLocator();

    //     // start from 1 to skip 'Overview'
    //     for (int i = 1; i < Math.min(subTab.size(), 5); i++) {
    //         Locator currentTab = subTab.get(i);

    //         Allure.step("Navigate to subtab: " + currentTab.textContent());
    //         screenerDTManageColumn.navigateToSubTabs(currentTab);

    //         // 2) Drag in drawer using icon
    //         Allure.step("Open Manage Columns and drag " + 1 + "st column onto " + 2 + "nd column");
    //         List<String> before;
    //         if(tile.equalsIgnoreCase("fund") && i == 3)
    //             before = screenerDTManageColumn.dragColumnInSideDrawer(2, 3);
    //         else
    //             before = screenerDTManageColumn.dragColumnInSideDrawer(1, 2);

    //         Allure.step("Then I verify the drag and drop");
    //         if(tile.equalsIgnoreCase("fund") && i == 3)
    //             screenerDTManageColumn.verifyDragAndDrop(before,2, 3);
    //         else
    //             screenerDTManageColumn.verifyDragAndDrop(before, 1, 2);
    //     }
    // }


    @DataProvider(name = "screenerData")
    public Object[][] screenerData() {
        return new Object[][]{

                // screenerType, tile
                {"Deal", "All Deals"},
                {"Deal", "Private Equity Investment"},
                {"Deal", "Merger and Acquisition"},
//                {"Deal", "Private Equity Exits"},
//                {"Deal", "Equity Capital Market"},
//                {"Deal", "Debt Transaction"},
                {"Investor", "Asset Manager"},
                {"Investor", "Fund"},
                {"Investor", "Limited Partner"},
                {"Investor", "Family Office"},
                {"Companies", "All"},
        };
    }
}
