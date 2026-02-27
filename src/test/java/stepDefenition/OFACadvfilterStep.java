package stepDefenition;

import java.util.Properties;

import org.testng.Assert;

import com.mongodb.client.model.Filters;
import com.pages.OFACadvfilterPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.MongoDBUtil;
import com.qea.utils.configReader;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

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

    // @And("Check the status 1")
    // public void check_the_status_1() throws InterruptedException {
    //     ofacPage.check_download_status_1();
    // }

    // --- DB Validation Steps ---

    private int uiCount;
    private long dbCount;

    @And("Apply Address Country filter with {string}")
    public void apply_address_country_filter(String country) throws InterruptedException {
        ofacPage.apply_address_country_filter(country);
    }

    @And("Capture the filtered record count from UI")
    public void capture_filtered_record_count_from_ui() throws InterruptedException {
        uiCount = ofacPage.capture_filtered_count();
        System.out.println("[Step] UI Filtered Count: " + uiCount);
    }

    @And("Fetch the record count from MongoDB for address country {string}")
    public void fetch_record_count_from_mongodb(String country) {
        MongoDBUtil mongoUtil = new MongoDBUtil();
        try {
            mongoUtil.connect();
            String collectionName = prop.getProperty("mongo.collection.ruleclass", "facctumRegulatoryList");

            // Use findDocuments instead of getCount to avoid countDocuments() compatibility issues
            java.util.List<java.util.Map<String, String>> results = mongoUtil.findDocuments(collectionName,
                Filters.and(
                    Filters.eq("listName", "OFAC"),
                    Filters.eq("statusId", 2000),
                    Filters.regex("addressDetailsList.countryName", country, "i")
                ),
                java.util.Arrays.asList("primaryName")
            );
            dbCount = results.size();
            System.out.println("[Step] MongoDB Count for OFAC Active records with address country '" + country + "': " + dbCount);
        } finally {
            mongoUtil.disconnect();
        }
    }

    @Then("Validate UI count matches MongoDB count")
    public void validate_ui_count_matches_mongodb_count() {
        System.out.println("[Validation] UI Count: " + uiCount + " | DB Count: " + dbCount);
        Assert.assertEquals(uiCount, (int) dbCount,
            "UI filtered count (" + uiCount + ") does not match MongoDB count (" + dbCount + ")");
        System.out.println("[Validation] PASSED - UI count matches MongoDB count: " + uiCount);
    }
}
