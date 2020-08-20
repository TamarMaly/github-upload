package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageobjects.AddressBookPage;

public class AddressBookTest extends BaseTest {

	//
	@Test
	public void tc22_addValidAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		String actual = ab.checkCurrentAddress();
		assertEquals(actual, "Herzel 48");

		ab.deleteAddress();
	}

	//
	@Test
	public void tc23_deleteAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.deleteAddress();

		boolean actual = ab.isThereAddress();
		assertEquals(actual, false);

	}

	//
	@Test
	public void tc24_editAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.editAddressDetails("Habarzel 10");

		String actual = ab.checkCurrentAddress();
		assertEquals(actual, "Habarzel 10");

		ab.deleteAddress();
	}

	//
	@Test
	public void tc25_editInvalidAddress() {
		AddressBookPage ab = new AddressBookPage(driver);
		ab.addNewValidAddress("Valid name", "Herzel 48", "Tel-Aviv", "Israel", "8795611", "0543511427");

		ab.editInvalidAddress("");

		String actual = ab.getErrorAddressMSG();
		assertEquals(actual, "יש להזין פרטים בשורת כתובת 1.");

		ab.cancelEditNDeleteAddress();
	}

	//
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
