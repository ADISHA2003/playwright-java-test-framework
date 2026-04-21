package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class company_Screener_Sel_Actions extends BasePageActions{

    WebDriver driver ;
    public company_Screener_Sel_Actions(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    By pageHeading= By.cssSelector(".page-heading");
    By dropdownSelection = By.cssSelector(".button.select");
    By startScreening = By.xpath("//button[normalize-space()='Start Screening']");
    By secondScreenVerification= By.cssSelector(".page-heading.md.mb-0");
    By tutorial= By.xpath("//button[normalize-space()='Watch Tutorial']");
    By savedScreening= By.xpath("//strong[@class='fs-16']");
    By FAQheading = By.xpath("//h2[normalize-space()='Frequently Asked Questions']");
    By FAQ1question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 14]");
    By FAQ2question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 15]");
    By FAQ3question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 16]");
    By FAQ4question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 17]");
    By FAQ5question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 18]");
    By FAQ6question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 19]");
    By FAQ7question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 20]");
    By FAQ8question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 21]");
    By FAQ9question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 22]");
    By FAQ10question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 23]");
    By FAQ11question= By.xpath("(//div[contains(@class,'grid-col')])[position() = 24]");

    //Method to verify the page heading
    public boolean verifyPageHeading(String expectedText) {
        String actualText = getSelElement(pageHeading).getText();
        return actualText.equals(expectedText);
    }

    //Method to verify dropdownSelection
    public boolean verifyDropdownSelection(String expectedText) {
        boolean isTextMatched = getSelElementTextAndCompare(dropdownSelection, expectedText);
        if (isTextMatched) {
            clickOnLocator(startScreening);
            waitForElementVisible(secondScreenVerification);
        }
        return isTextMatched;
    }

    //Method to verify StartScreening
    public boolean verifyStartScreening(String expectedText) {
        clickOnLocator(startScreening);
        return getSelElementTextAndCompare(secondScreenVerification, expectedText);

    }

    //Method to verify tutorial presence
    public boolean verifyTutorialPresence() {
        return isElementVisible(tutorial);

    }

    //Method to verify savedscreen presence
    public boolean verifySavedScreenPresence() {
    return isElementVisible(savedScreening);
    }

    //Method to verify FAQ presence
    public boolean verifyFAQPresence() {
        waitForElementVisible(FAQheading);
        return isElementVisible(FAQheading);

    }

    // Method to verify individual FAQ questions with expected questions
    public boolean verifyFAQ() {

        // Get the list of FAQ questions
        List<String[]> faqList = getFAQQuestions();

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

    // Private method to return a list of FAQ questions
    private List<String[]> getFAQQuestions() {
        List<String[]> faqList = new ArrayList<>();

        // Define individual FAQ questions
        String faq1Question = "How do I use the filters to find potential investments?";
        String faq2Question = "Can I customize the filters, such as adding, removing, or reordering them?";
        String faq3Question = "Can I save my screener searches for future use?";
        String faq4Question = "How do I view my screened results?";
        String faq5Question = "Can I further filter my screened results?";
        String faq6Question = "On the results screen, I want to only view the columns that are important to me. Can this be done?";
        String faq7Question = "Can I remove certain companies from the results page?";
        String faq8Question = "What related information about the companies can I get from the result data set? Is it possible to export the list of filtered companies?";
        String faq9Question = "Can I add companies to my saved lists?";
        String faq10Question = "Can I compare multiple companies side by side?";
        String faq11Question = "Can I set alerts for new companies that match my criteria?";


        // Store questions and their xpaths in arrays
        String[] questions = {faq1Question, faq2Question, faq3Question, faq4Question, faq5Question, faq6Question, faq7Question, faq8Question, faq9Question, faq10Question, faq11Question};
        By[] questionXpaths = {FAQ1question, FAQ2question, FAQ3question, FAQ4question, FAQ5question, FAQ6question, FAQ7question, FAQ8question, FAQ9question, FAQ10question, FAQ11question};

        // Iterate through the list of FAQ questions
        for (int i = 0; i < questions.length; i++) {
            String questionText = getSelElement(questionXpaths[i]).getText();
            faqList.add(new String[]{questionText, questions[i]});
        }

        return faqList;
    }
}
