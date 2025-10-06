package test_main;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import test_settings.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test_settings.ConfigProvider;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(id = "name-input")
    private WebElement inputName;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPass;

    @FindBy(id = "drink2")
    private WebElement checkBoxMilk;

    @FindBy(id = "drink3")
    private WebElement checkBoxCoffee;

    @FindBy(id = "color3")
    private WebElement radioBtnYellow;

    @FindBy(id = "automation")
    private WebElement selectItem;

    @FindBy(xpath = "//option[@value='undecided']")
    private WebElement selectItemValue;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "message")
    private WebElement inputMessage;

    @FindBy(id = "submit-btn")
    private WebElement submitBtn;

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul")
    private WebElement automationToolsList;

    private WebDriverWait wait;

    public LoginPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public LoginPage login(String name, String pass, String email) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        inputName.sendKeys(name);
        System.out.println("Enter name");
        Allure.step("Enter name");

        inputPass.sendKeys(pass);
        System.out.println("Enter password");
        Allure.step("Enter password");

        js.executeScript("arguments[0].click();", checkBoxMilk);
        System.out.println("Click checkBoxMilk");
        Allure.step("Click checkBoxMilk");

        js.executeScript("arguments[0].click();", checkBoxCoffee);
        System.out.println("Click checkBoxCoffee");
        Allure.step("Click checkBoxCoffee");

        js.executeScript("arguments[0].click();", radioBtnYellow);
        System.out.println("Click radioButtonYellow");
        Allure.step("Click radioButtonYellow");

        wait.until(ExpectedConditions.visibilityOf(selectItem));
        Select dropdown = new Select(selectItem);
        System.out.println("Click selectItem");
        Allure.step("Click selectItem");

        dropdown.selectByIndex(3);
        System.out.println("Click selectItemValue");
        Allure.step("Click selectItemValue");

        inputEmail.sendKeys(email);
        System.out.println("Enter email");
        Allure.step("Enter email");

        String longestTool = getItemAutomationTools();
        inputMessage.sendKeys(longestTool);
        System.out.println("Enter message");
        Allure.step("Enter message");

        js.executeScript("arguments[0].click();", submitBtn);
        System.out.println("Click submitBtn");
        Allure.step("Click submitBtn");
        return this;
    }

    private String getItemAutomationTools() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(automationToolsList));

        List<WebElement> options = automationToolsList.findElements(By.tagName("li"));

        System.out.println("Options: " + options.size());
        for (WebElement option : options) {
            System.out.println("Option text: '" + option.getText() + "'");
        }

        if (options.isEmpty()) {
            return "No options found";
        }

        int max = 0;
        WebElement longestOption = null;

        for (WebElement option : options) {
            String text = option.getText().trim();
            if (!text.isEmpty() && max < text.length()) {
                max = text.length();
                longestOption = option;
            }
        }

        String result = longestOption != null ? longestOption.getText().trim() : "No suitable option";
        System.out.println("Choose text: " + result);
        return result;
    }
}