package Utilities;
import java.io.*;
import java.nio.file.*;

public class CsvToHtmlFileCreation_Migration {


    public static void convertToHtmlFile(String filepath) {
            String inputCsvPath = filepath; // <-- change if needed
            String outputHtmlPath = System.getProperty("user.dir")+File.separator+"target/report.html";

            try (BufferedReader br = new BufferedReader(new FileReader(inputCsvPath));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(outputHtmlPath))) {

                bw.write("<html><head><title>CSV Report</title></head><body>");
                bw.write("<h2>CSV Report</h2><table border='1' cellpadding='5' cellspacing='0'>");

                String line;
                boolean isHeader = true;
                while ((line = br.readLine()) != null) {
                    String[] cells = line.split(",");

                    bw.write("<tr>");
                    for (String cell : cells) {
                        if (isHeader) {
                            bw.write("<th>" + cell.trim() + "</th>");
                        } else {
                            bw.write("<td>" + cell.trim() + "</td>");
                        }
                    }
                    bw.write("</tr>");
                    isHeader = false;
                }

                bw.write("</table></body></html>");
                System.out.println("HTML report generated at: " + outputHtmlPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


