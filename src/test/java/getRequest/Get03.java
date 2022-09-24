package getRequest;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.PracticeJsonPlacePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /*
         When
             I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
         Then
             HTTP Status Code should be 200
        And
             Search all ids and make sure there are 500 total records
             Use Pojo and deserialize them to Java
     */

@Test
    public void Get03WithPojo () {

    spec.pathParam("first", "comments");

    Response response =  given().spec(spec).when().get("/{first}");
   // response.prettyPrint();

    PracticeJsonPlacePojo [] actualData = response.as(PracticeJsonPlacePojo[].class);

    for (int i =0; i<actualData.length; i++){
        System.out.println(i+" name => "+ actualData[i].getName());
    }
    Assert.assertTrue("The expected data does not match", actualData.length==500);




}
}
