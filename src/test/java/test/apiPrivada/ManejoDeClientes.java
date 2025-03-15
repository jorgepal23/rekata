package test.apiPrivada;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ManejoDeClientes extends test.BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
        RestAssured.proxy("10.86.79.53", 8003);
        //RestAssured.proxy("piscis01.bancodebogota.net", 8003);
    }

    @Test
    public void CustomerLegalEntityManagement() {
        ExtentTest test = extent.createTest("GET Customer Clientes");

        RequestSpecification request = given()
                .baseUri("https://api-customer-legal.labdigitalbdbtvsstg.com")
                .basePath("V1/enterprise/company")
                .header("accept", "application/json")
                .header("X-RqUID", "836bd2c4-e3dd-446c-845f-17ec2036e234")
                .header("X-Channel", "CDF001")
                .header("X-CustIdentType", "NI")
                .header("X-CustIdentNum", "9078945612")
                .header("X-IPAddr", "10.10.10.10")
                .header("X-Name", "CDF001")
                .header("X-CompanyId", "9078945612")
                .header("x-api-key", "pFRsnJtRE0l9/lWyiWsWl00muWvEm2")
                .formParam("GovOrgInqInd", "Y");

        Response response = request.when().get();
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();

        test.info("Cuerpo de la respuesta: " + responseBody);
        test.info("Código de estado: " + statusCode);

        try {
            Assert.assertEquals(statusCode, 200);
            test.pass("La prueba GET Customer Clientes fue exitosa");
        } catch (AssertionError e) {
            test.fail("La prueba GET Customer Clientes falló. Código de estado: " + statusCode);
            throw e;
        }
    }
}
