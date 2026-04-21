package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
//import org.openqa.selenium.WebElement;

public class HomePage_Sel_Actions extends BasePageActions {
    public HomePage_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By HOMEPAGE_HEADER=By.cssSelector("div.top-nav.visible");
    By HOME_PAGE_FOOTER=By.cssSelector("div.lastpage-email-input");


    By LOGO_IMAGE=By.cssSelector("img[alt='VCCEdge']") ;

    // SECTION FOR MENU BAR


    By MENU_COMPANY;
    By MENU_OFFERINGS;
    By MENU_CUSTOMERS;
    By MENU_PRICING;
    By MENU_WHATS_NEW;
    By MENU_LOGIN=By.cssSelector(".login-btn");
    By MENU_BUTTON_BOOK_A_DEMO=By.cssSelector("button.demo-btn");

    //Popup form on clicking book a demo

    By POPUP_FORM_BOOK_A_DEMO = By.cssSelector("div.popup-content");

//IMAGE BELOW THE MENU BAR SECTION

    By IMAGE_BELOW_MENU_BAR= By.xpath("//div[@class='page1-img']/img");


    //SECTION FOR HEADING BELOW THE LOGO

    By MAIN_HEADING_BELOW_LOGO=By.xpath("//div[@class='p1-div']");
    By SUBHEADING_MAIN_HEADING_BELOW_LOGO=By.xpath("//div[@class='p2-div']");


    // SECTION FOR BUTTON

    By LABEL_Companies=By.cssSelector(".stats");
    By Count_Above_Label;





//HEADING ABOVE THE OTHER COMAPNIES LOGO AND THE LOGO OF OTHER COMAINES SECTION

    By SUBHEADING_ABOVE_OTHER_COMPANIES_LOGO=By.cssSelector(".page2-top-text");

    By OTHER_COMPANIES_LOGOS;


    // PREMIUM RESEARCH AND INSIGHTS SECTION


    By BUTTON_PREMIUM_RESEARCH_AND_INSIGHTS=By.cssSelector("div.btn-like-div-content");
    By BUTTON_PREMIUM_RESEARCH_AND_INSIGHTS_IMAGE=By.xpath("//img[@src='/_next/static/media/sliderimg2.a5179ea1.png' and @alt='Slider Image 23']");
    By MAIN_FIRST_HEADING_PREMIUM_RESEARCH_AND_INSIGHTS=By.cssSelector("div.data-div");
    By MAIN_SECOND_HEADING_PREMIUM_RESEARCH_AND_INSIGHTS=By.cssSelector("div.gradient-text");
    By PARAGRAPH_BELOW_MAIN_HEADING_PREMIUM_RESEARCH_AND_INSIGHTS=By.cssSelector("div.text-container");
    By BUTTON_HOW_WE_CAN_HELP=By.cssSelector("div.help-button");

//    By Image;


    // IMAGE BELOW PREMIUM INSIGHTS

    By IMAGE_BELOW_PREMIUM_RESEARCH_AND_INSIGHTS=By.cssSelector("div.page3");


    // count of label and figure
    By STAT_ITEM_COUNT = By.cssSelector(".count");
    By STAT_ITEM_LABEL = By.cssSelector(".type-comp");
    By LABELS_AND_COUNT_OF_COMPANIES=By.cssSelector(".stat-item");

    // DESIGNED FOR YOU SECTION


    By BUTTON_DESIGNED_FOR_YOU=By.cssSelector("div.btn-like-div1");
    By MAIN_HEADING_DESIGNED_FOR_YOU=By.cssSelector("div.text-1");


    //IMAGES AND TEXT IN DESINED FOR YOU SECTION

    By DESIGNED_FOR_YOU_GRAPH_IMAGE;
    By DESIGNED_FOR_YOU_ARROW_IMAGE;
    By DESIGNED_FOR_YOU_IMAGE_HEADING;
    By DESIGNED_FOR_YOU_IMAGE_SUBHEADING;


    // REPORTS SECTION

    By BUTTON_REPORTS=By.cssSelector("div.report-btn-like-div");
    By BUTTON_SPEAK_TO_EXPERT_REPORT=By.cssSelector("div.reports-email-input button");
    By PLACEHOLDER_EMAIL_ADDRESS_REPORT=By.cssSelector("div.reports-email-input input[type='email']");
    By MAIN_HEADING_REPORTS_SECTION=By.cssSelector("div.p5t1");
    By SUBHEADING_MAIN_HEADING_REPORTS_SECTION=By.cssSelector("div.p5t2");
    By REPORT_SECTION_IMAGE=By.cssSelector("div.reportsMultipleIcons");


    // BELOW REPORT SECTION CONTENT

    By MAIN_HEADING_BELOW_REPORTS_SECTION=By.cssSelector(".r2t1");
    By DOWN_ARROW_BUTTON_BELOW_REPORTS_SECTION;
    By GRAPH_IMAGE_BELOW_REPORTS_SECTION;
    By TEXT_IN_IMAGE_BELOW_REPORTS_SECTION=By.cssSelector(".card-content");
    By RIGHT_ARROW_BUTTON_BELOW_REPORTS_SECTION=By.cssSelector("div.next-button");

    //carousel

    By CAROUSEL_SLIDER;


    // CLIENT SAY SECTION
    By WHAT_CLIENT_SAY_HEADING=By.cssSelector(".heading");
    By WHAT_CLIENT_SAY_LEFT_ARROW=By.xpath("(//div[@class='arrowIcon'])[1]");
    By WHAT_CLIENT_SAY_RIGHT_ARROW=By.cssSelector("div.arrowIcon svg rect[fill='#005DB5']");

    By IMAGE_WHAT_CLIENT_SAY= By.xpath("//div[@class='client-image']");
    By PARAGRAPH_IN_IMAGE_WHAT_CLIENT_SAY= By.cssSelector("comment-desc");
    By HEADING_IN_IMAGE_WHAT_CLIENT_SAY= By.cssSelector(".client-name");
    By SUBHEADING_HEADING_IN_IMAGE_WHAT_CLIENT_SAY=By.cssSelector(".client-position");


    // FOOTER CONTENT SECTION

    By MAIN_HEADING_FOOTER=By.cssSelector("div.hero-section-h1");
    By SUBHEADING_MAIN_HEADING_FOOTER=By.xpath("//p[contains(text(), 'VCCEdge’s powerhouse')]");
    By BUTTON_FOOTER_SPEAK_TO_OUR_EXPERT;
    By LABEL_SHARE_YOUR_BUISNESS_EMAIL_ADDRESS;

    By TEXT_HYPERLINKS_FOOTER=By.cssSelector("div.footer-heading");
    By ICON_HYPERLINKS_FOOTER=By.cssSelector("div.social-icons");




    //  check logo method

    public boolean checkLogo() {
        return isElementVisible(LOGO_IMAGE);
    }

    public boolean onClickingLoginRedirectToLoginPage(String expectedLoginPageTitle) {

        clickOnLocator(MENU_LOGIN);

        String actualTitle = getTitle();
        return actualTitle.equals(expectedLoginPageTitle);
    }


    public boolean verifyMenuBarOptionsVisibility(String optionName) {
        By option;
        switch (optionName) {
            case "Company":
                option = MENU_COMPANY;
                break;
            case "Offerings":
                option = MENU_OFFERINGS;
                break;
            case "Customers":
                option = MENU_CUSTOMERS;
                break;
            case "Pricing":
                option = MENU_PRICING;
                break;
            case "WhatsNew":
                option = MENU_WHATS_NEW;
                break;
            case "Login":
                option = MENU_LOGIN;
                break;
            case "ButtonBookDemo":
                option = MENU_BUTTON_BOOK_A_DEMO;
                break;
            default:
                option = null;
        }
        return isElementVisible(option);
    }

    public boolean verifyMultipleImagesOnHomePageVisibility(String HomepageImages) {
        By image;
        switch (HomepageImages) {

            case "Image_below_menu":
                image = IMAGE_BELOW_MENU_BAR;
                break;
            case "Image_below_premium_insights":
                image = IMAGE_BELOW_PREMIUM_RESEARCH_AND_INSIGHTS;
                break;


            default:
                image = null;
        }
        return isElementVisible(image);
    }


    public boolean checkHeaderVisibility() {
        if (!isElementVisible(HOMEPAGE_HEADER)) {
            return false;
        }
        return true;
    }


    public boolean checkFooterLinksVisibility() {
        if (!isElementVisible(HOME_PAGE_FOOTER)) {
            return false;
        }

        return true;
    }

    public boolean isMainHeadingBelowLogoVisible() {
        return isElementVisible(MAIN_HEADING_BELOW_LOGO);
    }

    public boolean isSubheadingMainHeadingBelowLogoVisible() {
        return isElementVisible(SUBHEADING_MAIN_HEADING_BELOW_LOGO);
    }

    // check label and count of companies

    public boolean verifyLabelsAndCountsVisibility() {
        // Locate the stats section
        WebElement statsSection = getSelElement(LABEL_Companies);

        // Find all stat items within the stats section
        List<WebElement> statItems = getElements(LABELS_AND_COUNT_OF_COMPANIES);

        for (WebElement item : statItems) {
            // Locate the count and label elements
            WebElement countElement = getSelSubElement(item, STAT_ITEM_COUNT);
            WebElement labelElement = getSelSubElement(item, STAT_ITEM_LABEL);

            if (!labelElement.isDisplayed() || !countElement.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public boolean isPopupFormVisibleBook_A_Demo() {
        clickOnLocator(MENU_BUTTON_BOOK_A_DEMO);  // Click the "Book a Demo" button
        waitForElementVisible(POPUP_FORM_BOOK_A_DEMO);   // Wait for the popup form to become visible
        return isElementVisible(POPUP_FORM_BOOK_A_DEMO); // Return true if the popup form is visible, false otherwise
    }

    public HashMap<String, String> getStatsCountsofCompaniesData() {
        HashMap<String, String> statsMap = new HashMap<>();



            List<WebElement> statLabels = getElements(STAT_ITEM_LABEL);
            List<WebElement> statCounts = getElements(STAT_ITEM_COUNT);


            if (statLabels.size() != statCounts.size()) {
                throw new IllegalStateException("Mismatch between number of labels and counts");
            }

            for (int i = 0; i < statLabels.size(); i++) {
                String label = statLabels.get(i).getText();
                String count = statCounts.get(i).getText();
                statsMap.put(label, count);
            }


        return statsMap;
    }

    public boolean verifySpecificCountsofCompaniesData(HashMap<String, String> expectedCountsMap) {
        // Wait for 3 seconds beacuase api is taking 2.5 seconds
        forceWait(3);

        HashMap<String, String> actualCountsMap = getStatsCountsofCompaniesData();


        if (expectedCountsMap == null || actualCountsMap == null) {
            return expectedCountsMap == actualCountsMap;
        }

        boolean areMapsEqual = expectedCountsMap.equals(actualCountsMap);

        return areMapsEqual;
    }



    public boolean validateDesignedForYouSection(String elementType) {
        By locator;
        switch (elementType) {
            case "Button":
                locator = BUTTON_DESIGNED_FOR_YOU;
                break;
            case "MainHeading":
                locator = MAIN_HEADING_DESIGNED_FOR_YOU;
                break;
            case "GraphImage":
                locator = DESIGNED_FOR_YOU_GRAPH_IMAGE;
                break;
            case "ArrowImage":
                locator = DESIGNED_FOR_YOU_ARROW_IMAGE;
                break;
            case "ImageHeading":
                locator = DESIGNED_FOR_YOU_IMAGE_HEADING;
                break;
            case "ImageSubheading":
                locator = DESIGNED_FOR_YOU_IMAGE_SUBHEADING;
                break;
            default:
                throw new IllegalArgumentException("Invalid element type: " + elementType);
        }
        return isElementVisible(locator);
    }
    public boolean checkSubheadingAboveOtherCompaniesLogoVisibility()
    {
        return  isElementVisible(SUBHEADING_MAIN_HEADING_BELOW_LOGO);
    }
    public boolean validatePremiumResearchAndInsightsSection(String elementType) {
        By locator;
        switch (elementType) {
            case "Button":
                locator = BUTTON_PREMIUM_RESEARCH_AND_INSIGHTS;
                break;
            case "MainHeading":
                locator = MAIN_FIRST_HEADING_PREMIUM_RESEARCH_AND_INSIGHTS;
                break;
            case "Paragraph":
                locator = PARAGRAPH_BELOW_MAIN_HEADING_PREMIUM_RESEARCH_AND_INSIGHTS;
                break;
            case "HelpButton":
                locator = BUTTON_HOW_WE_CAN_HELP;
                break;

            case "BUTTON_PREMIUM_RESEARCH_AND_INSIGHTS_IMAGE":
                locator = BUTTON_PREMIUM_RESEARCH_AND_INSIGHTS_IMAGE;
                break;

            case "Image_Below_Preminum":
                locator = IMAGE_BELOW_PREMIUM_RESEARCH_AND_INSIGHTS;
                break;
            default:
                throw new IllegalArgumentException("Invalid element type: " + elementType);
        }
        return isElementVisible(locator);
    }


    public boolean verifyLoginButtonTitleName(String expectedTitle) {
        clickOnLocator(MENU_LOGIN);
        String actualTitle = getTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifyBookADemoButtonTitleName(String expectedText)
    {
        String actualText = getSelElement(MENU_BUTTON_BOOK_A_DEMO).getText();
        return actualText.equals(expectedText);

    }

    public boolean footerLinksVisibility(String linkText, String pageTitle) {
        By linkLocator = By.linkText(linkText);
        WebElement linkElement = getSelElement(linkLocator);

        if (!isElementVisible(linkElement)) {
            return false;

        }
        clickOnLocator(linkLocator);
        if (!getTitle().equalsIgnoreCase(pageTitle)) {
            return false;
        }

        return true;
    }

    public boolean verifyClientSayArrowsVisibility() {
        boolean leftArrowVisible = isElementVisible(WHAT_CLIENT_SAY_LEFT_ARROW);
        boolean rightArrowVisible = isElementVisible(WHAT_CLIENT_SAY_RIGHT_ARROW);

        if (!leftArrowVisible && !rightArrowVisible) {
            return false;
        } else if (!leftArrowVisible || !rightArrowVisible) {
            return false;
        }
        return true;
    }

    public boolean isFooterHeadingVisible() {
        boolean headingVisible = isElementVisible(MAIN_HEADING_FOOTER);
        return headingVisible;
    }

    public boolean isFooterSubHeadingVisible() {
        boolean subheadingVisible = isElementVisible(SUBHEADING_MAIN_HEADING_FOOTER);
        return subheadingVisible;
    }


    public boolean verifyReportSectionVisibility(String sectionElement) {
        By option;

        switch (sectionElement) {


            case "Report Button":
                option = BUTTON_REPORTS;
                break;

            case "Main Heading Report":
                option = MAIN_HEADING_REPORTS_SECTION;
                break;

            case "Sub Heading Report":
                option = SUBHEADING_MAIN_HEADING_REPORTS_SECTION;
                break;

            case "Report Button Speak to Expert":
                option = BUTTON_SPEAK_TO_EXPERT_REPORT;
                break;

            case "Email Address Report Section":
                option = PLACEHOLDER_EMAIL_ADDRESS_REPORT;
                break;

            case "Image Report Section":
                option = REPORT_SECTION_IMAGE;
                break;


            default:
                throw new IllegalArgumentException("Invalid element type: " + sectionElement);
        }

        return isElementVisible(option);
    }

    public boolean areFooterSocialMediaIconsVisible() {
        // Get a list of all social media icon link elements in the footer
       List<WebElement> socialMediaIconLinks=getElements(ICON_HYPERLINKS_FOOTER);
        for (WebElement iconLink : socialMediaIconLinks) {
            if (!iconLink.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public boolean areFooterTextLinksVisible() {
        // Replace FOOTER_TEXT_LINKS with the actual locator for footer text links
        By FOOTER_TEXT_LINKS = By.cssSelector("div.footer-column"); // Example selector

        List<WebElement> footerTextLinks = getElements(TEXT_HYPERLINKS_FOOTER);

        // Check if the list is not empty and all elements are visible
        if (footerTextLinks.isEmpty()) {
            return false; // No links found
        }

        for (WebElement link : footerTextLinks) {
            if (!link.isDisplayed()) {
                return false;
            }
        }

        return true;
    }



}

