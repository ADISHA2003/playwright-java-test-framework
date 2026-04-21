package tests;

import PlaywrightPageObject.ScreenerFilterPage.FilterFormat;
import PlaywrightPageObject.ScreenerFilterPage.FilterManagementService;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.util.*;

import static org.apache.velocity.tools.Scope.add;

public class TestCompanyScreener extends BaseUI_Test{

    static List<String[]> reportRows = new ArrayList<>();

    @Test(description = "As a user i can verify the company type filter",groups = {"regression"},dataProvider = "companyTypeProvider")
    public void test_company_type(String filterOption,String filterType,String bodyFormat,String sqlQuery ){

       Allure.step("Given I select an option for filter");
       String body = fs.getFilterOptionUpdatedBody(filterOption,filterType,bodyFormat);
       sqlQuery = sqlQuery.replace("filter_value_replace", fs.getCompanyTypeIdFromName(filterOption));


       Allure.step("Wen I get count of sql table for : "+filterOption);
        System.out.println(sqlQuery);
       connection = mySQLDBHandler.getConnection();
       mysqlResultSet = mySQLDBHandler.getResultSetMetaData(connection,sqlQuery);
       long sqlCount = mySQLDBHandler.getCountOfResultUnderColumn(mysqlResultSet,"count");

       Allure.step("And I get the count of view results section for : "+filterOption);
       long mongoCount = vrs.getScreeningResults(accessToken,body);

       Allure.step("Then I should see the company type filter options count with sql on applying options");
        Assert.assertEquals(mongoCount,sqlCount,"Mismatch in counts for filter option: "+filterOption+" SQL Count: "+sqlCount+" Mongo Count: "+mongoCount);
    }

    @DataProvider(name = "companyTypeProvider")
    public Object[][] getMultiSelectOptions() {
        //get payload from json file
        // 1. Define the Request Payload
        Map<String, Object> body = Map.of(
                "dataField", "companyType",
                "request", new ArrayList<>()
        );


        // 2. Execute API call and extract the nested "name" fields
        // We use JsonPath "data.values.name" which returns a nested list: [[Public, Private], [Trust, NGO...]]
        List<List<String>> nestedNames = RestAssured
                .given()
                .baseUri("https://prodapigw-research-service.vccedge.com")
                .header("Authorization", accessToken) // Use the dynamic token from previous step
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/services/research/sourcing/multi_select_values")
                .then()
                .statusCode(200)
                .extract()
                .path("data.values.name");

        nestedNames.forEach(list -> System.out.println("Fetched names: " + list));

        String reqbody = "{\"request\":[{\"id\":\"companyType\",\"name\":\"Company Type\",\"fieldName\":\"companyType\",\"condition\":\"&&\",\"status\":true,\"includeBlanks\":false,\"componentName\":\"Multiselect\",\"values\":[\"Trust\"]}],\"page\":0,\"size\":10}";
        String sqlQuery = "select count(distinct companyid) as count from company where companytypeid=filter_value_replace and deletion_status=0;";

        // 3. Flatten the nested list into a 1D list and then into Object[][]
        List<String> flatList = new ArrayList<>();
        for (List<String> subList : nestedNames) {
            if (subList != null) flatList.addAll(subList);
        }

        Object[][] dataProviderArray = new Object[flatList.size()][1];
        flatList.stream().forEach(name -> System.out.println("Flat name: " + name));

         dataProviderArray = new Object[flatList.size()][4];
        for (int i = 0; i < flatList.size(); i++) {
            String item = flatList.get(i);
            if(flatList.get(i).contains("(NGO)")) item = item.replace("(NGO)","");
            dataProviderArray[i][0] = item;
            dataProviderArray[i][1] = "multi_select_values";
            dataProviderArray[i][2] = reqbody;
            dataProviderArray[i][3] = sqlQuery;
        }

        return dataProviderArray;
    }

    @Test
    public void test_transaction_count(){

    }

    @Test
    public void test_transaction_date(){

    }

    @Test
    public void test_transaction_value(){

    }

    @Test(groups = {"smoke", "regression"})
    public void test_company_stage() {

        String sqlTemplate =
                "SELECT investment_category, company_count " +
                        "FROM ( " +
                        "   SELECT investment_category, COUNT(DISTINCT company_id) AS company_count " +
                        "   FROM ( " +
                        "       SELECT t.targetcompanyid AS company_id, " +
                        "           CASE " +
                        "               WHEN pp.stage_investment = 182 THEN " +
                        "                   CASE " +
                        "                       WHEN pp.roundofinvestment IS NOT NULL AND pp.roundofinvestment != 0 THEN " +
                        "                           CASE pp.roundofinvestment " +
                        "                               WHEN 144 THEN 'Series A' " +
                        "                               WHEN 145 THEN 'Series B' " +
                        "                               WHEN 146 THEN 'Series C' " +
                        "                               WHEN 147 THEN 'Series D' " +
                        "                               WHEN 193 THEN 'Series E+' " +
                        "                           END " +
                        "                       ELSE 'VC Funded (Undisclosed)' " +
                        "                   END " +
                        "               WHEN pp.stage_investment = 107 THEN 'Angel/Seed' " +
                        "               WHEN pp.stage_investment = 183 THEN 'PE Funded' " +
                        "           END AS investment_category " +
                        "       FROM transactions t " +
                        "       JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                        "       JOIN company c ON t.targetcompanyid = c.companyid " +
                        "       WHERE t.transactiontype = 34 " +
                        "           AND pp.transactionstatus IN (44, 101) " +
                        "           AND c.companytypeid NOT IN (8, 9) " +
                        "           AND c.companystatusid IN (13, 15) " +
                        "           AND t.deletion_status = 0 " +
                        "           AND pp.deletion_status = 0 " +
                        "           AND c.deletion_status = 0 " +
                        "           AND pp.announcementdate = ( " +
                        "               SELECT MAX(pp2.announcementdate) " +
                        "               FROM transactions t2 " +
                        "               JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                        "               WHERE t2.targetcompanyid = t.targetcompanyid " +
                        "                   AND t2.transactiontype = 34 " +
                        "                   AND pp2.transactionstatus IN (44, 101) " +
                        "                   AND pp2.stage_investment IN (182, 183, 107) " +
                        "                   AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                        "                   AND t2.deletion_status = 0 " +
                        "                   AND pp2.deletion_status = 0 " +
                        "           ) " +
                        "       GROUP BY t.targetcompanyid " +
                        "   ) AS subquery " +
                        "   WHERE investment_category IS NOT NULL " +
                        "   GROUP BY investment_category " +

                        "   UNION ALL " +

                        "   SELECT 'Others' AS investment_category, " +
                        "          t.total_count - IFNULL(c.categorized_count, 0) AS company_count " +
                        "   FROM ( " +
                        "       SELECT COUNT(DISTINCT t.targetcompanyid) AS total_count " +
                        "       FROM transactions t " +
                        "       JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                        "       JOIN company c ON t.targetcompanyid = c.companyid " +
                        "       WHERE t.transactiontype = 34 " +
                        "           AND pp.transactionstatus IN (44, 101) " +
                        "           AND c.companytypeid NOT IN (8, 9) " +
                        "           AND c.companystatusid IN (13, 15) " +
                        "           AND t.deletion_status = 0 " +
                        "           AND pp.deletion_status = 0 " +
                        "           AND c.deletion_status = 0 " +
                        "   ) t " +
                        "   CROSS JOIN ( " +
                        "       SELECT COUNT(DISTINCT t.targetcompanyid) AS categorized_count " +
                        "       FROM transactions t " +
                        "       JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                        "       JOIN company c ON t.targetcompanyid = c.companyid " +
                        "       WHERE t.transactiontype = 34 " +
                        "           AND pp.transactionstatus IN (44, 101) " +
                        "           AND c.companytypeid NOT IN (8, 9) " +
                        "           AND c.companystatusid IN (13, 15) " +
                        "           AND t.deletion_status = 0 " +
                        "           AND pp.deletion_status = 0 " +
                        "           AND c.deletion_status = 0 " +
                        "           AND pp.announcementdate = ( " +
                        "               SELECT MAX(pp2.announcementdate) " +
                        "               FROM transactions t2 " +
                        "               JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                        "               WHERE t2.targetcompanyid = t.targetcompanyid " +
                        "                   AND t2.transactiontype = 34 " +
                        "                   AND pp2.transactionstatus IN (44, 101) " +
                        "                   AND pp2.stage_investment IN (182, 183, 107) " +
                        "                   AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                        "                   AND t2.deletion_status = 0 " +
                        "                   AND pp2.deletion_status = 0 " +
                        "           ) " +
                        "   ) c " +

                        "   UNION ALL " +

                        "   SELECT 'Total' AS investment_category, " +
                        "          COUNT(DISTINCT t.targetcompanyid) AS company_count " +
                        "   FROM transactions t " +
                        "   JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                        "   JOIN company c ON t.targetcompanyid = c.companyid " +
                        "   WHERE t.transactiontype = 34 " +
                        "       AND pp.transactionstatus IN (44, 101) " +
                        "       AND c.companytypeid NOT IN (8, 9) " +
                        "       AND c.companystatusid IN (13, 15) " +
                        "       AND t.deletion_status = 0 " +
                        "       AND pp.deletion_status = 0 " +
                        "       AND c.deletion_status = 0 " +
                        ") AS final_result " +
                        "ORDER BY CASE investment_category " +
                        "   WHEN 'Total' THEN 1 " +
                        "   WHEN 'Others' THEN 2 " +
                        "   ELSE 3 END, company_count DESC";


        // -------------------------------
        // Categories to validate
        // -------------------------------
        List<String> filterOptions = Arrays.asList(
                "Angel/Seed",
                "VC Funded (Undisclosed)",
                "PE Funded",
                "Series A",
                "Series B",
                "Series C",
                "Series D",
                "Series E+"
        );

        connection = mySQLDBHandler.getConnection();

        try {
            // -------------------------------
            // 1️⃣ Execute SQL ONCE
            // -------------------------------
            String sqlQuery = sqlTemplate;
            mysqlResultSet = mySQLDBHandler.getResultSetMetaData(connection, sqlQuery);

            Map<String, Long> dbCounts = new HashMap<>();

            while (mysqlResultSet != null && mysqlResultSet.next()) {
                String category = mysqlResultSet.getString("investment_category");
                long cnt = mysqlResultSet.getLong("company_count");
                System.out.println("SQL -> Category: " + category + " | Count: " + cnt);
                dbCounts.put(category, cnt);
            }

            // -------------------------------
            // 2️⃣ Loop API for each category
            // -------------------------------
            for (String filterOption : filterOptions) {

                Allure.step("When I apply stage filter: " + filterOption);

                String bodyFormat =
                        "{\"request\":[{\"id\":\"roundOfInvestment\",\"name\":\"Company Stage\"," +
                                "\"fieldName\":\"roundOfInvestment\",\"condition\":\"&&\",\"status\":true," +
                                "\"includeBlanks\":false,\"componentName\":\"Multiselect\"," +
                                "\"values\":[\"" + filterOption + "\"]}]," +
                                "\"page\":0,\"size\":10}";

                String body = fs.getFilterOptionUpdatedBody(
                        filterOption,
                        "multi_select_values",
                        bodyFormat
                );

                Allure.step("And I get the count of view results section for : " + filterOption);
                long mongoCount = vrs.getScreeningResults(accessToken, body);

                if (mongoCount == 0) {
                    System.out.println("⚠ Mongo returned 0 for " + filterOption +
                            " | Request Body: " + body);
                }

                long sqlCount = dbCounts.getOrDefault(filterOption, 0L);
                long diff = sqlCount - mongoCount;

                System.out.println(
                        "Stage: " + filterOption +
                                " | UI: " + mongoCount +
                                " | SQL: " + sqlCount +
                                " | Diff: " + diff
                );

                reportRows.add(new String[]{
                        filterOption,
                        String.valueOf(mongoCount),
                        String.valueOf(sqlCount),
                        String.valueOf(diff)
                });

                // Optional assertion (comment if you only want report)
//                Assert.assertEquals(
//                        mongoCount,
//                        sqlCount,
//                        "Mismatch for stage: " + filterOption +
//                                " | SQL: " + sqlCount +
//                                " | UI: " + mongoCount
//                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (mysqlResultSet != null) mysqlResultSet.close();
            } catch (Exception ignored) {}

            try {
                if (connection != null) connection.close();
            } catch (Exception ignored) {}
        }
    }

    @Test(description = "As a user I can identify specific companies causing stage mismatch", 
          groups = {"regression"}, 
          dataProvider = "companyStageProvider")
    public void test_CompanyStageListItem(String stageCategory, String sqlQuery, String apiRequestBody) {
        
        Allure.step("Testing stage: " + stageCategory);
        
        connection = mySQLDBHandler.getConnection();
        
        try {
            // -------------------------------
            // 1️⃣ Get Company IDs from SQL
            // -------------------------------
            Allure.step("When I execute SQL query to get company IDs for: " + stageCategory);
            System.out.println("SQL Query: " + sqlQuery);
            
            mysqlResultSet = mySQLDBHandler.getResultSetMetaData(connection, sqlQuery);
            Set<Integer> sqlCompanyIds = new HashSet<>();
            
            while (mysqlResultSet != null && mysqlResultSet.next()) {
                sqlCompanyIds.add(mysqlResultSet.getInt("companyid"));
            }
            
            System.out.println("SQL Company IDs Count: " + sqlCompanyIds.size());
            
            // -------------------------------
            // 2️⃣ Get Company IDs from API
            // -------------------------------
            Allure.step("And I get company IDs from API for: " + stageCategory);
            
            // Build complete request body
            String body = fs.getFilterOptionUpdatedBody(stageCategory, "multi_select_values", apiRequestBody);

            // Get all company IDs from API (need to implement method to get IDs, not just count)
            List<Integer> apiCompanyIds = vrs.getScreeningResultIds(accessToken, body);
            
            System.out.println("API Company IDs Count: " + apiCompanyIds.size());
            
            // -------------------------------
            // 3️⃣ Compare Lists and Find Differences
            // -------------------------------
            Allure.step("Then I compare SQL and API company lists");
            
            // Companies in SQL but NOT in API
            Set<Integer> missingInAPI = new HashSet<>(sqlCompanyIds);
            missingInAPI.removeAll(apiCompanyIds);
            
            // Companies in API but NOT in SQL
            Set<Integer> extraInAPI = new HashSet<>(apiCompanyIds);
            extraInAPI.removeAll(sqlCompanyIds);
            
            // Print summary
            System.out.println("\n" + "=".repeat(80));
            System.out.println("STAGE: " + stageCategory);
            System.out.println("=".repeat(80));
            System.out.println("SQL Count: " + sqlCompanyIds.size());
            System.out.println("API Count: " + apiCompanyIds.size());
            System.out.println("Difference: " + (sqlCompanyIds.size() - apiCompanyIds.size()));
            
            if (!missingInAPI.isEmpty()) {
                System.out.println("\n❌ Companies in SQL but NOT in API (" + missingInAPI.size() + "):");
                missingInAPI.stream()
                    .limit(20)
                    .forEach(id -> {
                        String companyName = getCompanyName(connection, id);
                        System.out.println("  - CompanyId: " + id + " | Name: " + companyName);
                    });
                if (missingInAPI.size() > 20) {
                    System.out.println("  ... and " + (missingInAPI.size() - 20) + " more");
                }
            }
            
            if (!extraInAPI.isEmpty()) {
                System.out.println("\n⚠ Companies in API but NOT in SQL (" + extraInAPI.size() + "):");
                extraInAPI.stream()
                    .limit(20)
                    .forEach(id -> {
                        String companyName = getCompanyName(connection, id);
                        System.out.println("  - CompanyId: " + id + " | Name: " + companyName);
                    });
                if (extraInAPI.size() > 20) {
                    System.out.println("  ... and " + (extraInAPI.size() - 20) + " more");
                }
            }
            
            // Attach detailed report to Allure
            attachMismatchReport(stageCategory, sqlCompanyIds, apiCompanyIds, missingInAPI, extraInAPI, connection);
            
            // Assert if needed (can be commented for report-only mode)
            // Assert.assertEquals(apiCompanyIds.size(), sqlCompanyIds.size(), 
            //     "Company count mismatch for stage: " + stageCategory);
            
        } catch (Exception e) {
            System.err.println("Error processing stage: " + stageCategory);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (mysqlResultSet != null) mysqlResultSet.close();
            } catch (Exception ignored) {}
        }
    }
    
    @DataProvider(name = "companyStageProvider")
    public Object[][] getCompanyStageData() {
        return new Object[][] {
            // Format: {stageCategory, sqlQuery, apiRequestBody}
            
            // Angel/Seed
            {
                "Angel/Seed",
                "SELECT DISTINCT c.companyid " +
                "FROM transactions t " +
                "JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                "JOIN company c ON t.targetcompanyid = c.companyid " +
                "WHERE t.transactiontype = 34 " +
                "  AND pp.transactionstatus IN (44, 101) " +
                "  AND c.companytypeid NOT IN (8, 9) " +
                "  AND c.companystatusid IN (13, 15) " +
                "  AND t.deletion_status = 0 " +
                "  AND pp.deletion_status = 0 " +
                "  AND c.deletion_status = 0 " +
                "  AND pp.stage_investment = 107 " +
                "  AND pp.announcementdate = (" +
                "    SELECT MAX(pp2.announcementdate) " +
                "    FROM transactions t2 " +
                "    JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                "    WHERE t2.targetcompanyid = t.targetcompanyid " +
                "      AND t2.transactiontype = 34 " +
                "      AND pp2.transactionstatus IN (44, 101) " +
                "      AND pp2.stage_investment IN (182, 183, 107) " +
                "      AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                "      AND t2.deletion_status = 0 " +
                "      AND pp2.deletion_status = 0" +
                "  )",
                "{\"request\":[{\"id\":\"roundOfInvestment\",\"name\":\"Company Stage\",\"fieldName\":\"roundOfInvestment\",\"condition\":\"&&\",\"status\":true,\"includeBlanks\":false,\"componentName\":\"Multiselect\",\"values\":[\"Angel/Seed\"]}],\"page\":0,\"size\":1000}"
            },
            
            // PE Funded
            {
                "PE Funded",
                "SELECT DISTINCT c.companyid " +
                "FROM transactions t " +
                "JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                "JOIN company c ON t.targetcompanyid = c.companyid " +
                "WHERE t.transactiontype = 34 " +
                "  AND pp.transactionstatus IN (44, 101) " +
                "  AND c.companytypeid NOT IN (8, 9) " +
                "  AND c.companystatusid IN (13, 15) " +
                "  AND t.deletion_status = 0 " +
                "  AND pp.deletion_status = 0 " +
                "  AND c.deletion_status = 0 " +
                "  AND pp.stage_investment = 183 " +
                "  AND pp.announcementdate = (" +
                "    SELECT MAX(pp2.announcementdate) " +
                "    FROM transactions t2 " +
                "    JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                "    WHERE t2.targetcompanyid = t.targetcompanyid " +
                "      AND t2.transactiontype = 34 " +
                "      AND pp2.transactionstatus IN (44, 101) " +
                "      AND pp2.stage_investment IN (182, 183, 107) " +
                "      AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                "      AND t2.deletion_status = 0 " +
                "      AND pp2.deletion_status = 0" +
                "  )",
                "{\"request\":[{\"id\":\"roundOfInvestment\",\"name\":\"Company Stage\",\"fieldName\":\"roundOfInvestment\",\"condition\":\"&&\",\"status\":true,\"includeBlanks\":false,\"componentName\":\"Multiselect\",\"values\":[\"PE Funded\"]}],\"page\":0,\"size\":10}"
            },
            
            // Series A
            {
                "Series A",
                "SELECT DISTINCT c.companyid " +
                "FROM transactions t " +
                "JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                "JOIN company c ON t.targetcompanyid = c.companyid " +
                "WHERE t.transactiontype = 34 " +
                "  AND pp.transactionstatus IN (44, 101) " +
                "  AND c.companytypeid NOT IN (8, 9) " +
                "  AND c.companystatusid IN (13, 15) " +
                "  AND t.deletion_status = 0 " +
                "  AND pp.deletion_status = 0 " +
                "  AND c.deletion_status = 0 " +
                "  AND pp.stage_investment = 182 " +
                "  AND pp.roundofinvestment = 144 " +
                "  AND pp.announcementdate = (" +
                "    SELECT MAX(pp2.announcementdate) " +
                "    FROM transactions t2 " +
                "    JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                "    WHERE t2.targetcompanyid = t.targetcompanyid " +
                "      AND t2.transactiontype = 34 " +
                "      AND pp2.transactionstatus IN (44, 101) " +
                "      AND pp2.stage_investment IN (182, 183, 107) " +
                "      AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                "      AND t2.deletion_status = 0 " +
                "      AND pp2.deletion_status = 0" +
                "  )",
                "{\"request\":[{\"id\":\"roundOfInvestment\",\"name\":\"Company Stage\",\"fieldName\":\"roundOfInvestment\",\"condition\":\"&&\",\"status\":true,\"includeBlanks\":false,\"componentName\":\"Multiselect\",\"values\":[\"Series A\"]}],\"page\":0,\"size\":10}"
            },
            
            // Series D
            {
                "Series D",
                "SELECT DISTINCT c.companyid " +
                "FROM transactions t " +
                "JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                "JOIN company c ON t.targetcompanyid = c.companyid " +
                "WHERE t.transactiontype = 34 " +
                "  AND pp.transactionstatus IN (44, 101) " +
                "  AND c.companytypeid NOT IN (8, 9) " +
                "  AND c.companystatusid IN (13, 15) " +
                "  AND t.deletion_status = 0 " +
                "  AND pp.deletion_status = 0 " +
                "  AND c.deletion_status = 0 " +
                "  AND pp.stage_investment = 182 " +
                "  AND pp.roundofinvestment = 147 " +
                "  AND pp.announcementdate = (" +
                "    SELECT MAX(pp2.announcementdate) " +
                "    FROM transactions t2 " +
                "    JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
                "    WHERE t2.targetcompanyid = t.targetcompanyid " +
                "      AND t2.transactiontype = 34 " +
                "      AND pp2.transactionstatus IN (44, 101) " +
                "      AND pp2.stage_investment IN (182, 183, 107) " +
                "      AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
                "      AND t2.deletion_status = 0 " +
                "      AND pp2.deletion_status = 0" +
                "  )",
                "{\"request\":[{\"id\":\"roundOfInvestment\",\"name\":\"Company Stage\",\"fieldName\":\"roundOfInvestment\",\"condition\":\"&&\",\"status\":true,\"includeBlanks\":false,\"componentName\":\"Multiselect\",\"values\":[\"Series D\"]}],\"page\":0,\"size\":10}"
            }
        };
    }
    
    /**
     * Helper method to get company name from database
     */
    private String getCompanyName(java.sql.Connection connection, int companyId) {
        try {
            String query = "SELECT companyname FROM company WHERE companyid = " + companyId;
            java.sql.ResultSet rs = mySQLDBHandler.getResultSetMetaData(connection, query);
            if (rs != null && rs.next()) {
                return rs.getString("companyname");
            }
        } catch (Exception e) {
            System.err.println("Error getting company name for ID: " + companyId);
        }
        return "Unknown";
    }
    
    /**
     * Attach detailed mismatch report to Allure
     */
    private void attachMismatchReport(String stageCategory, Set<Integer> sqlIds, List<Integer> apiIds,
                                     Set<Integer> missingInAPI, Set<Integer> extraInAPI,
                                     java.sql.Connection connection) {
        StringBuilder report = new StringBuilder();
        report.append("COMPANY STAGE MISMATCH REPORT\n");
        report.append("=".repeat(80)).append("\n");
        report.append("Stage: ").append(stageCategory).append("\n");
        report.append("SQL Count: ").append(sqlIds.size()).append("\n");
        report.append("API Count: ").append(apiIds.size()).append("\n");
        report.append("Difference: ").append(sqlIds.size() - apiIds.size()).append("\n\n");
        
        if (!missingInAPI.isEmpty()) {
            report.append("Companies in SQL but NOT in API (").append(missingInAPI.size()).append("):\n");
            report.append("-".repeat(80)).append("\n");
            missingInAPI.forEach(id -> {
                String name = getCompanyName(connection, id);
                report.append("CompanyId: ").append(id).append(" | Name: ").append(name).append("\n");
            });
            report.append("\n");
        }
        
        if (!extraInAPI.isEmpty()) {
            report.append("Companies in API but NOT in SQL (").append(extraInAPI.size()).append("):\n");
            report.append("-".repeat(80)).append("\n");
            extraInAPI.forEach(id -> {
                String name = getCompanyName(connection, id);
                report.append("CompanyId: ").append(id).append(" | Name: ").append(name).append("\n");
            });
        }
        
        Allure.addAttachment("Mismatch Report - " + stageCategory, "text/plain", report.toString());
    }

    // -------------------------------
    // Final Report Printer
    // -------------------------------
    @AfterClass
    public void printFinalReport() {

        System.out.println("\n================ FINAL REPORT ================");
        System.out.printf("| %-18s | %-8s | %-8s | %-8s |\n",
                "Stage", "UI", "SQL", "Diff");
        System.out.println("----------------------------------------------------------");

        for (String[] row : reportRows) {
            System.out.printf("| %-18s | %-8s | %-8s | %-8s |\n",
                    row[0], row[1], row[2], row[3]);
        }
    }

}
