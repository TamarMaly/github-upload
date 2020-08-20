package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSettingsPage extends BasePage {

	@FindBy(css = "[data-edit-field = 'name']")
	private WebElement editAccoutNameTag;
	@FindBy(css = "#Name")
	private WebElement accoutNameTag;
	@FindBy(css = "[data-edit-field = 'email']")
	private WebElement editEmailTag;
	@FindBy(css = "#Email")
	private WebElement accoutEmailTag;
	@FindBy(css = "#CurrentPassword")
	private WebElement currentPasswordTag;
	@FindBy(css = "[data-edit-field = 'phone']")
	private WebElement editPhoneTag;
	@FindBy(css = "#PhoneNumber")
	private WebElement accoutPhoneTag;
	@FindBy(css = "[data-edit-field = 'password']")
	private WebElement editPasswordTag;
	@FindBy(css = "#NewPassword")
	private WebElement accoutPasswordTag;
	@FindBy(css = ".btn.btn-block.btn-lg.btn-primary.js-update-info")
	private WebElement submitTag;
	@FindBy(css = "body > div.toast.hide")
	private WebElement passwordSubmitPopUpTag;
	@FindBy(css = ".account-info-action-container > div:nth-child(2)")
	private WebElement disabledPhoneSubmitTag;
	@FindBy(css = ".col-xs-12.info-name > p")
	private WebElement accountName;
	@FindBy(css = ".info-email > p")
	private WebElement accoutEmail;
	@FindBy(css = ".col-xs-12.info-phone > p")
	private WebElement accoutPhone;
	@FindBy(css = ".text-label.error")
	private WebElement errorInvalidNameTag;
	@FindBy(css = ".icon.icon-iherblogo")
	private WebElement backToHPTag;

	public AccountSettingsPage(WebDriver driver) {
		super(driver);
	}

	//
	public void enterAccountSettingsPage() {
		HomePage hp = new HomePage(driver);
		hp.moveToAccountSettingsPage();
	}

	// TC15 TC16
	public void editAccountNameInfo(String name) {
		click(editAccoutNameTag);
		sleep(500);
		fillText(accoutNameTag, name);
		sleep(1500);
		click(submitTag);
		sleep(1500);
	}

	// TC17 TC18
	public void editAccountEmail(String email) {
		click(editEmailTag);
		sleep(500);
		fillText(accoutEmailTag, email);
		sleep(1500);
		fillText(currentPasswordTag, "Passcheck1@");
		sleep(1500);
		click(submitTag);
		sleep(1500);
	}

	// TC19
	public void editAccountPhone(String phone) {
		click(editPhoneTag);
		sleep(500);
		fillText(accoutPhoneTag, phone);
		sleep(1500);
		click(disabledPhoneSubmitTag);
		sleep(1500);
	}

	// TC20
	public void editAccountPassword(String Password) {
		click(editPasswordTag);
		sleep(500);
		fillText(accoutPasswordTag, Password);
		sleep(1500);
		if (submitTag.isDisplayed()) {
			click(submitTag);
			sleep(500);
		}
	}

	public void backToHomePage() {
		click(backToHPTag);
		sleep(2000);
	}

	// Assert methods
	public String checkCurrentEmail() {
		return getText(accoutEmail);
	}

	public String errorInvalidNameMessage() {

		return getText(errorInvalidNameTag);
	}

	public String accountName() {
		return getText(accountName);
	}

	public String accountPhone() {
		return getText(accoutPhone);
	}

	public boolean isEmailValid() {
		return editEmailTag.isDisplayed();
	}

	public boolean isPhoneValid() {
		return editPhoneTag.isDisplayed();
	}

	public String isPasswordChanged() {
		return getText(passwordSubmitPopUpTag);
	}

	public boolean isPasswordValid() {
		return editPasswordTag.isDisplayed();
	}
}
