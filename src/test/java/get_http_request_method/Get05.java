package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  	    Among the data there should be someone whose firstname is “Mary” and last name is “Ericsson”
     */
// Query Params is for selecting specific parameter (ex. ?firstname=Mary&lastname=Ericsson)

@Test
    public void get05(){

    //1.step: Set the URL
    spec.
            pathParam("first", "booking").
            queryParams("firstname", "Dane", "lastname", "Dominguez");

    //2.Step; Set the expected data

    //3.Step: Sent request and get data
    Response response = given().spec(spec).when().get("/{first}");

    response.prettyPrint();

    //4.Step: Do assertion

    response.then().assertThat().statusCode(200);
//To check "bookingid" exists or not
    assertTrue(response.asString().contains("bookingid"));




}

}
