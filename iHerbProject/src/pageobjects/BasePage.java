package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	public WebDriver driver;
	JavascriptExecutor js; 

	public BasePage(WebDriver driver) {
		this.driver = driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void fillText(WebElement el, String text) {
		// Highlight action
		js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid yellow;');", el);
		sleep(200);
		
		el.clear();
		el.sendKeys(text);
	}

	public void click(WebElement el) {
		// Highlight action
		js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid yellow;');", el);
		sleep(200);
		
		el.click();
	}

	public String getText(WebElement el) {
		// Highlight action
		js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid yellow;');", el);
		sleep(200);
				
		return el.getText();
	}

	public void fillAlert(String name) {
		driver.switchTo().alert().sendKeys(name);
		driver.switchTo().alert().accept();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void sleep(long mili) {
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}