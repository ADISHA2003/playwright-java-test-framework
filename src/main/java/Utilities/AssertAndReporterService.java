package Utilities;

import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;

import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import javax.sound.sampled.Line;
import java.util.Map;

public class AssertAndReporterService {


    public void AssertEquals(Logger logger, ExtentTest extest, String actual, String expected, String FailureMsg, String SuccessMsg){
        try{
            Assert.assertEquals(actual,expected,FailureMsg);
        }
        catch(AssertionError e){
            failureMsgLogs(FailureMsg,logger,extest);
            throw new AssertionError(e);}

        successMsgLogs(SuccessMsg,logger,extest);
    }


    public void AssertEquals(Logger logger, ExtentTest extest, boolean actual, boolean expected, String FailureMsg, String SuccessMsg){
        try{
            Assert.assertEquals(actual,expected,FailureMsg);
        }
        catch(AssertionError e){
            failureMsgLogs(FailureMsg,logger,extest);
            throw new AssertionError(e);}

        successMsgLogs(SuccessMsg,logger,extest);
    }


    public void AssertFalse(Logger logger,ExtentTest extest,boolean value,String FailureMsg,String SuccessMsg) {
        try {
            Assert.assertFalse(value, FailureMsg);
        } catch (AssertionError e) {
            failureMsgLogs(FailureMsg, logger, extest);
            throw new AssertionError(e);
        }

        successMsgLogs(SuccessMsg,logger,extest);
    }


    public void AssertTrue(Logger logger,ExtentTest extest,boolean value,String FailureMsg,String SuccessMsg) {
        try {
            Assert.assertTrue(value, FailureMsg);
        } catch (AssertionError e) {
            failureMsgLogs(FailureMsg, logger, extest);
            throw new AssertionError(e);
        }

        successMsgLogs(SuccessMsg,logger,extest);
    }


    public void failureMsgLogs(String FailureMsg,Logger logger, ExtentTest extest){
        Reporter.log("[FAILED]:"+FailureMsg,true);
        logger.error("[FAILED]:"+FailureMsg);
        extest.fail("[FAILED]:"+FailureMsg);

    }


    public static void successMsgLogs(String SuccessMsg,Logger logger, ExtentTest extest){
        Reporter.log("[PASSED]:"+SuccessMsg,true);
        logger.info(SuccessMsg);
        extest.pass("[PASSED]"+SuccessMsg);
        Allure.step("[PASSED]"+SuccessMsg);
    }


    public static void InfoLogs(String content,Logger logger, ExtentTest extest){
        Reporter.log("[INFO]:"+content,true);
        logger.info("[INFO]:"+content);
        extest.info("[INFO]:"+content);
        Allure.step("[INFO]:"+content);
    }

    public void logResponse(Response response,Logger logger, ExtentTest extest){
        InfoLogs("content-type :"+response.getContentType(),logger,extest);
        InfoLogs("headers :"+response.getHeaders().asList().toString(),logger,extest);
        InfoLogs("statusCode :"+response.statusCode(),logger,extest);
        InfoLogs("cookies :"+response.getCookies().entrySet().toString(),logger,extest);
        InfoLogs("response :"+response.then().extract().asString(),logger,extest);
    }
    public void logResponse(String url, Response response,Logger logger, ExtentTest extest){
        InfoLogs("URL :"+url,logger,extest);
        InfoLogs("content-type :"+response.getContentType(),logger,extest);
        InfoLogs("headers :"+response.getHeaders().asList().toString(),logger,extest);
        InfoLogs("statusCode :"+response.statusCode(),logger,extest);
        InfoLogs("cookies :"+response.getCookies().entrySet().toString(),logger,extest);
        InfoLogs("response :"+response.then().extract().asString(),logger,extest);
    }

    public void logResponse(String url, Map<String,Object> params, Response response, Logger logger, ExtentTest extest){
        InfoLogs("URL :"+url,logger,extest);
        InfoLogs("params :"+params.entrySet(),logger,extest);
        InfoLogs("content-type :"+response.getContentType(),logger,extest);
        InfoLogs("headers :"+response.getHeaders().asList().toString(),logger,extest);
        InfoLogs("statusCode :"+response.statusCode(),logger,extest);
        InfoLogs("cookies :"+response.getCookies().entrySet().toString(),logger,extest);
        InfoLogs("response :"+response.then().extract().asString(),logger,extest);
    }

    SoftAssert softAssert = new SoftAssert();


    public void SoftAssertTrue(Logger logger,ExtentTest extest,boolean value,String FailureMsg,String SuccessMsg) {
        try {
            softAssert.assertTrue(value, FailureMsg);
        } catch (AssertionError e) {
            failureMsgLogs(FailureMsg, logger, extest);
            throw new AssertionError(e);
        }

        successMsgLogs(SuccessMsg,logger,extest);
    }


    public void softAssertAll(){
        softAssert.assertAll();
    }


}
