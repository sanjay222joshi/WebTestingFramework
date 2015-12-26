package pageobjects;

import api.BrandPageData;
import helpers.DataFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static api.PageConstants.DESCRIPTION_CSS;
import static api.PageConstants.HEADER1_CSS;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {

    @FindBy(css = HEADER1_CSS)
    private WebElement header1;
    @FindBy(css = DESCRIPTION_CSS)
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

    public boolean hasHeader1() {
        return !findElementsByCss(driver, HEADER1_CSS).isEmpty();
    }

    public boolean hasDescription() {
        return !findElementsByCss(driver, DESCRIPTION_CSS).isEmpty();
    }
}
