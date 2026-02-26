package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegadvfilterPage {

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

	private By Watchlistmenu = By.xpath("(//span[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig'])[5]");
	private By Regulatorylist = By.xpath("(//span[text()='Regulatory list'])");

	private By Pagination = By.xpath("//button[@id='basic-button']");
	private By Pagevalue = By.xpath("//li[text()='100']");
	private By Ofac = By.xpath("//div[text()='OFAC Enhanced']");
	private By Uksanctions = By.xpath("//div[text()='UK SANCTIONS']");
	private By Un = By.xpath("//div[text()='UN']");
	private By Eu = By.xpath("//div[text()='EU']");

	private By Active = By.xpath("(//button[@id='simple-tab-0'])[2]");
	private By Filter = By.xpath("(//button[@id='record-table-filter-btn'])");

	// OFAC
	private By AddressCountry = By.xpath("(//span[text()='Address country'])[1]");
	private By AddressSearch = By.xpath("(//input[@placeholder='Search address country'])");
	private By Addresfilter = By.xpath("//input[@id='advance-filter-list-select-all-addressDetailsList.countryName']");
	private By Citizencountry = By.xpath("(//span[text()='Citizenship country'])[1]");
	private By CitizenSearch = By.xpath("(//input[@placeholder='Search citizenship country'])");
	private By Citizenfilter = By.xpath("//input[@id='advance-filter-list-select-all-citizenshipDetailsList.countryName']");
	private By Lastupdate = By.xpath("(//span[text()='Last Updated Date'])[1]");
	private By Startdate = By.xpath("(//input[@name='startDate'])");
	private By Enddate = By.xpath("(//input[@name='endDate'])");
	private By Nationalcountry = By.xpath("(//span[text()='Nationality country'])[1]");
	private By NationalSearch = By.xpath("(//input[@placeholder='Search nationality country'])");
	private By Nationalfilter = By.xpath("//input[@id='advance-filter-list-select-all-nationalityDetailsList.countryName']");
	private By Programname = By.xpath("(//span[text()='Program name'])[1]");
	private By ProgramSearch = By.xpath("(//input[@placeholder='Search program name'])");
	private By Programfilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programName']");
	private By Type = By.xpath("(//span[text()='Type'])[1]");
	private By TypeSearch = By.xpath("(//input[@placeholder='Search type'])");
	private By Typefilter = By.xpath("//input[@id='advance-filter-list-select-all-entityTypeName']");

	private By Selectall = By.xpath("//label[text()='Select all']");
	private By Initiated = By.xpath("//div[text()='Request initiated. Check status at Downloads tab']");
	private By Notinitiated = By.xpath("//div[text()='Request in progress. Check the Downloads tab for status']");
	private By Clearfilter = By.xpath("//button[text()='CLEAR FILTERS']");

	// UK SANCTIONS
	private By Designateddate = By.xpath("(//span[text()='Designated date'])[1]");
	private By Designatedate = By.xpath("(//input[@placeholder='DD/MM/YYYY'])[1]");
	private By Idtype = By.xpath("(//span[text()='Id Type'])[1]");
	private By Idtypesearch = By.xpath("(//input[@placeholder='Search id type'])");
	private By Idtypefilter = By.xpath("//input[@id='advance-filter-list-select-all-idNumberTypesList.idType']");
	private By Programsource = By.xpath("(//span[text()='Program Source'])[1]");
	private By Programsourcesearch = By.xpath("(//input[@placeholder='Search program source'])");
	private By Programsourcefilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programSource']");
	private By Regimename = By.xpath("(//span[text()='Regime Name'])[1]");
	private By Regimenamesearch = By.xpath("(//input[@placeholder='Search regime name'])");
	private By Regimenamefilter = By.xpath("//input[@id='advance-filter-list-select-all-sanctionProgramDetailsList.programName']");

	// UN
	private By Address1 = By.xpath("(//span[text()='Address country'])[1]");
	private By Designatedate1 = By.xpath("(//span[text()='Designated date'])[1]");
	private By Entity = By.xpath("(//span[text()='Entity Type'])[1]");
	private By Entitysearch = By.xpath("(//input[@placeholder='Search entity type'])");
	private By Entityfilter = By.xpath("//input[@id='advance-filter-list-select-all-entityTypeName']");
	private By Lastupdate1 = By.xpath("(//span[text()='Last updated date'])[1]");
	private By Startdate1 = By.xpath("(//input[@name='startDate'])");
	private By Enddate1 = By.xpath("(//input[@name='endDate'])");
	private By Programname1 = By.xpath("(//span[text()='Program Name'])[1]");

	private By Apply = By.xpath("(//button[@id='advance-filter-apply-btn'])");
	private By Downloadicon = By.xpath("(//button[@id='regulatory-records-download-btn'])");
	private By Excel = By.xpath("(//span[text()='Excel (.xlsx)'])");
	private By Tab = By.xpath("(//li[text()='Tab separated (.tsv)'])");

	private By Error = By.xpath("(//button[@id='simple-tab-1'])[2]");
	private By Delete = By.xpath("//button[@id='simple-tab-2']");
	private By Delta = By.xpath("(//span[@class='MuiTouchRipple-root css-w0pj6f'])[19]");
	private By New = By.xpath("(//button[@id='simple-tab-0'])[2]");
	private By Amend = By.xpath("(//button[@id='simple-tab-1'])[2]");
	private By DDelete = By.xpath("//button[@id='simple-tab-2']");
	private By Stable = By.xpath("//button[@id='simple-tab-3']");
	private By DError = By.xpath("//button[@id='simple-tab-4']");

	private By Downloads = By.xpath("//button[@id='simple-tab-1']");
	private By Success = By.xpath("(//div[text()='Success'])[1]");
	private By Download = By.xpath("(//button[@tabindex='0'])[9]");
	private By Download1 = By.xpath("(//button[@tabindex='0'])[10]");
	private By Status = By.xpath("//div[text()='Download started.']");
	private By Refresh = By.xpath("(//button[@tabindex='0'])[12]");

	WebDriver driver;

	public RegadvfilterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void facctlist_login(String url, String ornm, String usnm, String pswd) throws InterruptedException {
		driver.get(url);Thread.sleep(2000);

		boolean logo1 = driver.findElement(Welcomepage).isDisplayed();
		if (logo1 == true) {
			System.out.println("Welcome page is displayed");
		} else {
			System.out.println("Welcome is not displayed");
		}

		driver.findElement(Login).click();

		boolean logo2 = driver.findElement(Orgpage).isDisplayed();
		if (logo2 == true) {
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

	public void navigate_to_regulatory_list() throws InterruptedException {
		driver.findElement(Watchlistmenu).click();Thread.sleep(4000);
		driver.findElement(Regulatorylist).click();Thread.sleep(3000);
	}

	public void select_list_name() throws InterruptedException {
		driver.findElement(Pagination).click();Thread.sleep(2000);
		driver.findElement(Pagevalue).click();Thread.sleep(2000);
		driver.findElement(Ofac).click();Thread.sleep(2000);
	}

	public void select_tab() {
		driver.findElement(Active).click();
		driver.findElement(Filter).click();
	}


	public void apply_filter(String address, String citizenship, String startdate, String enddate, 
			String nationality, String program, String type,
			String designateddate, String idtype, String programsource, String regimename,
			String address1, String designateddate1, String entity, String startdate1, 
			String enddate1, String program1) throws InterruptedException {

		// OFAC Filter (All Filter)
		 driver.findElement(AddressCountry).click();
		 driver.findElement(AddressSearch).click();
		 driver.findElement(AddressSearch).sendKeys(address);
		 driver.findElement(Addresfilter).click();
		 driver.findElement(Citizencountry).click();
		 driver.findElement(CitizenSearch).click();
		 driver.findElement(CitizenSearch).sendKeys(citizenship);
		 driver.findElement(Citizenfilter).click();
		 driver.findElement(Lastupdate).click();
		 driver.findElement(Startdate).click();
		 driver.findElement(Startdate).sendKeys(startdate);
		 driver.findElement(Enddate).click();
		 driver.findElement(Enddate).sendKeys(enddate);
		 driver.findElement(Nationalcountry).click();
		 driver.findElement(NationalSearch).click();
		 driver.findElement(NationalSearch).sendKeys(nationality);
		 driver.findElement(Nationalfilter).click();
		 driver.findElement(Programname).click();
		 driver.findElement(ProgramSearch).click();
		 driver.findElement(ProgramSearch).sendKeys(program);
		 driver.findElement(Programfilter).click();
		 driver.findElement(Type).click();
		 driver.findElement(TypeSearch).click();
		 driver.findElement(TypeSearch).sendKeys(type);
		 driver.findElement(Typefilter).click();
		 driver.findElement(Apply).click();Thread.sleep(5000);
		 
		 // Wait and check if download button exists
		 try {
		 	boolean logo6 = driver.findElement(Downloadicon).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Downloadicon).click();
		 		driver.findElement(Tab).click();Thread.sleep(5000);
		 		System.out.println("Download started for all Filters");
		 	}
		 } catch (Exception e) {
              driver.findElement(Clearfilter).click();Thread.sleep(5000);
			  System.out.println("Data is not present for Applied Filters or Download button not found");
		 }
		
        //OFAC Filters (Single Filter)
		 driver.findElement(Filter).click();
		 driver.findElement(AddressCountry).click();
		 driver.findElement(Selectall).click();
		 driver.findElement(Apply).click();Thread.sleep(3000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 boolean logo7 = driver.findElement(Notinitiated).isDisplayed();
		 if (logo7 == true) {
			System.out.println("Download started");

		} else {
			System.out.println("Download is in progress");
		}
		 driver.findElement(Clearfilter).click();
		 driver.findElement(Filter).click();
		 driver.findElement(Citizencountry).click();
		 driver.findElement(Selectall).click();
		 driver.findElement(Apply).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 driver.findElement(Clearfilter).click();
		 driver.findElement(Filter).click();
		 driver.findElement(Lastupdate).click();
		 driver.findElement(Startdate).click();
		 driver.findElement(Startdate).sendKeys(startdate);
		 driver.findElement(Enddate).click();
		 driver.findElement(Enddate).sendKeys(enddate);
		 driver.findElement(Apply).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 driver.findElement(Clearfilter).click();
		 driver.findElement(Filter).click();
		 driver.findElement(Nationalcountry).click();
		 driver.findElement(Selectall).click();
		 driver.findElement(Apply).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 driver.findElement(Clearfilter).click();
		 driver.findElement(Filter).click();
		 driver.findElement(Programname).click();
		 driver.findElement(Selectall).click();
		 driver.findElement(Apply).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 driver.findElement(Clearfilter).click();
		 driver.findElement(Filter).click();
		 driver.findElement(Type).click();
		 driver.findElement(Selectall).click();
		 driver.findElement(Apply).click();Thread.sleep(12000);
		 driver.findElement(Downloadicon).click();
		 driver.findElement(Excel).click();
		 driver.findElement(Clearfilter).click();

	}

	public void check_status() throws InterruptedException {
		driver.findElement(Downloads).click();Thread.sleep(10000);
		driver .findElement (Refresh).click();Thread.sleep(20000);
		driver .findElement (Refresh).click();Thread.sleep(20000);
		driver .findElement (Refresh).click();Thread.sleep(20000);
		driver .findElement (Refresh).click();Thread.sleep(20000);
		driver .findElement (Refresh).click();Thread.sleep(10000);
		driver.findElement(Refresh).click();

		 try {
		 	boolean icon = driver.findElement(Download).isDisplayed();
		 	if (icon == true){
		 		driver.findElement(Download).click();
		 	}
		 } catch (Exception e) {
              driver.findElement(Download1).click();Thread.sleep(3000);
		 }

		 try {
		 	boolean stat = driver.findElement(Status).isDisplayed();
		 	if (stat == true){
		 		System.out.println("Download started");
		 	}
		 } catch (Exception e) {
			  System.out.println("Download is failed");
		 }
	}
}
