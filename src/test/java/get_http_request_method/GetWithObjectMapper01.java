package get_http_request_method;

import Utils.JsonUtil;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
            I send GET Request to the URL
    Then
        Status code is 200
        And response body is like {
                                    "userId": 10,
                                    "id": 198,
                                    "title": "quis eius est sint explicabo",
                                    "completed": true
                                  }
     */

    public static void main(String[] args) {
        JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
        data.expectedDataInString(10, "asdasfd",true);

    }

    @Test
    public void getWithObjectMapper01(){
        spec.pathParams("first","todos","second",198);
/*
        String expectedData = "{\n" +                       Works but not recommended
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
*/
        JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
        String expectedData = data.expectedDataInString(10, "quis eius est sint explicabo",true);

        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        //    assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));  // we do not normally do assertion for id because api assigns id for every different data
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }
}