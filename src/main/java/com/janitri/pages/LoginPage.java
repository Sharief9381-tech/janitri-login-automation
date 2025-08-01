package com.janitri.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Locators
   @FindBy(id = "userEmail")
private WebElement emailInput;

    @FindBy(id = "password") // Adjust ID based on actual page
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']") // Adjust XPath based on actual page
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type='password']/following-sibling::span") // Adjust for eye icon
    private WebElement passwordToggle;

    @FindBy(xpath = "//div[contains(@class, 'error-message')]") // Adjust for error message
    private WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public boolean isLoginButtonDisabled() {
        return !loginButton.isEnabled();
    }

    public void togglePasswordVisibility() {
        passwordToggle.click();
    }

    public boolean isPasswordMasked() {
        return passwordInput.getAttribute("type").equals("password");
    }

    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.isDisplayed() ? errorMessage.getText() : "";
    }

    public boolean arePageElementsPresent() {
        return emailInput.isDisplayed() && passwordInput.isDisplayed() && 
               passwordToggle.isDisplayed() && loginButton.isDisplayed() &&
               driver.getTitle().contains("Janitri"); // Adjust title check
    }
}