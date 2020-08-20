package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

	@FindBy(css = ".product-cell-container col-xs-12.col-sm-12 col-md-8 col-lg-6")
	private WebElement allSearchResultsNames;
	@FindBy(css = ".no-results-found-heading")
	private WebElement noResultsMAG;
	@FindBy(css = ".go-to-checkout.addToCartBtn.btn.btn-primary")
	private WebElement cartPopupTag;
	@FindBy(css = ".icon-exit.close")
	private WebElement closePopupTag;

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	Actions actions = new Actions(driver);

	// Search

	public List<WebElement> listOfItems() {
		List<WebElement> itemsList = driver
				.findElements(By.cssSelector(".product-cell-container col-xs-12.col-sm-12 col-md-8 col-lg-6"));
		return itemsList;
	}

	public boolean checkSearchedItems(String searchWord) {
		List<WebElement> itemsList = listOfItems();
		for (WebElement webElement : itemsList) {
			if (!webElement.getText().toLowerCase().contains(searchWord)) {
				return false;
			}
		}
		return true;
	}

	public boolean noresults() {
		if (getText(noResultsMAG).contains("לא הצלחנו למצוא פריטים תואמים עבור")) {
			return true;
		} else {
			return false;
		}
	}

	// add items to cart

	public void addItemToCartFromProductsPage(String itemNumber) {
		if (closePopupTag.isDisplayed()) {
			click(closePopupTag);
		}

		HomePage hp = new HomePage(driver);
		System.out.println(driver.getCurrentUrl());
		hp.searchProductsPage(itemNumber);

		WebElement product = driver.findElement(By.cssSelector("#pid_" + itemNumber));
		WebElement addToCartButton = driver
				.findElement(By.cssSelector("#pid_" + itemNumber + " > div> div > div > div > button"));
		if (!getText(product).toLowerCase().contains("אזל מהמלאי")) {
			actions.moveToElement(addToCartButton).build().perform();
			sleep(500);
			click(addToCartButton);
			sleep(2500);
		}
	}

	// Move to other pages

	public void moveToCart() {
		click(cartPopupTag);
		sleep(1000);
	}

	public void moveToProductDetailsPage(String itemNumber) {
		WebElement product = driver
				.findElement(By.cssSelector("#pid_" + itemNumber + " > div > .absolute-link-wrapper"));
		click(product);
		sleep(500);
	}

	// Asserts methods

	public boolean isInCart() {
		if (closePopupTag.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void removeItemFromCart(String itemNumber) {
		moveToCart();

		CartPage cp = new CartPage(driver);
		cp.removeItemFromCart(itemNumber);
		sleep(500);
	}
}
