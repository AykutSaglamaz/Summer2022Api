package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DummyGet08 extends JsonPlaceHolderBaseUrl {
       /*
        When
        I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos
        And Accept type is “application/json”
        Then
        HTTP Status Code should be 200
        And Response format should be JSON

        And make sure total number of users 200
     */

    @Test
    public void test08(){
        //1. step : Set the URL
     spec.pathParam("first", "todos");

        //2.Step; Set the expected data

        //3.Step: Send request and get data
        //Response->return type, response -> container
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion/validation/verification
         response.
                 then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).
                 body("todos", hasSize(200));




    }


}
