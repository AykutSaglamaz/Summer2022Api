package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/5450
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates":
                   {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                    }
                  "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get09(){

        //1. Step: Set the Url
        spec.pathParams("first", "booking", "second", 5450);

        //2.Step: Set the expected data

        Map<String, String> expectedbookingdates = new HashMap<>();
        expectedbookingdates.put("checkin", "2018-01-01");
        expectedbookingdates.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "James");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", expectedbookingdates);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println(expectedData);

        //3.Step: send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Do assertion
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        assertEquals(expectedbookingdates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedbookingdates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));


    }
}
