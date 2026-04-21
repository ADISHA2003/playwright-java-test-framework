package PlaywrightPageObject.ScreenerFilterPage;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewCriteriaActions extends BasePageActions {


    public ViewCriteriaActions(Page page) {
        super(page);
    }

    Locator Dropdown_accordion = page.locator("xpath=//div[@class='csfb-main-bottom']/div");
    Locator WhereCondition = page.locator("xpath=(//div[@class='csfb-main-bottom']//div[@id='criteriaFilter0']/div)[1]");
    Locator ClearApplyFilter = page.locator("xpath=//div[@class='csfb-main-bottom']//div[@class='flex']//button[text()='Clear Applied Filters']");
    Locator SaveCriteriaButton = page.locator("xpath=//div[@class='csfb-main-bottom']//div[@class='flex']//button[text()='Save Criteria']");
    Locator ViewResults = page.locator("xpath=//div[@class='csfb-main-bottom']//div[@class='flex']//button[text()='View All Result']");

    public void expandCriteriaSection(){
        if(!Dropdown_accordion.getAttribute("class").contains("opened"))
            Dropdown_accordion.click();
        page.waitForTimeout(1000); // wait for section to expand
    }
    public void collapseCriteriaSection(){
        if(Dropdown_accordion.getAttribute("class").contains("opened"))
            Dropdown_accordion.click();
        page.waitForTimeout(1000); // wait for section to expand
    }

    public Locator conditionStatement(int conditionId){
        Locator conditionRow = page.locator("xpath=(//div[@class='csfb-main-bottom']//div[@id='criteriaFilter"+(conditionId)+"']/div)[3]");
        return conditionRow;
    }

    public Locator conditionStatementGraph(int conditionId){
        Locator conditionRow = page.locator("xpath=(//div[@class='csfb-main-bottom']//div[@id='criteriaFilter"+(conditionId)+"']/div/div)[3]");
        return conditionRow;
    }

    public void AndICanViewButtonsOnCriteriaSection(){
        assertThat(ClearApplyFilter).isVisible();
        assertThat(SaveCriteriaButton).isVisible();
        assertThat(ViewResults).isVisible();
    }

    ScreenerFiltersEnum sfe = new ScreenerFiltersEnum();
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

    public void andIVerifyMultiselectFilterInCriteriaSection(FilterFormat ff){
        Locator conditionRow = conditionStatement(ff.filterPosition);
        if(ff.filterPosition==0){
            assertThat(WhereCondition).isVisible();
        }
        else {
            assertThat(AndOrCondition(conditionRow)).isVisible();
        }
            page.waitForTimeout(2000);
            assertThat(appliedFilter(conditionRow)).isVisible();
            assertThat(conditionOperator(conditionRow)).isVisible();
            assertThat(conditionValue(conditionRow,ff.filterInputValues)).isVisible();

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

    public Locator AndOrCondition(Locator conditionRow){
        return conditionRow.locator("xpath=.//div[1]");
    }
    public Locator appliedFilter(Locator conditionRow){
        return conditionRow.locator("xpath=.//div[2]");
    }
    public Locator conditionOperator(Locator conditionRow){
        return conditionRow.locator("xpath=.//div[3]");
    }
    public Locator conditionValue(Locator conditionRow,List<String> inputData){
        StringBuilder valueText = new StringBuilder();
        for(int i=0;i<inputData.size();i++){
            if(i==0)
                valueText.append(inputData.get(i));
            else
                valueText.append(", ").append(inputData.get(i));
        }
        return conditionRow.locator("xpath=.//div[4]").filter(new Locator.FilterOptions().setHasText(valueText.toString()));
    }

    public void conditionValueGraph(Locator conditionRow,FilterFormat ff){
        switch(ff.filterOperator.toLowerCase()){
            case "between":{StringBuilder valueText = new StringBuilder();
                String values = conditionRow.locator("xpath=./div[4]/div").getAttribute("title");
                List<String> inputData = ff.filterInputValues.stream().sorted().collect(Collectors.toList());
        for(int i=0;i<inputData.size();i++){
            Assert.assertTrue(values.contains(inputData.get(i).replaceAll("\\.?0*$","")), "Value "+inputData.get(i)+" not found in criteria section"+values );
        }
    }}
    }

    public void AndIClickOnViewResultsButton(){
        ViewResults.click();
        page.waitForTimeout(5000); // wait for data table to load
    }




}
