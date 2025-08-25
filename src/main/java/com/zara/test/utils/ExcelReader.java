package com.zara.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class for reading data from Excel files
 */
public class ExcelReader {
    private static final Logger logger = LogManager.getLogger(ExcelReader.class);
    
    private ExcelReader() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Reads a cell value from Excel file
     * @param filePath Path to the Excel file
     * @param rowIndex Row index (0-based)
     * @param columnIndex Column index (0-based)
     * @return Cell value as String
     */
    public static String readCellValue(String filePath, int rowIndex, int columnIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowIndex);
            
            if (row != null) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            return cell.getStringCellValue();
                        case NUMERIC:
                            return String.valueOf((int) cell.getNumericCellValue());
                        case BOOLEAN:
                            return String.valueOf(cell.getBooleanCellValue());
                        default:
                            return "";
                    }
                }
            }
            
        } catch (IOException e) {
            logger.error("Error reading Excel file: " + e.getMessage(), e);
        }
        
        return "";
    }
    
    /**
     * Reads search terms from Excel file
     * @param filePath Path to the Excel file
     * @return Array containing search terms
     */
    public static String[] readSearchTerms(String filePath) {
        String[] searchTerms = new String[2];
        
        // Read "şort" from column 0, row 0
        searchTerms[0] = readCellValue(filePath, 0, 0);
        
        // Read "gömlek" from column 1, row 0
        searchTerms[1] = readCellValue(filePath, 0, 1);
        
        logger.info("Read search terms from Excel: {} and {}", searchTerms[0], searchTerms[1]);
        return searchTerms;
    }
}
