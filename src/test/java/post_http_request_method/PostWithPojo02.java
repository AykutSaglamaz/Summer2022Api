package post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PostWithPojo02 extends HerOkuAppBaseUrl {
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
                         "checkin": "2021-09-21",
                         "checkout": "2021-12-21"
                         }
                "additionalneeds": "Breakfast with coffee, Dragon Juice"
              }
    When
             I send POST Request to the URL
    Then
            Status code is 200
    And
            Response body is like
            {
                "firstname": "Mary",
                "lastname": "Smith",
                "totalprice": 647,
                "depositpaid": false,
                "bookingdates":
                      {
                      "checkin": "2016-02-05",
                      "checkout": "2021-01-16"
                      }
                "additionalneeds": "Breakfast"
           }
 */

 @Test
 public void postWithPojo02() {

  //1. step set the url
  spec.pathParam("first", "booking");

  //2.Step: Set request body
  BookingDatesPojo bookingDates = new BookingDatesPojo("2016-02-05", "2021-01-16");

  BookingPojo requestBody = new BookingPojo("Aykut", "Saglam", 998, true, bookingDates, "Breakfast with coffee, Dragon Juice");

  //3.step: send the request and get the response
  Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
  response.prettyPrint();

  HerOkuAppPostResponseBodyPojo actualData = response.as(HerOkuAppPostResponseBodyPojo.class);

  //4. Step do assertion using de-serialization
  assertEquals(200, response.getStatusCode());

  assertEquals(requestBody.getFirstname(), actualData.getBooking().getFirstname());
  assertEquals(requestBody.getLastname(), actualData.getBooking().getLastname());
  assertEquals(requestBody.getTotalprice(), actualData.getBooking().getTotalprice());
  assertEquals(requestBody.getDepositpaid(), actualData.getBooking().getDepositpaid());
  assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
  assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
  assertEquals(requestBody.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());


 }

 @Test
 public void postWithPojo03() {


  //1. step set the url
  spec.pathParam("first", "booking");

  //2.Step: Set request body
  BookingDatesPojo bookingDates = new BookingDatesPojo("2016-02-05", "2021-01-16");

  BookingPojo requestBody = new BookingPojo("Aykut", "Saglam", 998, true, bookingDates, "Breakfast with coffee, Dragon Juice");

  //3.step: send the request and get the response
  Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
  response.prettyPrint();

  JsonPath json = response.jsonPath();
  Integer bookingId = json.getInt("bookingid");

  // set the url for Get Request
  spec.pathParams("first", "booking", "second", bookingId);
  // set the get Request
   Response response1 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
    response1.prettyPrint();

// do assertion
  BookingPojo actualData = response1.as(BookingPojo.class);

  assertEquals(200, response1.getStatusCode());
  assertEquals(requestBody.getFirstname(), actualData.getFirstname());
  assertEquals(requestBody.getLastname(), actualData.getLastname());
  assertEquals(requestBody.getTotalprice(), actualData.getTotalprice());
  assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
  assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
  assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());


 }

}