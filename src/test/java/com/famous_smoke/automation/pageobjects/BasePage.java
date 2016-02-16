package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.DataFactory;
import com.famous_smoke.automation.navigation.Navigator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByCss;
import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByName;
import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByXpath;

/**
 * <p>Contains the common elements for all
 * the Pages we are validating in https://www.famous-smoke.com
 * using the framework.</p>
 */
public class BasePage {

    /**
     * The Canonical URL.
     */
    @FindBy(xpath = PageConstants.CANONICAL_XPATH)
    private static WebElement canonical;
    /**
     * The MetaDescription.
     */
    @FindBy(name = PageConstants.META_DESCRIPTION_NAME)
    private static WebElement metaDescription;
    /**
     * The Breadcrumbs section.
     */
    @FindBy(css = PageConstants.BREADCRUMBS_CSS)
    private static WebElement breadcrumbs;
    /**
     * The promo form that should show on all pages.
     */
    @FindBy(name = PageConstants.PROMO_FORM_NAME)
    private static WebElement promoForm;
    /**
     * The link that closes the promotion.
     */
    @FindBy(css = PageConstants.PROMO_CLOSE_LINK_CSS)
    private static WebElement promoCloseLink;

    /**
     * Sizes the WebElements that count as Breadcrumb
     * links in the Breadcrumbs section.
     * @return the number of breadcrumb links.
     */
    public static Integer getBreadcrumbsCount(){
        return findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS).size();
    }

    /**
     * Performs a click in the specified breadcrumb.
     *
     * It can access the list of breadcrumbs links
     * in the breadcrumbs section and select the one
     * based on the index.
     * @param breadcrumbIndex the index of the breadcrumb
     *                        link to click.
     */
    public static void clickBreadcrumb(final Integer breadcrumbIndex){
        findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS)
                .get(breadcrumbIndex)
                .click();
    }

    /**
     * Closes the promotion form.
     */
    public static void closePromo() {
        promoCloseLink.click();
    }

    /**
     * Evaluates if the page has a canonical url.
     * @return true if there are elements found
     * with the XPATH of the canonical URL.
     */
    public static boolean hasCanonical() {
        return hasXPATHElement(PageConstants.CANONICAL_XPATH);
    }

    /**
     * Evaluates if the page has a meta description.
     * @return true if there are elements found
     * with the Name of the meta description.
     */
    public static boolean hasMetaDescription() {
        return hasNameElement(PageConstants.META_DESCRIPTION_NAME);
    }

    /**
     * Evaluates if the page has breadcrumbs.
     * @return true if there are elements found
     * with the CSS of the breadcrumbs.
     */
    public static boolean hasBreadcrumbs() {
        return hasCSSElement(PageConstants.BREADCRUMBS_CSS);
    }

    /**
     * Evaluates if the page has a promotion form.
     * @return true if there are elements found
     * with the Name of the promotion form and
     * the form is displayed.
     */
    public static boolean hasPromo() {
        return !findElementsByName(Navigator.driver, PageConstants.PROMO_FORM_NAME).isEmpty()
                && promoForm.isDisplayed();
    }

    /**
     * Constructs a BasePageData object
     * evluating the information of the
     * WebElements of the page.
     *
     * This is needed for the construction
     * of the PageData of the child classes.
     * @return the BasePageData of the
     * current page.
     */
    public static BasePageData getBasePageData() {
        String url = Navigator.driver.getCurrentUrl();
        String title = Navigator.driver.getTitle();

        String canonicalText = extractElementAttribute(
                canonical,
                PageConstants.ATTRIBUTE_HREF,
                hasCanonical()
        );
        String metaDescriptionText = extractElementAttribute(
                metaDescription,
                PageConstants.ATTRIBUTE_CONTENT,
                hasMetaDescription()
        );
        String breadcrumbsText = extractElementText(breadcrumbs, hasBreadcrumbs());

        List<String> breadcrumbsLinks = findElementsByCss(breadcrumbs, PageConstants.BREADCRUMBS_LINKS_CSS)
                        .stream()
                        .map(breadcrumb -> breadcrumb.getAttribute(PageConstants.ATTRIBUTE_HREF))
                        .collect(Collectors.toList());

        return DataFactory.createBasePage(
                url, title, canonicalText,
                metaDescriptionText,
                breadcrumbsText, breadcrumbsLinks);
    }

    /**
     * Determines if the Page has an
     * Elements with the CSS class.
     * @param css the CSS class.
     * @return true if there is at
     * least one element with the
     * CSS class.
     */
    protected static boolean hasCSSElement(final String css) {
        return !findElementsByCss(Navigator.driver, css).isEmpty();
    }

    /**
     * Determines if the Page has an
     * Elements with the given Name.
     * @param name the Name of the
     *             element.
     * @return true if there is at
     * least one element with the
     * Name.
     */
    protected static boolean hasNameElement(final String name) {
        return !findElementsByName(Navigator.driver, name).isEmpty();
    }

    /**
     * Determines if the Page has an
     * Elements with the given XPATH.
     * @param xpath the XPATH of the
     *             element.
     * @return true if there is at
     * least one element with the
     * XPATH.
     */
    protected static boolean hasXPATHElement(final String xpath) {
        return !findElementsByXpath(Navigator.driver, xpath).isEmpty();
    }

    /**
     * Extracts the text content of
     * a WebElement validating a
     * check.
     * @param element the WebElement.
     * @param check the check to
     *              pass.
     * @return the text of the WebElement
     * or an empty String if the check
     * is failed.
     */
    protected static String extractElementText(final WebElement element,
                                               final Boolean check) {
        return check ? element.getText() : "";
    }

    /**
     * Extracts the value of a
     * given attribute of the
     * passed WebElement validating
     * a check.
     * @param element the WebElement.
     * @param attribute the Attribute
     *                  to extract.
     * @param check the check to pass.
     * @return the value of the Attribute
     * or an empty String if the check
     * is failed.
     */
    protected static String extractElementAttribute(final WebElement element,
                                                    final String attribute,
                                                    final Boolean check) {
        return check ? element.getAttribute(attribute) : "";
    }

}
