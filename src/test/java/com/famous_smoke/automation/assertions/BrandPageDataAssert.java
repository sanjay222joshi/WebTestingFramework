package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BrandPageData;
import org.fest.assertions.api.AbstractAssert;

import java.util.List;

/**
 * <p>Custom <strong>FEST Assert</strong> class for the
 * BrandPageData objects.</p>
 *
 * <p>We use this class through the FamousSmokeAssertions
 * class in order to make our assertions more readable when
 * they are used in the steps definitions.</p>
 *
 * <p>This class must extend the AbstractAssert class.</p>
 */
public class BrandPageDataAssert extends AbstractAssert<BrandPageDataAssert, BrandPageData> {

    /**
     * Constructor matching the one requested by
     * AbstractAssert.
     * @param actual the BrandPageData object we are going
     *               to evaluate.
     */
    public BrandPageDataAssert(final BrandPageData actual) {
        super(actual, BrandPageDataAssert.class);
    }

    /**
     * Checks if the title field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert hasTitle() {
        return isNotEmpty(BrandPageData.TITLE_FIELD_NAME, actual.getTitle());
    }

    /**
     * Compares the title field against a String
     * parameter.
     * @param expected the String we will compare our title agains.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasTitleEqualTo(final String expected){
        return isEqualTo(BrandPageData.TITLE_FIELD_NAME, actual.getTitle(), expected);
    }

    /**
     * Validates that the title length is less
     * than the value passed as parameter.
     * @param max the maximum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasTitleLengthLessThan(final int max) {
        return isLessThan(BrandPageData.TITLE_FIELD_NAME, actual.getTitle(), max);
    }

    /**
     * Compares the canonical url against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasCanonicalEqualTo(final String expected){
        return isEqualTo(BrandPageData.CANONICAL_FIELD_NAME, actual.getCanonical(), expected);
    }

    /**
     * Compares the meta description against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasMetaDescriptionEqualTo(final String expected){
        return isEqualTo(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), expected);
    }

    /**
     * Validates that the meta description length is
     * greater than the value passed as parameter.
     * @param min the minimum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasMetaDescriptionLengthGreaterThan(final int min) {
        return isGreaterThan(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), min);
    }

    /**
     * Validates that the meta description length is
     * less than the value passed as parameter.
     * @param max the minimum length allowed.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasMetaDescriptionLengthLessThan(final int max) {
        return isLessThan(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), max);
    }

    /**
     * Checks if the breadcrumbs text field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert hasBreadcrumbsText() {
        return isNotEmpty(BrandPageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText());
    }

    /**
     * Compares the breadcrumbs text against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasBreadcrumbsTextEqualTo(final String expected){
        return isEqualTo(BrandPageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText(), expected);
    }

    /**
     * Checks if the breadcrumbs list is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert hasBreadcrumbs() {
        return isNotEmpty(BrandPageData.BREADCRUMBS_LINKS_FIELD_NAME, actual.getBreadcrumbsLinks());
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
    public BrandPageDataAssert hasBreadcrumbsEqualTo(final List<String> expected){
        return isEqualTo(BrandPageData.BREADCRUMBS_LINKS_FIELD_NAME,
                reduceBreadcrumbsList(actual.getBreadcrumbsLinks()),
                reduceBreadcrumbsList(expected));
    }

    /**
     * Checks if the header one field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert hasHeader1() {
        return isNotEmpty(BrandPageData.HEADER1_FIELD_NAME, actual.getHeader1());
    }

    /**
     * Compares the header one against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasHeader1EqualTo(final String expected){
        return isEqualTo(BrandPageData.HEADER1_FIELD_NAME, actual.getHeader1(), expected);
    }

    /**
     * Checks if the description field is not empty.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert hasDescription() {
        return isNotEmpty(BrandPageData.DESCRIPTION_FIELD_NAME, actual.getDescription());
    }

    /**
     * Compares the description against a value
     * passed as parameter.
     * @param expected the value to which we'll compare.
     * @return the current Assert object; this is done
     * to allow chain assertions
     */
    public BrandPageDataAssert hasDescriptionEqualTo(final String expected){
        return isEqualTo(BrandPageData.DESCRIPTION_FIELD_NAME, actual.getDescription(), expected);
    }

    /**
     * Checks if the identified field is true.
     * @return the current Assert object; this is done
     * to allow chain assertions.
     */
    public BrandPageDataAssert isIdentified() {
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
    private BrandPageDataAssert isNotEmpty(final String fieldName,
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
    private BrandPageDataAssert isNotEmpty(final String fieldName,
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
    private BrandPageDataAssert isEqualTo(final String fieldName,
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
    private BrandPageDataAssert isGreaterThan(final String fieldName,
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
    private BrandPageDataAssert isLessThan(final String fieldName,
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
     * to a single string separated by the constant
     * BrandPageData.BREADCRUMBS_LINKS_SEPARATOR.
     *
     * This is done through the Stream API using
     * a reduce operation, which is an accumulative
     * function that concatenates all the values of
     * the list together.
     * @param breadcrumbs the breadcrumbs list.
     * @return the String representation of the
     * breadcrumbs list.
     */
    private String reduceBreadcrumbsList(final List<String> breadcrumbs) {
        return breadcrumbs
                .stream()
                .reduce("", (a, b) -> a + BrandPageData.BREADCRUMBS_LINKS_SEPARATOR + b);
    }

}
