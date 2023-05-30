package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage  extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(12000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dataEmail\"]")))
                .isDisplayed();
    }
}
