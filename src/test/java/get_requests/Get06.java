package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/10
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                "firstname": "Mark",
                "lastname": "Jackson",
                "totalprice": 554,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2017-09-11",
                    "checkout": "2021-03-14"
                }

     */

     @Test
    public void get(){

         // 1. step : set the url
         spec.pathParams("first","booking","second",2);

         // 2. step ; set the expected data

         //3. step send the request and get the response

         Response response = given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

          //4. step: do assertions

         response.then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).body("firstname",equalTo("Sally"),
                         "lastname", equalTo("Ericsson"),
                         "totalprice", equalTo(115),
                         "depositpaid", equalTo(false),
                         "bookingdates.checkin", equalTo("2020-11-08"),
                         "bookingdates.checkout",equalTo("2021-01-10"));


         //2nd Way: We will use JsonPath Class
         JsonPath jsonPath = response.jsonPath();

         System.out.println(jsonPath.getString("firstname"));
         System.out.println("---");
         System.out.println(jsonPath);



         //Hard Assertion
         assertEquals("Mark",jsonPath.getString("firstname"));
         assertEquals("Jackson",jsonPath.getString("lastname"));
         assertEquals(554,jsonPath.getInt("totalprice"));
         assertEquals(false,jsonPath.getBoolean("depositpaid"));
         assertEquals("2017-09-11",jsonPath.getString("bookingdates.checkin"));
         assertEquals("2021-03-14",jsonPath.getString("bookingdates.checkout"));




         //Soft Assertion
         //To do Soft Assertion  follow this 3 steps:

         //1st: Create SoftAssert Object
         SoftAssert softAssert = new SoftAssert();


         //2nd: Do Assertion
          softAssert.assertEquals(jsonPath.getString("firstname"),"Mark","firstname did not match");//3rd parameter is for failure message
          softAssert.assertEquals(jsonPath.getString("lastname"),"Jackson","lastname did not match");
          softAssert.assertEquals(jsonPath.getInt("totalprice"),554,"totalprice did not match");
          softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),false,"depositpaid did not match");
          softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2017-09-11","checkin did not match");
          softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2021-03-14","checkout did not match");
          softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds did not match");

          //3rd: Use assertAll() method.
          softAssert.assertAll();




     }

}
