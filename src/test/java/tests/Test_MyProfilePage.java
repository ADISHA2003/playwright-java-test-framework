package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class Test_MyProfilePage extends BaseUI_Test {

    @Test(description = "As a user I can navigate to My Profile page and verify profile details",groups = {"smoke", "regression"})
    public void verify_MyProfile_Page_Details() {
        Allure.step("Given I am already logged in and on dashboard page");
        lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "testUserName", "testUserPassword");

        Allure.step("When I navigate to My Profile page from left navigation");
        lna.openLeftNavigation();
        lna.navigateTo("My VCC Edge", "My Profile");
        lna.verifyNavigationToPage("My VCC Edge", "My Profile");

        Allure.step("Then I verify My Profile page is loaded correctly");
        

        // Verify heading text
        String heading = mpp.getHeadingText();
        Assert.assertTrue(heading.contains("My Profile"), "Heading should contain 'My Profile'");

        // Verify key profile details are displayed
        Allure.step("Verify user name, email and phone fields are displayed");
        Assert.assertNotNull(mpp.getUserName(), "User Name should be visible");
        Assert.assertNotNull(mpp.getEmail(), "Email should be visible");
        Assert.assertNotNull(mpp.getPhone(), "Phone should be visible");

     
        Allure.step("Verify profile picture and buttons are visible");
        Assert.assertNotNull(mpp.getProfilePictureSrc(), "Profile picture should be visible");

        // Verify Edit button
        mpp.clickEditButton();
        Allure.step("Clicked Edit button successfully");
        mpp.clickCancelButton();
        Allure.step("Clicked Cancel button successfully");

        // Verify Change Password button
        mpp.clickChangePasswordButton();
        Allure.step("Change Password button clicked successfully");

        Allure.step("Then I confirm all expected elements and actions on My Profile page work correctly");
    }
}