package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage {

    private final SelenideElement cartIcon = $(".b-header__icons .js-cart-icon"),
            cartElement = $(".b-cart");
    private final ElementsCollection cartList = $(".b-cart__list").$$(".shop-item-inline");

    @Step("Открыть корзину")
    public CartPage openCart() {
        cartIcon.click();
        return this;
    }

    public CartPage checkCartIsEmpty() {
        cartElement.shouldHave(text("В вашей корзине еще ничего нет :( "));
        return this;
    }

    public CartPage checkCartIsNotEmpty() {
        cartElement.shouldNotHave(text("В вашей корзине еще ничего нет :( "));
        return this;
    }

    public CartPage checkNumberOfItemsInCart(Integer expectedValue) {
        assertThat(cartList).hasSize(expectedValue);
        return this;
    }
}
