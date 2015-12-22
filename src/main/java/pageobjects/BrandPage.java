package pageobjects;

import org.openqa.jetty.html.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandPage extends BasePage {



    @FindBy(name = "description")
    private WebElement metaDescription;

    @FindBy(css = ".title.oswald")
    private WebElement header1;
    @FindBy(css = ".justify")
    private WebElement description;


    public BrandPage(WebDriver driver) {
        super(driver);

    }

    @Override
    public BasePage initializeElements() {
        PageFactory.initElements(driver, this);
        return this;
    }



    public String getMetaDescription() {
        return metaDescription.getAttribute("content");
    }

    public String getHeader1() {
        return header1.getText();
    }

    public String getDescription() {
        return description.getText();
    }





}
