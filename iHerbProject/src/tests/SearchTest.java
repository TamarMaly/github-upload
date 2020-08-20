package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductsPage;

public class SearchTest extends BaseTest {

//	@Test
	public void tc27_searchDropDownMenu() {
		HomePage hp = new HomePage(driver);
		hp.searchDropDownMenu("apple");

		boolean actual = hp.searchedItemsInDDMenu("apple");
		assertEquals(actual, true);
	}

//	@Test
	public void tc28_emptySearch() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("");

		String actual = hp.currentURL();
		assertEquals(actual, "https://il.iherb.com/");
	}

//	@Test
	public void tc29_nonExistingSearch() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("dkjnvKJ");

		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.noresults();

		assertEquals(actual, true);
	}

	@Test
	public void tc30_searchInProductsPage() {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage("apple");

		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.checkSearchedItems("apple");

		assertEquals(actual, true);
	}
}
