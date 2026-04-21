package DatabaseUtilities_Actions;

import Constants.SQL_Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class CompanyStageExporter {

    public static void main(String[] args) {
        String env = "PROD"; // Change to "DEV" if needed
        String outputFile = "companies_by_stage.csv";

        String sql = """
            SELECT 
                investment_category,
                GROUP_CONCAT(DISTINCT c.companyname ORDER BY c.companyname SEPARATOR ', ') AS companies
            FROM (
                SELECT 
                    t.targetcompanyid AS company_id,
                    c.companyname,
                    CASE 
                        WHEN pp.stage_investment = 182 THEN 
                            CASE 
                                WHEN pp.roundofinvestment IS NOT NULL 
                                     AND pp.roundofinvestment != 0 
                                THEN 
                                    CASE pp.roundofinvestment
                                        WHEN 144 THEN 'Series A'
                                        WHEN 145 THEN 'Series B'
                                        WHEN 146 THEN 'Series C'
                                        WHEN 147 THEN 'Series D'
                                        WHEN 193 THEN 'Series E+'
                                    END
                                ELSE 'VC Funded (Undisclosed)'
                            END
                        WHEN pp.stage_investment = 107 THEN 'Angel/Seed'
                        WHEN pp.stage_investment = 183 THEN 'PE Funded'
                    END AS investment_category
                FROM transactions t
                JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid
                JOIN company c ON t.targetcompanyid = c.companyid
                WHERE 
                    t.transactiontype = 34
                    AND pp.transactionstatus IN (44, 101)
                    AND c.companytypeid NOT IN (8, 9)
                    AND c.companystatusid IN (13, 15)
                    AND t.deletion_status = 0
                    AND pp.deletion_status = 0
                    AND c.deletion_status = 0
                    AND pp.announcementdate = (
                        SELECT MAX(pp2.announcementdate)
                        FROM transactions t2
                        JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid
                        WHERE t2.targetcompanyid = t.targetcompanyid
                        AND t2.transactiontype = 34
                        AND pp2.transactionstatus IN (44, 101)
                        AND pp2.stage_investment IN (182, 183, 107)
                        AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477)
                        AND t2.deletion_status = 0
                        AND pp2.deletion_status = 0
                    )
            ) AS sub
            WHERE investment_category IS NOT NULL
            GROUP BY investment_category

            UNION ALL

            -- Others: companies not in the categorized list
            SELECT 
                'Others' AS investment_category,
                GROUP_CONCAT(DISTINCT c.companyname ORDER BY c.companyname SEPARATOR ', ') AS companies
            FROM transactions t
            JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid
            JOIN company c ON t.targetcompanyid = c.companyid
            WHERE 
                t.transactiontype = 34
                AND pp.transactionstatus IN (44, 101)
                AND c.companytypeid NOT IN (8, 9)
                AND c.companystatusid IN (13, 15)
                AND t.deletion_status = 0
                AND pp.deletion_status = 0
                AND c.deletion_status = 0
                AND t.targetcompanyid NOT IN (
                    SELECT DISTINCT company_id FROM (
                        SELECT 
                            t.targetcompanyid AS company_id
                        FROM transactions t
                        JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid
                        WHERE 
                            t.transactiontype = 34
                            AND pp.transactionstatus IN (44, 101)
                            AND t.deletion_status = 0
                            AND pp.deletion_status = 0
                            AND pp.announcementdate = (
                                SELECT MAX(pp2.announcementdate)
                                FROM transactions t2
                                JOIN privateplacement pp2 ON t2.detailtransactionid = pp2.privateplacementid
                                WHERE t2.targetcompanyid = t.targetcompanyid
                                AND t2.transactiontype = 34
                                AND pp2.transactionstatus IN (44, 101)
                                AND pp2.stage_investment IN (182, 183, 107)
                                AND (pp2.roundofinvestment IS NULL OR pp2.roundofinvestment != 477)
                                AND t2.deletion_status = 0
                                AND pp2.deletion_status = 0
                            )
                    ) AS categorized
                )

            UNION ALL

            -- Total: all companies
            SELECT 
                'Total' AS investment_category,
                GROUP_CONCAT(DISTINCT c.companyname ORDER BY c.companyname SEPARATOR ', ') AS companies
            FROM transactions t
            JOIN privateplacement pp ON t.detailtransactionid = pp.privateplacementid
            JOIN company c ON t.targetcompanyid = c.companyid
            WHERE 
                t.transactiontype = 34
                AND pp.transactionstatus IN (44, 101)
                AND c.companytypeid NOT IN (8, 9)
                AND c.companystatusid IN (13, 15)
                AND t.deletion_status = 0
                AND pp.deletion_status = 0
                AND c.deletion_status = 0

            ORDER BY 
                CASE investment_category
                    WHEN 'Total' THEN 1
                    WHEN 'Others' THEN 2
                    ELSE 3
                END,
                investment_category;
            """;

        try (Connection conn = getConnection(env);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             FileWriter csvWriter = new FileWriter(outputFile)) {

            csvWriter.append("Investment Category,Companies\n");

            while (rs.next()) {
                String category = rs.getString("investment_category");
                String companies = rs.getString("companies");
                csvWriter.append(category).append(",").append("\"").append(companies.replace("\"", "\"\"")).append("\"").append("\n");
            }

            System.out.println("CSV file created: " + outputFile);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(String env) throws SQLException {
        if ("DEV".equalsIgnoreCase(env)) {
            return DriverManager.getConnection(SQL_Constants.DEV_DB_URL, SQL_Constants.DEV_USER, SQL_Constants.DEV_PASSWORD);
        } else {
            return DriverManager.getConnection(SQL_Constants.PROD_DB_URL, SQL_Constants.PROD_USER, SQL_Constants.PROD_PASSWORD);
        }
    }
}