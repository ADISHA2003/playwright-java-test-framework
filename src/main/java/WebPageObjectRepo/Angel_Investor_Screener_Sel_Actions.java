package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class Angel_Investor_Screener_Sel_Actions extends AssetManager_investor_Screener_Sel_Actions{

        WebDriver driver ;
    public Angel_Investor_Screener_Sel_Actions(WebDriver driver) {
            super(driver);
            this.driver = driver;
        }

        By Angelscreener = By.xpath("(//div[@class='grid-col active'])[5]");

    public boolean verifyDropdownSelection(String expectedText) {
        clickonElement(getSelElement(dropdownSelection));
        boolean isTextMatched = clickonElement(getSelElement(Angelscreener));
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
        String faq1Question = "What is an angel investor?";
        String faq2Question = "How do angel investors differ from venture capitalists (VCs)?";
        String faq3Question = "What stage of companies do angel investors typically invest in?";
        String faq4Question = "What do angel investors look for in a startup?";

        // Store questions and their xpaths in arrays
        String[] questions = {faq1Question, faq2Question, faq3Question, faq4Question };
        By[] questionXpaths = {FAQ1question, FAQ2question, FAQ3question, FAQ4question};

        // Iterate through the list of FAQ questions
        for (int i = 0; i < questions.length; i++) {
            String questionText = getSelElement(questionXpaths[i]).getText();
            faqList.add(new String[]{questionText, questions[i]});
        }

        return faqList;
    }
}

