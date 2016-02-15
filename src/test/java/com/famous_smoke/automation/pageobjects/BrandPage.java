package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.data.DataFactory;
import com.famous_smoke.automation.navigation.Navigator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByCss;

/**
 * <p>Represents the Brand pages of the site.</p>
 *
 * <p>The BrandPage extends from BasePage</p>
 */
public class BrandPage extends BasePage {

    /**
     * The Header One.
     */
    @FindBy(css = PageConstants.BRAND_HEADER1_CSS)
    private static WebElement header1;
    /**
     * The Description/SEO Paragraph.
     */
    @FindBy(css = PageConstants.BRAND_DESCRIPTION_CSS)
    private static WebElement description;
    /**
     * The Brand Logo.
     */
    @FindBy(css = PageConstants.BRAND_LOGO_CSS)
    private static WebElement logo;
    /**
     * The BrandVideo.
     */
    @FindBy(css = PageConstants.BRAND_VIDEO_CSS)
    private static WebElement video;

    /**
     * Creates the BrandPageData by a mixture
     * of the BasePageData and the WebElements
     * of the BrandPage.
     * @return the BrandPageData object with the
     * information of the current BrandPage.
     */
    public static BrandPageData getBrandData() {
        String header1Text = hasHeader1() ?
                header1.getText()
                : "";
        String descriptionText = hasDescription() ?
                description.getText()
                : "";
        return DataFactory.createBrandPage(getBasePageData(), header1Text, descriptionText, isIdentified());
    }

    /**
     * Evaluates if the page has a header one.
     * @return true if there are elements found
     * with the CSS of the header one.
     */
    public static boolean hasHeader1() {
        return !findElementsByCss(Navigator.driver, PageConstants.BRAND_HEADER1_CSS).isEmpty();
    }

    /**
     * Evaluates if the page has a description.
     * @return true if there are elements found
     * with the CSS of the description.
     */
    public static boolean hasDescription() {
        return !findElementsByCss(Navigator.driver, PageConstants.BRAND_DESCRIPTION_CSS).isEmpty();
    }

    /**
     * Evaluates if the page has a logo.
     * @return true if there are elements found
     * with the CSS of the logo.
     */
    public static boolean hasLogo() {
        return !findElementsByCss(Navigator.driver, PageConstants.BRAND_LOGO_CSS).isEmpty();
    }

    /**
     * Evaluates if the page has a video.
     * @return true if there are elements found
     * with the CSS of the video.
     */
    public static boolean hasVideo() {
        return !findElementsByCss(Navigator.driver, PageConstants.BRAND_VIDEO_CSS).isEmpty();
    }

    /**
     * Evaluates if the page is identified either
     * by a Logo or a Video.
     * @return true if the BrandPage has Logo or
     * Video.
     */
    public static boolean isIdentified() {
        return hasLogo() || hasVideo();
    }
}
