package com.zara.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for writing data to text files
 */
public class FileWriter {
    private static final Logger logger = LogManager.getLogger(FileWriter.class);
    
    private FileWriter() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Writes product information to a text file
     * @param filePath Path to the output file
     * @param productName Name of the product
     * @param productPrice Price of the product
     */
    public static void writeProductInfo(String filePath, String productName, String productPrice) {
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            writer.println("=== Product Information ===");
            writer.println("Date: " + now.format(formatter));
            writer.println("Product Name: " + productName);
            writer.println("Product Price: " + productPrice);
            writer.println("==========================");
            writer.println();
            
            logger.info("Product information written to file: {}", filePath);
            
        } catch (IOException e) {
            logger.error("Error writing to file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Clears the content of a file
     * @param filePath Path to the file to clear
     */
    public static void clearFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {
            writer.print("");
            logger.info("File cleared: {}", filePath);
        } catch (IOException e) {
            logger.error("Error clearing file: " + e.getMessage(), e);
        }
    }
}
