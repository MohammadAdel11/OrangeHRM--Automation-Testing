package Organization_Module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationalLocationsDelete {
	WebDriver driver;
	WebDriverWait wait;

	private By AdminElement = By
			.xpath("//a//span[text()=\"Admin\"]");
	private By OrganizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By LocationButton = By.xpath("//a[text()='Locations']");
	private By DeleteButton = By.xpath("//i[@class='oxd-icon bi-trash']");
	private By ConfirmDelete = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");
	private By CancelButton = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-button-margin']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By DeleteSelectedButton = By.xpath("//button[text()=' Delete Selected ']");
	private By AllCheckBox = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");
	private By CheckBox=By.xpath("//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");

	public OrganizationalLocationsDelete(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void OpenLocationForm() {
		//wait.until(ExpectedConditions.elementToBeClickable(AdminElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(OrganizationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(LocationButton)).click();
	}

	public void clickDeleteButton(int index) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(DeleteButton, index));
		driver.findElements(DeleteButton).get(index).click();
	}

	public void ConfirmDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmDelete)).click();

	}

	public void CancelDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(CancelButton)).click();

	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isCancelButtonEnabled() {
		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(CancelButton));
		return cancel.isEnabled();
	}

	public void SelectRecord(int index) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CheckBox, index));
		driver.findElements(CheckBox).get(index).click();

	}
	public void SelectAllChecBox() {
		wait.until(ExpectedConditions.elementToBeClickable(AllCheckBox)).click();

	}

	public void DeleteSelected() {
		wait.until(ExpectedConditions.elementToBeClickable(DeleteSelectedButton)).click();
	}
	
	public boolean isSelectedCheckBox() {
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CheckBox));
		return checkbox.isSelected();
	}

}
