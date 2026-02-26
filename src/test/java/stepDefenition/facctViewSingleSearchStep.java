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
import org.openqa.selenium.WebDriver;
import com.pages.loginPage;
import com.qea.factory.DriverFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
// import io.cucumber.messages.internal.com.google.common.collect.Range;
import junit.framework.Assert;


public class facctViewSingleSearchStep {


	
	
private loginPage loginpage =new loginPage(DriverFactory.getDriver());
private FacctViewLoginPage facctLgPage =new FacctViewLoginPage(DriverFactory.getDriver());
private FacctViewWelcomePage fcWelcomepg = new FacctViewWelcomePage(DriverFactory.getDriver());
private interpolRedDataExtraction interpolR = new interpolRedDataExtraction(DriverFactory.getDriver());
private readExcelData readex = new readExcelData();
ArrayList id = new ArrayList<>();
ArrayList searchName = new ArrayList<>();
ArrayList noofRecord = new ArrayList<>();
ArrayList matchName = new ArrayList<>();
ArrayList startRange = new ArrayList<>();
ArrayList endRange = new ArrayList<>();
ArrayList nameforComparision = new ArrayList<>();
ArrayList ListURL = new ArrayList<>();
ArrayList<Integer> startRangeforCom = new ArrayList<>();
ArrayList<Integer> endRangeforComp = new ArrayList<>();

	WebDriver driver;
	Properties prop;
	private configReader confReader;
	public int noofrecords;

	@Given("user has already logged into account")
	public void user_has_already_logged_into_account(io.cucumber.datatable.DataTable loginlisttable) {
		 List<String> logincred=loginlisttable.asList();
		    System.out.println("Expected Table List:"+logincred);
			confReader = new configReader();
			prop = confReader.init_pop();
			DriverFactory.getDriver().get(prop.getProperty(logincred.get(0)));
			System.out.println("Login to FacctView");
			String lgtext=facctLgPage.getLoginPageText();
			Assert.assertTrue(lgtext.contains(logincred.get(4)));
			
			System.out.println("Page Title is:" +facctLgPage.getLoginPageText());
			facctLgPage.clickLoginButton();
			facctLgPage.enterOrgName(logincred.get(1));
			facctLgPage.clickcontButton();
			facctLgPage.enterUserName(prop.getProperty(logincred.get(2)));
			facctLgPage.enterPassword(prop.getProperty(logincred.get(3)));
			facctLgPage.clickonLoginButtonT();
			String pagetxt=facctLgPage.getWelcomePageText();
			Assert.assertTrue(pagetxt.contains(logincred.get(5)));
	  
	    
	}


//	@Then("I validate list on sidebar{string}")
//	public void i_validate_all_list_item() {
//		
//		
//	}
//	
//	
//	@Then("user gets first list")
//	public void user_gets_first_list(io.cucumber.datatable.DataTable sideListTable) {
//		
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    List<String> exceptsideList=sideListTable.asList();
//	    System.out.println("Expected Table List:"+exceptsideList);
//	    List<String> actualtsideList=fcWelcomepg.sidebarFirstList();
//	    System.out.println("Actual Table List:"+actualtsideList);
//	    Assert.assertTrue(exceptsideList.containsAll(actualtsideList));
//	}
//
	@Then("listone count should be {int}")
	public void listone_count_should_be(Integer int1) {
		Assert.assertTrue(fcWelcomepg.getsideBarListOneCount() == int1);
	    // Write code here that turns the phrase above into concrete actions
	    
	}
//	
////	@Then("check more outcomes")
////	public void check_more_outcomes() {
////	    // Write code here that turns the phrase above into concrete actions
////	    
////	}
//	
	@Given("user click on arrow list")
	public void click_arrow(){
		
		// fcWelcomepg.clickArrow();
		
	}
	
	@Then("user gets entity list")
	public void user_gets_EntityListDropdown(io.cucumber.datatable.DataTable entLst) {
		
	    List<String> exceptedeList=entLst.asList();
	    System.out.println("Expected Entity List:"+exceptedeList);
	    List<String> actualeList=fcWelcomepg.getEntityType();
	    System.out.println("Actual Entity List:"+actualeList);
	    Assert.assertTrue(exceptedeList.containsAll(actualeList));
	    


	}
	

	@Given("user enter entity name for search {string}")
	public void user_enter_name_for_search(String blname) {
		if (blname == "IndiVidualNamec") {
		confReader = new configReader();
		prop = confReader.facctlistdataProperty();
		fcWelcomepg.enterNameforSearch(prop.getProperty(blname));}
		else {fcWelcomepg.enterNameforSearch(blname);}
	    
	    
	}


	@Given("User click search button")
	public void user_clieck_search_button() {
		fcWelcomepg.clickSearchButton();
	   
	}
	
	@Given("User click button for more field")
	public void user_click_button_for_more_field() {
	    fcWelcomepg.extraFieldForSearch();
	}

	@Given("User enters Entity or National ID {string}")
	public void user_enters_entity_or_national_id(String neid) {
		if (neid == "NatID") {
		confReader = new configReader();
		prop = confReader.facctlistdataProperty();
		fcWelcomepg.enterNIDorEntID(prop.getProperty(neid));}
		else {fcWelcomepg.enterNIDorEntID(neid);}
	}

	@Given("User enters DOB or RegID {string}")
	public void user_enters_dob_or_reg_id(String dob) {
		if(dob=="DateofBirth") {
		 confReader = new configReader();
		prop = confReader.facctlistdataProperty();
		fcWelcomepg.enterdateofBirth(prop.getProperty(dob));}
		else{fcWelcomepg.enterdateofBirth(dob);}
	}
	
	@Then("User validate result is displayed {string}")
	public void user_validate_result_is_displayed(String rs) {
	   String sactual = fcWelcomepg.searchResult();
	   System.out.println("Validating Result is displayed on screen "+ sactual);
	   noofrecords = Integer.parseInt(sactual.replaceAll("[^0-9]", ""));
	   System.out.println("number of list displayed : "+ noofrecords);
	   confReader = new configReader();
		prop = confReader.facctlistdataProperty();
		if(rs=="NoOfRecord") {
		String result = prop.getProperty(rs);	
	   System.out.println("number of records from conf : "+ noofrecords);
	   Assert.assertEquals(noofrecords,(Integer.parseInt(result)));}
		else {Assert.assertEquals(noofrecords,Integer.parseInt(rs));}
	 
	}
//
//	@Then("User validate result is displayed {string}")
//	public void user_validate_result_is_displayed(String rs) {
//	   String sactual = fcWelcomepg.searchResult();
//	   System.out.println("Validating Result is displayed on screen "+ sactual);
//	   noofrecords = Integer.parseInt(sactual.replaceAll("[^0-9]", ""));
//	   System.out.println("number of list displayed : "+ noofrecords);
//	   confReader = new configReader();
//		prop = confReader.facctlistdataProperty();
//		String result = prop.getProperty(rs);	
//	  // Range<Integer> openClosed = Range.openClosed(0, 9);
//	   //System.out.println(openClosed.contains(noofrecords)); 
//	  // openClosed.contains(noofrecords);
//		 System.out.println("number of records from conf : "+ noofrecords);
//	   Assert.assertEquals(noofrecords,(Integer.parseInt(result)));
//	  //Assert.assertTrue(sactual.contains(rs));
//	}
//	
//	@Then("^User validate result is displayed from excel \"([^\"]*)\"$")
//	public void user_validate_result_is_displayed_from_excel(String Name) throws IOException {
//    String sactual = fcWelcomepg.searchResult();
//    int noofrecords = Integer.parseInt(sactual.replaceAll("[^0-9]", ""));
//    System.out.println("number of list displayed : "+ noofrecords);
//	ArrayList searchName = new ArrayList<>();
//	ArrayList numRecord = new ArrayList<>();
//	int indexofn,indexofname = 0, firstindexofname=0,lastindexofname=0;;
//	String actualscore=null, aname=null;
//	searchName=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,1);
//	numRecord=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,2);
//	Set uniqueName = new HashSet<>(searchName);
//	 List list = new ArrayList<String>(uniqueName); 
//	System.out.println("Unique Values of names for search  "+uniqueName);
//	if(list.contains(Name)) {
//		indexofname =list.indexOf(Name);
//		System.out.println("Index of Given Name"+indexofname +"Unq N :  "+Name);	
//	}
//	System.out.println("Type of data of number from Excel : "+ (numRecord.get(firstindexofname)).getClass().getName());
//	firstindexofname=searchName.indexOf(list.get(indexofname));
//	int recExc =Integer.valueOf((String) (numRecord.get(firstindexofname)));
//	System.out.println("Number of Record from excel validation "+numRecord.get(firstindexofname));
//	Assert.assertEquals(noofrecords,recExc);
//	
//}
//
//
	@Then("User download JSON file")
	public void user_download_json_file() {
		fcWelcomepg.downloadJson();
	   
	}
	@Then("User verifies popup alert {string}")
	public void user_verifypopup(String emessage) {
		String actualmsg=fcWelcomepg.verifyPopupMessage();
		System.out.println("Actual message displayed on screen "+ actualmsg);
	     Assert.assertEquals(actualmsg, emessage);
	   
	}
	@Then("user verifies the search result against excepted result {string} {string} {string}")
	public void user_verifies_the_searchresult(String name, String strange, String endrange)  throws InterruptedException {
		//String actualmsg=fcWelcomepg.verifyPopupMessage();
		List<String> uiNameList = fcWelcomepg.searchListContainerName();
		List<String> scorel = fcWelcomepg.searchListContainerScore();
		int indexofn;
		String actualscore=null, aname=null;
		int expectedSrange = Integer.parseInt(strange);
		int expectedErange = Integer.parseInt(endrange);
		if(uiNameList.contains(name))
		{
			indexofn=uiNameList.indexOf(name);
			actualscore=scorel.get(indexofn);
			System.out.println("Index of given nameScore "+ actualscore);
			aname=uiNameList.get(indexofn);
			System.out.println("Index of given name at Perticular Index "+ aname);
		}
		
		
		Range<Integer> openClosed = Range.openClosed(expectedSrange,expectedErange);
		 Assert.assertTrue(uiNameList.contains(aname));
		  Assert.assertTrue(openClosed.contains(Integer.parseInt(actualscore)));
	}

	
	
	
	
	
	
	
	@Then("User verifies the result")
	public void user_verifies_the_result()  throws InterruptedException {
		//String actualmsg=fcWelcomepg.verifyPopupMessage();
		List<String> uiNameList = fcWelcomepg.searchListContainerName();
		fcWelcomepg.searchListContainerScore();
		int indexofn;
		String actualscore=null, aname=null;
		confReader = new configReader();
		prop = confReader.facctlistdataProperty();
 	    String expectedname = prop.getProperty("MatchName");	
		int expectedSrange = Integer.parseInt(prop.getProperty("MatchStartRange"));
		int expectedErange = Integer.parseInt(prop.getProperty("MatchEndRange"));
		if(uiNameList.contains(expectedname))
		{
			indexofn=uiNameList.indexOf(expectedname);
			actualscore=fcWelcomepg.searchListContainerScore().get(indexofn);
			System.out.println("Index of given nameScore "+ actualscore);
			aname=uiNameList.get(indexofn);
			System.out.println("Index of given name at Perticular Index "+ aname);
		}
		
		
		Range<Integer> openClosed = Range.openClosed(expectedSrange,expectedErange);
		 Assert.assertTrue(uiNameList.contains(aname));
		  Assert.assertTrue(openClosed.contains(Integer.parseInt(actualscore)));
	}
	
//	@Before public void loader () throws InterruptedException, IOException {
//		
//		id=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,0);
//		searchName=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,1);
//		noofRecord=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,2);
//		matchName=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,3);
//		startRange=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,4);
//		endRange=readex.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,5);
//		
//	}
//
//	@Then("^User verifies all the result for search \"([^\"]*)\"$")
//	public void user_verifies_All_the_result(String Name)  throws InterruptedException, IOException {
//		
//		//String actualmsg=fcWelcomepg.verifyPopupMessage();
//		List<String> uiNameList = fcWelcomepg.searchListContainerName();
//		List<String> uiScoreList = fcWelcomepg.searchListContainerScore();
//		
//		int indexofn,indexofname = 0, firstindexofname=0,lastindexofname=0;;
//		String actualscore=null, aname=null;
//		
//		Set uniqueName = new HashSet<>(searchName);
//		 List list = new ArrayList<String>(uniqueName); 
//		System.out.println("Unique Values of names for search  "+uniqueName);
//		if(list.contains(Name)) {
//			indexofname =list.indexOf(Name);
//			System.out.println("Index of Given Name"+indexofname +"Unq N :  "+Name);
//			
//		}
//		System.out.println("Last of first unique Values Name Value "+searchName.lastIndexOf(list.get(indexofname)));
//		System.out.println("First Index of first unique Values Name Value "+searchName.indexOf(list.get(indexofname)));
//		firstindexofname=searchName.indexOf(list.get(indexofname));
//		lastindexofname=searchName.lastIndexOf(list.get(indexofname));
//		for(int i=firstindexofname;i<=lastindexofname; i++) {
//			nameforComparision.add(matchName.get(i));
//			System.out.println("range..."+ startRange.get(i));
//			int stran = (int)(double)(startRange.get(i));
//			int enran =(int)(double)endRange.get(i);
//			Integer sr = new Integer(stran);
//			Integer er = new Integer(enran);
//			startRangeforCom.add(sr);
//			endRangeforComp.add(er);
//		}
//		System.out.println("List of name for comparision "+ nameforComparision);
//		System.out.println("Comparision Result:  "+ uiNameList.containsAll(nameforComparision));
//		
//		System.out.println("Result from UI  "+uiNameList);
//		if(uiNameList.size()>0 && nameforComparision.size()>0)
//		{
//			
//			for(int i=0;i<nameforComparision.size(); i++) {
//				
//				indexofn=uiNameList.indexOf(nameforComparision.get(i));
//				System.out.println("Index of name from UI "+ indexofn);
//				System.out.println("Score from UI "+ actualscore);
//				System.out.println("1  "+System.currentTimeMillis());
//				actualscore=uiScoreList.get(indexofn);
//				System.out.println("2  "+System.currentTimeMillis());
//				aname=uiNameList.get(indexofn);
//				System.out.println("Name from UI "+ aname);
//				System.out.println("Name from excel : "+ nameforComparision.get(i)+"   :Name from UI : "+ aname);
//				//System.out.println("Given Start Range "+ startRangeforCom.get(i));
//				//System.out.println("Given End Range "+ endRangeforComp.get(i));
//				
//				Range<Integer> openClosed = Range.openClosed(startRangeforCom.get(i),endRangeforComp.get(i));
//				
//				 Assert.assertTrue(aname.equals(nameforComparision.get(i)));
//				  Assert.assertTrue(openClosed.contains(Integer.parseInt(actualscore)));
//				  
//				
//			}
//		
//		}
//		
//		
////		Range<Integer> openClosed = Range.openClosed(expectedSrange,expectedErange);
////		 Assert.assertTrue(fcWelcomepg.searchListContainerName().contains(aname));
////		  Assert.assertTrue(openClosed.contains(Integer.parseInt(actualscore)));
//	}
	
}
