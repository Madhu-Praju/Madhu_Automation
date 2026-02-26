package com.qea.utils;

import java.util.Properties;

/**
 * Utility class for managing common login configurations
 * Provides default values and validation for login scenarios
 */
public class LoginConfigUtils {
    
    private static final String DEFAULT_TENANT = "facctum";
    private static final String DEFAULT_USERNAME_KEY = "facctListusername";
    private static final String DEFAULT_PASSWORD_KEY = "facctListPass";
    private static final String DEFAULT_URL_KEY = "facctList";
    
    private Properties prop;
    
    public LoginConfigUtils(Properties properties) {
        this.prop = properties;
    }
    
    /**
     * Get default tenant name
     */
    public String getDefaultTenant() {
        return prop.getProperty(DEFAULT_TENANT, DEFAULT_TENANT);
    }
    
    /**
     * Get default username from properties
     */
    public String getDefaultUsername() {
        return prop.getProperty(DEFAULT_USERNAME_KEY);
    }
    
    /**
     * Get default password from properties
     */
    public String getDefaultPassword() {
        return prop.getProperty(DEFAULT_PASSWORD_KEY);
    }
    
    /**
     * Get default application URL
     */
    public String getDefaultUrl() {
        return prop.getProperty(DEFAULT_URL_KEY);
    }
    
    /**
     * Clean parameter value by removing quotes if present
     */
    public String cleanParameter(String parameter) {
        if (parameter == null) return null;
        return parameter.replace("'", "").replace("\"", "");
    }
    
    /**
     * Resolve parameter value - if it starts with quote, treat as property key
     */
    public String resolveParameterValue(String parameter) {
        if (parameter == null) return null;
        
        String cleaned = cleanParameter(parameter);
        
        // If original parameter had quotes, try to get from properties
        if (parameter.startsWith("'") || parameter.startsWith("\"")) {
            String propValue = prop.getProperty(cleaned);
            return propValue != null ? propValue : cleaned;
        }
        
        return cleaned;
    }
    
    /**
     * Validate if all required login properties are available
     */
    public boolean validateLoginProperties() {
        return getDefaultUsername() != null && 
               getDefaultPassword() != null && 
               getDefaultUrl() != null;
    }
    
    /**
     * Get formatted login credentials for logging (password masked)
     */
    public String getFormattedCredentials(String tenant, String username) {
        return String.format("Tenant: %s, Username: %s, Password: ****", 
                           tenant != null ? tenant : "default", 
                           username != null ? username : "default");
    }
}
