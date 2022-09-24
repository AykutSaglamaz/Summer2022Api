package getRequest;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get02 extends JsonPlaceHolderBaseUrl {
    /*
         When
             I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
         Then
             HTTP Status Code should be 200
        And
             Search all ids that are less than 30
             the number of ids less than 30 should be 29
     */
@Test
    public void test10(){
    //set the url
     spec.pathParam("first", "comments");

     Response response = given().spec(spec).when().get("/{first}");
     response.prettyPrint();

    JsonPath json = response.jsonPath();

    List<Integer> actualIds = json.getList("findAll{it.id<30}.id");
    System.out.println("actual id list => " + actualIds);

    Assert.assertTrue("the expected data does not match ", actualIds.size()==29);



}



}
