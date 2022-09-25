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

        //2.yol, JsonPath kullanarak. JsonPath bir class'tir ve JSON data'nin icerisinde hareket etmek icin bircok kullanisli methodu vardir.
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        // JsonPath objesini response objesinden olusturma
        JsonPath json = response.jsonPath();
        assertEquals("Dane", json.getString("firstname"));
        assertEquals("Soyisim eslesmiyor", "Dominguez", json.getString("lastname"));
        assertEquals("Totalprice eslesmiyor", 111, json.getInt("totalprice"));
        assertEquals("Checkin date eslesmiyor", "2018-01-01", json.getString("bookingdates.checkin"));

    //3.yol,
        // i) SoftAssert objesi olusturarak
        SoftAssert softAssert = new SoftAssert();

        // ii) SoftAssert objesini kullanarak Assertion yap
        softAssert.assertEquals(json.getString("firstname"), "Dane", "Isim eslesmiyor");
        softAssert.assertEquals(json.getString("lastname"), "Dominguez", "Soyisim eslesmiyor");
        softAssert.assertEquals(json.getInt("totalprice"), 111, "Totalprice eslesmiyor");

        // MUTLAKA EN SONDA assertAll() yapilmali. Eger assertAll() kullanmazsaniz herzaman test gecti gorunur fakat bu anlamli olmayabilir
        softAssert.assertAll();

    }
}
