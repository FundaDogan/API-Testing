package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get14(){

        //set the url
        spec.pathParams("first","todos","second",198);


        //set the expected data  ===> de=serialization ==
//        String expectedData = "{\n" +
//                                "    \"userId\": 10,\n" +
//                                "    \"id\": 198,\n" +
//                                "   \"title\": \"quis eius est sint explicabo\",\n" +
//                                "    \"completed\": true\n" +
//                                "  }";

        //since my de-serialization method converts json to java, it needs json in string data type.

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10,"quis eius est sint explicabo",true);
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);
        // bu metod string i hasmape donusturecek



        //send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualData = JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        // convertJsonToJava will get response in json format and convert it to hashmap. response u javanin
        // kullanabilmesi icin string formata donusturmek gerekiyor. asString() json datayi string icine yaziyor.


        // assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("userId"),actualData.get("UserId"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));






        
    }
}
