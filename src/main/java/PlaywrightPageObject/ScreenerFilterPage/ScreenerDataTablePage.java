package PlaywrightPageObject.ScreenerFilterPage;


import PlaywrightPageObject.BasePageActions;
import PlaywrightPageObject.DataTable.HandlingRowData;
import PlaywrightPageObject.DataTable.HandlingSearch;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenerDataTablePage extends BasePageActions {
        Locator heading = page.locator("h2:has-text('Company Data Table'), h1:has-text('Company Data Table')");
        Locator table = page.locator("table.company-data-table");
        Locator columnHeaders = page.locator("table.company-data-table thead th");
        Locator tableRows = page.locator("table.company-data-table tbody tr");
        Locator paginationNext = page.locator("button:has-text('Next'), .pagination-next");
        Locator paginationPrev = page.locator("button:has-text('Previous'), .pagination-prev");
        Locator exportButton = page.locator("#export-button-id-for-screener-page");
        Locator sortButton = page.locator("table.company-data-table thead th .sort-button");
        Locator rowActionButton = page.locator("table.company-data-table tbody tr .row-action");
        Locator WhereCondition = page.locator("xpath=(//div[@class='bottom-drawer open']//div[@id='criteriaFilter0']/div)[1]");

        // New locators
        Locator searchButton  = page.locator("button:has(span.svg-icon) >> text=Search");
        Locator searchInput = page.locator("input[placeholder='Search Visible Columns']");
        Locator currencyDropdown = page.locator("xpath=//div[@class=\"data-table-filters flex\"]//button[text()='₹ INR Crore' or text()='$ USD Million']");
        Locator manageColumnsButton = page.locator("xpath=//div[@class=\"data-table-filters flex\"]//button[contains(text(),'Column')]");
        Locator columnCheckboxes = page.locator(".columns-panel input[type='checkbox']");
        Locator filtersButton = page.locator("xpath=//div[@class=\"data-table-filters flex\"]//button[contains(text(),'Filter')]");
        Locator filtersPanel = page.locator(".filters-panel");
        Locator buttonStandalone = page.locator("xpath=//div[@class=\"data-table-filters flex\"]//button[text()='Standalone']");
        Locator buttonFinancialDropdown = page.locator("//div[@class=\"data-table-filters flex\"]//button[text()='FY 2025']");
        Locator buttonGrowthCagr = page.locator("//div[@class=\"data-table-filters flex\"]//button[text()='Growth/CAGR (3 Yrs)']");
        Locator buttonViewCriteria = page.locator("xpath=//button[@class=\"button secondary sm\"]");
        Locator buttonModifyOnCriteria = page.locator("xpath=//div[@class=\"bottom-drawer open\"]//a[@href=\"/screener/company/filters\"]");
        Locator modifyButton = page.locator("xpath=//span[text()='Applied Criteria:']/preceding-sibling::a");
        Locator appliedFilterPopup = page.locator("css=.MuiPaper-elevation .buttons-list div");
        Locator tableHeading = page.locator("css=.page-heading");
        private final HandlingSearch handlingSearch;

    public ScreenerDataTablePage(Page page) {
        super(page);
        handlingSearch = new HandlingSearch(page);
    }

    // Existing Actions
    public String getHeadingText() { return heading.textContent(); }
    public int getColumnCount() { return columnHeaders.count(); }
    public String getColumnHeader(int index) { return columnHeaders.nth(index).textContent(); }
    public int getRowCount() { return tableRows.count(); }
    public String getCellText(int rowIndex, int colIndex) {
        return tableRows.nth(rowIndex).locator("td").nth(colIndex).textContent();
    }
    public void clickSortButton(int colIndex) { sortButton.nth(colIndex).click(); }
    public void clickExportButton() { exportButton.click(); }
    public void goToNextPage() { paginationNext.click(); }
    public void goToPreviousPage() { paginationPrev.click(); }
    public void clickRowAction(int rowIndex) { rowActionButton.nth(rowIndex).click(); }



    // New Actions
    public void search(String query) {
        handlingSearch.performSearch("css=.data-table-filters .search-input-wrapper input", "button:has(span.svg-icon) >> text=Search", query);
    }

    public void selectCurrency(String currencyValue) {
        currencyDropdown.selectOption(currencyValue);
    }

    public void openManageColumns() {
        manageColumnsButton.click();
    }

    public void setColumnVisibility(int index, boolean visible) {
        Locator checkbox = columnCheckboxes.nth(index);
        if (checkbox.isChecked() != visible) {
            checkbox.click();
        }
    }

    public void openFiltersPanel() {
        filtersButton.click();
    }

    public boolean isFiltersPanelVisible() {
        return filtersPanel.isVisible();
    }

    public void thenIShouldSeeDropDownFiltersOnDataTableScreen(FilterFormat ff) {
        // Verify that the applied filter is reflected in the data table
        // This could involve checking for specific rows or values based on the filter criteria
        // Implementation will depend on the specific filter and expected results
        switch(ff.screenerName.toLowerCase()){
            case "companies":companyScreenerDataTableOptions(ff);
                break;
            case "asset_managers":
            case "funds":
            case "limited_partners":
            case "family_offices":investmentScreenerTableOptions(ff);
                break;
            case "all_deals":
            case "private_equity_investments":
            case "merger_and_acquisitions":
            case "private_equity_exits":
            case "equity_capital_markets":
            case "debt_transactions":

        }
    }

    public void companyScreenerDataTableOptions(FilterFormat ff){
        page.waitForTimeout(5000);
        assertThat(searchButton).isVisible();
        assertThat(currencyDropdown).isVisible();
        assertThat(manageColumnsButton).isVisible();
        assertThat(filtersButton).isVisible();
        assertThat(exportButton).isVisible();
        if(ff.filterInputValues.size()>1){
            assertThat(modifyButton).isVisible();
        }
        assertThat(buttonStandalone).isVisible();
        assertThat(buttonFinancialDropdown).isVisible();
        assertThat(buttonGrowthCagr).isVisible();
    }

    public void investmentScreenerTableOptions(FilterFormat ff){
        page.waitForTimeout(5000);
        assertThat(searchButton).isVisible();
        assertThat(currencyDropdown).isVisible();
        assertThat(manageColumnsButton).isVisible();
        assertThat(filtersButton).isVisible();
        assertThat(exportButton).isVisible();
        if(ff.filterInputValues.size()>1){
            assertThat(modifyButton).isVisible();
        }
    }

    public void thenIShouldSeeAppliedFilterOnDataTableScreen(FilterFormat ff){
        // Verify that the applied criteria is reflected in the data table
        // This could involve checking for specific rows or values based on the criteria
        // Implementation will depend on the specific criteria and expected results
        assertThat(buttonViewCriteria).isVisible();
        assertThat(modifyButton).isVisible();
        Locator filterApplied = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-name-"+ff.filterPosition+"']");
        Locator filterOperator = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-name-"+ff.filterPosition+"']/following-sibling::span[1]");
        Locator filterValue = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-value-"+ff.filterPosition+"']");
        assertThat(filterApplied).isVisible();
        assertThat(filterOperator).isVisible();
        assertThat(filterValue).isVisible();
        assertThat(filterApplied).containsText(ff.filterName);
        assertThat(filterOperator).containsText(ff.filterOperator);
        for(String val:ff.filterInputValues){
            System.out.println("Verifying filter value: "+val);
            System.out.println("Filter Value Text: "+filterValue);
            Assert.assertTrue(val.contains(filterValue.textContent().replaceAll("...","").trim()),"Filter value "+val+" is not present in the applied filter value text: "+filterValue.textContent().trim());
        }
    }

    public void thenIShouldSeeAppliedFilterOnDataTableScreenOnPopup(FilterFormat ff){
        assertThat(buttonViewCriteria).isVisible();
        assertThat(modifyButton).isVisible();
        Locator filterApplied = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-name-"+ff.filterPosition+"']");
        Locator filterOperator = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-name-"+ff.filterPosition+"']/following-sibling::span[1]");
        Locator filterValue = page.locator("xpath=//div[@class='content-container']//div[@class='flex']//div[@class='one-line-text flex cursor-hand']/span[@id='Filter-value-"+ff.filterPosition+"']");
        assertThat(filterApplied).isVisible();
        assertThat(filterOperator).isVisible();
        filterApplied.click();
        assertThat(appliedFilterPopup).isVisible();
        assertThat(filterApplied).containsText(ff.filterName);
        assertThat(filterOperator).containsText(ff.filterOperator);
        for(String val:ff.filterInputValues){
            System.out.println("Verifying filter value: "+val);
            System.out.println("Filter Value Text: "+appliedFilterPopup.textContent());
            Assert.assertTrue(val.equals(appliedFilterPopup.textContent().trim()),"Filter value "+val+" is not present in the applied filter value text: "+filterValue.textContent().trim());
        }
        hoverAndClickAtLocation(20,20);
    }

    public void verifyAppliedFilterOnTable(FilterFormat ff){
        switch (ff.filterType.toLowerCase()){
            case "multi_select_values": andIVerifyMultiselectFilterInCriteriaSection(ff); break;
            case "graph": andIVerifyGraphFilterInCriteriaSection(ff); break;
            case "graph_year": andIVerifyGraphYearFilterInCriteriaSection(ff); break;
            case "graph_financial": andIVerifyGraphFinancialYearFilterInCriteriaSection(ff);break;
            case "graph_financial_growth":    andIVerifyGraphGrowthFinancialYearFilterInCriteriaSection(ff);break;
            case "Sector": andIVerifySectorFilterInCriteriaSection(ff); break;
            case "Location": andIVerifyLocationFilterInCriteriaSection(ff); break;
        }
    }


    public void andIVerifyLocationFilterInCriteriaSection(FilterFormat ff){
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();

    }

    public void andIVerifySectorFilterInCriteriaSection(FilterFormat ff){
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();

    }

    public void AndICanViewCriteriaCondition(FilterFormat ff){
        verifyCriteriaConditionFormat(ff);
    }

    public void verifyCriteriaConditionFormat(FilterFormat ff){
        switch (ff.filterType.toLowerCase()) {
            case "multi_select_values":
                andIVerifyMultiselectFilterInCriteriaSection(ff);
                break;
            case "graph":
                andIVerifyGraphFilterInCriteriaSection(ff);
                break;
            case "graph_year":
                andIVerifyGraphYearFilterInCriteriaSection(ff);
                break;
            case "graph_financial":
                andIVerifyGraphFinancialYearFilterInCriteriaSection(ff);
                break;
            case "graph_financial_growth":
                andIVerifyGraphGrowthFinancialYearFilterInCriteriaSection(ff);
                break;
            case "Sector":
                andIVerifySectorFilterInCriteriaSection(ff);
                break;
            case "Location":
                andIVerifyLocationFilterInCriteriaSection(ff);
                break;
        }

    }

    public void andIVerifyMultiselectFilterInCriteriaSection(FilterFormat ff){
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();
        assertThat(buttonModifyOnCriteria).isVisible();

    }

    public void andIVerifyGraphFilterInCriteriaSection(FilterFormat ff){
        Locator conditionRow = conditionStatementGraph(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
       conditionValueGraph(conditionRow,ff);
        assertThat(buttonModifyOnCriteria).isVisible();

    }

    private void andIVerifyGraphGrowthFinancialYearFilterInCriteriaSection(FilterFormat ff) {
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();

    }

    private void andIVerifyGraphFinancialYearFilterInCriteriaSection(FilterFormat ff) {
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();

    }

    private void andIVerifyGraphYearFilterInCriteriaSection(FilterFormat ff) {
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
        assertThat(appliedFilter(conditionRow)).isVisible();
        assertThat(conditionOperator(conditionRow)).isVisible();
        assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();


    }

    public Locator AndOrCondition(Locator conditionRow){
        return conditionRow.locator("xpath=.//div[1]");
    }

    public Locator appliedFilter(Locator conditionRow){
        return conditionRow.locator("xpath=.//div").nth(1);
    }
    public Locator conditionOperator(Locator conditionRow){
        return conditionRow.locator("xpath=.//div").nth(2);
    }
    public Locator conditionValue(Locator conditionRow, List<String> inputData){
        StringBuilder valueText = new StringBuilder();
        for(int i=0;i<inputData.size();i++){
            if(i==0)
                valueText.append(inputData.get(i));
            else
                valueText.append(", ").append(inputData.get(i));
        }
        return conditionRow.locator("xpath=.//div//div").nth(3).filter(new Locator.FilterOptions().setHasText(valueText.toString()));
    }

    public void conditionValueGraph(Locator conditionRow, FilterFormat ff){
        switch(ff.filterOperator.toLowerCase()){
            case "between":{StringBuilder valueText = new StringBuilder();
                String values = conditionRow.locator("xpath=.//div[3]//div[@class='text-eclipse']").textContent();
                List<String> inputData = ff.filterInputValues.stream().sorted().collect(Collectors.toList());
                for(int i=0;i<inputData.size();i++){
                    Assert.assertTrue(values.contains(inputData.get(i)), "Value "+inputData.get(i)+" not found in criteria section" );
                }
            }}
         }

    public Locator conditionStatement(int conditionId){
        Locator conditionRow = page.locator("xpath=(//div[@class='bottom-drawer open']//div[@id='criteriaFilter"+(conditionId)+"']/div)[3]/div");
        return conditionRow;
    }

    public Locator conditionStatementGraph(int conditionId){
        Locator conditionRow = page.locator("xpath=(//div[@class='bottom-drawer open']//div[@id='criteriaFilter"+(conditionId)+"']/div/div)[3]");
        return conditionRow;
    }

    public void AndIClickOnViewCriteriaButton(){
        buttonViewCriteria.click();
    }

}
