import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTwoTest {
    private WebDriver driver;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        // Настройка WebDriverManager для Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        // Очистка куки перед каждым тестом
        driver.manage().deleteAllCookies();

        // Принятие куки, если окно появляется
        try {
            // Устанавливаем явное ожидание
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Ожидаем, пока кнопка согласия с куками станет кликабельной
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));

            // Кликаем по кнопке
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с куками не найдена или не доступна.");
        }

        paymentPage = new PaymentPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOnlineReplenishmentBlockTitle() {
        // Проверка названия блока "Онлайн пополнение без комиссии"
        assertNotNull(paymentPage.getBlockTitle(), "Блок 'Онлайн пополнение без комиссии' не найден.");
    }

    @Test
    public void testPaymentLogosPresence() {
        // Проверка наличия логотипов платёжных систем
        assertTrue(paymentPage.isPaymentLogosPresent(), "Логотипы платёжных систем не найдены.");
        // Проверка конкретных логотипов
        assertFalse(driver.findElements(By.xpath("//img[@alt='Visa']")).isEmpty(), "Логотип Visa не найден.");
        assertFalse(driver.findElements(By.xpath("//img[@alt='Verified By Visa']")).isEmpty(), "Логотип Verified By Visa не найден.");
        assertFalse(driver.findElements(By.xpath("//img[@alt='MasterCard']")).isEmpty(), "Логотип MasterCard не найден.");
        assertFalse(driver.findElements(By.xpath("//img[@alt='MasterCard Secure Code']")).isEmpty(), "Логотип MasterCard SecureCode не найден.");
        assertFalse(driver.findElements(By.xpath("//img[@alt='Белкарт']")).isEmpty(), "Логотип Белкарт не найден.");
    }

    @Test
    public void testMoreInfoLinkFunctionality() {
        // Проверка ссылки "Подробнее о сервисе"
        paymentPage.clickMoreInfoLink();

        WebElement replenishmentElement = new WebDriverWait(driver, Duration.ofSeconds(46))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"breadcrumbs\"]/div/div/div/div/ul/li[3]/span/span")));

        assertNotNull(replenishmentElement, "Ссылка 'Подробнее о сервисе' не работает.");
    }

    @Test // Задание №1
    public void testEmptyFieldsOnlineReplenishmentBlock() {
        // Проверка полей "Услуги Связи"
        paymentPage.clickServiceOption();
        paymentPage.clickServiceOptionChoiceComServ();
        paymentPage.getPhoneNumberFieldPlaceholderComServ();
        paymentPage.getAmountFieldPlaceholderComServ();
        paymentPage.getEmailFieldPlaceholderComServ();
        // Проверка полей "Домашний Интернет"
        paymentPage.clickServiceOption();
        paymentPage.clickServiceOptionChoiceHomeNet();
        paymentPage.getSubscriberNumberFieldPlaceholderHomeNet();
        paymentPage.getAmountFieldPlaceholderHomeNet();
        paymentPage.getEmailFieldPlaceholderHomeNet();
        // Проверка полей "Рассрочка"
        paymentPage.clickServiceOption();
        paymentPage.clickServiceOptionChoiceInstPlan();
        paymentPage.getAccountNumberFieldInstPlan();
        paymentPage.getAmountFieldInstPlan();
        paymentPage.getEmailFieldInstPlan();
        // Проверка полей "Задолженность"
        paymentPage.clickServiceOption();
        paymentPage.clicksServiceOptionChoiceDuty();
        paymentPage.getAccountNumberFieldDuty();
        paymentPage.getAmountFieldDuty();
        paymentPage.getEmailFieldDuty();
        }


    @Test  // Задание №2
    public void testPaymentFunctionality() {
        // "Услуги связи", заполнить поля (в соотв. с прошлым дз) и нажать кнопку "продолжить"
        paymentPage.clickServiceOption();
        paymentPage.clickServiceOptionChoiceComServ();
        paymentPage.SKPhoneNumberField();
        paymentPage.SKamountField();
        paymentPage.clickContinueButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement iframeElement = driver.findElement(By.cssSelector("iframe.bepaid-iframe"));
        driver.switchTo().frame(iframeElement);

        // Проверка отображения суммы
        paymentPage.getAmountOfMoneyTitle();
        paymentPage.getAmountOfMoneyButton();
        // Проверка номера телефона
        paymentPage.getActualPhoneNumber();
        // Проверка надписей в незаполненных полях
        paymentPage.getCardNumberFieldPlaceholder();
        paymentPage.getCardExpiryFieldPlaceholder();
        paymentPage.getCardCvcFieldPlaceholder();
        paymentPage.getHolderNameField();
        // Проверка наличия иконок платёжных систем
        paymentPage.getVisaLogo();
        paymentPage.getMasterCardLogo();
        paymentPage.getBelkartLogo();
        paymentPage.getMaestroLogo();
        paymentPage.getMirLogo();
    }
}