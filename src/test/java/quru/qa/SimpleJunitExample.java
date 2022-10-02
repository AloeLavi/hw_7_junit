package quru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("Проверка подбора билетов на самолет из Москвы в Пермь")
    @Test
    void tutuSimpleTest(){
        open("https://www.tutu.ru/");
        $(byName("city_from")).setValue("Москва").pressEnter();
        $(byName("city_to")).setValue("Пермь").pressEnter();
        $(byName("date_from")).setValue("01.01.2023").pressEnter();
        $(byClassName("j-submit_button")).click();
        $(byClassName("Ibn0I0o1tdeDjL4-YTn1n")).shouldHave(text("Москва — Пермь"));
    }

    @ValueSource(strings = {"Казань", "Сочи"})
    @ParameterizedTest(name = "Проверка подбора билетов на самолет из Москвы в {0}")
    void tutuParameterizedTest(String testData){
        open("https://www.tutu.ru/");
        $(byName("city_from")).setValue("Москва").pressEnter();
        $(byName("city_to")).setValue(testData).pressEnter();
        $(byName("date_from")).setValue("01.01.2023").pressEnter();
        $(byClassName("j-submit_button")).click();
        $(byClassName("Ibn0I0o1tdeDjL4-YTn1n")).shouldHave(text("Москва — "+ testData));
    }

    @CsvSource({
            "https://www.tutu.ru/poezda/, Москва, Сочи",
              "https://www.tutu.travel/poezda/, Moscow, Sochi"
            })
    @ParameterizedTest(name = "Проверка подбора билетов на поезд из Москвы в Сочи")
    void tutuParameterizedTestWithCityAndTime(String site, String city1, String city2){
        open(site);
        $(byName("schedule_station_from")).setValue(city1).pressEnter();
        $(byName("schedule_station_to")).setValue(city2);
        $(byClassName("j-date_to")).setValue("01.01.2023");
        $(byClassName("j-button-submit")).click();
        $(byClassName("_68Sr6IM8-eKoi8iow9l1e")).shouldHave(text(city1+" — "+ city2));
    }

}
