package stepDefenition;

import java.util.Properties;

import com.pages.OFACadvfilterPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class OFACadvfilterStep {

    String url;
    String ornm;
    String usnm;
    String pswd;

    // OFAC Filter Data
    String address = "Cuba";
    String citizenship = "Egypt";
    String startdate = "30/08/2024";
    String enddate = "22/12/2025";
    String nationality = "Egypt";
    String program = "CAR";
    String type = "Individual";

    OFACadvfilterPage ofacPage = new OFACadvfilterPage(DriverFactory.getDriver());

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

    @Given("Facctlist Login 1")
    public void facctlist_login_1() throws InterruptedException {
        ofacPage.facctlist_login_1(url, ornm, usnm, pswd);
    }

    @And("Navigate to Regulatory List 1")
    public void navigate_to_regulatory_list_1() throws InterruptedException {
        ofacPage.navigate_to_regulatory_list_1();
    }

    @And("Select List name 1")
    public void select_list_name_1() throws InterruptedException {
        ofacPage.select_ofac_list();
    }

    @And("Apply Filter in all tabs 1")
    public void apply_filter_in_all_tabs_1() throws InterruptedException {
        ofacPage.apply_ofac_filter(address, citizenship, startdate, enddate, nationality, program, type);
    }

    @And("Check the status 1")
    public void check_the_status_1() throws InterruptedException {
        ofacPage.check_download_status_1();
    }
}
