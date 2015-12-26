package modules;

import api.BrandPageData;
import api.CategoriesPageData;
import api.SeleniumAction;
import helpers.DataWorkbook;
import helpers.DriverFactory;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jorge on 26-12-2015.
 */
public class BrandCrawler  implements SeleniumAction {

    private static final String BRAND_LIST_URL  = "https://www.famous-smoke.com/brand-list";
    private static final int    MAXIMUM_BRANDS  = 10;


    @Override
    public void execute() throws Throwable {
        shutOffLogger();
        System.out.println("Starting the crawler...");
        WebDriver driver = DriverFactory.createSilentDriver(BRAND_LIST_URL);
        CategoriesPage categories = new CategoriesPage(driver);
        categories.initializeElements();
        CategoriesPageData categoriesData = categories.getCategoriesData();
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
        DataWorkbook workbook = DataWorkbook.getDefaultWorkbook();
        workbook.writeBrandPages(brandsData);
        System.out.println(i + " Brands written in the TestData File.");
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
        return page.getBrandData();
    }

}
