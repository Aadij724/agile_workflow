package com.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import com.example.context.TestContext;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(TestContext context) {
        driver = context.getDriver();
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        driver.get("https://example.com/login");
    }

    @When("the user enters valid username and password")
    public void userEntersValidCredentials() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("validuser@example.com");
        passwordField.sendKeys("validpassword123");
    }

    @When("the user enters invalid username or password")
    public void userEntersInvalidCredentials() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("invaliduser@example.com");
        passwordField.sendKeys("invalidpassword123");
    }

    @When("clicks the login button")
    public void userClicksLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("the user should be redirected to the dashboard")
    public void userIsRedirectedToDashboard() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("User should be redirected to dashboard", currentUrl.contains("/dashboard"));
    }

    @Then("the user should see an error message")
    public void userSeesErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue("Error message should be displayed", errorMessage.isDisplayed());
    }

    @When("the user clicks on the {string} link")
    public void userClicksForgotPasswordLink(String linkText) {
        WebElement forgotPasswordLink = driver.findElement(By.linkText(linkText));
        forgotPasswordLink.click();
    }

    @Then("the user should be redirected to the password reset page")
    public void userIsRedirectedToPasswordResetPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("User should be redirected to password reset page", currentUrl.contains("/reset-password"));
    }
}