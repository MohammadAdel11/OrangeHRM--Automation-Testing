package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationLocations {
	WebDriver driver;
	WebDriverWait wait;

	private By AdminElement = By
			.xpath("//a//span[text()=\"Admin\"]");

	private By OrganizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By LocationButton = By.xpath("//a[normalize-space()='Locations']");
	private By AddButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By Name=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By City=By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By State=By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By ZIP=By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By Country=By.xpath("//div[@class='oxd-select-text oxd-select-text--active']");
	private By Phone=By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By Fax=By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]");
	private By Address=By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");
	private By Notes=By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]]");
	private By SaveButton=By.xpath("//button[@type='submit']");
	private By CancelButton=By.xpath("//button[text()=' Cancel ']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By ExistErrorMessage=By.xpath("//span[text()='Already exists']");
	
	public OrganizationLocations(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void OpenLocationForm() {
		//wait.until(ExpectedConditions.elementToBeClickable(AdminElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(OrganizationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(LocationButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(AddButton)).click();
		
	}
	public void AddName(String name) {
	    WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Name));
	    nameInput.clear();
	    nameInput.sendKeys(name);
	}

	public void AddCity(String city) {
		
		driver.findElement(City).sendKeys(city);
	}
	public void AddState(String state) {
		driver.findElement(State).sendKeys(state);
	}
	public void AddZIP(String zip) {
		driver.findElement(ZIP).sendKeys(zip);
	}
	
	public void AddCountry(String country) {
		driver.findElement(Country).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> options = wait.until(
		    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']"))
		);
		for (WebElement option : options) {
		    if (option.getText().equals(country)) {
		        option.click();
		        break;
		    }
		  }
	}
	public void AddPhone(String phone) {
		driver.findElement(Phone).sendKeys(phone);
	}
	public void AddFax(String fax) {
		driver.findElement(Fax).sendKeys(fax);
	}
	public void AddAddress(String address) {
		driver.findElement(Address).sendKeys(address);
	}
	public void AddNotes(String notes) {
		driver.findElement(Notes).sendKeys(notes);
	}
	public void SaveAdd() {
		wait.until(ExpectedConditions.elementToBeClickable(SaveButton)).click();
	}
	public void CancelAdd() {
		WebElement button = driver.findElement(CancelButton);
		wait.until(ExpectedConditions.elementToBeClickable(CancelButton)).click();
		System.out.println(button.isEnabled());
	}
	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}
	public boolean isExistDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ExistErrorMessage));
		return error.isDisplayed();
	}

	

}
