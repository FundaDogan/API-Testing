package reviews;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Get06 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                   "firstname": "Sally",
                   "lastname": "Wilson",
                   "totalprice": 820,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2018-07-03",
                       "checkout": "2020-03-02"
                   }
               }
     */

    @Test
    public void get01(){
        spec.pathParams("1","booking","2",10);

        Response response = given().spec(spec).when().get("/{1}/{2}");

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Sally",jsonPath.getString("firstname"));
        assertEquals("Wilson",jsonPath.getString("lastname"));
        assertEquals(820,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-07-03",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2020-03-02",jsonPath.getString("bookingdates.checkout"));

    }
}
