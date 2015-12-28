package modules;

import api.BrandPageData;
import api.CategoriesPageData;
import api.PageData;
import api.SeleniumAction;
import helpers.DataWorkbook;
import helpers.DriverFactory;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
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

    private static final int MAXIMUM_BRANDS = 1000000;

    @Override
    public List<BrandPageData> execute(final Object param) throws Throwable {
        shutOffLogger();
        String url = param.toString();
        WebDriver driver = DriverFactory.createSilentDriver(url);
        CategoriesPage categories = new CategoriesPage(driver);
        categories.initializeElements();
        CategoriesPageData categoriesData = categories.getCategoriesData();
        driver.quit();
        List<BrandPageData> brandsData = new ArrayList<>();
        int i = 0;
        for (String link : categoriesData.getBrandsLinks()) {
            System.out.println(link);
            brandsData.add(navigate(link));
            ++i;
            if (i == MAXIMUM_BRANDS) {
                break;
            }
        }
        return brandsData;
    }

    private static void shutOffLogger(){
        LogFactory.
                getFactory().
                setAttribute("org.apache.commons.logging.Log",
                        "org.apache.commons.logging.impl.NoOpLog");
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    }

    private static BrandPageData navigate(final String url) {
        WebDriver driver = DriverFactory.createSilentDriver(url);
        BrandPage page = new BrandPage(driver);
        page.initializeElements();
        BrandPageData data = page.getBrandData();
        driver.quit();
        return data;
    }

}
