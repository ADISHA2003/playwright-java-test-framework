package PlaywrightPageObject.ScreenerFilterPage;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import io.restassured.response.Response;
import org.opentest4j.AssertionFailedError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FilterAreaActions extends BasePageActions{
    FilterManagementService fms;
    public FilterAreaActions(Page page) {
        super(page);
        fms = new FilterManagementService();
    }

    Locator applyButton = page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("Apply"));
    Locator updateButton = page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("Update"));
    Locator filterMainSection = page.locator("xpath=//div[contains(@class,'csfb-main-top ')]");
    Locator graphMinDropdown = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']/select)[1]");
    Locator graphMaxDropdown = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']/select)[2]");
    Locator graphOperatorDropdown = filterMainSection.locator("xpath=//div[@class='gbf-inner flex justify-center']/div/select");
    Locator graphMinInput = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']//input)[1]");
    Locator graphMaxInput = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']//input)[2]");
    Locator clearAppliedFilters = page.locator("xpath=//button[contains(text(),'Clear Applied Filters')]");
    Locator searchInput = page.locator("css=[placeholder=\"Search filters\"]");
    Locator searchInputAdd = page.locator("css=[class='search-n-manage-filter'] button.blue-text");
    Locator searchInputReset = page.locator("button[type='reset']");
    Locator graphMinCalenderInput = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']//input)[1]");
    Locator graphMaxCalenderInput = filterMainSection.locator("xpath=(//div[@class='gbf-inner flex justify-center']//input)[2]");


    public void whenIClickOnApplyUpdateButton() {
        if(applyButton.count()>0) {
            applyButton.click();
        }else updateButton.click();
        page.waitForTimeout(2000); // wait for filters to apply
    }

    private Locator manageModalButton = page.locator(".svg-icon.fill.cta-blue");
    public void whenIClickOnManageModalButton() {
        assertThat(manageModalButton).isVisible();
        manageModalButton.click();

    }

    public void whenISearchForAFilter(String filterName) {
        Locator searchBox = page.locator("input[placeholder='Search filters']");
        searchBox.fill(filterName);
        page.waitForTimeout(1000); // wait for results to update
    }

    public void thenIShouldBeAbleToAddFilter(String filterName) {
        Locator filterItem = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText(filterName));
        if (filterItem.count() == 0) {
            throw new AssertionError("Filter '" + filterName + "' is not visible.");
        }
    }

    public void expandGroup(String groupName) {

        Locator group = page.locator("css=.csfb-list-items").getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(groupName));
        if(!group.getAttribute("class").contains("expanded"))
            group.click();
        page.waitForTimeout(1000); // wait for group to expand
    }

    public void collapseGroup(String groupName) {
        Locator group = page.locator("css=.csfb-list-items").getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(groupName));
        if(group.getAttribute("class").contains("expanded"))
            group.click();
        page.waitForTimeout(1000); // wait for group to expand
    }

    public Locator openFilterUnderGroup(FilterFormat ff) {
        expandGroup(ff.filterGroup);
        Locator groupSection = page.locator("xpath=//div[@class=\"csfb-list-items\"]//div[@role='button' and contains(.,'"+ff.filterGroup+"')]/following-sibling::div");
        Locator filter = null;
        filter = groupSection.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(ff.filterName));
        try {
            assertThat(filter).isVisible();
        }catch(AssertionFailedError e) {
            addFilterFromSearch(ff);
        }
        filter.click();
        page.waitForTimeout(4000);
        return filter;
    }

    public void addFilterFromSearch(FilterFormat ff){
        searchInput.click();
        page.waitForTimeout(2000);
        searchInput.fill(ff.filterName);
        searchInputAdd.click();
        searchInputReset.click();
    }

    public void thenIShouldBeAbleToOpenFilterUnderGroup(FilterFormat ff) {
        openFilterUnderGroup(ff);}


    public void thenIShouldBeAbleToApplyFilterBasedOnType(FilterFormat ff) {
                switch (ff.filterType.toLowerCase()) {
                    case "multi_select_values":
                        handleMultiSelectFilter(ff.filterInputValues.get(0));
                        break;
                    case "top":
                        handleMultiSelectFilter(ff.filterInputValues.get(0));
                        break;
                    case "graph_financial_growth":
                        handleFinancialGrowthGraphFilter(ff);
                        break;
                    case "graph_financial":
                        handleFinancialGraphFilter(ff);
                        break;
                    case "graph":
                        handleGraphFilter(ff);
                        break;
                    case "graph_year":
                        handleGraphYearFilter(ff);
                        break;
                    case "multi view multi select":
                        handleMultiViewMultiSelectFilter();
                        break;
                    case "bmi multi select":
                        handleBMI_MultiSelectFilter();
                        break;
                    case "headquarters":
                        handleLocationFilter(ff);
                        break;
                    case "sector":
                        handleSector(ff);
                        break;
                    case "keyword search":
                        handleKeyWordSearchFilter();
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown filter type: " + ff.filterType);
                }
    }

    private void handleFinancialGrowthGraphFilter(FilterFormat ff) {
        assertThat(graphMinInput).isVisible();
        assertThat(graphOperatorDropdown).isVisible();
        assertThat(graphMaxInput).isVisible();
        enterText(graphMinInput,ff.filterInputValues.get(0).replaceAll("%",""));
        selectDropDownByValue(graphOperatorDropdown,ff.filterOperator);
        enterText(graphMaxInput,ff.filterInputValues.get(1).replaceAll("%",""));
    }


    public void handleMultiSelectFilter(String optionToSelect) {
        System.out.println("Selecting option: " + optionToSelect);
        Locator option = filterMainSection.locator("xpath=//label[text()='" + optionToSelect + "']");
        option.click();

    }

    public void handleFinancialGraphFilter(FilterFormat ff){
        assertThat(graphMinInput).isVisible();
        assertThat(graphOperatorDropdown).isVisible();
        assertThat(graphMaxInput).isVisible();
        enterText(graphMinInput,ff.filterInputValues.get(0).replaceAll("%",""));
        selectDropDownByValue(graphOperatorDropdown,ff.filterOperator);
        enterText(graphMaxInput,ff.filterInputValues.get(1).replaceAll("%",""));
    }

    public void handleGraphFilter(FilterFormat ff){
        assertThat(graphMinInput).isVisible();
        assertThat(graphOperatorDropdown).isVisible();
        assertThat(graphMaxInput).isVisible();
        enterText(graphMinInput,ff.filterInputValues.get(0).replaceAll("%",""));
        selectDropDownByValue(graphOperatorDropdown,ff.filterOperator);
        enterText(graphMaxInput,ff.filterInputValues.get(1).replaceAll("%",""));
    }

    public void handleGraphYearFilter(FilterFormat ff){
        try {
            assertThat(graphMinDropdown).isVisible();
            assertThat(graphOperatorDropdown).isVisible();
            assertThat(graphMaxDropdown).isVisible();
            selectDropDownByValue(graphMinDropdown, ff.filterInputValues.get(0));
            selectDropDownByValue(graphOperatorDropdown, ff.filterOperator);
            selectDropDownByValue(graphMaxDropdown, ff.filterInputValues.get(1));
        }catch(AssertionFailedError ase){
            // graph has calender
            String targetYear = ff.filterInputValues.get(0);
            String targetMonth = "Jan"; // Short form as seen in your HTML
            String targetDayFull = "01 Jan "+targetYear; // Full format as seen in aria-label
            graphMinCalenderInput.click();

// 1. Click the Header (e.g., "Jan 1995") to open the Year/Month view
            page.locator(".rs-calendar-header-title").click();

// 2. Select the Year from the scrollable list
// RSuite uses rows for years. We find the row for the year, then the month inside it.
            Locator yearRow = page.locator(".rs-calendar-month-dropdown-year").filter(
                    new Locator.FilterOptions().setHasText(targetYear)
            );

            while(!yearRow.isVisible()){
                page.locator("css=.rs-calendar-month-dropdown-scroll").hover();
                page.mouse().wheel(0,50);
            }
            while(!yearRow.locator("css=div[aria-label='"+targetMonth+" "+targetYear+"']").isVisible()){
                page.mouse().wheel(0,10);
            }
            yearRow.locator("css=div[aria-label='"+targetMonth+" "+targetYear+"']").click();

// 3. Now that the calendar has jumped to Oct 2010, click the specific day
            page.locator("[role='gridcell'][aria-label='" + targetDayFull + "']").click();

             targetYear = ff.filterInputValues.get(1);
             targetMonth = "Jan"; // Short form as seen in your HTML
             targetDayFull = "01 Jan "+targetYear; // Full format as sen in aria-label
            graphMaxCalenderInput.click();

// 1. Click the Header (e.g., "Jan 1995") to open the Year/Month view
            page.locator(".rs-calendar-header-title").click();

// 2. Select the Year from the scrollable list
// RSuite uses rows for years. We find the row for the year, then the month inside it.
            yearRow.getByRole(AriaRole.GRIDCELL).getByText(targetMonth).scrollIntoViewIfNeeded();
             yearRow = page.locator(".rs-calendar-month-dropdown-year").filter(
                    new Locator.FilterOptions().setHasText(targetYear)
            );
            while(!yearRow.isVisible()){
                page.locator("css=.rs-calendar-month-dropdown-scroll").hover();
                page.mouse().wheel(0,50);
            }
            while(!yearRow.locator("css=div[aria-label='"+targetMonth+" "+targetYear+"']").isVisible()){
                page.mouse().wheel(0,10);
            }
            yearRow.locator("css=div[aria-label='"+targetMonth+" "+targetYear+"']").click();

// 3. Now that the calendar has jumped to Oct 2010, click the specific day
            page.locator("[role='gridcell'][aria-label='" + targetDayFull + "']").click();
        }
    }

    public void handleMultiViewMultiSelectFilter(){

    }

    public void handleBMI_MultiSelectFilter(){

    }

    public void handleLocationFilter(FilterFormat ff){
        String[] optiontreeMap = ff.filterInputValues.get(0).split(" -> ");
        String Country = optiontreeMap[0];
        Locator countryOption = filterMainSection.locator("xpath=//ul[@class='list-reset filter-action-list']//button[text()='"+Country+"']");
        if(!countryOption.getAttribute("class").contains("active")){
            countryOption.click();
        }
        for(int i=1;i<optiontreeMap.length;i++) {
            Locator stateTreeOption = page.locator("xpath=//div[@class='checkbox-accordion opened']//span[contains(@class,'checkmark')]/parent::label[(text()='"+optiontreeMap[i]+"')]");
            stateTreeOption.click();
        }
    }

    public void handleKeyWordSearchFilter(){

    }

    public void clickApplyUpdateButton(){
        page.waitForTimeout(3000);
        Locator applyUpdate = filterMainSection.locator("xpath=//div[contains(@class,'btm-fix')]/button");
        applyUpdate.click();
    }

    public void clickClearAppliedFiltersButton() {
        page.waitForTimeout(3000);      
        clearAppliedFilters.click();
    }
    
    public void verifyFilterIsApplied(FilterFormat ff){
        Locator filter =openFilterUnderGroup(ff);
        assertThat(filter).containsClass("active");
    }

    public void handleSector(FilterFormat ff){
        String[] optiontreeMap = ff.filterInputValues.get(0).split(" -> ");
        String sector = optiontreeMap[0];
        Locator sectorOption = filterMainSection.locator("xpath=//ul[@class='list-reset filter-action-list']//button[text()='"+sector+"']");
        if(!sectorOption.getAttribute("class").contains("active")){
            sectorOption.click();
        }
        for(int i=1;i<optiontreeMap.length;i++) {
            Locator sectorTreeOption = page.locator("xpath=//div[@class='checkbox-accordion opened']//span[contains(@class,'checkmark')]/parent::label[(text()='"+optiontreeMap[i]+"')]");
            sectorTreeOption.click();
        }
    }

    public String getFilterOptionUpdatedBody(String filterOption,String filterType,String body){
        switch (filterType.toLowerCase()) {
            case "multi_select_values":
                return getMultiSelectUpdatedBody(filterOption, body);
            case "top":
                return getTopUpdatedBody(filterOption, body);
            default:
                throw new IllegalArgumentException("Unknown filter type: " + filterType);
        }
    }

    public String getMultiSelectUpdatedBody(String filterOption,String body){
     return body.replaceAll("\"values\":\\[.*?\\]","\"values\":[\""+filterOption+"\"]");
    }

    public String getTopUpdatedBody(String filterOption,String body){
    return body.replaceAll("\"values\":\\[.*?\\]","\"values\":[\""+filterOption+"\"]");
    }


    public String getCompanyTypeIdFromName(String filterOption) {
        Map<String,String> companyTypeMap = new HashMap<>();
        companyTypeMap.put("trust","198");
        companyTypeMap.put("private company","6");
        companyTypeMap.put("public company","8");
        companyTypeMap.put("foundation","196");
        companyTypeMap.put("education institution","4");
        companyTypeMap.put("government institution","195");
        companyTypeMap.put("non-government organisation","197");
        companyTypeMap.put("charitable trust","2");
        System.out.println(filterOption.toLowerCase());
        return companyTypeMap.get(filterOption.toLowerCase());


    }
}
