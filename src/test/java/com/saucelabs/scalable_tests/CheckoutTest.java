package com.saucelabs.scalable_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckoutTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void badInfo() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();

        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        Assertions.assertTrue(driver.findElement(By.cssSelector("input[data-test='firstName']")).getAttribute("class").contains("error"),
                "Expected error not found on page");
    }

    @Test
    public void goodInfo() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();

        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");

        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html",
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        driver.findElement(By.cssSelector("button[data-test='finish']")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());

        Assertions.assertTrue(driver.findElement(By.className("complete-text")).isDisplayed());
    }
}
