package PasswordManagerTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import TestSuiteOnExtentReport.BeforeAfterSuiteRepresent;

public class LoginTest extends BeforeAfterSuiteRepresent{


	WebDriver driver;
	public static final Logger logger = LogManager.getLogger(LoginTest.class.getName());
	@BeforeTest
	public void setup() {

		PropertyConfigurator.configure("log4j.properties");  
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
			logger.info("Click Test passed !!!");
			assertTrue(true);
			
		}else {
			test.log(Status.FAIL, "Click button disabled!");
			logger.info("Click Test not passed !!!");
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
			logger.info("Invalid Username Test passed !!!");
			assertEquals(invalidMessage, "Invalid username/password!");
			
		}else {
			test.log(Status.FAIL,"Invalid username test failed!");
			logger.error("Invalid Username Test not passed !!!");
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
			logger.info("Invalid Password Test passed !!!");
			assertEquals(invalidMessage, "Invalid username/password!");
			
		}else {
			test.log(Status.FAIL,"Invalid password test failed!");
			logger.error("Invalid Password Test not passed !!!");
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
			logger.info("Empty Username Field Test passed !!!");
			assertEquals(invalidMessage, "Invalid username/password!");
			
		}else { 
			test.log(Status.FAIL,"Invalid password test failed!");
			logger.error("Empty Username Field Test not passed !!!");
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
			logger.info("Empty Password Field Test passed !!!");
			assertEquals(invalidMessage, "Invalid username/password!");
			
		}else {
			test.log(Status.FAIL,"Invalid password test failed!");
			logger.error("Empty Password Field Test not passed !!!");
			assertTrue(false);
			
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
//		extent.flush();
	}
}
