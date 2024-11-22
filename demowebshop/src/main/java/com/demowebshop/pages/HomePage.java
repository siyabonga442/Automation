package com.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Log in");
    private By myAccountLink = By.linkText("My account");
    private By logoutLink = By.linkText("Log out");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void navigateToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void navigateToMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLink)).click();
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}