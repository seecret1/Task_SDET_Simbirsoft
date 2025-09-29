package test_main;

import org.junit.Test;
import test_settings.BaseSeleniumTest;

public class LoginTest extends BaseSeleniumTest {

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage();

        String name = "name";
        String password = "password";
        String email = "email@com.com";
        String message = "message";

        loginPage.login(
                name,
                password,
                email,
                message
        );
    }
}
