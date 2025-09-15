package com.test.hooks;

import com.test.context.TestContext;
import com.test.utils.ConfigReader;
import com.test.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Hooks class containing setup and teardown methods that run before and after each scenario.
 */
public class Hooks {
    private final TestContext testContext;
    private WebDriver driver;
    private final Properties properties;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        this.properties = ConfigReader.getProperties();
    }

    /**
     * Setup method that runs before each scenario.
     * Initializes the WebDriver and sets it in the TestContext.
     */
    @Before("@ui")
    public void setupDriver() {
        String browser = properties.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless", "false"));
        
        driver = DriverManager.getDriver(browser, headless);
        driver.manage().window().maximize();
        testContext.setDriver(driver);
    }

    /**
     * Teardown method that runs after each scenario.
     * Takes a screenshot if the scenario fails and quits the WebDriver.
     *
     * @param scenario The current scenario
     */
    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                // Take screenshot if scenario fails
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot of failure");
            }
            driver.quit();
        }
    }

    /**
     * Setup method for API tests.
     */
    @Before("@api")
    public void setupApi() {
        // API test setup if needed
    }

    /**
     * Teardown method for API tests.
     */
    @After("@api")
    public void tearDownApi() {
        // API test cleanup if needed
    }
}