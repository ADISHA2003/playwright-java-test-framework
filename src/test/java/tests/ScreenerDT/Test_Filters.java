package tests.ScreenerDT;

import Constants.ColumnType;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import tests.BaseUI_Test;

import java.util.List;


public class Test_Filters extends BaseUI_Test {

    @Test(
            description = "Apply and verify filter ",
            groups = "smoke"
    )
    public void Test_Apply_And_Verify_Filter() {
        String screenerType = "Companies";
        String tile = "All";

        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");
        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        int columnIndex = dtFilterVerifier.getColumnIndexByName("table", "Company Name");

        List<String> filterValues = filter.applyFilter(
                "Company Name",
                ColumnType.STRING,
                "IN"
        );

        String [][] popUpBox = {
                {".read-more.text-color-link.cursor-hand","cellClick"},
                {".react-tooltip", "wait"},
                {"ul.pl-8 li", "extract"},
                {".dialog-close-outside", "click"}
        };

        dtFilterVerifier.verifyFilterResults(
                "table tbody",
                columnIndex,
                ColumnType.STRING,
                "IN",
                filterValues,
                "",
                popUpBox
        );
    }

    @Test(
            description = "Verify Reset button select default columns"
//            groups = "smoke"
    )
    public void Test_Apply_Modal_String_Filter() {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        String columnName = "Sub-Industry";
        int columnIndex = dtFilterVerifier.getColumnIndexByName("table", columnName);
        // STRING column: Country IN [India, USA]
        filter.applyFilter(
                columnName,
                ColumnType.STRING,
                "IN",
                "Services to financial Institutions"
        );

        String [][] modal = {
            {".read-more.text-color-link.cursor-hand","cellClick"},
            {".MuiPaper-root.MuiPaper-elevation", "wait"},
            {".pills-list", "extract"},
            {".svg-icon.stroke.hover.dialog-close", "click"}
        };
        dtFilterVerifier.verifyFilterResults(
                "table tbody",
                columnIndex,
                ColumnType.STRING,
                "IN",
                List.of("Services to financial Institutions"),
                "",
                modal
        );
//        page.waitForTimeout(3000);
//        filter.applyFilter("Company Name", ColumnType.STRING, "NOT_IN", "swiggy ltd.");

    }

    @Test(
            description = "Verify Reset button select default columns"
//            groups = "smoke"
    )
    public void Test_Apply_Side_Drawer_String_Filter() {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        String columnName = "Investors Name";
        int columnIndex = dtFilterVerifier.getColumnIndexByName("table", columnName);
        // STRING column: Country IN [India, USA]
        filter.applyFilter(
                columnName,
                ColumnType.STRING,
                "IN",
                "360 one asset management ltd."
        );

        String [][] sideDrawer = {
                {".read-more.text-color-link.cursor-hand","cellClick"},
                {".MuiPaper-root.MuiPaper-elevation", "wait"},
                {".p-12.loop-parent .flex strong", "extract"},
                {".button.secondary.p-0", "click"}
        };
        dtFilterVerifier.verifyFilterResults(
                "table tbody",
                columnIndex,
                ColumnType.STRING,
                "IN",
                List.of("360 one asset management ltd."),
                "",
                sideDrawer
        );
//        page.waitForTimeout(3000);
//        filter.applyFilter("Company Name", ColumnType.STRING, "NOT_IN", "swiggy ltd.");

    }

    @Test(
            description = "Verify Reset button select default columns"
//            groups = "smoke"
    )
    public void Test_Apply_Number_Filter() {
        String screenerType = "Companies";
        String tile = "All";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        int columnIndex = dtFilterVerifier.getColumnIndexByName("table", "Edge Score");

        filter.applyFilter("Edge Score", ColumnType.NUMBER, "GTE", "9.8");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER, "GTE", List.of("9.8"),"",null);

        filter.applyFilter("Edge Score", ColumnType.NUMBER, "GT", "9.8");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"GT", List.of("9.8"),"",null);


        filter.applyFilter("Edge Score", ColumnType.NUMBER, "LTE", "9.8");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"LTE", List.of("9.8"),"",null);

        filter.applyFilter("Edge Score", ColumnType.NUMBER, "LT", "9.8");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"LT", List.of("9.8"),"",null);

        filter.applyFilter("Edge Score", ColumnType.NUMBER, "EQUALS", "9.8");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"EQUALS", List.of("9.8"),"",null);

//        filter.applyFilter("Edge Score", ColumnType.NUMBER, "NOT_EQUALS", "9.8");
//        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"NOT_EQUALS", List.of("9.8"),"",null);

        filter.applyFilter("Edge Score", ColumnType.NUMBER, "BETWEEN", "5", "10");
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"BETWEEN", List.of("5", "10"),"",null);

//        filter.applyFilter("Edge Score", ColumnType.NUMBER, "NOT_BETWEEN", "5", "10");
//        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.NUMBER,"NOT_BETWEEN", List.of("5", "10"),"",null);

    }

    @Test(
            description = "Verify Reset button select default columns"
//            groups = "smoke"
    )
    public void Test_Apply_Date_Filter() {
        String screenerType = "investor";
        String tile = "Fund";
        Allure.step("Given I am logged into the application");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        slp.navigateToScreenerAndOpenResults(Domain, screenerType, tile);

        int columnIndex = dtFilterVerifier.getColumnIndexByName("table", "Launched Date");

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "EQUALS",
                "01/01/2006"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"EQUALS", List.of("01/01/2006"),"",null);

//        filter.applyFilter("Launched Date",
//                ColumnType.DATE,
//                "NOT_EQUALS",
//                "10/02/2022"
//        );
//        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"NOT_EQUALS", List.of("10/02/2022"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "ON_OR_AFTER",
                "01/01/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"ON_OR_AFTER", List.of("01/01/2020"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "AFTER",
                "01/01/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"AFTER", List.of("01/01/2020"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "ON_OR_BEFORE",
                "31/12/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"ON_OR_BEFORE", List.of("31/12/2020"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "BEFORE",
                "31/12/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"BEFORE", List.of("31/12/2020"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "BETWEEN",
                "01/01/2010",
                "31/12/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"BETWEEN", List.of("01/01/2010", "31/12/2020"),"",null);

        filter.applyFilter("Launched Date",
                ColumnType.DATE,
                "NOT_BETWEEN",
                "01/01/2010",
                "31/12/2020"
        );
        dtFilterVerifier.verifyFilterResults("table tbody", columnIndex, ColumnType.DATE,"NOT_BETWEEN", List.of("01/01/2010", "31/12/2020"),"",null);
    }
}
