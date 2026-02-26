package com.qea.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration utility for Consolidated Report settings
 * 
 * @author Facctum Test Automation Team
 */
public class ConsolidatedReportConfig {
    
    private static final String CONFIG_FILE = "src/test/resources/config/config.properties";
    private static final String DEFAULT_FORMAT = "PDF";
    private static final String DEFAULT_REPORT_DIR = "consolidated-reports";
    
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    /**
     * Load configuration properties
     */
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            System.out.println("📋 Consolidated report configuration loaded");
        } catch (IOException e) {
            System.out.println("⚠️ Could not load consolidated report config, using defaults");
            properties = new Properties();
        }
    }
    
    /**
     * Get report format (PDF or WORD)
     * @return Report format
     */
    public static String getReportFormat() {
        return properties.getProperty("consolidated.report.format", DEFAULT_FORMAT).toUpperCase();
    }
    
    /**
     * Get report directory
     * @return Report directory path
     */
    public static String getReportDirectory() {
        return properties.getProperty("consolidated.report.directory", DEFAULT_REPORT_DIR);
    }
    
    /**
     * Check if screenshots should be included in report
     * @return true if screenshots should be included
     */
    public static boolean includeScreenshots() {
        return Boolean.parseBoolean(
            properties.getProperty("consolidated.report.include.screenshots", "true")
        );
    }
    
    /**
     * Check if environment info should be included in report
     * @return true if environment info should be included
     */
    public static boolean includeEnvironmentInfo() {
        return Boolean.parseBoolean(
            properties.getProperty("consolidated.report.include.environment", "true")
        );
    }
    
    /**
     * Check if step details should be included in report
     * @return true if step details should be included
     */
    public static boolean includeStepDetails() {
        return Boolean.parseBoolean(
            properties.getProperty("consolidated.report.include.steps", "true")
        );
    }
    
    /**
     * Check if consolidated report is enabled
     * @return true if consolidated report should be generated
     */
    public static boolean isEnabled() {
        return Boolean.parseBoolean(
            properties.getProperty("consolidated.report.enabled", "true")
        );
    }
    
    /**
     * Get current configuration as string for logging
     * @return Configuration summary
     */
    public static String getConfigSummary() {
        StringBuilder config = new StringBuilder();
        config.append("Consolidated Report Configuration:\n");
        config.append("- Enabled: ").append(isEnabled()).append("\n");
        config.append("- Format: ").append(getReportFormat()).append("\n");
        config.append("- Directory: ").append(getReportDirectory()).append("\n");
        config.append("- Include Screenshots: ").append(includeScreenshots()).append("\n");
        config.append("- Include Environment: ").append(includeEnvironmentInfo()).append("\n");
        config.append("- Include Steps: ").append(includeStepDetails()).append("\n");
        config.append("- Screenshots: No limit (all screenshots will be included)");
        
        return config.toString();
    }
}
