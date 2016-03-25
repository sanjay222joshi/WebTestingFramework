package com.famous_smoke.automation.actions;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BrandListPage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Crawls through the different Brand links
 * of the BrandListPage.</p>
 */
public class ScrapBrandItemsDataFromBrandListPageAction {

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
    public static List<BrandItemPageData> execute() throws Throwable {
        List<BrandItemPageData> itemsData = new ArrayList<>();
        Hooks.testBrandListPageData = BrandListPage.getBrandListData();
        int crawl = 0;
        for (String brandLink : Hooks.testBrandListPageData.getBrandLinks()) {
            try {
                Hooks.testUrl = brandLink;
                NavigateToTestUrlAction.execute();
                itemsData.addAll(CrawlThroughBrandPageItemsAction.execute());
            } catch (Exception ex) {
                String message = "FAILED TO SCRAP DATA FROM " + Hooks.testUrl + "\n"
                               + "EXCEPTION MESSAGE IS: " + ex.getMessage();
                System.err.println(message);
            }
            if (++crawl == Hooks.testMaximumCrawls) {
                break;
            }
        }
        return itemsData;
    }

}
