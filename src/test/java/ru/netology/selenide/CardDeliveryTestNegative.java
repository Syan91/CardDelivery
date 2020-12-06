package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTestNegative {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
    }

    @Test
    void ShouldNotAssignDeliveryIfNameInLowerCase() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("иванов иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void ShouldNotAssignDeliveryIfCityIsWrong() {
        $("[data-test-id = city] input").sendKeys("Новочеркасск");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfCityWithProhibitedSymbols() {
        $("[data-test-id = city] input").sendKeys("Moscow");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);

    }
    @Test
    void ShouldNotAssignDeliveryIfCityIsEmpty() {
        $("[data-test-id = date] input").sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfNameWithProhibitedSymbols() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Ivanov Ivan");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfDateIsWrong() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys("30.11.2020");
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfDateWithProhibitedSymbols() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys("sdfsd");
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Неверно введена дата")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithEmptyForm() {
        $(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithEmptyPhone() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithoutAgreement() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $(byText("Забронировать")).click();
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfPhoneInWrongFormat() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("89280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithEmptyName() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithoutFirstNameOnlySurname() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryWithoutSurnameOnlyFirstName() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfNameOfEightyLetters() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Ивааааааааааааааааааааааааааааааааааааааан Иванооооооооооооооооооооооооооооооооов");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfPhoneWithOneNumber() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfPhoneWithTenNumbers() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+7928000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfPhoneWithTwelveNumbers() {
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+792800000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void ShouldNotAssignDeliveryIfCityWrittenWithLowerCase() {
        $("[data-test-id = city] input").sendKeys("ростов-на-дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);

    }
    @Test
    void ShouldNotAssignDeliveryIfCityWrittenWithUpperCase() {
        $("[data-test-id = city] input").sendKeys("РОСТОВ-НА-ДОНУ");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);

    }
}

