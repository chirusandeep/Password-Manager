package PasswordManagerPageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	public void driverSet(WebDriver driver) {
		this.driver = driver;
	}
	
	public void url() {
		driver.get("http://localhost:8085/PasswordManager/login.jsp");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public void username(String username) {
		
		driver.findElement(By.id("username")).sendKeys(username);
	}
	
	public void password(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}
	
	public void signin() {
		driver.findElement(By.id("signin")).click();
	}
	
}
