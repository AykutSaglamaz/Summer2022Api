package class01_get_http_request_method;


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
        Url'e GET Request gonder
    Then
       Status code is 200
       And response body is like {
                                    "userId": 10,
                                    "id": 198,
                                    "title": "quis eius est sint explicabo",
                                    "completed": true
                                  }
     */
// daha sonra buna bakilacak
    public static void main(String[] args) {
        JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
        data.expectedDataInString(10, "asdasfd",true);

    }

    @Test
    public void getWithObjectMapper01(){
        //1.step: set the Url
        spec.pathParams("first","todos","second",198);
        //2.step: set the expected data
    //1.yol ===> Works but not recommended
/*
        String expectedData = "{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
         HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);
*/
      //2.yol
        JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
        String expectedData = data.expectedDataInString(10, "quis eius est sint explicabo",true);

        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);

    // 3.step: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        //4.step: do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        //    assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));  // we do not normally do assertion for id because api assigns id for every different data
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }
}