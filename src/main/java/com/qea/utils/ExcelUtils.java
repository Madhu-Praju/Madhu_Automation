package com.qea.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ExcelUtils {
    
    private Workbook workbook;
    private Sheet sheet;
    
    /**
     * Opens Excel file and initializes the workbook
     * @param filePath Path to Excel file
     */
    public void openExcelFile(String filePath) {
        try {
            // Validate file path
            if (filePath == null || filePath.trim().isEmpty()) {
                throw new IllegalArgumentException("File path cannot be null or empty");
            }
            
            File file = new File(filePath);
            
            // Check if file exists
            if (!file.exists()) {
                throw new RuntimeException("Excel file does not exist at path: " + file.getAbsolutePath());
            }
            
            // Check if file is readable
            if (!file.canRead()) {
                throw new RuntimeException("Excel file is not readable at path: " + file.getAbsolutePath());
            }
            
            // Check if file is not empty
            if (file.length() == 0) {
                throw new RuntimeException("Excel file is empty at path: " + file.getAbsolutePath());
            }
            
            System.out.println("Opening Excel file: " + file.getAbsolutePath());
            System.out.println("File size: " + file.length() + " bytes");
            
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(0); // Default to first sheet
            
            System.out.println("Excel file opened successfully. Sheet name: " + sheet.getSheetName());
            
        } catch (IOException e) {
            throw new RuntimeException("Error opening Excel file: " + filePath + ". Error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error opening Excel file: " + filePath + ". Error: " + e.getMessage(), e);
        }
    }
    
    /**
     * Gets search data by List Name from Excel
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @return Map containing listName and recordId
     */
    public Map<String, String> getSearchDataByListName(String filePath, String listName) {
        openExcelFile(filePath);
        
        Map<String, String> searchData = new HashMap<>();
        
        try {
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = -1;
            int recordIdColumnIndex = -1;
            
            for (Cell cell : headerRow) {
                String headerValue = getCellValueAsString(cell).trim();
                if ("List Name".equalsIgnoreCase(headerValue)) {
                    listNameColumnIndex = cell.getColumnIndex();
                } else if ("Record ID".equalsIgnoreCase(headerValue)) {
                    recordIdColumnIndex = cell.getColumnIndex();
                }
            }
            
            if (listNameColumnIndex == -1 || recordIdColumnIndex == -1) {
                throw new RuntimeException("Required columns 'List Name' or 'Record ID' not found in Excel");
            }
            
            // Search for the matching list name
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                Cell listNameCell = row.getCell(listNameColumnIndex);
                String cellListName = getCellValueAsString(listNameCell);
                
                if (listName.equalsIgnoreCase(cellListName.trim())) {
                    Cell recordIdCell = row.getCell(recordIdColumnIndex);
                    String recordId = getCellValueAsString(recordIdCell);
                    
                    searchData.put("listName", cellListName.trim());
                    searchData.put("recordId", recordId.trim());
                    break;
                }
            }
            
        } finally {
            closeExcelFile();
        }
        
        return searchData.isEmpty() ? null : searchData;
    }
    
    /**
     * Gets search data by List Name and data type from Excel
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @param dataType Type of data to retrieve ("Record ID", "Name", or "ID Value")
     * @return Map containing listName and the specified data type value
     */
    public Map<String, String> getSearchDataByListNameAndType(String filePath, String listName, String dataType) {
        openExcelFile(filePath);
        
        Map<String, String> searchData = new HashMap<>();
        
        try {
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int dataColumnIndex = findColumnIndex(headerRow, dataType);
            
            if (listNameColumnIndex == -1) {
                throw new RuntimeException("'List Name' column not found in Excel");
            }
            
            if (dataColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("'" + dataType + "' column not found in Excel");
            }
            
            // Search for the matching list name
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                Cell listNameCell = row.getCell(listNameColumnIndex);
                String cellListName = getCellValueAsString(listNameCell);
                
                if (listName.equalsIgnoreCase(cellListName.trim())) {
                    Cell dataCell = row.getCell(dataColumnIndex);
                    String dataValue = getCellValueAsString(dataCell);
                    
                    searchData.put("listName", cellListName.trim());
                    searchData.put(dataType.toLowerCase().replace(" ", ""), dataValue.trim());
                    break;
                }
            }
            
        } finally {
            closeExcelFile();
        }
        
        return searchData.isEmpty() ? null : searchData;
    }
    
    /**
     * Gets all test data from Excel file (backward compatibility)
     * @param filePath Path to Excel file
     * @return Map with List Name as key and Record ID as value
     */
    public Map<String, String> getAllTestDataRecordId(String filePath) {
        Map<String, String> testData = new LinkedHashMap<>(); // Preserve order
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int recordIdColumnIndex = findColumnIndex(headerRow, "Record ID");

            if (listNameColumnIndex == -1 || recordIdColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("Required columns 'List Name' or 'Record ID' not found in Excel");
            }
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                String recordId = getCellValueAsString(row.getCell(recordIdColumnIndex)).trim();
                
                if (!listName.isEmpty() && !recordId.isEmpty()) {
                    testData.put(listName, recordId);
                    System.out.println("Loaded: " + listName + " -> " + recordId);
                } else {
                    System.out.println("Skipping row " + (rowIndex + 1) + " - empty data");
                }
                
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("No valid test data found in Excel file");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return testData;
    }
    
    /**
     * Gets all test data from Excel file
     * @param filePath Path to Excel file
     * @return Map with List Name as key and inner Map containing Record ID, Name, and ID Value
     */
    public Map<String, Map<String, String>> getAllTestData(String filePath) {
        Map<String, Map<String, String>> testData = new LinkedHashMap<>(); // Preserve order
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int recordIdColumnIndex = findColumnIndex(headerRow, "Record ID");
            int nameColumnIndex = findColumnIndex(headerRow, "Name");
            int idValueColumnIndex = findColumnIndex(headerRow, "ID Value");

            if (listNameColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("Required column 'List Name' not found in Excel");
            }
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                
                if (!listName.isEmpty()) {
                    Map<String, String> rowData = new HashMap<>();
                    
                    // Get Record ID if column exists
                    if (recordIdColumnIndex != -1) {
                        String recordId = getCellValueAsString(row.getCell(recordIdColumnIndex)).trim();
                        rowData.put("recordId", recordId);
                    }
                    
                    // Get Name if column exists
                    if (nameColumnIndex != -1) {
                        String name = getCellValueAsString(row.getCell(nameColumnIndex)).trim();
                        rowData.put("name", name);
                    }
                    
                    // Get ID Value if column exists
                    if (idValueColumnIndex != -1) {
                        String idValue = getCellValueAsString(row.getCell(idValueColumnIndex)).trim();
                        rowData.put("idValue", idValue);
                    }
                    
                    testData.put(listName, rowData);
                    System.out.println("Loaded: " + listName + " -> " + rowData);
                } else {
                    System.out.println("Skipping row " + (rowIndex + 1) + " - empty List Name");
                }
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("No valid test data found in Excel file");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return testData;
    }
    
    /**
     * Gets all test data with Name from Excel file
     * @param filePath Path to Excel file
     * @return Map with List Name as key and Name as value
     */
    public Map<String, String> getAllTestDataByName(String filePath) {
        Map<String, String> testData = new LinkedHashMap<>(); // Preserve order
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int nameColumnIndex = findColumnIndex(headerRow, "Name");

            if (listNameColumnIndex == -1 || nameColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("Required columns 'List Name' or 'Name' not found in Excel");
            }
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                String name = getCellValueAsString(row.getCell(nameColumnIndex)).trim();
                
                if (!listName.isEmpty() && !name.isEmpty()) {
                    testData.put(listName, name);
                    System.out.println("Loaded: " + listName + " -> " + name);
                } else {
                    System.out.println("Skipping row " + (rowIndex + 1) + " - empty data");
                }
                
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("No valid test data found in Excel file");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return testData;
    }
    
    /**
     * Gets all test data with ID Value from Excel file
     * @param filePath Path to Excel file
     * @return Map with List Name as key and ID Value as value
     */
    public Map<String, String> getAllTestDataByIdValue(String filePath) {
        Map<String, String> testData = new LinkedHashMap<>(); // Preserve order
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int idValueColumnIndex = findColumnIndex(headerRow, "ID Value");

            if (listNameColumnIndex == -1 || idValueColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("Required columns 'List Name' or 'ID Value' not found in Excel");
            }
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                String idValue = getCellValueAsString(row.getCell(idValueColumnIndex)).trim();
                
                if (!listName.isEmpty() && !idValue.isEmpty()) {
                    testData.put(listName, idValue);
                    System.out.println("Loaded: " + listName + " -> " + idValue);
                } else {
                    System.out.println("Skipping row " + (rowIndex + 1) + " - empty data");
                }
                
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("No valid test data found in Excel file");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return testData;
    }
    
    /**
     * Gets specific data by List Name and data type (Record ID, Name, or ID Value)
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @param dataType Type of data to retrieve ("Record ID", "Name", or "ID Value")
     * @return String value of the specified data type for the given list name
     */
    public String getDataByListNameAndType(String filePath, String listName, String dataType) {
        String result = null;
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int dataColumnIndex = findColumnIndex(headerRow, dataType);

            if (listNameColumnIndex == -1) {
                throw new RuntimeException("'List Name' column not found in Excel");
            }
            
            if (dataColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("'" + dataType + "' column not found in Excel");
            }
            
            // Search for the matching list name
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String cellListName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                
                if (listName.equalsIgnoreCase(cellListName)) {
                    result = getCellValueAsString(row.getCell(dataColumnIndex)).trim();
                    System.out.println("Found " + dataType + ": " + result + " for List Name: " + listName);
                    break;
                }
            }
            
            if (result == null || result.isEmpty()) {
                throw new RuntimeException("No " + dataType + " found for List Name: " + listName);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving " + dataType + " for List Name '" + listName + "': " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return result;
    }
    
    /**
     * Gets Record ID by List Name
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @return Record ID for the specified list name
     */
    public String getRecordIdByListName(String filePath, String listName) {
        return getDataByListNameAndType(filePath, listName, "Record ID");
    }
    
    /**
     * Gets Name by List Name
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @return Name for the specified list name
     */
    public String getNameByListName(String filePath, String listName) {
        return getDataByListNameAndType(filePath, listName, "Name");
    }
    
    /**
     * Gets ID Value by List Name
     * @param filePath Path to Excel file
     * @param listName List name to search for
     * @return ID Value for the specified list name
     */
    public String getIdValueByListName(String filePath, String listName) {
        return getDataByListNameAndType(filePath, listName, "ID Value");
    }
    
    /**
     * Finds column index by header name (case-insensitive)
     * @param headerRow Header row
     * @param columnName Column name to find
     * @return Column index or -1 if not found
     */
    private int findColumnIndex(Row headerRow, String columnName) {
        for (Cell cell : headerRow) {
            String headerValue = getCellValueAsString(cell).trim();
            if (columnName.equalsIgnoreCase(headerValue)) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }
    
    /**
     * Prints available headers for debugging
     * @param headerRow Header row
     */
    private void printAvailableHeaders(Row headerRow) {
        System.out.println("Available headers in Excel file:");
        for (Cell cell : headerRow) {
            System.out.println("- '" + getCellValueAsString(cell).trim() + "'");
        }
    }
    
    /**
     * Validates Excel file structure
     * @param filePath Path to Excel file
     * @return true if file has required structure
     */
    public boolean validateExcelStructure(String filePath) {
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                return false;
            }
            
            Row headerRow = sheet.getRow(0);
            int listNameIndex = findColumnIndex(headerRow, "List Name");
            int recordIdIndex = findColumnIndex(headerRow, "Record ID");
            
            return listNameIndex != -1 && recordIdIndex != -1;
            
        } catch (Exception e) {
            return false;
        } finally {
            closeExcelFile();
        }
    }
    
    /**
     * Writes data to Excel file
     * @param filePath Path to Excel file
     * @param data Map containing data to write
     */
    public void writeTestData(String filePath, Map<String, String> data) {
        openExcelFile(filePath);
        
        try {
            // Create header row if sheet is empty
            if (sheet.getPhysicalNumberOfRows() == 0) {
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("List Name");
                headerRow.createCell(1).setCellValue("Record ID");
            }
            
            int rowNum = sheet.getLastRowNum() + 1;
            
            for (Map.Entry<String, String> entry : data.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }
            
            // Auto-size columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            
        } finally {
            closeExcelFile();
        }
    }
    
    /**
     * Gets cell value as string regardless of cell type
     * @param cell Excel cell
     * @return String value of the cell
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Handle numeric values that should be strings (like IDs)
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    
    /**
     * Closes the Excel file and releases resources
     */
    private void closeExcelFile() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing Excel file: " + e.getMessage());
        }
    }
    
    /**
     * Prints all test data from Excel file for debugging
     * @param filePath Path to Excel file
     */
    public void printAllTestData(String filePath) {
        Map<String, String> testData = getAllTestDataRecordId(filePath);
        System.out.println("\n========== EXCEL TEST DATA (Record ID) ==========");
        testData.forEach((listName, recordId) -> 
            System.out.println("List Name: " + listName + " | Record ID: " + recordId));
        System.out.println("=================================================\n");
    }
    
    /**
     * Prints all comprehensive test data from Excel file for debugging
     * @param filePath Path to Excel file
     */
    public void printAllTestDataEnhanced(String filePath) {
        Map<String, Map<String, String>> testData = getAllTestData(filePath);
        System.out.println("\n========== ENHANCED EXCEL TEST DATA ==========");
        testData.forEach((listName, dataMap) -> {
            System.out.println("List Name: " + listName);
            dataMap.forEach((key, value) -> 
                System.out.println("  " + key + ": " + value));
            System.out.println("---");
        });
        System.out.println("==============================================\n");
    }
    
    /**
     * Prints all test data by Name from Excel file for debugging
     * @param filePath Path to Excel file
     */
    public void printAllTestDataByName(String filePath) {
        Map<String, String> testData = getAllTestDataByName(filePath);
        System.out.println("\n========== EXCEL TEST DATA (Name) ==========");
        testData.forEach((listName, name) -> 
            System.out.println("List Name: " + listName + " | Name: " + name));
        System.out.println("============================================\n");
    }
    
    /**
     * Prints all test data by ID Value from Excel file for debugging
     * @param filePath Path to Excel file
     */
    public void printAllTestDataByIdValue(String filePath) {
        Map<String, String> testData = getAllTestDataByIdValue(filePath);
        System.out.println("\n========== EXCEL TEST DATA (ID Value) ==========");
        testData.forEach((listName, idValue) -> 
            System.out.println("List Name: " + listName + " | ID Value: " + idValue));
        System.out.println("===============================================\n");
    }
    
    /**
     * Prints all test data for all columns from Excel file for debugging
     * @param filePath Path to Excel file
     */
    public void printCompleteTestData(String filePath) {
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                System.out.println("Excel file is empty: " + filePath);
                return;
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int recordIdColumnIndex = findColumnIndex(headerRow, "Record ID");
            int nameColumnIndex = findColumnIndex(headerRow, "Name");
            int idValueColumnIndex = findColumnIndex(headerRow, "ID Value");
            
            System.out.println("\n========== COMPLETE EXCEL TEST DATA ==========");
            System.out.printf("%-20s | %-15s | %-25s | %-15s%n", "List Name", "Record ID", "Name", "ID Value");
            System.out.println("------------------------------------------------------------");
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = listNameColumnIndex != -1 ? getCellValueAsString(row.getCell(listNameColumnIndex)).trim() : "";
                String recordId = recordIdColumnIndex != -1 ? getCellValueAsString(row.getCell(recordIdColumnIndex)).trim() : "";
                String name = nameColumnIndex != -1 ? getCellValueAsString(row.getCell(nameColumnIndex)).trim() : "";
                String idValue = idValueColumnIndex != -1 ? getCellValueAsString(row.getCell(idValueColumnIndex)).trim() : "";
                
                System.out.printf("%-20s | %-15s | %-25s | %-15s%n", 
                    listName.length() > 20 ? listName.substring(0, 17) + "..." : listName,
                    recordId.length() > 15 ? recordId.substring(0, 12) + "..." : recordId,
                    name.length() > 25 ? name.substring(0, 22) + "..." : name,
                    idValue.length() > 15 ? idValue.substring(0, 12) + "..." : idValue);
            }
            
            System.out.println("==============================================\n");
            
        } catch (Exception e) {
            System.err.println("Error printing complete test data: " + e.getMessage());
        } finally {
            closeExcelFile();
        }
    }
    
    /**
     * Example method demonstrating how to use the enhanced getAllTestData method
     * @param filePath Path to Excel file
     */
    public void demonstrateEnhancedGetAllTestData(String filePath) {
        System.out.println("\n========== ENHANCED getAllTestData DEMONSTRATION ==========");
        
        Map<String, Map<String, String>> allData = getAllTestData(filePath);
        
        for (Map.Entry<String, Map<String, String>> entry : allData.entrySet()) {
            String listName = entry.getKey();
            Map<String, String> dataMap = entry.getValue();
            
            System.out.println("List Name: " + listName);
            
            // Access individual data types
            String recordId = dataMap.get("recordId");
            String name = dataMap.get("name");
            String idValue = dataMap.get("idValue");
            
            System.out.println("  Record ID: " + (recordId != null ? recordId : "Not available"));
            System.out.println("  Name: " + (name != null ? name : "Not available"));
            System.out.println("  ID Value: " + (idValue != null ? idValue : "Not available"));
            System.out.println("---");
        }
        
        System.out.println("Total records processed: " + allData.size());
        System.out.println("===========================================================\n");
    }
    
    /**
     * Gets all test data with Watchlist types from Excel file
     * @param filePath Path to Excel file
     * @return Map with List Name as key and Map containing all data including Watchlist
     */
    public Map<String, Map<String, String>> getAllTestDataWithWatchlist(String filePath) {
        Map<String, Map<String, String>> testData = new LinkedHashMap<>(); // Preserve order
        
        try {
            openExcelFile(filePath);
            
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new RuntimeException("Excel file is empty: " + filePath);
            }
            
            // Find header row indices
            Row headerRow = sheet.getRow(0);
            int listNameColumnIndex = findColumnIndex(headerRow, "List Name");
            int watchlistColumnIndex = findColumnIndex(headerRow, "Watchlist");
            int recordIdColumnIndex = findColumnIndex(headerRow, "Record ID");
            int nameColumnIndex = findColumnIndex(headerRow, "Name");
            int idValueColumnIndex = findColumnIndex(headerRow, "ID Value");

            if (listNameColumnIndex == -1) {
                printAvailableHeaders(headerRow);
                throw new RuntimeException("Required column 'List Name' not found in Excel");
            }
            
            // Read all data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                String listName = getCellValueAsString(row.getCell(listNameColumnIndex)).trim();
                
                if (!listName.isEmpty()) {
                    Map<String, String> rowData = new HashMap<>();
                    
                    // Get Watchlist type if column exists
                    if (watchlistColumnIndex != -1) {
                        String watchlist = getCellValueAsString(row.getCell(watchlistColumnIndex)).trim();
                        rowData.put("watchlist", watchlist);
                    }
                    
                    // Get Record ID if column exists
                    if (recordIdColumnIndex != -1) {
                        String recordId = getCellValueAsString(row.getCell(recordIdColumnIndex)).trim();
                        rowData.put("recordId", recordId);
                    }
                    
                    // Get Name if column exists
                    if (nameColumnIndex != -1) {
                        String name = getCellValueAsString(row.getCell(nameColumnIndex)).trim();
                        rowData.put("name", name);
                    }
                    
                    // Get ID Value if column exists
                    if (idValueColumnIndex != -1) {
                        String idValue = getCellValueAsString(row.getCell(idValueColumnIndex)).trim();
                        rowData.put("idValue", idValue);
                    }
                    
                    testData.put(listName, rowData);
                    System.out.println("Loaded: " + listName + " -> " + rowData);
                } else {
                    System.out.println("Skipping row " + (rowIndex + 1) + " - empty List Name");
                }
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("No valid test data found in Excel file");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            closeExcelFile();
        }
        
        return testData;
    }
}