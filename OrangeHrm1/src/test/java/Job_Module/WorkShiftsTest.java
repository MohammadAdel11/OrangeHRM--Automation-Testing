package Job_Module;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.LogIn;
import QABootcamp_Maven.OrangeHRM.TestBase;

public class WorkShiftsTest extends TestBase {
	// LogIn login;
    WorkShiftsPage shiftPage;

    @BeforeClass
    public void setupClass() {
     //   login = new LogIn(driver);
        shiftPage = new WorkShiftsPage(driver);
   //     login.Login("Admin", "admin123");
   //     Assert.assertTrue(login.isLogedIn(), "Admin should be logged in");
        shiftPage.openWorkShiftsDirect();
    }

    @AfterMethod
    public void smallPause() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test(priority = 1,description ="TC-14 Verify Admin can add a new Work Shift with valid details")
    public void addValidWorkShift() {
        shiftPage.clickAddButton();
        shiftPage.addWorkShift("Day Shift", "09:00 AM", "06:00 PM");
        Assert.assertTrue(shiftPage.isSuccessToastVisible(), "Expected success toast after saving Day Shift");
        Assert.assertTrue(shiftPage.isRowVisible("Day Shift"), "Day Shift row should exist");
    }

    @Test(priority = 2,description ="TC-15 Verify Admin can add an overnight Work Shift ")
    public void addOvernightWorkShift_bugShowsError() {
        shiftPage.clickAddButton();
        shiftPage.addWorkShift("Overnight Shift", "10:00 PM", "06:00 AM");
        Assert.assertTrue(shiftPage.isErrorToastVisible(),
                "Expected error toast: 'To time should be after from time'");
    }

    @Test(priority = 3,description ="TC-16 Verify Admin cannot add Work Shift with missing mandatory fields")
    public void cannotAddWithMissingMandatoryFields() {
        shiftPage.clickAddButton();
        shiftPage.addWorkShift("", "11:00 AM", "07:00 PM"); 
        Assert.assertTrue(shiftPage.isErrorToastVisible(), "Expected 'Required' error");
    }

    @Test(priority = 4,description ="TC-17 Verify Admin cannot add Work Shift with duplicate name")
    public void cannotAddDuplicateName() {
        shiftPage.clickAddButton();
        shiftPage.addWorkShift("Day Shift", "07:00 AM", "03:00 PM");
        Assert.assertTrue(shiftPage.isErrorToastVisible(), "Expected 'Already exists' error for duplicate name");
    }

    @Test(priority = 5,description ="TC-18 Verify Admin can edit an existing Work Shift")
    public void editDayShiftToDayShift2() {
        shiftPage.editWorkShift("Day Shift 2", "11:00 AM", "05:00 PM");
        Assert.assertTrue(shiftPage.isSuccessToastVisible(), "Expected success toast after editing");
        
    }
   
    @Test(priority = 6,description ="TC-19 Verify Admin can delete an existing Work Shift")
    public void deleteSingleWorkShift() {
        
        shiftPage.deleteWorkShift("General");
        Assert.assertTrue(shiftPage.isSuccessToastVisible(), "Expected success toast after single delete");
    }

    @Test(priority = 7,description ="TC-20 Verify Admin can delete multiple Work Shifts at the same time")
    public void deleteMultipleWorkShifts() {
        
        shiftPage.selectWorkShiftCheckbox("Day Shift 2");
        shiftPage.selectWorkShiftCheckbox("Twilight");
        shiftPage.deleteSelected();
        Assert.assertTrue(shiftPage.isSuccessToastVisible(), "Expected success toast after multi delete");
    }
    
    @AfterMethod
    public void cleanup() throws InterruptedException {
        shiftPage.closeAddDialogIfOpen();
        Thread.sleep(3000);
        }


}
	 
	