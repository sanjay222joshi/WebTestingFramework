package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.api.BrandPageData;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.BooleanArrayAssert;

import java.util.List;

/**
 * Created by jorge on 09-02-2016.
 */
public class BrandIdentificationAssert extends AbstractAssert<BrandIdentificationAssert, Boolean> {

    private final String url;

    public BrandIdentificationAssert(final String url,
                                     final Boolean actual) {
        super(actual, BrandIdentificationAssert.class);
        this.url = url;
    }

    public static BrandIdentificationAssert assertThat(final String url,
                                                       final Boolean actual) {
        return new BrandIdentificationAssert(url, actual);
    }


    public BrandIdentificationAssert isIdentified() {
        Assertions.assertThat(actual)
                .overridingErrorMessage(
                        AssertionMessages.BRAND_NOT_IDENTIFIED,
                        url)
                .isTrue();
        return this;
    }

}
