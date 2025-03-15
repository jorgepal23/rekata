package test.apiPublicas;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdatePatchObject {

    @Test
    public void UpadePatchObject(){
        File postBody = new File("src/main/resources/pathObject.json");

        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json")
                .body(postBody);

        Response response = request
                .when()
                .patch("ff80818190910e080190942ba18009d4");

        response.prettyPrint();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);

    }
}
