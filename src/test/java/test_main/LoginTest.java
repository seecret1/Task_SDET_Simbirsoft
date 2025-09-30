package test_main;

import org.junit.Test;
import test_settings.BaseSeleniumTest;
import test_settings.ConfigProvider;

public class LoginTest extends BaseSeleniumTest {

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage();

        loginPage.login(
                ConfigProvider.ADMIN_NAME,
                ConfigProvider.ADMIN_PASS,
                ConfigProvider.ADMIN_EMAIL
        );
    }
}
