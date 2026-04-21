package WebPageObjectRepo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CompanyScreeningFilter_Sel_Actions extends BasePageActions {
		
	public CompanyScreeningFilter_Sel_Actions(WebDriver driver) {
		super(driver);
		
	}
    By BREAD_CRUMB = By.xpath("//div[@class='site-breadcrumb']");
	By TEXT_COMPANY_SCREENER= By.cssSelector(".page-heading md mb-0");
	By INPUT_SEARCH_FOR_FILTERS =By.xpath("//input[@placeholder='Search Filter']");
	By VERIFY_ALL_FILTER_TABS =By.xpath("//strong[@class='mr-2']");
	By VERIFY_ALL_SUBFILTERS_TAB=By.xpath("//li[@class='removable']/button");
	By ALL_COMPANY_TYPE_CHECHBOXES =By.xpath("//input[@class=\"mr-4\"]");
	By TEXT_CRITERIA = By.xpath("//h2[text()='Criteria']");
	By TEXT_RESULT_PREVIEW = By.xpath("//h3[@class=\"box-title mb-0\"]");
	By BUTTON_APPLY_FILTER = By.xpath("//button[text()='Apply Filter']");
	By BUTTON_CLEAR_FILTER =By.xpath("//button[@class=\"button-type-text light mr-12 fs-12\"]");
	By BUTTON_SAVE_CRITERIA =By.xpath("//button[@class=\"button-type-text primary\"]");
	By BUTTON_VIEW_RESULTS =By.xpath("//button[text()='View Results']");
	By BUTTON_MANAGE_FILTERS =By.xpath("//button[@class=\"button secondary sm flex-align-right\"]");
	By BUTTON_VIEW_ALL =By.xpath("//button[text()='View all']");
	By FRONT_MOVE_SLIDER=By.xpath("//span[@class=\"MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-5s1qf1\"]");
	By BACK_MOVE_SLIDER=By.xpath("//span[@class=\"MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-5s1qf1\"][2]");
	By VERIFY_INPUT_BOX_SELECT_VALUE_FRONT=By.xpath("(//input[@class=\"input-range\"])[1]");
	By VERIFY_INPUT_BOX_SELECT_VALUE_BACK=By.xpath("(//input[@class=\"input-range\"])[2]");
	By SELECT_FINANCIAL_YEAR_DROPDOWN=By.xpath("//div[@class=\"mr-16\"]/select");
	By SELECT_FINANCIAL_STATEMENT_TYPE=By.xpath("//label[text()='Financial Statement type']/following-sibling::select");
	By SELECT_ISbETWEEN_DROPDOWN_SECTION=By.xpath("//select[@class='select']");
	
	public boolean verifyBreadcrumb(String expectedText) {
        return getSelElementTextAndCompare(BREAD_CRUMB, expectedText);
    }
	
	public boolean VerifyTextCompanyScreener(String expectedText) {	
	    return getSelElementTextAndCompare(TEXT_COMPANY_SCREENER,expectedText);
		}
	
	
	 public boolean verifyTextCompanyScreenerPageHeading(String expectedText) {
	        String actualText = getSelElement(TEXT_COMPANY_SCREENER).getText();
	        return actualText.equals(expectedText);
	    }

	public boolean verifyAndClickFilterTabToSubFilter(String filterTab, String subFilterTab) {
	    boolean filterTabVisible = isElementVisible(getFilterTab(filterTab));
	    boolean subFilterTabVisible = isElementVisible(getSubFilterTab(subFilterTab));
	    if (filterTabVisible && subFilterTabVisible) {
	    return clickonElement(getSubFilterTab(subFilterTab));
	    }
	    return false;
	}
	
	public WebElement getFilterTab(String filter) {
	      return getSelElement(By.xpath("//strong[text()='"+filter+"']"));
	}
	
	public WebElement getSubFilterTab(String subFilter) {		
	      return getSelElement(By.xpath("//button[text()='"+subFilter+"']"));
	}
	
	public String isCheckboxSelected(String checkboxText) {
	    WebElement element = getSelElement(By.xpath("//label[text()='" + checkboxText + "']"));
	    
	    System.out.println(element.isSelected());
	   // if (!isSelectedCheckBox) {
	        clickonElement(element);
	        forceWait(3);        
	        //isSelectedCheckBox =isElementSelected(element);
	   // }
	   //     JavascriptE
	        System.out.println(getAttribute(element, "checked"));
	    return getAttribute(element, "checked");
	}
	public boolean verifyAndSelectRadioButton(String radioButtonText) {
	    WebElement radioButton = getSelElement(By.xpath("//label[text()='"+radioButtonText +"']"));
	    
//	    boolean isSelectedrRadioButton = isElementSelected(radioButton);
//	    if (!isSelectedrRadioButton) {
	        clickonElement(radioButton);
//	    }
	    return isElementSelected(radioButton);
	}
	
	public boolean clickApplyButton() {
		
      return clickOnLocator(BUTTON_APPLY_FILTER);
	} 

	public boolean verifyClickClearFilterButton() {
		boolean status=isElementVisible(BUTTON_CLEAR_FILTER);
		if(status==true) {
			clickOnLocator(BUTTON_CLEAR_FILTER);			
		}
         return true;
	}
	
	public boolean verifyClickSaveCriteriaButton() {
		boolean status=isElementVisible(BUTTON_SAVE_CRITERIA);
		if(status==true) {
			clickOnLocator(BUTTON_SAVE_CRITERIA);			
		}
         return true;
	}
		
	public boolean verifySearchFilterSection(WebElement Element) {
	boolean status = isElementVisible(TEXT_COMPANY_SCREENER);
	if(status==true) {
		String screaning = getTextBoxValue(Element);	
	}
	return true;
	}
	public boolean verifyInputSearchFilterBox(String expectedText) {
	return 	getSelElementTextAndCompare(INPUT_SEARCH_FOR_FILTERS,expectedText );
	 	
	}
	
	public boolean verifyResultPreviewSection() {
    boolean status =isElementVisible(BUTTON_VIEW_RESULTS);
    if(status==true) {
    	clickOnLocator(BUTTON_VIEW_RESULTS);	
    }
    return true;
	}

	public boolean  verifyCriteriaSection(String expectedText) {
	return getSelElementTextAndCompare(TEXT_CRITERIA, expectedText);	
	}
	
	public boolean verifyClickViewAllButton() {
		boolean status=isElementVisible(BUTTON_VIEW_ALL);
		if(status==true) {
			clickOnLocator(BUTTON_VIEW_ALL);			
		}
         return true;
	}
	public boolean verifyManageFilterButton(String expectedText) {
		return getSelElementTextAndCompare(BUTTON_MANAGE_FILTERS,expectedText);
	}
	
	public boolean moveSliderFrontAndBack(int xpercentage, int ypercentage) {
	
			   frontMoveSlider(xpercentage);	
	           backMoveSlider(ypercentage);    
	         return true;
	}
	
	 public boolean frontMoveSlider( int xpercentage) {
	       
		 WebElement element= getSelElement(FRONT_MOVE_SLIDER);
		return clickAndHoldSlider(element, xpercentage);          
	    }

	 public boolean backMoveSlider(int ypercentage) {
		 
			 WebElement element= getSelElement(BACK_MOVE_SLIDER);
	         return clickAndHoldSlider(element, ypercentage);      
	    }
	 
	 
	 public boolean verifyAndSelectFinancialyearDropdown(String value) {
		 
		 WebElement element= getSelElement(SELECT_FINANCIAL_YEAR_DROPDOWN);
     	 selectDropDownByVisibleText(element, value); 
		 return true;
	 }
	 
	 public boolean VerifyAndselectFinancialStatementTypeDropdown(String value) {
		 
		 WebElement element= getSelElement(SELECT_FINANCIAL_STATEMENT_TYPE);
     	 selectDropDownByVisibleText(element, value); 
		 return true;
		 
	 }
	 
  public boolean VerifyAndSelectIsBetweenDropDown(String value) {
	  
	  WebElement element= getSelElement(SELECT_ISbETWEEN_DROPDOWN_SECTION);
  	  selectDropDownByVisibleText(element, value); 	  
	  return true;
  }
	
}
