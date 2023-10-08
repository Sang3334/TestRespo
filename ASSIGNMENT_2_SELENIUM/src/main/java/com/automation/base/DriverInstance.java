package com.automation.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	protected WebDriver driver;
		@BeforeClass
		public void initDriverInstance() {
			 WebDriverManager.chromedriver().setup();
			 WebDriver driver = new ChromeDriver();
			 this.driver=driver;
			 driver.manage().window().maximize();
	}
	@AfterClass
	public void closeDriverInstance() {
		//đóng trình duyệt
			 driver.close();
	}
}

