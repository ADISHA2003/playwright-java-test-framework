package tests;

import Constants.FilePath;

import DatabaseUtilities_Actions.MongoDBHandler;
import DatabaseUtilities_Actions.MySQLDBHandler;
import DatabaseUtilities_Actions.SQLQueryReader;
import FactoryClasses.BrowserInstanceFactory;
import FactoryClasses.BrowserUrlFactory;
import PlaywrightPageObject.*;
import PlaywrightPageObject.ScreenerFilterPage.*;
import Utilities.DateHelperService;
import PlaywrightPageObject.EntityDetailsStructure.EntityCommonActions;


import Utilities.MockingUtility;
import Utilities.PropertyFileReaderService;
import com.microsoft.playwright.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static Utilities.PropertyFileReaderService.writeToAllureEnvFile;


public class BaseUI_Test {

    protected WebDriver driver;
    protected BrowserInstanceFactory browserInstanceFactory;

    protected Page page;
    public BrowserUrlFactory urlFactory;

    public String RESOURCE_FOLDER;
    public String TEST_FOLDER;
    public String TEST_FILE;
    public String TEST_NAME;
    public Playwright playwright;

    public long startTimeInMilliseconds;
    public long endTimeInMilliseconds;
    public String startTime;
    public String endTime;
    public String executionTime;
    public String Domain;
    BrowserContext context;
    Browser playwrightBrowser;
    static Map<String,String> envConfigs = new HashMap<String,String>();
    public MongoDBHandler mongoDBConnection;
    Connection connection = null;
    String SQLQuery = null;
    public MySQLDBHandler mySQLDBHandler;
    SQLQueryReader sqlQueryReader;
    public ResultSet mysqlResultSet;
    Map<Integer, Map<String, Object>> resultMap;
    ResultSetMetaData resultSetMetaData;
    public MockingUtility mockingUtility;
    public List<Response> history = new CopyOnWriteArrayList<>();
    static String accessToken;
    private Connection postgresconnection;
    private PreparedStatement preparedStatement;
    private String tableName;
    private Map<String, Map<String, String>> testResultData;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"suiteName"})
    public void beforeSuiteActions(@Optional("smoke") String suiteName) {
        envConfigs.put("suiteName", suiteName);
    }


    @Parameters({ "browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeClassActions(@Optional("chrome") String browser) {
        envConfigs.put("browser", browser);
        envConfigs.put("env", System.getProperty("environment", "prod"));
        String testEnvironment = envConfigs.get("env");
        testResultData = new HashMap<>();
        if(!browser.equalsIgnoreCase("db")) {
            String execBrowser = System.getProperty("browser", browser);

            urlFactory = new BrowserUrlFactory();
            Domain = urlFactory.getDomain(testEnvironment);

            browserInstanceFactory = new BrowserInstanceFactory();

            // To be implemented
            playwright = Playwright.create();

            context = browserInstanceFactory.getPlaywrightDriver(execBrowser, playwright,
                    new Browser.NewContextOptions()
                            .setViewportSize(1920, 1080)
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            // global history collector
           // history = Collections.synchronizedList(new ArrayList<>());
            context.onResponse(response -> {
                if (response.request().resourceType().matches("xhr|fetch")&& response.request().url().contains("vccedge.com")) {
                    history.add(response);
                }
            });
            mockingUtility = new MockingUtility();
            page = context.newPage();
            System.out.println("Page initialized: " + (page != null));

            InitWebPageObject(page);


            mySQLDBHandler = new MySQLDBHandler(System.getProperty("environment", "prod"));

            /**
             * Authenticates using Rest Assured and returns the accessToken.
             */

                // Prepare the request body as a Map (Rest Assured converts this to JSON automatically)
                String usernameValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "testUserName");
                String passwordValues = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "testUserPassword");;
                System.out.println("Username: " + usernameValues);
                System.out.println("Password: " + passwordValues);

                Map<String, String> payload = new HashMap<>();
                payload.put("username", usernameValues);
                payload.put("password", passwordValues);

                accessToken = RestAssured
                        .given()
                        .relaxedHTTPSValidation()
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

            }
        if(browser.equalsIgnoreCase("db")) {
                   mySQLDBHandler = new MySQLDBHandler(System.getProperty("environment", "prod"));
            sqlQueryReader = new SQLQueryReader();
            mongoDBConnection = new MongoDBHandler(System.getProperty("environment", "prod"));


            initDBObjects();
        }
    }

    public String testCaseId;
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        testCaseId = method.getName().split("__")[0].replace("_","-");
        System.out.println(testCaseId);
        startTimeInMilliseconds = DateHelperService.getCurrentTimeInMilliSeconds();
        startTime = DateHelperService.getCurrentDateTimeInFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println("Test case ID: " + testCaseId);

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result,Object[] testData) {
        endTimeInMilliseconds = DateHelperService.getCurrentTimeInMilliSeconds();
        endTime = DateHelperService.getCurrentDateTimeInFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        executionTime = DateHelperService.getExecutionTimeInMilliSeconds(startTimeInMilliseconds,
                endTimeInMilliseconds);
//        if(result.getStatus() == ITestResult.FAILURE){
//            String screenshotpath = FilePath.SCREENSHOT_FOR_TESTS+result.getMethod().getMethodName()+"screenshot.png";
//            byte[] screenshotBytes =page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get(screenshotpath))
//                    .setFullPage(true));
//            Allure.addAttachment(
//                    "Screenshot on Failure",
//                    "image/png",
//                    new ByteArrayInputStream(screenshotBytes),
//                    ".png"
//            );
        //}
        String suite = envConfigs.get("suiteName");
        String description = result.getMethod().getDescription();

        String[] parts = new String[0];
        if (description != null && !description.isEmpty()) {
            parts = description.split("\\|");
        }

// Safe extraction
        String component = parts.length > 0 ? parts[0].trim() : "";
        String testcase_name = parts.length > 1 ? parts[1].trim() : "";
        String test_data = parts.length > 2 ? parts[2].trim() : "";

        String status = result.getStatus() == 1 ? "pass"
                : result.getStatus() == 2 ? "fail"
                : "skipped";

        if (parts.length > 2) {

            System.out.println("Test Data: " + test_data);

            testResultData.put(
                    testCaseId,
                    Map.of(
                            "suite", suite,
                            "component", component,
                            "testcase_name", testcase_name,
                            "test_data", test_data,
                            "status", status,
                            "is_automated", "true"
                    )
            );

        } else {

            if (testData != null && testData.length > 0 && testData[0] != null) {

                String dataValue = testData[0].toString();

                System.out.println("Test Data: " + dataValue);

                testResultData.put(
                        testCaseId + " for " + dataValue,
                        Map.of(
                                "suite", suite,
                                "component", component,
                                "testcase_name", testcase_name + " for " + dataValue,
                                "test_data", dataValue,
                                "status", status,
                                "is_automated", "true"
                        )
                );

            } else {

                testResultData.put(
                        testCaseId,
                        Map.of(
                                "suite", suite,
                                "component", component,
                                "testcase_name", testcase_name,
                                "test_data", "",
                                "status", status,
                                "is_automated", "true"
                        )
                );
            }
        }

//        String description = result.getMethod().getDescription();
//
//        String component = "";
//        String testcase_name = "";
//
//        if (description != null) {
//            String[] parts = description.split("\\|");
//
//            if (parts.length > 0) {
//                component = parts[0].trim();
//            }
//
//            if (parts.length > 1) {
//                testcase_name = parts[1].trim();
//            }
//        }
//            String test_data = new String();
//            System.out.println("Suite: " + suite);
//            System.out.println("Component: " + component);
//            System.out.println("Testcase Name: " + testcase_name);
//            System.out.println("test data" + testData);
//        if( result.getMethod().getDescription().split("\\|").length>2) {
//            test_data = result.getMethod().getDescription().split("\\|")[2].trim();
//            System.out.println("Test Data: " + test_data);
//             testResultData.put(testCaseId, Map.of("suite",suite,"component", component, "testcase_name", testcase_name, "test_data", test_data,"status",result.getStatus()==1?"pass":result.getStatus()==2?"fail":"skipped","is_automated","true"));
//
//        }
//        else{
//            if(testData.length>0){
//                System.out.println("Test Data: " + test_data);
//                testResultData.put(testCaseId+" for "+testData[0].toString(), Map.of("suite",suite,"component", component, "testcase_name", testcase_name+" for "+ testData[0].toString(), "test_data", testData[0].toString(),"status",result.getStatus()==1?"pass":result.getStatus()==2?"fail":"skipped","is_automated","true"));
//
//            }else{
//            testResultData.put(testCaseId, Map.of("suite",suite,"component", component, "testcase_name", testcase_name, "test_data", "","status",result.getStatus()==1?"pass":result.getStatus()==2?"fail":"skipped","is_automated","true"));
//
//            }
//
//        }
        System.out.println("test case id"+testResultData.entrySet());
        switch (suite) {
                case "smoke":tableName = "smoke_test_execution"; break;
                case "regression":tableName = "qa_testcase_execution_result"; break;
                case "data_migration":tableName = "qa_testcase_execution_result"; break;                    
                default:
                            break;
                    }
        System.out.println("Table Name: " + tableName  );
            
    }

    @AfterClass(alwaysRun = true)
    public void afterClassActions() {
//        try {
//            executePostgresqlQuery();
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (postgresconnection != null) {
//                postgresconnection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            if (page != null && page.video() != null) {
                Path videoPath = page.video().path();
                String className = this.getClass().getSimpleName();
                File dest = new File("videos/" + className + ".webm");
                FileUtils.copyFile(videoPath.toFile(), dest);
                System.out.println("Video saved: " + dest.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/"+this.getClass().getSimpleName()+".zip")));
            context.close();
        }
        if (page != null) {
            page.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteActions() {
        writeToAllureEnvFile("allure-results/",envConfigs);
    }


    // To generate artifacts
    public void generateArtifacts(String testname) {
        String destPath = FilePath.SCREENSHOT_PATH + System.getProperty("browser") + File.separator + testname + ".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File DestFile = new File(destPath);
        // Copy file at destination
        try {
            FileUtils.copyFile(screenshotFile, DestFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(destPath))
                    .setFullPage(true));

    }

    protected Login_playwright_actions lp;
    protected Home_playwright_actions hp;
    protected ScreenerLandingPage slp;
    protected ScreenerTopHeader sth;
    protected ManageModalActions mma;
    protected FilterAreaActions fs;
    protected LeftNavOverlayActions lna;
    protected DataTableExportActions dte;
    protected XLSDownloadsPage xls;
    protected EntityExportActions eet;
    protected ViewResultsSectionActions vrs;
    protected BookmarksPage bp;
    protected Global_Search_playwright_actions gs;
    protected ScreenerDataTablePage sdtp;
    protected BulkExportActions bea;
    protected ViewCriteriaActions vcs;
    protected ScreenerDataTableActions sdt;
    protected ScreenerFiltersEnum sfe;
    protected MyListActions ml;
    protected FilterManagementService fms;
    protected KeyDevActions kda;
    protected MyProfilePage mpp;
    protected SavedFilters_Actions sf;
    protected ScreenerDTManageColumn screenerDTManageColumn;
    protected DTFilterApplier filter;
    protected DTFilterVerifier dtFilterVerifier;
    protected Dashboard_playwright_actions dp;
    protected DTDropdownActions dtDropdownActions;
    protected EntityDetailsNavigationActions edna;
    protected BulkUploadActions bua;
    protected EntityCommonActions eca;
    protected BulkUploadMatchingActions buma;
    protected BulkExportMetricsActions bema;
    protected XartupInternalNavActions xina;
    protected AMdpUIactions amdpa;
    public void InitWebPageObject(Page Page) {
        lp = new Login_playwright_actions(Page);
        hp = new Home_playwright_actions(Page);
        slp = new ScreenerLandingPage(Page);
        sth = new ScreenerTopHeader(Page);
        mma = new ManageModalActions(Page);
        fs = new FilterAreaActions(Page);
        lna = new LeftNavOverlayActions(Page);
        dte = new DataTableExportActions(Page);
        xls = new XLSDownloadsPage(Page);
        eet = new EntityExportActions(Page);
        vrs = new ViewResultsSectionActions(Page);
        bp = new BookmarksPage(Page);
        gs = new Global_Search_playwright_actions(Page);
        sdtp = new ScreenerDataTablePage(Page);
        bea = new BulkExportActions(Page);
        vcs = new ViewCriteriaActions(Page);
        sdt = new ScreenerDataTableActions(Page);
        sfe = new ScreenerFiltersEnum();
        ml = new MyListActions(Page);
        fms = new FilterManagementService();
        kda = new KeyDevActions(Page);
        mpp = new MyProfilePage(Page);
        sf = new SavedFilters_Actions(Page);
        screenerDTManageColumn = new ScreenerDTManageColumn(Page);
        filter = new DTFilterApplier(Page);
        dtFilterVerifier = new DTFilterVerifier(Page);
        dp = new Dashboard_playwright_actions(Page);
        dtDropdownActions = new DTDropdownActions(page);
        edna = new EntityDetailsNavigationActions(page);
        bua = new BulkUploadActions(page);
        eca = new EntityCommonActions(page);
        buma = new BulkUploadMatchingActions(page);
        bema = new BulkExportMetricsActions(page);
        xina = new XartupInternalNavActions(page);
        amdpa = new AMdpUIactions(page);
    }

    public void initDBObjects() {
    }

    public PreparedStatement getSqlQueries(String suiteName) throws SQLException {
        if(suiteName.equalsIgnoreCase("regression")){
             String sql =
                        "INSERT INTO " + tableName + " (" +
                                "test_suite, component, testcase_name, test_data, status, is_automated" +
                                ") VALUES (?, ?, ?, ?, ?, ?) " +
                                "ON CONFLICT (component, testcase_name) " +
                                "DO UPDATE SET " +
                                "test_suite = EXCLUDED.test_suite, " +
                                "test_data = EXCLUDED.test_data, " +
                                "status = EXCLUDED.status, " +
                                "is_automated = EXCLUDED.is_automated";   
                System.out.println("Executing SQL: " + sql);           
                preparedStatement = postgresconnection.prepareStatement(sql);
                for (Map.Entry<String, Map<String, String>> entry : testResultData.entrySet()) {
                    String testCaseId = entry.getKey();
                    Map<String, String> data = entry.getValue();
                    
                    System.out.println(tableName+" "+testCaseId);
                    System.out.println("Setting suite: " + data.get("suite"));
                    preparedStatement.setString(1, data.get("suite"));
                    System.out.println("Setting component: " + data.get("component"));
                    preparedStatement.setString(2, data.get("component"));
                    System.out.println("Setting testcase_name: " + data.get("testcase_name"));
                    preparedStatement.setString(3, data.get("testcase_name"));
                    System.out.println("Setting test_data: " + data.get("test_data"));
                    preparedStatement.setString(4, data.get("test_data"));
                    System.out.println("Setting status: " + data.get("status"));
                    preparedStatement.setString(5, data.get("status")); // You need to add status to your map
                    System.out.println("Setting is_automated: true");
                    preparedStatement.setBoolean(6, true);preparedStatement.addBatch();
                } // You need to add executionTime
        }
        else if (suiteName.equalsIgnoreCase("smoke")){
             String sql =
                        "INSERT INTO " + tableName + " (" +
                                "test_component_name, test_case_name, test_data, status" +
                                ") VALUES (?, ?, ?, ?) " +
                                "ON CONFLICT (test_component_name, test_case_name) " +
                                "DO UPDATE SET " +
                                "test_data = EXCLUDED.test_data, " +
                                "status = EXCLUDED.status,"+
                                "test_component_name = EXCLUDED.test_component_name";   
                System.out.println("Executing SQL: " + sql);           
                preparedStatement = postgresconnection.prepareStatement(sql);
                for (Map.Entry<String, Map<String, String>> entry : testResultData.entrySet()) {
                    String testCaseId = entry.getKey();
                    Map<String, String> data = entry.getValue();
                    
                    System.out.println(tableName+" "+testCaseId);
                    System.out.println("Setting component: " + data.get("component"));
                    preparedStatement.setString(1, data.get("component"));
                    System.out.println("Setting testcase_name: " + data.get("testcase_name"));
                    preparedStatement.setString(2, data.get("testcase_name"));
                    System.out.println("Setting test_data: " + data.get("test_data"));
                    preparedStatement.setString(3, data.get("test_data"));
                    System.out.println("Setting status: " + data.get("status"));
                    preparedStatement.setString(4, data.get("status")); // You need to add status to your map
                    System.out.println(preparedStatement.toString());
                    preparedStatement.addBatch();// You need to add executionTime
        }}else preparedStatement= null;
        
        return preparedStatement;
    }
   
    public void executePostgresqlQuery() {
       try {
         System.out.println("test started");
            // Replace with your database credentials
            String url = "jdbc:postgresql://vccedge.cnudqi2x4ack.ap-south-1.rds.amazonaws.com:5432/postgres";
            String user = "postgres";
            String password = "rTon3g734sP7rTo2s";
            
            postgresconnection = DriverManager.getConnection(url, user, password);
         
                // String sql =
                //         "INSERT INTO " + tableName + " (" +
                //                 "test_suite, component, testcase_name, test_data, status, is_automated" +
                //                 ") VALUES (?, ?, ?, ?, ?, ?) " +
                //                 "ON CONFLICT (component, testcase_name) " +
                //                 "DO UPDATE SET " +
                //                 "test_suite = EXCLUDED.test_suite, " +
                //                 "test_data = EXCLUDED.test_data, " +
                //                 "status = EXCLUDED.status, " +
                //                 "is_automated = EXCLUDED.is_automated";   
                //System.out.println("Executing SQL: " + sql);           
                
                // for (Map.Entry<String, Map<String, String>> entry : testResultData.entrySet()) {
                //     String testCaseId = entry.getKey();
                //     Map<String, String> data = entry.getValue();
                    
                //     System.out.println(tableName+" "+testCaseId);
                //     System.out.println("Setting suite: " + data.get("suite"));
                //     preparedStatement.setString(1, data.get("suite"));
                //     System.out.println("Setting component: " + data.get("component"));
                //     preparedStatement.setString(2, data.get("component"));
                //     System.out.println("Setting testcase_name: " + data.get("testcase_name"));
                //     preparedStatement.setString(3, data.get("testcase_name"));
                //     System.out.println("Setting test_data: " + data.get("test_data"));
                //     preparedStatement.setString(4, data.get("test_data"));
                //     System.out.println("Setting status: " + data.get("status"));
                //     preparedStatement.setString(5, data.get("status")); // You need to add status to your map
                //     System.out.println("Setting is_automated: true");
                //     preparedStatement.setBoolean(6, true); // You need to add executionTime

                //     System.out.println("Adding to batch...");
                //     preparedStatement.addBatch();
                //     System.out.println("Added to batch.");
                // }
                preparedStatement = getSqlQueries(envConfigs.get("suiteName"));
                int[] updateCounts = preparedStatement.executeBatch();
                System.out.println("Rows affected: " + Arrays.toString(updateCounts));
            } catch (BatchUpdateException bue) {
                System.err.println("----- Batch-Update-Exception -----");
                System.err.println("SQLState: " + bue.getSQLState());
                System.err.println("Message: " + bue.getMessage());
                System.err.println("Vendor code: " + bue.getErrorCode());
                System.err.println("Update counts: " + Arrays.toString(bue.getUpdateCounts()));
                bue.printStackTrace();
            } catch (SQLException e) {
                System.err.println("----- SQL-Exception -----");
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
                System.err.println("Vendor code: " + e.getErrorCode());
                e.printStackTrace();
            }
        }
    }