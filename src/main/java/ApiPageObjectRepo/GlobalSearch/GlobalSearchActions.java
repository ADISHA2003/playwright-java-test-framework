package ApiPageObjectRepo.GlobalSearch;

import ApiPageObjectRepo.BaseApiActions;
import Pojos.GlobalSearchComponentPojoFiles.GlobalSearchComponentPojo;
import Utilities.JsonReaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;


public class GlobalSearchActions extends BaseApiActions {


    // api request on the end point
    public Response get_RequestGlobalSearch(String connectionType, String domain, String endpoint, String contentType, String requestBody){
        String url = connectionType+domain+endpoint;
        return restAssuredService.getRequest(url,contentType,requestBody);
    }

    // api request on the end point
    public Response post_RequestGlobalSearch(String connectionType, String domain, String endpoint, String contentType, String requestBody){
        String url = connectionType+domain+endpoint;
        System.out.println(url);
        System.out.println(requestBody);
        return restAssuredService.postRequest(url,contentType,requestBody);
    }

    // creating objectmapped towards the pojo
    public GlobalSearchComponentPojo globalSearchMapper(Response response){
        ObjectMapper objectMapper = initObjectMapper();
        try {
            GlobalSearchComponentPojo globalSearchComponentPojo = objectMapper.readValue(response.asString(), GlobalSearchComponentPojo.class);
            return globalSearchComponentPojo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String getCompanyName(Response response){
        GlobalSearchComponentPojo globalSearchResponse = globalSearchMapper(response);
        try{
        String companyName =  globalSearchResponse.getCompanies().getSearchMapper().get(0).getName();
        }catch(IndexOutOfBoundsException iob){
            return null;
        }return null;
    }

    public boolean validateJsonSchema(Response response,String resourceFolder,String testFolder,String fileName){
        InputStream globalSearchJsonSchema = JsonReaderService.getDataFromJsonFile_JsonFormat(resourceFolder,testFolder,fileName);
        try{response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(globalSearchJsonSchema));
        return true;}
        catch (AssertionError ae){
            return false;
        }
    }


}
