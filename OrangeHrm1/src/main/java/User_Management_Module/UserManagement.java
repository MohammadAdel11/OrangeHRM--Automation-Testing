package User_Management_Module;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagement {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	public UserManagement(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		action = new Actions(driver);
	}

	// User Management Module
	private By admin_btn = By.xpath("//span[text()='Admin']");

	// <Search>
	private By searchBtn = By.xpath("//button[normalize-space()='Search']");
	private By usernameSearchInput = By.xpath("//label[normalize-space()='Username']/following::input[1]");
	// </Search>

	// <User Management Module-AddUser>
	private By add_btn = By.xpath("//button[text()=' Add ']");
	private By userRoleField = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
	private By statusField = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");
	private By listBox = By.xpath("//div[@role='listbox' and contains(@class,'oxd-select-dropdown')]");
	private By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");

	private By userNameInput = By.xpath("//label[normalize-space()='Username']/following::input[1]");
	private By passInput = By.xpath("//label[normalize-space()='Password']/following::input[1]");
	private By confPassInput = By.xpath("//label[normalize-space()='Confirm Password']/following::input[1]");
	private By save_btn = By.xpath("//button[normalize-space()='Save']");
	private By successToast = By.xpath("//div[contains(@class,'oxd-toast') and .//p[normalize-space()='Success']]");
	private By cancel = By.xpath("//button[normalize-space()='Cancel']");

	// </User Management Module-AddUser>
	// <User Management Module-DeleteUser>
	private By deleteBtn;
	private By deleteUsers_Btn = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");
	// </User Management Module-DeleteUser>

	// <User Management Module-EditeUser>
	private By editBtn; 

	private By Yes_btn = By.xpath("//div[@class='oxd-checkbox-wrapper']/label/span");

	// </User Management Module-EditeUser>

	public void clickAdminBtn() {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(admin_btn));
		el.click();
	}

	// <Search>
	// <User Management Module-AddUser>

	public boolean waitForSuccessToast() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));

			wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));

			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void searchByUsername(String username) {
		WebElement u = wait.until(ExpectedConditions.elementToBeClickable(usernameSearchInput));
		u.clear();
		u.sendKeys(username);
		driver.findElement(searchBtn).click();

	}

	public boolean checkSearchResult(String username, String expectedRole, String expectedStatus) {

		String actualUsername = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + username + "']")))
				.getText().trim();

		String actualRole = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + expectedRole + "']")))
				.getText().trim();

		String actualStatus = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[text()='" + expectedStatus + "']")))
				.getText().trim();

		return actualUsername.equals(username) && actualRole.equals(expectedRole)
				&& actualStatus.equals(expectedStatus);
	}

	// </User Management Module-AddUser>

	// </Search>

	// <User Management Module-AddUser>
	public void clickAdd() {
		WebElement field = wait.until(ExpectedConditions.elementToBeClickable(add_btn));
		field.click();
	}

	public void selectUserRole(String role) {
		String value = normalizeRole(role);
		WebElement field = wait.until(ExpectedConditions.elementToBeClickable(userRoleField));
		field.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(listBox));
		By option = By.xpath("//div[@role='listbox']//*[normalize-space(text())='" + value + "']");
		WebElement optEl = wait.until(ExpectedConditions.elementToBeClickable(option));

		optEl.click();
	}

	private String normalizeRole(String role) {
		if (role == null)
			throw new IllegalArgumentException("role is null");
		String r = role.trim().toLowerCase();
		if (r.equals("admin"))
			return "Admin";
		if (r.equals("ess"))
			return "ESS";
		return role.trim();
	}

	public void selectStatus(String status) {
		String value = normalizeStatus(status);
		WebElement field = wait.until(ExpectedConditions.elementToBeClickable(statusField));
		field.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(listBox));
		By option = By.xpath("//div[@role='listbox']//*[normalize-space(text())='" + value + "']");
		WebElement optEl = wait.until(ExpectedConditions.elementToBeClickable(option));

		optEl.click();
	}

	private String normalizeStatus(String status) {
		if (status == null)
			throw new IllegalArgumentException("status is null");
		String r = status.trim().toLowerCase();
		if (r.equals("enabled"))
			return "Enabled";
		if (r.equals("disabled"))
			return "Disabled";
		return status.trim();
	}

	public void pickEmployeeNameFirstSuggestion(String prefix) throws InterruptedException {
		String p = (prefix == null || prefix.isBlank()) ? "a" : prefix;
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(employeeNameInput));
		input.clear();
		input.sendKeys(p);
		Thread.sleep(5000);
		input.sendKeys(Keys.ARROW_DOWN);
		input.sendKeys(Keys.ENTER);
	}

	public void enterUserName(String name) {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(userNameInput));
		el.clear();
		el.sendKeys(name);
	}

	public void enterPass(String password) {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passInput));
		el.clear();
		el.sendKeys(password);
	}

	public void enterConfPass(String password) {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(confPassInput));
		el.clear();
		el.sendKeys(password);
	}

	public void clickSave() {
		driver.findElement(save_btn).click();
	}

	public void addUser(String userRole, String employeeNamePrefix, String status, String username, String password,
			String confirmPassword) throws InterruptedException {
		selectUserRole(userRole);
		pickEmployeeNameFirstSuggestion(employeeNamePrefix);
		selectStatus(status);
		enterUserName(username);
		enterPass(password);
		enterConfPass(confirmPassword);
		clickSave();
	}

	private String[][] getTableData() {

		List<WebElement> usernameCells = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][2]")));
		int rowCount = usernameCells.size();

		String[][] list = new String[rowCount][3];

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < 3; j++) {
				int domCol = (j == 0) ? 2 : (j == 1) ? 3 : 5;

				String xp = "(.//div[@role='cell'][" + domCol + "])[" + (i + 1) + "]";
				By cellBy = By.xpath(xp);

				WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(cellBy));

				String val = cell.getText();
				if (val == null || val.trim().isEmpty()) {
					val = ((org.openqa.selenium.JavascriptExecutor) driver)
							.executeScript("return arguments[0].textContent;", cell).toString();
				}

				list[i][j] = val.trim();
			}
		}

		return list;
	}

	public boolean checkDataEntered(String username, String role, String status) {
		String[][] list = getTableData();

		for (int i = 0; i < list.length; i++) {
			if (Arrays.asList(list[i]).containsAll(Arrays.asList(username, role, status))) {
				return true;
			}
		}
		return false;
	}

	public boolean isAlreadyExistDisplayed() {
		By locator = By.xpath("//span[normalize-space()='Already exists']");
		try {
			WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return el.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isPassConfirmed() {
		By locator = By.xpath("//span[normalize-space()='Passwords do not match']");
		try {
			WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return el.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isRequired() {
		By locator = By.xpath("//span[normalize-space()='Required']");
		try {
			WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return el.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void clickCancel() {
		driver.findElement(cancel).click();
	}

	// </User Management Module-AddUser>
	// <User Management Module-DeleteUser>

	public void deleteSelectedUser(int index) {
		deleteBtn = By.xpath("(//button[i[contains(@class,'bi-trash')]])[" + index + "]");

		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));

		action.moveToElement(btn).perform();

		btn.click();

		WebElement el = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes, Delete']")));
		el.click();

	}

	public void deleteAllUsers() {

		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(deleteUsers_Btn));

		action.moveToElement(btn).perform();

		btn.click();
		driver.findElement(By.xpath("//button[normalize-space()='Delete Selected']")).click();

		WebElement el = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes, Delete']")));
		el.click();

	}
	// </User Management Module-DeleteUser>

	// <User Management Module-EditeUser>

	public void editSelectedUser(int index) {
		editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");

		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(editBtn));

		action.moveToElement(btn).perform();

		btn.click();

	}

	private void ctrlAClear(WebElement el) {
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(Keys.DELETE);
	}

	private void clearAndType(By locator, String text) {
		if (text == null)
			return; // skip
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
		ctrlAClear(el);
		el.sendKeys(text);
	}

	public void editSelectedUser(int index, String newUserRole, String newEmployeeName, String newStatus,
			String newUsername, boolean changePassword, String newPassword) throws InterruptedException {

		editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
		action.moveToElement(btn).perform();
		btn.click();

		if (newUserRole != null && !newUserRole.isBlank()) {
			selectUserRole(newUserRole); 
		}

		if (newEmployeeName != null && !newEmployeeName.isBlank()) {
			WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(employeeNameInput));
			emp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			emp.sendKeys(Keys.DELETE);
			emp.sendKeys(newEmployeeName);
			Thread.sleep(3000);
			emp.sendKeys(Keys.ARROW_DOWN);
			emp.sendKeys(Keys.ENTER);
		}

		if (newStatus != null && !newStatus.isBlank()) {
			selectStatus(newStatus);
		}

		if (newUsername != null && !newUsername.isBlank()) {
			clearAndType(userNameInput, newUsername);
		}

		if (changePassword && newPassword != null && !newPassword.isBlank()) {
			WebElement yesToggle = wait.until(ExpectedConditions.elementToBeClickable(Yes_btn));
			yesToggle.click();

			clearAndType(passInput, newPassword);
			clearAndType(confPassInput, newPassword);
		}

		wait.until(ExpectedConditions.elementToBeClickable(save_btn)).click();
	}
	// </User Management Module-EditeUser>

}
