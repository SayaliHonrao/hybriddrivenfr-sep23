/* 1. complete the TC1 & TC2 from below sheet
https://docs.google.com/spreadsheets/d/1XAsOC5KFoI_AOnB9Zwn4AvSSU0pHXyGgoCexc8Pt-HE/edit#gid=0
*/

package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest extends TestBase {


	@Test
	public void loginTest() {
		ControlActions.takeScreenshot("login1");
		loginPage.login("sayali.honrao@gmail.com", "Sayali@1441");
		boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		ControlActions.takeScreenshot("login2");
		Assert.assertTrue(loginFlag);
	}

	@Test
	public void verifyErrorMessages() {
		System.out.println("STEP - Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("VERIFY - Email required Error message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);

		System.out.println("VERIFY - Password required Error message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}

	@Test
	public void verifyPasswordErrorMessage() {
		System.out.println("STEP - Enter valid User email");
		loginPage.enterUserEmail("sayali.honrao@gmail.com");

		System.out.println("STEP - Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("VERIFY - Email required Error message is not visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorMessageFlag);

		System.out.println("VERIFY - Password required Error message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}

	@Test
	public void verifyEmailErrorMessageDisplayed() {
		System.out.println("STEP - Enter valid password");
		loginPage.enterPassword("Sayali@1441");

		System.out.println("STEP - Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("VERIFY - Email required Error message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);

		System.out.println("VERIFY - Password required Error message is not visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordErrorMessageFlag);
	}

	@Test(dataProvider = "LoginDataProvider")
	public void verifyloginUsingDataDriven(String username, String password, String loginStatus) {
		System.out.println("STEP - Login with given credentails");
		loginPage.login(username, password);
		String currentURL = "";
		boolean loginFlag;
		if (loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("VERIFY - Login Successful toast message displayed");
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertTrue(loginFlag, "Login successfully Message is not displayed");

			System.out.println("VERIFY - Incorrect email or password message is not displayed");
			loginFlag = loginPage.isLoginUnSuccessfulElementDisplayed();
			Assert.assertFalse(loginFlag, "Incorrect email or password message is displayed");

			currentURL = loginPage.getCurrentURL();
			System.out.println("VERIFY - Application should redirect to dahsboard page");
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL :" + currentURL);
		} else {
			loginFlag = loginPage.isLoginUnSuccessfulElementDisplayed();
			Assert.assertTrue(loginFlag, "Incorrect email or password message is not displayed");

			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertFalse(loginFlag, "Login successfully Message is displayed");

			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("auth/login"));
		}
	}

	@DataProvider(name = "LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		return ExcelOperations.getAllRows(".//testData/LoginData.xlsx", "Login");
	}
}