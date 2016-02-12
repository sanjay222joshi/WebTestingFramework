package com.famous_smoke.automation.util;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BrandPageData;
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

    /**
     * The extension with which the features files
     * will be created.
     */
    private static final String FEATURES_EXTENSION                = ".feature";
    /**
     * The extension of the template files.
     */
    private static final String TEMPLATES_EXTENSION               = ".template";
    /**
     * The base folder for all the templates.
     */
    private static final String FEATURES_TEMPLATES_FOLDER         = "src/test/resources/features-templates/";
    /**
     * The folder for the Action Validation
     * templates.
     */
    private static final String ACTIONVALIDATION_TEMPLATES_FOLDER = FEATURES_TEMPLATES_FOLDER + "actionvalidation/";
    /**
     * The folder for the SEO templates.
     */
    private static final String SEO_TEMPLATES_FOLDER              = FEATURES_TEMPLATES_FOLDER + "seo/";
    /**
     * The folder for the Validation templates.
     */
    private static final String VALIDATION_TEMPLATES_FOLDER       = FEATURES_TEMPLATES_FOLDER + "validation/";
    /**
     * The folder where the processed features
     * are going to be stored.
     */
    private static final String PROCESSED_FOLDER                  = "src/test/resources/features/processed/";
    /**
     * The TAG which will be replaced with
     * all the scrapped URLs.
     */
    private static final String LOAD_URLS_KEYWORD                 = "<LOAD_URLS>";
    /**
     * The TAG which will be replaced with
     * the scrapped Brand URLs.
     */
    private static final String LOAD_BRAND_URLS_KEYWORD           = "<LOAD_BRAND_URLS>";
    /**
     * The TAG which will be replaced with
     * the scrapped Brand Group URLs.
     */
    private static final String LOAD_GROUP_URLS_KEYWORD           = "<LOAD_BRAND_GROUP_URLS>";
    /**
     * The new line separator.
     */
    private static final String LINE_BREAKER                      = "\n";

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
        listTemplateFiles()
                .stream()
                .forEach(template -> createProcessedFeatureFile(template, datas));
    }

    /**
     * Determines if it is needed to process the templates.
     * @return true if the number of processed files
     * differ from the number of template files.
     * @throws IOException
     */
    public static boolean needToProcess() throws IOException {
        return listProcessedFiles().size() != listTemplateFiles().size();
    }

    /**
     * Creates a List of Paths representing the
     * templates files in the folders.
     * @return the Path List representing the
     * template files.
     */
    private static List<Path> listTemplateFiles() {
        return Arrays.stream(new String[]{
                SEO_TEMPLATES_FOLDER,
                VALIDATION_TEMPLATES_FOLDER,
                ACTIONVALIDATION_TEMPLATES_FOLDER})
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
     * @param datas the scrapped BrandPageData containing
     *              the URLs.
     * @return the Path of the feature file.
     * @throws RuntimeException if there is any IOException.
     */
    private static Path createProcessedFeatureFile(final Path template,
                                                   final Collection<BrandPageData> datas) {
        try {
            final String fileName = PROCESSED_FOLDER + template
                    .getFileName()
                    .toString()
                    .replace(TEMPLATES_EXTENSION, FEATURES_EXTENSION);
            return writeProcessedContent(
                    fileName,
                    readTemplateContent(template)
                            .replace(LOAD_URLS_KEYWORD, retrieveUrls(datas))
                            .replace(LOAD_BRAND_URLS_KEYWORD, retrieveBrandsUrls(datas))
                            .replace(LOAD_GROUP_URLS_KEYWORD, retrieveBrandGroupsUrls(datas))
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
     * @param datas the BrandPageData collection.
     * @return a String with the Brands URLs and
     * Scenarios IDs.
     */
    private static String retrieveBrandsUrls(final Collection<BrandPageData> datas) {
        return reduceUrls(datas.stream()
                .map(BasePageData::getURL)
                .filter(UrlValidators::isBrandPage));
    }

    /**
     * Converts the BrandPageData collection in a
     * String containing the URLs of the Brand Group
     * elements of the collection.
     * @param datas the BrandPageData collection.
     * @return a String with the Brand Group URLs
     * and Scenarios IDs.
     */
    private static String retrieveBrandGroupsUrls(final Collection<BrandPageData> datas) {
        return reduceUrls(datas.stream()
                .map(BasePageData::getURL)
                .filter(UrlValidators::isBrandGroupPage));
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
