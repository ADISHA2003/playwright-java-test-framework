package VCI.Data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;

public class Financial_snapshot {
	
	@Test
	public static void checkMySQLRecordsInMongoDB() {
		Connection mysqlConnection = null;
        try {
            mysqlConnection = MySQLDBHandler.getConnection();
            
	        // Iterate over MySQL tables and their corresponding entity names
	        Map<String, String> mysqlTables = new HashMap<>();
	        mysqlTables.put("half_yearly_results", "HalfYearlyResults");
	        mysqlTables.put("quarterly_results", "QuarterlyResults");
	        mysqlTables.put("nine_months_results", "NineMonthsResults");
	     
	        
	        for (Map.Entry<String, String> entry : mysqlTables.entrySet()) {
	            String tableName = entry.getKey();
	            String entityName = entry.getValue();

	            // Get primary keys from MySQL
	            Set<Integer> mysqlPrimaryKeys = getMySQLPrimaryKeys(mysqlConnection, tableName);

	            // Get primary keys from MongoDB for the specific entity name
	            MongoDBHandler mongoDBConnection = new MongoDBHandler();
	            MongoDatabase database = mongoDBConnection.getDatabase();
	            MongoCollection<Document> mongoCollection = database.getCollection("SnapshotResultsMongoDb");
	            Bson filter = eq("entityType", entityName);
	            Set<Integer> mongoPrimaryKeys = getMongoPrimaryKeys(mongoCollection, filter);
                long totalCountMongoDB = getTotalCountForEntity(mongoCollection, entityName);

	            // Check if all MySQL primary keys exist in MongoDB for the specific entity name
	            if (mongoPrimaryKeys.containsAll(mysqlPrimaryKeys)) {
	                System.out.println("All records from MySQL table '" + tableName + "' with entity name '" + entityName + "' are available in MongoDB.");
	            } else {
	                // Identify missing primary keys
	                Set<Integer> missingPrimaryKeys = new HashSet<>(mysqlPrimaryKeys);
	                missingPrimaryKeys.removeAll(mongoPrimaryKeys);
	                System.out.println("Missing records in MongoDB for table: " + tableName + " with entity name '" + entityName + "Missing primary keys: " + missingPrimaryKeys);
	                System.out.println("Total count of '" + entityName + "' in MongoDB: " + totalCountMongoDB);
                    System.out.println();
                }

                
                storeTotalCountInMySQL(mysqlConnection, entityName, totalCountMongoDB);
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        
        
	}
	
	private static long getTotalCountForEntity(MongoCollection<Document> mongoCollection, String entityName) {
	    // Construct the aggregation pipeline with Bson objects
	    List<Bson> pipeline = Arrays.asList(
	        Aggregates.match(eq("entityType", entityName)),
	        Aggregates.group(null, Accumulators.sum("count", 1))
	    );

	    // Execute the aggregation pipeline and get the result
	    Document result = mongoCollection.aggregate(pipeline).first();
	    if (result != null && result.containsKey("count")) {
	        Object countObject = result.get("count");
	        if (countObject instanceof Integer) {
	            return ((Integer) countObject).longValue();
	        } else if (countObject instanceof Long) {
	            return (Long) countObject;
	        }
	    }
	    return 0L; // If count not found, return 0
	}

	private static void storeTotalCountInMySQL(Connection mysqlConnection, String entityName, long totalCount) {
	    // No need for SQL insert statement as per the requirement
	    System.out.println("Total count of '" + entityName + "' in MongoDB: " + totalCount);
	}
	private static Set<Integer> getMySQLPrimaryKeys(Connection mysqlConnection, String tableName) throws SQLException {
        Set<Integer> primaryKeys = new HashSet<>();
        DatabaseMetaData metaData = mysqlConnection.getMetaData();
        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            rs.next(); // Move cursor to the first column
            String primaryKeyColumn = rs.getString("COLUMN_NAME"); // Get the column name
            String sqlQuery = "SELECT " + primaryKeyColumn + " FROM " + tableName;
            try (Statement stmt = mysqlConnection.createStatement();
                 ResultSet primaryKeysResultSet = stmt.executeQuery(sqlQuery)) {
                while (primaryKeysResultSet.next()) {
                    primaryKeys.add(primaryKeysResultSet.getInt(primaryKeyColumn));
                }
            }
        }
        return primaryKeys;
    }

	private static Set<Integer> getMongoPrimaryKeys(MongoCollection<Document> mongoCollection, Bson filter) {
        Set<Integer> primaryKeys = new HashSet<>();
        try (MongoCursor<Document> cursor = mongoCollection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Assuming your primary key field is named "primaryKey" in MongoDB
                Integer primaryKey = doc.getInteger("mySQLKey");
                primaryKeys.add(primaryKey);
            }
        }
        return primaryKeys;
    }
}


