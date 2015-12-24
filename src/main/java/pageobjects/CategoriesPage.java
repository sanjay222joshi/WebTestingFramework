package pageobjects;

import api.CategoriesPageData;
import helpers.DataFactory;
import org.openqa.selenium.By;
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
public class CategoriesPage extends BasePage {

    @FindBy(css = ".brand")
    private List<WebElement> brands;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getBrandsLinks() {
        ArrayList<WebElement> links = new ArrayList<>();
        if (hasBrands()) {
            links.addAll(brands);
        }
        return links;
    }

    public CategoriesPageData getCategoriesData() {
        ArrayList<String> links = new ArrayList<>();
        if (hasBrands()) {
            for (WebElement brand : brands) {
                links.add(brand.getAttribute("href"));
            }
        }
        return DataFactory.createCategories(getPageData(), links);
    }

    private boolean hasBrands() {
        return !driver.findElements(
                By.cssSelector(".brand")
        ).isEmpty();
    }
}
