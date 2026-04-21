package ApiPageObjectRepo.CompAnalysis;

import ApiPageObjectRepo.BaseApiActions;
import io.restassured.response.Response;


public class CompAnalysis_locationCountBasedOnFiltersPageActions extends BaseApiActions {

    public Response post_CompAnalysisLocationCount(String connectionType, String domain, String endpoint, String contentType, String requestBody){
        String url = connectionType+domain+endpoint;
        System.out.println(url);
        System.out.println(requestBody);
        return restAssuredService.postRequest(url,contentType,requestBody);
    }
}