package PlaywrightPageObject;

import org.bson.assertions.Assertions;
import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;

import io.qameta.allure.Allure;


public class SavedFilters_Actions extends BasePageActions {
   
	 public SavedFilters_Actions(Page page) {
	        super(page);
	    }

    // Locators
    private final String saveCriteriaBtn = "//button[contains(text(),'Save Criteria')]";
    private final String savePopupTitle = "//div[@role='dialog']//h3[normalize-space(text())='Save Criteria']";
    private final String criteriaNameInput = "//input[@class='ffi-full']";
    private final String criteriaDescInput = "//textarea[@class='ffi-full']";
    private final String saveBtnPopup = "//div[contains(@class, 'MuiDialog-paper')]//button[@class='button primary cta' and normalize-space(text())='Save']";
    private final String cancelBtnPopup = "//div[contains(@role,'dialog')]//button[contains(text(),'Cancel')]";

  //  private final String savedCriteriaDropdown = "//button[contains(text(),'Saved Criteria')]";
    private final String savedCriteriaList = "//button[normalize-space(text())='Saved Criteria']";
    private final String toastMessage = "//div[@role='alert']//div[normalize-space(text())='Your criteria has been saved successfully!']";

    
    private String savedCriteriaDropdown = "//button[.//text()[contains(.,'Saved Criteria')]]"; // update this locator
    private String savedCriteriaOption = "div.MuiPopover-paper";
   
    // Navigate to Dashboard saved filter
    private final String savedFiltersSection = "//h3[text()='Saved Filters']";
    private final String firstSavedFilter = "((//h3[normalize-space()='Saved Filters']/ancestor::div[contains(@class,'custom-accordion')]//div[contains(@class,'sidebar-saved-list')])[1]//div[contains(@class,'flex space-between pb-12 mb-12 bdr-light')])[1]";
    private final String resultsPage = "//div[@class='site-breadcrumb dark']"; // Update if your results page has a specific selector

    // Navigate to screener if needed
    public void navigateToScreener(String domain) {
        page.navigate(domain + "screener/companies");
        page.waitForLoadState();
    }

    // Click on Save Criteria button
    public void clickSaveCriteriaButton() {
        page.locator(saveCriteriaBtn).click();
        page.waitForSelector(savePopupTitle);
    }

    // Fill Save Criteria popup
    public void enterCriteriaDetails(String criteriaName, String description) {
        page.locator(criteriaNameInput).fill(criteriaName);
        if (description != null && !description.isEmpty()) {
            page.locator(criteriaDescInput).fill(description);
        }
    }

    public void openSavedCriteriaDropdown() {
    	page.waitForTimeout(5000);
        page.locator(savedCriteriaDropdown).click();
       
    }


    public void selectSavedCriteria(String criteriaName) {
    	openSavedCriteriaDropdown();
    	 Locator option = page.locator(String.format(savedCriteriaOption, criteriaName.trim()));
        option.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        option.click();
    }
   
    
    // Click Save in popup
    public void confirmSaveCriteria() {
        Locator saveBtn = page.locator(saveBtnPopup);
        saveBtn.click();
        page.waitForSelector(toastMessage);
    }

    // Cancel Save
    public void cancelSaveCriteria() {
        page.locator(cancelBtnPopup).click();
        page.waitForSelector(saveCriteriaBtn);
    }

    // Wait until criteria saved successfully toast is visible
    public void verifyCriteriaSavedToast(String expectedMessage) {
        Locator toast = page.locator(toastMessage);
        toast.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage = toast.innerText();
        Allure.step("Verifying toast message. Expected: '" + expectedMessage + "' | Actual: '" + actualMessage + "'");
        System.out.println("🔍 Toast appeared with text: " + actualMessage);
        Assert.assertTrue(
                actualMessage.contains(expectedMessage),
                "❌ Toast message mismatch! Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'"
            );
        System.out.println("✅ Toast message verified successfully!");
    }
    

    // Open dropdown
	/*
	 * public void openSavedCriteriaDropdown() {
	 * page.locator(savedCriteriaDropdown).click();
	 * page.waitForSelector(savedCriteriaList); }
	 * 
	 * // Select a saved criteria by name public void selectSavedCriteria(String
	 * criteriaName) { String optionXpath = String.format(
	 * "//div[contains(@class,'dropdown')]//span[contains(text(),'%s')]",
	 * criteriaName); Locator option = page.locator(optionXpath); option.waitFor(new
	 * Locator.WaitForOptions().setTimeout(5000)); option.click();
	 * page.waitForTimeout(1500); }
	 */
    // Verify that selected criteria appears as active (loaded)
    public boolean isCriteriaApplied(String criteriaName) {
        String appliedXpath = String.format("//button[contains(text(),'%s') or //div[contains(text(),'%s')]]", criteriaName, criteriaName);
        return page.locator(appliedXpath).count() > 0;
    }

    // Utility: check dropdown contains given criteria
    public boolean isCriteriaListedInDropdown(String criteriaName) {
        openSavedCriteriaDropdown();
        String optionXpath = String.format("//div[contains(@class,'dropdown')]//span[contains(text(),'%s')]", criteriaName);
        return page.locator(optionXpath).count() > 0;
    }

    // Optional: delete saved criteria (if implemented)
    public void deleteSavedCriteria(String criteriaName) {
        String deleteBtnXpath = String.format("//div[contains(text(),'%s')]/following-sibling::button[contains(@class,'delete')]", criteriaName);
        if (page.locator(deleteBtnXpath).count() > 0) {
            page.locator(deleteBtnXpath).click();
            page.waitForTimeout(1000);
        }
    }
    
    
    
    public void verifySavedFiltersSectionVisible() {
        page.locator(savedFiltersSection).isVisible();
    } 
    
    public void clickFirstSavedFilter() {
        page.locator(firstSavedFilter).click();
    }
    
    public void verifyResultsPageVisible() {
        page.locator(resultsPage).isVisible();
    }
    
    }

	
