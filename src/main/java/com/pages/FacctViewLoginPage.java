package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacctViewLoginPage {
	
	

	private WebDriver driver;
	
	//1. By Locator
	private By startPageText = By.xpath("//*[@id=\"applicationContainer\"]/div[1]/div[2]/span[2]");
	private By loginButton =By.xpath("//*[@id=\"applicationContainer\"]/div[1]/div[2]/div/div/button[2]");
	private By orgName = By.id("organizationName");
	private By continueButton = By.xpath("//button[@name='action']");
	private By contButton = By.xpath("/html/body/div/main/section/div/div/div/form/div[2]/button");
	private By emailId = By.id("username");
	private By passWord = By.id("password");
	private By loginButtont =By.xpath("/html/body/div/main/section/div/div/div/form/div[3]/button");
	private By welcomePage = By.xpath("//*[@id=\"instantScreening\"]/div[1]/div/h6");
	private By forgotPassword = By.linkText("Forgot password?");



public FacctViewLoginPage(WebDriver driver) {
	this.driver = driver;
	
}
public String getLoginPageText() {
	return (new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(startPageText)).getText());
}
public void clickLoginButton() {
	
	driver.findElement(loginButton).click();
	
}
// 3. page actions: features(behavior) of the page all form methods:

public void enterOrgName(String orgname) {
	driver.findElement(orgName).sendKeys(orgname);
}
public void clickcontButton() {
	
	//driver.findElement(contButton).click();
	new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(contButton)).click();
}


public boolean isForgotPwdLinkExist() {
	return driver.findElement(forgotPassword).isDisplayed();
}
public void enterUserName(String usernm) {
	driver.findElement(emailId).sendKeys(usernm);
}
public void enterPassword(String pwd) {
	driver.findElement(passWord).sendKeys(pwd);
}

public void clickonLoginButtonT() {
	
	driver.findElement(loginButtont).click();
	
}

public String getWelcomePageText() {
	return (new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(welcomePage)).getText());
}

public void clickOnContinue() {
	//driver.findElement(continueButton).click();
	
	new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(continueButton)).click();}

public void clickToContinue() {
	//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='c227b20f2 c8e6e34f8 c06daa549 c0fe4fa86 cf42875ec'"))).click();
	
	new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/section/div/div/div/form/div[3]/button"))).click();
}

public void clickOnLogin() {
	new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium fv-login ms-3 mt-5 css-12fuqtd'"))).click();
}

//wait 
	
	

}
