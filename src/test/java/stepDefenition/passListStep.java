// package stepDefenition;

// import java.util.List;

// import org.openqa.selenium.WebDriver;

// import com.pages.loginPage;
// import com.pages.passList;
// import com.pages.task_Screen_Approval;
// import com.qea.factory.DriverFactory;

// import io.cucumber.datatable.DataTable;
// import io.cucumber.java.en.And;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;
// import junit.framework.Assert;

// public class passListStep {
	
// private loginPage loginpage =new loginPage(DriverFactory.getDriver());
// private passList passListpage =new passList(DriverFactory.getDriver());
// private task_Screen_Approval task_screen=new task_Screen_Approval(DriverFactory.getDriver());
	
// 	WebDriver driver;
// 	public String current_status;
// 	public String passlistname;
// 	@Given("User click on passlist link")
// 	public void clickPassLogin() {
// 	    // Write code here that turns the phrase above into concrete actions
		
// 		passListpage.clickPassList();}
		
// 		@Given("User click on watchlistSources link")
// 		public void clickwatchlistsource() {
// 		    // Write code here that turns the phrase above into concrete actions
// 			passListpage.clicksidebarcontent();
			
// 			passListpage.clickWatchlistSourceList();
			
// 	}
		
// 		@Then("Navigate to the main window")
// 		public void navigate_to_the_main_window() {
// 		    // Write code here that turns the phrase above into concrete actions
// 			passListpage.clickMainWindow();
// 		}

// 		@And("Click and create the passlist")
// 		public void click_and_create_the_passlist(DataTable dt) {
			
// 			List<String> asList = dt.asList();
// 			passlistname =asList.get(0);
// 			String reason =asList.get(1);
// 		    // Write code here that turns the phrase above into concrete actions
// 		    passListpage.clickNewList();
// 		    passListpage.enterPrivatePasslistDetails(passlistname,reason);
// 		}

// 		@And("validate that the passlist is created")
// 		public void validate_that_the_passlist_is_created() {
// 			passListpage.passlistCreatedValidation();
// 		    // Write code here that turns the phrase above into concrete actions
		    
// 		}
// 		@And("Search passlist and get status")
// 		public void search_passlist_and_get_status(DataTable dataTable) throws InterruptedException {
// 			List<String> asList = dataTable.asList();
// 			String pass=asList.get(0);
// 			passListpage.searchPassList(pass);
// 			current_status=passListpage.getStatus(pass);
// 			System.out.println("The status is :"+ current_status);
// 		}

// 		@And("user navigate to task screen")
// 		public void navigate_to_task_page() {
			
// 			passListpage.navigateToTasksPage();
			
// 		}
// 		@And("Navigate based on status")
// 		public void navigate_based_on_status() throws InterruptedException {
// 		    // Write code here that turns the phrase above into concrete actions
			
// 			passListpage.navigateToTasksPage();
// 	    	passListpage.navigateToPasslistSection();
// 	    	current_status =passListpage.getTasksStatus(passlistname);
	    	
// 		    if(current_status.contains("Pending"))
		    	
// 		    {
// 		    	if(current_status.contains("Pending-L1")) {
// 		    	System.out.println("The current status is Pending1.. wip");
// 		    	current_status =passListpage.searchPasslistPending1(passlistname);
// 		    	current_status =passListpage.searchPasslistPending2(passlistname);
		    	
// 		    	}
// 		    	else {
// 			    	System.out.println("The current status is Pending2.. wip");
// 			    	current_status =passListpage.searchPasslistPending2(passlistname);
			    	
// 			    	}
		    	
// 		    }
// 		    else if(current_status.contains("Draft"))
// 		    {
// 		    	System.out.println("The current status is Draft.. wip");
// 		    	current_status =passListpage.searchPasslistDraft(passlistname);
// 		    	passListpage.navigateToTasksPage();
// 		    	passListpage.navigateToPasslistSection();
// 		    	current_status =passListpage.getTasksStatus(passlistname);
		    	
// 		    	current_status =passListpage.searchPasslistPending1(passlistname);
// 		    	current_status =passListpage.searchPasslistPending2(passlistname);
		    	
// 		    }
		    
// 		    else if(current_status.contains("Approved"))
// 		    {
		    	
// 		    }
// 		    else
// 		    {
		    	
// 		    }
		
// 		}
		
		
// 		@And("user clicks on passlist link to add records {string}")
// 		public void user_click_passlist_active_record(String passlistname) throws InterruptedException{
// 			System.out.println("Step passlistname :"+passlistname);
// 			passListpage.search_passlist_click_record(passlistname);
			
// 		}
// 		@And("user clicks to add records")
// 		public void get_Single_Reco() throws InterruptedException{
// 			passListpage.get_Single_Reco();}
			
// 			@Then("user add form values {string} {string} and {string}")
// 			public void user_add_form_values_and(String Name, String supress, String datee) throws InterruptedException {
// 				passListpage.input_form_values(Name, supress, datee);
// 			}

// 			@Then("user clicks save as draft button")
// 			public void user_clicks_save_as_draft_button() throws InterruptedException {
// 			    // Write code here that turns the phrase above into concrete actions
// 				passListpage.save_draft();
			  
// 			}

// 			@Then("user clicks to submit button")
// 			public void user_clicks_to_submit_button() throws InterruptedException {
// 			    // Write code here that turns the phrase above into concrete actions
// 				passListpage.submit_apprl();
// 			}
			
// 			@Then("user validate task screen and clicks passlist record button")
// 			public void user_click_passlist_record_button() throws InterruptedException {
// 				String txt=passListpage.task_screen();
// 				System.out.println("Printing task screen txt :"+ txt);
// 				Assert.assertTrue(txt.contains("Tasks"));
// 				passListpage.task_screen_passlist();
				
// 			}
			
// 			@And("user verify status and approves the passlist record {string} {string}")
// 			public void approve_passlist_based_on_status(String nameofrec,String lstname) throws InterruptedException {
// 			    // Write code here that turns the phrase above into concrete actions
				
				
// //				.navigateToTasksPage();
// //		    	passListpage.navigateToPasslistSection();
// 		    	current_status =task_screen.getPassrecordStatus(nameofrec,lstname);
// 		    	System.out.println("current status :" + current_status);
		    	
// 			    if(current_status.contains("Pending"))
			    	
// 			    {
// 			    	if(current_status.contains("Pending-L1")) {
// 			    		Assert.assertTrue(current_status.contains("Pending-L1"));
// 			    	System.out.println("The current status is Pending1.. wip");
// 			    	current_status =task_screen.searchPasslistrecordPending1(nameofrec,lstname);
// 			    	current_status =task_screen.searchPasslistrecordPending2(nameofrec,lstname);
			    	
// 			    	}
// 			    	else {
// 				    	System.out.println("The current status is Pending2.. wip");
// 				    	current_status =task_screen.searchPasslistrecordPending2(nameofrec,lstname);
				    	
// 				    	}
			    	
// 			    }
			    
// 			    else if(current_status.contains("Approved"))
// 			    {
// 			    	Assert.assertTrue(current_status.contains("Approved"));
// 			    }
// 			    else
// 			    {
			    	
// 			    }
			
// 			}
// 			@Then("user enters non manddatory field values {string} {string} {string} {string} {string} {string}")
// 			public void user_enters_non_manddatory_field_values(String addre, String cityi, String suptag, String supressorigi,String supressunitt,String txtarea) throws InterruptedException {
// 			    // Write code here that turns the phrase above into concrete actions
// 				passListpage.input_form_extravalues(addre, cityi, suptag, supressorigi, supressunitt, txtarea);
			    
// 			}

			
// }
