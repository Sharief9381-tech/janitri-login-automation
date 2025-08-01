package com.janitri.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(css = ".login-btn")
    private WebElement loginButton;

    @FindBy(css = ".toggle-password")
    private WebElement passwordToggle;

    @FindBy(css = ".alert-error")
    private WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Methods
    public boolean isLoginButtonDisabled() {
        return !wait.until(ExpectedConditions.elementToBeClickable(loginButton)).isEnabled();
    }

    public void togglePasswordVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordToggle)).click();
    }

    public boolean isPasswordMasked() {
        return wait.until(ExpectedConditions.visibilityOf(passwordInput)).getAttribute("type").equals("password");
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed() ? errorMessage.getText() : "";
    }

    public boolean arePageElementsPresent() {
        return wait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOf(passwordToggle)).isDisplayed() &&
               wait.until(ExpectedConditions.elementToBeClickable(loginButton)).isDisplayed() &&
               driver.getTitle().contains("Janitri");
    }
}