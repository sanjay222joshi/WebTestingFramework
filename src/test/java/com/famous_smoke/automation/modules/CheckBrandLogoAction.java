package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.pageobjects.BrandPage;

/**
 * Created by jorge on 26-12-2015.
 */
public class CheckBrandLogoAction {

    public static BrandPageData execute() throws Throwable {
        if(!BrandPage.hasLogo()) {
            return null;
        }
        return BrandPage.getBrandData();
    }
}
