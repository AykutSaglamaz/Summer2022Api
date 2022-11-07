package class01_get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Dane&lastname=Dominguez
        When
            Kullanici GET requesti URL'e gonderir
        Then
            Status code : 200
	  	And
	  	    Data'lar arasinda ismi (firstname) “Dane” ve soyismi (lastname) “Dominguez” olan biri olmali
     */

// Query Params' spesifik parametreler secmek icindir (orn. ?firstname=Dane&lastname=Dominguez)
// pathparams ise resource (kaynagi) kucultmek/daraltmak icindir

    @Test
    public void get05(){
       //1. adim: set URL
       spec.
            pathParam("first", "booking").
            queryParams("firstname","Dane", "lastname", "Dominguez");

        //2.Adim set expected data

        //3.Adim: GET Request gonder ve GET Response al
        Response response = given().spec(spec).when().get("/{first}");
        response.print();
//        response.prettyPrint();

        //4.Adim: Assertion yap
     /*   response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200).
                body("bookingid", hasItem(650));
     */
        response.then().assertThat().statusCode(200);
        //"bookingid" var mi yok mi
        assertTrue(response.asString().contains("bookingid"));

    }

}
