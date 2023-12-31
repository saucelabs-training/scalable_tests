package com.saucelabs.scalable_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderSection extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public HeaderSection(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = driver.findElements(shoppingCartBadge);
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public void logOut() {
        driver.findElement(menuButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(logoutLink).click();
    }
}
