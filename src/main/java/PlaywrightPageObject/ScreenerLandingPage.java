package PlaywrightPageObject;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import io.atlassian.util.concurrent.TimedOutException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.codehaus.plexus.util.FileUtils.waitFor;

public class ScreenerLandingPage extends BasePageActions{
    
    // Constructor
    public ScreenerLandingPage(Page page) {
        super(page);
    }
    
     // Header Navigation Elements
    private Locator headerLogo = page.locator("img").first();
    private Locator vccEdgeLogoLink = page.locator("a[href='/dashboard']:has-text('VCCEdge')");
    private Locator vccEdgeLogo = page.locator("img[alt='VCCEdge']");
    
    // Search Elements
    private Locator searchTextbox = page.locator("input[placeholder='Search with name, website, CIN, DIN or SC code']");
    private Locator searchButton = page.locator("button").filter(new Locator.FilterOptions().setHasText("img"));
    
    // Header Action Buttons
    private Locator notificationButton = page.locator("button").nth(1);
    private Locator helpButton = page.locator("a[href='https://intercom.help/vccedge/en/']");
    private Locator profileButton = page.locator("img").last();
    
    // Breadcrumb Navigation
    private Locator homeLink = page.locator("a[href='/dashboard']:has-text('Home')");
    private Locator breadcrumbSeparator = page.locator("text=/");
    private Locator screenerHomeBreadcrumb = page.locator("text=Screener Home");
    
    // Main Content Elements
    private Locator screenerHeading = page.locator("h1:has-text('Screener')");
    private Locator companyDropdownButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Company"));
    private Locator selectEntityHeading = page.locator("h2:has-text('Select Entity to Start Screening')");
    
    // Entity Selection Buttons
    private Locator allEntitiesButton = page.locator("(//button[contains(@class,\"two-lines-n-arrow\")])[1]");
    private Locator publicCompanyButton = page.locator("(//button[contains(@class,\"two-lines-n-arrow\")])[2]");
    private Locator privateCompanyButton = page.locator("(//button[contains(@class,\"two-lines-n-arrow\")])[3]");
    private Locator otherCompanyButton = page.locator("(//button[contains(@class,\"two-lines-n-arrow\")])[4]");
    
    // Use Case Section
    private Locator useCaseHeading = page.locator("h3:has-text('Select Use Case')");
    private Locator earlyStageFundingUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Early-Stage Indian Startups for Seed or Series A Funding"));
    private Locator growthStageInvestmentUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Identify Growth-Stage Companies for Series B or C Investments"));
    private Locator techStartupsRevenueGrowthUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Identify Tech Startups with Strong Revenue Growth Trajectories"));
    private Locator growthStageFinancialPerformanceUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Indian Growth Stage companies with Strong Financial Performance"));
    private Locator buyoutTargetsUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Buyout Targets for Mature Companies"));
    private Locator innovativeStartupsUseCase = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Innovative Startups in Emerging Sectors"));
    
    // FAQ Section
    private Locator faqHeading = page.locator("h3:has-text('Frequently Asked Questions')");
    private Locator faqFilterQuestion = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("How do I use the filters to find potential investments?"));
    private Locator faqCustomizeFiltersQuestion = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Can I customize the filters, such as adding, removing, or reordering them?"));
    private Locator faqSaveSearchQuestion = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Can I save my screener searches for future use?"));
    private Locator faqViewResultsQuestion = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("How do I view my screened results?"));
    private Locator viewAllFaqsButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View All FAQ\\'s"));
    
    // Right Sidebar Elements
    private Locator didYouKnowSection = page.locator("strong:has-text('Did you know?')");
    private Locator didYouKnowText = page.locator("text=You can easily personalize your data table! Just click the \"Column\" button to add, remove, or shuffle columns around.");
    
    // Saved Criteria Section
    private Locator savedCriteriaHeading = page.locator("h3:has-text('Saved Criteria')");
    private Locator savedCriteriaCount112 = page.locator("strong:has-text('count_112')");
    private Locator savedCriteriaCr1 = page.locator("strong:has-text('cr1')");
    private Locator savedCriteriaPublic = page.locator("strong:has-text('public criteria')");
    
    // Reusable Screener Searches Section
    private Locator reusableScreenerSearchesHeading = page.locator("h3:has-text('Reusable Screener Searches')");
    private Locator reusableScreenerSearchesDescription = page.locator("text=Easily save searches with 'Save Criteria' and access your last 20 saved searches from the Screener page.");
    private Locator screenerScreenshot = page.locator("img[alt='Screenshot 2']");
    
    // Intercom Elements
    private Locator intercomMessenger = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Intercom Messenger"));
    private Locator screenerDropdown = page.locator(".button.select");
    
    // Page Actions
    public void navigateToScreenerPage() {
        page.navigate("https://www.vccedge.com/screener/company");
    }
    
    public void performSearch(String searchTerm) {
        searchTextbox.fill(searchTerm);
        searchButton.click();
    }
    
    public void clickHomeInBreadcrumb() {
        homeLink.click();
    }
    
    public void clickCompanyDropdown() {
        companyDropdownButton.waitFor(new Locator.WaitForOptions().setTimeout(30000));
        companyDropdownButton.click();
        page.pause();
    }
    
    // Entity Selection Actions
    public void selectAllEntities() {
        allEntitiesButton.click();
    }
    
    public void selectPublicCompany() {
        publicCompanyButton.click();
    }
    
    public void selectPrivateCompany() {
        privateCompanyButton.click();
    }
    
    public void selectOtherCompany() {
        otherCompanyButton.click();
    }
    
    // Use Case Selection Actions
    public void selectEarlyStageFundingUseCase() {
        earlyStageFundingUseCase.click();
    }
    
    public void selectGrowthStageInvestmentUseCase() {
        growthStageInvestmentUseCase.click();
    }
    
    public void selectTechStartupsRevenueGrowthUseCase() {
        techStartupsRevenueGrowthUseCase.click();
    }
    
    public void selectGrowthStageFinancialPerformanceUseCase() {
        growthStageFinancialPerformanceUseCase.click();
    }
    
    public void selectBuyoutTargetsUseCase() {
        buyoutTargetsUseCase.click();
    }
    
    public void selectInnovativeStartupsUseCase() {
        innovativeStartupsUseCase.click();
    }
    
    // FAQ Actions
    public void clickFiltersFAQ() {
        faqFilterQuestion.click();
    }
    
    public void clickCustomizeFiltersFAQ() {
        faqCustomizeFiltersQuestion.click();
    }
    
    public void clickSaveSearchFAQ() {
        faqSaveSearchQuestion.click();
    }
    
    public void clickViewResultsFAQ() {
        faqViewResultsQuestion.click();
    }
    
    public void clickViewAllFAQs() {
        viewAllFaqsButton.click();
    }
    
    // Saved Criteria Actions
    public void clickSavedCriteriaCount112() {
        savedCriteriaCount112.click();
    }
    
    public void clickSavedCriteriaCr1() {
        savedCriteriaCr1.click();
    }
    
    public void clickSavedCriteriaPublic() {
        savedCriteriaPublic.click();
    }
    
    // Intercom Actions
    public void openIntercomChat() {
        intercomMessenger.click();
    }
    
    // Verification Methods
    public boolean isScreenerPageLoaded() {
        return screenerHeading.isVisible() && 
               selectEntityHeading.isVisible() && 
               useCaseHeading.isVisible();
    }

    public void clickViewAllResult() {
        click(page.locator("button:has-text('View All Result')"));
    }
    
    public boolean isSearchBoxVisible() {
        return searchTextbox.isVisible();
    }
    
    public void areEntityButtonsVisible() {
         assertThat(allEntitiesButton).hasCount(1);
         assertThat(publicCompanyButton).isAttached();
         assertThat(privateCompanyButton).isAttached();
         assertThat(otherCompanyButton).isAttached();
    }
    
    public boolean areUseCasesVisible() {
        return earlyStageFundingUseCase.isVisible() && 
               growthStageInvestmentUseCase.isVisible() && 
               techStartupsRevenueGrowthUseCase.isVisible();
    }
    
    public boolean isFAQSectionVisible() {
        return faqHeading.isVisible() && faqFilterQuestion.isVisible();
    }
    
    public boolean isSavedCriteriaSectionVisible() {
        return savedCriteriaHeading.isVisible() && 
               savedCriteriaCount112.isVisible();
    }
    
    public String getPageTitle() {
        return page.title();
    }
    
    public String getCurrentUrl() {
        return page.url();
    }
    
    public String getSearchValue() {
        return searchTextbox.inputValue();
    }
    
    // Wait Methods
    public void waitForPageLoad() {
        screenerHeading.waitFor();
        selectEntityHeading.waitFor();
    }
    
    public void waitForEntityButtonsToLoad() {
        allEntitiesButton.waitFor();
        publicCompanyButton.waitFor();
        privateCompanyButton.waitFor();
        otherCompanyButton.waitFor();
    }
    
    public void waitForUseCasesToLoad() {
        useCaseHeading.waitFor();
        earlyStageFundingUseCase.waitFor();
    }

    public void navigateToScreenerLandingPage(String domain, String screenerType){
        try{
            switch(screenerType.toLowerCase()){
                case "company":
                case "companies":
                    page.navigate(domain + "screener/company");
                    break;
                case "investor":
                case "investors":
                case "asset_manager":
                case "asset manager":
                    page.navigate(domain + "screener/investor");
                    break;
                case "deal":
                case "deals":
                    page.navigate(domain + "screener/deal");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid screener type: " + screenerType);
            }
        }catch(TimeoutError e){
            System.out.println("Ignoring Navigation to screener landing page timed out.");
        }
    }

    public void thenIShouldBeOnScreenerLandingPage(){
        waitForPageLoad();
        if (!isScreenerPageLoaded()) {
            throw new IllegalStateException("Screener landing page did not load correctly.");
        }
        String url = getCurrentUrl();
        if (!url.contains("/screener/")) {
            throw new IllegalStateException("Unexpected URL: " + url);
        }
    }

    public void verifyAllTheComponentsOnThePage(){
        waitForPageLoad();
        isFAQSectionVisible();

        isSavedCriteriaSectionVisible();

        waitForUseCasesToLoad();
        isScreenerPageLoaded();

        isSearchBoxVisible();
        areEntityButtonsVisible();
    }

    public void selectFilterTypeFromDropdown(String filterType){
        clickCompanyDropdown();
        switch (filterType.toLowerCase()) {
            case "companies":
                allEntitiesButton.click();
                break;
            case "deal":
            case "deals":
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Deal")).click();
                break;
            case "investor":
            case "investors":
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Investor")).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }
    }

    public void verifyScreenerLandingPageTitle(String expectedTitle){
       assertThat(screenerDropdown).hasText(expectedTitle);
    }

    public void selectTileOnLandingPageOf(String filterType, String tile){
        selectTileOnInvestorScreenerLandingPage(tile);
    }

    private void selectTileOnInvestorScreenerLandingPage(String tile) {
        Locator tileLocator = page.locator("xpath=//button[contains(@class,'two-lines-n-arrow')]//strong[text()='" + tile + "']");
        tileLocator.waitFor();
        if (!tileLocator.isVisible()) {
            throw new IllegalArgumentException("Tile not found or not visible on Investor Screener Landing Page: " + tile);
        }

        click(tileLocator);
        System.out.println("Clicked tile: " + tile);
    }

    private void selectTileOnDealScreenerLandingPage(String tile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectTileOnDealScreenerLandingPage'");
    }

    public void selectTileOnCompanyScreenerLandingPage( String tile){
        
        switch (tile.toLowerCase()) {
            case "all":
                allEntitiesButton.click();
                break;
            case "public company":
                publicCompanyButton.click();
                break;
            case "private company":
                privateCompanyButton.click();
                break;
            case "other company":
                otherCompanyButton.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid tile name: " + tile);
        }
    }

    public void navigateToScreenerAndOpenResults(String domain, String screenerType, String tile) {
        // Step 1: Go to screener landing page
        if(tile.equalsIgnoreCase("incubator")) {
            page.navigate(domain + "screener/incubator/filters");

        } else {
            navigateToScreenerLandingPage(domain, screenerType);

            // Step 2: Select the tile
            selectTileOnLandingPageOf(screenerType, tile);
        }

        // Step 3: Click "View All Result"
        clickViewAllResult();
    }

}
