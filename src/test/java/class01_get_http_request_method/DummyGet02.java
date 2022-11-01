package class01_get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class DummyGet02 extends DummyBaseUrl {
     /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            This user exists in the system
            {
            "id": 3,
            "employee_name": "Ashton Cox",
            "employee_salary": 86000,
            "employee_age": 66,
            "profile_image": ""
        },
     */
    @Test
    public void dummyGet01(){
        //1. Step: set the url
        spec.pathParams("first","api", "second", "v1","third","employees");

        //2. step: set the expected data

        //3. step: send a request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //4. step: Do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasItem(3), "data.employee_name", hasItem("Ashton Cox"), "data.employee_salary", hasItem(86000),
                        "status", equalTo("success"), "message", equalTo("Successfully! All records has been fetched."));



    }

}
