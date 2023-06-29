package com.saucelabs.scalable_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final WebDriver driver;
    private final By completeText = By.className("complete-text");

    public FinishPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public boolean isComplete() {
        return driver.findElement(completeText).isDisplayed();
    }
}
