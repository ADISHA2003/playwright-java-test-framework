package Constants;

public class ApiEndPoint {

    public static final String GET_EMPLOYEES = "employees";
    public static final String GET_SEARCH_GLOBAL = "global";
    public static final String POST_GRAPHQL = "graphql";
    public static final String SOURCING_DATA_TABLE = "sourcing/data_table/result";
    public static final String POST_FILTERS = "keyDevelopements/new/filterValues";

    // for the remaining screeners , the resource append is done in the test class
    public static final String GET_EVENT_TYPES_COUNT_BY_USERID= "keyDevelopements/getKeyEventTypesCountByUserId";
    public static final String GET_NEWS_LETTER_CONFIG ="api/getNewsLetterConfig";
    public static final String GET_ALL_KEYDEVELOPEMENTS = "keyDevelopements/all/key-developements";
    public static final String GET_SOURCING_RANGE = "sourcing/range";
    public static final String GET_SOURCING_MULTISELECTVALUES = "sourcing/multi_select_values";
    public static final String GET_SOURCING_BM_SECTORS = "sourcing/bm_sectors";
    public static final String GET_SOURCING_BM_TREE = "sourcing/bm_tree";
    public static final String GET_SOURCING_HEADQUARTERS = "sourcing/headquarter";
    public static final String GET_SOURCING_LOCATION = "sourcing/location";
    public static final String GET_TOP_ACQUIRERS = "sourcing/top_acquirers";
    public static final String GET_TOP_INVESTORS = "sourcing/top";
    public static final String GET_TOP_COMPANIES = "sourcing/top_companies";

    //Dashboard
    public static final String KEY_DEV = "api/key-dev";
    public static final String DEALS = "api/deals";
    public static final String COVERAGE = "api/coverage";
    public static final String FILTERS = "api/filters";
    public static final String CHARTS = "api/charts";
    public static final String SERVICES = "api/services";
    public static final String TOP_COMPANIES = "api/top-companies";
    public static final String FINANCIAL_DOCUMENTS = "api/financial-documents";
    public static final String DEAL_TYPE_SPLIT_VC = "api/deal-split-vc";
    public static final String STATES = "api/states";
    public static final String MEAN_MEDIAN = "api/mean-median";
    public static final String DEBT_TRENDS = "api/debt-trends";
    public static final String CAGR = "api/cagr";
    public static final String MNA = "api/mna";
    public static final String PE = "api/pe";
    public static final String LEAGUE_INFO = "api/league-info";
    public static final String FUND_LAUNCHED = "api/fund-launched";
    public static final String FUND_EXIT = "api/fund-exit";
    public static final String FUND_RAISE = "api/fund-raise";
    public static final String FUNDING_TREND = "api/funding-trend";
    public static final String PERMISSIONS = "permissions";
    public static final String DEAL_SPLIT = "api/deal-split";


    // Onboarding
    public static final String USER_PERSONAS = "user-personas";
    public static final String ONBOARDING = "onboarding";
    public static final String ONBOARDED = "user-onboarded";

    //Global Search endpoints
    public static final String ADD_RECENT_VISITED = "addRecentVisited";
    public static final String DELETE_RECENT_SEARCH = "deleteRecentSearch";
    public static final String DELETE_RECENT_VISITED = "deleteRecentVisited";
    public static final String GET_RECENT_VISITED = "getRecentVisited?userId=aabbcc";
    public static final String GET_RECENT_SEARCH = "get_recent_";
    public static final String SAVE_RECENT_SEARCH = "save_recent_search";
    public static final String GLOBAL_SEARCH = "global";
    public static final String SUGGESTED_QUERIES = "suggestedQueries/100";
    public static final String NLP_RECENT_SEARCH = "getrecentsearch/nlp";
    public static final String NLP_SEARCH = "search?";




    //for Company Detail Overview Page
    public  static  final  String GET_BUSINESS_DESC ="companydetails/business-description?";
    public  static  final  String GET_ANNUAL_REVENUE   ="companydetails/overview/annual-revenue?";
    public  static  final  String GET_BUSINESS_MODEL ="companydetails/businessModels?";
    public  static  final  String GET_COVERAGE_AREAS ="companydetails/coverage-area?";
    public  static  final  String GET_EDGE_RATINGS= "companydetails/edge-ratings?";
    public  static  final  String GET_KEY_METRIX=  "companydetails/overview/key-metrics?";
    public  static  final  String GET_REPORTS  ="companydetails/reports?";
    public  static  final  String POST_EBITDA_GROWTH_GRAPH="companydetails/ebitda-growth/graph";
    public  static  final  String POST_EBITDA_MARGIN_GRAPH="companydetails/ebitda-margin/graph";
    public  static  final  String POST_KEY_DEV="keyDevelopements/all/key-developements";
    public  static  final  String POST_NET_PROFIT_MARGIN ="companydetails/net-profit/graph";
    public static  final String POST_SHAREHOLDING_GRAPH ="companydetails/shareholding";
    public static  final String POST_TRANSACTION_AND_VALUATION_GRAPH ="companydetails/valuation-graph";
    public  static  final String POST_YOY_GRAPH ="companydetails/yoy-revenue-growth/graph";

    //for Company Detail financials
    public static final String GET_PNL_AWS = "companydetails/pnl";
    public static final String POST_CASHFLOW = "companydetails/cashflow";


    // for Investor and Shareholding Subtab
    public static  final String POST_SHAREHILDING_TAB_SHAREHOLDING_HISTORY="companydetails/shareholding";



    //PE Deals
    public static final String POST_DEAL_MULTIPLE = "companydeals/deal-multiple-pe";
    public static final String POST_SELLER_DETAILS = "companydeals/seller-details";
    public static final String POST_BUYER_DETAILS = "companydeals/buyer-details";
    public static final String POST_SOURCES = "companydeals/sources";
    public static final String POST_ADVISORS = "companydeals/advisors";
    public static final String GET_KEY_METRICS = "companydeals/deals/{dealId}/key-metrics";
    public static final String GET_TRANSACTION_DESCRIPTION = "companydeals/overview/transactionDescription-useProceed?";
    public static final String GET_DEAL_FEATURE = "companydeals/overview/deal-feature?";
    public static final String GET_DEAL_SUMMARY = "companydeals/overview/deal-summary?";
    public static final String GET_BREADCRUMB = "companydeals/overview/deal-breadcrumb?";
    public static final String POST_SIMILAR_DEALS = "companydeals/similar-deals";
    public static final String POST_LINKED_DEALS = "companydeals/linked-deals";
    public static final String POST_KEY_FINANCIALS = "services/research/companydetails/financial-overview/key-financials";
    public static final String SECURITY_DETAILS = "companydeals/{dealId}/security-details?";
    public static final String POST_LOGIN = "login";
    public static final String TargetCompanyFinancials = "companydetails/financial-overview/key-financials";
    public static final String SAVE_METRICS= "api/save-deal-metrics/{dealId}";
    public static final String GET_DEAL_FINANCIALS = "companydeals/overview/financial-mna";
    public static final String Issue_Expense = "companydeals/overview/ipo-issue-expense?";
    public static final String Offering_Timeline = "companydeals/overview/ipo-offering-timeline?";
    public static final String Offering_Segmentation = "companydeals/overview/ipo-offer-segmentation?";
    public static final String Lender_Details = "companydeals/lender-details";

// Comp Analysis
    public static final String SEARCH_COMPANY = "search/searchCompany";
    public static final String CompAnalysis= "graphql";
    public static final String LOCATION_COUNT_BASED_ON_FILTERS = "api/locationCountBasedOnFilters";

//CompanyScreener DataTable
    public static final String ADD_BOOKMARK = "company/bookmarks";
    public static final String POST_EXPORT = "api/data-retrieval/jobs";

}
