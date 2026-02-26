package com.pages;

import com.qea.factory.DriverFactory;
import com.qea.listeners.UIActionListener;
import com.qea.utils.configReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qea.utils.ElementLib;
import com.qea.utils.ElementLibImpl;



public class BasePage {
    
    WebDriver driver;
    protected ElementLib elementLib;
    protected configReader confReader;
    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        
        // Create ElementLibImpl instance and wrap it with UIActionListener proxy
        ElementLibImpl elementLibImpl = new ElementLibImpl(driver);
        this.elementLib = UIActionListener.createProxy(elementLibImpl);
        
        this.confReader = new configReader();
        PageFactory.initElements(driver, this);
    }
    protected void waitForPageToLoad() {
        if (elementLib != null) {
            elementLib.waitForPageToLoad();
        } else {
            throw new IllegalStateException("ElementLib is not initialized");
        }
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected void refreshPage() {
        driver.navigate().refresh();
        // Reapply zoom after page refresh
        applyZoomSetting();
    }

    protected void navigateBack() {
        driver.navigate().back();
        // Reapply zoom after navigation
        applyZoomSetting();
    }

    protected void navigateForward() {
        driver.navigate().forward();
        // Reapply zoom after navigation
        applyZoomSetting();
    }

    /**
     * Apply zoom setting to the current page
     * This method ensures zoom is maintained after page navigation
     */
    protected void applyZoomSetting() {
        DriverFactory.applyZoomSetting();
    }

    /**
     * Navigate to a URL and apply zoom setting
     * @param url The URL to navigate to
     */
    protected void navigateToUrl(String url) {
        driver.navigate().to(url);
        applyZoomSetting();
    }

    /**
     * Common method to perform coordinate-based click as a fallback when standard clicking fails
     * This method handles cases where elements might be overlapped or have click interception issues
     * 
     * @param element The WebElement to click
     * @param elementName Name of the element for logging purposes
     */
    protected void performCoordinateBasedClick(WebElement element, String elementName) {
        try {
            // Get element location and size
            int x = element.getLocation().getX() + (element.getSize().getWidth() / 2);
            int y = element.getLocation().getY() + (element.getSize().getHeight() / 2);
            
            System.out.println("Attempting coordinate-based click for " + elementName + " at coordinates: (" + x + ", " + y + ")");
            
            // Use Actions class for coordinate-based click
            Actions actions = new Actions(driver);
            actions.moveByOffset(x, y).click().build().perform();
            
            System.out.println("Successfully clicked " + elementName + " using coordinate-based approach");
            
        } catch (Exception coordinateEx) {
            System.out.println("Coordinate-based click failed for " + elementName + ": " + coordinateEx.getMessage());
            
            // Final fallback: Try JavaScript click
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("Successfully clicked " + elementName + " using JavaScript fallback in coordinate method");
                
            } catch (Exception finalEx) {
                System.out.println("All coordinate-based click methods failed for " + elementName + ": " + finalEx.getMessage());
                System.out.println("Test will continue with potential impact on subsequent steps");
            }
        }
    }


}
