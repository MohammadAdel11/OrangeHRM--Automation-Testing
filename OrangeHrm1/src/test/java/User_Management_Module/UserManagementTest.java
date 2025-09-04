package User_Management_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class UserManagementTest extends TestBase {
	UserManagement userManagement;

	
	private final String newUsername = "jhfdsjfhdjfjhk";
	private final String newPassword = "OrangeHRM_Usr$99";
	private final String newRole = "Admin";
	private final String newStatus = "Enabled";
	private final String empPrefix = "a";

	@BeforeMethod
	public void setUpPage() {
		userManagement = new UserManagement(driver);
	}

	@Test(priority = 2, description = "TC-05 Verify search works correctly")
	public void searchUser() throws InterruptedException {
		userManagement.searchByUsername(newUsername);
		Assert.assertTrue(userManagement.checkSearchResult(newUsername, newRole, newStatus),
				"The system should search for user correctly");
	}

	@Test(priority = 1, description = "TC-06 Create user (admin/ess) with valid data")
	public void addUser() throws InterruptedException {
		userManagement.clickAdminBtn();
		userManagement.clickAdd();
		userManagement.addUser(newRole, empPrefix, newStatus, newUsername, newPassword, newPassword);

		Assert.assertTrue(userManagement.waitForSuccessToast(), "Success toast did not appear after adding a user");

		Assert.assertTrue(userManagement.checkDataEntered(newUsername, newRole, newStatus),
				"The system should add the user correctly (data not found in the table)");
	}

	@Test(priority = 3, description = "TC-07 Create user (admin/ess) with existing username")
	public void checkUniqeUserName() {
		userManagement.clickAdd();
		userManagement.enterUserName(newUsername);
		Assert.assertTrue(userManagement.isAlreadyExistDisplayed(), "Alredy Exist isn't Display");

	}

	@Test(priority = 4, description = "TC-08 Verify password and confirm password match")
	public void checkPassword() {
		userManagement.enterPass(newPassword);
		userManagement.enterConfPass("hdgfjhssgfjh");
		Assert.assertTrue(userManagement.isPassConfirmed(), "Passwords do not match isn't display");

	}

	@Test(priority = 5, description = "TC-09 Verify required fields")
	public void checkRequiredFields() {
		userManagement.clickSave();

		Assert.assertTrue(userManagement.isRequired(), "Required isn't display");

	}

	@Test(priority = 6, description = "TC-10 Verify that the delete works correctly")
	public void deleteUser() {
		userManagement.clickCancel();
		userManagement.deleteSelectedUser(3);
		Assert.assertTrue(userManagement.waitForSuccessToast(), "Delete isn't work");

	}

	@Test(priority = 8, description = "TC-11 Verify that the delete All useres Selected works correctly")
	public void deleteAllUser() {
		userManagement.deleteAllUsers();
		Assert.assertTrue(userManagement.waitForSuccessToast(), "Delete isn't work");

	}

	@Test(priority = 7, description = "TC-12 Verify that the edit works correctly")
	public void editUser() throws InterruptedException {
		String editedUsername = "EditedUser31123";
		String editedPassword = "OrangeHRM_New$795";
		String editedRole = "ESS";
		String editedStatus = "Disabled";
		String editedEmpName = "u";

		userManagement.editSelectedUser(1, editedRole, editedEmpName, editedStatus, editedUsername, true,
				editedPassword);

		Assert.assertTrue(userManagement.waitForSuccessToast(), "Success toast did not appear after editing a user");


	}

}
