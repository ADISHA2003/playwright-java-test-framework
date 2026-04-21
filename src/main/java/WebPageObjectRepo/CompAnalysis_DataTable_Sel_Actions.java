package WebPageObjectRepo;

import Constants.PageUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompAnalysis_DataTable_Sel_Actions extends BasePageActions {

    WebDriver driver;
    String datatableURL;

    public CompAnalysis_DataTable_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    By saveandviewall = By.cssSelector(".button.primary.sm.br-2");
    By pageheading = By.xpath("//h1[normalize-space()='Competitive Analysis']");
    By filters = By.xpath("//div[@class='snf-meta mb-16']");
    // By overview= By.xpath("//span[@class='badge']");
    By datatabletabs = By.cssSelector(".data-table-tabs a");
    By activetab = By.xpath("//a[@class='button active']");
    By Quickfilter = By.cssSelector(".button.select.sm.br-2.false");
    By search = By.cssSelector(".button-type-text.primary.fs-12.fw-500");
    By selectAll = By.xpath("//span[@class='checkbox-col']//input[@type='checkbox']");
    By selection = By.cssSelector(".bdr-l.ml-8.pl-8.mr-16.fs-12");
    By addtomylist = By.xpath("//body[1]/div[2]/div[1]/div[2]/div[3]/div[3]/div[1]/div[1]/div[3]/span[1]");
    By delete = By.xpath("//div[@class='flex p-12 pt-0 bdr-b data-table-prev-div']//span[2]");
    By rowsperpage = By.xpath("//select[@class='ffi sm ml-4']");
    By pagination = By.cssSelector(".pagination.mr-negative-4");
    By currencydropdown = By.cssSelector(".buttons-nav-vert .button");
    By currencydefaultselection = By.cssSelector(".button.select.sm.br-2");
    By columnsmanage = By.xpath("//div[@class='flex-align-right']//button[2]");
    By filter = By.xpath("//div[@class='flex-align-right']//button[3]");
    By export = By.xpath("//div[@class='flex-align-right']//button[4]");
    By generatecomparison = By.xpath("//button[normalize-space()='Generate Comparison']");
    By tableheading = By.xpath("//table/thead/tr/th");
    By modifycriteria = By.cssSelector(".svg-icon.fill.cta-blue.hover.md");
    By username = By.xpath("//input[@type='email']");
    By password = By.xpath("//input[@type='password']");
    By login = By.xpath("//div[normalize-space()='Login']");
    By title = By.cssSelector(".page-heading.md.text-color-white");
    By specificrow = By.xpath("(//td[@class='cell-hover'])[1]");
    By rowhoverdelete = By.xpath("(//span[@id='delete-company-0-col-0'])[1]");
    By deletetooltip = By.xpath("//div[@role='tooltip']");
    By deleteyesoption = By.xpath("//button[normalize-space()='Yes']");
    By deletionconfirmation = By.cssSelector("div[class='ml-16'] div[class='chip sm light-blue m-0']");
    By selectionconfirmation = By.xpath("//strong[@class='mr-8']");
    By rowhoverbookmark = By.xpath("((//span[@class='svg-icon md stroke hover dtr-list'])[1]");
    By addtobookmarkconfirmation = By.xpath("(//span[@class='svg-icon stroke active fill hover hover-bookmark md dtr-bookmark'])[1]");
    By rowhoveraddtomylist = By.xpath("(//span[@class='svg-icon md stroke hover dtr-list'])[1]");
    By addtomylistpopup = By.cssSelector(".box-title.mb-0.mr-8");
    By addtomylistonselection = By.xpath("(//strong[@class='fs-12 mln-8 mr-12'][normalize-space()='Add to list'])[1]");
    By Bookmarkonselection = By.xpath("//strong[normalize-space()='Bookmark']");
    By deleteonselection = By.xpath("//strong[@class='fs-12 mr-12']");
    By createnewlist = By.xpath("//button[normalize-space()='Create New List']");
    By Bookmarkconfirmationonselection = By.xpath("//div[@class='Toastify']");
    By Columnselection = By.cssSelector(".gragable-chips > div");
    By columnsave = By.xpath("//button[normalize-space()='Apply']");
    By selectColumn = By.xpath("//select[@class='ffi text-color-label']");
    By selectOperator = By.xpath("//select[@class='ffi text-color-label ml-8']");
    By enterfiltervalue = By.xpath("//input[@class='ffi text-color-label flex-grow ml-8']");
    By applyfilter = By.xpath("//button[normalize-space()='Apply']");
    By filtericon = By.cssSelector("By.cssSelector(\"span.svg-icon.fill.sm svg\")");
    By linkedInvestortab=By.xpath("//a[@id='Linked Investors']");
    By linkeddealtab=By.xpath("//a[@id='Linked Deals']");
    By linkedprofessionaltab=By.xpath("//a[@id='Linked Professionals']");
    By checkbox = By.cssSelector("label[class='custom-checkbox']");

    public boolean performLogin(String username, String password) {
        launchUrl(PageUrl.UAT_LOGIN_URL);
        sendKeysWithLocator(this.username, username);
        sendKeysWithLocator(this.password, password);
        clickOnLocator(this.login);
        return isElementVisible(title);
    }

    public boolean verifyfilters() {
        return isElementVisible(filters);
    }

    public boolean verifyModifyCriteria() {
        return isElementVisible(modifycriteria);
    }

    public List<String> getAllTabNames() throws InterruptedException {
        List<String> tabNames = new ArrayList<>();
        // Locate all <a> elements within the data-table-tabs container
        List<WebElement> tabElements = getElements(datatabletabs);

        for (WebElement tabElement : tabElements) {
            // Extract the text from the <span> element inside the <a> tag
            String tabText = tabElement.getText().trim();
            String tabName = tabText.replaceAll("\\(.*\\)", "").trim();
            tabNames.add(tabName);

        }
        System.out.println(tabNames);
        return tabNames;
    }

    // Method to verify the tab names with the expected names
    public boolean verifyTabNames(List<String> expectedTabNames) throws InterruptedException {
        List<String> actualTabNames = getAllTabNames();
        return actualTabNames.equals(expectedTabNames);
    }

    // Method to check the presence of counts in parentheses for all tabs
    public boolean areTabCountsPresent() throws InterruptedException {
        waitForElementVisible(specificrow);
        List<WebElement> tabs = getElements(datatabletabs);

        for (WebElement tab : tabs) {
            String tabText = tab.getText().trim();
            if (tabText.startsWith("Overview")) {
                continue;
            }
            if (!tabText.matches(".*\\(.*\\).*")) {
                return false; // Return false if any tab does not contain a count

            }
        }
        return true; // Return true if all tabs contain counts
    }

    public boolean verifyActiveTab(String expectedTabName) {
        waitForElementVisible(activetab, 20);
        String activeTabText = getSelElement(activetab).getText().replaceAll("\\(.*\\)", "").trim();

        return activeTabText.equals(expectedTabName);

    }

   /* // Method to check the presence of the quick filter
    public boolean isQuickFilterPresent(String expectedText) {
        String actualText = getSelElement(Quickfilter).getText().replaceAll("\\(.*\\)", "").trim();
        return actualText.equals(expectedText);
    }*/

    // Method to check the presence of the search box
    public boolean isSearchBoxPresent() {
        return isElementVisible(search);
    }


    public boolean verifyAndSelectCurrency(List<String> expectedOptions, String currency, String columnName) {
        // Click to open the currency dropdown
        waitForElementVisible(specificrow, 30);
        clickUsingJavascript(currencydefaultselection);  // Assuming this clicks the initial dropdown button to reveal options

        // Locate all currency buttons
        List<WebElement> allButtons = getElements(currencydropdown);

        // Extract the text of each currency option and clean up
        List<String> formattedActualOptions = allButtons.stream()
                .map(button -> button.getText().trim())  // Trim to remove leading/trailing spaces
                .filter(option -> !option.isEmpty())  // Filter out empty strings
                .collect(Collectors.toList());

        // Verify if the options match the expected options
        if (!formattedActualOptions.equals(expectedOptions)) {
            return false; // Options do not match expected options
        }

        // Select the specified currency from the dropdown
        boolean isCurrencySelected = false;
        for (WebElement button : allButtons) {
            String buttonText = button.getText().trim();
            if (buttonText.equals(currency)) {
                button.click();  // Select the currency button
                isCurrencySelected = true;
                System.out.println("Selected currency: " + currency);
                break;
            }
        }

        if (!isCurrencySelected) {
            return false; // Currency not found in the dropdown
        }

        // After selecting the currency, verify if the correct column name is displayed in the table
        List<WebElement> tableHeaders = getElements(tableheading); // Get the column headers
        for (WebElement header : tableHeaders) {
            if (header.getText().contains(columnName)) {
                return true; // Currency is updated in the column header
            }
        }

        return false; // Currency is not updated in the column header
    }

    public boolean currencyselection(String currency, String columnName) {
        waitForElementVisible(specificrow, 30);
        clickUsingJavascript(currencydefaultselection);
        WebElement dropdown = getSelElement(currencydropdown);
        String allOptionsText = dropdown.getText();

        // Split the options text correctly
        List<String> formattedActualOptions = Arrays.stream(allOptionsText.split("(?<=Million)|(?<=Crore)|(?<=Lakh)"))
                .map(String::trim)
                .collect(Collectors.toList());
        System.out.println("Formatted Actual Options: " + formattedActualOptions);
        System.out.println("Currency: " + currency);

        if (formattedActualOptions.contains(currency)) {
            for (String optionText : formattedActualOptions) {
                if (optionText.equalsIgnoreCase(currency.trim())) {
                    List<WebElement> optionElements = getElements(currencydropdown);
                    for (WebElement option : optionElements) {
                        System.out.println("Option: " + option.getText());
                        if (option.getText().trim().equalsIgnoreCase(currency.trim())) {
                            System.out.println("Optionequals: " + option.getText());
                            Actions actions = new Actions(driver);
                            actions.moveToElement(option).click().perform();
                            break;
                        }
                    }
                    break;
                }
            }
        } else {
            System.out.println("Currency not found in formattedActualOptions");
        }

        // Verify if the currency is updated in the column header
        List<WebElement> tableHeaders = getElements(tableheading);
        for (WebElement header : tableHeaders) {
            if (header.getText().contains(columnName)) {
                return true; // Currency is updated in the column header
            }
        }
        return false; // Currency is not updated in the column header
    }

    public boolean verifyColumnsManage() {
        return isElementVisible(columnsmanage);
    }

    // Method to check the presence of the filter option
    public boolean isFilterOptionPresent() {
        return isElementVisible(filter);
    }

    // Method to check the presence of the export option
    public boolean isExportOptionPresent() {
        return isElementVisible(export);
    }

    // Method to check the presence of the "Generate Comparison" button
    public boolean isGenerateComparisonPresent() {
        return isElementVisible(generatecomparison);
    }

    // Method to get all column headers from the data table
    public List<String> getAllColumnHeaders() {
        List<String> columnHeaders = new ArrayList<>();

        waitForElementVisible(tableheading, 10);
        List<WebElement> headerElements = getElements(tableheading);

        // Extract and print column names
        for (WebElement header : headerElements) {
            String headerText = header.getText().trim();
            columnHeaders.add(headerText);

        }

        return columnHeaders;
    }

    // Method to verify the column headers with the expected headers
    public boolean verifyColumnHeaders(List<String> expectedHeaders) {
        waitForElementVisible(specificrow, 30);
        List<String> actualHeaders = getAllColumnHeaders();
        System.out.println("Actual Headers: " + actualHeaders);
        System.out.println("Expected Headers: " + expectedHeaders);
        return actualHeaders.equals(expectedHeaders);
    }

    public boolean isSelectAllPresent() {
        waitForElementVisible(specificrow);
        clickOnLocator(selectAll);
        return isElementVisible(selectionconfirmation);


    }

    public boolean rowhoverdelete() {
        waitForElementVisible(specificrow, 20);
        hoverOverRow(specificrow);
        clickOnLocator(rowhoverdelete);
        waitForElementVisible(deletetooltip, 10);
        clickOnLocator(deleteyesoption);
        return isElementVisible(deletionconfirmation);

    }

    public boolean rowhoverbookmark() {

            waitForElementVisible(specificrow);
            hoverOverRow(specificrow);
            boolean isBookmarkVisible = isElementVisible(addtobookmarkconfirmation);
            if (isBookmarkVisible) {
                clickOnLocator(addtobookmarkconfirmation); // Click again to set it to false for the next execution
            }
            return isBookmarkVisible;

    }

    public boolean rowhoveraddtomylist() {
        waitForElementVisible(specificrow, 30);
        hoverOverRow(specificrow);
        clickOnLocator(rowhoveraddtomylist);
        waitForElementVisible(addtomylistpopup, 10);
        return isElementVisible(addtomylistpopup);
    }

    public boolean verifyAddToMyListOnSelection() {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(selectAll);
        clickOnLocator(addtomylistonselection);
        waitForElementVisible(addtomylistpopup);
        return isElementVisible(addtomylistpopup);}

    public boolean verifyBookmarkOnSelection() {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(selectAll);
        clickOnLocator(Bookmarkonselection);
        return isElementVisible(Bookmarkconfirmationonselection);
    }

    public boolean verifyDeleteOnSelection() {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(selectAll);
        clickOnLocator(deleteonselection);
        return isElementVisible(deletionconfirmation);
    }

    public boolean addColumnToDataTable(String columnName) {
        clickOnLocator(columnsmanage);
        List<WebElement> columnOptions = getElements(Columnselection);
            for (WebElement columnOption : columnOptions) {
            String columnOptionText = columnOption.getText().trim();
            if (columnOptionText.equalsIgnoreCase(columnName)) {
                clickUsingJavascript(columnOption);
                getSelSubElement(columnOption, checkbox).click();

            }
        }

       clickOnLocator(columnsave);

        // Verify if the column is added to the table headers
        List<WebElement> tableHeaders = getElements(tableheading);
        for (WebElement header : tableHeaders) {
            String sanitizedHeaderText = header.getText().trim().replaceAll("[^\\x20-\\x7E]", "").toLowerCase();
            if (sanitizedHeaderText.equalsIgnoreCase(columnName.toLowerCase())) {
                return true; // Column found in the table headers
            }
        }

        return false;
    }

    public boolean goToInvestorTab(String expectedText) {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(linkedInvestortab);
        forceWait(5);
        waitForElementVisible(specificrow, 20);
        return verifyActiveTab(expectedText);
    }

    public boolean goToDealsTab(String expectedText) {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(linkeddealtab);
        forceWait(5);
        waitForElementVisible(specificrow, 20);
        return verifyActiveTab(expectedText);
    }

    public boolean goToProfessionalsTab(String expectedText) {
        waitForElementVisible(specificrow, 20);
        clickOnLocator(linkedprofessionaltab);
        forceWait(5);
        waitForElementVisible(specificrow, 20);
        return verifyActiveTab(expectedText);
    }
}