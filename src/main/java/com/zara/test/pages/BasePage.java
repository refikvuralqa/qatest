package com.zara.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base page class containing common functionality for all page objects
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.logger = LogManager.getLogger(this.getClass());
    }
    
    /**
     * Waits for element to be clickable and clicks it
     * @param locator Element locator
     */
    protected void waitAndClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked element: {}", locator);
        } catch (TimeoutException e) {
            logger.error("Element not clickable: {}", locator);
            throw e;
        }
    }
    
    /**
     * Waits for element to be visible and sends keys
     * @param locator Element locator
     * @param text Text to send
     */
    protected void waitAndSendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Sent keys '{}' to element: {}", text, locator);
        } catch (TimeoutException e) {
            logger.error("Element not visible: {}", locator);
            throw e;
        }
    }
    
    /**
     * Waits for element to be visible
     * @param locator Element locator
     * @return WebElement
     */
    protected WebElement waitForElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found: {}", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Element not found: {}", locator);
            throw e;
        }
    }
    
    /**
     * Gets text from element
     * @param locator Element locator
     * @return Element text
     */
    protected String getElementText(By locator) {
        WebElement element = waitForElement(locator);
        String text = element.getText();
        logger.info("Got text '{}' from element: {}", text, locator);
        return text;
    }
    
    /**
     * Checks if element is displayed
     * @param locator Element locator
     * @return true if element is displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Presses Enter key on element
     * @param locator Element locator
     */
    protected void pressEnter(By locator) {
        WebElement element = waitForElement(locator);
        element.sendKeys(Keys.ENTER);
        logger.info("Pressed Enter on element: {}", locator);
    }
}
