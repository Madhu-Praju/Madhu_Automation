package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class homePage extends BasePage {
    


    // Explicit constructor calling BasePage's constructor
    public homePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Page Elements
    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Subscriptions')]")
    private WebElement subscriptionsButton;

    @FindBy(xpath = "//span[contains(text(),'Roles')]")
    private WebElement rolesButton;

    @FindBy(xpath = "//span[contains(text(),'Groups')]")
    private WebElement groupsButton;

    @FindBy(xpath = "//span[contains(text(),'Users')]")
    private WebElement usersButton;

    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    private WebElement reportsButton;

    @FindBy(xpath = "//span[contains(text(),'API Management')]")
    private WebElement apiManagementButton;

    @FindBy(xpath = "//span[contains(text(),'Help')]")
    private WebElement helpButton;

    @FindBy(xpath = "//span[contains(text(),'Notifications')]")
    private WebElement notificationsButton;

    @FindBy(xpath = "//span[contains(text(),'System configuration')]")
    private WebElement systemConfigButton;

    @FindBy(xpath = "//div[contains(@class, 'navbar-content')]")
    private WebElement leftPanel;

    @FindBy(xpath = "//*[@data-testid='ExpandCircleDownOutlinedIcon']")
    private WebElement btnClose;

    // Alternative locator for the close button if the above doesn't work
    @FindBy(xpath = "//div[@class='expand-arrow']")
    private WebElement btnCloseAlternative;

    @FindBy(xpath = "//div[@class='expand-arrow']//div")
    private WebElement btnExpandpanel;

    // ______________________________Card Elements______________________________________//
    @FindBy(xpath = "//div[@class='product-card ']//div[text()='List']")
    private WebElement listManagementCard;
    
    @FindBy(xpath = "//div[contains(@class, 'product-card')]//div[text()='Customer']")
    private WebElement customerScreeningCard;
    
    @FindBy(xpath = "//div[contains(@class, 'product-card')]//div[text()='Transaction'][1]")
    private WebElement transactionScreeningCard;
    
    @FindBy(xpath = "//div[contains(@class,'product-card')]//div[text()='Monitoring']")
    private WebElement transactionMonitoringCard;
    
    @FindBy(className = "path-label")
    private WebElement dashboardTitle;
//-------------------Dashboard Elements-------------------//
    @FindBy(xpath = "//span[contains(text(),'Dashboard')]")
    private WebElement dashboardButton;

    @FindBy(xpath = "//span[contains(text(),'Tasks')]")
    private WebElement tasksButton;

    @FindBy(xpath = "//span[contains(text(),'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Watchlist')]")
    private WebElement watchlistButton;

    @FindBy(xpath = "//span[contains(text(),'Data Export')]")
    private WebElement dataExportButton;

    //    constructor
//    public homePage(WebDriver driver) {
//        this.driver = driver;
//        this.elementLib = new ElementlibImpl(driver);
//        PageFactory.initElements(driver, this);
//    }

    public void  waitForLeftPanelToLoad() {
        elementLib.waitForElementToBeVisible(leftPanel, Duration.ofSeconds(10));
        }

    public boolean isButtonPresent(String buttonName) {
        try {
            WebElement button = getButtonByName(buttonName);
            return button != null && elementLib.isElementDisplayed(button);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isButtonEnabled(String buttonName) {
        try {
            WebElement button = getButtonByName(buttonName);
            return button != null && elementLib.isEnabled(button);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickButton(String 
    buttonName) {
        WebElement button = getButtonByName(buttonName);
        if (button != null) {
            elementLib.click(button);
        } else {
            throw new RuntimeException("Button '" + buttonName + "' not found");
        }
    }
    private WebElement getButtonByName(String buttonName) {
        Map<String, WebElement> buttonMap = new HashMap<>();
        buttonMap.put("Home", homeButton);
        buttonMap.put("Subscriptions", subscriptionsButton);
        buttonMap.put("Roles", rolesButton);
        buttonMap.put("Groups", groupsButton);
        buttonMap.put("Users", usersButton);
        buttonMap.put("Reports", reportsButton);
        buttonMap.put("API Management", apiManagementButton);
        buttonMap.put("Help", helpButton);
        buttonMap.put("Notifications", notificationsButton);
        buttonMap.put("System configuration", systemConfigButton);
        buttonMap.put("Dashboard", dashboardButton);
        buttonMap.put("Tasks", tasksButton);
        buttonMap.put("Search", searchButton);
        buttonMap.put("Watchlist", watchlistButton);
        buttonMap.put("Data Export", dataExportButton);
        buttonMap.put("Reports", reportsButton);

        return buttonMap.get(buttonName);
    }

    public String[] getAllHomeButtonNames() {
        return new String[]{"Home", "Subscriptions", "Roles", "Groups", "Users",
                "Reports", "API Management", "Help", "Notifications", "System configuration"};
    }
    public String[] getAllDashboardButtonNames() {
        return new String[]{"Dashboard", "Tasks", "Search", "Watchlist", "Data Export"};
    }
    public Map<String, Boolean> validateAllhomeButtonsPresence() {
        Map<String, Boolean> buttonStatus = new HashMap<>();
        for (String buttonName : getAllHomeButtonNames()) {
            buttonStatus.put(buttonName, isButtonPresent(buttonName));
        }
        return buttonStatus;
    }
    public Map<String, Boolean> validateAllDashboardButtonsPresence() {
        Map<String, Boolean> buttonStatus = new HashMap<>();
        for (String buttonName : getAllDashboardButtonNames()) {
            buttonStatus.put(buttonName, isButtonPresent(buttonName));
        }
        return buttonStatus;
    }
    public void scrollToSystemConfigButton() {
        elementLib.scrollToElement(systemConfigButton);
    }
    // public void togglePanel() {
    //     try {
    //         if (elementLib.isElementDisplayed(btnClose)) {
    //             elementLib.click(btnClose);
    //             elementLib.hardWait(5);
    //             // Additional wait for panel animation to complete
    //         }
    //     } catch (Exception e) {
    //         // Try alternative locator if primary fails
    //         try {
    //             if (elementLib.isElementDisplayed(btnCloseAlternative)) {
    //                 elementLib.click(btnCloseAlternative);
    //                 // Additional wait for panel animation to complete
    //             }
    //         } catch (Exception ex) {
    //             throw new RuntimeException("Unable to click Close button: " + ex.getMessage());
    //         }
    //     }
    // }
         public void togglePanel() {
        if (elementLib.isElementDisplayed(btnClose)) {
            elementLib.click(btnClose);
            elementLib.hardWait(5);
        }
      }
    public boolean isPanelCollapsed() {
        try {
            // Wait for panel state to stabilize after collapse
            elementLib.hardWait(2);
            
            // Check if close button is not visible (primary indicator)
            boolean closeButtonNotVisible = !elementLib.isElementDisplayed(btnClose) && 
                                           !elementLib.isElementDisplayed(btnCloseAlternative);
            
            // Check class attribute for collapsed indicators
            String classAttribute = elementLib.getAttribute(leftPanel, "class");
            boolean hasCollapsedClass = classAttribute != null && 
                (classAttribute.contains("collapsed") || classAttribute.contains("compact") || 
                 classAttribute.contains("minimized") || classAttribute.contains("closed"));
            
            // Check if navigation buttons are hidden (additional verification)
            boolean buttonsHidden = false;
            try {
                buttonsHidden = !elementLib.isElementDisplayed(homeButton);
            } catch (Exception e) {
                buttonsHidden = true; // If button is not found, likely collapsed
            }
            
            // Panel is collapsed if close button is not visible OR has collapsed class OR buttons are hidden
            return closeButtonNotVisible || hasCollapsedClass || buttonsHidden;
            
        } catch (Exception e) {
            return false;
        }
    }
    public void expandPanel() {
        if (elementLib.isElementDisplayed(btnExpandpanel)) {
            elementLib.click(btnExpandpanel);
            elementLib.hardWait(2);
        } else {
            throw new RuntimeException("Expand button is not displayed. Cannot expand the panel.");
        }
    }
    
    public boolean isDashboardLoaded() {
        try {
            elementLib.waitForElementToBeVisible(dashboardTitle, Duration.ofSeconds(10));
            return elementLib.isElementDisplayed(dashboardTitle);
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areCardsVisible() {
        try {
            elementLib.waitForElementToBeVisible(listManagementCard, Duration.ofSeconds(10));
            // Use elementLib isElementDisplayed() method
            return elementLib.isElementDisplayed(listManagementCard) && 
                   elementLib.isElementDisplayed(customerScreeningCard) && 
                   elementLib.isElementDisplayed(transactionScreeningCard) && 
                   elementLib.isElementDisplayed(transactionMonitoringCard);
        } catch (Exception e) {
            return false;
        }
    }
    
    public WebElement getCardElement(String cardName) {
        switch (cardName) {
            case "List Management":
                return listManagementCard;
            case "Customer Screening":
                return customerScreeningCard;
            case "Transaction Screening":
                return transactionScreeningCard;
            case "Transaction Monitoring":
                return transactionMonitoringCard;
            default:
                throw new IllegalArgumentException("Unknown card name: " + cardName);
        }
    }
    public boolean isCardEnabled(String cardName) {
        WebElement card = getCardElement(cardName);
        if (card != null) {
            return elementLib.isEnabled(card);
        } else {
            throw new RuntimeException("Card '" + cardName + "' not found");
        }
    }
    public void clickCard(String cardName) {
        WebElement card = getCardElement(cardName);
        if (card != null) {
            elementLib.click(card);
        } else {
            throw new RuntimeException("Card '" + cardName + "' not found");
        }
    }
    
    
}