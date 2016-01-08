package helpers;

import org.openqa.selenium.WebDriver;
import pageobjects.BasePage;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

/**
 * Created by jorge on 26-12-2015.
 */
public final class PageLoader {

    private PageLoader() {
        //not called
    }

    public static BasePage loadBase(final String url) {
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        BasePage page = new BasePage(driver);
        page.initializeElements();
        return page;
    }

    public static BasePage loadBase(final WebDriver driver) {
        BasePage page = new BasePage(driver);
        page.initializeElements();
        return page;
    }

    public static CategoriesPage loadCategories(final String url){
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        CategoriesPage page = new CategoriesPage(driver);
        page.initializeElements();
        return page;
    }

    public static CategoriesPage loadCategories(final WebDriver driver){
        CategoriesPage page = new CategoriesPage(driver);
        page.initializeElements();
        return page;
    }

    public static BrandPage loadBrand(final String url){
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        BrandPage page = new BrandPage(driver);
        page.initializeElements();
        return page;
    }

    public static BrandPage loadBrand(final WebDriver driver){
        BrandPage page = new BrandPage(driver);
        page.initializeElements();
        return page;
    }
}
