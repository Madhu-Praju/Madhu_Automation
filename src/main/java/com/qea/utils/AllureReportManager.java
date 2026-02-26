package com.qea.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qea.factory.DriverFactory;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

/**
 * Enhanced Allure Report Manager for comprehensive test reporting
 * Provides utilities for screenshots, attachments, steps, and metadata
 * 
 * @author Facctum Test Automation Team
 */
public class AllureReportManager {
    
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Take and attach screenshot to Allure report with chronological ordering
     * @param stepName Name of the step where screenshot is taken
     * @return byte array of screenshot
     */
    @Attachment(value = "{stepName}", type = "image/png")
    public static byte[] takeScreenshot(String stepName) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null && ((TakesScreenshot) driver) != null) {
                // Generate chronologically ordered name using ScreenshotSequenceManager
                String orderedStepName = ScreenshotSequenceManager.generateManualScreenshotName(stepName);
                
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("📸 Screenshot captured for Allure: " + orderedStepName + " (Size: " + screenshot.length + " bytes)");
                
                // Save screenshot file for consolidated report with proper naming
                String screenshotPath = saveScreenshotFile(stepName, screenshot);
                if (screenshotPath != null) {
                    System.out.println("📁 Screenshot saved for consolidated report: " + screenshotPath);
                }
                
                // Also attach using Allure.addAttachment with ordered name
                try {
                    Allure.addAttachment(orderedStepName, "image/png", 
                        new java.io.ByteArrayInputStream(screenshot), ".png");
                } catch (Exception attachException) {
                    System.out.println("Allure attachment failed: " + attachException.getMessage());
                }
                
                return screenshot;
            } else {
                System.out.println("❌ Driver is null or doesn't support screenshots");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }
    
    /**
     * Take screenshot on test failure with chronological ordering
     * @param testName Name of the failed test
     * @return byte array of screenshot
     */
    @Attachment(value = "{testName}", type = "image/png")
    public static byte[] takeFailureScreenshot(String testName) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null && ((TakesScreenshot) driver) != null) {
                // Generate chronologically ordered error name
                String orderedErrorName = ScreenshotSequenceManager.generateErrorScreenshotName(testName);
                
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("🚨 Failure screenshot captured for Allure: " + orderedErrorName + " (Size: " + screenshot.length + " bytes)");
                
                // Also attach using Allure.addAttachment with ordered name
                try {
                    Allure.addAttachment(orderedErrorName, "image/png", 
                        new java.io.ByteArrayInputStream(screenshot), ".png");
                } catch (Exception attachException) {
                    System.out.println("Allure failure attachment failed: " + attachException.getMessage());
                }
                
                // Also attach browser information
                attachBrowserInfo();
                
                return screenshot;
            } else {
                System.out.println("❌ Driver is null or doesn't support screenshots for failure");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to capture failure screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }
    
    /**
     * Attach text content to Allure report
     * @param name Attachment name
     * @param content Content to attach
     */
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        try {
            System.out.println("📄 Text attachment added to Allure: " + name + " (Length: " + content.length() + " chars)");
            
            // Also use Allure.addAttachment as backup
            try {
                Allure.addAttachment(name, "text/plain", content, ".txt");
            } catch (Exception attachException) {
                System.out.println("Allure text attachment failed: " + attachException.getMessage());
            }
            
            return content;
        } catch (Exception e) {
            System.out.println("❌ Failed to attach text: " + e.getMessage());
            return content; // Return original content even if logging fails
        }
    }
    
    /**
     * Attach HTML content to Allure report
     * @param name Attachment name
     * @param htmlContent HTML content to attach
     */
    @Attachment(value = "{name}", type = "text/html")
    public static String attachHTML(String name, String htmlContent) {
        logStep("🌐 HTML attachment added: " + name);
        return htmlContent;
    }
    
    /**
     * Attach JSON content to Allure report
     * @param name Attachment name
     * @param jsonContent JSON content to attach
     */
    @Attachment(value = "{name}", type = "application/json")
    public static String attachJSON(String name, String jsonContent) {
        logStep("📋 JSON attachment added: " + name);
        return jsonContent;
    }
    
    /**
     * Log a step in Allure report
     * @param stepDescription Description of the step
     */
    @Step("{stepDescription}")
    public static void logStep(String stepDescription) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        System.out.println("[" + timestamp + "] " + stepDescription);
    }
    
    /**
     * Log a step with status
     * @param stepDescription Description of the step
     * @param status Status (PASSED, FAILED, SKIPPED)
     */
    @Step("{stepDescription} - Status: {status}")
    public static void logStepWithStatus(String stepDescription, String status) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String emoji = getStatusEmoji(status);
        System.out.println("[" + timestamp + "] " + emoji + " " + stepDescription + " - " + status);
    }
    
    /**
     * Add environment information to Allure report
     */
    public static void addEnvironmentInfo() {
        try {
            // Read configuration properties
            Properties prop = new configReader().init_pop();
            
            // Add environment information
            Allure.addAttachment("Environment Info", "text/plain", 
                buildEnvironmentInfo(prop));
            
            logStep("🌍 Environment information added to report");
            
        } catch (Exception e) {
            logStep("⚠️ Could not add environment info: " + e.getMessage());
        }
    }
    
    /**
     * Add browser information to Allure report
     */
    @Step("Adding Browser Information")
    public static void attachBrowserInfo() {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                String browserInfo = "Browser: " + driver.getClass().getSimpleName() + "\n";
                browserInfo += "Current URL: " + driver.getCurrentUrl() + "\n";
                browserInfo += "Page Title: " + driver.getTitle() + "\n";
                browserInfo += "Window Size: " + driver.manage().window().getSize() + "\n";
                browserInfo += "Timestamp: " + LocalDateTime.now().format(TIMESTAMP_FORMAT);
                
                attachText("Browser Information", browserInfo);
                logStep("🌐 Browser information attached");
            }
        } catch (Exception e) {
            logStep("⚠️ Could not attach browser info: " + e.getMessage());
        }
    }
    
    /**
     * Add test data to Allure report
     * @param testDataName Name of the test data
     * @param testData Test data content
     */
    @Step("Adding Test Data: {testDataName}")
    public static void attachTestData(String testDataName, String testData) {
        attachText("Test Data: " + testDataName, testData);
        logStep("📊 Test data attached: " + testDataName);
    }
    
    /**
     * Add Excel test data to Allure report
     * @param excelPath Path to Excel file
     * @param sheetName Sheet name
     * @param rowData Row data as string
     */
    @Step("Adding Excel Test Data from {excelPath}")
    public static void attachExcelData(String excelPath, String sheetName, String rowData) {
        String excelInfo = "Excel File: " + excelPath + "\n";
        excelInfo += "Sheet: " + sheetName + "\n";
        excelInfo += "Data: " + rowData + "\n";
        excelInfo += "Timestamp: " + LocalDateTime.now().format(TIMESTAMP_FORMAT);
        
        attachText("Excel Test Data", excelInfo);
        logStep("📈 Excel test data attached");
    }
    
    /**
     * Log API request/response information
     * @param endpoint API endpoint
     * @param method HTTP method
     * @param request Request body
     * @param response Response body
     * @param statusCode Response status code
     */
    @Step("API Call: {method} {endpoint}")
    public static void logApiCall(String endpoint, String method, String request, String response, int statusCode) {
        String apiInfo = "Endpoint: " + endpoint + "\n";
        apiInfo += "Method: " + method + "\n";
        apiInfo += "Status Code: " + statusCode + "\n";
        apiInfo += "Request: " + request + "\n";
        apiInfo += "Response: " + response + "\n";
        apiInfo += "Timestamp: " + LocalDateTime.now().format(TIMESTAMP_FORMAT);
        
        attachJSON("API Call Details", apiInfo);
        logStep("🔗 API call logged: " + method + " " + endpoint);
    }
    
    /**
     * Log search operation details
     * @param searchType Type of search (Global, List, etc.)
     * @param searchValue Search value
     * @param resultsCount Number of results
     */
    @Step("Search Operation: {searchType} search for '{searchValue}'")
    public static void logSearchOperation(String searchType, String searchValue, int resultsCount) {
        String searchInfo = "Search Type: " + searchType + "\n";
        searchInfo += "Search Value: " + searchValue + "\n";
        searchInfo += "Results Count: " + resultsCount + "\n";
        searchInfo += "Timestamp: " + LocalDateTime.now().format(TIMESTAMP_FORMAT);
        
        attachText("Search Operation Details", searchInfo);
        logStep("🔍 Search logged: " + searchType + " search returned " + resultsCount + " results");
    }
    
    /**
     * Build environment information string
     */
    private static String buildEnvironmentInfo(Properties prop) {
        StringBuilder envInfo = new StringBuilder();
        envInfo.append("Test Environment Information\n");
        envInfo.append("=" + "=".repeat(30) + "\n\n");
        
        // Framework information
        envInfo.append("Framework: Cucumber + Selenium + TestNG\n");
        envInfo.append("Browser: ").append(prop.getProperty("browser", "chrome")).append("\n");
        envInfo.append("Base URL: ").append(prop.getProperty("url", "N/A")).append("\n");
        
        // System information
        envInfo.append("\nSystem Information:\n");
        envInfo.append("OS: ").append(System.getProperty("os.name")).append("\n");
        envInfo.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
        envInfo.append("User: ").append(System.getProperty("user.name")).append("\n");
        
        // Test execution information
        envInfo.append("\nExecution Information:\n");
        envInfo.append("Execution Time: ").append(LocalDateTime.now().format(TIMESTAMP_FORMAT)).append("\n");
        envInfo.append("Working Directory: ").append(System.getProperty("user.dir")).append("\n");
        
        return envInfo.toString();
    }
    
    /**
     * Get emoji for status
     */
    private static String getStatusEmoji(String status) {
        switch (status.toUpperCase()) {
            case "PASSED":
                return "✅";
            case "FAILED":
                return "❌";
            case "SKIPPED":
                return "⏭️";
            case "BROKEN":
                return "💥";
            default:
                return "ℹ️";
        }
    }
    
    /**
     * Log test completion with summary
     * @param testName Test name
     * @param status Test status
     * @param duration Test duration in milliseconds
     */
    @Step("Test Completed: {testName}")
    public static void logTestCompletion(String testName, String status, long duration) {
        String summary = "Test: " + testName + "\n";
        summary += "Status: " + status + "\n";
        summary += "Duration: " + duration + "ms\n";
        summary += "Completed at: " + LocalDateTime.now().format(TIMESTAMP_FORMAT);
        
        attachText("Test Summary", summary);
        logStepWithStatus("Test execution completed", status);
    }
    
    /**
     * Test method to verify Allure attachments are working
     * Call this method to verify the setup is correct
     */
    public static void testAllureAttachments() {
        System.out.println("=== Testing Allure Attachments ===");
        
        // Test text attachment
        attachText("Test Attachment", "This is a test attachment to verify Allure is working");
        
        // Test JSON attachment
        attachJSON("Test JSON", "{\"test\": \"value\", \"timestamp\": \"" + 
            LocalDateTime.now().format(TIMESTAMP_FORMAT) + "\"}");
        
        // Test HTML attachment
        attachHTML("Test HTML", "<h1>Test HTML Attachment</h1><p>Timestamp: " + 
            LocalDateTime.now().format(TIMESTAMP_FORMAT) + "</p>");
        
        // Test screenshot if driver is available
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                takeScreenshot("Test Screenshot");
                System.out.println("✅ Screenshot test completed");
            } else {
                System.out.println("⚠️ No driver available for screenshot test");
            }
        } catch (Exception e) {
            System.out.println("❌ Screenshot test failed: " + e.getMessage());
        }
        
        System.out.println("=== Allure Attachments Test Completed ===");
    }
    
    /**
     * Save screenshot as file for consolidated report with chronological naming
     * @param stepName Name of the step
     * @param screenshot Screenshot byte array
     * @return Path to saved screenshot file
     */
    private static String saveScreenshotFile(String stepName, byte[] screenshot) {
        try {
            // Use ScreenshotSequenceManager for consistent file naming
            String fileName = ScreenshotSequenceManager.generateScreenshotFileName(
                stepName, ScreenshotSequenceManager.ScreenshotType.UI_ACTION);
            String filePath = "screenshots/" + fileName;
            
            java.io.File screenshotDir = new java.io.File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(filePath)) {
                fos.write(screenshot);
            }
            
            return filePath;
        } catch (Exception e) {
            System.out.println("Failed to save screenshot file: " + e.getMessage());
            return null;
        }
    }
}
