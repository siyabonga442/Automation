package com.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ComputersPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Category Locators
    private By computersMenuLocator = By.linkText("Computers");
    private By desktopsSubMenuLocator = By.linkText("Desktops");
    private By notebooksSubMenuLocator = By.linkText("Notebooks");
    private By accessoriesSubMenuLocator = By.linkText("Accessories");

    // Product Locators
    private By productItemsLocator = By.cssSelector(".product-item");
    private By productTitleLocator = By.cssSelector(".product-title");
    private By addToCartButtonLocator = By.cssSelector(".button-2.product-box-add-to-cart-button");

    public ComputersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToComputersMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(computersMenuLocator)).click();
    }

    public void navigateToDesktops() {
        navigateToComputersMenu();
        wait.until(ExpectedConditions.elementToBeClickable(desktopsSubMenuLocator)).click();
    }

    public void navigateToNotebooks() {
        navigateToComputersMenu();
        wait.until(ExpectedConditions.elementToBeClickable(notebooksSubMenuLocator)).click();
    }

    public void navigateToAccessories() {
        navigateToComputersMenu();
        wait.until(ExpectedConditions.elementToBeClickable(accessoriesSubMenuLocator)).click();
    }

    public List<String> getProductTitles() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitleLocator))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void addAllProductsToCart() {
        List<WebElement> addToCartButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(addToCartButtonLocator)
        );

        addToCartButtons.forEach(WebElement::click);
    }

    public int getProductCount() {
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(productItemsLocator)
        ).size();
    }

    public boolean isProductAvailable(String productName) {
        return getProductTitles().contains(productName);
    }
}