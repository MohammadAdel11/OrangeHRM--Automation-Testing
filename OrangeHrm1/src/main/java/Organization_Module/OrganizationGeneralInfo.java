package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationGeneralInfo {
	WebDriver driver;
	WebDriverWait wait;
	private By AdminElement = By
			.xpath("//a//span[text()=\"Admin\"]");

	private final By OrganizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");

	private final By GeneralInformationButton = By.xpath("//a[normalize-space()='General Information']");

	private final By EditToogle = By.cssSelector("span.oxd-switch-input");

	private By organizationName = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By RegistrationNumber = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By TaxID = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By Phone = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By Fax = By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By Email = By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]");
	private By AddressStreet1 = By.xpath("(//input[@class='oxd-input oxd-input--active'])[8]");
	private By AddressStreet2 = By.xpath("(//input[@class='oxd-input oxd-input--active'])[9]");
	private By City = By.xpath("(//input[@class='oxd-input oxd-input--active'])[10]");
	private By State = By.xpath("(//input[@class='oxd-input oxd-input--active'])[11]");
	private By ZIP_PostalCode = By.xpath("(//input[@class='oxd-input oxd-input--active'])[12]");
	private By Country = By.xpath("//div[@class='oxd-select-text-input']");
	private By Notes = By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");

	private By Save = By.xpath("//button[@type='submit']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By EmailError = By.xpath("//span[text()='Expected format: admin@example.com']");
	private By PhoneError = By.xpath("//span[text()='Allows numbers and only + - / ( )']");

	public OrganizationGeneralInfo(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openGeneralInformationForm() {
		//wait.until(ExpectedConditions.elementToBeClickable(AdminElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(OrganizationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(GeneralInformationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(EditToogle)).click();

	}

	public void EditOrganizatioName(String OrganizationName) {
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(organizationName));
		name.sendKeys(Keys.CONTROL + "a");
		name.sendKeys(Keys.DELETE);
//		driver.findElement(organizationName).clear();
		name.sendKeys(OrganizationName);
	}

	public void EditRegistrationNumber(String registrationnumber) {
		WebElement Number = wait.until(ExpectedConditions.elementToBeClickable(RegistrationNumber));
		Number.sendKeys(Keys.CONTROL + "a");
		Number.sendKeys(registrationnumber);
	}

	public void EditTaxID(String taxID) {
		WebElement tax = wait.until(ExpectedConditions.elementToBeClickable(TaxID));
		tax.sendKeys(Keys.CONTROL + "a");
		tax.sendKeys(taxID);
	}

	public void EditPhone(String phone) {
		WebElement PhoneNum = wait.until(ExpectedConditions.elementToBeClickable(Phone));
		PhoneNum.sendKeys(Keys.CONTROL + "a");
		PhoneNum.sendKeys(phone);
	}

	public void EditFax(String fax) {
		WebElement FAX = wait.until(ExpectedConditions.elementToBeClickable(Fax));
		FAX.sendKeys(Keys.CONTROL + "a");
		FAX.sendKeys(fax);
	}

	public void EditEmail(String email) {
		WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(Email));
		emailInput.sendKeys(Keys.CONTROL + "a");
		emailInput.sendKeys(email);
	}

	public void EditAddressStreet1(String addressStreet1) {
		WebElement address = wait.until(ExpectedConditions.elementToBeClickable(AddressStreet1));
		address.sendKeys(Keys.CONTROL + "a");
		address.sendKeys(addressStreet1);
	}

	public void AddressStreet2(String addressStreet2) {
		WebElement address2 = wait.until(ExpectedConditions.elementToBeClickable(AddressStreet2));
		address2.sendKeys(Keys.CONTROL + "a");
		address2.sendKeys(addressStreet2);
	}

	public void EditCity(String city) {
		WebElement CITY = wait.until(ExpectedConditions.elementToBeClickable(City));
		CITY.sendKeys(Keys.CONTROL + "a");
		CITY.sendKeys(city);
	}

	public void EditZIPPostalCode(String ZipPostalCode) {
		WebElement ZIP = wait.until(ExpectedConditions.elementToBeClickable(ZIP_PostalCode));
		ZIP.sendKeys(Keys.CONTROL + "a");
		ZIP.sendKeys(ZipPostalCode);
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

	public void EditNote(String notes) {
		WebElement note = wait.until(ExpectedConditions.elementToBeClickable(Notes));
		note.sendKeys(Keys.CONTROL + "a");
		note.sendKeys(notes);
	}

	public void SaveEdit() {
		wait.until(ExpectedConditions.elementToBeClickable(Save)).click();
	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}

	public boolean isinvalidFormatDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(EmailError));
		return error.isDisplayed();
	}

	public boolean isinvalidPhoneFormat() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneError));
		return error.isDisplayed();
	}

}
