package com.saucelabs.scalable_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BaseTest {
    WebDriver driver;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeAll
    public static void toggleExecution() {
        // This would normally be toggled via CI tool ENV or similar
        System.setProperty("SELENIUM_PLATFORM", "SAUCE");
    }

    @BeforeEach
    public void setUp(TestInfo testinfo) throws MalformedURLException {
        driver = isLocal() ? runLocal() : runSauce(testinfo);
    }

    public boolean isLocal() {
        return "LOCAL".equals(System.getProperty("SELENIUM_PLATFORM"));
    }

    private WebDriver runLocal() {
        return new ChromeDriver();
    }

    private WebDriver runSauce(TestInfo testinfo) throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.put("name", testinfo.getDisplayName());
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        return new RemoteWebDriver(url, browserOptions);
    }

    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                if (isLocal()) {
                    System.out.println("Test Failed!");
                } else {
                    ((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
                }
            } catch (Exception ignored) {
            } finally {
                driver.quit();
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            try {
                if (isLocal()) {
                    System.out.println("Test Passed!");
                } else {
                    ((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
                }
            } catch (Exception ignored) {
            } finally {
                driver.quit();
            }
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
        }
    }
}
