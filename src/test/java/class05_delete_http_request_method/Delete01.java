package class05_delete_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
            And Response body is {}
     */

    @Test
    public void delete01() {

        //1. step : set the url
        spec.pathParams("first", "todos", "second", 198);

        //2.Set the expected data
        Map<String, Object> expectedMap = new HashMap<>();

        //3.Step: send the request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}");
        response.prettyPrint();

        //GSON ==> de-serialization
        Map<String, Object> actualMap = response.as(HashMap.class);

        //4.step: Do assertion/verification

        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap, actualMap);
        assertTrue(actualMap.size() == 0);


    }
}
