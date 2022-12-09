package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class H05 extends ReqresBaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/2
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void get01(){

       spec.pathParams("first","api","second","unknown","third",23);

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();// de-serialization

        SoftAssert softassert = new SoftAssert();

       softassert.assertEquals(json.getString("data.name"),"true red","name did not match");
       softassert.assertEquals(json.getInt("data.year"),2002,"year did not match");
       softassert.assertEquals(json.getString("data.color"),"#BF1932","color did not match");
       softassert.assertEquals(json.getString("pantone_value"),"19-1664","pantone_value did not match");
       softassert.assertEquals(json.getString("support.url"),"https://reqres.in/#support-heading","url did not match");
       softassert.assertEquals(json.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text did not match");

       softassert.assertAll();
    }
}
