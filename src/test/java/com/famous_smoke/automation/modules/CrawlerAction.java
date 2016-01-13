package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.factory.DriverFactory;
import com.famous_smoke.automation.factory.PageFactory;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 26-12-2015.
 */
public class CrawlerAction  {

    private static final int MAXIMUM_CRAWLS = 2000;

    public static List<BrandPageData> execute(final String url) throws Throwable {
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
