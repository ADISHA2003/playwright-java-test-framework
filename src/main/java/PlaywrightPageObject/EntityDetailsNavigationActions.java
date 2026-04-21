package PlaywrightPageObject;

import Utilities.APIMonitoringUtility;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Allure;
import org.testng.Assert;

import java.util.*;

/**
 * Page Object for navigating and validating Entity Details pages
 * Supports multiple entity types: Company, Investor, Deal, etc.
 * Monitors API calls and validates tab navigation
 */
public class EntityDetailsNavigationActions {

    private Page page;
    private APIMonitoringUtility apiMonitor;

    // Locators
    public String TAB_SELECTOR = "css=.cd-nav div";
    private  String SUB_TAB_SELECTOR = "css=.data-table-tabs a";

    public EntityDetailsNavigationActions(Page page) {
        this.page = page;
        this.apiMonitor = new APIMonitoringUtility(page);
    }

    /**
     * Navigates to entity details page using entity ID
     * @param entityType Type of entity (company, investor, deal, etc.)
     * @param entityId Unique identifier for the entity
     */
    public void navigateToEntityDetailsPage(String entityType, String entityId) {
        String url = constructEntityURL(entityType, entityId);

        Allure.step("Navigating to " + entityType + " details page: " + entityId, () -> {
            apiMonitor.clearAPIHistory();
            page.navigate(url);

            // Verify page loaded successfully
            apiMonitor.waitForAPIsToComplete(10);

            Allure.step("Verifying page loaded without API failures");
            apiMonitor.verifyNoAPIFailures();
            apiMonitor.logAPISummaryToAllure();
        });
    }

    /**
     * Navigates to entity details page using entity name
     * @param entityType Type of entity
     * @param entityName Name of the entity
     * @param domainUrl Base domain URL
     */
    public void navigateToEntityDetailsByName(String entityType, String entityName, String domainUrl) {
        Allure.step("Searching for entity: " + entityName, () -> {
            // Use global search or dashboard to find entity
            String searchUrl = domainUrl + "/search?q=" + entityName.replace(" ", "%20");
            page.navigate(searchUrl);

            // Click on the first matching entity
            String entitySelector = String.format("a[href*='/%s/']", entityType.toLowerCase());
            page.locator(entitySelector).first().click();

            apiMonitor.waitForAPIsToComplete(10);
        });
    }

    /**
     * Navigates through all tabs on entity details page and validates APIs
     * @param verifyAPIs If true, verifies no API failures on each tab
     * @return Map of tab names to API call counts
     */
    public Map<String, Integer> navigateAndValidateAllTabs(boolean verifyAPIs) {
        Map<String, Integer> tabApiCounts = new LinkedHashMap<>();

        Allure.step("Identifying and navigating through all tabs", () -> {
            List<TabInfo> allTabs = getAllTabs();

            if (allTabs.isEmpty()) {
                Allure.step("No tabs found on this entity page");
                return;
            }

            Allure.step("Found " + allTabs.size() + " main tabs");

            // Clear API history at the start to accumulate all failures
            apiMonitor.clearAPIHistory();

            for (TabInfo tabInfo : allTabs) {
                String tabName = tabInfo.getName();

                Allure.step("Navigating to tab: " + tabName, () -> {
                    apiMonitor.setCurrentTab(tabName);

                    int apiCountBefore = apiMonitor.getAPICallCount();

                    try {
                        clickTab(tabInfo);
                        apiMonitor.waitForAPIsToComplete(10);

                        int apiCountAfter = apiMonitor.getAPICallCount();
                        int apiCallCount = apiCountAfter - apiCountBefore;
                        tabApiCounts.put(tabName, apiCallCount);

                        Allure.step(String.format("Tab '%s' loaded with %d API calls", tabName, apiCallCount));

                        // Check for sub-tabs
                        List<TabInfo> subTabs = getAllSubTabs();
                        if (!subTabs.isEmpty()) {
                            navigateSubTabs(tabName, subTabs, verifyAPIs, tabApiCounts);
                        }

                    } catch (Exception e) {
                        Allure.step("Error navigating to tab '" + tabName + "': " + e.getMessage());
                        System.err.println("Failed to navigate to tab: " + tabName + " - " + e.getMessage());
                    }
                });
            }

            // Log final summary
            apiMonitor.logAPISummaryToAllure();

            // Verify no API failures after all navigation if requested
            if (verifyAPIs) {
                apiMonitor.verifyNoAPIFailures();
            }
        });

        return tabApiCounts;
    }

    /**
     * Navigates through sub-tabs within a main tab
     */
    private void navigateSubTabs(String mainTabName, List<TabInfo> subTabs,
                                 boolean verifyAPIs, Map<String, Integer> tabApiCounts) {
        Allure.step("Found " + subTabs.size() + " sub-tabs in '" + mainTabName + "'");

        for (TabInfo subTabInfo : subTabs) {
            String subTabName = subTabInfo.getName();
            String fullTabPath = mainTabName + " > " + subTabName;

            Allure.step("Navigating to sub-tab: " + subTabName, () -> {
                apiMonitor.setCurrentTab(fullTabPath);

                int apiCountBefore = apiMonitor.getAPICallCount();

                try {
                    clickTab(subTabInfo);
                    apiMonitor.waitForAPIsToComplete(10);

                    int apiCountAfter = apiMonitor.getAPICallCount();
                    int apiCallCount = apiCountAfter - apiCountBefore;
                    tabApiCounts.put(fullTabPath, apiCallCount);

                    Allure.step(String.format("Sub-tab '%s' loaded with %d API calls",
                               subTabName, apiCallCount));

                } catch (Exception e) {
                    Allure.step("Error navigating to sub-tab '" + subTabName + "': " + e.getMessage());
                    System.err.println("Failed to navigate to sub-tab: " + subTabName + " - " + e.getMessage());
                }
            });
        }
    }

    /**
     * Gets all main tabs on the page
     */
    private List<TabInfo> getAllTabs() {
        List<TabInfo> tabs = new ArrayList<>();

        try {
            int count = page.locator(TAB_SELECTOR).count();

            for (int i = 0; i < count; i++) {
                Locator tab = page.locator(TAB_SELECTOR).nth(i);
                if (tab.isVisible()) {
                    String tabText = tab.textContent().trim();
                    if (!tabText.isEmpty()) {
                        tabs.add(new TabInfo(tabText, i, false));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error getting tabs: " + e.getMessage());
        }

        return tabs;
    }

    /**
     * Gets all sub-tabs currently visible on the page
     */
    private List<TabInfo> getAllSubTabs() {
        List<TabInfo> subTabs = new ArrayList<>();

        try {
            int count = page.locator(SUB_TAB_SELECTOR).count();

            for (int i = 0; i < count; i++) {
                Locator subTab = page.locator(SUB_TAB_SELECTOR).nth(i);
                if (subTab.isVisible()) {
                    String subTabText = subTab.textContent().trim();
                    if (!subTabText.isEmpty()) {
                        subTabs.add(new TabInfo(subTabText, i, true));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error getting sub-tabs: " + e.getMessage());
        }

        return subTabs;
    }

    /**
     * Clicks on a tab element
     */
    private void clickTab(TabInfo tabInfo) {
        Locator tabElement = page.locator(TAB_SELECTOR).nth(tabInfo.getIndex());

        // Scroll into view and click
        tabElement.scrollIntoViewIfNeeded();
        page.waitForTimeout(500); // Small delay for smooth scrolling
        tabElement.click();
    }

    /**
     * Clicks on a tab element
     */
    private void clickSubTab(TabInfo tabInfo) {
        Locator tabElement = page.locator(SUB_TAB_SELECTOR).nth(tabInfo.getIndex());

        // Scroll into view and click
        tabElement.scrollIntoViewIfNeeded();
        page.waitForTimeout(500); // Small delay for smooth scrolling
        tabElement.click();
    }


    /**
     * Waits for page to finish loading
     */
       /**
     * Constructs the entity URL based on type and ID
     */
    private String constructEntityURL(String entityType, String entityId) {
        String baseUrl = page.url().contains("www.vccedge.com")
            ? "https://www.vccedge.com"
            : page.url().split("/")[0] + "//" + page.url().split("/")[2];

        String normalizedType = entityType.toLowerCase().replace(" ", "-");
        return String.format("%s/%s/%s/overview", baseUrl, normalizedType, entityId);
    }

    /**
     * Verifies that entity details page is loaded correctly
     */
    public void verifyEntityDetailsPageLoaded(String entityType, String expectedIdentifier) {
        Allure.step("Verifying entity details page is loaded", () -> {
            // Check URL contains entity type
            String currentUrl = page.url();
            Assert.assertTrue(currentUrl.contains(entityType.toLowerCase()),
                "URL should contain entity type: " + entityType);


            // Verify no API failures during page load
            apiMonitor.verifyNoAPIFailures();

            Allure.step("Entity details page loaded successfully");
        });
    }

    /**
     * Gets the current API monitor instance
     */
    public APIMonitoringUtility getAPIMonitor() {
        return apiMonitor;
    }

    /**
     * Inner class to store tab information
     */
    private static class TabInfo {
        private final String name;
        private final int index;
        private final boolean isSubTab;

        public TabInfo(String name, int index, boolean isSubTab) {
            this.name = name;
            this.index = index;
            this.isSubTab = isSubTab;
        }

        public String getName() { return name; }
        public int getIndex() { return index; }
        public boolean isSubTab() { return isSubTab; }
    }
}
