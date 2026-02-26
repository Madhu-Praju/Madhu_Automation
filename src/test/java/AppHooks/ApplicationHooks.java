package AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qea.factory.DriverFactory;
import com.qea.listeners.ConsoleLogCapturer;
import com.qea.utils.AllureReportManager;
import com.qea.utils.ScreenshotSequenceManager;
import com.qea.utils.SimpleReportConsolidator;
import com.qea.utils.configReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

/**
 * Simplified Application Hooks for Cucumber Test Framework
 * Handles both suite-level and scenario-level setup/cleanup
 */
public class ApplicationHooks {
	
	// Instance variables
	private DriverFactory driverFactory;
	private configReader confReader;
	private Properties prop;
	
	// Constants
	private static final String SCREENSHOT_DIR = "screenshots";
	
	// Suite-level tracking
	private static boolean suiteInitialized = false;
	private static int scenarioCount = 0;
	private static String currentTagName = "UnknownTag";
	private static long testStartTime = 0;

	// ===============================================================================
	// SUITE SETUP - Runs once before all tests
	// ===============================================================================
	
	@Before(order = -1)
	public void setupTestSuite() {
		if (!suiteInitialized) {
			System.out.println("\n" + "=".repeat(50));
			System.out.println(" STARTING TEST SUITE");
			System.out.println("=".repeat(50));
			
			// Start console log capturing for the entire test suite
			ConsoleLogCapturer.startCapturing();
			
			// Initialize screenshot sequence manager for the entire suite
			ScreenshotSequenceManager.resetAllSequences();
			System.out.println("🔢 Screenshot sequence manager initialized for chronological ordering");
			
			createDirectories();
			
			// Add environment information to Allure report
			AllureReportManager.addEnvironmentInfo();
			AllureReportManager.logStep("Test Suite Initialization Complete");
			
			// Test Allure attachments
			AllureReportManager.testAllureAttachments();
			
			suiteInitialized = true;
		}
	}

	// ===============================================================================
	// SCENARIO SETUP - Runs before each test scenario
	// ===============================================================================

	@Before(order = 0)
	public void loadConfiguration() {
		confReader = new configReader();
		prop = confReader.init_pop();
		
		if (prop == null) {
			throw new RuntimeException("Failed to load configuration properties");
		}
		AllureReportManager.logStep("Configuration loaded successfully");
		System.out.println("Configuration loaded");
	}

	@Before(order = 1)
	public void setupDriver() {
		// Skip if driver is already active
		if (DriverFactory.isDriverActive()) {
			AllureReportManager.logStep("Using existing driver instance");
			System.out.println("Using existing driver");
			return;
		}
		
		String browser = prop.getProperty("browser", "chrome");
		AllureReportManager.logStep("Initializing " + browser + " browser");
		System.out.println("Starting " + browser + " browser...");
		
		driverFactory = new DriverFactory();
		WebDriver driver = driverFactory.init_driver(browser, prop);
		
		if (driver == null) {
			AllureReportManager.logStepWithStatus("Browser initialization failed", "FAILED");
			throw new RuntimeException("Failed to start browser: " + browser);
		}
		
		// Apply additional zoom setting after driver initialization
		try {
			Thread.sleep(1000); // Wait for browser to fully load
			DriverFactory.applyZoomSetting();
		} catch (Exception e) {
			System.err.println("Warning: Could not apply additional zoom setting: " + e.getMessage());
		}
		
		AllureReportManager.logStepWithStatus("Browser started successfully", "PASSED");
		AllureReportManager.attachBrowserInfo();
		System.out.println(" Browser started successfully");
	}

	@Before(order = 2)
	public void logScenarioStart(Scenario scenario) {
		scenarioCount++;
		String scenarioName = scenario.getName();
		testStartTime = System.currentTimeMillis();
		
		// Reset screenshot sequence for new test scenario
		ScreenshotSequenceManager.resetSequenceForNewTest();
		ScreenshotSequenceManager.setCurrentTestName(scenarioName);
		System.out.println("🔢 Screenshot sequence reset for new test: " + scenarioName);
		
		// Set current test name for log capturer
		ConsoleLogCapturer.setCurrentTestName(scenarioName);
		
		// Extract tag name for consolidated report and screenshot naming
		if (scenario.getSourceTagNames() != null && !scenario.getSourceTagNames().isEmpty()) {
			currentTagName = scenario.getSourceTagNames().iterator().next();
			// Also set tag name in ScreenshotSequenceManager for file naming
			ScreenshotSequenceManager.setCurrentTagName(currentTagName);
			System.out.println("📋 Test Tag: " + currentTagName + " (will be used in screenshot file names)");
		} else {
			// Set default tag name if no tags present
			ScreenshotSequenceManager.setCurrentTagName("NoTag");
			System.out.println("📋 No tags found - using 'NoTag' for screenshot file names");
		}
		
		AllureReportManager.logStep("📋 Starting Test Scenario #" + scenarioCount + ": " + scenarioName);
		
		// Add scenario metadata to Allure
		Allure.getLifecycle().updateTestCase(testCase -> {
			testCase.setName(scenarioName);
			if (scenario.getSourceTagNames() != null && !scenario.getSourceTagNames().isEmpty()) {
				testCase.getLabels().addAll(
					scenario.getSourceTagNames().stream()
						.map(tag -> new io.qameta.allure.model.Label().setName("tag").setValue(tag))
						.collect(java.util.stream.Collectors.toList())
				);
			}
		});
		
		System.out.println("\n" + "-".repeat(40));
		System.out.println(" Test #" + scenarioCount + ": " + scenarioName);
		System.out.println("-".repeat(40));
	}

	// ===============================================================================
	// SCENARIO CLEANUP - Runs after each test scenario
	// ===============================================================================

	@After(order = 1)
	public void takeScreenshotOnFailure(Scenario scenario) {
		String scenarioName = scenario.getName();
		
		if (scenario.isFailed()) {
			try {
				WebDriver driver = DriverFactory.getDriver();
				if (driver != null) {
					// Create simple screenshot name
					String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
					String screenshotName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp;
					
					// Take screenshot for Cucumber report
					byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/png", screenshotName);
					
					// Enhanced Allure screenshot with failure details
					AllureReportManager.takeFailureScreenshot(scenarioName);
					
					// Save screenshot to file
					saveScreenshot(driver, screenshotName);
					
					AllureReportManager.logStepWithStatus("Screenshot captured on failure", "FAILED");
					System.out.println("📸 Screenshot saved: " + screenshotName);
				}
			} catch (Exception e) {
				AllureReportManager.logStep("Could not take screenshot: " + e.getMessage());
				System.out.println("Could not take screenshot: " + e.getMessage());
			}
		} else {
			AllureReportManager.logStepWithStatus("Test scenario completed successfully", "PASSED");
		}
		
		// Attach captured console logs to Allure report regardless of test result
		try {
			ConsoleLogCapturer.attachLogsToAllure();
			System.out.println("📝 Console logs attached to Allure report (" + ConsoleLogCapturer.getLogCount() + " entries)");
		} catch (Exception e) {
			System.out.println("⚠️ Failed to attach console logs: " + e.getMessage());
		}
	}

	@After(order = 0)
	public void cleanupDriver(Scenario scenario) {
		// Show test result
		String result = scenario.isFailed() ? "FAILED" : "PASSED";
		String scenarioName = scenario.getName();
		long testDuration = System.currentTimeMillis() - testStartTime;
		
		// Log screenshot sequence summary
		String screenshotSummary = ScreenshotSequenceManager.getExecutionSummary();
		System.out.println("📸 " + screenshotSummary);
		AllureReportManager.attachText("Screenshot Summary", screenshotSummary);
		
		// Log test completion with Allure
		AllureReportManager.logTestCompletion(scenarioName, 
			scenario.isFailed() ? "FAILED" : "PASSED", 
			testDuration);
		
		System.out.println("Result: " + result);
		System.out.println("-".repeat(40) + "\n");
		
		// Clear captured logs for the current test (keep logs isolated per test)
		ConsoleLogCapturer.clearLogs();
		
		// Clean up driver
		DriverFactory.quitDriver();
		AllureReportManager.logStep("🧹 Driver cleanup completed");
		
		// Generate consolidated report after test completion
		try {
			String reportPath = SimpleReportConsolidator.generateConsolidatedReport(currentTagName);
			if (reportPath != null) {
				System.out.println("📊 Consolidated report generated: " + reportPath);
			}
		} catch (Exception e) {
			System.err.println("⚠️ Failed to generate consolidated report: " + e.getMessage());
		}
	}

	// ===============================================================================
	// HELPER METHODS
	// ===============================================================================

	/**
	 * Create necessary directories for test execution
	 */
	private void createDirectories() {
		try {
			// Create screenshots directory
			java.io.File screenshotDir = new java.io.File(SCREENSHOT_DIR);
			if (!screenshotDir.exists()) {
				screenshotDir.mkdirs();
				System.out.println("Created directory: " + SCREENSHOT_DIR);
			}
		} catch (Exception e) {
			System.out.println("Could not create directories: " + e.getMessage());
		}
	}

	/**
	 * Save screenshot to file system
	 */
	private void saveScreenshot(WebDriver driver, String screenshotName) {
		try {
			java.io.File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			java.io.File destFile = new java.io.File(SCREENSHOT_DIR, screenshotName + ".png");
			java.nio.file.Files.copy(screenshotFile.toPath(), destFile.toPath(), 
				java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("Could not save screenshot file: " + e.getMessage());
		}
	}

	// ===============================================================================
	// UTILITY METHODS FOR OTHER CLASSES
	// ===============================================================================

	/**
	 * Suite-level cleanup - called once after all tests
	 * This method can be called manually from TestRunner or similar
	 */
	public static void cleanupTestSuite() {
		if (suiteInitialized) {
			try {
				ConsoleLogCapturer.stopCapturing();
				System.out.println("\n" + "=".repeat(50));
				System.out.println(" TEST SUITE CLEANUP COMPLETED");
				System.out.println("=".repeat(50));
			} catch (Exception e) {
				System.err.println("⚠️ Error during test suite cleanup: " + e.getMessage());
			}
		}
	}

	/**
	 * Get current WebDriver instance
	 */
	public static WebDriver getCurrentDriver() {
		return DriverFactory.getDriver();
	}

	/**
	 * Check if WebDriver is active
	 */
	public static boolean isDriverActive() {
		return DriverFactory.isDriverActive();
	}

}
