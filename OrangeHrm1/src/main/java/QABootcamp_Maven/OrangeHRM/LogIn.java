package QABootcamp_Maven.OrangeHRM;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogIn {
    WebDriver driver;
    WebDriverWait wait;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    private By userName = By.name("username");
    private By password = By.name("password");
    private By logIn_btn = By.cssSelector(".oxd-button.oxd-button--main.orangehrm-login-button");

    public void enterUserName(String name) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        userField.sendKeys(name);
    }

    public void enterPass(String pass) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passField.sendKeys(pass);
    }

    public void clickLogIn() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(logIn_btn));
        loginButton.click();
    }

    public boolean isLogedIn() {
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")));
     
        return dashboard.isDisplayed();
    }
    
    public String getMassage() {
    	   WebElement errorMassage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                   By.xpath("//p[text()='Invalid credentials']")));
    	   return errorMassage.getText();
    }

    public void Login(String userName, String pass) {
        enterUserName(userName);
        enterPass(pass);
        clickLogIn();
    }
}
