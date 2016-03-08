package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.data.BrandItemPageData;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

/**
 * <p>Custom <strong>FEST Assert</strong> class for the
 * BrandItemPageData objects.</p>
 *
 * <p>We use this class through the FamousSmokeAssertions
 * class in order to make our assertions more readable when
 * they are used in the steps definitions.</p>
 *
 * <p>This class must extend the AbstractAssert class.</p>
 */
public class BrandItemPageDataAssert extends AbstractAssert<BrandItemPageDataAssert, BrandItemPageData> {

    private static final String COLLECTION_SEPARATOR = ",";

    /**
     * Constructor matching the one requested by
     * AbstractAssert.
     * @param actual the BrandItemPageData object we are going
     *               to evaluate.
     */
    public BrandItemPageDataAssert(final BrandItemPageData actual) {
        super(actual, BrandItemPageDataAssert.class);
    }

    /**
     * Checks if the header one field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandItemPageDataAssert hasHeader1() {
        return isNotEmpty(BrandItemPageData.HEADER1_FIELD_NAME, actual.getHeader1());
    }

    /**
     * Compares the header one against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandItemPageDataAssert hasHeader1EqualTo(final String expected){
        return isEqualTo(BrandItemPageData.HEADER1_FIELD_NAME, actual.getHeader1(), expected);
    }

    /**
     * Checks if the description field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandItemPageDataAssert hasDescription() {
        return isNotEmpty(BrandItemPageData.DESCRIPTION_FIELD_NAME, actual.getDescription());
    }

    /**
     * Compares the description against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandItemPageDataAssert hasDescriptionEqualTo(final String expected){
        return isEqualTo(BrandItemPageData.DESCRIPTION_FIELD_NAME, actual.getDescription(), expected);
    }

    /**
     * Checks if the identified field is true.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandItemPageDataAssert isIdentified() {
        FamousSmokeAssertions
                .assertThat(actual.getIdentified())
                .overridingErrorMessage(AssertionMessages.BRAND_NOT_IDENTIFIED, actual.getURL())
                .isTrue();
        return this;
    }

    /**
     * Performs the FEST Assertion to check
     * if the value is not empty.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @param fieldName the field name
     * @param field the field value
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    private BrandItemPageDataAssert isNotEmpty(final String fieldName,
                                               final String field) {
        FamousSmokeAssertions
                .assertThat(field)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_IS_EMPTY,
                        fieldName,
                        actual.getURL())
                .isNotEmpty();
        return this;
    }


    /**
     * Performs the FEST Assertion to check
     * if the value is equal to an expected
     * value.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @param fieldName the field name.
     * @param field the field value.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    private BrandItemPageDataAssert isEqualTo(final String fieldName,
                                              final String field,
                                              final String expected) {
        FamousSmokeAssertions
                .assertThat(field)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_NOT_MATCHING,
                        fieldName,
                        actual.getURL(),
                        field,
                        expected)
                .isEqualTo(expected);
        return this;
    }

    /**
     * Converts a list of Strings
     * to a single string separated by the constant.
     *
     * This is done through the Stream API using
     * a reduce operation, which is an accumulative
     * function that concatenates all the values of
     * the list together.
     * @param strings the strings list.
     * @return the String representation of the
     * strings list.
     */
    private String reduceCollectionToString(final List<String> strings) {
        return strings
                .stream()
                .reduce("", (a, b) -> a + COLLECTION_SEPARATOR + b);
    }

}
