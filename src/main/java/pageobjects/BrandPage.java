package pageobjects;

import api.BrandPageData;
import helpers.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {

    @FindBy(css = ".title.oswald")
    private WebElement header1;
    @FindBy(css = ".justify")
    private WebElement description;

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHeader1() {
        if (hasHeader1()) {
            return header1;
        } else {
            return null;
        }
    }

    public WebElement getDescription() {
        if (hasDescription()) {
            return description;
        } else {
            return null;
        }
    }

    public BrandPageData getBrandData() {
        String header1 = hasHeader1() ?
                this.header1.getText()
                : "";
        String description = hasDescription() ?
                this.description.getText()
                : "";
        return DataFactory.createBrand(getPageData(), header1, description);
    }

    private boolean hasHeader1() {
        return !driver.findElements(By.cssSelector(".title.oswald")).isEmpty();
    }

    private boolean hasDescription() {
        return !driver.findElements(By.cssSelector(".justify")).isEmpty();
    }
}
