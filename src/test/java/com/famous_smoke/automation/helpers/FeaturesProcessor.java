package com.famous_smoke.automation.helpers;

import com.famous_smoke.automation.api.PageData;
import com.famous_smoke.automation.validators.UrlValidators;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void processFeatures(List<PageData> datas) throws IOException {
        List<File> features = listFeatureFiles();
        for (File feature : features) {
            if (feature.getName().equals(SETUP_FEATURE)) {
                continue;
            }
            File processed = createProcessedFile(feature, datas);
            System.out.println("Feature " + processed.getName() + " processed." );
        }
    }

    public static boolean needToProcess() throws IOException {
        return listProcessedFiles().isEmpty();
    }

    private static List<File> listFeatureFiles() throws IOException {
        ArrayList<File> features = new ArrayList<>();
        Arrays.stream(new String[]{
                SEO_FEATURES_FOLDER,
                VALIDATION_FEATURES_FOLDER,
                ACTIONVALIDATION_FEATURES_FOLDER
        }).forEach(folder -> features.addAll(listFeatureFiles(folder)));
        return features;
    }

    private static List<File> listFeatureFiles(final String folderPath) {
        File folder = new File(folderPath);
        File[] features = folder.listFiles(new FeaturesFilter());
        return Arrays.asList(features);
    }

    private static List<File> listProcessedFiles() throws IOException {
        File processedFolder = new File(PROCESSED_FOLDER);
        return Arrays.asList(processedFolder.listFiles(new FeaturesFilter()));
    }

    private static File createProcessedFile(final File feature,
                                            final List<PageData> datas)
            throws IOException {
        File processedFolder = retrieveProcessedFolder();
        File processed = new File(processedFolder.toString() + "/" + feature.getName());
        processed.createNewFile();
        writeProcessedContent(
                processed,
                readFeatureContent(feature)
                        .replace(LOAD_URLS_KEYWORD, retrieveUrls(datas))
                        .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(datas))
                        .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(datas))
        );
        return processed;
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

    private static File retrieveProcessedFolder() throws IOException {
        File processedFolder = new File(PROCESSED_FOLDER);
        processedFolder.createNewFile();
        return processedFolder;
    }

    private static String readFeatureContent(final File feature) throws IOException {
        return Files.lines(feature.toPath())
                .reduce("", (lines, line) -> lines + line + LINE_BREAKER);
    }

    private static void writeProcessedContent(final File processed,
                                                final String content)
        throws IOException {
        Files.write(processed.toPath(), Arrays.asList(content.split(LINE_BREAKER)));
    }

    private static class FeaturesFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(FEATURES_EXTENSION);
        }
    }

}
