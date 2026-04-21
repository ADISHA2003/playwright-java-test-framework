package ApiPageObjectRepo;

import Utilities.RestAssuredService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.Map;

import org.apache.http.params.CoreConnectionPNames;


public class BaseApiActions {

    protected RestAssuredService restAssuredService;

    public BaseApiActions(){
    	
    	 
        this.restAssuredService = new RestAssuredService();
    }

    public boolean verifyStatusCodes(Response response, int code){
        if(response.statusCode() ==code){
            return true;
        }return false;
    }

    public ObjectMapper initObjectMapper()
    {
        return new ObjectMapper();
    }

    public Response postGraphQLResponse(String Domain, String endpoint, String body){
        return restAssuredService.graphQLPostRequest(Domain+endpoint,body);
    }

    public Response apiRequest(String url, Map<String,Object> header, String body, String method) {
        switch (method.toUpperCase()) {
            case "GET":
                return restAssuredService.getRequest(url, header);
            case "POST":
                System.out.println("inside post");
                return restAssuredService.postRequest(url, header, body);
            case "PUT":
                return restAssuredService.putRequest(url, header, body);
            case "DELETE":
                return restAssuredService.deleteRequest(url, header, body);
            default:
                return null;
        }
    }

        public Response apiRequest(String url, Map<String,Object> header, String body, String method, Map<String,Object> params){
            switch (method.toUpperCase()){
                case "GET":
                    if(params.size()>0){
                        return restAssuredService.getRequestWithParams(url,header,params);}
                    return restAssuredService.getRequest(url,header);
                case "POST":
                    if(params.size()>0){
                        return restAssuredService.postRequestWithParams(url,header,body,params);}
                    System.out.println("inside post");
                    return restAssuredService.postRequest(url,header,body);
                case "PUT":
                    return restAssuredService.putRequest(url,header,body);
                case "DELETE":
                    return restAssuredService.deleteRequest(url,header,body);
                default:
                    return null;
            }
    }

    public Response apiRequestQueryParams(String url, Map<String,Object> header, String body, String method, Map<String,Object> params){
        switch (method.toUpperCase()){
            case "GET":
                if(params.size()>0){
                    return restAssuredService.getRequestWithParams(url,header,params);}
                return restAssuredService.getRequest(url,header);
            case "POST":
                if(params.size()>0){
                    return restAssuredService.postRequestWithQueryParams(url,header,body,params);}
                System.out.println("inside post");
                return restAssuredService.postRequest(url,header,body);
            case "PUT":
                return restAssuredService.putRequest(url,header,body);
            case "DELETE":
                return restAssuredService.deleteRequest(url,header,body);
            default:
                return null;
        }
    }

}
