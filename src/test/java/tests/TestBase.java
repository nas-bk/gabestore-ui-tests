package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CartPage;
import pages.CatalogPage;
import pages.MainPage;

public class TestBase {
    static final WebDriverProvider driverConfig = new WebDriverProvider();
    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();
    CartPage cartPage = new CartPage();

    @BeforeAll
    static void beforeAll() {
        driverConfig.setConfiguration();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
