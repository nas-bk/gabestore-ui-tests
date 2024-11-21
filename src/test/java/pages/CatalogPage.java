package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    public final SelenideElement TITLE_PAGE = $(".b-title-with-filter h1"),
            LOGIN_MENU = $(".b-login"),
            CLEAR_FILTER_BUTTON = $(".js-clear-filter"),
            GAME_COUNTER = $(".js-catalog-game-count"),
            ADDITIONAL_FILTERS = $(".b-filter__hidden"),
            CART = $(".b-cart");
    public final ElementsCollection FILTER_SELECT = $$(".b-multiple-select"),
            CART_LIST = $(".b-cart__list").$$(".shop-item-inline");
    private final ElementsCollection GAME_CATALOG = $$(".b-catalog__wrapper .shop-item");
    private final String FAVORITE_BUTTON_SELECTOR = ".shop-item__favorite",
            FILTER_LIST = ".b-multiple-select__list-item",
            ADD_TO_CART_BUT_LOCATOR = ".js-addToCart";
    private final SelenideElement OPEN_FILTER_BUTTON = $(".b-filter__mainfilter .js-open-filter"),
            OPEN_CART = $(".b-header__icons").$(".js-cart-icon");


    @Step("Открыть страницу с каталогом товаров")
    public CatalogPage openCatalog() {
        open("/catalog");
        return this;
    }

    @Step("Нажать на кнопку 'Все фильтры' ")
    public CatalogPage openAllFilters() {
        OPEN_FILTER_BUTTON.click();
        return this;
    }

    @Step("Добавить игру в избранное")
    public CatalogPage addRandomGameToFavorites() {
        GAME_CATALOG
                .get(getRandomNumber())
                .$(FAVORITE_BUTTON_SELECTOR)
                .hover()
                .shouldBe(enabled)
                .click();
        return this;
    }

    @Step("Выбрать фильтр")
    public CatalogPage setRandomFilter(String filter, String filter_locator) {
        FILTER_SELECT.findBy(text(filter)).click();
        ElementsCollection list = $(filter_locator).$$(FILTER_LIST);
        list.get(getRandomNumber()).hover().click();
        return this;
    }

    @Step("Добавить игру в корзину")
    public CatalogPage addRandomGameToCart() {
        GAME_CATALOG
                .get(getRandomNumber())
                .$(ADD_TO_CART_BUT_LOCATOR)
                .hover()
                .shouldBe(enabled)
                .click();
        return this;
    }

    @Step("Открыть корзину")
    public CatalogPage openCart() {
        OPEN_CART.click();
        return this;
    }

    private Integer getRandomNumber() {
        return (int) (Math.random() * 5);
    }

}
