package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BasePageData;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

/**
 * <p>Custom <strong>FEST Assert</strong> class for the
 * BasePageData objects.</p>
 *
 * <p>We use this class through the FamousSmokeAssertions
 * class in order to make our assertions more readable when
 * they are used in the steps definitions.</p>
 *
 * <p>This class must extend the AbstractAssert class.</p>
 */
public class BasePageDataAssert extends AbstractAssert<BasePageDataAssert, BasePageData> {

    private static final String COLLECTION_SEPARATOR = ",";

    /**
     * Constructor matching the one requested by
     * AbstractAssert.
     * @param actual the BasePageData object we are going
     *               to evaluate.
     */
    public BasePageDataAssert(final BasePageData actual) {
        super(actual, BasePageDataAssert.class);
    }

    /**
     * Checks if the title field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BasePageDataAssert hasTitle() {
        return isNotEmpty(BasePageData.TITLE_FIELD_NAME, actual.getTitle());
    }

    /**
     * Compares the title field against a String
     * parameter.
     * @param expected the String we will compare our title agains.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasTitleEqualTo(final String expected){
        return isEqualTo(BasePageData.TITLE_FIELD_NAME, actual.getTitle(), expected);
    }

    /**
     * Validates that the title length is less
     * than the value passed as parameter.
     * @param max the maximum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasTitleLengthLessThan(final int max) {
        return isLessThan(BasePageData.TITLE_FIELD_NAME, actual.getTitle(), max);
    }

    /**
     * Compares the canonical url against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasCanonicalEqualTo(final String expected){
        return isEqualTo(BasePageData.CANONICAL_FIELD_NAME, actual.getCanonical(), expected);
    }

    /**
     * Compares the meta description against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasMetaDescriptionEqualTo(final String expected){
        return isEqualTo(BasePageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), expected);
    }

    /**
     * Validates that the meta description length is
     * greater than the value passed as parameter.
     * @param min the minimum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasMetaDescriptionLengthGreaterThan(final int min) {
        return isGreaterThan(BasePageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), min);
    }

    /**
     * Validates that the meta description length is
     * less than the value passed as parameter.
     * @param max the minimum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasMetaDescriptionLengthLessThan(final int max) {
        return isLessThan(BasePageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), max);
    }

    /**
     * Checks if the breadcrumbs text field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BasePageDataAssert hasBreadcrumbsText() {
        return isNotEmpty(BasePageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText());
    }

    /**
     * Compares the breadcrumbs text against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasBreadcrumbsTextEqualTo(final String expected){
        return isEqualTo(BasePageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText(), expected);
    }

    /**
     * Checks if the breadcrumbs list is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BasePageDataAssert hasBreadcrumbs() {
        return isNotEmpty(BasePageData.BREADCRUMBS_LINKS_FIELD_NAME, actual.getBreadcrumbsLinks());
    }

    /**
     * Compares the breadcrumbs list against another list
     * passed as parameter.
     *
     * Both lists are converted to Strings for their
     * evaluation.
     * @param expected the list to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BasePageDataAssert hasBreadcrumbsEqualTo(final List<String> expected){
        return isEqualTo(BasePageData.BREADCRUMBS_LINKS_FIELD_NAME,
                reduceCollectionToString(actual.getBreadcrumbsLinks()),
                reduceCollectionToString(expected));
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
    private BasePageDataAssert isNotEmpty(final String fieldName,
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
     * if the list is not empty.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @param fieldName the field name.
     * @param field the field value.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    private BasePageDataAssert isNotEmpty(final String fieldName,
                                          final List<String> field) {
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
    private BasePageDataAssert isEqualTo(final String fieldName,
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
     * Performs the FEST Assertion to check
     * if the field length is greater than the
     * parameter.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @param fieldName the field name.
     * @param field the field value.
     * @param min the minimum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    private BasePageDataAssert isGreaterThan(final String fieldName,
                                             final String field,
                                             final int min) {
        FamousSmokeAssertions
                .assertThat(field.length())
                .overridingErrorMessage(
                        AssertionMessages.FIELD_LENGTH_IS_LESS,
                        fieldName,
                        actual.getURL(),
                        field.length(),
                        min,
                        field)
                .isGreaterThanOrEqualTo(min);
        return this;
    }

    /**
     * Performs the FEST Assertion to check
     * if the field length is less than the
     * parameter.
     *
     * It overrides the error message with
     * the one defined as a constant in
     * AssertionMessages.
     * @param fieldName the field name.
     * @param field the field value.
     * @param max the maximum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    private BasePageDataAssert isLessThan(final String fieldName,
                                          final String field,
                                          final int max) {
        FamousSmokeAssertions
                .assertThat(field.length())
                .overridingErrorMessage(
                        AssertionMessages.FIELD_LENGTH_IS_MORE,
                        fieldName,
                        actual.getURL(),
                        field.length(),
                        max,
                        field)
                .isLessThanOrEqualTo(max);
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
