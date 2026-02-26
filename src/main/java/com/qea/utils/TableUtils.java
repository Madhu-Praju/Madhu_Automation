package com.qea.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TableUtils {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String TABLE_SELECTOR = "//table[contains(@class, 'MuiTable-root')]";
    private static final String HEADER_SELECTOR = TABLE_SELECTOR + "//thead//th";
    private static final String ROW_SELECTOR = TABLE_SELECTOR + "//tbody//tr";
    
    public TableUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Gets all table headers
     * @return List of header texts
     */
    public List<String> getTableHeaders() {
        List<WebElement> headerElements = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(HEADER_SELECTOR))
        );
        
        return headerElements.stream()
                .map(this::getHeaderText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }
    
    /**
     * Gets data from a specific column by header name
     * @param columnName Name of the column header
     * @return List of values in that column
     */
    public List<String> getColumnData(String columnName) {
        List<String> headers = getTableHeaders();
        int columnIndex = headers.indexOf(columnName);
        
        if (columnIndex == -1) {
            throw new RuntimeException("Column '" + columnName + "' not found in table");
        }
        
        List<WebElement> rows = driver.findElements(By.xpath(ROW_SELECTOR));
        List<String> columnData = new ArrayList<>();
        
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            if (columnIndex < cells.size()) {
                String cellText = getCellText(cells.get(columnIndex));
                columnData.add(cellText);
            }
        }
        
        return columnData;
    }
    
    /**
     * Gets all table data as a map with column names as keys
     * @return Map containing all table data
     */
    public Map<String, List<String>> getAllTableData() {
        List<String> headers = getTableHeaders();
        Map<String, List<String>> tableData = new LinkedHashMap<>();
        
        // Initialize lists for each column
        headers.forEach(header -> tableData.put(header, new ArrayList<>()));
        
        List<WebElement> rows = driver.findElements(By.xpath(ROW_SELECTOR));
        
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            
            for (int i = 0; i < Math.min(headers.size(), cells.size()); i++) {
                String cellText = getCellText(cells.get(i));
                String header = headers.get(i);
                tableData.get(header).add(cellText);
            }
        }
        
        return tableData;
    }
    
    /**
     * Prints complete table data to console in a formatted way
     */
    public void printCompleteTableData() {
        Map<String, List<String>> tableData = getAllTableData();
        List<String> headers = new ArrayList<>(tableData.keySet());
        
        // Print headers
        System.out.printf("%-15s", "Row #");
        headers.forEach(header -> System.out.printf("%-20s", header));
        System.out.println();
        System.out.println("=".repeat(15 + (headers.size() * 20)));
        
        // Determine number of rows
        int maxRows = tableData.values().stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);
        
        // Print data rows
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            System.out.printf("%-15s", "Row " + (rowIndex + 1));
            
            for (String header : headers) {
                List<String> columnData = tableData.get(header);
                String cellValue = rowIndex < columnData.size() ? 
                    columnData.get(rowIndex) : "";
                System.out.printf("%-20s", truncateText(cellValue, 18));
            }
            System.out.println();
        }
    }
    
    /**
     * Finds a row by Record ID and returns all its data
     * @param recordId Record ID to search for
     * @return Map containing row data with column names as keys
     */
    public Map<String, String> getRowDataByRecordId(String recordId) {
        Map<String, List<String>> tableData = getAllTableData();
        List<String> recordIds = tableData.get("Record ID");
        
        if (recordIds == null) {
            throw new RuntimeException("Record ID column not found in table");
        }
        
        int rowIndex = recordIds.indexOf(recordId);
        if (rowIndex == -1) {
            return new HashMap<>(); // Return empty map if not found
        }
        
        Map<String, String> rowData = new HashMap<>();
        tableData.forEach((columnName, columnData) -> {
            if (rowIndex < columnData.size()) {
                rowData.put(columnName, columnData.get(rowIndex));
            }
        });
        
        return rowData;
    }
    
    /**
     * Validates if a specific record exists in the table
     * @param recordId Record ID to validate
     * @param expectedListName Expected list name for validation
     * @return true if record exists with correct list name
     */
    public boolean validateRecord(String recordId, String expectedListName) {
        Map<String, String> rowData = getRowDataByRecordId(recordId);
        
        if (rowData.isEmpty()) {
            System.out.println("Record ID '" + recordId + "' not found in table");
            return false;
        }
        
        String actualListName = rowData.get("List name");
        boolean isValid = expectedListName.equalsIgnoreCase(actualListName);
        
        if (!isValid) {
            System.out.println("List name mismatch for Record ID '" + recordId + 
                             "'. Expected: '" + expectedListName + 
                             "', Actual: '" + actualListName + "'");
        }
        
        return isValid;
    }
    
    /**
     * Gets table row count
     * @return Number of data rows in table
     */
    public int getTableRowCount() {
        List<WebElement> rows = driver.findElements(By.xpath(ROW_SELECTOR));
        return rows.size();
    }
    
    /**
     * Checks if table is empty
     * @return true if table has no data rows
     */
    public boolean isTableEmpty() {
        return getTableRowCount() == 0;
    }
    
    /**
     * Gets text from header element, handling nested structures
     * @param headerElement Header WebElement
     * @return Clean header text
     */
    private String getHeaderText(WebElement headerElement) {
        try {
            // Try to find header-label div first
            WebElement headerLabel = headerElement.findElement(
                By.xpath(".//div[contains(@class, 'header-label')]")
            );
            return headerLabel.getText().trim();
        } catch (Exception e) {
            // Fallback to element text
            return headerElement.getText().trim();
        }
    }
    
    /**
     * Gets text from table cell, handling nested structures
     * @param cellElement Cell WebElement
     * @return Clean cell text
     */
    private String getCellText(WebElement cellElement) {
        try {
            // Check for string-cell class first
            WebElement stringCell = cellElement.findElement(
                By.xpath(".//div[contains(@class, 'string-cell')]")
            );
            return stringCell.getText().trim();
        } catch (Exception e1) {
            try {
                // Check for link-cell class
                WebElement linkCell = cellElement.findElement(
                    By.xpath(".//div[contains(@class, 'link-cell')]")
                );
                return linkCell.getText().trim();
            } catch (Exception e2) {
                // Fallback to cell text
                return cellElement.getText().trim();
            }
        }
    }
    
    /**
     * Truncates text to specified length for console display
     * @param text Text to truncate
     * @param maxLength Maximum length
     * @return Truncated text
     */
    private String truncateText(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? 
            text.substring(0, maxLength - 3) + "..." : text;
    }
    
    /**
     * Waits for table to load completely
     */
    public void waitForTableToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TABLE_SELECTOR)));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(HEADER_SELECTOR)));
    }
    
    /**
     * Prints table statistics
     */
    public void printTableStatistics() {
        int rowCount = getTableRowCount();
        List<String> headers = getTableHeaders();
        
        System.out.println("\n========== TABLE STATISTICS ==========");
        System.out.println("Total Rows: " + rowCount);
        System.out.println("Total Columns: " + headers.size());
        System.out.println("Column Headers: " + String.join(", ", headers));
        System.out.println("=====================================\n");
    }
}