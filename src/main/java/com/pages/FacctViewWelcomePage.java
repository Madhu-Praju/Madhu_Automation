package com.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qea.listeners.UIActionListener;
import com.qea.utils.ElementLib;
import com.qea.utils.ElementLibImpl;


public class FacctViewWelcomePage extends BasePage{
	
	private WebDriver driver;
	private By sideBarListone = By.xpath("//*[@id=\"sidebarContent\"]/ul[1]/li/div/div[2]/p");
	private By sideBarfirst = By.xpath("//*[@id=\"sidebarContent\"]/ul[1]/li[1]/div/div[2]/p");
	private By entList = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li");
	// private By clickArrow = By.xpath("//*[@id=\"entityType\"]");
	private By clickAny = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]");
	private By clickInd = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[2]");
	private By clickEnt = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[3]");	
	private By interName =By.xpath("//*[@id=\":ro:\"]");
	private By searchButton = By.xpath("//*[@id=\"searchInput\"]/div/div[3]/span/button[1]");
	private By extraFieldButton = By.xpath("//*[@id=\"searchInput\"]/div[1]/div[3]/span/button[2]");
	private By dateofBirth_reg =By.xpath("//*[@id=\":rp:\"]");
	private By natID_EntID =By.xpath("//*[@id=\"national-id\"]");
	private By searchResult = By.xpath("//*[@id=\"fv-summary-wrapper\"]/div[2]/b");
	private By downJson = By.xpath("//*[@id=\"json\"]/span[1]");
	private By popupVerify =By.xpath("//*[@id=\"7\"]");
	//private By blocklistName = By.xpath("/html/body/div[1]/div/div/div[1]/div[3]/div[4]/div[3]/div/div[2]/div[2]/div[1]/div/span[1]/div[2]/h6/div");
	private By blocklistName = By.tagName("h6");
	private By parentElement = By.xpath("//*[@id=\"searchResultContainer\"]");
	
	By clickArrow = By.xpath("//*[@id=\"entityType\"]");
   
	private ElementLib elementLib;

	public FacctViewWelcomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Create ElementLibImpl and wrap with UIActionListener proxy for screenshot capturing
		ElementLibImpl elementLibImpl = new ElementLibImpl(driver);
		this.elementLib = UIActionListener.createProxy(elementLibImpl);
	}
	
	public List<String> sidebarFirstList() {
		
		List<String> sideList = new ArrayList<>();
		List<WebElement> sideBarList = driver.findElements(sideBarListone);
		for (WebElement e : sideBarList)
		{
			String text =e.getAttribute("textContent");
		    System.out.println("getting Text: "+text);
		    sideList.add(text);
		}
		System.out.println("Printing second element :"+ driver.findElement(sideBarfirst).getAttribute("textContent"));
		return sideList;
	}
	
	public int getsideBarListOneCount() {
		
		return driver.findElements(sideBarListone).size();
	}
	
	public void clickSearch() {
		
		driver.findElement(sideBarfirst).click();
		
	}
    public void clickArrow(WebElement clickArrow) {
		clickArrow.click();
	
    	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		// driver.findElement(clickArrow).click();
		
	}
	public List<String> getEntityType(){
		
		List<String> entityList = new ArrayList<>();
		elementLib.waitForElementVisible(entList, 10);
		List<WebElement> entls = driver.findElements(entList);
		
		for (WebElement e : entls)
		{
			String text =e.getAttribute("textContent");
		    System.out.println("getting Text from Entity List: "+text);
		    entityList.add(text);
		}
		
		
		return entityList;
		
	}
	
	public void selectAny() {
        elementLib.waitForElementClickable(clickAny, 10);
		driver.findElement(clickAny).click();
		
	}
	
	public void selectInt() {
        elementLib.waitForElementClickable(clickInd, 10);
		driver.findElement(clickInd).click();
		
	}
	public void selectEntity() {
        elementLib.waitForElementClickable(clickEnt, 10);
		driver.findElement(clickEnt).click();
		
	}
	
	public void enterNameforSearch(String name) {
        elementLib.waitForElementVisible(interName, 10);
		driver.findElement(interName).sendKeys(name);
	}
	
	public void clickSearchButton() {
        elementLib.waitForElementClickable(searchButton, 10);
		driver.findElement(searchButton).click();
		
	}

	public void extraFieldForSearch() {
        elementLib.waitForElementClickable(extraFieldButton, 10);
		driver.findElement(extraFieldButton).click();
		
	}
	
	public void enterNIDorEntID(String idtype) {
        elementLib.waitForElementVisible(natID_EntID, 10);
		driver.findElement(natID_EntID).sendKeys(idtype);
	}
	public void enterdateofBirth(String dob) {
        elementLib.waitForElementVisible(dateofBirth_reg, 10);
		driver.findElement(dateofBirth_reg).sendKeys(dob);
	}
	
	public String searchResult() {
        elementLib.waitForElementVisible(searchResult, 10);
		return driver.findElement(searchResult).getText();
	}
	public void downloadJson() {
        elementLib.waitForElementClickable(downJson, 10);
		driver.findElement(downJson).click();
		
	}
	
	public String alertcaptur() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		return text;
	}
	public String verifyPopupMessage() {
        elementLib.waitForElementVisible(popupVerify, 10);
		return driver.findElement(popupVerify).getText();
	}
	
	public String blockListName() {
        elementLib.waitForElementVisible(blocklistName, 10);
		return driver.findElement(blocklistName).getText();
	}
	
public List<String> searchListContainerName() throws InterruptedException {
		
		elementLib.waitForElementVisible(parentElement, 20);
		WebElement sectionThreeText = driver.findElement(By.xpath("//*[@id=\"searchResultContainer\"]/div[2]"));
		Thread.sleep(1000);
		List<WebElement> score = sectionThreeText.findElements(By.xpath("./div/div/span[1]/div[1]"));
		List<WebElement> name = sectionThreeText.findElements(By.tagName("h6"));
		List<String> names =new ArrayList<>();
		List<String> scores =new ArrayList<>();			
				
		if(score.size()>0 && score.size()==name.size()) {
			for(int i=0; i<score.size(); i++) {
				names.add(name.get(i).getText());
				scores.add(score.get(i).getText());
			}
			
		}
		
		return names;
	}
	

public List<String> searchListContainerScore() throws InterruptedException {
	
	elementLib.waitForElementVisible(parentElement, 20);
	WebElement sectionThreeText = driver.findElement(By.xpath("//*[@id=\"searchResultContainer\"]/div[2]"));
	Thread.sleep(5000);
	//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
	List<WebElement> score = sectionThreeText.findElements(By.xpath("./div/div/span[1]/div[1]"));
	List<WebElement> name = sectionThreeText.findElements(By.tagName("h6"));
	List<String> names =new ArrayList<>();
	List<String> scores =new ArrayList<>();			
			
	//System.out.println("Score Size list:"+name.size());
	//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	if(score.size()>0 && score.size()==name.size()) {
		for(int i=0; i<score.size(); i++) {
			//System.out.println("Name inside loop Size list:"+name.get(i).getText());
			//names.add(name.get(i).getText(), score.get(i).getText());
			names.add(name.get(i).getText());
			//System.out.println("Map Score :  "+score.get(i).getText());
			scores.add(score.get(i).getText());
		}
		
	}
	
	//System.out.println("searchListContainerScore:"+scores);
	
	//return driver.findElement(blocklistName).getText();
	return scores;
}
	
	
}
