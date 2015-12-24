package pageobjects;

import api.PageData;
import helpers.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public abstract class BasePage {

    protected final WebDriver driver;

    @FindBy(name = "description")
    protected WebElement metaDescription;
    @FindBy(css = ".breadcrumb")
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
            list.addAll(
                    breadcrumbs.findElements(
                            By.cssSelector(".link")
                    )
            );
        }
        return list;
    }

    protected boolean hasMetaDescription() {
        return !driver.findElements(By.name("description")).isEmpty();
    }

    protected boolean hasBreadcrumbs() {
        return !driver.findElements(By.cssSelector(".breadcrumb")).isEmpty();
    }

    protected PageData getPageData() {
        String url = driver.getCurrentUrl();
        String canonical = "";
        if (!driver.findElements(By.xpath("//link[@rel='canonical']")).isEmpty()) {
            canonical = driver.findElement(By.xpath("//link[@rel='canonical']")).getAttribute("href");
        }

        String title = driver.getTitle();
        String metaDescription = hasMetaDescription() ?
                this.metaDescription.getAttribute("content")
                : "";
        String breadcrumbsText = hasBreadcrumbs()?
                breadcrumbs.getText()
                : "";
        ArrayList<String> breadcrumbs = new ArrayList<>();
        if (hasBreadcrumbs()) {
            for (WebElement breadcrumb : this.breadcrumbs.findElements(By.cssSelector(".link"))) {
                breadcrumbs.add(breadcrumb.getAttribute("href"));
            }
        }
        return DataFactory.createPage(
                url, title, canonical,
                metaDescription,
                breadcrumbsText, breadcrumbs);
    }
}
