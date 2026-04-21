package Utilities;

import Constants.FilePath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertyFileReaderService {

    // to read data from properties file
    public String readZephyrProperty(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(FilePath.ZEPHYR_PROPERTIES));
            return prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readProperty(String PATH,String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(PATH));
            return prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String updateExistingPropertyValue(String PATH, String key, String value) {
        Properties prop = new Properties();
        try {
            File file = new File(PATH);
            FileInputStream fileInputStream = new FileInputStream(file);
            prop.load(fileInputStream);
            prop.setProperty(key, value);
            prop.store(new FileOutputStream(file), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToAllureEnvFile(String ALLURE_RESULTS_PATH, Map<String,String> envConfig){
        Properties properties = new Properties();

        // 1. Add your environment variables here
        properties.setProperty("Browser", envConfig.get("browser"));
        properties.setProperty("Environment", envConfig.get("env"));

        // 2. Define the file path
        File allureResultsDir = new File(ALLURE_RESULTS_PATH);
        if (!allureResultsDir.exists()) {
            allureResultsDir.mkdirs(); // Create the directory if it doesn't exist
        }

        File environmentFile = new File(allureResultsDir, "environment.properties");

        try (FileOutputStream fos = new FileOutputStream(environmentFile)) {
            // 3. Write the properties to the file
            properties.store(fos, "Allure Environment Variables");
            System.out.println("Allure environment.properties file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error (e.g., log the failure)
        }
    }

}
