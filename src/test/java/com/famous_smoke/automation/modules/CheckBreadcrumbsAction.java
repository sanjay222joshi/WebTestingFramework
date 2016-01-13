package com.famous_smoke.automation.modules;


import com.famous_smoke.automation.pageobjects.BrandPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.famous_smoke.automation.validators.SourceValidators.isInternalError;
import static com.famous_smoke.automation.validators.SourceValidators.isNotFound;

/**
 * Created by jorge on 29-12-2015.
 */
public class CheckBreadcrumbsAction {

    public static BrandPage execute(final BrandPage page) throws Throwable {

        WebDriver driver = page.getDriver();
        String url = driver.getCurrentUrl();
        int breadcrumbs = page.getBreadcrumbs().size();
        for (int i = 0; i < breadcrumbs; ++i) {
            WebElement breadcrumb = page.getBreadcrumbs().get(i);
            breadcrumb.click();
            String source = driver.getPageSource();
            if (isNotFound(source) || isInternalError(source)) {
                return null;
            }
            if (!url.equals(driver.getCurrentUrl())) {
                driver.navigate().back();
            }
        }
        return page;
    }


}
