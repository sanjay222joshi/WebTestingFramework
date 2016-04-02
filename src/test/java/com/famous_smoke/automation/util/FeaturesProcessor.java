package com.famous_smoke.automation.util;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.validators.UrlValidators;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Processes the features template files, to attach
 * the scrapped URLS.</p>
 *
 * <p>It creates all the features files that go to the
 * <strong>features/processed</strong> folder.</p>
 *
 * <p>It also assigns a random UUID to each URL as
 * Scenario ID. This is necessary for the correct
 * generation of the reports.</p>
 */
public class FeaturesProcessor {

    private static final String FEATURES_EXTENSION               = ".feature";
    private static final String TEMPLATES_EXTENSION              = ".template";
    private static final String FEATURES_TEMPLATES_FOLDER        = "src/test/resources/features-templates/";
    private static final String BASE_FEATURES_TEMPLATES_FOLDER   = FEATURES_TEMPLATES_FOLDER + "base/";
    private static final String BRANDS_FEATURES_TEMPLATES_FOLDER = FEATURES_TEMPLATES_FOLDER + "brands/";
    private static final String ITEMS_FEATURES_TEMPLATES_FOLDER  = FEATURES_TEMPLATES_FOLDER + "items/";
    private static final String PROCESSED_FOLDER                 = "src/test/resources/features/processed/";
    private static final String LOAD_BASE_URLS_KEYWORD           = "<LOAD_BASE_URLS>";
    private static final String LOAD_BRAND_URLS_KEYWORD          = "<LOAD_BRAND_URLS>";
    private static final String LOAD_GROUP_URLS_KEYWORD          = "<LOAD_BRAND_GROUP_URLS>";
    private static final String LOAD_ITEM_URLS_KEYWORD           = "<LOAD_ITEM_URLS>";
    private static final String LINE_BREAKER                     = "\n";

    private FeaturesProcessor() {
        //not called
    }

    public static void processBaseFeatures(final Collection<BasePageData> datas) {
        processFeatures(listBaseTemplateFiles(), new ArrayList<>(datas));
    }

    /**
     * Retrieves all the template files and creates
     * a feature file for each of them.
     * @param datas the BrandPageData collection
     *              containing all the scrapped data.
     */
    public static void processBrandFeatures(final Collection<BrandPageData> datas) {
        processFeatures(listBrandsTemplateFiles(), new ArrayList<>(datas));
    }

    public static void processItemsFeatures(final Collection<BrandItemPageData> datas) {
        processFeatures(listItemsTemplateFiles(), new ArrayList<>(datas));
    }

    private static void processFeatures(final Collection<Path> templateFiles,
                                        final Collection<BasePageData> datas) {
        Collection<String> urls = datas
                .stream()
                .map(BasePageData::getURL)
                .collect(Collectors.toList());
        templateFiles
                .stream()
                .forEach(template -> createProcessedFeatureFile(template, urls));
    }

    /**
     * Determines if it is needed to process the templates.
     * @return true if the number of processed files
     * differ from the number of template files.
     * @throws IOException
     */
    public static boolean needToProcessBrands() throws IOException {
        return needToProcess(
                convertTemplateNamesToFeatureNames(listBrandsTemplateFiles())
        );
    }

    public static boolean needToProcessItems() throws IOException {
        return needToProcess(
                convertTemplateNamesToFeatureNames(listItemsTemplateFiles())
        );
    }

    private static boolean needToProcess(final List<String> fileNames) {
        return !listProcessedFiles()
                .stream()
                .map(processed -> processed.getFileName().toString())
                .collect(Collectors.toList())
                .containsAll(fileNames);
    }

    private static List<String> convertTemplateNamesToFeatureNames(final Collection<Path> templates) {
        return templates
                .stream()
                .map(template -> template.getFileName().toString())
                .map(name -> name.replace(TEMPLATES_EXTENSION, FEATURES_EXTENSION))
                .collect(Collectors.toList());
    }

    private static List<Path> listBaseTemplateFiles() {
        return listTemplateFolder(BASE_FEATURES_TEMPLATES_FOLDER);
    }

    private static List<Path> listBrandsTemplateFiles() {
        return listTemplateFolder(BRANDS_FEATURES_TEMPLATES_FOLDER);
    }

    private static List<Path> listItemsTemplateFiles() {
        return listTemplateFolder(ITEMS_FEATURES_TEMPLATES_FOLDER);
    }

    /**
     * Creates a List of Paths with the templates
     * files that exist inside a given folder.
     * @param folderPath the folder path in String
     *                   form.
     * @return the Path List of template files inside
     * the folder.
     * @throws RuntimeException if there is any
     * IOException.
     */
    private static List<Path> listTemplateFolder(final String folderPath) {
        try {
            return Files.list(Paths.get(folderPath))
                    .filter(template -> template.getFileName().toString().endsWith(TEMPLATES_EXTENSION))
                    .collect(Collectors.toList());
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Retrieves a List of Paths with the
     * processed feature files in the Processed
     * folder.
     * @return the List of Paths representing
     * the feature files.
     */
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

    /**
     * Creates a new feature File in the processed folder
     * based on the content of a template file.
     *
     * It replaces the KEYWORDS with the urls and Scenarios
     * IDs.
     * @param template the Path of the template file
     * @param urls the scrapped BrandPageData containing
     *              the URLs.
     * @return the Path of the feature file.
     * @throws RuntimeException if there is any IOException.
     */
    private static Path createProcessedFeatureFile(final Path template,
                                                   final Collection<String> urls) {
        try {
            final String fileName = PROCESSED_FOLDER + template
                    .getFileName()
                    .toString()
                    .replace(TEMPLATES_EXTENSION, FEATURES_EXTENSION);
            return writeProcessedContent(
                    fileName,
                    readTemplateContent(template)
                            .replace(LOAD_BASE_URLS_KEYWORD, reduceUrls(urls.stream()))
                            .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(urls))
                            .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(urls))
                            .replace(LOAD_ITEM_URLS_KEYWORD, retrieveItemsUrls(urls))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts the BrandPageData collection in a
     * String containing the URLs of the Brand elements
     * of the collection.
     * @param urls the BrandPageData collection.
     * @return a String with the Brands URLs and
     * Scenarios IDs.
     */
    private static String retrieveBrandsUrls(final Collection<String> urls) {
        return reduceUrls(
                urls.stream().filter(UrlValidators::isBrandPage)
        );
    }

    /**
     * Converts the BrandPageData collection in a
     * String containing the URLs of the Brand Group
     * elements of the collection.
     * @param urls the BrandPageData collection.
     * @return a String with the Brand Group URLs
     * and Scenarios IDs.
     */
    private static String retrieveBrandGroupsUrls(final Collection<String> urls) {
        return reduceUrls(
                urls.stream().filter(UrlValidators::isBrandGroupPage)
        );
    }

    private static String retrieveItemsUrls(final Collection<String> urls) {
        return reduceUrls(
                urls.stream().filter(UrlValidators::isBrandItemPage)
        );
    }

    /***
     * Convers a Stream of String in a single String.
     * @param urls the Stream of URLs Strings.
     * @return the string containing the URLs and the
     * Scenario ID.
     */
    private static String reduceUrls(final Stream<String> urls) {
        return urls
                .filter(url -> !url.isEmpty())
                .reduce("", (urlAccumulator, url) ->
                        urlAccumulator + "| "
                                + url  + " | "
                                + UUID.randomUUID().toString() + "-" + Instant.now().toEpochMilli() + " |"
                                + LINE_BREAKER);

    }

    /**
     * Reads the content of a template file.
     * @param template the Path fo the template file
     * @return the content of the template as a String.
     * @throws IOException
     */
    private static String readTemplateContent(final Path template) throws IOException {
        return Files.lines(template)
                .reduce("", (lines, line) -> lines + line + LINE_BREAKER);
    }

    /**
     * Writes the processed content inside a feature file.
     * @param fileName the name of the feature file.
     * @param content the String content.
     * @return the Path of the processed file.
     * @throws IOException
     */
    private static Path writeProcessedContent(final String fileName,
                                              final String content)
        throws IOException {
        return Files.write(Paths.get(fileName), Arrays.asList(content.split(LINE_BREAKER)));
    }

}
