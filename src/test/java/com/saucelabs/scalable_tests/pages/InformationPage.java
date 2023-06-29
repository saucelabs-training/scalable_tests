package com.saucelabs.scalable_tests.pages;

import com.saucelabs.scalable_tests.data.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage extends BasePage {
    private final By firstNameElement = By.cssSelector("input[data-test='firstName']");
    private final By lastNameElement = By.cssSelector("input[data-test='lastName']");
    private final By postalCodeElement = By.cssSelector("input[data-test='postalCode']");
    private final By continueButton = By.cssSelector("input[data-test='continue']");

    public InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addInformation(Person person) {
        driver.findElement(firstNameElement).sendKeys(person.getFirstName());
        driver.findElement(lastNameElement).sendKeys(person.getLastName());
        driver.findElement(postalCodeElement).sendKeys(person.getPostalCode());
        driver.findElement(continueButton).click();
    }
}
