package Job_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class JobTitlesTest extends TestBase {
    JobTitles jobTitles;
    

    private final String title         = "Senior QA Engineer12435";
    private final String description   = "Owns test strategy and automation";
    private final String specFilePath  = "C:\\hassan\\test.txt"; 
    private final String note          = "Created via Selenium test";

    @BeforeMethod
    public void setUpPage() {
        jobTitles = new JobTitles(driver);
       

    }

    @Test(priority = 0, description = "TC-13 Add valid job title")
    public void addJobTitle() {
    
        
        

        boolean saved = jobTitles.addJobTitleFlow(title, description, specFilePath, note);

        Assert.assertTrue(saved, "Job Title was not saved (Success toast did not appear).");
        
        
        Assert.assertTrue(
        	    jobTitles.checkJobTitleAndDescription(title, description),
        	    "Job Title & Description not found in the table!");
    }
    
    @Test(priority = 3, description = "TC-15 Verify that the delete works correctly")
	public void deleteJob() {
    	jobTitles.deleteSelectedUser(1);
		Assert.assertTrue(jobTitles.waitForSuccessToast(), "Delete isn't work");

	}

	@Test(priority = 4, description = "TC-16 Verify that the delete All useres Selected works correctly")
	public void deleteAllJobs() {
		jobTitles.addJobTitleFlow(title, description, specFilePath, note);
		jobTitles.deleteAllUsers();
		Assert.assertTrue(jobTitles.waitForSuccessToast(), "Delete isn't work");

	}
	@Test(priority = 2, description = "TC-17 Edit Job Title works correctly")
	public void editJobTitle() {
	    String editedTitle       = "Edited Job Title11";
	    String editedDescription = "updated job description";
	    String editedNote        = "Updated note1";
	    String editedSpecFile    = null; 

	    jobTitles.editJobTitle(
	        1,                
	        editedTitle,
	        editedDescription,
	        editedSpecFile,
	        editedNote
	    );

	    Assert.assertTrue(
	        jobTitles.waitForSuccessToast(),
	        "Success toast did not appear after editing the Job Title"
	    );
	}
	
	@Test(priority = 1, description = "TC-14 Verify required fields")
	public void checkRequiredFields() {
		jobTitles.clickAddButton();
		jobTitles.typeJobTitle(title);
		jobTitles.clickSaveButton();

		Assert.assertTrue(jobTitles.isAlreadyExistDisplayed(), "Required isn't display");
		
		jobTitles.clickCancel();

	}

}
