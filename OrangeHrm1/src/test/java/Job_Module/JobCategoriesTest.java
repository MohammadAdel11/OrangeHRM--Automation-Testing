package Job_Module;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import QABootcamp_Maven.OrangeHRM.LogIn;
import QABootcamp_Maven.OrangeHRM.TestBase;

public class JobCategoriesTest extends TestBase {
	// LogIn login;
	    JobCategoriesPage jobCategoriesPage;

	    @BeforeClass
	    public void setupClass() {
	      //  login = new LogIn(driver);
	        jobCategoriesPage = new JobCategoriesPage(driver);
	     //   login.Login("Admin", "admin123");
	     //   Assert.assertTrue(login.isLogedIn(), "Admin should be logged in");
	        jobCategoriesPage.goToJobCategories();
	    }

	    @Test(priority = 1, description= "TC-07 Verify that a Job category can be added successfully with valid input")
	    public void testAddJobCategoryValidInput() {
	        jobCategoriesPage.addJobCategory("Skillful");
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed(), "Step 1: Job Category added successfully");
	        
	    }

	    @Test(priority = 2, description= "TC-08 Verify system behavior when trying to add a Job Category without entering any data")
	    public void testJobCategoryFieldCannotBeBlank() {
	        jobCategoriesPage.addJobCategory("");
	        Assert.assertTrue(jobCategoriesPage.isRequiredErrorDisplayed(), "Step 2: Required error displayed");
	        
	        
	    }

	    @Test(priority = 3, description= "TC-09 Verify the system does not allow adding duplicate job categories")
	    public void testDuplicateJobCategoryNotAllowed() {
	        jobCategoriesPage.addJobCategory("Professionals");
	        Assert.assertTrue(jobCategoriesPage.isAlreadyExistsErrorDisplayed(), "Step 3: Duplicate error displayed");
	        
	    }

	    @Test(priority = 4, description= "TC-10 Verify an admin can edit an existing job category")
	    public void testEditJobCategory() {
	        jobCategoriesPage.editJobCategory("Professionals", "Professionals 2");
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed(), "Step 4: Job Category edited successfully");
	    }

	    @Test(priority = 5, description= "TC-11 Verify a job category can be deleted individually")
	    public void testDeleteSingleJobCategory() {
	        jobCategoriesPage.deleteJobCategory("Sales Workers");
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed(), "Step 5: Single Job Category deleted");
	    }

	    @Test(priority = 6, description= "TC-12 Verify multiple job categories can be deleted Simultaneously")
	    public void testDeleteMultipleJobCategories() {
	        jobCategoriesPage.selectJobCategoryCheckbox("Operatives");
	        jobCategoriesPage.selectJobCategoryCheckbox("Craft Workers");
	        jobCategoriesPage.deleteSelectedCategories();
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed(), "Step 6: Multiple Job Categories deleted");
	    }

	    @Test(priority = 7, description= "TC-13 Verify if special characters are allowed or restricted in job category name")
	    public void testSpecialCharactersInJobCategory() {
	        jobCategoriesPage.addJobCategory("Skillful@2025");
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed(),
	                "Step 7: Verify behavior with special characters");
	    }
	
	    @AfterMethod
	    public void resetPage() throws InterruptedException {
	        driver.navigate().refresh();
	        Thread.sleep(2000); 
	        jobCategoriesPage.goToJobCategories(); 
	    }


}
