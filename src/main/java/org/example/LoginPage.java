package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //метод для открытия страницы
    public void open() {
        driver.get("file:///Users/gasym/Downloads/qa-test.html");
    }

    //проверка на успешность загрузки страницы
    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(5000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginEmail\"]"))).isDisplayed();
    }

    //метод для удобной передачи данных в поля email/password и проверки корректности работы
    public void login(String email, String password) throws InterruptedException {
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"loginEmail\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"loginPassword\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"authButton\"]"));
        Thread.sleep(5000);
        emailInput.clear();
        passwordInput.clear();
        emailInput.sendKeys(email);
        Thread.sleep(5000);
        passwordInput.sendKeys(password);
        Thread.sleep(5000);
        loginButton.click();
    }

    //получение текста ошибки при ошибке авторизации
    public String getErrorMessage() {
        WebElement errorPage = driver.findElement(By.xpath("//*[@id=\"invalidEmailPassword\"]"));
        return errorPage.getText();
    }
}
