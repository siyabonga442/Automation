package com.demowebshop;

import com.demowebshop.base.BaseTest;
import com.demowebshop.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScenarios extends BaseTest {

    @Test
    public void fullPurchaseWorkflow() {
        // Register User
        driver.findElement(By.linkText("Register")).click();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser("John", "Doe", "johndoe" + System.currentTimeMillis() + "@example.com", "Password123!");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("johndoe" + System.currentTimeMillis() + "@example.com", "Password123!");

        // Navigate to Desktops and Add to Cart
        DesktopsPage desktopsPage = new DesktopsPage(driver);
        desktopsPage.navigateToDesktops();
        desktopsPage.sortDesktops("Price: Low to High");

        // Add all desktops to cart
        desktopsPage.addAllDesktopsToCart();

        // Validate Cart
        CartPage cartPage = new CartPage(driver);
        double initialTotal = cartPage.getCartTotal();
        Assert.assertTrue(initialTotal > 0, "Cart total should be greater than zero");

        // Remove one item
        cartPage.removeFirstItem();
        double updatedTotal = cartPage.getCartTotal();
        Assert.assertTrue(updatedTotal < initialTotal, "Cart total should decrease after removing an item");

        // Proceed to Checkout
        cartPage.proceedToCheckout();

        // Complete Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillBillingInformation(
                "John", "Doe",
                "johndoe" + System.currentTimeMillis() + "@example.com",
                "United States", "New York", "123 Test St",
                "10001", "1234567890"
        );

        // Confirm Order
        checkoutPage.confirmOrder();

        // Validate Order Completion
        Assert.assertTrue(checkoutPage.isOrderCompleted(), "Order should be completed successfully");
    }

    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        // Attempt login with invalid credentials
        loginPage.login("invalid@example.com", "wrongpassword");

        // Validate error message or login failure
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should remain on login page");
    }
    @Test
    public void computersPageNavigationTest() {
        HomePage homePage = new HomePage(driver);
        ComputersPage computersPage = new ComputersPage(driver);

        // Navigate to Computers Menu
        homePage.navigateToComputers();

        // Navigate to Desktops
        computersPage.navigateToDesktops();

        // Validate Product Availability
        Assert.assertTrue(computersPage.getProductCount() > 0, "No products found in Desktops");

        // Add all products to cart
        computersPage.addAllProductsToCart();
    }

    @Test
    public void computersCategoryVerificationTest() {
        ComputersPage computersPage = new ComputersPage(driver);

        // Navigate and check different categories
        computersPage.navigateToDesktops();
        Assert.assertTrue(computersPage.getProductCount() > 0, "No Desktop products found");

        computersPage.navigateToNotebooks();
        Assert.assertTrue(computersPage.getProductCount() > 0, "No Notebook products found");

        computersPage.navigateToAccessories();
        Assert.assertTrue(computersPage.getProductCount() > 0, "No Accessories found");
    }
}