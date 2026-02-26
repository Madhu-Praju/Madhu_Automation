package com.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class interpolRedDataExtraction {
	

	private WebDriver driver;
	
	//1. By Locator
	private By tableofLinks = By.xpath("/html/body/div[4]/div[2]/div/div/div[3]/div/div/div/div/div[1]/table");
	private By clickNextButton =By.xpath("/html/body/div[4]/div[2]/div/div/div[3]/div/div/div/div/div[2]/div[2]/div/a[2]");
	private By clickNxt = By.cssSelector(".paginate_button.next");
	private By getNumberofPage = By.xpath("//*[@id=\"commonDataTable_paginate\"]/span/a[6]");
	
	private By nameof =By.xpath("//*[@id=\"singlePanel\"]/div[1]/div/div/div[2]/div/h1");
    private By familyname = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/strong");
    private By gender =By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/table/tbody/tr[6]/td[2]/strong/span[1]");
    private By dob= By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/table/tbody/tr[7]/td[2]/strong[1]");
    private By placeofbirth = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/table/tbody/tr[8]/td[2]/strong/span[1]");
    private By nationality = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/table/tbody/tr[16]/td[2]/strong");
    private By languagespok = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[4]/table/tbody/tr[4]/td[2]/strong");
    private By charges = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[10]/p[1]");


public interpolRedDataExtraction(WebDriver driver) {
	this.driver = driver;
}

public List<String> getAllLinkandName() throws InterruptedException{
	
	Thread.sleep(8000);
	WebElement tableProducts = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[3]/div/div/div/div/div[1]/table"));
//	List<WebElement> tableRows = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tableProducts, By.tagName("tr")));
//	System.out.println("Print List" + tableRows);
	
	//WebElement sectionThreeText = driver.findElement(By.xpath("//*[@id=\"searchResultContainer\"]/div[2]"));
	Thread.sleep(500);
	//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
	//List<WebElement> tablerow = driver.findElements(By.tagName("td"));
	List<WebElement> tablerow = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div/div[3]/div/div/div/div/div[1]/table/tbody/tr/td"));//*[@id="commonDataTable"]/tbody/tr[1]/td[1]
	System.out.println("Score Size list:"+tablerow.size());
	List<WebElement> link = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div/div[3]/div/div/div/div/div[1]/table/tbody/tr/td/a"));
	List<String> number =new ArrayList<>();
	List<String> name =new ArrayList<>();			
			
	//System.out.println("Score Size list:"+name.size());
	//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	if(tablerow.size()>0 && link.size()>0) {
		for(int i=0; i<tablerow.size(); i++) {
			//System.out.println("Name inside loop Size list:"+name.get(i).getText());
			//names.add(name.get(i).getText(), score.get(i).getText());
			number.add(tablerow.get(i).getText());
			System.out.println("Table Row Number :  "+tablerow.get(i).getText());
			//name.add(link.get(i).getText());
			//System.out.println("Table Row NAme :  "+link.get(i).getText());
		}
		if(tablerow.size()>0 && link.size()>0) {
			for(int i=0; i<link.size(); i++) {
				//System.out.println("Name inside loop Size list:"+name.get(i).getText());
				//names.add(name.get(i).getText(), score.get(i).getText());
				//number.add(tablerow.get(i).getText());
				//System.out.println("Table Row Number :  "+tablerow.get(i).getText());
				name.add(link.get(i).getAttribute("href"));
				System.out.println("Table Row NAme :  "+link.get(i).getAttribute("href"));
			}
	}
	
	}
	
	
	
	return number;
}

public void clickNext() {
	driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	
	WebElement element = driver.findElement(By.cssSelector("#commonDataTable_next"));
	Actions actions = new Actions(driver);
//	actions.moveToElement(element).perform();
//	actions.click();
//	actions.perform();
	element.sendKeys(Keys.PAGE_DOWN);
	element.sendKeys(Keys.PAGE_DOWN);

	
	//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"commonDataTable_next\"]"))).click();
	//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#commonDataTable_next"))).click();
	driver.findElement(clickNextButton).click();
	
	
}


public String getName() {
	
	return driver.findElement(nameof).getText();
	
}
public String getFamilyName() {
	
	return driver.findElement(familyname).getText();
	
}
public String getGender() {
	
	return driver.findElement(gender).getText();
	
}
public String getDob() {
	
	return driver.findElement(dob).getText();
	
}
public String getPob() {
	
	return driver.findElement(placeofbirth).getText();
	
}
public String getnation() {
	
	return driver.findElement(nationality).getText();
	
}
public String getlang() {
	
	return driver.findElement(languagespok).getText();
	
}
public String getcharge() {
	
	return driver.findElement(charges).getText();
	
}
}


