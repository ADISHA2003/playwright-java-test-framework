package tests;
import Constants.FilePath;
import Utilities.PropertyFileReaderService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BaseAPITest extends BaseUI_Test{


    public LinkedList<Object> options= new LinkedList<>();
    public String endPoint = "";
    public String body = "";

   
}
