package VCI.Data;

import java.sql.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;
import org.bson.Document;
import org.testng.annotations.Test;

/**
 * Identifies specific companies causing stage mismatch between SQL and MongoDB
 * Generates detailed reports with company IDs and names
 */
public class CompanyStageMismatchFinder {

    private static final String OUTPUT_FILE = "Company_Stage_Mismatch_Report.csv";

    @Test
    public void findMismatchedCompanies() {
        Connection mysqlConnection = null;
        MongoDBHandler mongoDBHandler = null;

        try {
            mysqlConnection = MySQLDBHandler.getConnection();
            mongoDBHandler = new MongoDBHandler();
            MongoDatabase database = mongoDBHandler.getDatabase();
            MongoCollection<Document> mongoCollection = database.getCollection("companySourceSearching");

            System.out.println("=".repeat(80));
            System.out.println("COMPANY STAGE MISMATCH ANALYSIS");
            System.out.println("=".repeat(80));

            List<String[]> csvData = new ArrayList<>();
            csvData.add(new String[]{"Category", "CompanyId", "CompanyName", "Source", "StageInvestment", 
                                     "RoundOfInvestment", "AnnouncementDate", "TransactionStatus", "CompanyTypeId", 
                                     "CompanyStatusId", "Notes"});

            // Analyze each category
            analyzeAngelSeedMismatch(mysqlConnection, mongoCollection, csvData);
            analyzePEFundedMismatch(mysqlConnection, mongoCollection, csvData);
            analyzeSeriesAMismatch(mysqlConnection, mongoCollection, csvData);
            analyzeSeriesDMismatch(mysqlConnection, mongoCollection, csvData);
            
            // Export results
            exportToCSV(csvData);
            
            System.out.println("\n" + "=".repeat(80));
            System.out.println("Analysis complete. Report saved to: " + OUTPUT_FILE);
            System.out.println("=".repeat(80));

            mongoDBHandler.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (mysqlConnection != null) mysqlConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void analyzeAngelSeedMismatch(Connection connection, MongoCollection<Document> mongoCollection, 
                                         List<String[]> csvData) throws SQLException {
        System.out.println("\n1. ANGEL/SEED ANALYSIS (SQL: 10506, UI: 10480, Diff: +24)");
        System.out.println("-".repeat(80));

        String sqlQuery = buildAngelSeedQuery();
        Set<Integer> sqlCompanyIds = executeQueryAndGetCompanyIds(connection, sqlQuery);
        Set<Integer> mongoCompanyIds = getMongoAngelSeedCompanyIds(mongoCollection);

        System.out.println("SQL Company Count: " + sqlCompanyIds.size());
        System.out.println("MongoDB Company Count: " + mongoCompanyIds.size());

        // Find companies in SQL but not in MongoDB
        Set<Integer> missingInMongo = new HashSet<>(sqlCompanyIds);
        missingInMongo.removeAll(mongoCompanyIds);
        
        System.out.println("Companies in SQL but NOT in MongoDB: " + missingInMongo.size());
        exportCompanyDetails(connection, missingInMongo, "Angel/Seed", "Missing in MongoDB", csvData);

        // Find companies in MongoDB but not in SQL
        Set<Integer> missingInSQL = new HashSet<>(mongoCompanyIds);
        missingInSQL.removeAll(sqlCompanyIds);
        
        System.out.println("Companies in MongoDB but NOT in SQL: " + missingInSQL.size());
        printCompanyList(connection, missingInSQL, "Angel/Seed - Extra in MongoDB");
    }

    private void analyzePEFundedMismatch(Connection connection, MongoCollection<Document> mongoCollection, 
                                        List<String[]> csvData) throws SQLException {
        System.out.println("\n2. PE FUNDED ANALYSIS (SQL: 2074, UI: 2015, Diff: +59)");
        System.out.println("-".repeat(80));

        String sqlQuery = buildPEFundedQuery();
        Set<Integer> sqlCompanyIds = executeQueryAndGetCompanyIds(connection, sqlQuery);
        Set<Integer> mongoCompanyIds = getMongoPEFundedCompanyIds(mongoCollection);

        System.out.println("SQL Company Count: " + sqlCompanyIds.size());
        System.out.println("MongoDB Company Count: " + mongoCompanyIds.size());

        Set<Integer> missingInMongo = new HashSet<>(sqlCompanyIds);
        missingInMongo.removeAll(mongoCompanyIds);
        
        System.out.println("Companies in SQL but NOT in MongoDB: " + missingInMongo.size());
        exportCompanyDetails(connection, missingInMongo, "PE Funded", "Missing in MongoDB", csvData);

        Set<Integer> missingInSQL = new HashSet<>(mongoCompanyIds);
        missingInSQL.removeAll(sqlCompanyIds);
        
        System.out.println("Companies in MongoDB but NOT in SQL: " + missingInSQL.size());
        printCompanyList(connection, missingInSQL, "PE Funded - Extra in MongoDB");
    }

    private void analyzeSeriesAMismatch(Connection connection, MongoCollection<Document> mongoCollection, 
                                       List<String[]> csvData) throws SQLException {
        System.out.println("\n3. SERIES A ANALYSIS (SQL: 1897, UI: 1892, Diff: +5)");
        System.out.println("-".repeat(80));

        String sqlQuery = buildSeriesAQuery();
        Set<Integer> sqlCompanyIds = executeQueryAndGetCompanyIds(connection, sqlQuery);
        Set<Integer> mongoCompanyIds = getMongoSeriesACompanyIds(mongoCollection);

        System.out.println("SQL Company Count: " + sqlCompanyIds.size());
        System.out.println("MongoDB Company Count: " + mongoCompanyIds.size());

        Set<Integer> missingInMongo = new HashSet<>(sqlCompanyIds);
        missingInMongo.removeAll(mongoCompanyIds);
        
        System.out.println("Companies in SQL but NOT in MongoDB: " + missingInMongo.size());
        exportCompanyDetails(connection, missingInMongo, "Series A", "Missing in MongoDB", csvData);
    }

    private void analyzeSeriesDMismatch(Connection connection, MongoCollection<Document> mongoCollection, 
                                       List<String[]> csvData) throws SQLException {
        System.out.println("\n4. SERIES D ANALYSIS (SQL: 167, UI: 168, Diff: -1)");
        System.out.println("-".repeat(80));

        String sqlQuery = buildSeriesDQuery();
        Set<Integer> sqlCompanyIds = executeQueryAndGetCompanyIds(connection, sqlQuery);
        Set<Integer> mongoCompanyIds = getMongoSeriesDCompanyIds(mongoCollection);

        System.out.println("SQL Company Count: " + sqlCompanyIds.size());
        System.out.println("MongoDB Company Count: " + mongoCompanyIds.size());

        Set<Integer> missingInSQL = new HashSet<>(mongoCompanyIds);
        missingInSQL.removeAll(sqlCompanyIds);
        
        System.out.println("Companies in MongoDB but NOT in SQL: " + missingInSQL.size());
        printCompanyList(connection, missingInSQL, "Series D - Extra in MongoDB");
    }

    private String buildAngelSeedQuery() {
        return "SELECT DISTINCT t.targetcompanyid " +
               "FROM transactions t " +
               "JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
               "JOIN company c ON t.targetcompanyid = c.companyid " +
               "WHERE t.transactiontype = 34 " +
               "AND pp.transactionstatus IN (44, 101) " +
               "AND c.companytypeid NOT IN (8, 9) " +
               "AND c.companystatusid IN (13, 15) " +
               "AND t.deletion_status = 0 " +
               "AND pp.deletion_status = 0 " +
               "AND c.deletion_status = 0 " +
               "AND pp.stage_investment = 107 " +
               "AND pp.announcementdate = (" +
               "    SELECT MAX(pp2.announcementdate) " +
               "    FROM transactions t2 " +
               "    JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid " +
               "    WHERE t2.targetcompanyid = t.targetcompanyid " +
               "    AND t2.transactiontype = 34 " +
               "    AND pp2.transactionstatus IN (44, 101) " +
               "    AND pp2.stage_investment IN (182, 183, 107) " +
               "    AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477) " +
               "    AND t2.deletion_status = 0 " +
               "    AND pp2.deletion_status = 0" +
               ")";
    }

    private String buildPEFundedQuery() {
        return buildAngelSeedQuery().replace("pp.stage_investment = 107", "pp.stage_investment = 183");
    }

    private String buildSeriesAQuery() {
        return buildAngelSeedQuery().replace("pp.stage_investment = 107", 
                                            "pp.stage_investment = 182 AND pp.roundofinvestment = 144");
    }

    private String buildSeriesDQuery() {
        return buildAngelSeedQuery().replace("pp.stage_investment = 107", 
                                            "pp.stage_investment = 182 AND pp.roundofinvestment = 147");
    }

    private Set<Integer> executeQueryAndGetCompanyIds(Connection connection, String query) throws SQLException {
        Set<Integer> companyIds = new HashSet<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                companyIds.add(rs.getInt(1));
            }
        }
        return companyIds;
    }

    private Set<Integer> getMongoAngelSeedCompanyIds(MongoCollection<Document> collection) {
        // Implement MongoDB query to get Angel/Seed companies
        // This is a placeholder - adjust based on actual MongoDB schema
        Set<Integer> companyIds = new HashSet<>();
        Document query = new Document("profile.investmentStage", "Angel/Seed");
        for (Document doc : collection.find(query)) {
            if (doc.containsKey("companyId")) {
                companyIds.add(doc.getInteger("companyId"));
            }
        }
        return companyIds;
    }

    private Set<Integer> getMongoPEFundedCompanyIds(MongoCollection<Document> collection) {
        Set<Integer> companyIds = new HashSet<>();
        Document query = new Document("profile.investmentStage", "PE Funded");
        for (Document doc : collection.find(query)) {
            if (doc.containsKey("companyId")) {
                companyIds.add(doc.getInteger("companyId"));
            }
        }
        return companyIds;
    }

    private Set<Integer> getMongoSeriesACompanyIds(MongoCollection<Document> collection) {
        Set<Integer> companyIds = new HashSet<>();
        Document query = new Document("roundOfInvestment", "Series A");
        for (Document doc : collection.find(query)) {
            if (doc.containsKey("companyId")) {
                companyIds.add(doc.getInteger("companyId"));
            }
        }
        return companyIds;
    }

    private Set<Integer> getMongoSeriesDCompanyIds(MongoCollection<Document> collection) {
        Set<Integer> companyIds = new HashSet<>();
        Document query = new Document("roundOfInvestment", "Series D");
        for (Document doc : collection.find(query)) {
            if (doc.containsKey("companyId")) {
                companyIds.add(doc.getInteger("companyId"));
            }
        }
        return companyIds;
    }

    private void exportCompanyDetails(Connection connection, Set<Integer> companyIds, 
                                     String category, String notes, List<String[]> csvData) throws SQLException {
        if (companyIds.isEmpty()) return;

        String query = "SELECT c.companyid, c.companyname, pp.stage_investment, pp.roundofinvestment, " +
                      "pp.announcementdate, pp.transactionstatus, c.companytypeid, c.companystatusid " +
                      "FROM company c " +
                      "LEFT JOIN transactions t ON c.companyid = t.targetcompanyid " +
                      "LEFT JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid " +
                      "WHERE c.companyid IN (" + String.join(",", companyIds.stream()
                                                              .map(String::valueOf)
                                                              .toArray(String[]::new)) + ") " +
                      "AND t.transactiontype = 34 " +
                      "ORDER BY pp.announcementdate DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                csvData.add(new String[]{
                    category,
                    String.valueOf(rs.getInt("companyid")),
                    rs.getString("companyname"),
                    "SQL",
                    String.valueOf(rs.getInt("stage_investment")),
                    String.valueOf(rs.getInt("roundofinvestment")),
                    rs.getString("announcementdate"),
                    String.valueOf(rs.getInt("transactionstatus")),
                    String.valueOf(rs.getInt("companytypeid")),
                    String.valueOf(rs.getInt("companystatusid")),
                    notes
                });
            }
        }
    }

    private void printCompanyList(Connection connection, Set<Integer> companyIds, String label) throws SQLException {
        if (companyIds.isEmpty()) return;

        System.out.println("\n" + label + ":");
        System.out.println("CompanyId | CompanyName");
        System.out.println("-".repeat(60));

        String query = "SELECT companyid, companyname FROM company WHERE companyid IN (" + 
                      String.join(",", companyIds.stream().map(String::valueOf).toArray(String[]::new)) + ")";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            int count = 0;
            while (rs.next() && count < 50) {
                System.out.printf("%d | %s%n", rs.getInt("companyid"), rs.getString("companyname"));
                count++;
            }
            if (companyIds.size() > 50) {
                System.out.println("... and " + (companyIds.size() - 50) + " more");
            }
        }
    }

    private void exportToCSV(List<String[]> data) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            for (String[] row : data) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
            System.out.println("\nCSV report generated: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
