package reviews;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
         {
                "meta": null,
                "data": {
                    "id": 13,
                    "name": "Girish Trivedi",
                    "email": "girish_trivedi@hermann.name",
                    "gender": "male",
                    "status": "inactive"
                }
}
        }
     */

    @Test
    public void get01(){
        spec.pathParams("1","users","2",13);

        GoRestTestData obj = new GoRestTestData();
        Map<String,String> dataMap = obj.goRestDataMapSetUp("Girish Trivedi","girish_trivedi@hermann.name","male","inactive");

        Map<String,Object> expectedData = obj.expectedDataMapSetUp(null,dataMap);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataMap.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(dataMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"),((Map)actualData.get("data")).get("status"));




    }
}
