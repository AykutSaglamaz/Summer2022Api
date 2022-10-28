package class01_get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {

/*
       Given
            https://jsonplaceholder.typicode.com/todos
        When
             I send GET Request to URL
         Then
             Status code is 200
             1)Print all ids greater than 190 on the console
               Assert that there are 10 ids greater than 190
             2)Print all userIds less than 5 on the console
               Assert that maximum userId less than 5 is 4
             3)Print all titles whose ids are less than 5
               Assert that "delectus aut autem" is one of the titles whose id is less than 5
 */

    @Test
    public void  get07() {

        //1. Step: set the url
        spec.pathParam("first", "todos");

        //2.Set teh expected data

        //3.Send request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. do assertion
        response.then().assertThat().statusCode(200);

        //1-Print all ids greater than 190 on the console
        JsonPath json = response.jsonPath();

        List<Integer> idList = json.getList("findAll{it.id>190}.id"); //Groovy Language->
        // it-> from the Json Data which we are working in
        /* this.isim => icinde bulundugunuz siniftaki isim
         it.id => icinde bulundugunuz Json datadaki id */
        System.out.println(idList);//[191, 192, 193, 194, 195, 196, 197, 198, 199, 200]

        //1-2-Assert that there are 10 ids greater than 190
        assertEquals("id list does not have expected size", 10, idList.size());

        //2-Print all userIds less than 5 on the console

        List<Integer> useridList = json.getList("findAll{it.userId<5}.userId");// icinde bulundugun Json datadaki userId 5'ten kucuk olanin userId'lerini yazdir
        /* this.isim => icinde bulundugunuz siniftaki isim */
        /* it.id => icinde bulundugunuz Json datadaki id */
        System.out.println(useridList);

        //2-2-Assert that maximum userId less than 5 is 4
        Collections.sort(useridList);//Elements sorted in ascending order
        assertEquals((Integer) 4, useridList.get(useridList.size()-1));

     //3-Print all titles whose ids are less than 5
        List<String> titleList = json.getList("findAll{it.userId<5}.title");
        System.out.println(titleList);

        //3-2-assert that "delectus aut autem" is one of the titles whose id is less than 5

        //1.way
        assertTrue(titleList.contains("delectus aut autem"));
        //2.way
        assertTrue(titleList.stream().anyMatch(t->t.equals("delectus aut autem")));


    }
}
