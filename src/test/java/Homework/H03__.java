package Homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H03__ extends ReqresBaseUrl {

    @Test
    public void get01() {

        spec.pathParams("first", "api", "second", "users", "third", 2);

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}