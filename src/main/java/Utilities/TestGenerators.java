package Utilities;

import Constants.FilePath;
import io.restassured.RestAssured;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Constants.FilePath.BASE_API_TEST;
import static Utilities.FileHelperService.*;

public class TestGenerators {

    public static void main(String arg[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product name: ");
        String product = scanner.nextLine();
        System.out.print("Enter the component name: ");
        String componentName = scanner.nextLine();
        System.out.print("\nEnter the test type (API/WEB/DB): ");
        String TestType = scanner.nextLine();
        createTestFile(product,componentName,TestType);
    }

    public static void createTestFile(String product,String componentName, String testType) {
        switch (testType) {
            case "Web":
                generateWebTestSetup(product,componentName);
                break;
            case "API":
                generateApiTestSetup(product,componentName);
                break;
            case "DB":
                generateDBTestSetup(componentName);
                break;
            default: System.out.println("Invalid test type");
        }
    }

    public static void generateWebTestSetup(String product,String componentName){

    }

    public static void generateApiTestSetup(String product,String componentName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBuild from scratch (true/false)");
        String buildFromScratch = scanner.nextLine();
        System.out.print("\nEnter the curl command (in sinlge line)");
        String curlCommand = scanner.nextLine();
        System.out.println(curlCommand);
        Map<String,String> url = extractUrl(curlCommand);
        System.out.println("url"+url.entrySet());
        String header = extractHeaders(curlCommand);;
        System.out.println("header"+header);
        String body = extractBody(curlCommand);
        System.out.println("body"+body);
        String method = extractMethod(curlCommand);
        System.out.println("method"+method);
        String connectionType = url.get("connectionType");
        System.out.println("connection"+connectionType);
        String domain = url.get("domain");
        System.out.println("domain"+domain);
        String endpoint = url.get("endpoint");
        System.out.println("endpoint"+endpoint);
        //create test file
        createApiTestFile(product,componentName,connectionType,domain,endpoint,header,body,method);

        if(buildFromScratch.equals("true")){
        //create action file
        createAPIActionFile(product,componentName);

        //create resource folder
        String testFolder =createApiTestResourceFolder(product,componentName);
        createApiTestResourceFile(testFolder,componentName,body);

        //create api testngXml file
        createApiTestNGXMLFile(product,componentName,"API");

        //updateBaseApiAction
        String runTimeObject = new String();
        String pageActionName = componentName+"PageActions";
        runTimeObject = "\t\tprotected"+pageActionName+" "+pageActionName.substring(0,1).toLowerCase()+pageActionName.substring(1)+";\n"+"public void initAPI_PageObjects(){\n\t\t"+pageActionName.substring(0,1).toLowerCase()+pageActionName.substring(1)+"=new "+pageActionName+"();";
        String findObject = "public void initAPI_PageObjects(){" ;
        updateBaseApiTest(findObject,runTimeObject);

        runTimeObject= "package APITestSuite.; \n\nimport ApiPageObjectRepo."+product+"."+pageActionName+";\n";
        findObject = "package APITestSuite;";
        updateBaseApiTest(findObject,runTimeObject);
        }
    }

    private static void updateEndpointConstantFile(String ENDPOINT_NAME,String endpoint) {
        String content = FileHelperService.getFileContent(FilePath.API_ENDPOINT_PATH);
        String endpointConstLine = "public static final String "+endpoint.toUpperCase()+" = "+endpoint+";\n\n}";
        content = content.replaceFirst("}",endpointConstLine);
        updateFile(FilePath.API_ENDPOINT_PATH,content);
    }

    private static void updateDomainConstantFile(String DOMAIN_NAME,String domain) {
        String content = FileHelperService.getFileContent(FilePath.API_ENDPOINT_PATH);
        String endpointConstLine = "public static final String "+DOMAIN_NAME.toUpperCase()+" = "+domain+";\n\n}";
        content = content.replaceFirst("}",endpointConstLine);
        updateFile(FilePath.API_ENDPOINT_PATH,content);
    }

    public static void createApiTestFile(String component, String URL, String ENDPOINT) {
        String content = getApiTestTemplate(component, URL, ENDPOINT);
        createFile(FilePath.API_TEST_FOLDER+component+"_Test.java", content);
    }

    public static void createApiTestFile(String component, String URL,Map<String,Object> HEADER, String body,String method) {
        String content = getApiTestTemplate(component,URL,HEADER,body,method);
        createFile(FilePath.API_TEST_FOLDER+component+"_Test.java", content);
    }

    public static void createApiTestFile(String product,String componentName,String ConnectionType,String Domain,String endpoint,String HEADER, String body,String method) {
        String content = getTestMethodTemplate(product,componentName,ConnectionType,Domain,endpoint,HEADER,method);
        createFileIfNotExistElseAppend(FilePath.API_TEST_FOLDER+product+File.separator+componentName+"_Test.java",content,product, componentName);
    }


    public static void createAPIActionFile(String product,String component){
        String content = getApiPageObjectTemplate(product,component);
        createFile(FilePath.API_PAGE_OBJECT_REPO+product+File.separator+component+"PageActions.java",content);
    }

    public static void createApiTestNGXMLFile(String product,String component,String testType){
        String content = getTestNGXMLTemplate(product,component,testType);
        createFile(FilePath.API_RESOURCES+"SuiteXmls"+File.separator+product+File.separator+"TestNG_"+component+".xml",content);
    }

    public static void createFile(String filePath,String content){
        FileHelperService.updateFileContent(filePath,content);
    }

    public static void createFileIfNotExistElseAppend(String filePath,String content,String product,String componentName){
        updateTestFileContent(filePath,content,getTestClassImportClassTemplate(product,componentName));
    }

    public static void updateFile(String filePath,String content){
        FileHelperService.updateFileContent(filePath,content);
    }

    public static void generateDBTestSetup(String componentName){

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the collection: ");
        String Collection = scanner.nextLine();
        System.out.print("\nEnter the query name: ");
        String Query = scanner.nextLine();

        //create test file
        createDBTestFile(componentName,Collection,Query);

        //create action file
        createDBActionFile(componentName);

    }

    public static String getWebTestTemplate(String componentName) {
        String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);
        return "package WebTestSuite.FeatureTests;\n" +
                "\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" +
                "public class "+componentName+"Test extends BaseUI_Test {\n" +
                "\n" +
                "\n" +
                "    @Test\n" +
                "    public void <TestCaseName>(){\n" +
                "        FAILURE_MSG = Page title is not as expected;\n" +
                "        SUCCESS_MSG = Page title is as expected;\n" +
                "        PAGE_TITLE = <PageTitle>;\n" +
                "        String pageTitle  = "+actionClassObject+".launchUrl(<URL>);\n" +
                "\n" +
                "        assert_report.AssertEquals(logger,extentTest,pageTitle,PAGE_TITLE,FAILURE_MSG,SUCCESS_MSG);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}";
    }

    public static String getApiTestTemplate(String componentName,String Url,String endpointName) {
       String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);
        return "package ApiTestSuite;\n" +
                "                \n" +
                "                import Constants.*;\n" +
                "                import FactoryClasses.API_DomainFactory;\n" +
                "                import Utilities.JsonReaderService;\n" +
                "                import io.qameta.allure.testng.AllureTestNg;\n" +
                "                import org.testng.annotations.BeforeMethod;\n" +
                "                import org.testng.annotations.Listeners;\n" +
                "                import org.testng.annotations.Test;\n" +
                "                import APITestSuite.BaseAPI_Test;\n" +
                "                \n" +
                "                @Listeners(AllureTestNg.class)\n" +
                "                public class "+componentName+"_Test extends BaseAPI_Test{\n" +
                "                \n" +
                "                    @BeforeMethod\n" +
                "                    public void setDomain(){\n" +
                "                        DOMAIN_URL = API_DomainFactory.get"+componentName+"Domain(DOMAIN_ENV);\n" +
                "                        API_ENDPOINT = ApiEndPoint."+endpointName+";\n" +
                "                        API_HEADER = HeaderConstants.APPLICATION_JSON;\n" +
                "                    }\n" +
                "                \n" +
                "                    @Test\n" +
                "                    public void verifyValidResponseOfRequest"+componentName+"API(){\n" +
                "                        RESOURCE_FOLDER = FilePath.API_TESTData_PATH;\n" +
                "                        TEST_FOLDER = \""+componentName+"ComponentTestFolder\";\n" +
                "                        TEST_FILE = \""+componentName+"ComponentTestData.json\";\n" +
                "                        TEST_NAME = \"testCaseName\";\n" +
                "                        SUCCESS_MSG = \"Successful response is received\";\n" +
                "                        FAILURE_MSG = \"Successful response not received\";\n" +
                "                \n" +
                "                \n" +
                "                        //reading test data file\n" +
                "                        String requestBody = JsonReaderService.readTest_JsonFile(\n" +
                "                                RESOURCE_FOLDER,\n" +
                "                                TEST_FOLDER,\n" +
                "                                TEST_FILE,\n" +
                "                                TEST_NAME);\n" +
                "                \n" +
                "                         //fetching response from api request\n" +
                "                         response = "+actionClassObject+".post_RequestGlobalSearch(ConnectionType.SECURED,\n" +
                "                                 DOMAIN_URL,\n" +
                "                                 API_ENDPOINT,\n" +
                "                                 API_HEADER,\n" +
                "                                 requestBody);\n" +
                "                \n" +
                "                         //logging the response in report\n" +
                "                        assert_report.logResponse(response,logger,extentTest);\n" +
                "                \n" +
                "                         //Using custom assertion\n" +
                "                        assert_report.AssertFalse(\n" +
                "                                 logger,\n" +
                "                                 extentTest,\n" +
                "                                 response.asString().isEmpty(),\n" +
                "                                 FAILURE_MSG,\n" +
                "                                 SUCCESS_MSG);\n" +
                "                     }\n" +
                "                }";
    }

    public static String getApiTestTemplate(String componentName,String Url,Map<String,Object> header, String Body, String method) {
        String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);
        return "package ApiTestSuite;\n" +
                "                \n" +
                "                import Constants.*;\n" +
                "                import FactoryClasses.API_DomainFactory;\n" +
                "                import Utilities.JsonReaderService;\n" +
                "                import io.qameta.allure.testng.AllureTestNg;\n" +
                "                import org.testng.annotations.BeforeMethod;\n" +
                "                import org.testng.annotations.Listeners;\n" +
                "                import org.testng.annotations.Test;\n" +
                "                import APITestSuite.BaseAPI_Test;\n" +
                "                \n" +
                "                @Listeners(AllureTestNg.class)\n" +
                "                public class "+componentName+"_Test extends BaseAPI_Test{\n" +
                "               \n" +
                "                    @Test\n" +
                "                    public void verifyValidResponseOfRequest"+componentName+"API(){\n" +
                "                        RESOURCE_FOLDER = FilePath.API_TESTData_PATH;\n" +
                "                        TEST_FOLDER = \""+componentName+"ComponentTestFolder\";\n" +
                "                        TEST_FILE = \""+componentName+"ComponentTestData.json\";\n" +
                "                        TEST_NAME = \"testCaseName\";\n" +
                "                        SUCCESS_MSG = \"Successful response is received\";\n" +
                "                        FAILURE_MSG = \"Successful response not received\";\n" +
                "                \n" +
                "                \n" +
                "                        //reading test data file\n" +
                "                        String requestBody = JsonReaderService.readTest_JsonFile(\n" +
                "                                RESOURCE_FOLDER,\n" +
                "                                TEST_FOLDER,\n" +
                "                                TEST_FILE,\n" +
                "                                TEST_NAME);\n" +
                "                \n" +
                "                         //fetching response from api request\n" +
                "                         response = "+actionClassObject+".post_RequestGlobalSearch(ConnectionType.SECURED,\n" +
                "                                 DOMAIN_URL,\n" +
                "                                 API_ENDPOINT,\n" +
                "                                 API_HEADER,\n" +
                "                                 requestBody);\n" +
                "                \n" +
                "                         //logging the response in report\n" +
                "                        assert_report.logResponse(response,logger,extentTest);\n" +
                "                \n" +
                "                         //Using custom assertion\n" +
                "                        assert_report.AssertFalse(\n" +
                "                                 logger,\n" +
                "                                 extentTest,\n" +
                "                                 response.asString().isEmpty(),\n" +
                "                                 FAILURE_MSG,\n" +
                "                                 SUCCESS_MSG);\n" +
                "                     }\n" +
                "                }";
    }

    public static String getApiTestTemplate(String componentName,String connectionType,String domainName ,String endpointName,Map<String,Object> headers,String requestBody,String method) {
        String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);

        return "package APITestSuite;\n" +
                "\n" +
                "import Constants.*;\n" +
                "import FactoryClasses.API_DomainFactory;\n" +
                "import Utilities.JsonReaderService;\n" +
                "import io.qameta.allure.testng.AllureTestNg;\n" +
                "import org.testng.annotations.BeforeMethod;\n" +
                "import org.testng.annotations.Listeners;\n" +
                "import org.testng.annotations.Test;\n" +
                "import APITestSuite.BaseAPI_Test;\n" +
                "\n" +
                "@Listeners(AllureTestNg.class)\n" +
                "public class "+componentName+"_Test extends BaseAPI_Test{\n" +
                " \n" +
                "\n" +
                "        @Test\n" +
                "        public void verifyValidResponseOfRequest"+componentName+"API(){\n" +
                "        RESOURCE_FOLDER = FilePath.API_TESTData_PATH;\n" +
                "        TEST_FOLDER = \""+componentName+"TestFolder\";\n" +
                "        TEST_FILE = \""+componentName+"TestData.json\";\n" +
                "        TEST_NAME = \"testCaseName\";\n" +
                "        SUCCESS_MSG = \"Successful response with status 200 is received\";\n" +
                "        FAILURE_MSG = \"Successful response 200 not received\";\n" +
                "         \n" +
                "         \n" +
                "         //reading test data file\n" +
                "         String requestBody = JsonReaderService.readTest_JsonFile(\n" +
                "                                RESOURCE_FOLDER,\n" +
                "                                TEST_FOLDER,\n" +
                "                                TEST_FILE,\n" +
                "                                TEST_NAME);\n" +
                "                \n" +
                "          //fetching response from api request\n" +
                "          response = "+actionClassObject+"apiRequest("+connectionType+"://" +
                                                    domainName +"/"   +
                                                    endpointName  +
                "                                   "+headers+",\n" +
                "                                   "+requestBody+",\""+method+"\");" +
                "                         //logging the response in report\n" +
                "           assert_report.logResponse(response,logger,extentTest);\n" +
                "                \n" +
                "                         //Using custom assertion\n" +
                "           assert_report.AssertFalse(\n" +
                "                                 logger,\n" +
                "                                 extentTest,\n" +
                "                                 response.asString().isEmpty(),\n" +
                "                                 FAILURE_MSG,\n" +
                "                                 SUCCESS_MSG);\n" +
                "                     }\n" +
                "                }";
    }


    public static String getDBTestTemplate(String componentName,String QUERY_FILE_PATH_NAME) {
        String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);
        return "package DataBaseTestSuite.Migration;\n" +
                "                \n" +
                "                import static Constants.FilePath.QUERY_COMPANY_SQL;\n" +
                "                import org.testng.annotations.Test;\n" +
                "                \n" +
                "                public class "+componentName+"_Test extends BaseMigrationTest{\n" +
                "                \n" +
                "                    @Test\n" +
                "                    public void verify"+componentName+"chmigration() {\n" +
                "                                     \n" +
                "                            String sqlQuery=sqlQueryReader.readSqlFromFile(QUERY_COMPANY_SQL);\n" +
                "                            connection = mySQLDBHandler.getConnection();\n" +
                "                            mysqlResultSet = mySQLDBHandler.getResultSetMetaData(connection, sqlQuery);\n" +
                "                \n" +
                "                            resultMap = <pageObject>.storeMySqlTableResultsetToMap(mysqlResultSet,1);\n" +
                "                \n" +
                "                            database = mongoDBConnection.getDatabase();\n" +
                "                            mongoCollection = database.getCollection(\"collectionName\");\n" +
                "                \n" +
                "                            MYSQL_RECORD_COUNT = resultMap.size();\n" +
                "                            MONGO_RECORD_COUNT = mongoCollection.countDocuments();\n" +
                "                \n" +
                "                            mongoDBConnection.close();\n" +
                "                \n" +
                "                            Boolean result = companyCheck.validateCompanyCheckDbAgainstMongoDB(mongoCollection,\n" +
                "                                    resultMap);\n" +
                "                            \n" +
                "                    }\n" +
                "                \n" +
                "                \n" +
                "                \n" +
                "                }";
    }


    public static String getWebPageObjectTemplate(String componentName) {
        return "";
    }

    // To get API page object template
    public static String getApiPageObjectTemplate(String product,String componentName) {
       return "package ApiPageObjectRepo."+product+";\n" +
                "\n" +
                "import Pojos.GlobalSearchComponentPojoFiles.GlobalSearchComponentPojo;\n" +
                "import Utilities.JsonReaderService;\n" +
                "import com.fasterxml.jackson.core.JsonProcessingException;\n" +
                "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                "import io.restassured.module.jsv.JsonSchemaValidator;\n" +
                "import io.restassured.response.Response;\n" +
               "import ApiPageObjectRepo.BaseApiActions;\n"+
                "\n" +
                "import java.io.InputStream;\n" +
                "import java.util.List;\n" +
                "\n" +
                "\n" +
                "public class "+componentName+"PageActions extends BaseApiActions{}";

    }

    // To get DB page object template
    public static String getDBPageObjectTemplate(String componentName) {
        return "    package DatabaseAttributeObject;\n" +
                "\n" +
                "    import com.mongodb.client.MongoCollection;\n" +
                "    import org.bson.Document;\n" +
                "\n" +
                "    import java.sql.ResultSet;\n" +
                "    import java.sql.ResultSetMetaData;\n" +
                "    import java.sql.SQLException;\n" +
                "    import java.util.*;\n" +
                "\n" +
                "    public class "+componentName+" extends BaseAttributeActions{\n"+
                "}";
    }

    // To create Web test resource folder
    public static String createApiTestResourceFolder(String product, String componentName) {
        return createFolder(FilePath.API_RESOURCES+product+File.separator,componentName+"TestFolder");

    }

    // To create API test resource file
    public static void createApiTestResourceFile(String folder, String componentName,String data) {
        if(data.isEmpty()){data="{}";}
        String content = "{\n" +
                "  \"testCase200\": \n"+data+"\n}" ;
        createFile(folder+componentName+"_TestData.json",content);
        createFile(folder+componentName+"_TestSchema.json",content);
    }

    // To create DB test resource folder
    public static void createDBTestResourceFolder(String componentName) {

    }

    // To create DB test resource file
    public static void createDBTestResourceFile(String componentName) {

    }

    public static void createTestNGXmlFile(String product,String componentName,String folderpath,String testType) {
        String content = getTestNGXMLTemplate(product,componentName,testType);
        createFile(folderpath,content);

    }

    public static void updateBaseApiTest(String findObject,String runTimeObject) {
        updateFileReplaceString(BASE_API_TEST,findObject,runTimeObject);
    }

    public static void updateBaseWebTest(String pageActionName) {

    }
    public static void updateBaseDBTest(String pageActionName) {

    }

    public static void setApiDomain(String domain) {

    }

    public static void setApiEndpoint(String endpoint) {

    }

    public static String getTestNGXMLTemplate(String product,String componentName,String testType) {
        return " <suite name=\"" + componentName + "\">\n" +
                "    <test name=\"" + componentName + "\">\n" +
                "        <classes>\n" +
                "            <class name=\""+testType+"TestSuite." +product+"."+ componentName + "_Test\"/>\n" +
                "        </classes>\n" +
                "    </test>\n" +
                "</suite>";
    }

    public static void createDBTestFile(String component, String collection, String query) {
        String content = getDBTestTemplate(component, query);
        createFile(FilePath.DB_TEST_FOLDER + component + "_Test.java", content);
    }

    public static void createDBActionFile(String component) {
        String content = getDBPageObjectTemplate(component);
        createFile(FilePath.DB_PAGE_OBJECT_REPO + component + ".java", content);
    }

    public static void createZephyrTestClass(String product,String component, Set<String> testCases, String label, Map<String,Object> testCasePriority){
        switch(label.toUpperCase()){
            case "WEB":String content = getZephyrWebTestClassTemplate(component,testCases,testCasePriority);
                updateTestFileContent(FilePath.WEB_TEST_FOLDER+component+"_Test.java",content,getTestClassImportClassTemplate(product,component));break;
         default : break;
        }


    }

    public static void createZephyrTestCase(String component,String testCase,String label) {
        switch (label.toUpperCase()) {
            case "WEB":
                String content = getZephyrWebTestCaseTemplate(testCase);
             //   updateTestFileContent(FilePath.WEB_TEST_FOLDER + component + "_Test.java", content,getTestClassImportClassTemplate(component));
                break;
            default:
                break;
        }
    }


    public static String getZephyrWebTestClassTemplate(String component,Set<String> testCases,Map<String,Object> testCasePriority){
        String content = "package WebTestSuite.FeatureTests;" +
                "\n" +
                "import io.qameta.allure.testng.AllureTestNg;\n" +
                "import org.testng.annotations.Listeners;\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" +
                "@Listeners(AllureTestNg.class)\n" +
                "public class "+component+"_Test extends BaseUI_Test{\n\n";
        for(String testCase : testCases){
            String suite = getSuite(testCasePriority.get(testCase).toString());
            content += "    @Test(enabled=false , groups = {\""+suite+","+component+"\"})\n" +
                    "    public void "+testCase+"(){\n" +
                    "        FAILURE_MSG = \"\";\n" +
                    "        SUCCESS_MSG = \"\";\n" +
                    "                       }\n" +
                    "\n" +
                    "\n" ;
        }
        return content+"\n}";
    }

    public static String getZephyrWebTestCaseTemplate(String testCase){
        return "    @Test(enabled=false)\n" +
                "    public void "+testCase+"(){\n" +
                "        FAILURE_MSG = \"\";\n" +
                "        SUCCESS_MSG = \"\";\n" +
                "                       }\n" +
                "\n" +
                "\n" ;
    }

     public static String getSuite(String priority) {

         switch (priority) {
             case "6785308":
                 return "smoke";
             case "6785310":
                 return "regression";
             case "6785309":
                 return "sanity,regression";
             default:
                 System.out.println("Invalid priority");
                 throw new NullPointerException();
         }
     }

    private static String extractMethod(String curlCommand) {
        Pattern pattern = Pattern.compile("--request.* '");
        Matcher matcher = pattern.matcher(curlCommand);
        if (matcher.find()) {
            String method = matcher.group(0).toLowerCase().substring(10,matcher.group(0).indexOf("'")-1);
                return method;
        }
        return "post"; // Default to GET if no method is specified
    }

    private static Map<String,String> extractUrl(String curlCommand) {
        Map<String,String> urlComponents  = new HashMap<String,String>();
        Pattern pattern=Pattern.compile("https://[^']+");
        Matcher matcher= pattern.matcher(curlCommand);
        String url = new String();
        if (matcher.find()) {
            url= matcher.group(0);
        }
        urlComponents.put("connectionType",url.split(":")[0]);
        urlComponents.put("domain",url.split("services/")[0].replace("https://","")+"services/");
        urlComponents.put("endpoint",url.split("services/")[1]);
        return urlComponents;
    }

    private static String extractHeaders(String curlCommand) {
       Map<String,Object> headers = new HashMap<String, Object>();
            Pattern pattern = Pattern.compile("--header '[^']+: [^']+'");
        Matcher matcher = pattern.matcher(curlCommand);
        String header = new String();
        while (matcher.find()) {
            header = matcher.group(0);
            System.out.println(header);
            String key = header.substring(10,header.indexOf(":"));
            System.out.println(key);
            String value = header.substring(header.indexOf(":")+2,header.length()-1);
            System.out.println(value);
            headers.put(key,value);
        }
        System.out.println(headers);
        String headerString  = new String();
        for(String key: headers.keySet()) {
            headerString+= "headers.put(\""+key+"\",\""+headers.get(key)+"\");\n\t\t";
        }
        return headerString;
    }

    private static String extractBody(String curlCommand) {
        Pattern pattern = Pattern.compile("--data '([^']+)'");
        Matcher matcher = pattern.matcher(curlCommand);
        if (matcher.find()) {
            return matcher.group(0).split("--data '")[1].split("'")[0];
        }
        return "";
    }

    public static String getTestClassImportClassTemplate(String product,String componentName){
        return "package APITestSuite." +product+";\n"+
                "\n" +
                "import Constants.*;\n" +
                "import FactoryClasses.API_DomainFactory;\n" +
                "import Utilities.JsonReaderService;\n" +
                "import io.qameta.allure.testng.AllureTestNg;\n" +
                "import org.testng.annotations.BeforeMethod;\n" +
                "import org.testng.annotations.Listeners;\n" +
                "import org.testng.annotations.Test;\n" +
                "import APITestSuite.BaseAPI_Test;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;"+
                "\n" +
                "@Listeners(AllureTestNg.class)\n" +
                "public class "+componentName+"_Test extends BaseAPI_Test{\n" +
                " \n" +
                "\n" +
                "}";
    }

    public static String getTestMethodTemplate(String product,String componentName,String connectionType,String domainName,String endpointName,String headers,String method){
        String actionClassObject = componentName.substring(0,1).toLowerCase()+componentName.substring(1);
        return
                "        \n\t\t@Test\n" +
                "        public void verifyValidResponseOfRequest"+product+"_"+componentName+"API(){\n" +
                "        RESOURCE_FOLDER = FilePath.API_TESTData_PATH;\n" +
                "        TEST_FOLDER = \""+product+"/"+componentName+"TestFolder\";\n" +
                "        TEST_FILE = \""+product+"/"+componentName+"_TestData.json\";\n" +
                "        TEST_NAME = \"testCaseName\";\n" +
                "        SUCCESS_MSG = \"Successful response with status 200 is received\";\n" +
                "        FAILURE_MSG = \"Successful response 200 not received\";\n" +
                "         \n" +
                "         \n" +
                "         //reading test data file\n" +
                "        Map<String,Object> headers = new HashMap<String,Object>();\n"+
                "         "+headers+
                "         \nString requestBody = JsonReaderService.readTest_JsonFile(\n" +
                "                                RESOURCE_FOLDER,\n" +
                "                                TEST_FOLDER,\n" +
                "                                TEST_FILE,\n" +
                "                                \"testCase200\");\n" +
                "                \n" +
                "          //fetching response from api request\n" +
                "          response = "+actionClassObject+"PageActions.apiRequest(\""+connectionType+"://" +
                                       domainName   +
                                       endpointName  +"\","+
                                                    "headers,\n" +
                "                                   requestBody,\""+method+"\");" +
                "                         //logging the response in report\n" +
                "           assert_report.logResponse(response,logger,extentTest);\n" +
                "                \n" +
                "                         //Using custom assertion\n" +
                "           assert_report.AssertFalse(\n" +
                "                                 logger,\n" +
                "                                 extentTest,\n" +
                "                                 response.asString().isEmpty(),\n" +
                "                                 FAILURE_MSG,\n" +
                "                                 SUCCESS_MSG);\n" +
                "           assert_report.AssertTrue(\n" +
                "                    logger,\n" +
                "                    extentTest,\n" +
                "                    response.statusCode()==200,\n" +
                "                    FAILURE_MSG,\n" +
                "                    SUCCESS_MSG);"+
                "                     }\n";
    }



}


