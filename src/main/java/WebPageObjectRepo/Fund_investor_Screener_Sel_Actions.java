package WebPageObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class Fund_investor_Screener_Sel_Actions extends Incubator_Investor_Screener_Sel_Actions{

    WebDriver driver ;
    public Fund_investor_Screener_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By fundScreener = By.xpath("(//div[@class='grid-col active'])[4]");

    public boolean verifyDropdownSelection(String expectedText) {
        clickonElement(getSelElement(dropdownSelection));
        boolean isTextMatched = clickonElement(getSelElement(fundScreener));
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
        String faq1Question = "What is a fund?";
        String faq2Question = "How do funds raise capital?";
        String faq3Question = "What types of companies do VC/PE funds invest in?";
        String faq4Question = "What role does a fund play in a company?";

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
