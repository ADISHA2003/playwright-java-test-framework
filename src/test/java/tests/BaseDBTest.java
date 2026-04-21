package tests;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;


public class BaseDBTest extends BaseUI_Test {

    public MongoCollection<Document> mongoCollection;

    protected String sqlCountQuery = "select count(columnName) as count   from table condition";
    public String getCountQuery(String sql_table, String sql_condition, String columnName){
        System.out.println(sql_table);
        System.out.println(sql_condition);

        if(columnName.isEmpty()){
            sqlCountQuery = sqlCountQuery.replace("columnName", "*");
        }
        else{
            sqlCountQuery = sqlCountQuery.replace("columnName", columnName);
        }
        sqlCountQuery = sqlCountQuery.replace("table", sql_table);
        sqlCountQuery = sqlCountQuery.replace("condition", sql_condition);
        System.out.println(sqlCountQuery);
        return sqlCountQuery;
    }

    public long getCountOfDocumentsSQL(String sql_table, String sql_condition, String columnName){
        mysqlResultSet = mySQLDBHandler.getQueryResultSet(getCountQuery(sql_table,sql_condition,columnName));
        return mySQLDBHandler.getCountOfResultUnderColumn(mysqlResultSet,"count");

    }

    public long getCountofMongoRecords(String collection,String condition, String mongo_field, String mongo_value){
        mongoCollection = mongoDBConnection.getDatabase().getCollection(collection);
        long countMongoRecord=0;
        if(condition.equals("distinct")){
            countMongoRecord = mongoCollection.distinct(mongo_field,getMongoDocs(mongo_field,condition,mongo_value),Document.class).into(new ArrayList<>()).size();
        }
        else  countMongoRecord = mongoCollection.countDocuments(getMongoDocs(mongo_field,condition,mongo_value));
        return countMongoRecord;
    }

    protected Document getMongoDocs(String mongo_field, String condition, String mongo_value){
        Document document = new Document();
        switch(condition){
            case "eq":document.append(mongo_field, mongo_value);return document;
            case "ex":return new Document(mongo_field, new Document("$exists", mongo_value));
            case "ex_and":return new Document(mongo_field, new Document("$exists", true).append("$eq", mongo_value));
            case "in": return new Document(mongo_field, new Document("$in", Arrays.asList(mongo_value.split(","))));
            case "distinct":return new Document(mongo_field, new Document("$ne", mongo_value));
            case "bool": return new Document(mongo_field, Boolean.parseBoolean(mongo_value));
            default:document.append(mongo_field, mongo_value);return document;
        }
    }
}
