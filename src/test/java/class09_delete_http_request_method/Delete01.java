package class09_delete_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            Url'e DELETE Request gonder
        Then
            Status code  200 olmali
        And
            Response body is {}
     */

    @Test
    public void del01(){

        //1. step : set the url
        spec.pathParams("first", "todos", "second", 198);

        //2.Set the expected data
        Map<String, Object> expectedMap = new HashMap<>();

        //3.Step: send the request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.step: Do assertion/verification
            //GSON==> de-serialization
        Map<String ,Object> actualMap = response.as(HashMap.class);

        response.
                then().
                assertThat().
                statusCode(200);
        //1.yol
        assertEquals(expectedMap, actualMap);
        //2.yol==> recommended
        assertTrue(actualMap.isEmpty());
        //3.yol
        assertTrue(actualMap.size()==0);

    }

}
