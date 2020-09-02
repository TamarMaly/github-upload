package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.AddressBookPage;

public class AddressBookTest extends BaseTest {

	// Add valid address
	// If the user adds an address to the address book and it's valid then it will
	// be added the the address book
	@Test
	public void tc22_addValidAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		String actual = ab.checkCurrentAddress();
		assertEquals(actual, "Herzel 48");

		ab.deleteAddress();
	}

	// Remove address
	// If the user adds an address to the address book and it's invalid then it
	// won't be added the the address book
	@Test
	public void tc23_deleteAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.deleteAddress();

		boolean actual = ab.isThereAddress();
		assertEquals(actual, false);

	}

	// Edit valid address
	// If the user edits his address in the address book and it's valid then it will
	// be changed in the address book
	@Test
	public void tc24_editAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.editAddressDetails("Habarzel 10");

		String actual = ab.checkCurrentAddress();
		assertEquals(actual, "Habarzel 10");

		ab.deleteAddress();
	}

	// Edit invalid address
	// If the user edits his address in the address book and it's invalid then it
	// won't be changed in the address book
	@Test
	public void tc25_editInvalidAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.editInvalidAddress("");

		String actual = ab.getErrorAddressMSG();
		assertEquals(actual, "יש להזין פרטים בשורת כתובת 1.");

		ab.cancelEditNDeleteAddress();
	}

	// Edit invalid postal code
	// If the user edits his postal code in the address book and it's invalid then
	// it won't be changed in the address book
	@Test
	public void tc26_editInvalidPC() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.editInvalidPC("");

		String actual = ab.getErrorPCMSG();
		assertEquals(actual, "מיקוד לא חוקי.");

		ab.cancelEditNDeleteAddress();
	}
}
