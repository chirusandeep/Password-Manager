package PasswordManagerPageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCredentialPage {
	WebDriver driver;
	
	public void driverSet(WebDriver driver) {
		this.driver = driver;
	}
	public void url() {
		driver.get("http://localhost:8085/PasswordManager/add-credential.jsp");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	public void website(String website) {
		driver.findElement(By.id("website")).sendKeys(website);
	}
	public void username(String username) {
		driver.findElement(By.id("username")).sendKeys(username);
	}
	public void password(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}
	public void addcredential() {
		driver.findElement(By.id("addcred")).click();
	}
	public String message() {
		return driver.findElement(By.id("message")).getText();
	}
}
