package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.api.CategoriesPageData;
import com.famous_smoke.automation.factory.DataFactory;
import com.famous_smoke.automation.helpers.Navigator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.famous_smoke.automation.helpers.SeleniumFinder.findElementsByCss;

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
