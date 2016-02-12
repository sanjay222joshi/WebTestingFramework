package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BrandPage;

/**
 * <p>Executes the Navigation to a Brand page.</p>
 */
public class NavigateToBrandPageAction {

    /**
     * Navigates to the URL setup in the
     * Hooks class and initializes
     * the PageObjects.
     * @return the BrandPageData of the
     * current page.
     */
    public static BrandPageData execute() {
        Navigator.goUrl(Hooks.testUrl);
        Navigator.initializePages();
        return BrandPage.getBrandData();
    }
}
