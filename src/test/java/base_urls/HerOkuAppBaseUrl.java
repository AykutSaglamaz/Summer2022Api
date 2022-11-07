package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {

    //for base_url I create a package and I create base_urls and I use from there.
    // So, in test method I do not create base_url again and again

    //RequestSpecification data type' da bir obje olusturun
    protected RequestSpecification spec;

    //Eger methodun uzerinde @Before annotation kullanirsaniz, bu method her bir test method'dan once calisir
    // @Before anotation i ne zaman kullanirsiniz,
    // cevap: eger ben bir methodun herbir test methodundan once calismasini istiyorsam @Before kullanirim
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
