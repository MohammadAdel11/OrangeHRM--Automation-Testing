package Organization_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationLocationEditTest extends TestBase {
	private OrganizationLocationsEdit Edit;
	@BeforeClass
	public void setUpPage() {
		Edit = new OrganizationLocationsEdit(driver);
	}
	@Test(priority = 3, description = "Verify Update valid Location")
	public void ValidEditTest() {
		Edit.OpenLocationForm();
		Edit.clickEditButton(0);
		Edit.EditName("Egypt");
		Edit.EditCountry("Egypt");
		Edit.ClickSave();
		Assert.assertTrue(Edit.getSuccessMessage().contains("Successfully Updated"));

	}
	@Test(priority = 1, description = "Verify Edit Location with blank required fields")
	public void inValidEditTest() {
		Edit.OpenLocationForm();
		Edit.clickEditButton(0);
		Edit.EditName("");
		Edit.EditCountry("");
		Edit.ClickSave();
		Assert.assertTrue(Edit.isErrorMessageDisplayed());

	}
	
	@Test(priority = 2, description = "Verify Cancel Button is clickable")
	public void CancelTest() {
		Edit.OpenLocationForm();
		Edit.clickEditButton(0);
		Edit.ClickSave();
		Assert.assertTrue(Edit.isCancelButtonEnabled(), "Cancel button is not enabled");

	}
	



}
