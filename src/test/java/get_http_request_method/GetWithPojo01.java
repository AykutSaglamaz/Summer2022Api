package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetWithPojo01 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/2
    When
        I send GET Request to the URL
    Then
        Status code is 200
    And response body is like
            {
                "firstname": "Mary",
                "lastname": "Smith",
                "totalprice": 502,
                "depositpaid": false,
                "bookingdates": {
                                   "checkin": "2016-02-05",
                                    "checkout": "2021-01-16"
                    }
                 "additionalneeds": "Breakfast"
              }
     */

    @Test
    public void getWithPojo01(){
       //1.step: set the url

       spec.pathParams("first", "booking", "second", 2);

       //2.step: set the expected data

        BookingDatesPojo bookingDates = new BookingDatesPojo("2016-12-06","2021-03-21");
        //expectedData is given by user
        BookingPojo expectedData = new BookingPojo("James", "Brown", 2400, false, bookingDates, "Breakfast");

        //3.step :  Send the request and get the response

        Response response =  given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: do assertion/verification
        //Using Do-Serialization
        BookingPojo actualData= response.as(BookingPojo.class);

        assertEquals(200,response.getStatusCode() );
        assertEquals("Firstname are not matching ", expectedData.getFirstname(), actualData.getFirstname());
        assertEquals("Lastname are not matching ", expectedData.getLastname(), actualData.getLastname());
        assertEquals("Total Price are not matching ", expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals("Deposit paid are not matching ", expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals( "Check in dates are not matching ", expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates are not matching ", expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());


    }

}
