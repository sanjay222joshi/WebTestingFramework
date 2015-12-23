package pageobjects;

import api.Page;
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
public abstract class BasePage implements Page {

    protected final WebDriver driver;
    private final String url;
    private final String title;

    @FindBy(name = "description")
    private WebElement metaDescription;
    @FindBy(css = ".breadcrumb")
    private WebElement breadcrumbs;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
        url = driver.getCurrentUrl();
        title = driver.getTitle();
    }

    public BasePage initializeElements() {
        PageFactory.initElements(driver, this);
        return this;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMetaDescription() {
        if (hasMetaDescription()) {
            return metaDescription.getAttribute("content");
        } else {
            return "";
        }
    }

    @Override
    public List<String> getBreadCrumbs() {
        ArrayList<String> list = new ArrayList<>();
        if (hasBreadcrumbs()) {
            for (WebElement breadcrumb : breadcrumbs.findElements(By.cssSelector(".link"))) {
                String link = breadcrumb.getAttribute("href");
                String text = breadcrumb.findElement(By.xpath(".//span")).getText();
                list.add(text + ">>" + link);
            }
        }
        return list;
    }

    @Override
    public String getBreadCrumbsText() {
        if (hasBreadcrumbs()) {
            return breadcrumbs.getText();
        } else {
            return "";
        }
    }

    private boolean hasMetaDescription() {
        return !driver.findElements(By.name("description")).isEmpty();
    }

    private boolean hasBreadcrumbs() {
        return !driver.findElements(By.cssSelector(".breadcrumb")).isEmpty();
    }
}
