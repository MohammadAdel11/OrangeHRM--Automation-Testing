package PIM_Module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By pimElement = By.xpath("//span[text()='PIM']");
	private By addEmployees = By.xpath("//a[normalize-space()='Add Employee']");
	private By firstEmployeeName = By.xpath("//input[@placeholder='First Name']");
	private By lastEmployeeName = By.xpath("//input[@placeholder='Last Name']");
	private By saveEmployeeButton = By.xpath("//button[@type='submit']");
	private By titleEmployeePage = By.xpath("//h6[normalize-space()='Personal Details']");
	private By cancleButton = By.xpath("//button[text()=\" Cancel \"]");
	private By employeeListInformation = By.xpath("//h5[text()=\"Employee Information\"]");

	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openAddEmployeeForm() {
	    Actions actions = new Actions(driver);

	    // Move to PIM element and click
	    WebElement pim = wait.until(ExpectedConditions.elementToBeClickable(pimElement));
	    actions.moveToElement(pim).click().perform();

	    // Move to Add Employee element and click
	    WebElement addEmp = wait.until(ExpectedConditions.elementToBeClickable(addEmployees));
	    actions.moveToElement(addEmp).click().perform();
	}


	public void addEmployee(String firstName, String lastName) {
	    // Wait for first name field
	    WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstEmployeeName));
	    firstNameField.clear();
	    firstNameField.sendKeys(firstName);

	    // Wait for last name field
	    WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastEmployeeName));
	    lastNameField.clear();
	    lastNameField.sendKeys(lastName);
	}


	public void saveEmployee() {
		// Wait for loader to disappear before clicking Save *****
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-form-loader')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveEmployeeButton));
		wait.until(ExpectedConditions.elementToBeClickable(saveEmployeeButton)).click();
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-form-loader')]")));
	}

	public String getPersonalDetailsTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleEmployeePage)).getText();
	}

	public WebElement getErrorMessage(String errorText) {
		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + errorText + "']")));
	}

	public void cancelAddEmployee() {
		wait.until(ExpectedConditions.elementToBeClickable(cancleButton)).click();
	}

	public boolean isEmployeeListPageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeListInformation)).isDisplayed();
	}
}
