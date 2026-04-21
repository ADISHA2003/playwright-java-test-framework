package DatabaseUtilities_Actions;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

public class DbHelper {
    public String getValueFromMongo(String collection,
                                    String key,
                                    String condition,
                                    String keyValue,
                                    String dataPoint) {

        MongoDBHandler mongoConnection = new MongoDBHandler("PROD");
        MongoCollection<Document> mongoCollection = mongoConnection
                .getDatabase()
                .getCollection(collection);

        System.out.println(mongoCollection.countDocuments());
        Document documents = mongoCollection.find(getMongoDocs(key, condition, keyValue)).first();
        assert documents != null;
        System.out.println(documents.toJson());
        return documents.get(dataPoint).toString();
    }

    public String getCompanyScreenerS3Url(String userEmail) {
    MongoDBHandler mongoConnection = new MongoDBHandler("PROD");
    MongoCollection<Document> mongoCollection = mongoConnection
            .getDatabase()
            .getCollection("jobMetadata"); // confirm this collection name

    Document query = new Document("userEmail", userEmail)
            .append("entityName", "COMPANY_SCREENER")
            .append("status", "REPORT_GENERATION_COMPLETED");

    Document doc = mongoCollection.find(query)
            .sort(new Document("createdAt", -1)) // latest job
            .first();

    if (doc != null && doc.containsKey("s3Url")) {
        return doc.getString("s3Url");
    }
    return null;
}

    private Bson getMongoDocs(String mongo_field, String condition, String mongo_value) {
        switch (condition) {
            case "eq":
                System.out.println(mongo_field + " " + condition + " " + mongo_value);
                return Filters.eq(mongo_field, mongo_value);
            case "ex":
                return Filters.exists(mongo_field);
            case "ex_and":
                return new Document(mongo_field, new Document("$exists", true).append("$eq", mongo_value));
            case "in":
                return new Document(mongo_field, new Document("$in", Arrays.asList(mongo_value.split(","))));
            case "distinct":
                return new Document(mongo_field, new Document("$ne", mongo_value));
            case "bool":
                return new Document(mongo_field, Boolean.parseBoolean(mongo_value));
                default:
                return Filters.eq(mongo_field, mongo_value);
        }
    }
}
