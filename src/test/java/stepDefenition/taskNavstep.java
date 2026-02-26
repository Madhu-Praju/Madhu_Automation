package stepDefenition;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.qea.factory.DriverFactory;
import com.pages.dashboardpage;
import com.pages.homePage;
import com.pages.loginPage;
import com.qea.utils.configReader;
import com.qea.utils.AllureReportManager;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

/**
 * Enhanced Task Navigation Step Definitions with Allure Reporting
 * This class handles task navigation and validation with comprehensive Allure reporting
 */
@Epic("Facctum Application Testing")
@Feature("Task Navigation Functionality")
public class taskNavstep {
    WebDriver driver;
    Properties prop;
    WebDriverWait wait;
    private configReader confReader;
    private homePage homePage;
    private loginPage loginpage;
    private dashboardpage dashboardPage;
    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        if (driver == null) {
            AllureReportManager.logStepWithStatus("Driver initialization check failed", "FAILED");
            throw new RuntimeException("WebDriver is not initialized. Please check ApplicationHooks.");
        }
        homePage = new homePage(driver);
        loginpage = new loginPage(driver);
        dashboardPage = new dashboardpage(driver);
        
        AllureReportManager.logStep("Task Navigation step definition setup completed");
        System.out.println("Task Navigation step definition initialized successfully");
    }
    
    
    @Step("Validate dashboard card states")
    @Description("Validate that all dashboard cards are visible and in correct states")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Dashboard Card Validation")
    @When("User validate the card states")
    public void user_validate_the_card_states() {
        AllureReportManager.logStep("🎯 Starting card states validation");
        
        // Add explicit wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Wait for the page to be ready
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            
            // Add small buffer for dynamic content
            Thread.sleep(2000);
            
            // Check if cards are visible with retry mechanism
            boolean cardsVisible = false;
            int maxRetries = 3;
            
            for (int i = 0; i < maxRetries; i++) {
                cardsVisible = homePage.areCardsVisible();
                if (cardsVisible) {
                    break;
                }
                Thread.sleep(1000); // Wait 1 second before retry
            }
            
            if (!cardsVisible) {
                // Additional debugging information
                String currentUrl = driver.getCurrentUrl();
                String pageTitle = driver.getTitle();
                String pageSource = driver.getPageSource();
                
                AllureReportManager.attachText("Current URL", currentUrl);
                AllureReportManager.attachText("Page Title", pageTitle);
                AllureReportManager.attachText("Page Source Length", String.valueOf(pageSource.length()));
                AllureReportManager.logStepWithStatus("Card visibility validation", "FAILED");
                AllureReportManager.takeScreenshot("Cards Not Visible");
                
                System.out.println("Cards are not visible. Current URL: " + currentUrl);
                System.out.println("Page title: " + pageTitle);
                System.out.println("Page source length: " + pageSource.length());
                
                Assert.fail("Cards are not visible on dashboard after " + maxRetries + " retries. " +
                           "Current URL: " + currentUrl + ", Page title: " + pageTitle);
            }
            
            AllureReportManager.logStepWithStatus("Card states validation", "PASSED");
            AllureReportManager.takeScreenshot("Cards Visible on Dashboard");
            System.out.println("Cards validation successful - cards are visible on dashboard");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Card states validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Card Validation Interrupted");
            
            Assert.fail("Thread was interrupted while waiting for cards to be visible: " + e.getMessage());
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Card states validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Card Validation Exception");
            
            Assert.fail("Error while validating card states: " + e.getMessage());
        }
    }
    @Step("Validate '{cardName}' card is enabled")
    @Description("Verify that the specified card is enabled and clickable")
    @Severity(SeverityLevel.NORMAL)
    @Story("Card State Validation")
    @Then("{string} card should be enabled")
    public void card_should_be_enabled(String cardName) {
        AllureReportManager.logStep("✅ Validating card '" + cardName + "' is enabled");
        
        try {
            validateCardState(cardName, true);
            AllureReportManager.logStepWithStatus("Card enabled validation for " + cardName, "PASSED");
            AllureReportManager.takeScreenshot("Card Enabled - " + cardName);
            System.out.println("Card '" + cardName + "' is enabled and validated successfully.");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Card enabled validation for " + cardName, "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Card Enabled Validation Failed - " + cardName);
            throw e;
        }
    }

    @Step("Validate '{cardName}' card is disabled")
    @Description("Verify that the specified card is disabled and not clickable")
    @Severity(SeverityLevel.NORMAL)
    @Story("Card State Validation")
    @Then("{string} card should be disabled")
    public void card_should_be_disabled(String cardName) {
        AllureReportManager.logStep("❌ Validating card '" + cardName + "' is disabled");
        
        try {
            validateCardState(cardName, false);
            AllureReportManager.logStepWithStatus("Card disabled validation for " + cardName, "PASSED");
            AllureReportManager.takeScreenshot("Card Disabled - " + cardName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Card disabled validation for " + cardName, "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Card Disabled Validation Failed - " + cardName);
            throw e;
        }
    }
    
    /**
     * Common method to validate card enabled/disabled state with comprehensive debugging and Allure reporting
     * @param cardName - Name of the card to validate
     * @param shouldBeEnabled - true if card should be enabled, false if should be disabled
     */
    private void validateCardState(String cardName, boolean shouldBeEnabled) {
        String stateText = shouldBeEnabled ? "ENABLED" : "DISABLED";
        AllureReportManager.logStep("🔍 Validating " + stateText + " state for card: " + cardName);
        
        try {
            WebElement card = homePage.getCardElement(cardName);
            
            // Debug card element information
            System.out.println("Card element found: " + (card != null));
            
            if (card != null) {
                boolean isDisplayed = card.isDisplayed();
                boolean isEnabled = card.isEnabled();
                String cardText = card.getText();
                
                AllureReportManager.attachText("Card Name", cardName);
                AllureReportManager.attachText("Card Displayed", String.valueOf(isDisplayed));
                AllureReportManager.attachText("Card Enabled", String.valueOf(isEnabled));
                AllureReportManager.attachText("Card Text", cardText);
                
                // Get all relevant attributes
                String cardClass = card.getAttribute("class");
                String disabledAttr = card.getAttribute("disabled");
                String ariaDisabled = card.getAttribute("aria-disabled");
                
                // Check computed styles using JavaScript
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                Object pointerEvents = jsExecutor.executeScript("return window.getComputedStyle(arguments[0]).pointerEvents;", card);
                Object opacity = jsExecutor.executeScript("return window.getComputedStyle(arguments[0]).opacity;", card);
                
                AllureReportManager.attachText("Card Class", cardClass);
                AllureReportManager.attachText("Computed Pointer Events", String.valueOf(pointerEvents));
                AllureReportManager.attachText("Computed Opacity", String.valueOf(opacity));
                
                // Card should always be displayed
                Assert.assertTrue(card.isDisplayed(), cardName + " card is not displayed");
                
                if (shouldBeEnabled) {
                    // Validate ENABLED state
                    Assert.assertTrue(card.isEnabled(), cardName + " card is not enabled via WebElement.isEnabled()");
                    
                    // Check custom method
                    boolean isCardEnabled = homePage.isCardEnabled(cardName);
                    System.out.println("homePage.isCardEnabled() result: " + isCardEnabled);
                    Assert.assertTrue(isCardEnabled, cardName + " card should be enabled but homePage.isCardEnabled() returned false");
                    
                    // Verify card doesn't have disabled attributes
                    Assert.assertNull(disabledAttr, cardName + " card has disabled attribute but should be enabled");
                    Assert.assertNotEquals("true", ariaDisabled, cardName + " card has aria-disabled=true but should be enabled");
                    
                    // Check that CSS doesn't indicate disabled state
                    if (cardClass != null) {
                        String lowerClass = cardClass.toLowerCase();
                        Assert.assertFalse(lowerClass.contains("disabled"), cardName + " card has 'disabled' in class but should be enabled");
                    }
                    
                    System.out.println( cardName + " card successfully validated as ENABLED");
                    
                } else {
                    // Validate DISABLED state
                    boolean isActuallyDisabled = isCardActuallyDisabled(card, cardName);
                    Assert.assertTrue(isActuallyDisabled, cardName + " card should be disabled but appears to be enabled");
                }
                
                // Add scroll after validating customer screening card
                if ("Customer Screening".equalsIgnoreCase(cardName)) {
                    jsExecutor.executeScript("window.scrollBy(0, 300);");
                }
                
            } else {
                AllureReportManager.attachText("Validation Error", "Card element for '" + cardName + "' was not found");
                Assert.fail("Card element for '" + cardName + "' was not found");
            }
            
        } catch (Exception e) {
            AllureReportManager.attachText("Exception Details", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Enhanced method to check if a card is actually disabled
     * This method checks multiple indicators of disabled state
     */
    private boolean isCardActuallyDisabled(WebElement card, String cardName) {
        try {
            // Get various attributes that might indicate disabled state
            String disabledAttr = card.getAttribute("disabled");
            String ariaDisabled = card.getAttribute("aria-disabled");
            String cardClass = card.getAttribute("class");
            String style = card.getAttribute("style");
            
            // Check for traditional disabled attributes
            if (disabledAttr != null || "true".equals(ariaDisabled)) {
                return true;
            }
            
            // Check for disabled class variations
            if (cardClass != null) {
                String lowerClass = cardClass.toLowerCase();
                if (lowerClass.contains("disabled") || lowerClass.contains("inactive") || 
                    lowerClass.contains("unavailable") || lowerClass.contains("locked")) {
                    return true;
                }
            }
            
            // Check for CSS styles that indicate disabled state
            if (style != null) {
                String lowerStyle = style.toLowerCase();
                if (lowerStyle.contains("pointer-events: none") || 
                    lowerStyle.contains("cursor: not-allowed") ||
                    lowerStyle.contains("opacity: 0.5") ||
                    lowerStyle.contains("opacity: 0.3")) {
                    return true;
                }
            }
            
            // Check if the card is clickable using JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean isClickable = (Boolean) js.executeScript(
                "var element = arguments[0];" +
                "var computedStyle = window.getComputedStyle(element);" +
                "return computedStyle.pointerEvents !== 'none' && " +
                "       computedStyle.visibility !== 'hidden' && " +
                "       computedStyle.display !== 'none' && " +
                "       !element.hasAttribute('disabled') && " +
                "       element.getAttribute('aria-disabled') !== 'true';", 
                card
            );
            
            if (!isClickable) {
                return true;
            }
            
            // Try to check if clicking the card would trigger any action
            // If a card is truly disabled, clicking it should not cause navigation
            String currentUrl = driver.getCurrentUrl();
            
            try {
                // Try clicking the card
                card.click();
                Thread.sleep(1000); // Wait briefly for any potential navigation
                
                String newUrl = driver.getCurrentUrl();
                if (currentUrl.equals(newUrl)) {
                    return true;
                } else {
                    // Navigate back if we accidentally navigated
                    driver.navigate().back();
                    return false;
                }
            } catch (Exception e) {
                System.out.println(cardName + " is disabled - clicking caused exception: " + e.getMessage());
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error checking disabled state for " + cardName + ": " + e.getMessage());
            return false;
        }
    }

    @Step("Click on '{cardName}' card")
    @Description("User clicks on the specified card to navigate")
    @Severity(SeverityLevel.NORMAL)
    @Story("Card Navigation")
    @When("User click on {string} card")
    public void i_click_on_card(String cardName) {
        AllureReportManager.logStep("🖱️ Clicking on card: " + cardName);
        
        try {
            homePage.clickCard(cardName);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            AllureReportManager.logStepWithStatus("Card click for " + cardName, "PASSED");
            AllureReportManager.takeScreenshot("Card Clicked - " + cardName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Card click for " + cardName, "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Card Click Failed - " + cardName);
            throw e;
        }
    }

    @Step("Click on '{buttonName}' button")
    @Description("User clicks on the specified button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Button Navigation")
    @When("User click on {string} button")
    public void user_click_on_button(String buttonName) {
        AllureReportManager.logStep("🔘 Clicking on button: " + buttonName);
        
        try {
            homePage.clickButton(buttonName);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            AllureReportManager.logStepWithStatus("Button click for " + buttonName, "PASSED");
            AllureReportManager.takeScreenshot("Button Clicked - " + buttonName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Button click for " + buttonName, "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Button Click Failed - " + buttonName);
            throw e;
        }
    }

    @Step("Expand the left navigation panel")
    @Description("User expands the left side navigation panel")
    @Severity(SeverityLevel.NORMAL)
    @Story("Panel Navigation")
    @Then("user expands the left panel")
    public void user_expands_the_left_panel() {
        AllureReportManager.logStep("📂 Expanding left navigation panel");
        
        try {
            if (homePage == null) {
                AllureReportManager.logStepWithStatus("Left panel expansion", "FAILED");
                AllureReportManager.attachText("Error Details", "HomePage is not initialized");
                throw new RuntimeException("HomePage is not initialized. Please check the @Before hook.");
            }
            
            homePage.expandPanel();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            AllureReportManager.logStepWithStatus("Left panel expansion", "PASSED");
            AllureReportManager.takeScreenshot("Left Panel Expanded");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Left panel expansion", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Left Panel Expansion Failed");
            throw e;
        }
    }

    @Step("Validate different task navigation tasks count")
    @Description("Validate and display the count of different task navigation tasks")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("validate different task navigation tasks count")
    public void validate_different_task_navigation_tasks_count() {
        AllureReportManager.logStep("📊 Validating task navigation tasks count");
        
        try {
            if (dashboardPage == null) {
                AllureReportManager.logStepWithStatus("Task count validation", "FAILED");
                AllureReportManager.attachText("Error Details", "DashboardPage is not initialized");
                throw new RuntimeException("DashboardPage is not initialized. Please check the @Before hook.");
            }
            
            String totalTasks = dashboardPage.getTotalTasks();
            String totalTasksCount = dashboardPage.getTotalTasksCount();
            
            AllureReportManager.attachText("Total Tasks", totalTasks);
            AllureReportManager.attachText("Total Tasks Count", totalTasksCount);
            AllureReportManager.logStepWithStatus("Task count validation", "PASSED");
            AllureReportManager.takeScreenshot("Task Count Displayed");
            
            System.out.println(totalTasks + ": " + totalTasksCount);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Task count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Task Count Validation Failed");
            throw e;
        }
    }
    
}


