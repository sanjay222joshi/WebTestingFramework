package com.famous_smoke.automation.assertions;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

/**
 * Created by jorge on 09-02-2016.
 */
public class PageFieldAssert extends AbstractAssert<PageFieldAssert, String> {

    private final String url;
    private final String fieldName;

    public PageFieldAssert(final String url,
                           final String fieldName,
                           final String actual) {
        super(actual, PageFieldAssert.class);
        this.url = url;
        this.fieldName = fieldName;
    }

    public static PageFieldAssert assertThat(final String url,
                                             final String fieldName,
                                             final String actual) {
        return new PageFieldAssert(url, fieldName, actual);
    }

    public PageFieldAssert isNotEmpty() {
        Assertions.assertThat(actual)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_IS_EMPTY,
                        fieldName,
                        url)
                .isNotEmpty();
        return this;
    }

    public PageFieldAssert isEqualTo(final String expected) {
        Assertions.assertThat(actual)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_NOT_MATCHING,
                        fieldName,
                        url,
                        actual,
                        expected)
                .isEqualTo(expected);
        return this;
    }

    public PageFieldAssert hasLengthGreaterThan(final int min) {
        Assertions.assertThat(actual.length())
                .overridingErrorMessage(
                        AssertionMessages.FIELD_LENGTH_IS_LESS,
                        fieldName,
                        url,
                        actual.length(),
                        min,
                        actual)
                .isGreaterThanOrEqualTo(min);
        return this;
    }

    public PageFieldAssert hasLengthLessThan(final int max) {
        Assertions.assertThat(actual.length())
                .overridingErrorMessage(
                        AssertionMessages.FIELD_LENGTH_IS_MORE,
                        fieldName,
                        url,
                        actual.length(),
                        max,
                        actual)
                .isLessThanOrEqualTo(max);
        return this;
    }
}
