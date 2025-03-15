package test.apiPrivadaWithLambda;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.GeneralTask;

import java.util.HashMap;
import java.util.Map;


public class CustomerManagementV3WithLambda extends test.BaseTest {
    Map<String, String> headers = new HashMap<>();
    String baseUri = "https://api-clients.labdigitalbdbtvsstg.com";
    String endPoint;
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Test
    public void getCustomerWithLambda() {
        ExtentTest test = extent.createTest("Prueba: Obtener producto con Lambda");
        endPoint = "/customer-management-v3-mngr/V3/enterprise/customer/product";
        headers.put("x-api-key", "YcAx#9ghY!WOR^M5b5yRrcxXzHh2Lu");
        headers.put("X-GovIssueIdentType", "CC");
        headers.put("X-IdentSerialNum", "1002957689");
        headers.put("X-Channel", "OPB001");
        headers.put("X-CompanyId", "001");
        headers.put("X-Name", "OpenFinance");
        headers.put("X-RqUID", "13f26ab7-f282-487b-bfaf-a7d0acb04075");
        headers.put("X-IPAddr", "3.13.132.40");

        test.log(Status.INFO, "Se realiza consumo del API");
        GeneralTask generalTask = new GeneralTask(baseUri,endPoint,"GET","",headers);
        int statusCode = generalTask.performAs("Get Customer V3","Obtener producto de un cliente");
        test.log(Status.INFO, "El status code es: " + statusCode);
        try {
            Assert.assertEquals(statusCode, 200);
            test.pass("La prueba Get Clients V3 fue exitosa");
        } catch (AssertionError e) {
            test.fail("La prueba Get Clients V3  falló. Código de estado: " + statusCode);
            throw e;
        }
    }
}