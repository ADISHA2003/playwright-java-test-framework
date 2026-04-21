package VCI.Data;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.time.DateTimeException;
	import java.time.LocalDate;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Map;
	import java.util.Set;

	import org.bson.Document;
	import org.testng.annotations.Test;

	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoDatabase;

	import utilities.MongoDBHandler;
	import utilities.MySQLDBHandler;

	public class Financial_RightsDetails {
		 @Test
		    public void rightDetails() {
		    	 Connection connection = null;
		    	
		    	 try {
		             connection = MySQLDBHandler.getConnection();
		             String sqlQuery = "SELECT * FROM `rights_details` " ;
		            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
		            ResultSet mysqlResultSet = mysqlStmt.executeQuery();

		         
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

		            MongoCollection<Document> mongoCollection = database.getCollection("RightsDetailsMongoDb");
		            
		            int mysqlRecordCount = resultMap.size();
		            System.out.println("MySQL Record Count: " + mysqlRecordCount);

		            
		            long mongoRecordCount = mongoCollection.countDocuments();
		            System.out.println("MongoDB Record Count: " + mongoRecordCount);

		          
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

		                Document doc = mongoCollection.find(new Document("rightsDetailsId", primaryKeyMysql)).first();

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
		    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("ratio", "rightsDetailsId","companyCode","yearEnding","faceValueExistingInstrument", "rightsPremium","existingInstrumentName","xrDate","recordDate","modifiedDate"));
		    	return columnsToCompare.contains(mysqlFieldName);
	    	}
		    
		    private static String mapFieldName(String mysqlFieldName) {
		        switch (mysqlFieldName) {
		            case "rights_details_id":
		                return "rightsDetailsId";
		            case "company_code	":
		                return "companyCode";
		            case "year_ending":
		                return "yearEnding";
		            case "face_value_existing_instrument":
		                return "faceValueExistingInstrument";
		            case "rights_premium":
		                return "rightsPremium";
		            case "existing_instrument_name":
		                return "existingInstrumentName";
		            case "xr_date":
		                return "xrDate";
		            case "record_date":
		                return "recordDate";
		            case "modified_date":
		                return "modifiedDate";
		            
		            default:
		                return mysqlFieldName;
		        }
		    }


	}




