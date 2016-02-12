package com.famous_smoke.automation.assertions;

import com.famous_smoke.automation.data.BrandPageData;
import org.fest.assertions.api.AbstractAssert;

import java.util.List;

/**
 * Created by jorge on 11-02-2016.
 */
public class BrandPageDataAssert extends AbstractAssert<BrandPageDataAssert, BrandPageData> {

    public BrandPageDataAssert(final BrandPageData actual) {
        super(actual, BrandPageDataAssert.class);
    }

    public BrandPageDataAssert hasTitle() {
        return isNotEmpty(BrandPageData.TITLE_FIELD_NAME, actual.getTitle());
    }

    public BrandPageDataAssert hasTitleEqualTo(final String expected){
        return isEqualTo(BrandPageData.TITLE_FIELD_NAME, actual.getTitle(), expected);
    }

    public BrandPageDataAssert hasTitleLengthGreaterThan(final int min) {
        return isGreaterThan(BrandPageData.TITLE_FIELD_NAME, actual.getTitle(), min);
    }

    public BrandPageDataAssert hasTitleLengthLessThan(final int max) {
        return isLessThan(BrandPageData.TITLE_FIELD_NAME, actual.getTitle(), max);
    }

    public BrandPageDataAssert hasCanonical() {
        return isNotEmpty(BrandPageData.CANONICAL_FIELD_NAME, actual.getCanonical());
    }

    public BrandPageDataAssert hasCanonicalEqualTo(final String expected){
        return isEqualTo(BrandPageData.CANONICAL_FIELD_NAME, actual.getCanonical(), expected);
    }

    public BrandPageDataAssert hasMetaDescription() {
        return isNotEmpty(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription());
    }

    public BrandPageDataAssert hasMetaDescriptionEqualTo(final String expected){
        return isEqualTo(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), expected);
    }

    public BrandPageDataAssert hasMetaDescriptionLengthGreaterThan(final int min) {
        return isGreaterThan(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), min);
    }

    public BrandPageDataAssert hasMetaDescriptionLengthLessThan(final int max) {
        return isLessThan(BrandPageData.METADESCRIPTION_FIELD_NAME, actual.getMetaDescription(), max);
    }

    public BrandPageDataAssert hasBreadcrumbsText() {
        return isNotEmpty(BrandPageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText());
    }

    public BrandPageDataAssert hasBreadcrumbsTextEqualTo(final String expected){
        return isEqualTo(BrandPageData.BREADCRUMBS_TEXT_FIELD_NAME, actual.getBreadcrumbsText(), expected);
    }

    public BrandPageDataAssert hasBreadcrumbs() {
        return isNotEmpty(BrandPageData.BREADCRUMBS_FIELD_NAME, actual.getBreadcrumbs());
    }

    public BrandPageDataAssert hasBreadcrumbsEqualTo(final List<String> expected){
        return isEqualTo(BrandPageData.BREADCRUMBS_FIELD_NAME, actual.getBreadcrumbs(), expected);
    }

    public BrandPageDataAssert hasHeader1() {
        return isNotEmpty(BrandPageData.HEADER1_FIELD_NAME, actual.getHeader1());
    }

    public BrandPageDataAssert hasHeader1EqualTo(final String expected){
        return isEqualTo(BrandPageData.HEADER1_FIELD_NAME, actual.getHeader1(), expected);
    }

    public BrandPageDataAssert hasDescription() {
        return isNotEmpty(BrandPageData.DESCRIPTION_FIELD_NAME, actual.getDescription());
    }

    public BrandPageDataAssert hasDescriptionEqualTo(final String expected){
        return isEqualTo(BrandPageData.DESCRIPTION_FIELD_NAME, actual.getDescription(), expected);
    }

    public BrandPageDataAssert isIdentified() {
        FamousSmokeAssertions
                .assertThat(actual.isIdentified())
                .overridingErrorMessage(AssertionMessages.BRAND_NOT_IDENTIFIED, actual.getURL())
                .isTrue();
        return this;
    }

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

    private BrandPageDataAssert isEqualTo(final String fieldName,
                                          final List<String> field,
                                          final List<String> expected) {
        FamousSmokeAssertions
                .assertThat(field)
                .overridingErrorMessage(
                        AssertionMessages.FIELD_NOT_MATCHING,
                        fieldName,
                        actual.getURL(),
                        field.stream().reduce("", (a, b) -> a + BrandPageData.BREADCRUMBS_SEPARATOR + b),
                        expected.stream().reduce("", (a, b) -> a + BrandPageData.BREADCRUMBS_SEPARATOR + b))
                .isEqualTo(expected);
        return this;
    }
    
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

}
