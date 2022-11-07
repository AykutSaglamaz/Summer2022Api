package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Get02 extends HerOkuAppBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            Kullanici GET Request'i Url'e (APi) gonderir
        Then
            HTTP Status code 404 olmali
        And
            Status Line 'HTTP/1.1 404 Not Found' olmali
        And
            Response body "Not Found" icerir
        And
            Response body "ArcaneData" icermez
        And
            Server "Cowboy" olmali
     */
    //Not: Path Parameters: resource'u daha kucuk (daha belirgin) yapmak icin kullanilir

    @Test
    public void get02(){

    //1.Adim: Set URL
    //String url = "https://restful-booker.herokuapp.com/booking/1001"; ==> Not recommended
        //base_url sinifinda HerOkuAppBaseUrl sinifi olusturur ve lazim oldugunda kullanirim

        spec.pathParams("first","booking", "second", 1001);
        //https://restful-booker.herokuapp.com -> base parameter
        //booking -> first parameter
        //1001 -> second parameter

    //2.Adim: Set the expected data (beklenen)

    //3.Adim: GET Request gondermek ve GET Response almak icin Atomasyon kodunu yaz
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();// to print body on the console -> Not Found

    //4.Adim: Do Assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

    //assertTrue(true) ==> Green tick            assertTrue(false) ==> Red cross
        assertTrue(response.asString().contains("Not Found"));
        //If response.asString().contains("Not Found") returns true, you will get green tick

    //assertFalse(false) ==> Green tick            assertFalse(true) ==> Red cross
        assertFalse(response.asString().contains("ArcaneData"));
        //If response.asString().contains("ArcaneData") returns false, you will get green tick

    //Expected Data test case'den gelir, Actual Data ise  API'dan gelir
    //assertEquals()  true (dondurur) (test passes)  eger argumentlar eslesirse
        //argument ==> real data you used inside the method
        assertEquals("Cowboy", response.getHeader("Server"));
    }





}
