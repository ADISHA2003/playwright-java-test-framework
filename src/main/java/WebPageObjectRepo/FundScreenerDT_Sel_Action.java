package WebPageObjectRepo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import Utilities.CSV_FileReader;

public class FundScreenerDT_Sel_Action extends BasePageActions {

    By Tab_Overview = By.xpath("//span[contains(text(), 'Overveiw')]");
    By Tab_Funds = By.xpath("//span[contains(text(), 'Funds')]");
    By Tab_Linked_Companies = By.xpath("//span[contains(text(), 'Linked Companies')]");
    By Tab_Linked_Deals = By.xpath("//span[contains(text(), 'Linked Deals')]");
    By Screener_ViewAllResult_button = By.xpath("//button[contains(text(), 'View All Result')]");
    By Modify_link = By.xpath("//a[contains(text(), 'Modify')]");
    By View_Criteria_Button = By.xpath("//button[contains(text(), 'View Criteria')]");
    By View_Criteria_Modal = By.xpath("//div[@class = 'bd-block']");
    By View_Criteria_Modal_Modify_criteria_button = By.xpath("//a[contains(text(),'Modify Criteria')]");
    By View_Criteria_Modal_Close_Modal_button = By.xpath("//span[@class = 'svg-icon stroke dark bd-close']");

    By Tab_Column_Headers = By.xpath("//thead[@class = 'no-top-bdr'] //tr //th");
    By Filter_button = By.xpath("//button[contains(text(),'Filter')]");
    By FilterPopup_ColumnDropdown = By.xpath("//select[@id='0']");
    By FilterPopup_operatordropdown = By.xpath("//div[@class = 'shrink-0 w-118 bg-gray flex'] //select");
    By Filter_Value_field = By.xpath("//div[@class = 'dt-dd-meta']");
    By Filter_Value_field_Min_Max = By.xpath("//input[@class='filter-input']");
    By Filter_value_change_In_Control = By.xpath("//div[@id = 'changeInControl']");
    By Filter_Value_date_ranges = By.xpath("//input[@class='rs-date-range-input rs-input']");
    By Delete_filter = By.xpath("//span[@class= 'svg-icon stroke darkv1 hover xmd mr-2']");
    By View_All_Result_button = By.xpath("//button[contains(text(),'View All Result')]");
    public By Login_button_web_Home = By.xpath("//a[contains (text(), 'Login')]");
    public By login_Page_Email = By.xpath("//input[@type='email']");
    public By login_Page_password=  By.xpath("//input[@type='password']");
    public By Login_page_submitbutton = By.xpath("//div[contains (text(), 'Login')]");

    public FundScreenerDT_Sel_Action(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }

    public WebElement getWebElement(By element){
        return driver.findElement(element);
    }

    public void createLoginSession(String email, String password) {

        if (driver.getCurrentUrl().contains("login")) {
            gotoURL("https://uatapp.vccedge.com/");
            hardWait(5000);
            getWebElement(Login_button_web_Home).click();
            hardWait(5000);
            getWebElement(By.xpath("//input[@type='email']")).sendKeys(email);
            getWebElement(By.xpath("//input[@type='password']")).sendKeys(password);
            getWebElement(By.xpath("//div[contains (text(), 'Login')]")).click();
            hardWait(5000);
        } else {
            clickOnLocator(LOGO_VCC_EDGE);
        }
    }

    public void navigatingToURL(String URL) {
        navigateToURL(URL);
        hardWait(10000);
    }

    // select_the_columns_and_Respective_operator_and_value_field
    public boolean select_the_columns_and_Respective_operator_and_value_field(String ScreenerName, String TabName) {
        boolean result = false;
        boolean result_Dry_Powder = false, result_Investment_Size_Max = false, result_Investment_Size_Min = false,
                result_Total_Investment = false, result_Exits = false, result_Geographical_Preference = false,
                result_Fund_Raising_Stage_Date = false, result_Fund_Raising_Stage = false, result_HQ_Location = false,
                result_Investor_Type = false, result_Focus = false, result_Investment_Type = false,
                result_Deals_in_last_12 = false, result_Investee_Company = false, result_Area_of_Interest = false,
                result_Amount_Raised = false, result_Fund_Size = false, result_Asset_Manager = false,
                result_Launched_Date = false, result_Fund_Status = false, result_FundsType = false,
                result_FundName = false;
        boolean Result_LCN_Company_Name = false, Result_LCN_Fund_Name = false, Result_LCN_City = false,
                Result_LCN_Country = false, Result_LCN_Company_Type = false, Result_LCN_Company_Status = false,
                Result_LCN_Founded_Year = false, Result_LCN_Company_Stage = false, Result_LCN_Sector = false,
                Result_LCN_Industry = false, Result_LCN_Business_Model = false, Result_LCN_Sub_Industry = false,
                Result_LCN_Trade_Name = false, Result_LCN_Edge_Score = false,
                Result_LCN_Total_Equity_Funding_Raised = false, Result_LCN_Latest_PE_Valuation = false,
                Result_LCN_Latest_Annual_Revenue = false, Result_LCN_Latest_Market_Cap = false,
                Result_LCN_Employee_Count = false, Result_LCN_PAT_Margin = false, Result_LCN_EBITDA = false,
                Result_LCN_Valuation_Class = false,
                Result_LCN_Revenue_CAGR_3_Years = false, Result_LCN_EBITDA_CAGR_3_Years = false,
                Result_LCN_EBITDA_Margin = false, Result_LCN_Gross_Profit_Margin = false, Result_LCN_Net_Profit = false,
                Result_LCN_No_of_Investors = false;
        boolean result_LDT_Deal_Date = false, result_LDT_Target_Company = false, result_LDT_Deal_Type = false,
                result_LDT_Buyer_Lender = false, result_LDT_Seller_Borrower = false, result_LDT_Deal_Value = false,
                result_LDT_Deal_Subtype = false, result_LDT_Deal_Feature = false, result_LDT_Deal_Status = false,
                result_LDT_Transaction_Announced_Date = false, result_LDT_Transaction_Closing_Date = false,
                result_LDT_Cancelled_Date = false, result_LDT_Deal_Stage = false, result_LDT_Change_in_Control = false,
                result_LDT_Percent_Sought = false, result_LDT_EV_Revenue = false, result_LDT_EV_EBITDA = false,
                result_LDT_EV_PAT = false;

        if (ScreenerName == "Funds Screener") {
            if (TabName == "Funds") {

                clickOnLocator(Tab_Funds);
                hardWait(5000);
                clickOnLocator(Filter_button);
                hardWait(4000);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                // getting the column dropdown in the List
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());

                hardWait(2000);
                // Iterating with in the list of column dropdown
                for (String column : Actual_Column_filter_List) {
                    if (column.equalsIgnoreCase("Fund Name")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);
                        hardWait(2000);
                        boolean FundName_Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            FundName_Operator_dropdown_status = true;
                        } else {
                            FundName_Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean FundName_filter_value_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_FundName = FundName_Operator_dropdown_status && FundName_filter_value_status;
                        System.out.println("result_FundName : " + result_FundName);
                    } else if (column.equals("Fund Type")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_FundsType = Operator_dropdown_status && filter_value_Field_status;

                    } else if (column.equals("Fund Status")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Fund_Status = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Fund_Status : " + result_Fund_Status);
                    } else if (column.equals("Launched Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Launched_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Launched_Date : " + result_Launched_Date);
                    } else if (column.equals("Asset Manager")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Asset_Manager = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Asset_Manager : " + result_Asset_Manager);
                    } else if (column.equals("Fund Size")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Fund_Size = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Fund_Size : " + result_Fund_Size);
                    } else if (column.equals("Amount Raised")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Amount_Raised = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Amount_Raised : " + result_Amount_Raised);
                    } else if (column.equals("Area of Interest")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Area_of_Interest = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Area_of_Interest:" + result_Area_of_Interest);
                    } else if (column.equals("Current Investments")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Investee_Company = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Investee_Company: " + result_Investee_Company);
                    } else if (column.equals("#Deals in last 12 months")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Deals_in_last_12 = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Deals_in_last_12: " + result_Deals_in_last_12);
                    } else if (column.equals("Investment Type")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Investment_Type = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Investment_Type: " + result_Investment_Type);
                    } else if (column.equals("Focus")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Focus = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Focus : " + result_Focus);
                    } else if (column.equals("Investor Type")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Investor_Type = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Investor_Type: " + result_Investor_Type);
                    } else if (column.equals("HQ Location")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_HQ_Location = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_HQ_Location: " + result_HQ_Location);
                    } else if (column.equals("Fund Raising Stage")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Fund_Raising_Stage = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Fund_Raising_Stage : " + result_Fund_Raising_Stage);
                    } else if (column.equals("Fund Raising Stage Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Fund_Raising_Stage_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Fund_Raising_Stage_Date : " + result_Fund_Raising_Stage_Date);
                    } else if (column.equals("Geographical Preference")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Geographical_Preference = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Geographical_Preference: " + result_Geographical_Preference);
                    } else if (column.equals("Prior Investments")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();

                        boolean filter_value_Field_status = isElementEnabled(driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Exits = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Exits : " + result_Exits);
                    } else if (column.equals("Total Investment")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Total_Investment = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Total_Investment : " + result_Total_Investment);
                    } else if (column.equals("Investment Size (Min) $mn")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Investment_Size_Min = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Investment_Size_Min: " + result_Investment_Size_Min);
                    } else if (column.equals("Investment Size (Max) $mn")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Investment_Size_Max = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Investment_Size_Max: " + result_Investment_Size_Max);
                    } else if (column.equals("Dry Powder")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        // System.out.println("Operator List : " + Actual_OperatorList);

                        hardWait(2000);

                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }

                        hardWait(2000);

                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();

                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        // System.out.println(FundName_Operator_dropdown_status &&
                        // FundName_filter_value_status);
                        result_Dry_Powder = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Dry_Powder: " + result_Dry_Powder);
                    } else {
                        System.out.println("Unknown column: " + column);
                    }
                    result = result_Dry_Powder && result_Investment_Size_Max && result_Investment_Size_Min
                            && result_Total_Investment && result_Exits && result_Geographical_Preference
                            && result_Fund_Raising_Stage_Date && result_Fund_Raising_Stage && result_HQ_Location
                            && result_Investor_Type && result_Focus && result_Investment_Type && result_Deals_in_last_12
                            && result_Investee_Company && result_Area_of_Interest && result_Amount_Raised
                            && result_Fund_Size && result_Asset_Manager && result_Launched_Date && result_Fund_Status
                            && result_FundsType && result_FundName;
                }

            } else if (TabName == "Linked Companies") {
                clickOnLocator(Tab_Linked_Companies);
                hardWait(5000);
                clickOnLocator(Filter_button);
                hardWait(4000);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                // getting the column dropdown in the List
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());
                hardWait(2000);
                System.out.println(Actual_Column_filter_List);
                // Iterating with in the list of column dropdown
                for (String column : Actual_Column_filter_List) {
                    if (column.equalsIgnoreCase("Company Name")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Company_Name = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Company_Name: " + Result_LCN_Company_Name);
                    } else if (column.equals("Fund Name")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Fund_Name = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Fund_Name: " + Result_LCN_Fund_Name);
                    } else if (column.equals("City")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_City = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_City: " + Result_LCN_City);
                    } else if (column.equals("Country")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Country = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Country: " + Result_LCN_Country);
                    } else if (column.equals("Company Type")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Company_Type = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Company_Type: " + Result_LCN_Company_Type);
                    } else if (column.equals("Company Status")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Company_Status = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Company_Status: " + Result_LCN_Company_Status);
                    } else if (column.equals("Founded Year")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Founded_Year = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Founded_Year: " + Result_LCN_Founded_Year);
                    } else if (column.equals("Company Stage")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Company_Stage = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Company_Stage: " + Result_LCN_Company_Stage);
                    } else if (column.equals("Sector")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Sector = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Sector: " + Result_LCN_Sector);
                    } else if (column.equals("Industry")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Industry = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Industry: " + Result_LCN_Industry);
                    } else if (column.equals("Business Model")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Business_Model = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Business_Model: " + Result_LCN_Business_Model);
                    } else if (column.equals("Sub-Industry")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Sub_Industry = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Sub_Industry: " + Result_LCN_Sub_Industry);
                    } else if (column.equals("Trade Name")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Trade_Name = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Trade_Name: " + Result_LCN_Trade_Name);
                    } else if (column.equals("Edge Score")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Edge_Score = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Edge_Score: " + Result_LCN_Edge_Score);
                    } else if (column.equals("Total Equity Funding Raised")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Total_Equity_Funding_Raised = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println(
                                "result_Total_Equity_Funding_Raised: " + Result_LCN_Total_Equity_Funding_Raised);
                    } else if (column.equals("Latest PE Valuation")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Latest_PE_Valuation = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Latest_PE_Valuation: " + Result_LCN_Latest_PE_Valuation);
                    } else if (column.equals("Latest Annual Revenue")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Latest_Annual_Revenue = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Latest_Annual_Revenue: " + Result_LCN_Latest_Annual_Revenue);
                    } else if (column.equals("Latest Market Cap")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Latest_Market_Cap = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Latest_Market_Cap: " + Result_LCN_Latest_Market_Cap);
                    } else if (column.equals("Employee Count")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Employee_Count = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Employee_Count: " + Result_LCN_Employee_Count);
                    } else if (column.equals("PAT Margin")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_PAT_Margin = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_PAT_Margin: " + Result_LCN_PAT_Margin);
                    } else if (column.equals("EBITDA")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_EBITDA = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_EBITDA: " + Result_LCN_EBITDA);
                    } else if (column.equals("Valuation Class")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Valuation_Class = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Valuation_Class: " + Result_LCN_Valuation_Class);
                    } else if (column.equals("Revenue CAGR (3 Years)")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Revenue_CAGR_3_Years = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Revenue_CAGR_3_Years: " + Result_LCN_Revenue_CAGR_3_Years);
                    } else if (column.equals("EBITDA CAGR (3 Years)")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_EBITDA_CAGR_3_Years = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_EBITDA_CAGR_3_Years: " + Result_LCN_EBITDA_CAGR_3_Years);
                    } else if (column.equals("EBITDA Margin")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_EBITDA_Margin = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_EBITDA_Margin: " + Result_LCN_EBITDA_Margin);
                    } else if (column.equals("Gross Profit Margin")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Gross_Profit_Margin = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Gross_Profit_Margin: " + Result_LCN_Gross_Profit_Margin);
                    } else if (column.equals("Net Profit")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_Net_Profit = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_Net_Profit: " + Result_LCN_Net_Profit);
                    } else if (column.equals("No of Investors")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status;
                        if (Actual_OperatorList != null && !Actual_OperatorList.isEmpty()) {
                            Operator_dropdown_status = true;
                        } else {
                            Operator_dropdown_status = false;
                        }
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        Result_LCN_No_of_Investors = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_No_of_Investors: " + Result_LCN_No_of_Investors);
                    } else {
                        System.out.println("Unknown column: " + column);
                    }

                    result = Result_LCN_Company_Name && Result_LCN_Fund_Name && Result_LCN_City
                            && Result_LCN_Country
                            && Result_LCN_Company_Type && Result_LCN_Company_Status && Result_LCN_Founded_Year
                            && Result_LCN_Company_Stage && Result_LCN_Sector && Result_LCN_Industry
                            && Result_LCN_Business_Model && Result_LCN_Sub_Industry && Result_LCN_Trade_Name
                            && Result_LCN_Edge_Score && Result_LCN_Total_Equity_Funding_Raised
                            && Result_LCN_Latest_PE_Valuation && Result_LCN_Latest_Annual_Revenue
                            && Result_LCN_Latest_Market_Cap && Result_LCN_Employee_Count && Result_LCN_PAT_Margin
                            && Result_LCN_EBITDA && Result_LCN_Valuation_Class
                            && Result_LCN_Revenue_CAGR_3_Years && Result_LCN_EBITDA_CAGR_3_Years
                            && Result_LCN_EBITDA_Margin && Result_LCN_Gross_Profit_Margin && Result_LCN_Net_Profit
                            && Result_LCN_No_of_Investors;
                }

            } else if (TabName == "Linked Deals") {
                clickOnLocator(Tab_Linked_Deals);
                hardWait(5000);
                clickOnLocator(Filter_button);
                hardWait(4000);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                // getting the column dropdown in the List
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());

                hardWait(2000);
                System.out.println(Actual_Column_filter_List);
                // Iterating with in the list of column dropdown
                for (String column : Actual_Column_filter_List) {
                    if (column.equals("Deal Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Date: " + result_LDT_Deal_Date);
                    } else if (column.equals("Target Company")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Target_Company = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Target_Company: " + result_LDT_Target_Company);
                    } else if (column.equals("Deal Type")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Type = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Type: " + result_LDT_Deal_Type);
                    } else if (column.equals("Buyer/Lender")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Buyer_Lender = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Buyer_Lender: " + result_LDT_Buyer_Lender);
                    } else if (column.equals("Seller/Borrower")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Seller_Borrower = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Seller_Borrower: " + result_LDT_Seller_Borrower);
                    } else if (column.equals("Deal Value")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Value = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Value: " + result_LDT_Deal_Value);
                    } else if (column.equals("Deal Subtype")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Subtype = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Subtype: " + result_LDT_Deal_Subtype);
                    } else if (column.equals("Deal Feature")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Feature = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Feature: " + result_LDT_Deal_Feature);
                    } else if (column.equals("Deal Status")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Status = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Status: " + result_LDT_Deal_Status);
                    } else if (column.equals("Transaction Announced Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Transaction_Announced_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println(
                                "result_LDT_Transaction_Announced_Date: " + result_LDT_Transaction_Announced_Date);
                    } else if (column.equals("Transaction Closing Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Transaction_Closing_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out
                                .println("result_LDT_Transaction_Closing_Date: " + result_LDT_Transaction_Closing_Date);
                    } else if (column.equals("Cancelled Date")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_date_ranges).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_date_ranges));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Cancelled_Date = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Cancelled_Date: " + result_LDT_Cancelled_Date);
                    } else if (column.equals("Deal Stage")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Deal_Stage = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Deal_Stage: " + result_LDT_Deal_Stage);
                    } else if (column.equals("Change in Control")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_value_change_In_Control).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_value_change_In_Control));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Change_in_Control = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Change_in_Control: " + result_LDT_Change_in_Control);
                    } else if (column.equals("% Sought")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_Percent_Sought = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_Percent_Sought: " + result_LDT_Percent_Sought);
                    } else if (column.equals("EV/Revenue")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_EV_Revenue = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_EV_Revenue: " + result_LDT_EV_Revenue);
                    } else if (column.equals("EV/EBITDA")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_EV_EBITDA = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_EV_EBITDA: " + result_LDT_EV_EBITDA);
                    } else if (column.equals("EV/PAT")) {
                        select_value_dropdown_by_text(dropdown, column);
                        WebElement selectoperator = driver.findElement(FilterPopup_operatordropdown);
                        selectoperator.click();
                        Select operator_dropdown = new Select(selectoperator);
                        List<String> Actual_OperatorList = operator_dropdown.getOptions().stream().filter(option -> {
                            String style = option.getAttribute("style");
                            String disabled = option.getAttribute("disabled");
                            return (style == null || !style.contains("display: none")) &&
                                    (disabled == null || disabled.isEmpty());
                        }).map(WebElement::getText).collect(Collectors.toList());
                        hardWait(2000);
                        boolean Operator_dropdown_status = Actual_OperatorList != null
                                && !Actual_OperatorList.isEmpty();
                        hardWait(2000);
                        operator_dropdown.selectByIndex(0);
                        driver.findElement(Filter_Value_field_Min_Max).click();
                        boolean filter_value_Field_status = isElementEnabled(
                                driver.findElement(Filter_Value_field_Min_Max));
                        hardWait(2000);
                        clickOnLocator(Delete_filter);
                        hardWait(1000);
                        result_LDT_EV_PAT = Operator_dropdown_status && filter_value_Field_status;
                        System.out.println("result_LDT_EV_PAT: " + result_LDT_EV_PAT);
                    } else {
                        System.out.println("Unknown column: " + column);
                    }

                    result = result_LDT_Deal_Date && result_LDT_Target_Company && result_LDT_Deal_Type
                            && result_LDT_Buyer_Lender && result_LDT_Seller_Borrower && result_LDT_Deal_Value
                            && result_LDT_Deal_Subtype && result_LDT_Deal_Feature && result_LDT_Deal_Status
                            && result_LDT_Transaction_Announced_Date && result_LDT_Transaction_Closing_Date
                            && result_LDT_Cancelled_Date && result_LDT_Deal_Stage && result_LDT_Change_in_Control
                            && result_LDT_Percent_Sought && result_LDT_EV_Revenue && result_LDT_EV_EBITDA
                            && result_LDT_EV_PAT;
                }
            } else {
                result = false;
            }
        }
        /* result = FS_FT_result && FS_LCT_result /*&& FS_LDT_result */;
        return result;
    }

    // Filter Column Dropdown Validation
    public boolean validateFilterColumnDropdown(String ScreenerName, String TabName) {
        boolean result = false;
        if (ScreenerName == "Funds Screener") {
            if (TabName == "Funds") {
                clickOnLocator(Tab_Funds);
                hardWait(2000);
                clickOnLocator(Filter_button);
                hardWait(2000);
                waitForElementVisible(FilterPopup_ColumnDropdown);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());
                System.out.println("List from Web |" + TabName + ": " + Actual_Column_filter_List);
                // dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
                forceWait(5);
                List<String> Expected_Column_filter_list = Arrays.asList("Fund Name", "Fund Type", "Fund Status",
                        "Launched Date", "Asset Manager", "Fund Size", "Amount Raised", "Area of Interest",
                        "Current Investments", "#Deals in last 12 months", "Investment Type", "Focus", "Investor Type",
                        "HQ Location", "Fund Raising Stage", "Fund Raising Stage Date", "Geographical Preference",
                        "Prior Investments", "Total Investment", "Investment Size (Min) $mn",
                        "Investment Size (Max) $mn",
                        "Dry Powder");

                List<String> Actual_list_difference = Actual_Column_filter_List.stream()
                        .filter(item -> !Expected_Column_filter_list.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Column_filter_list.stream()
                        .filter(item -> !Actual_Column_filter_List.contains(item))
                        .collect(Collectors.toList());
                // Expected_Column_filter_list.removeAll(Actual_Column_filter_List);
                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println("Extra in actual: " + Actual_list_difference);
                    System.out.println("Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }
            } else if (TabName == "Linked Companies") {
                clickOnLocator(Tab_Linked_Companies);
                hardWait(2000);
                clickOnLocator(Filter_button);
                hardWait(2000);
                waitForElementVisible(FilterPopup_ColumnDropdown);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());
                System.out.println("List from Web |" + TabName + ": " + Actual_Column_filter_List);
                // dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
                forceWait(5);
                List<String> Expected_Column_filter_list = Arrays.asList("Company Name", "Fund Name", "City", "Country",
                        "Company Type", "Company Status", "Founded Year", "Sector", "Industry", "Business Model",
                        "Sub-Industry", "Trade Name", "Edge Score", "Company Stage", "Total Equity Funding Raised",
                        "Latest PE Valuation", "Latest Annual Revenue", "Latest Market Cap", "Employee Count",
                        "PAT Margin", "EBITDA", "Valuation Class", "Revenue CAGR (3 Years)", "EBITDA CAGR (3 Years)",
                        "EBITDA Margin", "Gross Profit Margin", "Net Profit", "No of Investors");

                List<String> Actual_list_difference = Actual_Column_filter_List.stream()
                        .filter(item -> !Expected_Column_filter_list.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Column_filter_list.stream()
                        .filter(item -> !Actual_Column_filter_List.contains(item))
                        .collect(Collectors.toList());
                // Expected_Column_filter_list.removeAll(Actual_Column_filter_List);
                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println("Extra in actual: " + Actual_list_difference);
                    System.out.println("Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }
            } else if (TabName == "Linked Deals") {
                clickOnLocator(Tab_Linked_Deals);
                hardWait(2000);
                clickOnLocator(Filter_button);
                hardWait(2000);
                waitForElementVisible(FilterPopup_ColumnDropdown);
                WebElement selectElement = driver.findElement(FilterPopup_ColumnDropdown);
                selectElement.click();
                Select dropdown = new Select(selectElement);
                List<String> Actual_Column_filter_List = dropdown.getOptions().stream().filter(option -> {
                    String style = option.getAttribute("style");
                    String disabled = option.getAttribute("disabled");
                    return (style == null || !style.contains("display: none")) &&
                            (disabled == null || disabled.isEmpty());
                }).map(WebElement::getText).collect(Collectors.toList());
                System.out.println("List from Web |" + TabName + ": " + Actual_Column_filter_List);
                // dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
                forceWait(5);
                List<String> Expected_Column_filter_list = Arrays.asList(
                        "Target Company", "Deal Date", "Buyer/Lender", "Seller/Borrower", "Deal Value", "Deal Subtype",
                        "Deal Feature", "Deal Status", "Transaction Announced Date", "Transaction Closing Date",
                        "Cancelled Date", "Deal Stage", "Change in Control", "% Sought", "EV/Revenue", "EV/EBITDA",
                        "EV/PAT");
                List<String> Actual_list_difference = Actual_Column_filter_List.stream()
                        .filter(item -> !Expected_Column_filter_list.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Column_filter_list.stream()
                        .filter(item -> !Actual_Column_filter_List.contains(item))
                        .collect(Collectors.toList());
                // Expected_Column_filter_list.removeAll(Actual_Column_filter_List);
                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println("Extra in actual: " + Actual_list_difference);
                    System.out.println("Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }
            } else {
                result = false;
            }

        }
        return result;
    }

    public boolean isclosebuttonvisible() {
        clickOnLocator(View_Criteria_Button);
        hardWait(2000);
        return isElementVisible(View_Criteria_Modal_Close_Modal_button) && isElementVisible(View_Criteria_Modal);
    }

    public boolean onclickclosebuoon() {
        String Expected_Url = "https://uatapp.vccedge.com/screener/funds/filters";
        clickOnLocator(View_Criteria_Modal_Close_Modal_button);
        return VerifyURL(Expected_Url);
    }

    public boolean onclickModifyCriteria() {
        String Expected_Url = "https://uatapp.vccedge.com/screener/funds/filters";
        clickOnLocator(View_Criteria_Button);
        hardWait(3000);
        clickOnLocator(View_Criteria_Modal_Modify_criteria_button);
        return VerifyURL(Expected_Url);
    }

    public boolean isNewModalwithModifyCriteriaButtonVisible() {
        clickOnLocator(View_Criteria_Button);
        return isElementVisible(View_Criteria_Modal) && isElementVisible(View_Criteria_Modal_Modify_criteria_button);
    }

    // Overview Tab is visible
    public boolean isTabOverviewVisible() {
        hardWait(3000);
        return isElementVisible(Tab_Overview);
    }

    // Funds Tab is visible
    public boolean isTabFundsVisible() {
        hardWait(3000);
        return isElementVisible(Tab_Funds);
    }

    // Linked Companies is Visible
    public boolean isTabLinkedCompaniesVisible() {
        hardWait(3000);
        return isElementVisible(Tab_Linked_Companies);
    }

    // Linked Deals is visible
    public boolean isTabLinkedDealsVisible() {
        hardWait(3000);
        return isElementVisible(Tab_Linked_Deals);
    }

    // click on View Result Button
    public void click_on_View_Result_Button() {
        clickOnLocator(View_All_Result_button);
        hardWait(5000);
    }

    // get the Default columns of each tab and assert them
    public boolean validateTabdefaultColumns(String ScreenerName, String Tabname) {

        boolean result = false;
        if (ScreenerName == "Funds Screener") {
            if (Tabname == "Funds") {

                clickOnLocator(Tab_Funds);
                hardWait(5000);
                List<String> Actual_default_columns = getElements(Tab_Column_Headers).stream().map(WebElement::getText)
                        .collect(Collectors.toList());
                System.out.println("Test Actusal :| " + Actual_default_columns);
                List<String> Expected_Default_Columns = Arrays.asList("Fund Name", "Fund Type", "Fund Status",
                        "Launched Date", "Asset Manager", "Fund Size ₹*", "Amount Raised ₹*", "Area of Interest",
                        "Current Investments", "#Deals in last 12 months");

                List<String> Actual_list_difference = Actual_default_columns.stream()
                        .filter(item -> !Expected_Default_Columns.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Default_Columns.stream()
                        .filter(item -> !Actual_default_columns.contains(item))
                        .collect(Collectors.toList());

                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println("Extra in actual: " + Actual_list_difference);
                    System.out.println("Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }

            } else if (Tabname == "Linked Companies") {
                clickOnLocator(Tab_Linked_Companies);
                hardWait(5000);
                List<String> Actual_default_columns = getElements(Tab_Column_Headers).stream().map(WebElement::getText)
                        .collect(Collectors.toList());
                System.out.println("Test Actusal :|" + Tabname + "|" + Actual_default_columns);
                List<String> Expected_Default_Columns = Arrays.asList("Company Name", "Fund Name", "City", "Country",
                        "Company Type", "Company Status", "Founded Year", "Sector", "Industry", "Business Model");

                List<String> Actual_list_difference = Actual_default_columns.stream()
                        .filter(item -> !Expected_Default_Columns.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Default_Columns.stream()
                        .filter(item -> !Actual_default_columns.contains(item))
                        .collect(Collectors.toList());

                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println(Tabname + " | Extra in actual: " + Actual_list_difference);
                    System.out.println(Tabname + " | Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }

            } else if (Tabname == "Linked Deals") {
                clickOnLocator(Tab_Linked_Deals);
                hardWait(5000);
                List<String> Actual_default_columns = getElements(Tab_Column_Headers).stream().map(WebElement::getText)
                        .collect(Collectors.toList());
                System.out.println("Test Actusal :| " + Actual_default_columns);
                List<String> Expected_Default_Columns = Arrays.asList("Target Company", "Deal Date", "Deal Description",
                        "Deal Type", "Buyer/Lender", "Seller/Borrower", "Deal Value ₹*", "Deal Details");

                List<String> Actual_list_difference = Actual_default_columns.stream()
                        .filter(item -> !Expected_Default_Columns.contains(item))
                        .collect(Collectors.toList());
                // Actual_Column_filter_List.removeAll(Expected_Column_filter_list);
                List<String> Expected_list_difference = Expected_Default_Columns.stream()
                        .filter(item -> !Actual_default_columns.contains(item))
                        .collect(Collectors.toList());

                if (Actual_list_difference.isEmpty() && Expected_list_difference.isEmpty()) {
                    result = true;
                } else {
                    result = false;
                    System.out.println("Extra in actual: " + Actual_list_difference);
                    System.out.println("Missing from actual: " + Expected_list_difference);
                    CSV_FileReader.writeToCsvFileColumnViselist(Actual_list_difference, Expected_list_difference,
                            "Fund_Default_Tab_failed" + "_" + getdatetime() + "_Results.csv");
                }

            }
        }
        return result;

    }

    // Validate the Modify
    public boolean isModifylinkVisible() {
        clickApplyButton(Screener_ViewAllResult_button);
        waitForElementVisible(Modify_link);
        return isElementVisible(Modify_link);
    }

    public boolean onclickModifylink() {
        String Expected_Url = "https://uatapp.vccedge.com/screener/funds/filters";
        clickOnLocator(Modify_link);
        return VerifyURL(Expected_Url);
    }

    public boolean isViewCriteriaButtonVisible() {
        return isElementVisible(View_Criteria_Button);
    }

}
