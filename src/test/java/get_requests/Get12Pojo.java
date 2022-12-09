package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/12
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Fabio",
                                    "lastname": "Dominguez",
                                    "totalprice": 111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2018-01-01",
                                        "checkout": "2019-01-01"
                                    },
                                    "additionalneeds": "Breakfast"
     */

    @Test
    public void get12(){

        //1. set the url
        spec.pathParams("first","booking","second",12);

        // set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Fabio","Dominguez",111,true,bookingDates,"Breakfast");

        // send the request

        Response response = given().spec(spec).when().get("{first}/{second}");

        // assertion

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBookingdates().getCheckout());

    }
}
