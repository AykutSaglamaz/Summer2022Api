package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PostDeleteWithPojo03 extends JsonPlaceHolderBaseUrl {

 /*
        Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
            I send Delete Request to the Url
        Then
            response body is like { }
     */

    @Test
    public void PostDeleteWithPojo03() {
        //1.Step set the url
        spec.pathParam("first", "todos");

        //2.Set the request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3. Step: send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).post("/{first}");

        //Deletion part
        //Get the id of newly created data
        JsonPath json = response.jsonPath();
        Integer id = json.getInt("id");

        spec.pathParams("first", "todos", "second", id);

        Response response2 = given().spec(spec).when().delete("/{first}/{second}");

        Map<String , Object> actualData = response2.as(HashMap.class);
        assertTrue(actualData.size()==0);
    }
}