package com.test.pages;

import com.test.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

/**
 * Base class for all Page Objects.
 * Contains common methods used across different pages.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;

    /**
     * Constructor to initialize the page with WebDriver.
     * 
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.properties = ConfigReader.getProperties();
        int explicitWaitTime = Integer.parseInt(properties.getProperty("explicit.wait", "15"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for the page to load completely.
     */
    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        wait.until(pageLoadCondition);
    }

    /**
     * Waits for an element to be clickable.
     * 
     * @param element WebElement to wait for
     * @return WebElement that is now clickable
     */
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for an element to be visible.
     * 
     * @param element WebElement to wait for
     * @return WebElement that is now visible
     */
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Clicks on an element after waiting for it to be clickable.
     * 
     * @param element WebElement to click
     */
    protected void click(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    /**
     * Enters text into an input field after waiting for it to be visible.
     * 
     * @param element WebElement to enter text into
     * @param text Text to enter
     */
    protected void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element).clear();
        element.sendKeys(text);
    }

    /**
     * Gets the text from an element after waiting for it to be visible.
     * 
     * @param element WebElement to get text from
     * @return Text of the element
     */
    protected String getText(WebElement element) {
        return waitForElementToBeVisible(element).getText();
    }

    /**
     * Checks if an element is displayed.
     * 
     * @param element WebElement to check
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Abstract method to check if the page is loaded.
     * Each page should implement this method to verify that it is loaded correctly.
     * 
     * @return true if the page is loaded, false otherwise
     */
    public abstract boolean isPageLoaded();
}