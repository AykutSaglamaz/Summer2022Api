package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetWithPojo01 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/2
    When
       Url'e GET Request gonder
    Then
        Status code is 200
    And response body is like
            {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 2400,
                "depositpaid": true,
                "bookingdates": {
                                   "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                    }
                 "additionalneeds": "Breakfast"
              }
     */

    @Test
    public void getWithPojo01(){
        //1.step: set the url

        spec.pathParams("first", "booking", "second", 2);

        //2.step: set the expected data

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        //expectedData is given by user
        BookingPojo expectedData = new BookingPojo("James", "Brown", 2400, true, bookingDates, "Breakfast");
//        System.out.println(expectedData);

        //3.step :  Send the request and get the response
        Response response =  given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: do assertion/verification
        //Using De-Serialization
        BookingPojo actualData= response.as(BookingPojo.class);

        assertEquals(200,response.getStatusCode() );
//        assertTrue(expectedData.getFirstname().equals(actualData.getFirstname()));
        assertEquals("Firstname are not matching: ", expectedData.getFirstname(), actualData.getFirstname());
        assertEquals("Lastname are not matching: ", expectedData.getLastname(), actualData.getLastname());
        assertEquals("Total Price are not matching: ", expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals("Deposit paid are not matching: ", expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals( "Check in dates are not matching: ", expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates are not matching: ", expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());


    }

}
