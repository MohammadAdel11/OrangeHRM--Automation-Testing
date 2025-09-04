package Organization_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationLOcationDeleteTest extends TestBase {
	private OrganizationalLocationsDelete Delete;
	private OrganizationLocations Location;

	@BeforeClass
	public void setUpPage() {
		Delete = new OrganizationalLocationsDelete(driver);
		Location = new OrganizationLocations(driver);
	}

	@Test(priority = 2, description = "Verify Delete recored")
	public void DeleteTest() {
		Delete.OpenLocationForm();
		Delete.clickDeleteButton(0);
		Delete.ConfirmDelete();
		Assert.assertTrue(Delete.getSuccessMessage().contains("Successfully Delete"));

	}

	@Test(priority = 1, description = "Verify Cancel Button")
	public void CancelTest() {
		Delete.OpenLocationForm();
		Delete.clickDeleteButton(0);
		Assert.assertTrue(Delete.isCancelButtonEnabled());
		Delete.CancelDelete();

	}

	@Test(priority = 3, description = "Verify Delete multiple record")
	////////////////Add////////////
	public void DeleteMultipleTest() {
		Location.OpenLocationForm();
		Location.AddName("Mohammad");
		Location.AddCountry("Canada");
		Location.SaveAdd();
		//////////// delete //////////////
		Delete.OpenLocationForm();
		Delete.SelectRecord(0);
		Delete.SelectRecord(1);
		Delete.DeleteSelected();
		Delete.ConfirmDelete();
		Assert.assertTrue(Delete.getSuccessMessage().contains("Successfully Delete"));
	}
	@Test(priority = 4, description = "Verify Add valid Location")
	public void ValidAddTest() {
		Location.OpenLocationForm();
		Location.AddName("Hanaa");
		Location.AddCountry("Canada");
		Location.SaveAdd();
		Assert.assertTrue(Location.getSuccessMessage().contains("Successfully Saved"));

	}

	@Test(priority = 5, description = "Verify Delete All Records")
	public void DeleteAllRecordsTest() {
		Delete.OpenLocationForm();
		Delete.SelectAllChecBox();
		Delete.DeleteSelected();
		Delete.ConfirmDelete();
		Assert.assertTrue(Delete.getSuccessMessage().contains("Successfully Delete"));
	}
	@Test(priority = 6, description = "Verify Add valid Location")
	public void ValidAddTest2() {
		Location.OpenLocationForm();
		Location.AddName("Hanaa");
		Location.AddCountry("Canada");
		Location.SaveAdd();
		Assert.assertTrue(Location.getSuccessMessage().contains("Successfully Saved"));

	}
	


}
