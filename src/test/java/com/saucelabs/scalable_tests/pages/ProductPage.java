package com.saucelabs.scalable_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart-']");
    private final By removeFromCartButton = By.cssSelector("button[data-test^='remove']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void removeItemFromCart() {
        driver.findElement(removeFromCartButton).click();
    }
}
