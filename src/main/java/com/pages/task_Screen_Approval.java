package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

public class task_Screen_Approval {
	
	
	private WebDriver driver;
	String status1,status2,status3, xpathofl;
	//1. By Locator
		private By passlistclick= By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/ul/div/li[2]/div");
		private By watchlistsourcelink = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/ul/li[4]/div/div[2]/p");
		//private By clickrightpane= By.xpath("//*[@id=\"sidebarContent\"]/ul/li[4]/div/div[1]");
		private By clickrightpane= By.xpath("//div[@id='sidebar']/div/div/div/ul/li[4]/div/div");
	private By clickNewListbutton =By.xpath("//span[@aria-label='Details']");
	private By passListName =By.xpath("//input[@id='scenario-name']");
	private By reason1 =By.xpath("//div[@aria-labelledby='reason reason']");
	private By clickInitiate =By.xpath("//button[@id='initiate-button']");
	private By cancel =By.xpath("//button[@id='scenario-cancel-button']");
	private By status=By.xpath("//*[@aria-labelledby='tableTitle']/tbody/tr[1]/td[6]");
	private By search =By.xpath("//input[@placeholder='Search']");
	private By taskicon=By.xpath("//*[local-name()='svg' and @data-testid='AssignmentTurnedInOutlinedIcon']");
	private By tasks=By.xpath("//div[@id='sidebar']/div[2]/div/div/ul/li[2]");
	private By mainWindowIcon =By.xpath("//*[@id=\"applicationContainer\"]/div[1]/div/div/button[1]");
	private By passlistButton =By.xpath("//*[@role='tablist']/button[6]");
	private By passlistrows =By.xpath("//*[@aria-label='sticky table']/tbody/tr");
	//*[@id="mui-p-61173-P-passlist"]/div/div/table"//*[@aria-label='sticky table']/tbody/tr""//*[@id=\"mui-p-73493-P-passlist_records\"]/div/div/table/tbody/tr"
	private By passlistrecord=By.xpath("//*[@aria-label='sticky table']/tbody/tr");
	//*[@id="mui-p-61173-P-passlist_records"]/div/div/table
	private By scroll=By.xpath("//*[@role='tabpanel']/div/div/div/div[4]/span[2]");
	private By description =By.xpath("//*[@label='description']");
	private By submit=By.xpath("//button[@id='btn-submit']");
	private By approve=By.xpath("//*[@type='success']");
	//*[@id="approve-reject-internal-list-entries"]/div[2]/div[2]/button[2]
	private By textArea=By.xpath("//textarea[@id='outlined-multiline-flexible']");
	private By actionButton=By.xpath("//button[@id='action-button'and @type='success']");
	
	
	public task_Screen_Approval(WebDriver driver) {
		this.driver = driver;
	}


	public String getPassrecordStatus(String passname, String listname) throws InterruptedException {
		String status="";
		System.out.println("Inside getTasksStatus");
		Actions action = new Actions(driver);
		List<WebElement> rows = driver.findElements(passlistrecord);
		
		int size=rows.size();
		System.out.println("Outside for loop of getTasksStatus- size "+ size);
		for(int i=1;i<=size;i++)
		{//*[@id="mui-p-61939-P-passlist_records"]/div/div/table/tbody/tr[1]/td[3]/span/div
			String name =driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[3]/span/div")).getText();
			String lname =driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[4]/span/div")).getText();
			
			System.out.println("Outside if condition of getTasksStatus- name "+ name +"Listname :"+ lname);
			if(name.contains(passname) && lname.contains(listname)) {
				System.out.println("Inside if condition of getTasksStatus" + i);
				 synchronized (driver)
					{
					    driver.wait(4000);
					}
				 status =driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[7]/span")).getText();
				 action.sendKeys(Keys.PAGE_DOWN).build().perform();
				 action.sendKeys(Keys.PAGE_DOWN).build().perform();
				 driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[1]/div/span[2]/div")).click();
				 //xpathofl= "//*[@aria-label='sticky table']/tbody/tr['+i+']/td[1]/div/span[2]/div";
				//*[@id="mui-p-95359-P-passlist_records"]/div/div/table/tbody/tr[1]/td[1]/div/span[2]/div
				 break;
			}
			}
		return status;
		
	}
	//*[@id="mui-p-11055-P-passlist_records"]/div/div/table/tbody/tr[1]/td[7]/span

	
	public String searchPasslistrecordPending1(String passname,String listname) throws InterruptedException{
		synchronized (driver)
		{
		    driver.wait(5000);
		}
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(approve)).click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(textArea)).sendKeys("Approved L1");
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(actionButton)).click();
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		status2=getPassrecordStatus(passname, listname);
		
return status2;

}
public String searchPasslistrecordPending2(String passname, String lstname) throws InterruptedException{
	synchronized (driver)
	{
	    driver.wait(5000);
	}
		new WebDriverWait(driver,  Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(approve)).click();
		new WebDriverWait(driver, Duration.ofSeconds(45)).until(ExpectedConditions.elementToBeClickable(textArea)).sendKeys("Approved L2");
		new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(actionButton)).click();
		status3=getPassrecordStatus(passname, lstname);
return status3;

}
}
