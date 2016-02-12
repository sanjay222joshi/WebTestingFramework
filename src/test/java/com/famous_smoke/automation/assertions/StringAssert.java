package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.validators.UrlValidators;
import org.fest.assertions.api.Assertions;

/**
 * Created by jorge on 09-02-2016.
 */
public class StringAssert extends org.fest.assertions.api.StringAssert {

    public StringAssert(final String actual) {
        super(actual);
    }

    public static StringAssert assertThat(final String actual) {
        return new StringAssert(actual);
    }

    public StringAssert isBrandURL() {
        Assertions.assertThat(UrlValidators.isBrandPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRAND,
                        actual)
                .isTrue();
        return this;
    }

    public StringAssert isBrandGroupURL() {
        Assertions.assertThat(UrlValidators.isBrandGroupPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRANDGROUP,
                        actual)
                .isTrue();
        return this;
    }

}
