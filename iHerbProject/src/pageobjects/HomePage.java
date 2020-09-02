package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	@FindBy(css = "#iherb-account > div > span > button")
	private WebElement loginTag;
	@FindBy(css = ".iherb-header-signed-in.my-account-button")
	private WebElement myAccountMenu;
	@FindBy(css = ".iherb-header-signed-in > .btn.btn-primary-universal.btn-block")
	private WebElement signOutTag;
	@FindBy(css = ".username > span")
	private WebElement loggedInEmail;
	@FindBy(css = ".my-account-links > li:nth-child(1) > a")
	private WebElement myAccountTag;
	@FindBy(css = ".accountsettings")
	private WebElement accountSettingsTag;
	@FindBy(css = ".addressbook")
	private WebElement adressBookTag;
	@FindBy(css = "#txtSearch")
	private WebElement searchBarTag;
	@FindBy(css = "#searchBtn")
	private WebElement searchButtonTag;
	@FindBy(css = ".search-v2 > li")
	private WebElement allSearchResults;
	@FindBy(css = ".suggested-products > div")
	private WebElement searchSuggestedProducts;
	@FindBy(css = ".icon.welcome-mat-module-close")
	private WebElement exitAd;

	Actions actions = new Actions(driver);

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Login the web site
	public void login() {
		LoginPage lp = new LoginPage(driver);
		lp.loginAndClickSignIn("zsou-dor@needrabit.tk", "Passcheck1@");
	}

	public void exitAd() {
		if (exitAd.isDisplayed()) {
			sleep(1000);
			click(exitAd);
		}
	}

	// Move to other pages
	public void moveToLoginPage() {
		exitAd();
		click(loginTag);
		sleep(1000);
	}

	public void moveToAccountSettingsPage() {
		if (isLogout()) {
			login();
		}
		actions.moveToElement(myAccountMenu).build().perform();
		sleep(1500);
		click(myAccountTag);
		sleep(2000);
		click(accountSettingsTag);
		sleep(2000);
	}

	public void moveToAddressBookPage() {
		if (isLogout()) {
			login();
		}
		actions.moveToElement(myAccountMenu).build().perform();
		sleep(1500);
		click(myAccountTag);
		sleep(2000);
		click(adressBookTag);
		sleep(2000);
	}

	// Search
	public void searchProductsPage(String searchWord) {
		exitAd();
		fillText(searchBarTag, searchWord);
		click(searchButtonTag);
		sleep(2000);
	}

	public void searchDropDownMenu(String searchWord) {
		click(searchBarTag);
		sleep(500);
		fillText(searchBarTag, searchWord);
		sleep(2500);
	}

	public List<WebElement> searchResultsDropDownMenu() {
		List<WebElement> searchList = driver.findElements(By.cssSelector(".search-v2 > li > div > span"));
		List<WebElement> searchList2 = driver.findElements(By.cssSelector(".suggested-products > div > div > div > a"));
		for (WebElement webElement : searchList2) {
			searchList.add(webElement);
		}
		return searchList;
	}

	public boolean searchedItemsInDDMenu(String searchWord) {
		List<WebElement> itemsList = searchResultsDropDownMenu();
		for (WebElement webElement : itemsList) {
			if (!webElement.getText().toLowerCase().contains(searchWord)) {
				return false;
			}
		}
		return true;
	}

	// TC14
	public void logout() {
		actions.moveToElement(myAccountMenu).build().perform();
		sleep(1500);
		click(signOutTag);
		sleep(2000);
	}

	// Asserts methods

	// TC01 TC10
	public String whoIsLoggedIn() {

		return getText(loggedInEmail);
	}

	// TC14
	public boolean isLogout() {
		return (loginTag.isDisplayed());
	}

	public String currentURL() {
		return driver.getCurrentUrl();
	}

}
