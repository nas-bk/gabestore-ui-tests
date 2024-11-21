package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final SelenideElement SEARCH_INPUT = $("input[name='search']"),
            GAMES_CATALOG_BUTTON = $(".b-header__container--search .btn"),
    MENU_ITEM = $(".b-menu__list");
    public final SelenideElement SEARCH_RESULT_WRAPPER = $(".b-search-result__wrapper");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open(Configuration.baseUrl);
        return this;
    }

    @Step("Ввести поисковой запрос")
    public MainPage setSearchQuery(String query) {
        SEARCH_INPUT.setValue(query);
        return this;
    }

    @Step("Открыть меню 'Каталог игр' ")
    public MainPage openMenu() {
        GAMES_CATALOG_BUTTON.click();
        return this;
    }

    @Step("Нажать на кнопку меню")
    public MainPage clickMenuItem(String item) {
        MENU_ITEM.$(byTagAndText("a", item))
                .shouldBe(enabled)
                .hover()
                .click();
        return this;
    }
}
