package com.qea.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Enhanced Screenshot Manager for better naming and report integration
 * Creates user-friendly screenshot names from existing screenshots and integrates with consolidated reports
 * 
 * @author Facctum Test Automation Team
 */
public class ScreenshotManager {
    
    private static final AtomicInteger screenshotCounter = new AtomicInteger(1);
    private static String currentTestName = "UnknownTest";
    
    /**
     * Set current test context for better screenshot naming
     * @param testName Current test name
     */
    public static void setCurrentTest(String testName) {
        currentTestName = cleanName(testName);
        screenshotCounter.set(1); // Reset counter for each test
    }
    
    /**
     * Generate user-friendly screenshot name from existing screenshot file
     * Converts names like "click_WebElement_906987389.png" to "LoginTest_Step01_CLICK_SubmitButton.png"
     * 
     * @param originalFileName Original screenshot filename
     * @param testName Test name for context
     * @param stepNumber Step number in the test
     * @return User-friendly screenshot name
     */
    public static String generateFriendlyScreenshotName(String originalFileName, String testName, int stepNumber) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            return String.format("%s_Step%02d_UNKNOWN_Action.png", cleanName(testName), stepNumber);
        }
        
        // Extract action type from original filename
        String actionType = extractActionType(originalFileName);
        String elementInfo = extractElementInfo(originalFileName);
        
        StringBuilder friendlyName = new StringBuilder();
        friendlyName.append(cleanName(testName != null ? testName : currentTestName));
        friendlyName.append("_Step").append(String.format("%02d", stepNumber));
        friendlyName.append("_").append(actionType.toUpperCase());
        
        if (!elementInfo.isEmpty()) {
            friendlyName.append("_").append(elementInfo);
        }
        
        friendlyName.append(".png");
        
        return friendlyName.toString();
    }
    
    /**
     * Extract action type from original screenshot filename
     * @param fileName Original filename
     * @return Action type (CLICK, SEND_KEYS, etc.)
     */
    private static String extractActionType(String fileName) {
        if (fileName.toLowerCase().contains("click")) {
            if (fileName.toLowerCase().contains("double")) {
                return "DOUBLE_CLICK";
            } else if (fileName.toLowerCase().contains("right")) {
                return "RIGHT_CLICK";
            } else {
                return "CLICK";
            }
        } else if (fileName.toLowerCase().contains("sendkeys")) {
            return "SEND_KEYS";
        } else if (fileName.toLowerCase().contains("clear")) {
            if (fileName.toLowerCase().contains("sendkeys")) {
                return "CLEAR_AND_SEND_KEYS";
            } else {
                return "CLEAR";
            }
        } else if (fileName.toLowerCase().contains("select")) {
            return "SELECT";
        } else if (fileName.toLowerCase().contains("hover")) {
            return "HOVER";
        } else if (fileName.toLowerCase().contains("scroll")) {
            return "SCROLL";
        } else if (fileName.toLowerCase().contains("navigate")) {
            return "NAVIGATE";
        } else if (fileName.toLowerCase().contains("wait")) {
            return "WAIT";
        } else if (fileName.toLowerCase().contains("verify")) {
            return "VERIFY";
        } else {
            return "ACTION";
        }
    }
    
    /**
     * Extract element information from filename or try to make it meaningful
     * @param fileName Original filename
     * @return Meaningful element identifier
     */
    private static String extractElementInfo(String fileName) {
        // Try to extract meaningful element info from locator patterns
        if (fileName.contains("By.id")) {
            Pattern idPattern = Pattern.compile("By\\.id\\s*:\\s*([^\\]\\)]+)");
            Matcher matcher = idPattern.matcher(fileName);
            if (matcher.find()) {
                return cleanName(matcher.group(1));
            }
        }
        
        if (fileName.contains("By.xpath")) {
            // Try to extract meaningful parts from xpath
            if (fileName.contains("button")) {
                return "Button";
            } else if (fileName.contains("input")) {
                return "Input";
            } else if (fileName.contains("link")) {
                return "Link";
            } else if (fileName.contains("div")) {
                return "Element";
            }
        }
        
        if (fileName.contains("By.className")) {
            Pattern classPattern = Pattern.compile("By\\.className\\s*:\\s*([^\\]\\)]+)");
            Matcher matcher = classPattern.matcher(fileName);
            if (matcher.find()) {
                return cleanName(matcher.group(1));
            }
        }
        
        if (fileName.contains("By.name")) {
            Pattern namePattern = Pattern.compile("By\\.name\\s*:\\s*([^\\]\\)]+)");
            Matcher matcher = namePattern.matcher(fileName);
            if (matcher.find()) {
                return cleanName(matcher.group(1));
            }
        }
        
        // For WebElement hashcode patterns, try to make generic names
        if (fileName.contains("WebElement")) {
            String actionType = extractActionType(fileName);
            switch (actionType) {
                case "CLICK":
                case "DOUBLE_CLICK":
                case "RIGHT_CLICK":
                    return "Button";
                case "SEND_KEYS":
                case "CLEAR":
                    return "Input";
                case "SELECT":
                    return "Dropdown";
                default:
                    return "Element";
            }
        }
        
        return "Element";
    }
    
    /**
     * Clean name by removing special characters and making it filesystem-safe
     * @param name Original name
     * @return Cleaned name
     */
    private static String cleanName(String name) {
        if (name == null || name.isEmpty()) {
            return "Unknown";
        }
        
        // Remove special characters and replace with underscores
        String cleaned = name.replaceAll("[^a-zA-Z0-9\\s]", "")
                           .replaceAll("\\s+", "_")
                           .replaceAll("_+", "_")
                           .trim();
        
        // Remove leading/trailing underscores
        cleaned = cleaned.replaceAll("^_+|_+$", "");
        
        // Limit length
        if (cleaned.length() > 50) {
            cleaned = cleaned.substring(0, 50);
        }
        
        return cleaned.isEmpty() ? "Unknown" : cleaned;
    }
    
    /**
     * Generate user-friendly name for verification screenshots
     * @param verificationDescription What is being verified
     * @param passed Whether the verification passed
     * @param testName Test name for context
     * @param stepNumber Step number
     * @return User-friendly verification screenshot name
     */
    public static String generateVerificationScreenshotName(String verificationDescription, boolean passed, String testName, int stepNumber) {
        StringBuilder name = new StringBuilder();
        
        name.append(cleanName(testName != null ? testName : currentTestName));
        name.append("_Step").append(String.format("%02d", stepNumber));
        name.append("_VERIFY_");
        name.append(cleanName(verificationDescription));
        name.append(passed ? "_PASS" : "_FAIL");
        name.append(".png");
        
        return name.toString();
    }
    
    /**
     * Rename existing screenshot file to user-friendly name
     * @param originalPath Original screenshot file path
     * @param newName New user-friendly name
     * @return New file path or original if rename failed
     */
    public static String renameScreenshot(String originalPath, String newName) {
        try {
            File originalFile = new File(originalPath);
            if (!originalFile.exists()) {
                return originalPath;
            }
            
            Path originalPathObj = Paths.get(originalPath);
            Path newPath = originalPathObj.getParent().resolve(newName);
            File newFile = newPath.toFile();
            
            // If new file already exists, append counter
            int counter = 1;
            while (newFile.exists()) {
                String nameWithoutExt = newName.substring(0, newName.lastIndexOf('.'));
                String extension = newName.substring(newName.lastIndexOf('.'));
                String numberedName = nameWithoutExt + "_" + counter + extension;
                newPath = originalPathObj.getParent().resolve(numberedName);
                newFile = newPath.toFile();
                counter++;
            }
            
            if (originalFile.renameTo(newFile)) {
                return newFile.getAbsolutePath();
            }
        } catch (Exception e) {
            System.err.println("Failed to rename screenshot: " + e.getMessage());
        }
        
        return originalPath; // Return original path if rename failed
    }
    
    /**
     * Set test context from Cucumber scenario
     * @param scenarioName Cucumber scenario name
     * @param stepText Current step text
     */
    public static void setTestContext(String scenarioName, String stepText) {
        setCurrentTest(scenarioName);
        // Step text handling can be added later if needed
    }
    
    /**
     * Reset screenshot counter for new test
     */
    public static void resetCounter() {
        screenshotCounter.set(1);
    }
    
    /**
     * Get next screenshot counter value
     * @return Next counter value
     */
    public static int getNextCounter() {
        return screenshotCounter.getAndIncrement();
    }
}
