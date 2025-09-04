package Job_Module;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmploymentStatusPage {
	 WebDriver driver;
	 WebDriverWait wait;

	    public EmploymentStatusPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    }

	    // Navigation
	    private By adminMenu = By.xpath("//span[text()='Admin']");
	    private By jobMenu = By.xpath("//span[text()='Job ']");
	    private By empStatusMenu = By.xpath("//a[text()='Employment Status']");

	    // Buttons & Fields
	    private By addBtn = By.xpath("//button[normalize-space()='Add']");
	    private By nameField = By.xpath("//label[text()='Name']/following::input[1]");
	    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	    private By successToast = By.xpath("//*[@id='oxd-toaster_1']");
	    private By requiredError = By.xpath("//span[text()='Required']");
	    
	    private By confirmDeleteBtn = By.xpath("//button[@type='button' and normalize-space()='Yes, Delete']");
	    private By deleteSelectedBtn = By.xpath("//button[normalize-space()='Delete Selected']");

	    // Navigate to Employment Status page
	    public void goToEmploymentStatus() {
	       // wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(jobMenu)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(empStatusMenu)).click();
	    }

	    // Clear Name field
	    public void clearNameField() {
	        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	        input.clear();
	    }

	    // Add new Employment Status
	    public void addEmploymentStatus(String statusName) {
	        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
	        clearNameField();
	        if (statusName != null && !statusName.isBlank()) {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(statusName);
	        }
	        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
	    }

	    public boolean isSuccessMessageDisplayed() {
	    	try {
	            WebElement msg = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-text.oxd-text--p.oxd-text--toast-message"))
	            );
	            String text = msg.getText().toLowerCase();
	            return text.contains("success");
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

	    // Edit Employment Status
	    public void editEmploymentStatus(String oldName, String newName) {
	        // Click Edit using pencil icon
	        By editBtn = By.xpath("//div[text()='" + oldName + "']/../..//button[i[contains(@class,'bi-pencil-fill')]]");
	        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

	        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	        input.sendKeys(Keys.CONTROL + "a");
	        input.sendKeys(Keys.DELETE);
	        input.sendKeys(newName);

	        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
	    }

	    // Delete single Employment Status
	    public void deleteEmploymentStatus(String statusName) {
	        // Click Delete using trash icon
	    	By deleteBtn = By.xpath("//div[text()='" + statusName + "']/../..//button[contains(@class,'oxd-icon-button')]");	
	    	wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

	        // Confirm deletion
	        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
	        
	    }

	    public boolean isDeleteMessageDisplayed() {
	    	try {
	            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
	            String text = msg.getText().toLowerCase();
	            return text.contains("deleted") || text.contains("success");
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    	// Locator for "Already exists" error
	    	private By alreadyExistsError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span");

	    	public boolean isAlreadyExistsErrorDisplayed() {
	    	    try {
	    	        return wait.until(ExpectedConditions.visibilityOfElementLocated(alreadyExistsError)).isDisplayed();
	    	    } catch (Exception e) {
	    	        return false;
	    	    }
	    	
	    }

	    // Delete multiple Employment Statuses
	    public void selectEmploymentStatusCheckbox(String statusName) {
	    	By checkbox = By.xpath("//div[text()='" + statusName + "']/ancestor::div[@role='row']//div[@class='oxd-checkbox-wrapper']");
	        wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
	    }

	    public void deleteSelectedStatuses() {
	    	WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBtn));
	        deleteBtn.click();
	        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
	    }
	    

	    // refresh page
	    public void refreshPage() {
	        driver.navigate().refresh();
	        goToEmploymentStatus();
	    }
	}


