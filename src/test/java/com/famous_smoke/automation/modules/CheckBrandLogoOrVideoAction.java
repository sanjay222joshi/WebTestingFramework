package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.pageobjects.BrandPage;

/**
 * Created by jorge on 26-12-2015.
 */
public class CheckBrandLogoOrVideoAction {

    public static Boolean execute() throws Throwable {
        return BrandPage.hasLogo() || BrandPage.hasVideo();
    }
}
