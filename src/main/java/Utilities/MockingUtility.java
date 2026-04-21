package Utilities;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class MockingUtility {

    WireMockServer wireMockServer;
    public void intializeMockServer(String port){
        wireMockServer = new WireMockServer(options().port(8089));
    }

    public void startMockingService(){
        wireMockServer.start();
    }

    public void stopMockingService(){
        wireMockServer.stop();
    }
    public String convertToCamelCase(String key){
        String[] parts = key.split(" ");
        StringBuilder camelCaseKey = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCaseKey.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseKey.toString();
    }

    public String getResponseBodyFromScreenerAPICall(
            List<Response> responseHistory,
            String endpointSubstring,
            String requestBodySubstring
    ) {
        // 1️⃣ First check HISTORY
        int retry = 3;
        while(retry>0) {
            System.out.println("Checking response history, attempt: " + (retry));
            for (Response res : responseHistory) {
                    System.out.println("Checking URL: " + res.url());
                if (res.url().contains(endpointSubstring)&& res.url().contains("vccedge.com")) {
                    try {System.out.println("FOUND in history: " + res.url());
                        Request req = res.request();
                        String body = req.postData();
                        System.out.println("Request Body: " + body);
                        System.out.println("Response Body: " + res.text());
                        System.out.println(requestBodySubstring);
                        if (body != null && body.contains(requestBodySubstring)) {

                            System.out.println("FOUND in history: " + res.url());
                            return res.text();   // FOUND in history
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
            try{
                Thread.sleep(5000);
            }
            catch (Exception e){}
        retry--;

        }
        return null;

    }

}
