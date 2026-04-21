package Utilities;
import Constants.JIRA_CONSTANTS;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.*;

public class ZephyrIntegration_Service {

    private static final String ZEPHYR_BASE_URL = "https://htmedia.atlassian.net/jira";
    private static String ZEPHYR_TOKEN ;
    RestAssuredService restAssuredService;
    Map<String, Object> params = new HashMap<String,Object>();
    Map<String,Object> requestMapBody = new HashMap<String,Object>();

    public static void main(String args[]){
        ZephyrIntegration_Service zephyrIntegration_service = new ZephyrIntegration_Service();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the test folder(your test folder name on jira): ");
        String testFolder = scanner.nextLine();
        System.out.print("\nEnter the test component(WEB/API/DB): ");
        String testComponent = scanner.nextLine();
        zephyrIntegration_service.fetchFolderDetails(testFolder,testComponent);
        System.out.print("\nPull Entire Test cases(yes/no): ");
        String pullEntire = scanner.nextLine();
        if(pullEntire.equals("yes")) {
                System.out.print("\nEnter the test type(Regression/Smoke/testCaseId): ");
            String testType = scanner.nextLine();
            zephyrIntegration_service.fetchFolderDetails(testFolder, testType);
        }
    }

    private String getAccessToken(){
        ZEPHYR_TOKEN = System.getenv("JIRA_ACCESS_TOKEN").toString().equals(null)? null : System.getenv("JIRA_ACCESS_TOKEN");
        return ZEPHYR_TOKEN;
    }

    public void fetchFolderDetails(String testComponent,String testType){
        boolean isLast = false;
        int startAt=0;
        int maxResults=10;

        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        List<String> names = new LinkedList<String>();
        List<String> ids = new LinkedList<String>();
        while(!isLast) {
            params.put("projectKey",JIRA_CONSTANTS.PROJECT_KEY);
            params.put("startAt",String.valueOf(startAt));
            params.put("maxResults",String.valueOf(maxResults));
            Response response = restAssuredService.getRequest_token_params(JIRA_CONSTANTS.JIRA_BASE_URL +
                            JIRA_CONSTANTS.GET_FOLDER_ENDPOINT, "application/json",
                    "Bearer " + accessToken, params);
            System.out.println(response.asString());
            isLast = response.path("isLast");
            startAt = response.path("startAt");
            startAt+=maxResults;
            maxResults = response.path("maxResults");
            JsonPath jsonPath = response.jsonPath();
            names = jsonPath.getList("values.name");
            ids = jsonPath.getList("values.id");
            int folderNumber = names.indexOf(testComponent);
            if(folderNumber != -1 ){
                fetchTestCases(testComponent,String.valueOf(ids.get(folderNumber)),testType);
            }
            if(folderNumber==-1 && isLast) {
                try {
                    throw new Exception("Folder not found on zephyr scale");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
    }

    public void fetchFolderDetails(String testComponent,String testType,String TestCaseId){
        boolean isLast = false;
        int startAt=0;
        int maxResults=10;

        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        List<String> names = new LinkedList<String>();
        List<String> ids = new LinkedList<String>();
        while(!isLast) {
            params.put("projectKey",JIRA_CONSTANTS.PROJECT_KEY);
            params.put("startAt",String.valueOf(startAt));
            params.put("maxResults",String.valueOf(maxResults));
            Response response = restAssuredService.getRequest_token_params(JIRA_CONSTANTS.JIRA_BASE_URL +
                            JIRA_CONSTANTS.GET_FOLDER_ENDPOINT, "application/json",
                    "Bearer " + accessToken, params);
            System.out.println(response.asString());
            isLast = response.path("isLast");
            startAt = response.path("startAt");
            startAt+=maxResults;
            maxResults = response.path("maxResults");
            JsonPath jsonPath = response.jsonPath();
            names = jsonPath.getList("values.name");
            ids = jsonPath.getList("values.id");
            int folderNumber = names.indexOf(testComponent);
            if(folderNumber != -1 ){
                fetchTestCase(testComponent,String.valueOf(ids.get(folderNumber)),TestCaseId,testType);
            }
            if(folderNumber==-1 && isLast) {
                try {
                    throw new Exception("Folder not found on zephyr scale");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fetchTestCases(String component,String folderId,String testType) {
        System.out.println(folderId);
        boolean isLast = false;
        int startAt=0;
        int maxResults=10;

        String accessToken = getAccessToken();

        restAssuredService = new RestAssuredService();
        List<String> keys = new LinkedList<String>();
        List<String> names = new LinkedList<String>();
        List<String> priorities = new LinkedList<String>();
        Set<String> testCaseName = new TreeSet<String>();
        Map<String,Object> testCasePriority = new HashMap<String,Object>();
        while(!isLast) {
            params.put("projectKey", JIRA_CONSTANTS.PROJECT_KEY);
            params.put("folderId", folderId);
            params.put("startAt", String.valueOf(startAt));
            params.put("maxResults", String.valueOf(maxResults));
            Response response = restAssuredService.getRequest_token_params(JIRA_CONSTANTS.JIRA_BASE_URL +
                            JIRA_CONSTANTS.GET_TESTCASES_ENDPOINT, "application/json",
                    "Bearer " + accessToken, params);
            System.out.println(response.asString());
            isLast = response.path("isLast");
            startAt = response.path("startAt");
            startAt += maxResults;
            maxResults = response.path("maxResults");
            JsonPath jsonPath = response.jsonPath();
            keys = jsonPath.getList("values.key");
            names = jsonPath.getList("values.name");
            priorities = jsonPath.getList("values.priority.id");

            for (int index = 0; index < keys.size(); index++) {
                String keyValue=keys.get(index).replaceAll("[^a-zA-Z0-9]", "_");
                String namesValue=names.get(index).replaceAll("[^a-zA-Z0-9]", "_");
                testCaseName.add( keyValue+ "__" + namesValue);
                testCasePriority.put(keyValue+ "__" + namesValue,priorities.get(index));
            }
//            for(String testCase : testCaseName){
//                System.out.println(testCase);
//            }
        }String product = new String();
        TestGenerators.createZephyrTestClass(product,component,testCaseName,testType,testCasePriority);

        }

    public void fetchTestCase(String component , String folderId,String caseId,String testType){
        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        String key = new String();
        String name = new String();
        String testCaseName = new String();
        params.put("projectKey",JIRA_CONSTANTS.PROJECT_KEY);
        params.put("folderId",folderId);
        params.put("caseId",caseId);
        Response response = restAssuredService.getRequest_token_params(JIRA_CONSTANTS.JIRA_BASE_URL +
                        JIRA_CONSTANTS.GET_TESTCASE_ENDPOINT+caseId, "application/json",
                "Bearer " + accessToken, params);
        System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        key = jsonPath.get("values.key");
        name = jsonPath.get("values.name");

        String keyValue=key.replaceAll("[^a-zA-Z0-9]", "_");
        String namesValue=name.replaceAll("[^a-zA-Z0-9]", "_");
        testCaseName = keyValue+ "__" + namesValue;

        TestGenerators.createZephyrTestCase(component,testCaseName,testType);
    }

    public void updateTestCase(String executionID,String endTime,String executionTime,String status){
        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        String key = new String();
        String name = new String();
        String testCaseName = new String();
        params.put("testExecutionIdOrKey",executionID);
        requestMapBody.put("statusName",status);
        requestMapBody.put("actualEndDate",endTime);
        requestMapBody.put("executionTime",executionTime);

        Response response = restAssuredService.putRequest_token_params_bodyMap(JIRA_CONSTANTS.JIRA_BASE_URL +
                        JIRA_CONSTANTS.PUT_TESTCASE_EXECUTION+executionID, "application/json",
                "Bearer " + accessToken, params,requestMapBody);
        System.out.println(response.toString());
    }

    public String getExecutionId(String CaseId, String testCycle){
        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        String key = new String();
        String name = new String();
        String testCaseName = new String();
        params.put("projectKey",JIRA_CONSTANTS.PROJECT_KEY);
        params.put("testCase",CaseId);
        params.put("testCycle",testCycle);
        System.out.println(params.entrySet());
        Response response = restAssuredService.getRequest_token_params(JIRA_CONSTANTS.JIRA_BASE_URL +
                        JIRA_CONSTANTS.GET_TESTCASE_EXECUTION, "application/json",
                "Bearer " + accessToken,params);
        System.out.println("response::"+response.asString());
        JsonPath jsonPath = response.jsonPath();
        String executionId = new String();
        try{ executionId = jsonPath.getList("values.key").get(0).toString();
            System.out.println("executionId"+executionId);}
        catch (IndexOutOfBoundsException iob){
            System.out.println("No execution id found for the test case");
        }
        return executionId;
    }

    public String getTestCycleID(String testCycleName) {
        String accessToken = getAccessToken();
        restAssuredService = new RestAssuredService();
        String key = new String();
        String name = new String();
        String testCaseName = new String();

        Response response = restAssuredService.getRequest_token(JIRA_CONSTANTS.JIRA_BASE_URL +
                        JIRA_CONSTANTS.GET_TESTCYCLE_ENDPOINT+testCycleName, "application/json",
                "Bearer " + accessToken);
        System.out.println(response.asString());

        return response.path("id");
    }

}

