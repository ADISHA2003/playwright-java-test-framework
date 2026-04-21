package FactoryClasses;

import Constants.FilePath;
import Utilities.PropertyFileReaderService;
import com.microsoft.playwright.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Paths;
import java.util.Arrays;

public class BrowserInstanceFactory {

    private WebDriver driver;
    public WebDriver getSeleniumDriver(String browser){
        switch (browser.toLowerCase()){

            case "chrome":return getChromeDriver();
            case "firefox":return getFireFoxDriver();
            case "edge":return getEdgeDriver();
            default: return null;
        }
    }


    public BrowserContext getPlaywrightDriver(String browserType, Playwright playwright, Browser.NewContextOptions options){
            Browser browser= null;
            switch (browserType){
                case "chrome":
                case "Chrome":
                case "CHROME":       String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Update this path as needed
                    Boolean Headless = Boolean.parseBoolean(PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "headless"));
                    if(Headless!=true) browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setExecutablePath(Paths.get(chromePath)).setArgs(Arrays.asList("--start-maximized")));
                    else browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
                        break;
                case "firefox":
                case "Firefox":
                case "FIREFOX": browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));break;
                case "webkit":
                case "Webkit":
                case "WEBKIT": browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));break;
                default : break;
            }

//           return  browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(null));

        return browser.newContext(options);
    }


    private WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        String flag = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG,"headless");
        if(flag.equals("true"))options.addArguments("--headless=new");
        WebDriverManager.chromedriver()
                .setup();
        return new ChromeDriver(options);

    }

    private WebDriver getFireFoxDriver(){
        WebDriverManager.firefoxdriver()
                .setup();
        return new FirefoxDriver();
    }

    private WebDriver getEdgeDriver(){
        WebDriverManager.edgedriver()
                .setup();
        return new EdgeDriver();
    }

}
