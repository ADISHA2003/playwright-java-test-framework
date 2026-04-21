package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompanyScreener_DataTable_Sel_Actions extends CompAnalysis_DataTable_Sel_Actions{

    public CompanyScreener_DataTable_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    By filters=By.xpath("//div[@class='flex mb-16']");

    public boolean verifyfilters() {
        return isElementVisible(filters);
    }




}
