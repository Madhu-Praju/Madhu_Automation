package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qea.utils.ElementUtils;

public class RegulatorylistPage {

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
	private By Ofac = By.xpath("//div[text()='OFAC']");
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
	//private By Address1 = By.xpath("(//span[text()='Address country'])[1]");
	//private By Designatedate1 = By.xpath("(//span[text()='Designated date'])[1]");
	private By Entity = By.xpath("(//span[text()='Entity Type'])[1]");
	private By Entitysearch = By.xpath("(//input[@placeholder='Search entity type'])");
	private By Entityfilter = By.xpath("//input[@id='advance-filter-list-select-all-entityTypeName']");
	//private By Lastupdate1 = By.xpath("(//span[text()='Last updated date'])[1]");
	//private By Startdate1 = By.xpath("(//input[@name='startDate'])");
	//private By Enddate1 = By.xpath("(//input[@name='endDate'])");
	//private By Programname1 = By.xpath("(//span[text()='Program Name'])[1]");

	private By Apply = By.xpath("(//button[@id='advance-filter-apply-btn'])");
	private By Downloadicon = By.xpath("(//button[@id='regulatory-records-download-btn'])");
	private By Excel = By.xpath("(//span[text()='Excel (.xlsx)'])");
	private By Tab = By.xpath("(//li[text()='Tab separated (.tsv)'])");
    private By Initiated = By.xpath("//div[text()='Request initiated. Check status at Downloads tab']");
	private By Notinitiated = By.xpath("//div[text()='Request in progress. Check the Downloads tab for status']");
    private By Selectall = By.xpath("//label[text()='Select all']");
	private By Clearall = By.xpath("//button[text()='CLEAR ALL']");
	private By Close = By.xpath("//button[text()='CLOSE']");
	private By Clearfilter = By.xpath("//button[text()='CLEAR FILTERS']");

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

	WebDriver driver;
	
	// Date variables for filters
	private String startdate = "30/08/2024";
	private String enddate = "22/12/2025";
	
	// Filter data variables
	private String address = "Cuba";
	private String citizenship = "Egypt";
	private String nationality = "Egypt";
	private String program = "CAR";
	private String type = "Individual";
	private String designatedate = "01/01/2024";

	public RegulatorylistPage(WebDriver driver) {
		this.driver = driver;
	}

    public void facctlist_login(String url, String ornm, String usnm, String pswd) throws InterruptedException {
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

	public void navigate_to_watchlist()throws InterruptedException{
		driver.findElement(Watchlistmenu).click();Thread.sleep(4000);
	}

	public void navigate_to_regulatory_list()throws InterruptedException{
		driver.findElement(Regulatorylist).click();Thread.sleep(3000);
		driver.findElement(Pagination).click();Thread.sleep(2000);
		driver.findElement(Pagevalue).click();Thread.sleep(2000);

	}

	public void click_on_ofac_list()throws InterruptedException{
		driver.findElement(Ofac).click();Thread.sleep(3000);
	}

	public void click_on_un_list()throws InterruptedException{
		driver.findElement(Un).click();Thread.sleep(3000);
	}

	public void click_on_eu_list()throws InterruptedException{
		driver.findElement(Eu).click();Thread.sleep(3000);
	}

	public void click_on_uk_sanction_list()throws InterruptedException{
		driver.findElement(Uksanctions).click();Thread.sleep(3000);
	}

    //OFAC ACTIVE TAB
	public void click_on_ofac_active_tab()throws InterruptedException{
		driver.findElement(Active).click();
	}

	public void click_on_ofac_active_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_active_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_active_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC ERROR TAB

	public void click_on_ofac_error_tab()throws InterruptedException{
		driver.findElement(Error).click();
	}

	public void click_on_ofac_error_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_error_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_error_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC DELETE TAB

	public void click_on_ofac_delete_tab()throws InterruptedException{
		driver.findElement(Delete).click();
	}

	public void click_on_ofac_delete_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_delete_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_delete_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC NEW TAB

	public void click_on_ofac_new_tab()throws InterruptedException{
		driver.findElement(Delta).click();
		driver.findElement(New).click();
	}

	public void click_on_ofac_new_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_new_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_new_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }
	
	//OFAC AMEND TAB

	public void click_on_ofac_amend_tab()throws InterruptedException{
		driver.findElement(Amend).click();
	}

	public void click_on_ofac_amend_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_amend_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_amend_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC DELTA DELETE TAB

	public void click_on_ofac_delta_delete_tab()throws InterruptedException{
		driver.findElement(DDelete).click();
	}

	public void click_on_ofac_delta_delete_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_delta_delete_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_delta_delete_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC STABLE TAB

	public void click_on_ofac_stable_tab()throws InterruptedException{
		driver.findElement(Stable).click();
	}

	public void click_on_ofac_stable_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_stable_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_stable_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//OFAC DELTA ERROR TAB

	public void click_on_ofac_delta_error_tab()throws InterruptedException{
		driver.findElement(DError).click();
	}

	public void click_on_ofac_delta_error_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_ofac_delta_error_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Citizencountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
        driver.findElement(Excel).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Nationalcountry).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_ofac_delta_error_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }


	//UK SANCTIONS ACTIVE TAB
	public void click_on_uksanction_active_tab()throws InterruptedException{
		driver.findElement(Active).click();
	}

	public void click_on_uksanction_active_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_active_single_filter() throws InterruptedException{
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_active_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UK SANCTIONS ERROR TAB

	public void click_on_uksanction_error_tab()throws InterruptedException{
		driver.findElement(Error).click();
	}

	public void click_on_uksanction_error_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_error_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_error_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UK SANCTIONS DELETE TAB

	public void click_on_uksanction_delete_tab()throws InterruptedException{
		driver.findElement(Delete).click();
	}

	public void click_on_uksanction_delete_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_delete_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();
        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_delete_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UK SANCTIONS NEW TAB

	public void click_on_uksanction_new_tab()throws InterruptedException{
		driver.findElement(Delta).click();
		driver.findElement(New).click();
	}

	public void click_on_uksanction_new_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_new_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_new_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }
	
	//UK SANCTIONS AMEND TAB

	public void click_on_uksanction_amend_tab()throws InterruptedException{
		driver.findElement(Amend).click();
	}

	public void click_on_uksanction_amend_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_amend_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_amend_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//Uk SANCTIONS DELTA DELETE TAB

	public void click_on_uksanction_delta_delete_tab()throws InterruptedException{
		driver.findElement(DDelete).click();
	}

	public void click_on_uksanction_delta_delete_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_delta_delete_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_delta_delete_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UK SANCTIONS STABLE TAB

	public void click_on_uksanction_stable_tab()throws InterruptedException{
		driver.findElement(Stable).click();
	}

	public void click_on_uksanction_stable_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_stable_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_stable_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UK SANCTIONS DELTA ERROR TAB

	public void click_on_uksanction_delta_error_tab()throws InterruptedException{
		driver.findElement(DError).click();
	}

	public void click_on_uksanction_delta_error_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_uksanction_delta_error_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Designateddate).click();
	 	driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Idtype).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_uksanction_delta_error_filter() throws InterruptedException{
		driver.findElement(Filter).click();
		driver.findElement(AddressCountry).click();
		driver.findElement(AddressSearch).click();
		driver.findElement(AddressSearch).sendKeys(address);
		driver.findElement(Addresfilter).click();
		driver.findElement(Designateddate).click();
		driver.findElement(Designatedate).click();
		driver.findElement(Designatedate).sendKeys(designatedate);
		driver.findElement(Idtype).click();
		driver.findElement(Idtypesearch).click();
		driver.findElement(Idtypesearch).sendKeys(nationality);
		driver.findElement(Idtypefilter).click();
		driver.findElement(Programsource).click();
		driver.findElement(Programsourcesearch).click();
		driver.findElement(Programsourcesearch).sendKeys(program);
		driver.findElement(Programsourcefilter).click();
		driver.findElement(Regimename).click();
		driver.findElement(Regimenamesearch).click();
		driver.findElement(Regimenamesearch).sendKeys(type);
		driver.findElement(Regimenamefilter).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }

	//UN ACTIVE TAB
	public void click_on_un_active_tab()throws InterruptedException{
		driver.findElement(Active).click();
	}

	public void click_on_un_active_filter_icon(){

         try {
		 	boolean logo6 = driver.findElement(Filter).isDisplayed();
		 	if (logo6 == true){
		 		driver.findElement(Filter).click();
		 		System.out.println("Filter Button is Exist");
		 	}
		 } catch (Exception e) {
			  System.out.println("Filter Button is not Exist");
		 }
	}
	public void apply_the_un_active_single_filter() throws InterruptedException{
		driver.findElement(AddressCountry).click();
		driver.findElement(Selectall).click();
        driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(4000);
		boolean logo7 = driver.findElement(Initiated).isDisplayed();
		if (logo7 == true){
		 	System.out.println("Download started");
		}else{
			System.out.println("Download not Started");
		}
        driver.findElement(Clearfilter).click();

        driver.findElement(Filter).click();
		driver.findElement(Lastupdate).click();
	 	driver.findElement(Startdate).click();
		driver.findElement(Startdate).sendKeys(startdate);
		driver.findElement(Enddate).click();
		driver.findElement(Enddate).sendKeys(enddate);
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Entity).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Programname).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);

		driver.findElement(Filter).click();
		driver.findElement(Type).click();
		driver.findElement(Selectall).click();
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
	}
    public void apply_all_un_active_filter() throws InterruptedException{
		driver.findElement(Filter).click();
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
		driver.findElement(Apply).click();Thread.sleep(3000);
		driver.findElement(Downloadicon).click();
		driver.findElement(Tab).click();Thread.sleep(2000);
		driver.findElement(Clearfilter).click();Thread.sleep(2000);
    }












	// public void click_on_clear_all(){
	// 	driver.findElement(Clearall).click();
	// }

	// public void click_on_close(){
	// 	driver.findElement(Close).click();
	// }

	// public void click_on_active_tab_filter(){
	// 	driver.findElement(Active).click();
	// }

	// public void choose_the_active_tab_filter()throws InterruptedException{
	// 	driver.findElement(Filter).click();
	// 	driver.findElement(AddressCountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Citizencountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Lastupdate).click();
	//  	driver.findElement(Startdate).click();
	// 	driver.findElement(Startdate).sendKeys(startdate);
	// 	driver.findElement(Enddate).click();
	// 	driver.findElement(Enddate).sendKeys(enddate);
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Nationalcountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Programname).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Type).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);
		
	// }

	// public void click_on_error_tab(){}

	// public void choose_the_error_tab_filter() throws InterruptedException{
	// 	driver.findElement(Filter).click();
	// 	driver.findElement(AddressCountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Citizencountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Lastupdate).click();
	//  	driver.findElement(Startdate).click();
	// 	driver.findElement(Startdate).sendKeys(startdate);
	// 	driver.findElement(Enddate).click();
	// 	driver.findElement(Enddate).sendKeys(enddate);
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Nationalcountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Programname).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Type).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);
	// }

	// public void click_on_delete_tab(){}

	// public void choose_the_delete_tab_filter() throws InterruptedException{
	// 	driver.findElement(Filter).click();
	// 	driver.findElement(AddressCountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Citizencountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Lastupdate).click();
	//  	driver.findElement(Startdate).click();
	// 	driver.findElement(Startdate).sendKeys(startdate);
	// 	driver.findElement(Enddate).click();
	// 	driver.findElement(Enddate).sendKeys(enddate);
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Nationalcountry).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Programname).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);

	// 	driver.findElement(Filter).click();
	// 	driver.findElement(Type).click();
	// 	driver.findElement(Selectall).click();
	// 	driver.findElement(Apply).click();Thread.sleep(3000);
	// 	driver.findElement(Downloadicon).click();
	// 	driver.findElement(Tab).click();Thread.sleep(2000);
	// 	driver.findElement(Clearfilter).click();Thread.sleep(2000);
	// }

	// public void click_on_delta_toggle(){}

	// public void click_on_new_tab(){}

	// public void choose_the_new_tab_filter(){}

	// public void click_on_amend_tab(){}

	// public void click_on_amend_tab_filter(){}
	
	// public void choose_the_amend_tab_filter(){}

	// public void click_on_delta_delete_tab(){}

	// public void click_on_delta_delete_tab_filter(){}

	// public void choose_the_delta_delete_tab_filter(){}

	// public void click_on_stable_tab(){}

	// public void click_on_stable_tab_filter(){}

	// public void choose_the_stable_tab_filter(){}

	// public void click_on_delta_error_tab(){}

	// public void click_on_delta_error_tab_filter(){}

	// public void choose_the_delta_error_tab_filter(){}

	// public void click_on_the_download_icon(){}

	// public void click_on_the_excel(){}

	// public void click_on_the_tsv(){}

	// public void click_on_the_downloads_tab(){}

	// public void verify_the_download_status(){}

	// public void click_on_the_downlad(){}

	// // Missing methods needed by step file
	// public void click_List_Management() throws InterruptedException {
	// 	driver.findElement(Facctlistarrow).click();
	// 	Thread.sleep(3000);
	// }

	// public void click_on_apply() throws InterruptedException {
	// 	driver.findElement(Apply).click();
	// 	Thread.sleep(2000);
	// }

	// public void click_error_tab_filter() throws InterruptedException {
	// 	driver.findElement(Filter).click();
	// 	Thread.sleep(1000);
	// }

	// public void click_on_delete_tab_filter() throws InterruptedException {
	// 	driver.findElement(Filter).click();
	// 	Thread.sleep(1000);
	// }

	// public void click_on_the_delta_toggle() throws InterruptedException {
	// 	driver.findElement(Delta).click();
	// 	Thread.sleep(2000);
	// }

	// public void click_on_the_new_tab() throws InterruptedException {
	// 	driver.findElement(New).click();
	// 	Thread.sleep(2000);
	// }

	// public void click_on_new_tab_filter() throws InterruptedException {
	// 	driver.findElement(Filter).click();
	// 	Thread.sleep(1000);
	// }

	// public void click_on_the_download() throws InterruptedException {
	// 	driver.findElement(Downloadicon).click();
	// 	Thread.sleep(2000);
	// }
}
