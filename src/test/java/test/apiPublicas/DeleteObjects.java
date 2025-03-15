package test.apiPublicas;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteObjects {

    @Test
    public void getObjects(){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects");

        Response response = request
                .when()
                .delete("/ff80818190910e080190942c018d09d5");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
