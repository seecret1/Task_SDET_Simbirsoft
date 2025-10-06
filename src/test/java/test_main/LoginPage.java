package test_main;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

    private final WebDriverWait wait;

    private final JavascriptExecutor js;

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

    @Step("Enter name")
    public void inputName(String name) {
        inputName.sendKeys(name);
        System.out.println("Enter name");
        Allure.step("Ввод имени");
    }

    @Step("Enter password")
    public void inputPass(String pass) {
        inputPass.sendKeys(pass);
        System.out.println("Enter password");
        Allure.step("Ввод пароля");
    }

    @Step("Click checkBo value = Milk")
    public void clickCheckBoxMilk() {
        js.executeScript("arguments[0].click();", checkBoxMilk);
        System.out.println("Click checkBoxMilk");
        Allure.step("Нажание на checkBox 'Milk'");
    }

    @Step("Click checkBox value = Coffee")
    public void clickCheckBoxCoffee() {
        js.executeScript("arguments[0].click();", checkBoxCoffee);
        System.out.println("Click checkBoxCoffee");
        Allure.step("Нажатие на checkBox 'Coffee'");
    }

    @Step("Click radioButton color = Yellow")
    public void clickRadioButtonYellow() {
        js.executeScript("arguments[0].click();", radioBtnYellow);
        System.out.println("Click radioButtonYellow");
        Allure.step("Нажатие на radioButton 'Yellow'");
    }

    @Step("Choose select item value")
    public void chooseSelectItem() {
        wait.until(ExpectedConditions.visibilityOf(selectItem));
        System.out.println("Click selectItem");
        Allure.step("Нажать на selectItem");
        Select dropdown = new Select(selectItem);
        dropdown.selectByIndex(3);
        System.out.println("Click selectItemValue");
        Allure.step("Выбрать значение selectItem");
    }

    @Step("Enter email")
    public void inputEmail(String email) {
        inputEmail.sendKeys(email);
        System.out.println("Enter email");
        Allure.step("Ввести логин");
    }

    @Step("Enter message")
    public void inputMessage() {
        String longestTool = getItemAutomationTools();
        inputMessage.sendKeys(longestTool);
        System.out.println("Enter message");
        Allure.step("Ввести самое длинное сообщение");
    }

    @Step("Click submit button")
    public void clickButtonSubmit() {
        js.executeScript("arguments[0].click();", submitBtn);
        System.out.println("Click submitBtn");
        Allure.step("Нажать на кнопку submitBtn");
    }

    public LoginPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(inputName));
    }

    public LoginPage login(String name, String pass, String email) {

        inputName(name);
        inputPass(pass);
        clickCheckBoxMilk();
        clickCheckBoxCoffee();
        clickRadioButtonYellow();
        chooseSelectItem();
        inputEmail(email);
        inputMessage();
        clickButtonSubmit();
        return this;
    }

    public String getItemAutomationTools() {
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
        Allure.step("Выбран текст: " + result);
        return result;
    }
}