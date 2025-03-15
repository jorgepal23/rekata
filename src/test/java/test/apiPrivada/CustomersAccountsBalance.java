package test.apiPrivada;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CustomersAccountsBalance extends test.BaseTest {

    @Test
    public void getCustomerAccountBalance() {
        ExtentTest test = extent.createTest("GET Customer Account Balance");

        File postBody = new File("src/main/resources/fakeStoreApi.json");
        RequestSpecification request = given()
                .baseUri("https://fakestoreapi.com")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .body(postBody);

        Response response = request.when().post();
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();

        test.info("Cuerpo de la respuesta: " + responseBody);
        test.info("Código de estado: " + statusCode);

        try {
            Assert.assertEquals(statusCode, 407);
            test.pass("La prueba GET Customer Account Balance fue exitosa");
        } catch (AssertionError e) {
            test.fail("La prueba GET Customer Account Balance falló. Código de estado: " + statusCode);
            throw e;
        }
    }
}
