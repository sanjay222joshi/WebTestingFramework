package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.factory.DataFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByCss;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {

    @FindBy(css = PageConstants.HEADER1_CSS)
    private WebElement header1;
    @FindBy(css = PageConstants.DESCRIPTION_CSS)
    private WebElement description;
    @FindBy(css = PageConstants.LOGO_CSS)
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
        return !findElementsByCss(driver, PageConstants.HEADER1_CSS).isEmpty();
    }

    public boolean hasDescription() {
        return !findElementsByCss(driver, PageConstants.DESCRIPTION_CSS).isEmpty();
    }

    public boolean hasLogo() {
        return !findElementsByCss(driver, PageConstants.LOGO_CSS).isEmpty();
    }
}
