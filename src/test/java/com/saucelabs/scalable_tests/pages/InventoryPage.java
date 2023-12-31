package com.saucelabs.scalable_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final WebDriver driver;
    private final By item1Link = By.id("item_1_title_link");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public void viewBoltTShirtProduct() {
        driver.findElement(item1Link).click();
    }

    public void addItem(Product product) {
        String cssSelector = "button[data-test='add-to-cart-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void removeItem(Product product) {
        String cssSelector = "button[data-test='remove-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
