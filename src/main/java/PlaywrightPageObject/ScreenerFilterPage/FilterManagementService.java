package PlaywrightPageObject.ScreenerFilterPage;

import Constants.FilePath;
import Utilities.JsonReaderService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.File;
import java.util.*;
import java.util.logging.Filter;

public class FilterManagementService {


    public Double getRandomDoubleInRange(Double min, Double max){
        return min + Math.random() * (max - min);
       // return new Random().nextDouble((max - min) + 1) + min;
    }

    public int getRandomIntInRange(int min, int max){
        System.out.println("Generating random int between " + min + " and " + max);
        return new Random().nextInt((max - min) + 1) + min;
    }


    public LinkedList<String> getRandomRangeForGraphFilter(Double min,Double max) {
        LinkedList<String> rangeValues = new LinkedList<>();
//        Double minValue = Double.parseDouble(min);
//        Double maxValue = Double.parseDouble(max);

        Double randomMin = getRandomDoubleInRange(min, max/2);
        Double randomMax = getRandomDoubleInRange(max/2, min);
        System.out.println(randomMax);
        System.out.println(randomMin);
        rangeValues.add(String.format("%.2f", randomMin));
        rangeValues.add(String.format("%.2f", randomMax));

        return rangeValues;
    }

    public LinkedList<String> getRandomRangeForGraphFilterFinancial(Double min,Double max,String type,String currencyShort) {
        LinkedList<String> rangeValues = new LinkedList<>();
//        Double minValue = Double.parseDouble(min);
//        Double maxValue = Double.parseDouble(max);

        Double randomMin = getRandomDoubleInRange(min, max/2);
        Double randomMax = getRandomDoubleInRange(max/2, min);

        rangeValues.add(String.format("%.2f", randomMin)+"%");
        rangeValues.add(String.format("%.2f", randomMax)+"%");
        rangeValues.add(type);
        rangeValues.add(currencyShort);
        return rangeValues;
    }

    public LinkedList<String> getRandomRangeForGraphYearFilter(int min,int max) {
        LinkedList<String> rangeValues = new LinkedList<>();


        int randomMin = getRandomIntInRange(min, max-50);
        int randomMax = getRandomIntInRange(max-50, max);

        System.out.println("Random Min Year: " + randomMin);
        System.out.println("Random Max Year: " + randomMax);
        rangeValues.add(String.valueOf(randomMin));
        rangeValues.add(String.valueOf(randomMax));

        return rangeValues;
    }

    public LinkedList<String> getMultiSelectInput(String response){

        // Base URI
//        RestAssured.baseURI = "https://prodapigw-research-service.vccedge.com";
//
//        // Request body
//        String requestBody = "{\n" +
//                "  \"dataField\": \""+ff.filterName+"\",\n" +
//                "  \"request\": []\n" +
//                "}";
//
//        // Create request
//        RequestSpecification request = RestAssured.given()
//                .header("authorization", auth)
//                .header("content-type", "application/json")
//                .body(requestBody);
//
//        // Send POST request
//        Response response = request
//                .post("/services/research/sourcing/multi_select_values");
//
//        // Print response
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asPrettyString());
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);

        // Extract 'values' lists from each data element
        List<List<Map<String, String>>> allValues = jsonPath.getList("data.values");
        LinkedList<String> InputValue = new LinkedList<>();
        System.out.println("All Values:");

        // Flatten and print names + dbValues
        for (List<Map<String, String>> valuesGroup : allValues) {
            for (Map<String, String> value : valuesGroup) {
                System.out.println("Name: " + value.get("name") +
                        " | dbValue: " + value.get("dbValue"));
                InputValue.add(value.get("name"));
            }
        }
     return InputValue;

    }

    public LinkedList<String> getTopSelectInput(String response){
     System.out.println(response);
    JsonPath jsonPath = new JsonPath(response);

    // Extract 'values' lists from each data element
    List<Map<String, String>> allValues = jsonPath.getList("data");
    LinkedList<String> InputValue = new LinkedList<>();
        System.out.println("All Values:");

    // Flatten and print names + dbValues
        for (Map<String, String> value : allValues) {
            System.out.println("Name: " + value.get("name") +
                    " | dbValue: " + value.get("dbValue"));
            InputValue.add(value.get("name"));
        }

     return InputValue;

}

    public String getMultiSelectInputOption(FilterFormat ff, int index,String response){
        if(ff.filterName.equalsIgnoreCase("has financials")){
        return "2024";
        }else{
        LinkedList<String> inputValues = getMultiSelectInput(response);
        return inputValues.get(index);}
    }

    public String getTopSelectInputOption(FilterFormat ff, int index,String response){
        LinkedList<String> inputValues = getTopSelectInput(response);
        return inputValues.get(index);
    }

    public LinkedList<String> getRangeOfInputValuesGraphYear(String response){
//        RestAssured.baseURI = "https://prodapigw-research-service.vccedge.com";
//
//        // Request body
//        String requestBody = "{\n" +
//                "  \"dataField\": \"foundedYear\",\n" +
//                "  \"request\": []\n" +
//                "}";
//
//        // Create and configure request
//        RequestSpecification request = RestAssured.given()
//                .header("authorization", "eyJhbGciOiJIUzI1NiJ9.eyJtYWlsIjoiYXl1c2guZ2F1ckBoaW5kdXN0YW50aW1lcy5jb20iLCJ1c2VybmFtZSI6IkF5dXNoIEdhdXIiLCJzdWIiOiI2NzEyYjJlYzNiMzU2MDIwMDFlM2U1YjYiLCJpYXQiOjE3NjE1MDMwNTQsImV4cCI6MTc2MTUwNjY1NH0.b_XZ1fckPSCL6EAmX_skvrRw7twdaV_6uHCTtsnaLwo")
//                .header("content-type", "application/json")
//                .body(requestBody);
//
//        // Send POST request
//        Response response = request.post("/services/research/sourcing/range");
//
//        // Print status and response
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        int min = (int) jsonPath.getDouble("data.min");
        int max = (int) jsonPath.getDouble("data.max");
        return getRandomRangeForGraphYearFilter(min,max);
    }

    public LinkedList<String> getRangeOfInputValuesGraphFinancial(String response){
        // Send POST request
        JsonPath jsonPath = new JsonPath(response);

        String currency = new String();
        String currencyShort = new String();
        if(jsonPath.get("data.financialField.currency")!=null) {
            currency = jsonPath.getString("data.financialField.currency");
            currencyShort=jsonPath.getString("data.financialField.currencyShort");
            System.out.println("Currency: " + currency);
        }
        Double min =  jsonPath.getDouble("data.min");
        System.out.println("Min Value: " + min);
        Double max =  jsonPath.getDouble("data.max");
        System.out.println("Max Value: " + max);
        return getRandomRangeForGraphFilterFinancial(min,max,currency,currencyShort);
    }

    public LinkedList<String> getRangeOfInputValuesGraph(String response){

        // Send POST request
        JsonPath jsonPath = new JsonPath(response);

         Double min =  jsonPath.getDouble("data.min");
        System.out.println("Min Value: " + min);
        Double max =  jsonPath.getDouble("data.max");
        System.out.println("Max Value: " + max);
        return getRandomRangeForGraphFilter(min,max);
    }

    public Double removeTrailingZeroesAndReturnValue(Double value){
        return Double.parseDouble(String.valueOf(value).replaceAll("\\.?0*$", ""));
    }

    public LinkedList<String> getRangeOfInputValuesGraphFinancialGrowth(String response){

        // Send POST request
        JsonPath jsonPath = new JsonPath(response);

        String type = new String();
        String currencyShort = new String();
        if(jsonPath.get("data.financialField.currency")!=null) {
            type = jsonPath.getString("data.financialField.type");
            currencyShort=jsonPath.getString("data.financialField.currencyShort");
        }
        Double min =  jsonPath.getDouble("data.min");
        System.out.println("Min Value: " + min);
        Double max =  jsonPath.getDouble("data.max");
        System.out.println("Max Value: " + max);
        return getRandomRangeForGraphFilterFinancial(min,max,type,currencyShort);
    }

    public static Map<String, String> getAllFiltersUnderTypeFromJsonFile(String screenerName,String requiredFilterType){
        String jsonFilePath = new String();
        switch (screenerName.toLowerCase()){
            case "company":
                jsonFilePath=FilePath.Screening_TestFolder+"CompScreeningManageFilters_TestData.json";
                break;
            case "asset_manager":
                jsonFilePath = FilePath.Screening_TestFolder + "AssetManagerScreeningManageFilters_TestData.json";
                break;
            case "fund":
                jsonFilePath = FilePath.Screening_TestFolder + "FundScreeningManageFilters_TestData.json";
                break;
            case "family_office":
                jsonFilePath = FilePath.Screening_TestFolder + "FamilyOfficeManageFilters_TestData.json";
                break;
            case "limited_partner":
                jsonFilePath = FilePath.Screening_TestFolder + "LimitedPartnerManageFilters_TestData.json";
                break;
            case "all_deals":
                jsonFilePath = FilePath.Screening_TestFolder + "AllDealsManageFilters_TestData.json";
                break;

            case "ecm_deals":
                jsonFilePath = FilePath.Screening_TestFolder + "ECMManageFilters_TestData.json";
                break;
                
            case "pe_deals":
                jsonFilePath = FilePath.Screening_TestFolder + "PEIManageFilters_TestData.json";
                break;
            case "mna_deal":
                jsonFilePath = FilePath.Screening_TestFolder + "M&AManageFilters_TestData.json";
                break;
            case "pee_deal":
                jsonFilePath = FilePath.Screening_TestFolder + "PrivateEquityExitsManageFilters_TestData.json";
                break;
            case "debt_deal":
                jsonFilePath = FilePath.Screening_TestFolder + "DebtTransManageFilters_TestData.json";
                break;

        }
        Map<String, String> filterTypeMap = null;
        try {
            filterTypeMap = parseFilterTypes(jsonFilePath,requiredFilterType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Print the map
        return filterTypeMap;

    }

    public static Map<String, String> parseFilterTypes(String jsonFilePath,String requiredFilterType) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));
        Map<String, String> result = new HashMap<>();

        for (JsonNode group : root) {
            String groupName = group.path("groupName").asText();
            JsonNode subGroups = group.path("subGroups");
            if (subGroups.isArray()) {
                for (JsonNode subGroup : subGroups) {
                    String subGroupName = subGroup.has("subGroupName") ?
                            subGroup.path("subGroupName").asText() :
                            subGroup.path("SubGroupName").asText();
                    // Handle subCategories if present
                    if (subGroup.has("subCategories")) {
                        for (JsonNode subCategory : subGroup.path("subCategories")) {
                            String subCategoryName = subCategory.path("subCategoryName").asText();
                            for (JsonNode filter : subCategory.path("filters")) {
                                if(filter.path("filterType").asText().equals(requiredFilterType)){
                                String filterName = filter.path("filterName").asText();
                                String filterType = filter.has("filterType") ? filter.path("filterType").asText() : null;
                                String key = groupName + "_" + subGroupName + "_" + subCategoryName + "_" + filterName;
                                result.put(key, filterType);}
                            }
                        }
                    }
                    // Handle filters directly under subGroup
                    if (subGroup.has("filters")) {
                        for (JsonNode filter : subGroup.path("filters")) {
                            String filterName = filter.path("filterName").asText();
                            String filterType = filter.has("filterType") ? filter.path("filterType").asText() : null;
                            String key = groupName + "_" + subGroupName + "_" + filterName;
                            result.put(key, filterType);
                        }
                    }
                }
            }
        }
        return result;
    }


}
