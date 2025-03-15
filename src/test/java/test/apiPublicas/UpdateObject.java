package test.apiPublicas;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateObject {

    @Test
    public void UpadeObject(){
        File postBody = new File("src/main/resources/putObject.json");

        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json")
                .body(postBody);

        Response response = request
                .when()
                .put("/ff80818190910e0801909421556509bd");

        response.prettyPrint();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);

    }
}
