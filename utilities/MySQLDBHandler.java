package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySQLDBHandler {
 
    private static final String DB_URL = "jdbc:mysql://13.232.28.95:3306/dev_appdb";
    private static final String USER = "devappdb";
    private static final String PASSWORD = "Ap!Rt9^p1k";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}