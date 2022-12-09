package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta" are among the users
       And
           The female users are less than or equal to male users
    */

    @Test
    public void get01(){

        spec.pathParam("first","users");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id",hasSize(10),
                "data.status",hasItem("active"),
                "data.name",hasItems("Malti Dutta", "Msgr. Gauranga Desai", "Aatmaj Chopra"));

        //The female users are less than or equal to male users

        //I will compare number of female and male users
        //1st way: I will get all genders in a list then calculate the number of females then
        // compare it with the list.

        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");
        System.out.println(genders);

        int numOfFemales =0;
        int numOfMales=0;

        for (String w:genders){
            if (w.equals("female")){
                numOfFemales++;
            }else{
                numOfMales++;
            }
        }
        System.out.println(numOfFemales);
        System.out.println(numOfMales);

        assertTrue(numOfFemales>numOfMales);


        //2nd way: I will get all females by using Groovy Language then compare it with males

        List<String> females = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(females);

        List<String> males = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(males);

        assertTrue(females.size()>males.size());


    }




}
