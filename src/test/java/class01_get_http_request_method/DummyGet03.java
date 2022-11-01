package class01_get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

public class DummyGet03 extends DummyBaseUrl {
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
    public void dummyGet03(){
        //1.step: set the url
        spec.pathParams("first","api", "second", "v1","third","employees");
        //2. set the expected date

        //3. step: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        //4. step: Do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("data.employee_name", hasItems("Doris Wilder", "Jenette Caldwell", "Bradley Greer"));





    }
}
