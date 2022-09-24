package Notes;

public class ApiNotes {
/*
API: Application Programming Interface
         APIs do not have user interface. {Bcoz of that its name is Programming Interface}
    (You won't see anything, just you'll imagine what you do)
        APIs are used to communicate with each other.
--------------------------------------------------------------------
        Amazon - API    API - Visa
        (request -->
                        <---respond)
       ( Request x Respond connection is like that below:)
        from Visa to API, from API to API, API to Amazon.
        JSON data format is very common for API. Jason data is similar with Maps.
        XML format is similar with HTML syntax.
        Api accepts XML or JSON format for the request.
        Api produces XML or JSON format for the respond.
        rest assured library in API.
        restful
----------------------------------------------------------------------
        when you create an API, you need end points.
        Your API can insert some data to DB.
        Your API can do update on DB.
        Your API can delete something from DB.
        Your API can read/get data from DB.
        1) Insert ---> url
        2) Update ---> url
        3) Delete
        4) Read
        For operations you will have URL's. URLs are called End Points.
        Ex: for ex for update operation you'll have 10 urls or have 20 urls for reading.
        Why do we need multiple URLs for insert operations?
       -Sometimes we want to insert students, parents, grades to DB. So you'll have many URL's
       For different insert operations, you'll create an End Points (URL) for every operation.
        --INTERVIEW QUESTION--
    ***As an API Automation Tester, you'll test/check every ENDPOINTS by using different scenarios if they're working as expected.
    Those scenarios will be given in the requirement document***
    ----------------------------------------
        ==> Swagger documentation is famous for API. -> https://petstore.swagger.io/
        Check Swagger Doc for all the details. according to the doc, create test cases and test manually and then write script to test it.
        ==> HTTP Request Methods:
        1) GET method: It is for reading data from DB. (Read --> R in CRUD)
                       To be able to use GET Method we just need Endpoint (endpoint is URL)
        2) POST method: It is for creating new data in DB. (Create --> C in CRUD)
                       To be able to send POST Request we need Endpoint + Request Body
        3) PUT method: It is for FULLY UPDATE in DB. (Update --> U in CRUD) for ex: you have fName,lName,address in DB. If u wanna update all records, you' need to use PUT.
                       To be able to send PUT Request we need Endpoint + Fully Request Body
        4) PATCH method: It is for PARTIAL UPDATE in DB. (Update --> U in CRUD) But if u wanna change just a specific cell in record, use PATCH to partial update.
                       To be able to send PATCH Request we need Endpoint + Partial Request Body
        5) DELETE method: It is for deleting data from DB. (Read --> R in CRUD)
                       To be able DELETE a record in DB, we just need Endpoint
    Petstore API produces only JSON data for response.
     **INT Q:
       What do you need to be able to use GET/PUT/PATCH.. METHOD?
       Just Endpoint or Endpoint + Body ... (Answers up above)
    ----------------------------------------
        ==> HTTP Status Codes:
        1XX : It means the request is received and the process is continuing.
        2XX : It means the action is successfully received, understood and accepted. (This request means everything is OK, there is no issue for API. This is the expected status code)
        3XX : It means further actions must be taken to complete the request. (If u get this, pls check your request, check if there's sth missing)(If u get this code, it means you made sth wrong, dont mention it to manager.)
        4XX : It means the request contains incorrect syntax or cannot be fulfilled. (If u get this, pls check your request, don't mention it to manager.)
        5XX : It means server is down. (Ask this: "I have 5XX error, does anybody have same" if u get this error, then they need to do sth)
       *** NOTE:***
        INTERVIEW Q: In API testing, what do you test at first?
        In API testing, STATUS CODE must be tested first.
        If the status code is 2XX then you can test the details.
----------------------------------------------------------------
        POSTMAN: It is an app to test endpoints of an API, MANUALLY. It is MANUAL TESTING APP.
        REST ASSURED LIBRARY : It is a library in Java which is used to test endpoints in automation.
        Q: How do u test API?
        For Automation; I use rest assured library, for manual test; I use POSTMAN.
        You should test behaviour manually first, according to the  manual result, you have to type automation SCRIPT.
        to type AUTOMATION script, you have to use Manual test at first.
        QA: uses Postman and RestAssured.
        Manual testers: Uses only POSTMAN.
        POSTMAN:  If there is minor differences, you ask ur manager if it's ok. because minor differences won't affect it.
        Body:
        {
    "sold": 20,
    "placed": 1,
    "string": 159,
    "Busy": 1,
    "pending": 19,
    "available": 686,
    "available;pending;sold": 1,
    "avalible": 1,
    "Not Available": 1,
    "{{pet1Status}}": 1,
    "1": 1,
    "{{pet3Status}}": 1,
    "avaliable": 14,
    "dog1": 1,
    "ok": 5,
    "free": 1,
    "OK": 1,
    "TEXTO MINUSCULO TESTE": 1
}
        Time is used for PERFORMANCE TESTING, just know that. They give a time, you check if it executes in that time or not.
        ------------------------
        REQUEST Body must match RESPONSE Body
 */

}
