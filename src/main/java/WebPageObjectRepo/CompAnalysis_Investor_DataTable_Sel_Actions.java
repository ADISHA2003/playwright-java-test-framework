package WebPageObjectRepo;

import Constants.PageUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class CompAnalysis_Investor_DataTable_Sel_Actions extends CompAnalysis_DataTable_Sel_Actions{

    public CompAnalysis_Investor_DataTable_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    By investortab=By.xpath("(//a[contains(@class,'button')])[3]");


    public boolean goToInvestorTab(String expectedText) {
        clickOnLocator(investortab);
        return verifyActiveTab(expectedText);

    }


}
