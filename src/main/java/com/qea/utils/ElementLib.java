package com.qea.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

/**
 * ElementLib Interface
 * Contains all essential methods for Selenium WebDriver operations
 * Used in Cucumber and TestNG frameworks
 */
public interface ElementLib {
    
    // Driver Management
    void setDriver(WebDriver driver);
    WebDriver getDriver();
    
    // Element Finding
    WebElement findElement(By locator);
    WebElement findElement(By locator, int timeout);
    List<WebElement> findElements(By locator);
    List<WebElement> findElements(By locator, int timeout);
    
    // Element Interaction
    void click(By locator);
    void click(WebElement element);
    void doubleClick(By locator);
    void doubleClick(WebElement element);
    void rightClick(By locator);
    void rightClick(WebElement element);
    
    // Text Input
    void sendKeys(By locator, String text);
    void sendKeys(WebElement element, String text);
    void clearAndSendKeys(By locator, String text);
    void clearAndSendKeys(WebElement element, String text);
    void clear(By locator);
    void clear(WebElement element);
    
    // Element Properties
    String getText(By locator);
    String getText(WebElement element);
    String getAttribute(By locator, String attribute);
    String getAttribute(WebElement element, String attribute);
    String getCssValue(By locator, String property);
    String getCssValue(WebElement element, String property);
    Collection<Object> getElementAttribute(By locator, String aClass);
    Collection<Object> getElementAttribute(WebElement element, String aClass);
    String getTagName(By locator);
    String getTagName(WebElement element);
    String getValue(By locator);
    String getValue(WebElement element);
    String getTabIndex(WebElement tab, String tabIndex);
    String getTabIndex(WebElement element);
    String getDomAttribute(By locator, String attribute);
    String getDomAttribute(WebElement element, String attribute);

    // Element State
    boolean isDisplayed(By locator);
    boolean isDisplayed(WebElement element);
    boolean isEnabled(By locator);
    boolean isEnabled(WebElement element);
    boolean isSelected(By locator);
    boolean isSelected(WebElement element);
    boolean isElementPresent(By locator);
    boolean isElementDisplayed(WebElement button);
    
    // Wait Operations
    void waitForElementVisible(By locator);
    void waitForElementToBeVisible(WebElement element, Duration timeout);
    void waitForElementVisible(By locator, int timeout);
    void waitForElementClickable(By locator);
    void waitForElementClickable(WebElement element, Duration timeout);
    void waitForElementClickable(By locator, int timeout);
    void waitForElementInvisible(By locator);
    void waitForElementInvisible(By locator, int timeout);
    void waitForElementPresent(By locator);
    void waitForElementPresent(By locator, int timeout);
    void waitForTextPresent(By locator, String text);
    void waitForTextPresent(By locator, String text, int timeout);
    void waitForPageToLoad();
    void waitForVisibilityOfAllElements(List<WebElement> elements);
    void waitForVisibilityOfAllElements(List<WebElement> elements, Duration timeout);
    void waitForVisibilityOfAllElements(By locator);
    
    // Dropdown Operations
    void selectByVisibleText(By locator, String text);
    void selectByValue(By locator, String value);
    void selectByIndex(By locator, int index);
    String getSelectedOption(By locator);
    List<String> getAllDropdownOptions(By locator);
    
    // Checkbox and Radio Button
    void checkCheckbox(By locator);
    void uncheckCheckbox(By locator);
    void toggleCheckbox(By locator);
    void selectRadioButton(By locator);
    
    // Navigation
    void navigateTo(String url);
    void refresh();
    void goBack();
    void goForward();
    String getCurrentUrl();
    String getTitle();
    
    // Window/Tab Operations
    void switchToWindow(String windowHandle);
    void switchToNewWindow();
    void closeCurrentWindow();
    void switchToDefaultWindow();
    String getCurrentWindowHandle();
    List<String> getAllWindowHandles();
    
    // Frame Operations
    void switchToFrame(By locator);
    void switchToFrame(WebElement element);
    void switchToFrame(String nameOrId);
    void switchToFrame(int index);
    void switchToDefaultContent();
    void switchToParentFrame();
    
    // Alert Operations
    void acceptAlert();
    void dismissAlert();
    String getAlertText();
    void sendKeysToAlert(String text);
    boolean isAlertPresent();
    
    // JavaScript Operations
    Object executeScript(String script, Object... args);
    void scrollToElement(By locator);
    void scrollToElement(WebElement element);
    void scrollToTop();
    void scrollToBottom();
    void highlightElement(By locator);
    void highlightElement(WebElement element);
    
    // Mouse Actions
    void hoverOverElement(By locator);
    void hoverOverElement(WebElement element);
    void dragAndDrop(By source, By target);
    void dragAndDrop(WebElement source, WebElement target);
    
    // Keyboard Actions
    void pressKey(By locator, String key);
    void pressKey(WebElement element, String key);
    void sendKeysCombination(String... keys);
    
    // Screenshot
    String takeScreenshot();
    String takeScreenshot(String fileName);
    String takeElementScreenshot(By locator);
    String takeElementScreenshot(WebElement element);
    
    // Utility Methods
    void implicitWait(int seconds);
    void explicitWait(int seconds);
    void hardWait(int seconds);
    void pageLoadTimeout(int seconds);
    void scriptTimeout(int seconds);
    
    // Table Operations
    List<List<String>> getTableData(By tableLocator);
    String getCellData(By tableLocator, int row, int column);
    void clickTableCell(By tableLocator, int row, int column);
    int getRowCount(By tableLocator);
    int getColumnCount(By tableLocator);
    
    // File Operations
    void uploadFile(By locator, String filePath);
    void downloadFile(By locator, String downloadPath);
    
    // Assertion Helpers
    void assertElementVisible(By locator);
    void assertElementNotVisible(By locator);
    void assertElementEnabled(By locator);
    void assertElementDisabled(By locator);
    void assertTextEquals(By locator, String expectedText);
    void assertTextContains(By locator, String expectedText);
    void assertAttributeEquals(By locator, String attribute, String expectedValue);
    void assertAttributeContains(By locator, String attribute, String expectedValue);
    
    

}
