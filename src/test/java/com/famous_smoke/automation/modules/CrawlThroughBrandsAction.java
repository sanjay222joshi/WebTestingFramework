package com.famous_smoke.automation.modules;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 26-12-2015.
 */
public class CrawlThroughBrandsAction {

    private static final int MAXIMUM_CRAWLS = 2000;

    public static List<BrandPageData> execute() throws Throwable {
        List<BrandPageData> brandsData = new ArrayList<>();
        int linkCount = CategoriesPage.getBrandsLinksCount();
        for (int index = 0; index < linkCount && index < MAXIMUM_CRAWLS; ++index) {
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
