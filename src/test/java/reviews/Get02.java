package reviews;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 extends HerOkuAppBaseUrl {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/1
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "TechProEd"
   And
       Server is "Cowboy"
*/
    @Test
    public void get02(){
        spec.pathParams("1","booking","2","1");

        Response response = given().spec(spec).when().get("/{1}/{2}");

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("TechProEd"));
        assertEquals(response.getHeader("Server"),"Cowboy");

    }

}


