package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/560
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                "firstname": "Jim",
                "lastname": "Brown",
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
        spec.pathParams("first", "booking", "second", 560);

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
                contentType("text/html").
                body("firstname", equalTo("Jim")).
                body("lastname", equalTo("Brown")).
                body("totalprice", equalTo(111)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2018-01-01")).
                body("bookingdates.checkout", equalTo("2019-01-01")).
                body("additionalneeds", equalTo("Breakfast"));

        //2.yol, JsonPath kullanarak
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        // JsonPath objesini response objesi kullanarak olusturma
        //JsonPath kullanarak. JsonPath bir class'tir ve JSON data'nin icerisinde hareket etmek icin bircok kullanisli methodu vardir.
        JsonPath json = response.jsonPath();
        assertEquals("Jim", json.getString("firstname"));
        assertEquals("Soyisim eslesmiyor", "Brown", json.getString("lastname"));
        assertEquals("Totalprice eslesmiyor", 111, json.getInt("totalprice"));
        assertEquals("Checkin date eslesmiyor", "2018-01-01", json.getString("bookingdates.checkin"));

    //3.yol,
        // i) SoftAssert objesi olusturarak
        SoftAssert softAssert = new SoftAssert();

        // ii) SoftAssert objesini kullanarak Assertion yap
        softAssert.assertEquals(json.getString("firstname"), "Jim", "Isim eslesmiyor");
        softAssert.assertEquals(json.getString("lastname"), "Brown", "Soyisim eslesmiyor");
        softAssert.assertEquals(json.getInt("totalprice"), 111, "Totalprice eslesmiyor");

        // iii) MUTLAKA EN SONDA assertAll() yapilmali. Eger assertAll() kullanmazsaniz herzaman test gecti gorunur fakat bu anlamli olmayabilir
        softAssert.assertAll();

    }
}
