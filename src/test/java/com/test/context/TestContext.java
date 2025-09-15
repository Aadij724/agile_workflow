package com.test.context;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * TestContext class to share state between step definitions.
 * This class acts as a container for objects that need to be shared across
 * different step definition classes during the execution of a scenario.
 */
public class TestContext {
    private WebDriver driver;
    private Map<String, Object> scenarioContext;
    private Response apiResponse;
    
    public TestContext() {
        scenarioContext = new HashMap<>();
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }
    
    public Object getContext(String key) {
        return scenarioContext.get(key);
    }
    
    public Boolean isContains(String key) {
        return scenarioContext.containsKey(key);
    }
    
    public Response getApiResponse() {
        return apiResponse;
    }
    
    public void setApiResponse(Response apiResponse) {
        this.apiResponse = apiResponse;
    }
}