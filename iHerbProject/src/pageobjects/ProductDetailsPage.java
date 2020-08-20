package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

	@FindBy(css = "[name='AddToCart']")
	private WebElement addToCart;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	// Add items to cart

	public void addItemToCart() {
		click(addToCart);
		sleep(2500);
	}

	public void addItemToCartFromProductDetailsPage(String itemNumber) {
		HomePage hp = new HomePage(driver);
		hp.searchProductsPage(itemNumber);

		ProductsPage pp = new ProductsPage(driver);
		pp.moveToProductDetailsPage(itemNumber);

		addItemToCart();
	}

}
