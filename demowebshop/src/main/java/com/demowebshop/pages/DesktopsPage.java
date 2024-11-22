package com.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DesktopsPage {
    private WebDriver driver;

    private By desktopItems = By.cssSelector(".product-item");
    private By addToCartButtons = By.cssSelector(".button-2.product-box-add-to-cart-button");
    private By sortDropdown = By.id("products-orderby");

    public DesktopsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToDesktops() {
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Desktops")).click();
    }

    public void sortDesktops(String sortOption) {
        Select sortSelect = new Select(driver.findElement(sortDropdown));
        sortSelect.selectByVisibleText(sortOption);
    }

    public List<String> getDesktopNames() {
        return driver.findElements(desktopItems)
                .stream()
                .map(item -> item.findElement(By.cssSelector(".product-title")).getText())
                .collect(Collectors.toList());
    }

    public void addAllDesktopsToCart() {
        List<WebElement> addToCartButtons = driver.findElements(this.addToCartButtons);
        addToCartButtons.forEach(WebElement::click);
    }
}
