package com.famous_smoke.automation.data;

import java.util.List;

/**
 * Created by jorge on 23-12-2015.
 */
public final class PageDataFactory {

    private PageDataFactory() {
        // not called
    }

    public static BasePageData createPage(final String url,
                                          final String canonical,
                                          final String title,
                                          final String metaDescription,
                                          final String breadcumbsText,
                                          final List<String> breadcumbs) {
        return new BasePageData(url, title, canonical,
                metaDescription,
                breadcumbsText, breadcumbs);

    }

    public static BrandPageData createBrand(final BasePageData basePageData,
                                            final String header1,
                                            final String description,
                                            final boolean identified) {
        return new BrandPageData(
                basePageData.getURL(),
                basePageData.getCanonical(),
                basePageData.getTitle(),
                basePageData.getMetaDescription(),
                basePageData.getBreadcrumbsText(),
                basePageData.getBreadcrumbs(),
                header1, description, identified);
    }

    public static CategoriesPageData createCategories(final BasePageData basePageData,
                                                      final List<String> links) {
        return new CategoriesPageData(
                basePageData.getURL(),
                basePageData.getCanonical(),
                basePageData.getTitle(),
                basePageData.getMetaDescription(),
                basePageData.getBreadcrumbsText(),
                basePageData.getBreadcrumbs(),
                links);
    }


}
