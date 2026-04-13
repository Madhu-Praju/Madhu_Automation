package stepDefenition;

import java.util.Properties;

import org.testng.Assert;

import com.mongodb.client.model.Filters;
import com.pages.UKSANCTIONSadvfilterPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.MongoDBUtil;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UKSANCTIONSadvfilterStep {

    String url;
    String ornm;
    String usnm;
    String pswd;

    // UK SANCTIONS
	String designateddate = "29/07/2012";
	String idtype = "IMONumber";
	String programname = "UK|UN";
	String regimename = "The Iran (Sanctions) Regulations 2023";
    String type = "Individual";

    //Wrong Input
    String id = "Adhar";

    UKSANCTIONSadvfilterPage uksanctionsPage = new UKSANCTIONSadvfilterPage(DriverFactory.getDriver());

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

    @Given("Facctlist Login 2")
    public void facctlist_login_2() throws InterruptedException {
        uksanctionsPage.facctlist_login_2(url, ornm, usnm, pswd);
    }

    @And("Navigate to Regulatory List 2")
    public void navigate_to_regulatory_list_2() throws InterruptedException {
        uksanctionsPage.navigate_to_regulatory_list_2();
    }

    @And("Select List name 2")
    public void select_list_name_2() throws InterruptedException {
        uksanctionsPage.select_uksanctions_list();
    }

    @And("Apply Filter in all tabs 2")
    public void apply_filter_in_all_tabs_2() throws InterruptedException {
        uksanctionsPage.apply_uksanctions_filter(designateddate, idtype, programname, regimename, type, id);
    }

    @And("Check the status 2")
    public void check_the_status_2() throws InterruptedException {
        uksanctionsPage.check_download_status_2();
    }
}
