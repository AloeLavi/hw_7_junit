package quru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleJunitExample {

    @Disabled
    @Test
    void simpleTest(){

    }

    @Test
    void tutuSimpleTest(){
        open("https://www.tutu.ru/");
        $(byName("city_from")).setValue("Москва").pressEnter();
        $(byName("city_to")).setValue("Казань").pressEnter();
        $(byName("date_from")).setValue("01.01.2023").pressEnter();
        $(byClassName("j-submit_button")).click();
        $(byClassName("Ibn0I0o1tdeDjL4-YTn1n")).shouldHave(text("Москва — Казань"));
    }

    @ValueSource
    @ParameterizedTest
    void tutuParameterizedTest(){
        open("https://www.tutu.ru/");
        $(byName("city_from")).setValue("Москва").pressEnter();
        $(byName("city_to")).setValue("Казань").pressEnter();
        $(byName("date_from")).setValue("01.01.2023").pressEnter();
        $(byClassName("j-submit_button")).click();
        $(byClassName("Ibn0I0o1tdeDjL4-YTn1n")).shouldHave(text("Москва — Казань"));
    }

}
