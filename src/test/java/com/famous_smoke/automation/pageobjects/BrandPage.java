package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.data.PageDataFactory;
import com.famous_smoke.automation.navigation.Navigator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByCss;

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
    @FindBy(css = PageConstants.VIDEO_CSS)
    private static WebElement video;

    public static BrandPageData getBrandData() {
        String header1Text = hasHeader1() ?
                header1.getText()
                : "";
        String descriptionText = hasDescription() ?
                description.getText()
                : "";
        return PageDataFactory.createBrand(getPageData(), header1Text, descriptionText, isIdentified());
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

    public static boolean hasVideo() {
        return !findElementsByCss(Navigator.driver, PageConstants.VIDEO_CSS).isEmpty();
    }

    public static boolean isIdentified() {
        return hasLogo() || hasVideo();
    }
}
