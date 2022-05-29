
package acme.testing.any.toolkitItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitItemByToolkitTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit-item/list-by-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String units) {

		super.clickOnMenu("Anonymous", "List All Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		super.clickOnButton("Components and Tools");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 3, units);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.code", code);
		super.checkInputBoxHasValue("item.technology", technology);
		super.checkInputBoxHasValue("item.description", description);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("item.link", link);
		super.checkInputBoxHasValue("units", units);
		
	}
}
