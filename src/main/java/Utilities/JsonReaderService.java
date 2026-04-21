package Utilities;

import PlaywrightPageObject.PojoClasses.DTFilter;
import PlaywrightPageObject.PojoClasses.ScreenerTab;
import PlaywrightPageObject.ScreenerDTManageColumn;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class JsonReaderService {


    // to run and verify utility or output of utility
//    public static void main(String arg[]){
//        readTest_JsonFile(Api_TestDataPath,"GlobalSearchComponentTestFolder","GlobalSearchComponentTestData.json","testWipro");
//    }

    public static String readTest_JsonFile(String resourceFolder, String folder, String jsonFilePath,String testName){
        String testFilePath = resourceFolder+folder+File.separator+jsonFilePath;
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and map to JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(testFilePath));

            // Get the desired node from the JSON structure
            JsonNode specificNode = rootNode.get(testName);

            // Convert the node to a String
            String nodeAsString = specificNode.toString();

            return nodeAsString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getDataFromJsonFile_JsonFormat(String resourceFoler,String testFolder,String fileName){
        String filepath = resourceFoler+File.separator+testFolder+File.separator+fileName;

        System.out.println(filepath);
        InputStream jsonStream = null;
        try {
            jsonStream = Files.newInputStream(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonStream;
    }

    public static String readTest_JsonFile(String jsonFilePath){
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and map to JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

            // Convert the node to a String
            String nodeAsString = rootNode.toString();

            return nodeAsString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValueFromJson(String jsonString, String pathName){


        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and map to JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // Get the desired node from the JSON structure
            JsonNode specificNode = rootNode.get(pathName);

            // Check if the node exists
            if (specificNode != null && specificNode.isTextual()) {
                // Convert the node to a string
                String nodeAsString = specificNode.asText();  // Use asText() for the actual value
                return nodeAsString;
            } else if (specificNode != null) {
                // If it's not textual, you can print its JSON structure
                String nodeAsString = specificNode.toString();  // Use toString() for JSON format
                return nodeAsString;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ScreenerTab> readScreenerTabs(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<ScreenerTab>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    public static ScreenerTab getTabByName(List<ScreenerTab> tabs, String tabName) {

        return tabs.stream()
                .filter(tab -> tabName.equalsIgnoreCase(tab.getTabName()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Tab '" + tabName + "' not found in JSON"));
    }

    public static List<String> getCurrencyConversionColumns(ScreenerTab tab) {

        return tab.getFilters().stream()
                .filter(Objects::nonNull)
                .filter(f -> Boolean.TRUE.equals(f.getCompanyConversion()))
                .map(DTFilter::getFilterName)
                .collect(Collectors.toList());

    }

}
