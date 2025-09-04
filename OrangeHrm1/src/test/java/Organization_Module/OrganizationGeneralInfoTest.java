package Organization_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationGeneralInfoTest extends TestBase {
	private OrganizationGeneralInfo Organization;

	@BeforeClass
	public void setUpPage() {
		Organization = new OrganizationGeneralInfo(driver);
		
	}



	@Test(priority = 4, description = "Verify edit organizationName")
	public void OrganizationNameTest() {
		Organization.openGeneralInformationForm();
		Organization.EditOrganizatioName("AXSOS2");
		Organization.SaveEdit();
		Assert.assertTrue(Organization.getSuccessMessage().contains("Successfully Updated"));

	}
	@Test(priority = 3, description = "Verify invalid phone Number")
	public void InvalidPhoneNumberTest() {
		Organization.openGeneralInformationForm();
		Organization.EditPhone("phone");
		Organization.SaveEdit();
		Assert.assertTrue(Organization.isinvalidPhoneFormat());

	}
	
	@Test(priority = 2, description = "Verify invalidEmail")
	public void InvalidEmailTest() {
		Organization.openGeneralInformationForm();
		Organization.EditEmail("test222");
		Organization.SaveEdit();
		Assert.assertTrue(Organization.isinvalidFormatDisplayed());

	}
	@Test(priority = 1, description = "Verify admin can edit general information by leave required field empty")
	public void InvalidBlankEditTest() {
		Organization.openGeneralInformationForm();
		Organization.EditOrganizatioName("");
		Organization.SaveEdit();
		Assert.assertTrue(Organization.isErrorMessageDisplayed());

	}
	


}
