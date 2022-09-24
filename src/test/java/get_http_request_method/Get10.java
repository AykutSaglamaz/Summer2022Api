package get_http_request_method;

import Utils.JsonUtil;
import base_urls.GoRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestApiBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/13
    When
        User send Get Request to the URL
    And
        Response body should be like
            {
                "meta": null,
                "data": {
                    "id": 13,
                    "name": "Fr. Ajit Prajapat",
                    "email": "ajit_fr_prajapat@barrows.org",
                    "gender": "female",
                    "status": "active"
                }
            }
    */


    @Test
    public void get10(){
   //1.set the url
   spec.pathParams("first", "users", "second", 13);

   //2.step: set the expected data
    GoRestDataPojo dataPojo = new GoRestDataPojo("Fr. Ajit Prajapat", "ajit_fr_prajapat@barrows.org", "female", "active");

    GoRestPojo expectedDataPojo = new GoRestPojo(null, dataPojo);
    System.out.println(expectedDataPojo);

    //3.step; send the request and get the response
      Response response = given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();

      GoRestPojo actualDataPojo =  JsonUtil.convertJsonToJava(response.asString(), GoRestPojo.class);
        System.out.println(actualDataPojo);
    //4.Step: Do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
        assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(expectedDataPojo.getData().getEmail(), actualDataPojo.getData().getEmail());
        assertEquals(expectedDataPojo.getData().getGender(), actualDataPojo.getData().getGender());

    }
}
