package post_http_request_method;

import Utils.JsonUtil;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {
 /*
    Given
        https://restful-booker.herokuapp.com/booking
        {
        "firstname": "James",
        "lastname": "Can",
        "totalprice": 1245,
        "depositpaid": true,
        "bookingdates": {
                "checkin": "2022-09-09",
                "checkout": "2022-09-21"
            },
         "additionalneeds": "Orange juice"
           }
    When
         Url'e POST Request gonderdim
    And
         Url'e Get Request gonderdim
    Then
        Status code is 200
    And
        GET response body asagidaki gibi olmali
                {
                        "bookingid": 40208,
                        "booking": {
                        "firstname": "James",
                        "lastname": "Can",
                        "totalprice": 1245,
                        "depositpaid": true,
                        "bookingdates": {
                                "checkin": "2022-09-09",
                                "checkout": "2020-09-21"
                            },
                        "additionalneeds": "Orange juice"
                        }
                   }
  */
    @Test
    public void postGetWithObjectMapperAndPojo01(){
        //1.adim: Url'e set et
        spec.pathParam("first", "booking");

        //2. adim: expected (request) datayi set et
        BookingDatesPojo bookingDate = new BookingDatesPojo("2022-09-09", "2022-09-21");
        BookingPojo expectedData = new BookingPojo("James","Can",1245, true, bookingDate, "Orange juice");

        //3.adim: Request gonder, response al
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        HerOkuAppPostResponseBodyPojo postResponsePojo = JsonUtil.convertJsonToJava(response.asString(), HerOkuAppPostResponseBodyPojo.class);
        System.out.println(postResponsePojo);

        Integer bookingid = postResponsePojo.getBookingid();

        //1.adim: Url'i GET icin set et
        spec.pathParams("first", "booking", "second", bookingid);

        //2 adim: expected data set et
        // Expected datayi set etmeye gerek yoktur cunku POST request ile gonderdigim expected data ile aynidir

        //3. adim: Get Request gonder, response al
        Response response1 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response1.prettyPrint();

        BookingPojo getResponsePojo = JsonUtil.convertJsonToJava(response1.asString(), BookingPojo.class);
        System.out.println(getResponsePojo);

        //4.adim: Assertion yap
        response1.then().statusCode(200);
        assertEquals(200, response1.getStatusCode());
        assertEquals(postResponsePojo.getBooking().getFirstname(), getResponsePojo.getFirstname());
        assertEquals(postResponsePojo.getBooking().getLastname(), getResponsePojo.getLastname());
        assertEquals(postResponsePojo.getBooking().getBookingdates().getCheckout(), getResponsePojo.getBookingdates().getCheckout());
        //veya
        assertEquals(expectedData.getTotalprice(), getResponsePojo.getTotalprice());
        assertEquals(expectedData.getBookingdates().getCheckin(), getResponsePojo.getBookingdates().getCheckin());
        assertEquals(expectedData.getAdditionalneeds(), getResponsePojo.getAdditionalneeds());





    }
}
