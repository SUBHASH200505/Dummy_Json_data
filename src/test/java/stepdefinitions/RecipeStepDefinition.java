package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import utils.ExcelUtil;
import utils.ApiUtil;

import java.util.Map;

public class RecipeStepDefinition {

    String testCaseID;

    Map<String, String> testData;

    Response response;

    @Given("I read test data {string}")
    public void read_test_data(String id) {

        testCaseID = id;

        testData =
                ExcelUtil.getData(testCaseID);

        if (testData == null || testData.isEmpty()) {

            throw new RuntimeException(
                    "Test data NOT found for: "
                            + testCaseID);

        }

        System.out.println("=================================");

        System.out.println(
                "Running TestCase: "
                        + testCaseID);

    }

    @Given("I validate precondition")
    public void validate_precondition() {

        if (testData == null ||
                testData.isEmpty()) {

            throw new RuntimeException(
                    "Precondition Failed: "
                            + testCaseID);

        }

    }

    @When("I perform API request")
    public void perform_api_request() {

        try {

            String method =
                    testData.get("method");

            String endpoint =
                    testData.get("endpoint");

            String body =
                    testData.get("testdata");

            System.out.println("Method: " + method);

            System.out.println("Endpoint: " + endpoint);

            System.out.println("Body: " + body);

            response =
                    ApiUtil.sendRequest(
                            method,
                            endpoint,
                            body);

        }

        catch (Exception e) {

            System.out.println(
                    "API FAILED for: "
                            + testCaseID);

            e.printStackTrace();

            response = null;

        }

    }

    @Then("I validate expected result")
    public void validate_expected_result() {

        if (response == null) {

            throw new RuntimeException(
                    "Response is NULL for: "
                            + testCaseID
                            + " (API failed)"
            );

        }

        String expectedStatusStr =
                testData.get("expectedstatus");

        if (expectedStatusStr == null ||
                expectedStatusStr.trim().isEmpty()) {

            throw new RuntimeException(
                    "ExpectedStatus missing for: "
                            + testCaseID);
        }

        int expectedStatus =
                (int) Double.parseDouble(
                        expectedStatusStr.trim());

        int actualStatus =
                response.getStatusCode();

        System.out.println(
                "Expected Status: "
                        + expectedStatus);

        System.out.println(
                "Actual Status: "
                        + actualStatus);

        if (actualStatus != expectedStatus) {

            throw new AssertionError(
                    "FAILED TestCase: "
                            + testCaseID
                            + " | Expected: "
                            + expectedStatus
                            + " | Actual: "
                            + actualStatus
            );

        }

        System.out.println(
                "PASSED TestCase: "
                        + testCaseID);

    }

}