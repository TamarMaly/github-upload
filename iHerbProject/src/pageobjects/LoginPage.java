package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(css = "#username_input")
	private WebElement userNameTag;
	@FindBy(css = "#Password")
	private WebElement passwordTag;
	@FindBy(css = ".btn.btn-full.btn-primary.form-submit")
	private WebElement signInTag;
	@FindBy(css = "#username_container > label.form-error.form-error-required")
	private WebElement errorMissingUserNameMessage;
	@FindBy(css = ".form-row > .form-error.form-error-required")
	private WebElement errorMissingPasswordMessage;
	@FindBy(css = ".error-message > span > ul > li")
	private WebElement errorWrongUserNPasswordMessage;
	@FindBy(css = ".icon.icon-iherblogo")
	private WebElement backToHPTag;

	Actions actions = new Actions(driver);

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Insert user name and password info to login
	public void insertLoginInfo(String user, String password) {
		fillText(userNameTag, user);
		fillText(passwordTag, password);
	}

	// Test cases methods

	// TC01 TC02 TC04 TC05 TC10 TC11 loginAndClickSignIn
	public void loginAndClickSignIn(String userName, String password) {
		enterLoginPage();
		insertLoginInfo(userName, password);
		click(signInTag);
		sleep(3000);
	}

	// TC03 TC06 TC12 TC13 loginWithoutSignIn
	public void loginWithoutSignIn(String userName, String password) {
		enterLoginPage();
		insertLoginInfo(userName, password);
	}

	// TC07 TC08 loginWithMissingInfo
	public void loginWithMissingInfo(String userName, String password) {
		enterLoginPage();
		insertLoginInfo(userName, password);
		click(passwordTag);
		click(userNameTag);
	}

	// TC09 noInfoLogin
	public void noInfoLogin() {
		enterLoginPage();
		click(userNameTag);
		click(passwordTag);
		click(userNameTag);
	}

	// Move to other pages

	public void enterLoginPage() {
		HomePage hp = new HomePage(driver);
		hp.moveToLoginPage();
	}

	public void backToHomePage() {
		click(backToHPTag);
		sleep(1500);
	}

	// Asserts methods

	// TC02 TC04 TC05 TC11
	public String errorUserNamePasswordMessage() {

		return getText(errorWrongUserNPasswordMessage);
	}

	// TC03 TC06 TC09 TC12 TC13
	public String missingUserNameMessage() {

		return getText(errorMissingUserNameMessage);
	}

	// TC07 TC08 TC09
	public String missingPasswordMessage() {

		return getText(errorMissingPasswordMessage);
	}

}
