package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import Utils.*;

import java.util.Map;

import org.testng.Assert;

public class AuthStepDefinition {

    Map<String, String> data;
    Response response;

    String method;
    String endpoint;
    String body;

    static String token; // shared token

    // =========================================
    // EXCEL BASED LOGIN
    // =========================================

    @Given("I read auth test data {string}")
    public void readData(String testCaseID) {

        data = ExcelUtil.getData(testCaseID);

        if (data == null || data.isEmpty()) {
            throw new RuntimeException("No data found for " + testCaseID);
        }
    }

    @And("I validate auth precondition")
    public void validatePrecondition() {
        System.out.println("Precondition validated");
    }

    @When("I perform auth API request")
    public void performRequest() {

        method = data.get("method");
        String endpointPath = data.get("endpoint");
        endpoint = ConfiReader.get("base.url") + endpointPath;
        body = data.get("testdata");

        response = ApiUtil.send(method, endpoint, body, null);

        // Extract token only for login
        if (endpoint.contains("/auth/login") && response.getStatusCode() == 200) {
            token = response.jsonPath().getString("accessToken");
            System.out.println("TOKEN: " + token);
        }

        System.out.println("RESPONSE: " + response.asString());
    }

    @Then("I validate auth expected result")
    public void validateResult() {

        int expected = Integer.parseInt(data.get("expectedstatus"));
        int actual = response.getStatusCode();

        Assert.assertEquals(actual, expected);
    }

    // =========================================
    // INLINE REQUESTS
    // =========================================

    @Given("I set auth request {string} {string}")
    public void setAuthRequest(String m, String e) {

        method = m.toUpperCase();
        endpoint = ConfiReader.get("base.url") + e;

        // 🔥 IMPORTANT: Reset token for negative scenario
        if (endpoint.contains("/auth/me")) {
            token = null;
        }

        // Login body
        if (method.equals("POST") && e.contains("/auth/login")) {
            body = "{ \"username\":\"emilys\", \"password\":\"emilyspass\" }";
        } else {
            body = null;
        }
    }

    @When("I send auth request")
    public void sendAuthRequest() {

        // 🔥 If token exists → use it, else send null
        response = ApiUtil.send(method, endpoint, body, token);

        // Extract token for login
        if (endpoint.contains("/auth/login") && response.getStatusCode() == 200) {
            token = response.jsonPath().getString("accessToken");
        }
    }

    @Then("I validate auth status {string}")
    public void validateAuthStatus(String status) {

        int expected = Integer.parseInt(status);
        int actual = response.getStatusCode();

        Assert.assertEquals(actual, expected);
    }

 

    @Then("Response body should contain {string}")
    public void validateResponseContains(String text) {

        Assert.assertTrue(response.asString().contains(text),
                "Response does not contain: " + text);
    }
}