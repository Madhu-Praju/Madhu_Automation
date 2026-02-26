package com.qea.factory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	// ThreadLocal driver for parallel execution support
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	// ThreadLocal properties for configuration access
	private static ThreadLocal<Properties> tlProperties = new ThreadLocal<>();
	
	// Supported browsers enum for better type safety
	public enum Browser {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	/**
	 * Initialize WebDriver instance based on browser type
	 * Implements ThreadLocal pattern for parallel execution
	 * 
	 * @param browser Browser name (case-insensitive)
	 * @return WebDriver instance
	 * @throws RuntimeException if browser initialization fails
	 */
	public WebDriver init_driver(String browser) {
		return init_driver(browser, null);
	}

	/**
	 * Initialize WebDriver instance based on browser type with configuration properties
	 * Implements ThreadLocal pattern for parallel execution
	 * 
	 * @param browser Browser name (case-insensitive)
	 * @param properties Configuration properties for driver setup
	 * @return WebDriver instance
	 * @throws RuntimeException if browser initialization fails
	 */
	public WebDriver init_driver(String browser, Properties properties) {
		// Store properties in ThreadLocal for configuration access
		if (properties != null) {
			tlProperties.set(properties);
		}
		// Validate input parameter
		if (browser == null || browser.trim().isEmpty()) {
			throw new IllegalArgumentException("Browser parameter cannot be null or empty");
		}
		
		// Check if driver is already initialized for current thread
		if (tlDriver.get() != null && isDriverActive()) {
			System.out.println("Active driver already exists for current thread, returning existing driver");
			return getDriver();
		}
		
		// Clean up any existing inactive driver
		if (tlDriver.get() != null && !isDriverActive()) {
			System.out.println("Inactive driver found, cleaning up before creating new one");
			quitDriver();
		}
		
		String browserName = browser.trim().toLowerCase();
		System.out.println("Initializing WebDriver for browser: " + browserName);
		
		try {
			switch (browserName) {
				case "chrome":
					initializeChromeDriver();
					break;
				case "firefox":
					initializeFirefoxDriver();
					break;
				case "edge":
					initializeEdgeDriver();
					break;
				case "safari":
					initializeSafariDriver();
					break;
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browser + 
						". Supported browsers are: chrome, firefox, edge, safari");
			}
			
			// Configure driver settings after successful initialization
			if (getDriver() != null) {
				configureDriver();
				System.out.println("WebDriver initialized successfully: " + getDriver().getClass().getSimpleName());
			} else {
				throw new RuntimeException("Failed to initialize WebDriver - driver is null");
			}
			
		} catch (Exception e) {
			System.err.println("Error initializing WebDriver for browser '" + browser + "': " + e.getMessage());
			// Clean up on failure
			quitDriver();
			throw new RuntimeException("WebDriver initialization failed for browser: " + browser, e);
		}
		
		return getDriver();
	}

	/**
	 * Initialize Chrome WebDriver with optimized options
	 */
	private void initializeChromeDriver() {
		try {
			ChromeOptions options = getChromeOptions();
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options));
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Chrome driver", e);
		}
	}

	/**
	 * Initialize Firefox WebDriver with optimized options
	 */
	private void initializeFirefoxDriver() {
		try {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Firefox driver", e);
		}
	}

	/**
	 * Initialize Edge WebDriver with optimized options
	 */
	private void initializeEdgeDriver() {
		try {
			EdgeOptions options = getEdgeOptions();
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver(options));
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Edge driver", e);
		}
	}

	/**
	 * Initialize Safari WebDriver (macOS only)
	 */
	private void initializeSafariDriver() {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (!os.contains("mac")) {
				throw new RuntimeException("Safari driver is only supported on macOS");
			}
			// Safari driver doesn't need WebDriverManager setup
			tlDriver.set(new SafariDriver());
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Safari driver", e);
		}
	}

	/**
	 * Get optimized Chrome options for better performance and stability
	 * @return ChromeOptions configured for automation
	 */
	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		
		// Get zoom level from properties
		Properties props = tlProperties.get();
		String zoomLevel = "67"; // default zoom level
		if (props != null && props.getProperty("browser.zoom") != null) {
			zoomLevel = props.getProperty("browser.zoom");
		}
		
		// Calculate zoom factor for Chrome (67% = 0.67)
		double zoomFactor = Double.parseDouble(zoomLevel) / 100.0;
		
		// Performance optimizations
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");
		
		// Set initial zoom level
		options.addArguments("--force-device-scale-factor=" + zoomFactor);
		
		// Stability improvements
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--disable-features=VizDisplayCompositor");
		
		// Memory and resource management
		options.addArguments("--max_old_space_size=4096");
		options.addArguments("--disable-background-timer-throttling");
		options.addArguments("--disable-backgrounding-occluded-windows");
		options.addArguments("--disable-renderer-backgrounding");
		
		// For CI/CD environments - uncomment if needed
		// options.addArguments("--headless=new");
		// options.addArguments("--window-size=1920,1080");
		
		return options;
	}

	/**
	 * Get optimized Edge options for better performance and stability
	 * @return EdgeOptions configured for automation
	 */
	private EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		
		// Get zoom level from properties
		Properties props = tlProperties.get();
		String zoomLevel = "67"; // default zoom level
		if (props != null && props.getProperty("browser.zoom") != null) {
			zoomLevel = props.getProperty("browser.zoom");
		}
		
		// Calculate zoom factor for Edge (67% = 0.67)
		double zoomFactor = Double.parseDouble(zoomLevel) / 100.0;
		
		// Performance optimizations
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");
		
		// Set initial zoom level
		options.addArguments("--force-device-scale-factor=" + zoomFactor);
		
		return options;
	}

	/**
	 * Configure driver settings after initialization
	 */
	private void configureDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			// Clear browser data
			driver.manage().deleteAllCookies();
			
			// Set timeouts
			driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
			driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
			
			// Maximize window
			driver.manage().window().maximize();
			
			// Apply zoom setting using multiple approaches for better compatibility
			applyZoomSetting();
			
			System.out.println("Driver configuration completed successfully");
		}
	}

	/**
	 * Apply zoom setting using multiple JavaScript approaches for better browser compatibility
	 * This method can be called after page navigation to ensure zoom is maintained
	 */
	public static void applyZoomSetting() {
		WebDriver driver = getDriver();
		if (driver != null) {
			try {
				// Wait a bit for page to fully load
				Thread.sleep(500);
				
				// Get zoom level from ThreadLocal properties
				Properties props = tlProperties.get();
				String zoomLevel = "67"; // default zoom level
				
				if (props != null && props.getProperty("browser.zoom") != null) {
					zoomLevel = props.getProperty("browser.zoom");
				}
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				// Wait for document to be ready
				js.executeScript("return document.readyState").equals("complete");
				
				// Multiple approaches to ensure zoom is applied
				// Approach 1: Set zoom on document body
				js.executeScript("document.body.style.zoom='" + zoomLevel + "%'");
				
				// Approach 2: Set zoom on document element
				js.executeScript("document.documentElement.style.zoom='" + zoomLevel + "%'");
				
				// Approach 3: Set zoom using CSS transform (fallback)
				double zoomFactor = Double.parseDouble(zoomLevel) / 100.0;
				js.executeScript("document.body.style.transform='scale(" + zoomFactor + ")'; " +
				                "document.body.style.transformOrigin='top left'; " +
				                "document.body.style.width='" + (100/zoomFactor) + "%';");
				
				// Approach 4: Set viewport meta tag if it exists
				js.executeScript(
					"var metaViewport = document.querySelector('meta[name=viewport]'); " +
					"if (metaViewport) { " +
					"  metaViewport.setAttribute('content', 'width=device-width, initial-scale=" + zoomFactor + "'); " +
					"} else { " +
					"  var meta = document.createElement('meta'); " +
					"  meta.name = 'viewport'; " +
					"  meta.content = 'width=device-width, initial-scale=" + zoomFactor + "'; " +
					"  document.getElementsByTagName('head')[0].appendChild(meta); " +
					"}"
				);
				
				System.out.println("Browser zoom set to " + zoomLevel + "% using multiple approaches");
				
			} catch (Exception e) {
				System.err.println("Warning: Could not set browser zoom: " + e.getMessage());
			}
		}
	}

	/**
	 * Get the WebDriver instance for current thread
	 * @return WebDriver instance or null if not initialized
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * Safely quit the WebDriver and clean up ThreadLocal
	 * Handles all cleanup operations including ThreadLocal removal
	 */
	public static void quitDriver() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			System.out.println("Quitting WebDriver for current thread...");
			try {
				// Close all windows and quit driver
				driver.quit();
				System.out.println("WebDriver quit successfully");
			} catch (Exception e) {
				System.err.println("Error while quitting driver: " + e.getMessage());
				// Continue with cleanup even if quit fails
			} finally {
				// Always remove from ThreadLocal to prevent memory leaks
				tlDriver.remove();
				tlProperties.remove();
				System.out.println("ThreadLocal driver and properties references cleared");
			}
		} else {
			System.out.println("No active driver found for current thread");
		}
	}
	
	/**
	 * Close current browser window only (use when multiple windows are open)
	 * Does not quit the entire driver session
	 */
	public static void closeCurrentWindow() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			try {
				// Check if there are multiple windows
				if (driver.getWindowHandles().size() > 1) {
					driver.close();
					System.out.println("Current browser window closed");
				} else {
					System.out.println("Only one window open, use quitDriver() instead");
					quitDriver();
				}
			} catch (Exception e) {
				System.err.println("Error while closing current window: " + e.getMessage());
			}
		} else {
			System.out.println("No active driver found to close window");
		}
	}
	
	/**
	 * Check if WebDriver is active and responsive
	 * @return true if driver exists and can perform operations, false otherwise
	 */
	public static boolean isDriverActive() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			try {
				// Test driver responsiveness by getting current URL
				driver.getCurrentUrl();
				return true;
			} catch (Exception e) {
				System.out.println("Driver exists but is not responsive: " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	/**
	 * Get current browser name from active driver
	 * @return Browser name or "Unknown" if driver not active
	 */
	public static String getCurrentBrowserName() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			String driverClass = driver.getClass().getSimpleName();
			return driverClass.replace("Driver", "");
		}
		return "Unknown";
	}

	/**
	 * Restart the current WebDriver with same browser
	 * Useful for recovering from crashed browser sessions
	 */
	public void restartDriver() {
		String currentBrowser = getCurrentBrowserName().toLowerCase();
		if (!"unknown".equals(currentBrowser)) {
			System.out.println("Restarting " + currentBrowser + " driver...");
			quitDriver();
			init_driver(currentBrowser);
		} else {
			throw new RuntimeException("Cannot restart driver - no active browser session found");
		}
	}

	/**
	 * Cleanup method for framework shutdown
	 * Ensures all ThreadLocal resources are properly cleaned up
	 */
	public static void cleanup() {
		try {
			quitDriver();
		} catch (Exception e) {
			System.err.println("Error during cleanup: " + e.getMessage());
		}
	}
}
