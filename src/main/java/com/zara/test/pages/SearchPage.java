package com.zara.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Search page object containing elements and methods for search functionality
 */
public class SearchPage extends BasePage {
    
    // Locators
    private static final By SEARCH_CONTENT = By.cssSelector(".layout-header-action-search__content");
    private static final By SEARCH_INPUT = By.xpath("//input[@id='search-home-form-combo-input']");
    private static final By PRODUCT_LIST = By.xpath("//ul[@class='product-grid__product-list']");
    private static final By PRODUCT_ITEMS = By.cssSelector("li.product-grid__product-item");
    
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Clicks on search content area
     */
    public void clickSearchContent() {
        logger.info("Clicking search content area");
        waitAndClick(SEARCH_CONTENT);
    }
    
    /**
     * Enters search term
     * @param searchTerm Term to search for
     */
    public void enterSearchTerm(String searchTerm) {
        logger.info("Entering search term: {}", searchTerm);
        waitAndSendKeys(SEARCH_INPUT, searchTerm);
    }
    
    /**
     * Clears search input
     */
    public void clearSearchInput() {
        logger.info("Clearing search input");
        WebElement element = waitForElement(SEARCH_INPUT);
        element.clear();
        
        // Wait a bit after clearing to ensure the input is properly cleared
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify the input is cleared
        String currentValue = element.getAttribute("value");
        if (currentValue != null && !currentValue.isEmpty()) {
            logger.warn("Search input was not cleared properly, current value: {}", currentValue);
            element.clear();
        }
    }
    
    /**
     * Presses Enter key on search input
     */
    public void pressEnterOnSearch() {
        logger.info("Pressing Enter on search input");
        pressEnter(SEARCH_INPUT);
    }
    
    /**
     * Performs search operation
     * @param searchTerm Term to search for
     */
    public void performSearch(String searchTerm) {
        clickSearchContent();
        enterSearchTerm(searchTerm);
        pressEnterOnSearch();
    }
    
    /**
     * Selects a random product from the product list
     * @return ProductPage object
     */
    public ProductPage selectRandomProduct() {
        logger.info("Selecting random product from list");
        
        // Wait for product list to be visible
        waitForElement(PRODUCT_LIST);
        
        // Get all product items
        List<WebElement> products = driver.findElements(PRODUCT_ITEMS);
        
        if (products.isEmpty()) {
            throw new RuntimeException("No products found in the list");
        }
        
        // Select first product (you can modify this to select random)
        WebElement firstProduct = products.get(0);
        
        // Scroll to element and click
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProduct).click().perform();
        
        logger.info("Selected first product from list");
        return new ProductPage(driver);
    }
}
