package VCI.Data;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;
import org.bson.Document;
	import org.testng.annotations.Test;

	public class financialcheck {

	    @Test
	    public void financialcomparison() {
	    	 Connection connection = null;
	    	
	    	 try {
	             connection = MySQLDBHandler.getConnection();
	             String sqlQuery = "SELECT * FROM `financial` ";
	            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
	            ResultSet mysqlResultSet = mysqlStmt.executeQuery();

	            // Step 3: Save MySQL results in a map
	            Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
	            ResultSetMetaData metaData = mysqlResultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            while (mysqlResultSet.next()) {
	                int primaryKey = mysqlResultSet.getInt(1);
	                Map<String, Object> rowMap = new HashMap<>();

	                for (int i = 2; i <= columnCount; i++) {
	                    String columnName = metaData.getColumnName(i);
	                    Object columnValue = null; 

	                    try { 
	                        columnValue = mysqlResultSet.getObject(i); 

	                        if (columnValue != null && columnValue instanceof java.sql.Date) { 
	                            java.sql.Date dateValue = (java.sql.Date) columnValue; 
	                            
	                            LocalDate localDate = dateValue.toLocalDate();
	                            
	                            // Check if the year, month, or day value is invalid
	                            if (localDate.getYear() == 0 || localDate.getMonthValue() == 0 || localDate.getDayOfMonth() == 0) {
	                                columnValue = null; // Replace invalid date with null 
	                                }
	                        } 
	                    } catch (SQLException e) { 
	                        if ("Zero date value prohibited".equals(e.getMessage())) { 
	                            columnValue = null; // Set column value to null 
	                            } else { 
	                            e.printStackTrace(); 
	                        } 
	                    } catch (DateTimeException ex) {
	                        // Catch any DateTimeException and set column value to null
	                        columnValue = null;
	                        
	                    }
	                    // Add the column value to rowMap
	                    String mappedFieldName = mapFieldName(columnName);
	                    rowMap.put(mappedFieldName, columnValue);
	                } 
	                // Add rowMap to your data structure
	            
	                resultMap.put(primaryKey, rowMap);
	            }


	            MongoDBHandler mongoDBConnection = new MongoDBHandler();

	            MongoDatabase database = mongoDBConnection.getDatabase();

	            MongoCollection<Document> mongoCollection = database.getCollection("FinancialMongoDb");
	            
	            int mysqlRecordCount = resultMap.size();
	            System.out.println("MySQL Record Count: " + mysqlRecordCount);

	            // Count MongoDB records
	            long mongoRecordCount = mongoCollection.countDocuments();
	            System.out.println("MongoDB Record Count: " + mongoRecordCount);

	            // Compare record counts
	            if (mysqlRecordCount == mongoRecordCount) {
	                System.out.println("Record counts match between MySQL and MongoDB.");
	            } else {
	                System.out.println("Record counts do not match between MySQL and MongoDB.");
	                System.out.println("MySQL Record Count: " + mysqlRecordCount);
	                System.out.println("MongoDB Record Count: " + mongoRecordCount);
	            }

	            for (Map.Entry<Integer, Map<String, Object>> entry : resultMap.entrySet()) {
	                Integer primaryKeyMysql = entry.getKey();
	                Map<String, Object> mysqlRow = entry.getValue();

	                Document doc = mongoCollection.find(new Document("id", primaryKeyMysql)).first();

	                if (doc != null) {
	                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
	                        String fieldName = mysqlEntry.getKey();
	                        Object mysqlValue = mysqlEntry.getValue();
	                        Object mongoValue = doc.get(fieldName);
	                        
	                        if (!shouldCompare(fieldName)) {
	                            continue; // Skip this field
	                        }
	                        if (mysqlValue != null && !mysqlValue.equals(mongoValue)) {
	                            System.out.println("Data mismatch for primary key: " + primaryKeyMysql + ", field: " + fieldName);
	                            System.out.println("MySQL value: " + mysqlValue + ", MongoDB value: " + mongoValue);
	                        }
	                    }
	                } else {
	                    System.out.println("Missing record in MongoDB for primary key: " + primaryKeyMysql);
	                }
	            }

	            connection.close();
	            mongoDBConnection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    private static boolean shouldCompare(String mysqlFieldName) {
	    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("id", "vcc_company_id","ebit","depreciation","inventories","investments","totalAssets","companyCode","yearEnding","operatingIncome","totalIncome","costOfSales","adjustedPbdit","adjustedPbt","adjustedPat","equityCapital","resAndSurplus","netWorth","totalDebt","grossBlock","currentAssets","cashAndBankBalance","netCurrentAssets","totalCurrentLiabilities","totalCurrentAssets","securedLoans","unsecuredLoans","netBlock","ebitaMargin","closingPrice","marketCapitalization","equityDividend","enterpriseValue","totalAssets","cfYearEnding","netCFlowOp","ntCshInIA","ntCshUsdFA","rYearEnding","totalDebtToOwnersFund","currentRatio","npByCapitalEmployed","reportedReturnOnNetWorth","netProfitMargin","dividendPayoutRatioCp","reportedEps","priceToBookValue","reportedCashEps","divYieldPer","operatingMargin","returnOnAssets","cashRatio","inventoryTurnoverRatio","workingCapitalTurnoverRatio","debtToAssetRatio","quickRatio","financialType","createdAt"));
	    	return columnsToCompare.contains(mysqlFieldName);
	    	}
	   
	    private static String mapFieldName(String mysqlFieldName) {
	        switch (mysqlFieldName) {
	            case "vcc_company_id":
	                return "vcc_company_id";
	            case "company_code	":
	                return "companyCode";
	            case "year_ending":
	                return "yearEnding";
	            case "operating_income":
	                return "operatingIncome";
	            case "total_income":
	                return "totalIncome";
	            case "cost_of_sales":
	                return "costOfSales";
	            case "adjusted_pbdit":
	                return "adjustedPbdit";
	            case "adjusted_pbt":
	                return "adjustedPbt";
	            case "adjusted_pat":
	                return "adjustedPat";
	            case "equity_capital":
	                return "equityCapital";
	            case "res_and_surplus":
	                return "resAndSurplus";
	            case "net_worth	":
	                return "netWorth";
	            case "total_debt":
	                return "totalDebt";
	            case "gross_block":
	                return "grossBlock";
	            case "current_assets":
	                return "currentAssets";
	            case "cash_and_bank_balance":
	                return "cashAndBankBalance";
	            case "net_current_assets	":
	                return "netCurrentAssets";
	            case "total_current_liabilities":
	                return "totalCurrentLiabilities";
	            case "total_current_assets":
	                return "totalCurrentAssets";
	            case "secured_loans":
	                return "securedLoans";
	            case "unsecured_loans":
	                return "unsecuredLoans";
	            case "net_block":
	                return "netBlock";
	            case "ebita_margin":
	                return "ebitaMargin";
	            case "closingprice":
	                return "closingPrice";
	            case "Market_Capitalization":
	                return "marketCapitalization";
	            case "equitydividend":
	                return "equityDividend";
	            case "EnterpriseValue	":
	                return "enterpriseValue";
	            case "TotalAssets":
	                return "totalAssets";
	            case "cfyearending":
	                return "cfYearEnding";
	            case "net_c_flow_op":
	                return "netCFlowOp";
	            case "nt_csh_in_i_a":
	                return "ntCshInIA";
	            case "nt_csh_usd_f_a":
	                return "ntCshUsdFA";
	            case "ryearending":
	                return "rYearEnding";
	            case "total_debt_to_ownersfund":
	                return "totalDebtToOwnersFund";
	            case "current_ratio":
	                return "currentRatio";
	            case "np_by_capital_employed":
	                return "npByCapitalEmployed";
	            case "reported_return_on_net_worth":
	                return "reportedReturnOnNetWorth";
	            case "net_profit_margin":
	                return "netProfitMargin";
	            case "dividend_payout_ratio_cp	":
	                return "dividendPayoutRatioCp";
	            case "reported_eps":
	                return "reportedEps";
	            case "price_to_book_value":
	                return "priceToBookValue";
	            case "reported_cash_eps":
	                return "reportedCashEps";
	            case "div_yield_per":
	                return "divYieldPer";
	            case "operating_margin":
	                return "operatingMargin";
	            case "return_on_assets":
	                return "returnOnAssets";
	            case "cash_ratio":
	                return "cashRatio";
	            case "inventory_turnover_ratio":
	                return "inventoryTurnoverRatio";
	            case "working_capital_turnover_ratio":
	                return "workingCapitalTurnoverRatio";
	            case "debt_to_asset_ratio":
	                return "debtToAssetRatio";
	            case "quick_ratio":
	                return "quickRatio";
	            case "financial_type":
	                return "financialType";
	            case "created_at":
	                return "createdAt";
	            default:
	                return mysqlFieldName;
	        }
	    }
	}

