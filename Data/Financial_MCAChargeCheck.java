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

		public class Financial_MCAChargeCheck {

		    @Test
		    public void MCAChargecomparison() {
		    	 Connection connection = null;
		    	
		    	 try {
		             connection = MySQLDBHandler.getConnection();
		             String sqlQuery = "SELECT * FROM `mca_charge_details`" ;
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
		                            
		                           if (localDate.getYear() == 0 || localDate.getMonthValue() == 0 || localDate.getDayOfMonth() == 0) {
		                                columnValue = null; 
		                                }
		                        } 
		                    } catch (SQLException e) { 
		                        if ("Zero date value prohibited".equals(e.getMessage())) { 
		                            columnValue = null; 
		                            } else { 
		                            e.printStackTrace(); 
		                        } 
		                    } catch (DateTimeException ex) {
		                        
		                        columnValue = null;
		                        
		                    }
		                    // Add the column value to rowMap
		                    String mappedFieldName = mapFieldName(columnName);
		                    rowMap.put(mappedFieldName, columnValue);
		                } 
		               
		            
		                resultMap.put(primaryKey, rowMap);
		            }


		            MongoDBHandler mongoDBConnection = new MongoDBHandler();

		            MongoDatabase database = mongoDBConnection.getDatabase();

		            MongoCollection<Document> mongoCollection = database.getCollection("McaChargeDetails");
		            
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
		    	// Specify the MySQL column names that should be compared
		    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("id", "srn","amount","address","companyId", "cinNumber","chargeId","chargeHolderName","dateOfCreation","dateOfModification","dateOfSatisfaction","createdAt","updatedAt","createdBy","updatedBy"));
		    	return columnsToCompare.contains(mysqlFieldName);
		    	}
		    
		    private static String mapFieldName(String mysqlFieldName) {
		        switch (mysqlFieldName) {
		            case "company_id":
		                return "companyId";
		            case "cin_number":
		                return "cinNumber";
		            case "charge_id":
		                return "chargeId";
		            case "charge_holder_name":
		                return "chargeHolderName";
		            case "date_of_creation":
		                return "dateOfCreation";
		            case "date_of_modification":
		                return "dateOfModification";
		            case "date_of_satisfaction":
		                return "dateOfSatisfaction";
		            case "created_at":
		                return "createdAt";
		            case "updated_at":
		                return "updatedAt";
		            case "created_by":
		                return "createdBy";
		            case "updated_by":
		                return "updatedBy";	
		            default:
		                return mysqlFieldName;
		        }
		    }
		}
