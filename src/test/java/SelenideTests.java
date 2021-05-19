import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    @Test
    @DisplayName("Нормальный ввод данных +3 дня")
    void shouldUseCalendar() {
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_MONTH, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dateFormat.format(date.getTime());
        ///
        open("http://localhost:7777");
        $("[placeholder='Город']").sendKeys("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.CLEAR);
        $("[placeholder='Дата встречи']").setValue(today);
        $("[name=name]").sendKeys("Якимов Дмитрий");
        $("[name=phone]").sendKeys("+79770000000");
        $("span[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);
    }
    @Test
    @DisplayName("Advanced ввод данных +7 дней")
    void shouldUseCalendarAdvanced() {
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String today = dateFormat.format(date.getTime());
        ///
        open("http://localhost:7777");
        $("[placeholder='Город']").sendKeys("Мо");
        $("div[class='popup popup_direction_bottom-left popup_target_anchor popup_size_m popup_visible popup_height_adaptive popup_theme_alfa-on-white input__popup']").find(byText("Москва")).click();
        $("span[class='icon icon_size_m icon_name_calendar icon_theme_alfa-on-white']").click();
        $("table[class='calendar__layout']").find(byText(today)).click();
        $("[name=name]").sendKeys("Якимов Дмитрий");
        $("[name=phone]").sendKeys("+79770000000");
        $("span[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);
    }

}



