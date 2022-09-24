package post_http_request_method;

import Utils.JsonUtil;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostWithObjectMapperAndPojo01 extends JsonPlaceHolderBaseUrl {

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

public void PostWithObjectMapperAndPojo01(){
//1.step: set the url
spec.pathParam("first", "todos");

//2.step
JsonPlaceHolderTestData expected = new JsonPlaceHolderTestData();
String expectedData = expected.expectedDataInString(55, "Tidy your room", false);

JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJava(expectedData, JsonPlaceHolderPojo.class);
System.out.println(expectedDataPojo);

//3.step: send the request and get the response
Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
response.prettyPrint();

JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

//4. Do assertion
    assertEquals(201, response.getStatusCode());
    assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
    assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
    assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());

}
}
