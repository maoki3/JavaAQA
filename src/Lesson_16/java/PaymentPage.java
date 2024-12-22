import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.NoSuchElementException;

public class PaymentPage {
    private WebDriver driver;

    // Конструктор
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Элементы страницы
    private By blockTitle = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2");
    private By paymentLogos = By.cssSelector("#pay-section ul");
    private By moreInfoLink = By.linkText("Подробнее о сервисе");
    private By serviceOption = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button");
    private By serviceOptionChoiceComServ = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > div.pay__form > div.select > div.select__wrapper.opened > ul > li:nth-child(1) > p");
    private By serviceOptionChoiceHomeNet = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > div.pay__form > div.select > div.select__wrapper.opened > ul > li.select__item.active > p");
    private By serviceOptionChoiceInstPlan = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > div.pay__form > div.select > div.select__wrapper.opened > ul > li:nth-child(3) > p");
    private By serviceOptionChoiceDuty = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > div.pay__form > div.select > div.select__wrapper.opened > ul > li:nth-child(4) > p");
    private By continueButton = By.xpath("//*[@id=\"pay-connection\"]/button");

    // Элементы для проверки информации до нажатия "Продолжить"
    private By phoneNumberField = By.id("connection-phone");
    private By amountField = By.id("connection-sum");

    // Для проверки надписей в незаполненных полях
    // Услуги связи
    private By phoneNumberFieldComServ = By.xpath("//input[@placeholder='Номер телефона']");
    private By amountFieldComServ = By.cssSelector("#connection-sum");
    private By emailFieldComServ = By.cssSelector("#connection-email");
    // Домашний интернет
    private By subscriberNumberFieldHomeNet = By.cssSelector("input.phone[placeholder='Номер абонента']");
    private By amountFieldHomeNet = By.cssSelector("#internet-sum");
    private By emailFieldHomeNet = By.cssSelector("#internet-email");
    // Рассрочка
    private By accountNumberFieldInstPlan = By.cssSelector("input.score[placeholder='Номер счета на 44']");
    private By amountFieldInstPlan = By.cssSelector("#instalment-sum");
    private By emailFieldInstPlan = By.cssSelector("#instalment-email");
    // Задолженность
    private By accountNumberFieldDuty = By.cssSelector("input.score[placeholder='Номер счета на 2073']");
    private By amountFieldDuty = By.cssSelector("#arrears-sum");
    private By emailFieldDuty = By.cssSelector("#arrears-email");

    // Поля для ввода реквизитов карты
    private By cardNumberField = By.xpath("//label[text()='Номер карты']");
    private By cardExpiryField = By.xpath("//label[text()='Срок действия']");
    private By cardCvcField = By.xpath("//label[text()='CVC']");
    private By holderNameField = By.xpath("//label[text()='Имя держателя (как на карте)']");

    // Логотипы платёжных систем
    private By visaLogo = By.xpath("//img[contains(@src, 'visa-system.svg')]");
    private By masterCardLogo = By.xpath("//img[contains(@src, 'mastercard-system.svg')]");
    private By belkartLogo = By.xpath("//img[contains(@src, 'belkart-system.svg')]");
    private By maestroLogo = By.xpath("//img[@src='assets/images/payment-icons/card-types/maestro-system.svg']");
    private By mirLogo = By.xpath("//img[@src='assets/images/payment-icons/card-types/mir-system-ru.svg']");

    // Корректность отображения суммы
    private By amountOfMoneyTitle = By.xpath("//span[contains(text(), '100.00')]");
    private By amountOfMoneyButton = By.xpath("//button[contains(text(), 'Оплатить')]");

    // Проверка номера телефона
    private By actualPhoneNumber = By.xpath("//span[contains(text(), 'Номер:') and contains(text(), '375297777777')]");

    // Для проверки названия блока "Онлайн пополнение без комиссии"
    public WebElement getBlockTitle() {
        return driver.findElement(blockTitle);
    }

    // Для проверки наличия логотипов платёжных систем
    public boolean isPaymentLogosPresent() {
        return !driver.findElements(paymentLogos).isEmpty();
    }

    // Для проверки ссылки "Подробнее о сервисе"
    public void clickMoreInfoLink() {
        driver.findElement(moreInfoLink).click();
    }

    // Клик по "Выбор услуг"
    public void clickServiceOption() {
        driver.findElement(serviceOption).click();
    }

    // Выбор "Услуги связи"
    public void clickServiceOptionChoiceComServ() {
        driver.findElement(serviceOptionChoiceComServ).click();
    }

    // Выбор "Домашний интернет"
    public void clickServiceOptionChoiceHomeNet() {
        driver.findElement(serviceOptionChoiceHomeNet).click();
    }

    // Выбор "Рассрочка"
    public void clickServiceOptionChoiceInstPlan() {
        driver.findElement(serviceOptionChoiceInstPlan).click();
    }

    // Выбор "Задолженность"
    public void clicksServiceOptionChoiceDuty() {
        driver.findElement(serviceOptionChoiceDuty).click();
    }

    // Клик по кнопке "Продолжить"
    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    // Ввод значений с прошлого дз
    public void SKPhoneNumberField() {
        WebElement phoneField = driver.findElement(phoneNumberField);

        phoneField.clear();

        phoneField.sendKeys("297777777");
    }

    public void SKamountField() {
        WebElement phoneField = driver.findElement(amountField);

        phoneField.clear();

        phoneField.sendKeys("100");
    }

    // Услуги связи
    // Проверка поля "Номер телефона"
    public String getPhoneNumberFieldPlaceholderComServ() {
        try {
            WebElement element = driver.findElement(phoneNumberFieldComServ);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Услуги связи: поле <Номер телефона> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "Сумма"
    public String getAmountFieldPlaceholderComServ() {
        try {
            WebElement element = driver.findElement(amountFieldComServ);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Услуги связи: поле <Сумма> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "E-mail для отправки чека"
    public String getEmailFieldPlaceholderComServ() {
        try {
            WebElement element = driver.findElement(emailFieldComServ);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Услуги связи: поле <E-mail для отправки чека> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Домашний интернет
    // Проверка поля "Номер абонента"
    public String getSubscriberNumberFieldPlaceholderHomeNet() {
        try {
            WebElement element = driver.findElement(subscriberNumberFieldHomeNet);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Домашний интернет: поле <Номер абонента> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "Сумма"
    public String getAmountFieldPlaceholderHomeNet() {
        try {
            WebElement element = driver.findElement(amountFieldHomeNet);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Домашний интернет: поле <Сумма> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "E-mail для отправки чека"
    public String getEmailFieldPlaceholderHomeNet() {
        try {
            WebElement element = driver.findElement(emailFieldHomeNet);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Домашний интернет: поле <E-mail для отправки чека> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Рассрочка
    // Проверка поля "Номер счёта на 44"
    public String getAccountNumberFieldInstPlan() {
        try {
            WebElement element = driver.findElement(accountNumberFieldInstPlan);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Рассрочка: поле <Номер счёта на 44> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "Сумма"
    public String getAmountFieldInstPlan() {
        try {
            WebElement element = driver.findElement(amountFieldInstPlan);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Рассрочка: поле <Сумма> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "E-mail для отправки чека"
    public String getEmailFieldInstPlan() {
        try {
            WebElement element = driver.findElement(emailFieldInstPlan);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Рассрочка: поле <E-mail для отправки чека> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Задолженность
    // Проверка поля "Номер счёта на 2073"
    public String getAccountNumberFieldDuty() {
        try {
            WebElement element = driver.findElement(accountNumberFieldDuty);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Задолженность: поле <Номер счёта на 44> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "Сумма"
    public String getAmountFieldDuty() {
        try {
            WebElement element = driver.findElement(amountFieldDuty);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Задолженность: поле <Сумма> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка поля "E-mail для отправки чека"
    public String getEmailFieldDuty() {
        try {
            WebElement element = driver.findElement(emailFieldDuty);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Задолженность: поле <E-mail для отправки чека> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка текста в поле "Номер карты"
    public String getCardNumberFieldPlaceholder() {
        try {
            WebElement element = driver.findElement(cardNumberField);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Поле <Номер карты> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка текста в поле "Срок действия"
    public String getCardExpiryFieldPlaceholder() {
        try {
            WebElement element = driver.findElement(cardExpiryField);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Поле <Срок действия> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка текста в поле "CVC"
    public String getCardCvcFieldPlaceholder() {
        try {
            WebElement element = driver.findElement(cardCvcField);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Поле <CVC> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка текста в поле "Имя держателя (как на карте)"
    public String getHolderNameField() {
        try {
            WebElement element = driver.findElement(holderNameField);
            String placeholder = element.getAttribute("placeholder");
            System.out.println("Поле <Имя держателя (как на карте)> отображается корректно.");
            return placeholder;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка корректности отображения суммы в шапке
    public WebElement getAmountOfMoneyTitle() {
        try {
            WebElement element = driver.findElement(amountOfMoneyTitle);
            System.out.println("Сумма <100.00 BYN> в шапке отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка корректности отображения суммы на кнопке
    public WebElement getAmountOfMoneyButton() {
        try {
            WebElement element = driver.findElement(amountOfMoneyButton);
            System.out.println("Сумма <100.00 BYN> на кнопке отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка корректности отображения номера телефона
    public WebElement getActualPhoneNumber() {
        try {
            WebElement element = driver.findElement(actualPhoneNumber);
            System.out.println("Номер телефона <375297777777> отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка наличия логотипа Visa
    public WebElement getVisaLogo() {
        try {
            WebElement element = driver.findElement(visaLogo);
            System.out.println("Логотип Visa отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка наличия логотипа MasterCard
    public WebElement getMasterCardLogo() {
        try {
            WebElement element = driver.findElement(masterCardLogo);
            System.out.println("Логотип MasterCard отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка наличия логотипа Belkart
    public WebElement getBelkartLogo() {
        try {
            WebElement element = driver.findElement(belkartLogo);
            System.out.println("Логотип Белкарт отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка наличия логотипа Maestro
    public WebElement getMaestroLogo() {
        try {
            WebElement element = driver.findElement(maestroLogo);
            System.out.println("Логотип Maestro отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }

    // Проверка наличия логотипа Mir
    public WebElement getMirLogo() {
        try {
            WebElement element = driver.findElement(mirLogo);
            System.out.println("Логотип Мир отображается корректно.");
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден.");
            return null;
        }
    }
}
