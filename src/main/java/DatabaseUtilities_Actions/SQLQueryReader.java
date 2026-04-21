package DatabaseUtilities_Actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SQLQueryReader {

    public String readSqlFromFile(String filePath) {
	        StringBuilder stringBuilder = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                stringBuilder.append(line);
	                stringBuilder.append("\n");
	            }return stringBuilder.toString();
	        }
			catch(IOException io){
				return null;
			}

    }
}

