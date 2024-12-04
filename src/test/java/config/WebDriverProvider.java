package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getProperties());
    private final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    public void setConfiguration() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser().toLowerCase();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        String host = String.format("https://%s:%s@%s/wd/hub", authConfig.login(), authConfig.password(), authConfig.getRemoteUrl());
        Configuration.pageLoadTimeout = 100000;
        Configuration.timeout = 20000;

        if (config.isRemote()) {
            Configuration.remote = host;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}
