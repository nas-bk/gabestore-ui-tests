package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Действия пользователя на главной странице сайта")
public class HeaderPanelTests extends TestBase {

    @ParameterizedTest(name = "Проверить, что открывается страница {1} после нажатия кнопки {0} в главном меню")
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void linkTest(String menuItem, String titlePage) {
        mainPage.openPage()
                .openMenu()
                .clickMenuItem(menuItem);

        step("Название страницы соответствует {0} ", () ->
                assertThat(catalogPage.TITLE_PAGE.getText()).isEqualTo(titlePage));
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Отображается панель автоподбора с играми соответвующему поисковому запросу")
    void gameSearchTest() {
        String game = "Devil May Cry";
        mainPage.openPage()
                .setSearchQuery(game)
                .SEARCH_RESULT_WRAPPER.shouldHave(text(game));

        step("Проверить, что панель автоподбора отображена и найдены игры содержащие запрос", () -> {
                mainPage.SEARCH_RESULT_WRAPPER.shouldHave(text(game));
                assertThat(mainPage.SEARCH_RESULT_WRAPPER.isEnabled()).isTrue();});
    }
}
