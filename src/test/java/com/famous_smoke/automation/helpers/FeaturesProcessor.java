package com.famous_smoke.automation.helpers;

import com.famous_smoke.automation.api.PageData;
import com.famous_smoke.automation.validators.UrlValidators;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jorge on 27-12-2015.
 */
public class FeaturesProcessor {

    private static final String FEATURES_EXTENSION               = ".feature";
    private static final String FEATURES_FOLDER                  = "src/test/resources/features/";
    private static final String SEO_FEATURES_FOLDER              = FEATURES_FOLDER + "seo/";
    private static final String VALIDATION_FEATURES_FOLDER       = FEATURES_FOLDER + "validation/";
    private static final String ACTIONVALIDATION_FEATURES_FOLDER = FEATURES_FOLDER + "actionvalidation/";
    private static final String PROCESSED_FOLDER                 = "src/test/resources/features-processed/";
    private static final String LOAD_URLS_KEYWORD                = "<LOAD_URLS>";
    private static final String LOAD_BRAND_URLS_KEYWORD          = "<LOAD_BRAND_URLS>";
    private static final String LOAD_GROUP_URLS_KEYWORD          = "<LOAD_BRAND_GROUP_URLS>";
    private static final String SETUP_FEATURE                    = "ExtractBrandPagesData.feature";
    private static final String LINE_BREAKER                     = "\n";

    private FeaturesProcessor() {
        //not called
    }

    public static void processFeatures(final List<PageData> datas) {
        listFeatureFiles()
                .stream()
                .forEach(feature -> createProcessedFile(feature, datas));
    }

    public static boolean needToProcess() throws IOException {
        return listProcessedFiles().size() < 2;
    }

    private static List<Path> listFeatureFiles() {
        return Arrays.stream(new String[]{
                        SEO_FEATURES_FOLDER,
                        VALIDATION_FEATURES_FOLDER,
                        ACTIONVALIDATION_FEATURES_FOLDER})
                .map(FeaturesProcessor::listFeatureFilesFolder)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Path> listFeatureFilesFolder(final String folderPath) {
        try {
            return Files.list(Paths.get(folderPath))
                    .collect(Collectors.toList());
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<Path> listProcessedFiles() {
        try {
            return Files
                    .list(Paths.get(PROCESSED_FOLDER))
                    .filter(path -> path.endsWith(FEATURES_EXTENSION))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createProcessedFile(final Path feature,
                                            final List<PageData> datas) {
        try {
            return writeProcessedContent(
                    PROCESSED_FOLDER + feature.getFileName(),
                    readFeatureContent(feature)
                            .replace(LOAD_URLS_KEYWORD, retrieveUrls(datas))
                            .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(datas))
                            .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(datas))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String retrieveUrls(final List<PageData> datas) {
        return datas.stream()
                .map(PageData::getURL)
                .reduce("", (urls, url) -> urls + "| " + url + " |" + LINE_BREAKER);
    }

    private static String retrieveBrandsUrls(final List<PageData> datas) {
        return datas.stream()
                .map(PageData::getURL)
                .filter(UrlValidators::isBrandPage)
                .reduce("", (urls, url) -> urls + "| " + url + " |" + LINE_BREAKER);
    }

    private static String retrieveBrandGroupsUrls(final List<PageData> datas) {
        return datas.stream()
                .map(PageData::getURL)
                .filter(UrlValidators::isBrandGroupPage)
                .reduce("", (urls, url) -> urls + "| " + url + " |" + LINE_BREAKER);
    }

    private static String readFeatureContent(final Path feature) throws IOException {
        return Files.lines(feature)
                .reduce("", (lines, line) -> lines + line + LINE_BREAKER);
    }

    private static Path writeProcessedContent(final String fileName,
                                              final String content)
        throws IOException {
        return Files.write(Paths.get(fileName), Arrays.asList(content.split(LINE_BREAKER)));
    }

}
