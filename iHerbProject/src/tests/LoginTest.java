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

	// Successful user login
	// If the user enters valid user and password info then he'll login the website
//	@Test
	public void tc01_successfulUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validUserName, validPassword);

		HomePage hp = new HomePage(driver);
		String actual = hp.whoIsLoggedIn();

		AssertJUnit.assertEquals(actual, "Current");
		hp.logout();
	}

	// Invalid user login - uses data provider
	// If the user enters invalid user info then he won't login the website
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

	// Empty user login
	// If the user enters only password he won't login the website
//	@Test
	public void tc03_emptyUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("", validPassword);

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	// Invalid password login
	// If the user enters valid user and invalid password info then he won't login
	// the website
//	@Test
	public void tc04_invalidPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validUserName, "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	// Invalid user info login
	// If the user enters invalid user and password info then he won't login the
	// website
//	@Test
	public void tc05_invalidUserInfoLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn("Wrong-Email@gmail.com", "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	// Empty user invalid password login
	// If the user enters only invalid password he won't login the website
//	@Test
	public void tc06_emptyUserInvalidPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("", "Passcheck");

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	// No password login
	// If the user enters only user info then he won't login the website
//	@Test
	public void tc07_noPasswordLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithMissingInfo(validUserName, "");

		String actual = lp.missingPasswordMessage();

		AssertJUnit.assertEquals(actual, "יש להזין סיסמה.");
		lp.backToHomePage();
	}

	// Empty password invalid user login
	// If the user enters only invalid user he won't login the website
//	@Test
	public void tc08_emptyPasswordInvalidUserLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithMissingInfo("Wrong-Email", "");

		String actual = lp.missingPasswordMessage();

		AssertJUnit.assertEquals(actual, "יש להזין סיסמה.");
		lp.backToHomePage();
	}

	// No info login
	// If the user doesn't enter any info then he won't login the website
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

	// Successful phone login
	// If the user enters invalid user login info then he won't login the website
//	@Test
	public void tc10_successfulPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validPhone, validPassword);

		HomePage hp = new HomePage(driver);
		String actual = hp.whoIsLoggedIn();

		AssertJUnit.assertEquals(actual, "Current");
		hp.logout();
	}

	// Invalid password with phone login
	// If the user enters invalid user login info then he won't login the website
//	@Test
	public void tc11_invalidPasswordWithPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn(validPhone, "Passcheck");

		String actual = lp.errorUserNamePasswordMessage();

		AssertJUnit.assertEquals(actual, "כתובת דוא\"\"ל או סיסמא שגויה. אנא נסו שנית.");
		lp.backToHomePage();
	}

	// Invalid phone login
	// If the user enters invalid user login info then he won't login the website
//	@Test
	public void tc12_invalidPhoneLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("5555", validPassword);

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	// Invalid phone info login
	// If the user enters invalid password login info then he won't login to the
	// website
//	@Test
	public void tc13_invalidPhoneInfoLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.loginWithoutSignIn("5555", "Passcheck");

		String actual = lp.missingUserNameMessage();

		AssertJUnit.assertEquals(actual, "יש להזין מספר טלפון נייד או כתובת אימייל");
		lp.backToHomePage();
	}

	// Logout
	// If the user chooses to logout he will logout the website and returns to the
	// website's homepage
//	@Test
	public void tc14_logout() {
		HomePage hp = new HomePage(driver);
		hp.login();

		hp.logout();
		boolean actual = hp.isLogout();

		AssertJUnit.assertEquals(actual, true);
	}
}
