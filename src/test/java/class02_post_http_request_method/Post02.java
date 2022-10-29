package class02_post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post02 extends JsonPlaceHolderBaseUrl {
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
            response body is like {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
             }
     */
    @Test
    public void post02() {

        //1.Step/ set the URL
        spec.pathParam("first", "todos");

        //2.step set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);

        //3.Step: Send the request and get the response

        Response response = given().
                                spec(spec). //endpoint
                                contentType(ContentType.JSON).
                                body(expectedData).
                            when().
                                post("/{first}");
        response.prettyPrint();

        // I added Status code to use it in assertion
        expectedData.put("StatusCode", 201);

        //4. Step: do assertion

        Map<String, Object> actualData = response.as(HashMap.class); //as() convert response object to HashMap data type => de-serialization
        System.out.println(actualData);

        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("userID"), actualData.get("userID"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
