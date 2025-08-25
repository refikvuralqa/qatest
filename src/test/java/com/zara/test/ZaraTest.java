package com.zara.test;

import com.zara.test.config.TestConfig;
import com.zara.test.pages.*;
import com.zara.test.utils.ExcelReader;
import com.zara.test.utils.FileWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Main test class for Zara website automation
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZaraTest {
    
    private static final Logger logger = LogManager.getLogger(ZaraTest.class);
    private static WebDriver driver;
    private static HomePage homePage;
    private static SearchPage searchPage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    
    @BeforeAll
    static void setUp() {
        logger.info("Setting up WebDriver");
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(TestConfig.IMPLICIT_WAIT));
        
        // Initialize page objects
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        
        logger.info("WebDriver setup completed");
    }
    
    @AfterAll
    static void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
        }
    }
    
    @Test
    @Order(1)
    @DisplayName("Complete Zara Website Test Flow")
    void testZaraWebsiteFlow() {
        try {
            logger.info("Starting Zara website test flow");
            
            // Step 1: Navigate to Zara website
            navigateToZaraWebsite();
            
            // Step 2: Accept cookies
            acceptCookies();
            
            // Step 3: Login to the website
            loginToWebsite();
            
            // Step 4: Navigate to men's category
            navigateToMensCategory();
            
            // Step 5: Search for "şort" and clear
            searchForShorts();
            
            // Step 6: Search for "gömlek" and select product
            searchForShirts();
            
            // Step 7: Get product information and add to cart
            ProductInfo productInfo = getProductInfoAndAddToCart();
            
            // Step 8: Verify cart and manage quantity
            verifyCartAndManageQuantity(productInfo);
            
            // Step 9: Remove item and verify empty cart
            removeItemAndVerifyEmptyCart();
            
            logger.info("Zara website test flow completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    private void navigateToZaraWebsite() {
        logger.info("Navigating to Zara website: {}", TestConfig.BASE_URL);
        driver.get(TestConfig.BASE_URL);
    }
    
    private void acceptCookies() {
        logger.info("Accepting cookies");
        homePage.acceptCookies();
    }
    
    private void loginToWebsite() {
        logger.info("Logging in to website");
        homePage.login(TestConfig.EMAIL, TestConfig.PASSWORD);
    }
    
    private void navigateToMensCategory() {
        logger.info("Navigating to men's category");
        homePage.navigateToMenCategory();
    }
    
    private void searchForShorts() {
        logger.info("Searching for shorts");
        String[] searchTerms = ExcelReader.readSearchTerms(TestConfig.EXCEL_FILE_PATH);
        String shortsTerm = searchTerms[0];
        
        // Click search content and enter shorts term
        searchPage.clickSearchContent();
        searchPage.enterSearchTerm(shortsTerm);
        
        // Wait a bit after entering shorts
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Clear the search input
        searchPage.clearSearchInput();
        
        // Wait a bit after clearing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void searchForShirts() {
        logger.info("Searching for shirts");
        String[] searchTerms = ExcelReader.readSearchTerms(TestConfig.EXCEL_FILE_PATH);
        String shirtsTerm = searchTerms[1];
        
        // Enter gömlek term and press Enter
        searchPage.enterSearchTerm(shirtsTerm);
        
        // Wait a bit after entering gömlek
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Press Enter to perform search
        searchPage.pressEnterOnSearch();
    }
    
    private ProductInfo getProductInfoAndAddToCart() {
        logger.info("Getting product information and adding to cart");
        
        // Select random product
        ProductPage selectedProductPage = searchPage.selectRandomProduct();
        
        // Get product information
        String productName = selectedProductPage.getProductName();
        String productPrice = selectedProductPage.getProductPrice();
        
        // Write product information to file
        FileWriter.writeProductInfo(TestConfig.OUTPUT_FILE_PATH, productName, productPrice);
        
        // Add product to cart
        selectedProductPage.addProductToCart();
        
        return new ProductInfo(productName, productPrice);
    }
    
    private void verifyCartAndManageQuantity(ProductInfo productInfo) {
        logger.info("Verifying cart and managing quantity");
        
        // Get cart price
        String cartPrice = cartPage.getCartItemPrice();
        
        // Compare prices
        boolean pricesMatch = cartPage.comparePrices(productInfo.getPrice(), cartPrice);
        Assertions.assertTrue(pricesMatch, "Product price and cart price should match");
        
        // Increase quantity
        cartPage.increaseQuantity();
        
        // Verify quantity is 2
        boolean quantityIsTwo = cartPage.verifyQuantityIsTwo();
        Assertions.assertTrue(quantityIsTwo, "Quantity should be 2");
    }
    
    private void removeItemAndVerifyEmptyCart() {
        logger.info("Removing item and verifying empty cart");
        
        // Remove item from cart
        cartPage.removeItemFromCart();
        
        // Verify cart is empty
        boolean cartIsEmpty = cartPage.verifyCartIsEmpty();
        Assertions.assertTrue(cartIsEmpty, "Cart should be empty");
    }
    
    /**
     * Inner class to hold product information
     */
    private static class ProductInfo {
        private final String name;
        private final String price;
        
        public ProductInfo(String name, String price) {
            this.name = name;
            this.price = price;
        }
        
        public String getName() {
            return name;
        }
        
        public String getPrice() {
            return price;
        }
    }
}
