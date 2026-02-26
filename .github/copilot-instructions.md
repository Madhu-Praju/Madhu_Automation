# Facctum Selenium Automation Framework Instructions

## Architecture Overview

This is a **Cucumber BDD + Selenium + TestNG** framework for testing Facctum products (FacctList, FacctView, FacctShield). The architecture follows:

- **Page Object Model (POM)**: `src/main/java/com/pages/` - All page classes extend `BasePage`
- **Step Definitions**: `src/test/java/stepDefenition/` - Cucumber step implementations  
- **Application Hooks**: `src/test/java/AppHooks/ApplicationHooks.java` - Browser lifecycle management
- **Driver Factory**: `src/main/java/com/qea/factory/DriverFactory.java` - ThreadLocal WebDriver management
- **Test Runner**: `src/test/java/mytestrunner/TestRunner.java` - Cucumber-TestNG integration

## Key Framework Patterns

### Driver Management
- Uses **ThreadLocal** pattern in `DriverFactory` for parallel execution
- WebDriverManager auto-manages driver binaries (no manual driver setup needed)
- Browser configuration via `src/test/resources/config/config.properties`

### Page Object Structure
```java
// All pages extend BasePage which provides ElementLib utilities
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver); // Initializes ElementLib and PageFactory
    }
}
```

### Step Definition Setup
```java
@Before
public void setUp() {
    driver = DriverFactory.getDriver(); // Get ThreadLocal driver
    loginpage = new loginPage(driver);
}
```

## Essential Development Workflows

### Running Tests
```bash
# Run specific feature/tag
mvn test -Dcucumber.options="--tags @homePage"

# Generate Allure reports  
mvn clean test allure:report
mvn allure:serve
```

### Test Configuration
- **Browser**: Set in `config.properties` (`browser=chrome`)
- **Environment URLs**: Multiple environments configured (dev, qa, stage)
- **Test Data**: Excel files in `src/test/resources/testData/`
- **Screenshots**: Auto-captured in `screenshots/` folder with timestamp

### Reporting Stack
- **Cucumber HTML**: `target/cucumber-report.html`
- **Allure Reports**: `target/allure-reports/`
- **ExtentReports**: Integrated via cucumber adapter
- **TestNG**: Traditional XML reports in `test-output/`

## Critical Framework Dependencies

### Browser Compatibility
⚠️ **Known Issue**: Chrome version compatibility warnings are expected and can be ignored if tests pass. Use WebDriverManager's auto-versioning.

### Package Structure Conventions
- `com.pages.*`: Page Object classes
- `com.qea.utils.*`: Utilities (Excel, JSON, Element operations)
- `com.qea.factory.*`: Driver factory and management
- `stepDefenition.*`: Cucumber step definitions (note: package name typo is intentional)
- `AppHooks.*`: Cucumber hooks for setup/teardown

### Test Execution Flow
1. `ApplicationHooks.@Before(order=0)`: Load config properties
2. `ApplicationHooks.@Before(order=1)`: Initialize browser
3. Individual step `@Before`: Setup page objects with driver
4. `ApplicationHooks.@After`: Screenshot on failure + driver cleanup

## Environment & Test Data

### Configuration Files
- `src/test/resources/config/config.properties`: URLs, credentials, browser config
- `src/test/resources/cucumber.properties`: Cucumber execution settings
- Maven profiles handle different environments

### Test Data Management
- Excel files for data-driven testing via `readExcelData` utility
- JSON support through `readJsonUtility`
- Credentials stored in config (consider externalization for production)

## Common Development Tasks

### Adding New Tests
1. Create `.feature` file in `src/test/resources/AppFeature/`
2. Implement step definitions in `src/test/java/stepDefenition/`
3. Create page objects in `src/main/java/com/pages/` extending `BasePage`
4. Update `TestRunner.java` features/tags as needed

### Page Object Best Practices
- Always extend `BasePage` for ElementLib utilities
- Use `@FindBy` annotations for element location
- Initialize in constructor: `PageFactory.initElements(driver, this)`
- Leverage `elementLib` for common operations (waits, clicks, etc.)

### Debugging Failed Tests
- Screenshots auto-saved with test name + timestamp
- Check `test-output/` for TestNG reports
- Allure reports provide step-by-step execution details
- Console logs show driver initialization status

### Parallel Execution
- Configured via `maven-surefire-plugin` with ThreadLocal drivers
- Currently set to 4 threads maximum
- Each thread gets isolated driver instance via DriverFactory pattern
