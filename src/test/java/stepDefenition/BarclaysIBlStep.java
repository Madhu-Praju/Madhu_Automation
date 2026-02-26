package stepDefenition;

import java.util.Properties;

import com.pages.BarclaysIblPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class BarclaysIBlStep {

	String url;
	String ornm;
	String usnm;
	String pswd;

	String pfirstname = "Ali";
	String pmiddlename1 = "Mid 1";
	String pmiddlename2 = "Mid 2";
	String pmiddlename3 = "Mid 3";
	String pmiddlename4 = "Mid 4";
	String pmiddlename5 = "Mid 5";
	String plastname = "Pasha";

	String afirstname = "Mohammed";
	String amiddlename1 = "Mid 1";
	String amiddlename2 = "Mid 2";
	String amiddlename3 = "Mid 3";
	String amiddlename4 = "Mid 4";
	String amiddlename5 = "Mid 5";
	String alastname = "Ali";

	String job = "CEO";
	String position = "CEO";
	String gender = "Male";
	String deceased = "Yes";

	String dob = "2001/12/12";
	String dod = "2002/12/12";
	String adob = "2003/12/12";

	String birthtown = "Nepal";

	String accountno = "83636";
	String customerid = "43463";
	String governmentid = "029272";
	String passport = "5342627";
	String relatedid = "63542327";

	String add1 = "Address 1";
	String add2 = "Address 2";
	String city = "Lahore";
	String state = "Pakistan";
	String zipcode = "736363";

	String wcid = "73736";
	String loc = "Location 1";

	String bucontact = "a@a.com";
	String expiryvalue = "2028/12/12";

	String reason = "Reason 123";
	String comments = "Comments 123";
	String furthercomm = "Further Comments 123";
	String refinfo = "Ref info 123";

	String submitcomment = "Review and Approve";

	String pfirstname1 = "Ali1";
	String plastname1 = "Pasha 1";
	String expiryvalue1 = "2029/12/12";
	String comments1 = "Comments 456";

	BarclaysIblPage Bibl = new BarclaysIblPage(DriverFactory.getDriver());

	Properties prop;
	private configReader confReader;

	@Before
	public void initialization() {
		confReader = new configReader();
		prop = confReader.init_pop();

		url = prop.getProperty("url");
		ornm = prop.getProperty("organisationname");
		usnm = prop.getProperty("username");
		pswd = prop.getProperty("password");
	}

	@Given("Login to facctlist App")
	public void login_to_facctlist_app() throws InterruptedException {
		Bibl.login_to_facctlist_app(url, ornm, usnm, pswd);
	}

	@And("Navigate to Internal List")
	public void navigate_to_internal_list() throws InterruptedException {
		Bibl.navigate_to_internal_list();
	}

	@And("Select List Name")
	public void select_list_name() throws InterruptedException {
		Bibl.select_list_name();
	}

	@And("Add Record")
	public void add_record() {
		Bibl.add_single_record();
	}

	@And("Select Single Record")
	public void select_single_record() throws InterruptedException {
		Bibl.select_single_record();
	}

	@And("Select Entity Type")
	public void select_entity_type() {
		Bibl.select_entity_type();
	}

	@And("Add all Basic Details")
	public void add_all_basic_details() {
		Bibl.add_all_basic_details(pfirstname, pmiddlename1, pmiddlename2, pmiddlename3, pmiddlename4, pmiddlename5, plastname,
				afirstname, amiddlename1, amiddlename2, amiddlename3, amiddlename4, amiddlename5, alastname,
				job, position, gender, deceased, dob, dod, adob, birthtown,
				accountno, customerid, governmentid, passport, relatedid,
				add1, add2, city, state, zipcode,
				wcid, loc);
	}

	@And("Add all Additional Details")
	public void add_all_additional_details() {
		Bibl.add_all_additional_details(bucontact, expiryvalue, reason, comments, furthercomm, refinfo);
	}

	@And("Click on Submit for Approval")
	public void click_on_submit_for_approval() throws InterruptedException {
		Bibl.click_on_submit_for_approval(submitcomment);
	}

	@And("Withdraw the Record")
	public void withdraw_the_record() throws InterruptedException {
		Bibl.withdraw_the_record();
	}

	@And("Navigate to Reject Tab")
	public void navigate_to_reject_tab() throws InterruptedException {
		Bibl.navigate_to_reject_tab();
	}

	@And("Select the Record")
	public void select_the_record() throws InterruptedException {
		Bibl.select_the_record();
	}

	@And("Amend the Record")
	public void amend_the_record() {
		Bibl.amend_the_record(pfirstname1, plastname1, expiryvalue1, comments1);
	}

	@And("Submit the Record")
	public void submit_the_record() throws InterruptedException {
		Bibl.submit_the_record(submitcomment);
	}
}
