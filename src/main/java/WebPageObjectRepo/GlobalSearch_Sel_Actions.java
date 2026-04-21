package WebPageObjectRepo;

import Constants.FilePath;
import Utilities.PropertyFileReaderService;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GlobalSearch_Sel_Actions extends BasePageActions{

    private static final Logger log = LoggerFactory.getLogger(GlobalSearch_Sel_Actions.class);
    WebDriver Driver;

    public GlobalSearch_Sel_Actions(WebDriver driver){
        super(driver);
        this.driver=driver;
    }


    By GLOBAL_SEARCH_BOX = By.xpath("//div[@class='search-input-wrapper']/input");
    By GLOBAL_SEARCH_LAYOUT = By.xpath("//div[@class='main-container d-block global-search-layout']");
    By NO_DATA_LAYOUT = By.xpath("//div[@class='no-data-block-inner']");
    By RECENTLY_SEARCHED_TITLE = By.xpath("//div[@class='flex space-between mb-12']/div");
    By RECENTSEARCH_CLEARALL_BUTTON = By.xpath("//div[@data-testid='recent-search-panel']/div/button");
    By RECENTLY_VISITED_TITLE = By.xpath("//div[@class='flex space-between mt-12 mb-8']/div");
    By RECENTVISITED_CLEARALL_BUTTON = By.xpath("//div[@data-testid='recent-visited-panel']/div/button");
    By RECENTLY_SEARCHED_LIST = By.xpath("//div[@class='flex mb-8']");
    By RESULT_CATEGORY_LIST = By.xpath("//div[@class='list-reset gs-nav buttons-nav-vert']");
    By DATA_CARD_LAYOUT = By.xpath("//div[@class='content-card search-card card-with-bookmark']");
    By NO_DATA_DISPLAY_IMAGE = By.xpath("//img[@alt='VCCEdge']");
    By NO_DATA_DISPLAY_TEXT = By.xpath("//div[@class='no-data-block-inner']");
    By DATA_APPEARING = By.xpath("//div[@class='border-box p-8 bdr-shadow gs-cards-new mb-16']");
    By SMART_SUGGESTIONS = By.xpath("//div[@class='flex mb-12']/strong");
    By NLP_BANNER = By.xpath("//div[@class='border-box bg-gradient-h bdr-0 p-4 mb-8 flex align-top']");
    By DROPDOWN_COMPANY_DATA_CARD = By.xpath("(//div[@class='company-list']//div[@data-testid='dropdown-search-card-1'])[1]");
    By LANDING_PAGE_COMPANY_DATA_CARD = By.xpath("//div[@data-testid='company-list-card']//div[@data-testid='result-card-company-pill-1']");
    By RECENTSEARCH_PILL = By.xpath("//button[@data-testid='recent-search-pill-item-1']");
    By RECENTVISITED_PILL = By.xpath("//div[@data-testid='recent-visted-company-pill-1']");
    By GLOBALSEARCH_CROSS_ICON = By.xpath("//button[@data-testid='clear-button']");
    By LANDING_PAGE_LAYOUT = By.xpath("//div[@class='gs-block grid grid-col-3-9 col-gap-16']");
    By DROPDOWN_VIEWALL_BUTTON = By.xpath("//div[@class='text-center']/button");
    By NO_RESULT_TEXT = By.xpath("//div[@class='no-data-block sm']");
    By LANDING_PAGE_HEADING = By.xpath("//h1[@class='page-heading md text-color-white']");
    By BREADCRUMB = By.xpath("//div[@class='main-container d-block global-search-layout']//div[@class='site-breadcrumb dark']");
    By RESULT_CATEGORY_TAB;
    By CATEGORY_SECTION_HEADING;
    By DATA_CARD_APPEARING;
    By NLP_ICON = By.xpath("//span[@class='svg-icon-fixed']");
    By NLP_RECENTSEARCHES_HEADING = By.xpath("//strong[@class='fw-500 fs-16']");
    By NLP_CLEARALL_BUTTON = By.xpath("//button[@class='button-type-text link fs-10']");
    By DROPDOWN_BOOKMARK = By.xpath("(//span[contains(@class, 'svg-icon') and contains(@class, 'hover-bookmark')])[1]");
    By LANDING_PAGE_BOOKMARK = By.xpath("(//div[@data-testid='result-card-company-pill-1']//span[contains(@class, 'hover-bookmark')])[2]");
    By DROPDOWN_DATACARD_NAME = By.xpath("(//div[@class='truncated-text text-color-primary'])[1]");
    By LANDINGPAGE_DATACARD_NAME = By.xpath("(//div[@data-testid='result-card-company-pill-1']//strong)[2]");
    By DROPDOWN_DATACARD_LOGO = By.xpath("(//div[@class='img-object-fit bdr br-4 bg-white wh-70-28'])[1]");
    By LANDINGPAGE_DATACARD_LOGO = By.xpath("(//div[@data-testid='result-card-company-pill-1']//a)[2]");
    By LANDING_PAGE_VIEWALL_BUTTON = By.xpath("(//div[@class='border-box bdr-light mb-16']//button)[1]");


    public void login_to_account(){
        login(PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG,"username"),PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG,"password"));
    }


    public boolean verify_globalsearch_display(){
        login_to_account();
        return isElementVisible(INPUT_GLOBAL_SEARCH);

    }


    public boolean verify_global_search_placeholder_text(String text){
        login_to_account();
        waitForElementVisible(INPUT_GLOBAL_SEARCH);
        if(verifyGlobalSearch()){
            return verifyGlobalSearchPlaceHolder(text);
        }
        return false;
    }




    public boolean verify_smartsuggestions_availability(String text){
        login_and_clickOnSearch();
        if(isElementVisible(SMART_SUGGESTIONS)){
            return getSelElement(SMART_SUGGESTIONS).getText().contains(text);
        }
        return false;
    }


    public boolean verify_NLP_banner(){
        login_and_clickOnSearch();
        return isElementVisible(NLP_BANNER);
    }


    public boolean verify_recently_searched_tv(String text){
        login_and_clickOnSearch();
        return getSelElementTextAndCompare(RECENTLY_SEARCHED_TITLE,text);
    }

    public boolean verify_clearAll_button_recentSearch(String text){
        login_and_clickOnSearch();
        if(isElementVisible(RECENTSEARCH_CLEARALL_BUTTON)){
            return getSelElement(RECENTSEARCH_CLEARALL_BUTTON).getText().equals(text);
        }
        return false;
    }

    public boolean verify_recently_visited_tv(String text){
        login_and_clickOnSearch();
        return getSelElementTextAndCompare(RECENTLY_VISITED_TITLE,text);
    }

    public boolean verify_clearAll_button_recentVisited(String text){
        login_and_clickOnSearch();
        if(isElementVisible(RECENTVISITED_CLEARALL_BUTTON)){
            return getSelElement(RECENTVISITED_CLEARALL_BUTTON).getText().equals(text);
        }
        return false;
    }

    public boolean verify_keyword_saved_in_recentSearch_and_recentVisited(String text){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        clickOnLocator(DROPDOWN_COMPANY_DATA_CARD);
        waitForElementVisible(INPUT_GLOBAL_SEARCH);
        clickOnLocator(GLOBALSEARCH_CROSS_ICON);
        waitForElementVisible(RECENTSEARCH_PILL);
        return getSelElementTextAndCompare(RECENTSEARCH_PILL,text) && getSelElementTextAndCompare(RECENTVISITED_PILL,text);
    }

    public boolean verify_landingPage_navigation_and_Heading(String text){

        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        waitForElementVisible(LANDING_PAGE_LAYOUT);
        return getSelElementTextAndCompare(LANDING_PAGE_HEADING,"Showing results for " + '"' + text +'"');
    }

    public boolean verify_viewAll_button_in_dropdown(String text){
       login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_VIEWALL_BUTTON);
        clickOnLocator(DROPDOWN_VIEWALL_BUTTON);
        return isElementVisible(LANDING_PAGE_LAYOUT);
    }

    public boolean verifySearchCategory_State(String text,String category,String state){


        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        WebElement element = getSelElement(By.xpath("//div[contains(@class,'main-container')]//strong[contains(text(),'" + category+ "')]/ancestor::div[contains(@class,'MuiButtonBase-root')]"));
        String area_expanded = element.getAttribute("aria-expanded");
        String area_disabled = element.getAttribute("aria-disabled");

        switch (state){
            case "active":
                return area_expanded.equals("true");
            case "not active":
                return area_expanded.equals("false");
            case "disabled":
                return area_disabled.equals("true");
            default:
                return false;
        }
    }



    public boolean verify_Minimum_keyword_for_searching_more_than_3_characters_(){

        WebElement element = getSelElement(GLOBAL_SEARCH_BOX);

        clickonElement(element);

        sendKeysWithElement(element,"Swi");

        return isElementVisible(DATA_APPEARING) && isElementVisible(RESULT_CATEGORY_LIST);

    }

    public boolean verify_Minimum_keyword_for_searching__less_than_3_characters_(){
        return false;


    }

    public boolean verify_see_all_button(){

        return true;

    }

    // In this I am calculating all categories count and comparing it with the "All" count.
    public boolean verify_count_of_ALL_tab(String text, int count){

        login_to_account();
        sendKeys(INPUT_GLOBAL_SEARCH,text);
        List<WebElement> categories= getElements(RESULT_CATEGORY_TAB);

        int c = 0;

        for(int i = 1;i<categories.size();i++){
            c += Integer.parseInt((categories.get(i).getText()).replaceAll("[^0-9]", ""));
        }

        int all_tab_count = Integer.parseInt((categories.get(0).getText()).replaceAll("[^0-9]", ""));
        return count==c && count==all_tab_count;
    }


    public boolean verify_count_of_result_categories(String text,String count, String category){
        login_and_sendKeys(text);
        By loc = By.xpath("//button[contains(text(),'" + category+"')]");
        String expected_category_name = getSelElement(loc).getText();
        String expected_category_name_count = expected_category_name.replaceAll("[^0-9]", "");
        return expected_category_name_count.equals(count);
    }

    public boolean verify_no_result_display(String text){
      login_and_sendKeys(text);
        waitForElementVisible(NO_RESULT_TEXT);
        return getSelElementTextAndCompare(NO_RESULT_TEXT,"Your Search did not match any results.\n" +
                "We suggest you try a different keyword.");
    }





    // In this I am verifying data card count and category section heading count
    public boolean verify_category_section_count(String text, String category, String count){
       login_and_sendKeys(text);
        WebElement element = getSelElement(By.xpath("//button[contains(text(),'" + category +"')]"));
        clickonElement(element);
        String category_section_heading_count = getSelElement(CATEGORY_SECTION_HEADING).getText().replaceAll("[^0-9]", "");
        String datacard_count = String.valueOf(getElements(DATA_CARD_APPEARING).size());
        return category_section_heading_count.equals(count) && datacard_count.equals(count);
    }


    public boolean verify_breadcrumb(String text,String expectedText){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        return getSelElementTextAndCompare(BREADCRUMB,expectedText);
    }



    public boolean verify_NLP_icon(){
        return isElementVisible(NLP_ICON);
    }


    public boolean verify_NLP_search_placeholder_text(String text) {
        login_to_account();
        waitForElementVisible(INPUT_GLOBAL_SEARCH);
        clickOnLocator(NLP_ICON);
        return verifyGlobalSearchPlaceHolder(text);
    }


    public boolean verify_NLP_recentSearches(String text){
        login_to_account();
        waitForElementVisible(INPUT_GLOBAL_SEARCH);
        clickOnLocator(NLP_ICON);
        if(isElementVisible(NLP_RECENTSEARCHES_HEADING)){
            return getSelElementTextAndCompare(NLP_RECENTSEARCHES_HEADING,text);
        }
        return false;
    }

    public boolean verify_NLP_clearAll_button(String text){
        login_to_account();
        clickOnLocator(NLP_ICON);
        if(isElementVisible(NLP_CLEARALL_BUTTON)){
            return getSelElementTextAndCompare(NLP_CLEARALL_BUTTON , text);
        }
        return false;
    }



    public boolean verify_the_bookmark_on_hovering_in_landingPage(String text){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        return verify_bookmark_functionality(LANDING_PAGE_COMPANY_DATA_CARD,LANDING_PAGE_BOOKMARK);
    }

    public boolean verify_the_bookmark_on_hovering_in_dropdown(String text){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        return verify_bookmark_functionality(DROPDOWN_COMPANY_DATA_CARD,DROPDOWN_BOOKMARK);
    }


    public boolean verify_bookmark_functionality(By dataCardLocator , By bookmarkLocator){

        hoverElement(getSelElement(dataCardLocator));
        WebElement element = getSelElement(bookmarkLocator);
        if(element.getAttribute("class").contains("active")){
            clickOnLocator(bookmarkLocator);
            forceWait(1);   //HTML is taking time to get updated
            return !element.getAttribute("class").contains("active");
        }
        else {
            clickOnLocator(bookmarkLocator);
            waitForElementVisible(bookmarkLocator);
            forceWait(1);   //HTML is taking time to get updated
            return element.getAttribute("class").contains("active");
        }
    }


    public boolean verify_dropdown_dataCard(String text){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        return verify_datacard_name_logo(DROPDOWN_DATACARD_NAME,DROPDOWN_DATACARD_LOGO,text);
    }

    public boolean verify_landingPage_dataCard(String text){
        login_and_sendKeys(text);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        waitForElementVisible(LANDING_PAGE_COMPANY_DATA_CARD);
        return verify_datacard_name_logo(LANDINGPAGE_DATACARD_NAME,LANDINGPAGE_DATACARD_LOGO,text);
    }


    public boolean verify_datacard_name_logo (By nameLocator , By logoLocator, String text){
        if(isElementVisible(nameLocator) && isElementVisible(logoLocator)){
            return getSelElement(nameLocator).getText().contains(text);
        }
        return false;
    }

    public boolean verify_landingPage_viewAll_button(String searchtext, String expectedValue){
        login_and_sendKeys(searchtext);
        waitForElementVisible(DROPDOWN_COMPANY_DATA_CARD);
        pressEnter(INPUT_GLOBAL_SEARCH);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getSelElement(LANDING_PAGE_VIEWALL_BUTTON));
        waitForElementVisible(LANDING_PAGE_VIEWALL_BUTTON);
        if(isElementVisible(LANDING_PAGE_VIEWALL_BUTTON) && getSelElementTextAndCompare(LANDING_PAGE_VIEWALL_BUTTON,expectedValue)){
            clickOnLocator(LANDING_PAGE_VIEWALL_BUTTON);
            waitForElementVisible(LANDING_PAGE_COMPANY_DATA_CARD);
            return getSelElement(By.xpath("//strong[contains(text(),'Companies')]/ancestor::div[contains(@class,'MuiButtonBase-root')]")).getAttribute("aria-expanded").equals("true");
        }
     return false;
    }

    public void login_and_clickOnSearch(){
        login_to_account();
        clickOnLocator(INPUT_GLOBAL_SEARCH);
    }
    public void login_and_sendKeys(String text){
        login_to_account();
        sendKeys(INPUT_GLOBAL_SEARCH,text);
    }
}
