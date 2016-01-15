package com.famous_smoke.automation.modules;


import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;

import static com.famous_smoke.automation.validators.SourceValidators.isInternalError;
import static com.famous_smoke.automation.validators.SourceValidators.isNotFound;

/**
 * Created by jorge on 29-12-2015.
 */
public class CheckBreadcrumbsAction {

    public static BrandPageData execute() throws Throwable {
        int breadcrumbs = BrandPage.getBreadcrumbsCount();
        for (int i = 0; i < breadcrumbs; ++i) {
            if (BasePage.hasPromo()) {
                BasePage.closePromo();
            }
            BrandPage.clickBreadcrumb(i);
            String source = Navigator.getPageSource();
            Navigator.goBack();
            if (isNotFound(source) || isInternalError(source)) {
                return null;
            }
        }
        return BrandPage.getBrandData();
    }


}
