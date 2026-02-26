package com.qea.utils;

import com.qea.listeners.ConsoleLogCapturer;

/**
 * Test Suite Manager for handling suite-level operations
 * Bridges between main package utilities and test hooks
 * 
 * @author Facctum Test Automation Team
 */
public class TestSuiteManager {
    
    private static boolean suiteInitialized = false;
    
    /**
     * Initialize the test suite
     */
    public static void initializeSuite() {
        if (!suiteInitialized) {
            ConsoleLogCapturer.startCapturing();
            suiteInitialized = true;
        }
    }
    
    /**
     * Cleanup the test suite
     */
    public static void cleanupSuite() {
        if (suiteInitialized) {
            ConsoleLogCapturer.stopCapturing();
            suiteInitialized = false;
        }
    }
    
    /**
     * Check if suite is initialized
     */
    public static boolean isSuiteInitialized() {
        return suiteInitialized;
    }
}