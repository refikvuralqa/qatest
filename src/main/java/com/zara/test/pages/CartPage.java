package com.zara.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Cart page object containing elements and methods for cart operations
 */
public class CartPage extends BasePage {
    
    // Locators
    private static final By CART_ITEM_PRICE = By.cssSelector("div[class='shop-cart-item-pricing__current'] span[class='money-amount__main']");
    private static final By QUANTITY_INCREASE_BUTTON = By.xpath("(//*[name()='path'])[13]");
    private static final By QUANTITY_DECREASE_BUTTON = By.xpath("(//*[name()='svg'][@class='zds-quantity-selector__icon'])[1]");
    private static final By QUANTITY_INPUT = By.cssSelector("input[value='2']");
    private static final By EMPTY_CART_MESSAGE = By.xpath("//span[contains(text(),'SEPETİNİZ BOŞ')]");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Gets cart item price
     * @return Cart item price
     */
    public String getCartItemPrice() {
        String price = getElementText(CART_ITEM_PRICE);
        logger.info("Cart item price: {}", price);
        return price;
    }
    
    /**
     * Increases quantity by clicking + button
     */
    public void increaseQuantity() {
        logger.info("Increasing quantity");
        waitAndClick(QUANTITY_INCREASE_BUTTON);
    }
    
    /**
     * Decreases quantity by clicking - button
     */
    public void decreaseQuantity() {
        logger.info("Decreasing quantity");
        waitAndClick(QUANTITY_DECREASE_BUTTON);
    }
    
    /**
     * Decreases quantity twice to remove item
     */
    public void removeItemFromCart() {
        logger.info("Removing item from cart");
        decreaseQuantity();
        decreaseQuantity();
    }
    
    /**
     * Verifies quantity is 2
     * @return true if quantity is 2
     */
    public boolean verifyQuantityIsTwo() {
        boolean isTwo = isElementDisplayed(QUANTITY_INPUT);
        logger.info("Quantity is 2: {}", isTwo);
        return isTwo;
    }
    
    /**
     * Verifies cart is empty
     * @return true if cart is empty
     */
    public boolean verifyCartIsEmpty() {
        boolean isEmpty = isElementDisplayed(EMPTY_CART_MESSAGE);
        logger.info("Cart is empty: {}", isEmpty);
        return isEmpty;
    }
    
    /**
     * Compares product price with cart price
     * @param productPrice Product page price
     * @param cartPrice Cart price
     * @return true if prices match
     */
    public boolean comparePrices(String productPrice, String cartPrice) {
        boolean pricesMatch = productPrice.equals(cartPrice);
        logger.info("Price comparison - Product: {}, Cart: {}, Match: {}", 
                   productPrice, cartPrice, pricesMatch);
        return pricesMatch;
    }
}
