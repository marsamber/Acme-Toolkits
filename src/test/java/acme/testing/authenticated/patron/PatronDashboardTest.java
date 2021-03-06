package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronDashboardTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/dashboard/patron-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(Integer patronagesProposed, Integer patronagesAccepted, Integer patronagesDenied) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Dashboard");
	
		super.checkFormExists();
		super.checkNotErrorsExist();
		
		
		super.signOut();	
	}
}
