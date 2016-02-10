package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.validators.UrlValidators;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

/**
 * Created by jorge on 09-02-2016.
 */
public class UrlAssert extends AbstractAssert<UrlAssert, String> {

    public UrlAssert(final String actual) {
        super(actual, UrlAssert.class);
    }

    public static UrlAssert assertThat(final String actual) {
        return new UrlAssert(actual);
    }

    public UrlAssert isBrandURL() {
        Assertions.assertThat(UrlValidators.isBrandPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRAND,
                        actual)
                .isTrue();
        return this;
    }

    public UrlAssert isBrandGroupURL() {
        Assertions.assertThat(UrlValidators.isBrandGroupPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRANDGROUP,
                        actual)
                .isTrue();
        return this;
    }

}
