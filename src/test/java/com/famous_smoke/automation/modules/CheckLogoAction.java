package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.factory.DriverFactory;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.PageConstants;
import org.openqa.selenium.WebDriver;

/**
 * Created by jorge on 26-12-2015.
 */
public class CheckLogoAction {

    public static BrandPage execute(final BrandPage page) throws Throwable {
        if(!page.hasLogo()) {
            return null;
        }
        String logoSrc = page.getLogo().getAttribute(PageConstants.ATTRIBUTE_SRC);
        WebDriver logoDriver = DriverFactory.createDefaultDriver(logoSrc);
        logoDriver.quit();
        return page;
    }
}
