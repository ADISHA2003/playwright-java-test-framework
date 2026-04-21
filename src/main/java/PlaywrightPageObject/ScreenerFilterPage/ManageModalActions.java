package PlaywrightPageObject.ScreenerFilterPage;

import PlaywrightPageObject.BasePageActions;
import Utilities.JsonReaderService;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.WaitForSelectorState;

import Constants.FilePath;

import io.qameta.allure.Allure;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.ArrayList;

import java.util.List;

public class ManageModalActions extends BasePageActions {
    private Locator modal=page.locator("xpath=//div[@role='dialog']");
    private Locator subGroupList= modal.locator(".subgroup-list-selector");
    private  Locator filterList= modal.locator(".filter-list-selector");
    private  Locator defaultFilter= modal.locator(".filter-default-selector");
    private  Locator groupList = page.locator("//div[@class=\"dialog-content cs-filters-window\"]//div[contains(@class,'MuiAccordionSummary-root')]");
    private Locator buttonReset = page.locator("xpath=//button[@id=\"restore-filters\"]");
    private Locator buttonResetConfirmDialogue = page.locator("xpath=(//div[@role='tooltip']//button)[2]");
    public ManageModalActions(Page page) {
        super(page);
            }

    public List<String> getGroupNames() {
        return groupList.allInnerTexts();
    }

    public List<String> getSubGroupNames(int groupIndex) {
        return subGroupList.nth(groupIndex).allInnerTexts();
    }

    public List<String> getFilterNames(int groupIndex, int subGroupIndex) {
        return filterList.nth(groupIndex).locator(":scope > .subgroup").nth(subGroupIndex).allInnerTexts();
    }

    public void andIClickOnResetButton() {
        assertThat(buttonReset).isVisible();
        buttonReset.click();
        assertThat(buttonResetConfirmDialogue).isVisible();
        buttonResetConfirmDialogue.click();
    }

    public String getDefaultFilter() {
        return defaultFilter.innerText();
    }

    public void selectGroup(String groupName) {
        groupList.locator("text=" + groupName).click();
    }

    public void selectSubGroup(String subGroupName) {
        subGroupList.locator("text=" + subGroupName).click();
    }

    public void selectFilter(String filterName) {
        filterList.locator("text=" + filterName).click();
    }

    public boolean verifyGroups(List<String> expectedGroups) {
        List<String> actualGroups = getGroupNames();
        return actualGroups.equals(expectedGroups);
    }

    public boolean verifySubGroups(int groupIndex, List<String> expectedSubGroups) {
        List<String> actualSubGroups = getSubGroupNames(groupIndex);
        return actualSubGroups.equals(expectedSubGroups);
    }

    public boolean verifyFilters(int groupIndex, int subGroupIndex, List<String> expectedFilters) {
        List<String> actualFilters = getFilterNames(groupIndex, subGroupIndex);
        return actualFilters.equals(expectedFilters);
    }

    public boolean verifyDefaultFilter(String expectedDefault) {
        return getDefaultFilter().equals(expectedDefault);
    }

    public boolean thenIVerifyManageModalPopupVisible() {

        modal.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));

        if (!modal.isVisible()) {
            System.out.println("WARNING: Manage modal popup is not visible");
            return false;
        }

        return true;
    }

    public void thenIVerifyDefaultFiltersInCorrectOrderOf(String filterType,String tile) {
       switch(filterType.toLowerCase()){
           case "companies":
                verifyFilterAsWellAsDefaultState(FilePath.Screening_TestFolder+"CompScreeningManageFilters_TestData.json");
               break;
                   
           case "investor":
               switch (tile.toLowerCase()) {
                   case "Asset Manager":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "AssetManagerScreeningManageFilters_TestData.json");
                       break;
                   case "Fund":
                	   verifyFilterAsWellAsDefaultState (FilePath.Screening_TestFolder + "FundScreeningManageFilters_TestData.json");
                       break;
                   case "Limited Partner":
                	   verifyFilterAsWellAsDefaultState(FilePath.Screening_TestFolder + "LimitedPartnerManageFilters_TestData.json");
                       break;
                   case "Family Office":
                	   verifyFilterAsWellAsDefaultState (FilePath.Screening_TestFolder + "FamilyOfficeManageFilters_TestData.json");
                       break;
               }
               break;
           case "deal":
               switch (tile.toLowerCase()) {
                   case "All Deals":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "AllDealsManageFilters_TestData.json");
                       break;
                   case "Private Equity Investment":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "PEIManageFilters_TestData.json");
                       break;
                   case "Merger and Acquisition":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "M&AManageFilters_TestData.json");
                       break;
                   case "Private Equity Exits":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "PrivateEquityExitsManageFilters_TestData.json");
                       break;
                   case "Equity Capital Market":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "ECMManageFilters_TestData.json");
                       break;
                   case "Debt Transaction":
                	   verifyFilterAsWellAsDefaultState ( FilePath.Screening_TestFolder + "DebtTransManageFilters_TestData.json");
                       break;
               }
               break;
           default:
               System.out.println("Invalid filter type provided: " + filterType);
               break;
       }
    }

    public void verifyFilterAsWellAsDefaultState(String jsonFilePath){
        System.out.println(jsonFilePath);

        assertGenerateXPathsFromJSON(jsonFilePath);

    }

    public void expandGroupOnModal(String groupName){

        Locator groupHeading = page.locator("//div[@class='dialog-content cs-filters-window']//div[contains(@class,'MuiAccordionSummary-root')]//div[contains(.,'"+groupName+"') and @class='flex flex-grow']/../..");
        groupHeading.scrollIntoViewIfNeeded();
        if(groupHeading.getAttribute("aria-expanded").contains("false")){
            groupHeading.click();

        }
        System.out.println("expanded group"+groupName);
    }

    public void collapseGroupOnModal(String groupName){
        Locator groupHeading = page.locator("//div[@class='dialog-content cs-filters-window']//div[contains(@class,'MuiAccordionSummary-root')]//div[contains(.,'"+groupName+"') and @class='flex flex-grow']/../..");
        groupHeading.scrollIntoViewIfNeeded();
        if(!groupHeading.getAttribute("aria-expanded").contains("true")){
            groupHeading.click();
        }
    }

    public Locator assertGenerateXPathsFromJSON(String jsonFilePath) {

        Locator filterxpath=null;

        try {
            String jsonString = JsonReaderService.readTest_JsonFile(jsonFilePath);
            JSONArray data = new JSONArray(jsonString);

            // Iterate through the main groups
            for (int i = 0; i < data.length(); i++) {
                JSONObject group = data.getJSONObject(i);
                String groupName = group.optString("groupName");
                if (groupName.isEmpty()) {
                    continue;
                }
                expandGroupOnModal(groupName);

                // Iterate through subgroups
                JSONArray subGroups = group.optJSONArray("subGroups");
                if (subGroups == null) {
                    continue;
                }
                
                for (int j = 0; j < subGroups.length(); j++) {
                    JSONObject subgroup = subGroups.getJSONObject(j);
                    String subGroupName = subgroup.optString("subGroupName", subgroup.optString("SubGroupName"));
                    if (subGroupName.isEmpty()) {
                        continue;
                    }

                    // Check for sub-categories
                    JSONArray subCategories = subgroup.optJSONArray("subCategories");
                    if (subCategories != null) {
                        for (int k = 0; k < subCategories.length(); k++) {
                            JSONObject subcategory = subCategories.getJSONObject(k);
                            String subCategoryName = subcategory.optString("subCategoryName");
                            if (subCategoryName.isEmpty()) {
                                continue;
                            }
                            

                            // Iterate through filters
                            JSONArray filters = subcategory.optJSONArray("filters");
                            if (filters == null) {
                                continue;
                            }
                            Locator filterCategoryXPath = page.locator("//div[@class='dialog-content cs-filters-window']//div[contains(.,'"+groupName+"') and contains(@class,'MuiPaper-root')]//div[contains(.,'"+
                                subGroupName+"') and contains(@class,'MuiAccordionDetails-root')]//div[contains(.,'"+subCategoryName+"') and @class='filter-buttons-list']");
                            filterCategoryXPath.hover();
                            for (int l = 0; l < filters.length(); l++) {
                                JSONObject filterItem = filters.getJSONObject(l);
                                String filterName = filterItem.optString("filterName");
                                if (filterName.isEmpty()) {
                                    continue;
                                }
                                filterxpath = filterCategoryXPath.locator("//button[text()='"+filterName+"']");
                                filterxpath.hover();
                                assertThat(filterxpath).isVisible();
                                Allure.step("Verifying filter: " + filterName + " in sub-category: " + subCategoryName + ", sub-group: " + subGroupName + ", group: " + groupName+" default"+ filterItem.optBoolean("isDefault", false));
                                if(!filterItem.optBoolean("isDefault")){
                                    assertThat(filterxpath).containsClass("false");
                                }else
                                    assertThat(filterxpath).containsClass("active-filter");
                            }
                        }
                    } else { // If no sub-categories, iterate through filters directly
                        JSONArray filters = subgroup.optJSONArray("filters");
                        if (filters == null) {
                            continue;
                        }
                        for (int l = 0; l < filters.length(); l++) {
                            JSONObject filterItem = filters.getJSONObject(l);
                            String filterName = filterItem.optString("filterName");
                            if (filterName.isEmpty()) {
                                continue;
                            }
                            filterxpath= page.locator("//div[@class='dialog-content cs-filters-window']//div[contains(.,'"+groupName+"') and contains(@class,'MuiPaper-root')]//div[contains(.,'"+subGroupName+"') and contains(@class,'MuiAccordionDetails-root')]//button[text()='"+filterName+"']");
                            filterxpath.hover();
                            assertThat(filterxpath).isVisible();
                            Allure.step("Verifying filter: " + filterName + " in sub-group: " + subGroupName + ", group: " + groupName+" default"+ filterItem.optBoolean("isDefault", false));
                            if(!filterItem.optBoolean("isDefault")){
                                assertThat(filterxpath).containsClass("false");
                            }else
                                assertThat(filterxpath).containsClass("active-filter");
                        }
                    }
                }
                collapseGroupOnModal(groupName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filterxpath;
    }

}