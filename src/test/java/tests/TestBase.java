package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CatalogPage;
import pages.MainPage;

public class TestBase {
    static WebDriverProvider driverConfig = new WebDriverProvider();
    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();

    @BeforeAll
    static void beforeAll() {
        driverConfig.createWebDriver();
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
