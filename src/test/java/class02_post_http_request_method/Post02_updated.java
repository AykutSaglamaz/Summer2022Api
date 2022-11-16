package class02_post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_updated extends JsonPlaceHolderBaseUrl {
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
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataSetUp();

//        Map<String, Object> expectedDataMap = expectedData.expectedDataSetUpWithAllKeys(66, "Tidy your room", false);

        //3.Step: Send the request and get the response

        Response response = given().
                        spec(spec).//endpoint
                        auth().basic("admin", "1234").//credentials
                        contentType(ContentType.JSON).//contentType
                        body(expectedDataMap).
                        when().
                        post("/{first}");

        response.prettyPrint();
        // I added Status code to use it in assertion
        expectedDataMap.put("StatusCode", 201);

        //4. Step: do assertion

        Map<String, Object> actualData = response.as(HashMap.class); //from object to HashMap data type => de-serialization
        System.out.println(actualData);

        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("userID"), actualData.get("userID"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));


    }
}