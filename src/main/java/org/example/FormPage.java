package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
    }


    //метод успешной авторизации для перехода к странице заполнения формы
    public void open() throws InterruptedException {
        driver.get("file:///Users/gasym/Downloads/qa-test.html");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"loginEmail\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"loginPassword\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"authButton\"]"));
        Thread.sleep(5000);
        emailInput.sendKeys("protei@mail.ru");
        Thread.sleep(1000);
        passwordInput.sendKeys("test");
        Thread.sleep(1000);
        loginButton.click();
    }

    //метод отправки анкеты с переданными в параметр email/name
    public void sendForm(String email, String name) throws InterruptedException {
        driver.get("file:///Users/gasym/Downloads/qa-test.html");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"dataEmail\"]"));
        WebElement nameInput = driver.findElement(By.xpath("//*[@id=\"dataName\"]"));
        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"dataSend\"]"));
        emailInput.sendKeys(email);
        Thread.sleep(1000);
        nameInput.sendKeys(name);
        sendButton.click();
    }

    //проверка на успешность загрузки страницы
    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(5000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dataEmail\"]"))).isDisplayed();
    }

    //получение текста ошибки при попытке отправить анкету с пустым полем email
    public String getNoMailError() {
        WebElement noMailError = driver.findElement(By.xpath("//*[@id=\"emailFormatError\"]"));
        return noMailError.getText();
    }

    //получение текста ошибки при попытке отправить анкету с пустым полем name
    public String getNoNameError() {
        WebElement noNameError = driver.findElement(By.xpath("//*[@id=\"blankNameError\"]\n"));
        return noNameError.getText();
    }

    public String getSuccessMessage() {
        WebElement successMessage = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]"));
        return successMessage.getText();
    }
}
