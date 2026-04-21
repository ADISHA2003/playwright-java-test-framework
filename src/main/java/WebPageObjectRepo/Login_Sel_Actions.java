package WebPageObjectRepo;

import Constants.PageUrl;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.thoughtworks.qdox.model.expression.Not;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Constants.PageUrl.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Sel_Actions extends BasePageActions {

    //    BasePageActions pageActions;
    WebDriver driver ;

    // BG IMAGE SELECTOR
    By BG_IMAGE_LEFT_SIDE = By.xpath("//img[@src='/_next/static/media/LoginPageBg.3f5272fb.png']");

    // TEXT ON IMAGE SELECTORS
    By TEXT_YOUR_GO_TO_PLATFORM_FOR = By.className("p1login_t1");
    By TEXT_DATA = By.xpath("(//div[@class='p1login_t2'])[1]");
    By TEXT_INSIGHTS = By.xpath("(//div[@class='p1login_t2'])[2]");;
    By TEXT_RESEARCH = By.xpath("(//div[@class='p1login_t2'])[3]");;

    //BACK TO HOME SELECTOR
    By ICON_BACK_TO_HOME = By.className("arrow_symbol");
    By TEXT_BACK_TO_HOME = By.className("(//div[@class='arrow_txt'])");

    // VCC EDGE LOGO SELECTOR
    By LOGO_VCC_EDGE = By.tagName("rect");

    // WELCOME MESSAGE SELECTORS
    By TEXT_WELCOME_BACK = By.className("login_t1");
    By TEXT_PLEASE_LOGIN_TO_GET_STARTED=By.className("login_t2");

    // EMAIL SELECTORS
    By TEXT_EMAIL = By.xpath("(//div[@class='credentials mb-6'][1])");
    By INPUT_EMAIL = By.xpath("//input[@placeholder='Enter your email address']");
    By EMAIL_ALERT_MESSAGE = By.xpath("(//div[@class='cred_placeholder']//div[@class='text-color-red'])");

    // PASSWORD SELECTORS
    By TEXT_PASSWORD = By.xpath("(//div[@class='credentials mb-6'][2])");
    By INPUT_PASSWORD = By.cssSelector("input[type='password']");
    By INPUT_NEW_PASSWORD = By.xpath("(//input[@type='password'])[1]");
    By INPUT_CONFIRM_PASSWORD = By.xpath("(//input[@type='password'])[2]");
    By PASSOWRD_ALERT_MESSAGE = By.xpath("(//div[@class='cred_placeholder'][2]//div[@class='text-color-red'])");

    // REMEMBER ME SELECTOR
    By CHECKBOX_REMEMBER = By.xpath("(//input[@type='checkbox'])");
    By TEXT_REMEMBER_ME = By.className("check_text");

    // FORGOT PASSWORD SELECTOR
    By TEXT_FORGOT_PASSWORD = By.className("forgot_pass_text");

    // LOGIN BUTTON SELECTOR
    By BUTTON_LOGIN = By.className("loginpage-login-btn");

    // NEW CUSTOMERS SELECTOR
    By TEXT_NOT_A_CUSTOMER = By.className("not_a_cust_text1");
    By LINK_TALK_TO_US_ABOUT_YOUR_REQUIREMENTS = By.className("not_a_cust_text2");

    //POP UP CONTAINER SELECTOR
    By POP_UP_CONTAINER =  By.className("popup-content");

    //Back to singin text section
    By Text_BACK_TO_SIGNIN = By.cssSelector(".back-to-sign-in");

    //Back to signin link
    By LINK_BACK_TO_SIGN_IN = By.cssSelector(".back-to-sign-in a");

    By LINK_RESEND_CODE = By.xpath("(//button[@class='button-type-text cta'])");

    By INPUT_EMAIL_OLD_VCCEDGE = By.cssSelector("#user-login input[name='name']");

    By INPUT_PASSWORD_OLD_VCCEDGE = By.cssSelector("#user-login input[name='pass']");

    By BUTTON_LOGIN_OLD_VCCEDGE = By.cssSelector("button[value='login']");

    By RESEND_CODE_NOTIFICATION = By.xpath("(//div[@class='Toastify__toast-body']/div[2])");

    By BUTTON_CHANGE_PASSWORD = By.xpath("(//button[@class='loginpage-login-btn click-me button disabled '])");


    public Login_Sel_Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // IMAGE IS VISIBLE
    public boolean isImageLeftSideVisible(){
        return isElementVisible(BG_IMAGE_LEFT_SIDE);
    }

    // "YOUR GO TO PLATFORM" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextYourGoToPlatformFor(){
        return isElementVisible(TEXT_YOUR_GO_TO_PLATFORM_FOR) && verifyText(TEXT_YOUR_GO_TO_PLATFORM_FOR, "your go-to platform for");
    }

    // "DATA" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextData(){
        return isElementVisible(TEXT_DATA) && verifyText(TEXT_DATA, "data.");
    }

    // "INSIGHTS" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextInsight(){
        return isElementVisible(TEXT_INSIGHTS) && verifyText(TEXT_INSIGHTS, "insights.");
    }

    // "RESEARCH" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextResearch(){
        return isElementVisible(TEXT_RESEARCH) && verifyText(TEXT_RESEARCH, "research.");
    }

    // "BACK TO HOME" TEXT AND ICON IS VISIBLE
    public boolean verifyTextAndIconBackToHome(){
        return isElementVisible(ICON_BACK_TO_HOME) && isElementVisible(TEXT_BACK_TO_HOME);
    }

    // ON CLICK BACK TO HOME TEXT, USER SHOULD LAND ON HOME PAGE.
    public boolean clickTextBackToHome(String expectedUrl){
        return clickAndVerifyURL(TEXT_BACK_TO_HOME, expectedUrl);
    }

    // ON CLICK BACK TO HOME ARROW, USER SHOULD LAND ON HOME PAGE.

    public boolean clickIconBackToHome(String expectedUrl){
        return clickAndVerifyURL(TEXT_BACK_TO_HOME, expectedUrl);
    }

    // VISIBILITY OF VCC EDGE LOGO
    public boolean isLogoVisible(){
        return isElementVisible(LOGO_VCC_EDGE);
    }

    // "WELCOME BACK" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextWelcomeBack(){
        return isElementVisible(TEXT_WELCOME_BACK) && verifyText(TEXT_WELCOME_BACK, "Welcome Back");
    }

    // "PLEASE LOGIN TO GET STARTED" IS VISIBLE AND SAME WITH FIGMA.
    public boolean verifyTextPleaseLoginToGetStarted(){
        return isElementVisible(TEXT_PLEASE_LOGIN_TO_GET_STARTED) && verifyText(TEXT_PLEASE_LOGIN_TO_GET_STARTED, "Please Login to get started");
    }


    //LABEL EMAIL IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyEmailText(){
        return isElementVisible(TEXT_EMAIL) && verifyText(TEXT_EMAIL, "Email*");
    }

    // VALID EMAIL FORMAT
    public boolean verifyEmailFormat(){
        String email = getTextBoxValue(getSelElement(INPUT_EMAIL));
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return verifyPattern(emailRegex,email);
    }

    //PLACEHOLDER IS SAME WITH FIGMA
    public boolean verifyEmailPlaceholder(){
        return verifyPlaceHolderText(INPUT_EMAIL, "Enter your email address");
    }

    //  LABEL PASSWORD IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyPasswordText(){
        return isElementVisible(TEXT_PASSWORD) && verifyText(TEXT_PASSWORD, "Password*");
    }

    // VALID PASSWORD FORMAT
    public boolean verifyPasswordFormat(String password){
        sendKeys(INPUT_PASSWORD, password);
        clickonElement(getSelElement(BUTTON_LOGIN));
        waitForElementVisible(PASSOWRD_ALERT_MESSAGE, 2);
        return isElementVisible(PASSOWRD_ALERT_MESSAGE);
    }

    // VERIFY PASSWORD PLACEHOLDER
    public boolean verifyPasswordPlaceholder(){
        return verifyPlaceHolderText(INPUT_PASSWORD, "Enter your password");
    }

    // CHECKBOX IS CHECKED ON CLICK
    public boolean verifyRememberMeCheckboxIsCheckedOnClick(){
        WebElement ele = getSelElement(CHECKBOX_REMEMBER);
        clickonElement(ele);
        return ele.isSelected();
    }

    // CHECKBOX IS UNCHECK ON CLICK
    public boolean verifyRememberMeCheckboxIsUncheckedOnClick(){
        WebElement ele = getSelElement(CHECKBOX_REMEMBER);
        if(!ele.isSelected()){
            clickonElement(ele);
        }
        clickonElement(ele);
        return !ele.isSelected();
    }

    // CHECKBOX IS VISIBLE
    public boolean checkboxRememberIsVisible(){
        return isElementVisible(getSelElement(CHECKBOX_REMEMBER));
    }

    // DEFAULT STATE OF CHECK BOX
    public boolean verifyRememberMeCheckboxDefaultState(){
        return !getSelElement(CHECKBOX_REMEMBER).isSelected();
    }

    // "REMEMBER ME" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyRememberMeText(){
        return verifyText(TEXT_REMEMBER_ME, "Remember Me") && isElementVisible(TEXT_REMEMBER_ME);
    }

    // "FORGOT PASSWORD" IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyForgotPasswordText(){
        return verifyText(TEXT_FORGOT_PASSWORD, "Forgot Password?") && isElementVisible(TEXT_FORGOT_PASSWORD);
    }

    // FUNCTIONALITY OF FORGOT PASSWORD
    public boolean clickForgotPassword() {
        clickonElement(getSelElement(TEXT_FORGOT_PASSWORD));
        return getSelElement(BUTTON_LOGIN).getText().equals("Reset Password");
    }

    public void clickOnSignIn() {
        clickonElement(getSelElement(LINK_BACK_TO_SIGN_IN));
    }

    public void clickOnResendCode() {
        forceWait(31);
        clickonElement(getSelElement(LINK_RESEND_CODE));
    }

    // LOGIN IS VISIBLE AND SAME WITH FIGMA
    public boolean verifyTextLoginButton(){
        return isElementVisible(BUTTON_LOGIN) && verifyText(BUTTON_LOGIN  , "login");
    }

    // USER LANDS ON DASHBOARD WHEN CLICK ON LOGIN
    public boolean clickLoginButtonNavigatesToDashBoard(String expectedUrl){
        return clickAndVerifyURL(BUTTON_LOGIN, expectedUrl);
    }

	// "NOT A CUSTOMER" IS VISIBLE AND SAME WITH FIGMA.
    public boolean verifyNotACustomer(){
        return isElementVisible(TEXT_NOT_A_CUSTOMER) && verifyText(TEXT_NOT_A_CUSTOMER, "Not a customer?");
    }

    // "TALK TO US ABOUT YOUR REQUIREMENTS" IS VISIBLE AND SAME WITH FIGMA.
    public boolean verifyTextTalkToUsAboutYourRequirements(){
        return isElementVisible(LINK_TALK_TO_US_ABOUT_YOUR_REQUIREMENTS) && verifyText(LINK_TALK_TO_US_ABOUT_YOUR_REQUIREMENTS, "Talk to us about your requirements");
    }

    // A POP-UP BOX SHOULD OPEN WHEN CLICK ON "TALK TO US ABOUT YOUR REQUIREMENTS".
    public boolean clickTalkToUsAboutYourRequirements() {
        WebElement element = getSelElement(LINK_TALK_TO_US_ABOUT_YOUR_REQUIREMENTS);
        clickUsingJavascript(element);
        waitForElementVisible(POP_UP_CONTAINER,1);
        return isElementVisible(POP_UP_CONTAINER);
    }

    public boolean enterEmail(String email) {
        WebElement emailField = getSelElement(INPUT_EMAIL);
        emailField.clear();
        emailField.sendKeys(email);
        forceWait(2);
        return true;
    }

    public boolean clickOnSubmit(){
        WebElement submitButton = getSelElement(BUTTON_LOGIN);
        submitButton.click();
        return true;

    }

    public boolean verifyBackToSignIn(){
        isElementVisible(Text_BACK_TO_SIGNIN);
        return isElementVisible(LINK_BACK_TO_SIGN_IN);
    }

    public boolean enterOtp(String OTP){
        System.out.println(OTP);
        for(int fieldNumber=0;fieldNumber<6;fieldNumber++) {
            By INPUT_OTP_FIELD = By.cssSelector("#otp-input-" + fieldNumber);
            System.out.println("OTP field number: " + OTP.substring(fieldNumber, fieldNumber + 1));
            sendKeys(INPUT_OTP_FIELD, OTP.substring(fieldNumber, fieldNumber + 1));
        }
        return clickOnSubmit();
    }

    public boolean enterNewAndConfirmPassword(String newPassword){
        waitForElementVisible(INPUT_NEW_PASSWORD, 10);
        clickOnLocator(INPUT_NEW_PASSWORD);
        sendKeys(INPUT_NEW_PASSWORD, newPassword);
        clickOnLocator(INPUT_CONFIRM_PASSWORD);
        sendKeys(INPUT_CONFIRM_PASSWORD, newPassword);

        return clickOnSubmit();
    }

    public boolean loginToOldVccEdgeWithNewPassword(String email, String newPassword){
        sendKeys(INPUT_EMAIL_OLD_VCCEDGE, email);
        sendKeys(INPUT_PASSWORD_OLD_VCCEDGE, newPassword);
        return clickOnLocator(BUTTON_LOGIN_OLD_VCCEDGE);
    }

    public boolean loginAgainUsingUpdatedPassword(String email,String password){
        getSelElement(LINK_BACK_TO_SIGN_IN).click();
        sendKeys(INPUT_EMAIL,email);
        sendKeys(INPUT_PASSWORD,password);
        return clickOnSubmit();

    }

    public boolean logInIntoApplication(String email,String password){
        sendKeys(INPUT_EMAIL,email);
        forceWait(2);
        sendKeys(INPUT_PASSWORD,password);
        forceWait(2);
        return clickOnSubmit();
    }

    public boolean verifyResendNotification(String actualText){
        return verifyText(RESEND_CODE_NOTIFICATION, actualText);
    }

    public boolean verifyLoginFailsWithIncorrectEmail(String Text){
        forceWait(2);
        return isElementVisible(EMAIL_ALERT_MESSAGE) && verifyText(EMAIL_ALERT_MESSAGE,Text);
    }

    public boolean verifyLoginFailsWithIncorrectPassword(String Text){
        forceWait(2);
        return isElementVisible(EMAIL_ALERT_MESSAGE) && verifyText(EMAIL_ALERT_MESSAGE,Text);
    }

    public boolean verifyLoginFailsWithEmptyPassword(String Text){
        forceWait(2);
        return isElementVisible(PASSOWRD_ALERT_MESSAGE) && verifyText(PASSOWRD_ALERT_MESSAGE,Text);
    }

    public Boolean verifyForgotPasswordIsEnabled() {
        return isElementEnabled(BUTTON_CHANGE_PASSWORD);
    }
}
