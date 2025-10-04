package test_settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

abstract public class BaseSeleniumTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        BaseSeleniumPage.setDriver(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
