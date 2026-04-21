package VCI.Data;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;
import utilities.SQLQueryReader;

public class companyinvestor {

    @Test
    public void companyInvestorcomparison() throws SQLException, IOException {
        Connection connection = null;
        Map<Integer, Map<String, Object>> resultMap = new HashMap<>();


        try {
            connection = MySQLDBHandler.getConnection();
            String sqlQuery = SQLQueryReader.readSqlFromFile("C:\\Users\\209749\\eclipse-workspace\\VCI_Data2\\src\\main\\resources\\Investorrelation.sql");
            
            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
            ResultSet mysqlResultSet = mysqlStmt.executeQuery();
            
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
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
     
        MongoDBHandler mongoDBConnection = new MongoDBHandler();
        
        MongoDatabase database = mongoDBConnection.getDatabase();
        MongoCollection<Document> mongoCollection = database.getCollection("company");
        Document mongoDocument = null; 
        for (Map.Entry<Integer, Map<String, Object>> entry : resultMap.entrySet()) {
            Integer primaryKey = entry.getKey();
            Map<String, Object> record = entry.getValue();

            
            Object companyid = record.get("companyId"); 
            
            
             mongoDocument = mongoCollection.find(eq("companyId", companyid)).first();
             if (mongoDocument != null) {
            	 List<Document> investors = mongoDocument.getList("investors", Document.class);
            	 for (Document investor : investors) {
                     
            		 Object investorid = record.get("investorsId");

            		 Object investorId = investor.get("investorId");
                     
                     if (investorid != null && investorId != null) {
                         
                         if (investorId instanceof String && investorid instanceof Integer) {
                             
                             try {
                                 investorId = Integer.parseInt((String) investorId);
                             } catch (NumberFormatException e) {
                               
                                 e.printStackTrace();
                                 continue; 
                             }
                         } else if (investorid instanceof Integer && investorId instanceof String) {
                            
                             investorid = String.valueOf(investorid);
                         }
                    
                     if (investorid.equals(investorId)) {
                    	
                        boolean allFieldsMatch = true;
                         for (Map.Entry<String, Object> recordEntry : record.entrySet()) {
                             String fieldName = recordEntry.getKey();
                             Object mysqlValue = recordEntry.getValue();
                             Object mongoValue = investor.get(mapFieldName(fieldName));
                             /*System.out.println("Field: " + fieldName);
                             System.out.println("MySQL Value: " + mysqlValue);
                             System.out.println("MongoDB Value: " + mongoValue);*/

                             if (!shouldCompare(fieldName)) {
                                 continue; 
                             }
                             if ((mysqlValue == null && mongoValue != null) || !mysqlValue.equals(mongoValue)) {
                                
                                allFieldsMatch = false;
                               
                                   
                                    System.out.println("Match found for companyid: " + companyid + " and Investor Id" +investorid+ ",Field " + fieldName +" does not match ;MySQL Value:" + mysqlValue + ", MongoDB Value: " + mongoValue);
                                
                                 break;
                             }
                         }

                     }
                 }
             } }
                     else {
                 
                 System.out.println("No matching record found in MongoDB for companyid value: " + companyid);
             }
         }

            
        }
    
    /*private static Object findNestedMongoDBValue(Document doc, String[] fieldNames) {
        Object value = doc;
        for (String fieldName : fieldNames) {
            if (value instanceof Document) {
                value = ((Document) value).get(fieldName);
            } else {
                return null;
            }
        }
        return value;
    }
    */
private static boolean shouldCompare(String mysqlFieldName) {
// Specify the MySQL column names that should be compared
Set<String> columnsToCompare = new HashSet<>(Arrays.asList("companyid", "investorid", "name", "relationshipType", "percentageStake", "stakedesc", "investorType"));
return columnsToCompare.contains(mysqlFieldName);
}
            
            
        

    private static String mapFieldName(String mysqlFieldName) {
        switch (mysqlFieldName) {
            case "targetcompanyid":
                return "companyId";
            case "relativecompanyid":
                return "investorsId";
            case "investor_name":
                return "name";
            case "relationshiptypedesc":
                return "relationshipType";
            case "percentagestake":
                return "percentageStake";
            case "stakedesc":
                return "stakeType";
           case "RelationShipType":
                return "investorType";

        }
        return mysqlFieldName;
    }
}
