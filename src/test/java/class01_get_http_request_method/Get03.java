package class01_get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           Kullanici GET Request'i Url'e (APi) gonderir
       Then
           HTTP Status Code 200 olmali
       And
           Response formati “application/json” olmali
       And
           “title”, “et itaque necessitatibus maxime molestiae qui quas velit” olmali,
       And
           “completed” is false
       And
           “userId”  2 olmali
    */
    @Test
    public void get03(){

        //1.Step: Set the Url
        spec.pathParams("birinci","todos", "ikinci", 23);

        //2.Step: Set the expected data

        //3.Step: GET Request gonder ve GET Response al
        Response response = given().spec(spec).when().get("/{birinci}/{ikinci}");
        response.prettyPrint();

        //4.Step: Do assertion
        //Not:  "application/json" kullanmak yerine ContentType.JSON kullanilabilir
        //1.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));
        //2.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));
    }
}
