package Job_Module;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkShiftsPage {
	private  WebDriver driver;
    private  WebDriverWait wait;

    public WorkShiftsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    // Buttons
    private By addButton= By.xpath("//button[normalize-space()='Add']");
    private By saveButton= By.xpath("//button[normalize-space()='Save']");
    private By deleteSelectedBtn= By.xpath("//button[normalize-space()='Delete Selected']");
    private By confirmDeleteBtn= By.xpath("//button[normalize-space()='Yes, Delete']");
    private By cancelButton= By.xpath("//button[normalize-space()='Cancel']");
    private By editButton= By.xpath("(//button[contains(@class,'oxd-icon-button')])[4]");
    // field locators
    private By shiftName= By.xpath("//label[normalize-space()='Shift Name']/following::input[1]");
    private By fromHours= By.xpath("//label[normalize-space()='From']/following::input[1]");
    private By toHours= By.xpath("//label[normalize-space()='To']/following::input[1]");
    private By tableBody= By.cssSelector(".oxd-table-body");
    private By successToast= By.xpath("//p[contains(.,'Successfully Saved') or contains(.,'Successfully Deleted') or contains(.,'Successfully Updated')]"
    		);
    private By errorToast= By.xpath("//p[contains(.,'Required') or contains(.,'Already exists') or contains(.,'Should be')]");

    // Actions

    public void openWorkShiftsDirect() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/workShift");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
      
        wait.until(ExpectedConditions.visibilityOfElementLocated(shiftName));
    }

    public void addWorkShift(String name, String from, String to) {
        WebElement ShiftName = wait.until(ExpectedConditions.visibilityOfElementLocated(shiftName));
        ShiftName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        if (name != null) ShiftName.sendKeys(name);

        
        WebElement fromH = wait.until(ExpectedConditions.elementToBeClickable(fromHours));
        fromH.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fromH.sendKeys(from);
        fromH.sendKeys(Keys.TAB);

       
        WebElement toH = wait.until(ExpectedConditions.elementToBeClickable(toHours));
        toH.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        toH.sendKeys(to);
        toH.sendKeys(Keys.TAB);

        clickSave();
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        try {
            save.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        }
    }

    public void editWorkShift(String newName, String newFrom, String newTo) {
    	
    	
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editBtn.click();
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(shiftName));
        nameInput.click();
        nameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        nameInput.sendKeys(Keys.DELETE);
        nameInput.sendKeys(newName);

        WebElement fromInput = driver.findElement(fromHours);
        fromInput.click();
        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, newFrom);

        WebElement toInput = driver.findElement(toHours);
        toInput.click();
        toInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, newTo);
        
        try { Thread.sleep(500); 
        } 
        catch (InterruptedException e) { 	
        }
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }
   

    public void deleteWorkShift(String name) {
        By deleteBtn = By.xpath("//div[text()='" + name + "']/ancestor::div[@class='oxd-table-card']" +
                                "//button[i[contains(@class,'bi-trash')]]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmDeleteBtn));
    }

    public void selectWorkShiftCheckbox(String name) {
        By checkbox = By.xpath("//div[text()='" + name + "']/ancestor::div[@class='oxd-table-card']" +
                               "//div[contains(@class,'oxd-checkbox-wrapper')]");
        wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
    }

    public void deleteSelected() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmDeleteBtn));
    }
    
    public void closeAddDialogIfOpen() {
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
            if (cancelBtn.isDisplayed()) {
                cancelBtn.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelButton));
            }
        } catch (Exception e) {
        }
        }

    // Assertions 

    public boolean isRowVisible(String shift) {
        try {
            By row = By.xpath("//div[@class='oxd-table-card']//div[text()='" + shift + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(row));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
  


    public boolean isSuccessToastVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorToastVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
        
        
        
    }
}

	
	

	
	