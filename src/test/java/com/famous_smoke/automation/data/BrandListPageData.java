package com.famous_smoke.automation.data;

import java.util.Collection;

/**
 * Created by jorge on 24-02-2016.
 */
public class BrandListPageData extends BasePageData {

    private final Collection<String> brandLinks;

    public BrandListPageData(final BasePageData basePageData,
                             final Collection<String> brandLinks) {
        super(
                basePageData.getURL(),
                basePageData.getCanonical(),
                basePageData.getTitle(),
                basePageData.getMetaDescription(),
                basePageData.getBreadcrumbsText(),
                basePageData.getBreadcrumbsLinks()
        );
        this.brandLinks = brandLinks;
    }

    public Collection<String> getBrandLinks() {
        return brandLinks;
    }
}
