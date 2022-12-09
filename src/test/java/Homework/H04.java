package Homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertTrue;

public class H04 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */

    @Test
    public void get01(){

        spec.pathParam("first","booking").
                queryParams("firstname","Brandon","lastname","Wilson");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));
    }

}
