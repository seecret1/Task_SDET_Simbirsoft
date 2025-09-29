package test_main;

import org.junit.Test;
import test_settings.BaseSeleniumTest;

public class Testing extends BaseSeleniumTest {

    @Test
    public void check() {
        String name = "standard_user";
        String password = "secret_sauce";
        TestPage testPage = new TestPage();
        testPage.testPage(name, password);
    }
}
