package com.famous_smoke.automation.pageobjects;

import com.famous_smoke.automation.data.BasePageData;
import com.famous_smoke.automation.data.BrandItemPageData;
import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.data.DataFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.famous_smoke.automation.util.SeleniumFinder.findElementsByCss;

/**
 * Created by jorge on 15-02-2016.
 */
public class BrandItemPage extends BasePage {

    private static final String RATING_SPLITTER = "\n";

    @FindBy(css = PageConstants.ITEM_HEADER1_CSS)
    private static WebElement header1;
    @FindBy(css = PageConstants.ITEM_DESCRIPTION_CSS)
    private static WebElement description;
    @FindBy(css = PageConstants.ITEM_SPECS_CSS)
    private static List<WebElement> specs;
    @FindBy(css = PageConstants.ITEM_PRICING_CSS)
    private static WebElement pricing;
    @FindBy(css = PageConstants.ITEM_RATING_CSS)
    private static WebElement rating;
    @FindBy(css = PageConstants.ITEM_IMAGE_CSS)
    private static WebElement image;
    @FindBy(css = PageConstants.ITEM_VIDEO_CSS)
    private static WebElement video;

    public static boolean hasHeader1() {
        return hasCSSElement(PageConstants.ITEM_HEADER1_CSS);
    }

    public static boolean hasDescription() {
        return hasCSSElement(PageConstants.ITEM_DESCRIPTION_CSS);
    }

    public static boolean hasSpecs() {
        return hasCSSElement(PageConstants.ITEM_SPECS_CSS);
    }

    public static boolean hasPricing() {
        return hasCSSElement(PageConstants.ITEM_PRICING_CSS);
    }

    public static boolean hasRating() {
        return hasCSSElement(PageConstants.ITEM_RATING_CSS);
    }

    public static boolean hasImage() {
        return hasCSSElement(PageConstants.ITEM_IMAGE_CSS);
    }

    public static boolean hasVideo() {
        return hasCSSElement(PageConstants.ITEM_VIDEO_CSS);
    }

    public static boolean isIdentified() {
        return hasVideo() || hasImage();
    }

    public static BrandItemPageData getItemData() {
        String header1Text = extractElementText(header1, hasHeader1());
        String descriptionText = extractElementText(description, hasDescription());
        String ratingText = extractElementText(rating, hasRating());
        if (!ratingText.isEmpty()) {
            ratingText = ratingText.split(RATING_SPLITTER)[0];
        }
        String pricingText = extractElementText(pricing, hasPricing());

        Collection<String> specsTexts = specs.stream()
                .map(spec -> findElementsByCss(spec, PageConstants.ITEM_SPECS_TEXT_CSS))
                .flatMap(Collection::stream)
                .map(WebElement::getText)
                .collect(Collectors.toSet());

        return DataFactory.createBrandItemPage(
                getBasePageData(),
                header1Text,
                descriptionText,
                specsTexts,
                pricingText,
                ratingText,
                isIdentified()
        );
    }

}
