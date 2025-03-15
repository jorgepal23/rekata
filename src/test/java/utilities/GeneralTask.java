package utilities;

import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.http.HttpMethodName;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class GeneralTask {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private String baseUrl;
    private String endPoint;
    private String method;
    private String parameters;
    private Map<String, String> headers;
    private Map<String, Object> body = new HashMap<>();

    private static String responseService;
    private static Response response;
    public static final ExtentReports EXTENT = new ExtentReports();

    public String getResponseService() {
        return responseService;
    }

    public GeneralTask() {
    }

    public GeneralTask(
            String baseUrl,
            String endPoint,
            String method,
            String parameters,
            Map<String, String> headers,
            Map<String, Object> body) {
        this.baseUrl = baseUrl;
        this.endPoint = endPoint;
        this.method = method;
        this.parameters = parameters;
        this.headers = headers;
        this.body = body;
    }

    public GeneralTask(
            String baseUrl,
            String endPoint,
            String method,
            String parameters,
            Map<String, String> headers) {
        this.baseUrl = baseUrl;
        this.endPoint = endPoint;
        this.method = method;
        this.parameters = parameters;
        this.headers = headers;
    }

    public int performAs(String nameTest, String description) {
        Logger logger = Logger.getLogger("MyLogs");

        EnvironmentValuesTask env = new EnvironmentValuesTask();
        ExtentTest test = EXTENT.createTest(nameTest, description);
        boolean bol = body.keySet().isEmpty();

        String jsonBody = gson.toJson(body);
        String jsonHeaders = gson.toJson(headers);
        HashMap<String, String> jsonMapLambda = new HashMap<>();

        if (bol) {
            jsonMapLambda.put("method", method);
            jsonMapLambda.put("url", baseUrl + endPoint + parameters);
            jsonMapLambda.put("headers", jsonHeaders);
        } else {
            jsonMapLambda.put("method", method);
            jsonMapLambda.put("url", baseUrl + endPoint + parameters);
            jsonMapLambda.put("headers", jsonHeaders);
            jsonMapLambda.put("data", jsonBody);
        }

        String jsonLambda =
                gson.toJson(jsonMapLambda).replace("\\\"", "\"").replace("\"{", "{")
                        .replace("}\"", "}").replace("\\", "");
        Request<Void> request = new DefaultRequest<>("lambda");
        request.setHttpMethod(HttpMethodName.POST);
        request.setEndpoint(URI
                .create("https://twvte6s3pacclyswgzn3ex7dfy0gblio.lambda-url.us-east-1.on.aws/"));     //Cambiar por la URL de cada uno
        InputStream str = new ByteArrayInputStream(jsonLambda.getBytes(StandardCharsets.UTF_8));
        request.setContent(str);

        logger.info(jsonLambda);

        AWSSessionCredentials credentials =
                new AWSSessionCredentials() {
                    @Override
                    public String getSessionToken() {
                        return env.getEnv("AWS_SESSION_TOKEN");
                    }

                    @Override
                    public String getAWSAccessKeyId() {
                        return env.getEnv("AWS_ACCESS_KEY_ID");
                    }

                    @Override
                    public String getAWSSecretKey() {
                        return env.getEnv("AWS_SECRET_ACCESS_KEY");
                    }
                };

        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("us-east-1");
        signer.setServiceName(request.getServiceName());
        signer.sign(request, credentials);

        request.addHeader("Content-Type", "application/json");

        logger.log(Level.INFO, request.getHeaders().toString());

        int status = petition(request, jsonLambda);

        if (status == 200) {
            test.log(Status.PASS, nameTest + " pasó correctamente");
        } else {
            test.log(Status.FAIL, nameTest + " falló");
        }

        return status;
    }

    private static int petition(Request<Void> request, String jsonLambda) {
        Logger logger = Logger.getLogger("MyLogs");
        response =
                given()
                        .header("Authorization", request.getHeaders().values().toArray()[0].toString())
                        .header("Content-Type", request.getHeaders().values().toArray()[1].toString())
                        .header("Host", request.getHeaders().values().toArray()[2].toString())
                        .header("X-Amz-Date", request.getHeaders().values().toArray()[3].toString())
                        .header("X-Amz-Security-Token", request.getHeaders().values().toArray()[4].toString())
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(jsonLambda)
                        .when()
                        .post("https://twvte6s3pacclyswgzn3ex7dfy0gblio.lambda-url.us-east-1.on.aws/");     //Cambiar por la URL de cada uno

        logger.log(Level.INFO, "El response es: " + response.getBody().asString());
        logger.log(Level.INFO, "status es " + response.getStatusCode());
        GeneralTask.responseService = response.getBody().asString();
        return response.getStatusCode();
    }
}