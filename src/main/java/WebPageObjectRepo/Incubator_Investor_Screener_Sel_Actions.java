package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class Incubator_Investor_Screener_Sel_Actions extends AssetManager_investor_Screener_Sel_Actions {

    WebDriver driver;

    public Incubator_Investor_Screener_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By incubatorScreener = By.xpath("(//div[@class='grid-col active'])[3]");

    //Method to verify dropdownSelection
    public boolean verifyDropdownSelection(String expectedText) {
        clickonElement(getSelElement(dropdownSelection));
        boolean isTextMatched = clickonElement(getSelElement(incubatorScreener));
        if (isTextMatched) {
            clickOnLocator(startScreening);
            waitForElementVisible(secondScreenVerification);
        }
        return isTextMatched;
    }


    // Private method to return a list of FAQ questions
    private List<String[]> getFAQQuestions() {
        List<String[]> faqList = new ArrayList<>();

        // Define individual FAQ questions
        String faq1Question = "What is an incubator?";
        String faq2Question = "How do incubators support startups?";
        String faq3Question = "How long do startups stay in an incubator?";
        String faq4Question = "How do incubators select startups?";


        // Store questions and their xpaths in arrays
        String[] questions = {faq1Question, faq2Question, faq3Question, faq4Question};
        By[] questionXpaths = {FAQ1question, FAQ2question, FAQ3question, FAQ4question};

        // Iterate through the list of FAQ questions
        for (int i = 0; i < questions.length; i++) {
            String questionText = getSelElement(questionXpaths[i]).getText();
            faqList.add(new String[]{questionText, questions[i]});
        }

        return faqList;
    }

}

