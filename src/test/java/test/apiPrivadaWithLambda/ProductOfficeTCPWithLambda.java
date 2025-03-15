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


public class ProductOfficeTCPWithLambda extends test.BaseTest{
    Map<String, String> headers = new HashMap<>();
    String baseUri = "https://tcc-commons.bbogcreditocomercialtccstg.com";
    String endPoint;
    String office = "1327";
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Test
    public void getProductWithLambda() {
        ExtentTest test = extent.createTest("Prueba: Obtener la oficina con Lambda");
        endPoint = "/api-tcp/V1/Product/office/" + office;
        headers.put("X-Api-Key", "v7Br32kRQ2aRdbe0J4dq69EEZZU130iM1yipJKD1");
        headers.put("X-Channel", "TCP-STG");
        headers.put("X-Name", "TCP-STG");
        headers.put("X-RqUID", "52bd0526-736a-45a2-b45b-9e5bfe1f3a50");
        headers.put("X-IP-Address", "192.168.0.1");
        headers.put("X-Captcha-Key", "03AFcWeA7A-9-3qZF0CKm57GhsWtyrUwPML6F7CyeICg_2MBcqss4SSDkjNveWz6elBqvB6DwrZaYGMAIbf1ceE5oLFLsorGxK9edpH2oculJqgUwr-r_2wZgKrhCCx5KFSrzyu0FuWvV6LDx6JFM4u5xXImDQva8d2nRMIJZryx7NK65tkfoT7gKAwSAVESuuc4wnqzzdGJXSHoHLOQEgxyC7qRTDEtp6T-KsIdR5dW_F5wdUZGGL5WGgD_Rz3OcvMmx2XpYJgXBZUSc_hdutkjEIDyNACK8yjVNmfNKNAaaEl1zVEZmf26_zlk0mfHuwlay77ATloH9pB9FQHoUbEsieoUjE7pAwDZZVNJgtKtSzh8j9SShI25wAL4iElExZpK_6AEZGEb_EFxReRAYKFcVhqaf7WfOibN8UUXaFrOYLh-QghUbGi6ImX8OKs-DLyxGKRvWZjgy3ERsHpUoeGAxz2YpZyyXhNAmybmKR8VWARW1GYQaAd2m__eJ6leCT0GNA9umdHpoXlvwJTLeHRAEADtI3s4aXsQuAF1-8Ltdj7vmRchF0cedFwzDWZONtp9k8SgEx4aZm-GvXPeZViuS65hA39h3zNrW8cmHa9CaHcqUMl1zsHf0mJVTXBC2Fkp54rn3zjgW6d-HvFEkXPg9zLGeExl6PXqQditJ57Yi7BAaRImmgzAVVPRwSB1Vn_deqkBi461Y3N-v7666GnkkmGw7lukOG6k5d0T1NDrGiKMJ7QFvZxSPF-EJe9K-AmLcmGUo7C9vtEEqlKllpbo3IQlX6e0f-__qXiRN-23BryLduXE84GM0QkQgzhwXnvrSC_jD7n_RgZXP8gGz14hs34q0djfvH9BRvBX3PWBf7nRZfmPQdJMpN-pH15cnPwj0SYtdDoJJlHX1TVFTUYT_H81KWQGERwnW4ZynxoPZRkq_gmA71BzpOe-oJVv_hyuHRTKzT-C0R8WtvdUdO3D_cH2OqQ7C6LlZ867QGNGcJZrlI7QU22si17iGaS8JREER9TY7N4VQFOAne4NH82sZKhBlIc8-p3LIackAGeLtlnTnrniyOiDZuVO_cv4ALcJTAZD0O66HGA8T9Anp351CcySVCjicnzl1r9wrDeL9hiAe4DXzdFK-Bh3QlXa8-6dUU5UdV1BPqqw2fboZ5Swb-x6wyMoFZW_N8mnbpuwlTzj3bSyrw0m2OqaUq00yerhnKkPeLHnTtIDjD_cR9oynb10yK7jyTBImg36lgK7UTsRKibUUhQHEQbJp-itXSaAKKFYXbUqD2hE9iGkZiT349fhN7Oj4xbids8PfUF1HgaXTVQ3SvFzCP9TxnX3dGjBbtZc9a2ZNOsPTFno3YlssFAyeR0dgOWQJAIEG4RJzRhRVs2fy06dJEtmrbVYdM6C4sdoh86PoFIcoCBtPl-7q5sWWuzeZITNXBo5SzpGF-za9paYQliMXw3tAF2HrrGfMWoxaH6y_Z_fSGhM3qS4basouq9ezoe6Kg4K39dmMqF1OuiPjSRWtRZCNeK0mSXmqSQnOJSia3D7IqChQRSn9-CacXm8phHGOkW4PaQ-ZmyiPErbJ8ZXNW7AD0gkBkw_FCjtkB3lflgr7JragYYmmSZfzm_XzE5qM0efj8zXhv_k8oSbd4S_jlhiKcI4EQinCHbgL1cp1wHILktaeDFJe_kf9uXYTZTK3BP_QIL85FEuCoPlQoAHVUPiAqQ8SL7qvHtBCAMKCrvhP2GRO1Pmr2UAukY7G8bQ");

        test.log(Status.INFO, "Se realiza consumo del API");
        GeneralTask generalTask = new GeneralTask(baseUri, endPoint, "GET", "", headers);
        int statusCode = generalTask.performAs("Get Customer V3", "Obtener producto de un cliente");
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
