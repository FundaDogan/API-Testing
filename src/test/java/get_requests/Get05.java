package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and lastname is "Dear"
     */

    @Test
    public void get05(){

        // 1. set the url
        spec.pathParam("first","booking").
                queryParams("firstname","Adamz","lastname","Dear");

        // 2. set the expected data

        // 3. step: set the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. step : Do assertion

        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));

    }
}
