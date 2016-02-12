package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.validators.UrlValidators;
import org.fest.assertions.api.Assertions;

/**
 * <p>We create our own StringAssert class to add
 * two URL validations.</p>
 *
 * <p>If we need additional validations of pure
 * String values, we must add it to this class.</p>
 */
public class StringAssert extends org.fest.assertions.api.StringAssert {

    public StringAssert(final String actual) {
        super(actual);
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
