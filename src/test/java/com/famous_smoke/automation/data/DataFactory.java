package com.famous_smoke.automation.data;

import java.util.List;

/**
 * <p>Creates the instances of the Data
 * representations of the Page Objects.</p>
 *
 * <p>An implementation of the Factory pattern.</p>
 */
public final class DataFactory {

    private DataFactory() {
        // not called
    }

    /**
     * Create a BasePage Data representation.
     * @param url the URL of the page.
     * @param canonical the Canonical URL of the page.
     * @param title the Title of the page.
     * @param metaDescription the MetaDescription of
     *                        the page.
     * @param breadcumbsText the Breadcrumbs Text of
     *                       the page.
     * @param breadcumbsLinks the List with the Breadcrumbs
     *                        Links of the page.
     * @return the BasePage Data representation.
     */
    public static BasePageData createBasePage(final String url,
                                              final String canonical,
                                              final String title,
                                              final String metaDescription,
                                              final String breadcumbsText,
                                              final List<String> breadcumbsLinks) {
        return new BasePageData(
                url,
                title,
                canonical,
                metaDescription,
                breadcumbsText,
                breadcumbsLinks
        );
    }

    /**
     * Creates a BrandPage Data representation.
     * @param basePageData the Data of the BasePage
     *                     from which the BrandPage
     *                     extends.
     * @param header1 the Header One of the BrandPage.
     * @param description the Description of the
     *                    BrandPage.
     * @param identified the validation of identification
     *                   of the BrandPage.
     * @return the BrandPage Data representation.
     */
    public static BrandPageData createBrandPage(final BasePageData basePageData,
                                                final String header1,
                                                final String description,
                                                final boolean identified) {
        return new BrandPageData(
                basePageData,
                header1,
                description,
                identified
        );
    }

}
