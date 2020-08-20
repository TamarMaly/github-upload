package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import pageobjects.AccountSettingsPage;
import utils.Utils;

public class AccountSettingsTest extends BaseTest {

	//
	@Test
	public void tc15_editValidAccountName() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountNameInfo("Valid name");

		String actual = as.accountName();
		AssertJUnit.assertEquals(actual, "Valid name");

		as.editAccountNameInfo("Current name");
		as.backToHomePage();
	}

	//
	@Test
	public void tc16_editInvalidAccountName() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountNameInfo("V");

		String actual = as.errorInvalidNameMessage();
		AssertJUnit.assertEquals(actual, "יש להזין שם משפחה.*");

		as.backToHomePage();
	}
	
	@Test
	public void tc17_editValidEmail() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountEmail("zzz@needrabit.tk");

		String actual = as.checkCurrentEmail();
		AssertJUnit.assertEquals(actual, "zzz@needrabit.tk");

		as.editAccountEmail(Utils.readProperty("user"));
		as.backToHomePage();
	}

	@Test
	public void tc18_editInvalidEmail() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountEmail("zzz");

		boolean actual = as.isEmailValid();
		AssertJUnit.assertEquals(false, actual);

		as.backToHomePage();
	}

	@Test
	public void tc19_editInvalidPhone() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountPhone("xcxzc");

		boolean actual = as.isPhoneValid();
		AssertJUnit.assertEquals(false, actual);

		as.backToHomePage();
	}

	@Test
	public void tc20_editValidPassword() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountPassword("Passcheck2@");

		String actual = as.isPasswordChanged();
		AssertJUnit.assertEquals(actual, "הסיסמה עודכנה בהצלחה");

		as.editAccountPassword(Utils.readProperty("password"));
		as.backToHomePage();
	}

	@Test
	public void tc21_editInvalidPassword() {
		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountPassword("xcxzc");

		boolean actual = as.isPasswordValid();
		AssertJUnit.assertEquals(false, actual);

		as.backToHomePage();
	}

}
