package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;

/**
 * Created by jorge on 14-01-2016.
 */
public class NavigateToPageAction {

    public static BrandPageData execute() {
        Navigator.goUrl(Hooks.testUrl);
        Navigator.initializePages();
        return BrandPage.getBrandData();
    }
}
