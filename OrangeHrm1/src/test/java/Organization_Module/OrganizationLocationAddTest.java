package Organization_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationLocationAddTest extends TestBase {
	private OrganizationLocations Location;

	@BeforeClass
	public void setUpPage() {
		Location = new OrganizationLocations(driver);
	}


	@Test(priority = 2, description = "Verify Add valid Location")
	public void ValidAddTest() {
		Location.OpenLocationForm();
		Location.AddName("OntarioOttawa");
		Location.AddCountry("Canada");
		Location.SaveAdd();
		Assert.assertTrue(Location.getSuccessMessage().contains("Successfully Saved"));

	}

	@Test(priority = 3, description = "Verify Add duplicate Location")
	public void DuplicateAddTest() {
		Location.OpenLocationForm();
		Location.AddName("OntarioOttawa");
		Location.AddCountry("Canada");
		Location.SaveAdd();
		Assert.assertTrue(Location.isExistDisplayed());

	}

	@Test(priority = 1, description = "Verify Add invalid Location blank required fields")
	public void inValidAddTest() {
		Location.OpenLocationForm();
		Location.AddName("");
		Location.AddCountry("");
		Location.SaveAdd();
		Assert.assertTrue(Location.isErrorMessageDisplayed());

	}
	



}
