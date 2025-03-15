package test.apiPublicas;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetWithUrlParams {
   // Metodo 1
    // @Test
    public void getWithUrlParams1(){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .formParam("id","1")
                .formParam("id","2");

        Response response = request
                .when()
                .get();

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
    }
    @Test
    public void getWithUrlParams2(){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .formParam("id","1","2");

        Response response = request
                .when()
                .get();

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);



    }
}
