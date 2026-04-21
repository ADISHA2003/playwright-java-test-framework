package Utilities;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Response;
import io.qameta.allure.Allure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for monitoring and validating API calls during test execution
 * Tracks API responses and identifies failures or errors
 */
public class APIMonitoringUtility {

    private List<Response> apiResponses;
    private List<APIFailure> apiFailures;
    private Page page;
    private String currentTab;

    public APIMonitoringUtility(Page page) {
        this.page = page;
        this.apiResponses = Collections.synchronizedList(new ArrayList<>());
        this.apiFailures = Collections.synchronizedList(new ArrayList<>());
        this.currentTab = "Unknown";
        setupAPIListener();
    }

    /**
     * Sets up a response listener to capture all XHR and Fetch API calls
     */
    private void setupAPIListener() {
        page.onResponse(response -> {
            // Capture only API calls (xhr/fetch) to vccedge domain
            if (response.request().resourceType().matches("xhr|fetch") && 
                response.request().url().contains("vccedge.com")) {
                apiResponses.add(response);
                
                // Check if response indicates failure
                if (isAPIFailure(response)) {
                    APIFailure failure = new APIFailure(
                        response.url(),
                        response.status(),
                        response.statusText(),
                        response.request().method(),
                        getCurrentTimestamp(),
                        currentTab
                    );
                    apiFailures.add(failure);
                    
                    // Log to Allure report
                    Allure.step("API Failure Detected: " + response.url() + 
                               " | Status: " + response.status());
                }
            }
        });
    }

    /**
     * Determines if an API response represents a failure
     * @param response The API response to check
     * @return true if the response is a failure
     */
    private boolean isAPIFailure(Response response) {
        int status = response.status();
        // Consider 4xx and 5xx status codes as failures
        // Also check for aborted or timeout scenarios
        return status >= 405 || status == 0;
    }

    /**
     * Sets the current tab being navigated
     * @param tabName Name of the current tab
     */
    public void setCurrentTab(String tabName) {
        this.currentTab = tabName;
    }

    /**
     * Clears all tracked API responses and failures
     * Should be called at the start of each navigation/action
     */
    public void clearAPIHistory() {
        apiResponses.clear();
        apiFailures.clear();
        currentTab = "Unknown";
    }

    /**
     * Gets all API responses captured since last clear
     * @return List of captured responses
     */
    public List<Response> getAPIResponses() {
        return new ArrayList<>(apiResponses);
    }

    /**
     * Gets all API failures detected since last clear
     * @return List of API failures
     */
    public List<APIFailure> getAPIFailures() {
        return new ArrayList<>(apiFailures);
    }

    /**
     * Checks if any API failures occurred
     * @return true if there are any failures
     */
    public boolean hasAPIFailures() {
        return !apiFailures.isEmpty();
    }

    /**
     * Gets count of API calls made
     * @return Number of API calls
     */
    public int getAPICallCount() {
        return apiResponses.size();
    }

    /**
     * Waits for all pending API calls to complete
     * @param maxWaitSeconds Maximum time to wait in seconds
     */
    public void waitForAPIsToComplete(int maxWaitSeconds) {
        try {
            page.waitForLoadState(
                    com.microsoft.playwright.options.LoadState.NETWORKIDLE,
                    new Page.WaitForLoadStateOptions().setTimeout((long) maxWaitSeconds * 1000)
            );
            page.waitForTimeout(500);
        } catch (PlaywrightException e) {
            Allure.step("Network idle timeout reached: " + e.getMessage());
        }
    }

    /**
     * Verifies that no API failures occurred
     * @throws AssertionError if any API failures are detected
     */
    public void verifyNoAPIFailures() {
        if (hasAPIFailures()) {
            String failureReport = generateFailureReport();
            Allure.addAttachment("API Failures Report", "text/plain", failureReport);
            throw new AssertionError("API failures detected:\n" + failureReport);
        }
    }

    /**
     * Generates a detailed report of all API failures
     * @return Formatted failure report
     */
    public String generateFailureReport() {
        if (apiFailures.isEmpty()) {
            return "No API failures detected";
        }

        StringBuilder report = new StringBuilder();
        report.append("=== API Failures Report ===\n\n");
        report.append("Total Failures: ").append(apiFailures.size()).append("\n\n");

        for (int i = 0; i < apiFailures.size(); i++) {
            APIFailure failure = apiFailures.get(i);
            report.append(String.format("Failure #%d:\n", i + 1));
            report.append(String.format("  Tab: %s\n", failure.getTabPath()));
            report.append(String.format("  URL: %s\n", failure.getUrl()));
            report.append(String.format("  Method: %s\n", failure.getMethod()));
            report.append(String.format("  Status: %d - %s\n", failure.getStatusCode(), failure.getStatusText()));
            report.append(String.format("  Timestamp: %s\n", failure.getTimestamp()));
            report.append("\n");
        }

        return report.toString();
    }

    /**
     * Gets summary of API calls grouped by status code
     * @return Map of status codes to count
     */
    public Map<Integer, Long> getAPIStatusSummary() {
        return apiResponses.stream()
            .collect(Collectors.groupingBy(Response::status, Collectors.counting()));
    }

    /**
     * Logs API monitoring summary to Allure report
     */
    public void logAPISummaryToAllure() {
        Map<Integer, Long> summary = getAPIStatusSummary();
        StringBuilder summaryText = new StringBuilder();
        summaryText.append("API Calls Summary:\n");
        summaryText.append("Total API Calls: ").append(getAPICallCount()).append("\n");
        summaryText.append("Status Code Distribution:\n");
        summary.forEach((code, count) -> 
            summaryText.append(String.format("  %d: %d calls\n", code, count))
        );
        summaryText.append("Total Failures: ").append(apiFailures.size()).append("\n");
        
        Allure.addAttachment("API Monitoring Summary", "text/plain", summaryText.toString());
    }

    private String getCurrentTimestamp() {
        return new Date().toString();
    }

    /**
     * Inner class to represent an API failure
     */
    public static class APIFailure {
        private final String url;
        private final int statusCode;
        private final String statusText;
        private final String method;
        private final String timestamp;
        private final String tabPath;

        public APIFailure(String url, int statusCode, String statusText, String method, String timestamp, String tabPath) {
            this.url = url;
            this.statusCode = statusCode;
            this.statusText = statusText;
            this.method = method;
            this.timestamp = timestamp;
            this.tabPath = tabPath;
        }

        public String getUrl() { return url; }
        public int getStatusCode() { return statusCode; }
        public String getStatusText() { return statusText; }
        public String getMethod() { return method; }
        public String getTimestamp() { return timestamp; }
        public String getTabPath() { return tabPath; }

        @Override
        public String toString() {
            return String.format("%s %s - %d %s [%s] (Tab: %s)", method, url, statusCode, statusText, timestamp, tabPath);
        }
    }
}
