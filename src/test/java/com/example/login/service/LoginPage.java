package com.example.login.service;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://example.com/login");
    }

    @Test
    public void testSuccessfulLogin() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        usernameField.sendKeys("validUsername");
        passwordField.sendKeys("validPassword");
        loginButton.click();

        WebElement welcomeMessage = driver.findElement(By.id("welcomeMessage"));
        assertTrue(welcomeMessage.isDisplayed());
    }


}
