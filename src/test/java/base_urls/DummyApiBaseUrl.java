package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyApiBaseUrl {

protected RequestSpecification spec;


 @Before
public void setUp(){

     spec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/").build();


        }
    }


