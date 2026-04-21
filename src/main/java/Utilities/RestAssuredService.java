package Utilities;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.Map;

public class RestAssuredService {
	
	RestAssuredConfig config;
	public RestAssuredService() {
		config = RestAssured.config()
    	        .httpClient(HttpClientConfig.httpClientConfig().setParam("http.socket.timeout", 120000)
    	                .setParam("http.connection.timeout", 120000));
	}

    public Response getRequest(String URL, String contentType){
        System.out.println(URL);
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response getRequest(String URL, String contentType, String requestBody){
        System.out.println(URL);
        System.out.println(requestBody);
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .body(requestBody)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response postRequest(String URL, String requestBody){
        System.out.println(URL);
        System.out.println(requestBody);
        return RestAssured.given()
                .contentType("application/json")
                .baseUri(URL)
                .body(requestBody)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public Response postRequest(String URL, String contentType, String requestBody){
        System.out.println(URL);
        System.out.println(requestBody);
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .body(requestBody)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public Response graphQLPostRequest(String URL, String requestBody){
        return postRequest(URL,requestBody);
    }

    public Response getRequest_token(String URL, String contentType, String token){
        System.out.println(URL);
        System.out.println(token);
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .header("Authorization","Bearer "+token)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response getRequest_token_params(String URL, String contentType, String token, Map<String, Object> params){
        System.out.println(URL);
        System.out.println(params.entrySet());
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .header("Authorization","Bearer "+token)
                .queryParams(params)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response putRequest_token_params_bodyMap(String URL, String contentType, String token, Map<String, Object> params, Map<String, Object> bodyMap){
        System.out.println(URL);
        System.out.println(params.entrySet());
        System.out.println(bodyMap);
        return RestAssured.given().config(config)
                .contentType(contentType)
                .baseUri(URL)
                .header("Authorization","Bearer "+token)
                .queryParams(params)
                .body(bodyMap)
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public Response putRequest(String URL, Map<String, Object> header, String body) {
        System.out.println(URL);
        System.out.println(header.entrySet());
        System.out.println(body);
        return RestAssured.given().config(config)
                .baseUri(URL)
                .headers(header)
                .body(body)
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public Response deleteRequest(String URL, Map<String, Object> header, String body) {
        System.out.println(URL);
        System.out.println(header.entrySet());
        System.out.println(body);
        return RestAssured.given().config(config)
                .baseUri(URL)
                .headers(header)
                .body(body)
                .when()
                .delete()
                .then()
                .extract()
                .response();
    }

    public Response getRequest(String URL, Map<String,Object> header){
        System.out.println(URL);
        System.out.println(header.entrySet());
        return RestAssured.given().config(config)
                .headers(header)
                .baseUri(URL)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response postRequest(String URL, Map<String,Object> header, String requestBody){
        System.out.println("URL : "+URL);
        System.out.println("header : "+header.entrySet());
        System.out.println("Request body : "+requestBody);
        return RestAssured.given().config(config)
                .headers(header)
                .baseUri(URL)
                .body(requestBody)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public Response getRequestWithParams(String URL, Map<String,Object> header, Map<String,Object> params){
        System.out.println(URL);
        System.out.println(header.entrySet());
        return RestAssured.given().config(config)
                .headers(header)
                .baseUri(URL)
                .queryParams(params)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response postRequestWithParams(String URL, Map<String,Object> header, String requestBody,Map<String,Object> param){
        System.out.println(URL);
        System.out.println(header.entrySet());
        System.out.println(requestBody);
        return RestAssured.given().config(config)
                .headers(header)
                .baseUri(URL)
                .body(requestBody)
                .params(param)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public Response postRequestWithQueryParams(String URL, Map<String,Object> header, String requestBody,Map<String,Object> params){
        System.out.println(URL);
        System.out.println(header.entrySet());
        System.out.println(requestBody);
        return RestAssured.given()
                .headers(header)
                .baseUri(URL)
                .body(requestBody)
                .queryParams(params)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }
}
