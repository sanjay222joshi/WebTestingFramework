package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public class BrandCategoriesPage extends BasePage {

    @FindBy(css = ".brand")
    private List<WebElement> brands;

    public BrandCategoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage initializeElements() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public List<String> getBrandsLinks() {
        ArrayList<String> links = new ArrayList<>();
        for (WebElement brand : brands) {
           links.add(brand.getAttribute("href"));
        }
        return links;
    }
}
