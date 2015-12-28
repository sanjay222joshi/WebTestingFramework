package modules;

import api.BrandPageData;
import api.CategoriesPageData;
import api.PageData;
import api.SeleniumAction;
import helpers.DataWorkbook;
import helpers.DriverFactory;
import helpers.PageLoader;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jorge on 26-12-2015.
 */
public class CrawlerAction implements SeleniumAction<List<BrandPageData>> {

    private static final int MAXIMUM_BRANDS = 2000;

    @Override
    public List<BrandPageData> execute(final Object param) throws Throwable {
        String url = param.toString();
        WebDriver driver = DriverFactory.createSilentDriver(url);
        CategoriesPage categories = PageLoader.loadCategories(driver);
        List<BrandPageData> brandsData = new ArrayList<>();
        int linkCount = categories.getBrandsLinks().size();
        for (int i = 0; i < linkCount; ++i) {
            categories.getBrandsLinks().get(i).click();
            BrandPage brand = PageLoader.loadBrand(driver);
            brandsData.add(brand.getBrandData());
            driver.navigate().back();
            if (i == MAXIMUM_BRANDS) {
                break;
            }
        }
        driver.quit();
        return brandsData;
    }

}
