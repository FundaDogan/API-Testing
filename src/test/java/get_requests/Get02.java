package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {

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
    public void get01(){

//        i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/1";

//        ii) Set the expected Data(Post-Put-Patch)

//        iii) Type code to send the Request
        Response response = given().when().get(url);
        response.prettyPrint();

//        iv) Do Assertion

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");


        // How to assert if response body has a specific text
        // assertTrue(x) method passes if the x is true
        assertTrue(response.asString().contains("Not Found"));


        // How to assert if response body does not have a specific text
        //assertFalse(y) method passes if the y is false
        assertFalse(response.asString().contains("TechProEd"));


        // assertEqual(x,y) method passes if x equals to y
        assertEquals("Cowboy", response.getHeader("Server"));


    }

}
