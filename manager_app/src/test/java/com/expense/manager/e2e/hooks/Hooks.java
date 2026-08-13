package com.expense.manager.e2e.hooks;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static WebDriver driver;

    @Before(order = 0)
    public void setup() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();

        driver = new RemoteWebDriver(
                new URL("http://selenium:4444/wd/hub"),
                options
        );

        driver.manage().window().maximize();
    }

    @After(order = 0)
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
