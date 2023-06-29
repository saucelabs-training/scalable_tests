package com.saucelabs.scalable_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeItem(Product product) {
        String cssSelector = "button[data-test='remove-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
