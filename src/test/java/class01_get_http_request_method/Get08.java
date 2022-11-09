package class01_get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08  extends JsonPlaceHolderBaseUrl {
    /*
            API test'te en buyuk zorluk data type'tir
     1) Java Objects (butun non-primitiveler), Maps ve Primitive'leri data type olarak kullanir.
        API ise  XML, Json gibi formatlari kullanir.
        Java ve API farkli data type kullanir bunlarin birbirleriyle iletisim kurmasi gerekir
        Data type'lar farkli olunca iletisim (Communication) kurmak mumkun degildir.

            --Bunun icin 2 tane secenegimiz var:

        i) Data type'i Json'dan Java object cevirir ==>De-Serialization
        ii) Data type'i Java Object'ten Json'e cevirir  ==> Serialization

        Serialization and De-Serialization icin 2 tane secenegimiz var
            a) GSON--> Google olusturur
            b) Object Mapper --> daha populerdir
     */
/*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
 */

    @Test
    public void get08() {

        //1. step: set the url

        spec.pathParams("first", "todos", "second", 2);

        //2. step : Set the expected data
        Map<String, Object> expecteddata = new HashMap<>();// HashMap<>() kullandim cunku Map'de en hizlisi o'dur
        expecteddata.put("userId", 1);
        expecteddata.put("id", 2);
        expecteddata.put("title", "quis ut nam facilis et officia qui");
        expecteddata.put("completed", false);
        expecteddata.put("Status Code", 200);
        expecteddata.put("Via", "1.1 vegur");
        expecteddata.put("Server", "cloudflare");
        System.out.println(expecteddata);// HashMap elemanlari rastgele ekler bu nedenle hizlidir

        //3.Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // GSON kullanarak,API'dan gelen Json Data'yi Java Object formatina cevirebiliriz
        Map<String, Object> actualData = response.as(HashMap.class);// GSON olan as() kullanilarak response objesindeki Json formati hashMap ceviririz
        System.out.println(actualData);

        //4.Step: Do assertion

        assertEquals(expecteddata.get("userId"), actualData.get("userId"));
        assertEquals(expecteddata.get("id"), actualData.get("id"));
        assertEquals(expecteddata.get("title"), actualData.get("title"));
        assertEquals(expecteddata.get("completed"), actualData.get("completed"));
        assertEquals(expecteddata.get("Status Code"), response.getStatusCode());
        assertEquals(expecteddata.get("Via"), response.getHeader("Via"));
        assertEquals(expecteddata.get("Server"), response.getHeader("Server"));


    }
}
