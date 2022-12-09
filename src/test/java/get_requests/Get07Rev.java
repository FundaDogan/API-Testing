package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class Get07Rev extends JsonPlaceHolderBaseUrl {

    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200

			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190

			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4

			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void get01(){

        spec.pathParam("first","todos");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);



        //2)Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();

        List<Integer> ids = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println(ids);

        //Assert that there are 10 ids greater than 190
        assertEquals(10,ids.size());



        //3)Print all userIds whose ids are less than 5 on the console
        List<Integer> userIds = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,userIds.size());



        //4)Print all titles whose ids are less than 5
        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println(titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue("expected title is not exist",titles.contains("delectus aut autem"));


    }

}
