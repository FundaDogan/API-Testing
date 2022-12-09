package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.JsonUtil;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S2Put extends HerOkuAppBaseUrl {

    //type test case

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
                    {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                    "checkin" : "2019-01-01",
                    "checkout" : "2020-01-01"
                },
                "additionalneeds" : "Breakfast"
            }
        When
            User sends Put request
        Then
            status code should be 200;
        And
            Response body should be like
                        {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 500,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2019-01-01",
                    "checkout": "2020-01-01"
                },
                "additionalneeds": "Breakfast"
            }

     */

    @Test
    public void put01(){

        //set the url
        spec.pathParams("first","booking","second",bookingId);

        //set the expectedData
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2019-01-01","2020-01-01");
        BookingPojo expectedData = new BookingPojo("James","Brown",500,false,bookingDatesPojo,"Breakfast");
        System.out.println(expectedData);

        // send the request get the response

        Response response = given().spec(spec).
                contentType(ContentType.JSON).
                headers("Cookie","token="+generateToken()).body(expectedData).when().put("/{first}/{second}");

        response.prettyPrint();

        // do assertion
        BookingPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }

}
