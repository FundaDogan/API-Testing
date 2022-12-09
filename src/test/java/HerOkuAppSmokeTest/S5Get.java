package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class S5Get extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends Get request
    Then
        Status code is 404
    And
        Response body is like "Not Found"
     */
    @Test
    public void get02() {
        //Set the url
        spec.pathParams("first", "booking", "second", S1Post.bookingId);

        //set the expectedData
        String expectedData = "Not Found";

        //send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // do assertion

        assertEquals(404, response.getStatusCode());
        assertEquals(expectedData, response.asString());


    }

}
