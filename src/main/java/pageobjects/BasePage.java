package pageobjects;

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
    private final String url;
    private final String title;

    @FindBy(css = ".breadcrumb")
    private WebElement breadcrumbs;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
        url = driver.getCurrentUrl();
        title = driver.getTitle();
    }

    public abstract BasePage initializeElements();

    public String getURL() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getBreadCrumbs() {
        ArrayList<String> list = new ArrayList<>();
        for (WebElement breadcrumb : breadcrumbs.findElements(By.cssSelector(".link"))) {
            String link = breadcrumb.getAttribute("href");
            String text = breadcrumb.findElement(By.xpath(".//span")).getText();
            list.add(text + ">>" + link);
        }
        return list;
    }

    public String getBreadCrumbsText() {
        return breadcrumbs.getText();
    }
}
