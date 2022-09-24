package get_http_request_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class DummyGet03 extends DummyApiBaseUrl {

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
             User can see following employees in the system
             Doris Wilder, Jenette Caldwell and Bradley Greer
     */

    @Test
    public void test03() {
//1.step: Set the URL
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //2.Step; Set the expected data

        //3.Step: Send request and get data
        //Response->return type, response -> container
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //do assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("data.employee_name", hasItems("Doris Wilder", "Jenette Caldwell", "Bradley Greer"));


    }


}
