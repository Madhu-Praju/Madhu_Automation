package stepDefenition;

import java.util.Properties;

import org.testng.Assert;

import com.mongodb.client.model.Filters;
import com.pages.EUadvfilterPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.MongoDBUtil;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EUadvfilterStep {

    String url;
    String ornm;
    String usnm;
    String pswd;

    // EU Filter Data
    String citizenship = "Egypt";
    String designatedate = "12/12/2024";
    String startdate = "30/08/2024";
    String enddate = "22/12/2025";
    String type = "Person";

    // Wrong Input Data
    String citi = "Karnataka";

    EUadvfilterPage euPage = new EUadvfilterPage(DriverFactory.getDriver());

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

    @Given("Facctlist Login 3")
    public void facctlist_login_3() throws InterruptedException {
        euPage.facctlist_login_3(url, ornm, usnm, pswd);
    }

    @And("Navigate to Regulatory List 3")
    public void navigate_to_regulatory_list_3() throws InterruptedException {
        euPage.navigate_to_regulatory_list_3();
    }

    @And("Select List name 3")
    public void select_list_name_3() throws InterruptedException {
        euPage.select_eu_list();
    }

    @And("Apply Filter in all tabs 3")
    public void apply_filter_in_all_tabs_3() throws InterruptedException {
        euPage.apply_eu_filter(citizenship, designatedate, startdate, enddate, type, citi);
    }

    // @And("Check the status 3")
    // public void check_the_status_3() throws InterruptedException {
    //     euPage.check_download_status_3();
    // }
    
}
