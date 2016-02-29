package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.data.BrandListPageData;
import com.famous_smoke.automation.data.DataFactory;
import com.famous_smoke.automation.util.SeleniumFinder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Represents the page with all the
 * Brand Links, that we use to scrap the
 * data of the Brand Pages.</p>
 *
 * <p>BrandListPage extends from BasePage.</p>
 */
public class BrandListPage extends BasePage {

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
        if (hasPromo()) {
            closePromo();
        }
        waitUntilElementIsClickable(
                brands.get(brandIndex)
        ).click();
    }

    public static BrandListPageData getBrandListData() {
        List<String> brandLinks = brands
                .stream()
                .map(b -> extractElementAttribute(b, PageConstants.ATTRIBUTE_HREF, true))
                .collect(Collectors.toList());
        return DataFactory.createBrandListPage(getBasePageData(), brandLinks);
    }

}
