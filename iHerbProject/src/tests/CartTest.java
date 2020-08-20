package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;

public class CartTest extends BaseTest {

	// Item number - 95745 "Gummiology"
	@Test
	public void tc31_buyFromProductsPage() {
		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");

		assertEquals(pp.isInCart(), true);

		pp.removeItemFromCart("95745");
	}

	// Item number - 95745 "Gummiology"
	@Test
	public void tc32_buyFromProductDetailsPage() {
		ProductDetailsPage pd = new ProductDetailsPage(driver);
		pd.addItemToCartFromProductDetailsPage("95745");

		ProductsPage pp = new ProductsPage(driver);
		assertEquals(pp.isInCart(), true);

		pp.removeItemFromCart("95745");
	}

	// Item number - 95745 "Gummiology"
	@Test
	public void tc33_removeItemFromCart() {
		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");
		pp.removeItemFromCart("95745");

		CartPage cp = new CartPage(driver);
		assertEquals(cp.isCartEmpty(), true);
	}

	// Item number - 95745 "Gummiology", Item number - 32041 "Puffs"
	// Item number - 82471 "Bathtub Crayons"
	@Test
	public void tc34_checkCartSubtotal() {
		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");
		pp.addItemToCartFromProductsPage("32041");
		pp.addItemToCartFromProductsPage("82471");
		pp.moveToCart();

		CartPage cp = new CartPage(driver);
		assertEquals(cp.calculateItemsInCart(), cp.cartTotal());
	}
}
