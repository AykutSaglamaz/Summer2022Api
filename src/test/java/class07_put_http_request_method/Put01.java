package class07_put_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198

             {
            "userId": 21,
            "title": "Wash the dishes",
            "completed": false
                }
    When
            Url'e PUT Request gonder
            with the PUT Request body like
    Then
           Status code is 200
           And response body is like
           {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
             }
     */

    @Test
    public void put01(){
        //1.Step: set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2. Step: set the request body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpWithAllKeys(21,"Wash the dishes", false);

        //3.Step: Send the request and get the response

        Response response =  given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();

        //4.Do assertion
        //1. way
        response.
                then().
                assertThat().
                statusCode(200).
                body("userID", equalTo(requestBodyMap.get("userID")),
                        "title",equalTo(requestBodyMap.get("title")),
                        "completed", equalTo(requestBodyMap.get("completed")));

        //2.way: use GSON: De-serialization: Converting Json Data to Java Object
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(requestBodyMap.get("userID"), actualDataMap.get("userID"));
        assertEquals(requestBodyMap.get("title"), actualDataMap.get("title"));
        assertEquals(requestBodyMap.get("completed"), actualDataMap.get("completed"));

        //How to use GSON in Serialization: Java object ==>> Json Data

        Map<String, Integer> ages = new HashMap<>();// { }>> empty map
        ages.put("Ali  Can", 13);
        ages.put("Veli  Han", 15);
        ages.put("Ayse  Kan", 21);
        ages.put("Mary  Star", 65);
        System.out.println(ages);

        //Convert ages to json data
        //1.Step: Create Gson Object
        Gson gson = new Gson();
        //2.Step: By using 'gson' object method to convert Java Object to Json Data
        String jsonFromMap = gson.toJson(ages);

        System.out.println(jsonFromMap);

    }
}

