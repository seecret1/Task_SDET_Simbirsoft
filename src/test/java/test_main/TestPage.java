package test_main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test_settings.BaseSeleniumPage;

public class TestPage extends BaseSeleniumPage {

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public TestPage() {
        driver.get("https://www.saucedemo.com/");
        PageFactory.initElements(driver, this);
    }

    public TestPage testPage(
            String name, String pass
    ) {
        userName.sendKeys(name);
        password.sendKeys(pass);
        loginButton.click();

        return this;
    }

}
