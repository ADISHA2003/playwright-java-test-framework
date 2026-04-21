package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

public class keyDevelopments_Sel_Actions extends BasePageActions{

    WebDriver driver ;
    public keyDevelopments_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By BUTTON_FILTER = By.xpath("//button[@class=\'button primary\' and contains(text(),\'Filter\')]");
    By BUTTON_EXPORT = By.xpath("//button[@class=\'button primary\' and contains(text(),\'Export\')]");
    By BUTTON_NEWSLETTER = By.xpath("//button[@class=\'button primary\' and contains(text(),\'NewsLetter\')]");
    By BUTTON_DATEPICKER = By.cssSelector(".search-n-filters .button.button.secondary");
    By INPUT_SEARCH_FOR_KEYWORD = By.cssSelector(".search-n-filters .search-input-wrapper input");
    By TAB_ALL_KEY_DEVELOPMENTS = By.xpath("//button[contains(text(),'All')]");
    By TAB_ACTIVE;
    By TAB_MY_FEED = By.xpath("//button[contains(text(),'My Feed')]");
    By TAB_READING_LIST = By.xpath("//button[contains(text(),'Reading List')]");
    By ICON_BOOKMARK_LIST = By.cssSelector(".bookmark-icon");
    By ICON_SHARE;
    By LIST_CONTENT_CARD= By.cssSelector(".content-card.search-card");
    By LOGO_CONTENT_CARD_LIST = By.cssSelector("card-image");
    By HEADING_CONTENT_CARD_LIST = By.cssSelector("box-title");
    By DESCRIPTION_CONTENT_CARD_LIST = By.cssSelector(".card-textarea p");
    By TAGS_CONTENT_CARD_LIST = By.cssSelector(".card-textarea .tags");
    By SIDEBAR_RIGHT_ANALYTICS = By.cssSelector(".sidebar-content");
    By BUTTON_CROSS_SEARCH_OVERLAY = By.cssSelector("close-btn");
    By BUTTON_SAVE_SEARCH_OVERLAY = By.xpath("//button[@class=\"button primary sm\" and contains(text(),'Save')]");
    By BUTTON_RESET_SEARCH_OBERLAY = By.xpath("//button[@class=\"button secondary sm\" and contains(text(),'Reset')]");
    By TITLE_SEARCH_OVERLAY = By.cssSelector(".filter-window .box-title");
    By TITLE_NEWSLETTER_OVERLAY= By.cssSelector(".MuiPopover-paper .border-box");
    By TOGGLE_NEWSLETTER_OVERLAY = By.cssSelector(".PrivateSwitchBase-input");
    By TEXT_TOGGLE_NEWSLETTER_OVERLAY = By.cssSelector("//div[@class='custom-switch']/span[2]");
    By LABEL_EMAIL_NEWSLETTER_OVERLAY  = By.xpath("//div[contains(@classiPopover-paper')]/div/div[2]/label");
    By LABEL_ADD_EMAIL_NEWSLETTER_OVERLAY = By.xpath("//div[contains(@class,'MuiPopover-paper')]/div/div[2]/label/span");
    By LABEL_TIME_NEWSLETTER_OVERLAY = By.xpath("//div[contains(@class,'MuiPopover-paper')]/div/div[3]/div[1]//label");
    By LABEL_FREQUENCY_NEWSLETTER_OVERLAY = By.xpath("//div[contains(@class,'MuiPopover-paper')]/div/div[3]/div[2]//label");
    By SELECT_TIME_HOUR_NEWSLETTER_OVERLAY = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[3]//select)[1]");
    By SELECT_MERIDIEN_NEWSLETTER_OVERLAY = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[3]//select)[2]");
    By SELECT_FREQUENCY_NEWSLETTER_OVERLAY = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[3]//select)[3]");
    By LABEL_DATA_TO_EXPORT = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[1]//label)[1]");
    By LABEL_ALL_DATA_TO_EXPORT = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[1]//label)[2]");
    By LABEL_FIRST50_TO_EXPORT = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[1]//label)[3]");
    By LABEL_FIRST100_TO_EXPORT = By.xpath("(//div[contains(@class,'MuiPopover-paper')]/div/div[1]//label)[4]");
    By LABEL_FORMAT_EXPORT = By.xpath("//div[contains(@class,'MuiPopover-paper')]/div/div[2]//label");
    By SELECT_FORMMAT_EXPORT = By.xpath("//div[contains(@class,'MuiPopover-paper')]/div/div[2]//select");
    By LIST_TABS_TITLE = By.cssSelector(".main-container.d-block .tab-items li button");



    public boolean verifyActiveTab(String tabName){
           boolean active = false;
           int times = 5;
            By tab;
            switch (tabName.toLowerCase()){
                case "all key developments" : tab = TAB_ALL_KEY_DEVELOPMENTS;break;
                case "my feed": tab = TAB_MY_FEED; break;
                default: return false;
        }
        WebElement requiredTabElement = getSelSubElement(tab, By.xpath("./.."));

        while (!active && times!=0) {
            times--;
            if(requiredTabElement.getAttribute("class").contains("active")){
                active=true;break;
            }
          }
        return active;
        }

    public void waitForCountToAppear(){
        waitForElementVisible(TAB_ALL_KEY_DEVELOPMENTS);
        String count = "0";
        int times = 5;
        while(count.equals("0")&& times!=0) {
            forceWait(1);
            times--;
            count = getSelElement(TAB_ALL_KEY_DEVELOPMENTS).getText().split("\\(")[1].split("\\)")[0];

        }
    }



    public boolean verifyCountAlongTabTitle(){
        waitForCountToAppear();
        List<WebElement> tabs = driver.findElements(LIST_TABS_TITLE);
        Boolean countPresent = false;
        for(WebElement tab: tabs){
           String count = tab.getText().split("\\(")[1].split("\\)")[0];
            if(count.matches(".*\\d.*")&& !(count.equals("0"))){
                countPresent = true;
            }
            if(!countPresent) return false;
        }
        return countPresent;

    }

    public boolean clickOnKeyDevTab(String tabName){
        By tab;
        switch (tabName.toLowerCase()){
            case "all key developments": tab = TAB_ALL_KEY_DEVELOPMENTS;break;
            case "my feed": tab = TAB_MY_FEED;break;
            default:return false;
        }
        return clickonElement(getSelElement(tab));
        
    }

    public boolean verifyGS_back_info_question_userProfile(){
            if(verifyGlobalSearch() && verifySearchNavOptions()){
                return true;
            }else return false;
    }

    public boolean verifyFilterExportButton(){
        clickOnLocator(TAB_ALL_KEY_DEVELOPMENTS);
        forceWait(7);
//        waitForElementVisible(BUTTON_FILTER,5);
        if(isElementVisible(BUTTON_FILTER) && isElementVisible(BUTTON_EXPORT)) {
            clickOnLocator(TAB_MY_FEED);
            waitForElementVisible(BUTTON_FILTER,5);
            if (isElementVisible(BUTTON_FILTER) && isElementVisible(BUTTON_EXPORT)) {
                clickOnLocator(TAB_READING_LIST);
                waitForElementVisible(BUTTON_FILTER,5);
                if (isElementVisible(BUTTON_FILTER) && isElementVisible(BUTTON_EXPORT)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifySearchTabOnAllTabs(){
        clickOnLocator(TAB_ALL_KEY_DEVELOPMENTS);
        if(isElementVisible(INPUT_SEARCH_FOR_KEYWORD)){
            clickOnLocator(TAB_MY_FEED);
            if(isElementVisible(INPUT_SEARCH_FOR_KEYWORD)){
                clickOnLocator(TAB_READING_LIST);
                if(isElementVisible(INPUT_SEARCH_FOR_KEYWORD)){
                    return true;} }}
        return false;
    }


    public boolean verifySearchKeyWordPlaceholder(String text) {
        return verifyPlaceHolderText(INPUT_SEARCH_FOR_KEYWORD, text);

    }

    public boolean verifyDatePickerIconOnAllTabs(){
        clickOnLocator(TAB_ALL_KEY_DEVELOPMENTS);
        if(isElementVisible(BUTTON_DATEPICKER)){
            clickOnLocator(TAB_MY_FEED);
            if(isElementVisible(BUTTON_DATEPICKER)){
                clickOnLocator(TAB_READING_LIST);
                if(isElementVisible(BUTTON_DATEPICKER)){
                    return true;} }}
        return false;
    }

    public boolean verifyKeyDevelopmentsDataCardFormat(){
        WebElement datacard_1 = getDataCard(0);
        if(isElementVisible(getSelSubElement(datacard_1,LOGO_CONTENT_CARD_LIST))){
            if(isElementVisible(getSelSubElement(datacard_1,HEADING_CONTENT_CARD_LIST))){
                if(isElementVisible(getSelSubElement(datacard_1,DESCRIPTION_CONTENT_CARD_LIST))){
                    if(isElementVisible(getSelSubElement(datacard_1,TAGS_CONTENT_CARD_LIST))){
                        return true;
                    }
                }
            }
        }return false;
    }

    public WebElement getDataCard(int index){
        return getElements(LIST_CONTENT_CARD).get(index);
    }

    public boolean verifyKeyDevelopmentsDataCardHaveValidDataPoints(){
        WebElement datacard_1 = getDataCard(0);
        return getSelSubElementS(datacard_1,TAGS_CONTENT_CARD_LIST).size()>0;
    }

    public boolean verifyBookmarkAndShareIcon(){
        return isElementVisible(ICON_BOOKMARK_LIST);
    }
}
