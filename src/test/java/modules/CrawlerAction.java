package modules;

import api.BrandPageData;
import api.SeleniumAction;
import factory.DriverFactory;
import factory.PageFactory;
import org.openqa.selenium.WebDriver;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 26-12-2015.
 */
public class CrawlerAction implements SeleniumAction<List<BrandPageData>> {

    private static final int MAXIMUM_CRAWLS = 2;

    @Override
    public List<BrandPageData> execute(final Object param) throws Throwable {
        String url = param.toString();
        WebDriver driver = DriverFactory.createSilentDriver(url);
        CategoriesPage categories = PageFactory.createCategories(driver);
        List<BrandPageData> brandsData = new ArrayList<>();
        int linkCount = categories.getBrandsLinks().size();
        for (int i = 0; i < linkCount; ++i) {
            categories.getBrandsLinks().get(i).click();
            BrandPage brand = PageFactory.createBrand(driver);
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
