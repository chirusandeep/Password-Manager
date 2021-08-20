package PasswordManagerPageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditCredentialPage {

	
	WebDriver driver;
	
	public void driverSet(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectEditElement(String elementId) {
		driver.findElement(By.id(elementId)).click();
	}
	
	public void Updatewebsite(String website) {
		driver.findElement(By.id("website")).clear();
		driver.findElement(By.id("website")).sendKeys(website);
	}
	public void Updateusername(String username) {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
	}
	public void Updatepassword(String password) {
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
	}
	public void editedCredentialSubmit() {
		driver.findElement(By.id("editcred")).click();
	}
	public String successMessage() {
		return driver.findElement(By.id("message")).getText();
	}
	public String errorMessage() {
		return driver.findElement(By.id("errormessage")).getText();
	}
}
