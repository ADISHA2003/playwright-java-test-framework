package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.sound.midi.SysexMessage;

public class CompAnalysisScreen2_Sel_Actions extends BasePageActions{

    WebDriver driver;

    public CompAnalysisScreen2_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By COMPETITOR_ANALYSIS_HEADING = By.cssSelector("h1.page-heading.md.mb-0.mr-16");
    By COMPANY_IN_DRAFT_BUTTON = By.cssSelector("button.button.info-light.no-hand-cursor.sm.br-2.mt-4");
    By COMPANY_LOGO = By.xpath("//div[@class='shrink-0 br-4 mr-12 ca-img']");
    By COMPANY_NAME = By.cssSelector("h2#company-name.mb-0.box-title.text-eclipse");
    By RESULT_PREVIEW_HEADING = By.cssSelector("h3.box-title.mb-0");
    By SAVE_VIEW_ALL_BUTTON = By.cssSelector("button.button.primary.sm.br-2");
    By RESULT_PREVIEW_DISPLAY = By.xpath("//div[@class='sidebar-content pt-0 ca-results-preview']");
    By RESET_TO_DEFAULT_BUTTON = By.cssSelector("button.button.secondary");
    By APPLY_FILTER_BUTTON = By.cssSelector("button.button.primary");



    public boolean verify_comp_analysis_screen2_title(String text){

        forceWait(1);    // without forcewait gettitle() is giving NULL value
        String expectedtext = getTitle();

        return expectedtext.equals("Competitive Analysis - " + text + " | VCCEdge");
    }


    public boolean verify_draft_state_icon_company(String text){

        if(isElementVisible(COMPANY_IN_DRAFT_BUTTON)){

            return getSelElementTextAndCompare(COMPANY_IN_DRAFT_BUTTON, text);
        }

        return false;

    }


    public boolean verify_company_name(String text){

        if(isElementVisible(COMPANY_NAME)){

            return getSelElementTextAndCompare(COMPANY_NAME , text);
        }
        return false;
    }


    public boolean verify_company_logo(){

        return isElementVisible(COMPANY_LOGO);

    }


    public boolean verify_competitor_analysis_heading(String text){

        if(isElementVisible(COMPETITOR_ANALYSIS_HEADING)){

            return getSelElementTextAndCompare(COMPETITOR_ANALYSIS_HEADING, text);
        }
        return false;
    }


    public boolean verify_result_preview_heading(String text, String count){

        if(isElementVisible(RESULT_PREVIEW_HEADING)){

            forceWait(1);  //Without forcewait , it is taking count as 0
            String expectedtext = text + " (" + count +")";

            return getSelElementTextAndCompare(RESULT_PREVIEW_HEADING, expectedtext);
        }
        return false;
    }


    public boolean verify_save_view_all_button(String text){

        if(isElementVisible(SAVE_VIEW_ALL_BUTTON)){

            return getSelElementTextAndCompare(SAVE_VIEW_ALL_BUTTON, text);
        }
        return false;
    }


    public boolean verify_apply_filter_button(String text){

        if(isElementVisible(APPLY_FILTER_BUTTON)){

            return getSelElementTextAndCompare(APPLY_FILTER_BUTTON , text);
        }
        return false;
    }


    public boolean verify_reset_to_default_button(String text){

        if(isElementVisible(RESET_TO_DEFAULT_BUTTON)){

            return getSelElementTextAndCompare(RESET_TO_DEFAULT_BUTTON, text);
        }
        return false;
    }

    public boolean clickOnSaveAndViewAll(){
        refreshPage();
        return clickOnLocator(SAVE_VIEW_ALL_BUTTON);
    }




}
