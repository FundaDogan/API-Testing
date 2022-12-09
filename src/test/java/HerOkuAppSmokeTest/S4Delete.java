package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import java.lang.module.ResolutionException;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S4Delete extends HerOkuAppBaseUrl {

    //type test case
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends Delete Request
    Then
        Status code 201
    And
        Response body is "Created"
     */

    @Test
    public void delete01(){
        //set the url
        spec.pathParams("first","booking","second",bookingId);

        //set the expected data
        String expectedData = "Created";

        //send the request get the response
        Response response = given().spec(spec).
                headers("Cookie","token= "+ AuthenticationHerOkuApp.generateToken()).
                when().delete("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData,response.asString());

    }
}
