package DatabaseUtilities_Actions;

import Constants.SQL_Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDBHandler {

    public String env= "DEV";
    public MySQLDBHandler(String env) {
        this.env= env;
    }
    public Connection getConnection() {
        try {
            String dbEnv = this.env;
            Connection connection=null;
            if(dbEnv.equalsIgnoreCase("dev")){
                System.out.println("Connecting to DEV DB");
                connection = DriverManager.getConnection(SQL_Constants.DEV_DB_URL, SQL_Constants.DEV_USER,
                        SQL_Constants.DEV_PASSWORD);
                return connection;
            }else if(dbEnv.equalsIgnoreCase("prod")){
                System.out.println("Connecting to PROD DB");
             connection = DriverManager.getConnection(SQL_Constants.PROD_DB_URL, SQL_Constants.PROD_USER,
                    SQL_Constants.PROD_PASSWORD);}

            return connection;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ResultSet getResultSetMetaData(Connection connection, String sqlQuery){
        PreparedStatement mysqlStmt = null;
        try {
            mysqlStmt = connection.prepareStatement(sqlQuery);

        ResultSet mysqlResultSet = mysqlStmt.executeQuery();
        return mysqlResultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getQueryResultSet(String query){
        Connection connection = getConnection();
        try {
            // Establish the connection

            // Check if the connection is valid (timeout is 5 seconds)
            if (connection.isValid(5)) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while establishing connection: " + e.getMessage());
        }
        return getResultSetMetaData(connection,query);
    }

    public List<String> getValuesUnderColumn(ResultSet resultset,String columnName){
        // List to store all values of the specific column
        List<String> columnValues = new ArrayList<>();
        String columnValue = null;
        try {
        // Process the result set
             while (resultset.next()) {
            // Get the value of the specific column (e.g., "column_name")
                 columnValue = resultset.getString(columnName);
                 columnValues.add(columnValue);
            }
        }catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Add the column value to the list
        return columnValues;
        }

    public List<Integer> getLongValuesUnderColumn(ResultSet resultset,String columnName){
        // List to store all values of the specific column
        List<Integer> columnValues = new ArrayList<>();
        Integer columnValue = null;
        try {
            // Process the result set
            while (resultset.next()) {
                // Get the value of the specific column (e.g., "column_name")
                columnValue = resultset.getInt(columnName);
                columnValues.add(columnValue);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the column value to the list
        return columnValues;
    }

    public Long getCountOfResultUnderColumn(ResultSet resultset,String columnName){
        String count = getValuesUnderColumn(resultset,columnName).get(0);
        return Long.parseLong(count);

    }
}