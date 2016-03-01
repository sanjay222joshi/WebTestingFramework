package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.validators.UrlValidators;


/**
 * <p>We create our own StringAssert class to add
 * two URL validations.</p>
 *
 * <p>If we need additional validations of pure
 * String values, we must add it to this class.</p>
 */
public class StringAssert extends org.assertj.core.api.StringAssert {

    /**
     * The constructor that matches the parent class.
     * @param actual the String to assert.
     */
    public StringAssert(final String actual) {
        super(actual);
    }

    /**
     * Evaluates if the String is a Brand
     * URL.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public StringAssert isBrandURL() {
        FamousSmokeAssertions
                .assertThat(UrlValidators.isBrandPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRAND,
                        actual)
                .isTrue();
        return this;
    }

    /**
     * Evaluates if the String is a Brand
     * Group URL.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public StringAssert isBrandGroupURL() {
        FamousSmokeAssertions
                .assertThat(UrlValidators.isBrandGroupPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_BRANDGROUP,
                        actual)
                .isTrue();
        return this;
    }

    public StringAssert isItemURL() {
        FamousSmokeAssertions
                .assertThat(UrlValidators.isBrandItemPage(actual))
                .overridingErrorMessage(
                        AssertionMessages.URL_NOT_ITEM,
                        actual)
                .isTrue();
        return this;
    }

}
