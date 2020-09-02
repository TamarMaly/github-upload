package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;

public class CartTest extends BaseTest {

	// Buy from products page
	// If the user adds an item to cart from the products page then it will appear
	// in his cart
	// Item number - 95745 "Gummiology"
	@Test
	public void tc31_buyFromProductsPage() {
		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");

		assertEquals(pp.isInCart(), true);

		pp.removeItemFromCart("95745");
	}

	// Buy from product details page
	// If the user adds an item to cart from the product's details page it will
	// appear in his cart
	// Item number - 95745 "Gummiology"
	@Test
	public void tc32_buyFromProductDetailsPage() {
		ProductDetailsPage pd = new ProductDetailsPage(driver);
		pd.addItemToCartFromProductDetailsPage("95745");

		ProductsPage pp = new ProductsPage(driver);
		assertEquals(pp.isInCart(), true);

		pp.removeItemFromCart("95745");
	}

	// Remove item from cart
	// If the user removes an items from his cart then it won't appear in the cart
	// Item number - 95745 "Gummiology"
	@Test
	public void tc33_removeItemFromCart() {
		ProductsPage pp = new ProductsPage(driver);
		pp.addItemToCartFromProductsPage("95745");
		pp.removeItemFromCart("95745");

		CartPage cp = new CartPage(driver);
		assertEquals(cp.isCartEmpty(), true);
	}

	// Check cart subtotal
	// If the user adds few items to his cart the subtotal of the cart will show the
	// sum of the cost of all the items
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
