package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"

            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false

            }
     */

    @Test
    public void get01(){

        //1. step set the url
        spec.pathParams("first","todos","second",2);

        //2.Step: Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server", "cloudflare");

        //3.sent the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //as method convert reponse to map..expected data is map too

        //4. do assertion
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));
    }

    @Test
    public void get02(){
        //1. step set the url
        spec.pathParams("first","todos","second",2);


        //2.Step: Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataJPH(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server", "cloudflare");

        System.out.println("expectedData = " + expectedData);



        //3.sent the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4. do assertion
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

}
