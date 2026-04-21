//package ApiPageObjectRepo.ScreenerFilters;
//
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.Request;
//import com.microsoft.playwright.Response;
//
//public class CommonScreenerFilters {
//
//    Browser context;
//
//    public CommonScreenerFilters(Browser context){
//        this.context=context;
//    }
//
//    public String getResponseBodyFromAPICall(String apiUrl, String filterName){
//        String requestMatchKey=convertToCamelCase(filterName);
//        System.out.println("Request Match Key: " + requestMatchKey);
//        Request matchedRequest = page.waitForRequest(
//                req -> req.url().contains(apiUrl) &&
//                        req.postData() != null &&
//                        req.postData().contains(requestMatchKey),
//                () -> {} // required empty callback
//        );
//
//        // Step 2: Wait for the response for that specific request
//        Response matchedResponse = page.waitForResponse(
//                res -> res.url().equals(matchedRequest.url()) &&
//                        res.request().postData().equals(matchedRequest.postData()),
//                () -> {} // required empty callback
//        );
//
//        // Step 3: Get the response body
//        String responseBody = matchedResponse.text();
//
//        // Step 4: return the required value
//        return responseBody;
//    }
//}
