package com.demowebshop;

import com.demowebshop.base.BaseTest;
import com.demowebshop.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTestScenarios extends BaseTest {

    @Test
    public void invalidRegistrationTests() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegister();
        RegisterPage registerPage = new RegisterPage(driver);

        // Test 1: Empty Registration Fields
        driver.findElement(By.id("register-button")).click();
        Assert.assertTrue(driver.getPageSource().contains("is required"), "Validation for required fields failed");

        // Test 2: Mismatched Passwords
        registerPage.registerUser("John", "Doe",
                "test" + System.currentTimeMillis() + "@example.com",
                "Password123!");
        driver.findElement(By.id("ConfirmPassword")).clear();
        driver.findElement(By.id("ConfirmPassword")).sendKeys("DifferentPassword");
        driver.findElement(By.id("register-button")).click();
        Assert.assertTrue(driver.getPageSource().contains("Password and confirmation password do not match"));
    }

    @Test
    public void loginValidationTests() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLogin();
        LoginPage loginPage = new LoginPage(driver);

        // Test 1: Invalid Email Format
        loginPage.login("invalid-email", "password");
        Assert.assertTrue(driver.getPageSource().contains("Wrong email"), "Email format validation failed");

        // Test 2: SQL Injection Attempt
        loginPage.login("' OR 1=1 --", "password");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "SQL Injection not properly handled");
    }

    @Test
    public void cartVulnerabilityTests() {
        // Navigate and add items
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Desktops")).click();

        // Get initial price
        WebElement firstProduct = driver.findElements(By.cssSelector(".product-item")).get(0);
        String initialPrice = firstProduct.findElement(By.cssSelector(".price")).getText();

        // Attempt price manipulation (Client-side validation check)
        // This tests if price can be manipulated via browser dev tools
        WebElement quantityInput = firstProduct.findElement(By.cssSelector(".qty"));
        quantityInput.clear();
        quantityInput.sendKeys("-1000"); // Negative quantity test

        driver.findElement(By.cssSelector(".button-2.product-box-add-to-cart-button")).click();

        // Verify cart behavior with invalid quantity
        WebElement cartQuantity = driver.findElement(By.cssSelector(".cart-qty"));
        Assert.assertTrue(Integer.parseInt(cartQuantity.getText()) >= 0, "Negative quantity allowed");
    }
}