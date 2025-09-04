package Job_Module;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobCategoriesPage {
	 WebDriver driver;
	 WebDriverWait wait;

	    public JobCategoriesPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    }

	    // Locators
	    private By adminMenu = By.xpath("//span[text()='Admin']");
	    private By jobMenu = By.xpath("//span[text()='Job ']");
	    private By jobCategoriesMenu = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a");
	    private By addBtn = By.xpath("//button[normalize-space()='Add']");
	    private By nameField = By.xpath("//label[text()='Name']/../following-sibling::div/input");
	    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	    private By requiredError = By.xpath("//span[text()='Required']");
	    private By alreadyExistsError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span");
	    private By successToast = By.xpath("//p[contains(@class,'oxd-text--toast-title')]");

	    // Navigate to Job Categories
	    public void goToJobCategories() {
	        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(jobMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(jobCategoriesMenu)).click();
	    }
	    public void clearNameField() {
	    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        input.clear();
	    }

	    // Add Job Category
	    public void addJobCategory(String name) {
	        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
	        if (name != null) {
	            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	            nameInput.clear();
	            nameInput.sendKeys(name);
	        }
	        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
	    }
	    

	    // Edit Job Category
	    public void editJobCategory(String oldName, String newName) {
	        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='" + oldName + "']/../..//button[i[contains(@class,'bi-pencil-fill')]]")));
	        editIcon.click();

	        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	        nameInput.sendKeys(Keys.CONTROL + "a");
	        nameInput.sendKeys(Keys.DELETE);
	        nameInput.sendKeys(newName);

	        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
	    }


	    // Delete one Job Category
	    public void deleteJobCategory(String name) {
	        WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='" + name + "']/../..//button[i[contains(@class,'bi-trash')]]")));
	        deleteIcon.click();
	        confirmDelete();
	    }

	    // Select checkbox for multiple delete
	    public void selectJobCategoryCheckbox(String name) {
	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='" + name + "']/../..//div[@class='oxd-checkbox-wrapper']")));
	        checkbox.click();
	    }

	    // Delete selected
	    public void deleteSelectedCategories() {
	        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Delete Selected']")));
	        deleteBtn.click();
	        confirmDelete();
	    }

	    // Confirm delete 
	    private void confirmDelete() {
	        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Yes, Delete']")));
	        confirmBtn.click();
	    }

	    // Verifications
	    public boolean isSuccessMessageDisplayed() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean isRequiredErrorDisplayed() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredError)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean isAlreadyExistsErrorDisplayed() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(alreadyExistsError)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	     
		    
	    }
	    
	


