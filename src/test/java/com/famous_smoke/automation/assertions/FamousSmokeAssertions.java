package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BrandPageData;
import org.fest.assertions.api.Assertions;

/**
 * <p>Entry point to the FEST Assertions.</p>
 *
 * <p>It extends from the Assertions class, so it
 * inherits all the static <strong>assertThat</strong>
 * functions defined on it.</p>
 *
 * <p>Besides the bulk of the inherited assertions
 * we have created two new assertion functions to override
 * the assertions of Strings and to assert BrandPageData objects.</p>
 */
public class FamousSmokeAssertions extends Assertions {

    /**
     * Asserts BrandPageData objects.
     * @param actual the BrandPageData to assert.
     * @return the BrandPageAssert object.
     */
    public static BrandPageDataAssert assertThat(final BrandPageData actual) {
        return new BrandPageDataAssert(actual);
    }

    /**
     * Asserts String objects.
     * @param actual the String to assert
     * @return the StringAssert object.
     */
    public static StringAssert assertThat(final String actual) {
        return new StringAssert(actual);
    }
}