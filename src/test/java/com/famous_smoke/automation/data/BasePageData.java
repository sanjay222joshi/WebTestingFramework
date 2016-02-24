package com.famous_smoke.automation.data;

import java.util.List;

/**
 * <p>Contains the data representation of the BasePage
 * Page Object.</p>
 */
public class BasePageData {

    public static final String URL_FIELD_NAME               = "URL";
    public static final String CANONICAL_FIELD_NAME         = "CANONICAL URL";
    public static final String TITLE_FIELD_NAME             = "TITLE";
    public static final String METADESCRIPTION_FIELD_NAME   = "META DESCRIPTION";
    public static final String BREADCRUMBS_TEXT_FIELD_NAME  = "BREADCRUMBS TEXT";
    public static final String BREADCRUMBS_LINKS_FIELD_NAME = "BREADCRUMBS LINKS";

    private static final int   HASH_CODE_SUM_PRIME         = 739;
    private static final int   HASH_CODE_MULT_PRIME        = 499;


    private final String url;
    private final String canonical;
    private final String title;
    private final String metaDescription;
    private final String breadcumbsText;
    private final List<String> breadcumbsLinks;

    public BasePageData(final String url,
                        final String canonical,
                        final String title,
                        final String metaDescription,
                        final String breadcumbsText,
                        final List<String> breadcumbsLinks) {
        this.url = url;
        this.canonical = canonical;
        this.title = title;
        this.metaDescription = metaDescription;
        this.breadcumbsText = breadcumbsText;
        this.breadcumbsLinks = breadcumbsLinks;
    }

    public String getURL() {
        return url;
    }

    public String getCanonical() {
        return canonical;
    }

    public String getTitle() {
        return title;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public String getBreadcrumbsText() {
        return breadcumbsText;
    }

    public List<String> getBreadcrumbsLinks() {
        return breadcumbsLinks;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof BasePageData) {
            BasePageData comparable = (BasePageData) o;
            return this.url.equals(comparable.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return HASH_CODE_MULT_PRIME * (HASH_CODE_SUM_PRIME + url.hashCode());
    }

}
