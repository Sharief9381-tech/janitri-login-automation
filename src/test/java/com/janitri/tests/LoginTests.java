package com.janitri.tests;

import com.janitri.pages.LoginPage;
import com.janitri.utils.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonDisabled(), "Login button should be disabled when fields are empty.");
    }

    @Test(priority = 2)
    public void testPasswordMaskedToggle() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "Test123!");
        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after toggle.");
        loginPage.togglePasswordVisibility();
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked after second toggle.");
    }

    @Test(priority = 3)
    public void testInvalidLoginShowsErrorMessage() {
        loginPage = new LoginPage(driver);
        loginPage.login("invalid@janitri.in", "WrongPass123!");
        String errorMsg = loginPage.getErrorMessage();
        System.out.println("Error Message: " + errorMsg);
        Assert.assertFalse(errorMsg.isEmpty(), "Error message should be displayed for invalid credentials.");
    }

    @Test(priority = 4)
    public void testPageElementsPresence() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.arePageElementsPresent(), "All expected page elements should be present.");
    }
}