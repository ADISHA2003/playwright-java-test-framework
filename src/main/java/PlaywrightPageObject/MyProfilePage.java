package PlaywrightPageObject;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class MyProfilePage {
    private final Page page;

    // Locators
    private final Locator heading;
    private final Locator userName;
    private final Locator email;
    private final Locator phone;
    private final Locator editButton;
    private final Locator saveButton;
    private final Locator cancelButton;
    private final Locator changePasswordButton;
    private final Locator profilePicture;

    public MyProfilePage(Page page) {
        this.page = page;
        this.heading = page.locator("//a[text()='My Profile']");
        this.userName = page.locator("//div[contains(@class,'fw-600') and contains(@class,'one-line-text')]//div[@class='text-eclipse']");
        this.email = page.locator("//div[div[text()='Email ID']]//button[contains(@class,'text-left')]");
        this.phone = page.locator("//div[div[text()='Mobile Number']]//button[contains(@class,'text-left')]");
        this.editButton = page.locator("//div[div[@class='fw-500 mr-8' and text()='What Brings you here ?']]/button[text()='Edit']");
        this.saveButton = page.locator("//button[text()='Save Changes']");
        this.cancelButton = page.locator("//span[@class='svg-icon stroke darkv1 hover dialog-close top-10']");
        this.changePasswordButton = page.locator("//button[text()='Change Password']");
        this.profilePicture = page.locator("//img[@src='/images/profile_img.png']");
    }

    // Actions
    public String getHeadingText() {
        return heading.textContent();
    }

	/*
	 * public String getUserName() { return userName.inputValue(); }
	 */
    public String getUserName() {
        return userName.textContent().trim();  // ✅ Corrected
    }

    public String getEmail() {
        return email.textContent().trim();
    }

    public String getPhone() {
        return phone.textContent().trim();
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickChangePasswordButton() {
        changePasswordButton.click();
    }

    public String getProfilePictureSrc() {
        return profilePicture.getAttribute("src");
    }
}