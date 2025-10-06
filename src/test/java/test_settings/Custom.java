package test_settings;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Custom implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        try {
            String elementText = element.getText();
            if (elementText != null && !elementText.trim().isEmpty()) {
                Allure.step("Click on element with text: '" + elementText + "'");
            } else {
                String value = element.getAttribute("value");
                if (value != null && !value.trim().isEmpty()) {
                    Allure.step("Click on element with value: '" + value + "'");
                } else {
                    Allure.step("Click on element");
                }
            }
        } catch (Exception e) {
            Allure.step("Click on element");
        }
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        Allure.step("Finding element by: " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        if (result != null) {
            Allure.step("Element found successfully");
        }
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        if (keysToSend != null && keysToSend.length > 0) {
            Allure.step("Enter text: '" + Arrays.toString(keysToSend) + "'");
        }
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        Allure.step("Error in method: " + method.getName() + " - " + e.getTargetException().getMessage());
    }
}