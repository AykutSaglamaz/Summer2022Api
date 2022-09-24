package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
    The biggest challenge in API Testing is data types
     1) Java use Objects and Primitives as data types,
        API uses XML, Json etc.
        Java an API are using different data types, they should communicate with each other
        Communication is impossible with different data types.

        We have 2 options:
        i) Convert Json to Java object ==>De-Serialization -> to convert json data type to java data type
        ii) Convert Java Object to Json ==> Serialization

        For Serialization and De-Serialization we have 2 options
            a) GSON--> Google Created it
            b) Object Mapper --> more populer

     */


/*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            I send GET Request to the URL
    Then
            Status code is 200
    And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
    {
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
    }
 */

@Test
    public void get08(){

    //1. step: set the url

    spec.pathParams("first", "todos", "second", 2);

    //2. step : Set the expected data
    Map<String, Object> expecteddata = new HashMap<>();
    expecteddata.put("userId", 1);
    expecteddata.put("id", 2);
    expecteddata.put("title", "quis ut nam facilis et officia qui");
    expecteddata.put("completed", false);
    expecteddata.put("Status Code", 200);
    expecteddata.put("Via", "1.1 vegur");
    expecteddata.put("Server", "cloudflare");
    System.out.println(expecteddata);

    //3.Send the request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //By using GSON we are able to convert Json Data which is coming from API to Java Object
    Map<String, Object> actualData = response.as(HashMap.class);
    System.out.println(actualData);

    //4.Step: Do assertion

    assertEquals(expecteddata.get("userId"), actualData.get("userId"));
    assertEquals(expecteddata.get("id"), actualData.get("id"));
    assertEquals(expecteddata.get("title"), actualData.get("title"));
    assertEquals(expecteddata.get("completed"), actualData.get("completed"));
    assertEquals(expecteddata.get("Status Code"), response.getStatusCode());
    assertEquals(expecteddata.get("Via"), response.getHeader("Via"));
    assertEquals(expecteddata.get("Server"), response.getHeader("Server"));





}


}
