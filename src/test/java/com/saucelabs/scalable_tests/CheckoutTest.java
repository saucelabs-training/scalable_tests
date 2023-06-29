package com.saucelabs.scalable_tests;

import com.saucelabs.scalable_tests.data.Person;
import com.saucelabs.scalable_tests.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.scalable_tests.pages.CartPage;
import com.saucelabs.scalable_tests.pages.CheckoutPage;
import com.saucelabs.scalable_tests.pages.FinishPage;
import com.saucelabs.scalable_tests.pages.HomePage;
import com.saucelabs.scalable_tests.pages.InformationPage;
import com.saucelabs.scalable_tests.pages.InventoryPage;
import com.saucelabs.scalable_tests.pages.Product;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = User.valid();

        homePage.login(validUser);
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem(Product.ONESIE);
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);

        Person validPerson = new Person();
        informationPage.addInformation(validPerson);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assertions.assertTrue(checkoutPage.isOnPage(), "Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);
        Person validPerson = new Person();
        informationPage.addInformation(validPerson);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.finish();

        FinishPage finish = new FinishPage(driver);
        Assertions.assertTrue(finish.isOnPage());
        Assertions.assertTrue(finish.isComplete());
    }
}
