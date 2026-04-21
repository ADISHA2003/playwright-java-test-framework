package PlaywrightPageObject;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
public class Dashboard_playwright_actions extends BasePageActions{


    public Dashboard_playwright_actions(Page page) {
        super(page);initializeLocators();
    }
        // Header/Navigation Elements
        private  Locator headerContainer;
        private  Locator logoLink;
        private  Locator userProfileDropdown;
        private  Locator userAvatar;
        private  Locator userName;
        private  Locator logoutButton;
        private  Locator settingsLink;
        private  Locator helpLink;
        private  Locator notificationsIcon;
        private  Locator notificationsBadge;

        // Main Navigation Menu
        private  Locator mainNavigation;
        private  Locator dashboardNavLink;
        private  Locator companiesNavLink;
        private  Locator dealsNavLink;
        private  Locator fundsNavLink;
        private  Locator reportsNavLink;
        private  Locator searchNavLink;
        private  Locator portfolioNavLink;
        private  Locator watchlistNavLink;

        // Search Elements
        private  Locator globalSearchBox;
        private  Locator globalSearchButton;
        private  Locator searchSuggestions;
        private  Locator advancedSearchLink;
        private  Locator searchFiltersButton;

        // Dashboard Content Areas
        private  Locator dashboardTitle;
        private  Locator dashboardSubtitle;
        private  Locator contentContainer;
        private  Locator leftSidebar;
        private  Locator rightSidebar;
        private  Locator mainContentArea;

        // Dashboard Widgets/Cards
        private  Locator quickStatsContainer;
        private  Locator totalCompaniesCard;
        private  Locator totalDealsCard;
        private  Locator totalFundsCard;
        private  Locator marketCapCard;
        private  Locator recentActivityCard;
        private  Locator watchlistCard;
        private  Locator portfolioOverviewCard;

        // Quick Access Buttons
        private  Locator quickAccessContainer;
        private  Locator addCompanyButton;
        private  Locator addDealButton;
        private  Locator createReportButton;
        private  Locator importDataButton;
        private  Locator exportDataButton;

        // Recent Activities Section
        private  Locator recentActivitiesSection;
        private  Locator recentActivitiesTitle;
        private  Locator recentActivitiesList;
        private  Locator viewAllActivitiesLink;
        private  Locator activityItems;

        // Watchlist Section
        private  Locator watchlistSection;
        private  Locator watchlistTitle;
        private  Locator watchlistTable;
        private  Locator watchlistTableHeaders;
        private  Locator watchlistTableRows;
        private  Locator addToWatchlistButton;
        private  Locator removeFromWatchlistButtons;

        // Market Overview Section
        private  Locator marketOverviewSection;
        private  Locator marketOverviewTitle;
        private  Locator marketIndicesContainer;
        private  Locator sectorPerformanceChart;
        private  Locator marketNewsSection;
        private  Locator marketNewsList;

        // Filter and Sort Elements
        private  Locator filtersContainer;
        private  Locator dateRangeFilter;
        private  Locator sectorFilter;
        private  Locator locationFilter;
        private  Locator dealTypeFilter;
        private  Locator sortByDropdown;
        private  Locator applyFiltersButton;
        private  Locator clearFiltersButton;

        // Data Table Elements (Generic for various tables)
        private  Locator dataTable;
        private  Locator tableHeaders;
        private  Locator tableRows;
        private  Locator tablePagination;
        private  Locator previousPageButton;
        private  Locator nextPageButton;
        private  Locator pageNumberButtons;
        private  Locator rowsPerPageDropdown;

        // Modal and Overlay Elements
        private  Locator modalOverlay;
        private  Locator modalContainer;
        private  Locator modalCloseButton;
        private  Locator modalTitle;
        private  Locator modalContent;
        private  Locator modalFooter;

        // Chart and Graph Elements
        private  Locator chartContainer;
        private  Locator chartTitle;
        private  Locator chartLegend;
        private  Locator chartTooltip;
        private  Locator chartZoomControls;
        private  Locator chartExportButton;

        // Footer Elements
        private  Locator footerContainer;
        private  Locator footerLinks;
        private  Locator copyrightText;
        private  Locator versionInfo;

        // Loading and Status Elements
        private  Locator loadingSpinner;
        private  Locator loadingMessage;
        private  Locator errorMessage;
        private  Locator successMessage;
        private  Locator warningMessage;
        
        private Locator  smartGridButton;
        private Locator  smartGridTitle ;
        private Locator  gridContainer  ;
        private Locator selectedEntityDropdown;
        private Locator gridViewTab;
        private Locator tokenUsageButton;
        
        /**
         * Initialize all page locators
         */
        private void initializeLocators() {
            // Header/Navigation Elements
            this.headerContainer = page.locator("header, .header, .navbar");
            this.logoLink = page.locator(".logo, .brand, a[href='/'], a[href='/dashboard']");
            this.userProfileDropdown = page.locator(".user-profile, .profile-dropdown, .user-menu");
            this.userAvatar = page.locator(".user-avatar, .profile-avatar, .avatar");
            this.userName = page.locator(".user-name, .profile-name, .username");
            this.logoutButton = page.locator("button:has-text('Logout'), a:has-text('Logout'), .logout");
            this.settingsLink = page.locator("a:has-text('Settings'), .settings, a[href*='settings']");
            this.helpLink = page.locator("a:has-text('Help'), .help, a[href*='help']");
            this.notificationsIcon = page.locator(".notifications, .notification-icon, .bell");
            this.notificationsBadge = page.locator(".notification-badge, .badge, .notification-count");

            // Main Navigation Menu
            this.mainNavigation = page.locator("nav, .navigation, .main-nav, .sidebar-nav");
            this.dashboardNavLink = page.locator("a:has-text('Dashboard'), a[href*='dashboard']");
            this.companiesNavLink = page.locator("a:has-text('Companies'), a[href*='companies']");
            this.dealsNavLink = page.locator("a:has-text('Deals'), a[href*='deals']");
            this.fundsNavLink = page.locator("a:has-text('Funds'), a[href*='funds']");
            this.reportsNavLink = page.locator("a:has-text('Reports'), a[href*='reports']");
            this.searchNavLink = page.locator("a:has-text('Search'), a[href*='search']");
            this.portfolioNavLink = page.locator("a:has-text('Portfolio'), a[href*='portfolio']");
            this.watchlistNavLink = page.locator("a:has-text('Watchlist'), a[href*='watchlist']");

            // Search Elements
            this.globalSearchBox = page.locator("input[placeholder*='Search'], .search-input, #global-search");
            this.globalSearchButton = page.locator("button:has-text('Search'), .search-button, .search-btn");
            this.searchSuggestions = page.locator(".search-suggestions, .autocomplete, .search-dropdown");
            this.advancedSearchLink = page.locator("a:has-text('Advanced Search'), .advanced-search");
            this.searchFiltersButton = page.locator("button:has-text('Filters'), .filters-button");

            // Dashboard Content Areas
            this.dashboardTitle = page.locator("h1:has-text('Dashboard'), .dashboard-title, .page-title");
            this.dashboardSubtitle = page.locator(".dashboard-subtitle, .page-subtitle");
            this.contentContainer = page.locator(".content, .main-content, .dashboard-content");
            this.leftSidebar = page.locator(".left-sidebar, .sidebar-left");
            this.rightSidebar = page.locator(".right-sidebar, .sidebar-right");
            this.mainContentArea = page.locator(".main-content-area, .content-main");

            // Dashboard Widgets/Cards
            this.quickStatsContainer = page.locator(".quick-stats, .stats-container, .dashboard-stats");
            this.totalCompaniesCard = page.locator(".companies-card, .total-companies, [data-metric='companies']");
            this.totalDealsCard = page.locator(".deals-card, .total-deals, [data-metric='deals']");
            this.totalFundsCard = page.locator(".funds-card, .total-funds, [data-metric='funds']");
            this.marketCapCard = page.locator(".market-cap-card, .market-cap, [data-metric='market-cap']");
            this.recentActivityCard = page.locator(".recent-activity-card, .activity-card");
            this.watchlistCard = page.locator(".watchlist-card, .watchlist-widget");
            this.portfolioOverviewCard = page.locator(".portfolio-card, .portfolio-overview");

            // Quick Access Buttons
            this.quickAccessContainer = page.locator(".quick-actions, .action-buttons, .quick-access");
            this.addCompanyButton = page.locator("button:has-text('Add Company'), .add-company-btn");
            this.addDealButton = page.locator("button:has-text('Add Deal'), .add-deal-btn");
            this.createReportButton = page.locator("button:has-text('Create Report'), .create-report-btn");
            this.importDataButton = page.locator("button:has-text('Import'), .import-btn");
            this.exportDataButton = page.locator("button:has-text('Export'), .export-btn");

            // Recent Activities Section
            this.recentActivitiesSection = page.locator(".recent-activities, .activity-section");
            this.recentActivitiesTitle = page.locator("h2:has-text('Recent Activities'), .activities-title");
            this.recentActivitiesList = page.locator(".activities-list, .activity-items");
            this.viewAllActivitiesLink = page.locator("a:has-text('View All'), .view-all-activities");
            this.activityItems = page.locator(".activity-item, .activity");

            // Watchlist Section
            this.watchlistSection = page.locator(".watchlist-section, .watchlist");
            this.watchlistTitle = page.locator("h2:has-text('Watchlist'), .watchlist-title");
            this.watchlistTable = page.locator(".watchlist-table, table");
            this.watchlistTableHeaders = page.locator(".watchlist-table thead th, table thead th");
            this.watchlistTableRows = page.locator(".watchlist-table tbody tr, table tbody tr");
            this.addToWatchlistButton = page.locator("button:has-text('Add to Watchlist'), .add-watchlist");
            this.removeFromWatchlistButtons = page.locator("button:has-text('Remove'), .remove-watchlist");

            // Market Overview Section
            this.marketOverviewSection = page.locator(".market-overview, .market-section");
            this.marketOverviewTitle = page.locator("h2:has-text('Market Overview'), .market-title");
            this.marketIndicesContainer = page.locator(".market-indices, .indices");
            this.sectorPerformanceChart = page.locator(".sector-chart, .performance-chart");
            this.marketNewsSection = page.locator(".market-news, .news-section");
            this.marketNewsList = page.locator(".news-list, .news-items");

            // Filter and Sort Elements
            this.filtersContainer = page.locator(".filters, .filter-container");
            this.dateRangeFilter = page.locator("input[type='date'], .date-picker, .date-range");
            this.sectorFilter = page.locator("select[name*='sector'], .sector-filter");
            this.locationFilter = page.locator("select[name*='location'], .location-filter");
            this.dealTypeFilter = page.locator("select[name*='deal'], .deal-type-filter");
            this.sortByDropdown = page.locator("select[name*='sort'], .sort-dropdown");
            this.applyFiltersButton = page.locator("button:has-text('Apply'), .apply-filters");
            this.clearFiltersButton = page.locator("button:has-text('Clear'), .clear-filters");

            // Data Table Elements
            this.dataTable = page.locator(".data-table, .table, table");
            this.tableHeaders = page.locator(".data-table th, table th");
            this.tableRows = page.locator(".data-table tbody tr, table tbody tr");
            this.tablePagination = page.locator(".pagination, .table-pagination");
            this.previousPageButton = page.locator("button:has-text('Previous'), .prev-page");
            this.nextPageButton = page.locator("button:has-text('Next'), .next-page");
            this.pageNumberButtons = page.locator(".page-number, .pagination-number");
            this.rowsPerPageDropdown = page.locator("select[name*='rows'], .rows-per-page");

            // Modal and Overlay Elements
            this.modalOverlay = page.locator(".modal-overlay, .overlay, .backdrop");
            this.modalContainer = page.locator(".modal, .modal-container, .dialog");
            this.modalCloseButton = page.locator(".modal-close, .close-button, button:has-text('×')");
            this.modalTitle = page.locator(".modal-title, .modal-header h1, .modal-header h2");
            this.modalContent = page.locator(".modal-content, .modal-body");
            this.modalFooter = page.locator(".modal-footer, .modal-actions");

            // Chart and Graph Elements
            this.chartContainer = page.locator(".chart, .graph, .chart-container");
            this.chartTitle = page.locator(".chart-title, .graph-title");
            this.chartLegend = page.locator(".chart-legend, .legend");
            this.chartTooltip = page.locator(".chart-tooltip, .tooltip");
            this.chartZoomControls = page.locator(".chart-zoom, .zoom-controls");
            this.chartExportButton = page.locator(".chart-export, button:has-text('Export Chart')");

            // Footer Elements
            this.footerContainer = page.locator("footer, .footer");
            this.footerLinks = page.locator("footer a, .footer a");
            this.copyrightText = page.locator(".copyright, .footer-copyright");
            this.versionInfo = page.locator(".version, .app-version");

            // Loading and Status Elements
            this.loadingSpinner = page.locator(".loading, .spinner, .loader");
            this.loadingMessage = page.locator(".loading-message, .loading-text");
            this.errorMessage = page.locator(".error, .error-message, .alert-error");
            this.successMessage = page.locator(".success, .success-message, .alert-success");
            this.warningMessage = page.locator(".warning, .warning-message, .alert-warning");
            
            
            
            this.smartGridButton =page.locator("//strong[text()=\"Smart Grid\"]");             
            this.tokenUsageButton = page.locator("button:has-text('Token Usage')") ;
            this.gridContainer =page.locator("//div[@class=\"css-33fhir\"]");
          //  this.selectedEntityDropdown=page.locator("button:has-text('Companies')");
           // this.gridViewTab = page.locator("div[role='menu'] >> text=Companies");
            
        }

           
        
        
        
        private static final Map<String, String> locatorMap = Map.of(
        	    "XARTUP", "div.lcg-blue:has(img[src*='logo_xartup'])",
        	    "VCCIRCLE", "div.lcg-yellow:has(img[src*='logo_vcc'])"
        	);
        
        // =========================== NAVIGATION ACTIONS ===========================

        /**
         * Navigate to the dashboard page
         */
        public void navigateToDashboard(String DASHBOARD_PAGE_URL) {
            page.navigate(DASHBOARD_PAGE_URL);
            waitForPageLoad();
        }

        /**
         * Click on the logo to go to home page
         */
        public void clickLogo() {
            logoLink.click();
            waitForPageLoad();
        }

        /**
         * Click on user profile dropdown
         */
        public void clickUserProfile() {
            userProfileDropdown.click();
        }

        /**
         * Logout from the application
         */
        public void logout() {
            clickUserProfile();
            logoutButton.click();
            waitForPageLoad();
        }

        /**
         * Navigate to settings page
         */
        public void navigateToSettings() {
            clickUserProfile();
            settingsLink.click();
            waitForPageLoad();
        }

        /**
         * Click on notifications icon
         */
        public void clickNotifications() {
            notificationsIcon.click();
        }

        /**
         * Navigate to companies section
         */
        public void navigateToCompanies() {
            companiesNavLink.click();
            waitForPageLoad();
        }

        /**
         * Navigate to deals section
         */
        public void navigateToDeals() {
            dealsNavLink.click();
            waitForPageLoad();
        }

        /**
         * Navigate to funds section
         */
        public void navigateToFunds() {
            fundsNavLink.click();
            waitForPageLoad();
        }

        /**
         * Navigate to reports section
         */
        public void navigateToReports() {
            reportsNavLink.click();
            waitForPageLoad();
        }

        /**
         * Navigate to portfolio section
         */
        public void navigateToPortfolio() {
            portfolioNavLink.click();
            waitForPageLoad();
        }

        /**
         * Navigate to watchlist section
         */
        public void navigateToWatchlist() {
            watchlistNavLink.click();
            waitForPageLoad();
        }

        // =========================== SEARCH ACTIONS ===========================

        /**
         * Perform global search
         */
        public void performGlobalSearch(String searchTerm) {
            globalSearchBox.clear();
            globalSearchBox.fill(searchTerm);
            globalSearchButton.click();
        }

        /**
         * Click on advanced search
         */
        public void clickAdvancedSearch() {
            advancedSearchLink.click();
        }

        /**
         * Open search filters
         */
        public void openSearchFilters() {
            searchFiltersButton.click();
        }

        /**
         * Select search suggestion
         */
        public void selectSearchSuggestion(int index) {
            searchSuggestions.locator("li").nth(index).click();
        }

        // =========================== DASHBOARD CONTENT ACTIONS ===========================

        /**
         * Click on total companies card
         */
        public void clickTotalCompaniesCard() {
            totalCompaniesCard.click();
        }

        /**
         * Click on total deals card
         */
        public void clickTotalDealsCard() {
            totalDealsCard.click();
        }

        /**
         * Click on total funds card
         */
        public void clickTotalFundsCard() {
            totalFundsCard.click();
        }

        /**
         * Click on market cap card
         */
        public void clickMarketCapCard() {
            marketCapCard.click();
        }

        // =========================== QUICK ACCESS ACTIONS ===========================

        /**
         * Click on add company button
         */
        public void clickAddCompany() {
            addCompanyButton.click();
        }

        /**
         * Click on add deal button
         */
        public void clickAddDeal() {
            addDealButton.click();
        }

        /**
         * Click on create report button
         */
        public void clickCreateReport() {
            createReportButton.click();
        }

        /**
         * Click on import data button
         */
        public void clickImportData() {
            importDataButton.click();
        }

        /**
         * Click on export data button
         */
        public void clickExportData() {
            exportDataButton.click();
        }

        // =========================== WATCHLIST ACTIONS ===========================

        /**
         * Add item to watchlist
         */
        public void addToWatchlist() {
            addToWatchlistButton.click();
        }

        /**
         * Remove item from watchlist by index
         */
        public void removeFromWatchlist(int index) {
            removeFromWatchlistButtons.nth(index).click();
        }

        /**
         * Click on view all activities
         */
        public void viewAllActivities() {
            viewAllActivitiesLink.click();
            waitForPageLoad();
        }

        // =========================== FILTER AND SORT ACTIONS ===========================

        /**
         * Set date range filter
         */
        public void setDateRange(String fromDate, String toDate) {
            dateRangeFilter.first().fill(fromDate);
            dateRangeFilter.last().fill(toDate);
        }

        /**
         * Select sector filter
         */
        public void selectSector(String sector) {
            sectorFilter.selectOption(sector);
        }

        /**
         * Select location filter
         */
        public void selectLocation(String location) {
            locationFilter.selectOption(location);
        }

        /**
         * Select deal type filter
         */
        public void selectDealType(String dealType) {
            dealTypeFilter.selectOption(dealType);
        }

        /**
         * Select sort option
         */
        public void selectSortBy(String sortOption) {
            sortByDropdown.selectOption(sortOption);
        }

        /**
         * Apply filters
         */
        public void applyFilters() {
            applyFiltersButton.click();
            waitForPageLoad();
        }

        /**
         * Clear all filters
         */
        public void clearFilters() {
            clearFiltersButton.click();
            waitForPageLoad();
        }

        // =========================== TABLE ACTIONS ===========================

        /**
         * Click on table header to sort
         */
        public void clickTableHeader(int columnIndex) {
            tableHeaders.nth(columnIndex).click();
        }

        /**
         * Click on table row
         */
        public void clickTableRow(int rowIndex) {
            tableRows.nth(rowIndex).click();
        }

        /**
         * Navigate to next page
         */
        public void goToNextPage() {
            nextPageButton.click();
            waitForPageLoad();
        }

        /**
         * Navigate to previous page
         */
        public void goToPreviousPage() {
            previousPageButton.click();
            waitForPageLoad();
        }

        /**
         * Go to specific page
         */
        public void goToPage(int pageNumber) {
            pageNumberButtons.locator(String.format("text='%d'", pageNumber)).click();
            waitForPageLoad();
        }

        /**
         * Set rows per page
         */
        public void setRowsPerPage(String rowsCount) {
            rowsPerPageDropdown.selectOption(rowsCount);
            waitForPageLoad();
        }

        // =========================== MODAL ACTIONS ===========================

        /**
         * Close modal
         */
        public void closeModal() {
            modalCloseButton.click();
        }

        /**
         * Click on modal overlay to close
         */
        public void closeModalByOverlay() {
            modalOverlay.click();
        }

        // =========================== CHART ACTIONS ===========================

        /**
         * Export chart
         */
        public void exportChart() {
            chartExportButton.click();
        }

        /**
         * Hover over chart to show tooltip
         */
        public void hoverOverChart() {
            chartContainer.hover();
        }

        // =========================== VALIDATION METHODS ===========================

        /**
         * Check if dashboard page is loaded
         */
        public boolean isDashboardPageLoaded() {
            return dashboardTitle.isVisible() && contentContainer.isVisible();
        }

        /**
         * Check if user is logged in
         */
        public boolean isUserLoggedIn() {
            return userProfileDropdown.isVisible() && userName.isVisible();
        }

        /**
         * Get username
         */
        public String getUserName() {
            return userName.textContent().trim();
        }

        /**
         * Check if notifications have badge
         */
        public boolean hasNotificationBadge() {
            return notificationsBadge.isVisible();
        }

        /**
         * Get notification count
         */
        public String getNotificationCount() {
            return notificationsBadge.textContent().trim();
        }

        /**
         * Check if loading spinner is visible
         */
        public boolean isLoading() {
            return loadingSpinner.isVisible();
        }

        /**
         * Check if error message is displayed
         */
        public boolean hasError() {
            return errorMessage.isVisible();
        }

        /**
         * Get error message text
         */
        public String getErrorMessage() {
            return errorMessage.textContent().trim();
        }

        /**
         * Check if success message is displayed
         */
        public boolean hasSuccessMessage() {
            return successMessage.isVisible();
        }

        /**
         * Get success message text
         */
        public String getSuccessMessage() {
            return successMessage.textContent().trim();
        }

        /**
         * Get total companies count from card
         */
        public String getTotalCompaniesCount() {
            return totalCompaniesCard.locator(".count, .number, .value").textContent().trim();
        }

        /**
         * Get total deals count from card
         */
        public String getTotalDealsCount() {
            return totalDealsCard.locator(".count, .number, .value").textContent().trim();
        }

        /**
         * Get total funds count from card
         */
        public String getTotalFundsCount() {
            return totalFundsCard.locator(".count, .number, .value").textContent().trim();
        }

        /**
         * Get market cap value from card
         */
        public String getMarketCapValue() {
            return marketCapCard.locator(".count, .number, .value").textContent().trim();
        }

        /**
         * Get number of watchlist items
         */
        public int getWatchlistItemsCount() {
            return watchlistTableRows.count();
        }

        /**
         * Get number of recent activities
         */
        public int getRecentActivitiesCount() {
            return activityItems.count();
        }

        /**
         * Check if modal is open
         */
        public boolean isModalOpen() {
            return modalContainer.isVisible();
        }

        /**
         * Get modal title
         */
        public String getModalTitle() {
            return modalTitle.textContent().trim();
        }

        /**
         * Wait for dashboard to load completely
         */
        public void waitForDashboardToLoad() {
            dashboardTitle.waitFor();
            contentContainer.waitFor();
            // Wait for loading spinner to disappear
            if (loadingSpinner.isVisible()) {
                loadingSpinner.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
            }
        }

        /**
         * Wait for table data to load
         */
        public void waitForTableDataToLoad() {
            dataTable.waitFor();
            // Wait for at least one row to appear
            tableRows.first().waitFor();
        }

        /**
         * Wait for charts to load
         */
        public void waitForChartsToLoad() {
            chartContainer.waitFor();
        }
        
        
        public void navigateAndVerifyNewTabWithTitle(
                String locatorKey,
                String expectedUrlPart,
                String expectedTitlePart) {

            String selector = locatorMap.get(locatorKey.toUpperCase());
            if (selector == null) {
                throw new IllegalArgumentException("Invalid locator key: " + locatorKey);
            }

            Locator banner = page.locator(selector);
            banner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.ATTACHED)
                    .setTimeout(15000));

            banner.scrollIntoViewIfNeeded();

            Page popup = page.waitForPopup(banner::click);
            popup.waitForLoadState(LoadState.DOMCONTENTLOADED);

            // ✅ URL validation (MOST IMPORTANT)
            String actualUrl = popup.url();
            if (!actualUrl.toLowerCase().contains(expectedUrlPart.toLowerCase())) {
                throw new AssertionError("URL validation failed. Expected: "
                        + expectedUrlPart + " | Actual: " + actualUrl);
            }

            // ⚠️ Title validation ONLY if expectedTitlePart is NOT empty
            if (expectedTitlePart != null && !expectedTitlePart.isEmpty()) {
                popup.waitForFunction(
                        "document.title && document.title.length > 0",
                        null,
                        new Page.WaitForFunctionOptions().setTimeout(10000)
                );

                String actualTitle = popup.title();
                if (!actualTitle.toLowerCase().contains(expectedTitlePart.toLowerCase())) {
                    throw new AssertionError("Title validation failed. Expected: "
                            + expectedTitlePart + " | Actual: " + actualTitle);
                }
            }

            System.out.println("Navigation successful for: " + locatorKey);
            popup.close();
        }
        
        public void waitForSmartGridToLoad() {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            // Companies SELECT dropdown (correct element)
            Locator companiesSelect =
                    smartGridFrame.locator(
                        "//div[contains(@class,'chakra-select__wrapper')]//select"
                    );

            companiesSelect.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)
            );

            if (loadingSpinner.isVisible()) {
                loadingSpinner.waitFor(
                    new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(120000)
                );
            }
        }


            
        public void clickOnSmarGrid() {
            smartGridButton.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(60000)
            );
            smartGridButton.click();
        }

			
        public void verifySmartGridIsOpened() {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            Locator companiesSelect =
                    smartGridFrame.locator(
                        "//div[contains(@class,'chakra-select__wrapper')]//select"
                    );

            companiesSelect.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)
            );

            Assert.assertTrue(
                companiesSelect.isVisible(),
                "❌ Companies dropdown is not visible in Smart Grid"
            );
        }

            
        public void verifySmartGridDefaultView(String expectedEntity, String expectedView) {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            // Companies SELECT dropdown
            Locator selectedEntityDropdown =
                    smartGridFrame.locator(
                        "//div[contains(@class,'chakra-select__wrapper')]//select"
                    );

            selectedEntityDropdown.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)
            );

            String actualValue = selectedEntityDropdown.inputValue();

            Assert.assertEquals(
                actualValue,
                "company",
                "❌ Default selected entity is not Companies"
            );

            // Grid tab
            Locator gridViewTab =
                    smartGridFrame.locator("button:has-text('Grid')");

            Assert.assertTrue(
                gridViewTab.isVisible(),
                "❌ Grid view is not visible by default"
            );
        }
        public void clickAddColumn() {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            Locator addColumnButton =
                    smartGridFrame.locator(
                        "table.chakra-table th button.chakra-button"
                    );

            addColumnButton.click();
        }
        public void verifyAddColumnButtonPresent() {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            Locator addColumnButton =
                    smartGridFrame.locator(
                        "table.chakra-table th button.chakra-button"
                    );

            addColumnButton.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)
            );

            Assert.assertTrue(
                addColumnButton.isVisible(),
                "❌ Add Column button is not visible"
            );
        }
        
        public void verifyCompanyNameColumnPresent() {

            FrameLocator smartGridFrame =
                    page.frameLocator("iframe[title='External Website']");

            Locator companyNameHeader =
                    smartGridFrame.locator(
                        "//table[contains(@class,'chakra-table')]//th[.//p[normalize-space()='COMPANY NAME']]"
                    );

            companyNameHeader.waitFor(
                new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)
            );

            Assert.assertTrue(
                companyNameHeader.isVisible(),
                "❌ COMPANY NAME column header is not visible"
            );
        }
    }
