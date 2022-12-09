package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
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
                "firstname": "Alex",
                "lastname": "Dominguez",
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

        // 1. set the url
        spec.pathParams("first","booking","second",246);

        // 2. set the expected data

        Map<String,String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Alex");
        expectedData.put("lastname","Dominguez");
        expectedData.put("totalPrice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates",bookingDatesMap);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println("expectedData = " + expectedData);

        //3.send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // 4. do assertion

        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = "+ actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalPrice"),actualData.get("totalPrice"));
        assertEquals(expectedData.get("depositPaid"),actualData.get("depositPaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map)(actualData.get("bookingDates"))).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));

    }

    @Test
    public void get02(){

        //1.set the url
        spec.pathParams("first","booking","second","246");

        //2. set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String ,String> bookingdatesMap = obj.bookingdatesMapSetUp("2018-01-01","2019-01-01");
        Map<String,Object> expectedData = obj.expectedDataSetUp("Alex","Dominguez",111,true, bookingdatesMap,"Breakfast");
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        //2nd Way:Recommended
        assertEquals(bookingdatesMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));

    }
}
