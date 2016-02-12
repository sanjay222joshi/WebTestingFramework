package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Crawls through the different Brand links
 * of the CategoriesPage.</p>
 */
public class CrawlThroughBrandsAction {

    /**
     * Goes through the BrandLinks list of the
     * categories page, one link at a time.
     *
     * Due to the flakiness of the WebElements
     * we cannot use complex iterations of object
     * so we must iterate using a FOR cycle.
     *
     * The length of the FOR cycle is controlled
     * by the linkCount and the testMaximumCrawls
     * variables; if the index reaches one of these
     * two values, the cycle is interrupted.
     *
     * It's important to check if the Promo is
     * enabled and close it in each iteration
     * as it would prevent interacting with the
     * BrandPage.
     * @return the BrandPageData list of all the
     * iterated BrandPages.
     * @throws Throwable
     */
    public static List<BrandPageData> execute() throws Throwable {
        List<BrandPageData> brandsData = new ArrayList<>();
        int linkCount = CategoriesPage.getBrandsLinksCount();
        for (int index = 0; index < linkCount && index < Hooks.testMaximumCrawls; ++index) {
            if (BasePage.hasPromo()) {
                BasePage.closePromo();
            }
            CategoriesPage.clickBrandLink(index);
            BrandPageData data = BrandPage.getBrandData();
            brandsData.add(data);
            Navigator.goBack();
        }
        return brandsData;
    }

}
