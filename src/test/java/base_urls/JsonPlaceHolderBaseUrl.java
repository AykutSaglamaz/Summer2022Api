package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    // base_url icin bir package ve base_urls olusturuyorum ardindan istedigim her yerden buna erisebilirim.
    // Bu amacla, test methodda  her seferinde base_url olusturmak zorunda kalmiyorum

    //RequestSpecification data type'nde bir obje olusturun
    protected RequestSpecification spec;

    //Eger methodun uzerinde @Before annotation kullanirsaniz, bu method her bir test method'dan once calisir
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

}
