package com.pages;

import java.time.Duration;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {
    private WebDriver driver;

    // Locators
    //lOCATORS FOR WELCOME PAGE
    private By textWelcome = By.className("welcome");
    private By imgFacctum = By.xpath("//div//img[@alt='facctum logo white']");
    private By textPara = By.xpath("//div//h1");
    private By btnLogin = By.xpath("//button[text()='LOG IN']");

    private By textwelcome = By.xpath("//h1[text()='Welcome']");
    private By enteryourOrganization = By.xpath("//input[@id='organizationName']");
    private By continueButton = By.xpath("//div//button[text()='CONTINUE']");
//    private By paragraphName = By.className("c4bb6cb5d.c271e25f3");
    
    // private By titlewelcome = By.xpath("//*[@id='applicationContainer']/div[3]/div/div[1]/span/span[2]");
    private By inputEmailId = By.xpath("//input[@name='username' and @type='text' and @inputmode='email']");
    private By inputPassWord = By.xpath("//input[@name='password' and @type='password']");
    private By btnforgotPassword = By.xpath("//a[contains(text(), 'Forgot password')]");
    private By btnContinue = By.xpath("//button[@type='submit' and normalize-space(text())='Continue']");
    private By textGreet = By.className("greetings");
    private By textDashboard = By.className("path-label");

    private By btnUser = By.xpath("//div[@class='action-center']//div[contains(@class,'MuiAvatar')]");
    private By texttenantName = By.xpath("//span[@class='facct-tooltip ']//p");
    private By textLoginId = By.xpath("//div[@class='email']");

    private By btnSearch = By.xpath("//div[contains(@class,'navbar-content')]//div[@aria-label='Search']");

    // Constructor
    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstPageText() {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(textwelcome)).getText();
    }

    public String welcomePagepara() {
        return driver.findElement(textPara).getText();
    }

    public String getwelcometext() {
        return driver.findElement(textWelcome).getText();
    }

    public WebElement getFacctumLogoElement() {
        return driver.findElement(imgFacctum);
    }
    public void ClickLoginButton() {
        driver.findElement(btnLogin).click();
    }

    public boolean getForgotPwdLinkElement() {
        return driver.findElement(btnforgotPassword).isEnabled();
    }
    public void enterloginID(String loginId) {
        // Use explicit wait instead of implicit wait
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(inputEmailId)).sendKeys(loginId);
    }
    public void enterPass(String pwd) {
        // new WebDriverWait(driver, Duration.ofSeconds(20))
        //     .until(ExpectedConditions.visibilityOfElementLocated(inputPassWord)).sendKeys(pwd);
            driver.findElement(inputPassWord).sendKeys(pwd);
    }
    
    public void clickContinue() {
        // Use explicit wait instead of implicit wait
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }   
    
     public void enterOrgName(String orgname) {
        driver.findElement(enteryourOrganization).sendKeys(orgname);
    }

    public void clickOnContinue() {
        // Use explicit wait instead of implicit wait
        // new WebDriverWait(driver, Duration.ofSeconds(15))
        //          .until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(continueButton).click();
	}

    public void clickOnLoginButton() {
        By loginBtn = By.cssSelector("button.MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.fv-login.ms-3.mt-5.css-12fuqtd");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public String getGreetText() {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(textGreet)).getText();
    }

    public String getpageNameText()
    {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(textDashboard)).getText();
    }

 
   public String validateTenant() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(btnUser)).click();
              String tenatText = driver.findElement(texttenantName).getText();
        String[] parts = tenatText.split(" ");
        String tenatName = parts[0]; // Assuming the tenant name is the first part
              
        return tenatName;

    }

    public String validateLoginId() {
          
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(btnUser));
          String loginIdText = driver.findElement(textLoginId).getText();
          return loginIdText;
     }

    public void clickOnSearchButton() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
    }
    
    public boolean validatePageUrl(String expectedUrlPart) {
        return driver.getCurrentUrl().contains(expectedUrlPart);
    }
}



    
