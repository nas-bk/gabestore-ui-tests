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
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Действия пользователя в каталоге")
public class CatalogTests extends TestBase {

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование добавления товара в избранное без авторизации")
    void addGameToFavoritesWithoutAuth() {
        catalogPage.openCatalog()
                .addRandomGameToFavorites();

        step("Проверить открытие формы Вход/Регистрации", () ->
                assertThat(catalogPage.LOGIN_MENU.isDisplayed()).isTrue());
    }

    @ParameterizedTest(name = "Тестирование работы фильтра {0} ")
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/filter.csv", numLinesToSkip = 1)
    void filterGameItemsTest(String filter, String locator) {
        catalogPage.openCatalog();
        var countPrev = catalogPage.GAME_COUNTER.getAttribute("textContent");
        catalogPage.openAllFilters()
                .setRandomFilter(filter, locator);

        step("Проверить, что отобразилась кнопка удаления фильтра", () ->
                catalogPage.CLEAR_FILTER_BUTTON.shouldBe(visible));

        step("Проверить, что изменилось количество игр в коталоге.", () ->
                catalogPage.GAME_COUNTER.shouldNotHave(text(countPrev)));
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тестирование работы кнопки 'Все фильтры' ")
    void openAllFiltersTest() {
        catalogPage
                .openCatalog()
                .openAllFilters();

        step("Проверить, что открылась форма с дополнительными фильтрами", () ->
                assertThat(catalogPage.ADDITIONAL_FILTERS.isEnabled()).isTrue());
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование добавления товара в корзину")
    void addGameToCart() {
        catalogPage.openCatalog()
                .addRandomGameToCart()
                .openCart();

        step("Проверить, что список товаров в корзине равен количеству добавленного товара", () -> {
                catalogPage.CART.shouldNotHave(text("В вашей корзине еще ничего нет :( "));
                assertThat(catalogPage.CART_LIST).hasSize(1);
        });
    }
}