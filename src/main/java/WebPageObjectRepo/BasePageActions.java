package WebPageObjectRepo;

import Constants.FilePath;
import Utilities.AssertAndReporterService;
import Utilities.PropertyFileReaderService;
import co.elastic.clients.elasticsearch.watcher.Email;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BasePageActions {

    WebDriver driver;
    Page page;
    public AssertAndReporterService assert_report;

    public BasePageActions(WebDriver driver) {
        this.driver = driver;
    }

    public By LOGO_VCC_EDGE = By.xpath("//img[@alt='VCCEdge']");
    By INPUT_GLOBAL_SEARCH = By.xpath("//input[@data-testid='search-input']");
    By HEADER_NAV_INFO = By.cssSelector(".header-nav .svg-icon:nth-child(1)");
    By HEADER_NAV_HELP = By.cssSelector(".header-nav .svg-icon:nth-child(2)");
    By HEADER_NAV_PROFILE = By.cssSelector(".header-nav .svg-icon:nth-child(3)");
    By BUTTON_APPLY = By.xpath("//button[@class=\'button primary\' and contains(text(),\'Apply\')]");;
    By BUTTON_CANCEL = By.xpath("//button[@class=\'button primary\' and contains(text(),\'Cancel\')]");
    By EMAIL = By.xpath("//input[@type='email']");
    By PASSWORD = By.xpath("//input[@type='password']");
    By LOGIN_BUTTON = By.xpath("//div[@class='loginpage-login-btn click-me']");
    By TEXT_CLIENT_EXCEPTION = By
            .xpath("//h2[contains(text(), \"Application error: a client-side exception has occurred\")]");
    By Login_button_web_Home = By.xpath("//a[contains (text(), 'Login')]");

    @Step("Launch URL: {0}")
    public String launchUrl(String url) {
        System.out.println(driver.toString());
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        return driver.getTitle();
    }

    public void hardWait(int milisecondTime) {
        try {
            Thread.sleep(milisecondTime);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void gotoURL(String URL) {
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void navigateToURL(String URL) {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        hardWait(5000);
    }

    public void createSession(String BaseURL , String email, String password) {
        if (getCurrentUrl().contains("login")) {
            gotoURL(BaseURL);
            hardWait(5000);
            driver.findElement(Login_button_web_Home).click();
            hardWait(5000);
            driver.findElement(EMAIL).sendKeys(email);
            driver.findElement(PASSWORD).sendKeys(password);
            driver.findElement(LOGIN_BUTTON).click();
            hardWait(5000);
        } else {
             clickOnLocator(LOGO_VCC_EDGE);
        }
    }

    public void login(String username, String password) {

        if (driver.getCurrentUrl().contains("login")) {
            waitForElementVisible(EMAIL, 10);
            getSelElement(EMAIL).sendKeys(username);
            getSelElement(PASSWORD).sendKeys(password);
            getSelElement(LOGIN_BUTTON).click();
        } else {
            clickOnLocator(LOGO_VCC_EDGE);
        }

    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean clickonElement(WebElement element) {
        element.click();
        return true;
    }

    public void enterText(Object element, String text) {

    }

    public void scrollToElement(Object element) {

    }

    public void clearField(Object element) {

    }

    public void executeJavascript(String script) {

    }

    public void clickOnVisibleElement(Object element) {

    }

    public boolean clickAndHoldSlider(WebElement element, int percentage) {
        Actions actions = new Actions(driver);
        int width = element.getSize().getWidth();
        int xOffset = (int) (width * (percentage / 100.0));
        actions.clickAndHold(element).moveByOffset(xOffset, 0).release().build().perform();
        return true;
    }

    // Select value in dropdown using the Text
    public void select_value_dropdown_by_text(Select dropdown, String dropdownText) {
        dropdown.selectByVisibleText(dropdownText);
        hardWait(1000);
    }

    public void hoverElement(Object element) {

    }

    // Method to hover over a specific row in a table
    public void hoverOverRow(By rowLocator) {
        WebElement rowElement = driver.findElement(rowLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(rowElement).perform();
    }

    public boolean mouseMoveToElement(WebElement element) {

        new Actions(driver).moveToElement(element).release().build().perform();
        return true;

    }

    public boolean selectDropDownByVisibleText(WebElement element, String text) {

        new Select(element).selectByVisibleText(text);
        return true;
    }

    public void selectDropDownByValue(WebElement element, String value) {

        new Select(element).selectByValue(value);
    }

    public void selectDropDownByIndex(WebElement element, int index) {

        new Select(element).selectByIndex(index);

    }

    public void selectRadioButton(Object element) {

    }

    public void selectCheckBox(Object element) {

    }

    public void switchToFrame(Object element) {

    }

    public void switchToDefaultContent() {

    }

    public void switchToWindow(String window) {

    }

    public void switchToParentWindow() {

    }

    public void switchToAlert() {

    }

    public void switchToPrompt() {

    }

    public void clickSubmitButton(WebElement element) {
        element.click();
    }

    public void clickApplyButton(Object element) {

    }

    public void clickCancelButton(Object element) {

    }

    public void clickCrossButton(Object element) {

    }

    public String getTextBoxValue(WebElement element) {
        return element.getAttribute("value");
    }

    public Object getLabelTitle(WebElement element) {
        return null;
    }

    public String getPlaceHolderText(WebElement element) {
        return element.getAttribute("value");
    }

    public Object getToolTipText(Object element) {
        return null;
    }

    public String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public Object getCssValue(Object element, String cssValue) {
        return null;
    }

    public Object getTagName(Object element) {
        return null;
    }

    public Object getLinkText(Object element) {
        return null;
    }

    public void dragAndDrop(Object sourceElement, Object targetElement) {

    }

    public void doubleClick(Object element) {

    }

    public void rightClick(Object element) {

    }

    public void clickOutSideOverlay() {

    }

    public void selectValuesFromDatePicker(Object element, String date) {

    }

    public void selectValuesFromTimePicker(Object element, String time) {

    }

    public void getElementScreenshot(Object element) {

    }

    public void scrollPageDown() {

    }

    public void scrollPageUp() {

    }

    public void scrollDownTillElement(Object element) {

    }

    public void scrollUpTillElement(Object element) {

    }

    public void scrollRight() {

    }

    public void scrollLeft() {

    }

    public void verifyValuesInTable(Object element, String column, String value) {

    }

    public List<Object> getTableValues(Object element, String column) {
        return new LinkedList<Object>();
    }

    public Object getTableValue(Object element, String row, String column) {
        return null;
    }

    public Object getTableValue(Object element, int row, int column) {
        return null;
    }

    public Object getTableValue(Object element, int row, String column) {
        return null;
    }

    public boolean verifyVCCLogo() {
        return driver.findElement(LOGO_VCC_EDGE).isDisplayed();
    }

    public boolean verifyGlobalSearch() {
        return driver.findElement(INPUT_GLOBAL_SEARCH).isDisplayed();
    }

    public boolean verifyGlobalSearchPlaceHolder(String placeholderText) {
        return verifyPlaceHolderText(INPUT_GLOBAL_SEARCH, placeholderText);
    }

    public boolean verifyPlaceHolderText(By inp_locator, String placeHolderText) {
        waitForElementVisible(inp_locator, 10);
        return driver.findElement(inp_locator).getAttribute("placeholder").equals(placeHolderText);

    }

    public boolean verifySearchNavOptions() {
        return driver.findElement(HEADER_NAV_INFO).isDisplayed() &&
                driver.findElement(HEADER_NAV_HELP).isDisplayed() &&
                driver.findElement(HEADER_NAV_PROFILE).isDisplayed();
    }

    public boolean verifyPattern(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return text.matches(regex);
    }

    public void forceWait(int timeSecond) {
        try {
            Thread.sleep(timeSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> driver.findElement(locator).isDisplayed());
    }

    public void waitForElementVisible(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(driver -> driver.findElement(locator).isDisplayed());
    }

    public WebElement getSelElement(By locator) {
        waitForElementVisible(locator, 10);
        return driver.findElement(locator);
    }

    public WebElement getSelSubElement(By locator, By subLocator) {
        return driver.findElement(locator).findElement(subLocator);
    }

    public WebElement getSelSubElement(WebElement element, By subLocator) {
        return element.findElement(subLocator);
    }

    public boolean isElementVisible(By locator) {
        return getSelElement(locator).isDisplayed();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementEnabled(By locator) {
        return getSelElement(locator).isEnabled();
    }

    public boolean clickOnLocator(By locator) {
        getSelElement(locator).click();
        return true;
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> getSelSubElementS(WebElement element, By subLocator) {
        return element.findElements(subLocator);
    }

    public boolean verifyText(By locator, String actualText) {
        WebElement element = getSelElement(locator);
        return element.getText().equalsIgnoreCase(actualText);
    }

    public boolean verifyTextByWebElement(WebElement element, String actualText) {

        return element.getText().equalsIgnoreCase(actualText);
    }

    public boolean sendKeys(By locator, String key) {
        getSelElement(locator).sendKeys(key);
        return true;
    }

    public void waitForUrlToBe(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void waitForUrlToBe(String expectedUrl, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void clickUsingJavascript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return;
    }

    public void clickUsingJavascript(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean clickAndVerifyURL(By locator, String expectedUrl) {
        System.out.println(getSelElement(locator).isDisplayed());
//        WebElement link = getSelElement(locator);
//        System.out.println("link : " + link);
//        clickonElement(link);
//        clickOnLocator(locator);

//        clickUsingJavascript(locator);
//        clickElementWhenClickable(locator);
        mouseHoverAndClick(locator);
        // Wait for the new page to load (if applicable)
//        waitForUrlToBe(expectedUrl, 10);
        System.out.println("expected url : "+expectedUrl);


        // Verify the URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("current url : " + currentUrl);
        return currentUrl.equals(expectedUrl);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean clickAndVerifyURL(WebElement element, String expectedUrl) {
        clickonElement(element);

        // Wait for the new page to load (if applicable)
        waitForUrlToBe(expectedUrl, 10);

        // Verify the URL
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(expectedUrl);
    }

    public boolean VerifyURL(String expectedUrl) {

        waitForUrlToBe(expectedUrl, 10);

        // Verify the URL
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(expectedUrl);
    }

    public void clickElementWhenClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println(element);
        element.click();
    }

    public boolean getSelElementTextAndCompare(WebElement element, String expected) {
        return element.getText().equals(expected);
    }

    public boolean getSelElementTextAndCompare(By locator, String expected) {
        return getSelElement(locator).getText().equals(expected);
    }

    public void sendKeysWithLocator(By locator, String text) {
        getSelElement(locator).sendKeys(text);
    }

    public void sendKeysWithElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void pressEnter(By locator) {
        getSelElement(locator).sendKeys(Keys.ENTER);
    }

    public boolean checkIfLocatorVisible(By locator) {
        int count = 0;
        Boolean flag = false;
        while (count < 5) {
            try {
                flag = isElementVisible(locator);
                count++;
            } catch (NoSuchElementException | TimeoutException te) {
                flag = false;
            }
        }
        return flag;
    }

    public Boolean verifyClientException() {
        return checkIfLocatorVisible(TEXT_CLIENT_EXCEPTION);
    }

    public Boolean launchNewTabAndNavigateToUrl(String Url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + Url + "', '_blank');");
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.navigate().to(Url.toLowerCase());
        return true;
    }

    public Boolean verifyCurrentPageTitle(String pageName) {
        waitForPageToLoad(pageName);
        return driver.getCurrentUrl().toLowerCase().contains(pageName.toLowerCase());
    }

    public String getCurrentPageTitle() {
        return driver.getCurrentUrl();
    }

    public Boolean waitForPageToLoad(String pageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains(pageName.toLowerCase()));
        return true;
    }

    public Boolean checkIfUserProfileIconVisible() {
        return checkIfLocatorVisible(HEADER_NAV_PROFILE);
    }

    public Boolean verifyUserLoggedIn() {
        return checkIfLocatorVisible(INPUT_GLOBAL_SEARCH);
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public String getdatetime() {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatted = timestamp.format(formatter);
        return formatted;
    }

    public boolean mouseHoverAndClick(By locator) {
        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = getSelElement(locator);

            System.out.println("mouseHover");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();

            return true;
        } catch (Exception e) {
            System.out.println("Failed to hover and click: " + e.getMessage());
            return false;
        }
    }

}
