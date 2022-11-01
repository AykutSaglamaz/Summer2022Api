package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseUrl {
    //Create an object in RequestSpecification data type
    protected RequestSpecification spec;


    @Before
    //If you use @Before annotation at the top of a method, it means the method will be executed before every test method
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/").build();
    }
}
