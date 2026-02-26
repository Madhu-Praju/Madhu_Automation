package com.qea.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages screenshot sequencing and naming to ensure chronological ordering in Allure reports
 * Provides sequential numbering and timestamp-based naming for proper ascending order display
 * 
 * @author Facctum Test Automation Team
 */
public class ScreenshotSequenceManager {
    
    // Thread-safe sequence counter for screenshot ordering
    private static final AtomicInteger screenshotSequence = new AtomicInteger(0);
    
    // Thread-safe sequence counter for test scenarios
    private static final AtomicInteger testSequence = new AtomicInteger(0);
    
    // Timestamp formats for different use cases
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    
    // Current test name for grouping screenshots
    private static String currentTestName = "UnknownTest";
    
    // Current tag name for file naming
    private static String currentTagName = "UnknownTag";
    
    /**
     * Reset screenshot sequence for new test scenario
     * Call this at the beginning of each test scenario
     */
    public static void resetSequenceForNewTest() {
        screenshotSequence.set(0);
        testSequence.incrementAndGet();
    }
    
    /**
     * Reset all sequences (call at test suite start)
     */
    public static void resetAllSequences() {
        screenshotSequence.set(0);
        testSequence.set(0);
    }
    
    /**
     * Set current test name for screenshot grouping
     * @param testName Name of current test
     */
    public static void setCurrentTestName(String testName) {
        currentTestName = testName != null ? testName.replaceAll("[^a-zA-Z0-9_-]", "_") : "UnknownTest";
    }
    
    /**
     * Set current tag name for file naming
     * @param tagName Name of current tag (e.g., @loginTest, @globalSearch)
     */
    public static void setCurrentTagName(String tagName) {
        // Clean tag name for file system compatibility
        if (tagName != null && !tagName.trim().isEmpty()) {
            // Remove @ symbol if present at the beginning
            String cleanTag = tagName.trim();
            if (cleanTag.startsWith("@")) {
                cleanTag = cleanTag.substring(1);
            }
            
            // Keep only alphanumeric characters, underscores, and hyphens
            cleanTag = cleanTag.replaceAll("[^a-zA-Z0-9_-]", "");
            
            // Set the cleaned tag name or default if empty after cleaning
            currentTagName = cleanTag.isEmpty() ? "UnknownTag" : cleanTag;
        } else {
            currentTagName = "UnknownTag";
        }
        
        System.out.println("🏷️ Tag name set for screenshots: " + currentTagName);
    }
    
    /**
     * Get next screenshot sequence number
     * @return Next sequence number
     */
    public static int getNextSequenceNumber() {
        return screenshotSequence.incrementAndGet();
    }
    
    /**
     * Generate chronologically ordered screenshot name for UI actions
     * Format: [SequenceNo]_[Timestamp]_UI_Action_[ActionDescription]
     * 
     * @param actionDescription Description of the UI action
     * @return Properly formatted screenshot name for chronological ordering
     */
    public static String generateActionScreenshotName(String actionDescription) {
        int sequenceNo = getNextSequenceNumber();
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String displayTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        
        // Clean action description
        String cleanAction = actionDescription.replaceAll("[^a-zA-Z0-9_-]", "_");
        
        // Limit action description length
        if (cleanAction.length() > 50) {
            cleanAction = cleanAction.substring(0, 47) + "...";
        }
        
        // Format: [SequenceNo]_[Timestamp]_UI_Action_[ActionDescription]
        String screenshotName = String.format("%04d_%s_UI_Action_%s", 
            sequenceNo, timestamp, cleanAction);
        
        // For Allure display, add readable timestamp
        String allureName = String.format("Step %04d [%s] - %s", 
            sequenceNo, displayTime, actionDescription);
        
        // Store both names for different purposes
        return allureName; // Return the human-readable name for Allure
    }
    
    /**
     * Generate chronologically ordered screenshot name for errors
     * Format: [SequenceNo]_[Timestamp]_ERROR_[ErrorDescription]
     * 
     * @param errorDescription Description of the error
     * @return Properly formatted error screenshot name
     */
    public static String generateErrorScreenshotName(String errorDescription) {
        int sequenceNo = getNextSequenceNumber();
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String displayTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        
        // Clean error description
        String cleanError = errorDescription.replaceAll("[^a-zA-Z0-9_-]", "_");
        
        // Limit error description length
        if (cleanError.length() > 50) {
            cleanError = cleanError.substring(0, 47) + "...";
        }
        
        // For Allure display, add readable timestamp and ERROR marker
        String allureName = String.format("❌ ERROR Step %04d [%s] - %s", 
            sequenceNo, displayTime, errorDescription);
        
        return allureName;
    }
    
    /**
     * Generate chronologically ordered screenshot name for manual captures
     * Format: [SequenceNo]_[Timestamp]_MANUAL_[Description]
     * 
     * @param description Description of manual screenshot
     * @return Properly formatted manual screenshot name
     */
    public static String generateManualScreenshotName(String description) {
        int sequenceNo = getNextSequenceNumber();
        String displayTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        
        // For Allure display
        String allureName = String.format("📸 Manual Step %04d [%s] - %s", 
            sequenceNo, displayTime, description);
        
        return allureName;
    }
    
    /**
     * Generate file name for screenshot storage (used by AllureReportManager)
     * This creates a file-system friendly name with sequence number for ordering
     * 
     * @param description Base description
     * @param type Type of screenshot (UI_Action, ERROR, MANUAL)
     * @return File-system friendly filename
     */
    public static String generateScreenshotFileName(String description, ScreenshotType type) {
        int sequenceNo = screenshotSequence.get(); // Don't increment, just get current
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        
        // Use current tag name instead of TestXXXX format
        String tagPrefix = currentTagName != null ? currentTagName : "UnknownTag";
        
        // Clean description for filename
        String cleanDesc = description.replaceAll("[^a-zA-Z0-9_-]", "_");
        if (cleanDesc.length() > 40) {
            cleanDesc = cleanDesc.substring(0, 37) + "...";
        }
        
        // Format: TagName_StepXXXX_Timestamp_Type_Description.png
        return String.format("%s_Step%04d_%s_%s_%s.png", 
            tagPrefix, sequenceNo, timestamp, type.name(), cleanDesc);
    }
    
    /**
     * Get current test execution summary for reporting
     * @return Summary string with test and screenshot counts
     */
    public static String getExecutionSummary() {
        return String.format("%s - Screenshots captured: %d", 
            currentTagName, screenshotSequence.get());
    }
    
    /**
     * Get current sequence number without incrementing
     * @return Current sequence number
     */
    public static int getCurrentSequenceNumber() {
        return screenshotSequence.get();
    }
    
    /**
     * Get current tag name being used for file naming
     * @return Current tag name
     */
    public static String getCurrentTagName() {
        return currentTagName;
    }
    
    /**
     * Enum for screenshot types
     */
    public enum ScreenshotType {
        UI_ACTION,
        ERROR,
        MANUAL,
        VALIDATION,
        NAVIGATION
    }
    
    /**
     * Generate screenshot name for specific test step
     * @param stepName Name of the test step
     * @param actionDescription Description of action within the step
     * @return Formatted screenshot name
     */
    public static String generateStepScreenshotName(String stepName, String actionDescription) {
        int sequenceNo = getNextSequenceNumber();
        String displayTime = LocalDateTime.now().format(DISPLAY_FORMAT);
        
        String allureName = String.format("Step %04d [%s] %s - %s", 
            sequenceNo, displayTime, stepName, actionDescription);
        
        return allureName;
    }
    
    /**
     * Create debug information about screenshot sequencing
     * @return Debug string with current state
     */
    public static String getDebugInfo() {
        return String.format("ScreenshotSequenceManager State: Tag='%s', Test=%d, Screenshot=%d, CurrentTest='%s'", 
            currentTagName, testSequence.get(), screenshotSequence.get(), currentTestName);
    }
    
    /**
     * Log sequence manager state (for debugging)
     */
    public static void logCurrentState() {
        System.out.println("🔢 " + getDebugInfo());
    }
}