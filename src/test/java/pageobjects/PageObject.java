package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jorge on 11-01-2016.
 */
public abstract class PageObject {

    protected final WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
