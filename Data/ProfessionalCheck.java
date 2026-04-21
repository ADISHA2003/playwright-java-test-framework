package VCI.Data;
import java.sql.*;
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

	public class ProfessionalCheck { 

	    @Test
	    public void professionalcomparison() {
	    	Connection connection = null;
	        

	         try {
	             connection = MySQLDBHandler.getConnection();
	             String sqlQuery="SELECT * FROM `professional` ";
	        	
	            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
	            ResultSet mysqlResultSet = mysqlStmt.executeQuery();
	         
	            Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
	            ResultSetMetaData metaData = mysqlResultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();
	            while (mysqlResultSet.next()) {
	                int primaryKey = mysqlResultSet.getInt(1);
	                String primaryKeyNamesql = metaData.getColumnName(1);
	                int companyTypeId = mysqlResultSet.getInt("professionalid");
	                    Map<String, Object> rowMap = new HashMap<>();
	                    for (int i = 2; i <= columnCount; i++) {
	                        String columnName = metaData.getColumnName(i);
	                        Object columnValue = null; 

	                        try { 

	                        // Retrieve the column value 

	                        	columnValue = mysqlResultSet.getObject(i); 

	                        // Handle zero date explicitly 

	                        if (columnValue instanceof java.sql.Date) { 

	                        java.sql.Date dateValue = (java.sql.Date) columnValue; 

	                        // Check if the date is a zero date 

	                        if (dateValue.toString().equals("0000-00-00")) { 

	                        columnValue = null; // Replace zero date with null 

	                        } 

	                        } 

	                        } catch (SQLException e) { 

	                        // Handle the SQLException 

	                        if ("Zero date value prohibited".equals(e.getMessage())) { 

	                        // Handle zero date prohibited exception 

	                        columnValue = null; // Set column value to null 

	                        } else { 

	                        // Handle other SQLExceptions 

	                        // Log or print the exception 

	                        e.printStackTrace(); 

	                        } 

	                        } 
	                        String mappedFieldName = mapFieldName(columnName);
	                        rowMap.put(mappedFieldName, columnValue);
	                    }
	                    resultMap.put(primaryKey, rowMap);
	                }
	            

	            // Step 4: Establish MongoDB connection
	            MongoDBHandler mongoDBConnection = new MongoDBHandler();

	            // Get the database instance
	            MongoDatabase database = mongoDBConnection.getDatabase();

	            MongoCollection<Document> mongoCollection = database.getCollection("professional");
	            
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

	                Document doc = mongoCollection.find(new Document("professionalId", primaryKeyMysql)).first();

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
	    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("professionalId","companyId","professionalName","firstName","middleName","lastName","biography","fax","email","companyPhoneNumber","directPhoneNumber","linkedinLink","angelFlag","dinNumber","createdAt"));
	    	return columnsToCompare.contains(mysqlFieldName);
    	
	    	}
	    
	    private static String mapFieldName(String mysqlFieldName) {
	        switch (mysqlFieldName) {
	            case "professionalid":
	                return "professionalId";
	            case "companyid	":
	                return "companyId";
	            case "professionalname":
	                return "professionalName";
	            case "firstname":
	                return "firstName";
	            case "middlename":
	                return "middleName";
	            case "lastname":
	                return "lastName";
	            case "companyphonenumber":
	                return "companyPhoneNumber";
	            case "directphonenumber":
	                return "directPhoneNumber";
	            case "linkedinlink":
	                return "linkedinLink";
	            case "angel_flag":
	                return "angelFlag";
	            case "din_number":
	                return "dinNumber";
	            case "created_at":
	                return "createdAt";
	            
	            default:
	                return mysqlFieldName;
	        }
	    }
	}
