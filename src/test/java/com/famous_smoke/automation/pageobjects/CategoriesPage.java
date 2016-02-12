package com.famous_smoke.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public class CategoriesPage extends BasePage {

    @FindBy(css = PageConstants.BRANDS_CSS)
    private static List<WebElement> brands;

    public static Integer getBrandsLinksCount() {
        return brands.size();
    }

    public static void clickBrandLink(final Integer brandIndex) {
        brands.get(brandIndex).click();
    }

}
