package class01_get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyGet07 extends DummyBaseUrl {
    /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            This user exists in the system
            {
            "id": 18,
            "employee_name": "Gloria Little",
            "employee_salary": 237500,
            "employee_age": 59,
            "profile_image": ""
            },
          And
            Make sure Gloria Little earns more than Bradley Greer
            "id": 19,
            "employee_name": "Bradley Greer",
            "employee_salary": 132000,
            "employee_age": 41,
            "profile_image": ""
     */

    @Test
    public void dummyGet07(){
        //1.adim: set the url
        spec.pathParams("first","api","second","v1","third","employee","final",18);
        //2.adim: set the expected data

        //3.adim: send request and get response(data)
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();

        //4.adim: do assertion/verification/validation
        //1.yol
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body(
                "data.id",equalTo(18),"data.employee_name",equalTo("Gloria Little"),
                "data.employee_salary",equalTo(237500),"data.employee_age",equalTo(59)
        );
        //2.yol
        JsonPath json = response.jsonPath();
        spec.pathParams("first","api","second","v1","third","employee","final",19);

        Response response2 = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response2.prettyPrint();

        JsonPath json2 = response2.jsonPath();

        Assert.assertTrue(Integer.parseInt(json.getString("data.employee_salary"))>Integer.parseInt(json2.getString("data.employee_salary")));
    }
}
