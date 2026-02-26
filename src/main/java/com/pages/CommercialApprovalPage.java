package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CommercialApprovalPage {

	private By Login = By.xpath("//button[@aria-label='LOG IN']");
	private By Orgname = By.id("organizationName");
	private By Continue1 = By.xpath("//button[text()='CONTINUE']");
	private By Username = By.id("username");
	private By Password = By.id("password");
	private By Forgotpass = By.xpath("//a[text()='Forgot password?']");
	private By Continue2 = By.xpath("//button[text()='Continue']");
	private By Dashboardpage = By.xpath("//p[text() = 'UK FCA to continue focus on sanctions systems and controls in 2024/2025']");
	private By Facctlistarrow = By.xpath("(//div[@class=\"facct-card product-card \"])[1]");
	private By Facctlistdashboard = By.xpath("//div[text()='Hello,']");

	private By Tasks = By.xpath("//span[text()='Tasks']");
	private By Listconfiguration = By.xpath("(//button[@role ='tab'])[7]");

	private By List = By.xpath("(//div[@class='link-cell list-name-width'])[1]");
	private By Claim = By.xpath("//button[text()='CLAIM']");
	private By Approve = By.xpath("(//button[@aria-label = 'APPROVE'])[1]");
	private By Comments = By.xpath("//textarea[@placeholder='Comments']");
	private By Approve1 = By.xpath("(//button[@aria-label = 'APPROVE'])[2]");

	WebDriver driver;

	public CommercialApprovalPage(WebDriver driver) {
		this.driver = driver;
	}

	// LOGIN TO FACCTLIST
	public void login_to_facctlist(String url, String ornm, String usnm, String pswd) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);

		boolean logo1 = driver.findElement(By.xpath("//h1[text() = 'Facctum AML Platform']")).isDisplayed();
		if (logo1 == true) {
			System.out.println("Welcome page is displayed");
		} else {
			System.out.println("Welcome is not displayed");
		}

		driver.findElement(Login).click();
		driver.findElement(Orgname).sendKeys(ornm);
		driver.findElement(Continue1).click();
		Thread.sleep(2000);

		boolean logo2 = driver.findElement(By.xpath("//h1[text()='Welcome']")).isDisplayed();
		if (logo2 == true) {
			System.out.println("Login page is displayed");
		} else {
			System.out.println("Login is not displayed");
		}

		driver.findElement(Username).sendKeys(usnm);
		driver.findElement(Password).sendKeys(pswd);
		boolean link = driver.findElement(Forgotpass).isDisplayed();
		if (link == true) {
			System.out.println("Forgot password link is present");
		}
		driver.findElement(Continue2).click();
		Thread.sleep(2000);

		boolean logo3 = driver.findElement(Dashboardpage).isDisplayed();
		if (logo3 == true) {
			System.out.println("Dashboard page is displayed");
			Thread.sleep(2000);
		} else {
			System.out.println("Dashboard page is not displayed");
		}

		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		driver.findElement(Facctlistarrow).click();
		Thread.sleep(2000);

		boolean logo4 = driver.findElement(Facctlistdashboard).isDisplayed();
		if (logo4 == true) {
			System.out.println("Facctlist Dashboard page is displayed");
		} else {
			System.out.println("Facctlist Dashboard page is not displayed");
		}
	}

	// NAVIGATING TO TASK PAGE
	public void navigate_to_task_page() throws InterruptedException {
		driver.findElement(Tasks).click();
		Thread.sleep(2000);
	}

	// APPROVAL OF COMMERCIAL LIST
	public void approval_of_commercial_list() throws InterruptedException {
		driver.findElement(Listconfiguration).click();
		Thread.sleep(2000);
		driver.findElement(List).click();
		Thread.sleep(2000);
		driver.findElement(Claim).click();
		Thread.sleep(2000);
		driver.findElement(Approve).click();
		driver.findElement(Comments).sendKeys("List Approved");
		Thread.sleep(2000);
		driver.findElement(Approve1).click();
	}
}
