package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by jorge on 24-12-2015.
 */
public final class DriverFactory {

    private DriverFactory() {
        // not called
    }

    public static WebDriver createDefaultDriver(final String url){
        return createSilentDriver(url);
    }

    public static WebDriver createSilentDriver(final String url) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);
        return driver;
    }

}
