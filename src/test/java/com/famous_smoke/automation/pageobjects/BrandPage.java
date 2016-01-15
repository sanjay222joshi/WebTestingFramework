package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.factory.DataFactory;
import com.famous_smoke.automation.helpers.Navigator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByCss;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {

    @FindBy(css = PageConstants.HEADER1_CSS)
    private static WebElement header1;
    @FindBy(css = PageConstants.DESCRIPTION_CSS)
    private static WebElement description;
    @FindBy(css = PageConstants.LOGO_CSS)
    private static WebElement logo;

    public static BrandPageData getBrandData() {
        String header1Text = hasHeader1() ?
                header1.getText()
                : "";
        String descriptionText = hasDescription() ?
                description.getText()
                : "";
        return DataFactory.createBrand(getPageData(), header1Text, descriptionText);
    }

    public static boolean hasHeader1() {
        return !findElementsByCss(Navigator.driver, PageConstants.HEADER1_CSS).isEmpty();
    }

    public static boolean hasDescription() {
        return !findElementsByCss(Navigator.driver, PageConstants.DESCRIPTION_CSS).isEmpty();
    }

    public static boolean hasLogo() {
        return !findElementsByCss(Navigator.driver, PageConstants.LOGO_CSS).isEmpty();
    }
}
