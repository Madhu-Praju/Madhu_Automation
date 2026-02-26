package stepDefenition;

import java.util.Properties;

import com.pages.CommercialApprovalPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CommercialApprovalStep {

	CommercialApprovalPage cap = new CommercialApprovalPage(DriverFactory.getDriver());

	Properties prop;
	private configReader confReader;

	String url;
	String ornm;
	String usnm;
	String pswd;

	@Before
	public void initialization() {
		confReader = new configReader();
		prop = confReader.init_pop();

		url = prop.getProperty("url");
		ornm = prop.getProperty("organisationname");
		usnm = prop.getProperty("username1");
		pswd = prop.getProperty("password1");
	}

	@Given("Login to Facctlist")
	public void login_to_facctlist() throws InterruptedException {
		cap.login_to_facctlist(url, ornm, usnm, pswd);
	}

	@And("Navigate to Task Page")
	public void navigate_to_task_page() throws InterruptedException {
		cap.navigate_to_task_page();
	}

	@And("Approval of Commercial List")
	public void approval_of_commercial_list() throws InterruptedException {
		cap.approval_of_commercial_list();
	}
}
