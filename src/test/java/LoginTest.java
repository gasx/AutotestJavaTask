import org.example.FormPage;
import org.example.HomePage;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    //Применение конфигурации
    @BeforeTest
    public void setup() {
        driver = getDriver();
    }

    //Открывается ли страница входа
    @Test(priority = 1)
    public void pageOpens() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    //выдает соответствующую ошибку при попытке авторизоваться с неверным паролем
    @Test(priority = 2)
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("test@protei.ru", "wrong_password");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Неверный E-Mail или пароль");
    }

    //выдает соответствующую ошибку при попытке авторизоваться с неверной почтой
    @Test(priority = 3)
    public void canNotLoginWithInvalidEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("wrongmail@protei.ru", "test");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Неверный E-Mail или пароль");
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/gasym/Downloads/chromedriver_mac64 (1)/chromedriver");
        return new ChromeDriver();
    }

    //авторизация с корректными данными происходит успешно
    @Test(priority = 4)
    public void normalLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("test@protei.ru", "test");
        assertTrue(new HomePage(driver).isLoaded());
    }

    //TODO написать тест страницы после успешной авторизации

    //отправка формы с корректными данными происходит успешно
    @Test(priority = 5)
    public void normalFormSend() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        assertTrue(formPage.isLoaded());
        formPage.sendForm("email@mail.ru", "Normal Name");
        String successMessage = formPage.getSuccessMessage();
        assertEquals(successMessage, "Данные добавлены.");
    }

    //появляется ошиба при попытке отправить форму с пустым полем email
    @Test(priority = 6)
    public void formEmptyEmailTest() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        assertTrue(formPage.isLoaded());
        formPage.sendForm("", "someName");
        String errorMessage = formPage.getNoMailError();
        assertEquals(errorMessage, "Неверный формат E-Mail");
    }

    //появляется ошиба при попытке отправить форму с пустым полем имени
    @Test(priority = 7)
    public void formEmptyNameTest() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        assertTrue(formPage.isLoaded());
        formPage.sendForm("somemail@mail.ru", "");
        String errorMessage = formPage.getNoNameError();
        assertEquals(errorMessage, "Поле имя не может быть пустым");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
