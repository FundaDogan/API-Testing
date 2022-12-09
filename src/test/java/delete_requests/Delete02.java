package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class Delete02 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 201
		 	And Response body is "Created"
     */

    @Test
    public void delete02(){
        //set the url
        spec.pathParams("first","booking","second",19275);

        //set the expected data

        String expectedData = "Created";

        // send the request and get the response
        Response response = given().spec(spec).
                header("Cookie","token= "+ generateToken()).
                contentType(ContentType.JSON).when().delete("/{first{/{second}");

        response.prettyPrint();

        //  given().spec(spec).cookie("25e166bd492295e").when().delete();   seklinde de olur

        // do assertion
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData,response.asString());



    }
}
