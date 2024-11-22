package com.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Billing Information Locators
    private By billingFirstName = By.id("BillingNewAddress_FirstName");
    private By billingLastName = By.id("BillingNewAddress_LastName");
    private By billingEmail = By.id("BillingNewAddress_Email");
    private By billingCountry = By.id("BillingNewAddress_CountryId");
    private By billingCity = By.id("BillingNewAddress_City");
    private By billingAddress = By.id("BillingNewAddress_Address1");
    private By billingZip = By.id("BillingNewAddress_ZipPostalCode");
    private By billingPhone = By.id("BillingNewAddress_PhoneNumber");

    // Checkout Buttons
    private By continueButton = By.cssSelector(".button-1.new-address-next-step-button");
    private By confirmOrderButton = By.cssSelector(".button-1.confirm-order-next-step-button");
    private By orderCompleteMessage = By.cssSelector(".section.order-completed");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillBillingInformation(String firstName, String lastName, String email,
                                       String country, String city, String address,
                                       String zip, String phone) {
        // Wait and fill billing information
        wait.until(ExpectedConditions.elementToBeClickable(billingFirstName)).sendKeys(firstName);
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingEmail).sendKeys(email);

        // Select country
        Select countrySelect = new Select(driver.findElement(billingCountry));
        countrySelect.selectByVisibleText(country);

        // Fill rest of the details
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingZip).sendKeys(zip);
        driver.findElement(billingPhone).sendKeys(phone);

        // Continue to next step
        driver.findElement(continueButton).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public boolean isOrderCompleted() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(orderCompleteMessage)).isDisplayed();
    }
}