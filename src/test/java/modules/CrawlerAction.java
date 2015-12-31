package modules;

import api.BrandPageData;
import api.SeleniumAction;
import helpers.DriverFactory;
import helpers.PageLoader;
import org.openqa.selenium.WebDriver;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 26-12-2015.
 */
public class CrawlerAction implements SeleniumAction<List<BrandPageData>> {

    private static final int MAXIMUM_CRAWLS = 2000;

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
            if (i == MAXIMUM_CRAWLS) {
                break;
            }
        }
        driver.quit();
        return brandsData;
    }

}
