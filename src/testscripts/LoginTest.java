/* 1. complete the TC1 & TC2 from below sheet
https://docs.google.com/spreadsheets/d/1XAsOC5KFoI_AOnB9Zwn4AvSSU0pHXyGgoCexc8Pt-HE/edit#gid=0
*/

package testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;

public class LoginTest {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		ControlActions.launchBrowser();
	}

	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage();
		loginPage.login("sayali.honrao@gmail.com", "Sayali@1441");
		
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag);
	
	}

	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}