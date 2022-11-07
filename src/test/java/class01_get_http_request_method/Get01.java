package class01_get_http_request_method;

import io.restassured.*;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
//JUnit is a framework to make testing easy

public class Get01 {
    /*
        APi test'te Gherkin dilini (Language) kullaniriz
   Gherkin dilinde bazi anahtar kavramlar kullaniriz: Given, When, Then, And
    ((given) Size bir kitap verildi , (when)okudugunuzda, (Then) basarili olursunuz)
        Given : On kosullari bildirir (Sartlar) (baslangic)
        When : Hareketleri (yapacagimiz isi) bildirmek icin kullanilir
        Then : Sonuc icin kullanilir
        And : Coklu 'Given, When, Then' icin kullanilir
     */

    /*         test case olusturma
       Given
           https://restful-booker.herokuapp.com/booking/3
       When
           Kullanici GET Request'i Url'e (APi) gonderir
           User send a GET Request to the url (API)
       Then
            HTTP Statu Kodu 200 olmali
           HTTP Status Code should be 200
       And
           Content Type'i JSON olmali
           Content Type should be JSON
       And
           Statu Line(duzeyi) HTTP/1.1 200 OK olmali
           Status Line should be HTTP/1.1 200 OK
    */
    @Test
    public void get01() {
        // 1) set Url
        String url = "https://restful-booker.herokuapp.com/booking/6";

        // 2) Set the expected data (beklenen data)

        // 3) GET Request gondermek ve GET Response almak icin Atomasyon kodunu yaz
        Response response = given().when().get(url);// get() getter gibi datayi okuyacak
        // get() kullandigimizda datayi okur ve alir
        response.prettyPrint(); //prettyPrint() consolde yazdirmak icin kullanilir

        // 4) Assertion yap

        response.then().assertThat().statusCode(200).contentType("application/JSON").statusLine("HTTP/1.1 200 OK");

         /*
        Eger Assertion'da coklu hata varsa, kodun calismasi ilk hatada durur ve sonraki kodlar calismaz
        Yani ikinci, ucuncu gibi hatalar hakkida hicbir bilgi alamazsiniz bu iyi birsey degildir
        Bu nedenle bu tip Assertion'a "Hard Assertion" denir
        Diger tip Assertion ise "Soft Assertion"dir
        Soft Assertion (Verification)'da butun kodlar calisir/kosar ve butun assertion'lar veya hatalar icin rapor alirsiniz
     */
        // statusCode, contentType ve StatusLine gibi console yazdirma
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Content Type: " + response.getContentType());
        System.out.println("Status Line: " +response.getStatusLine());
        System.out.println("Headers: " + response.getHeaders());// for all headers
        System.out.println("Time: " + response.getTime());

        System.out.println("Via: " + response.getHeader("Via"));// single header icin

    }
}
