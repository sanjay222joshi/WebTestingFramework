package com.famous_smoke.automation.validators;

/**
 * <p>Provides validator to determine the type of
 * URLs.</p>
 */
public class UrlValidators {

    /**
     * The Brand URL pattern.
     */
    private static final String BRAND_URL = "/brand/";
    /**
     * The Brand Group URL pattern.
     */
    private static final String BRAND_GROUP_URL = "/brandgroup/";
    private static final String BRAND_ITEM_URL = "/item+";

    private UrlValidators() {
        //not called
    }

    /**
     * Validates if the URL is of a Brand page.
     * @param url the URL String.
     * @return true if url contains the Brand URL
     * pattern.
     */
    public static boolean isBrandPage(final String url) {
        return url.contains(BRAND_URL);
    }

    /**
     * Validates if the URL is of a Brand Group
     * page.
     * @param url the URL String
     * @return true if url contains the Brand Group
     * URL pattern.
     */
    public static boolean isBrandGroupPage(final String url) {
        return url.contains(BRAND_GROUP_URL);
    }

    public static boolean isBrandItemPage(final String url) {
        return url.contains(BRAND_ITEM_URL);
    }
}
