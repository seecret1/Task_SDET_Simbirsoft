package test_main;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_settings.BaseSeleniumTest;
import test_settings.ConfigProvider;
import test_settings.EmailValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseSeleniumTest {

    private final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void testLoginPositive() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(
                ConfigProvider.ADMIN_NAME,
                ConfigProvider.ADMIN_PASS,
                ConfigProvider.ADMIN_EMAIL
        );
    }

    @Test
    public void testLoginNegativeEmptyName() {
        String name = ConfigProvider.PAVEL_NAME;

         new LoginPage().login(
                 name,
                 ConfigProvider.PAVEL_PASS,
                 ConfigProvider.PAVEL_EMAIL
         );

        assertFalse("Name null or empty", (name == null || name.trim().isEmpty()));
    }

    @Test
    public void testLoginNegativeInvalidEmail() {
        String email = ConfigProvider.MAX_EMAIL;

        new LoginPage().login(
                ConfigProvider.MAX_NAME,
                ConfigProvider.MAX_PASS,
                email
        );

        assertTrue("Email incorrect format", EmailValidator.isValidEmail(email));
    }
}