package tests;

import Constants.PageUrl;
import DatabaseUtilities_Actions.DbHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Gmail_Inbox_Reader;

import java.util.Map;

@Listeners({AllureTestNg.class})
public class Test_Login extends BaseUI_Test{


    @AfterMethod(alwaysRun = true)
    public void delayBetweenTests() {
        try {
            System.out.println("Wait interval: Sleeping for 10 seconds before the next test case...");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @Test(description = "Login | As a user I can verify all the essential components of the Login page", groups = {"smoke", "regression"})
    public void Test_Login_Verify_Components() throws Exception {

        Allure.step("Given I am on the login page");
        lp.navigateToLoginPage(Domain);

        Allure.step("And I should see the Back To Home button");
        lp.thenIVerifyElementVisible("text=Back To Home");

        Allure.step("And I should see the username/email input field");
        lp.thenIVerifyElementVisible("input[placeholder='Enter your email or username']");

        Allure.step("Then I should see the password input field");
        lp.thenIVerifyElementVisible("input[placeholder='Enter your password']");

        Allure.step("And I should see the Login button");
        lp.thenIVerifyElementVisible("text=Login");

        Allure.step("And I should see the Forgot Password link");
        lp.thenIVerifyElementVisible("text=Forgot Password?");

        Allure.step("And I should see Talk to us about your requirements");
        lp.thenIVerifyElementVisible("text=Talk to us about your requirements");
    }

    @Test(description = "Login | As a user I can click on the login button from the Home page and get redirected to the Login page", groups = {"smoke", "regression"})
    public void Test_Home_Click_Login_Button() throws Exception {
        Allure.step("Given I am on the Home page");
        hp.navigateToHomePage(Domain );

        Allure.step("When I click on the Login button");
        hp.whenIClickOnLoginButton("Login");

        Allure.step("Then I should be redirected to the Login page");
        lp.thenIShouldBeOnLoginPage(Domain);
    }

    @Test(description = "Login | As a user I cannot login using an invalid username and a valid password", groups = {"smoke", "regression"})
    public void Test_Login_InvalidUsername_ValidPassword() {
        Allure.step("Given I am on the login page");
        lp.navigateToLoginPage(Domain);

        Allure.step("When I try to login with invalid username and valid password");
        lp.whenILoginWithIncorrectUsername("ayush.gau@gmail.com", "testUserPassword");

        Allure.step("Then I should not be able to login and should see an error message");
        lp.thenIShouldSeeLoginError("text=User does not exist.");
    }

    @Test(description = "Login | As a user I cannot login using a valid username and an invalid password", groups = {"smoke", "regression"})
    public void Test_Login_ValidUsername_InvalidPassword() throws Exception {
        Allure.step("Given I am on the login page");
        lp.navigateToLoginPage(Domain);

        Allure.step("When I try to login with invalid username and valid password");
        lp.whenILoginWithIncorrectPassword("testUserName", "testUserName");

        Allure.step("Then I should not be able to login and should see an error message");
        lp.thenIShouldSeeLoginError("text=Invalid credentials. Please try again.");
    }

    @Test(description = "Login | As a user i can login using valid credentials", groups = {"smoke", "regression"})
    public void Test_Login_Valid_Credentials() throws Exception{
        Allure.step("Given I am on login page");
        lp.navigateToLoginPage(Domain);

        Allure.step("When I login with username and password");
        lp.whenILoginWithCredentials("testUserName","testUserPassword");

        Allure.step("Then I should be able to login into the application");
        lp.thenIShouldBeAbleToLogin( Domain );

        Allure.step("And I logout from the application");
        lp.whenILogout();
        lp.thenIShouldBeOnLoginPage(Domain);
    }

    @Test(description = "Login | As a user I can reset my password using the forgot password flow and login with the new password", groups = {"smoke", "regression"})
    public void Test_Login_Forgot_Password() throws Exception {
        Allure.step("Given I am on the login page");
        lp.navigateToLoginPage(Domain );

        Allure.step("When I reset my password using forgot password flow");
        // Get mail data

        lp.clickOnResetPasswordAndSendMail("gmail.username", Domain);
        String expectedSubject = "Reset Your Password  – Confirmation Code Inside" ;
        Map<String, String> emailData = Gmail_Inbox_Reader.waitForExportEmailAndExtractS3Url(
                expectedSubject, 1000, 100, System.currentTimeMillis()
        );
        lp.fillOtpEnterNewPasswordAndLogin("gmail.username","ADIsha@2003", Domain, emailData);

        // Allure.step("Then i redirect to Login page");
        // lp.navigateToLoginPage(Domain);

        Allure.step("Then I should be able to login with the new password");
        lp.thenIShouldBeAbleToLogin(Domain);

        Allure.step("And I logout from the application");
        lp.whenILogout();
        lp.thenIShouldBeOnLoginPage(Domain);
    }

    @Test(description = "Login | User should be able to login when landing URL is internal app URL", groups = {"smoke", "regression", "production"})
    public void Test_Login_From_Internal_App_Url() {

        String internalUrl = Domain + "my-list";
        String successUrlPart = "my-list";

        Allure.step(
                "Given the user directly lands on an internal application URL without being logged in " +
                        "and the application redirects the user to the login page"
        );
        lp.launchUrl(internalUrl);

        Allure.step(
                "When the user logs in with valid credentials from the login page"
        );
        lp.whenILoginWithCredentials("testUserName", "testUserPassword");

        Allure.step(
                "Then the user should be logged in successfully and redirected back to the same internal application URL"
        );
        lp.thenIShouldBeAbleToLogin(Domain, successUrlPart);
    }

}
