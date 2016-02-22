package com.famous_smoke.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * <p>Represents the page with all the
 * Brand Links, that we use to scrap the
 * data of the Brand Pages.</p>
 *
 * <p>CategoriesPage extends from BasePage.</p>
 */
public class CategoriesPage extends BasePage {

    /**
     * The Brands links.
     */
    @FindBy(css = PageConstants.CATEGORIES_BRANDS_CSS)
    private static List<WebElement> brands;

    /**
     * Measures the size of the Brand Links
     * WebElements List.
     * @return
     */
    public static Integer getBrandsLinksCount() {
        return brands.size();
    }

    /**
     * Clicks on a brand link in the WebElements List
     * represented by the index parameter.
     * @param brandIndex the brand position in the List.
     */
    public static void goToBrand(final Integer brandIndex) {
        waitUntilElementIsClickable(
                brands.get(brandIndex)
        ).click();
    }

}
