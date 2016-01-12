package factory;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import pageobjects.BasePage;
import pageobjects.BrandPage;
import pageobjects.CategoriesPage;

/**
 * Created by jorge on 26-12-2015.
 */
public final class PageFactory {

    private PageFactory() {
        //not called
    }

    public static BasePage createBase(final String url) {
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        BasePage page = new BasePage(driver);
        return page;
    }

    public static BasePage createBase(final WebDriver driver) {
        BasePage page = new BasePage(driver);
        return page;
    }

    public static CategoriesPage createCategories(final String url){
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        CategoriesPage page = new CategoriesPage(driver);
        return page;
    }

    public static CategoriesPage createCategories(final WebDriver driver){
        CategoriesPage page = new CategoriesPage(driver);
        return page;
    }

    public static BrandPage createBrand(final String url){
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        BrandPage page = new BrandPage(driver);
        return page;
    }

    public static BrandPage createBrand(final WebDriver driver){
        BrandPage page = new BrandPage(driver);
        return page;
    }
}
