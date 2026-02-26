package com.pages;

/**
 * Data class to hold tab validation results
 * Used by taskCommonPage for tab validation operations
 */
public class TabValidationResult {
    
    private String tabName;
    private boolean isPresent;
    private boolean isDisplayed;
    private boolean isActive;
    private boolean isEnabled;
    private String status;
    private String error;
    
    // Default constructor
    public TabValidationResult() {
        this.isPresent = false;
        this.isDisplayed = false;
        this.isActive = false;
        this.isEnabled = false;
        this.status = "UNKNOWN";
    }
    
    // Parameterized constructor
    public TabValidationResult(String tabName, boolean isPresent, boolean isDisplayed, 
                             boolean isActive, String status, boolean isEnabled) {
        this.tabName = tabName;
        this.isPresent = isPresent;
        this.isDisplayed = isDisplayed;
        this.isActive = isActive;
        this.status = status;
        this.isEnabled = isEnabled;
    }
    
    // Getters
    public String getTabName() {
        return tabName;
    }
    
    public boolean isPresent() {
        return isPresent;
    }
    
    public boolean isDisplayed() {
        return isDisplayed;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public boolean isEnabled() {
        return isEnabled;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getError() {
        return error;
    }
    
    public boolean hasError() {
        return error != null && !error.trim().isEmpty();
    }
    
    // Setters
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
    
    public void setPresent(boolean present) {
        this.isPresent = present;
    }
    
    public void setDisplayed(boolean displayed) {
        this.isDisplayed = displayed;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    // Utility methods
    public boolean isValid() {
        return !hasError() && isPresent && isDisplayed;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TabValidationResult{");
        sb.append("tabName='").append(tabName).append('\'');
        sb.append(", isPresent=").append(isPresent);
        sb.append(", isDisplayed=").append(isDisplayed);
        sb.append(", isActive=").append(isActive);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", status='").append(status).append('\'');
        if (hasError()) {
            sb.append(", error='").append(error).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }
}
