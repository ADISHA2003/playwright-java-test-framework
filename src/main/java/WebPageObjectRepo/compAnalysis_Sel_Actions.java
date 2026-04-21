package WebPageObjectRepo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class compAnalysis_Sel_Actions extends BasePageActions {

    WebDriver driver;

    public compAnalysis_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    By breadcrumb = By.xpath("//div[@class='site-breadcrumb dark']");
    By searchBox = By.xpath(("//input[@placeholder=\"Try Searching for 'Swiggy'\"]"));
    By comparison = By.xpath("//button[normalize-space()='Start Comparison']");
    By tutorial = By.xpath("//button[normalize-space()='Watch Tutorial']");
    By FAQ = By.cssSelector(".section-title.lg.mb-0");
    By searchdropdown = By.xpath("//div[@class='dropdown-card dropdown-type-list max-h']");
    By listPreviousCompAnalysis = By.xpath("//ul[@class='list-reset']");
    By FAQheading = By.xpath("//h2[normalize-space()='Frequently Asked Questions']");
    By FAQ1question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 14]");
    By FAQ2question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 15]");
    By FAQ3question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 16]");
    By FAQ4question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 17]");
    By companyverify=By.id("company-name");

    // Method to verify the title of the page
    public boolean verifyPageTitle(String expectedTitle) {

            String actualTitle = getTitle();

            // Compare the retrieved title with the expected title
            return actualTitle.equals(expectedTitle);

    }

    // Method to verify breadcrumb
    public boolean verifyBreadcrumb(String expectedText) {
        return getSelElementTextAndCompare(breadcrumb, expectedText);
    }


    // Method to verify search box presence
    public boolean verifySearchBoxPresence() {
        return isElementVisible(searchBox);

    }

    // Method to verify search placeholder
    public boolean verifySearchPlaceholder(String expectedPlaceholder) {
        return verifyPlaceHolderText(searchBox,expectedPlaceholder);
    }

    // Method to search any company, verify search dropdown, click on the company and submit
    public boolean searchAndSelectCompany(String companyName) {


            // Locate the search box element and enter the company name
            sendKeysWithLocator(searchBox,companyName);

            // Wait for the search dropdown to appear
            waitForElementVisible(searchdropdown);

            // Locate the company in the search dropdown and click on it
            String companyXPath = String.format("//h4[normalize-space(text())='%s']", companyName);
            WebElement companyElement = getSelSubElement(searchdropdown, By.xpath(companyXPath));
            clickonElement(companyElement);

            // Submit the search
            waitForElementClickable(comparison);
            clickOnLocator(comparison);
            waitForElementVisible(companyverify);
            return isElementVisible(companyverify);
    }

    // Method to verify tutorial button presence
    public boolean verifyTutorialButtonPresence() {
            waitForElementVisible(tutorial);
            return isElementVisible(tutorial);

    }

    //Method to verify previous competitive analysis

    public String checkListCompAnalysis() {

            // Locate the list of previous competitive analyses
            List<WebElement> compAnalysisList = getElements(listPreviousCompAnalysis);

            // Check if the list is empty
            if (compAnalysisList.isEmpty()) {
                return "Previous competitive analysis will be shown here.";
            } else {
                // Verify the entries format
                String regex = "^[a-zA-Z0-9_]+_\\d{2}-\\d{2}-\\d{2}_\\d{2}:\\d{2}$";
                for (WebElement entry : compAnalysisList) {
                    String entryText = entry.getText();
                    if (!entryText.matches(regex)) {
                        return "An entry does not match the required format: " + entryText;
                    }
                }
                return "Entries are available and verified";
            }
        }

    // Method to verify individual FAQ questions with expected questions
    public boolean verifyFAQ() {
            // Define the expected FAQ heading
            String expectedFAQHeading = "Frequently Asked Questions";

            // Retrieve the actual FAQ heading using its xpath
            By faqHeadingXpath = By.xpath("//h2[normalize-space()='Frequently Asked Questions']");
            String actualFAQHeading = getSelElement(faqHeadingXpath).getText();

            // Verify the actual FAQ heading with the expected FAQ heading
            if (!actualFAQHeading.equals(expectedFAQHeading)) {
                return false;

            }

            // Get the list of FAQ questions and answers
            List<String[]> faqList = getFAQQuestionsAndAnswers();

            // Iterate through the list of FAQ questions and verify them
            for (String[] faq : faqList) {
                String questionText = faq[0];
                String expectedQuestion = faq[1];
               
                // Verify the question with the expected question
                if (!questionText.equals(expectedQuestion)) {
                    return false;
                }
            }
            return true;

    }

    // Private method to return a list of FAQ questions and answers
    private List<String[]> getFAQQuestionsAndAnswers() {
        List<String[]> faqList = new ArrayList<>();

        // Define individual FAQ questions
        String faq1Question = "How does VCC Edge competitive analysis work?";
        String faq2Question = "What is Edge Score and how it is calculated ?";
        String faq3Question = "What is a business model of a company ?";
        String faq4Question = "Can I request for competitors of a company in case I am not able to find any competitor ?";

        // Store questions and their xpaths in arrays
        String[] questions = {faq1Question, faq2Question, faq3Question, faq4Question};
        By[] questionXpaths = {FAQ1question, FAQ2question, FAQ3question, FAQ4question};

        // Iterate through the list of FAQ questions and answers
        for (int i = 0; i < questions.length; i++) {
            String questionText = getSelElement(questionXpaths[i]).getText();
            faqList.add(new String[]{questionText, questions[i]});
        }

        return faqList;
    }
    public void waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}