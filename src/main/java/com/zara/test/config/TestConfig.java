package com.zara.test.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Configuration class for test constants and settings
 */
public class TestConfig {
    private static final Logger logger = LogManager.getLogger(TestConfig.class);
    
    // Base URL
    public static final String BASE_URL = "https://www.zara.com/tr/";
    
    // Login credentials
    public static final String EMAIL = "refik.6547@gmail.com";
    public static final String PASSWORD = "Test123test";
    
    // Excel file path
    public static final String EXCEL_FILE_PATH = "src/test/resources/testdata.xlsx";
    
    // Output file path
    public static final String OUTPUT_FILE_PATH = "src/test/resources/product_info.txt";
    
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    
    // Test data
    public static final String SHORT_SEARCH_TERM = "şort";
    public static final String SHIRT_SEARCH_TERM = "gömlek";
    
    private TestConfig() {
        // Private constructor to prevent instantiation
    }
}
