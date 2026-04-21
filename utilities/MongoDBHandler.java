package utilities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class MongoDBHandler {
	   private MongoClient mongoClient;
	    private MongoDatabase database;

	    // MongoDB URI and database name
	    private static final String MONGODB_URI = "mongodb+srv://vccedge:vccedge#2wert@cluster0.epsk1yv.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
	    private static final String DATABASE_NAME = "vci";

	    public MongoDBHandler() {
	        this.mongoClient = new MongoClient(new MongoClientURI(MONGODB_URI));
	        this.database = mongoClient.getDatabase(DATABASE_NAME);
	        
	    }
	    public MongoDatabase getDatabase() {
	        return this.database;
	    }	    
	    
	    public void close() {
	        this.mongoClient.close();
	    }
	}
