package getRequest;

import base_urls.DummyApiBaseUrl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 extends DummyApiBaseUrl {
    /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            Make sure Rhona Davidson earns more than Herrod Chandler
           {
            "id": 7,
            "employee_name": "Herrod Chandler",
            "employee_salary": 137500,
            "employee_age": 59,
            "profile_image": ""
            },
            {
            "id": 8,
            "employee_name": "Rhona Davidson",
            "employee_salary": 327900,
            "employee_age": 55,
            "profile_image": ""
         },
     */
    @Test
    public void test09(){
        //set the url
        spec.pathParams("first","api","second","v1","third","employee", "final", 7);

       Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
        System.out.println("employee_salary => "+ json.getString("data.employee_salary"));

        spec.pathParams("first","api","second","v1","third","employee", "final", 8);

        Response response1 = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");

        JsonPath json1 = response1.jsonPath();
        System.out.println("employee_salary => "+ json1.getString("data.employee_salary"));

        Assert.assertTrue(Integer.parseInt(json.getString("data.employee_salary"))<Integer.parseInt(json1.getString("data.employee_salary")));
    }

}
