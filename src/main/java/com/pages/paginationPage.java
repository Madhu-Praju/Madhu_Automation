package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginationPage extends BasePage {

    private WebDriverWait wait;

    public paginationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    

    @FindBy(xpath = "//div[contains(@style,'display:')]//button")
    private WebElement btnRowperpagedd;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//span[contains(@style,'padding-left')]")
    private WebElement txtcurrentRowcount;

    @FindBy(xpath = "//ul[@role='menu']//li")
    private List<WebElement> dropdownOptions;

    @FindBy(xpath = "(//button[contains(@class,'pagination-next-btn')])[3]")
    private WebElement btnNext;
    
    @FindBy(xpath = "(//button[contains(@class,'pagination-next-btn')])[4]")
    private WebElement btnlastPage;

    @FindBy(xpath = "(//button[contains(@class,'pagination-next-btn')])[1]")
    private WebElement btnfirstPage;

    @FindBy(xpath = "(//button[contains(@class,'pagination-next-btn')])[2]")
    private WebElement btnpreviousPage;

    @FindBy(xpath = "//p[contains(@class,'pagination-text')]")
    private WebElement txtPaginationCount;

    @FindBy(xpath = "//div[@class=\"w-13\"]")
    private List<WebElement> txtUnclaimedRecords;

    @FindBy(xpath = "//div[@class='w-13']//div[@class='facct-name']")
    private List<WebElement> txtClaimedRecords;

    @FindBy(xpath = "//*[local-name() = 'svg' and contains(@class,'lock-icon')]")
    private List<WebElement> lockIcons;
    

    // Locators for each option in the dropdown (by value attribute)
    @FindBy(xpath = "//li[@role='menuitem' and @value='10']")
    private WebElement option10;

    @FindBy(xpath = "//li[@role='menuitem' and @value='20']")
    private WebElement option20;

    @FindBy(xpath = "//li[@role='menuitem' and @value='30']")
    private WebElement option30;

    @FindBy(xpath = "//li[@role='menuitem' and @value='40']")
    private WebElement option40;

    @FindBy(xpath = "//li[@role='menuitem' and @value='50']")
    private WebElement option50;

    @FindBy(xpath = "//li[@role='menuitem' and @value='100']")
    private WebElement option100;

       // Method to open the Row per page dropdown
    public int getActualRowCount() {
        elementLib.waitForVisibilityOfAllElements(tableRows);
        return tableRows.size();
    }
    public int getCurrentRowCount() {
        elementLib.waitForElementToBeVisible(txtcurrentRowcount, Duration.ofSeconds(10));
        String rowCountText = txtcurrentRowcount.getText();
        return Integer.parseInt(rowCountText.replaceAll("[^0-9]", ""));
    }
    public void clickOnNextButton() {
        elementLib.waitForElementClickable(btnNext, Duration.ofSeconds(10));
        elementLib.click(btnNext);
    }
    public void clickOnFirstButton() {
        elementLib.waitForElementClickable(btnfirstPage, Duration.ofSeconds(10));
        elementLib.click(btnfirstPage);
    }
    public void clickOnPreviousButton() {
        elementLib.waitForElementClickable(btnpreviousPage, Duration.ofSeconds(10));
        elementLib.click(btnpreviousPage);
    }

    public void clickOnLastButton() {
        elementLib.waitForElementClickable(btnlastPage, Duration.ofSeconds(10));
        elementLib.click(btnlastPage);
    }

    public void changePaginationCount(int rowsPerPage) {
        elementLib.waitForElementToBeVisible(btnRowperpagedd, Duration.ofSeconds(10));
        elementLib.click(btnRowperpagedd);
        elementLib.waitForVisibilityOfAllElements(dropdownOptions);
        for (WebElement option : dropdownOptions) {
            String value = option.getAttribute("value");
            if (value != null && value.trim().equals(String.valueOf(rowsPerPage).trim())) {
                elementLib.waitForElementToBeVisible(option, Duration.ofSeconds(10));
                elementLib.click(option);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid rows per page: " + rowsPerPage);
        // No need to close dropdown, it closes automatically after selection
    }
    public int getPaginationCount() {
        elementLib.waitForElementToBeVisible(txtPaginationCount, Duration.ofSeconds(10));
        String paginationText = txtPaginationCount.getText();
      
        try {
            String[] dashSplit = paginationText.split("-");
            if (dashSplit.length < 2) return -1;
            String afterDash = dashSplit[1].trim();
            String[] ofSplit = afterDash.split(" of");
            String numberStr = ofSplit[0].replaceAll("[^0-9]", "");
            return Integer.parseInt(numberStr);
        } catch (Exception e) {
            System.out.println("Could not parse pagination count: " + e.getMessage());
            return -1;
        }
    }
    public int gettotalRecordsCount(){
        elementLib.waitForElementToBeVisible(txtPaginationCount, Duration.ofSeconds(10));
        String paginationText = txtPaginationCount.getText();
        // Example: "7971-7975 of 7975"
        try {
            String[] dashSplit = paginationText.split("-");
            if (dashSplit.length < 2) throw new RuntimeException("Pagination text format invalid: " + paginationText);
            String startStr = dashSplit[0].replaceAll("[^0-9]", "");
            return Integer.parseInt(startStr);
        } catch (Exception e) {
            System.out.println("Could not parse start record count: " + e.getMessage());
            return -1;
        }
    }
    public int defaultRecordsCount() {
        elementLib.waitForElementToBeVisible(txtPaginationCount, Duration.ofSeconds(10));
        String paginationText = txtPaginationCount.getText();
        // Example: "1-10 of 7974"
        try {
            String[] ofSplit = paginationText.split(" of ");
            if (ofSplit.length < 2) throw new RuntimeException("Pagination text format invalid: " + paginationText);
            String totalStr = ofSplit[1].replaceAll("[^0-9]", "");
            return Integer.parseInt(totalStr);
        } catch (Exception e) {
            System.out.println("Could not parse default records count: " + e.getMessage());
            return -1;
        }
    }

    public int getFilteredRecordsCount(){
        elementLib.waitForElementToBeVisible(txtPaginationCount, Duration.ofSeconds(10));
        String paginationText = txtPaginationCount.getText();
        // Example: "1-10 of 7974"
        try {
            String[] ofSplit = paginationText.split(" of ");
            if (ofSplit.length < 2) throw new RuntimeException("Pagination text format invalid: " + paginationText);
            String totalStr = ofSplit[1].replaceAll("[^0-9]", "");
            return Integer.parseInt(totalStr);
        } catch (Exception e) {
            System.out.println("Could not parse filtered records count: " + e.getMessage());
            return -1;
        }
    }
    public boolean isFirstPageButtonDisabled() {
        elementLib.waitForElementToBeVisible(btnfirstPage, Duration.ofSeconds(10));
        // Check tabIndex property
        String tabIndex = btnfirstPage.getAttribute("tabindex");
        boolean isTabIndexMinusOne = "-1".equals(tabIndex);
       
        boolean isDisabled = !btnfirstPage.isEnabled() || isTabIndexMinusOne;
        return isDisabled;
    }
    public boolean isPreviousPageButtonDisabled() {
        elementLib.waitForElementToBeVisible(btnpreviousPage, Duration.ofSeconds(10));
        // Check tabIndex property
        String tabIndex = btnpreviousPage.getAttribute("tabindex");
        boolean isTabIndexMinusOne = "-1".equals(tabIndex);
        boolean isDisabled = !btnpreviousPage.isEnabled() || isTabIndexMinusOne;
        return isDisabled;
    }
    public boolean isNextPageButtonDisabled() {
        elementLib.waitForElementToBeVisible(btnNext, Duration.ofSeconds(10));
        // Check tabIndex property
        String tabIndex = btnNext.getAttribute("tabindex");
        boolean isTabIndexMinusOne = "-1".equals(tabIndex);
        boolean isDisabled = !btnNext.isEnabled() || isTabIndexMinusOne;
        return isDisabled;
    }
    public boolean isLastPageButtonDisabled() {
        elementLib.waitForElementToBeVisible(btnlastPage, Duration.ofSeconds(10));
        // Check tabIndex property
        String tabIndex = btnlastPage.getAttribute("tabindex");
        boolean isTabIndexMinusOne = "-1".equals(tabIndex);
        boolean isDisabled = !btnlastPage.isEnabled() || isTabIndexMinusOne;
        return isDisabled;
    }
    public boolean validateUnclaimedRecordsContainDash() {
        elementLib.waitForVisibilityOfAllElements(txtUnclaimedRecords);
        for (WebElement el : txtUnclaimedRecords) {
            String text = el.getText();
            if (text == null || !text.contains("-")) {
                return false;
            }
        }
        return true;
    }
    
    public String getClaimedRecordsText() {
        try {
            elementLib.waitForVisibilityOfAllElements(txtClaimedRecords, Duration.ofSeconds(10));
            StringBuilder claimedRecordsText = new StringBuilder();
            
            // Check if list is empty
            if (txtClaimedRecords == null || txtClaimedRecords.isEmpty()) {
                System.out.println("No claimed records found on the page");
                return "";
            }
            
            // Use enhanced for loop to avoid index issues
            for (int i = 0; i < txtClaimedRecords.size(); i++) {
                try {
                    // Double-check bounds before accessing
                    if (i >= txtClaimedRecords.size()) {
                        System.out.println("Index " + i + " out of bounds, breaking loop");
                        break;
                    }
                    
                    WebElement el = txtClaimedRecords.get(i);
                    // Scroll to the element to ensure it is in view and text is loaded
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
                    String name = el.getText();
                    
                    if (name != null && !name.trim().isEmpty()) {
                        claimedRecordsText.append(name);
                        // Use pipe separator instead of space to avoid breaking multi-word names
                        if (i < txtClaimedRecords.size() - 1) {
                            claimedRecordsText.append(" | ");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException at index " + i + ", current size: " + txtClaimedRecords.size());
                    break;
                } catch (Exception e) {
                    System.out.println("Error processing claimed record at index " + i + ": " + e.getMessage());
                    continue;
                }
            }
            return claimedRecordsText.toString();
        } catch (Exception e) {
            System.out.println("Error in getClaimedRecordsText: " + e.getMessage());
            return "";
        }
    }
    
    /**
     * Gets a list of individual claimed record names (useful for step definitions)
     * @return List<String> - list of individual claimed user names
     */
    public java.util.List<String> getClaimedRecordsList() {
        java.util.List<String> claimedNames = new java.util.ArrayList<>();
        try {
            elementLib.waitForVisibilityOfAllElements(txtClaimedRecords, Duration.ofSeconds(10));
            
            // Check if list is empty
            if (txtClaimedRecords == null || txtClaimedRecords.isEmpty()) {
                System.out.println("No claimed records found on the page");
                return claimedNames;
            }
            
            for (int i = 0; i < txtClaimedRecords.size(); i++) {
                try {
                    // Double-check bounds before accessing
                    if (i >= txtClaimedRecords.size()) {
                        System.out.println("Index " + i + " out of bounds, breaking loop");
                        break;
                    }
                    
                    WebElement el = txtClaimedRecords.get(i);
                    // Scroll to the element to ensure it is in view and text is loaded
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
                    String name = el.getText();
                    
                    if (name != null && !name.trim().isEmpty()) {
                        claimedNames.add(name.trim());
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException at index " + i + ", current size: " + txtClaimedRecords.size());
                    break;
                } catch (Exception e) {
                    System.out.println("Error processing claimed record at index " + i + ": " + e.getMessage());
                    continue;
                }
            }
            return claimedNames;
        } catch (Exception e) {
            System.out.println("Error in getClaimedRecordsList: " + e.getMessage());
            return claimedNames;
        }
    }
    public boolean validateLockIconsPresent() {
        try {
            // Wait for all rows and lock icons to be visible
            elementLib.waitForVisibilityOfAllElements(tableRows, Duration.ofSeconds(10));
            elementLib.waitForVisibilityOfAllElements(lockIcons, Duration.ofSeconds(10));
            
            // Check if either list is empty
            if (tableRows == null || tableRows.isEmpty()) {
                System.out.println("No table rows found on the page");
                return false;
            }
            
            if (lockIcons == null || lockIcons.isEmpty()) {
                System.out.println("No lock icons found on the page");
                return false;
            }
            
            System.out.println("Found " + tableRows.size() + " table rows and " + lockIcons.size() + " lock icons");
            
            // There should be a lock icon for each record (row) on the page
            if (tableRows.size() != lockIcons.size()) {
                System.out.println("Mismatch: " + tableRows.size() + " rows but " + lockIcons.size() + " lock icons");
                return false;
            }
            
            // Validate each lock icon is displayed using indexed loop for better error handling
            for (int i = 0; i < lockIcons.size(); i++) {
                try {
                    // Double-check bounds before accessing
                    if (i >= lockIcons.size()) {
                        System.out.println("Index " + i + " out of bounds for lock icons, breaking loop");
                        break;
                    }
                    
                    WebElement el = lockIcons.get(i);
                    if (!el.isDisplayed()) {
                        System.out.println("Lock icon at index " + i + " is not displayed");
                        return false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException at lock icon index " + i + ", current size: " + lockIcons.size());
                    return false;
                } catch (Exception e) {
                    System.out.println("Error checking lock icon at index " + i + ": " + e.getMessage());
                    return false;
                }
            }
            
            System.out.println("All lock icons are present and displayed");
            return true;
            
        } catch (Exception e) {
            System.out.println("Error in validateLockIconsPresent: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Clicks on specific coordinates on the page using multiple approaches for reliability.
     * @param x - X coordinate to click (relative to the current viewport)
     * @param y - Y coordinate to click (relative to the current viewport)
     */
    public void clickAnywhere(int x, int y) {
        try {
            System.out.println("Attempting to click at coordinates: (" + x + ", " + y + ")");
            
            // Method 1: Using Actions class with moveByOffset
            Actions actions = new Actions(driver);
            actions.moveByOffset(x, y).click().perform();
            System.out.println("Successfully clicked using Actions.moveByOffset method");
            
            // Reset mouse position to avoid offset accumulation
            actions.moveByOffset(-x, -y).perform();
            
        } catch (Exception e1) {
            System.out.println("Actions.moveByOffset failed: " + e1.getMessage());
            
            try {
                // Method 2: Using JavaScript to click at coordinates
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String script = "document.elementFromPoint(" + x + ", " + y + ").click();";
                js.executeScript(script);
                System.out.println("Successfully clicked using JavaScript method");
                
            } catch (Exception e2) {
                System.out.println("JavaScript click failed: " + e2.getMessage());
                
                try {
                    // Method 3: Alternative Actions approach - move to body first then offset
                    Actions actions = new Actions(driver);
                    WebElement body = driver.findElement(org.openqa.selenium.By.tagName("body"));
                    actions.moveToElement(body, 0, 0).moveByOffset(x, y).click().perform();
                    System.out.println("Successfully clicked using Actions.moveToElement + offset method");
                    
                } catch (Exception e3) {
                    System.out.println("All click methods failed. Final error: " + e3.getMessage());
                    throw new RuntimeException("Unable to click at coordinates (" + x + ", " + y + ") using any method", e3);
                }
            }
        }
    }

    
    
    
}

