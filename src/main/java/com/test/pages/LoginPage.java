package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Login page.
 * Contains all elements and methods related to the login functionality.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    @FindBy(className = "login-form")
    private WebElement loginForm;

    /**
     * Constructor to initialize the page with WebDriver.
     * 
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the username in the username input field.
     * 
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    /**
     * Enters the password in the password input field.
     * 
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Performs login with the given credentials.
     * 
     * @param username Username to enter
     * @param password Password to enter
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Checks if the error message is displayed.
     * 
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Gets the text of the error message.
     * 
     * @return Text of the error message
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Checks if the login page is loaded.
     * 
     * @return true if the login page is loaded, false otherwise
     */
    @Override
    public boolean isPageLoaded() {
        return isElementDisplayed(loginForm);
    }
}