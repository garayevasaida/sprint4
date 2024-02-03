package PageObjects;

public class OrderFirstPage {
    //"Для кого самокат"

    public static final String NAME_INPUT = "//input[@placeholder='* Имя']\n";  // Поле ввода "Имя"
    public static final String SURNAME_INPUT = "//input[@placeholder='* Фамилия']\n";  // Поле ввода "Фамилия"
    public static final String ADDRESS_INPUT = "//input[@placeholder='* Адрес: куда привезти заказ']\n"; // Поле ввода "Адресс"
    public static final String METRO_STATION_INPUT = "//input[@class='select-search__input' and @placeholder='* Станция метро']\n"; // Поле ввода "Станция метро"
    public static final String PHONE_NUMBER_INPUT = "//input[@placeholder='* Телефон: на него позвонит курьер']\n"; // Поле ввода "Номер телефона"
    public static final String NEXT_BUTTON = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']\n"; // Кнопка "Далее" для перехода на вторую страницу оформления заказа
    //"Про Аренду"

    public static final String DATE_OF_DELIVERY = "//input[@placeholder='* Когда привезти самокат']\n";  // Поле ввода "Когда привезти самокат"
    public static final String RENTAL_PERIOD = "//div[@class='Dropdown-placeholder' and contains(text(), '* Срок аренды')]\n";  // Поле ввода "Срок аренды"

    public static final String SCOOTER_COLOUR_BLACK = "//label[@for='black']/input[@id='black' and @type='checkbox']"; // Чекбокс, черный цвет
    public static final String SCOOTER_COLOUR_GREY = "//label[@for='grey']/input[@id='grey' and @type='checkbox']\n"; // Чекбокс, серый цвет
    public static final String COMMENT = "//input[@placeholder='Комментарий для курьера']\n"; // Поле ввода "Комментарий для курьера"
    public static final String ORDER_BUTTON = "//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']\n"; // Кнопка "Заказать" для завершения оформления заказа
}
