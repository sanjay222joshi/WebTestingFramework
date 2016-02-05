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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jorge on 27-12-2015.
 */
public class FeaturesProcessor {

    private static final String FEATURES_EXTENSION                = ".feature";
    private static final String TEMPLATES_EXTENSION               = ".template";
    private static final String FEATURES_TEMPLATES_FOLDER         = "src/test/resources/features-templates/";
    private static final String SEO_TEMPLATES_FOLDER              = FEATURES_TEMPLATES_FOLDER + "seo/";
    private static final String VALIDATION_TEMPLATES_FOLDER       = FEATURES_TEMPLATES_FOLDER + "validation/";
    private static final String ACTIONVALIDATION_TEMPLATES_FOLDER = FEATURES_TEMPLATES_FOLDER + "actionvalidation/";
    private static final String PROCESSED_FOLDER                  = "src/test/resources/features/processed/";
    private static final String LOAD_URLS_KEYWORD                 = "<LOAD_URLS>";
    private static final String LOAD_BRAND_URLS_KEYWORD           = "<LOAD_BRAND_URLS>";
    private static final String LOAD_GROUP_URLS_KEYWORD           = "<LOAD_BRAND_GROUP_URLS>";
    private static final String LINE_BREAKER                      = "\n";

    private FeaturesProcessor() {
        //not called
    }

    public static void processFeatures(final Collection<PageData> datas) {
        listTemplateFiles()
                .stream()
                .forEach(template -> createProcessedFeatureFile(template, datas));
    }

    public static boolean needToProcess() throws IOException {
        return listProcessedFiles().isEmpty();
    }

    private static List<Path> listTemplateFiles() {
        return Arrays.stream(new String[]{
                SEO_TEMPLATES_FOLDER,
                VALIDATION_TEMPLATES_FOLDER,
                ACTIONVALIDATION_TEMPLATES_FOLDER})
                .map(FeaturesProcessor::listTemplateFolder)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Path> listTemplateFolder(final String folderPath) {
        try {
            return Files.list(Paths.get(folderPath))
                    .filter(template -> template.getFileName().toString().endsWith(TEMPLATES_EXTENSION))
                    .collect(Collectors.toList());
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<Path> listProcessedFiles() {
        try {
            return Files
                    .list(Paths.get(PROCESSED_FOLDER))
                    .filter(feature -> feature.getFileName().toString().endsWith(FEATURES_EXTENSION))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createProcessedFeatureFile(final Path template,
                                                   final Collection<PageData> datas) {
        try {
            final String fileName = PROCESSED_FOLDER + template
                    .getFileName()
                    .toString()
                    .replace(TEMPLATES_EXTENSION, FEATURES_EXTENSION);
            return writeProcessedContent(
                    fileName,
                    readFeatureContent(template)
                            .replace(LOAD_URLS_KEYWORD, retrieveUrls(datas))
                            .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(datas))
                            .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(datas))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String retrieveUrls(final Collection<PageData> datas) {
        return reduceUrls(datas.stream()
                .map(PageData::getURL));
    }

    private static String retrieveBrandsUrls(final Collection<PageData> datas) {
        return reduceUrls(datas.stream()
                .map(PageData::getURL)
                .filter(UrlValidators::isBrandPage));
    }

    private static String retrieveBrandGroupsUrls(final Collection<PageData> datas) {
        return reduceUrls(datas.stream()
                .map(PageData::getURL)
                .filter(UrlValidators::isBrandGroupPage));
    }

    private static String reduceUrls(final Stream<String> urls) {
        return urls
                .reduce("", (urlAccumulator, url) ->
                        urlAccumulator + "| " + url + " | " + UUID.randomUUID().toString() + " |" + LINE_BREAKER);

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
