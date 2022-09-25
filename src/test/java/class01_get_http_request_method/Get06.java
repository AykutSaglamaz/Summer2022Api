package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends HerOkuAppBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/5528
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                "firstname": "Dane",
                "lastname": "Dominguez",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": { "checkin": "2018-01-01",
                                  "checkout":"2019-01-01"
                                  },
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get06(){
        //1.Adim: set URL
        spec.pathParams("first", "booking", "second", 5528);

        //2.Adim: Set expected data

        //3.Adim: GET Request gonder ve Get Response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Adim: Assertion yap
        //1.yol:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Dane")).
                body("lastname", equalTo("Dominguez")).
                body("totalprice", equalTo(111)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2018-01-01")).
                body("bookingdates.checkout", equalTo("2019-01-01")).
                body("additionalneeds", equalTo("Breakfast"));


    }
}
