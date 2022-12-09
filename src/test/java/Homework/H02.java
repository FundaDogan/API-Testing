package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class H02 extends ReqresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */

    @Test
    public void get01(){

        spec.pathParams("first","api","second","users","third",23);

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(0,response.as(HashMap.class).size());
    }
}
