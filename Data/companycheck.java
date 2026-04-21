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
import utilities.SQLQueryReader;
import org.bson.Document;
import org.testng.annotations.Test;

public class companycheck {

    @Test
    public void companycomparison() {
    	
    	 Connection connection = null;
      

         try {
             connection = MySQLDBHandler.getConnection();
             String sqlQuery=SQLQueryReader.readSqlFromFile("C:\\Users\\209749\\eclipse-workspace\\VCI_Data2\\src\\main\\resources\\CompanyQuery.sql");
        	
            
            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
            ResultSet mysqlResultSet = mysqlStmt.executeQuery();

            // Step 3: Save MySQL results in a map
            Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
            ResultSetMetaData metaData = mysqlResultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (mysqlResultSet.next()) {
                int primaryKey = mysqlResultSet.getInt(1);
                //String primaryKeyNamesql = metaData.getColumnName(1);
                int companyTypeId = mysqlResultSet.getInt("companytypeid");
                if (companyTypeId != 60) {
                    Map<String, Object> rowMap = new HashMap<>();
                    for (int i = 2; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = null; 

                        try { 
                            columnValue = mysqlResultSet.getObject(i); 

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

                        
                        if (columnName.equals("primaryaddressline1") || columnName.equals("primaryaddressline2") || columnName.equals("primaryaddressline3")) {
                            
                            String mergedValue = (String)rowMap.getOrDefault("contact.address", "");
                            
                            mergedValue += (columnValue != null ? columnValue.toString() : "");
                            
                            rowMap.put("contact.address", mergedValue);
                        } else {
                            
                            String mappedFieldName = mapFieldName(columnName);
                            rowMap.put(mappedFieldName, columnValue);
                        }
                    }
                    resultMap.put(primaryKey, rowMap);
                }
            }

            
        
            MongoDBHandler mongoDBConnection = new MongoDBHandler();
            MongoDatabase database = mongoDBConnection.getDatabase();

            MongoCollection<Document> mongoCollection = database.getCollection("company");
            
            int mysqlRecordCount = resultMap.size();
            //System.out.println("MySQL Record Count: " + mysqlRecordCount);

            long mongoRecordCount = mongoCollection.countDocuments();
            //System.out.println("MongoDB Record Count: " + mongoRecordCount);

           
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

                Document doc = mongoCollection.find(new Document("companyId", primaryKeyMysql)).first();

                if (doc != null) {
                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
                        String fieldName = mysqlEntry.getKey();
                        Object mysqlValue = mysqlEntry.getValue();
                        Object mongoValue = doc.get(fieldName);
                        
                        if (!shouldCompare(fieldName)) {
                            continue; // Skip this field
                        }
                        if (mysqlValue instanceof Long) {
                            mysqlValue = ((Long) mysqlValue).intValue();
                        }
                        if ((mysqlValue == null || (mysqlValue instanceof String && ((String) mysqlValue).isEmpty())) && mongoValue == null) {
                            
                            continue;
                        }
                        if (mysqlValue instanceof Integer && (Integer) mysqlValue == 0 && mongoValue == null) {
                          
                            continue; 
                        }
                        if (mysqlValue instanceof String && ((String) mysqlValue).isEmpty()) {
                            
                            if (mongoValue instanceof String && ((String) mongoValue).isEmpty()) {
                                continue;
                            }
                        }
                        if (mongoValue == null) {
                            
                            mongoValue = findNestedMongoDBValue(doc, fieldName.split("\\."));
                            
                            if (mysqlValue instanceof String && ((String) mysqlValue).isEmpty()) {
                                
                                if (mongoValue instanceof String && ((String) mongoValue).isEmpty()) {
                                    continue;
                                }
                            }
                            if (mysqlValue instanceof String && mysqlValue.equals(0) && mongoValue == null) {
                                
                                continue; 
                        }}

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

    private static Object findNestedMongoDBValue(Document doc, String[] fieldNames) {
        Object value = doc;
        for (String fieldName : fieldNames) {
            if (value instanceof Document) {
                value = ((Document) value).get(fieldName);
            } else {
                return null;             }
        }
        return value;
    }
    
    private static boolean shouldCompare(String mysqlFieldName) {
    	
    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("companyId", "name","previousName","sc_code","yearFounded", "companyType", "taxonomy.employees","launchDate", "profile.fundStatus","profile.fundSize", "profile.companyStatus", "contacts.registeredAddressFlag","contact.address", "contacts.country", "contacts.state", "contacts.city", "contacts.pinCode", "contacts.phoneNumber1", "contacts.phoneNumber2","contacts.faxNumber","contacts.emailId","businessDescription","profile.directoryFeature", "taxonomy.industryGroup","taxonomy.industry","taxonomy.subIndustry","contacts.district","closingDate","profile.minimum","profile.maximum","profile.investmentStage","taxonomy.areaOfInterest","taxonomy.global","taxonomy.continent","taxonomy.subcontinent","taxonomy.specialization","profile.dealTypes","profile.description","profile.dealValue","profile.stakeValue","profile.fundingReceived","profile.motherChildFund","profile.seedIncubation","profile.ventureCapital","profile.privateEquity","profile.investorEquity","cin","profile.indiaCoverage","profile.fundingStatus","profile.investorType","profile.fundType","profile.mcaStatus","profile.investmentType","profile.domicile","profile.denomination","taxonomy.sectorTags","contacts.mobileNumber","companyLogo","profile.primaryExchange","contacts.latitude","contacts.longitude","shortDescription","taxonomy.sectorTheme","taxonomy.businessModel","taxonomy.categoryCoverage","logoHeight","logoWidth","mcacheCompanyId","profile.incorporationDate","profile.fundRegistrationId","profile.financialAdded","profile.financialValidated","profile.channelPartnerFlag","profile.yearOfValuation","profile.valuationClass","profile.fundInvestorType","profile.digitalNativeCompany","profile.familyOfficeType","createdAt","listingDate","updatedAt"));
    	return columnsToCompare.contains(mysqlFieldName);
    	}
    private static String mapFieldName(String mysqlFieldName) {
        switch (mysqlFieldName) {
            case "companyid":
                return "companyId";
            case "companyname":
                return "name";
            case "previous_name":
                return "previousName";
            case "yearfounded":
                return "yearFounded";
            case "companyTypeFromMaster":
                return "companyType";
            case "numberofemployees":
                return "taxonomy.employees";
            case "launchdate":
                return "launchDate";
            case "fundStatusFromMaster":
                return "profile.fundStatus";
            case "fundsize":
                return "profile.fundSize";
            case "companyStatusFromMaster":
                return "profile.companyStatus";
            case "registered_address_flag":
                return "contacts.registeredAddressFlag";
            case "primaryaddressline1":
            case "primaryaddressline2":
            case "primaryaddressline3":
                return "contact.address";
            case "countryNameFromCoutryTable":
                return "contacts.country";
            case "stateNameFromStateTable":
                return "contacts.state";
            case "city":
                return "contacts.city";
            case "pincode":
                return "contacts.pinCode";
            case "phonenumber1":
                return "contacts.phoneNumber1";
            case "phonenumber2":
                return "contacts.phoneNumber2";
            case "faxnumber":
                return "contacts.faxNumber";
            case "emailid":
                return "contacts.emailId";
            case "businessdescription":
                return "businessDescription";
            case "directoryFeatureFromMaster":
                return "profile.directoryFeature";
            case "industryGroupNameFromIndustryGroupTable":
                return "taxonomy.industryGroup";
            case "industryFromIndustryTable":
                return "taxonomy.industry";
            case "subIndustryFromSubIndustryTable":
                return "taxonomy.subIndustry";
            case "district":
                return "contacts.district";
            case "closing_date":
                return "closingDate";
            case "minimum":
                return "profile.minimum";
            case "maximum":
                return "profile.maximum";
            case "stageInvestmentFromMaster":
                return "profile.investmentStage";
            case "areaofintrest":
                return "taxonomy.areaOfInterest";
            case "globalFromGlobalTable":
                return "taxonomy.global";
            case "ContinentNameFromContinentTable":
                return "taxonomy.continent";
            case "subContinentFromIndiaSubContinentTable":
                return "taxonomy.subcontinent";
            case "specialization":
                return "taxonomy.specialization";
            case "deal_types":
                return "profile.dealTypes";
            case "description":
                return "profile.description";
            case "deal_value":
                return "profile.dealValue";
            case "stake_value":
                return "profile.stakeValue";
            case "funding_recieved":
                return "profile.fundingReceived";
            case "mother_child_fund":
                return "profile.motherChildFund";
            case "seed_incubation":
                return "profile.seedIncubation";
            case "venture_capital":
                return "profile.ventureCapital";
            case "private_equity":
                return "profile.privateEquity";
            case "investor_equity":
                return "profile.investorEquity";
            case "corporate_identity_number":
                return "cin";
            case "india_coverage":
                return "profile.indiaCoverage";
            case "fundingStatusFromMaster":
                return "profile.fundingStatus";
            case "investorTypeFromMaster":
                return "profile.investorType";
            case "fund_type":
                return "profile.fundType";
            case "MCA_status":
                return "profile.mcaStatus";
            case "investment_type":
                return "profile.investmentType";
            case "domicile":
                return "profile.domicile";
            case "denomination":
                return "profile.denomination";
            case "sector_tags":
                return "taxonomy.sectorTags";
            case "mobile_number":
                return "contacts.mobileNumber";
            case "company_logo":
                return "companyLogo";
            case "primaryExchangeFromMaster":
                return "profile.primaryExchange";
            case "latitude":
                return "contacts.latitude";
            case "longitude":
                return "contacts.longitude";
            case "short_description":
                return "shortDescription";
            case "sector_theme":
                return "taxonomy.sectorTheme";
            case "business_model":
                return "taxonomy.businessModel";
            case "category_coverage":
                return "taxonomy.categoryCoverage";
            case "logo_height":
                return "logoHeight";
            case "logo_width":
                return "logoWidth";
            case "mcache_company_id":
                return "mcacheCompanyId";
            case "incorporationdate":
                return "profile.incorporationDate";
            case "fund_registration_id":
                return "profile.fundRegistrationId";
            case "financial_added":
                return "profile.financialAdded";
            case "financial_validated":
                return "profile.financialValidated";
            case "channel_partner_flag":
                return "profile.channelPartnerFlag";
            case "year_of_valuation":
                return "profile.yearOfValuation";
            case "valuationClassFromCode":
                return "profile.valuationClass";
            case "fundInvestorTypeFromMaster":
                return "profile.fundInvestorType";
            case "digital_native_company":
                return "profile.digitalNativeCompany";
            case "family_office_type":
                return "profile.familyOfficeType";
            case "created_at":
                return "createdAt";
            case "listing_date":
                return "listingDate";
            case "updated_at":
                return "updatedAt";
                     default:
                return mysqlFieldName;
        }
    }
}
