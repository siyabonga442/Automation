package com.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartTotal = By.cssSelector(".cart-total-right");
    private By removeItemCheckbox = By.cssSelector(".remove-from-cart input[type='checkbox']");
    private By updateCartButton = By.cssSelector(".button-2.update-cart-button");
    private By checkoutButton = By.cssSelector(".button-1.checkout-button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public double getCartTotal() {
        String totalText = wait.until(ExpectedConditions.presenceOfElementLocated(cartTotal))
                .getText().replace("$", "").trim();
        return Double.parseDouble(totalText);
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(removeItemCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}