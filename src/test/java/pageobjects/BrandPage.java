package pageobjects;

import api.BrandPageData;
import factory.DataFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pageobjects.PageConstants.DESCRIPTION_CSS;
import static pageobjects.PageConstants.HEADER1_CSS;
import static pageobjects.PageConstants.LOGO_CSS;
import static helpers.SeleniumFinder.findElementsByCss;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {

    @FindBy(css = HEADER1_CSS)
    private WebElement header1;
    @FindBy(css = DESCRIPTION_CSS)
    private WebElement description;
    @FindBy(css = LOGO_CSS)
    private WebElement logo;

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

    public WebElement getLogo() {
        if(hasLogo()) {
            return logo;
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

    public boolean hasLogo() {
        return !findElementsByCss(driver, LOGO_CSS).isEmpty();
    }
}
