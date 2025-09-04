package Job_Module;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import QABootcamp_Maven.OrangeHRM.TestBase;
public class EmploymentStatusTest extends TestBase {
	// LogIn login;
    EmploymentStatusPage empStatusPage;

    @BeforeClass
    public void setupClass() {
       // login = new LogIn(driver);
        empStatusPage = new EmploymentStatusPage(driver);
     //   login.Login("Admin", "admin123");
     //   Assert.assertTrue(login.isLogedIn(), "Admin should be logged in");
        empStatusPage.goToEmploymentStatus();
    }

    @Test(priority = 1, description= "TC-01 Verify that Admin can successfully add a new employment status")
    public void testAddEmploymentStatus() {
        empStatusPage.addEmploymentStatus("Contractual");
        Assert.assertTrue(empStatusPage.isSuccessMessageDisplayed(), "Step 1: Contractual added");
        empStatusPage.refreshPage();
       
    }

    @Test(priority = 2, description= "TC-02 Verify That Employment Status field cannot be left blank during creation")
    public void testEmploymentStatusFieldCannotBeBlank() {
        empStatusPage.addEmploymentStatus("");
        Assert.assertTrue(empStatusPage.isRequiredErrorDisplayed(), "Step 2: Required error displayed");
        empStatusPage.refreshPage();
        
    }

    @Test(priority = 3, description= "TC-03 Verify that admin can edit an existing employment status")
    public void testEditEmploymentStatus() {
        empStatusPage.editEmploymentStatus("Freelance", "Freelance 2");
        Assert.assertTrue(empStatusPage.isSuccessMessageDisplayed(), "Step 3: Edited to Freelance 2");
        empStatusPage.refreshPage();
        
    }

    @Test(priority = 4, description= "TC-04 Verify that a single employment status can be deleted")
    public void testDeleteSingleEmploymentStatus()  {
        empStatusPage.deleteEmploymentStatus("Full-Time Contract");
        Assert.assertTrue(empStatusPage.isDeleteMessageDisplayed(), "Step 4: Full-Time Contract is removed");
        empStatusPage.refreshPage();
       
    }

    @Test(priority = 5, description= "TC-05 Verify that multiple employment status can be deleted at the same time")
    public void testDeleteMultipleEmploymentStatuses() {
        empStatusPage.selectEmploymentStatusCheckbox("Part-Time Internship");
        empStatusPage.selectEmploymentStatusCheckbox("Part-Time Contract");
        empStatusPage.deleteSelectedStatuses();
        Assert.assertTrue(empStatusPage.isDeleteMessageDisplayed(), "Step 5: Selected statuses are deleted as expected");
        empStatusPage.refreshPage();
 
    
    }

    @Test(priority = 6, description= "TC-06 Verify that the system does not allow adding duplicate employment status")
    public void testDuplicateEmploymentStatusNotAllowed()  {
    	
    	empStatusPage.addEmploymentStatus("Full Time Probation");
        empStatusPage.addEmploymentStatus("Full Time Probation"); 
        Assert.assertTrue(empStatusPage.isAlreadyExistsErrorDisplayed(), "Step 6: Error message 'Already exists' is displayed");
    }
    
    @AfterMethod
    public void waitBetweenTests() throws InterruptedException {
        Thread.sleep(5000);
    }
}

  
