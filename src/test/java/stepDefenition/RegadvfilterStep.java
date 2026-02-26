package stepDefenition;

import java.util.Properties;

import com.pages.RegadvfilterPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class RegadvfilterStep {
	String url;
	String ornm;
	String usnm;
	String pswd;

	// OFAC
	String address = "Cuba";
	String citizenship = "Egypt";
	String startdate = "30/08/2024";
	String enddate = "22/12/2025";
	String nationality = "Egypt";
	String program = "CAR";
	String type = "Individual";

	// UK SANCTIONS
	String designateddate = "29/07/2012";
	String idtype = "IMONumber";
	String programsource = "UK|UN";
	String regimename = "The Iran (Sanctions) Regulations 2023";

	// UN
	String address1 = "China";
	String designateddate1 = "30/06/2024";
	String entity = "Individual";
	String startdate1 = "12/12/2024";
	String enddate1 = "03/01/2026";
	String program1 = "CAR";

	RegadvfilterPage Raf = new RegadvfilterPage(DriverFactory.getDriver());

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

	@Given("Facctlist Login")
	public void facctlist_login() throws InterruptedException {
		Raf.facctlist_login(url, ornm, usnm, pswd);
	}

	@And("Navigate to Regulatory List")
	public void navigate_to_regulatory_list() throws InterruptedException {
		Raf.navigate_to_regulatory_list();
	}

	@And("Select List name")
	public void select_list_name() throws InterruptedException {
		Raf.select_list_name();
	}

	@And("Select Tab")
	public void select_tab() {
		Raf.select_tab();
	}

	@And("Apply Filter")
	public void apply_filter() throws InterruptedException {
		Raf.apply_filter(address, citizenship, startdate, enddate, nationality, program, type,
				designateddate, idtype, programsource, regimename,
				address1, designateddate1, entity, startdate1, enddate1, program1);
	}

	@And("Check the status")
	public void check_the_status() throws InterruptedException {
		Raf.check_status();
	}
}
