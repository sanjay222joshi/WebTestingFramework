package com.famous_smoke.automation.validators;

/**
 * Created by jorge on 29-12-2015.
 */
public class UrlValidators {

    private static final String BRAND_URL = "/brand/";
    private static final String BRAND_GROUP_URL = "/brandgroup/";

    private UrlValidators() {
        //not called
    }

    public static boolean isBrandPage(final String url) {
        return url.contains(BRAND_URL);
    }

    public static boolean isBrandGroupPage(final String url) {
        return url.contains(BRAND_GROUP_URL);
    }
}
