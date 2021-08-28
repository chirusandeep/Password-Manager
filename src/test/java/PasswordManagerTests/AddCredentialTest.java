package PasswordManagerTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PasswordManagerPageObjects.AddCredentialPage;
import PasswordManagerPageObjects.LoginPage;
import TestSuiteOnExtentReport.BeforeAfterSuiteRepresent;



public class AddCredentialTest extends BeforeAfterSuiteRepresent{

	WebDriver driver;
	LoginPage loginpage = new LoginPage();
	AddCredentialPage addcred = new AddCredentialPage();
    public static final Logger logger = LogManager.getLogger(AddCredentialTest.class.getName());
	
	@BeforeTest
	public void setup() {
		PropertyConfigurator.configure("log4j.properties");       
		driver = new ChromeDriver();
		loginpage.driverSet(driver);
		loginpage.url();
		loginpage.username("sandeep");
		loginpage.password("sandeep");
		loginpage.signin();
		addcred.driverSet(driver);
		addcred.url();
	}
	
	//Test Case 1: Adding Valid Data
	@Test
	public void addValidData() {

		test = extent.createTest("Add Credential: Adding valid data","Description: Adding valid data in fields.");
		addcred.website("http://gmail.com");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("sandeep@gmail.com");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("sandeep#007");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Credential Added successfull!")) {
			test.log(Status.PASS, "Added valid data credentials.");
			logger.info("Added Valid Data !!!");
			assertEquals(message, "Credential Added successfull!");
			
		}else {
			test.log(Status.FAIL,"Unable to add valid data credentials.");
			logger.error("Valid data not added !!!");
			assertTrue(false);
	    	
		}
		
		
	}
	//Test Case 2: Empty Website Data
	@Test
	public void emptyWebsiteData() {
		test = extent.createTest("Add Credential: Adding valid data","Description: Adding valid data in fields.");
		addcred.website("");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("sandeep@gmail.com");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("sandeep#007");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Credential Failed to add!")) {
			test.log(Status.PASS, "Unable to add empty data on Website field.");
			logger.info("Empty Website Data passed!!!");
			assertEquals(message, "Credential Failed to add!");
			
		}else {
			test.log(Status.FAIL,"Empty Website data accepted.");
			logger.error("Empty Website Data not passed!!!");
			assertTrue(false);
			
		}
		
		
	}
	//Test Case 3: Max Length Website Data
	@Test
	public void MaxLengthWebsiteData() {
		test = extent.createTest("Add Credential: Max Length Website field","Description: Providing Max lenght in website field.");
		addcred.website("4,/x%(p0AY,)\"GShWxADSEz6*rC*G`sD,F<mR/39");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("sandeep@gmail.com");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("sandeep#007");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Credential Added successfull!")) {
			test.log(Status.PASS, "Added valid data credentials.");
			logger.info("Maximum Length Website Data test passed !!!");
			assertEquals(message, "Credential Added successfull!");
			
		}else {
			test.log(Status.FAIL,"Unable to add valid data credentials.");
			logger.error("Maximum Length Website Data test not passed !!!");
			assertTrue(false);
			
		}
		
	}
	//Test Case 4: Invalid Length Website Data
	@Test
	public void InvalidLengthWebsiteData() {
		test = extent.createTest("Add Credential: Invalid Length Website Data","Description: Providing empty data in website field.");
		addcred.website("\"2GJ2\"VKt)z/ygJ_8k(GZSa@cZ8ZLbl0@x>+E^G1??kPvsp`<v1");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("sandeep@gmail.com");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("sandeep#007");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Website length should be below 40!")) {
			test.log(Status.PASS, "Lenght error message is displayed.");
			logger.info("Invalid Length Website Data passed !!!");
			assertEquals(message, "Website length should be below 40!");
			
		}else {
			test.log(Status.FAIL,"Accepts more than 40 characters.");
			logger.error("Invalid Length Website Data not passed !!!");
			assertTrue(false);
			
		}
		
		
	}
	//Test Case 5: Invalid Username Length
	@Test
	public void InvalidUsernameLength() {
		test = extent.createTest("Add Credential: Invalid Length Username Data","Description: Providing invalid length data in username field.");
		addcred.website("http://yahoo.com");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("mk7b\"rON1451-7j^=71ioon.|\\/,]zGDx8P&ST:XomQeiV!jR}x\\4Iaj5sza}");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("sandeep#007");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Username length should be below 60!")) {
			test.log(Status.PASS, "Lenght error message is displayed.");
			logger.info("Invalid Username Length passed !!!");
			assertEquals(message, "Username length should be below 60!");
			
		}else {
			test.log(Status.FAIL,"Accepts more than 60 characters.");
			logger.error("Invalid Username Length not passed !!!");
			assertTrue(false);
			
		}
		
		
	}
	//Test Case 6: Invalid Password Length
	@Test
	public void InvalidPasswordLength() {
		test = extent.createTest("Add Credential: Invalid Length Password Data","Description: Providing invalid length data in password field.");
		addcred.website("http://netflix.com");
		test.log(Status.INFO, "Website URL Entered into field.");
		addcred.username("sandeep@gmail.com");
		test.log(Status.INFO, "Username entered into field.");
		addcred.password("mk7b\\\"rON1451-7j^=71ioon.|\\\\/,]zGDx8P&ST:XomQeiV!jR}x\\\\4Iaj5sza}");
		test.log(Status.INFO, "Password entered into field.");
		addcred.addcredential();
		String message = addcred.message();
		if(message.equals("Password length should be below 60!")) {
			test.log(Status.PASS, "Lenght error message is displayed.");
			logger.info("Invalid Password Length passed !!!");
			assertEquals(message, "Password length should be below 60!");
			
		}else {
			test.log(Status.FAIL,"Accepts more than 60 characters.");
			logger.error("Invalid Password Length not passed !!!");
			assertTrue(false);
			
		}
		
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
