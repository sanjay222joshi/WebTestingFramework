package modules;

import api.SeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BrandPage;
import validators.SourceValidators;

import static validators.SourceValidators.isInternalError;
import static validators.SourceValidators.isNotFound;

/**
 * Created by jorge on 29-12-2015.
 */
public class CheckBreadcrumbsAction implements SeleniumAction<BrandPage> {

    @Override
    public BrandPage execute(Object param) throws Throwable {
        BrandPage page = (BrandPage) param;
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
