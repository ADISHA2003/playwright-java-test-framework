package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SQLQueryReader {
	  public static String readSqlFromFile(String filePath) throws IOException {
	        StringBuilder stringBuilder = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                stringBuilder.append(line);
	                stringBuilder.append("\n");
	            }
	        }
	        return stringBuilder.toString();
	    }
}

