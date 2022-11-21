package class01_get_http_request_method;

import base_urls.GoRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestApiBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        Url'e Get Request gonder
    Then
        "pagination total" degeri 4007'dur
    And
        "current Link"  "https://gorest.co.in/public/v1/users?page=1" olmali
    And
        User sayisi 10 olmali
    And
        active user 5'ten fazla olmali
    And
        "Narayan Mahajan", "Chapal Pilla", "Jai Sharma" kullanicilar arasindadir
    And
        female users, male users'dan daha fazladir
     */
    @Test
    public void get11(){
        //1.adim: url'i set et
        spec.pathParam("first", "users");

        //2.adim: expected datayi set

        //3.adim: GET Request gonder, get response al
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //4.adim: assertion
        //1.yol
        response.
                then().
                statusCode(200).
                body("meta.pagination.total", equalTo(4002),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10), "data.status", hasItem("active"),
                        "data.name", hasItems("Jai Sharma", "Tejas Desai", "Saroja Kaul"));

        //2.yol: JsonPath kullan
        JsonPath json =  response.jsonPath();
        assertEquals(4002, json.getInt("meta.pagination.total"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
        assertEquals(10, json.getList("data.id").size());

        assertTrue(json.getList("data.status").contains("active"));

        List<String> NameList = Arrays.asList("Jai Sharma", "Tejas Desai", "Saroja Kaul");
        assertTrue(json.getList("data.name").containsAll(NameList));

        //female users, male users'dan daha fazladir
        //1.yol
        List<String> genderList = json.getList("data.gender");
        System.out.println(genderList);

        int femaleCounter= 0;
        for (String w:genderList){
            if (w.equals("female")){
                femaleCounter++;
            }
        }
        assertTrue(femaleCounter == genderList.size() - femaleCounter);
        //2.yol groovy
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size() >= maleList.size());


        //active user 5'ten fazla olmali
        //1.yol
        List<String> statusList = json.getList("data.status");
        System.out.println(statusList);

        int statusCounter= 0;
        for (String w:statusList){
            if (w.equals("inactive")){
                statusCounter++;
            }
        }
        assertTrue(statusCounter>5);

        //2.yol==> groovy kullan
        List<String> activeStatusList =  json.getList("data.findAll{it.status='active'}.status");
        System.out.println(activeStatusList);
        assertTrue(activeStatusList.size()>5);

    }
}
