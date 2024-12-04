package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
    private final SelenideElement searchInput = $("input[name='search']"),
            gameCatalogBtn = $(".b-header__container--search .btn"),
            menuItem = $(".b-menu__list"),
            searchResultWrapper = $(".b-search-result__wrapper");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open(Configuration.baseUrl);
        return this;
    }

    @Step("Ввести поисковой запрос")
    public MainPage setSearchQuery(String query) {
        searchInput.setValue(query);
        return this;
    }

    @Step("Нажать Enter в строке поиска")
    public MainPage pressEnterOnSearchBar() {
        searchInput.pressEnter();
        return this;
    }

    @Step("Открыть меню 'Каталог игр' ")
    public MainPage openMenu() {
        gameCatalogBtn.click();
        return this;
    }

    @Step("Нажать на кнопку меню")
    public MainPage clickMenuItem(String item) {
        menuItem.$(byTagAndText("a", item))
                .shouldBe(enabled)
                .hover()
                .click();
        return this;
    }

    public MainPage checkPopupIsEnabled() {
        assertThat(searchResultWrapper.isEnabled()).isTrue();
        return this;
    }

    public MainPage checkThatResultMatchesSearchQuery(String query) {
        searchResultWrapper.shouldHave(Condition.text(query));
        return this;
    }
}
