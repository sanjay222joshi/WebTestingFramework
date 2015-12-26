package helpers;

import org.openqa.selenium.WebDriver;
import pageobjects.BrandPage;

/**
 * Created by jorge on 26-12-2015.
 */
public final class PageLoader {

    private PageLoader() {
        //not called
    }

    public static BrandPage loadBrand(final String url){
        WebDriver driver = DriverFactory.createDefaultDriver(url);
        BrandPage page = new BrandPage(driver);
        return page;
    }
}
