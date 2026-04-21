package Utilities;
import Constants.SQL_Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Arrays;
import java.util.Map;

public class Postgresql_helper_service {

    public String getData(String query,String columnName,String env){


        try {
            String postgresURl=!env.equalsIgnoreCase("DEV") ? SQL_Constants.UAT_POSTGRESQL : SQL_Constants.DEV_POSTGRESQL;
            String user=!env.equalsIgnoreCase("DEV") ? SQL_Constants.UAT_POSTGRESQL_USER : SQL_Constants.DEV_POSTGRESQL_USER;
            String password=!env.equalsIgnoreCase("DEV") ? SQL_Constants.UAT_POSTGRESQL_PASSWORD : SQL_Constants.DEV_POSTGRESQL_PASSWORD;
            Connection conn = DriverManager.getConnection(postgresURl, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);


            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    byte[] fileBytes = rs.getBytes(columnName);
                    System.out.println(Arrays.toString(fileBytes));

                    // Optionally convert to string (if text data)
                    String content = new String(fileBytes, StandardCharsets.UTF_8);
                    System.out.println("Status: " + content);
                    return content;
                } else {
                    System.out.println("No record found for query: " + query);
                    return null;
                }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
