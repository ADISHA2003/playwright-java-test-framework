package WebPageObjectRepo;

import Constants.PageUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompAnalysis_Deals_DataTable_Sel_Actions extends CompAnalysis_DataTable_Sel_Actions{

    public CompAnalysis_Deals_DataTable_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    By dealtab=By.xpath("(//a[contains(@class,'button')])[4]");


    public boolean goToDealsTab(String expectedText) {
        clickOnLocator(dealtab);
        return verifyActiveTab(expectedText);

    }
}
