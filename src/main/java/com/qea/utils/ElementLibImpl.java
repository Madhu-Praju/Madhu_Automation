package com.qea.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * ElementLibImpl Implementation
 * Implements all essential methods for Selenium WebDriver operations
 * Used in Cucumber and TestNG frameworks
 */
public class ElementLibImpl implements ElementLib {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private final int DEFAULT_TIMEOUT = 10;
    private String mainWindowHandle;
    private JavascriptExecutor jsExecutor;
    
    // Constructor
    public ElementLibImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.actions = new Actions(driver);
        this.mainWindowHandle = driver.getWindowHandle();
    }
    
    // Driver Management
    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.actions = new Actions(driver);
        this.mainWindowHandle = driver.getWindowHandle();
    }
    
    @Override
    public WebDriver getDriver() {
        return driver;
    }
    
    // Element Finding
    @Override
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    @Override
    public WebElement findElement(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    @Override
    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    
    @Override
    public List<WebElement> findElements(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return customWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    
    // Element Interaction
    @Override
    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    @Override
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Override
    public void doubleClick(By locator) {
        WebElement element = findElement(locator);
        actions.doubleClick(element).perform();
    }
    
    @Override
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }
    
    @Override
    public void rightClick(By locator) {
        WebElement element = findElement(locator);
        actions.contextClick(element).perform();
    }
    
    @Override
    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }
    
    // Text Input
    @Override
    public void sendKeys(By locator, String text) {
        WebElement element = findElement(locator);
        element.sendKeys(text);
    }
    
    @Override
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }
    
    @Override
    public void clearAndSendKeys(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    @Override
    public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
    
    @Override
    public void clear(By locator) {
        WebElement element = findElement(locator);
        element.clear();
    }
    
    @Override
    public void clear(WebElement element) {
        element.clear();
    }
    
    // Element Properties
    @Override
    public String getText(By locator) {
        WebElement element = findElement(locator);
        return element.getText();
    }
    
    @Override
    public String getText(WebElement element) {
        return element.getText();
    }
    
    @Override
    public String getAttribute(By locator, String attribute) {
        WebElement element = findElement(locator);
        return element.getAttribute(attribute);
    }
    
    @Override
    public String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
    
    @Override
    public String getCssValue(By locator, String property) {
        WebElement element = findElement(locator);
        return element.getCssValue(property);
    }
    
    @Override
    public String getCssValue(WebElement element, String property) {
        return element.getCssValue(property);
    }

   
    @Override
    public String getTabIndex(WebElement element) {
        return element.getAttribute("tabindex");
    }

    @Override
    public String getDomAttribute(By locator, String attribute) {
        WebElement element = findElement(locator);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    @Override
    public String getDomAttribute(WebElement element, String attribute) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    // Element State
    @Override
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    @Override
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
    
    @Override
    public boolean isEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    @Override
    public boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
    
    @Override
    public boolean isSelected(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    @Override
    public boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
    
    @Override
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isElementDisplayed(WebElement button) {
        try {
            return button != null && button.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            // Element is not present or is stale
            return false;
        } catch (Exception e) {
            // Any other unexpected exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Collection<Object> getElementAttribute(By locator, String aClass) {
        Collection<Object> attributes = new ArrayList<>();
        try {
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                String attributeValue = element.getAttribute(aClass);
                if (attributeValue != null) {
                    attributes.add(attributeValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // or use logging
        }
        return attributes;
    }

    @Override
    public Collection<Object> getElementAttribute(WebElement element, String aClass) {
        Collection<Object> attributes = new ArrayList<>();
        try {
            if (element != null) {
                // Find all child elements inside the given element
                List<WebElement> children = element.findElements(By.xpath(".//*"));

                for (WebElement child : children) {
                    String attributeValue = child.getAttribute(aClass);
                    if (attributeValue != null) {
                        attributes.add(attributeValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attributes;
    }


    @Override
    public String getTagName(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getTagName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getTagName(WebElement element) {
        try {
            return element != null ? element.getTagName() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getValue(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getAttribute("value");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getValue(WebElement element) {
        try {
            return element != null ? element.getAttribute("value") : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Wait Operations
    @Override
    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    @Override
    public void waitForElementVisible(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    @Override
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    @Override
    public void waitForElementClickable(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    @Override
    public void waitForElementClickable(WebElement element, Duration timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        customWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void waitForElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    @Override
    public void waitForElementInvisible(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    @Override
    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    @Override
    public void waitForElementPresent(By locator, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    @Override
    public void waitForTextPresent(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    @Override
    public void waitForTextPresent(By locator, String text, int timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        customWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(webDriver -> {
            String readyState = ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString();
            return readyState.equals("complete");
        });
       wait.until(driver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public void waitForVisibilityOfAllElements(List<WebElement> elements) {
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    @Override
    public void waitForVisibilityOfAllElements(List<WebElement> elements, Duration timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        for (WebElement element : elements) {
            customWait.until(ExpectedConditions.visibilityOf(element));
        }
    }
    
    @Override
    public void waitForVisibilityOfAllElements(By locator) {
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
    
    
    // Dropdown Operations
    @Override
    public void selectByVisibleText(By locator, String text) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
    @Override
    public void selectByValue(By locator, String value) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    
    @Override
    public void selectByIndex(By locator, int index) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    
    @Override
    public String getSelectedOption(By locator) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }
    
    @Override
    public List<String> getAllDropdownOptions(By locator) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    // Checkbox and Radio Button
    @Override
    public void checkCheckbox(By locator) {
        WebElement element = findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    @Override
    public void uncheckCheckbox(By locator) {
        WebElement element = findElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }
    
    @Override
    public void toggleCheckbox(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }
    
    @Override
    public void selectRadioButton(By locator) {
        WebElement element = findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    // Navigation
    @Override
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    @Override
    public void refresh() {
        driver.navigate().refresh();
    }
    
    @Override
    public void goBack() {
        driver.navigate().back();
    }
    
    @Override
    public void goForward() {
        driver.navigate().forward();
    }
    
    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    @Override
    public String getTitle() {
        return driver.getTitle();
    }
    
    // Window/Tab Operations
    @Override
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
    
    @Override
    public void switchToNewWindow() {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindowHandle)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
    
    @Override
    public void closeCurrentWindow() {
        driver.close();
    }
    
    @Override
    public void switchToDefaultWindow() {
        driver.switchTo().window(mainWindowHandle);
    }
    
    @Override
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }
    
    @Override
    public List<String> getAllWindowHandles() {
        return new ArrayList<>(driver.getWindowHandles());
    }
    
    // Frame Operations
    @Override
    public void switchToFrame(By locator) {
        WebElement frameElement = findElement(locator);
        driver.switchTo().frame(frameElement);
    }
    
    @Override
    public void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
    }
    
    @Override
    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }
    
    @Override
    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }
    
    @Override
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
    @Override
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }
    
    // Alert Operations
    @Override
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    
    @Override
    public void dismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
    
    @Override
    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }
    
    @Override
    public void sendKeysToAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
    }
    
    @Override
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    // JavaScript Operations
    @Override
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }
    
    @Override
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    @Override
    public void scrollToElement(WebElement element) {
        executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    @Override
    public void scrollToTop() {
        executeScript("window.scrollTo(0, 0);");
    }
    
    @Override
    public void scrollToBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    @Override
    public void highlightElement(By locator) {
        WebElement element = findElement(locator);
        highlightElement(element);
    }
    
    @Override
    public void highlightElement(WebElement element) {
        String originalStyle = element.getAttribute("style");
        executeScript("arguments[0].style.border='3px solid red';", element);
        hardWait(1);
        executeScript("arguments[0].style.border='" + originalStyle + "';", element);
    }
    
    // Mouse Actions
    @Override
    public void hoverOverElement(By locator) {
        WebElement element = findElement(locator);
        actions.moveToElement(element).perform();
    }
    
    @Override
    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
    
    @Override
    public void dragAndDrop(By source, By target) {
        WebElement sourceElement = findElement(source);
        WebElement targetElement = findElement(target);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }
    
    @Override
    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }
    
    // Keyboard Actions
    @Override
    public void pressKey(By locator, String key) {
        WebElement element = findElement(locator);
        element.sendKeys(Keys.valueOf(key));
    }
    
    @Override
    public void pressKey(WebElement element, String key) {
        element.sendKeys(Keys.valueOf(key));
    }
    
    @Override
    public void sendKeysCombination(String... keys) {
        Keys[] keysArray = Arrays.stream(keys)
                .map(Keys::valueOf)
                .toArray(Keys[]::new);
        actions.sendKeys(Keys.chord(keysArray)).perform();
    }
    
    // Screenshot
    @Override
    public String takeScreenshot() {
        return takeScreenshot("screenshot_" + System.currentTimeMillis());
    }
    
    @Override
    public String takeScreenshot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
        try {
            FileUtils.copyFile(sourceFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
    
    @Override
    public String takeElementScreenshot(By locator) {
        WebElement element = findElement(locator);
        return takeElementScreenshot(element);
    }
    
    @Override
    public String takeElementScreenshot(WebElement element) {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/screenshots/element_" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(sourceFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
    
    // Utility Methods
    @Override
    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
    
    @Override
    public void explicitWait(int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    
    @Override
    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public void pageLoadTimeout(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }
    
    @Override
    public void scriptTimeout(int seconds) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(seconds));
    }
    
    // Table Operations
    @Override
    public List<List<String>> getTableData(By tableLocator) {
        WebElement table = findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<List<String>> tableData = new ArrayList<>();
        
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.isEmpty()) {
                cells = row.findElements(By.tagName("th"));
            }
            List<String> rowData = cells.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            tableData.add(rowData);
        }
        return tableData;
    }
    
    @Override
    public String getCellData(By tableLocator, int row, int column) {
        WebElement table = findElement(tableLocator);
        WebElement cell = table.findElement(By.xpath(".//tr[" + (row + 1) + "]//td[" + (column + 1) + "]"));
        return cell.getText();
    }
    
    @Override
    public void clickTableCell(By tableLocator, int row, int column) {
        WebElement table = findElement(tableLocator);
        WebElement cell = table.findElement(By.xpath(".//tr[" + (row + 1) + "]//td[" + (column + 1) + "]"));
        cell.click();
    }
    
    @Override
    public int getRowCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        return table.findElements(By.tagName("tr")).size();
    }
    
    @Override
    public int getColumnCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        WebElement firstRow = table.findElement(By.tagName("tr"));
        return firstRow.findElements(By.tagName("td")).size();
    }
    
    // File Operations
    @Override
    public void uploadFile(By locator, String filePath) {
        WebElement element = findElement(locator);
        element.sendKeys(filePath);
    }
    
    @Override
    public void downloadFile(By locator, String downloadPath) {
        // Set download preferences
        System.setProperty("webdriver.chrome.driver", downloadPath);
        click(locator);
        hardWait(3); // Wait for download to complete
    }
    
    // Assertion Helpers
    @Override
    public void assertElementVisible(By locator) {
        Assert.assertTrue(isDisplayed(locator), "Element is not visible: " + locator);
    }
    
    @Override
    public void assertElementNotVisible(By locator) {
        Assert.assertFalse(isDisplayed(locator), "Element is visible: " + locator);
    }
    
    @Override
    public void assertElementEnabled(By locator) {
        Assert.assertTrue(isEnabled(locator), "Element is not enabled: " + locator);
    }
    
    @Override
    public void assertElementDisabled(By locator) {
        Assert.assertFalse(isEnabled(locator), "Element is enabled: " + locator);
    }
    
    @Override
    public void assertTextEquals(By locator, String expectedText) {
        String actualText = getText(locator);
        Assert.assertEquals(actualText, expectedText, "Text does not match");
    }
    
    @Override
    public void assertTextContains(By locator, String expectedText) {
        String actualText = getText(locator);
        Assert.assertTrue(actualText.contains(expectedText), 
                "Text '" + actualText + "' does not contain '" + expectedText + "'");
    }
    
    @Override
    public void assertAttributeEquals(By locator, String attribute, String expectedValue) {
        String actualValue = getAttribute(locator, attribute);
        Assert.assertEquals(actualValue, expectedValue, 
                "Attribute '" + attribute + "' value does not match");
    }

    @Override
    public void assertAttributeContains(By locator, String attribute, String expectedValue) {
        String actualValue = getAttribute(locator, attribute);
        Assert.assertTrue(actualValue != null && actualValue.contains(expectedValue),
                "Attribute '" + attribute + "' value '" + actualValue + "' does not contain '" + expectedValue + "'");
    }

    @Override
    public void waitForElementToBeVisible(WebElement element, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public String getTabIndex(WebElement tab, String tabIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTabIndex'");
    }


    
}
