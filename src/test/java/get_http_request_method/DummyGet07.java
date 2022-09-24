package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DummyGet07 extends JsonPlaceHolderBaseUrl {
 /*
        When
        I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos
        And Accept type is “application/json”
        Then
        HTTP Status Code should be 200
        And Response format should be JSON
        And there should be 200 todos
        And "numquam repellendus a magnam" should be one of the todos
        And 194, 192, and 186 should be among the userIds
     */


    @Test
    public void test07(){

        //1.step: set up url
        spec.pathParam("first", "todos");
        //2.Step; Set the expected data

        //3.Step: Send request and get data
        //Response->return type, response -> container
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //4. Step: do assertion/validation/verification

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasSize(200),
                        "title", hasItem("numquam repellendus a magnam"),
                        "id", hasItems(94, 192, 186));




    }

}
