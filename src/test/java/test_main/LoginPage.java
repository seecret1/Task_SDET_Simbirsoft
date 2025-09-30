package test_main;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul")
    private WebElement automationToolsList;

    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LoginPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    public LoginPage login(String name, String pass, String email) {
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

        String longestTool = new ItemAutomationTools().getItemAutomationTools();
        inputMessage.sendKeys(longestTool);

        js.executeScript("arguments[0].click();", submitBtn);

        return this;
    }

    public class ItemAutomationTools {
        public String getItemAutomationTools() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(automationToolsList));

            List<WebElement> options = automationToolsList.findElements(By.tagName("li"));

            System.out.println("Найдено options: " + options.size());
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
            System.out.println("Выбранный текст: " + result);
            return result;
        }
    }
}