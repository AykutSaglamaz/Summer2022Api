package get_http_request_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyGet04 extends DummyApiBaseUrl {
   /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            This user exists in the system
            "status": "success",
            "data": {
                "id": 5,
                "employee_name": "Airi Satou",
                "employee_salary": 162700,
                "employee_age": 33,
                "profile_image": ""
            },
    "message": "Successfully! Record has been fetched."
        },
     */

    @Test
    public void test04(){
        //1.step: Set the URL
        spec.pathParams("first", "api", "second", "v1", "third", "employee", "fourth", 5);
        //2.Step; Set the expected data

        //3.Step: Send request and get data
        //Response->return type, response -> container
     Response response =  given().spec(spec).when().get("/{first}/{second}/{third}/{fourth}");
     response.prettyPrint();

     //4.Step: Do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("status", equalTo("success")).
                body("data.id", equalTo(5)).
                body("data.employee_salary", equalTo(433060)).
                body("data.employee_age", equalTo(22)).
                body("message", equalTo("Successfully! Record has been fetched."));






    }




}
