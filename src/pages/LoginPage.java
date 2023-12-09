package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ControlActions;

public class LoginPage extends ControlActions {
	
	public void login(String email, String password) {
		System.out.println("STEP 1 - Enter user details and Login");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	} 
	
	public boolean isLoginSuccessfullyDisplayed(){	
		WebElement loginSuccessfulElement = getElement("XPATH","//div[@aria-label='Login Successfully']",true);
		return loginSuccessfulElement.isDisplayed();
	}
}