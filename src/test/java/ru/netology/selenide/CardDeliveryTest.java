package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

 class CardDeliveryTest {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    @BeforeEach
    void setup(){
        open("http://localhost:9999/");
        SelenideElement form = $ ("[action]");
    }
     @Test
     void ShouldAssignDelivery(){
         $("[data-test-id = city] input").sendKeys("Москва");
         $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
         $("[data-test-id = date] input").sendKeys(date);
         $("[data-test-id=name] input").sendKeys("Иванов Иван");
         $("[data-test-id=phone] input").sendKeys("+79280000000");
         $("[data-test-id=agreement]").click();
         $(byText("Забронировать")).click();
         $(".notification__content").waitUntil(Condition.visible,15000).shouldHave(text(date));

     }
     @Test
     void ShouldAssignDeliveryIfNameWithHyphen(){
         $("[data-test-id = city] input").sendKeys("Москва");
         $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
         $("[data-test-id = date] input").sendKeys(date);
         $("[data-test-id=name] input").sendKeys("Иванов-Петров Иван");
         $("[data-test-id=phone] input").sendKeys("+79280000000");
         $("[data-test-id=agreement]").click();
         $(byText("Забронировать")).click();
         $(".notification__content").waitUntil(Condition.visible,15000).shouldHave(text(date));

     }
    @Test
    void ShouldAssignDeliveryIfCityWithHyphen(){
        $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(date);
        $("[data-test-id=name] input").sendKeys("Иванов Иван");
        $("[data-test-id=phone] input").sendKeys("+79280000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content").waitUntil(Condition.visible,15000).shouldHave(text(date));

    }
     @Test
     void ShouldAssignDeliveryIfNameInUpperCase(){
         $("[data-test-id = city] input").sendKeys("Ростов-на-Дону");
         $("[data-test-id = date] input").doubleClick().sendKeys(Keys.DELETE);
         $("[data-test-id = date] input").sendKeys(date);
         $("[data-test-id=name] input").sendKeys("ИВАНОВ ИВАН");
         $("[data-test-id=phone] input").sendKeys("+79280000000");
         $("[data-test-id=agreement]").click();
         $(byText("Забронировать")).click();
         $(".notification__content").waitUntil(Condition.visible,15000).shouldHave(text(date));
     }
}
