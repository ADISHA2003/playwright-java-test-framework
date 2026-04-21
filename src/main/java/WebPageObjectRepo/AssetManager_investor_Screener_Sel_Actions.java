package WebPageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AssetManager_investor_Screener_Sel_Actions extends company_Screener_Sel_Actions {


    WebDriver driver ;
    public AssetManager_investor_Screener_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
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
        String faq1Question = "What is the role of a general partner?";
        String faq2Question = "What are the main differences between general partners and limited partners?";
        String faq3Question = "Can a general partner invest their own money in the partnership?";
        String faq4Question = "What are the risks associated with investing with general partners?";


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


