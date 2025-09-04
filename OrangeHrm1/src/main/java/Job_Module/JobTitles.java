package Job_Module;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class JobTitles {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    public JobTitles(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
    }

    private By jobMenu        = By.xpath("//li[normalize-space()='Job'] | //span[normalize-space()='Job']");
    private By jobTitlesMenu  = By.xpath("//li[normalize-space()='Job Titles'] | //a[normalize-space()='Job Titles']");
    private By addBtn         = By.xpath("//button[normalize-space()='Add']");
    private By saveBtn        = By.xpath("//button[normalize-space()='Save']");
    private By successToast   = By.xpath("//div[contains(@class,'oxd-toast') and .//p[normalize-space()='Success']]");
    private By jobTitleField  = By.xpath("(//input[contains(@class,'oxd-input--active')])[2]");
    private By jobDescription = By.xpath("(//textarea[contains(@class,'oxd-textarea--active')])[1]");
    private By jobNote        = By.xpath("(//textarea[@placeholder='Add note'])[1]");
    private By fileInput      = By.xpath("//input[@type='file']");
	private By cancel = By.xpath("//button[normalize-space()='Cancel']");

    //delete 
    private By deleteBtn;
	private By deleteUsers_Btn = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");

    public void clickJobMenu() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(jobMenu));
        el.click();
    }

    public void clickJobTitlesMenu() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(jobTitlesMenu));
        el.click();
    }

    public void clickAddButton() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        el.click();
    }

    public void typeJobTitle(String title) {
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(jobTitleField));
        f.clear();
        f.sendKeys(title);
    }

    public void typeDescription(String desc) {
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(jobDescription));
        f.clear();
        f.sendKeys(desc);
    }

    public void typeNote(String noteText) {
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(jobNote));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", f);
        f.clear();
        f.sendKeys(noteText);
    }

    public void uploadSpecification(String path) {
        if (path == null || path.isBlank()) return;
        Path p = Path.of(path);
        if (!Files.exists(p)) return;

        WebElement file = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", file);
        file.sendKeys(p.toAbsolutePath().toString());
    }

    public void clickSaveButton() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", el);
        el.click();
    }

    public boolean waitForSuccessToast() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean addJobTitleFlow(String title, String description, String specFilePath, String note) {
        clickJobMenu();
        clickJobTitlesMenu();
        clickAddButton();
        typeJobTitle(title);
        typeDescription(description);
        uploadSpecification(specFilePath);
        typeNote(note);
        clickSaveButton();
        return waitForSuccessToast();
    }
    
    private String[][] getJobTitlesAndDescriptions() {
        List<WebElement> titleCells = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][2]")));

        List<WebElement> descCells = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][3]")));

        int rowCount = titleCells.size();
        String[][] list = new String[rowCount][2]; 

        for (int i = 0; i < rowCount; i++) {
            String title = titleCells.get(i).getText().trim();
            String desc  = descCells.get(i).getText().trim();

            if (title.isEmpty()) {
                title = ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].textContent;", titleCells.get(i)).toString().trim();
            }
            if (desc.isEmpty()) {
                desc = ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].textContent;", descCells.get(i)).toString().trim();
            }

            list[i][0] = title;
            list[i][1] = desc;
        }

        return list;
    }

    public boolean checkJobTitleAndDescription(String expectedTitle, String expectedDesc) {
        String[][] data = getJobTitlesAndDescriptions();

        for (int i = 0; i < data.length; i++) {
            if (data[i][0].equals(expectedTitle) && data[i][1].equals(expectedDesc)) {
                return true;
            }
        }
        return false;
    }

    // delete job title
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

	private void ctrlAClear(WebElement el) {
	    el.click();
	    el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    el.sendKeys(Keys.DELETE);
	}

	private void clearAndType(By locator, String text) {
	    if (text == null || text.isBlank()) return;
	    WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
	    ctrlAClear(el);
	    el.sendKeys(text);
	}

	public void editJobTitle(int index, String newTitle, String newDescription, String newSpecFilePath, String newNote) {
	    By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
	    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
	    btn.click();

	    clearAndType(jobTitleField, newTitle);

	    clearAndType(jobDescription, newDescription);

	    if (newSpecFilePath != null && !newSpecFilePath.isBlank()) {
	        try {
	            Path p = Path.of(newSpecFilePath);
	            if (Files.exists(p)) {
	                WebElement file = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", file);
	                file.sendKeys(p.toAbsolutePath().toString());
	            }
	        } catch (Exception ignore) {}
	    }

	    clearAndType(jobNote, newNote);

	    WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", save);
	    save.click();
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
	    WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancel));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);
	    cancelBtn.click();
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


}
