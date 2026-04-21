package WebPageObjectRepo;

import Constants.PageUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompAnalysis_Professional_DataTable_Sel_Actions extends CompAnalysis_DataTable_Sel_Actions{

    public CompAnalysis_Professional_DataTable_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    By professionaltab=By.xpath("(//a[contains(@class,'button')])[5]");


    public boolean goToProfessionalTab(String expectedText) {
        clickOnLocator(professionaltab);
        return verifyActiveTab(expectedText);

    }

}
