package DatabaseUtilities_Actions;

public class DataMigrationEnrichmentService {

    public String getColumnJsonNodeMapping(String mysqlFieldName){
        String mongoJsonMapping = new String();
        mongoJsonMapping+=String.valueOf(mysqlFieldName.charAt(0));
        for(int index=1;index<mysqlFieldName.length();index++){
            if(mysqlFieldName.charAt(index-1)=='_'){
                mongoJsonMapping+=String.valueOf(mysqlFieldName.charAt(index));
            }
        }
        return mongoJsonMapping;
    }
}
