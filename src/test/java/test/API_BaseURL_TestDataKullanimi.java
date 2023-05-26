package test;

import baseURLDeposu.JsonPlaceHolderBaseURLDeposu;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class API_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseURLDeposu {
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
    public void test01(){
        // 1- URL and Body
        specJsonPlace.pathParam("pp1",44);
        // 2- Expected Data
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();
        JSONObject expBody = jsonPlaceHolder.expDataCreate();

        //System.out.println(expBody);

        // 3- Response save
        Response res = given().spec(specJsonPlace).when().get("{pp1}"); //eger ıkı taneyse {pp1}/{pp2} seklınde yazıcaksın.
        //res.prettyPrint();

        // 4- Assertion
        JsonPath respJsonPath = res.jsonPath();

        //Assert.assertEquals(jsonPlaceHolder.basariliStatusKod,res.getStatusCode());
        assertEquals(jsonPlaceHolder.basariliStatusKod,res.getStatusCode()); //jUnıt den statıc code ımport ettık

        assertEquals(expBody.getInt("userId"),respJsonPath.getInt("userId"));
        assertEquals(expBody.getString("title"),respJsonPath.getString("title"));



    }
}
