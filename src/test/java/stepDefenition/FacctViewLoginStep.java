package stepDefenition;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Range;
import com.pages.FacctViewLoginPage;
import com.pages.FacctViewWelcomePage;
import com.pages.interpolRedDataExtraction;
import com.pages.loginPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;
import com.qea.utils.readExcelData;

import java.util.concurrent.TimeUnit;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class FacctViewLoginStep {
	
	
	private loginPage loginpage;
	private FacctViewLoginPage facctLgPage;
	private FacctViewWelcomePage fcWelcomepg;
	private interpolRedDataExtraction interpolR;
	private readExcelData readex = new readExcelData();
	ArrayList<String> id = new ArrayList<>();
	ArrayList<String> searchName = new ArrayList<>();
	ArrayList<String> noofRecord = new ArrayList<>();
	ArrayList<String> matchName = new ArrayList<>();
	ArrayList<Double> startRange = new ArrayList<>();
	ArrayList<Double> endRange = new ArrayList<>();
	ArrayList<String> nameforComparision = new ArrayList<>();
	ArrayList<String> ListURL = new ArrayList<>();
	ArrayList<Integer> startRangeforCom = new ArrayList<>();
	ArrayList<Integer> endRangeforComp = new ArrayList<>();
	
	WebDriver driver;
	Properties prop;
	private configReader confReader;
	public int noofrecords;
	
	@Before
	public void setUp() {
		loginpage = new loginPage(DriverFactory.getDriver());
		facctLgPage = new FacctViewLoginPage(DriverFactory.getDriver());
		fcWelcomepg = new FacctViewWelcomePage(DriverFactory.getDriver());
		interpolR = new interpolRedDataExtraction(DriverFactory.getDriver());
	}
	
	// @Given("I Open url {string}")
	// public void i_Open_URL(String URL) {
	// 	confReader = new configReader();
	// 	prop = confReader.init_pop();
	// 	DriverFactory.getDriver().get(prop.getProperty(URL));
	// 	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//     // Write code here that turns the phrase above into concrete actions
	// 	System.out.println("Login to FacctView");
	   
	// }
	
	@Given("I Open given url {string}")
	public void i_Open_GIVEN_URL(String URL) {
		
		DriverFactory.getDriver().get(URL);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Login to FacctView");
	   
	}
	@Given("I validate Login page text {string}")
	public void i_validate_login_page_text(String logintext) throws InterruptedException {
		Thread.sleep(2000);
		String lgtext=facctLgPage.getLoginPageText();
		Assert.assertTrue(lgtext.contains(logintext));
		
		System.out.println("Page Title is:" +facctLgPage.getLoginPageText());
	
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Given("I click to facctView login button")
	public void i_click_to_facct_view_login_button() {
		facctLgPage.clickLoginButton();
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Given("I enter tenant name {string}")
	public void i_enter_tenant_name(String orgName) {
		facctLgPage.enterOrgName(orgName);
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Given("I click to continue button")
	public void i_click_to_continue_button() {
	    // Write code here that turns the phrase above into concrete actions
		facctLgPage.clickcontButton();
	    
	}

	@Given("I enter facctView login id {string}")
	public void i_enter_facct_view_login_id(String loginId) throws InterruptedException {
		confReader = new configReader();
		prop = confReader.init_pop();
		Thread.sleep(2000);
		facctLgPage.enterUserName(prop.getProperty(loginId));
		//DriverFactory.getDriver().get(prop.getProperty(URL));
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Given("I enter facctView login password {string}")
	public void i_enter_facct_view_login_password(String passwor) {
	    // Write code here that turns the phrase above into concrete actions
		confReader = new configReader();
		prop = confReader.init_pop();
		facctLgPage.enterPassword(prop.getProperty(passwor));
	}

	@Given("I click to next button")
	public void i_click_to_next_button() {
	    // Write code here that turns the phrase above into concrete actions
		facctLgPage.clickonLoginButtonT();
	    
	}

	@Then("I validate welcome page text {string}")
	public void i_validate_welcome_page_text(String pagetext) {
		System.out.println("Welcome page Title is:" +facctLgPage.getWelcomePageText());
		String pagetxt=facctLgPage.getWelcomePageText();
		Assert.assertTrue(pagetxt.contains(pagetext));
		
		System.out.println("Page Title is:" +facctLgPage.getWelcomePageText());
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("I validate list on sidebar{string}")
	public void i_validate_all_list_item() {
		
		
	}
	
	
	@Then("user gets first list")
	public void user_gets_first_list(io.cucumber.datatable.DataTable sideListTable) {
        List<String> exceptsideList = sideListTable.asList();
        System.out.println("Expected Table List:" + exceptsideList);
        List<String> actualtsideList = fcWelcomepg.sidebarFirstList();
        System.out.println("Actual Table List:" + actualtsideList);
        Assert.assertTrue(exceptsideList.containsAll(actualtsideList));
    }
	
//	@Then("listone count should be {int}")
//	public void listone_count_should_be(Integer int1) {
//		Assert.assertTrue(fcWelcomepg.getsideBarListOneCount() == int1);
//	    // Write code here that turns the phrase above into concrete actions
//	    
//	}
	
//	@Then("check more outcomes")
//	public void check_more_outcomes() {
//	    // Write code here that turns the phrase above into concrete actions
//	    
//	}
//	
//	@Given("user click on arrow list")
//	public void click_arrow(){
//		
//		fcWelcomepg.clickArrow();
//		
//	}
//	
//	@Then("user gets entity list")
//	public void user_gets_EntityListDropdown(io.cucumber.datatable.DataTable entLst) {
//		
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    List<String> exceptedeList=entLst.asList();
//	    System.out.println("Expected Entity List:"+exceptedeList);
//	    List<String> actualeList=fcWelcomepg.getEntityType();
//	    System.out.println("Actual Entity List:"+actualeList);
//	    Assert.assertTrue(exceptedeList.containsAll(actualeList));
//	    
//
//
//	}
	
	@Given("User selects Individual from list")
	public void user_selects_individual_from_list() {
	   fcWelcomepg.selectInt();
	    
	}
	@Given("User selects Any from list")
	public void user_selects_any_from_list() {
	   fcWelcomepg.selectAny();
	    
	}
	@Given("User selects entity from list")
	public void user_selects_entity_from_list() {
	   fcWelcomepg.selectEntity();
	    
	}
	
	
	
	@Then("^User validate result is displayed from excel \"([^\"]*)\"$")
	public void user_validate_result_is_displayed_from_excel(String Name) throws IOException {
        String sactual = fcWelcomepg.searchResult();
        int noofrecords = Integer.parseInt(sactual.replaceAll("[^0-9]", ""));
        System.out.println("number of list displayed : " + noofrecords);
        ArrayList<String> searchName = new ArrayList<>();
        ArrayList<String> numRecord = new ArrayList<>();
        int indexofname = 0, firstindexofname = 0;
        searchName = readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2, 1);
        numRecord = readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2, 2);
        Set<String> uniqueName = new HashSet<>(searchName);
        List<String> list = new ArrayList<>(uniqueName);
        System.out.println("Unique Values of names for search  " + uniqueName);
        if (list.contains(Name)) {
            indexofname = list.indexOf(Name);
            System.out.println("Index of Given Name" + indexofname + "Unq N :  " + Name);
        }
        System.out.println("Type of data of number from Excel : " + (numRecord.get(firstindexofname)).getClass().getName());
        firstindexofname = searchName.indexOf(list.get(indexofname));
        int recExc = Integer.valueOf(numRecord.get(firstindexofname));
        System.out.println("Number of Record from excel validation " + numRecord.get(firstindexofname));
        Assert.assertEquals(noofrecords, recExc);
    }

	
	@Before public void loader () throws InterruptedException, IOException {
		
		id=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,0);
		searchName=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,1);
		noofRecord=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,2);
		matchName=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,3);
		startRange=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,4);
		endRange=readex.readExcel("./src\\test\\resources\\testData", "Test_Sheet.xlsx", 2,5);
		
	}

	@Then("^User verifies all the result from excel for search \"([^\"]*)\"$")
	public void user_verifies_All_the_result(String Name)  throws InterruptedException, IOException {
		
		//String actualmsg=fcWelcomepg.verifyPopupMessage();
		List<String> uiNameList = fcWelcomepg.searchListContainerName();
		Thread.sleep(6000);
		List<String> uiScoreList = fcWelcomepg.searchListContainerScore();
		
		int indexofn, indexofname = 0, firstindexofname = 0, lastindexofname = 0;
		String actualscore = null, aname = null;
		
		Set<String> uniqueName = new HashSet<>(searchName);
		 List<String> list = new ArrayList<>(uniqueName); 
		System.out.println("Unique Values of names for search  "+uniqueName);
		if(list.contains(Name)) {
			indexofname =list.indexOf(Name);
			//System.out.println("Index of Given Name"+indexofname +"Unq N :  "+Name);
			
		}
		System.out.println("Last of first unique Values Name Value "+searchName.lastIndexOf(list.get(indexofname)));
		System.out.println("First Index of first unique Values Name Value "+searchName.indexOf(list.get(indexofname)));
		firstindexofname=searchName.indexOf(list.get(indexofname));
		lastindexofname=searchName.lastIndexOf(list.get(indexofname));
		for(int i=firstindexofname;i<=lastindexofname; i++) {
			nameforComparision.add(matchName.get(i));
			//System.out.println("range..."+ startRange.get(i));
			int stran = (int)(double)(startRange.get(i));
			int enran =(int)(double)endRange.get(i);
			Integer sr = Integer.valueOf(stran);
			Integer er = Integer.valueOf(enran);
			startRangeforCom.add(sr);
			endRangeforComp.add(er);
		}
		System.out.println("List of name for comparision from Excel "+ nameforComparision);
		//System.out.println("Comparision Result:  "+ uiNameList.containsAll(nameforComparision));
		
		System.out.println("Result of Name List from UI  "+uiNameList);
		if(uiNameList.size()>0 && nameforComparision.size()>0)
		{
			
			for(int i=0;i<nameforComparision.size(); i++) {
				
				indexofn=uiNameList.indexOf(nameforComparision.get(i));
				//System.out.println("Index of name from UI "+ indexofn);
				
			//	System.out.println("1  "+System.currentTimeMillis());
				actualscore=uiScoreList.get(indexofn);
				//System.out.println("2  "+System.currentTimeMillis());
				aname=uiNameList.get(indexofn);
				System.out.println("Name from UI "+ aname);
				System.out.println("Name from excel : "+ nameforComparision.get(i)+"   :Name from UI : "+ aname);
				System.out.println("Given Start Range "+ startRangeforCom.get(i));
				System.out.println("Given End Range "+ endRangeforComp.get(i));
				System.out.println("Score from UI "+ actualscore);
				Range<Integer> openClosed = Range.openClosed(startRangeforCom.get(i),endRangeforComp.get(i));
				
				 Assert.assertEquals(aname, nameforComparision.get(i));
				 Assert.assertTrue(openClosed.contains(Integer.parseInt(actualscore)));
				  
				
			}
		
		}
		
		

	}

	
	@Then("^User downloads all the link from CBI")
	public void user_download() throws InterruptedException {
		
		interpolR.getAllLinkandName();
	}
	@Then("^User click Next button")
	public void user_clicknxt() throws InterruptedException {
		
		interpolR.clickNext();
	}

	
	@Then("^User get all the details from page")	
public void i_get_Open_GIVEN_URL() throws IOException, InterruptedException {
		ArrayList ListURL = new ArrayList<>();
		ListURL=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
		ArrayList<String> familyName = new ArrayList<>();
		ArrayList<String> gender = new ArrayList<>();
		ArrayList<String> dateofbirth = new ArrayList<>();
		ArrayList<String> placeofbirth = new ArrayList<>();
		ArrayList<String> language = new ArrayList<>();
		ArrayList<String> charges = new ArrayList<>();
		ListURL.size();
		DriverFactory dd = new DriverFactory();
		System.out.println("List Size " + ListURL.size());
		for(int i=0;i<ListURL.size(); i++) {
			ListURL.get(i);
			DriverFactory.getDriver().get((String) ListURL.get(i));
			Thread.sleep(4000);
			System.out.println(interpolR.getName());
			System.out.println(interpolR.getGender());
			System.out.println(interpolR.getDob());
			System.out.println(interpolR.getPob());
			System.out.println(interpolR.getnation());
			System.out.println(interpolR.getlang());
			System.out.println(interpolR.getcharge());
			System.out.println("Printing familyname------- " + familyName );
		}
}

@Then("^User get all the details for family from page")
public void i_get_Open_family() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getFamilyName());
    }
}

@Then("^User get all the details for gender from page")
public void i_get_Open_gender() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getGender());
    }
}

@Then("^User get all the details for dateofb from page")
public void i_get_Open_dob() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getDob());
    }
}

@Then("^User get all the details for pod from page")
public void i_get_Open_pod() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getPob());
    }
}

@Then("^User get all the details for nati from page")
public void i_get_Open_nat() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getnation());
    }
}

@Then("^User get all the details for lan from page")
public void i_get_Open_lan() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getlang());
    }
}

@Then("^User get all the details for charge from page")
public void i_get_Open_char() throws IOException, InterruptedException {
    ArrayList<String> ListURL = new ArrayList<>();
    ListURL = readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop", "Interpole_List.xlsx", 0,0);
    ListURL.size();
    for(int i=0;i<ListURL.size(); i++) {
        ListURL.get(i);
        DriverFactory.getDriver().get((String) ListURL.get(i));
        Thread.sleep(4000);
        System.out.println(interpolR.getcharge());
    }
}
}
