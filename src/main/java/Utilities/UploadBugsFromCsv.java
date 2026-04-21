package Utilities;

import Constants.FilePath;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;

public class UploadBugsFromCsv {

        private static final String JIRA_URL = System.getenv("JIRA_URL"); // Replace with your Jira instance URL
        private static final String JIRA_USERNAME = System.getenv("JIRA_USERNAME"); // Replace with your Jira username
        private static final String JIRA_API_TOKEN = System.getenv("JIRA_API_TOKEN"); // Replace with your Jira API token
        private static final String PARENT_ISSUE_KEY = System.getenv("PARENT_ISSUE_KEY"); // Replace with your parent issue key
        private static final String CSV_FILE_PATH = FilePath.BUGS+"BETA_BUGS"+ File.separator+"DataTable.csv"; // Path to your CSV file
        private static final String outputFilePath = FilePath.BUGS+"BETA_BUGS"+ File.separator+"output.csv"; // Path to your CSV file
        public static void main(String[] args) {


                // Create request
                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");
                String RESOURCE_FOLDER = FilePath.BUGS;
                String FOLDER = "BugsTemplate";
                String FILE = "bugTemplate.json";
                String template = "templateOpen";
                String requestBody = new String();
                try {
                        FileReader fileReader = new FileReader(CSV_FILE_PATH);
                        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                        for (CSVRecord csvRecord : csvParser) {
                                requestBody = JsonReaderService.readTest_JsonFile(
                                        RESOURCE_FOLDER,
                                        FOLDER,
                                        FILE,
                                        template);
                                String Component = csvRecord.get("Component");
                                System.out.println(Component);
                                String summary = csvRecord.get("Bug");

                                System.out.println(summary);
                                String labels = (Component).replaceAll(" ","_");
                                System.out.println(labels);
                                summary+=labels+"_"+summary;
                                if(summary.length()>255){
                                        summary = summary.substring(0, 254);
                                }
                                requestBody = requestBody.replaceAll("<summary>", summary);
                                requestBody = requestBody.replaceAll("<description>", summary);
                                requestBody = requestBody.replaceAll("<labels>", labels);


                request.body(requestBody);
                System.out.println(requestBody);
                // Send POST request
                Response response = request.auth().preemptive().basic(JIRA_USERNAME,JIRA_API_TOKEN).post(JIRA_URL);

                // Print response
                                System.out.println(response.asString());
                System.out.println(response.path("key").toString());
                writeIssueKeyToCsv(response.path("key").toString());
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

//        public static String replaceValuesInTemplate(String template){
//
//        }

        public static void writeIssueKeyToCsv(String key){
                try{
                        FileWriter fileWriter = new FileWriter(outputFilePath, true);
                        fileWriter.append(key).append("\n");
                        fileWriter.flush();
                        System.out.println("Values written to CSV file successfully.");
                }catch (Exception e){
                        e.printStackTrace();
                }
        }



    }
