package PlaywrightPageObject;

import com.microsoft.playwright.*;

import co.elastic.clients.elasticsearch.shutdown.Type;

public class Top_Nav_Bar_Plt_Actions {	
	
	public static void main(String[] args) {
	
		        Playwright playwright = Playwright.create() ;
		            Browser browser = playwright.chromium().launch(
		            			new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
		            Page page = browser.newPage();
		            page.navigate("https://dev.vccedge.com/login.php?refere_url=Y29tcGFueS9wcm9maWxlLzM1ODE=");
		            System.out.println(page.title());
		            page.locator("#edit-name").type("navneet.yadav@hindustantimes.com");
		            page.locator("#edit-pass").type("nA876543@#");
		            page.waitForTimeout(3000);
		            page.locator("//button[text()='Login']").click();
		           // page.close();
		           // browser.close();
		            //playwright.close();
		        
		    }
	
	
	
	
	
	
	
	
	
	
	
	

}
