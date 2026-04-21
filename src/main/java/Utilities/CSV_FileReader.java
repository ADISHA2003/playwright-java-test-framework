package Utilities;

import Constants.FilePath;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSV_FileReader {


    public static Iterator<Object[]> readTestDataFromFile(String filePath) {
        List<Object[]> testData = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                List<String> data = new ArrayList<>();
                csvRecord.forEach(data::add);
                testData.add(data.toArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData.iterator();
    }

    public static void writeToCsvFile(String filePath , List<String[]> data){
        // write to csv file
        try (FileWriter fileWriter = new FileWriter(FilePath.DB_RESOURCES + filePath,true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            for (String[] record : data) {
                csvPrinter.printRecord((Object[]) record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCsvFileColumnVise(List<Integer> sqlRecord,List<Integer> mongoRecord,String filePath){
        int maxRows = Math.max(sqlRecord.size(), mongoRecord.size());

        String fileName = filePath;

        try (FileWriter writer = new FileWriter(FilePath.DB_RESOURCES + filePath)) {
            // Optionally, write header
            writer.append("sqlrecord,mongoRecord\n");

            // Write each row
            for (int i = 0; i < maxRows; i++) {
                String sqlrecord = i < sqlRecord.size() ? sqlRecord.get(i).toString() : "";
                String MongoRecord = i < mongoRecord.size() ? mongoRecord.get(i).toString() : "";

                // Create the CSV row
                String row = String.join(",", sqlrecord, MongoRecord) + "\n";
                writer.append(row);
            }
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToCsvFileColumnViselist(List<String> actual_default_columns,List<String> expected_Default_Columns,String filePath){
        int maxRows = Math.max(actual_default_columns.size(), expected_Default_Columns.size());

        String fileName = filePath;

        try (FileWriter writer = new FileWriter(FilePath.DB_RESOURCES + filePath)) {
            // Optionally, write header
            writer.append("sqlrecord,mongoRecord\n");

            // Write each row
            for (int i = 0; i < maxRows; i++) {
                String sqlrecord = i < actual_default_columns.size() ? actual_default_columns.get(i).toString() : "";
                String MongoRecord = i < expected_Default_Columns.size() ? expected_Default_Columns.get(i).toString() : "";

                // Create the CSV row
                String row = String.join(",", sqlrecord, MongoRecord) + "\n";
                writer.append(row);
            }
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> readIntegerTestDataFromFileUnderColumn(String filePath,String column) {
        List<Integer> testData = new ArrayList<>();
        try (FileReader fileReader = new FileReader(FilePath.DB_RESOURCES+filePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                if(!csvRecord.get(column).isEmpty())
                    testData.add(Integer.parseInt(csvRecord.get(column)));
            }
        } catch (IOException e) {
            e.printStackTrace();}

        return testData;
    }

//    public static void main(String []arg){
//        List<Integer> testData= readIntegerTestDataFromFileUnderColumn("Test_Collection_Difference/fund_Results.csv","mongoRecord");
//        System.out.println(testData);
//    }

    public static void writeToCsvFileColumnVise(List<Integer> sqlRecord,List<Integer> mongoRecord,String filePath,String col1, String col2){
        int maxRows = Math.max(sqlRecord.size(), mongoRecord.size());

        String fileName = filePath;

        try (FileWriter writer = new FileWriter(FilePath.DB_RESOURCES + filePath)) {
            // Optionally, write header
            writer.append(col1+","+col2+"\n");

            // Write each row
            for (int i = 0; i < maxRows; i++) {
                String sqlrecord = i < sqlRecord.size() ? sqlRecord.get(i).toString() : "";
                String MongoRecord = i < mongoRecord.size() ? mongoRecord.get(i).toString() : "";

                // Create the CSV row
                String row = String.join(",", sqlrecord, MongoRecord) + "\n";
                writer.append(row);
            }
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
