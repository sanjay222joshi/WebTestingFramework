package com.famous_smoke.automation.data;

import java.util.List;

/**
 * Created by jorge on 23-12-2015.
 */
public class CategoriesPageData extends BasePageData {

    private final List<String> links;

    public CategoriesPageData(final String url,
                           final String canonical,
                           final String title,
                           final String metaDescription,
                           final String breadcumbsText,
                           final List<String> breadcumbs,
                           final List<String> links){
        super(url, title, canonical,
                metaDescription,
                breadcumbsText, breadcumbs);
        this.links = links;
    }

    public List<String> getBrandsLinks() {
        return links;
    }
}
