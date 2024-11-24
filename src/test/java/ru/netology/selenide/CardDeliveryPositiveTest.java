package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryPositiveTest {


        @BeforeEach
        void setUp() {
            open("http://localhost:9999");
        }
    private String generateDate(long addDays, String pattern) {

        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


        @Test
        public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

            $("[data-test-id=\"city\"] [placeholder='Город']").setValue("Москва");
            String planningDate =generateDate(4,"dd.MM.yyyy");
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.SHIFT,Keys.HOME,Keys.DELETE);
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
            $("[data-test-id=name] [type=text]").setValue("Валентина Гришкова");
            $("[data-test-id=phone] [type=tel]").setValue("+79351175683");
            $("[data-test-id=agreement]").click();
            $("[role=button] .button__content").click();
            $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
            $("[data-test-id='notification'] .notification__content")
                    .shouldHave(Condition.exactText("Встреча успешно забронирована на "+ planningDate ));
        }

        // 2.Отправка звяки с указанием фамилии через дефис
        @Test
        public void shouldreturnlastNameWitHyphen() {

            $("[data-test-id=\"city\"] [placeholder='Город']").setValue("Москва");
            String planningDate =generateDate(4,"dd.MM.yyyy");
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").sendKeys( Keys.SHIFT,Keys.HOME,Keys.DELETE);
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
            $("[data-test-id=name] [type=text]").setValue("Валентина Гришкова-Егорова");
            $("[data-test-id=phone] [type=tel]").setValue("+79351175683");
            $("[data-test-id=agreement]").click();
            $("[role=button] .button__content").click();
            $(withText("Успешно!")).shouldBe(Condition.visible,Duration.ofMillis(15000));
            $("[data-test-id='notification'] .notification__content")
                    .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));

        }

        // 3.Отправка заявки с указанием городом через дефиз
        @Test
        public void dataWithCitythroughHyphen() {

            $("[data-test-id=\"city\"] [placeholder='Город']").setValue("Ростов-на-Дону");
            String planningDate =generateDate(4,"dd.MM.yyyy");
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.SHIFT,Keys.HOME,Keys.DELETE);
            $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
            $("[data-test-id=name] [type=text]").setValue("Валентина Гришкова");
            $("[data-test-id=phone] [type=tel]").setValue("+79351175683");
            $("[data-test-id=agreement]").click();
            $("[role=button] .button__content").click();
            $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
            $("[data-test-id='notification'] .notification__content")
                    .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));

        }
    }


