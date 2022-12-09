package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class H03 extends ReqresBaseUrl {
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

    /*
    {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
     */

    @Test
    public void get01(){

        //set the url
        spec.pathParams("first","api","second","users","third",2);



        // set the expected data
        ReqresTestData obj = new ReqresTestData();
        Map<String,String> dataMap = obj.dataMapSetUp("janet.weaver@reqres.in","Janet","Weaver");
        System.out.println(dataMap);

        Map<String,String> supportMap = obj.supportMapSetUp("To keep ReqRes free, contributions towards server costs are appreciated!");
        System.out.println(supportMap);

        Map<Object,Object> expectedData = obj.expectedDataSetUp(dataMap,supportMap);
        System.out.println(expectedData);


        // send the request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();



        //do assertion
        Map<String, Object> actualData =response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(dataMap.get("email"),((Map)(actualData.get(dataMap))).get("email"));
        assertEquals(dataMap.get("first_name"),((Map)actualData.get(dataMap)).get("first_name"));
        assertEquals(dataMap.get("last_name"),((Map)actualData.get(dataMap)).get("last_name"));
        assertEquals(supportMap.get("text"),((Map)actualData.get(supportMap)).get("text"));





    }
}
