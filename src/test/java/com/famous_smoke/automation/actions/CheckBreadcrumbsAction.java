package com.famous_smoke.automation.actions;


import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;

import static com.famous_smoke.automation.validators.SourceValidators.isInternalError;
import static com.famous_smoke.automation.validators.SourceValidators.isNotFound;

/**
 * <p>Navigates to the Diferrent breadcrumbs of a
 * BrandPage.</p>
 */
public class CheckBreadcrumbsAction {

    /**
     * We click on all the breadcrumbs of a
     * Brand page to check if they are valid.
     *
     * Due to the flakiness of the WebElements
     * we cannot use complex iterations of object
     * so we must iterate using a FOR cycle.
     *
     * @return the BrandPageData of the Brand
     * page; null if we detect a 404 or 500
     * in any step of the navigation.
     * @throws Throwable
     */
    public static BasePageData execute() throws Throwable {
        int breadcrumbs = BasePage.getBreadcrumbsCount();
        for (int i = 0; i < breadcrumbs; ++i) {
            if (BasePage.hasPromo()) {
                BasePage.closePromo();
            }
            BasePage.clickBreadcrumb(i);
            String source = Navigator.getPageSource();
            Navigator.goBack();
            if (isNotFound(source) || isInternalError(source)) {
                return null;
            }
        }
        return BasePage.getBasePageData();
    }


}
