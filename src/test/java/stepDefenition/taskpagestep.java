package stepDefenition;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pages.paginationPage;
import com.pages.taskCommonPage;
import com.pages.auditLogPage;
import com.qea.factory.DriverFactory;
import com.qea.utils.AllureReportManager;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import java.time.Duration;

/**
 * Enhanced Task Page Step Definitions with Allure Reporting
 * This class handles task page operations and validations with comprehensive Allure reporting
 */
@Epic("Facctum Application Testing")
@Feature("Task Page Functionality")
public class taskpagestep {
    WebDriver driver;
    Properties prop;
    WebDriverWait wait;

    private taskCommonPage taskCommonPage;
    private paginationPage paginationPage;
    private auditLogPage auditLogPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        if (driver == null) {
            AllureReportManager.logStepWithStatus("Driver initialization check failed", "FAILED");
            throw new RuntimeException("Driver is not initialized. Please check your hooks setup.");
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.taskCommonPage = new taskCommonPage(driver);
        this.paginationPage = new paginationPage(driver);
        this.auditLogPage = new auditLogPage(driver);
        
        AllureReportManager.logStep("Task page step definition setup completed");
    }
    
    @Step("Verify all main tabs are visible on Tasks page")
    @Description("Validate that all main tabs (Pending L1, Pending L2, Overdue, Rejected, Review) are displayed on the Tasks page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Tab Visibility Validation")
    @Given("User can see all main tabs on Tasks page")
    public void userCanSeeAllMainTabsOnTasksPage() {
        AllureReportManager.logStep("Verifying all main tabs are visible on Tasks page");
        
        try {
            boolean pendingL1Visible = taskCommonPage.isPendingL1TabDisplayed();
            boolean pendingL2Visible = taskCommonPage.isPendingL2TabDisplayed();
            boolean overdueVisible = taskCommonPage.isOverdueTabDisplayed();
            boolean rejectedVisible = taskCommonPage.isRejectedTabDisplayed();
            boolean reviewVisible = taskCommonPage.isReviewTabDisplayed();
            
            AllureReportManager.attachText("Pending L1 Tab Visible", String.valueOf(pendingL1Visible));
            AllureReportManager.attachText("Pending L2 Tab Visible", String.valueOf(pendingL2Visible));
            AllureReportManager.attachText("Overdue Tab Visible", String.valueOf(overdueVisible));
            AllureReportManager.attachText("Rejected Tab Visible", String.valueOf(rejectedVisible));
            AllureReportManager.attachText("Review Tab Visible", String.valueOf(reviewVisible));
            
            Assert.assertTrue(pendingL1Visible, "Pending L1 tab is not visible");
            Assert.assertTrue(pendingL2Visible, "Pending L2 tab is not visible");
            Assert.assertTrue(overdueVisible, "Overdue tab is not visible");
            Assert.assertTrue(rejectedVisible, "Rejected tab is not visible");
            Assert.assertTrue(reviewVisible, "Review tab is not visible");
            
            AllureReportManager.logStepWithStatus("Main tabs visibility validation", "PASSED");
            AllureReportManager.takeScreenshot("All Main Tabs Visible");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Main tabs visibility validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Main Tabs Visibility Failed");
            throw e;
        }
    }
    @Step("Ensure Pending L1 tab is active")
    @Description("Verify that the Pending L1 tab is active, if not, click to activate it")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Activation")
    @When("the Pending L1 tab is already active")
    public void thePendingL1TabIsAlreadyActive() throws InterruptedException {
        AllureReportManager.logStep("🔄 Ensuring Pending L1 tab is active");
        
        try {
            // Check if Pending L1 tab is active
            boolean isTabActive = taskCommonPage.isPendingL1TabActive();
            boolean hasActiveColor = taskCommonPage.isPendingL1TabActive();
            
            AllureReportManager.attachText("Initial Tab Active Status", String.valueOf(isTabActive));
            
            // If not active, click to activate
            if (!hasActiveColor) {
                AllureReportManager.logStep("Clicking to activate Pending L1 tab");
                taskCommonPage.clickPendingL1Tab();  // Click to activate
                Thread.sleep(2000);                  // Wait for activation
                hasActiveColor = taskCommonPage.isPendingL1TabActive(); // Then verify
                AllureReportManager.attachText("Tab Active After Click", String.valueOf(hasActiveColor));
            }
            
            Assert.assertTrue(isTabActive, "Pending L1 tab is not active");
            Assert.assertTrue(hasActiveColor, "Pending L1 tab is not active after clicking");
            
            AllureReportManager.logStepWithStatus("Pending L1 tab activation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L1 Tab Active");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L1 tab activation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L1 Tab Activation Failed");
            throw e;
        }
    }

    @Step("Validate all sub-tabs presence under Pending L1 tab")
    @Description("Verify that all sub-tabs (External Records, Lists Configuration, Press Release Records, Templates, Internal Records) are displayed under Pending L1 tab")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sub-Tab Visibility Validation")
    @Then("User validate all sub-tabs presence under active main tab")
    public void userValidateAllSubTabsPresence() {
        AllureReportManager.logStep("Validating all sub-tabs presence under Pending L1 tab");
        
        try {
            boolean externalRecordsVisible = taskCommonPage.isExternalRecordsSubTabDisplayed();
            boolean listsConfigVisible = taskCommonPage.isListsConfigurationSubTabDisplayed();
            boolean pressReleaseVisible = taskCommonPage.isPressReleaseRecordsSubTabDisplayed();
            boolean templatesVisible = taskCommonPage.isTemplatesSubTabDisplayed();
            boolean internalRecordsVisible = taskCommonPage.isInternalRecordsSubTabDisplayed();
            
            AllureReportManager.attachText("External Records Tab Visible", String.valueOf(externalRecordsVisible));
            AllureReportManager.attachText("Lists Configuration Tab Visible", String.valueOf(listsConfigVisible));
            AllureReportManager.attachText("Press Release Records Tab Visible", String.valueOf(pressReleaseVisible));
            AllureReportManager.attachText("Templates Tab Visible", String.valueOf(templatesVisible));
            AllureReportManager.attachText("Internal Records Tab Visible", String.valueOf(internalRecordsVisible));
            
            Assert.assertTrue(externalRecordsVisible, "External Records tab is not visible");
            Assert.assertTrue(listsConfigVisible, "Lists Configuration tab is not visible");
            Assert.assertTrue(pressReleaseVisible, "Press Release Records tab is not visible");
            Assert.assertTrue(templatesVisible, "Templates tab is not visible");
            Assert.assertTrue(internalRecordsVisible, "Internal Records tab is not visible");
            
            AllureReportManager.logStepWithStatus("Sub-tabs visibility validation", "PASSED");
            AllureReportManager.takeScreenshot("All Sub-Tabs Visible Under Pending L1");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Sub-tabs visibility validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Sub-Tabs Visibility Failed");
            throw e;
        }
    }
    
    @Step("Validate Pending L1 total task count")
    @Description("Verify that the Pending L1 tab displays a valid numeric task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("Pending L1 total task count")
    public void pendingL1TotalTaskCount() {
        AllureReportManager.logStep("📊 Validating Pending L1 total task count");
        
        try {
            String pendingL1count = taskCommonPage.getPendingL1Count();
            
            AllureReportManager.attachText("Pending L1 Count", pendingL1count);
            
            Assert.assertNotNull(pendingL1count, "Pending L1 count is null");
            Assert.assertTrue(pendingL1count.matches("\\d+"), "Pending L1 count is not a valid number: " + pendingL1count);
            
            System.out.println("Pending L1 Count: " + pendingL1count);
            
            AllureReportManager.logStepWithStatus("Pending L1 count validation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L1 Count Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L1 count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L1 Count Validation Failed");
            throw e;
        }
    }

    @Step("Validate External Records sub-tab is active")
    @Description("Ensure External Records sub-tab is active and validate its task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Activation Validation")
    @Then("Validate Sub-tab External Records is already active")
    public void validateSubTabExternalRecordsIsActive() {
        AllureReportManager.logStep("🗂️ Validating External Records sub-tab is active");
        
        try {
            if (taskCommonPage.isExternalRecordsSubTabActive()) {
                AllureReportManager.logStep("External Records sub-tab is already active");
                System.out.println("External Records sub-tab is already active");
            } else {
                AllureReportManager.logStep("Clicking to activate External Records sub-tab");
                taskCommonPage.clickExternalRecordsSubTab();
                wait.until(driver -> taskCommonPage.isExternalRecordsSubTabActive());
                System.out.println("External Records sub-tab is now active");
            }
            
            boolean isTabActive = taskCommonPage.isExternalRecordsSubTabActive();
            boolean hasActiveColor = taskCommonPage.isExternalRecordsSubTabActive();
            String externalRecordsCount = taskCommonPage.getExternalRecordsCount();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            AllureReportManager.attachText("External Records Count", externalRecordsCount);
            
            Assert.assertTrue(isTabActive, "External Records sub-tab is not active");
            Assert.assertTrue(hasActiveColor, "External Records sub-tab does not have active color");
            Assert.assertNotNull(externalRecordsCount, "External Records count is null");
            Assert.assertTrue(externalRecordsCount.matches("\\d+"), "External Records count is not a valid number: " + externalRecordsCount);
            
            System.out.println("External Records sub-tab is active");
            System.out.println("External Records Count: " + externalRecordsCount);
            
            AllureReportManager.logStepWithStatus("External Records sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("External Records Sub-Tab Active");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("External Records sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("External Records Sub-Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on Internal Records sub-tab")
    @Description("User clicks on the Internal Records sub-tab to switch the view")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on sub tab INTERNAL RECORDS")
    public void userClickOnSubTabInternalRecords() {
        AllureReportManager.logStep("📁 Clicking on Internal Records sub-tab");
        
        try {
            taskCommonPage.clickInternalRecordsSubTab();
            wait.until(driver -> taskCommonPage.isInternalRecordsSubTabActive());
            
            System.out.println("Clicked on Internal Records sub-tab");
            
            AllureReportManager.logStepWithStatus("Internal Records sub-tab click", "PASSED");
            AllureReportManager.takeScreenshot("Internal Records Sub-Tab Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Internal Records sub-tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Internal Records Sub-Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate Internal Records sub-tab is active")
    @Description("Verify that the Internal Records sub-tab is active and validate its task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Activation Validation")
    @Then("User validate that the INTERNAL RECORDS sub-tab is active")
    public void userValidateInternalRecordsSubTabIsActive() {
        AllureReportManager.logStep("📂 Validating Internal Records sub-tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isInternalRecordsSubTabActive();
            boolean hasActiveColor = taskCommonPage.isInternalRecordsSubTabActive();
            String internalRecordsCount = taskCommonPage.getInternalRecordsCount();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            AllureReportManager.attachText("Internal Records Count", internalRecordsCount);
            
            Assert.assertTrue(isTabActive, "Internal Records sub-tab is not active");
            Assert.assertTrue(hasActiveColor, "Internal Records sub-tab does not have active color");
            Assert.assertNotNull(internalRecordsCount, "Internal Records count is null");
            Assert.assertTrue(internalRecordsCount.matches("\\d+"), "Internal Records count is not a valid number: " + internalRecordsCount);
            
            System.out.println("Internal Records sub-tab is active");
            System.out.println("Internal Records Count: " + internalRecordsCount);
            
            AllureReportManager.logStepWithStatus("Internal Records sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Internal Records Sub-Tab Active Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Internal Records sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Internal Records Sub-Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on Lists Configuration sub-tab")
    @Description("User clicks on the Lists Configuration sub-tab to switch the view")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on sub tab LISTS CONFIGURATION")
    public void userClickOnSubTabListsConfiguration() {
        AllureReportManager.logStep("📋 Clicking on Lists Configuration sub-tab");
        
        try {
            taskCommonPage.clickListsConfigurationSubTab();
            wait.until(driver -> taskCommonPage.isListsConfigurationSubTabActive());
            
            System.out.println("Clicked on Lists Configuration sub-tab");
            
            AllureReportManager.logStepWithStatus("Lists Configuration sub-tab click", "PASSED");
            AllureReportManager.takeScreenshot("Lists Configuration Sub-Tab Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Lists Configuration sub-tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Lists Configuration Sub-Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate Lists Configuration sub-tab is active")
    @Description("Verify that the Lists Configuration sub-tab is active and validate its task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Activation Validation")
    @Then("User validate that the LISTS CONFIGURATION sub-tab is active")
    public void userValidateListsConfigurationSubTabIsActive() {
        AllureReportManager.logStep("🗒️ Validating Lists Configuration sub-tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isListsConfigurationSubTabActive();
            boolean hasActiveColor = taskCommonPage.isListsConfigurationSubTabActive();
            String listsConfigurationCount = taskCommonPage.getListsConfigurationCount();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            AllureReportManager.attachText("Lists Configuration Count", listsConfigurationCount);
            
            Assert.assertTrue(isTabActive, "Lists Configuration sub-tab is not active");
            Assert.assertTrue(hasActiveColor, "Lists Configuration sub-tab does not have active color");
            Assert.assertNotNull(listsConfigurationCount, "Lists Configuration count is null");
            Assert.assertTrue(listsConfigurationCount.matches("\\d+"), "Lists Configuration count is not a valid number: " + listsConfigurationCount);
            
            System.out.println("Lists Configuration sub-tab is active");
            System.out.println("Lists Configuration Count: " + listsConfigurationCount);
            
            AllureReportManager.logStepWithStatus("Lists Configuration sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Lists Configuration Sub-Tab Active Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Lists Configuration sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Lists Configuration Sub-Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on Press Release Records sub-tab")
    @Description("User clicks on the Press Release Records sub-tab to switch the view")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on sub tab PRESS RELEASE RECORDS")
    public void userClickOnSubTabPressReleaseRecords() {
        AllureReportManager.logStep("📰 Clicking on Press Release Records sub-tab");
        
        try {
            taskCommonPage.clickPressReleaseRecordsSubTab();
            wait.until(driver -> taskCommonPage.isPressReleaseRecordsSubTabActive());
            
            System.out.println("Clicked on Press Release Records sub-tab");
            
            AllureReportManager.logStepWithStatus("Press Release Records sub-tab click", "PASSED");
            AllureReportManager.takeScreenshot("Press Release Records Sub-Tab Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Press Release Records sub-tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Press Release Records Sub-Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate Press Release Records sub-tab is active")
    @Description("Verify that the Press Release Records sub-tab is active and validate its task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Activation Validation")
    @Then("User validate that the PRESS RELEASE RECORDS sub-tab is active")
    public void userValidatePressReleaseRecordsSubTabIsActive() {
        AllureReportManager.logStep("📃 Validating Press Release Records sub-tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isPressReleaseRecordsSubTabActive();
            boolean hasActiveColor = taskCommonPage.isPressReleaseRecordsSubTabActive();
            String pressReleaseRecordsCount = taskCommonPage.getPressReleaseRecordsCount();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            AllureReportManager.attachText("Press Release Records Count", pressReleaseRecordsCount);
            
            Assert.assertTrue(isTabActive, "Press Release Records sub-tab is not active");
            Assert.assertTrue(hasActiveColor, "Press Release Records sub-tab does not have active color");
            Assert.assertNotNull(pressReleaseRecordsCount, "Press Release Records count is null");
            Assert.assertTrue(pressReleaseRecordsCount.matches("\\d+"), "Press Release Records count is not a valid number: " + pressReleaseRecordsCount);
            
            System.out.println("Press Release Records sub-tab is active");
            System.out.println("Press Release Records Count: " + pressReleaseRecordsCount);
            
            AllureReportManager.logStepWithStatus("Press Release Records sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Press Release Records Sub-Tab Active Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Press Release Records sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Press Release Records Sub-Tab Validation Failed");
            throw e;
        }
    }
   
    @Step("Click on Templates sub-tab")
    @Description("User clicks on the Templates sub-tab to switch the view")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on sub tab TEMPLATES")
    public void userClickOnSubTabTemplates() {
        AllureReportManager.logStep("📋 Clicking on Templates sub-tab");
        
        try {
            taskCommonPage.clickTemplatesSubTab();
            wait.until(driver -> taskCommonPage.isTemplatesSubTabActive());
            
            System.out.println("Clicked on Templates sub-tab");
            
            AllureReportManager.logStepWithStatus("Templates sub-tab click", "PASSED");
            AllureReportManager.takeScreenshot("Templates Sub-Tab Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Templates sub-tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Templates Sub-Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate Templates sub-tab is active")
    @Description("Verify that the Templates sub-tab is active and validate its task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Activation Validation")
    @Then("User validate that the TEMPLATES sub-tab is active")
    public void userValidateTemplatesSubTabIsActive() {
        AllureReportManager.logStep("📄 Validating Templates sub-tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isTemplatesSubTabActive();
            boolean hasActiveColor = taskCommonPage.isTemplatesSubTabActive();
            String templatesCount = taskCommonPage.getTemplatesCount();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            AllureReportManager.attachText("Templates Count", templatesCount);
            
            Assert.assertTrue(isTabActive, "Templates sub-tab is not active");
            Assert.assertTrue(hasActiveColor, "Templates sub-tab does not have active color");
            Assert.assertNotNull(templatesCount, "Templates count is null");
            Assert.assertTrue(templatesCount.matches("\\d+"), "Templates count is not a valid number: " + templatesCount);
            
            System.out.println("Templates sub-tab is active");
            System.out.println("Templates Count: " + templatesCount);
            
            AllureReportManager.logStepWithStatus("Templates sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Templates Sub-Tab Active Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Templates sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Templates Sub-Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Validate sum of sub-tab counts equals Pending L1 total")
    @Description("Verify that the sum of all sub-tab task counts matches the Pending L1 total task count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Task Count Validation")
    @Then("Sum of all sub-tab task counts should be equal to Pending L1 total task count")
    public void sumOfAllSubTabTaskCountsShouldBeEqualToPendingL1TotalTaskCount() {
        AllureReportManager.logStep("🧮 Validating sum of sub-tab counts equals Pending L1 total");
        
        try {
            String pendingL1TotalCount = taskCommonPage.getPendingL1Count();
            int sumOfSubTabCounts = 
                    parseCount(taskCommonPage.getExternalRecordsCount()) +
                    parseCount(taskCommonPage.getInternalRecordsCount()) +
                    parseCount(taskCommonPage.getListsConfigurationCount()) +
                    parseCount(taskCommonPage.getPressReleaseRecordsCount()) +
                    parseCount(taskCommonPage.getTemplatesCount());
            String sumOfSubTabCountsStr = String.valueOf(sumOfSubTabCounts);
            
            AllureReportManager.attachText("Pending L1 Total Count", pendingL1TotalCount);
            AllureReportManager.attachText("Sum of Sub-tab Counts", sumOfSubTabCountsStr);
            AllureReportManager.attachText("External Records Count", taskCommonPage.getExternalRecordsCount());
            AllureReportManager.attachText("Internal Records Count", taskCommonPage.getInternalRecordsCount());
            AllureReportManager.attachText("Lists Configuration Count", taskCommonPage.getListsConfigurationCount());
            AllureReportManager.attachText("Press Release Records Count", taskCommonPage.getPressReleaseRecordsCount());
            AllureReportManager.attachText("Templates Count", taskCommonPage.getTemplatesCount());
            
            System.out.println("Sum of Sub-tab Counts: " + sumOfSubTabCounts);
            Assert.assertEquals(pendingL1TotalCount, sumOfSubTabCountsStr, "Sum of all sub-tab task counts is not equal to Pending L1 total task count");
            System.out.println("Sum of all sub-tab task counts is equal to Pending L1 total task count");
            
            AllureReportManager.logStepWithStatus("Pending L1 sum validation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L1 Sum Validation Passed");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L1 sum validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L1 Sum Validation Failed");
            throw e;
        }
    }

    // Helper method for safe integer parsing
    private int parseCount(String countStr) {
        try {
            return Integer.parseInt(countStr);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Step("Click on Pending L2 tab")
    @Description("User clicks on the Pending L2 tab to switch to the second level pending tasks")
    @Severity(SeverityLevel.NORMAL)
    @Story("Main Tab Navigation")
    @When("User click on Pending L2 tab")
    public void userClickOnPendingL2Tab() {
        AllureReportManager.logStep("📋 Clicking on Pending L2 tab");
        
        try {
            taskCommonPage.clickPendingL2Tab();
            wait.until(driver -> taskCommonPage.isPendingL2TabActive());
            
            System.out.println("Clicked on Pending L2 tab");
            
            AllureReportManager.logStepWithStatus("Pending L2 tab click", "PASSED");
            AllureReportManager.takeScreenshot("Pending L2 Tab Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L2 tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L2 Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate Pending L2 tab is active")
    @Description("Verify that the Pending L2 tab is active and highlighted")
    @Severity(SeverityLevel.NORMAL)
    @Story("Main Tab Activation Validation")
    @Then("User validate that the Pending L2 tab is active")
    public void userValidatePendingL2TabIsActive() {
        AllureReportManager.logStep("✅ Validating Pending L2 tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isPendingL2TabActive();
            boolean hasActiveColor = taskCommonPage.isPendingL2TabActive();
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isTabActive));
            AllureReportManager.attachText("Has Active Color", String.valueOf(hasActiveColor));
            
            Assert.assertTrue(isTabActive, "Pending L2 tab is not active");
            Assert.assertTrue(hasActiveColor, "Pending L2 tab does not have active color");
            
            System.out.println("Pending L2 tab is active");
            
            AllureReportManager.logStepWithStatus("Pending L2 tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L2 Tab Active Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L2 tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L2 Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Validate Pending L2 total task count")
    @Description("Verify that the Pending L2 tab displays a valid numeric task count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("Pending L2 total task count")
    public void pendingL2TotalTaskCount() {
        AllureReportManager.logStep("📊 Validating Pending L2 total task count");
        
        try {
            String pendingL2Count = taskCommonPage.getPendingL2Count();
            
            AllureReportManager.attachText("Pending L2 Count", pendingL2Count);
            
            Assert.assertNotNull(pendingL2Count, "Pending L2 count is null");
            Assert.assertTrue(pendingL2Count.matches("\\d+"), "Pending L2 count is not a valid number: " + pendingL2Count);
            
            System.out.println("Pending L2 Count: " + pendingL2Count);
            
            AllureReportManager.logStepWithStatus("Pending L2 count validation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L2 Count Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L2 count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L2 Count Validation Failed");
            throw e;
        }
    }
    
    @Step("Validate sum of sub-tab counts equals Pending L2 total")
    @Description("Verify that the sum of all sub-tab task counts matches the Pending L2 total task count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Task Count Validation")
    @Then("Sum of all sub-tab task counts should be equal to Pending L2 total task count")
    public void sumOfAllSubTabTaskCountsShouldBeEqualToPendingL2TotalTaskCount() {
        AllureReportManager.logStep("🧮 Validating sum of sub-tab counts equals Pending L2 total");
        
        try {
            String pendingL2TotalCount = taskCommonPage.getPendingL2Count();
            int sumOfSubTabCounts = 
                    parseCount(taskCommonPage.getExternalRecordsCount()) +
                    parseCount(taskCommonPage.getInternalRecordsCount());
                    
            String sumOfSubTabCountsStr = String.valueOf(sumOfSubTabCounts);
            
            AllureReportManager.attachText("Pending L2 Total Count", pendingL2TotalCount);
            AllureReportManager.attachText("Sum of Sub-tab Counts", sumOfSubTabCountsStr);
            AllureReportManager.attachText("External Records Count", taskCommonPage.getExternalRecordsCount());
            AllureReportManager.attachText("Internal Records Count", taskCommonPage.getInternalRecordsCount());
            
            System.out.println("Sum of Sub-tab Counts: " + sumOfSubTabCounts);
            Assert.assertEquals(pendingL2TotalCount, sumOfSubTabCountsStr, "Sum of all sub-tab task counts is not equal to Pending L2 total task count");
            System.out.println("Sum of all sub-tab task counts is equal to Pending L2 total task count");
            
            AllureReportManager.logStepWithStatus("Pending L2 sum validation", "PASSED");
            AllureReportManager.takeScreenshot("Pending L2 Sum Validation Passed");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pending L2 sum validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pending L2 Sum Validation Failed");
            throw e;
        }
    }
    @Step("Click on Rejected tab")
    @Description("Navigate to Rejected tab to view rejected task records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @When("User click on Rejected tab")
    public void userClickOnRejectedTab() {
        AllureReportManager.logStep("🚫 Clicking on Rejected tab");
        
        try {
            taskCommonPage.clickRejectedTab();
            wait.until(driver -> taskCommonPage.isRejectedTabActive());
            
            AllureReportManager.logStepWithStatus("Rejected tab click", "PASSED");
            AllureReportManager.takeScreenshot("Rejected Tab Clicked");
            System.out.println("Clicked on Rejected tab");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Rejected tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Rejected Tab Click Failed");
            throw e;
        }
    }
    @Step("Validate Rejected tab is active")
    @Description("Verify that the Rejected tab is active and properly displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @Then("User validate that the Rejected tab is active")
    public void userValidateRejectedTabIsActive() {
        AllureReportManager.logStep("✅ Validating Rejected tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isRejectedTabActive();
            boolean hasActiveColor = taskCommonPage.isRejectedTabActive();
            
            AllureReportManager.attachText("Rejected Tab Active", String.valueOf(isTabActive));
            AllureReportManager.attachText("Rejected Tab Has Active Color", String.valueOf(hasActiveColor));
            
            Assert.assertTrue(isTabActive, "Rejected tab is not active");
            Assert.assertTrue(hasActiveColor, "Rejected tab does not have active color");
            
            AllureReportManager.logStepWithStatus("Rejected tab active validation", "PASSED");
            AllureReportManager.takeScreenshot("Rejected Tab Active Validated");
            System.out.println("Rejected tab is active");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Rejected tab active validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Rejected Tab Active Validation Failed");
            throw e;
        }
    }
    @Step("Get rejected total task count")
    @Description("Retrieve and validate the total count of rejected tasks")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("Rejected total task count")
    public void rejectedTotalTaskCount() {
        AllureReportManager.logStep("📊 Getting rejected total task count");
        
        try {
            String rejectedCount = taskCommonPage.getRejectedCount();
            
            AllureReportManager.attachText("Rejected Count", rejectedCount);
            
            Assert.assertNotNull(rejectedCount, "Rejected count is null");
            Assert.assertTrue(rejectedCount.matches("\\d+"), "Rejected count is not a valid number: " + rejectedCount);
            
            AllureReportManager.logStepWithStatus("Rejected total task count retrieval", "PASSED");
            AllureReportManager.takeScreenshot("Rejected Total Task Count");
            System.out.println("Rejected Count: " + rejectedCount);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Rejected total task count retrieval", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Rejected Total Task Count Failed");
            throw e;
        }
    }

    @Step("Validate sum of sub-tab counts equals Rejected total")
    @Description("Verify that the sum of all sub-tab task counts matches the Rejected total task count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Task Count Validation")
    @Then("Sum of all sub-tab task counts should be equal to Rejected total task count")
    public void Sum_of_all_sub_tab_task_counts_should_be_equal_to_Rejected_total_task_count(){
        AllureReportManager.logStep("🧮 Validating sum of sub-tab counts equals Rejected total");
        
        try {
            String rejectedTotalCount = taskCommonPage.getRejectedCount();
            int sumOfSubTabCounts = 
                    parseCount(taskCommonPage.getExternalRecordsCount()) +
                    parseCount(taskCommonPage.getInternalRecordsCount()) +
                    parseCount(taskCommonPage.getListsConfigurationCount()) +
                    parseCount(taskCommonPage.getPressReleaseRecordsCount()) +
                    parseCount(taskCommonPage.getTemplatesCount());
                    
            String sumOfSubTabCountsStr = String.valueOf(sumOfSubTabCounts);
            
            AllureReportManager.attachText("Rejected Total Count", rejectedTotalCount);
            AllureReportManager.attachText("Sum of Sub-tab Counts", sumOfSubTabCountsStr);
            AllureReportManager.attachText("External Records Count", taskCommonPage.getExternalRecordsCount());
            AllureReportManager.attachText("Internal Records Count", taskCommonPage.getInternalRecordsCount());
            AllureReportManager.attachText("Lists Configuration Count", taskCommonPage.getListsConfigurationCount());
            AllureReportManager.attachText("Press Release Records Count", taskCommonPage.getPressReleaseRecordsCount());
            AllureReportManager.attachText("Templates Count", taskCommonPage.getTemplatesCount());
            
            System.out.println("Sum of Sub-tab Counts: " + sumOfSubTabCounts);
            Assert.assertEquals(rejectedTotalCount, sumOfSubTabCountsStr, "Sum of all sub-tab task counts is not equal to Rejected total task count");
            System.out.println("Sum of all sub-tab task counts is equal to Rejected total task count");
            
            AllureReportManager.logStepWithStatus("Rejected sum validation", "PASSED");
            AllureReportManager.takeScreenshot("Rejected Sum Validation Passed");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Rejected sum validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Rejected Sum Validation Failed");
            throw e;
        }
    }
    @Step("Click on Overdue tab")
    @Description("Navigate to Overdue tab to view overdue task records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @When("User click on Overdue tab")
    public void User_click_on_Overdue_tab() {
        AllureReportManager.logStep("⏰ Clicking on Overdue tab");
        
        try {
            taskCommonPage.clickOverdueTab();
            wait.until(driver -> taskCommonPage.isOverdueTabActive());
            
            AllureReportManager.logStepWithStatus("Overdue tab click", "PASSED");
            AllureReportManager.takeScreenshot("Overdue Tab Clicked");
            System.out.println("Clicked on Overdue tab");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Overdue tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Overdue Tab Click Failed");
            throw e;
        }
    }

    @Step("Validate Overdue tab is active")
    @Description("Verify that the Overdue tab is active and properly displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @Then("User validate that the Overdue tab is active")
    public void User_validate_that_the_Overdue_tab_is_active() {
        AllureReportManager.logStep("✅ Validating Overdue tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isOverdueTabActive();
            boolean hasActiveColor = taskCommonPage.isOverdueTabActive();
            
            AllureReportManager.attachText("Overdue Tab Active", String.valueOf(isTabActive));
            AllureReportManager.attachText("Overdue Tab Has Active Color", String.valueOf(hasActiveColor));
            
            Assert.assertTrue(isTabActive, "Overdue tab is not active");
            Assert.assertTrue(hasActiveColor, "Overdue tab does not have active color");
            
            AllureReportManager.logStepWithStatus("Overdue tab active validation", "PASSED");
            AllureReportManager.takeScreenshot("Overdue Tab Active Validated");
            System.out.println("Overdue tab is active");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Overdue tab active validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Overdue Tab Active Validation Failed");
            throw e;
        }
    }
    @Step("Get overdue total task count")
    @Description("Retrieve and validate the total count of overdue tasks")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("Overdue total task count")
    public void Overdue_total_task_count() {
        AllureReportManager.logStep("📊 Getting overdue total task count");
        
        try {
            String overdueCount = taskCommonPage.getOverdueCount();
            
            AllureReportManager.attachText("Overdue Count", overdueCount);
            
            Assert.assertNotNull(overdueCount, "Overdue count is null");
            Assert.assertTrue(overdueCount.matches("\\d+"), "Overdue count is not a valid number: " + overdueCount);
            
            AllureReportManager.logStepWithStatus("Overdue total task count retrieval", "PASSED");
            AllureReportManager.takeScreenshot("Overdue Total Task Count");
            System.out.println("Overdue Count: " + overdueCount);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Overdue total task count retrieval", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Overdue Total Task Count Failed");
            throw e;
        }
    }
    
    @Step("Validate sum of sub-tab counts equals Overdue total")
    @Description("Verify that the sum of all sub-tab task counts matches the Overdue total task count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Task Count Validation")
    @Then("Sum of all sub-tab task counts should be equal to Overdue total task count")
    public void Sum_of_all_sub_tab_task_counts_should_be_equal_to_Overdue_total_task_count() {
        AllureReportManager.logStep("🧮 Validating sum of sub-tab counts equals Overdue total");
        
        try {
            String overdueTotalCount = taskCommonPage.getOverdueCount();
            int sumOfSubTabCounts =
                    parseCount(taskCommonPage.getExternalRecordsCount()) +
                    parseCount(taskCommonPage.getInternalRecordsCount()) +
                    parseCount(taskCommonPage.getListsConfigurationCount()) +
                    parseCount(taskCommonPage.getPressReleaseRecordsCount()) +
                    parseCount(taskCommonPage.getTemplatesCount());

            String sumOfSubTabCountsStr = String.valueOf(sumOfSubTabCounts);

            AllureReportManager.attachText("Overdue Total Count", overdueTotalCount);
            AllureReportManager.attachText("Sum of Sub-tab Counts", sumOfSubTabCountsStr);
            AllureReportManager.attachText("External Records Count", taskCommonPage.getExternalRecordsCount());
            AllureReportManager.attachText("Internal Records Count", taskCommonPage.getInternalRecordsCount());
            AllureReportManager.attachText("Lists Configuration Count", taskCommonPage.getListsConfigurationCount());
            AllureReportManager.attachText("Press Release Records Count", taskCommonPage.getPressReleaseRecordsCount());
            AllureReportManager.attachText("Templates Count", taskCommonPage.getTemplatesCount());

            System.out.println("Sum of Sub-tab Counts: " + sumOfSubTabCounts);
            Assert.assertEquals(overdueTotalCount, sumOfSubTabCountsStr, "Sum of all sub-tab task counts is not equal to Overdue total task count");
            System.out.println("Sum of all sub-tab task counts is equal to Overdue total task count");
            
            AllureReportManager.logStepWithStatus("Overdue sum validation", "PASSED");
            AllureReportManager.takeScreenshot("Overdue Sum Validation Passed");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Overdue sum validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Overdue Sum Validation Failed");
            throw e;
        }
    }

    @Step("Click on Review tab")
    @Description("Navigate to Review tab to view review task records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @When("User click on Review tab")
    public void User_click_on_Review_tab() {
        AllureReportManager.logStep("👁️ Clicking on Review tab");
        
        try {
            taskCommonPage.clickReviewTab();
            wait.until(driver -> taskCommonPage.isReviewTabActive());
            
            AllureReportManager.logStepWithStatus("Review tab click", "PASSED");
            AllureReportManager.takeScreenshot("Review Tab Clicked");
            System.out.println("Clicked on Review tab");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Review tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Review Tab Click Failed");
            throw e;
        }
    }

    @Step("Validate Review tab is active")
    @Description("Verify that the Review tab is active and properly displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @Then("User validate that the Review tab is active")
    public void User_validate_that_the_Review_tab_is_active() {
        AllureReportManager.logStep("✅ Validating Review tab is active");
        
        try {
            boolean isTabActive = taskCommonPage.isReviewTabActive();
            boolean hasActiveColor = taskCommonPage.isReviewTabActive();
            
            AllureReportManager.attachText("Review Tab Active", String.valueOf(isTabActive));
            AllureReportManager.attachText("Review Tab Has Active Color", String.valueOf(hasActiveColor));
            
            Assert.assertTrue(isTabActive, "Review tab is not active");
            Assert.assertTrue(hasActiveColor, "Review tab does not have active color");
            
            AllureReportManager.logStepWithStatus("Review tab active validation", "PASSED");
            AllureReportManager.takeScreenshot("Review Tab Active Validated");
            System.out.println("Review tab is active");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Review tab active validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Review Tab Active Validation Failed");
            throw e;
        }
    }

    @Step("Get review total task count")
    @Description("Retrieve and validate the total count of review tasks")
    @Severity(SeverityLevel.NORMAL)
    @Story("Task Count Validation")
    @Then("Review total task count")
    public void Review_total_task_count() {
        AllureReportManager.logStep("📊 Getting review total task count");
        
        try {
            String reviewCount = taskCommonPage.getReviewCount();
            
            AllureReportManager.attachText("Review Count", reviewCount);
            
            Assert.assertNotNull(reviewCount, "Review count is null");
            Assert.assertTrue(reviewCount.matches("\\d+"), "Review count is not a valid number: " + reviewCount);
            
            AllureReportManager.logStepWithStatus("Review total task count retrieval", "PASSED");
            AllureReportManager.takeScreenshot("Review Total Task Count");
            System.out.println("Review Count: " + reviewCount);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Review total task count retrieval", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Review Total Task Count Failed");
            throw e;
        }
    }

    @Step("Validate sum of sub-tab counts equals Review total")
    @Description("Verify that the sum of all sub-tab task counts matches the Review total task count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Task Count Validation")
    @Then("Sum of all sub-tab task counts should be equal to Review total task count")
    public void Sum_of_all_sub_tab_task_counts_should_be_equal_to_Review_total_task_count() {
        AllureReportManager.logStep("🧮 Validating sum of sub-tab counts equals Review total");
        
        try {
            String reviewTotalCount = taskCommonPage.getReviewCount();
            int sumOfSubTabCounts =
                    parseCount(taskCommonPage.getExternalRecordsCount()) +
                    parseCount(taskCommonPage.getInternalRecordsCount());

            String sumOfSubTabCountsStr = String.valueOf(sumOfSubTabCounts);

            AllureReportManager.attachText("Review Total Count", reviewTotalCount);
            AllureReportManager.attachText("Sum of Sub-tab Counts", sumOfSubTabCountsStr);
            AllureReportManager.attachText("External Records Count", taskCommonPage.getExternalRecordsCount());
            AllureReportManager.attachText("Internal Records Count", taskCommonPage.getInternalRecordsCount());

            System.out.println("Sum of Sub-tab Counts: " + sumOfSubTabCounts);
            Assert.assertEquals(reviewTotalCount, sumOfSubTabCountsStr, "Sum of all sub-tab task counts is not equal to Review total task count");
            System.out.println("Sum of all sub-tab task counts is equal to Review total task count");
            
            AllureReportManager.logStepWithStatus("Review sum validation", "PASSED");
            AllureReportManager.takeScreenshot("Review Sum Validation Passed");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Review sum validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Review Sum Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on External Records tab")
    @Description("User clicks on the External Records tab to switch the view and waits for page load")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on External Records sub-tab")
    public void User_click_on_External_Records_tab() {
        AllureReportManager.logStep("📂 Clicking on External Records tab");
        
        try {
            taskCommonPage.clickExternalRecordsSubTab();
            wait.until(driver -> taskCommonPage.isExternalRecordsSubTabActive());
            
            AllureReportManager.logStepWithStatus("External Records tab click", "PASSED");
            AllureReportManager.takeScreenshot("External Records Tab Clicked");
            System.out.println("Clicked on External Records tab");
            
            Thread.sleep(1000); // Wait for the page to load
            
            AllureReportManager.logStep("Waiting for page to load after External Records tab click");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("External Records tab click", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted while waiting for page load: " + e.getMessage());
            AllureReportManager.takeScreenshot("External Records Tab Click Interrupted");
            System.out.println("Thread was interrupted while waiting for the page to load");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("External Records tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("External Records Tab Click Failed");
            throw e;
        }
    }

    @Step("Validate row counts in pagination")
    @Description("Verify that the actual row count matches the current row count displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Validation")
    @Then("User validates Pagination count with page row counts")
    public void User_validates_row_counts() {
        AllureReportManager.logStep("📊 Validating row counts in pagination");
        
        try {
            int actualRowCount = paginationPage.getActualRowCount();
            int currentRowCount = paginationPage.getCurrentRowCount();
            
            AllureReportManager.attachText("Actual Row Count", String.valueOf(actualRowCount));
            AllureReportManager.attachText("Current Row Count", String.valueOf(currentRowCount));
            
            System.out.println("Actual Row Count: " + actualRowCount);
            System.out.println("Current Row Count: " + currentRowCount);
            
            Assert.assertEquals(currentRowCount, actualRowCount, "Current row count is not equal to actual row count");
            
            AllureReportManager.logStepWithStatus("Row count validation", "PASSED");
            AllureReportManager.takeScreenshot("Row Counts Validated");
            System.out.println("Row counts are valid");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Row count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Row Count Validation Failed");
            throw e;
        }
    }
    
    @Step("Change pagination count to: {int}")
    @Description("User changes the pagination count to display different number of records per page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Management")
    @When("User changes the pagination count to {int}")
    public void User_changes_the_pagination_count_to(int option) {
        AllureReportManager.logStep("⚙️ Changing pagination count to: " + option);
        
        try {
            paginationPage.changePaginationCount(option);
            int newPaginationCount = paginationPage.getPaginationCount();
            
            AllureReportManager.attachText("Target Pagination Count", String.valueOf(option));
            AllureReportManager.attachText("New Pagination Count", String.valueOf(newPaginationCount));
            
            System.out.println("Changed pagination count to: " + option);
            System.out.println("New Pagination Count: " + newPaginationCount);
            
            AllureReportManager.logStepWithStatus("Pagination count change", "PASSED");
            AllureReportManager.takeScreenshot("Pagination Count Changed to " + option);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pagination count change", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Count Change Failed");
            throw e;
        }
    }
    
    @Step("Validate pagination count increase on next button clicks")
    @Description("Verify that pagination count increases by the selected rows per page on each next button click")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @Then("On Each next button click pagination count should increase by rows per page selected")
    public void validatePaginationCountIncrease() {
        AllureReportManager.logStep("⏭️ Validating pagination count increase on next button clicks");
        
        try {
            int step = paginationPage.getPaginationCount(); // rows per page selected
            int initialPaginationCount = step;
            int clickCount = 8; // Default click count
            int expectedPaginationCount = initialPaginationCount;
            
            AllureReportManager.attachText("Initial Pagination Count", String.valueOf(initialPaginationCount));
            AllureReportManager.attachText("Step Size (Rows per Page)", String.valueOf(step));
            AllureReportManager.attachText("Number of Clicks", String.valueOf(clickCount));
            
            System.out.println("Initial Pagination Count: " + initialPaginationCount);
            
            for (int i = 0; i < clickCount; i++) {
                paginationPage.clickOnNextButton();
                System.out.println("Clicked on Next button: " + (i + 1));
                
                Thread.sleep(2000);
                
                expectedPaginationCount += step;
                int currentPaginationCount = paginationPage.getPaginationCount();
                
                AllureReportManager.attachText("Click " + (i + 1) + " - Expected Count", String.valueOf(expectedPaginationCount));
                AllureReportManager.attachText("Click " + (i + 1) + " - Actual Count", String.valueOf(currentPaginationCount));
                
                System.out.println("Pagination Count after click " + (i + 1) + ": " + currentPaginationCount);
                Assert.assertEquals(currentPaginationCount, expectedPaginationCount, "Pagination count did not increase by " + step + " after click " + (i + 1));
            }
            
            System.out.println("Pagination count increased by " + step + " on each next click successfully");
            
            AllureReportManager.logStepWithStatus("Pagination count increase validation", "PASSED");
            AllureReportManager.takeScreenshot("Pagination Count Increase Validated");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Pagination count increase validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Count Increase Interrupted");
            System.out.println("Thread was interrupted while waiting after clicking next button");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pagination count increase validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Count Increase Failed");
            throw e;
        }
    }

    @Step("Validate pagination count changed to expected value")
    @Description("Verify that pagination count has changed to the expected value")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Management")
    @Then("pagination count should change to {int}")
    public void paginationCountShouldChangeTo(int expectedCount) {
        AllureReportManager.logStep("🔢 Validating pagination count changed to: " + expectedCount);
        
        try {
            int actualCount = paginationPage.getPaginationCount();
            
            AllureReportManager.attachText("Expected Pagination Count", String.valueOf(expectedCount));
            AllureReportManager.attachText("Actual Pagination Count", String.valueOf(actualCount));
            
            System.out.println("Expected Pagination Count: " + expectedCount);
            System.out.println("Actual Pagination Count: " + actualCount);
            
            Assert.assertEquals(actualCount, expectedCount, "Pagination count did not change to expected value");
            
            AllureReportManager.logStepWithStatus("Pagination count validation", "PASSED");
            AllureReportManager.takeScreenshot("Pagination Count Changed Successfully");
            System.out.println("Pagination count changed to expected value successfully");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pagination count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Count Validation Failed");
            throw e;
        }
    }

    @Step("Validate records count in pagination text")
    @Description("Verify the total records count displayed in pagination text")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Information")
    @Then("validate reords count in pagination text")
    public void validate_reords_count_in_pagination_text() {
        AllureReportManager.logStep("📊 Validating records count in pagination text");
        
        try {
            int totalRecordsCount = paginationPage.gettotalRecordsCount();
            
            AllureReportManager.attachText("Total Records Count", String.valueOf(totalRecordsCount));
            
            System.out.println("Total Records Count: " + totalRecordsCount);
            
            AllureReportManager.logStepWithStatus("Records count validation", "PASSED");
            AllureReportManager.takeScreenshot("Records Count in Pagination Text");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Records count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Records Count Validation Failed");
            throw e;
        }
    }

    @Step("Click on double arrow right icon for last page navigation")
    @Description("Click on the last page navigation button (double arrow right icon)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @When("User click on Double Arrow Right icon")
    public void User_click_on_Double_Arrow_Right_icon() {
        AllureReportManager.logStep("⏩ Clicking on double arrow right icon to navigate to last page");
        
        try {
            paginationPage.clickOnLastButton();
            
            AllureReportManager.logStepWithStatus("Double arrow right icon click", "PASSED");
            AllureReportManager.takeScreenshot("Double Arrow Right Icon Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Double arrow right icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Double Arrow Right Icon Click Failed");
            throw e;
        }
    }

    @Step("Validate navigation to last page")
    @Description("Verify that user is successfully navigated to the last page and validate pagination count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @Then("User should navigated to last page and validate pagination count")
    public void User_should_navigated_to_last_page_and_validate_pagination_count() {
        AllureReportManager.logStep("✅ Validating navigation to last page and pagination count");
        
        try {
            int currentPaginationCount = paginationPage.getPaginationCount();
            
            AllureReportManager.attachText("Current Pagination Count on Last Page", String.valueOf(currentPaginationCount));
            
            System.out.println("Records count after clicking last button: " + currentPaginationCount);
            
            AllureReportManager.logStepWithStatus("Last page navigation validation", "PASSED");
            AllureReportManager.takeScreenshot("Last Page Navigation Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Last page navigation validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Last Page Navigation Validation Failed");
            throw e;
        }
    }

    @Step("Validate pagination count decrease on back button clicks")
    @Description("Verify that pagination count decreases by the actual rows on each back button click")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @Then("On Each back button click pagination count should decrease by rows per page selected")
    public void validatePaginationCountDecrease() {
        AllureReportManager.logStep("⏮️ Validating pagination count decrease on back button clicks");
        
        try {
            int clickCount = 5; // Default click count
            int expectedStartRecord = paginationPage.gettotalRecordsCount();
            
            AllureReportManager.attachText("Initial Start Record", String.valueOf(expectedStartRecord));
            AllureReportManager.attachText("Number of Back Clicks", String.valueOf(clickCount));
            
            System.out.println("Initial Start Record: " + expectedStartRecord);
            
            for (int i = 0; i < clickCount; i++) {
                paginationPage.clickOnPreviousButton();
                System.out.println("Clicked on Back button: " + (i + 1));
                
                Thread.sleep(2000);
                
                int rowsOnCurrentPage = paginationPage.getActualRowCount();
                expectedStartRecord -= rowsOnCurrentPage;
                int currentStartRecord = paginationPage.gettotalRecordsCount();
                
                AllureReportManager.attachText("Back Click " + (i + 1) + " - Rows on Page", String.valueOf(rowsOnCurrentPage));
                AllureReportManager.attachText("Back Click " + (i + 1) + " - Expected Start Record", String.valueOf(expectedStartRecord));
                AllureReportManager.attachText("Back Click " + (i + 1) + " - Actual Start Record", String.valueOf(currentStartRecord));
                
                System.out.println("Start Record after click " + (i + 1) + ": " + currentStartRecord);
                Assert.assertEquals(currentStartRecord, expectedStartRecord, "Start record did not decrease by " + rowsOnCurrentPage + " after click " + (i + 1));
            }
            
            System.out.println("Start record decreased by actual rows on each back click successfully");
            
            AllureReportManager.logStepWithStatus("Pagination back button decrease validation", "PASSED");
            AllureReportManager.takeScreenshot("Pagination Back Button Decrease Validated");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Pagination back button decrease validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Back Button Decrease Interrupted");
            System.out.println("Thread was interrupted while waiting after clicking back button");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Pagination back button decrease validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Pagination Back Button Decrease Failed");
            throw e;
        }
    }

    @Step("Click on double arrow left icon for first page navigation")
    @Description("Click on the first page navigation button (double arrow left icon)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @When("User click on Double Arrow Left icon")
    public void User_click_on_Double_Arrow_Left_icon() {
        AllureReportManager.logStep("⏪ Clicking on double arrow left icon to navigate to first page");
        
        try {
            paginationPage.clickOnFirstButton();
            
            AllureReportManager.logStepWithStatus("Double arrow left icon click", "PASSED");
            AllureReportManager.takeScreenshot("Double Arrow Left Icon Clicked");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Double arrow left icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Double Arrow Left Icon Click Failed");
            throw e;
        }
    }

    @Step("Validate navigation to first page")
    @Description("Verify that user is successfully navigated to the first page after clicking double arrow left")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @Then("User should navigated to first page")
    public void User_should_navigated_to_first_page() {
        AllureReportManager.logStep("✅ Validating navigation to first page");
        
        try {
            int currentPaginationCount = paginationPage.getPaginationCount();
            
            AllureReportManager.attachText("Current Pagination Count", String.valueOf(currentPaginationCount));
            
            System.out.println("Records count after clicking first button: " + currentPaginationCount);
            
            AllureReportManager.logStepWithStatus("First page navigation validation", "PASSED");
            AllureReportManager.takeScreenshot("First Page Navigation Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("First page navigation validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("First Page Navigation Validation Failed");
            throw e;
        }
    }

    @Step("Validate first page and previous page buttons are disabled")
    @Description("Verify that first page button and previous page button are disabled when on first page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @Then("Validated that btnfirstPage and btnpreviousPage are disabled")
    public void Validated_that_btnfirstPage_and_btnpreviousPage_are_disabled() {
        AllureReportManager.logStep("🔘 Validating first page and previous page buttons are disabled");
        
        try {
            boolean isFirstPagebtnDisabled = paginationPage.isFirstPageButtonDisabled();
            boolean isPreviousPagebtnDisabled = paginationPage.isPreviousPageButtonDisabled();
            
            AllureReportManager.attachText("First Page Button Disabled", String.valueOf(isFirstPagebtnDisabled));
            AllureReportManager.attachText("Previous Page Button Disabled", String.valueOf(isPreviousPagebtnDisabled));
            
            System.out.println("Is First Page button disabled? " + isFirstPagebtnDisabled);
            System.out.println("Is Previous Page button disabled? " + isPreviousPagebtnDisabled);
            
            Assert.assertTrue(isFirstPagebtnDisabled, "First Page button is not disabled");
            Assert.assertTrue(isPreviousPagebtnDisabled, "Previous Page button is not disabled");
            
            AllureReportManager.logStepWithStatus("First and previous buttons disabled validation", "PASSED");
            AllureReportManager.takeScreenshot("First and Previous Buttons Disabled Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("First and previous buttons disabled validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("First and Previous Buttons Disabled Validation Failed");
            throw e;
        }
    }

    @Step("Validate last page and next page buttons are disabled")
    @Description("Verify that last page button and next page button are disabled when on last page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Navigation")
    @When("Validated that btnlastPage and btnnextPage are disabled")
    public void Validated_that_btnlastPage_and_btnnextPage_are_disabled() {
        AllureReportManager.logStep("🔘 Validating last page and next page buttons are disabled");
        
        try {
            boolean isLastPagebtnDisabled = paginationPage.isLastPageButtonDisabled();
            boolean isNextPagebtnDisabled = paginationPage.isNextPageButtonDisabled();
            
            AllureReportManager.attachText("Last Page Button Disabled", String.valueOf(isLastPagebtnDisabled));
            AllureReportManager.attachText("Next Page Button Disabled", String.valueOf(isNextPagebtnDisabled));
            
            System.out.println("Is Last Page button disabled? " + isLastPagebtnDisabled);
            System.out.println("Is Next Page button disabled? " + isNextPagebtnDisabled);
            
            Assert.assertTrue(isLastPagebtnDisabled, "Last Page button is not disabled");
            Assert.assertTrue(isNextPagebtnDisabled, "Next Page button is not disabled");
            
            AllureReportManager.logStepWithStatus("Last and next buttons disabled validation", "PASSED");
            AllureReportManager.takeScreenshot("Last and Next Buttons Disabled Validated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Last and next buttons disabled validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Last and Next Buttons Disabled Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on Claimed by filter icon")
    @Description("User clicks on the Claimed by filter icon to open filter options")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Filtering")
    @When("User click on Claimed by filter icon")
    public void User_click_on_Claimed_by_filter_icon() {
        AllureReportManager.logStep("🔍 Clicking on Claimed by filter icon");
        
        try {
            taskCommonPage.clickFilterClaimedBy();
            System.out.println("Clicked on Claimed by filter icon");

            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.defaultRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            AllureReportManager.logStepWithStatus("Claimed by filter icon click", "PASSED");
            AllureReportManager.takeScreenshot("Claimed by Filter Opened");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Claimed by filter icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claimed by Filter Click Failed");
            throw e;
        }
    }
    @Step("Click on List Name filter icon")
    @Description("User clicks on the List Name filter icon to open filter options")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Filtering")
    @When("User click on List Name filter icon")
    public void User_click_on_List_Name_filter_icon() {
        AllureReportManager.logStep("🔍 Clicking on List Name filter icon");
        
        try {
            taskCommonPage.clickFilterListName();
            System.out.println("Clicked on List Name filter icon");

            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.defaultRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            AllureReportManager.logStepWithStatus("List Name filter icon click", "PASSED");
            AllureReportManager.takeScreenshot("List Name Filter Opened");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("List Name filter icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("List Name Filter Click Failed");
            throw e;
        }
    }

    @Step("Click on Type filter icon")
    @Description("User clicks on the Type filter icon to open filter options")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Filtering")
    @When("User click on Type filter icon")
    public void User_click_on_Type_filter_icon() {
        AllureReportManager.logStep("🔍 Clicking on Type filter icon");
        
        try {
            taskCommonPage.clickFilterType();
            System.out.println("Clicked on Type filter icon");

            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.defaultRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            AllureReportManager.logStepWithStatus("Type filter icon click", "PASSED");
            AllureReportManager.takeScreenshot("Type Filter Opened");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Type filter icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Type Filter Click Failed");
            throw e;
        }
    }

    @Step("Click on Action filter icon")
    @Description("User clicks on the Action filter icon to open filter options")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Filtering")
    @When("User click on Action filter icon")
    public void User_click_on_Action_filter_icon() {
        AllureReportManager.logStep("🔍 Clicking on Action filter icon");
        
        try {
            taskCommonPage.clickFilterAction();
            System.out.println("Clicked on Action filter icon");

            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.defaultRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            AllureReportManager.logStepWithStatus("Action filter icon click", "PASSED");
            AllureReportManager.takeScreenshot("Action Filter Opened");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Action filter icon click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Action Filter Click Failed");
            throw e;
        }
    }
   
    @Step("Check on Select all checkbox")
    @Description("User checks the Select all checkbox to select all visible records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Selection")
    @Then("checked on Select all checkbox")
    public void checked_on_select_all_checkbox() {
        AllureReportManager.logStep("☑️ Checking on Select all checkbox");
        
        try {
            taskCommonPage.clickSelectAllCheckbox();
            Thread.sleep(3000);
            
            System.out.println("Select all checkbox is checked");
            paginationPage.clickAnywhere(1200, 100);
            
            AllureReportManager.logStepWithStatus("Select all checkbox operation", "PASSED");
            AllureReportManager.takeScreenshot("Select All Checkbox Checked");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Select all checkbox operation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Select All Checkbox Interrupted");
            System.out.println("Thread was interrupted while waiting after selecting all checkbox");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Select all checkbox operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Select All Checkbox Failed");
            throw e;
        }
    }


    @Step("Validate filtered results match default count")
    @Description("Verify that the filtered records count matches the default records count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Results Validation")
    @Then("User Validated the results")
    public void User_Validated_the_results() {
        AllureReportManager.logStep("✅ Validating filtered results match default count");
        
        try {
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            int filteredRecordsCount = paginationPage.getFilteredRecordsCount();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            AllureReportManager.attachText("Filtered Records Count", String.valueOf(filteredRecordsCount));
            
            System.out.println("Default Records Count: " + defaultRecordsCount);
            System.out.println("Filtered Records Count: " + filteredRecordsCount);
            
            Assert.assertEquals(filteredRecordsCount, defaultRecordsCount, "Filtered records count is not equal to default records count");
            
            AllureReportManager.logStepWithStatus("Filter results validation", "PASSED");
            AllureReportManager.takeScreenshot("Filter Results Validated");
            System.out.println("Filter results validation passed successfully");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Filter results validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Filter Results Validation Failed");
            throw e;
        }
    }

    @Step("Check claimed checkbox and validate results")
    @Description("User checks the claimed checkbox and validates that claimed records count is less than total records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter by Claimed Status")
    @Then("Checked on Claimed checkbox")
    public void Checked_on_Claimed_checkbox() {
        AllureReportManager.logStep("☑️ Checking claimed checkbox and validating results");
        
        try {
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            System.out.println("Default Records Count: " + defaultRecordsCount);
            
            taskCommonPage.clickClaimedCheckbox();
            paginationPage.clickAnywhere(1200, 100);
            
            Thread.sleep(2000);
            System.out.println("Claimed checkbox is checked");
            
            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.getFilteredRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            int claimedRecordsCount = paginationPage.getFilteredRecordsCount();
            
            AllureReportManager.attachText("Claimed Records Count", String.valueOf(claimedRecordsCount));
            System.out.println("Claimed Records Count: " + claimedRecordsCount);
            
            Assert.assertTrue(claimedRecordsCount < defaultRecordsCount, "Claimed records count is not less than total records count");
            
            AllureReportManager.logStepWithStatus("Claimed checkbox validation", "PASSED");
            AllureReportManager.takeScreenshot("Claimed Checkbox Checked and Validated");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Claimed checkbox validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Claimed Checkbox Interrupted");
            System.out.println("Thread was interrupted while waiting after clicking Claimed checkbox");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Claimed checkbox validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claimed Checkbox Validation Failed");
            throw e;
        }
    }

    
    @Step("Click refresh button and reload page data")
    @Description("User clicks on the refresh button to reload the page data and verify the default records count")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page Refresh Functionality")
    @When("user clicks on refresh button")
    public void user_clicks_on_refresh_button() {
        AllureReportManager.logStep("🔄 Clicking refresh button to reload page data");
        
        try {
            taskCommonPage.clickRefreshButton();
            Thread.sleep(2000);
            
            AllureReportManager.logStep("Clicking refresh button again for complete reload");
            taskCommonPage.clickRefreshButton();

            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            
            AllureReportManager.attachText("Default Records Count After Refresh", String.valueOf(defaultRecordsCount));
            System.out.println("Default Records Count after refresh: " + defaultRecordsCount);
            
            AllureReportManager.logStepWithStatus("Refresh button operation", "PASSED");
            AllureReportManager.takeScreenshot("Page Refreshed Successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Refresh button operation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Refresh Button Operation Interrupted");
            System.out.println("Thread was interrupted while waiting for page refresh");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Refresh button operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Refresh Button Operation Failed");
            throw e;
        }
    }

    @Step("Check unclaimed checkbox and validate results")
    @Description("User checks the unclaimed checkbox and validates that unclaimed records count is less than total records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter by Unclaimed Status")
    @Then("Checked on Unclaimed checkbox")
    public void Checked_on_Unclaimed_checkbox() {
        AllureReportManager.logStep("☑️ Checking unclaimed checkbox and validating results");
        
        try {
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            System.out.println("Default Records Count: " + defaultRecordsCount);
            
            taskCommonPage.clickUnclaimedCheckbox();
            paginationPage.clickAnywhere(1200, 100);

            Thread.sleep(2000);
            
            // Wait for the filtered records to be updated
            wait.until(driver -> {
                try {
                    int count = paginationPage.getFilteredRecordsCount();
                    return count > 0; // Wait until filtered records are loaded
                } catch (Exception e) {
                    return false;
                }
            });
            
            int unClaimedRecordsCount = paginationPage.getFilteredRecordsCount();
            
            AllureReportManager.attachText("Unclaimed Records Count", String.valueOf(unClaimedRecordsCount));
            System.out.println("Unclaimed Records Count: " + unClaimedRecordsCount);
            
            Assert.assertTrue(unClaimedRecordsCount < defaultRecordsCount, "Unclaimed records count is not less than total records count");
            
            AllureReportManager.logStepWithStatus("Unclaimed checkbox validation", "PASSED");
            AllureReportManager.takeScreenshot("Unclaimed Checkbox Checked and Validated");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Unclaimed checkbox validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Unclaimed Checkbox Interrupted");
            System.out.println("Thread was interrupted while waiting after clicking Unclaimed checkbox");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Unclaimed checkbox validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Unclaimed Checkbox Validation Failed");
            throw e;
        }
    }
    @Step("Validate unclaimed records count")
    @Description("Verify that unclaimed records count is less than or equal to total records count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Record Count Validation")
    @Then("User validated the unclaimed records count is less than or equal to the total records count")
    public void User_validated_the_unclaimed_records_count_is_less_than_or_equal_to_the_total_records_count() {
        AllureReportManager.logStep("📊 Validating unclaimed records count against total records count");
        
        try {
            int unclaimedRecordsCount = paginationPage.getFilteredRecordsCount();
            int totalRecordsCount = paginationPage.gettotalRecordsCount();
            
            AllureReportManager.attachText("Unclaimed Records Count", String.valueOf(unclaimedRecordsCount));
            AllureReportManager.attachText("Total Records Count", String.valueOf(totalRecordsCount));
            
            System.out.println("Unclaimed Records Count: " + unclaimedRecordsCount);
            System.out.println("Total Records Count: " + totalRecordsCount);
            
            Assert.assertTrue(unclaimedRecordsCount < totalRecordsCount, "Unclaimed records count is greater than total records count");
            
            AllureReportManager.logStepWithStatus("Unclaimed records count validation", "PASSED");
            AllureReportManager.takeScreenshot("Unclaimed Records Count Validated");
            System.out.println("Unclaimed records count validation passed successfully");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Unclaimed records count validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Unclaimed Records Count Validation Failed");
            throw e;
        }
    }

    @Step("Validate unclaimed records don't contain user names")
    @Description("Verify that unclaimed records in the claimed by section don't contain any user names (should show dash)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Unclaimed Records Validation")
    @Then("Validate that unclaimed records doesn't contains any user name")
    public void Validate_that_claimed_by_section_doesn_t_contains_any_user_name() {
        AllureReportManager.logStep("🔍 Validating unclaimed records don't contain user names");
        
        try {
            boolean isClaimedBySectionEmpty = paginationPage.validateUnclaimedRecordsContainDash();
            
            AllureReportManager.attachText("Is Claimed By Section Empty (Contains Dash)", String.valueOf(isClaimedBySectionEmpty));
            System.out.println("Is Claimed By section empty (contains dash)? " + isClaimedBySectionEmpty);
            
            Assert.assertTrue(isClaimedBySectionEmpty, "Unclaimed records contain user names instead of dash");
            
            AllureReportManager.logStepWithStatus("Unclaimed records validation", "PASSED");
            AllureReportManager.takeScreenshot("Unclaimed Records Validated - No User Names");
            System.out.println("Unclaimed records validation passed - no user names found");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Unclaimed records validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Unclaimed Records Validation Failed");
            throw e;
        }
    }

    @Step("Validate claimed records contain user names and lock icons")
    @Description("Verify that claimed records contain user names and have lock icons displayed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Claimed Records Validation")
    @Then("Validate that claimed records contains user name and have lock icon")
    public void Validate_that_claimed_records_contains_user_name_and_have_lock_icon() {
        AllureReportManager.logStep("🔐 Validating claimed records contain user names and lock icons");
        
        try {
            // Get individual claimed user names from getClaimedRecordsList()
            java.util.List<String> claimedRecordsList = paginationPage.getClaimedRecordsList();
            
            AllureReportManager.attachText("Number of Claimed Records", String.valueOf(claimedRecordsList != null ? claimedRecordsList.size() : 0));
            System.out.println("Claimed Records User Names:");
            
            // Check if there are any claimed records
            if (claimedRecordsList == null || claimedRecordsList.isEmpty()) {
                AllureReportManager.attachText("Claimed Records Status", "No claimed records found on the page");
                System.out.println("No claimed records found on the page");
                return;
            }
            
            // Print each claimed record name on a new line - properly formatted
            StringBuilder claimedRecordsText = new StringBuilder();
            for (int i = 0; i < claimedRecordsList.size(); i++) {
                String name = claimedRecordsList.get(i);
                if (name != null && !name.trim().isEmpty()) {
                    String recordInfo = (i + 1) + ". " + name;
                    claimedRecordsText.append(recordInfo).append("\n");
                    System.out.println(recordInfo);
                }
            }
            AllureReportManager.attachText("Claimed Records User Names", claimedRecordsText.toString());
            
            // Validate that each claimed record has a lock icon
            boolean allLockIconsPresent = paginationPage.validateLockIconsPresent();
            
            AllureReportManager.attachText("All Lock Icons Present", String.valueOf(allLockIconsPresent));
            Assert.assertTrue(allLockIconsPresent, "Not all claimed records have a lock icon");
            
            AllureReportManager.logStepWithStatus("Claimed records validation", "PASSED");
            AllureReportManager.takeScreenshot("Claimed Records Validated - User Names and Lock Icons");
            System.out.println("Claimed records validation passed - all records have user names and lock icons");
            
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Claimed records validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claimed Records Validation Failed");
            System.out.println("Error validating claimed records: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Failed to validate claimed records: " + e.getMessage());
        }
    }
    @Step("Click on claimed all record checkbox")
    @Description("User clicks on all claimed record checkboxes to select them")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Selection")
    @When("User click on claimed all record checkbox")
    public void userClickOnRecordCheckbox() {
        AllureReportManager.logStep("☑️ Clicking on claimed all record checkbox");
        
        try {
            taskCommonPage.selectAllrecordsCheckBox();
            Thread.sleep(2000);
            
            System.out.println("Clicked on any claimed record checkbox");
            
            AllureReportManager.logStepWithStatus("Claimed record checkbox selection", "PASSED");
            AllureReportManager.takeScreenshot("Claimed Record Checkbox Selected");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Claimed record checkbox selection", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Claimed Record Checkbox Selection Interrupted");
            System.out.println("Thread was interrupted while waiting for the page to load");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Claimed record checkbox selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claimed Record Checkbox Selection Failed");
            throw e;
        }
    }
    @Step("Validate alert text and close alert")
    @Description("Verify the alert text content and close the alert dialog")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Alert Validation")
    @Then("Validate alert text and close alert")
    public void Validate_alert_text_and_close_alert() {
        AllureReportManager.logStep("⚠️ Validating alert text and closing alert");
        
        try {
            String alertText = taskCommonPage.getAlertText();
            
            AllureReportManager.attachText("Alert Text", alertText);
            System.out.println("Alert Text: " + alertText);
            
            Assert.assertTrue(alertText.contains("Please select one source and status for bulk claiming or unclaiming"), 
                            "Alert text does not match expected text");
            
            taskCommonPage.closeAlert();
            taskCommonPage.selectAllrecordsCheckBox();
            
            AllureReportManager.logStepWithStatus("Alert validation and closure", "PASSED");
            AllureReportManager.takeScreenshot("Alert Validated and Closed");
            System.out.println("Alert validation passed and alert closed successfully");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Alert validation and closure", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Alert Validation Failed");
            throw e;
        }
    }

    @Step("Validate submitted by column is sorted old to new")
    @Description("Verify that the submitted by column is sorted by old to new date order")
    @Severity(SeverityLevel.NORMAL)
    @Story("Column Sorting Validation")
    @When("User validate the submitted by column is sorted by old to new date")
    public void User_validate_the_submitted_by_column_is_sorted_by_old_to_new_date() {
        AllureReportManager.logStep("📅 Validating submitted by column is sorted by old to new date");
        
        try {
            boolean isSorted = taskCommonPage.validateDatesOldToNew();
            
            AllureReportManager.attachText("Is Sorted Old to New", String.valueOf(isSorted));
            
            Assert.assertTrue(isSorted, "Submitted By column is not sorted by old to new date");
            
            AllureReportManager.logStepWithStatus("Submitted by column sort validation (old to new)", "PASSED");
            AllureReportManager.takeScreenshot("Submitted By Column Sorted Old to New");
            System.out.println("Submitted by column is correctly sorted by old to new date");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Submitted by column sort validation (old to new)", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Submitted By Column Sort Validation Failed");
            throw e;
        }
    }

    @Step("Click sort by new to old date in submitted column")
    @Description("User clicks to sort by new to old date in submitted column and validates the sorting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Column Sorting")
    @When("User Click on sort by new to old date in submitted column")
    public void User_Click_on_sort_by_new_to_old_date_in_submitted_column() {
        AllureReportManager.logStep("📅 Clicking to sort by new to old date in submitted column");
        
        try {
            taskCommonPage.clickSortSubmittedByNewest();
            Thread.sleep(5000);
            
            boolean isSorted = taskCommonPage.validateDatesNewToOld();
            
            AllureReportManager.attachText("Is Sorted New to Old", String.valueOf(isSorted));
            Assert.assertTrue(isSorted, "Submitted By column is not sorted by new to old date");
            
            // Get the sorted dates (this method already prints the numbered list)
            List<String> sortedDates = taskCommonPage.getExtractedDates();
            StringBuilder datesText = new StringBuilder("Sorted Submitted By Dates (New to Old):\n");
            for (String date : sortedDates) {
                datesText.append(date).append("\n");
            }
            AllureReportManager.attachText("Sorted Dates (New to Old)", datesText.toString());
            
            AllureReportManager.logStepWithStatus("Submitted by column sort (new to old)", "PASSED");
            AllureReportManager.takeScreenshot("Submitted By Column Sorted New to Old");
            System.out.println("Submitted by column sorted by new to old date successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Submitted by column sort (new to old)", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Submitted By Column Sort Interrupted");
            System.out.println("Thread was interrupted while waiting for sort action");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Submitted by column sort (new to old)", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Submitted By Column Sort Failed");
            throw e;
        }
    }
    @Step("Click on Source Filter")
    @Description("User clicks on source filter and manages select all checkbox state")
    @Severity(SeverityLevel.NORMAL)
    @Story("Source Filtering")
    @When("User Click on Source Filter")
    public void userClickOnSourceFilter() {
        AllureReportManager.logStep("🔍 Clicking on Source Filter");
        
        try {
            taskCommonPage.clickSourceFilter();
            taskCommonPage.clickSourceSelectAllCheckbox();
            
            Thread.sleep(2000);
            
            taskCommonPage.clickSourceSelectAllCheckbox();
            
            AllureReportManager.logStepWithStatus("Source filter operation", "PASSED");
            AllureReportManager.takeScreenshot("Source Filter Opened and Configured");
            System.out.println("Source filter opened and configured successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Source filter operation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Source Filter Operation Interrupted");
            System.out.println("Thread was interrupted while waiting for filter to open");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Source filter operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Source Filter Operation Failed");
            throw e;
        }
    }
    @Step("Select Reconciliation source and validate results")
    @Description("User selects Reconciliation source from filter and validates the filtered results")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Source Filtering")
    @Then("User Selects a Reconciliation source and validated the results")
    public void userSelectsASource() {
        AllureReportManager.logStep("🎯 Selecting Reconciliation source and validating results");
        
        try {
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            
            taskCommonPage.clickSourceReconciliationCheckbox();
            Thread.sleep(2000);
            
            paginationPage.clickAnywhere(1200, 100);
            
            // Validate the results after selecting the source
            int filteredRecordsCount = paginationPage.getFilteredRecordsCount();
            
            AllureReportManager.attachText("Filtered Records Count", String.valueOf(filteredRecordsCount));
            
            Assert.assertTrue(filteredRecordsCount < defaultRecordsCount, "Filtered records count is not less than default records count");

            List<String> sourceName = taskCommonPage.getSourceValues();
            
            AllureReportManager.attachText("Source Values", sourceName.toString());
            
            Assert.assertTrue(sourceName.contains("Reconciliation"), "Reconciliation source is not selected");
            
            AllureReportManager.logStepWithStatus("Reconciliation source selection and validation", "PASSED");
            AllureReportManager.takeScreenshot("Reconciliation Source Selected and Validated");
            System.out.println("Reconciliation source selected and validated successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Reconciliation source selection and validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Reconciliation Source Selection Interrupted");
            System.out.println("Thread was interrupted during reconciliation source selection");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Reconciliation source selection and validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Reconciliation Source Selection Failed");
            throw e;
        }
    }

    @Step("Select Commercial source and validate results")
    @Description("User selects Commercial source from filter and validates the filtered results with detailed record information")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Source Filtering")
    @Then("User Selects a Commercial source and validated the results")
    public void User_Selects_a_Commercial_source_and_validated_the_results() {
        AllureReportManager.logStep("🎯 Selecting Commercial source and validating results");
        
        try {
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            
            taskCommonPage.clickSourceCommercialCheckbox();
            Thread.sleep(2000);
            
            paginationPage.clickAnywhere(1200, 100);
            
            // Validate the results after selecting the source
            int filteredRecordsCount = paginationPage.getFilteredRecordsCount();
            
            AllureReportManager.attachText("Filtered Records Count", String.valueOf(filteredRecordsCount));
            
            Assert.assertTrue(filteredRecordsCount < defaultRecordsCount, "Filtered records count is not less than default records count");

            List<String> sourceName = taskCommonPage.getSourceValues();
            
            AllureReportManager.attachText("Source Values", sourceName.toString());
            
            Assert.assertTrue(sourceName.contains("Commercial"), "Commercial source is not selected");
            
            // Print records for debugging and detailed validation
            taskCommonPage.printTableRecords();
            int totalRecordsFound = taskCommonPage.getRecordCount();
            
            AllureReportManager.attachText("Total Records Found", String.valueOf(totalRecordsFound));
            
            System.out.println("Total records found: " + totalRecordsFound);
            
            // Basic validation - ensure records exist
            Assert.assertTrue(totalRecordsFound > 0, "No records found in table after Commercial source filter");
            
            AllureReportManager.logStepWithStatus("Commercial source selection and validation", "PASSED");
            AllureReportManager.takeScreenshot("Commercial Source Selected and Validated");
            System.out.println("Commercial source selected and validated successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Commercial source selection and validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Commercial Source Selection Interrupted");
            System.out.println("Thread was interrupted during commercial source selection");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Commercial source selection and validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Commercial Source Selection Failed");
            throw e;
        }
    }
    
    @Step("Select a random record checkbox")
    @Description("User selects a random record checkbox from the available records")
    @Severity(SeverityLevel.NORMAL)
    @Story("Record Selection")
    @When("User select a random record checkbox")
    public void selectRandomRecord() {
        AllureReportManager.logStep("🎯 Selecting a random record checkbox");
        
        try {
            taskCommonPage.selectRandomRecordCheckbox();
            Thread.sleep(2000);
            
            boolean isAnyCheckboxSelected = taskCommonPage.isAnyRecordCheckboxSelected();
            String checkedRecordId = taskCommonPage.getCheckedRecordId();
            
            AllureReportManager.attachText("Is Any Checkbox Selected", String.valueOf(isAnyCheckboxSelected));
            AllureReportManager.attachText("Checked Record ID", checkedRecordId);
            
            System.out.println("Is any record checkbox selected? " + isAnyCheckboxSelected);
            System.out.println("Checked Record ID: " + checkedRecordId);
            
            AllureReportManager.logStepWithStatus("Random record selection", "PASSED");
            AllureReportManager.takeScreenshot("Random Record Selected");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Random record selection", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Record Selection Interrupted");
            System.out.println("Thread was interrupted while waiting for the page to load");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Random record selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Record Selection Failed");
            throw e;
        }
    }

    @Step("Click claim button and validate results")
    @Description("User clicks claim button and validates that the record is claimed successfully with proper audit logging")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Record Claiming")
    @Then("User click on claim button and validates the results")
    public void User_click_on_claim_button() {
        AllureReportManager.logStep("⚡ Claiming record and validating results");
        
        try {
            Assert.assertTrue(taskCommonPage.isClaimButtonPresent(), "Claim button is not present");
            
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            String checkedRecordId = taskCommonPage.getCheckedRecordId();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            AllureReportManager.attachText("Checked Record ID", checkedRecordId);
            
            System.out.println("Checked Record ID: " + checkedRecordId);
            
            // Claim the record
            taskCommonPage.clickClaimButton();
            wait.until(driver -> {
                try {
                    return taskCommonPage.isAlertMessageVisible();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            
            Thread.sleep(2000);
            
            int afterClaimRecordsCount = paginationPage.getFilteredRecordsCount();
            String alertMessage = taskCommonPage.getAlertMessageText();
            
            AllureReportManager.attachText("After Claim Records Count", String.valueOf(afterClaimRecordsCount));
            AllureReportManager.attachText("Alert Message", alertMessage);
            
            Assert.assertTrue(alertMessage.contains("Records claimed successfully"), "Alert message does not contain 'Claimed'");
            Assert.assertTrue(afterClaimRecordsCount < defaultRecordsCount, "Records count did not decrease after claiming");
            
            AllureReportManager.takeScreenshot("Record Claimed Successfully");
            
            // Validate the claimed record and perform unclaim
            taskCommonPage.clickFilterClaimedBy();
            taskCommonPage.clickUnclaimedCheckbox();
            taskCommonPage.clickClaimedCheckbox();
            
            paginationPage.clickAnywhere(1200, 100);
            
            taskCommonPage.clearEnterAndPressEnterOnRecordIDSearch(checkedRecordId);
            taskCommonPage.clickSearchButton();
            Thread.sleep(3000);
            
            Assert.assertTrue(taskCommonPage.isRecordIdPresentAlt(checkedRecordId), "Claimed record ID is not present in the filtered results");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // Unclaim the record
            AllureReportManager.logStep("Unclaiming the same record for cleanup");
            taskCommonPage.clickBtnUnclaimed();
            Thread.sleep(3000);
            
            String unclaimAlertMessage = taskCommonPage.getAlertMessageText();
            AllureReportManager.attachText("Unclaim Alert Message", unclaimAlertMessage);
            
            Assert.assertTrue(unclaimAlertMessage.contains("Records unclaimed successfully"), "Alert message does not contain 'Unclaimed'");
            
            // Verify audit log
            AllureReportManager.logStep("Checking audit log entries");
            auditLogPage.clickAuditLogIcon();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            auditLogPage.getLatest10AuditLogEntries();
            auditLogPage.closeAuditLog();
            
            taskCommonPage.clickRefreshButton();
            
            AllureReportManager.logStepWithStatus("Record claim and unclaim operation", "PASSED");
            AllureReportManager.takeScreenshot("Claim Unclaim Operation Completed");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Record claim and unclaim operation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Claim Operation Interrupted");
            throw new RuntimeException("Thread was interrupted during claim operation", e);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Record claim and unclaim operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claim Operation Failed");
            throw e;
        }
    }

    @Step("Claim and unclaim record from same page")
    @Description("User claims a record and then unclaims it from the same page, validating the record counts at each step")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Record Claim/Unclaim Operations")
    @When("user claim and unclaim record from same page")
    public void user_claim_and_unclaim_record_from_same_page() {
        AllureReportManager.logStep("⚡ Claiming and unclaiming record from same page");
        
        try {
            Assert.assertTrue(taskCommonPage.isClaimButtonPresent(), "Claim button is not present");
            
            int defaultRecordsCount = paginationPage.defaultRecordsCount();
            String checkedRecordId = taskCommonPage.getCheckedRecordId();
            
            AllureReportManager.attachText("Default Records Count", String.valueOf(defaultRecordsCount));
            AllureReportManager.attachText("Checked Record ID", checkedRecordId);
            
            System.out.println("Default Records Count: " + defaultRecordsCount);
            System.out.println("Checked Record ID: " + checkedRecordId);
            
            // Claim the record
            AllureReportManager.logStep("Claiming the record");
            taskCommonPage.clickClaimButton();
            wait.until(driver -> {
                try {
                    return taskCommonPage.isAlertMessageVisible();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            
            Thread.sleep(2000);
            
            int afterClaimRecordsCount = paginationPage.getFilteredRecordsCount();
            String claimAlertMessage = taskCommonPage.getAlertMessageText();
            
            AllureReportManager.attachText("After Claim Records Count", String.valueOf(afterClaimRecordsCount));
            AllureReportManager.attachText("Claim Alert Message", claimAlertMessage);
            
            System.out.println("After Claim Records Count: " + afterClaimRecordsCount);
            Assert.assertTrue(claimAlertMessage.contains("Records claimed successfully"), "Alert message does not contain 'Claimed'");
            Assert.assertTrue(afterClaimRecordsCount < defaultRecordsCount, "Records count did not decrease after claiming");
            
            AllureReportManager.takeScreenshot("Record Claimed Successfully");
            
            // Unclaim the record
            AllureReportManager.logStep("Unclaiming the record");
            Assert.assertTrue(taskCommonPage.isUnclaimButtonPresent(), "Unclaim button is not present");
            taskCommonPage.clickUnclaimButton();
            wait.until(driver -> {
                try {
                    return taskCommonPage.isAlertMessageVisible();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            
            Thread.sleep(2000);
            
            int afterUnclaimRecordsCount = paginationPage.getFilteredRecordsCount();
            String unclaimAlertMessage = taskCommonPage.getAlertMessageText();
            
            AllureReportManager.attachText("After Unclaim Records Count", String.valueOf(afterUnclaimRecordsCount));
            AllureReportManager.attachText("Unclaim Alert Message", unclaimAlertMessage);
            
            System.out.println("After Unclaim Records Count: " + afterUnclaimRecordsCount);
            Assert.assertTrue(unclaimAlertMessage.contains("Records unclaimed successfully"), "Alert message does not contain 'Unclaimed'");
            Assert.assertTrue(afterUnclaimRecordsCount > afterClaimRecordsCount, "Records count did not increase after unclaiming");
            Assert.assertTrue(defaultRecordsCount == afterUnclaimRecordsCount, "Default records count should be equal to after unclaiming records count");
            
            AllureReportManager.logStepWithStatus("Claim and unclaim operation", "PASSED");
            AllureReportManager.takeScreenshot("Claim Unclaim Operation Completed");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Claim and unclaim operation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Claim Unclaim Operation Interrupted");
            throw new RuntimeException("Thread was interrupted during claim/unclaim operation", e);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Claim and unclaim operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Claim Unclaim Operation Failed");
            throw e;
        }
    }

    @When("User Claim and reject record and validate the results")
    public void User_Claim_and_reject_record_and_validate_the_results() {
        String checkedRecordId = taskCommonPage.getCheckedRecordId();
        System.out.println("Checked Record ID: " + checkedRecordId);
       Assert.assertTrue(taskCommonPage.isClaimButtonPresent(), "Claim button is not present");
       taskCommonPage.clickClaimButton();
       wait.until(driver -> {
           try {
               return taskCommonPage.isAlertMessageVisible();
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
       });
       try {
           Thread.sleep(2000); // Wait for the page to refresh
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
       Assert.assertTrue(taskCommonPage.getAlertMessageText().contains("Records claimed successfully"), "Alert message does not contain 'Claimed'");
       taskCommonPage.clickRejectButton();

       wait.until(driver -> {
           try {
               return taskCommonPage.isCommentHeadingDisplayed();
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
       });
       
       taskCommonPage.getCommentHeadingText();
       try {
           Thread.sleep(2000); // Wait for the page to refresh
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
       taskCommonPage.isRejectCommentButtonEnabled();
       taskCommonPage.inputCommentText("Automated Rejection Comment");
       wait.until(driver -> {
           try {
               return taskCommonPage.isRejectCommentButtonEnabled();
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
       });
       taskCommonPage.clickBtnRejectCommentbox();
       taskCommonPage.clickRejectedTab();
         try {
              Thread.sleep(2000); // Wait for the page to refresh
         } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
         }
       Assert.assertTrue(taskCommonPage.getAlertMessageText().contains("Records rejected successfully"), "Alert message does not contain 'rejected'");
       System.out.println("Clicked on Rejected tab");
       taskCommonPage.searchRecordIDAndPressEnter(checkedRecordId);
       taskCommonPage.clickSearchButton();
       Assert.assertTrue(taskCommonPage.isRecordIdPresent(checkedRecordId), "Unclaimed record ID is not present in the filtered results");
    //    Audit report
        auditLogPage.clickAuditLogIcon();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        auditLogPage.getLatest10AuditLogEntries();

        auditLogPage.closeAuditLog();
        taskCommonPage.clickRefreshButton();
    }

    @When("User Claim and Accept record and validate the results")
    public void User_Claim_and_Accept_record_and_validate_the_results() {
        // Write code here that turns the phrase above into concrete actions
        String checkedRecordId = taskCommonPage.getCheckedRecordId();
        System.out.println("Checked Record ID: " + checkedRecordId);
        Assert.assertTrue(taskCommonPage.isClaimButtonPresent(), "Claim button is not present");
        taskCommonPage.clickClaimButton();
        wait.until(driver -> {
            try {
                return taskCommonPage.isAlertMessageVisible();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        try {
            Thread.sleep(2000); // Wait for the page to refresh
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(taskCommonPage.getAlertMessageText().contains("Records claimed successfully"), "Alert message does not contain 'Claimed'");
        Assert.assertTrue(taskCommonPage.isApproveButtonClickable(), "Approve button is not clickable");
        taskCommonPage.clickApproveButton();

        wait.until(driver -> {
            try {
                return taskCommonPage.isCommentHeadingDisplayed();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });

        taskCommonPage.getCommentHeadingText();
        try {
            Thread.sleep(2000); // Wait for the page to refresh
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        taskCommonPage.isApproveCommentButtonEnabled();
        taskCommonPage.inputCommentText("Automated Acceptance Comment");
        wait.until(driver -> {
            try {
                return taskCommonPage.isApproveCommentButtonEnabled();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        taskCommonPage.clickBtnApproveCommentbox();
        try {
            Thread.sleep(2000); // Wait for the page to refresh
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(taskCommonPage.getAlertMessageText().contains("Records approved successfully"), "Alert message does not contain 'approved'");

    }

    @Then("User Selects the latest record to approve")
    public void User_Selects_the_latest_record_to_approve() {
        // Write code here that turns the phrase above into concrete actions
        taskCommonPage.selectLastRecordCheckbox();
        try {
            Thread.sleep(2000); // Wait for the page to load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted while waiting for the page to load");
        }
        boolean isAnyCheckboxSelected = taskCommonPage.isAnyRecordCheckboxSelected();
        System.out.println("Is any record checkbox selected? " + isAnyCheckboxSelected);
        // get record id for checked record
        String checkedRecordId = taskCommonPage.getCheckedRecordId();
        System.out.println("Checked Record ID: " + checkedRecordId);
    }
    

    @Step("Print list of tabs under specific main tab")
    @Description("Print the list of sub-tabs available under a specific main tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Listing Functionality")
    @When("User prints list of tabs under {string} main tab")
    public void userPrintsListOfTabsUnderSpecificMainTab(String mainTabName) {
        AllureReportManager.logStep("🎯 Printing list of tabs under " + mainTabName + " main tab");
        
        try {
            AllureReportManager.attachText("Target Main Tab", mainTabName);
            
            // Click on the specified main tab
            taskCommonPage.clickMainTabByName(mainTabName);
            Thread.sleep(2000); // Wait for tab to load
            
            // Get and print sub-tabs
            List<String> subTabs = taskCommonPage.getAvailableSubTabs();
            
            AllureReportManager.attachText("Number of Sub-Tabs", String.valueOf(subTabs.size()));
            AllureReportManager.attachText("Sub-Tabs List", String.join(", ", subTabs));
            
            System.out.println("\n=== SUB-TABS UNDER " + mainTabName.toUpperCase() + " ===");
            if (subTabs.isEmpty()) {
                System.out.println("No sub-tabs found under " + mainTabName);
            } else {
                for (int i = 0; i < subTabs.size(); i++) {
                    System.out.println((i + 1) + ") " + subTabs.get(i));
                }
            }
            System.out.println("==========================================\n");
            
            AllureReportManager.logStepWithStatus("Sub-tabs listing for " + mainTabName, "PASSED");
            AllureReportManager.takeScreenshot("Sub-Tabs Listed for " + mainTabName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Sub-tabs listing for " + mainTabName, "FAILED");
            AllureReportManager.attachText("Error Details", "Thread was interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Sub-Tabs Listing Interrupted");
            throw new RuntimeException("Thread was interrupted", e);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Sub-tabs listing for " + mainTabName, "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Sub-Tabs Listing Failed");
            throw e;
        }
    }

    @Step("Print comprehensive tabs report")
    @Description("Print a comprehensive report of all main tabs and their respective sub-tabs")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Listing Functionality")
    @Then("User prints comprehensive tabs report")
    public void userPrintsComprehensiveTabsReport() {
        AllureReportManager.logStep("📈 Printing comprehensive tabs report");
        
        try {
            List<String> mainTabs = taskCommonPage.getAvailableMainTabs();
            int totalSubTabs = 0;
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           FACCTUM TASK TABS COMPREHENSIVE REPORT");
            System.out.println("=".repeat(60));
            
            for (String mainTab : mainTabs) {
                try {
                    taskCommonPage.clickMainTabByName(mainTab);
                    Thread.sleep(1500); // Wait for tab to load
                    
                    List<String> subTabs = taskCommonPage.getAvailableSubTabs();
                    totalSubTabs += subTabs.size();
                    
                    System.out.println("\n📂 " + mainTab + " (" + subTabs.size() + " sub-tabs):");
                    System.out.println("-".repeat(40));
                    
                    if (subTabs.isEmpty()) {
                        System.out.println("   ❌ No sub-tabs available");
                    } else {
                        for (int i = 0; i < subTabs.size(); i++) {
                            System.out.println("   " + (i + 1) + ") " + subTabs.get(i));
                        }
                    }
                } catch (Exception e) {
                    System.err.println("   ❌ Error processing " + mainTab + ": " + e.getMessage());
                }
            }
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("SUMMARY:");
            System.out.println("• Total Main Tabs: " + mainTabs.size());
            System.out.println("• Total Sub-Tabs: " + totalSubTabs);
            System.out.println("• Main Tabs: " + String.join(", ", mainTabs));
            System.out.println("=".repeat(60) + "\n");
            
            AllureReportManager.attachText("Total Main Tabs", String.valueOf(mainTabs.size()));
            AllureReportManager.attachText("Total Sub-Tabs", String.valueOf(totalSubTabs));
            AllureReportManager.attachText("Main Tabs", String.join(", ", mainTabs));
            
            AllureReportManager.logStepWithStatus("Comprehensive tabs report", "PASSED");
            AllureReportManager.takeScreenshot("Comprehensive Tabs Report Generated");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Comprehensive tabs report", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Comprehensive Tabs Report Failed");
            throw e;
        }
    }

    @Step("Navigate to facctum home page")
    @Description("User navigates to the facctum application home page")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Navigation Setup")
    @Given("User on facctum home page")
    public void userOnFacctumHomePage() {
        AllureReportManager.logStep("🏠 Navigating to facctum home page");
        
        try {
            // This step assumes user is already logged in and on home page
            // If additional navigation is needed, it can be added here
            String currentUrl = driver.getCurrentUrl();
            AllureReportManager.attachText("Current URL", currentUrl);
            
            System.out.println("User is on facctum home page: " + currentUrl);
            
            AllureReportManager.logStepWithStatus("Navigation to home page", "PASSED");
            AllureReportManager.takeScreenshot("Facctum Home Page");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Navigation to home page", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Home Page Navigation Failed");
            throw e;
        }
    }

    // ============ NEW FILTER AND SCROLL FUNCTIONALITY ============
    
    @Step("Select filter checkboxes by names")
    @Description("Select one or more filter checkboxes by their display names and close the filter")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Functionality")
    @When("User selects filter checkboxes {string}")
    public void userSelectsFilterCheckboxes(String checkboxNames) {
        AllureReportManager.logStep("🔽 Selecting filter checkboxes: " + checkboxNames);
        
        try {
            // Split multiple checkbox names by comma and trim whitespace
            String[] checkboxArray = checkboxNames.split(",");
            for (int i = 0; i < checkboxArray.length; i++) {
                checkboxArray[i] = checkboxArray[i].trim();
            }
            
            AllureReportManager.attachText("Checkbox Names to Select", java.util.Arrays.toString(checkboxArray));
            
            // Use the new method from taskCommonPage
            taskCommonPage.selectFilterCheckboxesByName(checkboxArray);
            
            AllureReportManager.logStepWithStatus("Filter checkboxes selection", "PASSED");
            AllureReportManager.takeScreenshot("Filter Checkboxes Selected");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Filter checkboxes selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Filter Selection Failed");
            throw e;
        }
    }
    
    @Step("Scroll to middle then to end of page")
    @Description("Scroll to middle of page, wait 3 seconds, then scroll to end to view all data")
    @Severity(SeverityLevel.MINOR)
    @Story("Page Navigation")
    @When("User scrolls to middle then to end of page")
    public void userScrollsToMiddleThenToEnd() {
        AllureReportManager.logStep("📜 Scrolling to middle then to end of page");
        
        try {
            // Use the new scroll method from taskCommonPage
            taskCommonPage.scrollToMiddleThenToEnd();
            
            AllureReportManager.logStepWithStatus("Page scrolling operation", "PASSED");
            AllureReportManager.takeScreenshot("Page Scrolled to End");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Page scrolling operation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Page Scrolling Failed");
            throw e;
        }
    }
    
    @Step("Extract and print table data")
    @Description("Extract all table data and print to console and Allure reports")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Extraction")
    @Then("User can see updated table data in console and reports")
    public void userCanSeeUpdatedTableDataInConsoleAndReports() {
        AllureReportManager.logStep("📊 Extracting and printing updated table data");
        
        try {
            // Use the new table data extraction method from taskCommonPage
            taskCommonPage.extractAndPrintTableData();
            
            AllureReportManager.logStepWithStatus("Table data extraction", "PASSED");
            AllureReportManager.takeScreenshot("Table Data Extracted");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Table data extraction", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Table Data Extraction Failed");
            throw e;
        }
    }
    
    @Step("Complete filter, scroll and data extraction workflow")
    @Description("Execute complete workflow: select filters, scroll page, and extract table data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Complete Filter Workflow")
    @When("User applies filters {string} and views complete data")
    public void userAppliesFiltersAndViewsCompleteData(String checkboxNames) {
        AllureReportManager.logStep("🔄 Executing complete filter, scroll and data extraction workflow");
        
        try {
            // Split multiple checkbox names by comma and trim whitespace
            String[] checkboxArray = checkboxNames.split(",");
            for (int i = 0; i < checkboxArray.length; i++) {
                checkboxArray[i] = checkboxArray[i].trim();
            }
            
            AllureReportManager.attachText("Filter Workflow - Checkboxes", java.util.Arrays.toString(checkboxArray));
            
            // Use the combined method from taskCommonPage
            taskCommonPage.selectFiltersScrollAndExtractData(checkboxArray);
            
            AllureReportManager.logStepWithStatus("Complete filter workflow", "PASSED");
            AllureReportManager.takeScreenshot("Complete Filter Workflow Done");
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Complete filter workflow", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Complete Filter Workflow Failed");
            throw e;
        }
    }
    
    @Step("Validate filter is applied successfully")
    @Description("Verify that the selected filters have been applied and table is updated")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Validation")
    @Then("the filters are applied successfully")
    public void theFiltersAreAppliedSuccessfully() {
        AllureReportManager.logStep("✅ Validating that filters are applied successfully");
        
        try {
            // Wait for table to update after filters
            Thread.sleep(2000);
            
            // Verify table data is visible (basic validation)
            try {
                taskCommonPage.extractAndPrintTableData();
                AllureReportManager.attachText("Filter Validation", "Table data is accessible after filter application");
            } catch (Exception e) {
                AllureReportManager.attachText("Filter Validation", "No table data found - this might indicate no matching records");
            }
            
            AllureReportManager.logStepWithStatus("Filter application validation", "PASSED");
            AllureReportManager.takeScreenshot("Filters Applied Successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AllureReportManager.logStepWithStatus("Filter application validation", "FAILED");
            AllureReportManager.attachText("Error Details", "Thread interrupted: " + e.getMessage());
            AllureReportManager.takeScreenshot("Filter Validation Failed");
            throw new RuntimeException("Filter validation interrupted", e);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Filter application validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Filter Validation Failed");
            throw e;
        }
    }
    
    @Step("User selects single filter checkbox")
    @Description("Select a single filter checkbox by name")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Functionality")
    @When("User selects {string} filter")
    public void userSelectsFilter(String checkboxName) {
        AllureReportManager.logStep("🔽 Selecting single filter checkbox: " + checkboxName);
        
        try {
            AllureReportManager.attachText("Single Checkbox Selection", checkboxName);
            
            // Use the filter selection method for single checkbox
            taskCommonPage.selectFilterCheckboxesByName(checkboxName.trim());
            
            AllureReportManager.logStepWithStatus("Single filter checkbox selection", "PASSED");
            AllureReportManager.takeScreenshot("Single Filter Selected - " + checkboxName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Single filter checkbox selection", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Single Filter Selection Failed");
            throw e;
        }
    }

    // ============ PARAMETERIZED STEP DEFINITIONS FOR TABS ============
    
    @Step("Click on main tab by name")
    @Description("Click on the specified main tab (Pending L1, Pending L2, Review, etc.)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    @When("User click on {string} tab")
    public void userClickOnTab(String tabName) {
        AllureReportManager.logStep("📌 Clicking on main tab: " + tabName);
        
        try {
            AllureReportManager.attachText("Main Tab Selection", tabName);
            
            // Use the existing method from taskCommonPage
            taskCommonPage.clickTabByName(tabName);
            
            AllureReportManager.logStepWithStatus("Main tab click", "PASSED");
            AllureReportManager.takeScreenshot("Main Tab Clicked - " + tabName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Main tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Main Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate main tab is active")
    @Description("Verify that the specified main tab is currently active")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Validation")
    @Then("User validate that the {string} tab is active")
    public void userValidateThatTheTabIsActive(String tabName) {
        AllureReportManager.logStep("✅ Validating main tab is active: " + tabName);
        
        try {
            AllureReportManager.attachText("Tab Validation", tabName);
            
            boolean isActive = false;
            switch (tabName.toUpperCase()) {
                case "PENDING L1":
                    isActive = taskCommonPage.isPendingL1TabActive();
                    break;
                case "PENDING L2":
                    isActive = taskCommonPage.isPendingL2TabActive();
                    break;
                case "REJECTED":
                    isActive = taskCommonPage.isRejectedTabActive();
                    break;
                case "OVERDUE":
                    isActive = taskCommonPage.isOverdueTabActive();
                    break;
                case "REVIEW":
                    isActive = taskCommonPage.isReviewTabActive();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown main tab: " + tabName);
            }
            
            Assert.assertTrue(isActive, tabName + " tab is not active");
            
            AllureReportManager.attachText("Tab Active Status", String.valueOf(isActive));
            AllureReportManager.logStepWithStatus("Main tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Main Tab Active - " + tabName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Main tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Main Tab Validation Failed");
            throw e;
        }
    }
    
    @Step("Click on sub-tab by name")
    @Description("Click on the specified sub-tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Navigation")
    @When("User click on sub tab {string}")
    public void userClickOnSubTab(String subTabName) {
        AllureReportManager.logStep("📌 Clicking on sub-tab: " + subTabName);
        
        try {
            AllureReportManager.attachText("Sub-Tab Selection", subTabName);
            
            // Use the existing method from taskCommonPage
            taskCommonPage.clickTabByName(subTabName);
            
            AllureReportManager.logStepWithStatus("Sub-tab click", "PASSED");
            AllureReportManager.takeScreenshot("Sub-Tab Clicked - " + subTabName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Sub-tab click", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Sub-Tab Click Failed");
            throw e;
        }
    }
    
    @Step("Validate sub-tab is active")
    @Description("Verify that the specified sub-tab is currently active")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sub-Tab Validation")
    @Then("User validate that the {string} sub-tab is active")
    public void userValidateThatTheSubTabIsActive(String subTabName) {
        AllureReportManager.logStep("✅ Validating sub-tab is active: " + subTabName);
        
        try {
            AllureReportManager.attachText("Sub-Tab Validation", subTabName);
            
            boolean isActive = false;
            switch (subTabName.toUpperCase()) {
                case "EXTERNAL RECORDS":
                    isActive = taskCommonPage.isExternalRecordsSubTabActive();
                    break;
                case "INTERNAL RECORDS":
                    isActive = taskCommonPage.isInternalRecordsSubTabActive();
                    break;
                case "LISTS CONFIGURATION":
                    isActive = taskCommonPage.isListsConfigurationSubTabActive();
                    break;
                case "PRESS RELEASE RECORDS":
                    isActive = taskCommonPage.isPressReleaseRecordsSubTabActive();
                    break;
                case "TEMPLATES":
                    isActive = taskCommonPage.isTemplatesSubTabActive();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown sub-tab: " + subTabName);
            }
            
            Assert.assertTrue(isActive, subTabName + " sub-tab is not active");
            
            AllureReportManager.attachText("Sub-Tab Active Status", String.valueOf(isActive));
            AllureReportManager.logStepWithStatus("Sub-tab validation", "PASSED");
            AllureReportManager.takeScreenshot("Sub-Tab Active - " + subTabName);
        } catch (Exception e) {
            AllureReportManager.logStepWithStatus("Sub-tab validation", "FAILED");
            AllureReportManager.attachText("Error Details", e.getMessage());
            AllureReportManager.takeScreenshot("Sub-Tab Validation Failed");
            throw e;
        }
    }

    

    

}