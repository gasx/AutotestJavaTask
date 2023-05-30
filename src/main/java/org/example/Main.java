package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/gasym/Downloads/chromedriver_mac64 (1)/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///Users/gasym/Downloads/qa-test.html");
        String title = driver.getTitle();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("test@protei.ru", "wrong_password");
        String errorMessage = loginPage.getErrorMessage();
        System.out.println(errorMessage);
//        assertEquals(errorMessage, "Неверный E-Mail или пароль");

    }

}