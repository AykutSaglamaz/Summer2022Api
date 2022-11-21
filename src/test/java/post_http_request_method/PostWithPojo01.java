package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
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
       JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room", false);

       //3. Step: send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).post("/{first}");

       //4.Step: do assertion
       //1.way
        response.then().assertThat().statusCode(201).body("userId", equalTo(requestBody.getUserId()),
                "title", equalTo(requestBody.getTitle()),
                "completed", equalTo(requestBody.getCompleted()));

        //2. way: De-Serialization
       JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

       assertEquals(requestBody.getUserId(), actualData.getUserId());
       assertEquals(requestBody.getCompleted(), actualData.getCompleted());
       assertEquals(requestBody.getTitle(), actualData.getTitle());

   }



}
