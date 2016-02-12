package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BrandPageData;
import org.fest.assertions.api.Assertions;

/**
 * Created by jorge on 11-02-2016.
 */
public class FamousSmokeAssertions extends Assertions {

    public static BrandPageDataAssert assertThat(final BrandPageData actual) {
        return new BrandPageDataAssert(actual);
    }

    public static StringAssert assertThat(final String actual) {
        return new StringAssert(actual);
    }
}
