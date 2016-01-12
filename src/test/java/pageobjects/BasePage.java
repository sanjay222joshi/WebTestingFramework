package pageobjects;

import api.PageData;
import factory.DataFactory;
import factory.SectionFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pageobjects.PageConstants.*;
import static helpers.SeleniumFinder.*;

/**
 * Created by jorge on 21-12-2015.
 */
public class BasePage extends PageObject {

    @FindBy(xpath = CANONICAL_XPATH)
    protected WebElement canonical;
    @FindBy(name = META_DESCRIPTION_NAME)
    protected WebElement metaDescription;
    @FindBy(css = BREADCRUMBS_CSS)
    protected WebElement breadcrumbs;

    private final HeaderSection headerSection;

    public BasePage(final WebDriver driver) {
        super(driver);
        headerSection = SectionFactory.createHeader(driver, PAGE_HEADER_CSS);
    }

    public HeaderSection getHeaderSection(){
        return headerSection;
    }

    public WebElement getCanonical() {
        if (hasCanonical()) {
            return canonical;
        } else {
            return null;
        }
    }

    public WebElement getMetaDescription() {
        if (hasMetaDescription()) {
            return metaDescription;
        } else {
            return null;
        }
    }

    public List<WebElement> getBreadcrumbs() {
        ArrayList<WebElement> list = new ArrayList<>();
        if (hasBreadcrumbs()) {
            list.addAll(findElementsByCss(breadcrumbs,BREADCRUMBS_LINKS_CSS));
        }
        return list;
    }

    public boolean hasCanonical() {
        return !findElementsByXpath(driver, CANONICAL_XPATH).isEmpty();
    }

    public boolean hasMetaDescription() {
        return !findElementsByName(driver, META_DESCRIPTION_NAME).isEmpty();
    }

    public boolean hasBreadcrumbs() {
        return !findElementsByCss(driver, BREADCRUMBS_CSS).isEmpty();
    }

    public PageData getPageData() {
        String url = driver.getCurrentUrl();
        String title = driver.getTitle();
        String canonical = hasCanonical() ?
                this.canonical.getAttribute(ATTRIBUTE_HREF)
                : "";
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



}
