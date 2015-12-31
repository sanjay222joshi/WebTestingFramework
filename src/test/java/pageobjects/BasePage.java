package pageobjects;

import api.PageData;
import helpers.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static api.PageConstants.*;

/**
 * Created by jorge on 21-12-2015.
 */
public abstract class BasePage {

    protected final WebDriver driver;

    @FindBy(name = META_DESCRIPTION_NAME)
    protected WebElement metaDescription;
    @FindBy(css = BREADCRUMBS_CSS)
    protected WebElement breadcrumbs;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getMetaDescription() {
        if (hasMetaDescription()) {
            return metaDescription;
        } else {
            return null;
        }
    }

    public BasePage initializeElements() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public List<WebElement> getBreadcrumbs() {
        ArrayList<WebElement> list = new ArrayList<>();
        if (hasBreadcrumbs()) {
            list.addAll(findElementsByCss(breadcrumbs,BREADCRUMBS_LINKS_CSS));
        }
        return list;
    }

    public boolean hasMetaDescription() {
        return !findElementsByName(driver, META_DESCRIPTION_NAME).isEmpty();
    }

    public boolean hasBreadcrumbs() {
        return !findElementsByCss(driver, BREADCRUMBS_CSS).isEmpty();
    }

    protected PageData getPageData() {
        String url = driver.getCurrentUrl();
        String canonical = "";
        if (!findElementsByXpath(driver, CANONICAL_XPATH).isEmpty()) {
            canonical = findElementByXpath(driver,CANONICAL_XPATH).getAttribute(ATTRIBUTE_HREF);
        }

        String title = driver.getTitle();
        String metaDescription = hasMetaDescription() ?
                this.metaDescription.getAttribute(ATTRIBUTE_CONTENT)
                : "";
        String breadcrumbsText = hasBreadcrumbs()?
                breadcrumbs.getText()
                : "";
        ArrayList<String> breadcrumbs = new ArrayList<>();
        if (hasBreadcrumbs()) {
            breadcrumbs.addAll(
                    findElementsByCss(this.breadcrumbs, BREADCRUMBS_LINKS_CSS)
                            .stream()
                            .map(breadcrumb -> breadcrumb.getAttribute(ATTRIBUTE_HREF))
                            .collect(Collectors.toList())
                    );
        }
        return DataFactory.createPage(
                url, title, canonical,
                metaDescription,
                breadcrumbsText, breadcrumbs);
    }

    protected static WebElement findElementByCss(final SearchContext context,
                                                   final String css) {
        return findElement(context, By.cssSelector(css));
    }

    protected static List<WebElement> findElementsByCss(final SearchContext context,
                                                 final String css) {
        return findElements(context, By.cssSelector(css));
    }

    protected static WebElement findElementByName(final SearchContext context,
                                                   final String name) {
        return findElement(context, By.name(name));
    }

    protected static List<WebElement> findElementsByName(final SearchContext context,
                                                  final String name) {
        return findElements(context, By.name(name));
    }

    protected static WebElement findElementByXpath(final SearchContext context,
                                                   final String xpath) {
        return findElement(context, By.xpath(xpath));
    }

    protected static List<WebElement> findElementsByXpath(final SearchContext context,
                                                   final String xpath) {
        return findElements(context, By.xpath(xpath));
    }

    private static WebElement findElement(final SearchContext context,
                                          final By by){
        return context.findElement(by);
    }

    private static List<WebElement> findElements(final SearchContext context,
                                          final By by){
        return context.findElements(by);
    }

}
