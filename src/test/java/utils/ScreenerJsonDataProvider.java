package utils;
import com.google.gson.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
public class ScreenerJsonDataProvider {

    /**
     * TestNG Data Provider for CompScreeningManageFilters_TestData.json
     * Provides filter data based on filterType parameter
     */


        private static final String CompanyScreener_JSON_FILE_PATH = "src/test/resources/WEBTestSuiteResources/Screening_TestFolder/CompScreeningManageFilters_TestData.json";
        private static final String AssetManagerScreener_JSON_FILE_PATH = "src/test/resources/WEBTestSuiteResources/Screening_TestFolder/AssetManagerScreeningManageFilters_TestData.json";
        private static final String FundScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/FundScreeningManageFilters_TestData.json";
        private static final String FamilyOfficeScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/FamilyOfficeManageFilters_TestData.json";
        private static final String LimitedPartnerScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/LimitedPartnerManageFilters_TestData.json";
        private static final String AllDealsScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/AllDealsManageFilters_TestData.json";
        private static final String EcmDealsScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/ECMManageFilters_TestData.json";
        private static final String PeDealsScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/PEIManageFilters_TestData.json";
        private static final String MnaDealScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/M&AManageFilters_TestData.json";
        private static final String PEEDealScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/PrivateEquityExitsManageFilters_TestData.json";
        private static final String DebtDealScreener_JSON_FILE_PATH ="src/test/resources/WEBTestSuiteResources/Screening_TestFolder/DebtTransManageFilters_TestData.json";
        /**
         * Data Provider that returns all filters
         * @return Object[][] with columns: groupName, subGroupName, category, subCategory, filterName, filterType, isDefault
         */
        @DataProvider(name = "allFilters")
        public static Object[][] getAllFilters(Method method) {
            String screener = method.getAnnotation(Test.class)
                    .testName();
            return getFiltersByType(screener,null);
        }

        /**
         * Data Provider that returns filters by specific filterType
         * Usage: @Test(dataProvider = "filtersByType", dataProviderClass = FilterDataProvider.class)
         *        public void testFilter(String groupName, String subGroupName, String category,
         *                               String subCategory, String filterName, String filterType, boolean isDefault) { }
         *
         * To filter by type, pass filterType as method parameter
         */
        @DataProvider(name = "filtersByType")
        public static Object[][] getFiltersByType(String screener,String filterType) {
            List<Object[]> filterData = new ArrayList<>();

            try {
                JsonArray jsonArray = parseJsonFile(screener);

                for (JsonElement groupElement : jsonArray) {
                    JsonObject group = groupElement.getAsJsonObject();
                    String groupName = getJsonString(group, "groupName");
                    System.out.println("Processing group: " + groupName);
                    JsonArray subGroups = group.getAsJsonArray("subGroups");
                    System.out.println("SubGroups: " + subGroups);
                    if (subGroups != null) {
                        processSubGroups(screener,subGroups, groupName, filterType, filterData);
                    }else{
                        processFilters(screener,group.getAsJsonArray("filters"), groupName, null, null, null, filterType, filterData);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load filter data from JSON: " + e.getMessage());
            }

            return filterData.toArray(new Object[0][]);
        }

        /**
         * Data Provider for MultiSelect filters only
         */
        @DataProvider(name = "multiSelectFilters")
        public static Object[][] getMultiSelectFilters(Method method) {
            String screener = method.getName();
            System.out.println("Fetching MultiSelect filters...");
            return getFiltersByType("company","multi_select_values");
        }


    @DataProvider(name = "getGraphFinancialFilters")
    public static Object[][] getGraphFinancialFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","graph_financial");
    }

    @DataProvider(name = "getGraphYearFilters")
    public static Object[][] getGraphYearFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","graph_year");
    }

    @DataProvider(name = "getGraphFinancialGrowthFilters")
    public static Object[][] getGraphFinancialGrowthFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","graph_financial");
    }

    @DataProvider(name = "getAllGraphFinancialGrowthFilters")
    public static Object[][] getAllGraphFinancialGrowthFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph_financial_growth");
    }

    @DataProvider(name = "getGraphFilters")
    public static Object[][] getGraphFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","graph");
    }

    @DataProvider(name = "getLocationFilters")
    public static Object[][] getLocationFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","Headquarters");
    }

    @DataProvider(name = "getAllLocationFilters")
    public static Object[][] getAllLocationFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph");
    }

    @DataProvider(name = "getAllIndustryClassificationFilters")
    public static Object[][] getAllIndustryClassificationFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph");
    }

    @DataProvider(name = "getIndustryClassificationFilters")
    public static Object[][] getIndustryClassificationFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByType("company","Sector");
    }

    @DataProvider(name = "getAllGraphFilters")
    public static Object[][] getAllGraphFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph");
    }

    @DataProvider(name = "getAllGraphYearFilters")
    public static Object[][] getAllGraphYearFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph_year");
    }

    @DataProvider(name = "getAllGraphFinancialFilters")
    public static Object[][] getAllGraphFinancialFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("graph_financial");
    }

    @DataProvider(name = "getFiltersByTypeFromAllScreener")
    public static Object[][] getFiltersByTypeFromAllScreener(String filterType) {
        List<Object[]> filterData = new ArrayList<>();
        String screener_list[]={"company","asset_manager", "fund", "limited_partner","family_office", "all_deals", "ecm_deals", "pe_deals","mna_deal","pee_deal", "debt_deal"};
        for(String screener:screener_list){try {
            JsonArray jsonArray = parseJsonFile(screener);

            for (JsonElement groupElement : jsonArray) {
                JsonObject group = groupElement.getAsJsonObject();
                String groupName = getJsonString(group, "groupName");
                System.out.println("Processing group: " + groupName);
                JsonArray subGroups = group.getAsJsonArray("subGroups");
                System.out.println("SubGroups: " + subGroups);
                if (subGroups != null) {
                    processSubGroups(screener,subGroups, groupName, filterType, filterData);
                }
                else{
                    processFilters(screener,group.getAsJsonArray("filters"), groupName, null, null, null, filterType, filterData);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load filter data from JSON: " + e.getMessage());
        }
        }

        return filterData.toArray(new Object[0][]);
    }

    /**
     * Data Provider for MultiSelect filters only
     */
    @DataProvider(name = "getMultiSelectFiltersFromAllScreener")
    public static Object[][] getMultiSelectFiltersFromAllScreener(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching MultiSelect filters...");
        return getFiltersByTypeFromAllScreener("multi_select_values");
    }

    @DataProvider(name = "topFilters")
    public static Object[][] getTopFilters(Method method) {
        String screener = method.getAnnotation(Test.class)
                .testName();
        System.out.println("Fetching top filters...");
        return getFiltersByType("company","top");
    }

        /**
         * Data Provider for default filters only
         */
        @DataProvider(name = "defaultFilters")
        public static Object[][] getDefaultFilters() {
            List<Object[]> filterData = new ArrayList<>();

            try {
                JsonArray jsonArray = parseJsonFile();

                for (JsonElement groupElement : jsonArray) {
                    JsonObject group = groupElement.getAsJsonObject();
                    String groupName = getJsonString(group, "groupName");

                    JsonArray subGroups = group.getAsJsonArray("subGroups");
                    if (subGroups != null) {
                        processSubGroupsForDefault(subGroups, groupName, filterData);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load default filter data from JSON: " + e.getMessage());
            }

            return filterData.toArray(new Object[0][]);
        }

        /**
         * Data Provider for non-default filters only
         */
        @DataProvider(name = "nonDefaultFilters")
        public static Object[][] getNonDefaultFilters() {
            List<Object[]> filterData = new ArrayList<>();

            try {
                JsonArray jsonArray = parseJsonFile();

                for (JsonElement groupElement : jsonArray) {
                    JsonObject group = groupElement.getAsJsonObject();
                    String groupName = getJsonString(group, "groupName");

                    JsonArray subGroups = group.getAsJsonArray("subGroups");
                    if (subGroups != null) {
                        processSubGroupsForNonDefault(subGroups, groupName, filterData);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load non-default filter data from JSON: " + e.getMessage());
            }

            return filterData.toArray(new Object[0][]);
        }

        /**
         * Data Provider that returns data as Map for easier access
         */
        @DataProvider(name = "filtersAsMap")
        public static Object[][] getFiltersAsMap() {
            List<Object[]> filterData = new ArrayList<>();

            try {
                JsonArray jsonArray = parseJsonFile();

                for (JsonElement groupElement : jsonArray) {
                    JsonObject group = groupElement.getAsJsonObject();
                    String groupName = getJsonString(group, "groupName");

                    JsonArray subGroups = group.getAsJsonArray("subGroups");
                    if (subGroups != null) {
                        processSubGroupsAsMap(subGroups, groupName, filterData);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load filter data as map from JSON: " + e.getMessage());
            }

            return filterData.toArray(new Object[0][]);
        }

        /**
         * Data Provider for filters grouped by groupName
         */
        @DataProvider(name = "filtersByGroup")
        public static Object[][] getFiltersByGroup(String screener,String targetGroupName) {
            List<Object[]> filterData = new ArrayList<>();

            try {
                JsonArray jsonArray = parseJsonFile();

                for (JsonElement groupElement : jsonArray) {
                    JsonObject group = groupElement.getAsJsonObject();
                    String groupName = getJsonString(group, "groupName");

                    if (targetGroupName == null || groupName.equals(targetGroupName)) {
                        JsonArray subGroups = group.getAsJsonArray("subGroups");
                        if (subGroups != null) {
                             processSubGroups(screener,subGroups, groupName, null, filterData);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load filter data by group from JSON: " + e.getMessage());
            }
            return filterData.toArray(new Object[0][]);
        }

        // ========== Helper Methods ==========

        /**
         * Parse JSON file and return JsonArray
         */
        private static JsonArray parseJsonFile() throws IOException {
            try (FileReader reader = new FileReader(CompanyScreener_JSON_FILE_PATH)) {
                JsonParser parser = new JsonParser();
                return parser.parse(reader).getAsJsonArray();
            }
        }

    private static JsonArray parseJsonFile(String screener) throws IOException {
            String filePath = new String();
            switch(screener.toLowerCase()){
                case "company":filePath=CompanyScreener_JSON_FILE_PATH;break;
                case "asset_manager": filePath = AssetManagerScreener_JSON_FILE_PATH;break;
                case "fund":filePath = FundScreener_JSON_FILE_PATH;break;
                case "limited_partner":filePath=LimitedPartnerScreener_JSON_FILE_PATH;break;
                case "family_office":filePath = FamilyOfficeScreener_JSON_FILE_PATH;break;
                case "all_deals":filePath=AllDealsScreener_JSON_FILE_PATH;break;
                case "ecm_deals":filePath=EcmDealsScreener_JSON_FILE_PATH;break;
                case "pe_deals":filePath=PeDealsScreener_JSON_FILE_PATH;break;
                case "mna_deal":filePath=MnaDealScreener_JSON_FILE_PATH;break;
                case "pee_deal":filePath=PEEDealScreener_JSON_FILE_PATH;break;
                case "debt_deal":filePath=DebtDealScreener_JSON_FILE_PATH;break;
                };
        System.out.println("Parsing JSON file: " + filePath);
            try (FileReader reader = new FileReader(filePath)) {
            JsonParser parser = new JsonParser();
            return parser.parse(reader).getAsJsonArray();
        }
    }

        /**
         * Process subGroups and extract filter data
         */
        private static void processSubGroups(String screener,JsonArray subGroups, String groupName, String filterType, List<Object[]> filterData) {
            for (JsonElement subGroupElement : subGroups) {
                JsonObject subGroup = subGroupElement.getAsJsonObject();

                // Handle both "subGroupName" and "SubGroupName" (case inconsistency in JSON)
                String subGroupName = getJsonString(subGroup, "subGroupName");
                if (subGroupName == null || subGroupName.isEmpty()) {
                    subGroupName = getJsonString(subGroup, "SubGroupName");
                }

                // Check if subCategories exist
                if (subGroup.has("subCategories")) {
                    JsonArray subCategories = subGroup.getAsJsonArray("subCategories");
                    processSubCategories(screener,subCategories, groupName, subGroupName, filterType, filterData);
                }

                // Check if filters exist directly under subGroup
                if (subGroup.has("filters")) {
                    JsonArray filters = subGroup.getAsJsonArray("filters");
                    processFilters(screener,filters, groupName, subGroupName, null, null, filterType, filterData);
                }
            }
        }

        /**
         * Process subCategories
         */
        private static void processSubCategories(String screener,JsonArray subCategories, String groupName, String subGroupName,
                                                 String filterType, List<Object[]> filterData) {
            for (JsonElement subCategoryElement : subCategories) {
                JsonObject subCategory = subCategoryElement.getAsJsonObject();
                String subCategoryName = getJsonString(subCategory, "subCategoryName");

                if (subCategory.has("filters")) {
                    JsonArray filters = subCategory.getAsJsonArray("filters");
                    processFilters(screener,filters, groupName, subGroupName, "subCategory", subCategoryName, filterType, filterData);
                }
            }
        }

        /**
         * Process filters and add to filterData list
         */
        private static void processFilters(String screener,JsonArray filters, String groupName, String subGroupName,
                                           String category, String subCategory, String filterType, List<Object[]> filterData) {
            for (JsonElement filterElement : filters) {
                JsonObject filter = filterElement.getAsJsonObject();

                String filterName = getJsonString(filter, "filterName");
                System.out.println("Processing filter: " + filterName);
                String currentFilterType = getJsonString(filter, "filterType");
                String filterAPIReference = filter.has("apiReference")?getJsonString(filter, "apiReference"):null;
                String filterApi = filter.has("api")?getJsonString(filter, "api"):null;
                boolean isDefault = filter.has("isDefault") ? filter.get("isDefault").getAsBoolean() : false;

                // If filterType is specified, only include matching filters
                if (filterType == null || filterType.equals(currentFilterType)) {
                    System.out.println("Adding filter: " + filterName + " of type: " + currentFilterType);
                    filterData.add(new Object[]{screener,
                            groupName,
                            subGroupName,
                            category,
                            subCategory,
                            filterName,
                            currentFilterType,
                            isDefault,
                            filterApi,
                            filterAPIReference
                    });
                }
            }
        }

        /**
         * Process subGroups for default filters only
         */
        private static void processSubGroupsForDefault(JsonArray subGroups, String groupName, List<Object[]> filterData) {
            for (JsonElement subGroupElement : subGroups) {
                JsonObject subGroup = subGroupElement.getAsJsonObject();

                String subGroupName = getJsonString(subGroup, "subGroupName");
                if (subGroupName == null || subGroupName.isEmpty()) {
                    subGroupName = getJsonString(subGroup, "SubGroupName");
                }

                if (subGroup.has("subCategories")) {
                    JsonArray subCategories = subGroup.getAsJsonArray("subCategories");
                    processSubCategoriesForDefault(subCategories, groupName, subGroupName, filterData);
                }

                if (subGroup.has("filters")) {
                    JsonArray filters = subGroup.getAsJsonArray("filters");
                    processFiltersForDefault(filters, groupName, subGroupName, null, null, filterData);
                }
            }
        }

        /**
         * Process subCategories for default filters
         */
        private static void processSubCategoriesForDefault(JsonArray subCategories, String groupName, String subGroupName,
                                                           List<Object[]> filterData) {
            for (JsonElement subCategoryElement : subCategories) {
                JsonObject subCategory = subCategoryElement.getAsJsonObject();
                String subCategoryName = getJsonString(subCategory, "subCategoryName");

                if (subCategory.has("filters")) {
                    JsonArray filters = subCategory.getAsJsonArray("filters");
                    processFiltersForDefault(filters, groupName, subGroupName, "subCategory", subCategoryName, filterData);
                }
            }
        }

        /**
         * Process filters for default only
         */
        private static void processFiltersForDefault(JsonArray filters, String groupName, String subGroupName,
                                                     String category, String subCategory, List<Object[]> filterData) {
            for (JsonElement filterElement : filters) {
                JsonObject filter = filterElement.getAsJsonObject();

                String filterName = getJsonString(filter, "filterName");
                String filterType = getJsonString(filter, "filterType");
                boolean isDefault = filter.has("isDefault") ? filter.get("isDefault").getAsBoolean() : false;
                String filterAPIReference = filter.has("apiReference")?getJsonString(filter, "apiReference"):null;
                String filterApi = filter.has("api")?getJsonString(filter, "api"):null;

                if (isDefault) {
                    filterData.add(new Object[]{
                            groupName,
                            subGroupName,
                            category,
                            subCategory,
                            filterName,
                            filterType,
                            isDefault,
                            filterApi,
                            filterAPIReference

                    });
                }
            }
        }

        /**
         * Process subGroups for non-default filters only
         */
        private static void processSubGroupsForNonDefault(JsonArray subGroups, String groupName, List<Object[]> filterData) {
            for (JsonElement subGroupElement : subGroups) {
                JsonObject subGroup = subGroupElement.getAsJsonObject();

                String subGroupName = getJsonString(subGroup, "subGroupName");
                if (subGroupName == null || subGroupName.isEmpty()) {
                    subGroupName = getJsonString(subGroup, "SubGroupName");
                }

                if (subGroup.has("subCategories")) {
                    JsonArray subCategories = subGroup.getAsJsonArray("subCategories");
                    processSubCategoriesForNonDefault(subCategories, groupName, subGroupName, filterData);
                }

                if (subGroup.has("filters")) {
                    JsonArray filters = subGroup.getAsJsonArray("filters");
                    processFiltersForNonDefault(filters, groupName, subGroupName, null, null, filterData);
                }
            }
        }

        /**
         * Process subCategories for non-default filters
         */
        private static void processSubCategoriesForNonDefault(JsonArray subCategories, String groupName, String subGroupName,
                                                              List<Object[]> filterData) {
            for (JsonElement subCategoryElement : subCategories) {
                JsonObject subCategory = subCategoryElement.getAsJsonObject();
                String subCategoryName = getJsonString(subCategory, "subCategoryName");

                if (subCategory.has("filters")) {
                    JsonArray filters = subCategory.getAsJsonArray("filters");
                    processFiltersForNonDefault(filters, groupName, subGroupName, "subCategory", subCategoryName, filterData);
                }
            }
        }

        /**
         * Process filters for non-default only
         */
        private static void processFiltersForNonDefault(JsonArray filters, String groupName, String subGroupName,
                                                        String category, String subCategory, List<Object[]> filterData) {
            for (JsonElement filterElement : filters) {
                JsonObject filter = filterElement.getAsJsonObject();

                String filterName = getJsonString(filter, "filterName");
                String filterType = getJsonString(filter, "filterType");
                boolean isDefault = filter.has("isDefault") ? filter.get("isDefault").getAsBoolean() : false;
                String filterAPIReference = filter.has("apiReference")?getJsonString(filter, "apiReference"):null;
                String filterApi = filter.has("api")?getJsonString(filter, "api"):null;

                if (!isDefault) {
                    filterData.add(new Object[]{
                            groupName,
                            subGroupName,
                            category,
                            subCategory,
                            filterName,
                            filterType,
                            isDefault,
                            filterApi,
                            filterAPIReference
                    });
                }
            }
        }

        /**
         * Process subGroups and return as Map
         */
        private static void processSubGroupsAsMap(JsonArray subGroups, String groupName, List<Object[]> filterData) {
            for (JsonElement subGroupElement : subGroups) {
                JsonObject subGroup = subGroupElement.getAsJsonObject();

                String subGroupName = getJsonString(subGroup, "subGroupName");
                if (subGroupName == null || subGroupName.isEmpty()) {
                    subGroupName = getJsonString(subGroup, "SubGroupName");
                }

                if (subGroup.has("subCategories")) {
                    JsonArray subCategories = subGroup.getAsJsonArray("subCategories");
                    processSubCategoriesAsMap(subCategories, groupName, subGroupName, filterData);
                }

                if (subGroup.has("filters")) {
                    JsonArray filters = subGroup.getAsJsonArray("filters");
                    processFiltersAsMap(filters, groupName, subGroupName, null, null, filterData);
                }
            }
        }

        /**
         * Process subCategories as Map
         */
        private static void processSubCategoriesAsMap(JsonArray subCategories, String groupName, String subGroupName,
                                                      List<Object[]> filterData) {
            for (JsonElement subCategoryElement : subCategories) {
                JsonObject subCategory = subCategoryElement.getAsJsonObject();
                String subCategoryName = getJsonString(subCategory, "subCategoryName");

                if (subCategory.has("filters")) {
                    JsonArray filters = subCategory.getAsJsonArray("filters");
                    processFiltersAsMap(filters, groupName, subGroupName, "subCategory", subCategoryName, filterData);
                }
            }
        }

        /**
         * Process filters and return as Map
         */
        private static void processFiltersAsMap(JsonArray filters, String groupName, String subGroupName,
                                                String category, String subCategory, List<Object[]> filterData) {
            for (JsonElement filterElement : filters) {
                JsonObject filter = filterElement.getAsJsonObject();

                String filterName = getJsonString(filter, "filterName");
                String filterType = getJsonString(filter, "filterType");
                String filterAPIReference = filter.has("apiReference")?getJsonString(filter, "apiReference"):null;
                String filterApi = filter.has("api")?getJsonString(filter, "api"):null;
                boolean isDefault = filter.has("isDefault") ? filter.get("isDefault").getAsBoolean() : false;

                Map<String, Object> filterMap = new LinkedHashMap<>();
                filterMap.put("groupName", groupName);
                filterMap.put("subGroupName", subGroupName);
                filterMap.put("category", category);
                filterMap.put("subCategory", subCategory);
                filterMap.put("filterName", filterName);
                filterMap.put("filterType", filterType);
                filterMap.put("isDefault", isDefault);
                filterMap.put("filterApi", filterApi);
                filterMap.put("filterAPIReference", filterAPIReference);

                filterData.add(new Object[]{filterMap});
            }
        }

        /**
         * Safely get string value from JsonObject
         */
        private static String getJsonString(JsonObject obj, String key) {
            if (obj.has(key) && !obj.get(key).isJsonNull()) {
                return obj.get(key).getAsString();
            }
            return null;
        }

        // ========== Utility Methods for Tests ==========

        /**
         * Get count of default filters
         */
        public static int getDefaultFilterCount() {
            Object[][] filters = getDefaultFilters();
            return filters.length;
        }

        public static String getTileFromScreener(String screener){
            switch (screener){
                case "company":return "All";
                case "asset_manager": return "Asset Manager";
                default : return "All";
            }
        }


}
