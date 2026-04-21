package VCI.Data;

import org.bson.Document;
import org.testng.annotations.Test;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

	public class Transaction {


	    private Map<Integer, Map<String, Object>> executeQueryAndSaveToMap(Connection connection, String query) throws SQLException {
	        Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {
	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();
	            while (resultSet.next()) {
	                int primaryKeymysql = resultSet.getInt(1);
	                Map<String, Object> rowMap = new HashMap<>();
	                for (int i = 1; i <= columnCount; i++) {
	                    String columnName = metaData.getColumnName(i);
	                    Object columnValue = null; 

                        try { 

                        	columnValue = resultSet.getObject(i); 

                        if (columnValue instanceof java.sql.Date) { 

                        java.sql.Date dateValue = (java.sql.Date) columnValue; 

                        if (dateValue.toString().equals("0000-00-00")) { 

                        columnValue = null;
                        } 

                        } 

                        } catch (SQLException e) { 


                        if ("Zero date value prohibited".equals(e.getMessage())) { 

                        columnValue = null; 

                        } else { 

                         e.printStackTrace(); 

                        } 

                        } 
                        String mappedFieldName = mapFieldName(columnName);
                        rowMap.put(mappedFieldName, columnValue);
                    }
	                resultMap.put(primaryKeymysql, rowMap);
	            }
	        }
	        return resultMap;
	    }

	    private void executeQueriesBasedOnTransactionType(Connection connection, Map<Integer, Map<String, Object>> resultMap) throws SQLException {
	        Map<Integer, ResultSet> queryResultsMap = new HashMap<>();
	        for (Map.Entry<Integer, Map<String, Object>> entry : resultMap.entrySet()) {
	        	Object transactionTypeObj = entry.getValue().get("transactiontype");
	        	String transactionType;
	        	if (transactionTypeObj instanceof String) {
	        	    transactionType = (String) transactionTypeObj;
	        	} else if (transactionTypeObj instanceof Integer) {
	        	   
	        	    transactionType = Integer.toString((Integer) transactionTypeObj);
	        	} else {
	        	   
	        	    throw new IllegalArgumentException("Transaction type is neither String nor Integer");
	        	}
	            String query = "";
	            switch (transactionType) {
	            case "33":
                    query = "SELECT * FROM `mergeracquisitiondetails` ";
                    break;
                case "34":
                    query = "SELECT * FROM `privateplacement` ";
                    break;
                case "35":
                    query = "SELECT * FROM `ecm` ";
                    break;
                case "36":
                    query = "SELECT * FROM `debt_issue` ";
                    break;
                case "412":
                    query = "SELECT * FROM `ipo` ";
                    break;
                // Add cases for other column A values
                default:
                    throw new IllegalArgumentException("Invalid value for transactionType: " + transactionType);
	            }
	            queryResultsMap.put(Integer.parseInt(transactionType), executeQueryAndSaveToResultSet(connection, query));
	        }
	        appendQueryResultToResultMap(queryResultsMap, resultMap);
	    }

	    private ResultSet executeQueryAndSaveToResultSet(Connection connection, String query) throws SQLException {
	        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        return statement.executeQuery(query);
	    }

	    private void appendQueryResultToResultMap(Map<Integer, ResultSet> queryResultsMap, Map<Integer, Map<String, Object>> resultMap) throws SQLException {
	        for (Map.Entry<Integer, Map<String, Object>> entry : resultMap.entrySet()) {
	            int transactionId = (int) entry.getValue().get("detailtransactionid");
	            Object transactionTypeObj = entry.getValue().get("transactiontype");

	            if (transactionTypeObj != null && transactionTypeObj instanceof Integer) {
	               
	                String transactionType = String.valueOf(transactionTypeObj);
	                ResultSet queryResult = queryResultsMap.get(Integer.parseInt(transactionType));
	            if (queryResult != null) {
	                queryResult.beforeFirst();
	                while (queryResult.next()) {
	                    int queryTransactionId = queryResult.getInt(1);
	                    if (transactionId == queryTransactionId) {
	                        ResultSetMetaData metaData = queryResult.getMetaData();
	                        int columnCount = metaData.getColumnCount();
	                        Map<String, Object> rowMap = resultMap.get(entry.getKey());
	                        for (int i = 1; i <= columnCount; i++) {
	                            String columnName = metaData.getColumnName(i);
	                            Object value = null; 

	                            try { 

	                            	value = queryResult.getObject(i); 

	                            if (value instanceof java.sql.Date) { 

	                            java.sql.Date dateValue = (java.sql.Date) value; 

	                            if (dateValue.toString().equals("0000-00-00")) { 

	                            value = null; 

	                            } 

	                            } 

	                            } catch (SQLException e) { 
	                            if ("Zero date value prohibited".equals(e.getMessage())) { 
	                              value = null; 
	                            } else { 
	                            e.printStackTrace(); 

	                            } 

	                            } 
	                            rowMap.put(columnName, value);
	                        }
	                        break;
	                    }
	                }
	            }
	        }
	    }
	    }

	    
	    @Test
	    public void processData() throws SQLException {
	        
	        Connection connection = null;
	        

	         try {
	             connection = MySQLDBHandler.getConnection();
	             String query = "SELECT * FROM `transactions` LIMIT 10 "; // Replace your_table with the actual table name
	            Map<Integer, Map<String, Object>> resultMap = executeQueryAndSaveToMap(connection, query);
	            executeQueriesBasedOnTransactionType(connection, resultMap);
	            
	            MongoDBHandler mongoDBConnection = new MongoDBHandler();

	            MongoDatabase database = mongoDBConnection.getDatabase();
	          
	            MongoCollection<Document> mongoCollection = database.getCollection("transactions");
	         
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

	                Document doc = mongoCollection.find(new Document("transactionId", primaryKeyMysql)).first();

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
	            mongoDBConnection.close();

	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	        
	
	 private static boolean shouldCompare(String mysqlFieldName) {
	    	// Specify the MySQL column names that should be compared
	    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("professionalId","companyId","professionalName","firstName","middleName","lastName","biography","fax","email","companyPhoneNumber","directPhoneNumber","linkedinLink","angelFlag","dinNumber","createdAt"));
	    	return columnsToCompare.contains(mysqlFieldName);
 	
	    	}
	  	    private static String mapFieldName(String mysqlFieldName) {
	        switch (mysqlFieldName) {
	            case "transactionid":
	                return "transactionId";
	            case "transactiontype":
	                return "transactionType";
	            case "previous_name":
	                return "previousName";
	            default:
	                return mysqlFieldName;
	        }
	    }
	}
