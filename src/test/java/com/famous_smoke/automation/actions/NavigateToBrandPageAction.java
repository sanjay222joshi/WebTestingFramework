package com.famous_smoke.automation.actions;

import com.famous_smoke.automation.data.BrandPageData;
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
        NavigateToTestUrlAction.execute();
        return BrandPage.getBrandData();
    }
}
