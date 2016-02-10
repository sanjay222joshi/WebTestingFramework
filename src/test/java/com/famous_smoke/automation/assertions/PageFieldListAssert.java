package com.famous_smoke.automation.assertions;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

import java.util.List;

/**
 * Created by jorge on 09-02-2016.
 */
public class PageFieldListAssert extends AbstractAssert<PageFieldListAssert, List<String>> {

    private final String url;
    private final String fieldName;

    public PageFieldListAssert(final String url,
                               final String fieldName,
                               final List<String> actual) {
        super(actual, PageFieldListAssert.class);
        this.url = url;
        this.fieldName = fieldName;
    }

    public static PageFieldListAssert assertThat(final String url,
                                                 final String fieldName,
                                                 final List<String> actual) {
        return new PageFieldListAssert(url, fieldName, actual);
    }


    public PageFieldListAssert isEqualTo(final List<String> expected) {
        Assertions.assertThat(actual)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_NOT_MATCHING,
                        fieldName,
                        url,
                        actual.stream().reduce("", (a, b) -> a + ", " + b),
                        expected.stream().reduce("", (a, b) -> a + ", " + b))
                .isEqualTo(expected);
        return this;
    }

    public PageFieldListAssert isNotEmpty() {
        Assertions.assertThat(actual)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_IS_EMPTY,
                        fieldName,
                        url)
                .isNotEmpty();
        return this;
    }
}
