package Constants;

public class MongoConstants {

    public static final String MONGODB_URI = System.getenv("MONGODB_URI");
    public static final String DEV_MONGO_URI = System.getenv("DEV_MONGO_URI");
    public static final String PROD_MONGO_URI = System.getenv("PROD_MONGO_URI");
    public static final String DATABASE_NAME = "vci";
}
