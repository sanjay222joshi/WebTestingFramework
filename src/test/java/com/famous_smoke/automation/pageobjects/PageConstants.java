package com.famous_smoke.automation.pageobjects;

/**
 * <p>Contains the filter values that the Page
 * Objects use to find the WebElements.</p>
 */
public final class PageConstants {

    public static final String ATTRIBUTE_CONTENT     = "content";
    public static final String ATTRIBUTE_HREF        = "href";
    public static final String ATTRIBUTE_SRC         = "src";

    public static final String META_DESCRIPTION_NAME = "description";
    public static final String BREADCRUMBS_CSS       = ".breadcrumb";
    public static final String PROMO_FORM_NAME       = "emailcollection";
    public static final String PROMO_CLOSE_LINK_CSS  = ".closebutton.trackEvent";
    public static final String BREADCRUMBS_LINKS_CSS = ".link";
    public static final String CANONICAL_XPATH       = "//link[@rel='canonical']";
    public static final String BRAND_HEADER1_CSS     = ".title.oswald";
    public static final String BRAND_DESCRIPTION_CSS = ".justify";
    public static final String BRAND_LOGO_CSS        = ".full.nopad";
    public static final String BRAND_VIDEO_CSS       = ".minivideo";
    public static final String CATEGORIES_BRANDS_CSS = ".brand";

    public static final String PAGE_HEADER_CSS       = ".pagebar";


    private PageConstants() {
        //not called
    }
}
