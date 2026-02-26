package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BarclaysIblPage {

	private By Welcomepage = By.xpath("//div[text()='Welcome']");
	private By Login = By.xpath("//button[@aria-label='LOG IN']");
	private By Orgpage = By.xpath("//h1[text()='Welcome']");
	private By Orgname = By.id("organizationName");
	private By Continue1 = By.xpath("//button[text()='CONTINUE']");
	private By Loginpage = By.xpath("//h1[text()='Welcome']");
	private By Username = By.id("username");
	private By Password = By.id("password");
	private By Forgotpass = By.xpath("//a[text()='Forgot password?']");
	private By Continue2 = By.xpath("//button[text()='Continue']");
	private By Facctlistarrow = By.xpath("(//div[@class='product-card '])");
	private By Facctlistdashboard = By.xpath("//div[text()='Dashboard']");

	private By Watchlistmenu = By.xpath("(//span[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig'])[4]");
	private By Internallist = By.xpath("(//span[text()='Internal list'])");

	private By Listname = By.xpath("//div[text()='Clone  GPLD 822']");
	private By Addrecord = By.xpath("//button[@aria-label='ADD RECORDS']");
	private By Singlerecord = By.xpath("//li[text()='Single record']");
	private By Entitytype = By.xpath("(//div[@aria-haspopup='listbox'])[1]");
	private By SelectEntity = By.xpath("//li[@data-value='1']");
	private By Customertype = By.xpath("(//div[@aria-haspopup='listbox'])[2]");
	private By SelectCustomer = By.xpath("//span[text()='Barclays Customer']");
	private By Title = By.xpath("(//div[@aria-haspopup='listbox'])[3]");
	private By Selecttitle = By.xpath("//span[text()='Duke']");

	private By Pfirstname = By.xpath("(//input[@name='firstName'])[1]");
	private By Pmiddlename1 = By.xpath("(//input[@name='middleName1'])[1]");
	private By Pmiddlename2 = By.xpath("(//input[@name='middleName2'])[1]");
	private By Pmiddlename3 = By.xpath("(//input[@name='middleName3'])[1]");
	private By Pmiddlename4 = By.xpath("(//input[@name='middleName4'])[1]");
	private By Pmiddlename5 = By.xpath("(//input[@name='middleName5'])[1]");
	private By Plastname = By.xpath("(//input[@name='lastName'])[1]");

	private By plusicon1 = By.xpath("(//button[@aria-label='button'])[3]");
	private By nametype = By.xpath("(//div[@aria-haspopup='listbox'])[5]");
	private By Alias = By.xpath("//span[text()='Alias']");

	private By Afirstname = By.xpath("(//input[@name='firstName'])[2]");
	private By Amiddlename1 = By.xpath("(//input[@name='middleName1'])[2]");
	private By Amiddlename2 = By.xpath("(//input[@name='middleName2'])[2]");
	private By Amiddlename3 = By.xpath("(//input[@name='middleName3'])[2]");
	private By Amiddlename4 = By.xpath("(//input[@name='middleName4'])[2]");
	private By Amiddlename5 = By.xpath("(//input[@name='middleName5'])[2]");
	private By Alastname = By.xpath("(//input[@name='lastName'])[2]");

	private By Job = By.xpath("//div[@class=\"facct-form-section jobTitle\"]//input");
	private By Position = By.xpath("(//div[@class=\"facct-form-section position\"]//input)[1]");
	private By Gender = By.xpath("(//input[@id=\"1\"])[1]");
	private By Deceased = By.xpath("(//input[@id=\"1\"])[2]");

	private By Dob = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[19]");
	private By plusicon2 = By.xpath("(//button[@aria-label='button'])[8]");
	private By datetype1 = By.xpath("(//div[@aria-haspopup='listbox'])[7]");
	private By Dod = By.xpath("//span[text()='Date of Deceased']");
	private By Dodvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[20]");
	private By plusicon3 = By.xpath("(//button[@aria-label='button'])[9]");
	private By datetype2 = By.xpath("(//div[@aria-haspopup='listbox'])[8]");
	private By Adob = By.xpath("//span[text()='Additional Date of Birth']");
	private By Adobvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[21]");

	private By Birthtown = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[22]");
	private By Birthcountry = By.xpath("(//div[@aria-haspopup='listbox'])[9]");
	private By Birthcountryvalue = By.xpath("//span[text()='Abkhazia']");

	private By dropdown1 = By.xpath("(//div[@aria-haspopup='listbox'])[10]");
	private By Accountno = By.xpath("//span[text()='Account number']");
	private By Accountnovalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[23]");
	private By plusicon4 = By.xpath("(//button[@aria-label='button'])[14]");
	private By dropdown2 = By.xpath("(//div[@aria-haspopup='listbox'])[11]");
	private By Customerid = By.xpath("//span[text()='Customer id']");
	private By Customeridvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[24]");
	private By Plusicon5 = By.xpath("(//button[@aria-label='button'])[15]");
	private By dropdown3 = By.xpath("(//div[@aria-haspopup='listbox'])[12]");
	private By Governmentid = By.xpath("//span[text()='Government issued id']");
	private By Governmanetidvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[25]");
	private By plusicon6 = By.xpath("(//button[@aria-label='button'])[16]");
	private By dropdown4 = By.xpath("(//div[@aria-haspopup='listbox'])[13]");
	private By Passport = By.xpath("//span[text()='Passport']");
	private By Passportvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[26]");
	private By plusicon7 = By.xpath("(//button[@aria-label='button'])[17]");
	private By dropdown5 = By.xpath("(//div[@aria-haspopup='listbox'])[14]");
	private By Relatedid = By.xpath("//span[text()='Related id']");
	private By Relatedidvalue = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[27]");

	private By dropdown6 = By.xpath("(//div[@aria-haspopup='listbox'])[15]");
	private By Address = By.xpath("//span[text()='Address']");
	private By Address1 = By.xpath("(//input[@name='addressLine1'])[1]");
	private By Address2 = By.xpath("(//input[@name='addressLine2'])[1]");
	private By City = By.xpath("(//input[@name='city'])[1]");
	private By State = By.xpath("(//input[@name='county'])[1]");
	private By Zipcode = By.xpath("(//input[@name='zipCode'])[1]");
	private By dropdown7 = By.xpath("(//div[@aria-haspopup='listbox'])[16]");
	private By Addcountry = By.xpath("//span[text()='Angola']");

	private By Worldcheckid = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[33]");
	private By Location = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[34]");

	private By Additionaldetails = By.xpath("//button[@aria-label='Additional details']");
	private By dropdown8 = By.xpath("(//div[@aria-haspopup='listbox'])[2]");
	private By Businessunit = By.xpath("//span[text()='BUK']");
	private By dropdown9 = By.xpath("(//div[@aria-haspopup='listbox'])[3]");
	private By Subbusinessunit = By.xpath("//span[text()='Business Banking (BB)']");
	private By Bucontact = By.xpath("(//div[@style=\"pointer-events: all; user-select: text;\"]//input)[1]");

	private By Reviewperiod = By.xpath("(//div[@aria-haspopup='listbox'])[4]");
	private By Periodvalue = By.xpath("//span[text()='Monthly (30 Days)']");
	private By Expirydate = By.xpath("//input[@placeholder='YYYY/MM/DD']");

	private By Reason = By.xpath("//textarea[@placeholder='Reason']");
	private By Comments = By.xpath("//textarea[@placeholder='Comments']");
	private By Furthercomments = By.xpath("//textarea[@placeholder='Further comments']");
	private By Referenceinfo = By.xpath("//textarea[@placeholder='Reference information']");

	private By Submitforapproval = By.xpath("//button[@aria-label='SUBMIT FOR APPROVAL']");
	private By SubmitComment = By.xpath("(//textarea[@placeholder='Comments'])[2]");
	private By Submit = By.xpath("//button[@aria-label='Submit']");

	private By Kebabmenu1 = By.xpath("(//tbody[@class='MuiTableBody-root css-1xnox0e']//div)[144]");
	private By Withdraw = By.xpath("//li[text()='Withdraw']");
	private By Confirmwithdraw = By.xpath("//button[@aria-label='Withdraw']");

	private By Rejecttab = By.id("simple-tab-3");
	private By Kebabmenu2 = By.xpath("(//tbody[@class='MuiTableBody-root css-1xnox0e']//div)[48]");
	private By Edit = By.xpath("//li[text()='Edit']");
	private By Selectcustomer1 = By.xpath("//span[text()='Non Barclays Customer - Related Party']");
	private By Selecttitle1 = By.xpath("//span[text()='Sir']");

	private By Gender1 = By.xpath("(//input[@id=\"3\"])[1]");
	private By Businessunit1 = By.xpath("//span[text()='BX']");
	private By Subbusinessunit1 = By.xpath("//span[text()='Financial Crime Advisory']");
	private By Periodvalue1 = By.xpath("//span[text()='Annual (365 Days)']");
	private By Expiryclear = By.xpath("//button[@title='Clear']");
	private By Expirydate1 = By.xpath("//input[@placeholder='YYYY/MM/DD']");

	WebDriver driver;

	public BarclaysIblPage(WebDriver driver) {
		this.driver = driver;
	}


	// LOGIN TO FACCTLIST
	public void login_to_facctlist_app(String url, String ornm, String usnm, String pswd) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);

		boolean logo1 = driver.findElement(Welcomepage).isDisplayed();
		if (logo1 == true) {
			System.out.println("Welcome page is displayed");
		} else {
			System.out.println("Welcome is not displayed");
		}

		driver.findElement(Login).click();

		boolean logo2 = driver.findElement(Orgpage).isDisplayed();
		if (logo1 == true) {
			System.out.println("Organisation page is displayed");
		} else {
			System.out.println("Organisation Page is not displayed");
		}

		driver.findElement(Orgname).sendKeys(ornm);
		driver.findElement(Continue1).click();
		Thread.sleep(2000);

		boolean logo3 = driver.findElement(Loginpage).isDisplayed();
		if (logo3 == true) {
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
		Thread.sleep(5000);

		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		driver.findElement(Facctlistarrow).click();
		Thread.sleep(5000);

		boolean logo5 = driver.findElement(Facctlistdashboard).isDisplayed();
		if (logo5 == true) {
			System.out.println("Facctlist Dashboard page is displayed");
		} else {
			System.out.println("Facctlist Dashboard page is not displayed");
		}
	}

	// NAVIGATE TO INTERNAL LIST
	public void navigate_to_internal_list() throws InterruptedException {
		driver.findElement(Watchlistmenu).click();
		Thread.sleep(2000);
		driver.findElement(Internallist).click();
		Thread.sleep(5000);

		boolean logo5 = driver.findElement(Internallist).isDisplayed();
		if (logo5 == true) {
			System.out.println("Internal List page is displayed");
		} else {
			System.out.println("Internal List page is not displayed");
		}
	}

	public void select_list_name() throws InterruptedException {
		driver.findElement(Listname).click();
		Thread.sleep(6000);
	}

	public void add_single_record() {
		driver.findElement(Addrecord).click();
	}

	public void select_single_record() throws InterruptedException {
		driver.findElement(Singlerecord).click();
		Thread.sleep(3000);
	}

	public void select_entity_type() {
		driver.findElement(Entitytype).click();
		driver.findElement(SelectEntity).click();
	}


	// BASIC DETAILS
	public void add_all_basic_details(String pfirstname, String pmiddlename1, String pmiddlename2, 
			String pmiddlename3, String pmiddlename4, String pmiddlename5, String plastname,
			String afirstname, String amiddlename1, String amiddlename2, String amiddlename3, 
			String amiddlename4, String amiddlename5, String alastname,
			String job, String position, String gender, String deceased, String dob, String dod, 
			String adob, String birthtown,
			String accountno, String customerid, String governmentid, String passport, String relatedid,
			String add1, String add2, String city, String state, String zipcode,
			String wcid, String loc) {

		driver.findElement(Customertype).click();
		driver.findElement(SelectCustomer).click();
		driver.findElement(Title).click();
		driver.findElement(Selecttitle).click();

		driver.findElement(Pfirstname).click();
		driver.findElement(Pfirstname).sendKeys(pfirstname);
		driver.findElement(Pmiddlename1).click();
		driver.findElement(Pmiddlename1).sendKeys(pmiddlename1);
		driver.findElement(Pmiddlename2).click();
		driver.findElement(Pmiddlename2).sendKeys(pmiddlename2);
		driver.findElement(Pmiddlename3).click();
		driver.findElement(Pmiddlename3).sendKeys(pmiddlename3);
		driver.findElement(Pmiddlename4).click();
		driver.findElement(Pmiddlename4).sendKeys(pmiddlename4);
		driver.findElement(Pmiddlename5).click();
		driver.findElement(Pmiddlename5).sendKeys(pmiddlename5);
		driver.findElement(Plastname).click();
		driver.findElement(Plastname).sendKeys(plastname);

		driver.findElement(plusicon1).click();
		driver.findElement(nametype).click();
		driver.findElement(Alias).click();

		driver.findElement(Afirstname).click();
		driver.findElement(Afirstname).sendKeys(afirstname);
		driver.findElement(Amiddlename1).click();
		driver.findElement(Amiddlename1).sendKeys(amiddlename1);
		driver.findElement(Amiddlename2).click();
		driver.findElement(Amiddlename2).sendKeys(amiddlename2);
		driver.findElement(Amiddlename3).click();
		driver.findElement(Amiddlename3).sendKeys(amiddlename3);
		driver.findElement(Amiddlename4).click();
		driver.findElement(Amiddlename4).sendKeys(amiddlename4);
		driver.findElement(Amiddlename5).click();
		driver.findElement(Amiddlename5).sendKeys(amiddlename5);
		driver.findElement(Alastname).click();
		driver.findElement(Alastname).sendKeys(alastname);

		driver.findElement(Job).click();
		driver.findElement(Job).sendKeys(job);
		driver.findElement(Position).click();
		driver.findElement(Position).sendKeys(position);
		driver.findElement(Gender).click();
		driver.findElement(Gender).sendKeys(gender);
		driver.findElement(Deceased).click();
		driver.findElement(Deceased).sendKeys(deceased);

		driver.findElement(Dob).click();
		driver.findElement(Dob).sendKeys(dob);
		driver.findElement(plusicon2).click();
		driver.findElement(datetype1).click();
		driver.findElement(Dod).click();
		driver.findElement(Dodvalue).click();
		driver.findElement(Dodvalue).sendKeys(dod);
		driver.findElement(plusicon3).click();
		driver.findElement(datetype2).click();
		driver.findElement(Adob).click();
		driver.findElement(Adobvalue).click();
		driver.findElement(Adobvalue).sendKeys(adob);

		driver.findElement(Birthtown).click();
		driver.findElement(Birthtown).sendKeys(birthtown);
		driver.findElement(Birthcountry).click();
		driver.findElement(Birthcountryvalue).click();

		driver.findElement(dropdown1).click();
		driver.findElement(Accountno).click();
		driver.findElement(Accountnovalue).click();
		driver.findElement(Accountnovalue).sendKeys(accountno);
		driver.findElement(plusicon4).click();
		driver.findElement(dropdown2).click();
		driver.findElement(Customerid).click();
		driver.findElement(Customeridvalue).click();
		driver.findElement(Customeridvalue).sendKeys(customerid);
		driver.findElement(Plusicon5).click();
		driver.findElement(dropdown3).click();
		driver.findElement(Governmentid).click();
		driver.findElement(Governmanetidvalue).click();
		driver.findElement(Governmanetidvalue).sendKeys(governmentid);
		driver.findElement(plusicon6).click();
		driver.findElement(dropdown4).click();
		driver.findElement(Passport).click();
		driver.findElement(Passportvalue).click();
		driver.findElement(Passportvalue).sendKeys(passport);
		driver.findElement(plusicon7).click();
		driver.findElement(dropdown5).click();
		driver.findElement(Relatedid).click();
		driver.findElement(Relatedidvalue).click();
		driver.findElement(Relatedidvalue).sendKeys(relatedid);

		driver.findElement(dropdown6).click();
		driver.findElement(Address).click();
		driver.findElement(Address1).click();
		driver.findElement(Address1).sendKeys(add1);
		driver.findElement(Address2).click();
		driver.findElement(Address2).sendKeys(add2);
		driver.findElement(City).click();
		driver.findElement(City).sendKeys(city);
		driver.findElement(State).click();
		driver.findElement(State).sendKeys(state);
		driver.findElement(Zipcode).click();
		driver.findElement(Zipcode).sendKeys(zipcode);
		driver.findElement(dropdown7).click();
		driver.findElement(Addcountry).click();

		driver.findElement(Worldcheckid).click();
		driver.findElement(Worldcheckid).sendKeys(wcid);
		driver.findElement(Location).click();
		driver.findElement(Location).sendKeys(loc);
	}


	// ADDITIONAL DETAILS
	public void add_all_additional_details(String bucontact, String expiryvalue,
			String reason, String comments, String furthercomm, String refinfo) {

		driver.findElement(Additionaldetails).click();
		driver.findElement(dropdown8).click();
		driver.findElement(Businessunit).click();
		driver.findElement(dropdown9).click();
		driver.findElement(Subbusinessunit).click();
		driver.findElement(Bucontact).click();
		driver.findElement(Bucontact).sendKeys(bucontact);

		driver.findElement(Reviewperiod).click();
		driver.findElement(Periodvalue).click();
		driver.findElement(Expirydate).click();
		driver.findElement(Expirydate).sendKeys(expiryvalue);

		driver.findElement(Reason).sendKeys(reason);
		driver.findElement(Comments).sendKeys(comments);
		driver.findElement(Furthercomments).sendKeys(furthercomm);
		driver.findElement(Referenceinfo).sendKeys(refinfo);
	}

	// SUBMIT FOR APPROVAL
	public void click_on_submit_for_approval(String submitcomment) throws InterruptedException {
		driver.findElement(Submitforapproval).click();
		driver.findElement(SubmitComment).click();
		driver.findElement(SubmitComment).sendKeys(submitcomment);
		driver.findElement(Submit).click();
		Thread.sleep(6000);
	}

	// WITHDRAW OF THE RECORD
	public void withdraw_the_record() throws InterruptedException {
		driver.findElement(Kebabmenu1).click();
		driver.findElement(Withdraw).click();
		driver.findElement(Confirmwithdraw).click();
		Thread.sleep(4000);
	}

	// AMENDMENT OF WITHDRAWN RECORD
	public void navigate_to_reject_tab() throws InterruptedException {
		driver.findElement(Rejecttab).click();
		Thread.sleep(5000);
	}

	public void select_the_record() throws InterruptedException {
		driver.findElement(Kebabmenu2).click();
		driver.findElement(Edit).click();
		Thread.sleep(5000);
	}

	public void amend_the_record(String pfirstname1, String plastname1, String expiryvalue, String comments1) {
		driver.findElement(Customertype).click();
		driver.findElement(Selectcustomer1).click();
		driver.findElement(Title).click();
		driver.findElement(Selecttitle1).click();

		WebElement fstnm = driver.findElement(Pfirstname);
		fstnm.click();
		Actions fa = new Actions(driver);
		fa.click(fstnm).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
		driver.findElement(Pfirstname).click();
		driver.findElement(Pfirstname).sendKeys(pfirstname1);

		WebElement lstnm = driver.findElement(Plastname);
		lstnm.click();
		Actions la = new Actions(driver);
		la.click(lstnm).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
		driver.findElement(Plastname).click();
		driver.findElement(Plastname).sendKeys(plastname1);

		driver.findElement(Gender1).click();

		driver.findElement(Additionaldetails).click();
		driver.findElement(dropdown8).click();
		driver.findElement(Businessunit1).click();
		driver.findElement(dropdown9).click();
		driver.findElement(Subbusinessunit1).click();

		driver.findElement(Reviewperiod).click();
		driver.findElement(Periodvalue1).click();
		driver.findElement(Expiryclear).click();
		driver.findElement(Expirydate1).sendKeys(expiryvalue);

		WebElement comts = driver.findElement(Comments);
		comts.click();
		Actions cm = new Actions(driver);
		cm.click(comts).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
		driver.findElement(Comments).click();
		driver.findElement(Comments).sendKeys(comments1);
	}

	public void submit_the_record(String submitcomment) throws InterruptedException {
		driver.findElement(Submitforapproval).click();
		driver.findElement(SubmitComment).click();
		driver.findElement(SubmitComment).sendKeys(submitcomment);
		driver.findElement(Submit).click();
		Thread.sleep(6000);
	}
}
