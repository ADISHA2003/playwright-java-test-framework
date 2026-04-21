package VCI.Data;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;
import utilities.SQLQueryReader;
import org.bson.Document;
import org.testng.annotations.Test;

public class CompanyRelationCheck {

    @Test
    public void companyrelationcomparison() {
       
            
        	Connection connection = null;
            

            try {
                connection = MySQLDBHandler.getConnection();
                String sqlQuery=SQLQueryReader.readSqlFromFile("C:\\Users\\209749\\eclipse-workspace\\VCI_Data2\\src\\main\\resources\\CompanyRelation.sql");
            
            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
            ResultSet mysqlResultSet = mysqlStmt.executeQuery();
        
            Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
            ResultSetMetaData metaData = mysqlResultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (mysqlResultSet.next()) {
                int primaryKey = mysqlResultSet.getInt(1);
                String primaryKeyNamesql = metaData.getColumnName(1);
                
                {
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
	                    String mappedFieldName = mapFieldName(columnName);
                        rowMap.put(mappedFieldName, columnValue);
                    }
                    resultMap.put(primaryKey, rowMap);
                
                }}

            
            MongoDBHandler mongoDBConnection = new MongoDBHandler();

            MongoDatabase database = mongoDBConnection.getDatabase();
            
            MongoCollection<Document> mongoCollection = database.getCollection("companyRelation");
            
            int mysqlRecordCount = resultMap.size();
            long mongoRecordCount = mongoCollection.countDocuments();
           

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

                Document doc = mongoCollection.find(new Document("investorId", primaryKeyMysql)).first();

                if (doc != null) {
                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
                        String fieldName = mysqlEntry.getKey();
                        Object mysqlValue = mysqlEntry.getValue();
                        Object mongoValue = doc.get(fieldName);
                        
                        String mysqlStringValue = (mysqlValue != null) ? mysqlValue.toString() : null;
                        String mongoStringValue = (mongoValue != null) ? mongoValue.toString() : null;
                        
                        if (!shouldCompare(fieldName)) {
                            continue; 
                        }
                        
                        if (mysqlStringValue instanceof String && ((String) mysqlStringValue).isEmpty()) {
                            // If MongoDB value is also blank, continue to the next iteration
                            if (mongoStringValue instanceof String && ((String) mongoStringValue).isEmpty()) {
                                continue;
                            }
                        }
                        if (mongoStringValue == null) {
                            
                        	mongoStringValue = (String) findNestedMongoDBValue(doc, fieldName.split("\\."));
                            
                            if (mysqlStringValue instanceof String && ((String) mysqlStringValue).isEmpty()) {
                                
                                if (mongoStringValue instanceof String && ((String) mongoStringValue).isEmpty()) {
                                    continue;
                                }
                            }
                            if (mysqlStringValue instanceof String && mysqlStringValue.equals(0) && mongoStringValue == null) {
                                
                                continue; 
                        }}

                        if (mysqlStringValue != null && !mysqlStringValue.equals(mongoStringValue)) {
                            System.out.println("Data mismatch for primary key: " + primaryKeyMysql + ", field: " + fieldName);
                            System.out.println("MySQL value: " + mysqlStringValue + ", MongoDB value: " + mongoStringValue);
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
    
    private static Object findNestedMongoDBValue(Document doc, String[] fieldNames) {
        Object value = doc;
        for (String fieldName : fieldNames) {
            if (value instanceof Document) {
                value = ((Document) value).get(fieldName);
                if (value == null) {
                    return null; 
                }
            } else if (value instanceof List) {
                List<Object> nestedValues = new ArrayList<>();
                for (Object obj : (List<?>) value) {
                    if (obj instanceof Document) {
                        Object nestedValue = findNestedMongoDBValue((Document) obj, fieldNames);
                        if (nestedValue != null) {
                            nestedValues.add(nestedValue);
                        }
                    }
                }
                if (!nestedValues.isEmpty()) {
                    return nestedValues; 
                } else {
                    return null; 
                }
            } else {
                return null; 
            }
        }
        return value;
    }

    private static boolean shouldCompare(String mysqlFieldName) {
    	
    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("investorId", "primary._id","primary.name","primary.type","related._id", "related.name", "related.type","relationshipType", "percentageStake","capitalCommited", "creationTimestamp"));
    	return columnsToCompare.contains(mysqlFieldName);
    	}
    
    private static String mapFieldName(String mysqlFieldName) {
        switch (mysqlFieldName) {
            case "investorid":
                return "investorId";
            case "targetcompanyid":
                return "primary._id";
            case "primarycompanyname":
                return "primary.name";
            case "primarycompanytype":
                return "primary.type";
            case "relativecompanyid":
                return "related._id";
            case "relatedcompanyname":
                return "related.name";
            case "relatedcompanytype":
                return "related.type";
            case "relationshiptypedesc":
                return "relationshipType";
            case "percentagestake":
                return "percentageStake";
            case "staketype":
                return "stakeType";
            case "capital_committed":
                return "capitalCommited";
            case "creation_timestamp":
                return "creationTimestamp";
      
            default:
                return mysqlFieldName;
        }
    }
}
