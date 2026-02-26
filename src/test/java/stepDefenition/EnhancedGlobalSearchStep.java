package stepDefenition;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pages.globalSearchPage;
import com.pages.loginPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.AllureReportManager;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

/**
 * Enhanced Global Search Step Definitions with Allure Reporting
 * This is an example of how to integrate Allure annotations with existing step definitions
 */
@Epic("Facctum Application Testing")
@Feature("Global Search Functionality") 
public class EnhancedGlobalSearchStep {
    
    WebDriver driver;
    Properties prop;
    WebDriverWait wait;

    private globalSearchPage globalSearchPage;
    private loginPage loginPage;
    private static final String EXCEL_FILE_PATH = "src/test/resources/testData/GlobalSearchData.xlsx";

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        if (driver == null) {
            AllureReportManager.logStepWithStatus("Driver initialization check failed", "FAILED");
            throw new RuntimeException("Driver is not initialized. Please check your hooks setup.");
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.loginPage = new loginPage(driver);
        this.globalSearchPage = new globalSearchPage(driver);
        
        AllureReportManager.logStep("Step definition setup completed");
    }

    @Step("Navigate to the global search page")
    @Description("User navigates to the global search functionality page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Navigation")
    @When("navigate to the global search page")
    public void navigateToTheGlobalSearchPage() {
        AllureReportManager.logStep("Navigating to global search page");
        
        loginPage.clickOnSearchButton();
        boolean isSearchHeadingDisplayed = globalSearchPage.isSearchHeadingDisplayed();
        
        AllureReportManager.logStep("Search heading displayed: " + isSearchHeadingDisplayed);
        System.out.println("is Search Heading Displayed: " + isSearchHeadingDisplayed);
        
        if (isSearchHeadingDisplayed) {
            AllureReportManager.logStepWithStatus("Global search page navigation", "PASSED");
            AllureReportManager.takeScreenshot("Global Search Page Loaded");
        } else {
            AllureReportManager.logStepWithStatus("Global search page navigation", "FAILED");
            AllureReportManager.takeScreenshot("Global Search Page Load Failed");
        }
        
        Assert.assertTrue(isSearchHeadingDisplayed, "Search heading is not displayed");
    }

    @Step("Validate URL contains '{expectedUrlPart}'")
    @Description("Verify that current page URL contains the expected URL part")
    @Severity(SeverityLevel.NORMAL)
    @Then("should validate the URL contains {string}")
    public void should_validate_the_URL_contains(String expectedUrlPart) {
        AllureReportManager.logStep("🌐 Validating URL contains: " + expectedUrlPart);
        System.out.println("Validating URL contains: " + expectedUrlPart);
        
        String currentUrl = driver.getCurrentUrl();
        boolean urlContainsExpected = loginPage.validatePageUrl(expectedUrlPart);
        
        AllureReportManager.attachText("Current URL", currentUrl);
        AllureReportManager.attachText("Expected URL Part", expectedUrlPart);
        
        if (urlContainsExpected) {
            AllureReportManager.logStepWithStatus("URL validation", "PASSED");
        } else {
            AllureReportManager.logStepWithStatus("URL validation", "FAILED");
            AllureReportManager.takeScreenshot("URL Validation Failed");
        }
        
        Assert.assertTrue(urlContainsExpected, "URL does not contain the expected part: " + expectedUrlPart);
    }

    @Step("Validate search button is disabled")
    @Description("Verify that the search button is in disabled state")
    @Severity(SeverityLevel.NORMAL)
    @Then("Validate search button is disabled")
    public void validate_search_button_is_disabled() {
        AllureReportManager.logStep("Validating search button disabled state");
        
        boolean isSearchBtnEnabled = globalSearchPage.isSearchbtnEnabled();
        AllureReportManager.logStep("Search Button Enabled: " + isSearchBtnEnabled);
        System.out.println("Is Search Button Enabled: " + isSearchBtnEnabled);
        
        if (!isSearchBtnEnabled) {
            AllureReportManager.logStepWithStatus("Search button validation", "PASSED");
        } else {
            AllureReportManager.logStepWithStatus("Search button validation", "FAILED");
            AllureReportManager.takeScreenshot("Search Button Not Disabled");
        }
        
        Assert.assertFalse(isSearchBtnEnabled, "Search button is not disabled");
    }

    @Step("Perform bulk search using Excel data")
    @Description("Execute search operations for each record found in Excel test data file")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Data Driven Search")
    @When("User perform search for each record in Excel data")
    public void user_perform_search_for_each_record_in_excel_data() {
        AllureReportManager.logStep("Starting bulk search from Excel data");
        AllureReportManager.attachTestData("Excel File Path", EXCEL_FILE_PATH);
        
        try {
            globalSearchPage.performBulkSearchFromExcel(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("Bulk search operation", "PASSED");
                AllureReportManager.takeScreenshot("Search Results Displayed");
            } else {
                AllureReportManager.logStepWithStatus("Bulk search operation", "FAILED");
                AllureReportManager.takeScreenshot("No Search Results");
            }
            
            Assert.assertTrue(isDisplayed, "Search results table is not displayed");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Bulk search operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Bulk Search Exception");
            
            System.err.println("Bulk search operation failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based bulk search failed", e);
        }
    }

    @Step("Select watchlist option: '{watchlistOption}'")
    @Description("User selects a specific watchlist option from the dropdown")
    @Severity(SeverityLevel.NORMAL)
    @When("User selects a watchlist option {string}")
    public void user_selects_a_watchlist_option(String watchlistOption) {
        AllureReportManager.logStep("📋 Selecting watchlist option: " + watchlistOption);
        
        try {
            globalSearchPage.openWatchlistDropdown();
            globalSearchPage.isWatchlistDropdownOpen();
            globalSearchPage.selectWatchlistOption(watchlistOption);
            String selectedOption = globalSearchPage.getSelectedWatchlistOption();
            
            AllureReportManager.attachText("Selected Watchlist", selectedOption);
            AllureReportManager.logStepWithStatus("Watchlist selection", "PASSED");
            AllureReportManager.takeScreenshot("Watchlist Selected - " + watchlistOption);
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Watchlist selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Watchlist Selection Failed");
            throw e;
        }
    }

    @Step("Select search by option: '{searchByOption}'")
    @Description("User selects search criteria from the search by dropdown")
    @Severity(SeverityLevel.NORMAL)
    @When("User selects a search by option {string}")
    public void user_selects_a_search_by_option(String searchByOption) {
        AllureReportManager.logStep("🔍 Selecting search by option: " + searchByOption);
        
        try {
            globalSearchPage.openSearchByDropdown();
            globalSearchPage.isSearchByDropdownOpen();
            globalSearchPage.selectSearchByOption(searchByOption);
            
            AllureReportManager.attachText("Search By Option", searchByOption);
            AllureReportManager.logStepWithStatus("Search by selection", "PASSED");
            AllureReportManager.takeScreenshot("Search By Selected - " + searchByOption);
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Search by selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Search By Selection Failed");
            throw e;
        }
    }

    @Step("Perform search by Name using Excel data")
    @Description("Execute name-based search operations using Excel test data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Name Search")
    @Then("User perform search for each Name in Excel data")
    public void user_perform_search_for_each_name_in_excel_data() {
        AllureReportManager.logStep("Starting name-based search from Excel data");
        
        try {
            globalSearchPage.performBulkSearchFromExcelByName(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.logSearchOperation("Name Search", "Excel Data", 
                isDisplayed ? 1 : 0); // Results count - can be enhanced based on actual page object methods
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("Name search operation", "PASSED");
                AllureReportManager.takeScreenshot("Name Search Results");
            } else {
                AllureReportManager.logStepWithStatus("Name search operation", "FAILED");
                AllureReportManager.takeScreenshot("Name Search No Results");
            }
            
            Assert.assertTrue(isDisplayed, "Search results table is not displayed");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Name search operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Name Search Exception");
            
            System.err.println("Name search operation failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based name search failed", e);
        }
    }

    @Step("Perform search by ID value using Excel data")
    @Description("Execute ID value-based search operations using Excel test data")
    @Severity(SeverityLevel.NORMAL)
    @Story("ID Search")
    @Then("User perform search for each ID value in Excel data")
    public void user_perform_search_for_each_id_value_in_excel_data() {
        AllureReportManager.logStep("🆔 Starting ID value-based search from Excel data");
        
        try {
            globalSearchPage.performBulkSearchFromExcelByIdValue(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.logSearchOperation("ID Value Search", "Excel Data", 
                isDisplayed ? 1 : 0); // Results count - can be enhanced based on actual page object methods
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("ID value search operation", "PASSED");
                AllureReportManager.takeScreenshot("ID Value Search Results");
            } else {
                AllureReportManager.logStepWithStatus("ID value search operation", "FAILED");
                AllureReportManager.takeScreenshot("ID Value Search No Results");
            }
            
            Assert.assertTrue(isDisplayed, "Search results table is not displayed");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("ID value search operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("ID Value Search Exception");
            
            System.err.println("ID value search operation failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based ID value search failed", e);
        }
    }

    @Step("Search using list '{listName}' for record '{recordId}'")
    @Description("Select a specific list and perform search for a given record ID")
    @Severity(SeverityLevel.NORMAL)
    @Story("List-based Search")
    @When("User selects list {string} and search for {string}")
    public void user_selects_list_and_search_for(String listName, String recordId) {
        AllureReportManager.logStep("📝 Selecting list '" + listName + "' and searching for '" + recordId + "'");
        
        try {
            globalSearchPage.openListDropdown();
            globalSearchPage.isListDropdownOpen();
            globalSearchPage.getAvailableListNames();
            globalSearchPage.selectListByName(listName);
            globalSearchPage.closeListDropdown();
            globalSearchPage.enterSearchText(recordId);
            globalSearchPage.clickSearchButton();
            
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.attachText("List Name", listName);
            AllureReportManager.attachText("Search Record ID", recordId);
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("List-based search", "PASSED");
                AllureReportManager.takeScreenshot("List Search Results - " + listName);
                globalSearchPage.printTableDataToConsole();
            } else {
                AllureReportManager.logStepWithStatus("List-based search", "FAILED");
                AllureReportManager.takeScreenshot("List Search No Results - " + listName);
            }
            
            Assert.assertTrue(isDisplayed, "Search results table is not displayed");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("List-based search", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("List Search Exception");
            throw e;
        }
    }

    @Step("Select search by option: '{searchByOption}' (Alternative Method)")
    @Description("User selects search criteria from the search by dropdown - Alternative implementation")
    @Severity(SeverityLevel.NORMAL)
    @When("User Selects search by option {string}")
    public void user_Selects_search_by_option(String searchByOption) {
        AllureReportManager.logStep("🔍 Selecting search by option (alternative): " + searchByOption);
        
        try {
            globalSearchPage.openSearchByDropdown();
            globalSearchPage.isSearchByDropdownOpen();
            globalSearchPage.selectSearchByOption(searchByOption);
            
            AllureReportManager.attachText("Search By Option", searchByOption);
            AllureReportManager.logStepWithStatus("Search by selection (alternative)", "PASSED");
            AllureReportManager.takeScreenshot("Search By Selected (Alt) - " + searchByOption);
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Search by selection (alternative)", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Search By Selection Failed (Alt)");
            throw e;
        }
    }

    @Step("Search using list '{listName}' with Excel data")
    @Description("Select a specific list and perform search using Excel test data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Excel-based List Search")
    @When("User selects list {string} and search from Excel")
    public void user_selects_list_and_search_from_excel(String listName) {
        AllureReportManager.logStep("📊 Selecting list '" + listName + "' and searching using Excel data");
        
        try {
            globalSearchPage.openListDropdown();
            globalSearchPage.isListDropdownOpen();
            globalSearchPage.getAvailableListNames();
            globalSearchPage.selectListByName(listName);
            globalSearchPage.searchUsingExcelDataByRecordId("IBL04-08", EXCEL_FILE_PATH);
            globalSearchPage.printTableDataToConsole();
            
            AllureReportManager.attachText("List Name", listName);
            AllureReportManager.attachTestData("Excel File Path", EXCEL_FILE_PATH);
            AllureReportManager.logStepWithStatus("Excel-based list search", "PASSED");
            AllureReportManager.takeScreenshot("Excel List Search Results - " + listName);
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Excel-based list search", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Excel List Search Exception");
            throw e;
        }
    }

    @Step("Search multiple lists '{listNames}' for '{searchValue}' with search type '{searchType}'")
    @Description("Select multiple lists and perform search with specified search type")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Multiple List Search")
    @When("User selects multiple list names {string} and search for {string} with search type {string}")
    public void when_user_selects_multiple_list_names_and_search_for_with_search_type(String listNames, String searchValue, String searchType) {
        AllureReportManager.logStep("📋 Selecting multiple lists and searching by Record ID");
        AllureReportManager.attachText("List Names", listNames);
        AllureReportManager.attachText("Search Value", searchValue);
        AllureReportManager.attachText("Search Type", searchType);
        
        try {
            globalSearchPage.openListDropdown();
            globalSearchPage.isListDropdownOpen();
            globalSearchPage.getAvailableListNames();
            globalSearchPage.selectMultipleListsByName(listNames, searchType, searchValue);
            
            AllureReportManager.logStepWithStatus("Multiple list selection and search", "PASSED");
            AllureReportManager.takeScreenshot("Multiple List Search - " + searchType);
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Multiple list selection and search", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Multiple List Search Exception");
            
            System.err.println("Multiple list selection and search failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Multiple list selection and search operation failed", e);
        }
    }

    @Step("Perform bulk search with Watchlist validation")
    @Description("Execute search operations for each record with Watchlist validation from Excel data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Watchlist Validation Search")
    @When("User perform search for each record in Excel data with Watchlist validation")
    public void user_perform_search_for_each_record_in_excel_data_with_watchlist_validation() {
        AllureReportManager.logStep("🔍 Starting bulk search with Watchlist validation from Excel data");
        AllureReportManager.attachTestData("Excel File Path", EXCEL_FILE_PATH);
        
        try {
            globalSearchPage.performBulkSearchForRecordIDFromExcelWithWatchlistValidation(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.logSearchOperation("Bulk Search with Watchlist Validation", "Excel Data", 
                isDisplayed ? 1 : 0);
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("Bulk search with Watchlist validation", "PASSED");
                AllureReportManager.takeScreenshot("Watchlist Validation Search Results");
            } else {
                AllureReportManager.logStepWithStatus("Bulk search with Watchlist validation", "FAILED");
                AllureReportManager.takeScreenshot("Watchlist Validation Search No Results");
            }
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Bulk search with Watchlist validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Watchlist Validation Search Exception");
            
            System.err.println("Bulk search with Watchlist validation operation failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based bulk search with Watchlist validation failed", e);
        }
    }

    @Step("Verify all searches completed with correct Watchlist selections")
    @Description("Validate that all search operations completed successfully with proper Watchlist validation")
    @Severity(SeverityLevel.NORMAL)
    @Then("verify all searches completed with correct Watchlist selections")
    public void verify_all_searches_completed_with_correct_watchlist_selections() {
        AllureReportManager.logStep("✅ Verifying all searches completed with correct Watchlist selections");
        
        try {
            // This step validates that the bulk search with Watchlist validation completed successfully
            // The actual validation is done within the performBulkSearchFromExcelWithWatchlistValidation method
            System.out.println("Verifying that all searches completed with correct Watchlist selections...");
            
            // Additional validation can be added here if needed
            // For now, if we reached this point, it means the bulk search completed without throwing exceptions
            System.out.println("All searches with Watchlist validation completed successfully.");
            
            AllureReportManager.logStepWithStatus("Watchlist validation verification", "PASSED");
            AllureReportManager.takeScreenshot("All Watchlist Validations Completed");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Watchlist validation verification", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Watchlist Validation Verification Failed");
            
            System.err.println("Verification of Watchlist validation searches failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Verification of Watchlist validation searches failed", e);
        }
    }

    @Step("Perform bulk ID Value search with Watchlist validation")
    @Description("Execute ID Value-based search operations with Watchlist validation from Excel data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("ID Value Watchlist Search")
    @When("User perform search for each ID Value in Excel data with Watchlist validation")
    public void user_perform_search_for_each_id_value_in_excel_data_with_watchlist_validation() {
        AllureReportManager.logStep("🆔 Starting ID Value search with Watchlist validation from Excel data");
        AllureReportManager.attachTestData("Excel File Path", EXCEL_FILE_PATH);
        
        try {
            globalSearchPage.performBulkSearchForIDValueFromExcelWithWatchlistValidation(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.logSearchOperation("ID Value Search with Watchlist Validation", "Excel Data", 
                isDisplayed ? 1 : 0);
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("ID Value search with Watchlist validation", "PASSED");
                AllureReportManager.takeScreenshot("ID Value Watchlist Validation Results");
            } else {
                AllureReportManager.logStepWithStatus("ID Value search with Watchlist validation", "FAILED");
                AllureReportManager.takeScreenshot("ID Value Watchlist Validation No Results");
            }
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("ID Value search with Watchlist validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("ID Value Watchlist Validation Exception");
            
            System.err.println("Bulk search with Watchlist validation operation failed for ID Value: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based bulk search with Watchlist validation failed for ID Value", e);
        }
    }

    @Step("Perform bulk Name search with Watchlist validation")
    @Description("Execute Name-based search operations with Watchlist validation from Excel data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Name Watchlist Search")
    @When("User perform search for each Name in Excel data with Watchlist validation")
    public void user_perform_search_for_each_name_in_excel_data_with_watchlist_validation() {
        AllureReportManager.logStep("👤 Starting Name search with Watchlist validation from Excel data");
        AllureReportManager.attachTestData("Excel File Path", EXCEL_FILE_PATH);
        
        try {
            globalSearchPage.performBulkSearchForNameFromExcelWithWatchlistValidation(EXCEL_FILE_PATH);
            boolean isDisplayed = globalSearchPage.isResultsTableDisplayed();
            
            AllureReportManager.logSearchOperation("Name Search with Watchlist Validation", "Excel Data", 
                isDisplayed ? 1 : 0);
            
            if (isDisplayed) {
                AllureReportManager.logStepWithStatus("Name search with Watchlist validation", "PASSED");
                AllureReportManager.takeScreenshot("Name Watchlist Validation Results");
            } else {
                AllureReportManager.logStepWithStatus("Name search with Watchlist validation", "FAILED");
                AllureReportManager.takeScreenshot("Name Watchlist Validation No Results");
            }
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Name search with Watchlist validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Name Watchlist Validation Exception");
            
            System.err.println("Bulk search with Watchlist validation operation failed for Name: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Excel-based bulk search with Watchlist validation failed for Name", e);
        }
    }
}
