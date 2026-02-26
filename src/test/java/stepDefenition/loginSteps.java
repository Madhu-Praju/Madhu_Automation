package stepDefenition;

import java.time.Duration;
import java.util.Properties;

import io.cucumber.java.en.And;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.pages.FacctViewLoginPage;
import com.pages.FacctViewWelcomePage;
import com.pages.loginPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginSteps {
	
//	WebDriver driver;
	Properties prop;
	private WebDriver driver;
	private configReader confReader;
	private loginPage loginpage;
	
	@Before
	public void setUp() {
		driver = DriverFactory.getDriver();
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Please check your hooks setup.");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		loginpage = new loginPage(DriverFactory.getDriver());
		DriverFactory.getDriver().manage().deleteAllCookies();
	}
	
	@Given("I Open url {string}")
	public void i_Open_URL(String URL) {

		confReader = new configReader();
		prop = confReader.init_pop();
		DriverFactory.getDriver().get(prop.getProperty(URL));
		driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Login to FacctList Application");
	   
	}
	@And("User validate Welcome page elements")
	public void userValidateWelcomePageElements() {
		// Write code here that turns the phrase above into concrete actions
		String welcomeText = loginpage.getwelcometext();
		Assert.assertNotNull(welcomeText, "Welcome text element should not be null");
		Assert.assertEquals(welcomeText, "Welcome");

		WebElement facctumLogo = loginpage.getFacctumLogoElement();
		// Assert.assertTrue(facctumLogo.isDisplayed(), "Facctum logo should be displayed");
		System.out.println("Facctum logo is diaplayed: " + facctumLogo.isDisplayed());

		String welcomeParagraph = loginpage.welcomePagepara();
		Assert.assertNotNull(welcomeParagraph, "Welcome paragraph should not be null");


		System.out.println("Welcome page elements validated successfully.");
	}

	@Given("User click on login button on Welcome page")
	public void iClickOnLoginButtonOnWelcomePage() {
		loginpage.ClickLoginButton();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));

	}

	@Given("User validates welcome texts")
	public void user_validates_welcome_texts() {	
		// Write code here that turns the phrase above into concrete actions
		String welcometext = loginpage.getFirstPageText();
		Assert.assertNotNull(welcometext, "Welcome text should not be null");
		Assert.assertFalse(welcometext.trim().isEmpty(), "Welcome text should not be empty");
		System.out.println("Welcome text: " + welcometext);
		
	}
	@Given("user enters the organization name {string}")
	public void user_enters_the_organization_name(String orgName) {
		loginpage.enterOrgName(orgName);
	}
	@Given("user presses continue key")
	public void user_press_continue_key() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.clickOnContinue();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	}


	
	// @Given("I verify login page by welcome text")
	// 		public void i_validate_login_page_text(String logintext) throws InterruptedException {
	// 		Thread.sleep(2000);
	// 		String lgtext=loginpage.getfirstPageText();
	// 		Assert.assertTrue(lgtext.contains("Welcome to"));
			
	// 		System.out.println("Page Title is:" +loginpage.getfirstPageText());
	// }
	
	// @Given("I click on login button")
	// public void i_click_on_login_button() {
	//     loginpage.clickLogin();;
	// 	// Write code here that turns the phrase above into concrete actions
	 
	// }
	

	// @Given("I press continue key to Login to Dashboard")
	// public void i_press_continue_key_to() {
	//     // Write code here that turns the phrase above into concrete actions
	// 	loginpage.clickOnContinue();
	 
	// }
	

	@When("user enters facctlist login id {string}")
    public void user_enters_facctlist_login_id(String loginId){
		confReader = new configReader();
		prop = confReader.init_pop();
		loginpage.enterloginID(prop.getProperty(loginId));
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		// loginpage.enterUserName(prop.getProperty(loginId));
		// loginpage.enterUserName(loginId);
//		List<String> asList = dt.asList();
//		String emailid =asList.get(0);
//		String reason =asList.get(1);
//	    // DataTable dt Write code here that turns the phrase above into concrete actions
//	    passListpage.clickNewList();
//	    passListpage.enterPrivatePasslistDetails(passlistname,reason);
		//String emailId="Globalhead@natwestpoc.com";
		// String emailId="globalhead@datavium.com";
		//String emailId="nwgh@natwestpoc.com";
		// loginpage.enterUserName(emailId);
	    // Write code here that turns the phrase above into concrete actions
	  }
    
    @When("user enters the Password {string}")
	public void user_enters_the_password(String passwd) {
	    // System.out.println("[DEBUG] Password being entered: '" + passwd + "'");
		loginpage.enterPass(prop.getProperty(passwd));
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));		
	    
	  
	}
	
	@Then("validate clickable forgot password text")
	public void validate_clickable_forgot_password_text() {
		// Write code here that turns the phrase above into concrete actions
		boolean forgotPwdLink = loginpage.getForgotPwdLinkElement();
		Assert.assertTrue(forgotPwdLink, "Forgot password link should be clickable");
		System.out.println("Forgot password link is enabled: " + forgotPwdLink);
		// Assert.assertTrue(loginpage.isForgotPwdLinkExist(), "Forgot password link should be present");
	}
	@When("user presses continue key to Login to Home page")
	public void user_clicks_on_SignIn_button() {
	    // Write code here that turns the phrase above into concrete actions
		loginpage.clickContinue();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
		System.out.println("Login to FacctList Application");
	}

	@Then("user navigates to the Home page")
	public void user_navigates_to_the_dashboard() {
	    // Write code here that turns the phrase above into concrete actions
	    String greettext = loginpage.getGreetText();
			
	    Assert.assertNotNull(greettext, "Greeting should not be null");

	    // Assert.assertFalse(greettext.trim().isEmpty(), "Page title should not be empty");
	    System.out.println("Greeting text:" + greettext);
	}
	@Then("validate that the user is on the {string} page")
	public void validate_that_the_user_is_on_the_dashboard_page(String pageNametext) throws InterruptedException {
		// Set page zoom to 80%
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("document.body.style.zoom='67%'");
		Thread.sleep(10000);

		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		pageNametext = loginpage.getpageNameText();
		// Assert.assertNotNull(Dashtext, "Dashboard text should not be null");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		System.out.println("User is on " + pageNametext + " page");
		Assert.assertTrue(pageNametext.contains(pageNametext), "Page text should contain '" + pageNametext + "'");
	}

	@Then("user validates the tenant name {string}")
	public void user_validates_the_tenant_name(String tenant) {
		// Write code here that turns the phrase above into concrete actions
		String tenantkey = loginpage.validateTenant();
		Assert.assertNotNull(tenantkey, "Tenant name should not be null");
		
		System.out.println("Tenant Name: " + tenantkey);
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	}
	
	@Then("user validates the login id {string}")
	public void user_validates_the_login_id(String userID) {
		// Write code here that turns the phrase above into concrete actions
		String loginId = loginpage.validateLoginId();
		Assert.assertNotNull(loginId, "Login ID should not be null");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		System.out.println("Login ID: " + loginId);
		
	}
	@Then("page title should be {string}")
	public void page_title_should_be(String titlepg) {
	    // Write code here that turns the phrase above into concrete actions
		// // String title=loginpage.titlePage();
		// Assert.assertTrue(title.contains(titlepg));
		
		// System.out.println("Page Title is:" +title); 
	   
	}
	@Given("I click on URL")
	public void I_click_on_URL() {
		String url = prop.getProperty("app.url");
		DriverFactory.getDriver().get(url);
		System.out.println("Navigated to URL: " + url);
	}
    
    @Given("I Opean url {string}")
    public void I_Opean_url(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    // Common Login Steps for Reusability
    @Given("User perform complete login with {string} {string} {string}")
    public void user_perform_complete_login_with(String tenantId, String userID, String password) {
        // This method reuses existing step definitions to perform complete login
        System.out.println("Starting complete login process...");
        
        user_enters_the_organization_name(tenantId);
        user_press_continue_key();
        user_enters_facctlist_login_id(userID);
        user_enters_the_password(password);
        user_clicks_on_SignIn_button();
        user_navigates_to_the_dashboard();
        
        System.out.println("Complete login process finished successfully.");
    }
    
    @Given("I perform complete login to home page with {string} {string} {string}")
    public void i_perform_complete_login_to_home_page_with(String tenantId, String userID, String password) {
        // This method reuses existing step definitions for login to home page
        System.out.println("Starting complete login to home page process...");
        
        user_enters_the_organization_name(tenantId);
        user_press_continue_key();
        user_enters_facctlist_login_id(userID);
        user_enters_the_password(password);
        user_clicks_on_SignIn_button();
        user_navigates_to_the_dashboard();
        
        try {
            validate_that_the_user_is_on_the_dashboard_page("Home");
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to validate home page", e);
        }
        
        System.out.println("Complete login to home page process finished successfully.");
    }
    
    @Given("I perform complete login with validation using {string} {string} {string}")
    public void i_perform_complete_login_with_validation_using(String tenantId, String userID, String password) {
        // This method performs complete login with all validations
        System.out.println("Starting complete login with validation process...");
        
        user_enters_the_organization_name(tenantId);
        user_press_continue_key();
        user_enters_facctlist_login_id(userID);
        user_enters_the_password(password);
        validate_clickable_forgot_password_text();
        user_clicks_on_SignIn_button();
        user_navigates_to_the_dashboard();
        
        try {
            validate_that_the_user_is_on_the_dashboard_page("Home");
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to validate home page", e);
        }
        
        user_validates_the_tenant_name(tenantId);
        user_validates_the_login_id(userID);
        
        System.out.println("Complete login with validation process finished successfully.");
    }



}
