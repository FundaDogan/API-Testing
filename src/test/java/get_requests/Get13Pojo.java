package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
      Given
          https://gorest.co.in/public/v1/users/131
      When
          User send GET Request to the URL
      Then
          Status Code should be 200
      And
          Response body should be like
                      {
                          "meta": null,
                          "data": {
                              "id": 131,
                              "name": "Prem Pilla",
                              "email": "pilla_prem@mueller.info",
                              "gender": "female",
                              "status": "active"
                          }
                      }
  */

    @Test
    public void get13(){

        spec.pathParams("first","users","second",131);

        GoRestDataPojo data = new GoRestDataPojo("Darshan Kaur","darshan_kaur@williamson-maggio.co","male","inactive");
        GoRestPojo expectedData = new GoRestPojo(null,data);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(data.getName(),actualData.getData().getName());
        assertEquals(data.getEmail(),actualData.getData().getEmail());
        assertEquals(data.getGender(),actualData.getData().getGender());
        assertEquals(data.getStatus(),actualData.getData().getStatus());





    }
}
