package com.saucelabs.scalable_tests;

import com.saucelabs.scalable_tests.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.scalable_tests.pages.HeaderSection;
import com.saucelabs.scalable_tests.pages.HomePage;
import com.saucelabs.scalable_tests.pages.InventoryPage;

public class AuthenticationTest extends BaseTest {

    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);
        User lockedOutUser = User.lockedOut();

        homePage.login(lockedOutUser);

        Assertions.assertTrue(homePage.isLockedOut(), "Error Not Found");
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = User.valid();

        homePage.login(validUser);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assertions.assertTrue(inventoryPage.isOnPage(), "Login Not Successful");
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = User.valid();
        homePage.login(validUser);

        HeaderSection headerSection = new HeaderSection(driver);
        headerSection.logOut();

        Assertions.assertTrue(homePage.isOnPage(), "Logout Not Successful");
    }
}
