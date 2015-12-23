package pageobjects;

import api.Brand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage implements Brand {

    @FindBy(css = ".title.oswald")
    private WebElement header1;
    @FindBy(css = ".justify")
    private WebElement description;

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getHeader1() {
        if (hasHeader1()) {
            return header1.getText();
        } else {
            return "";
        }
    }

    @Override
    public String getDescription() {
        if (hasDescription()) {
            return description.getText();
        } else {
            return "";
        }
    }

    private boolean hasHeader1() {
        return !driver.findElements(By.cssSelector(".title.oswald")).isEmpty();
    }

    private boolean hasDescription() {
        return !driver.findElements(By.cssSelector(".justify")).isEmpty();
    }
}
