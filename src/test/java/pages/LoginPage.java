package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By username = By.id("username");
    By password = By.id("password");
    By loginBtn = By.cssSelector("button[type='submit']");
    By flashMsg = By.id("flash");

    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void enterUsername(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public boolean isSuccess() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(flashMsg));
            String msg = driver.findElement(flashMsg).getText();
            return msg.contains("You logged into a secure area!");
        } catch (Exception e) {
            return false;
        }
    }
}