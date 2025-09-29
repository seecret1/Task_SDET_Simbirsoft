package test_main;

import test_settings.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public LoginPage() {
        driver.get("https://practice-automation.com/form-fields/");
        PageFactory.initElements(driver, this);
    }

    public LoginPage login(
            String name, String pass, String email, String message
    ) {
        inputName.sendKeys(name);
        inputPass.sendKeys(pass);
        checkBoxMilk.click();
        checkBoxCoffee.click();
        radioBtnYellow.click();
        selectItem.click();
        selectItemValue.click();
        inputEmail.sendKeys(email);
        inputMessage.sendKeys(message);

        return this;
    }

}
