package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Действия пользователя на главной странице сайта")
public class HeaderPanelTests extends TestBase {

    @ParameterizedTest(name = "Тестирование содержимого хэдер меню")
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void linkTest(String menuItem, String titlePage) {
        mainPage.openPage()
                .openMenu()
                .clickMenuItem(menuItem);

        step("Название страницы соответствует " + titlePage, () ->
                catalogPage.checkThatPageTitleMatches(titlePage));
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование поисковой строки магазина")
    void gameSearchTest() {
        String game = "Devil May Cry";
        mainPage.openPage()
                .setSearchQuery(game);

        step("Проверить, что открылся popup с результатами поиска", () -> {
            mainPage.checkPopupIsEnabled()
                    .checkThatResultMatchesSearchQuery(game);
        });
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тестирование работы поиска при пустом поисковом запросе")
    void unsuccessfulSearchWithEmptyQueryTest() {

        mainPage.openPage()
                .pressEnterOnSearchBar();

        step("Проверить, что url страницы не изменился", () ->
                assertThat(getWebDriver().getCurrentUrl()).isEqualTo(baseUrl + "/"));
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тестирование открытие корзины без авторизации")
    void cartOpenWithoutAuthTest() {

        mainPage.openPage();
        cartPage.openCart();

        step("Проверить, что открылась страница корзины", () ->
                assertThat(getWebDriver().getCurrentUrl()).isEqualTo(baseUrl + "/basket"));
        step("Проверить, что корзина пустая", () ->
                cartPage.checkCartIsEmpty());
    }
}
