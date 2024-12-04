package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.qameta.allure.Allure.step;

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
                catalogPage.checkAuthFormIsOpen());
    }

    @ParameterizedTest(name = "Тестирование работы фильтра {0} ")
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/filter.csv", numLinesToSkip = 1)
    void filterGameItemsTest(String filter, String locator) {
        catalogPage.openCatalog();
        var countPrev = catalogPage.getQuantityOfGoods();
        catalogPage.openAllFilters()
                .setRandomFilter(filter, locator);

        step("Проверить, что отобразилась кнопка удаления фильтра", () ->
                catalogPage.checkFilterCleanButtonIsVisible());

        step("Проверить, что изменилось количество игр в каталоге.", () ->
                catalogPage.checkValueChange(countPrev));
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
                catalogPage.checkAdditionalFiltersIsEnabled());
    }

    @Test
    @Owner("Bochkareva Anastasia")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование добавления товара в корзину")
    void addGameToCart() {
        catalogPage.openCatalog()
                .addRandomGameToCart();

        cartPage.openCart();

        step("Проверить, что список товаров в корзине равен количеству добавленного товара", () -> {
            cartPage.checkCartIsNotEmpty()
                    .checkNumberOfItemsInCart(1);
        });
    }
}