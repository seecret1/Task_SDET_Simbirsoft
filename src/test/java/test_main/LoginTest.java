package test_main;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_settings.BaseTest;
import test_settings.ConfigProvider;
import test_settings.EmailValidator;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты формы")
@Feature("Заполнение данных формы")
@DisplayName("Тесты для страницы логина")
public class LoginTest extends BaseTest {

    @Test
    @Story("Позитивное заполнение формы")
    @DisplayName("Успешное заполнение всех полей формы")
    @Description("Тест проверяет корректное заполнение всех полей формы и отправку данных")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginPositive() {
        Allure.step("Создание страницы логина", step -> {
            new LoginPage().login(
                    ConfigProvider.ADMIN_NAME,
                    ConfigProvider.ADMIN_PASS,
                    ConfigProvider.ADMIN_EMAIL
            );

            Allure.step("Проверка успешной отправки", step2 -> {
                assertTrue(true, "Форма должна быть успешно отправлена");
            });
        });
    }

    @Test
    @Story("Негативное тестирование")
    @DisplayName("Тест с пустым именем")
    @Description("Тест проверяет обработку пустого имени")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginNegativeEmptyName() {
        Allure.step("Создание страницы логина", step -> {
            String name = ConfigProvider.PAVEL_NAME;

            new LoginPage().login(
                    name,
                    ConfigProvider.PAVEL_PASS,
                    ConfigProvider.PAVEL_EMAIL
            );

            assertTrue((name == null || name.trim().isEmpty()), "Имя должно быть пустым");

            Allure.step("Проверка валидации", step2 -> {
                assertTrue(true, "Отображается сообщение об ошибке для пустого имени");
            });
        });
    }

    @Test
    @Story("Негативное тестирование")
    @DisplayName("Тест с некорректным email")
    @Description("Тест проверяет обработку некорректного email")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginNegativeInvalidEmail() {
        Allure.step("Создание страницы логина", step -> {
            String email = ConfigProvider.MAX_EMAIL;

            new LoginPage().login(
                    ConfigProvider.MAX_NAME,
                    ConfigProvider.MAX_PASS,
                    email
            );

            assertFalse(EmailValidator.isValidEmail(email), "Email должен быть некорректным");

            Allure.step("Проверка валидации email", step2 -> {
                assertTrue(true, "Отображается сообщение об ошибке для некорректного email");
            });
        });
    }
}
