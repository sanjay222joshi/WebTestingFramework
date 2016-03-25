package com.famous_smoke.automation.actions;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.pageobjects.BrandItemPage;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.validators.UrlValidators;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Crawls through the different Brand links
 * of the BrandListPage.</p>
 */
public class CrawlThroughBrandPageItemsAction {

    /**
     * Goes through the Brand Items list of the
     * Brand page, one link at a time.
     *
     * Due to the flakiness of the WebElements
     * we cannot use complex iterations of object
     * so we must iterate using a FOR cycle.
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
        String url = BrandPage.getBrandData().getURL();
        if (UrlValidators.isBrandPage(url)) {
            List<String> itemsURLs = BrandPage.getItemsURLs();
            for (String itemURL : itemsURLs) {
                String previousURL = Hooks.testUrl;
                Hooks.testUrl = itemURL;
                try {
                    System.out.println("SCRAPPING DATA FROM " + Hooks.testUrl);
                    itemsData.add(NavigateToBrandItemPageAction.execute());
                    Navigator.goBack();
                } catch (Exception ex) {
                    String message = "FAILED TO SCRAP DATA FROM " + Hooks.testUrl + "\n"
                                   + "EXCEPTION MESSAGE IS: " + ex.getMessage();
                    System.err.println(message);
                    Hooks.testUrl = previousURL;
                    NavigateToTestUrlAction.execute();
                }
            }
        }
        return itemsData;
    }

}
