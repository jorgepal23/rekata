package test.apiPrivada;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CustomerManagementV3 extends test.BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation("SSL");
        RestAssured.proxy("10.86.79.53", 8003);
        //RestAssured.proxy("piscis01.bancodebogota.net", 8003);
    }

    @Test
    public void getCustomerManagementV3() {
        ExtentTest test = extent.createTest("GET Customer V3");

        RequestSpecification request = given()
                .baseUri("https://api-clients.labdigitalbdbtvsstg.com")
                .basePath("/customer-management-v3-mngr/V3/enterprise")
                .header("x-api-key", "GX3aQY44BECr0fXF3gIbLlijEQ8Muc")
                .header("X-GovIssueIdentType", "CC")
                .header("X-IdentSerialNum", "1002957689")
                .header("X-Channel", "OPB001")
                .header("X-CompanyId", "001")
                .header("X-Name", "OpenFinance")
                .header("X-RqUID", "13f26ab7-f282-487b-bfaf-a7d0acb04075")
                .header("X-IPAddr", "3.13.132.40");

        Response response = request.when().get("/customer/product");
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();

        test.info("Respuesta recibida:");
        test.info("Cuerpo de la respuesta: " + responseBody);
        test.info("Código de estado: " + statusCode);

        try {
            Assert.assertEquals(statusCode, 200);
            test.pass("La prueba GET Customer V3 fue exitosa");
        } catch (AssertionError e) {
            test.fail("La prueba GET Customer V3 falló. Código de estado: " + statusCode);
            throw e;
        }
    }
}
