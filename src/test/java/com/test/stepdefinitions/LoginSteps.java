package com.test.stepdefinitions;

import com.test.context.TestContext;
import com.test.pages.DashboardPage;
import com.test.pages.LoginPage;
import com.test.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Step definitions for login functionality.
 */
public class LoginSteps {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final TestContext testContext;

    /**
     * Constructor to initialize the step definition with the test context.
     * 
     * @param testContext the shared test context
     */
    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
        this.driver = testContext.getDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String baseUrl = ConfigReader.getProperties().getProperty("base.url");
        driver.get(baseUrl + "/login");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User is not logged in");
    }

    @Then("I should be redirected to the dashboard page")
    public void i_should_be_redirected_to_the_dashboard_page() {
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard page is not loaded");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message does not match");
    }
}