package PasswordManagerTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestSuiteOnExtentReport.BeforeAfterSuiteRepresent;

public class RegistrationTest extends BeforeAfterSuiteRepresent{
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		driver.get("http://localhost:8085/PasswordManager/register.jsp");
	}
	@Test
	public void Clicktest() {
		test = extent.createTest("Registration: Click Test", "Description: Checking the functionality of Registration submission.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("anjali");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("anjali");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		boolean click = driver.findElement(By.id("signup")).isEnabled();
		if(click) {
			test.log(Status.PASS, "Registation button enabled!");
			assertTrue(true);
		}else {
			test.log(Status.FAIL, "Registation button disabled!");
			assertTrue(false);
		}
		
	}
	@Test
	public void AlreadyExitTest() {
		test = extent.createTest("Registration: Already exit user test", "Description: Checking for existing user functionality on registration.");
		driver.findElement(By.id("fullname")).sendKeys("anjali");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("anjali");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("anjali");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("User Already Exists!")) {
			test.log(Status.PASS, "User Already Exists!");
			assertEquals(invalidMessage, "User Already Exists!");
		}else {
			test.log(Status.FAIL, "Registered Existing user!");
			assertTrue(false);
		}
		
	}
	@Test
	public void invalidFullnameTest() {
		test = extent.createTest("Registration: Invalid Fullname Test", "Description: Checking the functionality of Invalid Fullname field.");
		driver.findElement(By.id("fullname")).sendKeys("");
		test.log(Status.INFO, "Empty fullname provided into field.");
		driver.findElement(By.id("username")).sendKeys("anjali");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("anjali");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Please check your fullname!")) {
			test.log(Status.PASS, "Error message Displayed successfull!");
			assertEquals(invalidMessage, "Please check your fullname!");
		}else {
			test.log(Status.FAIL, "Registered Existing user!");
			assertTrue(false);
		}
		
	}

	@Test
	public void invalidUserPassTest() {
		test = extent.createTest("Registration: Invalid UserPassTest", "Description: Checking the functionality of Invalid Username and password fields.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Please check your username!")) {
			test.log(Status.PASS, "Error message of username displayed successfull!");
			assertEquals(invalidMessage, "Please check your username!");
		}else {
			test.log(Status.FAIL, "Accepted empty username and password.");
			assertTrue(false);
		}
	}
	
	@Test
	public void invalidUsernameLengthTest() {
		test = extent.createTest("Registration: Invalid Username Length Test", "Description: Checking the exceeded username length invalid test.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("GNNTNSLHRNZMMUZCKWHVMHVUMKBSVLDDBRWQJJXDWXRITTNDPUADBYXQIZKFK");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("anjali");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Username lenghth should be below 50.")) {
			test.log(Status.PASS, "Error message of username length displayed.");
			assertEquals(invalidMessage, "Username lenghth should be below 50.");
		}else {
			test.log(Status.FAIL, "Accepted invalid length username.");
			assertTrue(false);
		}
		
	}
	
	@Test
	public void invalidLengthPasswordLengthTest() {
		test = extent.createTest("Registration: Invalid Password Length Test", "Description: Checking the exceeded password length test.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("anjali");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("cue#yC~}A_~t\\|8w_4m`}x<Lo[]JNkW&40\"=>_lNpk[gHYDBN&XL{8glYy+vI");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Password lenghth should be below 40.")) {
			test.log(Status.PASS, "Error message of password length displayed.");
			assertEquals(invalidMessage, "Password lenghth should be below 40.");
		}else {
			test.log(Status.FAIL, "Accepted invalid length password.");
			assertTrue(false);
		}
		
	}
	
	@Test
	public void emptyUsernameFieldTest() {
		test = extent.createTest("Registration: Empty Username Test", "Description: Checking the empty username test.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("anjali");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Please check your username!")) {
			test.log(Status.PASS, "Error message of password length displayed.");
			assertEquals(invalidMessage, "Please check your username!");
		}else {
			test.log(Status.FAIL, "Accepted empty username.");
			assertTrue(false);
		}
		
	}
	
	@Test
	public void emptyPasswordFieldTest() {
		test = extent.createTest("Registration: Empty Password Test", "Description: Checking the empty password test.");
		driver.findElement(By.id("fullname")).sendKeys("anjali patil");
		test.log(Status.INFO, "Fullname entered into field.");
		driver.findElement(By.id("username")).sendKeys("anjali");
		test.log(Status.INFO, "Username entered into field.");
		driver.findElement(By.id("password")).sendKeys("");
		test.log(Status.INFO, "Password entered into field.");
		driver.findElement(By.id("repassword")).sendKeys("anjali");
		test.log(Status.INFO, "Re-enter password entered into field.");
		driver.findElement(By.id("signup")).click();
		String invalidMessage = driver.findElement(By.id("message")).getText();
		if(invalidMessage.equals("Please check your Password!")) {
			test.log(Status.PASS, "Error message of empty password displayed.");
			assertEquals(invalidMessage, "Please check your Password!");
		}else {
			test.log(Status.FAIL, "Accepted empty password.");
			assertTrue(false);
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}