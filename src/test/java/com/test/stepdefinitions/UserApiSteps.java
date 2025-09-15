package com.test.stepdefinitions;

import com.test.context.TestContext;
import com.test.utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Step definitions for User API tests.
 */
public class UserApiSteps {
    private final TestContext testContext;
    private RequestSpecification request;
    private Map<String, Object> requestBody;
    private final Properties properties;

    /**
     * Constructor to initialize the step definition with the test context.
     * 
     * @param testContext the shared test context
     */
    public UserApiSteps(TestContext testContext) {
        this.testContext = testContext;
        this.properties = ConfigReader.getProperties();
        this.requestBody = new HashMap<>();
    }

    @Given("the API base URL is set")
    public void the_api_base_url_is_set() {
        String baseUrl = properties.getProperty("api.base.url");
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.contentType(ContentType.JSON);
    }

    @Given("I have the following user details:")
    public void i_have_the_following_user_details(DataTable dataTable) {
        Map<String, String> userDetails = dataTable.asMap(String.class, String.class);
        requestBody.putAll(userDetails);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        Response response = request.get(endpoint);
        testContext.setApiResponse(response);
    }

    @When("I send a POST request to {string} with the user details")
    public void i_send_a_post_request_to_with_the_user_details(String endpoint) {
        Response response = request.body(requestBody).post(endpoint);
        testContext.setApiResponse(response);
    }

    @When("I send a PUT request to {string} with the user details")
    public void i_send_a_put_request_to_with_the_user_details(String endpoint) {
        Response response = request.body(requestBody).put(endpoint);
        testContext.setApiResponse(response);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        Response response = request.delete(endpoint);
        testContext.setApiResponse(response);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        Response response = testContext.getApiResponse();
        Assert.assertEquals(response.getStatusCode(), statusCode.intValue(), 
                "Expected status code " + statusCode + " but got " + response.getStatusCode());
    }

    @Then("the response should contain user details")
    public void the_response_should_contain_user_details() {
        Response response = testContext.getApiResponse();
        Assert.assertNotNull(response.jsonPath().get("id"), "User ID is missing in response");
        Assert.assertNotNull(response.jsonPath().get("name"), "User name is missing in response");
        Assert.assertNotNull(response.jsonPath().get("email"), "User email is missing in response");
    }

    @Then("the response should contain the created user ID")
    public void the_response_should_contain_the_created_user_id() {
        Response response = testContext.getApiResponse();
        Assert.assertNotNull(response.jsonPath().get("id"), "Created user ID is missing in response");
    }

    @Then("the response should contain updated user details")
    public void the_response_should_contain_updated_user_details() {
        Response response = testContext.getApiResponse();
        
        // Verify that the response contains the updated fields
        for (Map.Entry<String, Object> entry : requestBody.entrySet()) {
            String key = entry.getKey();
            Object expectedValue = entry.getValue();
            Object actualValue = response.jsonPath().get(key);
            
            Assert.assertEquals(actualValue, expectedValue, 
                    "Expected " + key + " to be " + expectedValue + " but got " + actualValue);
        }
    }
}