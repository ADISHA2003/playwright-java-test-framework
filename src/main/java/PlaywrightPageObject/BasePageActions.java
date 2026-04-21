package PlaywrightPageObject;

import Utilities.PropertyFileReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasePageActions {

    protected Page page;

    protected ObjectMapper objectMapper = new ObjectMapper();
    PropertyFileReaderService prop = new PropertyFileReaderService();
    public BasePageActions(Page page) {
        this.page = page;
        this.page.setDefaultTimeout(120000);
    }

    public String launchUrl(String url) {
        page.navigate(url);
        return page.title();
    }


    public String getTitle() {
        return "";
    }


    public void click(Locator locator) {
        locator.click();
    }



    public void enterText(Locator element, String text) {
        element.fill(text);
    }


    public void scrollToElement(Object element) {

    }


    public void clearField(Object element) {

    }


    public void executeJavascript(String script) {

    }


    public void clickOnVisibleElement(Object element) {

    }


    public void hoverElement(Object element) {

    }

    public void hoverElementAndClick(Locator element) {
        element.hover();
        element.click();
    }

    public void hoverAndClickAtLocation(double x,double y){
        page.mouse().move(x,y);
        page.mouse().click(x,y);
    }


    public void mouseMoveToElement(Object element) {

    }


    public void selectDropDownByVisibleText(Object element, String text) {

    }


    public void selectDropDownByValue(Locator element, String value) {
        element.selectOption(value);
    }


    public void selectDropDownByIndex(Object element, int index) {

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


    public void clickSubmitButton(Object element) {

    }


    public void clickApplyButton(Object element) {

    }


    public void clickCancelButton(Object element) {

    }


    public void clickCrossButton(Object element) {

    }


    public Object getTextBoxValue(Object element) {
        return null;
    }


    public Object getLabelTitle(Object element) {
        return null;
    }


    public Object getPlaceHolderText(Object element) {
        return null;
    }


    public Object getToolTipText(Object element) {
        return null;
    }


    public Object getAttribute(Object element, String attribute) {
        return null;
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

    public void verifyCurrentUrl(String expectedPath, int seconds) {
        page.waitForURL(expectedPath, new Page.WaitForURLOptions().setTimeout(seconds * 120000));
        assertThat(page).hasURL(expectedPath);
    }

    public void clickButtonByExactText(String buttonName) {
        page.getByText(buttonName, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void waitForURL(String expectedPath, int seconds) {
        page.waitForURL(expectedPath, new Page.WaitForURLOptions().setTimeout(seconds * 1000));
    }

    public void fillInputByPlaceholder(String value, String placeholder){
        page.getByPlaceholder(placeholder).fill(value);
    }

    public void waitForUrlContains(String partialUrl, int timeoutMillis) {
        page.waitForURL("**" + partialUrl + "**",
                new Page.WaitForURLOptions().setTimeout(timeoutMillis * 1000));
    }

    public void waitForButtonWithExactTextToBeEnabled(String buttonText, int timeoutMillis) {
        page.waitForFunction(
                "text => { " +
                        "const el = Array.from(document.querySelectorAll('button')) " +
                        "          .find(b => b.textContent.trim() === text);" +
                        "return el && !el.disabled;" +
                        "}",
                buttonText,
                new Page.WaitForFunctionOptions().setTimeout(timeoutMillis * 1000)
        );
    }

    public void waitForElementToBeVisible(String selector, int timeoutMillis) {
        page.waitForSelector(
                selector,
                new Page.WaitForSelectorOptions().setTimeout(timeoutMillis * 1000).setState(WaitForSelectorState.VISIBLE)
        );
    }

    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public String getCellText(int rowIndex, int colIndex) {
        Locator cell = page.locator("table tbody tr").nth(rowIndex).locator("td").nth(colIndex);
        return cell.innerText().trim();
    }

    public boolean hasClassValue(String selector, String expectedClass) {

        Locator element= page.locator(selector);
        waitForElementToBeVisible(element, 30);

        String classValue = element.getAttribute("class");
        return classValue != null && classValue.contains(expectedClass); // class updated successfully
    }

    public boolean doesntHaveClassValue(String selector, String expectedClass) {
        String classValue = page.getAttribute(selector, "class");
        return classValue == null || !classValue.contains(expectedClass);
    }

    public String updateEntityType(String entityType) {
        if (entityType.equalsIgnoreCase("company"))
            return "Companies";
        else
            return entityType + "s";
    }

    public void waitForElementToBeVisible(Locator locator, int timeoutMillis) {
        locator.waitFor(new Locator.WaitForOptions()
                .setTimeout(timeoutMillis * 1000)
                .setState(WaitForSelectorState.VISIBLE)
        );
    }

    public String getAuthTokenFromSession(String endpoint){
        String authHolder[] = {null};

        page.onRequest(request -> {
            if (request.url().contains(endpoint)) {
                System.out.println("Request URL: " + request.url());
                System.out.println("auth"+request.headers().get("authorization"));
                authHolder[0] = request.headers().get("authorization");

            }
        });
        page.waitForRequest(
                req -> req.url().contains(endpoint),
                () -> {}
        );
        return authHolder[0];
    }

    public String convertToCamelCase(String key){
        String[] parts = key.split(" ");
        StringBuilder camelCaseKey = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCaseKey.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseKey.toString();
    }


    public void clickElementWhenVisible(String elementSelector) {
        Locator element = page.locator(elementSelector);
        waitForElementToBeVisible(elementSelector, 10); // optional wait for stability
        click(element);
    }



}
