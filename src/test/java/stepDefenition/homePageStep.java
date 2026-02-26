package stepDefenition;


import com.pages.homePage;
import com.qea.factory.DriverFactory;
import com.qea.utils.configReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;
import java.util.Properties;

public class homePageStep {
    WebDriver driver;
    Properties prop;
    WebDriverWait wait;

    private homePage homePage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized. Please check ApplicationHooks.");
        }
        homePage = new homePage(driver);
        System.out.println("HomePage step definition initialized successfully");
    }

    @When("user is on the Home page")
    public void userIsOnTheHomePage() {
        if (homePage == null) {
            throw new RuntimeException("HomePage is not initialized. Please check the @Before hook.");
        }
        homePage.waitForLeftPanelToLoad();
    }
    @Then("all navigation buttons should be present on home page")
    public void allNavigationButtonsShouldBePresent() throws InterruptedException {
        Map <String, Boolean> buttonValidationResults = homePage.validateAllhomeButtonsPresence();

        for (Map.Entry<String, Boolean> entry : buttonValidationResults.entrySet()) {
            // Scroll to System configuration button if it's the current button being checked
            if ("System configuration".equals(entry.getKey())) {
                Thread.sleep(2000);
                homePage.scrollToSystemConfigButton();
                Thread.sleep(2000);
            }
            Assert.assertTrue(entry.getValue(),
                    "Button '" + entry.getKey() + "' is not present in the left panel");
        }
    }
    @Then("all navigation buttons should be present on Dashboard page")
    public void allNavigationButtonsShouldBePresentOnDashboard() throws InterruptedException {
        Map <String, Boolean> buttonValidationResults = homePage.validateAllDashboardButtonsPresence();

        for (Map.Entry<String, Boolean> entry : buttonValidationResults.entrySet()) {
            // Scroll to System configuration button if it's the current button being checked
            Assert.assertTrue(entry.getValue(),
                    "Button '" + entry.getKey() + "' is not present in the left panel");
        }
    }
    @Then("the {string} button should be present")
    public void theButtonShouldBePresent(String buttonName) throws InterruptedException {
        // Scroll to System configuration button if it's the button being checked
        if ("System configuration".equals(buttonName)) {
            Thread.sleep(2000);
            homePage.scrollToSystemConfigButton();
            Thread.sleep(2000);
        }
        Assert.assertTrue(homePage.isButtonPresent(buttonName),
                "Button '" + buttonName + "' is not present in the left panel");
        System.out.println("Button '" + buttonName + "' is present in the left panel");
    }


    @Then("the {string} button should be enabled")
    public void theButtonShouldBeEnabled(String buttonName) {
        Assert.assertTrue(homePage.isButtonEnabled(buttonName),
                "Button '" + buttonName + "' is not enabled");
        System.out.println("Button '" + buttonName + "' is enabled");
    }
    @When("user collapses the left panel")
    public void userCollapsesTheLeftPanel() {
        homePage.togglePanel();
    }

    @Then("the left panel should be collapsed")
    public void theLeftPanelShouldBeCollapsed() {
        Assert.assertTrue(homePage.isPanelCollapsed(),
                "Left panel is not collapsed");
        System.out.println("Left panel is collapsed");
    }

}
