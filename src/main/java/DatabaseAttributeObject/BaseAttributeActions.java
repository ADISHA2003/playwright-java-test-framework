package DatabaseAttributeObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Projections;
import org.bson.BsonType;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;


import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class BaseAttributeActions {

    public String getQueryWithReplacement(String query, String replacement) {
        return query.replaceAll("value", replacement);
    }

    public ResultSet getResultSet(String query) {
        return null;
    }

    public static String convertCamelCaseToUnderscore(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                // If it's not the first character, prepend an underscore
                if (i != 0) {
                    result.append("_");
                }
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public HashMap<String,HashMap<String, Object>> getSqlToMap(String key, ResultSet mysqlResultSet) {
        HashMap<String,HashMap<String, Object>> sqlTableMap= new HashMap<String,HashMap<String, Object>>();
        try {
            ResultSetMetaData md = mysqlResultSet.getMetaData();
            int columns = md.getColumnCount();

            while (true) {
                if (!mysqlResultSet.next()) break;
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (int i = 1; i <= columns; ++i) {
                    try {
                        row.put(md.getColumnName(i), mysqlResultSet.getObject(i));
                    } catch (DateTimeException dte) {
                    }sqlTableMap.put(String.valueOf(key),row);

                }System.out.println(sqlTableMap.entrySet());return sqlTableMap;
            }
        } catch (SQLException e) {
            System.out.println("Error in mapping sql to pojo");
            return null;
        }
        return null;
    }

public boolean verifyKeyTypes(String collectionName,MongoCollection<Document> collection) {
        HashMap<String,HashSet<BsonType>> FieldType = new HashMap<String,HashSet<BsonType>>();
    int pageNumber=1;
    int pageSize = 100000;
    List<Document> documentList;
    do{
        int skip = (pageNumber - 1) * pageSize;
        FindIterable<Document> documents = collection.find()
                    .skip(skip)
                    .limit(pageSize);
       documentList = documents.into(new ArrayList<>());
        System.out.println("Page number: " + pageNumber+" "+documentList.size());
        FieldType= checkKeyTypes(collectionName,FieldType,documentList);
        pageNumber++;
    }while(documentList.size() != pageSize);
    System.out.println(FieldType.entrySet());
    for(String key : FieldType.keySet()){
        if(FieldType.get(key).size()>1){
            System.out.println("Key: "+key+" has multiple types: "+FieldType.get(key));
            return false;
        }
    }
return true;
}

public HashMap<String,HashSet<BsonType>> checkKeyTypes( String parent,HashMap<String,HashSet<BsonType>> FieldType,List<Document> documents) {
    for (Document document : documents) {
        traverseMongoDocuments(parent,FieldType,document);
    }return FieldType;
}

    public HashMap<String,HashSet<BsonType>> traverseMongoDocuments(String parent, HashMap<String,HashSet<BsonType>>FieldType,Document document){

            if (document != null) {
                for (Map.Entry<String, Object> entry : document.entrySet()) {
                    HashSet<BsonType> typeSet = new HashSet<BsonType>();
                    if ((document.get(entry.getKey()) instanceof Document))
                        FieldType.putAll(traverseMongoDocuments(entry.getKey(),FieldType,(Document) document.get(entry.getKey())));

                    // Convert documents to a list to count them
                    else if(FieldType.containsKey(parent+"."+entry.getKey())){
                        System.out.println("fieldexists"+FieldType.entrySet());
                        typeSet.addAll(FieldType.get(parent+"."+entry.getKey()));
                    }
                    typeSet.add(determineBsonType(entry.getValue()));
                    FieldType.put(parent+"."+entry.getKey(),typeSet);
                }
            }return FieldType;
    }

    private static BsonType determineBsonType(Object value) {
        if (value instanceof String) {
            return BsonType.STRING;
        } else if (value instanceof Integer) {
            return BsonType.INT32;
        } else if (value instanceof Long) {
            return BsonType.INT64;
        } else if (value instanceof Double) {
            return BsonType.DOUBLE;
        } else if (value instanceof Boolean) {
            return BsonType.BOOLEAN;
        } else if (value instanceof Document) {
            return BsonType.DOCUMENT;
        } else if (value instanceof org.bson.types.ObjectId) {
            return BsonType.OBJECT_ID;
        } else if (value instanceof java.util.Date) {
            return BsonType.DATE_TIME;
        } else if (value instanceof byte[]) {
            return BsonType.BINARY;
        } else if (value instanceof java.util.List) {
            return BsonType.ARRAY;
        } else {
            return BsonType.UNDEFINED; // Use UNDEFINED for unrecognized types
        }
    }

    public Document getMongoFirstDocument(MongoCollection<Document> mongoCollection) {
        return mongoCollection.find().first();
    }

    public Document getMongoFirstDocument(String node, String key,MongoCollection<Document> mongoCollection){
        return mongoCollection.find(eq(node,Integer.parseInt(key))).first();
    }

    public String convertUnderScoreFormatToCamelcase(String underScroreString){
        boolean flag = false;
        String camelCaseString = new String();
        for (int i = 0; i < underScroreString.length(); i++){
            char ch = underScroreString.charAt(i);
            if(ch=='_'){flag = true; continue;}
            if(flag){
                camelCaseString = camelCaseString + Character.toUpperCase(ch);
                flag = false;}
            else
            camelCaseString = camelCaseString + ch;
        }
        System.out.println(camelCaseString);
        return camelCaseString;
    }

    public boolean verifySqlMongoValues(String node, HashMap<String,HashMap<String,Object>> mysqlMap, Document companyMongo) {
        boolean flag = false;
        for(String primaryId : mysqlMap.keySet()) {
            verifyMigrationLogic(node,primaryId,mysqlMap,companyMongo);

        }
        return flag;
    }

    public boolean verifyMigrationLogic(String node, String key, HashMap<String,HashMap<String,Object>> mysqlMap, Document docMongo){

        boolean flag = false;
        HashMap<String, HashSet<BsonType>> FieldType = traverseMongoDocuments(node, docMongo);
        if (FieldType.size() == mysqlMap.size()) {
            for (String mongoNode : FieldType.keySet()) {
                String sqlField = convertCamelCaseToUnderscore(node.split(".")[1]);
                if (mysqlMap.containsKey(sqlField)){

                    flag = true;
                } else if(mysqlMap.get(sqlField).isEmpty()){
                    flag=true;
                }else{
                    System.out.println("columns not matching" + mongoNode + " " + sqlField);
                    flag = false;
                    break;
                }

            }
            return flag;
        } else {
            System.out.println("size is not same");
            return flag;
        }
    }


    public HashMap<String,HashSet<BsonType>> traverseMongoDocuments(String parent,Document document){
        HashMap<String,HashSet<BsonType>>FieldType = new HashMap<String,HashSet<BsonType>>();
        if (document != null) {
            for (Map.Entry<String, Object> entry : document.entrySet()) {
                HashSet<BsonType> typeSet = new HashSet<BsonType>();
                if ((document.get(entry.getKey()) instanceof Document))
                    FieldType.putAll(traverseMongoDocuments(entry.getKey(),FieldType,(Document) document.get(entry.getKey())));

                    // Convert documents to a list to count them
                else if(FieldType.containsKey(parent+"."+entry.getKey())){
                    System.out.println("fieldexists"+FieldType.entrySet());
                    typeSet.addAll(FieldType.get(parent+"."+entry.getKey()));
                }
                typeSet.add(determineBsonType(entry.getValue()));
                FieldType.put(parent+"."+entry.getKey(),typeSet);
            }
        }return FieldType;
    }


}
