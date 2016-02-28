package com.famous_smoke.automation.util;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.validators.UrlValidators;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final String BRANDS_FEATURES_TEMPLATES_FOLDER = FEATURES_TEMPLATES_FOLDER + "brands/";
    private static final String ITEMS_FEATURES_TEMPLATES_FOLDER  = FEATURES_TEMPLATES_FOLDER + "items/";
    private static final String BRANDS_ACTIONVALIDATION_FOLDER   = BRANDS_FEATURES_TEMPLATES_FOLDER + "actionvalidation/";
    private static final String BRANDS_SEO_FOLDER                = BRANDS_FEATURES_TEMPLATES_FOLDER + "seo/";
    private static final String BRANDS_VALIDATION_FOLDER         = BRANDS_FEATURES_TEMPLATES_FOLDER + "validation/";
    private static final String ITEMS_ACTIONVALIDATION_FOLDER    = ITEMS_FEATURES_TEMPLATES_FOLDER + "actionvalidation/";
    private static final String ITEMS_SEO_FOLDER                 = ITEMS_FEATURES_TEMPLATES_FOLDER + "seo/";
    private static final String ITEMS_VALIDATION_FOLDER          = ITEMS_FEATURES_TEMPLATES_FOLDER + "validation/";
    private static final String PROCESSED_FOLDER                 = "src/test/resources/features/processed/";
    private static final String LOAD_URLS_KEYWORD                = "<LOAD_URLS>";
    private static final String LOAD_BRAND_URLS_KEYWORD          = "<LOAD_BRAND_URLS>";
    private static final String LOAD_GROUP_URLS_KEYWORD          = "<LOAD_BRAND_GROUP_URLS>";
    private static final String LINE_BREAKER                     = "\n";

    private FeaturesProcessor() {
        //not called
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
        return listProcessedFiles().size() < listBrandsTemplateFiles().size();
    }

    public static boolean needToProcessItems() throws IOException {
        return listProcessedFiles().size() < listItemsTemplateFiles().size();
    }

    /**
     * Creates a List of Paths representing the
     * templates files in the folders.
     * @return the Path List representing the
     * template files.
     */
    private static List<Path> listBrandsTemplateFiles() {
        return Arrays.stream(new String[]{
                BRANDS_SEO_FOLDER,
                BRANDS_VALIDATION_FOLDER,
                BRANDS_ACTIONVALIDATION_FOLDER})
                .map(FeaturesProcessor::listTemplateFolder)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Path> listItemsTemplateFiles() {
        return Arrays.stream(new String[]{
                ITEMS_SEO_FOLDER,
                ITEMS_VALIDATION_FOLDER,
                ITEMS_ACTIONVALIDATION_FOLDER})
                .map(FeaturesProcessor::listTemplateFolder)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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
                            .replace(LOAD_URLS_KEYWORD, reduceUrls(urls.stream()))
                            .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(urls))
                            .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(urls))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts the BrandPageData collection in a
     * String containing the URLs of all the elements
     * of the collection.
     * @param datas the BrandPageData collection.
     * @return a String with the URLs and Scenarios IDs.
     */
    private static String retrieveUrls(final Collection<BrandPageData> datas) {
        return reduceUrls(datas.stream()
                .map(BasePageData::getURL));
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

    /***
     * Convers a Stream of String in a single String.
     * @param urls the Stream of URLs Strings.
     * @return the string containing the URLs and the
     * Scenario ID.
     */
    private static String reduceUrls(final Stream<String> urls) {
        return urls
                .reduce("", (urlAccumulator, url) ->
                        urlAccumulator + "| " + url + " | " + UUID.randomUUID().toString() + " |" + LINE_BREAKER);

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
