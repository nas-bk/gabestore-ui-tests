package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.RandomNumberGenerator.getRandomNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage {
    private final SelenideElement titlePage = $(".b-title-with-filter h1"),
            loginMenu = $(".b-login"),
            filterCleanButton = $(".js-clear-filter"),
            gameCounter = $(".js-catalog-game-count"),
            additionalFilters = $(".b-filter__hidden"),
            openFilterBtn = $(".b-filter__mainfilter .js-open-filter");
    private final ElementsCollection filterSelect = $$(".b-multiple-select"),
            gameCatalog = $$(".b-catalog__wrapper .shop-item");


    @Step("Открыть страницу с каталогом товаров")
    public CatalogPage openCatalog() {
        open("/catalog");
        return this;
    }

    @Step("Нажать на кнопку 'Все фильтры' ")
    public CatalogPage openAllFilters() {
        openFilterBtn.click();
        return this;
    }

    @Step("Добавить игру в избранное")
    public CatalogPage addRandomGameToFavorites() {
        String favoriteBtnSelector = ".shop-item__favorite";
        gameCatalog
                .get(getRandomNumber(5))
                .$(favoriteBtnSelector)
                .hover()
                .shouldBe(enabled)
                .click();
        return this;
    }

    @Step("Выбрать фильтр")
    public CatalogPage setRandomFilter(String filter, String filter_locator) {
        String filterList = ".b-multiple-select__list-item";
        filterSelect.findBy(text(filter)).click();
        ElementsCollection list = $(filter_locator).$$(filterList);
        list.get(getRandomNumber(5)).hover().click();
        return this;
    }

    @Step("Добавить игру в корзину")
    public CatalogPage addRandomGameToCart() {
        String addToCartBtn = ".js-addToCart";
        gameCatalog
                .get(getRandomNumber(5))
                .$(addToCartBtn)
                .hover()
                .shouldBe(enabled)
                .click();
        return this;
    }

    public String getQuantityOfGoods() {
        return gameCounter.getAttribute("textContent");
    }

    public CatalogPage checkThatPageTitleMatches(String titlePage) {
        assertThat(this.titlePage.getText()).isEqualTo(titlePage);
        return this;
    }

    public CatalogPage checkAuthFormIsOpen() {
        assertThat(loginMenu.isDisplayed()).isTrue();
        return this;
    }

    public CatalogPage checkFilterCleanButtonIsVisible() {
        filterCleanButton.shouldBe(visible);
        return this;
    }

    public CatalogPage checkValueChange(String value) {
        gameCounter.shouldNotHave(text(value));
        return this;
    }

    public CatalogPage checkAdditionalFiltersIsEnabled() {
        assertThat(additionalFilters.isEnabled()).isTrue();
        return this;
    }
}
