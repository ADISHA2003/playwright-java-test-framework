package WebPageObjectRepo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LeftNavigationBar_Sel_Actions extends BasePageActions{
	
	WebDriver driver; 
	public LeftNavigationBar_Sel_Actions(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	By CONTAINER_LEFT_NAV=By.className("new-nav-overlay") ;
	By BUTTON_MENU =By.cssSelector(".header-logo span svg");
	By ICON_EXPAND =By.xpath("//span[@class='svg-icon fill darkv1 sm']");
	By VERIFY_DROPDOWN_SUBMODULE;
	By VERIFY_ALL_MODULE_TEXT = By.xpath("//div[@class='style_site-accordion__U7hir nav_site-accordion__exg_V']/div");
	By VERIFY_ALL_SUBMODULE_TEXT = By.xpath("//div[@class='style_site-accordion__U7hir nav_site-accordion__exg_V']/div//button");
    By VERIFY_LANDING_PAGE =By.xpath("//div[@class='gradient-primary hero-block negative-mb-box']");
	

	public boolean clickLeftNavMenuICON(){
		WebElement leftNavMenuIcon = getSelElement(BUTTON_MENU);
		if(!checkIfLocatorVisible(CONTAINER_LEFT_NAV)) {
			return clickOnLocator(BUTTON_MENU);
		}
		return false;
	}

	public boolean LeftNaviagationMouseOver() {
		WebElement navigationBar=getSelElement(CONTAINER_LEFT_NAV);
        return mouseMoveToElement(navigationBar);

	}
	
//	public boolean clickPin_Button() {
//		LeftNaviagationMouseOver();
//		return clickOnLocator(BUTTON_PIN);
//	}
//
//	public boolean clickUnpin_Button(){
//		mouseMoveToElement(getSelElement(LOGO_VCC_EDGE));
//		return clickOnLocator(BUTTON_PIN);
//	}
     
	public boolean navigateToModuleSubModule(String module,String subModule, String expectedURL) {
//		clickPin_Button();
//		if(navigateToModule(module,subModule)) {
//			clickAndVerifyURL(getSubModule(subModule), expectedURL);
//			clickonElement(getModule(module));
//			return clickUnpin_Button();
//		}
		return false;
	}
	
	public boolean navigateToModule(String module, String subModule) {
		clickonElement(getModule(module));
		return isElementVisible(getSubModule(subModule));
	}
	
	public WebElement getModule(String module) {
		return getSelElement(By.xpath("//strong[contains(text(), '"+module+"')]"));
	}
	
	public WebElement getSubModule(String subModule) {
		return getSelElement(By.xpath("//button[text()='"+subModule+"']"));
	}
	
       
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


