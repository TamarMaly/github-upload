package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	@FindBy(css = ".rtl-17rm9tq")
	private WebElement itemPrice;
	@FindBy(css = "#summary > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)")
	private WebElement sum;
	@FindBy(css = ".iherb-header-cart")
	private WebElement cartTag;
	@FindBy(css = ".rtl-1u24u9e")
	private WebElement emptyCartTag;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	// Remove items from the cart

	public void removeItemFromCart(String itemNumber) {
		WebElement removeItemTag = driver
				.findElement(By.cssSelector("[data-qa-element='btn-item-remove-" + itemNumber + "']"));
		click(removeItemTag);
		sleep(1000);
	}

	// Calculate total of the cart

	public double calculateItemsInCart() {
		List<WebElement> allPrices = driver.findElements(By.cssSelector(".rtl-17rm9tq"));
		double sum = 0;
		for (WebElement webElement : allPrices) {
			String price = webElement.getText();
			String price1 = price.substring(1);
			double price2 = Double.parseDouble(price1);
			sum += price2;
		}
		return sum;
	}

	public double cartTotal() {
		String total = sum.getText().substring(1);
		return Double.parseDouble(total);
	}

	public boolean isCartEmpty() {
		click(cartTag);
		sleep(500);
		if (emptyCartTag.isDisplayed()) {
			return true;
		} else {
			return false;

		}
	}
}
