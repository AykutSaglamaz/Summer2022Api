package class02_post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {
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
    Then
        Status code is 201
    And
         response body is like
         {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false,
              "id": 201
         }
 */

    @Test
    public void postWithPojo01(){
        //1.Step set the url
        spec.pathParam("first", "todos");

        //2.Set the request body
        JsonPlaceHolderPojo requestBodyPojo = new JsonPlaceHolderPojo(55,"Tidy your room", false);

        //3. Step: send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyPojo).post("/{first}");

        //4.Step: do assertion
        //1.way
        response.
                then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(requestBodyPojo.getUserId()),
                "title", equalTo(requestBodyPojo.getTitle()),
                "completed", equalTo(requestBodyPojo.getCompleted()));

        //2. way: use De-Serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

        assertEquals(requestBodyPojo.getUserId(), actualData.getUserId());
        assertEquals(requestBodyPojo.getCompleted(), actualData.getCompleted());
        assertEquals(requestBodyPojo.getTitle(), actualData.getTitle());

    }
}
