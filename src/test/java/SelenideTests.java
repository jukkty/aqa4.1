import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {

    public String getPlanningDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:7777");
    }

    @Test
    @DisplayName("Нормальный ввод данных +3 дня")
    void shouldUseCalendar() {
        String planningDate = getPlanningDate(3, "dd.MM.yyyy");
        ///
        $("[placeholder='Город']").sendKeys("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").sendKeys("Якимов Дмитрий");
        $("[name=phone]").sendKeys("+79770000000");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content").waitUntil(visible, 15000)
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    @DisplayName("Advanced ввод данных +7 дней")
    void shouldUseCalendarAdvanced() {
        String planningDate = getPlanningDate(7, "dd");
        String planningDateWithFullPattern = getPlanningDate(7, "dd.MM.yyyy");
        ///
        $("[placeholder='Город']").sendKeys("Мо");
        $$(".menu-item__control").findBy(text("Москва")).click();
        $("[placeholder='Дата встречи']").click();
//        $("div[data-step='1']").click(); Кнопка смены месяца
        $("table[class='calendar__layout']").find(byText(planningDate)).click();
        $("[name=name]").sendKeys("Якимов Дмитрий");
        $("[name=phone]").sendKeys("+79770000000");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content").waitUntil(visible, 15000)
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDateWithFullPattern));
    }

}



