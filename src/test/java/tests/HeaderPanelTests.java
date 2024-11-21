package tests;

import com.codeborne.selenide.Condition;
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

    @ParameterizedTest(name = "Тестирование содержимого хэдер меню")
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
    @DisplayName("Тестирование поисковой строки магазина")
    void gameSearchTest() {
        String game = "Devil May Cry";
        mainPage.openPage()
                .setSearchQuery(game)
                .SEARCH_RESULT_WRAPPER.shouldHave(text(game));

        step("Проверить, что открылся popup с результатами поиска", () -> {
                mainPage.SEARCH_RESULT_WRAPPER.shouldHave(Condition.text(game));
                assertThat(mainPage.SEARCH_RESULT_WRAPPER.isEnabled()).isTrue();});
    }
}
