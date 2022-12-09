package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
            1) https://jsonplaceholder.typicode.com/todos
            2)  {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post03(){

        // set the url
        spec.pathParam("first","todos");

        // 2. set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //3 . send request get response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        // 4.  do assertion
        JsonPlaceHolderPojo actualdData = response.as(JsonPlaceHolderPojo.class);

        assertEquals(expectedData.getUserId(),actualdData.getUserId());
        assertEquals(expectedData.getTitle(),actualdData.getTitle());
        assertEquals(expectedData.getCompleted(),actualdData.getCompleted());
    }
}
