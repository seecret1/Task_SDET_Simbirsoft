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

    String DEMO_PASS = readConfig().getString("userParams.demo.password");
}
