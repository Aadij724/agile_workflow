package com.test.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner class to execute Cucumber scenarios.
 * This class configures and runs Cucumber tests with TestNG.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.test.stepdefinitions", "com.test.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        dryRun = false,
        tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    /**
     * Runs tests in parallel if enabled.
     * 
     * @return data provider to enable parallel execution
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}