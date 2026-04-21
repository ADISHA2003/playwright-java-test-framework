package PlaywrightPageObject;

import Constants.FilePath;
import Constants.PageUrl;
import DatabaseUtilities_Actions.DbHelper;
import Utilities.PropertyFileReaderService;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login_playwright_actions extends BasePageActions {

    DbHelper helper = new DbHelper();
    String LOGIN_URL = PageUrl.LOGIN_URL;
    String DASHBOARD_URL = PageUrl.DASHBOARD_URL;

    public Login_playwright_actions(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        // Assuming you have methods to interact with the Playwright page
        fillInputByPlaceholder(username,"Enter your email or username");
        fillInputByPlaceholder(password,"Enter your password");
        clickButton("Login");
    }

    public void logout() {
        clickButton("logoutButton");
    }

    private void clickButton(String buttonName) {
        // Implementation to click buttons
        clickButtonByExactText(buttonName);
    }

    public void navigateToLoginPage(String domain) {
        // Navigate to the login page
        System.out.println("Navigating to Login Page: " + domain + LOGIN_URL);
        page.navigate(domain+ LOGIN_URL);
    }

    public void whenILoginWithCredentials(String username, String password) {
        // Perform login action
        String usernameValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "username");
        String passwordValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "newPassword");
        System.out.println("Username: " + usernameValues);
        System.out.println("Password: " + passwordValues);
        login(usernameValues, passwordValues);
    }


    public void thenIShouldBeAbleToLogin(String Domain) {
        // Verify successful login
        verifyCurrentUrl(Domain + "dashboard", 5);
    }

    public void thenIShouldBeAbleToLogin(String Domain, String endpoint) {
        // Verify successful login
        verifyCurrentUrl(Domain + endpoint, 5);
    }

    public void fillOtp(String otp) {
        if (otp.length() != 6) {
            throw new IllegalArgumentException("OTP must be exactly 6 digits.");
        }

        for (int i = 0; i < 6; i++) {
            String selector = "#otp-input-" + i;
            String digit = String.valueOf(otp.charAt(i));
            page.fill(selector, digit);
        }
    }

    public void clickOnResetPasswordAndSendMail(String Email, String Domain){
        clickButtonByExactText("Forgot Password?");
        waitForURL(Domain + "forgot-password", 10);

        String usernameValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, Email);
        fillInputByPlaceholder(usernameValues, "Enter your email address");
        clickButtonByExactText("Reset Password");
    }

    private static String extractOtp(String text) {
        if (text == null) return null;

        // Looks for:
        // "Your confirmation code:" followed by optional spaces/newline and then 6 digits
        Pattern pattern = Pattern.compile("Your confirmation code:\\s*(\\d{6})", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1); // the 6-digit OTP
        }

        // Optional fallback: any standalone 6-digit number in the text
        pattern = Pattern.compile("\\b(\\d{6})\\b");
        matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }



    public void fillOtpEnterNewPasswordAndLogin(String Email, String password, String Domain, Map<String, String> emailData){


        waitForURL(Domain +  "forgot-password/validate-code", 10);
        String usernameValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, Email);

        String otp = extractOtp(emailData.get("body"));
        fillOtp(otp);
        clickButtonByExactText("Verify");
        page.waitForTimeout(30);

        fillInputByPlaceholder(password,"Enter Password");
        fillInputByPlaceholder(password,"Re-enter the new password to confirm");
        waitForButtonWithExactTextToBeEnabled("Change Password", 10);

        clickButtonByExactText("Change Password");
        // page.waitForTimeout(15000);
        login(usernameValues, password);
    }

    public void thenIShouldSeeLoginError(String expectedError) {
        thenIVerifyElementVisible(expectedError);
    }

    public void thenIVerifyElementVisible(String selector){
        waitForElementToBeVisible(selector, 6);
    }

    public void whenILoginWithIncorrectUsername(String username, String password) {
        String passwordValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, password);;
        login(username,passwordValues);
    }

    public void whenILoginWithIncorrectPassword(String username, String password) {
        String usernameValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, username);;
        login(usernameValues,password);
    }

    public void whenILogout(){
        page.locator(".header-nav .svg-icon.md.cursor-hand").click();
        waitForElementToBeVisible("text=Logout", 2);
        clickButton("Logout");
    }

    public void thenIShouldBeOnLoginPage(String domain){
        waitForURL(domain+LOGIN_URL, 3);
    }

    public void ensureUserIsLoggedIn(String domain, String username, String password)  {
        // Check if logout/profile icon exists (user is logged in)
        navigateToLoginPage(domain);
        page.waitForTimeout(1000);

        if (!page.url().contains("login")) {
            System.out.println("✅ User is already logged in, skipping login.");
            return;
        }

        // Otherwise, login
        whenILoginWithCredentials(username, password);
        thenIShouldBeAbleToLogin(domain);
    }

    public void givenIamAlreadyOnLoginPageAndOnDashboard(String domain,String username,String password) {
        page.navigate(domain+DASHBOARD_URL);
        if(page.url().contains("login?")){
            page.waitForTimeout(3000);
            whenILoginWithCredentials(username,password);
            thenIShouldBeAbleToLogin(domain);
        }
        else if(page.url().equals(domain+"dashboard")){
            // Already on dashboard, do nothing
        }
    }

    /**
     * Authenticates using Rest Assured and returns the accessToken.
     */
    public String getAccessToken(String usernameValues,String passwordValues) {

        System.out.println("Username: " + usernameValues);
        System.out.println("Password: " + passwordValues);

        Map<String, String> payload = new HashMap<>();
        payload.put("username", usernameValues);
        payload.put("password", passwordValues);

        String accessToken = RestAssured
                .given()
                .baseUri("https://authentication-service-api.vccedge.com")
                .header("accept", "application/json, text/plain, */*")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200) // Validates that the login was successful
                .extract()
                .path("accessToken"); // Navigates the JSON response to find the key

        return accessToken;
    }
}
