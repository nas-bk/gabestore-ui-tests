package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "classpath:remote.properties"
})
public interface AuthConfig extends Config {

    String login();

    String password();

    @Key("remoteUrl")
    String getRemoteUrl();
}
