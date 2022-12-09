package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class H03_ extends ReqresBaseUrl {
/*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"

         */

    @Test
    public void get01() {

        //set the url
        spec.pathParams("first", "api", "second", "users", "third", 2);

        // send the request

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
       assertEquals(200, response.getStatusCode());
       assertEquals("application/json; charset=utf-8", response.getContentType());
       assertEquals("janet.weaver@reqres.in", json.getString("data.email"));
       assertEquals("Janet", json.getString("data.first_name"));
       assertEquals("Weaver", json.getString("data.last_name"));
       assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", json.getString("support.text"));


    }
}