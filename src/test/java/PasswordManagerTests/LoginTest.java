package PasswordManagerTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import TestSuiteOnExtentReport.BeforeAfterSuiteRepresent;

public class LoginTest extends BeforeAfterSuiteRepresent{

//    public static ExtentHtmlReporter htmlReporter;
//    public static ExtentReports extent;
//    public static ExtentTest test;
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
//		 htmlReporter = new ExtentHtmlReporter("login-report.html");
//	     extent = new ExtentReports();
//	     extent.attachReporter(htmlReporter);      
		driver = new ChromeDriver();
		driver.get("http://localhost:8085/PasswordManager/login.jsp");
	}
//	Test case 1: Click Test
	@Test
	public void ClickTest() throws InterruptedException {
		
		test = extent.createTest("Login: Click Test", "Description: Checking the functionality of SignIn.");
		driver.findElement(By.id("username")).sendKeys("sandeep");
		test.log(Status.INFO, "Username entered into the field.");
		driver.findElement(By.id("password")).sendKeys("sandeep");
		test.log(Status.INFO, "Password entered into the field.");
		boolean click = driver.findElement(By.id("signin")).isEnabled();
		if(click) {
			test.log(Status.PASS, "Click button enabled!");
			assertTrue(true);
		}else {
			test.log(Status.FAIL, "Click button disabled!");
			assertTrue(false);
		}
		
	}
//	Test case 2: Invalid Username Test
	@Test
	public void invalidUsernameTest() {
		test = extent.createTest("Login: Invalid Username Test", "Description: Checking the Invalid Username.");
		driver.findElement(By.id("username")).sendKeys("sanddep");
		test.log(Status.INFO, "Username entered into the field.");
		driver.findElement(By.id("password")).sendKeys("sandeep");
		test.log(Status.INFO, "Password entered into the field.");
		driver.findElement(By.id("signin")).click();
		test.log(Status.INFO, "User clicked signin.");
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Invalid username/password!")) {
			test.log(Status.PASS, "Invalid Username test passed.");
			assertEquals(invalidMessage, "Invalid username/password!");
		}else {
			test.log(Status.FAIL,"Invalid username test failed!");
			assertTrue(false);
		}
		
	}
//	Test case 3: Invalid Password Test
	@Test
	public void invalidPasswordTest() {
		test = extent.createTest("Login: Invalid Password Test", "Description: Checking the functionality of Invalid Password.");
		driver.findElement(By.id("username")).sendKeys("sandeep");
		test.log(Status.INFO, "Username entered into the field.");
		driver.findElement(By.id("password")).sendKeys("sanddep");
		test.log(Status.INFO, "Password entered into the field.");
		driver.findElement(By.id("signin")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		
		if(invalidMessage.equals("Invalid username/password!")) {
			test.log(Status.PASS, "Invalid password test passed.");
			assertEquals(invalidMessage, "Invalid username/password!");
		}else {
			test.log(Status.FAIL,"Invalid password test failed!");
			assertTrue(false);
		}
	}
//	Test case 4: Empty Username Field Test
	@Test
	public void emptyUsernameFieldTest() {
		test = extent.createTest("Login: Empty Username Field Test", "Description: Checking the functionality of empty username field.");
		driver.findElement(By.id("username")).sendKeys("");
		test.log(Status.INFO, "Username entered into the field.");
		driver.findElement(By.id("password")).sendKeys("sandeep");
		test.log(Status.INFO, "Password entered into the field.");
		driver.findElement(By.id("signin")).click();
		test.log(Status.INFO, "User clicked signin.");
		String invalidMessage = driver.findElement(By.id("message")).getText();
		
		if(invalidMessage.equals("Invalid username/password!")) {
			test.log(Status.PASS, "Invalid password test passed.");
			assertEquals(invalidMessage, "Invalid username/password!");
		}else {
			test.log(Status.FAIL,"Invalid password test failed!");
			assertTrue(false);
		}
	}
//	Test case 5: Empty Password Field Test
	@Test
	public void emptyPasswordFieldTest() {
		test = extent.createTest("Login: Empty Password Field Test", "Description: Checking the functionality of empty password field.");
		driver.findElement(By.id("username")).sendKeys("sandeep");
		test.log(Status.INFO, "Username entered into the field.");
		driver.findElement(By.id("password")).sendKeys("");
		test.log(Status.INFO, "Password entered into the field.");
		driver.findElement(By.id("signin")).click();
		test.log(Status.INFO, "User clicked signin.");
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Invalid username/password!")) {
			test.log(Status.PASS, "Invalid password test passed.");
			assertEquals(invalidMessage, "Invalid username/password!");
		}else {
			test.log(Status.FAIL,"Invalid password test failed!");
			assertTrue(false);
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
//		extent.flush();
	}
}
