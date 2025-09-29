package test_main;

import org.openqa.selenium.support.ui.Select;
import test_settings.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test_settings.ConfigProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BaseSeleniumPage {

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

    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LoginPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    public LoginPage login(String name, String pass, String email, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        inputName.sendKeys(name);
        inputPass.sendKeys(pass);

        js.executeScript("arguments[0].click();", checkBoxMilk);
        js.executeScript("arguments[0].click();", checkBoxCoffee);
        js.executeScript("arguments[0].click();", radioBtnYellow);

        wait.until(ExpectedConditions.visibilityOf(selectItem));
        Select dropdown = new Select(selectItem);
        dropdown.selectByIndex(3);

        inputEmail.sendKeys(email);
        inputMessage.sendKeys(message);

        js.executeScript("arguments[0].click();", submitBtn);

        return this;
    }

    public class ItemAutomationTools {
        public String getItemAutomationTools() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf());

            Select dropdown = new Select();

            List<WebElement> options = dropdown.getOptions();

            int max = 0;
            for (WebElement option : options) {
                if (max < option.getText().length())
                    max = option.getText().length();
            }
            for (WebElement option : options) {
                if (option.getText().length() == max) {
                    return option.getText();
                }
            }

            return null;
        }
    }
}