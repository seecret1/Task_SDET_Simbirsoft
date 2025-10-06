package test_main;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import test_settings.BaseTest;
import test_settings.ConfigProvider;
import test_settings.EmailValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginPositive() {
        Allure.step("Test Login Positive");
        new LoginPage().login(
                ConfigProvider.ADMIN_NAME,
                ConfigProvider.ADMIN_PASS,
                ConfigProvider.ADMIN_EMAIL
        );
    }

    @Test
    public void testLoginNegativeEmptyName() {
        Allure.step("Test Login Negative. Name not correct");
        String name = ConfigProvider.PAVEL_NAME;

         new LoginPage().login(
                 name,
                 ConfigProvider.PAVEL_PASS,
                 ConfigProvider.PAVEL_EMAIL
         );

        assertFalse((name == null || name.trim().isEmpty()), "Name null or empty");
    }

    @Test
    public void testLoginNegativeInvalidEmail() {
        Allure.step("Test Login Negative. Email not correct");
        String email = ConfigProvider.MAX_EMAIL;

        new LoginPage().login(
                ConfigProvider.MAX_NAME,
                ConfigProvider.MAX_PASS,
                email
        );

        assertTrue(EmailValidator.isValidEmail(email), "Email incorrect format");
    }
}