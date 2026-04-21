package DatabaseUtilities_Actions;

import Constants.MongoConstants;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MongoDBHandler {
	   private MongoClient mongoClient;
	    private MongoDatabase database;

	    // MongoDB URI and database name
	    private static final String MONGODB_URI = MongoConstants.DEV_MONGO_URI;
		private static final String PROD_MONGO_URI = MongoConstants.PROD_MONGO_URI;
		private static final String DATABASE_NAME =MongoConstants.DATABASE_NAME;

//		public MongoDBHandler(String URI){
//			CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("Pojos.entityMongo").build();
//
//				CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
//						MongoClientSettings.getDefaultCodecRegistry(),
//						CodecRegistries.fromProviders(pojoCodecProvider)
//				);
//
//				MongoClientSettings settings = MongoClientSettings.builder()
//						.applyConnectionString(new ConnectionString(URI))
//						.codecRegistry(pojoCodecRegistry)
//						.build();
//
//				this.database = MongoClients.create(settings).getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
//
//		}

	public MongoDBHandler(String env){
		if(env.equals("DEV"))
			this.database = MongoClients.create(MONGODB_URI).getDatabase(DATABASE_NAME);
		else
			this.database = MongoClients.create(PROD_MONGO_URI).getDatabase(DATABASE_NAME);

	}

	    public MongoDatabase getDatabase() {
	        return this.database;
	    }	    
	    
	    public void close() {
	        this.mongoClient.close();
	    }

	}
