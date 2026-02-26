package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LsegStandardPage {

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

	private By Watchlistmenu = By.xpath("//span[text()='Watchlist']");
	private By Commerciallist = By.xpath("(//span[text()='Commercial list'])[1]");
	private By Commerciallistt = By.xpath("(//div[text()='Commercial list'])[2]");

	private By Addsource = By.xpath("//button[@aria-label='ADD SOURCE']");
	private By Selectprovider = By.xpath("(//div[@role='combobox'])[1]");
	private By Lseg = By.xpath("//li[text()='LSEG']");
	private By Filedetails = By.xpath("(//div[@role='combobox'])[2]");
	private By LsegStandard = By.xpath("//li[@data-value='44']");
	private By Path = By.name("filePath");
	private By Username1 = By.name("username");
	private By Password1 = By.name("password");
	private By Passwordicon = By.xpath("//div[@class='password-icon']");
	private By Contractnumber = By.name("contractNumber");
	private By Contractstartdate = By.name("contractStartDate");
	private By Contractenddate = By.name("contractEndDate");
	private By Timezone = By.xpath("(//div[@role='combobox'])[3]");
	private By Utc = By.xpath("//li[@data-value='UTC']");
	private By Dayweek = By.xpath("(//div[@role='combobox'])[4]");
	private By Allday = By.xpath("(//li[@role='option'])[1]");
	private By Time = By.xpath("//input[@placeholder='hh:mm']");
	private By Preprevious = By.xpath("(//input[@name='Previous run'])[1]");
	private By Preweekly = By.xpath("(//input[@name='Weekly'])[1]");
	private By Premonthly = By.xpath("(//input[@name='Monthly'])[1]");
	private By Precheck = By.xpath("(//input[@type='checkbox'])[1]");
	private By Postprevious = By.xpath("(//input[@name='Previous run'])[2]");
	private By Postweekly = By.xpath("(//input[@name='Weekly'])[2]");
	private By Postmonthly = By.xpath("(//input[@name='Monthly'])[2]");
	private By Postcheck = By.xpath("(//input[@type='checkbox'])[2]");
	private By Submitapprove = By.xpath("//button[text()='SUBMIT FOR APPROVAL']");
	private By Comment = By.xpath("//textarea[@placeholder='Comments']");
	private By Submit = By.xpath("//button[text()='SUBMIT']");

	WebDriver driver;

	public LsegStandardPage(WebDriver driver) {
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

	// NAVIGATING TO COMMERCIAL LIST
	public void navigate_to_commercial_list() throws InterruptedException {
		driver.findElement(Watchlistmenu).click();
		Thread.sleep(2000);
		driver.findElement(Commerciallist).click();
		Thread.sleep(2000);

		boolean logo5 = driver.findElement(Commerciallistt).isDisplayed();
		if (logo5 == true) {
			System.out.println("Commercial List page is displayed");
		} else {
			System.out.println("Commercial List page is not displayed");
		}
	}

	// LSEG STANDARD CONFIGURATION
	public void creation_of_lseg_standard(String path, String usnm1, String pswd1, String cnumber, 
			String cstartdate, String cenddate, String time, String prepre, String preweek, 
			String premonth, String postpre, String postweek, String postmonth, String cmt) throws InterruptedException {
		
		driver.findElement(Addsource).click();
		Thread.sleep(2000);
		driver.findElement(Selectprovider).click();
		driver.findElement(Lseg).click();
		driver.findElement(Filedetails).click();
		driver.findElement(LsegStandard).click();
		driver.findElement(Path).sendKeys(path);
		driver.findElement(Username1).sendKeys(usnm1);
		driver.findElement(Password1).sendKeys(pswd1);
		driver.findElement(Passwordicon).click();
		driver.findElement(Contractnumber).sendKeys(cnumber);
		driver.findElement(Contractstartdate).click();
		driver.findElement(Contractstartdate).sendKeys(cstartdate);
		driver.findElement(Contractenddate).click();
		driver.findElement(Contractenddate).sendKeys(cenddate);
		driver.findElement(Timezone).click();
		driver.findElement(Utc).click();
		driver.findElement(Dayweek).click();
		driver.findElement(Allday).click();

		Actions a = new Actions(driver);
		WebElement w = driver.findElement(By.xpath("//span[@class='file-contraints']"));
		a.doubleClick(w).perform();

		driver.findElement(Time).click();
		driver.findElement(Time).sendKeys(time);
		driver.findElement(Preprevious).sendKeys(prepre);
		driver.findElement(Preweekly).sendKeys(preweek);
		driver.findElement(Premonthly).sendKeys(premonth);
		driver.findElement(Precheck).click();
		driver.findElement(Postprevious).sendKeys(postpre);
		driver.findElement(Postweekly).sendKeys(postweek);
		driver.findElement(Postmonthly).sendKeys(postmonth);
		driver.findElement(Postcheck).click();
		driver.findElement(Submitapprove).click();
		driver.findElement(Comment).sendKeys(cmt);
		driver.findElement(Submit).click();
		Thread.sleep(2000);
		System.out.print("LSEG STANDARD LIST CREATED");
		driver.quit();
	}
}
