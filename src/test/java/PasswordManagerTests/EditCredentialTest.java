package PasswordManagerTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PasswordManagerPageObjects.EditCredentialPage;
import PasswordManagerPageObjects.LoginPage;
import TestSuiteOnExtentReport.BeforeAfterSuiteRepresent;

public class EditCredentialTest extends BeforeAfterSuiteRepresent{

	WebDriver driver;
	LoginPage loginpage = new LoginPage();
	EditCredentialPage edit = new EditCredentialPage();
	
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		loginpage.driverSet(driver);
		driver.manage().window().maximize();
		loginpage.url();
		loginpage.username("sandeep");
		loginpage.password("sandeep");
		loginpage.signin();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		edit.driverSet(driver);
	}
	@Test(priority = 1)
	public void MaxLengthWebsiteData() {
		test = extent.createTest("Edit Cred: Max Length Website","Description: Max length of data given in Website field.");
		edit.selectEditElement("edit-105");
		edit.Updatewebsite("d/j_H\"4nj.KHu/$emQq]\\!\\\"3Qno/.Vv_qF`%,^r");
		test.log(Status.INFO, "Website URL Entered into field.");
		edit.editedCredentialSubmit();
		test.log(Status.INFO, "Clicked on update credential.");
		String message = edit.successMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		if(message.equals("Successfully updated!")) {
			test.log(Status.PASS, "Sucessfully updated credentials!");
			assertEquals(message, "Successfully updated!");
		}else {
			test.log(Status.FAIL, "Unable updated credentials!");
			assertTrue(false);
		}
		
		
	}
	@Test(priority = 2)
	public void ExceedLengthWebsiteData() {
		test = extent.createTest("Edit Credential: Exceed Length Website Data","Description: Max length of data given in Website field.");
		edit.selectEditElement("edit-105");
		edit.Updatewebsite("d/j_H\"4nj.KHu/$emQq]\\!\\\"3Qno/.Vv_qF`%,^r123");
		test.log(Status.INFO, "Website URL Entered into field.");
		edit.editedCredentialSubmit();
		test.log(Status.INFO, "Clicked on update credential.");
		String message = edit.errorMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		if(message.equals("Website length should be below 40!")) {
			test.log(Status.PASS, "Length error message displayed.");
			assertEquals(message, "Website length should be below 40!");
		}else {
			test.log(Status.FAIL, "updated Website credential on more than 40!");
			assertTrue(false);
		}
		
	}
	@Test(priority = 3)
	public void UpdateWebsiteData() {
		test = extent.createTest("Edit Credential: Update Website Data","Description: Updating Website field.");
		edit.selectEditElement("edit-105");
		edit.Updatewebsite("https://gmail.com");
		test.log(Status.INFO, "Website URL Entered into field.");
		edit.editedCredentialSubmit();
		String message = edit.successMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		
		if(message.equals("Successfully updated!")) {
			test.log(Status.PASS, "Successfully updated website data!");
			assertEquals(message, "Successfully updated!");
		}else {
			test.log(Status.FAIL, "Unable to update website data!");
			assertTrue(false);
		}
	}
	@Test(priority = 4)
	public void UpdateUsername() {
		test = extent.createTest("Edit Credential: Update Website Data","Description: Updating Website field.");
		edit.selectEditElement("edit-105");
		edit.Updateusername("sandeep@g2a.com");
		test.log(Status.INFO, "Username Entered into field.");
		edit.editedCredentialSubmit();
		test.log(Status.INFO, "Clicked on update credential.");
		String message = edit.successMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		
		if(message.equals("Successfully updated!")) {
			test.log(Status.PASS, "Successfully updated username data!");
			assertEquals(message, "Successfully updated!");
		}else {
			test.log(Status.FAIL, "Unable to update username data!");
			assertTrue(false);
		}
	}
	@Test(priority = 5)
	public void UpdatePassword() {
		test = extent.createTest("Edit Credential: Update Password Data","Description: Updating password field.");
		edit.selectEditElement("edit-105");
		edit.Updatepassword("sandeep#007");
		test.log(Status.INFO, "password Entered into field.");
		edit.editedCredentialSubmit();
		test.log(Status.INFO, "Clicked on update credential.");
		String message = edit.successMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		
		if(message.equals("Successfully updated!")) {
			test.log(Status.PASS, "Successfully updated password data!");
			assertEquals(message, "Successfully updated!");
		}else {
			test.log(Status.FAIL, "Unable to update password data!");
			assertTrue(false);
		}
	}
	
	@Test(priority = 6)
	public void UpdateWebsiteURL() {
		test = extent.createTest("Edit Credential: Update Website Data","Description: Updating website field.");
		edit.selectEditElement("edit-105");
		edit.Updatewebsite("https://g2a.com");
		test.log(Status.INFO, "Website entered into field.");
		edit.editedCredentialSubmit();
		test.log(Status.INFO, "Clicked on update credential.");
		String message = edit.successMessage();
		driver.navigate().to("http://localhost:8085/PasswordManager/index.jsp");
		
		if(message.equals("Successfully updated!")) {
			test.log(Status.PASS, "Successfully updated password data!");
			assertEquals(message, "Successfully updated!");
		}else {
			test.log(Status.FAIL, "Unable to update password data!");
			assertTrue(false);
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
