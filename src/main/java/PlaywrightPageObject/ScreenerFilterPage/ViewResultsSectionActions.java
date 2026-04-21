package PlaywrightPageObject.ScreenerFilterPage;

import PlaywrightPageObject.BasePageActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewResultsSectionActions extends BasePageActions {
    public ViewResultsSectionActions(Page page) {
        super(page);
    }

    private Locator heading_resultsSection = page.locator("//div[@class='sidebar bottom-fixed-sidebar']//h3");
    private Locator viewResultCard = page.locator("css=.content-card");
    private Locator spinner = page.locator("css=.spinner");

    public void verifyResultsSection(String screenerName){

    }

    public void thenIVerifyViewResultsCount(){

    }

    public void thenIverifyViewResultsSectionIsScrollable(){

    }

    public void thenIShouldSeeResultsSectionAndCard(){
        heading_resultsSection.isVisible();
        assertThat(heading_resultsSection).containsText("Results Preview");
        assertThat(spinner).isHidden();
        page.waitForTimeout(2000); // wait for results to load
        viewResultCard.all().get(0).isVisible();
        assertThat(viewResultCard).not().hasCount(0);
    }

    /**
     * Fetches screening results using the provided access token.
     * @param token The JWT token retrieved from the login method.
     * @return The response body as a String (or you can return the Response object).
     */
    public long getScreeningResults(String token,String requestBody) {


        // 2. Execute the POST request
        long totalElements = RestAssured
                .given()
                .baseUri("https://prodapigw-research-service.vccedge.com")
                .header("Authorization", token) // Pass the dynamic token here
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/services/research/sourcing/screening_result")
                .then()
                .statusCode(200) // Ensure the request was successful
                .extract()
                .jsonPath().getLong("data.totalElements"); // Extract the totalElements from the response

        System.out.println(totalElements);
        return totalElements;
    }

    public List<Integer> getScreeningResultIds(String token,String requestBody) {
        // 2. Execute the POST request
        List<Integer> totalElements = RestAssured
                .given()
                .baseUri("https://prodapigw-research-service.vccedge.com")
                .header("Authorization", token) // Pass the dynamic token here
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/services/research/sourcing/screening_result")
                .then()
                .statusCode(200) // Ensure the request was successful
                .extract()
                .jsonPath().getList("data.content.companyId"); // Extract the totalElements from the response

        return totalElements;
    }

}
