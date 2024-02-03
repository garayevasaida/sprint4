package test;

import PageObjects.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderByTopButton {
    @Test
    public void fullOrderTest() {
        driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Клик по верхней кнопке "Заказать"
        WebElement topOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.TOP_ORDER_BUTTON)));
        topOrderButton.click();

        // Заполнение строковых полей ввода
        driver.findElement(By.xpath(OrderFirstPage.NAME_INPUT)).sendKeys(nameInput);
        driver.findElement(By.xpath(OrderFirstPage.SURNAME_INPUT)).sendKeys(surnameInput);
        driver.findElement(By.xpath(OrderFirstPage.ADDRESS_INPUT)).sendKeys(addressInput);

        selectMetroStation(stationNameInput); // Вызываем метод, который инициализирован ранее.
        // Тестовые данные (Названия станций метро) переданы в параметризации

        driver.findElement(By.xpath(OrderFirstPage.PHONE_NUMBER_INPUT)).sendKeys(phoneNumberInput);

        // Нажатие кнопки "Далее"
        WebElement nextButton = driver.findElement(By.xpath(OrderFirstPage.NEXT_BUTTON));
        nextButton.click();

        System.out.println("Все данные заполнились без ошибок, выполнен переход на вторую страницу оформления заказа");

        // Ожидание загрузки элемента на второй странице
        WebElement orderButton = driver.findElement(By.xpath(OrderFirstPage.ORDER_BUTTON));

        // Заполнение данных на второй странице
        driver.findElement(By.xpath(OrderFirstPage.DATE_OF_DELIVERY)).sendKeys(dateOfDelivery);
        driver.findElement(By.xpath(OrderFirstPage.DATE_OF_DELIVERY)).sendKeys(Keys.ENTER);

        selectColor(scooterColour); // Вызываем метод, который инициализирован ранее.
        selectRentDurationInDays(rentalPeriod); // Вызываем метод, который инициализирован ранее.
        driver.findElement(By.xpath(OrderFirstPage.COMMENT)).sendKeys(comment);

        // Нажатие кнопки "Заказать" на второй странице
        orderButton.click();

        System.out.println("Все данные на второй странице заполнились без ошибок");

        WebElement daButton = driver.findElement(By.xpath(OrderLastPage.CONFIRM_ORDER_BUTTON));
        daButton.click();

        WebDriverWait SuccessfullOrderWindow = new WebDriverWait(driver, 3);
        SuccessfullOrderWindow.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderLastPage.CONFIRMED_ORDER_WINDOW)));

        WebElement titleOrder = driver.findElement(By.xpath(OrderLastPage.TITLE_ORDER_CONFIRMED));

        String expectedOrderConfirmedTitle = "Заказ оформлен";

        Assert.assertTrue(titleOrder.getText().contains(expectedOrderConfirmedTitle));

        System.out.println("Заказ выполнен успешно, никаких ошибок нет");

    }
    private static WebDriver driver;

    private final String nameInput; private final String surnameInput; private final String addressInput; private final String stationNameInput; private final String phoneNumberInput;
    private final String dateOfDelivery; private final int rentalPeriod; private final String scooterColour; private final String comment;

    public OrderByTopButton (String nameInput, String surnameInput, String addressInput, String stationNameInput, String phoneNumberInput, String dateOfDelivery, int rentalPeriod, String scooterColour, String comment) {
        this.nameInput = nameInput;
        this.surnameInput = surnameInput;
        this.addressInput = addressInput;
        this.stationNameInput = stationNameInput;
        this.phoneNumberInput = phoneNumberInput;
        this.dateOfDelivery = dateOfDelivery;
        this.rentalPeriod = rentalPeriod;
        this.scooterColour = scooterColour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Саша", "Зайцев", "Коненково", "Бибирево", "11111111111", "10.09.2024", 5, "Черный", "Жду с нетерпением"},
                {"Олег", "Грозный", "Костромская улица", "Аутульфьево", "+375356214489", "06.07.2024", 3, "Серый", "Оставьте у двери"},
                {"Тимур", "Половинко", "Маяковская улица", "Отрадная", "+12345698745", "01.08.2024", 2, "Все цвета", ""}
        });
    }

    private void selectColor(String scooterColour) {
        String checkboxLocator;

        if ("Черный".equals(scooterColour)) {
            checkboxLocator = OrderFirstPage.SCOOTER_COLOUR_BLACK;
        } else if ("Серый".equals(scooterColour)) {
            checkboxLocator = OrderFirstPage.SCOOTER_COLOUR_GREY;
        } else if ("Все цвета".equals(scooterColour)) {
            WebElement blackCheckbox = driver.findElement(By.xpath(OrderFirstPage.SCOOTER_COLOUR_BLACK));
            WebElement greyCheckbox = driver.findElement(By.xpath(OrderFirstPage.SCOOTER_COLOUR_GREY));
            {
                blackCheckbox.click();
            }
            {
                greyCheckbox.click();
            }

            return;
        } else {
            // Обработка неверного параметра или других случаев
            System.out.println("Некорректное значение параметра scooterColour");
            return;
        }

        WebElement checkbox = driver.findElement(By.xpath(checkboxLocator));
        checkbox.click();
    }

    private void selectRentDurationInDays(int rentalPeriod) {
        WebElement rentalPeriodDays = driver.findElement(By.xpath(OrderFirstPage.RENTAL_PERIOD));
        rentalPeriodDays.click();

        String optionLocator = String.format("//div[@class='Dropdown-menu']/div[@class='Dropdown-option'][%d]", rentalPeriod);        WebElement specificOption = driver.findElement(By.xpath(optionLocator));
        specificOption.click();
    }

    private void selectMetroStation(String stationNameInput) {
        final String metroStationPattern = ".//div[@class='Order_Form__17u6u']/div/div[@class='select-search has-focus']/div[@class='select-search__select']//*[text()='%s']";
        final String metroStationSelected = String.format(metroStationPattern, stationNameInput);

        driver.findElement(By.xpath(OrderFirstPage.METRO_STATION_INPUT)).click();

        WebElement searchStationFromList = driver.findElement(By.xpath(metroStationSelected));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchStationFromList);

        searchStationFromList.click();
    }
    private WebDriver getDriver() {
        String driverType = System.getenv("BROWSER");
        switch (driverType.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                return driver;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().setSize(new Dimension(1920, 1080)); // Измените размеры по необходимости
                return driver;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер. Поддерживаются только 'chrome' и 'firefox'");
        }
    }
    @After
    public void tearDown() {
        // Закрываем браузер после завершения всех тестов
        driver.quit();
    }
}
