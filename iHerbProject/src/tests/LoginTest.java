package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.Utils;

public class LoginTest extends BaseTest {

	protected String validUserName = Utils.readProperty("user");
	protected String validPhone = Utils.readProperty("phone");
	protected String validPassword = Utils.readProperty("password");

	//
//	@Test
	public void tc01_successfulUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validUserName, validPassword);

		HomePage hp = new HomePage(driver);
		String actual = hp.whoIsLoggedIn();

		AssertJUnit.assertEquals(actual, "Current");
		hp.logout();
	}

	//
	@Test(dataProvider = "getData", description = "In this test I used dataProvider which means the test method will run multiple times with different data sets - login should fail")
	public void tc02_invalidUserLogin(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(email, password);

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] myData = { { "222Email@gmail.com", validPassword }, { "Wrong1@gmail.com", validPassword },
				{ "Wrong1-Email777@gmail.com", validPassword }

		};
		return myData;
	}

	//
//	@Test
	public void tc03_emptyUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("", validPassword);

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc04_invalidPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validUserName, "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc05_invalidUserInfoLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn("Wrong-Email@gmail.com", "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc06_emptyUserInvalidPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("", "Passcheck");

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc07_noPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithMissingInfo(validUserName, "");

		String actual = lp.missingPasswordMessage();

		AssertJUnit.assertEquals(actual, "יש להזין סיסמה.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc08_emptyPasswordInvalidUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithMissingInfo("Wrong-Email", "");

		String actual = lp.missingPasswordMessage();

		AssertJUnit.assertEquals(actual, "יש להזין סיסמה.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc09_noInfoLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.noInfoLogin();

		String actual = lp.missingUserNameMessage();
		String actual1 = lp.missingPasswordMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		AssertJUnit.assertEquals(actual1, "יש להזין סיסמה.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc10_successfulPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validPhone, validPassword);

		HomePage hp = new HomePage(driver);
		String actual = hp.whoIsLoggedIn();

		AssertJUnit.assertEquals(actual, "Current");
		hp.logout();
	}

	//
//	@Test
	public void tc11_invalidPasswordWithPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validPhone, "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc12_invalidPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("5555", validPassword);

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc13_invalidPhoneInfoLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("5555", "Passcheck");

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	//
//	@Test
	public void tc14_logout() {
		HomePage hp = new HomePage(driver);
		hp.login();

		hp.logout();
		boolean actual = hp.isLogout();

		AssertJUnit.assertEquals(actual, true);
	}
}
