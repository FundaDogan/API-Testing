package get_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyApiBaseUrl {

  /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "[Tatyana Fitzpatrick]"
    And
        Total salary of all employees is 6,644,770
     */

    @Test
    public void get01(){

        //set the url
        spec.pathParam("1","employees");

        //set the expected Data


        //send the request
        Response response = given().spec(spec).when().get("/{1}");
        response.prettyPrint();

        // do assertion
        response.then().
                assertThat().
                statusCode(200).
                body("data",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //The greatest age is 66

       List<Integer> ages =  response.jsonPath().getList("data.employee_age");
        System.out.println(ages);
        Collections.sort(ages);

        assertEquals(66,(int)ages.get(ages.size()-1));

        //The name of the lowest age is "[Tatyana Fitzpatrick]"

       String name = response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
        System.out.println(name);
       assertEquals("Tatyana Fitzpatrick",name);

       List<Integer> salaries = response.jsonPath().getList("data.employee_salary");

       //1.st way
        int sumOfSalaries=0;

        for(int w:salaries){

            sumOfSalaries= sumOfSalaries+w;
        }
        System.out.println(sumOfSalaries);
        assertEquals(6644770,sumOfSalaries);

        //2.way
        int sumOfSalariesLambda = salaries.stream().reduce(0,(t,u)->(t+u));
        System.out.println(sumOfSalariesLambda);
        assertEquals(6644770,sumOfSalaries);

        //3.way
        int sumOfSalariesLambda2 = salaries.stream().reduce(0,Math::addExact);
        System.out.println(sumOfSalariesLambda2);
        assertEquals(6644770,sumOfSalaries);





    }
}
