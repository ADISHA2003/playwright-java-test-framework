package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelperService {


    public static void main(String[] args) {
        //getFileContent("src/test/java/WebTestSuite/FeatureTests/SampleTest.java");
        updateFileContent("src/test/java/Api_TestSuite/GlobalSearch2_Test.java","package WebTestSuite.FeatureTests;\n" +
                "\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" +
                "public class SampleTest extends BaseUI_Test {\n" +
                "\n" +
                "\n" +
                "    @Test\n" +
                "    public void demo1(){\n" +
                "        FAILURE_MSG = \"Page title is not as expected\";\n" +
                "        SUCCESS_MSG = \"Page title is as expected\";\n" +
                "        PAGE_TITLE = \"Googles\";\n" +
                "        String pageTitle  = demoPage.launchUrl(\"https://www.google.com\");\n" +
                "\n" +
                "        assert_report.AssertEquals(logger,extentTest,pageTitle,PAGE_TITLE,FAILURE_MSG,SUCCESS_MSG);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}");
    }
    // the method should return the content of the file
    public static String getFileContent(String filePath){
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(content);
        return "";
    }

    public static void updateTestFileContent(String filePath, String content,String globalComponent){
        String testContent = new String();
        try {
            // Check if the file exists
            if (!Files.exists(Paths.get(filePath))) {
                // Create the file
                Files.createFile(Paths.get(filePath));
                testContent = globalComponent.substring(0,globalComponent.length()-2)+content+"}";
            }else{
                testContent = new String(Files.readAllBytes(Paths.get(filePath)));
                if(testContent.isEmpty()){
                    testContent = globalComponent;
                }
                if(testContent.contains("}"))
                    testContent = testContent.substring(0,testContent.length()-2)+content+"}";
                else testContent = content;
            }
            System.out.println("testcontent"+testContent);
            Files.write(Paths.get(filePath), testContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFileContent(String filePath, String content){
        try {
            // Check if the file exists
            if (!Files.exists(Paths.get(filePath))) {
                // Create the file
                Files.createFile(Paths.get(filePath));
            }
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createFolder(String folderPath,String folderName){
        try {
            Files.createDirectories(Paths.get(folderPath+folderName));
            return folderPath+folderName+ File.separator;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateFileReplaceString(String filePath,String findString,String replaceString){
        try {
            // Check if the file exists
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            fileContent = fileContent.replace(findString,replaceString);
            Files.write(Paths.get(filePath), fileContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
