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

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Настройка WebDriverManager для Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");

        // Принятие куки, если окно появляется
        try {
            WebElement cookieButton = driver.findElement(By.id("cookie-agree"));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с куками не найдена или не доступна.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOnlineReplenishmentBlockTitle() {
        // Проверка названия блока 'Онлайн пополнение без комиссии'
        WebElement blockTitle = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2"));
        assertNotNull(blockTitle, "Блок 'Онлайн пополнение без комиссии' не найден.");
    }

    @Test
    public void testPaymentLogosPresence() {
        // Проверка наличия логотипов платёжных систем
        List<WebElement> paymentLogos = driver.findElements(By.cssSelector("#pay-section ul"));
        assertFalse(paymentLogos.isEmpty(), "Логотипы платёжных систем не найдены.");
    }

    @Test
    public void testMoreInfoLinkFunctionality() {
        // Проверка работы ссылки «Подробнее о сервисе»
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();

        // Проверка, что открылась новая страница
        WebElement replenishmentElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/main/div/div[4]/ul[1]/li[1]")));
        assertNotNull(replenishmentElement, "Ссылка 'Подробнее о сервисе' не работает.");
    }

    @Test
    public void testContinueButtonFunctionality() {
        // Заполнение полей и проверка кнопки «Продолжить»
        // Кликаем на всплывающее меню в блоке "Онлайн пополнение без комиссии"
        WebElement serviceOption = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
        serviceOption.click();
        // Во всплывающем меню кликаем на "Услуги связи"
        WebElement serviceOptionChoice = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]/p"));
        serviceOptionChoice.click();
        // Вводим номер телефона
        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys("297777777");
        // Вводим сумму в рублях
        WebElement sumRublesInput = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        sumRublesInput.sendKeys("100");
        // Кликаем на кнопку "Продолжить"
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();

        // Проверка результата (появилось всплывающее окно)
        WebElement confirmationMessage = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]")));
        assertNotNull(confirmationMessage, "Кнопка 'Продолжить' не работает.");
    }
}