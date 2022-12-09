package reviews;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                            {
                                "firstname": "John",
                                    "lastname": "Smith",
                                    "totalprice": 111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                                "additionalneeds": "Breakfast"
                            }

     */

    @Test
    public void get01(){

        spec.pathParams("1","booking","2",246);

        TestHerOku obj = new TestHerOku();
        Map<String,String> bookingDates = obj.bookingDatesSetUp("2018-01-01","2019-01-01");
        Map<String,Object> expectedData = obj.expectedDataSetUp("John","Smith",111,true,bookingDates,"Breakfast");

        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{1}/{2}");
        Map<String,Object> actualData = response.as(HashMap.class);

        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }
}
