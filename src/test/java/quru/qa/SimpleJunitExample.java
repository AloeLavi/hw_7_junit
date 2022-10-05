package quru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import quru.qa.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJunitExample {

    @Disabled
    @Test
    void simpleTest(){

    }
    @Disabled

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
@Disabled
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
    @Disabled

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


    static Stream<Arguments> selenideSiteButtonsTextDataProvider() {
        return Stream.of(
                Arguments.of(List.of("Ofertas del Día", "Servicio al Cliente", "Listas", "Tarjetas de Regalo", "Vender"), "español"),
                Arguments.of(List.of("Angebote des Tages", "Kundenservice", "Wunschlisten", "Geschenkkarten", "Verkaufen bei Amazon"), "Deutsch")
        );
    }

    @MethodSource("selenideSiteButtonsTextDataProvider")
    @ParameterizedTest(name = "Проверка кнопок меню в зависимости от локали: {1}")
    void tutuParameterizedTestWithCityAndTime(List<String> buttons, Locale locale){
        open("https://www.amazon.com/");
        $(".icp-nav-link-inner").click();
        $(withText(String.valueOf(locale))).click();
        $(".a-button-input").click();
        $$(".nav-progressive-content a").filter(visible).shouldHave(CollectionCondition.texts(buttons));

    }

}
