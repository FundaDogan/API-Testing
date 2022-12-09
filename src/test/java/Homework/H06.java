package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class H06 extends ReqresBaseUrl {

    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void get01(){

        spec.pathParams("first","api","second","unknown");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        assertEquals(200,response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();
        List<String> pantone_value = jsonPath.getList("data.pantone_value");
        System.out.println("pantone_values : " + pantone_value);

        System.out.println();

        List<Integer> idsGreaterThan3= jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println(idsGreaterThan3);
        assertEquals(3,idsGreaterThan3.size());

        System.out.println();

        List<String> namesWhoseIdsAreLessThan3 = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println(namesWhoseIdsAreLessThan3);
        assertEquals(2,namesWhoseIdsAreLessThan3.size());




    }

}
