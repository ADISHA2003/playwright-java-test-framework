package tests;
import Utilities.CSV_FileReader;
import com.mongodb.client.FindIterable;
import io.qameta.allure.Allure;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test_Entity_Mapping extends BaseDBTest{


    @Test(description = "As a user I can verify entity mapping of investor between MySQL and MongoDB", dataProvider = "InvestorCoverageTestDataProvider")
    public void verfiy_Investor_Entity_Mapping(String type, String sql_table, String columnName,String sql_condition, String mongo_collection,String condition, String mongo_field, String mongo_value,String mongoId) {
        // Test implementation goes here

        Allure.step("Given I have access to both MySQL and MongoDB databases");

        Allure.step("When I execute query and fetch the count of documents from both databases based on the provided conditions");
        long countOfSqlRecords = getCountOfDocumentsSQL(sql_table,sql_condition,columnName);
        long countMongoRecord= getCountofMongoRecords(mongo_collection,condition,mongo_field,mongo_value);
        System.out.println(countMongoRecord);
        System.out.println(countOfSqlRecords);

        Allure.step("Then I compare the counts from MySQL and MongoDB to verify entity mapping accuracy");
        Assert.assertEquals(countMongoRecord,countOfSqlRecords,"Mismatch in counts: MySQL=" + countOfSqlRecords + ", MongoDB=" + countMongoRecord);

//        String [] str = {type,sql_table,sql_condition,mongo_collection,mongo_field,mongo_value,String.valueOf(count_CompanySqlRecord),String.valueOf(countMongoRecord)};
//        ArrayList<String[]> data = new ArrayList<String[]>();
//        data.add(str);
//        CSV_FileReader.writeToCsvFile("Test_Coverage"+ File.separator+"CoverageResults.csv", data);
        List<Integer> list_CompanySqlRecord=mySQLDBHandler.getLongValuesUnderColumn(mysqlResultSet,columnName);

        List<Integer> listMongoRecord = new ArrayList<>();
        FindIterable<Document> documents = mongoCollection.find(getMongoDocs(mongo_field, condition,mongo_value));
        if (documents != null) {
            for (Document doc : documents) {
                listMongoRecord.add(doc.getInteger(mongoId));
            }
            // Get the list of integers from the 'numbers' field.
        }
        int i=0;
        System.out.println("SQL Records: "+list_CompanySqlRecord.size());
        System.out.println("Mongo Records: "+listMongoRecord.size());
        List<Integer> commonSql = new ArrayList<>(list_CompanySqlRecord);
        List<Integer> commonMongo = new ArrayList<>(listMongoRecord);
        commonSql.removeAll(listMongoRecord);
        commonMongo.removeAll(list_CompanySqlRecord);
        System.out.println("SQL Records: "+commonSql);
        System.out.println("Mongo Records: "+commonMongo);

        CSV_FileReader.writeToCsvFileColumnVise(commonSql,commonMongo,"Test_Collection_Difference"+ File.separator+type+"_"+sql_table+"_Results.csv");

    }


    @DataProvider(name = "InvestorCoverageTestDataProvider")
    public Iterator<Object[]> coverageTestDataProvider() {
        return CSV_FileReader.readTestDataFromFile("Test_Coverage"+ File.separator+"CoverageTestData.csv");
    }

    @Test()
    public void verfiy_Company_Entity_Mapping() {
        // Test implementation goes here
    }

    @Test()
    public void verfiy_Deals_Entity_Mapping() {
        // Test implementation goes here
    }

    @Test()
    public void verfiy_Service_Provider_Entity_Mapping() {
        // Test implementation goes here
    }

    @Test()
    public void verfiy_Professionals_Entity_Mapping() {
        // Test implementation goes here
    }

    @Test()
    public void verfiy_Users_Entity_Mapping() {
        // Test implementation goes here
    }


}
