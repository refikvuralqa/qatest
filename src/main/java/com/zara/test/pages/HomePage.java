package com.zara.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Home page object containing elements and methods for the main page
 */
public class HomePage extends BasePage {
    
    // Locators
    private static final By COOKIE_ACCEPT_BUTTON = By.id("onetrust-accept-btn-handler");
    private static final By LOGIN_BUTTON = By.xpath("//a[contains(text(),'GİRİŞ YAP')]");
    private static final By EMAIL_FIELD = By.xpath("//input[@id='zds-:r5:']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[id='zds-:r8:']");
    private static final By SIGN_IN_BUTTON = By.xpath("//button[normalize-space()='Oturum aç']");
    private static final By MENU_BUTTON = By.xpath("//button[@aria-label='Menüyü aç']//*[name()='svg']");
    private static final By MEN_CATEGORY = By.xpath("//span[@class='layout-categories-category-wrapper layout-categories-category-level-1__content']//span[@class='layout-categories-category-name'][normalize-space()='ERKEK']");
    private static final By VIEW_ALL_BUTTON = By.xpath("//span[normalize-space()='TÜMÜNÜ GÖR']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Accepts cookies
     */
    public void acceptCookies() {
        logger.info("Accepting cookies");
        waitAndClick(COOKIE_ACCEPT_BUTTON);
    }
    
    /**
     * Clicks on login button
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        waitAndClick(LOGIN_BUTTON);
    }
    
    /**
     * Enters email address
     * @param email Email address to enter
     */
    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        waitAndSendKeys(EMAIL_FIELD, email);
    }
    
    /**
     * Enters password
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        waitAndSendKeys(PASSWORD_FIELD, password);
    }
    
    /**
     * Clicks sign in button
     */
    public void clickSignInButton() {
        logger.info("Clicking sign in button");
        waitAndClick(SIGN_IN_BUTTON);
    }
    
    /**
     * Performs complete login process
     * @param email Email address
     * @param password Password
     */
    public void login(String email, String password) {
        clickLoginButton();
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }
    
    /**
     * Opens menu
     */
    public void openMenu() {
        logger.info("Opening menu");
        waitAndClick(MENU_BUTTON);
    }
    
    /**
     * Clicks on men's category
     */
    public void clickMenCategory() {
        logger.info("Clicking men's category");
        waitAndClick(MEN_CATEGORY);
    }
    
    /**
     * Clicks view all button
     */
    public void clickViewAllButton() {
        logger.info("Clicking view all button");
        waitAndClick(VIEW_ALL_BUTTON);
    }
    
    /**
     * Navigates to men's category
     */
    public void navigateToMenCategory() {
        openMenu();
        clickMenCategory();
        clickViewAllButton();
    }
}
