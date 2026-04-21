package VCI.Data;

	import java.sql.*;
import java.text.SimpleDateFormat;
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

	public class Financial_DocumentAccesstablecheck {

			    @Test
			    public void Documentaccesstablecomparison() {
			    	 Connection connection = null;
			    	
			    	 try {
			             connection = MySQLDBHandler.getConnection();
			             String sqlQuery = "SELECT * FROM `document_access_table` " ;
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
			                        
			                        if (columnName.equals("instrument_type")) {
			                            try {
			                                // Retrieve the column value
			                                columnValue = mysqlResultSet.getInt(i);
			                            } catch (SQLException e) {
			                                // Handle any SQL exception
			                                e.printStackTrace();
			                            }
			                        }


			                        if (columnValue != null && columnValue instanceof java.sql.Date) { 
			                            java.sql.Date dateValue = (java.sql.Date) columnValue; 
			                            
			                            LocalDate localDate = dateValue.toLocalDate();
			                            
			                            if (localDate.getYear() == 0 || localDate.getMonthValue() == 0 || localDate.getDayOfMonth() == 0) {
			                                columnValue = null; 
			                                }
			                        } 
			                    } catch (SQLException e) { 
			                        if ("Zero date value prohibited".equals(e.getMessage())) { 
			                            columnValue = null; // Set column value to null 
			                            } else { 
			                            e.printStackTrace(); 
			                        } 
			                    } catch (DateTimeException ex) {
			                        
			                        columnValue = null;
			                        
			                    }
			                   
			                    
			                    String mappedFieldName = mapFieldName(columnName);
			                    rowMap.put(mappedFieldName, columnValue);
			                } 
			               
			            
			                resultMap.put(primaryKey, rowMap);
			            }


			            MongoDBHandler mongoDBConnection = new MongoDBHandler();

			            MongoDatabase database = mongoDBConnection.getDatabase();

			            MongoCollection<Document> mongoCollection = database.getCollection("DocumentAccessTableMongoDb");
			            
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

			                Document doc = mongoCollection.find(new Document("documentId", primaryKeyMysql)).first();
			                boolean allMatch = true;
				                if (doc != null) {
				                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
				                        String fieldName = mysqlEntry.getKey();
				                        Object mysqlValue = mysqlEntry.getValue();
				                        Object mongoValue = doc.get(fieldName);
				                        if (!shouldCompare(fieldName)) {
				                            continue; 
				                        }
				                        
				                        String mysqlDateStr = mysqlValue != null ? mysqlValue.toString() : null;
				                        String mongoDateStr = mongoValue != null ? mongoValue.toString() : null;

				                       if (mysqlDateStr == null && mongoDateStr == null) {
				                            continue; 
				                        } 
				                        
				                        if (mysqlDateStr==mongoDateStr) {
				                        	
				                        	System.out.println("Data matched "+ primaryKeyMysql + ", field: " + fieldName+"MySQL value: " + mysqlDateStr + ", MongoDB value: " + mongoDateStr);
				                        }
				                        else if (mysqlDateStr == null || mongoDateStr == null || !mysqlDateStr.equals(mongoDateStr)) {
				                            System.out.println("Data mismatch for primary key: " + primaryKeyMysql + ", field: " + fieldName);
				                            System.out.println("MySQL value: " + mysqlDateStr + ", MongoDB value: " + mongoDateStr);
				                        }
				                    }
				                    
				                    if (allMatch) {
				                        for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
				                            String fieldName = mysqlEntry.getKey();
				                            Object mysqlValue = mysqlEntry.getValue();
				                            Object mongoValue = doc.get(fieldName);
				                            System.out.println("Field: " + fieldName);
				                            System.out.println("MySQL value: " + mysqlValue);
				                            System.out.println("MongoDB value: " + mongoValue);
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
			    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("source", "language" , "headline","documentId","documentCategory","documentType","documentDate","uploadedDate","uploadedBy","transactionId","transactionType","targetCompanyId","finYearEnd","documentFormat","originalFileName","md5Hash"));
			    	return columnsToCompare.contains(mysqlFieldName);
			    	}
			    
			    private static String mapFieldName(String mysqlFieldName) {
			        switch (mysqlFieldName) {
			            case "document_id":
			                return "documentId";
			            case "document_category":
			                return "documentCategory";
			            case "document_type":
			                return "documentType";
			            case "document_date":
			                return "documentDate";
			            case "uploaded_date":
			                return "uploadedDate";
			            case "uploaded_by":
			                return "uploadedBy";
			            case "transaction_id":
			                return "transactionId";
			            case "transaction_type":
			                return "transactionType";
			            case "targetcompanyid":
			                return "targetCompanyId";
			            case "fin_year_end":
			                return "finYearEnd";
			            case "document_format":
			                return "documentFormat";
			            case "original_file_name":
			                return "originalFileName";
			            case "md5_hash":
			            	return "md5Hash";
			            default:
			                return mysqlFieldName;
			        }
			    }
			}

	

