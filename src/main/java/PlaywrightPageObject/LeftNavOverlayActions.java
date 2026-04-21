
package PlaywrightPageObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;
import java.util.Map;

public class LeftNavOverlayActions extends BasePageActions {

    public LeftNavOverlayActions(Page page) {
        super(page);
    }

    /**
     * Navigates through the left nav tree by clicking each menu/submenu in order.
     * Example: navigateTo("My VccEdge", "My profile")
     */
    public void navigateTo(String... path) {
        Locator current = overlay;

        if (path.length == 0) {
            throw new IllegalArgumentException("Path must contain at least one element");
        }

        // First element is always the tab
        Locator tab = current.getByText(path[0]).first();
        tab.hover();

        if (path.length > 1) {
            // Sub-tab expected
            Locator subTab = current.locator("//div[contains(.,\""+path[1]+"\") and @class=\"flex space-between\"]");
            if (subTab.count() > 0) {
                // Sub-tab exists → click sub-tab
                subTab.click();
                return;
            }
        }

        tab.click();
    }


    //private Locator overlay1= page.locator("//*[@class='new-nav nav-open inline-modal-parent ']");
    private Locator overlay= page.locator("//div[contains(@class,'new-nav-overlay')]");
    private Locator menuItems= overlay.locator(".menu-item, [data-testid='nav-item']");
    private Locator closeButton= overlay.locator(".close-btn, [aria-label='Close']");
    private Locator userProfile= overlay.locator(".user-profile, [data-testid='user-profile']");
    private Locator logo=overlay.locator(".logo, [data-testid='logo']");
    private Locator nineDotIcon = page.locator("//*[@class='svg-icon fill invert lg mr-16 cursor-hand z-index-6']");
    

    public void openLeftNavigation() {
        if(overlay.count()<=0){
            nineDotIcon.click();
            overlay.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));}
    }


    public void verifyNavigationToPage(String tab, String expectedPageName) {
    	System.out.println("sabTab:"+expectedPageName);	
        String actualTitle = page.title();
        expectedPageName ="VCCEdge: India's Top Financial Data and Investment Research";
        if(tab=="Home"    ||tab=="Screener" || tab=="Key Development" ||tab=="Analytical Tools" ||tab=="VCCEdge Intelligence" ||tab=="My VCC Edge" ) {
        
        if (!actualTitle.contains(expectedPageName)) {
            throw new AssertionError("Navigation failed! Expected: " + expectedPageName + " but got: " + actualTitle);
        }
        }else if(tab=="jk") {
            
            if (!actualTitle.contains(expectedPageName)) {
                throw new AssertionError("Navigation failed! Expected: " + expectedPageName + " but got: " + actualTitle);
            }
            }
    
    }
    
    public boolean isVisible() {
        return overlay.isVisible();
    }

    public List<String> getMenuItemTexts() {
        return menuItems.allInnerTexts();
    }

    public void clickMenuItem(String itemText) {
        menuItems.locator("text=" + itemText).click();
    }

    public void close() {
        closeButton.click();
    }

    public void clickUserProfile() {
        userProfile.click();
    }

    public void clickLogo() {
        logo.click();
    }


    /**
     * Verifies if a sub-element is visible under a parent menu and if the sub-menu order matches the provided list.
     * @param parentMenu The parent menu text (e.g., "My Vccedge")
     * @param subElement The sub-element text (e.g., "My Profile")
     * @param expectedOrder The expected order of sub-menu items (e.g., Arrays.asList("My Profile", "Bookmarks"))
     * @return true if the sub-element is visible and the order matches, false otherwise
     */
    public boolean isSubElementVisible(String parentMenu, String subElement, List<String> expectedOrder) {
        // Expand the parent menu if needed
        Locator parent = overlay.locator("text=" + parentMenu);
        if (!parent.first().isVisible()) {
            return false;
        }
        parent.first().click();

        // Find all sub-menu items under the parent menu
        // Adjust the selector as per your DOM structure for sub-menu items
        Locator subMenuItems = parent.first().locator("..") // go to parent container
            .locator(".submenu-item, [data-testid='submenu-item'], li, ul li");
        List<String> subMenuTexts = subMenuItems.allInnerTexts();

        // Check if the sub-menu order matches the expected order
        boolean orderCorrect = false;
        if (subMenuTexts.size() >= expectedOrder.size()) {
            orderCorrect = true;
            for (int i = 0; i < expectedOrder.size(); i++) {
                if (!subMenuTexts.get(i).trim().equalsIgnoreCase(expectedOrder.get(i).trim())) {
                    orderCorrect = false;
                    break;
                }
            }
        }

        // Also check if the requested subElement is visible
        Locator sub = overlay.locator("text=" + subElement);
        return sub.first().isVisible() && orderCorrect;
    }

    public void navigateAndVerifyNewTab(String cssClass, String expectedUrlPart) {
        // Wait for the new tab (popup) to open
        Page popup = page.waitForPopup(() -> {
            // Click on the menu item like XARTUP or VCCIRCLE
        	page.locator("a."+cssClass).click(new Locator.ClickOptions().setForce(true));///a.xartp, +cssClass
        });

        // Wait until the new tab is loaded
        popup.waitForLoadState();

        // Validate that the new tab URL contains the expected part
        String actualUrl = popup.url();
        if (!actualUrl.contains(expectedUrlPart)) {
            throw new AssertionError("Expected URL to contain '" + expectedUrlPart + "', but got: " + actualUrl);
        }

        System.out.println("✅ Navigation to " + cssClass + " successful. URL: " + actualUrl);

        // Close the new tab
        popup.close();
    }
    
} 
    

