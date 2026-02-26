# seleniumseries
Selenium_Automation for Facctum Product

## ⚠️ Browser & Selenium Compatibility Note

If you see a warning like:

> org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
> WARNING: Unable to find an exact match for CDP version ...

This means your Chrome browser is newer than the Selenium/WebDriver/ChromeDriver version supports. To resolve:

- **Update Selenium and WebDriverManager** to the latest versions in your `pom.xml`.
- **Let WebDriverManager manage your driver versions** (do not hardcode driver versions).
- **If you still see the warning and tests fail:**
    - Downgrade your Chrome browser to a version supported by your Selenium/WebDriverManager.
    - Or, wait for a Selenium/WebDriverManager update that supports the new Chrome version.
- **If tests pass, you can ignore the warning.**

See the [ChromeDriver release notes](https://chromedriver.chromium.org/downloads) for version compatibility.
