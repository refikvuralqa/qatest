package com.zara.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Product page object containing elements and methods for product details
 */
public class ProductPage extends BasePage {
    
    // Locators
    private static final By PRODUCT_NAME = By.cssSelector(".product-detail-info__header-name");
    private static final By PRODUCT_PRICE = By.cssSelector(".money-amount__main");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//span[contains(text(),'Ekle')]");
    private static final By SIZE_SELECTOR = By.xpath("//div[normalize-space()='S (US S)']");
    private static final By SMART_TRY_REJECT_BUTTON = By.xpath("//button[contains(text(),'Hayır, teşekkürler')]");
    private static final By COMPLETE_ORDER_BUTTON = By.xpath("//span[contains(text(),'Siparişi Tamamla')]");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Gets product name
     * @return Product name
     */
    public String getProductName() {
        String name = getElementText(PRODUCT_NAME);
        logger.info("Product name: {}", name);
        return name;
    }
    
    /**
     * Gets product price
     * @return Product price
     */
    public String getProductPrice() {
        String price = getElementText(PRODUCT_PRICE);
        logger.info("Product price: {}", price);
        return price;
    }
    
    /**
     * Clicks add to cart button
     */
    public void clickAddToCart() {
        logger.info("Clicking add to cart button");
        waitAndClick(ADD_TO_CART_BUTTON);
    }
    
    /**
     * Selects product size
     */
    public void selectSize() {
        logger.info("Selecting product size");
        waitAndClick(SIZE_SELECTOR);
    }
    
    /**
     * Rejects smart try offer
     */
    public void rejectSmartTry() {
        logger.info("Rejecting smart try offer");
        waitAndClick(SMART_TRY_REJECT_BUTTON);
    }
    
    /**
     * Clicks complete order button
     */
    public void clickCompleteOrder() {
        logger.info("Clicking complete order button");
        waitAndClick(COMPLETE_ORDER_BUTTON);
    }
    
    /**
     * Adds product to cart with size selection
     */
    public void addProductToCart() {
        clickAddToCart();
        selectSize();
        rejectSmartTry();
        clickCompleteOrder();
    }
}
