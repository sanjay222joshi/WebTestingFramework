package pageobjects;

import api.CategoriesPageData;
import factory.DataFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pageobjects.PageConstants.ATTRIBUTE_HREF;
import static pageobjects.PageConstants.BRANDS_CSS;
import static helpers.SeleniumFinder.findElementsByCss;

/**
 * Created by jorge on 21-12-2015.
 */
public class CategoriesPage extends BasePage {

    @FindBy(css = BRANDS_CSS)
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
            links.addAll(brands
                    .stream()
                    .map(brand -> brand.getAttribute(ATTRIBUTE_HREF))
                    .collect(Collectors.toList())
            );
        }
        return DataFactory.createCategories(getPageData(), links);
    }

    public boolean hasBrands() {
        return !findElementsByCss(driver, BRANDS_CSS).isEmpty();
    }
}
