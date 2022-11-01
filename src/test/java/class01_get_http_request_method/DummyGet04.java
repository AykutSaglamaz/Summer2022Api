package class01_get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class DummyGet04 extends DummyBaseUrl {
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
                          },
           "message": "Successfully! Record has been fetched."
     */
    @Test
    public void dummyGet04(){
        //1.adim: set the url
        spec.pathParams("first","api", "second", "v1","third","employees");
        //2.adim: set the expectedData

        //3.adim: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //4.adim: do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("status", equalTo("success")).
                body("data.id", hasItem(5)).
                body("data.employee_name", hasItem("Airi Satou")).
                body("data.employee_salary", hasItem(162700)).
                body("data.employee_age", hasItem(33)).
                body("message", equalTo("Successfully! All records has been fetched."));


    }

}
