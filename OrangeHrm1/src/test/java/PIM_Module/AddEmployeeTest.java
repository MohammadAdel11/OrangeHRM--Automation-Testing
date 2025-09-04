package PIM_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class AddEmployeeTest extends TestBase {

	EmployeePage emp;

	@BeforeClass
	public void setUpPages() {
		emp = new EmployeePage(driver);
		// Login once before running all tests
		//Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login failed!");
	}

	@Test(priority = 1)
	public void validAddEmployee() {
		emp.openAddEmployeeForm();
		emp.addEmployee("Lina", "Shaqoura");
		emp.saveEmployee();
		Assert.assertEquals(emp.getPersonalDetailsTitle(), "Personal Details");
	}

	@Test(priority = 2)
	public void invalidAddEmployeeWithBlankFields() {
		emp.openAddEmployeeForm();
		emp.addEmployee("", "");
		emp.saveEmployee();
		Assert.assertTrue(emp.getErrorMessage("Required").isDisplayed());
	}

	@Test(priority = 3)
	public void invalidAddEmployeeWithTooLargeData() {
		emp.openAddEmployeeForm();
		emp.addEmployee("A".repeat(100), "B".repeat(100));
		emp.saveEmployee();
		Assert.assertTrue(emp.getErrorMessage("Should not exceed 30 characters").isDisplayed());
	}

	@Test(priority = 4)
	public void cancelAddEmployeeTest() {
		// Navigate to Add Employee
		emp.openAddEmployeeForm();

		emp.addEmployee("Lina", "Shaqoura");
		// Click Cancel
		emp.cancelAddEmployee();

		// Verify that Admin is redirected back to Employee List
		Assert.assertTrue(emp.isEmployeeListPageDisplayed(),
				"Admin should be redirected to Employee List page after clicking Cancel");
	}

}
