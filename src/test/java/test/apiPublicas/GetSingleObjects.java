package test.apiPublicas;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetSingleObjects {
    @Test
    @Parameters({"id","expectedStatusCode"})

    public void getObjects(String id, int expectedStatusCode){
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects");

        Response response = request
                .when()
                .get(id);

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, expectedStatusCode);
        System.out.println(expectedStatusCode);

        /*JSONObject jsonReponse = new JSONObject(response.asString());
        String name = jsonReponse.getString("name");
        System.out.println(name);
        JSONObject jsonData = jsonReponse.getJSONObject("data");
        String cpuModel = jsonData.getString("CPU model");
        System.out.println(cpuModel);
        */

    }
}
