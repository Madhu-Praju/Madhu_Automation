package stepDefenition;

import java.util.Properties;

import com.pages.LsegStandardPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LsegStandardStep {

	LsegStandardPage Lstrd = new LsegStandardPage(DriverFactory.getDriver());

	Properties prop;
	private configReader confReader;

	String url;
	String ornm;
	String usnm;
	String pswd;

	private String path = "//khkhuihuw";
	private String usnm1 = "ABD";
	private String pswd1 = "Abc123";
	private String cnumber = "1234567890";
	private String cstartdate = "12122001";
	private String cenddate = "12122004";
	private String time = "1200";
	private String prepre = "1.10";
	private String preweek = "0.11";
	private String premonth = "99.99";
	private String postpre = "99.99";
	private String postweek = "1.11";
	private String postmonth = "0.99";
	private String cmt = "Submit for Approval";

	@Before
	public void initialization() {
		confReader = new configReader();
		prop = confReader.init_pop();

		url = prop.getProperty("url");
		ornm = prop.getProperty("organisationname");
		usnm = prop.getProperty("username");
		pswd = prop.getProperty("password");
	}

	@Given("Login to the Facctlist")
	public void login_to_the_facctlist() throws InterruptedException {
		Lstrd.login_to_facctlist(url, ornm, usnm, pswd);
	}

	@And("Navigate to Commercial List")
	public void navigate_to_commercial_list() throws InterruptedException {
		Lstrd.navigate_to_commercial_list();
	}

	@And("Creation of Lseg Standard")
	public void creation_of_lseg_standard() throws InterruptedException {
		Lstrd.creation_of_lseg_standard(path, usnm1, pswd1, cnumber, cstartdate, cenddate, time, 
				prepre, preweek, premonth, postpre, postweek, postmonth, cmt);
	}
}
