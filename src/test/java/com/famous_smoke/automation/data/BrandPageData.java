package com.famous_smoke.automation.data;

import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Created by jorge on 22-12-2015.
 */
public class BrandPageData extends BasePageData {

    public static final String HEADER1_FIELD_NAME     = "HEADER 1";
    public static final String DESCRIPTION_FIELD_NAME = "SEO PARAGRAPH";
    public static final String IDENTIFIED_FIELD_NAME  = "IDENTIFIED";

    private final String header1;
    private final String description;
    private final Boolean identified;

    public BrandPageData(final String url,
                         final String canonical,
                         final String title,
                         final String metaDescription,
                         final String breadcumbsText,
                         final List<String> breadcumbs,
                         final String header1,
                         final String description,
                         final Boolean identified) {
        super(url, canonical, title,
                metaDescription, breadcumbsText, breadcumbs);
        this.header1 = header1;
        this.description = description;
        this.identified = identified;
    }

    public String getHeader1() {
        return header1;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isIdentified() {
        return identified;
    }

}
