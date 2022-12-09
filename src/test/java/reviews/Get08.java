package reviews;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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

        spec.pathParams("1","todos","2",2);


        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        Map<String,Object> expectedData = obj.expectedDataJPH(1,"quis ut nam facilis et officia qui", false);


        Response response = given().spec(spec).when().get("/{1}/{2}");


        Map<String,Object> actualData = response.as(HashMap.class);


        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals("1.1 vegur",response.getHeader("Via"));
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
