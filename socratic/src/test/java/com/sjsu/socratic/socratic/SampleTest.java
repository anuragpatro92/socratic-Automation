package com.sjsu.socratic.socratic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SampleTest {

	AppiumDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	ExtentReports extentreports = new ExtentReports("report.html");
	ExtentTest test;
	
	
	//@BeforeClass
	public void beforeSuite() {
		
		test = extentreports.startTest("SampleTest");
		
	}
	
	//@Test
	public void test1() {
		System.out.println("anurag");
		
		test.log(LogStatus.PASS,"matched");
		//Assert.assertEquals("anu","anu");
	}

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "1", "4" }, { "2", "5" },
				{ "3", "102" }, { "4", "2x plus 1 equals 5" },
				{ "5", "left parenthesis 2  plus  3 right parenthesis   caret  2 equals 25" },
				{ "6", "3967672674789433" }, { "7", "10" },
				{ "8", "220" },
				{ "9", "x  caret  2  dash  3x  plus  2  equals  0" },
				{ "10", "3x  caret  2  dash  5x  plus  6  equals  0" }, { "11", "4" },
				{ "12", "5" }, { "13", "102" }, { "14", "2x plus 1 equals 5" },
				{ "15", "left parenthesis 2  plus  3 right parenthesis   caret  2 equals 25" },
				{ "16", "3967672674789433" }, { "17", "10" },
				{ "18", "220" },
				{ "19", "x  caret  2  dash  3x  plus  2  equals  0" },
				{ "20", "3x  caret  2  dash  5x  plus  6  equals  0" } };
	}

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set up desired capabilities and pass the Android app-activity and app-package
		// to Appium
		
		test = extentreports.startTest("SampleTest");

		capabilities.setCapability("BROWSER_NAME", "Android");
		// capabilities.setCapability("VERSION", "4.14.83-perf+");
		capabilities.setCapability("deviceName", "ba3eedaa");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.google.socratic");
		// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity", "com.google.android.apps.education.bloom.app.home.HomeActivity");

	}

	// @Test
	public void testCal() throws Exception {
		// locate the Text on the calculator by using By.name()

		driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		WebElement two = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
		two.click();
		WebElement plus = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
		plus.click();
		WebElement four = driver.findElement(By.id("com.google.android.calculator:id/digit_4"));
		four.click();
		WebElement equalTo = driver.findElement(By.id("com.google.android.calculator:id/eq"));
		equalTo.click();
		// locate the edit box of the calculator by using By.tagName()
		WebElement results = driver.findElement(By.id("com.google.android.calculator:id/formula"));
		// Check the calculated value on the edit box
		assert results.getText().equals("6") : "Actual value is : " + results.getText()
				+ " did not match with expected value: 6";

	}

	@Test(dataProvider = "data-provider")
	public void testsocratic(String TestCaseId, String actualResult) throws Exception {

		driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

		WebElement signIn = driver.findElement(By.id("com.google.socratic:id/sign_in_button"));
		signIn.click();

		/*
		 * WebElement accnt = //
		 * driver.findElement(By.xpath("//UIAButton[@name='anutinkupatro@gmail.com']"));
		 * 
		 * // WebElement accnt =
		 * driver.findElementByLinkText("anutinkupatro@gmail.com");
		 * 
		 * // WebElement accnt = driver.findElement(By.className("ImageView"));
		 * 
		 */

		Thread.sleep(2000);
		WebElement accnt = driver
				.findElement(By.xpath("//android.widget.TextView[@text='" + "anutinkupatro@gmail.com" + "']"));

		accnt.click();

		WebElement letgo = driver.findElement(By.id("com.google.socratic:id/button"));
		letgo.click();

		Thread.sleep(2000);
		WebElement allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		allow.click();

		// System.out.println(allow.getText());
		Thread.sleep(3000);
		WebElement camera = driver.findElement(By.id("com.google.socratic:id/camera_button"));
		camera.click();

		Thread.sleep(3000);
		WebElement go = driver.findElement(By.id("com.google.socratic:id/accept_button"));
		go.click();

		Thread.sleep(3000);
		WebElement layout = driver.findElement(By.id("com.google.socratic:id/question_math"));
		String str = layout.getAttribute("content-desc");

		str = str.replace(" ", "");

		
		if(str.contains(actualResult.replace(" ", ""))) {
			
			System.out.println("Passed =" + TestCaseId);
			test.log(LogStatus.PASS,"matched");
			
		}else {
			
			System.out.println("Passed =" + TestCaseId);
			test.log(LogStatus.FAIL, "Unable to find the answer");
			
		}
		Thread.sleep(3000);

	}
	
	@AfterClass
	public  void endTest()
	{
		extentreports.endTest(test);
		extentreports.flush();
	}
	

}
