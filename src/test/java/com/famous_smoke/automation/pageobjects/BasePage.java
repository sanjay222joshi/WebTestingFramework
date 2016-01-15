package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.api.PageData;
import com.famous_smoke.automation.factory.DataFactory;
import com.famous_smoke.automation.helpers.Navigator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByCss;
import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByName;
import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByXpath;

/**
 * Created by jorge on 21-12-2015.
 */
public class BasePage extends PageObject {

    @FindBy(xpath = PageConstants.CANONICAL_XPATH)
    private static WebElement canonical;
    @FindBy(name = PageConstants.META_DESCRIPTION_NAME)
    private static WebElement metaDescription;
    @FindBy(css = PageConstants.BREADCRUMBS_CSS)
    private static WebElement breadcrumbs;
    @FindBy(name = PageConstants.PROMO_FORM_NAME)
    private static WebElement promoForm;
    @FindBy(css = PageConstants.PROMO_CLOSE_LINK_CSS)
    private static WebElement promoCloseLink;

    public static Integer getBreadcrumbsCount(){
        return findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS).size();
    }

    public static void clickBreadcrumb(final Integer breadcrumbIndex){
        findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS)
                .get(breadcrumbIndex)
                .click();
    }

    public static void closePromo() {
        promoCloseLink.click();
    }

    public static boolean hasCanonical() {
        return !findElementsByXpath(Navigator.driver, PageConstants.CANONICAL_XPATH).isEmpty();
    }

    public static boolean hasMetaDescription() {
        return !findElementsByName(Navigator.driver, PageConstants.META_DESCRIPTION_NAME).isEmpty();
    }

    public static boolean hasBreadcrumbs() {
        return !findElementsByCss(Navigator.driver, PageConstants.BREADCRUMBS_CSS).isEmpty();
    }

    public static boolean hasPromo() {
        return !findElementsByName(Navigator.driver, PageConstants.PROMO_FORM_NAME).isEmpty()
                && promoForm.isDisplayed();
    }

    public static PageData getPageData() {
        String url = Navigator.driver.getCurrentUrl();
        String title = Navigator.driver.getTitle();
        String canonicalText = hasCanonical() ?
                canonical.getAttribute(PageConstants.ATTRIBUTE_HREF)
                : "";
        String metaDescriptionText = hasMetaDescription() ?
                metaDescription.getAttribute(PageConstants.ATTRIBUTE_CONTENT)
                : "";
        String breadcrumbsText = hasBreadcrumbs()?
                breadcrumbs.getText()
                : "";
        ArrayList<String> breadcrumbsLinks = new ArrayList<>();
        if (hasBreadcrumbs()) {
            breadcrumbsLinks.addAll(
                    findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS)
                            .stream()
                            .map(breadcrumb -> breadcrumb.getAttribute(PageConstants.ATTRIBUTE_HREF))
                            .collect(Collectors.toList())
                    );
        }
        return DataFactory.createPage(
                url, title, canonicalText,
                metaDescriptionText,
                breadcrumbsText, breadcrumbsLinks);
    }

}
