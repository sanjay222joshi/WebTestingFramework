package com.famous_smoke.automation.factory;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.api.CategoriesPageData;
import com.famous_smoke.automation.api.PageData;

import java.util.List;

/**
 * Created by jorge on 23-12-2015.
 */
public final class DataFactory {

    private DataFactory() {
        // not called
    }

    public static PageData createPage(final String url,
                                      final String canonical,
                                      final String title,
                                      final String metaDescription,
                                      final String breadcumbsText,
                                      final List<String> breadcumbs) {
        return new PageDataImpl(url, title, canonical,
                metaDescription,
                breadcumbsText, breadcumbs);

    }

    public static BrandPageData createBrand(final PageData pageData,
                                            final String header1,
                                            final String description) {
        return new BrandDataImpl(
                pageData.getURL(),
                pageData.getCanonical(),
                pageData.getTitle(),
                pageData.getMetaDescription(),
                pageData.getBreadcrumbsText(),
                pageData.getBreadcrumbs(),
                header1, description);
    }

    public static CategoriesPageData createCategories(final PageData pageData,
                                                      final List<String> links) {
        return new CategoriesPageDataImpl(
                pageData.getURL(),
                pageData.getCanonical(),
                pageData.getTitle(),
                pageData.getMetaDescription(),
                pageData.getBreadcrumbsText(),
                pageData.getBreadcrumbs(),
                links);
    }

    private static class PageDataImpl implements PageData {

        private final String url;
        private final String canonical;
        private final String title;
        private final String metaDescription;
        private final String breadcumbsText;
        private final List<String> breadcumbs;

        PageDataImpl(final String url,
                     final String canonical,
                     final String title,
                     final String metaDescription,
                     final String breadcumbsText,
                     final List<String> breadcumbs) {
            this.url = url;
            this.canonical = canonical;
            this.title = title;
            this.metaDescription = metaDescription;
            this.breadcumbsText = breadcumbsText;
            this.breadcumbs = breadcumbs;
        }

        @Override
        public String getURL() {
            return url;
        }

        @Override
        public String getCanonical() {
            return canonical;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getMetaDescription() {
            return metaDescription;
        }

        @Override
        public String getBreadcrumbsText() {
            return breadcumbsText;
        }

        @Override
        public List<String> getBreadcrumbs() {
            return breadcumbs;
        }

        @Override
        public boolean equals(final Object o) {
            if (o.getClass().isAssignableFrom(PageData.class)) {
                PageData comparable = (PageData) o;
                return this.url.equals(comparable.getURL());
            }
            return false;
        }
    }

    private static class BrandDataImpl
            extends PageDataImpl
            implements BrandPageData {


        private final String header1;
        private final String description;

        BrandDataImpl(final String url,
                      final String canonical,
                      final String title,
                      final String metaDescription,
                      final String breadcumbsText,
                      final List<String> breadcumbs,
                      final String header1,
                      final String description) {
            super(url, canonical, title,
                    metaDescription, breadcumbsText, breadcumbs);
            this.header1 = header1;
            this.description = description;
        }

        @Override
        public String getHeader1() {
            return header1;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    private static class CategoriesPageDataImpl
            extends PageDataImpl
            implements CategoriesPageData {

        private final List<String> links;

        CategoriesPageDataImpl(final String url,
                               final String canonical,
                               final String title,
                               final String metaDescription,
                               final String breadcumbsText,
                               final List<String> breadcumbs,
                               final List<String> links){
            super(url, title, canonical,
                    metaDescription,
                    breadcumbsText, breadcumbs);
            this.links = links;
        }

        @Override
        public List<String> getBrandsLinks() {
            return links;
        }
    }


}
