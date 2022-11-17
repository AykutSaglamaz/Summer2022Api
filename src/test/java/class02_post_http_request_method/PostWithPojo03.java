package class02_post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithPojo03 extends HerOkuAppBaseUrl {
/*
    Given
             https://restful-booker.herokuapp.com/booking/

             {
                "firstname": "Aykut",
                "lastname": "Saglam",
                "totalprice": 998,
                "depositpaid": true,
                "bookingdates":
                        {
                         "checkin": "2022-11-05",
                         "checkout": "2022-11-21"
                         },
                "additionalneeds": "Breakfast with coffee, Dragon Juice"
              }
    When
            URL'e POST Request gonder
            URL'e GET request gonder ( yeni olusturulan datayi almak icin)
    Then
            Status code 200 olmali
    And
            Response body asagidaki gibi olmali
                  {
                 "firstname": "Aykut",
                 "lastname": "Saglam",
                 "totalprice": 998,
                 "depositpaid": true,
                 "bookingdates": {
                                "checkin": "2022-11-05",
                                "checkout": "2022-11-21"
                                   },
                 "additionalneeds": "Breakfast with coffee, Dragon Juice"
                     }
 */

    @Test
    public void postWithPojo03() {


        //1. step set the url
        spec.pathParam("first", "booking");

        //2.Step: Set request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-11-05", "2022-11-21");

        BookingPojo requestBody = new BookingPojo("Aykut", "Saglam", 998, true, bookingDates, "Breakfast with coffee, Dragon Juice");

        //3.step: send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

    // DB'de yeni data olusturduktan sonra, "Get" methodunda kullanmak icin bizim "bookingid" ye ihtiyacimiz var
        // "bookingid" yeni olusturulan POST Response Body'den alinir
        JsonPath json = response.jsonPath();
        Integer bookingId = json.getInt("bookingid");

        // set the url for Get method
        spec.pathParams("first", "booking", "second", bookingId);

        // send the GET Request and get response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response1.prettyPrint();

// do assertion
        //response1'i Pojo ya cevir
        BookingPojo actualData = response1.as(BookingPojo.class);

        assertEquals(200, response1.getStatusCode());
        assertEquals(requestBody.getFirstname(), actualData.getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());


    }
}
