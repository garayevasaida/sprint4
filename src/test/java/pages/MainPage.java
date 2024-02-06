package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private By mainScooterPage = By.xpath("//*[contains(@href,'https://qa-scooter.praktikum-services.ru/')]"); //Локатор URL Самоката
    private By orderUpButton = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']"); //Локатор верхней кнопки "Заказать"
    public void clickOrderUpButton() {
        driver.findElement(orderUpButton).click();   // Клик на верхнюю кнопку "Заказать"
    }
    private By orderDownButton = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp' or (@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");
    //Локатор нижней кнопки "Заказать"

    //Скролл и клик на нижнюю кнопку "Заказать"
    public void scrollToOrderDownButtonAndClick() {
        WebElement element = driver.findElement(orderDownButton);
        // Прокрутка к элементу
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Ожидание
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(orderDownButton));
        // Клик по кнопке
        element.click();
    }
    public void clickOrderDownButton() {
        driver.findElement(orderDownButton).click();   // Клик на нижнюю кнопку "Заказать"
    }
    //Локаторы кнопок списка:
    private By firstQuestion = By.id("accordion__heading-0"); // Первый вопрос "Сколько это стоит? И как оплатить"
    private By secondQuestion = By.id("accordion__heading-1"); // Второй вопрос "Хочу сразу несколько самокатов! Так можно?"
    private By thirdQuestion = By.id("accordion__heading-2"); // Третий вопрос "Как рассчитывается время аренды?"
    private By fourthQuestion = By.id("accordion__heading-3"); // Четтвёртый вопрос "Можно ли заказать самокат прямо на сегодня?"
    private By fifthQuestion = By.id("accordion__heading-4"); // Пятый вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
    private By sixthQuestion = By.id("accordion__heading-5"); // Шестой вопрос "Вы привозите зарядку вместе с самокатом?"
    private By seventhQuestion = By.id("accordion__heading-6"); // Седьмой вопрос "Можно ли отменить заказ?"
    private By eighthQuestion = By.id("accordion__heading-7"); // Восьмой вопрос "Я живу за МКАДом, привезёте?"


    private By firstAnswer = By.xpath("//p[contains(text(),'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]"); // Ответ на первый вопрос
    private By secondAnswer = By.xpath("//p[contains(text(),'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.')]"); // Ответ на второй вопрос
    private By thirdAnswer = By.xpath("//p[contains(text(),'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.')]"); // Ответ на третий вопрос
    private By fourthAnswer = By.xpath("//p[contains(text(),'Только начиная с завтрашнего дня. Но скоро станем расторопнее.')]"); // Ответ на четвертый вопрос
    private By fifthAnswer = By.xpath("//p[contains(text(),'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.')]"); // Ответ на пятый вопрос
    private By sixthAnswer = By.xpath("//p[contains(text(),'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.')]"); // Ответ на шестой вопрос
    private By seventhAnswer = By.xpath("//p[contains(text(),'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.')]"); // Ответ на седьмой вопрос
    private By eighthAnswer = By.xpath("//p[contains(text(),'Да, обязательно. Всем самокатов! И Москве, и Московской области.')]"); // Ответ на вопрос вопрос

    //Методы:
    public void clickFirstQuestion() {
        driver.findElement(firstQuestion).click();
    }
    public void clickSecondQuestion() {
        driver.findElement(secondQuestion).click();
    }
    public void clickThirdQuestion() {
        driver.findElement(thirdQuestion).click();
    }
    public void clickFourthAnswer() {
        driver.findElement(fourthQuestion).click();
    }
    public void clickFifthQuestion() {
        driver.findElement(fifthQuestion).click();
    }
    public void clickSixthQuestion() {
        driver.findElement(sixthQuestion).click();
    }
    public void clickSeventhQuestion() {
        driver.findElement(seventhQuestion).click();
    }
    public void clickEighthQuestion() {
        driver.findElement(eighthQuestion).click();
    }

    // Методы ответа
    public String getFirstAnswer() {
        return driver.findElement(firstAnswer).getText();
    }
    public String getSecondAnswer() {
        return driver.findElement(secondAnswer).getText();
    }
    public String getThirdAnswer() {
        return driver.findElement(thirdAnswer).getText();
    }
    public String getFourthAnswer() {
        return driver.findElement(fourthAnswer).getText();
    }
    public String getFifthAnswer() {
        return driver.findElement(fifthAnswer).getText();
    }
    public String getSixthAnswer() {
        return driver.findElement(sixthAnswer).getText();
    }
    public String getSeventhAnswer() {
        return driver.findElement(seventhAnswer).getText();
    }
    public String getEighthAnswer() {
        return driver.findElement(eighthAnswer).getText();
    }
    private String closedDropdownLocatorFormat = "accordion__heading-%d";
    private String textInputLocatorFormat = "accordion__panel-%d";

    // Метод для клика по закрытому дропдауну с использованием форматирования локатора
    public void clickClosedDropdownByIndex(int index) {
        driver.findElement(By.id(String.format(closedDropdownLocatorFormat, index))).click();
    }

    public String getTextFromTextInputByIndex(int index) {
        return  driver.findElement(By.id(String.format(textInputLocatorFormat, index))).getText();
    }
    //Метод скролла до начала таблицы и клик на первый элемент
    public void scrollToTable() {
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }


}


