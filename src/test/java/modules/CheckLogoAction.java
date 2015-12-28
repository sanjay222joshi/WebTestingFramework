package modules;

import api.SeleniumAction;
import helpers.DriverFactory;
import org.openqa.selenium.WebDriver;
import pageobjects.BrandPage;

import static api.PageConstants.ATTRIBUTE_SRC;

/**
 * Created by jorge on 26-12-2015.
 */
public class CheckLogoAction implements SeleniumAction<BrandPage> {

    @Override
    public BrandPage execute(final Object param) throws Throwable {
        BrandPage page = (BrandPage) param;

        if(!page.hasLogo()) {
            return null;
        }
        String logoSrc = page.getLogo().getAttribute(ATTRIBUTE_SRC);
        WebDriver logoDriver = DriverFactory.createDefaultDriver(logoSrc);
        logoDriver.quit();
        return page;
    }
}
