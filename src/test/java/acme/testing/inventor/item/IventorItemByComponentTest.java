package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class IventorItemByComponentTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-by-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		
		super.signIn("inventor3", "inventor3");

		super.clickOnMenu("Inventor", "List My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
	
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/deleteComponent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentTest(final int recordIndex,final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {

		super.signIn("inventor3", "inventor3");

		super.clickOnMenu("Inventor", "List My Components");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();

		super.clickOnSubmit("Delete");

		super.checkNotErrorsExist();

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/negativeDeleteComponent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeComponentTest(final int recordIndex,final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List My Components");

		super.checkListingExists();
		super.checkNotListingEmpty();

		super.sortListing(1, "desc");

		super.clickOnListingRecord(0);
		super.checkFormExists();

		super.checkNotButtonExists("Delete");

		super.signOut();

	}
}
