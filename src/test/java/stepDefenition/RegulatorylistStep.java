// package stepDefenition;

// import java.util.Properties;

// import com.pages.RegulatorylistPage;
// import com.qea.factory.DriverFactory;
// import com.qea.utils.configReader;

// import io.cucumber.java.Before;
// import io.cucumber.java.en.And;
// import io.cucumber.java.en.Given;

// public class RegulatorylistStep {
//     String url;
// 	String ornm;
// 	String usnm;
// 	String pswd;

// 	// OFAC
// 	String address = "Cuba";
// 	String citizenship = "Egypt";
// 	String startdate = "30/08/2024";
// 	String enddate = "22/12/2025";
// 	String nationality = "Egypt";
// 	String program = "CAR";
// 	String type = "Individual";

// 	// UK SANCTIONS
// 	String designateddate = "29/07/2012";
// 	String idtype = "IMONumber";
// 	String programsource = "UK|UN";
// 	String regimename = "The Iran (Sanctions) Regulations 2023";

// 	// UN
// 	String address1 = "China";
// 	String designateddate1 = "30/06/2024";
// 	String entity = "Individual";
// 	String startdate1 = "12/12/2024";
// 	String enddate1 = "03/01/2026";
// 	String program1 = "CAR";

//     RegulatorylistPage regPage = new RegulatorylistPage(DriverFactory.getDriver());

//     Properties prop;
//     private configReader confReader;

//     @Before
//     public void initialization() {
//         confReader = new configReader();
//         prop = confReader.init_pop();
//     }

//     @Given("Login to Facctlist")
//     public void login_to_facctlist() throws InterruptedException {
//         String url = prop.getProperty("url");
//         String ornm = prop.getProperty("organisationname");
//         String usnm = prop.getProperty("username");
//         String pswd = prop.getProperty("password");
//         regPage.facctlist_login(url, ornm, usnm, pswd);
//     }

//     @Given("Click on List managemant")
//     public void click_on_list_management() throws InterruptedException {
//         regPage.click_List_Management();
//     }

//     @Given("Navigate to Watchlist")
//     public void navigate_to_watchlist() throws InterruptedException {
//         regPage.navigate_to_watchlist();
//     }

//     @Given("Navigate to Regulatory list")
//     public void navigate_to_regulatory_list() throws InterruptedException {
//         regPage.navigate_to_regulatory_list();
//     }

//     @Given("Click on the OFAC List")
//     public void click_on_ofac_list() throws InterruptedException {
//         regPage.click_on_ofac_list();
//     }

//     @Given("Click on the UN List")
//     public void click_on_un_list() throws InterruptedException {
//         regPage.click_on_un_list();
//     }

//     @Given("Click on the EU List")
//     public void click_on_eu_list() throws InterruptedException {
//         regPage.click_on_eu_list();
//     }

//     @Given("Click on the UK Sanction List")
//     public void click_on_uk_sanction_list() throws InterruptedException {
//         regPage.click_on_uk_sanction_list();
//     }


//     // Active Tab Filter Steps
//     @Given("click on Active Tab")
//     public void click_on_active_tab() throws InterruptedException {
//         regPage.click_on_active_tab();
//     }

//     @And("click on filter icon")
//     public void click_on_filter_icon() throws InterruptedException {
//         regPage.click_on_filter_icon();
//     }

//     @And("apply the Single filter")
//     public void select_the_filter() throws InterruptedException {
//         regPage.select_the_filter();
//     }

//     @And("apply all filter")
//     public void click_on_clear_all() throws InterruptedException {
//         regPage.click_on_clear_all();
//     }

//     @And("Click on close")
//     public void click_on_close() throws InterruptedException {
//         regPage.click_on_close();
//     }

//     @And("Click on Active tab Filter")
//     public void click_on_active_tab_filter() throws InterruptedException {
//         regPage.click_on_active_tab_filter();
//     }

//     @And("Choose the Active tab Filter")
//     public void choose_the_active_tab_filter() throws InterruptedException {
//         regPage.choose_the_active_tab_filter();
//     }

//     @And("Click on Apply")
//     public void click_on_apply() throws InterruptedException {
//         regPage.click_on_apply();
//     }

//     // Error Tab Filter Steps
//     @Given("click on Error Tab")
//     public void click_on_error_tab() throws InterruptedException {
//         regPage.click_on_error_tab();
//     }

//     @And("Click on Error tab Filter")
//     public void click_on_error_tab_filter() throws InterruptedException {
//         regPage.click_error_tab_filter();
//     }

//     @And("Choose the Error tab Filter")
//     public void choose_the_error_tab_filter() throws InterruptedException {
//         regPage.choose_the_error_tab_filter();
//     }

//     // Delete Tab Filter Steps
//     @Given("click on Delete Tab")
//     public void click_on_delete_tab() throws InterruptedException {
//         regPage.click_on_delete_tab();
//     }

//     @And("Click on Delete tab Filter")
//     public void click_on_delete_tab_filter() throws InterruptedException {
//         regPage.click_on_delete_tab_filter();
//     }

//     @And("Choose the Delete tab Filter")
//     public void choose_the_delete_tab_filter() throws InterruptedException {
//         regPage.choose_the_delete_tab_filter();
//     }

//     // Delta/New Tab Filter Steps
//     @Given("click on the delta Toggle")
//     public void click_on_delta_toggle() throws InterruptedException {
//         regPage.click_on_the_delta_toggle();
//     }

//     @And("Click on the New Tab")
//     public void click_on_new_tab() throws InterruptedException {
//         regPage.click_on_the_new_tab();
//     }

//     @And("Click on New tab Filter")
//     public void click_on_new_tab_filter() throws InterruptedException {
//         regPage.click_on_new_tab_filter();
//     }

//     @And("Choose the New tab Filter")
//     public void choose_the_new_tab_filter() throws InterruptedException {
//         regPage.choose_the_new_tab_filter();
//     }


//     // Amend Tab Filter Steps
//     @Given("click on Amend Tab")
//     public void click_on_amend_tab() throws InterruptedException {
//         regPage.click_on_amend_tab();
//     }

//     @And("Click on Amend tab Filter")
//     public void click_on_amend_tab_filter() throws InterruptedException {
//         regPage.click_on_amend_tab_filter();
//     }

//     @And("Choose the Amend tab Filter")
//     public void choose_the_amend_tab_filter() throws InterruptedException {
//         regPage.choose_the_amend_tab_filter();
//     }

//     // Delta Delete Tab Filter Steps
//     @Given("click on Delta delete Tab")
//     public void click_on_d_delete_tab() throws InterruptedException {
//         regPage.click_on_delta_delete_tab();
//     }

//     @And("Click on Delata delete tab Filter")
//     public void click_on_delta_delete_tab_filter() throws InterruptedException {
//         regPage.click_on_delta_delete_tab_filter();
//     }

//     @And("Choose the Delata delete tab Filter")
//     public void choose_the_delta_delete_tab_filter() throws InterruptedException {
//         regPage.choose_the_delta_delete_tab_filter();
//     }

//     // Stable Tab Filter Steps
//     @Given("click on Stable Tab")
//     public void click_on_stable_tab() throws InterruptedException {
//         regPage.click_on_stable_tab();
//     }

//     @And("Click on Stable tab Filter")
//     public void click_on_stable_tab_filter() throws InterruptedException {
//         regPage.click_on_stable_tab_filter();
//     }

//     @And("Choose the Stable tab Filter")
//     public void choose_the_stable_tab_filter() throws InterruptedException {
//         regPage.choose_the_stable_tab_filter();
//     }

//     // Delta Error Tab Filter Steps
//     @Given("click on Delta Error Tab")
//     public void click_on_d_error_tab() throws InterruptedException {
//         regPage.click_on_delta_error_tab();
//     }

//     @And("Click on Delta Error tab Filter")
//     public void click_on_d_error_tab_filter() throws InterruptedException {
//         regPage.click_on_delta_error_tab_filter();
//     }

//     @And("Choose the Delta Error tab Filter")
//     public void choose_the_d_error_tab_filter() throws InterruptedException {
//         regPage.choose_the_delta_error_tab_filter();
//     }

//     // Download Steps
//     @Given("Click on the Download icon")
//     public void click_on_download_icon() throws InterruptedException {
//         regPage.click_on_the_download_icon();
//     }

//     @And("click on the Excel")
//     public void click_on_excel() throws InterruptedException {
//         regPage.click_on_the_excel();
//     }

//     @And("click on the Tsv")
//     public void click_on_tsv() throws InterruptedException {
//         regPage.click_on_the_tsv();
//     }

//     // Downloads Page Steps
//     @Given("Click on the Downloads tab")
//     public void click_on_downloads_tab() throws InterruptedException {
//         regPage.click_on_the_downloads_tab();
//     }

//     @And("Verify the Download status")
//     public void verify_download_status() throws InterruptedException {
//         regPage.verify_the_download_status();
//     }

//     @And("Click on the download")
//     public void click_on_the_download() throws InterruptedException {
//         regPage.click_on_the_download();
//     }
// }
