package class01_get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class DummyGet05 extends DummyBaseUrl {
     /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
             Status Line should be HTTP/1.1 200 OK
         And
             User can see following employee ids in the system
             3 , 7 , 11

     */
    @Test
    public void dummyGet05(){
        //1.adim: set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //2.adim: set the expected data

        //3.adim: send request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //4.adim: do assertion
        //1.yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("data.id", hasItems(3, 7, 11));





    }
}
