package Utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {


    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    //1. Method: Json data'yi Java Object cevirmek icin kullanilir => De-Serialization
    //generic method
    public static <T> T convertJsonToJava(String json, Class<T> cls){

        T javaResult = null;
        try {
            javaResult =  mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object "+ e.getMessage());
        }
        return javaResult;
    }


    //2. Method: Java Object"i Json data cevirmek icin kullanilir => Serialization
    public static String convertJavaToJson(Object obj){
        String jsonResult = null;

        try {
            jsonResult =  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Json object could not be converted to Java "+ e.getMessage());
        }
        return jsonResult;

    }
}
