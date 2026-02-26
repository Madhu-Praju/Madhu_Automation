package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qea.factory.DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class CBI_Interpole {

	private static WebDriver driver;
	private DriverFactory driverFactory;
	//private WebDriver driver;
	
	public CBI_Interpole(WebDriver driver) {
		this.driver = driver;
		
	}

   public static void main(String[] args) {
//      System.setProperty("webdriver.chrome.driver",
//         "https://www.interpol.int/How-we-work/Notices/Red-Notices/View-Red-Notices#2023-61645");
           DriverFactory driverFactory2 = new DriverFactory();
      driverFactory2.init_driver("chrome");
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      DriverFactory.getDriver().get("https://www.interpol.int/How-we-work/Notices/Red-Notices/View-Red-Notices#2023-61645");
      System.out.println("Browser Launched");
      //URL launch

      // identify element with class name then use click method
//      WebElement m=driver.
//      findElement(By.className("sign-in-form__submit-button"));
//      m.click();
//      driver.close();
   }
}

