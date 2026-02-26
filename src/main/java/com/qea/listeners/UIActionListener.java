package com.qea.listeners;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qea.utils.AllureReportManager;
import com.qea.utils.ElementLib;
import com.qea.utils.ScreenshotSequenceManager;

/**
 * UI Action Listener that automatically captures screenshots after UI interactions
 * Uses Proxy pattern to intercept ElementLib method calls without modifying core implementation
 * 
 * This listener captures screenshots for all UI interactions including:
 * - Click operations
 * - Text input operations
 * - Select operations
 * - Navigation operations
 * - Scroll operations
 * 
 * @author Facctum Test Automation Team
 */
public class UIActionListener implements InvocationHandler {
    
    private final ElementLib elementLib;
    private final DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    
    // Define UI action methods that should trigger screenshot capture
    private static final List<String> UI_ACTION_METHODS = Arrays.asList(
        // Click operations
        "click", "doubleClick", "rightClick",
        
        // Text input operations
        "sendKeys", "clearAndSendKeys", "clear",
        
        // Selection operations
        "selectByText", "selectByValue", "selectByIndex", "selectRadioButton",
        
        // Navigation operations
        "navigateTo", "refresh", "goBack", "goForward",
        
        // Window/Tab operations
        "switchToWindow", "switchToNewWindow", "closeCurrentWindow",
        
        // Frame operations
        "switchToFrame", "switchToDefaultContent", "switchToParentFrame",
        
        // Alert operations
        "acceptAlert", "dismissAlert", "sendKeysToAlert", "isAlertPresent",
        
        // Scroll operations
        "scrollToElement", "scrollToTop", "scrollToBottom",
        
        // Mouse actions
        "hoverOverElement", "dragAndDrop",
        
        // Keyboard actions
        "pressKey", "sendKeysCombination",
        
        // File operations
        "uploadFile",
        
        // Wait operations
        "waitForElementVisible", "waitForElementClickable", "waitForElementInvisible",
        "waitForElementPresent", "waitForTextPresent", "waitForPageToLoad",
        
        // State checks
        "isDisplayed", "isEnabled", "isSelected", "isElementPresent",
        
        // Data retrieval
        "getText", "getAttribute", "getCssValue", "getTagName", "getValue",
        
        // Element finding
        "findElement", "findElements"
    );
    
    // Define methods that should NOT trigger screenshots (minimal list for assertions only)
    private static final List<String> NON_ACTION_METHODS = Arrays.asList(
        "implicitWait", "explicitWait", "hardWait",
        "pageLoadTimeout", "scriptTimeout", "getAlertText",
        "getCurrentUrl", "getTitle", "getCurrentWindowHandle", "getAllWindowHandles",
        "assertElementVisible", "assertElementNotVisible", "assertElementEnabled",
        "assertElementDisabled", "assertTextPresent", "assertTextNotPresent"
    );
    
    /**
     * Private constructor - use createProxy method to create instances
     */
    private UIActionListener(ElementLib elementLib) {
        this.elementLib = elementLib;
    }
    
    /**
     * Create a proxy instance of ElementLib with UI action listening capabilities
     * 
     * @param elementLib The original ElementLib implementation
     * @return Proxied ElementLib that captures screenshots on UI actions
     */
    public static ElementLib createProxy(ElementLib elementLib) {
        return (ElementLib) Proxy.newProxyInstance(
            elementLib.getClass().getClassLoader(),
            new Class<?>[]{ElementLib.class},
            new UIActionListener(elementLib)
        );
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String timestamp = LocalDateTime.now().format(timestampFormat);
        
        try {
            // Log the action being performed
            String actionDescription = buildActionDescription(methodName, args);
            System.out.println("[" + timestamp + "] 🎬 UI Action: " + actionDescription);
            
            // Execute the original method
            Object result = method.invoke(elementLib, args);
            
            // Capture screenshot after UI actions (but not for getter/checker methods)
            if (shouldCaptureScreenshot(methodName)) {
                captureActionScreenshot(actionDescription);
                
                // Small delay to ensure action is visually complete
                try {
                    Thread.sleep(500); // 500ms delay for visual stability
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            return result;
            
        } catch (Exception e) {
            // Capture screenshot on error
            String errorDescription = "ERROR in " + buildActionDescription(methodName, args);
            System.out.println("[" + timestamp + "] ❌ " + errorDescription + ": " + e.getMessage());
            captureErrorScreenshot(errorDescription);
            throw e;
        }
    }
    
    /**
     * Determine if a screenshot should be captured for this method
     */
    private boolean shouldCaptureScreenshot(String methodName) {
        // Don't capture for non-action methods
        if (NON_ACTION_METHODS.contains(methodName)) {
            return false;
        }
        
        // Always capture for known UI action methods
        if (UI_ACTION_METHODS.contains(methodName)) {
            return true;
        }
        
        // For other methods, capture if they don't start with common getter prefixes
        return !methodName.startsWith("get") && 
               !methodName.startsWith("is") && 
               !methodName.startsWith("wait") &&
               !methodName.startsWith("find") &&
               !methodName.startsWith("assert");
    }
    
    /**
     * Build a human-readable description of the action being performed
     */
    private String buildActionDescription(String methodName, Object[] args) {
        StringBuilder description = new StringBuilder();
        description.append(methodName);
        
        if (args != null && args.length > 0) {
            description.append("(");
            for (int i = 0; i < args.length; i++) {
                if (i > 0) description.append(", ");
                
                Object arg = args[i];
                if (arg instanceof By) {
                    description.append("By: ").append(arg.toString());
                } else if (arg instanceof WebElement) {
                    try {
                        String tagName = ((WebElement) arg).getTagName();
                        description.append("Element: ").append(tagName);
                    } catch (Exception e) {
                        description.append("Element: WebElement");
                    }
                } else if (arg instanceof String) {
                    String text = (String) arg;
                    if (text.length() > 50) {
                        description.append("'").append(text.substring(0, 47)).append("...'");
                    } else {
                        description.append("'").append(text).append("'");
                    }
                } else {
                    description.append(arg != null ? arg.toString() : "null");
                }
            }
            description.append(")");
        }
        
        return description.toString();
    }
    
    /**
     * Capture screenshot after a successful UI action with chronological ordering
     */
    private void captureActionScreenshot(String actionDescription) {
        try {
            // Generate chronologically ordered screenshot name
            String orderedScreenshotName = ScreenshotSequenceManager.generateActionScreenshotName(actionDescription);
            
            AllureReportManager.takeScreenshot(orderedScreenshotName);
            System.out.println("📸 Screenshot captured for: " + actionDescription);
            
        } catch (Exception e) {
            System.out.println("⚠️ Failed to capture screenshot for action: " + actionDescription + " - " + e.getMessage());
        }
    }
    
    /**
     * Capture screenshot when an error occurs during UI action with chronological ordering
     */
    private void captureErrorScreenshot(String errorDescription) {
        try {
            // Generate chronologically ordered error screenshot name
            String orderedErrorName = ScreenshotSequenceManager.generateErrorScreenshotName(errorDescription);
            
            AllureReportManager.takeFailureScreenshot(orderedErrorName);
            System.out.println("📸 Error screenshot captured for: " + errorDescription);
            
        } catch (Exception e) {
            System.out.println("⚠️ Failed to capture error screenshot: " + errorDescription + " - " + e.getMessage());
        }
    }
}