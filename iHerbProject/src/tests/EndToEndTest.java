package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageobjects.AccountSettingsPage;
import pageobjects.AddressBookPage;
import pageobjects.HomePage;
import pageobjects.ProductsPage;
import utils.Utils;

public class EndToEndTest extends BaseTest {

	@Test
	public void tc31_E2EBuyFromProductsPage() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("apple");

		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");
		pp.addItemToCartFromProductsPage("32041");
		pp.addItemToCartFromProductsPage("82471");

		hp.login();
		hp.logout();

		boolean actual = hp.isLogout();
		AssertJUnit.assertEquals(actual, true);
	}

	@Test
	public void tc32_E2EChangeSettings() {
		HomePage hp = new HomePage(driver);
		hp.login();

		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");
		ab.deleteAddress();

		AccountSettingsPage as = new AccountSettingsPage(driver);
		as.enterAccountSettingsPage();
		as.editAccountEmail("zzz@needrabit.tk");
		as.editAccountEmail(Utils.readProperty("user"));
		as.backToHomePage();

		hp.searchDropDownMenu("apple");
		hp.logout();

		boolean actual = hp.isLogout();
		AssertJUnit.assertEquals(actual, true);
	}

}
