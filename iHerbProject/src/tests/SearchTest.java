package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductsPage;

public class SearchTest extends BaseTest {

	// Search in drop down menu
	// If the user searches for an existing item in search then a list of all
	// relevant items will appear in the drop down under search
	@Test
	public void tc27_searchDropDownMenu() {
		HomePage hp = new HomePage(driver);
		hp.searchDropDownMenu("apple");

		boolean actual = hp.searchedItemsInDDMenu("apple");
		assertEquals(actual, true);
	}

	// Empty search
	// If the user searches for empty search then nothing will happen
	@Test
	public void tc28_emptySearch() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("");

		String actual = hp.currentURL();
		assertEquals(actual, "https://il.iherb.com/");
	}

	// Non existing search
	// If the user searches for non existing item in search then a message will
	// appear
	@Test
	public void tc29_nonExistingSearch() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("dkjnvKJ");

		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.noresults();

		assertEquals(actual, true);
	}

	// Search in products page
	// If the user searches for an existing item in search then a list of all
	// relevant items will appear
	@Test
	public void tc30_searchInProductsPage() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("apple");

		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.checkSearchedItems("apple");

		assertEquals(actual, true);
	}
}
