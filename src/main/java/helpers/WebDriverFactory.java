package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by jorge on 24-12-2015.
 */
public class WebDriverFactory {

    private WebDriverFactory() {
        // not called
    }

    public static WebDriver createSilentDriver(final String url) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);
        return driver;
    }

}
