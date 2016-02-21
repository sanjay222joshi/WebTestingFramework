package com.famous_smoke.automation.actions;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;

/**
 * <p>Executes the Navigation to a Brand page.</p>
 */
public class NavigateToTestUrlAction {

    /**
     * Navigates to the URL setup in the
     * Hooks class and initializes
     * the PageObjects.
     * @return the BrandPageData of the
     * current page.
     */
    public static BasePageData execute() {
        Navigator.goUrl(Hooks.testUrl);
        Navigator.initializePages();
        return BasePage.getBasePageData();
    }
}
