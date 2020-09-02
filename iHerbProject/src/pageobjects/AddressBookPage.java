package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends BasePage {

	@FindBy(css = "#btnNewAddress")
	private WebElement addNewAddressTag;
	@FindBy(css = "#FirstName")
	private WebElement nameTag;
	@FindBy(css = "#AddressLine1")
	private WebElement addressTag;
	@FindBy(css = "#City")
	private WebElement cityTag;
	@FindBy(css = "#RegionName")
	private WebElement countryTag;
	@FindBy(css = "#pc")
	private WebElement postalCodeTag;
	@FindBy(css = "#PhoneNumber")
	private WebElement phoneTag;
	@FindBy(css = ".btn.btn-lg.btn-primary")
	private WebElement submitTag;
	@FindBy(css = ".card-action > form > .btn.btn-link")
	private WebElement deleteAddressTag;
	@FindBy(css = "span > input.btn.btn-link")
	private WebElement editAddressTag;
	@FindBy(css = ".form-group.address-line1 > .form-hint.field-name.form-error")
	private WebElement errorAddressMSG;
	@FindBy(css = ".form-group.js-address-form-postal-code > .form-hint.field-name.form-error")
	private WebElement errorPCMSG;
	@FindBy(css = ".btn.btn-lg.btn-secondary")
	private WebElement cancelTag;
	@FindBy(css = ".card-info.address > p:nth-child(2)")
	private WebElement currentAddress;

	Actions actions = new Actions(driver);

	public AddressBookPage(WebDriver driver) {
		super(driver);
	}

	// Move to Address Book page
	public void enterAddressBookPage() {
		HomePage hp = new HomePage(driver);
		hp.moveToAddressBookPage();
	}

	// Used in method addNewValidAddress
	public void addNewAddress(String name, String address, String city, String country, String pc, String phone) {
		click(addNewAddressTag);
		sleep(500);
		fillText(nameTag, name);
		fillText(addressTag, address);
		fillText(cityTag, city);
		fillText(countryTag, country);
		fillText(postalCodeTag, pc);
		fillText(phoneTag, phone);
		click(submitTag);
		sleep(1500);
	}

	// TC22 TC23 TC24
	public void deleteAddress() {
		sleep(500);
		click(deleteAddressTag);
		sleep(1500);
	}

	// TC22 TC23 TC25
	public void addNewValidAddress(String name, String address, String city, String country, String pc, String phone) {
		enterAddressBookPage();
		addNewAddress(name, address, city, country, pc, phone);
	}

	// TC24 TC25
	public void editAddress(String address) {
		sleep(500);
		click(editAddressTag);
		sleep(500);
		fillText(addressTag, address);
		click(submitTag);
		sleep(1500);
	}

	// TC24
	public void editAddressDetails(String address) {
		editAddress(address);
	}

	// TC25
	public void editInvalidAddress(String address) {
		editAddress(address);
		click(cityTag);
	}

	// TC26
	public void editInvalidPC(String pc) {
		sleep(500);
		click(editAddressTag);
		sleep(500);
		fillText(postalCodeTag, pc);
		click(cityTag);
		sleep(500);
	}

	// TC25 TC26
	public void cancelEditNDeleteAddress() {
		click(cancelTag);
		sleep(500);
		deleteAddress();
	}

	// Asserts methods

	// TC22 TC24
	public String checkCurrentAddress() {

		return getText(currentAddress);
	}

	// TC23
	public boolean isThereAddress() {
		List<WebElement> AddressList = driver.findElements(By.cssSelector(".col-xs-8.col-xl-6"));
		if (AddressList.size() == 1) {
			return false;
		} else {
			return true;
		}
	}

	// TC25
	public String getErrorAddressMSG() {

		return getText(errorAddressMSG);
	}

	// TC26
	public String getErrorPCMSG() {

		return getText(errorPCMSG);
	}

}
