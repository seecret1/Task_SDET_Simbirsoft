package test_settings;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");
    String ADMIN_NAME = readConfig().getString("userParams.admin.name");
    String ADMIN_PASS = readConfig().getString("userParams.admin.password");
    String ADMIN_EMAIL = readConfig().getString("userParams.admin.login");

    String PAVEL_NAME = readConfig().getString("userParams.pavel.name");
    String PAVEL_PASS = readConfig().getString("userParams.pavel.password");
    String PAVEL_EMAIL = readConfig().getString("userParams.pavel.login");

    String MAX_NAME = readConfig().getString("userParams.max.name");
    String MAX_PASS = readConfig().getString("userParams.max.password");
    String MAX_EMAIL = readConfig().getString("userParams.max.login");
}
