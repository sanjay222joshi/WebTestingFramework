package com.famous_smoke.automation.actions;

import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.pageobjects.BrandItemPage;

/**
 * <p>Executes the Navigation to a Brand page.</p>
 */
public class NavigateToBrandItemPageAction {

    /**
     * Navigates to the URL setup in the
     * Hooks class and initializes
     * the PageObjects.
     * @return the BrandPageData of the
     * current page.
     */
    public static BrandItemPageData execute() {
        NavigateToTestUrlAction.execute();
        return BrandItemPage.getItemData();
    }
}
