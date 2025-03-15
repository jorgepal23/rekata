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

public class ClientsAccountManagement extends test.BaseTest {

    @BeforeClass
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
        RestAssured.proxy("10.86.79.53", 8003);
        // RestAssured.proxy("piscis01.bancodebogota.net", 8003);
    }

    @Test
    public void postClientsAccount() {
        ExtentTest test = extent.createTest("POST Clients Account Management");


            File postBody = new File("src/main/resources/clientAccount.json");
            RequestSpecification request = given()
                    .baseUri("https://api-clients.labdigitalbdbtvsstg.com")
                    .basePath("clients-account-management/V1/Enterprise/account/overdraftLimits")
                    .header("accept", "application/json")
                    .header("X-RqUid", "EE4D3E24-E61F-4D24-986F-67D143307930")
                    .header("X-TerminalId", "10.10.10.10")
                    .header("X-Channel", "CAB001")
                    .header("X-Name", "CAB001")
                    .header("X-CompanyId", "001")
                    .header("X-NetworkOwner", "CAB001")
                    .header("x-api-key", "uFUtZJL1l@#Y4xOTzMWq!VhmREwe!1")
                    .header("Content-Type", "application/json")
                    .body(postBody);

            test.info("Preparando la solicitud POST para gestión de cuentas de clientes");

            Response response = request.when().post();
            String responseBody = response.getBody().asString();
            int statusCode = response.getStatusCode();

        test.info("Respuesta recibida:");
        test.info("Cuerpo de la respuesta: " + responseBody);
        test.info("Código de estado: " + statusCode);

        try {
            Assert.assertEquals(statusCode, 200);
            test.pass("La prueba POST Clients Account Management fue exitosa");
        } catch (AssertionError e) {
            test.fail("La prueba POST Clients Account Management falló. Código de estado: " + statusCode);
            throw e;
        }
    }
}
