package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationLocationsEdit {
	WebDriver driver;
	WebDriverWait wait;

	private By AdminElement = By
			.xpath("//a//span[text()=\"Admin\"]");
	private By OrganizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By LocationButton = By.xpath("//a[text()='Locations']");
	private By EditButton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
	private By Name = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By City = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By State = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By ZIP = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By Country = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']");
	private By Phone = By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By Address = By
			.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");
	private By Notes = By
			.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]");
	private By Save = By.xpath("//button[@type='submit']");
	private By Cancel = By.xpath("(//button[@type='button'])[4]");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By PhoneError = By.xpath("//span[text()='Allows numbers and only + - / ( )']");

	public OrganizationLocationsEdit(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void OpenLocationForm() {
		//wait.until(ExpectedConditions.elementToBeClickable(AdminElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(OrganizationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(LocationButton)).click();
	}

	public void clickEditButton(int index) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(EditButton, index));
		driver.findElements(EditButton).get(index).click();

	}

	public void EditName(String name) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(Name));
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(name);
	}

	public void EditCity(String city) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(City)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(City)).sendKeys(city);
	}

	public void EditState(String state) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(State)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(State)).sendKeys(state);
	}

	public void EditZIP(String zip) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP)).sendKeys(zip);
	}

	public void EditCountry(String country) {
		driver.findElement(Country).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().equals(country)) {
				option.click();
				break;
			}
		}
	}

	public void EditPhone(String phone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).sendKeys(phone);
	}

	public void EditAddress(String address) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address)).sendKeys(address);
	}

	public void EditNotes(String note) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Notes)).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Notes)).sendKeys(note);
	}

	public void ClickSave() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Save)).click();
	}

	public void ClickCancel() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Cancel)).click();
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isinvalidPhoneFormat() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneError));
		return error.isDisplayed();
	}
	public boolean isCancelButtonEnabled() {
	    WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(Cancel));
	    return cancel.isEnabled();
	}
	
}
