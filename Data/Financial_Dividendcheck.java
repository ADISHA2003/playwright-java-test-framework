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

	public class Financial_Dividendcheck {

			    @Test
			    public void Dividendcomparison() {
			    	 Connection connection = null;
			    	
			    	 try {
			             connection = MySQLDBHandler.getConnection();
			             String sqlQuery = "SELECT * FROM `dividend` " ;
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
			                                
			                                columnValue = mysqlResultSet.getInt(i);
			                            } catch (SQLException e) {
			                                
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
			                            columnValue = null;
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

			            MongoCollection<Document> mongoCollection = database.getCollection("DividendMongoDb");
			            
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

			                Document doc = mongoCollection.find(new Document("dividendId", primaryKeyMysql)).first();

			                if (doc != null) {
			                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
			                        String fieldName = mysqlEntry.getKey();
			                        Object mysqlValue = mysqlEntry.getValue();
			                        Object mongoValue = doc.get(fieldName);
			                        if (!shouldCompare(fieldName)) {
			                            continue; 
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
			    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("percentage", "instrumentType","value","remarks","dividendId","companyCode","dateOfAnnouncement","interimOrFinal","instrumentTypeDescription","dateOfSatisfaction","recordDate","bookClosureStartDate","bookClosureEndDate","modifiedDate","delFlag","xdDate"));
			    	return columnsToCompare.contains(mysqlFieldName);
			    	}
			    
			    private static String mapFieldName(String mysqlFieldName) {
			        switch (mysqlFieldName) {
			            case "dividend_id":
			                return "dividendId";
			            case "company_code":
			                return "companyCode";
			            case "date_of_announcement":
			                return "dateOfAnnouncement";
			            case "interim_or_final":
			                return "interimOrFinal";
			            case "instrument_type":
			                return "instrumentType";
			            case "instrument_type_description":
			                return "instrumentTypeDescription";
			            case "date_of_satisfaction":
			                return "dateOfSatisfaction";
			            case "record_date":
			                return "recordDate";
			            case "book_closure_start_date":
			                return "bookClosureStartDate";
			            case "book_closure_end_date":
			                return "bookClosureEndDate";
			            case "modified_date":
			                return "modifiedDate";
			            case "del_flag":
			                return "delFlag";
			           //case "xd_date":
			            //   return "xdDate";
			            default:
			                return mysqlFieldName;
			        }
			    }
			}

	

