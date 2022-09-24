package get_http_request_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

public class DummyGet01 extends DummyApiBaseUrl {
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
             There are 24 employees in total
     */

    @Test
    public void test01(){

        //1.step: Set the URL
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //2.Step; Set the expected data

        //3.Step: Send request and get data

        //Create Response object and store it in response object container
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        //4.Step: Do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                body("data.id", hasSize(24));

    }

}
