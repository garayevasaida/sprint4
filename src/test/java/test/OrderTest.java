package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.OrderFirstPage;
import pages.MainPage;
import pages.OrderLastPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String comment;
    private WebDriver driver;
    private MainPage mainPage;
    private OrderFirstPage orderFirstPage;
    private OrderLastPage orderLastPage;

    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/"; //Адрес главной страницы "Яндекс Самоката"

    //Конструктор
    public OrderTest(String firstName, String lastName, String address, String phoneNumber, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Саша", "Зайцев", "Коненково", "Бибирево", "11111111111", "10.09.2024", 5, "Черный", "Жду с нетерпением"},
                {"Олег", "Грозный", "Костромская улица", "Аутульфьево", "+375356214489", "06.07.2024", 3, "Серый", "Оставьте у двери"},
                {"Тимур", "Половинко", "Маяковская улица", "Отрадная", "+12345698745", "01.08.2024", 2, "Все цвета", ""}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //driver = new FirefoxDriver(); // Мозилла
        mainPage = new MainPage(driver);
        orderFirstPage = new OrderFirstPage(driver);
        orderLastPage = new OrderLastPage(driver);
        driver.get(SCOOTER_URL);
        driver.manage().window().maximize();  //Расширение экрана

    }
    @Test

    public void testOrderTest() {
        mainPage.clickOrderUpButton(); //Клик на верхнюю кнопку "Заказать" на главной странице

        orderFirstPage.fillOrderForm1(firstName, lastName, address, phoneNumber);  //Заполняется форма "Для кого самокат"
        orderFirstPage.nextButtonClick(); //Клик на кнопку "Далее"

        orderLastPage.fillOrderForm2(deliveryDate, comment); // Заполняется форма "Про аренду"

        orderLastPage.clickPlaceOrderButton(); // Клик на кнопку "Заказать"
        orderLastPage.clickYesButton(); //Клик на кнопку "Да"
        orderLastPage.assertOrderDoneTextVisible(); //Проверка, что заголовок "Заказ оформлен" видим
    }


    @After
    public void tearDown() {

        driver.quit();
    }
}
