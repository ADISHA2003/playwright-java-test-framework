package PlaywrightPageObject;



import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Global_Search_playwright_actions extends BasePageActions{

    public Global_Search_playwright_actions(Page page) {
        super(page);
    }

    private Locator INPUT_GLOBAL_SEARCH =page.locator("input[placeholder='Search with name, website, CIN, DIN or SC code']");
    private Locator INPUT_NLP_SEARCH =page.locator("//input[@data-testid=\"search-input\"]");
    private Locator GLOBAL_SEARCH_BOX               = page.locator("//div[@class='search-input-wrapper']/input");
    private Locator GLOBAL_SEARCH_LAYOUT            = page.locator("//div[@class='main-container d-block global-search-layout']");
    private Locator NO_DATA_LAYOUT                  = page.locator("//div[@class='no-data-block-inner']");
    private Locator RECENTLY_SEARCHED_TITLE         = page.locator("//div[text()='Recent Searches']");
    private Locator RECENTSEARCH_CLEARALL_BUTTON    = page.locator("//div[@data-testid='recent-search-panel']/div/button");
    private Locator RECENTLY_VISITED_TITLE          = page.locator("//div[@class='flex space-between mt-12 mb-8']/div");
    private Locator RECENTVISITED_CLEARALL_BUTTON   = page.locator("//div[@data-testid='recent-visited-panel']/div/button");
    private Locator RECENTLY_SEARCHED_LIST          = page.locator("//div[@class='flex mb-8']");
    private Locator RESULT_CATEGORY_LIST            = page.locator("//div[@class='list-reset gs-nav buttons-nav-vert']");
    private Locator DATA_CARD_LAYOUT                = page.locator("//div[@class='content-card search-card card-with-bookmark']");
    private Locator NO_DATA_DISPLAY_IMAGE           = page.locator("//img[@alt='VCCEdge']");
    private Locator NO_DATA_DISPLAY_TEXT            = page.locator("//div[@class='no-data-block-inner']");
    private Locator DATA_APPEARING                  = page.locator("//div[@class='border-box p-8 bdr-shadow gs-cards-new mb-16']");
    private Locator SMART_SUGGESTIONS               = page.locator("//div[@class='flex mb-12']/strong");
    private Locator NLP_BANNER                      = page.locator("//div[@class='border-box bg-gradient-h bdr-0 p-4 mb-8 flex align-top']");
    private Locator DROPDOWN_COMPANY_DATA_CARD      = page.locator("//div[@class='company-list']//div[@data-testid='result-card-company-pill-1']");
    private Locator LANDING_PAGE_COMPANY_DATA_CARD  = page.locator("div.company-list div[data-testid='dropdown-search-card-1']");
    private Locator RECENTSEARCH_PILL               = page.locator("//button[@data-testid='recent-search-pill-item-1']");
    private Locator RECENTVISITED_PILL              = page.locator("//div[@id='company-name-result-1']");
    private Locator GLOBALSEARCH_CROSS_ICON         = page.locator("//button[@data-testid='clear-button']");
    private Locator LANDING_PAGE_LAYOUT             = page.locator("//div[contains(@class,'gs-block') and contains(@class,'grid-col-3-9')]");
    private Locator DROPDOWN_VIEWALL_BUTTON         = page.locator("//button[@class='button-type-text faded-arrow cta fs-12']");
    private Locator NO_RESULT_TEXT                  = page.locator("//div[contains(@class,'no-data-block-inner')]");
    private Locator LANDING_PAGE_HEADING            = page.locator("//h1[@class='page-heading md text-color-white mb-0']");
    private Locator BREADCRUMB                      = page.locator("//div[contains(@class,'site-breadcrumb')]");
    private Locator NLP_ICON                        = page.locator("//button[@class='ai-search']/span[@class='svg-icon-fixed']");
    private Locator NLP_RECENTSEARCHES_HEADING      = page.locator("//strong[@class='fw-500 fs-16']");
    private Locator NLP_CLEARALL_BUTTON             = page.locator("//button[@class='button-type-text link fs-10']");
    private Locator DROPDOWN_BOOKMARK               = page.locator("(//span[contains(@class, 'svg-icon') and contains(@class, 'hover-bookmark')])[1]");
    private Locator LANDING_PAGE_BOOKMARK           = page.locator("(//div[@data-testid='result-card-company-pill-1']//span[contains(@class, 'hover-bookmark')])[2]");
    private Locator DROPDOWN_DATACARD_NAME          = page.locator("(//div[contains(@class,'list-hover') and contains(@class,'cursor-hand')])[1]");
    
    private Locator DROPDOWN_DATACARD_LOGO          = page.locator("(//div[@class='img-object-fit bdr br-4 bg-white wh-70-28'])[1]");
    private Locator LANDING_PAGE_VIEWALL_BUTTON     = page.locator("(//div[@class='border-box bdr-light mb-16']//button)[1]");
    
    private Locator RESULT_CATEGORY_TAB;
    private Locator CATEGORY_SECTION_HEADING;
    private Locator DATA_CARD_APPEARING;
  
    public boolean verifyGlobalSearchDisplay() {
        return (INPUT_GLOBAL_SEARCH).isVisible();
    }

    public void whenISearchForEntity(String entityName){
        Locator searchBox = page.locator("[data-testid='search-input']");
        searchBox.fill(entityName);
        page.waitForTimeout(1500);
    }

    public boolean verifyGlobalSearchPlaceholder(String text) {
        Locator input =(INPUT_GLOBAL_SEARCH);
        input.waitFor();
        return input.getAttribute("placeholder").equals(text);
    }
    public boolean verifySmartSuggestionAvailability(String text) {
        clickOnSearch();
        return (SMART_SUGGESTIONS).first().textContent().contains(text);
    }

    public boolean verifyNlpBanner() {
        clickOnSearch();
        return (NLP_BANNER).isVisible();
    } 
    
    public boolean verifyRecentlySearchedTitle(String expected) {
        clickOnSearch();
        Locator title = RECENTLY_SEARCHED_TITLE;
        title.waitFor();
        String actual = title.textContent().trim();

        return actual.equals(expected);
    }
    
    
    public boolean verifyClearAllButtonRecentSearch(String text) {
        clickOnSearch();
        Locator btn = (RECENTSEARCH_CLEARALL_BUTTON);
        return btn.isVisible() && btn.textContent().equals(text);
    }
    
    public boolean verifyRecentlyVisitedTitle(String expected) {
        clickOnSearch();
        return (RECENTLY_VISITED_TITLE).textContent().equals(expected);
    } 	
    
    public boolean verifyClearAllButtonRecentVisited(String expected) {
        clickOnSearch();
        Locator btn =(RECENTVISITED_CLEARALL_BUTTON);
        return btn.isVisible() && btn.textContent().equals(expected);
    }
    
    
    public boolean verifyKeywordSavedToRecent(String text) {
        typeInSearch(text);

       (DROPDOWN_COMPANY_DATA_CARD).first().click();
       clickOnSearch();
      (GLOBALSEARCH_CROSS_ICON).click();

        (RECENTSEARCH_PILL).waitFor();
        return (RECENTSEARCH_PILL).textContent().contains(text)
                && (RECENTVISITED_PILL).textContent().contains(text);
    }
    
    
    public boolean verifyLandingPageHeading(String text) {
        typeInSearch(text);
        page.keyboard().press("Enter");
        (DROPDOWN_VIEWALL_BUTTON).click();
        (LANDING_PAGE_LAYOUT).waitFor();

        return (LANDING_PAGE_HEADING)
                .textContent()
                .equals("Showing results for \"" + text + "\"");
    }
    
    public boolean verifyViewAllButtonDropdown(String text) {
        typeInSearch(text);
      (DROPDOWN_VIEWALL_BUTTON).click();
        return (LANDING_PAGE_LAYOUT).isVisible();
    }
    
    public boolean verifySearchCategoryState(String text, String category, String state) {

        typeInSearch(text);

        page.keyboard().press("Enter");

        String selector = "//button[@class=\"button-type-text faded-arrow cta fs-12\"]";

        page.waitForSelector(selector);

        Locator element = page.locator(selector).first();

        String ariaExpanded = element.getAttribute("aria-expanded");
        String ariaDisabled = element.getAttribute("aria-disabled");

        if (ariaExpanded == null) ariaExpanded = "";
        if (ariaDisabled == null) ariaDisabled = "";

        switch (state.toLowerCase()) {
            case "active":
                return ariaExpanded.equals("true");

            case "not active":
                return ariaExpanded.equals("false");

            case "disabled":
                return ariaDisabled.equals("true");

            default:
                return false;
        }
    }
    
    public boolean verifyMinimumKeywordMoreThan3() {
        Locator input = (INPUT_GLOBAL_SEARCH);

        input.click();
        input.fill("Swi");

        return (DATA_APPEARING).isVisible()
                && (RESULT_CATEGORY_LIST).isVisible();
    }
    
    public boolean verifyCountOfAllTab(String text, int expectedCount) {
        (INPUT_GLOBAL_SEARCH).fill(text);

        List<Locator> cats = (RESULT_CATEGORY_TAB).all();

        int sum = 0;
        for (int i = 1; i < cats.size(); i++) {
            String number = cats.get(i).textContent().replaceAll("[^0-9]", "");
            sum += Integer.parseInt(number);
        }

        int allCount = Integer.parseInt(cats.get(0).textContent().replaceAll("[^0-9]", ""));

        return sum == expectedCount && allCount == expectedCount;
    }

    public boolean verifyCountOfResultCategories(String companyName, String count, String category) {

      

        Locator loc = page.locator("//button[contains(text(),'" + category + "')]");

        String actualCount = extractNumber(loc.textContent().trim());

        return actualCount.equals(count);
    } 
    private String extractNumber(String text) {
        return text.replaceAll("[^0-9]", "");
    
    } 
   
    public boolean verifyNlpRecentSearches(String text) {

       INPUT_GLOBAL_SEARCH.waitFor();

        NLP_ICON.click();

        Locator heading = NLP_RECENTSEARCHES_HEADING;

        if (heading.isVisible()) {
            return heading.textContent().trim().equals(text);
        }

        return false;
    }
    
	public boolean verifyNoResultDisplay(String text) {
        typeInSearch(text);
       (NO_RESULT_TEXT).waitFor();

        return (NO_RESULT_TEXT).textContent().contains(
                "Your Search did not match any results."
        );
    }
    
    public boolean verifyCategorySectionCount(String text, String category, String count) {

        typeInSearch(text);

        Locator tab = page.locator("//button[contains(text(),'" + category + "')]");
        tab.click();

        String headerCount = (CATEGORY_SECTION_HEADING).textContent().replaceAll("[^0-9]", "");
        String dataCardCount = String.valueOf((DATA_CARD_APPEARING).count());

        return headerCount.equals(count) && dataCardCount.equals(count);
    }
    
    public boolean verifyBreadcrumb(String text, String expected) {
        typeInSearch(text);
        page.keyboard().press("Enter");
        return (BREADCRUMB).textContent().equals(expected);
    }
    
    public boolean verifyNlpIcon() {
        return (NLP_ICON).isVisible();
    } 

    public boolean verifyNlpClearAllButton(String expectedText) {

        NLP_ICON.click();

        Locator clearAllBtn = NLP_CLEARALL_BUTTON;

        if (clearAllBtn.isVisible()) {
            return clearAllBtn.textContent().trim().equals(expectedText);
        }

        return false;
    }
  

	public boolean verifyDropdownDataCard(String text) {
		typeInSearch(text);
	    page.keyboard().press("Enter");
       
        DROPDOWN_COMPANY_DATA_CARD.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualName = DROPDOWN_DATACARD_NAME.innerText().trim();
        boolean logoVisible = DROPDOWN_DATACARD_LOGO.isVisible();
        
        return actualName.equalsIgnoreCase(text) && logoVisible;
    }

	public void verifyLandingPageDataCard(String text) {

	    typeInSearch(text);
	    page.keyboard().press("Enter");

	    Locator card = page
	            .locator("div.company-list div[data-testid='dropdown-search-card-1']")
	            .first();

	    card.waitFor(new Locator.WaitForOptions().setTimeout(5000));
	    assertThat(card).isVisible();

	    Locator name = card.locator("div.fw-500");
	    Locator logo = card.locator("div.shrink-0 img");

	    verifyDataCard
	    (name, logo, text);
	}
  
	public void verifyDataCard(Locator nameLocator, Locator logoLocator, String expectedText) {

	    assertThat(nameLocator).isVisible();
	    assertThat(nameLocator).containsText(expectedText);

	    assertThat(logoLocator).isVisible();
	}

    
	public void clickOnSearch() {
        (INPUT_GLOBAL_SEARCH).click();
    }

    public void typeInSearch(String text) {
    	
       (INPUT_GLOBAL_SEARCH).fill(text);
    }
    
    public boolean verifySeeAllButton(){
        return true;
    }
    
    public boolean verifyNlpSearchPlaceholder(String text) {
       (NLP_ICON).click();
        return (INPUT_NLP_SEARCH).getAttribute("placeholder").equals(text);
    }
    
  
    
    
    
    public void whenISearchForEntityWithGivenType(String entityName, String entityType) {
        whenISearchForEntity(entityName);
        entityType = updateEntityType(entityType);
        System.out.println(entityType);
        Locator entityTypeButton = page.locator(
                ".header-search div .dropdown-card div div div.buttons-list button:has-text(\"" + entityType + "\")"
        );

        page.waitForTimeout(500);

        if(entityTypeButton.isEnabled()) {
            System.out.println(entityType);
            click(entityTypeButton);
        }
    }

    public void whenISearchForEntityAndGoToLandingPageWithGivenType(String entityName, String entityType) {
        whenISearchForEntity(entityName);
        page.waitForTimeout(1000);
        page.keyboard().press("Enter");
        page.waitForTimeout(2000);

        Locator accordionButton = page.locator(
                "div.MuiAccordionSummary-root strong:has-text('" + updateEntityType(entityType) + "')"
        );

        click(accordionButton);
    }

}