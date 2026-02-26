// package com.pages;

// import java.time.Duration;
// import java.util.List;
// import java.util.Scanner;

// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.interactions.Actions;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// public class passList {
	
	
// 	private WebDriver driver;
// 	String status1,status2,status3;
// 	//1. By Locator
// 		private By passlistclick= By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/ul/div/li[2]/div");
// 		private By watchlistsourcelink = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/ul/li[4]/div/div[2]/p");
// 		//private By clickrightpane= By.xpath("//*[@id=\"sidebarContent\"]/ul/li[4]/div/div[1]");
// 		private By clickrightpane= By.xpath("//div[@id='sidebar']/div/div/div/ul/li[4]/div/div");
// 	//private By clickNewListbutton =By.xpath("//span[@aria-label='Details']");
// 	private By clickNewListbutton =By.xpath("//*[@id='internal_list']/div/div[2]/button");//*[@id="internal_list"]/div/div[2]/button
// 	private By passListName =By.xpath("//input[@id='scenario-name']");
// 	private By reason1 =By.xpath("//div[@aria-labelledby='reason reason']");
// 	private By clickInitiate =By.xpath("//button[@id='initiate-button']");
// 	private By cancel =By.xpath("//button[@id='scenario-cancel-button']");
// 	private By status=By.xpath("//*[@aria-labelledby='tableTitle']/tbody/tr[1]/td[6]");
// 	private By search =By.xpath("//input[@placeholder='Search']");
// 	private By taskicon=By.xpath("//*[local-name()='svg' and @data-testid='AssignmentTurnedInOutlinedIcon']");
// 	private By tasks=By.xpath("//div[@id='sidebar']/div[2]/div/div/ul/li[2]");
// 	private By mainWindowIcon =By.xpath("//*[@id=\"applicationContainer\"]/div[1]/div/div/button[1]");
// 	private By passlistButton =By.xpath("//*[@role='tablist']/button[6]");
// 	private By passlistrows =By.xpath("//*[@aria-label='sticky table']/tbody/tr");
// 	private By scroll=By.xpath("//*[@role='tabpanel']/div/div/div/div[4]/span[2]");
// 	private By description =By.xpath("//*[@label='description']");
// 	private By submit=By.xpath("//button[@id='btn-submit']");
// 	private By approve=By.xpath("//*[@type='success']");
// 	private By textArea=By.xpath("//textarea[@id='outlined-multiline-flexible']");
// 	private By actionButton=By.xpath("//button[@id='action-button']");
	
// 	//Record addition//*[@id="add-edit-record"]/span/div[1]/button
// 	//private By search_passlist =By.xpath("//*[@id=':rc:']");//*[@id="internal_list"]/div/div[2]/div/div/div
// 	private By search_passlist =By.xpath("//*[@id=':rg:']");
// 	private By get_pass_name =By.xpath("//*[@id='internal_list']/div/div[3]/div[1]/div/div/div/div/table/tbody/tr/td[1]/span/div");
// 	private By get_status = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[2]/div/div[2]/div/div/div/div[1]/span[2]");
// 	private By click_record =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[2]/div/div[2]/div/div/div/div[2]/span[2]/span");
// 	private By add_records =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[2]/div/div[1]/div/div/div/button");
// 	private By click_Single_Record = By.xpath("//*[@id='add-more-record-dropdown-menu']/div[3]/ul/li");
// 	private By input_supress_tag =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[2]/div/div/input");
// 	private By input_name=By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div/input");
// 	private By adddate =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[1]/div[2]/div/div/div/input");
// 	private By add_information=By.xpath("//*[@id='recharts_measurement_span']");
// 	//private By save_draft =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/span/div[1]/button");//*button[@type='default'][@id='submit-for-approval']
// 	private By save_draft =By.xpath("//*[@id='add-edit-record']/span/div[1]/button");
// 	//*[@id="add-edit-record"]/span/div[1]/button//*[@id="submit-for-approval"]
// 	private By submit_apprl=By.xpath("//button[@id=\"submit-for-approval\"]");
// 	private By get_list_txt = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[1]/div[1]/div[1]/div");
// 	private By click_passlist = By.cssSelector("#internal_list > div > div:nth-child(3) > div:nth-child(1) > div > div > div > div > table > tbody > tr:nth-child(1) > td.MuiTableCell-root.MuiTableCell-body.MuiTableCell-sizeMedium.table-row.f-w-500.undefined.css-q34dxg > span > div");
// 	private By taskscreen_text =By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/span/span/span[2]");
// 	private By passlist_button=By.xpath("//*[@role='tablist']/button[2]");
// 	private By address = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[1]/div/div[1]/div/input");
// 	private By city   = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[1]/div/div[2]/div/input");
// 	private By supresstag =  By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[3]/div/div/input");
// 	private By supressorigin = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[4]/div[1]/div/div/input");
// 	private By supressunit = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[4]/div[2]/div/div/input");
// 	private By textarea =  By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[3]/div[2]/div[2]/div[5]/div/div/textarea[1]");
	
	

// 	public passList(WebDriver driver) {
// 		this.driver = driver;
		
// 	}
	
// 	public void clickWatchlistSourceList()  {
		
// 		//driver.findElement(watchlistsourcelink).click();
// 		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(watchlistsourcelink)).click();
		
// 	}
	
// 	public void clickPassList() {
		
// 		//driver.findElement(passlistclick).click();
// 		new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(passlistclick)).click();
		
// 	}
	
// public void clicksidebarcontent() {
		
// 		//driver.findElement(clickrightpane).click();
// 	Actions act=new Actions(driver);
// 	act.moveToElement(driver.findElement(clickrightpane)).build().perform();
	
		
// 	}
	
// 	public void clickMainWindow() {
// 		Actions act=new Actions(driver);
// 		System.out.println("Inside click main window ");
// 		WebElement clickNewListbutton1=driver.findElement(mainWindowIcon);
// 		act.moveToElement(clickNewListbutton1).build().perform();
		
		
// 	}

// 	public void clickNewList() {
// 		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(clickNewListbutton)).click();
		
		
		
// 	}
// 	public void enterPrivatePasslistDetails(String plname,String reason) {
		
// 		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(passListName)).sendKeys(plname,Keys.TAB);
		
// 		if(driver.getPageSource().contains(plname)) {
// 			System.out.println("Inside if statement");
// 			driver.findElement(reason1).sendKeys(Keys.PAGE_DOWN);
// 			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(cancel)).click();
// 		}
// 		else {
			
// 			System.out.println("Inside else statement");
// 		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(reason1)).click();
// 		String searchText = reason;
// 		WebElement dropdown = driver.findElement(By.xpath("//ul[@role='listbox']"));
// 		dropdown.click(); // assuming you have to click the "dropdown" to open it
// 		List<WebElement> options = dropdown.findElements(By.tagName("li"));
// 		for (WebElement option : options)
// 		{
// 		    if (option.getAttribute("data-value").equals(searchText))
// 		    {
// 		        option.click(); // click the desired option
// 		        break;
// 		    }
// 		}
		
// 		driver.findElement(reason1).sendKeys(Keys.PAGE_DOWN);
// 		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(clickInitiate)).click();
// }
		
// 		}
	
// 	public void passlistCreatedValidation() {
		
// 		if(driver.getPageSource().contains("passlist_new1"))
// 		{
// 			System.out.println("Passlist is already created");
// 		}
// 		else
// 			System.out.println("Passlist is not created");
		
// 	}
	
// 	public void searchPassList(String passliname) throws InterruptedException {
// 		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(search)).click();
// 		driver.findElement(search).sendKeys(passliname);	
// 		synchronized (driver)
// 		{
// 		    driver.wait(5000);
// 		}
		
// 	}
	
// 	public String  getStatus(String pass) {
// 		String status1=new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(status)).getText();
// 		return status1;
// 	}
	
// 	public void navigateToTasksPage() {
// 		Actions action =new Actions(driver);
// 		action.moveToElement(driver.findElement(taskicon)).build().perform();
// 		driver.findElement(tasks).click();
// 		WebElement clickNewListbutton1=driver.findElement(mainWindowIcon);
// 		action.moveToElement(clickNewListbutton1).build().perform();
		
// 	}
	
// 	public void navigateToPasslistSection() throws InterruptedException {
// 		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(passlistButton)).click();
// 		synchronized(driver)
// 		{
// 			driver.wait(3000);
// 		}
// 	}
	
// 	public String getTasksStatus(String passlistname) {
// 		String status="";
// 		System.out.println("Inside getTasksStatus");
// 		List<WebElement> rows = driver.findElements(passlistrows);
		
// 		int size=rows.size();
// 		System.out.println("Outside for loop of getTasksStatus- size "+ size);
// 		for(int i=1;i<=size;i++)
// 		{
// 			String name =driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[2]/span/div")).getText();
// 			System.out.println("Outside if condition of getTasksStatus- name "+ name);
// 			if(name.contains(passlistname)) {
// 				System.out.println("Inside if condition of getTasksStatus");
// 				 status =driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[5]/span")).getText();
// 				 driver.findElement(By.xpath("//*[@aria-label='sticky table']/tbody/tr["+i+"]/td[2]/span/div")).click();
// 				 break;
// 			}
// 			}
// 		return status;
		
// 	}
// 	public String  searchPasslistDraft(String plname) throws InterruptedException {
		
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				driver.findElement(By.xpath("//a[contains(text(),'Overview')]")).sendKeys(Keys.PAGE_DOWN);
// 				driver.findElement(description).sendKeys("Test");
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				driver.findElement(description).sendKeys(Keys.PAGE_UP,Keys.PAGE_UP);
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(submit)).click();
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				searchPassList(plname);
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				status1=getStatus(plname);
				
			
		
// 		return status1;
// 	}
	
// 	public String searchPasslistPending1(String passlistname) throws InterruptedException{
		
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(approve)).click();
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(textArea)).sendKeys("Approved L1");
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(actionButton)).click();
// 				synchronized (driver)
// 				{
// 				    driver.wait(3000);
// 				}
// 				status2=getTasksStatus(passlistname);
				
// 		return status2;
		
// 	}
// public String searchPasslistPending2(String passlistname){
	
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(approve)).click();
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(textArea)).sendKeys("Approved L2");
// 				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(actionButton)).click();
// 				status3=getTasksStatus(passlistname);
// 		return status3;
		
// 	}
// public void search_passlist_click_record(String passlistname) throws InterruptedException {
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
	
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(search)).sendKeys(passlistname);
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
// 	String Passname = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_pass_name)).getText();
// 	System.out.println("passlist name :"+Passname );
// 	System.out.println("passlist name from screen :"+passlistname );
// 	//assert passlistname == Passname : "not equal";
// 	System.out.println("passlist name if condition :"+Passname );
		
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_pass_name)).click();
// 	//new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_passlist)).click();
// 	String list_txt=new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_list_txt)).getText();
	
// 	//assert passlistname == list_txt : "not equal as link not clicked";
// 	String status =driver.findElement(get_status).getText();
// 	//String status = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_status)).getText();
// 	System.out.println("statussss  :"+status );
	
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_record)).click();
// 	//new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_pass_name)).click();
// 		if(status=="APPROVED") {
// 			//assert status == "APPROVED" : "not equal";
// 			System.out.println("status  :"+status );
// 			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(get_pass_name)).click();
// 			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_record)).click();
		
// 	}	
	
// }

// public void get_Single_Reco() throws InterruptedException {
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
// 	String add_record = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(add_records)).getText();
// 	System.out.println("add record   :"+add_record );
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(add_records)).click();
// 	String txt =new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_Single_Record)).getText();
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_Single_Record)).click();
// 	System.out.println("txttt   :"+txt );
// 	if(add_record == "Add records") {
// 		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(add_records)).click();
// 		String txtt =new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_Single_Record)).getText();
// 		if("Single Record"==txt) {
			
// 			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(click_Single_Record)).click();
// 		}
		
		
// 	}	
	
	
	
	
// }
    
// //
// public void input_form_values(String name, String supressid, String datev) throws InterruptedException {
	
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
	
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(input_name)).sendKeys(name);
	
// 	new WebDriverWait(driver, 35).until(ExpectedConditions.elementToBeClickable(input_supress_tag)).sendKeys(supressid);
// 	new WebDriverWait(driver, 35).until(ExpectedConditions.elementToBeClickable(adddate)).sendKeys(datev);
// 	driver.findElement(input_supress_tag).sendKeys(Keys.PAGE_DOWN);
// //	JavascriptExecutor js = (JavascriptExecutor) driver;
// //
// //    // Launch the application       
// //    driver.get("input_supress_tag");
// //
// //    //This will scroll the web page till end.       
// //    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	
// }  
// public void input_form_extravalues(String addre, String cityi, String suptag, String supressorigi,String supressunitt,String txtarea) throws InterruptedException {
	
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
// 	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(address)).sendKeys(addre);
// 	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(city)).sendKeys(cityi);
// 	new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(supresstag)).sendKeys(suptag);
// 	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(supressorigin)).sendKeys(supressorigi);
// 	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(supressunit)).sendKeys(supressunitt);
// 	new WebDriverWait(driver, 49).until(ExpectedConditions.elementToBeClickable(textarea)).sendKeys(txtarea);
// 	driver.findElement(input_supress_tag).sendKeys(Keys.PAGE_DOWN);
// //	JavascriptExecutor js = (JavascriptExecutor) driver;
// //
// //    // Launch the application       
// //    driver.get("input_supress_tag");
// //
// //    //This will scroll the web page till end.       
// //    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	
// }  
         
// //public void add_information(String info) {} 
// public void save_draft() throws InterruptedException {
	
// 	Actions action = new Actions(driver);
//     //open SoftwareTestingMaterial.com
//     //sleep for 3secs to load the page
//      Thread.sleep(3000);
//     //SCROLL DOWN
//  action.sendKeys(Keys.PAGE_DOWN).build().perform();
//  action.sendKeys(Keys.PAGE_DOWN).build().perform();
//  action.sendKeys(Keys.PAGE_DOWN).build().perform();
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
	
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(save_draft)).click();
	
// }      
// public void submit_apprl() throws InterruptedException {
	
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
	
// 	new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(submit_apprl)).click();
// }

// //after moving to task page 
// public String task_screen() throws InterruptedException {
// 	synchronized (driver)
// 	{
// 	    driver.wait(3000);
// 	}
	

// 	return new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(taskscreen_text)).getText();	
		
// }

// public void task_screen_passlist() throws InterruptedException {
	
// 	synchronized (driver)
// 	{
// 	    driver.wait(4000);
// 	}
	
// 	String passlisttxt=new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(passlist_button)).getText();	
// 	System.out.println("passlist  txttt   :"+passlisttxt );
// 	if (passlisttxt.contains("Passlist records")) {
// 		System.out.println("passlist  txttt   :"+passlisttxt );
// 		new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(passlist_button)).click();		
		
// 	}
	
	
	
// }



// }
