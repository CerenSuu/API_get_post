package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;


import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;

public class API_GetRequest {
    /*
    https://jsonplaceholder.typicode.com/posts/44 url'şnne bir GET req yolladıgımızda donen
    res'ın

    status code=200
    content type=JSON
    res body'sınde bulunan userId=5
    res body'sınde bulunan title= "optio dolor molestias sit"

    oldugunu test edin.
     */
    @Test
    public void get01() {

        // 1- Req URL ve Body olustur
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        // 2- Expected Data olustur

        JSONObject expBody = new JSONObject();
        expBody.put("userId",5);
        expBody.put("title","optio dolor molestias sit");

        // 3- Responsu kaydet

        Response response = given().when().get(url);
        //response.prettyPrint();

        // 4- Assertion

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath actBody = response.jsonPath();

        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));


    }

}