package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import pojos.PracticeJsonPlacePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostPractice01 extends JsonPlaceHolderBaseUrl {

    /*
        Given    https://jsonplaceholder.typicode.com/comments
            {
                "name": "This class has smart people",
                "postId": 80,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
            When I send Post Request to the URL
            Then the status code should be 201
            Response should be like
                 {
                "name": "This class has smart people",
                "postId": 80,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
         */
@Test
    public void post (){
    spec.pathParam("first", "comments");

    PracticeJsonPlacePojo requestBody = new PracticeJsonPlacePojo(80, 501, "This class has smart people", "techproedstudents@gmail.com", "Congratulations Everyone");

    Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).post("/{first}");

    PracticeJsonPlacePojo actualData = response.as(PracticeJsonPlacePojo.class);

    assertEquals(requestBody.getName(), actualData.getName());
    assertEquals(requestBody.getPostId(), actualData.getPostId());
    assertEquals(requestBody.getId(), actualData.getId());
    assertEquals(requestBody.getEmail(), actualData.getEmail());
    assertEquals(requestBody.getBody(), actualData.getBody());

}



}
