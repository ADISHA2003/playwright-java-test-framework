package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Investor_Asset_manager_screening_Filter_Sel_Actions extends CompanyScreeningFilter_Sel_Actions{
     WebDriver driver;
	 public Investor_Asset_manager_screening_Filter_Sel_Actions(WebDriver driver) {
		super(driver);
		this.driver=driver;
			
	 } 
	
	 By INVESTOR_BREAD_CRUMB = By.xpath("//div[@class='site-breadcrumb']");
	 By TEXT_INVESTOR_SCREENER_ASSET_MANAGER= By.xpath("//h1[@class='page-heading md mb-0']");
	
	 
	 public boolean verifyInvestorBreadcrumb (String expectedText) { 	 
		 return getSelElementTextAndCompare(INVESTOR_BREAD_CRUMB, expectedText); 
		 }
		
		 public boolean VerifyTextInvestorAssetManagerScreener(String expectedText) {	
			    return getSelElementTextAndCompare(TEXT_INVESTOR_SCREENER_ASSET_MANAGER,expectedText);
				}
	
	
	
	
	
	
	
	
	

}
